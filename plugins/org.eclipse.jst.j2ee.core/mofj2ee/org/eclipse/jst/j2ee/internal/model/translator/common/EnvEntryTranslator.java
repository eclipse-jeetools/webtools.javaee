/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.model.translator.common;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.xml.DeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class EnvEntryTranslator extends Translator implements DeploymentDescriptorXmlMapperI {
	private boolean isJ2EE14;
	private static Translator[] children;
	private static Translator[] reorderedChildren;
	//Hacks because of inconsistencies in the servlet spec
	boolean typeBeforeValue = true;
	/**
	 * Constructor for EnvEntryTranslator.
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public EnvEntryTranslator(EStructuralFeature aFeature, boolean writeTypeBeforeValue, boolean isJ2EE14) {
		super(ENV_ENTRY, aFeature);
		typeBeforeValue = writeTypeBeforeValue;
		this.isJ2EE14 = isJ2EE14;
	}

	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren()
	 */
	public Translator[] getChildren(Object obj, int versionID) {
		//Hava to you create a different one each time
		children = createChildren();
		setReorderedChildren();
		
		if (typeBeforeValue)
			return children;
		
		return reorderedChildren;
	}

	protected Translator[] createChildren() {
		CommonPackage ePackageCommon = CommonPackage.eINSTANCE;
		Translator descriptionTranslator;
		if (isJ2EE14)
			descriptionTranslator = CommonTranslators.createDescriptionTranslator(ePackageCommon.getEnvEntry_Descriptions()); 
		else 
			descriptionTranslator = new Translator(DESCRIPTION, ePackageCommon.getEnvEntry_Description());
			
		return new Translator[] {
			IDTranslator.INSTANCE,				
			descriptionTranslator,
			new Translator(ENV_ENTRY_NAME, ePackageCommon.getEnvEntry_Name()),
			new EnvEntryTypeTranslator(), 
			new Translator(ENV_ENTRY_VALUE, ePackageCommon.getEnvEntry_Value())
		};
	}
	
	protected void setReorderedChildren() {
		reorderedChildren = new Translator[children.length];
		System.arraycopy(children, 0, reorderedChildren, 0, children.length);
		Translator temp = reorderedChildren[3];
		reorderedChildren[3] = reorderedChildren[4];
		reorderedChildren[4] = temp;
	}


}
