package org.eclipse.jem.internal.beaninfo.ui;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: SearchpathOrderingWorkbookPage.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:20:50 $ 
 */

import java.util.List;

import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.ListDialogField;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @version 	1.0
 * @author
 */
public class SearchpathOrderingWorkbookPage extends BuildSearchBasePage {
	
	private ListDialogField fSearchPathList;
	
	public SearchpathOrderingWorkbookPage(ListDialogField searchPathList, List interestedFieldsForEnableControl) {
		fSearchPathList= searchPathList;
		interestedFieldsForEnableControl.add(fSearchPathList);
	}
	
	public Control getControl(Composite parent) {
		PixelConverter converter= new PixelConverter(parent);
		
		Composite composite= new Composite(parent, SWT.NONE);
		
		LayoutUtil.doDefaultLayout(composite, new DialogField[] { fSearchPathList }, true, SWT.DEFAULT, SWT.DEFAULT);

		int buttonBarWidth= converter.convertWidthInCharsToPixels(24);
		fSearchPathList.setButtonsMinWidth(buttonBarWidth);
					
		return composite;
	}
	
	/*
	 * @see BuildPathBasePage#getSelection
	 */
	public List getSelection() {
		return fSearchPathList.getSelectedElements();
	}

	/*
	 * @see BuildPathBasePage#setSelection
	 */	
	public void setSelection(List selElements) {
		fSearchPathList.selectElements(new StructuredSelection(selElements));
	}		

}
