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
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.TypeNameMatch;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jdt.ui.dialogs.TypeSelectionExtension;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface.BusinessInterfaceType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class BusinessInterfaceSelectionDialog extends OpenTypeSelectionDialog {
	
    private Button remoteRadio;
    private Button localRadio;
    
    private List<BusinessInterface> resultToReturn;
    
    public BusinessInterfaceSelectionDialog(Shell parent, boolean multi, IRunnableContext context, 
            IJavaSearchScope scope, int elementKinds) {
        this(parent, multi, context, scope, elementKinds, null);
    }
    
    public BusinessInterfaceSelectionDialog(Shell parent, boolean multi, IRunnableContext context, 
            IJavaSearchScope scope, int elementKinds, TypeSelectionExtension extension) {
    	super(parent, false, context, scope, elementKinds, extension);
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }
    
    @Override
	protected Control createDialogArea(Composite parent) {
        Composite area = (Composite)super.createDialogArea(parent);
        
        Label label = new Label(area, SWT.NONE);
        label.setText(EJBUIMessages.addAs);
        label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        remoteRadio = new Button(area, SWT.RADIO);
        remoteRadio.setText(EJBUIMessages.REMOTE_BUSINESS_INTERFACE);
        remoteRadio.setSelection(false);
        remoteRadio.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        localRadio = new Button(area, SWT.RADIO);
        localRadio.setText(EJBUIMessages.LOCAL_BUSINESS_INTERFACE);
        localRadio.setSelection(true);
        localRadio.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        return area;
    }
    
    @Override
    public void setResult(List newResult) {
    	resultToReturn = new ArrayList<BusinessInterface>();
    	
    	Iterator iterator = newResult.iterator();
    	while (iterator.hasNext()) {
    		TypeNameMatch typeNameMatch = (TypeNameMatch) iterator.next();
    		IType javaType = typeNameMatch.getType();
            BusinessInterfaceType type = (localRadio.getSelection() 
            		? BusinessInterfaceType.LOCAL
					: BusinessInterfaceType.REMOTE);
            resultToReturn.add(new BusinessInterface(javaType, type));
    	}
    }
    
    @Override
    public BusinessInterface[] getResult() {
    	return resultToReturn.toArray(new BusinessInterface[] { });
    }
}
