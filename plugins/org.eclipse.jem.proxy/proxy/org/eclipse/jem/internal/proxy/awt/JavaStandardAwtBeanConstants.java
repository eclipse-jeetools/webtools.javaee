package org.eclipse.jem.internal.proxy.awt;
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
 *  $RCSfile: JavaStandardAwtBeanConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;

/**
 * Standard AWT Constants
 *
 * Use the static accessor method (getConstants()) to get the appropriate 
 * constants instance for the registry. This is done so that until the
 * the constants are needed, they aren't registered.
 *
 * NOTE: Since everything in here just proxies, there is
 *       no need to have them separated by VM. That is why
 *       this in the Proxy package.
 *
 * This is final because this can't be extended. This specific one
 * will be registered with the factory for this key. Any extensions
 * must be done in their own constants and registry key.
 *
 * Creation date: (4/7/00 4:47:48 PM)
 * @author: Administrator
 */
public final class JavaStandardAwtBeanConstants {
		
	public static final String REGISTRY_KEY = "STANDARDPROXYAWTCONSTANTS:"; //$NON-NLS-1$
			
	final boolean AWTLoaded;	// AWT may not be available. So non-ui JVM's don't have AWT.
	final boolean AWTRegistered;	// AWT was registered to be used in this VM. It may be registered, but still not available.
		
	final IFieldProxy dimensionHeightProxy;
	final IFieldProxy dimensionWidthProxy;
	final IMethodProxy setDimensionSizeHWProxy;
	final IMethodProxy setDimensionSizeDProxy;
	
	final IFieldProxy pointXProxy;
	final IFieldProxy pointYProxy;
	final IMethodProxy setPointLocationXYProxy;
	final IMethodProxy setPointLocationPProxy;
	
	final IFieldProxy rectangleHeightProxy;
	final IFieldProxy rectangleWidthProxy;
	final IFieldProxy rectangleXProxy;
	final IFieldProxy rectangleYProxy;
	final IMethodProxy setRectangleSizeHWProxy;
	final IMethodProxy setRectangleSizeDProxy;
	final IMethodProxy setRectangleLocationXYProxy;
	final IMethodProxy setRectangleLocationPProxy;
	final IMethodProxy setRectangleBoundsXYHWProxy;
	final IMethodProxy setRectangleBoundsRProxy;
	
/**
 * Get the constants instance for the specified registry.
 */
public static JavaStandardAwtBeanConstants getConstants(ProxyFactoryRegistry registry) {
	JavaStandardAwtBeanConstants constants = (JavaStandardAwtBeanConstants) registry.getConstants(REGISTRY_KEY);
	if (constants == null)
		registry.registerConstants(REGISTRY_KEY, constants = new JavaStandardAwtBeanConstants(registry, registry.getBeanProxyFactoryExtension(IStandardAwtBeanProxyFactory.REGISTRY_KEY) != null));
	return constants;
}
		

public JavaStandardAwtBeanConstants(ProxyFactoryRegistry registry, boolean isRegistered) {
	super();
	
	AWTRegistered = isRegistered;
	if (!AWTRegistered) {
		AWTLoaded = false;
		dimensionHeightProxy = dimensionWidthProxy = pointXProxy = pointYProxy = rectangleHeightProxy = rectangleWidthProxy = rectangleXProxy = rectangleYProxy = null;
		setDimensionSizeHWProxy = setDimensionSizeDProxy = setPointLocationXYProxy = setPointLocationPProxy = setRectangleSizeHWProxy = setRectangleSizeDProxy = setRectangleLocationXYProxy = setRectangleLocationPProxy = setRectangleBoundsXYHWProxy = setRectangleBoundsRProxy = null;		
		return;
	}
	
	IStandardBeanTypeProxyFactory typeFactory = registry.getBeanTypeProxyFactory();
	
	IBeanTypeProxy dimensionTypeProxy = typeFactory.getBeanTypeProxy("java.awt.Dimension");//$NON-NLS-1$
	if (dimensionTypeProxy != null) {
		// AWT is available.
		AWTLoaded = true;
		IBeanTypeProxy intTypeProxy = typeFactory.getBeanTypeProxy("int"); //$NON-NLS-1$
		
		dimensionHeightProxy = dimensionTypeProxy.getFieldProxy("height");//$NON-NLS-1$
		dimensionWidthProxy = dimensionTypeProxy.getFieldProxy("width");//$NON-NLS-1$
		setDimensionSizeHWProxy = dimensionTypeProxy.getMethodProxy("setSize", new IBeanTypeProxy[] {intTypeProxy, intTypeProxy}); //$NON-NLS-1$
		setDimensionSizeDProxy = dimensionTypeProxy.getMethodProxy("setSize", new IBeanTypeProxy[] {dimensionTypeProxy}); //$NON-NLS-1$
	
		IBeanTypeProxy pointTypeProxy = typeFactory.getBeanTypeProxy("java.awt.Point");//$NON-NLS-1$
		pointXProxy = pointTypeProxy.getFieldProxy("x");//$NON-NLS-1$
		pointYProxy = pointTypeProxy.getFieldProxy("y");	//$NON-NLS-1$
		setPointLocationXYProxy = pointTypeProxy.getMethodProxy("setLocation", new IBeanTypeProxy[] {intTypeProxy, intTypeProxy}); //$NON-NLS-1$
		setPointLocationPProxy = pointTypeProxy.getMethodProxy("setLocation", new IBeanTypeProxy[] {pointTypeProxy}); //$NON-NLS-1$
		
		
		IBeanTypeProxy rectangleTypeProxy = typeFactory.getBeanTypeProxy("java.awt.Rectangle");//$NON-NLS-1$
		rectangleHeightProxy = rectangleTypeProxy.getFieldProxy("height");//$NON-NLS-1$
		rectangleWidthProxy = rectangleTypeProxy.getFieldProxy("width");//$NON-NLS-1$
		rectangleXProxy = rectangleTypeProxy.getFieldProxy("x");//$NON-NLS-1$
		rectangleYProxy = rectangleTypeProxy.getFieldProxy("y");//$NON-NLS-1$
		setRectangleSizeHWProxy = rectangleTypeProxy.getMethodProxy("setSize", new IBeanTypeProxy[] {intTypeProxy, intTypeProxy}); //$NON-NLS-1$
		setRectangleSizeDProxy = rectangleTypeProxy.getMethodProxy("setSize", new IBeanTypeProxy[] {dimensionTypeProxy}); //$NON-NLS-1$
		setRectangleLocationXYProxy = rectangleTypeProxy.getMethodProxy("setLocation", new IBeanTypeProxy[] {intTypeProxy, intTypeProxy}); //$NON-NLS-1$
		setRectangleLocationPProxy = rectangleTypeProxy.getMethodProxy("setLocation", new IBeanTypeProxy[] {pointTypeProxy});		 //$NON-NLS-1$
		setRectangleBoundsXYHWProxy = rectangleTypeProxy.getMethodProxy("setBounds", new IBeanTypeProxy[] {intTypeProxy, intTypeProxy, intTypeProxy, intTypeProxy}); //$NON-NLS-1$
		setRectangleBoundsRProxy = rectangleTypeProxy.getMethodProxy("setBounds", new IBeanTypeProxy[] {rectangleTypeProxy}); //$NON-NLS-1$
	} else {
		AWTLoaded = false;
		dimensionHeightProxy = dimensionWidthProxy = pointXProxy = pointYProxy = rectangleHeightProxy = rectangleWidthProxy = rectangleXProxy = rectangleYProxy = null;
		setDimensionSizeHWProxy = setDimensionSizeDProxy = setPointLocationXYProxy = setPointLocationPProxy = setRectangleSizeHWProxy = setRectangleSizeDProxy = setRectangleLocationXYProxy = setRectangleLocationPProxy = setRectangleBoundsXYHWProxy = setRectangleBoundsRProxy = null;
	}
}

/**
 * Answer if AWT is available.
 */
public boolean isAWTAvailable() {
	return AWTLoaded;
}

/**
 * Answer if AWT was registered to be available.
 */
public boolean isAWTRegistered() {
	return AWTRegistered;
}



/**
 * getDimensionHeightFieldProxy method comment.
 */
public IFieldProxy getDimensionHeightFieldProxy() {
	return dimensionHeightProxy;
}
/**
 * getDimensionWidthProxy method comment.
 */
public IFieldProxy getDimensionWidthFieldProxy() {
	return dimensionWidthProxy;
}
/**
 * getDimensionSetSizeWHProxy method comment.
 */
public IMethodProxy getDimensionSetSizeHWProxy() {
	return setDimensionSizeHWProxy;
}
/**
 * getDimensionSetSizeDProxy method comment.
 */
public IMethodProxy getDimensionSetSizeDProxy() {
	return setDimensionSizeDProxy;
}

/**
 * getPointXFieldProxy method comment.
 */
public IFieldProxy getPointXFieldProxy() {
	return pointXProxy;
}
/**
 * getPointYFieldProxy method comment.
 */
public IFieldProxy getPointYFieldProxy() {
	return pointYProxy;
}
/**
 * getPointSetLocationXYProxy method comment.
 */
public IMethodProxy getPointSetLocationXYProxy() {
	return setPointLocationXYProxy;
}
/**
 * getPointSetLocationPProxy method comment.
 */
public IMethodProxy getPointSetLocationPProxy() {
	return setPointLocationPProxy;
}
/**
 * getRectangleHeightFieldProxy method comment.
 */
public IFieldProxy getRectangleHeightFieldProxy() {
	return rectangleHeightProxy;
}
/**
 * getRectangleWidthFieldProxy method comment.
 */
public IFieldProxy getRectangleWidthFieldProxy() {
	return rectangleWidthProxy;
}
/**
 * getRectangleXFieldProxy method comment.
 */
public IFieldProxy getRectangleXFieldProxy() {
	return rectangleXProxy;
}
/**
 * getRectangleYFieldProxy method comment.
 */
public IFieldProxy getRectangleYFieldProxy() {
	return rectangleYProxy;
}
/**
 * getRectangleSetSizeWHProxy method comment.
 */
public IMethodProxy getRectangleSetSizeHWProxy() {
	return setRectangleSizeHWProxy;
}
/**
 * getRectangleSetSizeDProxy method comment.
 */
public IMethodProxy getRectangleSetSizeDProxy() {
	return setRectangleSizeDProxy;
}
/**
 * getRectangleSetLocationXYProxy method comment.
 */
public IMethodProxy getRectangleSetLocationXYProxy() {
	return setRectangleLocationXYProxy;
}
/**
 * getRectangleSetLocationPProxy method comment.
 */
public IMethodProxy getRectangleSetLocationPProxy() {
	return setRectangleLocationPProxy;
}

/**
 * getRectangleSetBoundsXYHWProxy method comment.
 */
public IMethodProxy getRectangleSetBoundsXYHWProxy() {
	return setRectangleBoundsXYHWProxy;
}
/**
 * getRectangleSetBoundsRProxy method comment.
 */
public IMethodProxy getRectangleSetBoundsRProxy() {
	return setRectangleBoundsRProxy;
}


}
