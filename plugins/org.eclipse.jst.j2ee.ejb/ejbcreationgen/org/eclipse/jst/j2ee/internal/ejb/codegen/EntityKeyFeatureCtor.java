/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.codegen;



import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaConstructorGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTopLevelGenerationHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.MergeResults;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityKeyFeatureCtor extends JavaConstructorGenerator {
	private boolean fOldCtorHadNoParms = false;
	protected IEJBCodegenHandler codegenHandler;

	/**
	 * EntityKeyFeatureCtor constructor comment.
	 */
	public EntityKeyFeatureCtor() {
		super();
	}

	/**
	 * Dispatch to the merge strategy. This method is here simply to provide a hook for derived
	 * generators. We hook this to make sure we do not delete the default constructor from the key
	 * class.
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMethodHistoryDescriptor methodHistory, IDOMMethod newMethod) throws GenerationException {
		MergeResults mr = super.dispatchToMergeStrategy(methodHistory, newMethod);
		if (fOldCtorHadNoParms) {
			mr.setOldMember(null);
			if (mr.isDeleteOnly())
				mr.setGenerate(false);
		}
		return mr;
	}

	/**
	 * Assigns each parm to a key field.
	 */
	protected void getBody(IGenerationBuffer bodyBuf) throws GenerationException {
		bodyBuf.indent();

		// Next the key attributes.
		Entity entity = (Entity) getSourceElement();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		JavaParameterDescriptor[] parmDescs = EJBGenHelpers.getEntityKeyAttributeFieldsAsParms(entity, topHelper, getSourceContext().getNavigator());
		if ((parmDescs != null) && (parmDescs.length > 0)) {
			String[] parmName = new String[1];
			for (int i = 0; i < parmDescs.length; i++) {
				parmName[0] = parmDescs[i].getName();
				bodyBuf.formatWithMargin("this.%0 = %0;\n", parmName);//$NON-NLS-1$
			}
		}

		// Next do the roles using the privateSetXxxxKey methods.
		// NOT YET!
		List keyRoles = EJBGenHelpers.getEntityKeyRoles(entity, topHelper, getSourceContext().getNavigator());
		CommonRelationshipRole role;
		String[] args;
		for (int i = 0; i < keyRoles.size(); i++) {
			role = (CommonRelationshipRole) keyRoles.get(i);
			args = new String[2];
			args[0] = RoleHelper.getKeySetterName(role);
			args[1] = RoleHelper.getParameterName(role);
			bodyBuf.formatWithMargin("%0(%1);\n", args);//$NON-NLS-1$
		}

		bodyBuf.unindent();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Creates a key for Entity Bean: " + ((Entity) getSourceElement()).getName() + IEJBGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}

	/**
	 * The new context is the parameter.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		JavaParameterDescriptor[] parmDescs = EJBGenHelpers.getEntityKeyFieldsAsRoundParms(entity, topHelper, getSourceContext().getNavigator());
		return parmDescs;
	}

	/**
	 * This implementation sets the mofObject as the source element and checks old field info in the
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);

		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		Entity oldBean = (Entity) topHelper.getOldMetaObject();
		initEJBCodegenHandler();
		if (codegenHandler != null && oldBean != null && getSupertype(oldBean) != null)
			return;
		if ((oldBean != null) && (oldBean.getPrimaryKeyName() != null)) {
			JavaMethodHistoryDescriptor historyDesc = new JavaMethodHistoryDescriptor();
			historyDesc.setName(Signature.getSimpleName(oldBean.getPrimaryKeyName()));

			JavaParameterDescriptor[] parmDescs = EJBGenHelpers.getEntityKeyFieldsAsRoundParms(oldBean, topHelper, null);

			if (parmDescs.length > 0) {
				String[] parmTypes = new String[parmDescs.length];
				for (int i = 0; i < parmTypes.length; i++)
					parmTypes[i] = parmDescs[i].getType();
				historyDesc.setParameterTypes(parmTypes);
			} else {
				fOldCtorHadNoParms = true;
			}

			setHistoryDescriptor(historyDesc);
		}
	}

	protected EnterpriseBean getSupertype(Entity entity) {
		EjbModuleExtensionHelper helper = getEjbModuleExtension();
		if (helper != null)
			return helper.getSuperType(entity);
		return null;
	}

	protected EjbModuleExtensionHelper getEjbModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}


	protected void initEJBCodegenHandler() {
		if (codegenHandler == null) {
			try {
				IProject project = ((JavaTopLevelGenerationHelper) getTopLevelHelper()).getJavaProject().getProject();
				codegenHandler = EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(project);
			} catch (GenerationException e) {
				Logger.getLogger().logError(e);
			}
		}
	}
}