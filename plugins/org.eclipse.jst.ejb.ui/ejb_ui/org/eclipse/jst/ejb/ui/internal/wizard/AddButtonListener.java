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
package org.eclipse.jst.ejb.ui.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddButtonListener implements SelectionListener {

	private static final String SEARCH_FILTER = "**"; //$NON-NLS-1$
	private static final String EMPTY = ""; //$NON-NLS-1$
	private AddSessionBeanWizardPage page;
	private IDataModel model;
	
	public AddButtonListener(AddSessionBeanWizardPage beanPage, IDataModel model){
		page = beanPage;
		this.model = model;
	}
	
	public void widgetSelected(SelectionEvent e) {
		BusinessInterface remInt = chooseEnclosingType(getRoots(), new String[] { "All_APIs" }, 
				page.getShell(), page.getWizard().getContainer(),
				IJavaSearchConstants.INTERFACE, EMPTY);
		if (remInt != null) {
			IType type = remInt.getInterfaceType();
			if (type != null) {
				String text = type.getFullyQualifiedName();
				List biList = (List) model.getProperty(INewSessionBeanClassDataModelProperties.BUSINESSINTERFACES);
				if (!hasInterface(text, biList)) {
					biList.add(remInt);
					model.setProperty(INewSessionBeanClassDataModelProperties.BUSINESSINTERFACES, biList);
					page.updateBusInterfacesList();
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
        
        try {
            RemoteLocalTypeSelectionDialog dialog = new RemoteLocalTypeSelectionDialog(shell, false, null, scope, type, true);
            dialog.setTitle(EJBUIMessages.chooseInterface);
            dialog.setMessage(EJBUIMessages.chooseInterface);
            dialog.setInitialPattern(currSelection);
            final boolean isRemote = dialog.isRemoteInterface();
            final boolean isLocal = dialog.isLocalInterface();
            dialog.setValidator(new ISelectionStatusValidator() {
                public IStatus validate(Object[] selection) {
                    if (selection.length == 0)
                        return new Status(IStatus.ERROR, EMPTY, 0, EMPTY, null);
                    BusinessInterface type = null;
                    if (selection[0] instanceof IType) {
                        type = new BusinessInterface((IType) selection[0], isRemote, isLocal);
                    } else {
                        type = (BusinessInterface) selection[0];
                    }
                    try {
                        return checkInterface(type);
                    } catch (JavaModelException e) {
                        return Status.OK_STATUS;
                    }
                }
            });
            if (dialog.open() == RemoteLocalTypeSelectionDialog.OK) {
                Object[] obj = dialog.getResult();
                if (obj != null) {
                    ret = (BusinessInterface) obj[0];
                    IStatus status = checkInterface(ret);
                    if (!status.isOK()) {
                        ret = null;
                    }
                }
            }
        } catch (JavaModelException e) {
        }
        return ret;
    }
	
	private IStatus checkInterface(final BusinessInterface remInt) throws JavaModelException {
        return Status.OK_STATUS;
    }
	
	public boolean hasInterface(String text, List<BusinessInterface> biList) {
		
        for (Iterator<BusinessInterface> i = biList.iterator(); i.hasNext(); ) {
            BusinessInterface element = (BusinessInterface) i.next();
            if (element.getInterfaceName().equals(text)){
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
            if (project == null){
            	IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
            	List<IJavaProject> javaPrjcts = new ArrayList<IJavaProject>();
            	for (int i =0;i<projects.length;i++){
            		javaPrjcts.add(JavaCore.create(projects[i]));
            	}
            	prjs = (IJavaProject[]) javaPrjcts.toArray(new IJavaProject[javaPrjcts.size()]);
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
}
