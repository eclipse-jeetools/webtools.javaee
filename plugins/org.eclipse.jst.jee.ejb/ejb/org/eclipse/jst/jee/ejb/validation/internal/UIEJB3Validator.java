/*******************************************************************************
 * Copyright (c) 2010 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ejb.validation.internal;

import java.text.MessageFormat;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.jee.ejb.Activator;
import org.eclipse.wst.validation.AbstractValidator;
import org.eclipse.wst.validation.ValidationEvent;
import org.eclipse.wst.validation.ValidationResult;
import org.eclipse.wst.validation.ValidationState;
import org.eclipse.wst.validation.Validator;
import org.eclipse.wst.validation.ValidatorMessage;
import org.eclipse.wst.validation.internal.MarkerManager;
import org.eclipse.wst.validation.internal.ValManager;
import org.eclipse.wst.validation.internal.ValOperation;

@SuppressWarnings("restriction")
public class UIEJB3Validator extends AbstractValidator {

	private static final String NO_BEANS_MESSAGE = "org.eclipse.jst.jee.ejb3.nobeansmessage"; //$NON-NLS-1$
	
	public static String ID = "org.eclipse.jst.jee.ejb3.validator"; //$NON-NLS-1$
	public static String MARKER_ID_NO_BEANS = "org.eclipse.jst.jee.ejb3.nobeans"; //$NON-NLS-1$
		
	@Override
	public void validationStarting(IProject project, ValidationState state, IProgressMonitor monitor){		
		Validator v = ValManager.getDefault().getValidator(UIEJB3Validator.ID, project);
		if (project != null) 
			ValManager.getDefault().validate(v, new ValOperation(), project, IResourceDelta.NO_CHANGE, new NullProgressMonitor(), null);
	}	
	
	@Override
	public ValidationResult validate(IResource resource, int kind, ValidationState state, IProgressMonitor monitor) {
		if ((resource == null) || !IProject.class.isInstance(resource))  
			return null;
		IProject proj = (IProject)resource;
		try {
			MarkerManager.getDefault().clearMarker(proj, getParent());
		} catch (CoreException e) {
			Activator.logError(e);
		}
		if (!JavaEEProjectUtilities.isEJBProject(proj)) 
			return null;
		EJBJar ejbJar = (EJBJar)ModelProviderManager.getModelProvider(proj).getModelObject();
		EnterpriseBeans ebs = ejbJar.getEnterpriseBeans();
		if (ebs == null || ebs.getSessionBeans().size() + ebs.getMessageDrivenBeans().size() + ebs.getEntityBeans().size() == 0) {
			ValidationResult vRes = new ValidationResult();
			ValidatorMessage vMsg = createValidatorMessage(
					Messages.NO_BEANS_ERROR, 
					proj, 
					getParent().getMessage(NO_BEANS_MESSAGE).getCurrent().getMarkerSeverity(), 
					IMarker.PRIORITY_HIGH); 
			vRes.add(vMsg);
			return vRes;
		}		
		return null;
	}	
	
	@Override
	public ValidationResult validate(ValidationEvent event, ValidationState state, IProgressMonitor monitor){
		IResource res = event.getResource();
		return validate(res, 0, state, monitor);
	}	
	
	private static ValidatorMessage createValidatorMessage(String txt, 
			  IResource res,
			  int severity,
			  int priority) {
		ValidatorMessage msg = ValidatorMessage.create(txt, res);
		msg.setAttribute(IMarker.SEVERITY, severity);
		msg.setAttribute(IMarker.PRIORITY, priority);
		msg.setAttribute(IMarker.LOCATION, MessageFormat.format(Messages.NO_BEANS_ERROR_LOCATION, new Object[] { res.getName() }));
		msg.setType(MARKER_ID_NO_BEANS); 
		return msg;
	}	
	
}
