/*
 * Created on Mar 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal.workingsets;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComponentWorkingSetDescriptor {

	private IConfigurationElement configElement;
	public static final String ATT_ID = "id"; //$NON-NLS-1$
	public static final String ATT_LABEL = "label"; //$NON-NLS-1$
	public static final String ATT_ICON = "icon"; //$NON-NLS-1$	
	public static final String ATT_MODULE_TYPE = "typeId"; //$NON-NLS-1$	
	
	private String id ;
	private String label ;
	private String icon;
	private String typeId;

	
	/**
	 * @param anElement
	 */
	public ComponentWorkingSetDescriptor(IConfigurationElement aConfigElement) throws WorkbenchException{
		super();
		configElement = aConfigElement;
		init();
	}

	void init() throws WorkbenchException {
		label = configElement.getAttribute(ATT_LABEL);
		id = configElement.getAttribute(ATT_ID);
		typeId = configElement.getAttribute(ATT_MODULE_TYPE);
		icon = configElement.getAttribute(ATT_ICON);
		
		if (id == null || id.length() == 0) {
			throw new WorkbenchException("Missing attribute: " + //$NON-NLS-1$
					ATT_ID + " in common working set extension: " + //$NON-NLS-1$
						configElement.getDeclaringExtension().getUniqueIdentifier());
		}

		if (label == null || label.length() == 0) {
			throw new WorkbenchException("Missing attribute: " + //$NON-NLS-1$
					ATT_LABEL + " in common working set extension: " + //$NON-NLS-1$
						configElement.getDeclaringExtension().getUniqueIdentifier());
		}

		if (typeId == null || typeId.length() == 0) {
			throw new WorkbenchException("Missing attribute: " + //$NON-NLS-1$
					ATT_MODULE_TYPE + " in common working set extension: " + //$NON-NLS-1$
						configElement.getDeclaringExtension().getUniqueIdentifier());
		}
	}

	/**
	 * @return
	 */
	public String getTypeId() {
		// TODO Auto-generated method stub
		return typeId;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	/**
	 * @return
	 */
	public String getLabel() {
		// TODO Auto-generated method stub
		return label;
	}
	
	/**
     * Returns the page's icon
     * 
     * @return the page's icon
     */
    public ImageDescriptor getIcon() {
        if (icon == null)
            return null;

        IExtension extension = configElement.getDeclaringExtension();
        String extendingPluginId = extension.getNamespace();
        return AbstractUIPlugin.imageDescriptorFromPlugin(extendingPluginId,
                icon);
    }

	/**
	 * @throws BundleException
	 * 
	 */
//	public void start() throws BundleException {
//	    	Bundle bundle= Platform.getBundle(configElement.getDeclaringExtension().getNamespace());
//	    	if ( bundle.getState() != Bundle.ACTIVE )
//	    		bundle.start();
//	    	
//	}
}
