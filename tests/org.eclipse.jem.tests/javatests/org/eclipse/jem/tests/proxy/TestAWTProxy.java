package org.eclipse.jem.tests.proxy;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestAWTProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/27 17:32:36 $ 
 */
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.awt.*;
import org.eclipse.jem.internal.proxy.awt.IStandardAwtBeanProxyFactory;
import org.eclipse.jem.internal.proxy.awt.JavaStandardAwtBeanConstants;

/**
 * @author richkulp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestAWTProxy extends AbstractTestProxy {

	public TestAWTProxy() {
		super();
	}

	public TestAWTProxy(String name) {
		super(name);
	}
	
	private JavaStandardAwtBeanConstants constants;	// If this is null then don't do tests.
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		constants = JavaStandardAwtBeanConstants.getConstants(registry);
		assertNotNull(constants);
		if (!constants.isAWTAvailable()) {
			// this is not an error. It just means we don't anything. This is signified by constants being null.
			constants = null;
		}
	}
	
	public void testAWTColor() throws ClassCastException, ThrowableProxy, InstantiationException {
		if (constants == null)
			return;	// No AWT, so test isn't done, but still considered good.
			
		// AWT Color tests
		IBeanTypeProxy colorType = proxyTypeFactory.getBeanTypeProxy("java.awt.Color"); //$NON-NLS-1$
		assertNotNull(colorType);
		IBeanProxy colorProxy = colorType.newInstance("java.awt.Color.cyan"); //$NON-NLS-1$
		assertNotNull(colorProxy);
		assertEquals("java.awt.Color[r=0,g=255,b=255]", colorProxy.toBeanString());
	}
	
	public void testAWTProxyFactory() {
		if (constants == null)
			return;	// No AWT, so test isn't done, but still considered good.
			
		IStandardAwtBeanProxyFactory awtProxyFactory = (IStandardAwtBeanProxyFactory) registry.getBeanProxyFactoryExtension(IStandardAwtBeanProxyFactory.REGISTRY_KEY);
		assertNotNull(awtProxyFactory);
		
		// Test the creation through the helpers, plus test special methods 
		IDimensionBeanProxy dimBean = awtProxyFactory.createDimensionBeanProxyWith(5,6);
		assertNotNull(dimBean);
		assertEquals("java.awt.Dimension[width=5,height=6]", dimBean.toBeanString()); //$NON-NLS-1$
		assertEquals(5, dimBean.getWidth());
		assertEquals(6, dimBean.getHeight());		
		
		IPointBeanProxy pointBean = awtProxyFactory.createPointBeanProxyWith(5,6);
		assertNotNull(pointBean);
		assertEquals("java.awt.Point[x=5,y=6]", pointBean.toBeanString()); //$NON-NLS-1$
		assertEquals(5, pointBean.getX());
		assertEquals(6, pointBean.getY());		
		
		IRectangleBeanProxy rectBean = awtProxyFactory.createBeanProxyWith(5,6,7,8);
		assertNotNull(rectBean);
		assertEquals("java.awt.Rectangle[x=5,y=6,width=7,height=8]", rectBean.toBeanString()); //$NON-NLS-1$
		assertEquals(5, rectBean.getX());
		assertEquals(6, rectBean.getY());		
		assertEquals(7, rectBean.getWidth());
		assertEquals(8, rectBean.getHeight());				
	}
	
	public void testAWTSpecialsInitString() throws ClassCastException, ThrowableProxy, InstantiationException {
		if (constants == null)
			return;	// No AWT, so test isn't done, but still considered good.
			
		
		// Test the creation through init strings
		IBeanTypeProxy dimType = proxyTypeFactory.getBeanTypeProxy("java.awt.Dimension"); //$NON-NLS-1$
		assertNotNull(dimType);		
		IDimensionBeanProxy dimBean = (IDimensionBeanProxy) dimType.newInstance("new java.awt.Dimension(7,8)"); //$NON-NLS-1$
		assertNotNull(dimBean);
		assertEquals("java.awt.Dimension[width=7,height=8]", dimBean.toBeanString()); //$NON-NLS-1$
		
		IBeanTypeProxy pointType = proxyTypeFactory.getBeanTypeProxy("java.awt.Point"); //$NON-NLS-1$
		IPointBeanProxy pointBean = (IPointBeanProxy) pointType.newInstance("new java.awt.Point(5,6)"); //$NON-NLS-1$
		assertNotNull(pointBean);
		assertEquals("java.awt.Point[x=5,y=6]", pointBean.toBeanString()); //$NON-NLS-1$
		
		IBeanTypeProxy rectType = proxyTypeFactory.getBeanTypeProxy("java.awt.Rectangle"); //$NON-NLS-1$
		IRectangleBeanProxy rectBean = (IRectangleBeanProxy) rectType.newInstance("new java.awt.Rectangle(5,6,7,8)"); //$NON-NLS-1$
		assertNotNull(rectBean);
		assertEquals("java.awt.Rectangle[x=5,y=6,width=7,height=8]", rectBean.toBeanString()); //$NON-NLS-1$
	}

}
