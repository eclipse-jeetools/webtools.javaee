/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMCallbackInputStream.java,v $
 *  $Revision: 1.4 $  $Date: 2004/08/27 15:35:20 $ 
 */

import java.io.*;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.internal.proxy.core.ProxyPlugin;
import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.common.remote.IOCommandException;
/**
 * This is the inputstream used to return a lot of data from
 * the server.
 */

public class REMCallbackInputStream extends InputStream {
	
	protected DataInputStream fIn;
	protected DataOutputStream fOut;
	protected byte[] fBuffer;
	protected int fNextByte = 0;
	protected int fLastByte = 0;
	
	public REMCallbackInputStream(DataInputStream in, DataOutputStream out) {
		fIn = in;
		fOut = out;
		
		Integer bufSize = Integer.getInteger("proxyvm.bufsize"); //$NON-NLS-1$
		if (bufSize == null)
			bufSize = new Integer(16000);		
		fBuffer = new byte[bufSize.intValue()];
	}
	
	protected void clearStream() {
		fIn = null;
		fOut = null;
		fBuffer = null;
	}
	
	protected void fillBuffer() throws IOException {
		if (fIn == null)
			throw new InterruptedIOException();
		try {
			if (fLastByte != -1) {
				fLastByte = Commands.readBytes(fIn, fBuffer);
				if (fLastByte != -1)
					fNextByte = 0;
				else {
					fNextByte = -1;
				}
			}	
		} catch (CommandException e) {
			try {
				close(false);
			} catch (IOException e1) {
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e1));	// Error during close, just log it.
			}
			throw new IOCommandException(e);
		}
	}
			
	public int read() throws IOException {
		if (fNextByte >= fLastByte) {
			fillBuffer();
		}
		if (fLastByte == -1)
			return -1;	// We've previously reached the end.		
		
		return ((int) fBuffer[fNextByte++]) & 0xFF;	// So that it is in range 0 to 255.
	}
	
	public int read(byte b[], int off, int len) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		} else if ((off < 0) || (off > b.length) || (len < 0) ||
			((off + len) > b.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		}
		
		int read = 0;
		while (len > 0) {
			if (fNextByte >= fLastByte)
				fillBuffer();
			if (fLastByte == -1)
				return read != 0 ? read : -1;	// No more, return amount read, or if none read, then -1
			int move = fLastByte - fNextByte;
			if (move > len)
				move = len;
			System.arraycopy(fBuffer, fNextByte, b, off, move);
			read += move;
			fNextByte += move;
			off += move;
			len -= move;
		}
		
		return read;
	}
			
	public int available() throws IOException {
		return fLastByte != -1 ? fLastByte - fNextByte : 0;
	}
	
	public void close() throws IOException {
		close(true);
	}
	
	protected void close(boolean wait) throws IOException {
		if (fOut == null)
			return;	// Already closed.
		try {
			fOut.writeByte(Commands.CALLBACK_STREAM_TERMINATE);
			fOut.flush();
			fOut = null;	// So that we know we closed already if error occurs during in read() below.
			if (wait) {
				// Need to read until End of stream because we need to flush out the
				// stream so that next time we use this connection we start fresh.
				// If the server has already finished writing, we will read until then.
				// If the server has not yet finished writing, when it reads the terminate
				// request it will send an end of stream marker for us to read. But either
				// way we can't leave until we've read everything from the stream.
				
				// If called with no wait, then we are closing hard and will be closing the socket too.
				while (read() != -1) {
				}
			}
		} finally {
			clearStream();
		}
	}
}
