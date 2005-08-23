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
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web Service Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getWebServiceDescriptionName <em>Web Service Description Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getWsdlFile <em>Wsdl File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getPortComponents <em>Port Components</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getDescriptionType <em>Description Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getDisplayNameType <em>Display Name Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServiceDescriptionImpl#getIconType <em>Icon Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebServiceDescriptionImpl extends EObjectImpl implements WebServiceDescription
{
	/**
	 * The default value of the '{@link #getJaxrpcMappingFile() <em>Jaxrpc Mapping File</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getJaxrpcMappingFile()
	 * @generated
	 * @ordered
	 */
  protected static final String JAXRPC_MAPPING_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJaxrpcMappingFile() <em>Jaxrpc Mapping File</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getJaxrpcMappingFile()
	 * @generated
	 * @ordered
	 */
  protected String jaxrpcMappingFile = JAXRPC_MAPPING_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWebServiceDescriptionName() <em>Web Service Description Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWebServiceDescriptionName()
	 * @generated
	 * @ordered
	 */
  protected static final String WEB_SERVICE_DESCRIPTION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWebServiceDescriptionName() <em>Web Service Description Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWebServiceDescriptionName()
	 * @generated
	 * @ordered
	 */
  protected String webServiceDescriptionName = WEB_SERVICE_DESCRIPTION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getWsdlFile() <em>Wsdl File</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlFile()
	 * @generated
	 * @ordered
	 */
  protected static final String WSDL_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsdlFile() <em>Wsdl File</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlFile()
	 * @generated
	 * @ordered
	 */
  protected String wsdlFile = WSDL_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSmallIcon() <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getSmallIcon()
	 * @generated
	 * @ordered
	 */
  protected static final String SMALL_ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSmallIcon() <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getSmallIcon()
	 * @generated
	 * @ordered
	 */
  protected String smallIcon = SMALL_ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getLargeIcon() <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getLargeIcon()
	 * @generated
	 * @ordered
	 */
  protected static final String LARGE_ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLargeIcon() <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getLargeIcon()
	 * @generated
	 * @ordered
	 */
  protected String largeIcon = LARGE_ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
  protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
  protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
  protected static final String DISPLAY_NAME_EDEFAULT = null;

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
	 * The cached value of the '{@link #getPortComponents() <em>Port Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getPortComponents()
	 * @generated
	 * @ordered
	 */
  protected EList portComponents = null;

	/**
	 * The cached value of the '{@link #getDescriptionType() <em>Description Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDescriptionType()
	 * @generated
	 * @ordered
	 */
  protected DescriptionType descriptionType = null;

	/**
	 * The cached value of the '{@link #getDisplayNameType() <em>Display Name Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDisplayNameType()
	 * @generated
	 * @ordered
	 */
  protected DisplayNameType displayNameType = null;

	/**
	 * The cached value of the '{@link #getIconType() <em>Icon Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getIconType()
	 * @generated
	 * @ordered
	 */
  protected IconType iconType = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WebServiceDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return WsddPackage.eINSTANCE.getWebServiceDescription();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getJaxrpcMappingFile() {
		return jaxrpcMappingFile;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setJaxrpcMappingFile(String newJaxrpcMappingFile) {
		String oldJaxrpcMappingFile = jaxrpcMappingFile;
		jaxrpcMappingFile = newJaxrpcMappingFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__JAXRPC_MAPPING_FILE, oldJaxrpcMappingFile, jaxrpcMappingFile));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getWebServiceDescriptionName() {
		return webServiceDescriptionName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setWebServiceDescriptionName(String newWebServiceDescriptionName) {
		String oldWebServiceDescriptionName = webServiceDescriptionName;
		webServiceDescriptionName = newWebServiceDescriptionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__WEB_SERVICE_DESCRIPTION_NAME, oldWebServiceDescriptionName, webServiceDescriptionName));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getWsdlFile() {
		return wsdlFile;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setWsdlFile(String newWsdlFile) {
		String oldWsdlFile = wsdlFile;
		wsdlFile = newWsdlFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__WSDL_FILE, oldWsdlFile, wsdlFile));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getSmallIconGen() {
		return smallIcon;
	}

  public String getSmallIcon() {
  	if(eResource() == null) 
  		return getSmallIconGen();
  	
  	switch (getJ2EEVersionID()) {
  	case J2EEVersionConstants.J2EE_1_2_ID :
  	case J2EEVersionConstants.J2EE_1_3_ID :
  		return getSmallIconGen(); 
  	case J2EEVersionConstants.J2EE_1_4_ID :
  	default :
  		if (getIconType() != null)
  			return getIconType().getSmallIcon();
  	}
  	return null;
  }

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setSmallIconGen(String newSmallIcon) {
		String oldSmallIcon = smallIcon;
		smallIcon = newSmallIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__SMALL_ICON, oldSmallIcon, smallIcon));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getLargeIconGen() {
		return largeIcon;
	}

  public String getLargeIcon() {
  	if(eResource() == null) 
  		return getLargeIconGen();
  	
  	switch (getJ2EEVersionID()) {
  	case J2EEVersionConstants.J2EE_1_2_ID :
  	case J2EEVersionConstants.J2EE_1_3_ID :
  		return getLargeIconGen(); 
  	case J2EEVersionConstants.J2EE_1_4_ID :
  	default :
  		if (getIconType() != null)
  			return getIconType().getLargeIcon();
  	}
  	return null;
  }

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setLargeIconGen(String newLargeIcon) {
		String oldLargeIcon = largeIcon;
		largeIcon = newLargeIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__LARGE_ICON, oldLargeIcon, largeIcon));
	}

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jst.j2ee.internal.common.DescriptionGroup#getJ2EEVersionID()
   */
  public int getJ2EEVersionID() throws IllegalStateException {
  	J2EEVersionResource res = (J2EEVersionResource) eResource();
  	if (res == null)
  		throw new IllegalStateException();
  	return res.getJ2EEVersionID();
  }
  
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getDescriptionGen() {
		return description;
	}

	public String getDescription() {
		if(eResource() == null) 
			return getDescriptionGen();
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			return getDescriptionGen(); 
		case J2EEVersionConstants.J2EE_1_4_ID :
		default :
			if (getDescriptionType() != null)
				return ((Description)getDescriptionType()).getValue();
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDescriptionGen(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION, oldDescription, description));
	}

  public void setDescription(String newDescription) {
  	if(eResource() == null) {
  		setDescriptionGen(newDescription);
  		internalSetDescriptionType(newDescription);
  		return;
  	}
  	
  	switch (getJ2EEVersionID()) {
  	case J2EEVersionConstants.J2EE_1_2_ID :
  	case J2EEVersionConstants.J2EE_1_3_ID :
  		setDescriptionGen(newDescription);
  		break;
  	case J2EEVersionConstants.J2EE_1_4_ID :
  	default :
  		internalSetDescriptionType(newDescription); 
  		break;
  	}
  }

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getDisplayNameGen() {
		return displayName;
	}

  public String getDisplayName() {
  	if(eResource() == null) 
  		return getDisplayNameGen();
  	
  	switch (getJ2EEVersionID()) {
  	case J2EEVersionConstants.J2EE_1_2_ID :
  	case J2EEVersionConstants.J2EE_1_3_ID :
  		return getDisplayNameGen(); 
  	case J2EEVersionConstants.J2EE_1_4_ID :
  	default :
  		if (getDisplayNameType() != null)
  			return ((DisplayName)getDisplayNameType()).getValue();
  	}
  	return null;
  }

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDisplayNameGen(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME, oldDisplayName, displayName));
	}

  public void setDisplayName(String newDisplayName) {
  	if(eResource() == null) {
  		setDisplayNameGen(newDisplayName);
  		internalSetDisplayNameType(newDisplayName);
  		return;
  	}
  	
  	switch (getJ2EEVersionID()) {
  	case J2EEVersionConstants.J2EE_1_2_ID :
  	case J2EEVersionConstants.J2EE_1_3_ID :
  		setDisplayNameGen(newDisplayName);
  		break;
  	case J2EEVersionConstants.J2EE_1_4_ID :
  	default :
  		internalSetDisplayNameType(newDisplayName);
  		break;
  	}
  }

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList getPortComponents() {
		if (portComponents == null) {
			portComponents = new EObjectContainmentEList(PortComponent.class, this, WsddPackage.WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS);
		}
		return portComponents;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public DescriptionType getDescriptionType() {
		return descriptionType;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetDescriptionType(DescriptionType newDescriptionType, NotificationChain msgs) {
		DescriptionType oldDescriptionType = descriptionType;
		descriptionType = newDescriptionType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE, oldDescriptionType, newDescriptionType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDescriptionType(DescriptionType newDescriptionType) {
		if (newDescriptionType != descriptionType) {
			NotificationChain msgs = null;
			if (descriptionType != null)
				msgs = ((InternalEObject)descriptionType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE, null, msgs);
			if (newDescriptionType != null)
				msgs = ((InternalEObject)newDescriptionType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE, null, msgs);
			msgs = basicSetDescriptionType(newDescriptionType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE, newDescriptionType, newDescriptionType));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public DisplayNameType getDisplayNameType() {
		return displayNameType;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetDisplayNameType(DisplayNameType newDisplayNameType, NotificationChain msgs) {
		DisplayNameType oldDisplayNameType = displayNameType;
		displayNameType = newDisplayNameType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE, oldDisplayNameType, newDisplayNameType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDisplayNameType(DisplayNameType newDisplayNameType) {
		if (newDisplayNameType != displayNameType) {
			NotificationChain msgs = null;
			if (displayNameType != null)
				msgs = ((InternalEObject)displayNameType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE, null, msgs);
			if (newDisplayNameType != null)
				msgs = ((InternalEObject)newDisplayNameType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE, null, msgs);
			msgs = basicSetDisplayNameType(newDisplayNameType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE, newDisplayNameType, newDisplayNameType));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public IconType getIconType() {
		return iconType;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetIconType(IconType newIconType, NotificationChain msgs) {
		IconType oldIconType = iconType;
		iconType = newIconType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE, oldIconType, newIconType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setIconType(IconType newIconType) {
		if (newIconType != iconType) {
			NotificationChain msgs = null;
			if (iconType != null)
				msgs = ((InternalEObject)iconType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE, null, msgs);
			if (newIconType != null)
				msgs = ((InternalEObject)newIconType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE, null, msgs);
			msgs = basicSetIconType(newIconType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE, newIconType, newIconType));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WsddPackage.WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS:
					return ((InternalEList)getPortComponents()).basicRemove(otherEnd, msgs);
				case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE:
					return basicSetDescriptionType(null, msgs);
				case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE:
					return basicSetDisplayNameType(null, msgs);
				case WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE:
					return basicSetIconType(null, msgs);
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WsddPackage.WEB_SERVICE_DESCRIPTION__JAXRPC_MAPPING_FILE:
				return getJaxrpcMappingFile();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WEB_SERVICE_DESCRIPTION_NAME:
				return getWebServiceDescriptionName();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WSDL_FILE:
				return getWsdlFile();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__SMALL_ICON:
				return getSmallIcon();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__LARGE_ICON:
				return getLargeIcon();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION:
				return getDescription();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME:
				return getDisplayName();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS:
				return getPortComponents();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE:
				return getDescriptionType();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE:
				return getDisplayNameType();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE:
				return getIconType();
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
			case WsddPackage.WEB_SERVICE_DESCRIPTION__JAXRPC_MAPPING_FILE:
				setJaxrpcMappingFile((String)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WEB_SERVICE_DESCRIPTION_NAME:
				setWebServiceDescriptionName((String)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WSDL_FILE:
				setWsdlFile((String)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS:
				getPortComponents().clear();
				getPortComponents().addAll((Collection)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE:
				setDescriptionType((DescriptionType)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE:
				setDisplayNameType((DisplayNameType)newValue);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE:
				setIconType((IconType)newValue);
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
			case WsddPackage.WEB_SERVICE_DESCRIPTION__JAXRPC_MAPPING_FILE:
				setJaxrpcMappingFile(JAXRPC_MAPPING_FILE_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WEB_SERVICE_DESCRIPTION_NAME:
				setWebServiceDescriptionName(WEB_SERVICE_DESCRIPTION_NAME_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WSDL_FILE:
				setWsdlFile(WSDL_FILE_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS:
				getPortComponents().clear();
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE:
				setDescriptionType((DescriptionType)null);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE:
				setDisplayNameType((DisplayNameType)null);
				return;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE:
				setIconType((IconType)null);
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
			case WsddPackage.WEB_SERVICE_DESCRIPTION__JAXRPC_MAPPING_FILE:
				return JAXRPC_MAPPING_FILE_EDEFAULT == null ? jaxrpcMappingFile != null : !JAXRPC_MAPPING_FILE_EDEFAULT.equals(jaxrpcMappingFile);
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WEB_SERVICE_DESCRIPTION_NAME:
				return WEB_SERVICE_DESCRIPTION_NAME_EDEFAULT == null ? webServiceDescriptionName != null : !WEB_SERVICE_DESCRIPTION_NAME_EDEFAULT.equals(webServiceDescriptionName);
			case WsddPackage.WEB_SERVICE_DESCRIPTION__WSDL_FILE:
				return WSDL_FILE_EDEFAULT == null ? wsdlFile != null : !WSDL_FILE_EDEFAULT.equals(wsdlFile);
			case WsddPackage.WEB_SERVICE_DESCRIPTION__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case WsddPackage.WEB_SERVICE_DESCRIPTION__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case WsddPackage.WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS:
				return portComponents != null && !portComponents.isEmpty();
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE:
				return descriptionType != null;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE:
				return displayNameType != null;
			case WsddPackage.WEB_SERVICE_DESCRIPTION__ICON_TYPE:
				return iconType != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (jaxrpcMappingFile: ");
		result.append(jaxrpcMappingFile);
		result.append(", webServiceDescriptionName: ");
		result.append(webServiceDescriptionName);
		result.append(", wsdlFile: ");
		result.append(wsdlFile);
		result.append(", smallIcon: ");
		result.append(smallIcon);
		result.append(", largeIcon: ");
		result.append(largeIcon);
		result.append(", description: ");
		result.append(description);
		result.append(", displayName: ");
		result.append(displayName);
		result.append(')');
		return result.toString();
	}

	/**
	 * @param newDescription
	 */
	protected void internalSetDescriptionType(String newDescription) {
		DescriptionType newValue = null;
		if(getDescriptionType() != null) {
			newValue = getDescriptionType();
			newValue.setValue(newDescription);
		}
		else {
			newValue = WscommonFactory.eINSTANCE.createDescriptionType();
			newValue.setValue(newDescription);
			setDescriptionType(newValue);
		}
		
	}

	/**
	 * @param newDisplayName
	 */
	protected void internalSetDisplayNameType(String newDisplayName) {
		DisplayNameType newValue = null;
		if(getDisplayNameType() != null) {
			newValue = getDisplayNameType();
			newValue.setValue(newDisplayName);
		}
		else  {
			newValue = WscommonFactory.eINSTANCE.createDisplayNameType();
			newValue.setValue(newDisplayName);
			setDisplayNameType(newValue);
		}			  
		
	}

	/**
	 * @param newLargeIcon
	 */
	protected void internalSetLargeIconType(String newLargeIcon) {
		IconType newValue = null;
		if(getIconType() != null) {
			newValue = getIconType();
			newValue.setLargeIcon(newLargeIcon);
		}
		else {
			newValue = CommonFactory.eINSTANCE.createIconType();
			newValue.setLargeIcon(newLargeIcon);
			setIconType(newValue);
		}
		
	}

	/**
	 * @param newSmallIcon
	 */
	protected void internalSetSmallIconType(String newSmallIcon) {
		IconType newValue = null;
		if(getIconType() != null) {
			newValue = getIconType();
			newValue.setSmallIcon(newSmallIcon);
		}
		else {
			newValue = CommonFactory.eINSTANCE.createIconType();
			newValue.setSmallIcon(newSmallIcon);
			setIconType(newValue);
		}
	}

	public void setLargeIcon(String newLargeIcon) {
		if(eResource() == null) {
			setLargeIconGen(newLargeIcon);
			internalSetLargeIconType(newLargeIcon);
			return;
		}
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			setLargeIconGen(newLargeIcon);
			break;
		case J2EEVersionConstants.J2EE_1_4_ID :
		default : 
			internalSetLargeIconType(newLargeIcon); 
			break;
		}
	}

	public void setSmallIcon(String newSmallIcon) {
		if(eResource() == null) {
			setSmallIconGen(newSmallIcon);
			internalSetSmallIconType(newSmallIcon); 
			return;
		}
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			setSmallIconGen(newSmallIcon);
			break;
		case J2EEVersionConstants.J2EE_1_4_ID :
		default : 
			internalSetSmallIconType(newSmallIcon); 
			break;
		}
	}

} //WebServiceDescriptionImpl
