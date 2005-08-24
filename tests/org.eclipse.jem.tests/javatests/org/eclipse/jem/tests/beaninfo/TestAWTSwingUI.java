/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.beaninfo;
/*
 *  $RCSfile: TestAWTSwingUI.java,v $
 *  $Revision: 1.8 $  $Date: 2005/08/24 20:58:55 $ 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jem.internal.beaninfo.MethodProxy;
import org.eclipse.jem.internal.beaninfo.PropertyDecorator;
import org.eclipse.jem.internal.beaninfo.core.Utilities;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.tests.JavaTestsPlugin;

/**
 * @author richkulp
 *
 * AWT/Swing tests
 */
public class TestAWTSwingUI extends AbstractBeanInfoTestCase {

	/**
	 * 
	 */
	public TestAWTSwingUI() {
		super();
	}

	/**
	 * @param name
	 */
	public TestAWTSwingUI(String name) {
		super(name);
	}
	
	/*
	 * The number of properties keep increasing by version,
	 * but there is a minimal set. This minimal set will be
	 * stored in a file and can be loaded by this method.
	 * This set can be used to make sure that at least the
	 * required properties are there.
	 * 
	 * The filename has to be relative to the tests plugin.
	 */
	protected Set getNames(String filename) throws IOException {
		Set names = new HashSet(50);
		URL url = JavaTestsPlugin.getPlugin().getBundle().getEntry(filename);
		assertNotNull(url);
		BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
		try {
			String line = null;
			while ((line = rdr.readLine()) != null)
				names.add(line);
		} finally {
			rdr.close();
		}
		return names;
	}
	
	/*
	 * Test the names against the set. Print those not found in the set, 
	 * or those in the againstSet that weren't found. Throw assert error if
	 * any from against set not found. 
	 * 
	 */
	protected void testIncludesAllNames(String id, List testNames, Set againstSet, Set exclude) {
		Set workingAgainst = new HashSet(againstSet.size());
		workingAgainst.addAll(againstSet);	// There may be dup testNames (like for operations), so in that case we leave againstSet alone.
		boolean wroteAny = false;
		for (Iterator itr = testNames.iterator(); itr.hasNext();) {
			ENamedElement testName = (ENamedElement) itr.next();
			if (exclude.contains(testName))
				continue;	// This is one we want to ignore
			if (againstSet.contains(testName.getName()))
				workingAgainst.remove(testName.getName());	// Get rid of it since found.
			else {
				if (!wroteAny) {
					wroteAny = true;
					System.out.println("Names in list that are new for " + id + ':');
				}
				System.out.print("  ");
				System.out.println(testName.getName());
			}
		}
		if (wroteAny)
			System.out.println("End of new names.");
		
		if (!workingAgainst.isEmpty()) {
			System.out.println("Names that should of been found, but weren't for " + id + ':');
			for (Iterator itr = workingAgainst.iterator(); itr.hasNext();) {
				String name = (String) itr.next();
				System.out.print("  ");
				System.out.println(name);
			}
			System.out.println("End of missing names");
			fail("Missing some required names for "+id+'.');
		}
	}
	
	/*
	 * Write the names out. Call only when want to update list. Filename must
	 * be a local file.
	 */
	protected void writeNames(String filename, List props, Set excluding) throws FileNotFoundException {
		PrintWriter pr = new PrintWriter(new FileOutputStream(filename));
		try {
			for (Iterator itr = props.iterator(); itr.hasNext();) {
				ENamedElement element = (ENamedElement) itr.next();
				if (excluding != null && excluding.contains(element))
					continue;
				pr.println(element.getName());
			}
		} finally {
			pr.close();
		}
	}
	
	/**
	 * Reflect the entire super type hierarchy of the class passed in, including the class itself.
	 * @param jclass
	 */
	protected void reflectHierachy(JavaClass jclass) {
		JavaClass loopClass = jclass;
		while (loopClass != null) {
			System.out.println("Reflecting for " + loopClass.getQualifiedName()); //$NON-NLS-1$
			loopClass.isFinal(); // This causes reflection.
			loopClass = loopClass.getSupertype();
		}		
	}
	
	/**
	 * Use when the compares don't match to get a sorted list of operations so that
	 * manually verify the count.
	 * @param jclass
	 */
	protected void showSortedOperations(JavaClass jclass) {
			 List allB = jclass.getEAllOperations();
			 String[] names = new String[allB.size()];
			 for (int i = 0; i < names.length; i++) {
				 names[i] = ((MethodProxy) allB.get(i)).getMethod().getMethodElementSignature();
			 }
			 Arrays.sort(names);
			 System.out.println("--- List of Operations for " + jclass.toString());
			 for (int i = 0; i < names.length; i++) {
				 System.out.println(names[i]);
			 }		
	}
	
	/**
	 * Use when the compares don't match to get a sorted list of properties so that
	 * manually verify the count.
	 * @param jclass
	 */
	protected void showSortedProperties(JavaClass jclass) {
		List allP = jclass.getAllProperties();
		String[] names = new String[allP.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = ((EStructuralFeature) allP.get(i)).getName();
		}
		Arrays.sort(names);
		System.out.println("--- List of properties for " + jclass.toString());
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}		
	}
	
	protected JavaClass getButton() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/java.awt#Button"), true); //$NON-NLS-1$
	}

	protected JavaClass getJLabel() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/javax.swing#JLabel"), true); //$NON-NLS-1$
	}
	
	public void testExternalJar() throws IOException {
		objFeaturesSetup();
		
		// This tests getting beaninfo out of jar. The jar is within the project. It contains ButtonBeanInfo.
		JavaClass button = getButton();
		reflectHierachy(button);	// First reflect all parents (not introspect).
		// Now cause introspection.
		
		// To write out the names if want new set.
//		writeNames("d:/temp/extjarprops.txt", button.getAllProperties(), objFeaturesSet);		
		
		Set names = getNames("testdata/extjarprops.txt");
		testIncludesAllNames("Button", button.getAllProperties(), names, objFeaturesSet);

		// To write out the names if want new set.
//		writeNames("d:/temp/extjarops.txt", button.getEOperations(), Collections.EMPTY_SET);		
		
		names = getNames("testdata/extjarops.txt");
		testIncludesAllNames("Button", button.getEOperations(), names, Collections.EMPTY_SET);
		
//		showSortedProperties(button);
	}
	
	public void testJLabel() throws IOException {
		objFeaturesSetup();
		
		// This tests JLabel beaninfo out of current project, but through search path.
		JavaClass jlabel = getJLabel();
		reflectHierachy(jlabel);	// First reflect all parents (not introspect).
		// Now cause introspection.
	
		// To write out the names if want new set.
//		writeNames("d:/temp/jlabelprops.txt", jlabel.getAllProperties(), objFeaturesSet);
		
		Set names = getNames("testdata/jlabelprops.txt");
		testIncludesAllNames("JLabel", jlabel.getAllProperties(), names, objFeaturesSet);
		
		// To write out the names if want new set.
//		writeNames("d:/temp/jlabelops.txt", jlabel.getEAllOperations(), Collections.EMPTY_SET);		

		names = getNames("testdata/jlabelops.txt");
		testIncludesAllNames("JLabel", jlabel.getEAllOperations(), names, Collections.EMPTY_SET);
		
//		showSortedProperties(jlabel);
//		showSortedOperations(jlabel);
		
		// Test that isPreferred works both for 1.3 JVM and 1.4 and later since there was a bug in 1.3.
		EStructuralFeature enabledSF = jlabel.getEStructuralFeature("enabled"); //$NON-NLS-1$
		PropertyDecorator pd = Utilities.getPropertyDecorator(enabledSF);
		assertTrue(pd.isPreferred());
	}
	
	/*
	 * At time of this test creation, this is the sorted lists for comparison purposes.
	 * 
		--- List of properties for java.awt.Button
		actionCommand
		alignmentX
		alignmentY
		background
		bounds
		class
		colorModel
		componentOrientation
		cursor
		enabled
		events
		focusTraversable
		font
		foreground
		graphics
		implicit
		initializationString
		instantiateUsing
		label
		locale
		location
		locationOnScreen
		maximumSize
		minimumSize
		name
		parent
		preferredSize
		serializeData
		showing
		size
		toolkit
		treeLock
		valid
		visible
		
		--- List of properties for javax.swing.JLabel
		alignmentX
		alignmentY
		autoscrolls
		background
		border
		bounds
		class
		colorModel
		componentCount
		componentOrientation
		components
		cursor
		debugGraphicsOptions
		disabledIcon
		displayedMnemonic
		doubleBuffered
		enabled
		events
		focusCycleRoot
		focusTraversable
		font
		foreground
		graphics
		height
		horizontalAlignment
		horizontalTextPosition
		icon
		iconTextGap
		implicit
		initializationString
		insets
		instantiateUsing
		labelFor
		layout
		locale
		location
		locationOnScreen
		managingFocus
		maximumSize
		minimumSize
		name
		nextFocusableComponent
		opaque
		optimizedDrawingEnabled
		paintingTile
		parent
		preferredSize
		registeredKeyStrokes
		requestFocusEnabled
		rootPane
		serializeData
		showing
		size
		text
		toolTipText
		toolkit
		topLevelAncestor
		treeLock
		valid
		validateRoot
		verticalAlignment
		verticalTextPosition
		visible
		visibleRect
		width
		x
		y
	
		--- List of Operations for javax.swing.JLabel
		add(java.awt.Component)
		add(java.awt.Component,int)
		add(java.awt.Component,java.lang.Object)
		add(java.awt.Component,java.lang.Object,int)
		add(java.awt.PopupMenu)
		add(java.lang.String,java.awt.Component)
		addNotify()
		checkImage(java.awt.Image,java.awt.image.ImageObserver)
		computeVisibleRect(java.awt.Rectangle)
		contains(int,int)
		contains(java.awt.Point)
		createImage(int,int)
		createImage(java.awt.image.ImageProducer)
		createToolTip()
		dispatchEvent(java.awt.AWTEvent)
		doLayout()
		equals(java.lang.Object)
		firePropertyChange(java.lang.String,boolean,boolean)
		firePropertyChange(java.lang.String,char,char)
		firePropertyChange(java.lang.String,double,double)
		firePropertyChange(java.lang.String,float,float)
		firePropertyChange(java.lang.String,int,int)
		firePropertyChange(java.lang.String,long,long)
		firePropertyChange(java.lang.String,short,short)
		getAccessibleContext()
		getActionForKeyStroke(javax.swing.KeyStroke)
		getAlignmentX()
		getAlignmentY()
		getAutoscrolls()
		getBackground()
		getBorder()
		getBounds()
		getBounds(java.awt.Rectangle)
		getClass()
		getClientProperty(java.lang.Object)
		getColorModel()
		getComponent(int)
		getComponentAt(int,int)
		getComponentAt(java.awt.Point)
		getComponentCount()
		getComponents()
		getConditionForKeyStroke(javax.swing.KeyStroke)
		getCursor()
		getDisabledIcon()
		getDisplayedMnemonic()
		getFont()
		getFontMetrics(java.awt.Font)
		getForeground()
		getGraphics()
		getHeight()
		getHorizontalAlignment()
		getHorizontalTextPosition()
		getIcon()
		getIconTextGap()
		getInsets()
		getLabelFor()
		getLayout()
		getLocale()
		getLocation()
		getLocation(java.awt.Point)
		getLocationOnScreen()
		getMaximumSize()
		getMinimumSize()
		getName()
		getNextFocusableComponent()
		getParent()
		getPreferredSize()
		getRegisteredKeyStrokes()
		getRootPane()
		getSize()
		getSize(java.awt.Dimension)
		getText()
		getToolTipText()
		getToolkit()
		getTopLevelAncestor()
		getTreeLock()
		getUI()
		getVerticalAlignment()
		getVerticalTextPosition()
		getVisibleRect()
		getWidth()
		getX()
		getY()
		grabFocus()
		hasFocus()
		hashCode()
		invalidate()
		isAncestorOf(java.awt.Component)
		isDoubleBuffered()
		isEnabled()
		isFocusCycleRoot()
		isFocusTraversable()
		isManagingFocus()
		isOpaque()
		isOptimizedDrawingEnabled()
		isRequestFocusEnabled()
		isShowing()
		isValid()
		isValidateRoot()
		isVisible()
		list()
		list(java.io.PrintStream)
		list(java.io.PrintStream,int)
		list(java.io.PrintWriter,int)
		notify()
		notifyAll()
		paint(java.awt.Graphics)
		paintAll(java.awt.Graphics)
		paintImmediately(java.awt.Rectangle)
		prepareImage(java.awt.Image,java.awt.image.ImageObserver)
		print(java.awt.Graphics)
		printAll(java.awt.Graphics)
		putClientProperty(java.lang.Object,java.lang.Object)
		remove(int)
		remove(java.awt.Component)
		remove(java.awt.MenuComponent)
		removeAll()
		removeNotify()
		repaint()
		repaint(java.awt.Rectangle)
		requestDefaultFocus()
		requestFocus()
		resetKeyboardActions()
		revalidate()
		scrollRectToVisible(java.awt.Rectangle)
		setAlignmentX(float)
		setAlignmentY(float)
		setAutoscrolls(boolean)
		setBackground(java.awt.Color)
		setBorder(javax.swing.border.Border)
		setBounds(int,int,int,int)
		setBounds(java.awt.Rectangle)
		setCursor(java.awt.Cursor)
		setDebugGraphicsOptions(int)
		setDisabledIcon(javax.swing.Icon)
		setDisplayedMnemonic(char)
		setDoubleBuffered(boolean)
		setEnabled(boolean)
		setFont(java.awt.Font)
		setForeground(java.awt.Color)
		setHorizontalAlignment(int)
		setHorizontalTextPosition(int)
		setIcon(javax.swing.Icon)
		setIconTextGap(int)
		setLabelFor(java.awt.Component)
		setLayout(java.awt.LayoutManager)
		setLocale(java.util.Locale)
		setLocation(int,int)
		setLocation(java.awt.Point)
		setMaximumSize(java.awt.Dimension)
		setMinimumSize(java.awt.Dimension)
		setName(java.lang.String)
		setNextFocusableComponent(java.awt.Component)
		setOpaque(boolean)
		setPreferredSize(java.awt.Dimension)
		setRequestFocusEnabled(boolean)
		setSize(int,int)
		setSize(java.awt.Dimension)
		setText(java.lang.String)
		setToolTipText(java.lang.String)
		setUI(javax.swing.plaf.LabelUI)
		setVerticalAlignment(int)
		setVerticalTextPosition(int)
		setVisible(boolean)
		toString()
		transferFocus()
		unregisterKeyboardAction(javax.swing.KeyStroke)
		update(java.awt.Graphics)
		updateUI()
		validate()
		wait()
		wait(long)
		wait(long,int)
			
	 */

}
