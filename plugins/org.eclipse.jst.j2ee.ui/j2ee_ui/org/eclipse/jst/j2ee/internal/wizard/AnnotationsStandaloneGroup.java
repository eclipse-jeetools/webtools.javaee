/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 29, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AnnotationsStandaloneGroup {

	protected Object model;
	protected Object synchHelper;
	protected Button useAnnotations;
	private boolean isForBean;
	private boolean useServletString = false;
	public static final String EJBTAGSET = "ejb"; //$NON-NLS-1$

	/**
	 * Constructor
	 */
	public AnnotationsStandaloneGroup(Composite parent, Object model, boolean forBean) {
		this(parent, model, forBean, false);
	}

	/**
	 * Constructor
	 */
	public AnnotationsStandaloneGroup(Composite parent, Object model, boolean forBean, boolean useServlet) {
		super();
		synchHelper = new DataModelSynchHelper((IDataModel)model);
		this.model = model;
		this.isForBean = forBean;
		this.useServletString = useServlet;
		
		buildComposites(parent);
	}

	/**
	 * @param parent
	 */
	protected void buildComposites(Composite parent) {
		// Add separator
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		separator.setLayoutData(gd);
		// Add spacer
		Label spacer = new Label(parent, SWT.NONE);
		GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
		gd1.horizontalSpan = 3;
		spacer.setLayoutData(gd1);
		// Add annotations checkbox and label
		useAnnotations = new Button(parent, SWT.CHECK);
		String labelText;
		if (useServletString)
			labelText = J2EEUIMessages.getResourceString(J2EEUIMessages.USE_ANNOTATIONS_SERVLET);
		else if (isForBean)
			labelText = J2EEUIMessages.getResourceString(J2EEUIMessages.USE_ANNOTATIONS);
		else
			labelText = J2EEUIMessages.getResourceString(J2EEUIMessages.ADD_ANNOTATIONS_SUPPORT);
		useAnnotations.setText(labelText);
		((DataModelSynchHelper)synchHelper).synchCheckbox(useAnnotations, IAnnotationsDataModel.USE_ANNOTATIONS, null);
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.horizontalSpan = 2;
		useAnnotations.setLayoutData(gd2);
	}

	public void dispose() {
		((IDataModel)model).removeListener((DataModelSynchHelper)synchHelper);
		synchHelper = null;
		model = null;
	}

	public void setEnablement(IProject project) {
		//TODO Remove - this is to be handled by the provider of the DataModel.
	}
	
	

	public void setUseServlet(boolean aBoolean) {
		useServletString = aBoolean;
	}
}
