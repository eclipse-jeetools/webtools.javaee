package org.eclipse.jem.internal.proxy.vm.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: CallbackOutputStream.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.io.OutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.IOCommandException;
/**
 * This is the special stream used to buffer and write a lot
 * of bytes to callback into the client.
 *
 * Before writing any bytes to the client, it will see if the
 * client has sent anything back. If it has, this is an indication
 * that that client has requested that the stream be closed. In that
 * case an InterruptedIOException is raised to indicate that
 * the stream will be closed and no more data will be sent.
 */
public class CallbackOutputStream extends OutputStream {
	
	protected CallbackHandler fHandler;
	protected RemoteVMServerThread fSvr;
	protected byte[] fBuffer;
	protected int fNextByte = 0;
	
	public CallbackOutputStream(CallbackHandler handler, RemoteVMServerThread svr) {
		fHandler = handler;
		fSvr = svr;
		
		Integer bufSize = Integer.getInteger("proxyvm.bufsize"); //$NON-NLS-1$
		if (bufSize == null)
			bufSize = new Integer(5000);
		fBuffer = new byte[bufSize.intValue()];
	}
	
	protected void clearStream() {
		fSvr.returnCallbackHandler(fHandler);
		fSvr = null;
		fHandler = null;
		fBuffer = null;
	}
	
	protected void processException(CommandException e) throws IOException {
		clearStream();
		throw new IOCommandException(e);
	}
	
	public void flush() throws IOException {
		flushBuffer();
		fHandler.out.flush();
	}

	protected void flushBuffer() throws IOException {
		if (fHandler == null)
			throw new IOException("Stream closed. No more operations permitted."); //$NON-NLS-1$
		if (fNextByte > 0) {
			try {
				if (fHandler.in.available() > 0) {
					// There was something waiting. This means a cancel has been requested.
					fHandler.in.skip(fHandler.in.available());
					close(false);	// Now close the stream. We need to have the close indicator sent so that the remote side, but we don't need to wait because the terminate response has already been sent.
					throw new InterruptedIOException();
				}
				fHandler.writeBytes(fBuffer, fNextByte);
				fNextByte = 0;
			} catch (CommandException e) {
				processException(e);
			}
		}
	}
	
	/**
	 * Closes this output stream and releases any system resources 
	 * associated with this stream. The general contract of <code>close</code> 
	 * is that it closes the output stream. A closed stream cannot perform 
	 * output operations and cannot be reopened.
	 * <p>
	 * Close will send a -1 to the client and set to indicate it is closed.
	 *
	 * @exception  IOException  if an I/O error occurs.
	 */
	public void close() throws IOException {
		close(true);
	}
	

	protected void close(boolean wait) throws IOException {
		if (fHandler != null) {
			try {
				flushBuffer();
				try {				
					fHandler.writeBytes(null, -1);
					fHandler.out.flush();
					if (wait) {
						// Wait means wait for the remote side to send the terminated response.
						// A normal close will do this. If the remote side sent a cancel request, then it has already
						// sent the terminated response and it is waiting for us to send the end-of-file command.
						
						fHandler.in.readByte();	// Block and wait until the terminate request. There shouldn't be anything else in the pipeline.
						if (fHandler.in.available() > 0)
							// There was something else waiting. Let's just clear it out to be safe.
							fHandler.in.skip(fHandler.in.available());
					}
				} catch (CommandException e) {
					processException(e);
				}
			} finally {
				clearStream();
			}
		}
	}
	
	public void write(int b) throws IOException {
		fBuffer[fNextByte++] = (byte) b;
		if (fNextByte >= fBuffer.length)
			flushBuffer();
	}
	
	public void write(byte b[], int off, int len) throws IOException {
		if (b == null) {
		    throw new NullPointerException();
		} else if ((off < 0) || (off > b.length) || (len < 0) ||
			   ((off + len) > b.length) || ((off + len) < 0)) {
		    throw new IndexOutOfBoundsException();
		} else if (len == 0) {
		    return;
		}
		
		while (len > 0) {
			int move = fBuffer.length-fNextByte;
			if (len < move)
				move = len;
			System.arraycopy(b, off, fBuffer, fNextByte, move);
			len -= move;
			off += move;
			fNextByte += move;
			if (fNextByte >= fBuffer.length)
				flushBuffer();
		}
	}

}