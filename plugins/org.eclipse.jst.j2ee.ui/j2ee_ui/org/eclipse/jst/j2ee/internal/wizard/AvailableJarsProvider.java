/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;



import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.application.operations.ClassPathSelection;
import org.eclipse.jst.j2ee.application.operations.ClasspathElement;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.frameworks.ui.OverlayIcon;


public class AvailableJarsProvider implements org.eclipse.jface.viewers.IStructuredContentProvider, org.eclipse.jface.viewers.ITableLabelProvider {
	protected static Image utilImage;
	protected static Image invalidImage;
	protected static Image ejbImage;
	protected static Image ejbClientImage;

	/**
	 * AvailableJarsContentProvider constructor comment.
	 */
	public AvailableJarsProvider() {
		super();
	}

	/**
	 * Adds a listener to this label provider. Has no effect if an identical listener is already
	 * registered.
	 * <p>
	 * Label provider listeners are informed about state changes that affect the rendering of the
	 * viewer that uses this label provider.
	 * </p>
	 * 
	 * @param listener
	 *            a label provider listener
	 */
	public void addListener(org.eclipse.jface.viewers.ILabelProviderListener listener) {
	}

	/**
	 * Disposes of this label provider. When a label provider is attached to a viewer, the viewer
	 * will automatically call this method when the viewer is being closed. When label providers are
	 * used outside of the context of a viewer, it is the client's responsibility to ensure that
	 * this method is called when the provider is no longer needed.
	 */
	public void dispose() {
	}

	/**
	 * Returns the label image for the given column of the given element.
	 * 
	 * @param element
	 *            the object representing the entire row, or <code>null</code> indicating that no
	 *            input object is set in the viewer
	 * @param columnIndex
	 *            the zero-based index of the column in which the label appears
	 */
	public org.eclipse.swt.graphics.Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex > 0)
			return null;
		ClasspathElement cp = (ClasspathElement) element;
		if (!cp.isValid())
			return getInvalidImage();

		switch (cp.getJarType()) {
			case ClasspathElement.EJB_JAR :
				return getEjbImage();
			case ClasspathElement.EJB_CLIENT_JAR :
				return getEjbClientImage();
			default :
				return getUtilImage();
		}
	}

	/**
	 * Returns the label text for the given column of the given element.
	 * 
	 * @param element
	 *            the object representing the entire row, or <code>null</code> indicating that no
	 *            input object is set in the viewer
	 * @param columnIndex
	 *            the zero-based index of the column in which the label appears
	 */
	public String getColumnText(Object element, int columnIndex) {
		String value = null;
		switch (columnIndex) {
			case 0 : {
				value = ((ClasspathElement) element).getText();
				break;
			}
			case 1 :
				value = ((ClasspathElement) element).getProjectName();
		}
		return value == null ? "" : value;//$NON-NLS-1$
	}

	protected static Image getEjbImage() {
		if (ejbImage == null)
			ejbImage = getImageDescriptor("EJBJar").createImage();//$NON-NLS-1$
		return ejbImage;
	}

	protected static Image getEjbClientImage() {
		if (ejbClientImage == null)
			ejbClientImage = getImageDescriptor("ejbclientjar_obj").createImage();//$NON-NLS-1$
		return ejbClientImage;
	}

	/**
	 * Returns the elements to display in the viewer when its input is set to the given element.
	 * These elements can be presented as rows in a table, items in a list, etc. The result is not
	 * modified by the viewer.
	 * 
	 * @param inputElement
	 *            the input element
	 * @return the array of elements to display in the viewer
	 */
	public java.lang.Object[] getElements(Object inputElement) {
		ClassPathSelection selection = (ClassPathSelection) inputElement;
		return filter(selection, selection.getFilterLevel()).toArray();
	}

	/**
	 * @param list
	 */
	private List filter(ClassPathSelection selection, int filterLevel) {
		List list = selection.getClasspathElements();
		List result = new ArrayList(list.size());
		for (int i = 0; i < list.size(); i++) {
			ClasspathElement element = (ClasspathElement) list.get(i);
			if (!element.isSelected()) {
				switch (filterLevel) {
					case (ClassPathSelection.FILTER_EJB_CLIENT_JARS) :
						if (element.isEJBClientJar())
							continue;
						break;
					case (ClassPathSelection.FILTER_EJB_SERVER_JARS) :
						if (element.isEJBJar() && selection.getOppositeElement(element) != null)
							continue;
				}
			}
			result.add(element);
		}
		return result;
	}

	/**
	 * This gets a .gif from the icons folder.
	 */
	protected static ImageDescriptor getImageDescriptor(String key) {
		ImageDescriptor imageDescriptor = null;

		URL gifImageURL = (URL) J2EEPlugin.getPlugin().getImage(key);
		imageDescriptor = ImageDescriptor.createFromURL(gifImageURL);
		return imageDescriptor;
	}

	protected static Image getInvalidImage() {
		if (invalidImage == null) {
			ImageDescriptor base = getImageDescriptor("jar_nonexist_obj");//$NON-NLS-1$
			ImageDescriptor overlay = getImageDescriptor("warning_co");//$NON-NLS-1$
			invalidImage = new OverlayIcon(base, new ImageDescriptor[][]{{overlay}}).createImage();
		}
		return invalidImage;
	}

	protected static Image getUtilImage() {
		if (utilImage == null)
			utilImage = getImageDescriptor("jar_obj").createImage();//$NON-NLS-1$
		return utilImage;
	}

	/**
	 * Notifies this content provider that the given viewer's input has been switched to a different
	 * element.
	 * <p>
	 * A typical use for this method is registering the content provider as a listener to changes on
	 * the new input (using model-specific means), and deregistering the viewer from the old input.
	 * In response to these change notifications, the content provider propagates the changes to the
	 * viewer.
	 * </p>
	 * 
	 * @param viewer
	 *            the viewer
	 * @param oldInput
	 *            the old input element, or <code>null</code> if the viewer did not previously
	 *            have an input
	 * @param newInput
	 *            the new input element, or <code>null</code> if the viewer does not have an input
	 */
	public void inputChanged(org.eclipse.jface.viewers.Viewer viewer, Object oldInput, Object newInput) {
	}

	/**
	 * Returns whether the label would be affected by a change to the given property of the given
	 * element. This can be used to optimize a non-structural viewer update. If the property
	 * mentioned in the update does not affect the label, then the viewer need not update the label.
	 * 
	 * @param element
	 *            the element
	 * @param property
	 *            the property
	 * @return <code>true</code> if the label would be affected, and <code>false</code> if it
	 *         would be unaffected
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * Removes a listener to this label provider. Has no affect if an identical listener is not
	 * registered.
	 * 
	 * @param listener
	 *            a label provider listener
	 */
	public void removeListener(org.eclipse.jface.viewers.ILabelProviderListener listener) {
	}
}