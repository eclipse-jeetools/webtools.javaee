/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;


import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigFactory;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;


/**
 * Initializer class to preregister packages
 */
public class ArchiveInit {
	protected static boolean initialized = false;
	protected static boolean plugin_initialized = false;

	public static void init() {
		init(true);
	}

	public static void init(boolean shouldPreRegisterPackages) {
		if (!initialized) {
			initialized = true;
			invokePrereqInits(shouldPreRegisterPackages);
			if (shouldPreRegisterPackages)
				preRegisterPackages();
		}
	}

	private static void preRegisterPackages() {
		//CommonarchivePackage reg
		ExtendedEcoreUtil.preRegisterPackage("commonarchive.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
						@Override
						public EPackage getEPackage() {
							return CommonarchivePackage.eINSTANCE;
						}
						@Override
						public EFactory getEFactory() {
							return CommonarchiveFactory.eINSTANCE;
						}
					});
		ExtendedEcoreUtil.preRegisterPackage("commonarchive.looseconfig.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
						@Override
						public EPackage getEPackage() {
							return LooseconfigPackage.eINSTANCE;
						}
						@Override
						public EFactory getEFactory() {
							return LooseconfigFactory.eINSTANCE;
						}
					});
	}

	public static void invokePrereqInits(boolean shouldPreRegisterPackages) {
		J2EEInit.init(shouldPreRegisterPackages);
	}
}
