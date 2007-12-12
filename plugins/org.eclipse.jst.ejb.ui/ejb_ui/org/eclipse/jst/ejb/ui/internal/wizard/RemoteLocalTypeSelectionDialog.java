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

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jdt.ui.dialogs.TypeSelectionExtension;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.RemoteLocalInterface;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class RemoteLocalTypeSelectionDialog extends OpenTypeSelectionDialog {
	
    private Button remoteRadio;
    private Button localRadio;
    
    private boolean isRemote = false;
    private boolean isLocal = true;
    private boolean hasAddAs = true;
    
    public RemoteLocalTypeSelectionDialog(Shell parent, boolean multi, IRunnableContext context, 
            IJavaSearchScope scope, int elementKinds, boolean hasAddAs) {
        this(parent, multi, context, scope, elementKinds, null, hasAddAs);
    }
    
    public RemoteLocalTypeSelectionDialog(Shell parent, boolean multi, IRunnableContext context, 
            IJavaSearchScope scope, int elementKinds, TypeSelectionExtension extension, boolean hasAddAs) {
    	super(parent, false, context, scope, elementKinds, extension);
        setShellStyle(getShellStyle() | SWT.RESIZE);

        this.hasAddAs = hasAddAs;
        if (hasAddAs) {
            isRemote = false;
            isLocal = true;
        } else {
            isRemote = false;
            isLocal = false;
        }
    }
    
    public void setRemoteSelected(boolean selected) {
        if (!remoteRadio.isDisposed()) { 
            remoteRadio.setSelection(selected);
            isRemote = selected;
        }
        if (!localRadio.isDisposed()) {
            localRadio.setSelection(!selected);
            isLocal = !selected;
        }
    }
    
    public void setAddAsEnabled(boolean enabled) {
        if (!remoteRadio.isDisposed()) {
            remoteRadio.setEnabled(enabled);
        }
        if (!localRadio.isDisposed()) {
            localRadio.setEnabled(enabled);
        }
    }
    
    
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite)super.createDialogArea(parent);
        if (hasAddAs) {
            Label label = new Label(area, SWT.NONE);
            label.setText(EJBUIMessages.addAs);
            label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            remoteRadio = new Button(area, SWT.RADIO);
            remoteRadio.setText(EJBUIMessages.REMOTE_BUSSINES_INTERFACE);
            remoteRadio.setSelection(false);
            remoteRadio.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            remoteRadio.addSelectionListener(new SelectionListener() {
                public void widgetSelected(SelectionEvent e) {
                    Button radio = (Button) e.getSource();
                    isRemote = radio.getSelection();
                    isLocal = !isRemote;
                    localRadio.setSelection(isLocal);
                }

				public void widgetDefaultSelected(SelectionEvent e) {
				}
            });
            localRadio = new Button(area, SWT.RADIO);
            localRadio.setText(EJBUIMessages.LOCAL_BUSSINES_INTERFACE);
            localRadio.setSelection(true);
            localRadio.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            localRadio.addSelectionListener(new SelectionListener() {
                public void widgetSelected(SelectionEvent e) {
                    Button radio = (Button) e.getSource();
                    isLocal = radio.getSelection();
                    isRemote = !isLocal;
                    remoteRadio.setSelection(isRemote);
                }

				public void widgetDefaultSelected(SelectionEvent e) {
				}
            });
        }
        return area;
    }
    
    public RemoteLocalInterface[] getResult() {
        Object[] objects = super.getResult();
        RemoteLocalInterface[] result = null;
        if (objects != null) {
            result = new RemoteLocalInterface[objects.length];
            for (int i = 0; i < objects.length; i++) {
                IType type = (IType) objects[i];
                result[i] = new RemoteLocalInterface(type, isRemote, isLocal);
            }
        }
        return result;
    }
    
    public boolean isRemoteInterface() {
        return isRemote;
    }
    
    public boolean isLocalInterface() {
        return isLocal;
    }
}
