/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal.workingsets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.wst.common.navigator.internal.plugin.WorkbenchNavigatorPlugin;
import org.eclipse.wst.common.navigator.internal.views.extensions.RegistryReader;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComponentWorkingSetRegistry {
	private static final ComponentWorkingSetRegistry INSTANCE = new ComponentWorkingSetRegistry();
	private static boolean isInitialized = false;
	private static final String[] NO_DESCRIPTORS = new String[0];
	private List commonWorkingSetDescriptors = new ArrayList();

	/**
	 * 
	 */
	public ComponentWorkingSetRegistry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		new ComponentWorkingSetRegistryReader().readRegistry();
	}

	/**
	 *  
	 */
	public static ComponentWorkingSetRegistry getInstance() {
		if (isInitialized)
			return INSTANCE;
		synchronized (INSTANCE) {
			if (!isInitialized) {
				INSTANCE.init();
				isInitialized = true;
			}
		}
		return INSTANCE;
	}
	
	/**
	 * @param aDesc
	 */
	private void addCommonWorkingSetDescriptor(ComponentWorkingSetDescriptor aDesc) {
		if (aDesc == null)
			return;
		synchronized (commonWorkingSetDescriptors) {
			boolean bValue = commonWorkingSetDescriptors.contains(aDesc);
			if (bValue == false) {
				commonWorkingSetDescriptors.add(aDesc);
			}
		}
	}
	
	public ComponentWorkingSetDescriptor[] getComponentWorkingSetDescriptors() {
		ComponentWorkingSetDescriptor[] descriptors = new ComponentWorkingSetDescriptor[commonWorkingSetDescriptors.size()];
		return (ComponentWorkingSetDescriptor[])commonWorkingSetDescriptors.toArray(descriptors);
		
	}
	
	public boolean containsId(String id) {
		ComponentWorkingSetDescriptor descriptor = null;
		for (int x=0; x< commonWorkingSetDescriptors.size(); ++x) {
			descriptor = (ComponentWorkingSetDescriptor)commonWorkingSetDescriptors.get(x);
			if (descriptor.getId().equals(id)) 
				return true;
		}
		return false;
	}
	
	class ComponentWorkingSetRegistryReader extends RegistryReader {

		private static final String COMPONENT_WORKING_SET = "componentWorkingSet"; //$NON-NLS-1$


		ComponentWorkingSetRegistryReader() {
			super(J2EENavigatorPlugin.PLUGIN_ID, COMPONENT_WORKING_SET);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.wst.common.navigator.internal.views.extensions.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
		 */
		protected boolean readElement(IConfigurationElement anElement) {
			if (COMPONENT_WORKING_SET.equals(anElement.getName())) {
				try {
					addCommonWorkingSetDescriptor(new ComponentWorkingSetDescriptor(anElement));
					return true;
				} catch (WorkbenchException e) {
					//	 log an error since its not safe to open a dialog here
					WorkbenchNavigatorPlugin.log("Unable to create common working set descriptor.", e.getStatus());//$NON-NLS-1$
				}
			}
			return false;
		}
	}

	/**
	 * @param editPageId
	 * @param typeId
	 * @return
	 */
	public ComponentWorkingSetDescriptor getWorkingSetDescriptor(String editPageId, String typeId) {
		ComponentWorkingSetDescriptor[] descriptors = getComponentWorkingSetDescriptors();
		ComponentWorkingSetDescriptor descriptor = null;
		for (int x=0; x< descriptors.length ; ++x) {
			descriptor = descriptors[x];
			if (descriptor.getId().equals(editPageId) 
					&& descriptor.getTypeId().equals(typeId))
				return descriptor;
		}
		return null;
	}

}
