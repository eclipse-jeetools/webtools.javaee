/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.taglib.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.taglib.ExtensibleType;
import org.eclipse.jst.j2ee.taglib.Function;
import org.eclipse.jst.j2ee.taglib.JSPTag;
import org.eclipse.jst.j2ee.taglib.JSPTagAttribute;
import org.eclipse.jst.j2ee.taglib.JSPVariable;
import org.eclipse.jst.j2ee.taglib.TagFile;
import org.eclipse.jst.j2ee.taglib.TagLib;
import org.eclipse.jst.j2ee.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.TldExtension;
import org.eclipse.jst.j2ee.taglib.Validator;



public class TaglibAdapterFactory extends AdapterFactoryImpl {
	protected static TaglibPackage modelPackage;

	public TaglibAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = (TaglibPackage)EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI);
		}
	}
	public boolean isFactoryForType(Object type) {
		if (type == modelPackage) {
			return true;
		}
		if (type instanceof EObject) {
			return ((EObject)type).eClass().eContainer() == modelPackage;
		}
		return false;
	}

	protected TaglibSwitch sw = new TaglibSwitch() {
		public Object caseTagLib(TagLib object) {
			return createTagLibAdapter();
		}
		public Object caseValidator(Validator object) {
			return createValidatorAdapter();
		}
		public Object caseJSPTag(JSPTag object) {
			return createJSPTagAdapter();
		}
		public Object caseJSPTagAttribute(JSPTagAttribute object) {
			return createJSPTagAttributeAdapter();
		}
		public Object caseJSPVariable(JSPVariable object) {
			return createJSPVariableAdapter();
		}
	};

	public Adapter createAdapter(Notifier target) {
		return (Adapter)sw.doSwitch((EObject)target);
	}

	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	public Adapter createTagLibAdapter() {
		return null;
	}

	public Adapter createValidatorAdapter() {
		return null;
	}

	public Adapter createJSPTagAdapter() {
		return null;
	}

	public Adapter createJSPTagAttributeAdapter() {
		return null;
	}

	public Adapter createJSPVariableAdapter() {
		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean isFactoryForTypeGen(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaglibSwitch modelSwitch =
		new TaglibSwitch() {
			public Object caseTagLib(TagLib object) {
				return createTagLibAdapter();
			}
			public Object caseJSPTag(JSPTag object) {
				return createJSPTagAdapter();
			}
			public Object caseJSPTagAttribute(JSPTagAttribute object) {
				return createJSPTagAttributeAdapter();
			}
			public Object caseValidator(Validator object) {
				return createValidatorAdapter();
			}
			public Object caseJSPVariable(JSPVariable object) {
				return createJSPVariableAdapter();
			}
			public Object caseFunction(Function object) {
				return createFunctionAdapter();
			}
			public Object caseTagFile(TagFile object) {
				return createTagFileAdapter();
			}
			public Object caseTldExtension(TldExtension object) {
				return createTldExtensionAdapter();
			}
			public Object caseExtensibleType(ExtensibleType object) {
				return createExtensibleTypeAdapter();
			}
			public Object caseDescriptionGroup(DescriptionGroup object) {
				return createDescriptionGroupAdapter();
			}
			public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
				return createCompatibilityDescriptionGroupAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAdapterGen(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createTagLibAdapterGen() {

		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createValidatorAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createJSPTagAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createJSPTagAttributeAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createJSPVariableAdapterGen() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.taglib.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.taglib.Function
	 * @generated
	 */
	public Adapter createFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.taglib.TagFile <em>Tag File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.taglib.TagFile
	 * @generated
	 */
	public Adapter createTagFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.taglib.TldExtension <em>Tld Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.taglib.TldExtension
	 * @generated
	 */
	public Adapter createTldExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.taglib.ExtensibleType <em>Extensible Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.taglib.ExtensibleType
	 * @generated
	 */
	public Adapter createExtensibleTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.common.DescriptionGroup <em>Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.common.DescriptionGroup
	 * @generated
	 */
	public Adapter createDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.internal.common.CompatibilityDescriptionGroup <em>Compatibility Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.internal.common.CompatibilityDescriptionGroup
	 * @generated
	 */
	public Adapter createCompatibilityDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TaglibAdapterFactory



