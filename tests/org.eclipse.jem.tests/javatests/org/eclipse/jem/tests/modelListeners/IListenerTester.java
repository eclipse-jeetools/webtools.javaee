/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IListenerTester.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 23:00:16 $ 
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
