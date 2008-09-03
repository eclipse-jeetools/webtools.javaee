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
	
	public enum BusinessInterfaceType {
		LOCAL, REMOTE;
	}
	
	private IType javaType;
    private String name;
    
    private BusinessInterfaceType type;
    
    public BusinessInterface(IType javaType, BusinessInterfaceType type) {
        this(javaType.getFullyQualifiedName(), type); 
        this.javaType = javaType;
    }
    
    public BusinessInterface(String name, BusinessInterfaceType type) {
        this.name = name; 
        this.type = type;
    }
    
    public String getFullyQualifiedName() {
        return name;
    }
    
    public String getSimpleName() {
		return Signature.getSimpleName(name);
	}
    
    public IType getJavaType() {
        return javaType;
    }
    
    public boolean exists() {
    	return javaType != null;
    }
    
    public BusinessInterfaceType getType() {
    	return type;
    }
    
    public boolean isLocal() {
        return type == BusinessInterfaceType.LOCAL;
    }
    
    public boolean isRemote() {
        return type == BusinessInterfaceType.REMOTE;
    }
    
    public boolean hasUnimplementedMethod() throws JavaModelException {
    	if (javaType != null) {
    		IMethod[] methods = javaType.getMethods();
    		for (IMethod method : methods) {
    			boolean resolved = method.isResolved();
    			if (!resolved) {
    				int flags = method.getFlags();
    				if (Flags.isAbstract(flags) || Flags.isInterface(flags)) {
    					method.getParameterTypes();
    				}
    			}
    		}
    	}
    	return false;
    }

    public void setFullyQualifiedName(String newName) {
        name = newName;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessInterface other = (BusinessInterface) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
}
