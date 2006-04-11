/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.navigator.ui.Messages;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

public class LoadingDDNode {

	private static ImageDescriptor loadingOne;
	private static ImageDescriptor loadingTwo;
	private static ImageDescriptor loadingThree;
	private static ImageDescriptor loadingFour;

	static {
		try {
			loadingOne = J2EENavigatorPlugin.getDefault().getImageDescriptor("full/etool16/loading1.gif"); //$NON-NLS-1$
			loadingTwo = J2EENavigatorPlugin.getDefault().getImageDescriptor("full/etool16/loading2.gif"); //$NON-NLS-1$
			loadingThree = J2EENavigatorPlugin.getDefault().getImageDescriptor("full/etool16/loading3.gif"); //$NON-NLS-1$
			loadingFour = J2EENavigatorPlugin.getDefault().getImageDescriptor("full/etool16/loading4.gif"); //$NON-NLS-1$
		} catch (RuntimeException e) {
			String msg = e.getMessage() != null ? e.getMessage() : e.toString();
			J2EENavigatorPlugin.logError(0, msg, e);
			loadingOne = ImageDescriptor.getMissingImageDescriptor();
			loadingTwo = ImageDescriptor.getMissingImageDescriptor();
			loadingThree = ImageDescriptor.getMissingImageDescriptor();
			loadingFour = ImageDescriptor.getMissingImageDescriptor();
		}
	}
 
	private String text; 
	private String text1;
	private String text2;
	private String text3;
	private int count = 0;
	
	public LoadingDDNode(String type) {
		text = NLS.bind(Messages.LoadingDDNode_Loading_0_, type);
		text1 = text  + "."; //$NON-NLS-1$
		text2 = text  + ".."; //$NON-NLS-1$
		text3 = text  + "..."; //$NON-NLS-1$
	}

	public String getText() {

		switch ( count % 4) {
			case 0 :
				return text;
			case 1 :
				return text1;
			case 2 :
				return text2;
			case 3 :
			default :
				return text3;
		} 
	}

	public Image getImage() {
		switch ( count = (++count % 4)) {
			case 0 :
				return J2EENavigatorPlugin.getDefault().getImage(loadingOne);
			case 1 :
				return J2EENavigatorPlugin.getDefault().getImage(loadingTwo);
			case 2 :
				return J2EENavigatorPlugin.getDefault().getImage(loadingThree);
			case 3 :
			default :
				return J2EENavigatorPlugin.getDefault().getImage(loadingFour);
		}
	} 
	
}
