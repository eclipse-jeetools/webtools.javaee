/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * $RCSfile: LocalLaunchTabGroup.java,v $ $Revision: 1.5 $ $Date: 2005/08/24 21:07:12 $
 */
package org.eclipse.jem.internal.ui.proxy.remote;

import org.eclipse.debug.ui.*;
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.*;

/**
 * Launch Tab Group for the Local Launch of a Remote Proxy Configuration.
 * 
 * @since 1.0.0
 */
public class LocalLaunchTabGroup extends AbstractLaunchConfigurationTabGroup {

	/**
	 * Constructs a new Java applet tab group.
	 */
	public LocalLaunchTabGroup() {
	}

	/*
	 * @see ILaunchConfigurationTabGroup#createTabs(ILaunchConfigurationDialog, String)
	 */
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs =
			new ILaunchConfigurationTab[] {
				new LocalLaunchProjectTab(),
				new JavaArgumentsTab(),
				new JavaJRETab(),
				new JavaClasspathTab(),
				new SourceLookupTab(),	// TODO Need to see why SourceLookupTab didn't work. Until then use this.
				new EnvironmentTab(),
				new CommonTab()};
		setTabs(tabs);
	}

}
