/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IListenerTester.java,v $
 *  $Revision: 1.1 $  $Date: 2004/06/09 22:47:00 $ 
 */
package org.eclipse.jem.tests.modelListeners;

import junit.framework.AssertionFailedError;
 

/**
 * For many of the listener tests, the actual Assert is done on a safe runnable so that it can't be 
 * normally sent. So we will have a listener tester that will retrieve the exception and will
 * be called to get it from the main thread.
 * @since 1.0.0
 */
public interface IListenerTester {

	public void isException() throws AssertionFailedError;
	public void isComplete() throws AssertionFailedError;
}
