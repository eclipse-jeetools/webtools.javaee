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
 * Created on Mar 31, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class ContainerManagedEntityTranslator extends EntityTranslator {

	private static Translator[] children13;
	private static Translator[] children14;
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public ContainerManagedEntityTranslator() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		return EjbFactory.eINSTANCE.createContainerManagedEntity();
	}
	//TODO Uncomment all commented code and fix
	/*
	protected String extractStringValue(MapInfo map, EObject mofObject) {
		if (map.getDOMName().equals(PRIMKEY_FIELD)) {	
			CMPAttribute attr = getContainerManagedEntity().getPrimaryKeyAttribute();
			return attr == null ? getSpecifiedPrimKeyFieldName() : attr.getName();
		} else
			return super.extractStringValue(map, mofObject);
	
	} 

	protected String getSpecifiedPrimKeyFieldName() {
		return CMPSpecAdapter.getSpecifiedPrimKeyFieldName(getContainerManagedEntity());
	}
	*/
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		switch (versionID) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :
				if (children13 == null)
					children13 = create13Children();
				return children13;	
			default :
				if (children14 == null)
					children14 = create14Children();
				return children14; 
		}
	}

	protected String getPersistenceType() {
		return CONTAINER;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.model.translator.ejb.EntityTranslator#getSpecificMaps()
	 */
	protected Translator[] getSpecificMaps(int versionID) {
		Translator[] cmpTranslators = new Translator[] {
			//Begin EJB 2.0 added
			new CMPVersionTranslator(),
			new Translator(ABSTRACT_SCHEMA_NAME, EJB_PKG.getContainerManagedEntity_AbstractSchemaName()),
			//End EJB 2.0 added
		
			new CMPFieldTranslator(),
			new PrimKeyFieldTranslator(),
		};
		return (Translator[]) concat(super.getSpecificMaps(versionID), cmpTranslators);
	}
	
	
	protected Translator[] getEndMaps13() {
		//Added for EJB 2.0
		Translator[] queryT = new Translator[] {
			createQueryTranslators()
		};
		return (Translator[]) concat(super.getEndMaps13(), queryT);
	}
	
	protected Translator[] getEndMaps14() {
			//Added for EJB 2.0
			Translator[] queryT = new Translator[] {
				createQueryTranslators()
			};
			return (Translator[]) concat(super.getEndMaps14(), queryT);
	}
		
	public static Translator createQueryTranslators() {
		GenericTranslator result = new GenericTranslator(QUERY, EJB_PKG.getContainerManagedEntity_Queries());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, EJB_PKG.getQuery_Description()),
			createQueryMethodTranslators(),
			new Translator(RESULT_TYPE_MAPPING, EJB_PKG.getQuery_ReturnTypeMapping()),
			new Translator(EJB_QL, EJB_PKG.getQuery_EjbQL())
		});
		return result;
	}
	
	public static Translator createQueryMethodTranslators() {
		GenericTranslator result = new GenericTranslator(QUERY_METHOD, EJB_PKG.getQuery_QueryMethod());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(METHOD_NAME, EJB_PKG.getMethodElement_Name()),
			new MethodParamsTranslator(EJB_PKG.getMethodElement_Parms())
			});
		return result;
	}
	
	/*
	protected void primUpdateDOMFeature(MapInfo map, Node node, EObject mofObject) {
		if (map == noFeaturePrimKeyMapInfo)
			return;
		if (map.getDOMName().equals(CMP_VERSION)) {
			updateDOMCmpVersionIfNecessary(map, node, mofObject);
			return;
		}
		super.primUpdateDOMFeature(map, node, mofObject);
		//If the primary key class has changed, this will impact the primary key field
		if (map.getDOMName().equals(PRIM_KEY_CLASS)) {
			String specifiedPrimKeyField = CMPSpecAdapter.getSpecifiedPrimKeyFieldName(getContainerManagedEntity());
			try {
				super.primUpdateDOMFeature(noFeaturePrimKeyMapInfo, node, mofObject);
			} finally {
				CMPSpecAdapter.setPrimKeyFieldFromXml(specifiedPrimKeyField, getContainerManagedEntity());
			}
		}
	}
	

	protected void updateDOMCmpVersionIfNecessary(MapInfo map, Node node, EObject mofObject) {
		if (!(mofObject instanceof ContainerManagedEntity)) 
			return;
		
		EJBJar parent = ((ContainerManagedEntity)mofObject).getEjbJar();
		if (parent.isVersion2_0Descriptor())
			super.primUpdateDOMFeature(map, node, mofObject);
	}
	protected void primUpdateDOMMultiFeature(MapInfo map, Node node, EObject mofObject) {
		if (map == featurePrimKeyMapInfo) 
			super.primUpdateDOMFeature(noFeaturePrimKeyMapInfo, node, mofObject);
		else 
			super.primUpdateDOMMultiFeature(map, node, mofObject);
	}
	protected boolean primUpdateMOFFeature(MapInfo map, Node node, EObject mofObject) {
		boolean hasChanged = super.primUpdateMOFFeature(map, node, mofObject);
		//If the key class has changed, from the xml side, then we need to make sure we stamp the primkey field
		//as being specified from xml, since we will not automatically remove that node
		if (map.getDOMName().equals(PRIM_KEY_CLASS)) {
			updateXmlKeyField(noFeaturePrimKeyMapInfo, node);
			if (hasChanged)
				updateMOFPrimKeyFieldFeature(featurePrimKeyMapInfo, getNode(), true);
		}
		return hasChanged;
	}
	protected void primUpdateMOFMultiFeature(MapInfo map, Node node, EObject mofObject) {
		if (map == noFeaturePrimKeyMapInfo)
			return;
		if (map.getDOMName().equals(PRIMKEY_FIELD)) {
			updateMOFPrimKeyFieldFeature(map, node, false);
		} else if (map.getDOMName().equals(CMP_FIELD)) {
			//cache the old list of cmp attributes before update
			List cmpAttributesListCOPY = new ArrayList();
			cmpAttributesListCOPY.addAll(getContainerManagedEntity().getPersistentAttributes());
		
			//do the update
			super.primUpdateMOFMultiFeature(map, node, mofObject);

			List newCmpAttributeList = getContainerManagedEntity().getPersistentAttributes();
			//key the cmp attribute list and the key attribute list.
			performKeySynchronization(cmpAttributesListCOPY, newCmpAttributeList);
		} else 
			super.primUpdateMOFMultiFeature(map, node, mofObject);
	}
	/**
	 * Method that synchronizies the list of cmp attributes that are keys with the key attribute list.
	 * When keys are added and deleted from the cmp attribute list.
	 * 
	 * @param List cmpAttributesListCOPY - Copy of the cmp attribute list before any attributes where added
	 * 										or removed.
	 * @param List newCmpAttributeList - List of cmp attributes after a modification has occured.
	 */
	 /*
	protected void performKeySynchronization( List cmpAttributesListCOPY, List newCmpAttributeList ) {	
		if( cmpAttributesListCOPY != null && newCmpAttributeList != null ) {	

			//add and removes keys based on what the adapters give back
		
			List addedCmpAttributeList = getCmpAttributesDeltaList( cmpAttributesListCOPY, newCmpAttributeList );
			if( addedCmpAttributeList != null && !addedCmpAttributeList.isEmpty() ) {					
				//determine if any of the added attributes are keys, if so get a list of them.
				List addedKeyAttributeList = getKeyAttributesFromList( addedCmpAttributeList, getContainerManagedEntity().getPrimaryKey() );
				//now, add the keys to the list.
				addKeysToKeyAttributeList( addedKeyAttributeList, getContainerManagedEntity() );
			}// if
		
			List subtractedCmpAttributeList = getCmpAttributesDeltaList( newCmpAttributeList, cmpAttributesListCOPY );
			if( subtractedCmpAttributeList != null && !subtractedCmpAttributeList.isEmpty() ) {
				//determine if any of the subtracted attributes are keys, if so get a list of them.
				List subtractedKeyAttributeList = getKeyAttributesFromList( subtractedCmpAttributeList, getContainerManagedEntity().getPrimaryKey() );
				//now, subtract the keys from the list.
				removeKeysFromKeyAttributeList( subtractedKeyAttributeList, getContainerManagedEntity() );
			}// if		
		
		}// if	
	}// performKeySynchronization

	/**
	 * Remove a bunch of keys from the current key attribute list.
	 * 
	 * @param List subtractedKeyAttributeList - The list of keys to subtract from the key attributes list.
	 * @param ContainerManagedEntity cmp - The current container managed entity.
	 */
	 
	 /*
	public static void removeKeysFromKeyAttributeList( List subtractedKeyAttributeList, ContainerManagedEntity cmp ) {
		if( subtractedKeyAttributeList != null && !subtractedKeyAttributeList.isEmpty() ) {
			//Subtract the key attributes from the container managed entity
			Iterator kli = subtractedKeyAttributeList.iterator();
		
			while( kli.hasNext() ) {
				CMPAttribute cmpAttribute = (CMPAttribute)kli.next();
				String cmpAttributeName = cmpAttribute.getName();
				if( cmpAttributeName != null ) {
					if( cmp != null && cmp.getKeyAttributes() != null ) {
						List keyAttributeList = cmp.getKeyAttributes();
						keyAttributeList.remove( cmpAttribute );
					}// if
				}// if
			}// while
		}// if
	}// performKeySynchronization

	/**
	 * Adds a bunch of keys to the current key attribute list.
	 * 
	 * @param List addedCmpAttributeList - The list of keys to add to the key attributes list.
	 * @param ContainerManagedEntity cmp - The current container managed entity.
	 */
	 
	 /*
	public static void addKeysToKeyAttributeList( List addedKeyAttributeList, ContainerManagedEntity cmp ) {
		if( addedKeyAttributeList != null && !addedKeyAttributeList.isEmpty() ) {
			//Add the key attributes to the container managed entity
			Iterator kli = addedKeyAttributeList.iterator();
		
			while( kli.hasNext() ) {
				CMPAttribute cmpAttribute = (CMPAttribute)kli.next();
				String cmpAttributeName = cmpAttribute.getName();
				if( cmpAttributeName != null ) {
					if( cmp != null && cmp.getKeyAttributes() != null )
						cmp.addKeyAttributeName( cmpAttributeName );
				}// if
			}// while
		}// if
	}// addKeysToKeyAttributeList

	/**
	 * Gets the key attributes contained in the list.
	 * 
	 * @param List attributeList - List of attributes.
	 * @param JavaClass primaryKey - The primary key of the cmp.
	 * @return List
	 */
	 
	 /*
	public static List getKeyAttributesFromList(  List attributeList, JavaClass primaryKey ) {
		if (primaryKey == null || attributeList == null || attributeList.isEmpty()) 
			return Collections.EMPTY_LIST;
		
		List foundKeyAttributeList = new ArrayList();
		Iterator it = attributeList.iterator();		
		while( it.hasNext() ) {	
			CMPAttribute cmpAttr = (CMPAttribute)it.next();			
			if( cmpAttr != null ) {
				String cmpAttributeName = cmpAttr.getName();
				if (cmpAttributeName != null) {			
					Field keyField = primaryKey.getField(cmpAttributeName);	
					if (keyField != null) 				
						foundKeyAttributeList.add(cmpAttr);
				}// if
			} //if
		}// while	
		return foundKeyAttributeList;
	}// getKeyAttributesFromList

	/**
	 * Gets the delta elements of two lists
	 * 
	 * @param List firstList - The list that is being compared too.
	 * @param List secondList - The list to check the difference of.
	 * @return List
	 */
	 
	 /*
	protected List getCmpAttributesDeltaList( List firstList, List secondList ) {
		List diffAttributeList = new ArrayList();
	
		if( firstList != null && secondList != null ) {
			Iterator it = secondList.iterator();
			while( it.hasNext() ) {
				CMPAttribute cmpAttribute = (CMPAttribute)it.next();
				if( cmpAttribute != null && !firstList.contains( cmpAttribute ) ) {
					diffAttributeList.add( cmpAttribute );
				}// if
			}// while
			return diffAttributeList;
		}// if	
		return java.util.Collections.EMPTY_LIST;
	}// getCmpAttributesDeltaList

	protected void updateMOFPrimKeyFieldFeature(MapInfo map, Node node, boolean force) {
		Resource res = getEObject().eResource();
		boolean modFlag = res == null ? false : res.isModified();
		String keyField = getDOMText(map, node);
		//Determine if the prim-key field has changed.  If so, then recompute the keys; otherwise leave 
		//it alone
		if (keyField != null)
			keyField = keyField.trim();
		CMPAttribute existingKeyAtt = getContainerManagedEntity().getPrimaryKeyAttribute();
		String existingKeyField = existingKeyAtt == null ? null : existingKeyAtt.getName();
		if (!force 
			&& ((keyField == null && existingKeyField == null) 
				|| (keyField != null && keyField.equals(existingKeyField))
				|| (existingKeyField != null && existingKeyField.equals(keyField))))
			return;
		
		try {
			getContainerManagedEntity().clearKeyAttributes(); //necessary to clear reflected flag
			CMPAttribute keyAtt = null;
			CMPSpecAdapter.setPrimKeyFieldFromXml(keyField, getContainerManagedEntity());
			if (keyField != null) {
				keyAtt = getContainerManagedEntity().addKeyAttributeName(keyField, false);
				if (keyAtt != null) {
					//This is an invalid state.
					String className = getContainerManagedEntity().getPrimaryKeyName();
					JavaClass ref = null;
					if (className != null)
						ref = JavaClassImpl.createClassRef(className);
					keyAtt.setEType(ref);
				}
			} else {
				//Hack to force the UI to refresh
				EList keys = getContainerManagedEntity().getKeyAttributes();
				if (!keys.isEmpty())
					keys.set(0, keys.get(0));
			}
		} finally {
			if (res != null && res.isTrackingModification())
				res.setModified(modFlag);
		}
	}
	protected void updateXmlKeyField(MapInfo map, Node node) {
		String keyField = getDOMText(map, node);
		CMPSpecAdapter.setPrimKeyFieldFromXml(keyField, getContainerManagedEntity());
	}
	
		/**
		 * @see AbstractDOMNodeAdapter#getMOFChildren(EObject, MapInfo)
		 */
		 /*
		protected List getMOFChildren(EObject mofObject, MapInfo map) {
			if (getContainerManagedEntity().isVersion2_X() && CMP_FIELD.equals(map.getDOMName()))
				return get20CMPAttributes((ContainerManagedEntity)mofObject);
			else
				return super.getMOFChildren(mofObject, map);
		}

		/**
		 * Method get20CMPAttributes.
		 * @param containerManagedEntity
		 * @return List
		 */
		 
		 /*
		private List get20CMPAttributes(ContainerManagedEntity cmp) {
			List allAttributes, removed, result;
			removed = null;
			allAttributes = cmp.getPersistentAttributes();
			int size = allAttributes.size();
			CMPAttribute att = null;
			for (int i = 0; i < size; i++) {
				att = (CMPAttribute) allAttributes.get(i);
				if (att.isDerived()) {
					if (removed == null)
						removed = new ArrayList();
					removed.add(att);
				}
			}
			if (removed != null) {
				result = new ArrayList(size);
				result.addAll(allAttributes);
				result.removeAll(removed);
			} else
				result = allAttributes;
			return result;
		}
	
		/**
		 * @see com.ibm.etools.mof2dom.AbstractDOMNodeAdapter#notifyChanged(new ENotificationImpl((InternalEObject)Notifier, int,(EStructuralFeature) EObject, Object, Object, int))
		 */
		 
		 /*
		public void notifyChanged(Notification notification) {
			if (notification.getEventType() == ContainerManagedEntity.DERIVED_FLAG_CHANGE)
				updateDOMFeature(getMap(CMP_FIELD), getNode(), getEObject());
			else
				super.notifyChanged(notification);
		}

		private MapInfo getMap(String aNodeName) {
			for (int i = 0; i < fMaps.length; i++) {
				if (aNodeName.equals(fMaps[i].getDOMName()))
					return fMaps[i];
			}
			return null;
		}
		*/
	}


