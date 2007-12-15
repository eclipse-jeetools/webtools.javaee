/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;

public class BusinessInterface {
	
	private IType interfaceType;
    private String interfaceName;
    
    private boolean isRemote;
    private boolean isLocal;
    
    public BusinessInterface(IType type, boolean remote, boolean local) {
        this.interfaceType = type;
        this.interfaceName = interfaceType.getFullyQualifiedName(); 
        this.isRemote = remote;
        this.isLocal = local;
    }
    
    public BusinessInterface(String name, boolean remote, boolean local) {
        this.interfaceType = null;
        this.interfaceName = name; 
        this.isRemote = remote;
        this.isLocal = local;
    }
    
    public IType getInterfaceType() {
        return interfaceType;
    }
    
    public String getInterfaceName() {
        return interfaceName;
    }
    
    public boolean isRemote() {
        return isRemote;
    }
    
    public boolean isLocal() {
        return isLocal;
    }
    
    public String getSimpleName(){
		return Signature.getSimpleName(interfaceName);
	}
    
    public boolean hasUnimplementedMethod() throws JavaModelException {
    	if (interfaceType != null){
    		IMethod[] methods = interfaceType.getMethods();
    		for (IMethod method : methods){
    			boolean resolved = method.isResolved();
    			if (!resolved){
    				int flags = method.getFlags();
    				if (Flags.isAbstract(flags) || Flags.isInterface(flags)) {
    					method.getParameterTypes();
    				}
    			}
    		}
    	}
    	return false;
    }
}
