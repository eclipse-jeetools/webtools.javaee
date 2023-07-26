/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.navigator.ui.Messages;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.ui.internal.navigator.ILoadingDDNode;

public class LoadingDDNode implements ILoadingDDNode{

	private static ImageDescriptor loadingOne;
	private static ImageDescriptor loadingTwo;
	private static ImageDescriptor loadingThree;
	private static ImageDescriptor loadingFour;
	

	private static final Set loadingFiles = new HashSet();
	private static final Map placeHolders = new HashMap();

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

	private boolean disposed = false;
	private IProject modelProject;
	

	/**
	 * Return a place holder node to sit in the tree until data is available.
	 * This place holder node will be animated for the user's enjoyment. 
	 *  
	 * @param modelFile The modelFile to be loaded
	 * @return A new unique place holder for this file for a given load cycle
	 */
	public static LoadingDDNode createPlaceHolder(IProject modelFile) {
		LoadingDDNode node = null;
		synchronized (LoadingDDNode.class) {
			if(placeHolders.containsKey(modelFile))
				node = (LoadingDDNode) placeHolders.get(modelFile);
			else 			
				placeHolders.put(modelFile, node = new LoadingDDNode(modelFile));
		}
		return node;
	}
	
	public LoadingDDNode(IProject model) {
		text = NLS.bind(Messages.LoadingDDNode_Loading_0_, model.getName());
		text1 = text  + "."; //$NON-NLS-1$
		text2 = text  + ".."; //$NON-NLS-1$
		text3 = text  + "..."; //$NON-NLS-1$
		modelProject = model;
	}

	@Override
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

	@Override
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
	
	
	
	public boolean isDisposed() {
		return disposed;
	}
	
	public void dispose() {
		synchronized (LoadingDDNode.class) {
			disposed = true;
			placeHolders.remove(modelProject);	
			loadingFiles.remove(modelProject);
		}
	}

	/**
	 * Employ a Test and Set (TST) primitive to ensure 
	 * that only job is spawned to load the model file
	 *   
	 * 
	 * @return True only if no other jobs are trying to load this model.
	 */
	public static boolean canBeginLoading(IProject modelProject) {
		synchronized (LoadingDDNode.class) {  
			if(loadingFiles.contains(modelProject))
				return false;
			loadingFiles.add(modelProject);
			return true;
		}

	}

	/**
	 * Return true if a job has requested permission to load the model file   
	 * @param modelFile The model file that should be loaded
	 * @return True only if a job is trying to load the model.
	 */
	public static boolean isBeingLoaded(IProject modelProject) {
		synchronized (LoadingDDNode.class) {  
			return loadingFiles.contains(modelProject);
		}
	}
	
}
