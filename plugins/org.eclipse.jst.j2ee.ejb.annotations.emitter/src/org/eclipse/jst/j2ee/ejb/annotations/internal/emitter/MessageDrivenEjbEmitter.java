/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.emitter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean;

public class MessageDrivenEjbEmitter extends EjbEmitter {
	public static final String TYPE_COMMENT = "/typeComment.javajet";
	public static final String TYPE_STUB = "/typeStub.javajet";
	public static final String METHOD_STUBS = "/methodStubs.javajet";
	public static final String FIELDS = "/fields.javajet";
	public static final String TEMPLATESDIR = "messageTemplates";
	public MessageDrivenEjbEmitter(IConfigurationElement emitterConfig)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException {
		super(emitterConfig);
	}
	public String emitTypeComment(IEnterpriseBean root)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException {
		return generate(emitterConfig.getAttribute(TEMPLATESDIR), TYPE_COMMENT,
				root);
	}
	public String emitTypeStub(IEnterpriseBean root)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException {
		return generate(emitterConfig.getAttribute(TEMPLATESDIR), TYPE_STUB,
				root);
	}
	public String emitInterfaceMethods(IEnterpriseBean root)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException {
		return generate(emitterConfig.getAttribute(TEMPLATESDIR), METHOD_STUBS,
				root);
	}
	public String emitFields(IEnterpriseBean root) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, CoreException {
		return generate(emitterConfig.getAttribute(TEMPLATESDIR), FIELDS, root);
	}
}