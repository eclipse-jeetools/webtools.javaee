/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class ModulesItemProvider extends J2EEItemProvider {
	public static final String MODULES = J2EEUIMessages.getResourceString("Modules_UI_"); //$NON-NLS-1$

	/**
	 * Constructor for ModulesItemProvider.
	 */
	public ModulesItemProvider() {
		super();
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param children
	 */
	public ModulesItemProvider(Collection children) {
		super(children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 */
	public ModulesItemProvider(String text) {
		super(text);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param children
	 */
	public ModulesItemProvider(String text, Collection children) {
		super(text, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 */
	public ModulesItemProvider(String text, Object image) {
		super(text, image);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param children
	 */
	public ModulesItemProvider(String text, Object image, Collection children) {
		super(text, image, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param parent
	 */
	public ModulesItemProvider(String text, Object image, Object parent) {
		super(text, image, parent);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param text
	 * @param image
	 * @param parent
	 * @param children
	 */
	public ModulesItemProvider(String text, Object image, Object parent, Collection children) {
		super(text, image, parent, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text) {
		super(adapterFactory, text);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image) {
		super(adapterFactory, text, image);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param parent
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image, Object parent) {
		super(adapterFactory, text, image, parent);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, Collection children) {
		super(adapterFactory, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Collection children) {
		super(adapterFactory, text, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image, Collection children) {
		super(adapterFactory, text, image, children);
	}

	/**
	 * Constructor for ModulesItemProvider.
	 * 
	 * @param adapterFactory
	 * @param text
	 * @param image
	 * @param parent
	 * @param children
	 */
	public ModulesItemProvider(AdapterFactory adapterFactory, String text, Object image, Object parent, Collection children) {
		super(adapterFactory, text, image, parent, children);
	}


	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(Object)
	 */
	@Override
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("folder"); //$NON-NLS-1$
	}

	public Application getParentApplication() {
		return (Application) getParent();
	}

	@Override
	public IFile getAssociatedFile() {

		try {
			Application application = getParentApplication();
			if (application != null && application.eResource() != null) {
				return WorkbenchResourceHelperBase.getIFile(application.eResource().getURI());
			}
		} catch (Throwable t) {

		}
		return null;
	}


	/**
	 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getText(Object)
	 */
	@Override
	public String getText(Object object) {
		return MODULES;
	}

	@Override
	public boolean hasChildren(Object object) {
		getChildren(object);
		return !localChildren.isEmpty();
	}

	private List localChildren = null;

	@Override
	public Collection getChildren(Object object) {
		return initChildren(object);
	}

	protected List initChildren(Object object) {
		localChildren = new ArrayList();
		Application app = (Application) getParent();
		IVirtualComponent ear = ComponentUtilities.findComponent(app);
		List modules = app.getModules();
		List binaryModules = new ArrayList();
		IVirtualReference[] refs = ear.getReferences();
		for (int i = 0; i < modules.size(); i++) {
			Module module = (Module) modules.get(i);
			String moduleURI = module.getUri();
			boolean foundBinary = false;
			if(moduleURI != null) {
				String moduleName = new Path(moduleURI).lastSegment();
				for (int j = 0; j < refs.length && !foundBinary; j++) {
					IVirtualComponent component = refs[j].getReferencedComponent();
					if (component.isBinary()) {
						if (refs[j].getArchiveName().equals(moduleName)) {
							foundBinary = true;
							JavaEEBinaryComponentHelper helper = null;
							try {
								helper = new JavaEEBinaryComponentHelper(component);
								Object binaryModule = helper.getPrimaryRootObject();
								if (binaryModule != null) {
									binaryModules.add(binaryModule);
								}
							} finally {
								if(helper != null){
									helper.dispose();
								}
							}
						}
					}
				}
			}
			if (!foundBinary) {
				localChildren.add(module);
			}
		}

		if (!binaryModules.isEmpty()) {
			localChildren.add(new J2EEBinaryModulesItemProvider(app, getAdapterFactory(), binaryModules));
		}

		return localChildren;

	}
}
