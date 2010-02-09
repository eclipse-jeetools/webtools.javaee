/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.dialogs.TypeSelectionExtension;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddButtonListener implements SelectionListener {

	private static final String SEARCH_FILTER = "**"; //$NON-NLS-1$
	private static final String EMPTY = ""; //$NON-NLS-1$
	
	private AddSessionBeanWizardPage page;
	private IDataModel model;
	
	public AddButtonListener(AddSessionBeanWizardPage page, IDataModel model) {
		this.page = page;
		this.model = model;
	}
	
	public void widgetSelected(SelectionEvent e) {
		BusinessInterface iface = chooseEnclosingType(getRoots(), new String[] { "All_APIs" },  //$NON-NLS-1$
				page.getShell(), page.getWizard().getContainer(),
				IJavaSearchConstants.INTERFACE, EMPTY);
		
		if (iface != null) {
			IType type = iface.getJavaType();
			if (type != null) {
				String text = type.getFullyQualifiedName();
				List<BusinessInterface> biList = (List<BusinessInterface>) model.getProperty(INTERFACES);
				if (!hasInterface(text, biList)) {
					biList.add(iface);
					model.setProperty(INTERFACES, biList);
					model.notifyPropertyChange(INTERFACES, IDataModel.VALUE_CHG);
					page.updateBusinessInterfacesList();
				}
			}
		}
	}
	
	private IPackageFragmentRoot[] getRoots() {
		return null;
	}
	
	public BusinessInterface chooseEnclosingType(
			IPackageFragmentRoot[] root,
			String[] jdkTypes, 
			Shell shell, 
			IRunnableContext container,
			int type, 
			String currentSelection) {
		
		BusinessInterface ret = null;
		String currSelection = SEARCH_FILTER;
		IJavaSearchScope scope = buildJavaSearchScope(root, jdkTypes);

        if (currentSelection != null && !currentSelection.equals(EMPTY)) {
            currSelection = currentSelection;
        }
        TypeSelectionExtension selectionExtension = createTypeSelectionExtension();
		BusinessInterfaceSelectionDialog dialog = new BusinessInterfaceSelectionDialog(shell, false, null, scope, type,
				selectionExtension);
        dialog.setTitle(EJBUIMessages.chooseInterface);
        dialog.setMessage(EJBUIMessages.chooseInterface);
        dialog.setInitialPattern(currSelection);
        if (dialog.open() == BusinessInterfaceSelectionDialog.OK) {
            ret = dialog.getResult()[0];
        }
        
        return ret;
    }
	
	public boolean hasInterface(String text, List<BusinessInterface> biList) {
		
        for (Iterator<BusinessInterface> i = biList.iterator(); i.hasNext(); ) {
            BusinessInterface element = i.next();
            if (element.getFullyQualifiedName().equals(text)) {
            	return true;
            }
        }
        return false;
    }

	public void widgetDefaultSelected(SelectionEvent e) {
	}
	
	private static IJavaSearchScope buildJavaSearchScope(IPackageFragmentRoot[] root, String[] jdkTypes) {
        IJavaProject project = null;
        ArrayList<IPackageFragmentRoot> pkgRoots = new ArrayList<IPackageFragmentRoot>();

        if (root != null) {
            if (root.length == 1 && (root[0] != null)) {
                project = root[0].getJavaProject();
                pkgRoots.add(root[0]);
            } else
                pkgRoots.addAll(Arrays.asList(root));
        }
        
        if (jdkTypes != null) {
            IJavaProject[] prjs = { project };
            if (project == null) {
            	IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
            	List<IJavaProject> javaProjects = new ArrayList<IJavaProject>();
            	for (IProject p : projects) {
            		javaProjects.add(JavaCore.create(p));
            	}
            	prjs = javaProjects.toArray(new IJavaProject[javaProjects.size()]);
            }

            for (int i = 0; prjs != null && (i < prjs.length); i++) {
                try {
                    pkgRoots.addAll(Arrays.asList(prjs[i].getAllPackageFragmentRoots()));
                } catch (JavaModelException e) {
                    continue;
                }
            }
        }

        IPackageFragmentRoot[] roots = new IPackageFragmentRoot[pkgRoots.size()];
        try {
            pkgRoots.toArray(roots);
        } catch (ArrayStoreException e) {
            return null;
        }
        
        return SearchEngine.createJavaSearchScope(roots);
    }
	
	private TypeSelectionExtension createTypeSelectionExtension() {
		return new BusinessInterfaceSelectionExtension();
	}
}
