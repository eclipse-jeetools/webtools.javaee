/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    BEA Systems, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.classpath.tests;

import org.eclipse.wst.common.tests.OperationTestCase;

/**
 * Abstract base class for classpath dependency unit tests
 * 
 * @author rfrost@bea.com
 */
public abstract class AbstractTests extends OperationTestCase {
    
    protected AbstractTests(final String name) {
        super(name);
    }
}
