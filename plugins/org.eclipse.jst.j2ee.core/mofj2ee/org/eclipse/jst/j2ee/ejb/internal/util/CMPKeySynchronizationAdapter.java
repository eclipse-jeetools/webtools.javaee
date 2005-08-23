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
/*
 * Created on Oct 24, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.ejb.internal.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaVisibilityKind;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.internal.impl.ContainerManagedEntityImpl;


/**
 * @author jlanuti
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CMPKeySynchronizationAdapter extends AdapterImpl {
    
    public static final String ADAPTER_TYPE = CMPKeySynchronizationAdapter.class.getName();
    
    private boolean initialized = false;

	protected ContainerManagedEntity cmp = null;
	private boolean isUpdating = false;
	private boolean isEnabled = true;
	
	/**
	 * Default constructor
	 */
	public CMPKeySynchronizationAdapter(ContainerManagedEntity cmp) {
		super();
		this.cmp = cmp;
	}

	/**
	 * If the keyAttributes list is empty, try to reflect
	 * the keyAttributes from the fields of the primaryKey class.
	 */
	public void initializeKeyAttributes() {
	    if(cmp == null || initialized)
	        return;
	    
	    Resource res = cmp.eResource();
	    if(res == null)
	        return;
	    
	    boolean cachedIsModified = res.isModified(); 

	    try {
			// Handle primkeyfield scenario
			if (cmp.getPrimKeyField()!=null)
				initializeForPrimKey();
			
			// Handle compound key scenario
			else 
				initializeForCompoundKey();
	    } finally {
	        res.setModified(cachedIsModified);
	    }

        initialized = true;
	}
	
	protected void initializeForPrimKey() { 
		// Ensure the key attributes list contains only the primKeyField attribute
		if (!(getKeyAttributes().size()==1 && getKeyAttributes().contains(cmp.getPrimKeyField())))
			primKeyFieldChanged(cmp.getPrimKeyField());
	}
	
	protected void initializeForCompoundKey() { 
		List keys = getKeyAttributes();
		// clear key list
		keys.clear();
		//ensure the key class is not null
		JavaClass keyClass = cmp.getPrimaryKey();
		if (keyClass == null)
			return;
		keyClass.eAdapters().add(this);
		
		// reflect key fields into key attributes list
		List keyFields = keyClass.getFieldsExtended();
		for (int i = 0; i < keyFields.size(); i++) {
			Field field = (Field) keyFields.get(i);
			if (field.getJavaVisibility() == JavaVisibilityKind.PUBLIC_LITERAL) { //only public fields
				CMPAttribute keyAttribute = cmp.getPersistentAttribute(field.getName());
				if (keyAttribute != null)
					keys.add(keyAttribute);
			}	
		}
	}
	
	public void notifyChanged(Notification notification) {
		if (isUpdating || !isEnabled) return; //we don't want to react to our own changes
		try {
			isUpdating = true;
			EClass featureClass = ((EObject)notification.getNotifier()).eClass();
			if (EjbPackage.eINSTANCE.getContainerManagedEntity().equals(featureClass)) {			
				switch (notification.getFeatureID(ContainerManagedEntity.class)) {
					// handle if cmp attribute is updated, added, removed
					case EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES : {
						handleCMPAttributeEvent(notification);
						break;	
					}
					// handle the key attributes list is updated programatically
					case EjbPackage.CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES : {
						handleCMPAttributeEvent(notification);
						break;	
					}	
					// handle if the key class is changed,added, removed
					case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIMARY_KEY : {
						handleKeyClassEvent(notification);
						break;
					}
					// handle prim key field sets, adds, removes
					case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD : {
						handlePrimKeyFieldEvent(notification);
						break;
					}
				}
			} else if (EjbPackage.eINSTANCE.getCMPAttribute().equals(featureClass)) {
				switch (notification.getFeatureID(CMPAttribute.class)) {
					// handle a set or change of an attribute name
					case (EjbPackage.CMP_ATTRIBUTE__NAME) : {
						handleCMPAttributeRenameEvent((CMPAttribute)notification.getNotifier(), notification.getOldValue());
						break;	
					}
				}
			}
		} finally { 
			// restore initial notification state
			isUpdating = false;
		}		
	}

	/**
	 * @param attribute
	 */
	private void handleCMPAttributeRenameEvent(CMPAttribute attribute, Object oldValue) {
		// if renamed attribute was in key and is not anymore, remove it from key list
		if (getKeyAttributes().contains(attribute)) {
			if (!isAttributeKeyClassField(attribute.getName()))
				removeKeyAttribute(attribute,(String)oldValue);
		}
		// if renamed attribute is now part of the key, add it to the key list		
		else
			attributeAdded(attribute);	
	}

	/**
	 * @param notification
	 */
	private void handlePrimKeyFieldEvent(Notification notification) {
		EObject newPrimKeyField = (EObject)notification.getNewValue();
		cmp.setPrimKeyField((CMPAttribute)newPrimKeyField);
		// if primKeyField is removed or a proxy, recompute key attributes based on key class
		if (newPrimKeyField == null || newPrimKeyField.eIsProxy()) {
			CMPAttribute primField = (CMPAttribute)newPrimKeyField;
			if (primField!=null && primField.getName() == null) {
				primField.setName(((InternalEObject)primField).eProxyURI().toString());
				makePrimKeyFieldProxy(primField.getName());
			}
			removeAllKeyAttributes();
			initialized = false;
			initializeKeyAttributes();
		}
		// Ensure the key attributes list contains only the primKeyField attribute
		else if (!(getKeyAttributes().size()==1 && getKeyAttributes().contains(newPrimKeyField))) {
			primKeyFieldChanged(newPrimKeyField);
		}		
			
	}

	/**
	 * @param notification
	 */
	private void handleKeyClassEvent(Notification notification) {
		// if primkey null or proxy, clear key fields and reinitialize if key class not null
		if (cmp.getPrimKeyField()==null || cmp.getPrimKeyField().eIsProxy()) {
			keyClassChanged();
		}
		// if primkey is not null, then do nothing
		
	}

	/**
	 * @param notification
	 */
	private void handleCMPAttributeEvent(Notification notification) {
		int eventType = notification.getEventType();
		// handle a CMPAttribute added
		 if (eventType == Notification.ADD) {
			 attributeAdded((CMPAttribute)notification.getNewValue());
		 }
		 // handle a CMPAttribute removed
		 else if (eventType == Notification.REMOVE) {
		 	CMPAttribute attribute = (CMPAttribute)notification.getOldValue();
			// remove this adapter from the deleted attribute
			attribute.eAdapters().remove(this);
			removeKeyAttribute(attribute, attribute.getName());
			
		 }
	}

	protected void removeKeyAttribute(CMPAttribute attribute, String oldName) {
		
		// if attribute was a key attribute it will be removed, otherwise do nothing
		getKeyAttributes().remove(attribute);
		
		// if it is the prim key attribute, then make the prim key attribute a proxy
		if (cmp.getPrimKeyField()!=null && cmp.getPrimKeyField().equals(attribute)) {
			makePrimKeyFieldProxy(oldName);
		}
			
	}
	
	public void makePrimKeyFieldProxy(String oldName) {
		if (cmp.getPrimKeyField() == null) return;
		cmp.setPrimKeyField(EjbFactory.eINSTANCE.createCMPAttribute());
		cmp.getPrimKeyField().setName(oldName);
		((InternalEObject)cmp.getPrimKeyField()).eSetProxyURI(URI.createURI(oldName));
		
	}
	
	protected void removeAllKeyAttributes() {
		// clear key attributes list on cmp
		getKeyAttributes().clear();
	}
	
	protected void addKeyAttribute(CMPAttribute attribute) {
		
		CMPAttribute primKeyField = cmp.getPrimKeyField();
		// if prim key is null add attribute to key list
		if (primKeyField==null || primKeyField.eIsProxy()) {
			if (!getKeyAttributes().contains(attribute))
				getKeyAttributes().add(attribute);
			// if att is only key att, and type is the key class type, set the prim key field
			if (getKeyAttributes().size()==1 && attribute.getType().equals(cmp.getPrimaryKey()))
				cmp.setPrimKeyField(attribute);
		}
		// if primkey set + primkeyfield is the new attribute ensure its the only one
		else if (primKeyField.equals(attribute)) {
			if (!(getKeyAttributes().size()==1 && getKeyAttributes().contains(attribute))) {
				getKeyAttributes().clear();
				getKeyAttributes().add(attribute);
			}	
		}
		// if primkey set,new attribute not prim key, key atts > 1, remove primkey field
		else {
			cmp.setPrimKeyField(null);
			if (!getKeyAttributes().contains(attribute))
				getKeyAttributes().add(attribute);
		}		
	}
	
	protected void attributeAdded(CMPAttribute attribute) {
		//Make sure cmp key adapters are initialized if needed
		cmp.getKeyAttributes();
		
		// add this as a listener to name changes on this attribute
		if (!attribute.eAdapters().contains(this))
			attribute.eAdapters().add(this);
		
		if (attribute.getName() ==null) return;
		// add it to the key list if applicable and sort the key list
		if (isAttributeKeyClassField(attribute.getName())) {
			addKeyAttribute(attribute);
			sortKeyAttributeList();
		}
		// if new attribute name is now prim key name, fix up prim key field
		else if (cmp.getPrimKeyField()!=null && cmp.getPrimKeyField().eIsProxy() && attribute.getName().equals(cmp.getPrimKeyField().getName())) {
			cmp.setPrimKeyField(attribute);
			primKeyFieldChanged(attribute);
		}	
	}
	

	/**
	 * Is the cmp attribute also a field in the key class?
	 */
	protected boolean isAttributeKeyClassField(String attributeName) {
		// ensure key class is not null
		JavaClass keyClass = cmp.getPrimaryKey();
		if (keyClass == null)
			return false;
		// see if the attribute name matchs any of the reflected key class public field names
		List keyFields = keyClass.getFieldsExtended();
		for (int i = 0; i < keyFields.size(); i++) {
			Field field = (Field) keyFields.get(i);
			if (field.getJavaVisibility() == JavaVisibilityKind.PUBLIC_LITERAL) {
				if (field.getName().equals(attributeName))
					return true;
			}	
		}
		return false;	
	}
	
	/**
	 * Sort the list of the key attributes to match the order of the key class
	 */
	private void sortKeyAttributeList() {
		
		List result = new ArrayList();
		JavaClass keyClass = cmp.getPrimaryKey();
		if (keyClass == null) return;
		List keyFields = keyClass.getFieldsExtended();
		List currentKeys = getKeyAttributes();
		
		// create a new list of the attributes in order of key class fields.
		for (int i = 0; i < keyFields.size(); i++) {
			Field field = (Field) keyFields.get(i);
			if (field.getJavaVisibility() == JavaVisibilityKind.PUBLIC_LITERAL) { //only public fields
				for (int j=0; j<currentKeys.size(); j++) {
					CMPAttribute attribute = (CMPAttribute) currentKeys.get(j);
					if (field.getName().equals(attribute.getName()))
						result.add(attribute);	
				}			
			}	
		}
		//clear list and set the new key attribute order on the cmp
		getKeyAttributes().clear();
		getKeyAttributes().addAll(result);
	}
	
	protected void keyClassChanged() {
		JavaClass key = cmp.getPrimaryKey();
		if (key !=null)
			key.eAdapters().remove(this);
		// if not prim key class, clear key attribute list
		removeAllKeyAttributes();
		initialized = false;
		initializeKeyAttributes();
	}
	
	protected void primKeyFieldChanged(EObject primKeyField) {
		// if a primKeyField is added, do a remove all and an add
		removeAllKeyAttributes();
		
		if (primKeyField != null && !primKeyField.eIsProxy()) {
			if (!primKeyField.eAdapters().contains(this))
				primKeyField.eAdapters().add(this);
			getKeyAttributes().add(primKeyField);
		}
	}
	
	private EList getKeyAttributes() {
	    return ((ContainerManagedEntityImpl)cmp).getKeyAttributesGen();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		super.setTarget(newTarget); 
	}
	
	
    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return ADAPTER_TYPE.equals(type);
    }
    //To turn sync on or off
    public void setEnabled(boolean enabled){
    	isEnabled = enabled;
    }
	
}
