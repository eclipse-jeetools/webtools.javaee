/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: FeatureDecoratorImpl.java,v $
 *  $Revision: 1.2.4.1 $  $Date: 2004/06/24 18:19:38 $ 
 */


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoFactory;
import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.FeatureAttributeValue;
import org.eclipse.jem.internal.beaninfo.FeatureDecorator;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoProxyConstants;
import org.eclipse.jem.internal.proxy.core.EnumerationBeanProxyWrapper;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBooleanBeanProxy;
import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.core.IStringBeanProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getShortDescription <em>Short Description</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isExpert <em>Expert</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isHidden <em>Hidden</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isPreferred <em>Preferred</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isMergeIntrospection <em>Merge Introspection</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isAttributesExplicit <em>Attributes Explicit</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureDecoratorImpl extends EAnnotationImpl implements FeatureDecorator{
	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	protected IBeanProxy fFeatureProxy;
	protected FeatureDecorator fFeatureDecoratorProxy;
	private int fIsImplicit = NOT_IMPLICIT;
	private String fProxyDisplayName = null;
	
	protected boolean isDesignTimeProxy = false;
	protected boolean setIsDesignTimeProxy;
	protected boolean isPreferredProxy = false;
	protected boolean setIsPreferredProxy;
	
	protected String categoryProxy;	
	protected boolean setCategoryProxy;
	protected String shortDescriptionProxy;
	protected boolean setShortDescriptionProxy;
	 
	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;
	/**
	 * This is true if the Display Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean displayNameESet = false;

	/**
	 * The default value of the '{@link #getShortDescription() <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortDescription() <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDescription()
	 * @generated
	 * @ordered
	 */
	protected String shortDescription = SHORT_DESCRIPTION_EDEFAULT;
	/**
	 * This is true if the Short Description attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean shortDescriptionESet = false;

	/**
	 * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected String category = CATEGORY_EDEFAULT;
	/**
	 * The default value of the '{@link #isExpert() <em>Expert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExpert()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXPERT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExpert() <em>Expert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExpert()
	 * @generated
	 * @ordered
	 */
	protected boolean expert = EXPERT_EDEFAULT;

	/**
	 * This is true if the Expert attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean expertESet = false;

	/**
	 * The default value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected boolean hidden = HIDDEN_EDEFAULT;

	/**
	 * This is true if the Hidden attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hiddenESet = false;

	/**
	 * The default value of the '{@link #isPreferred() <em>Preferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferred()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PREFERRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPreferred() <em>Preferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferred()
	 * @generated
	 * @ordered
	 */
	protected boolean preferred = PREFERRED_EDEFAULT;

	/**
	 * This is true if the Preferred attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean preferredESet = false;

	/**
	 * The default value of the '{@link #isMergeIntrospection() <em>Merge Introspection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeIntrospection()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MERGE_INTROSPECTION_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMergeIntrospection() <em>Merge Introspection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeIntrospection()
	 * @generated
	 * @ordered
	 */
	protected boolean mergeIntrospection = MERGE_INTROSPECTION_EDEFAULT;
	/**
	 * The default value of the '{@link #isAttributesExplicit() <em>Attributes Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAttributesExplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ATTRIBUTES_EXPLICIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAttributesExplicit() <em>Attributes Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAttributesExplicit()
	 * @generated
	 * @ordered
	 */
	protected boolean attributesExplicit = ATTRIBUTES_EXPLICIT_EDEFAULT;


	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EMap attributes = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected FeatureDecoratorImpl() {
		super();
		setSource(this.getClass().getName());		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getFeatureDecorator();
	}

	protected boolean retrievedAttributes;
	
	protected boolean validProxy(IBeanProxy proxy) {
		return proxy != null ? proxy.isValid() : false;
	}
			
	public EMap getAttributes() {
		if (!isAttributesExplicit()) {
			if (validProxy(fFeatureProxy) && !retrievedAttributes) {
				retrievedAttributes = true;
				EMap attribs = this.getAttributesGen();				
				
				IMethodProxy getValue = BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getValueProxy();
				try {				
					EnumerationBeanProxyWrapper attrNames = new EnumerationBeanProxyWrapper(BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getAttributeNamesProxy().invoke(fFeatureProxy));
					while (attrNames.hasMoreElements()) {
						try {
							IStringBeanProxy attrName = (IStringBeanProxy) attrNames.nextElement();
							IBeanProxy attrValue = getValue.invoke(fFeatureProxy, attrName);
							if ("ivjDesignTimeProperty".equals(attrName.stringValue())) { //$NON-NLS-1$
								// This is special, we pull it out.
								// This actually only has meaning for Properties, so we only set it here, it is not referenced till there.
								if (attrValue instanceof IBooleanBeanProxy) {
									IBooleanBeanProxy v = (IBooleanBeanProxy) attrValue;
									isDesignTimeProxy = v.booleanValue();
								} else
									isDesignTimeProxy = false;
								setIsDesignTimeProxy = true;
							} else if ("category".equals(attrName.stringValue())) { //$NON-NLS-1$
								// This is special we pull it out.
								if (attrValue instanceof IStringBeanProxy) {
									categoryProxy = ((IStringBeanProxy) attrValue).stringValue();
								} else
									categoryProxy = null;
								setCategoryProxy = true;
							} else if ("preferred".equals(attrName.stringValue())) { //$NON-NLS-1$
								// There is a bug JDK 1.3 where preferred setting in the FeatureDescriptor is not
								// preserved and is lost. This was fixed in 1.4. So to allow 1.3 preferred to work
								// we allow the convention that if there is a "preferred" attribute setting, then
								// we will use that for preferred. 
								// When sure that a descriptor is only used in 1.4, then the "preferred" attribute
								// can be removed from that beaninfo featureDescriptor.
								if (attrValue instanceof IBooleanBeanProxy) {
									IBooleanBeanProxy v = (IBooleanBeanProxy) attrValue;
									isPreferredProxy = v.booleanValue();
									setIsPreferredProxy = true;									
								} 

							} else {
								// See if entry already exists, if not create. If it does, and there wasn't a value proxy
								// previously set, then ignore it (because came from an override). If previously set, then
								// set with new one.
								String key = attrName.stringValue();
								FeatureAttributeValue fv = (FeatureAttributeValue) attribs.get(key);
								if (fv == null)
									fv = BeaninfoFactory.eINSTANCE.createFeatureAttributeValue();
								else if (!fv.isSetValueProxy())
									continue;
								fv.setValueProxy(attrValue);
								attribs.put(key, fv);
							}
						} catch (ThrowableProxy e) {
						}
					}
				} catch (ThrowableProxy e) {
				}						
			}
		}
		return this.getAttributesGen();
	}
	
	/**
	 * Was this decorator and/or feature implicitly created by introspection?
	 * The default should be NOT_IMPLICIT.
	 * This is here for linkage with the introspection process
	 * and is not really a MOF property. The introspection needs
	 * to know which features it created and which were explicitly
	 * created by other means. Implicitly created ones may be 
	 * deleted at any time when the introspection determines it
	 * needs to.
	 */
	public int isImplicitlyCreated() {
		return fIsImplicit;
	}
	
	public void setImplicitlyCreated(int implicit) {
		fIsImplicit = implicit;
	}
	
	
	public boolean isIntrospected() {
		return fFeatureProxy != null;
	}
	
	public IBeanProxy getDescriptorProxy() {
		return fFeatureProxy;
	}
	
	public void setDescriptorProxy(IBeanProxy descriptor) {
		fFeatureProxy = descriptor;
		fProxyDisplayName = null;
		if (retrievedAttributes) {
			attributes.clear();
			retrievedAttributes = false;
		}
		isDesignTimeProxy = false;
		setIsDesignTimeProxy = false;
		
		setCategoryProxy = false;
		categoryProxy = null;
		
		setShortDescriptionProxy = false;
		shortDescriptionProxy = null;
		
		isPreferredProxy = false;
		setIsPreferredProxy = false;

		if (validProxy(fFeatureProxy)) {
			// Cache display name because this is used over and over.
			if (!isSetDisplayName())
				try {
					fProxyDisplayName = ((IStringBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getDisplayNameProxy().invoke(fFeatureProxy)).stringValue();
				} catch (NullPointerException e) {
				} catch (ThrowableProxy e) {
				}
				
		}
				
	}
	
	public void setDecoratorProxy(FeatureDecorator decorator) {
		fFeatureDecoratorProxy = decorator;
	}
	
	public String getName() {
		ENamedElement ne = (ENamedElement) getEModelElement();
		if (ne != null)
			return ne.getName();	// The name from the owner of the feature has to be the name of feature.
		else
			return "?";	// Don't know what it is. //$NON-NLS-1$
	}
	
	public String getDisplayName() {
		if (!isSetDisplayName())
			if (fProxyDisplayName != null) 
				return fProxyDisplayName;
			else
				return getName();	// Use the name as the display name.
		return this.getDisplayNameGen();
	}
	public String getShortDescription() {
		if (!isSetShortDescription()) {
			if (!setShortDescriptionProxy) {
				// Short description is used over and over, so we cache it.
				setShortDescriptionProxy = true;
				if (validProxy(fFeatureProxy))
					try {
						IStringBeanProxy str = (IStringBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getShortDescriptionProxy().invoke(fFeatureProxy);
						if (str != null)
							shortDescriptionProxy = str.stringValue();
					} catch (ThrowableProxy e) {
					};
			}
			
			return shortDescriptionProxy != null ? shortDescriptionProxy : getDisplayName();	// It not set, then use display name.
		}
					
		return this.getShortDescriptionGen();
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		boolean oldDisplayNameESet = displayNameESet;
		displayNameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME, oldDisplayName, displayName, !oldDisplayNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDisplayName() {
		String oldDisplayName = displayName;
		boolean oldDisplayNameESet = displayNameESet;
		displayName = DISPLAY_NAME_EDEFAULT;
		displayNameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME, oldDisplayName, DISPLAY_NAME_EDEFAULT, oldDisplayNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDisplayName() {
		return displayNameESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortDescription(String newShortDescription) {
		String oldShortDescription = shortDescription;
		shortDescription = newShortDescription;
		boolean oldShortDescriptionESet = shortDescriptionESet;
		shortDescriptionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION, oldShortDescription, shortDescription, !oldShortDescriptionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetShortDescription() {
		String oldShortDescription = shortDescription;
		boolean oldShortDescriptionESet = shortDescriptionESet;
		shortDescription = SHORT_DESCRIPTION_EDEFAULT;
		shortDescriptionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION, oldShortDescription, SHORT_DESCRIPTION_EDEFAULT, oldShortDescriptionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetShortDescription() {
		return shortDescriptionESet;
	}

	public String getCategory() {
		if (category != CATEGORY_EDEFAULT)
			return getCategoryGen();
		getAttributes();	// Force retrieval of attributes so that we can see if category is there.
		return setCategoryProxy ? categoryProxy : CATEGORY_EDEFAULT;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCategoryGen() {
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategory(String newCategory) {
		String oldCategory = category;
		category = newCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__CATEGORY, oldCategory, category));
	}

	public boolean isExpert() {
		if (validProxy(fFeatureProxy) && !isSetExpert())
			try {
				return ((IBooleanBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getIsExpertProxy().invoke(fFeatureProxy)).booleanValue();
			} catch (ThrowableProxy e) {
			};
					
		return this.isExpertGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExpertGen() {
		return expert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpert(boolean newExpert) {
		boolean oldExpert = expert;
		expert = newExpert;
		boolean oldExpertESet = expertESet;
		expertESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__EXPERT, oldExpert, expert, !oldExpertESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExpert() {
		boolean oldExpert = expert;
		boolean oldExpertESet = expertESet;
		expert = EXPERT_EDEFAULT;
		expertESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__EXPERT, oldExpert, EXPERT_EDEFAULT, oldExpertESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExpert() {
		return expertESet;
	}

	/**
	 */
	public boolean isHidden() {
		if (validProxy(fFeatureProxy) && !isSetHidden())
			try {
				return ((IBooleanBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getIsHiddenProxy().invoke(fFeatureProxy)).booleanValue();
			} catch (ThrowableProxy e) {
			};
					
		return this.isHiddenGen();
	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHiddenGen() {
		return hidden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHidden(boolean newHidden) {
		boolean oldHidden = hidden;
		hidden = newHidden;
		boolean oldHiddenESet = hiddenESet;
		hiddenESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__HIDDEN, oldHidden, hidden, !oldHiddenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHidden() {
		boolean oldHidden = hidden;
		boolean oldHiddenESet = hiddenESet;
		hidden = HIDDEN_EDEFAULT;
		hiddenESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__HIDDEN, oldHidden, HIDDEN_EDEFAULT, oldHiddenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHidden() {
		return hiddenESet;
	}

	public boolean isPreferred() {
		getAttributes();	// This will cause the preferred flag to be set if found. (Due to 1.3 bug, we need ti also test the attributes).
		if (setIsPreferredProxy)
			return isPreferredProxy;
		else if (validProxy(fFeatureProxy) && !isSetPreferred())
			try {
				return ((IBooleanBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getIsPreferredProxy().invoke(fFeatureProxy)).booleanValue();
			} catch (ThrowableProxy e) {
			};
					
		return this.isPreferredGen();
	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPreferredGen() {
		return preferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferred(boolean newPreferred) {
		boolean oldPreferred = preferred;
		preferred = newPreferred;
		boolean oldPreferredESet = preferredESet;
		preferredESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__PREFERRED, oldPreferred, preferred, !oldPreferredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPreferred() {
		boolean oldPreferred = preferred;
		boolean oldPreferredESet = preferredESet;
		preferred = PREFERRED_EDEFAULT;
		preferredESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__PREFERRED, oldPreferred, PREFERRED_EDEFAULT, oldPreferredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPreferred() {
		return preferredESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMergeIntrospection() {
		return mergeIntrospection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergeIntrospection(boolean newMergeIntrospection) {
		boolean oldMergeIntrospection = mergeIntrospection;
		mergeIntrospection = newMergeIntrospection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION, oldMergeIntrospection, mergeIntrospection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (displayName: ");
		if (displayNameESet) result.append(displayName); else result.append("<unset>");
		result.append(", shortDescription: ");
		if (shortDescriptionESet) result.append(shortDescription); else result.append("<unset>");
		result.append(", category: ");
		result.append(category);
		result.append(", expert: ");
		if (expertESet) result.append(expert); else result.append("<unset>");
		result.append(", hidden: ");
		if (hiddenESet) result.append(hidden); else result.append("<unset>");
		result.append(", preferred: ");
		if (preferredESet) result.append(preferred); else result.append("<unset>");
		result.append(", mergeIntrospection: ");
		result.append(mergeIntrospection);
		result.append(", attributesExplicit: ");
		result.append(attributesExplicit);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplayNameGen() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortDescriptionGen() {
		return shortDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap getAttributesGen() {
		if (attributes == null) {
			attributes = new EcoreEMap(BeaninfoPackage.eINSTANCE.getFeatureAttributeMapEntry(), FeatureAttributeMapEntryImpl.class, this, BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
					return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT:
				return isAttributesExplicit() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				return getAttributes();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(ATTRIBUTES_EXPLICIT_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT:
				return attributesExplicit != ATTRIBUTES_EXPLICIT_EDEFAULT;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAttributesExplicit() {
		return attributesExplicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributesExplicit(boolean newAttributesExplicit) {
		boolean oldAttributesExplicit = attributesExplicit;
		attributesExplicit = newAttributesExplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT, oldAttributesExplicit, attributesExplicit));
	}

	/**
	 * @see org.eclipse.jem.internal.beaninfo.FeatureDecorator#needIntrospection()
	 */
	public boolean needIntrospection() {
		return fFeatureProxy != null && !validProxy(fFeatureProxy);
	}

}
