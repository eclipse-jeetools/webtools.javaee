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
package org.eclipse.jem.internal.instantiation.base;
/*
 *  $RCSfile: JavaObjectInstance.java,v $
 *  $Revision: 1.12 $  $Date: 2005/02/04 23:11:33 $ 
 */

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;

import org.eclipse.jem.internal.instantiation.JavaAllocation;
import org.eclipse.jem.java.JavaHelpers;

/**
 * This is the default instance for java model objects.
 * It should not be referenced directly, the IJavaObjectInstance interface should be
 * used instead. It is public so that it can be subclassed.
 */
public class JavaObjectInstance extends EObjectImpl implements IJavaObjectInstance , FeatureValueProvider {
	
	public JavaHelpers getJavaType(){
		return (JavaHelpers) eClass();
	}
	
	public JavaAllocation getAllocation() {
		return isSetAllocation() ? (JavaAllocation) eGet(JavaInstantiation.getAllocationFeature(this)) : null;
	}
	
	/** 
	 * Visit the argument with all of the set features in an optimized fashion 
	 */
	private final static Object NIL = EStructuralFeatureImpl.InternalSettingDelegateSingle.NIL;
	public void visitSetFeatures(Visitor aVisitor) {
		if (eHasSettings()) {
			JavaObjectInstancePropertiesHolder settings = (JavaObjectInstancePropertiesHolder) eProperties();

			Object[] setPropertyValues = settings.eSettings();
			if (setPropertyValues != null) {
				List allFeatures = settings.getAllStructuralFeatures();
				for (int i = 0; i < setPropertyValues.length; i++) {
					Object propertyValue = setPropertyValues[i];
					if (propertyValue != null) {
						// <null> is handled by the placeholder NIL. A setting of true null means not set. A setting of NIL means set to null.
						if (propertyValue == NIL)
							propertyValue = null;
						aVisitor.isSet((EStructuralFeature) allFeatures.get(i), propertyValue);
					}
				}
			} 
		}												
	}
		
	public boolean isSetAllocation() {
		return eIsSet(JavaInstantiation.getAllocationFeature(this));
	}
	
	public void setAllocation(JavaAllocation allocation) {
		eSet(JavaInstantiation.getAllocationFeature(this), allocation);
	}
	
	
	public boolean isPrimitive(){
		return false;
	}
	
	public String toString() {
		// EObject's toString is too big for us, so we do a customized one.
		StringBuffer result = new StringBuffer(getClass().getName());
		result.append('@');
		result.append(Integer.toHexString(hashCode()));
		
		if (eIsProxy())
		{
		  result.append(" (eProxyURI: "); //$NON-NLS-1$
		  result.append(eProxyURI());
		  result.append(')');
		}
		if(getJavaType() != null){
			result.append('{');
			result.append(getJavaType().getQualifiedName());
			result.append('}');
		}
		
		try {
			JavaAllocation allocation = getAllocation();
			if (allocation != null) {
				result.append(':'); //$NON-NLS-1$
				result.append(allocation.toString());
			}
		} catch (IllegalArgumentException e) {
		} catch (NullPointerException e) {
			// This can occur because sometimes this eClass can't be constructed right and won't have an initstring feature.
			// In that case an NPE is thrown.
		}
		return result.toString();
	}
	
	protected static class JavaObjectInstancePropertiesHolder extends EPropertiesHolderImpl {
		private EList allStructuralFeatures;
		
		public JavaObjectInstancePropertiesHolder() {
		}
		
		public Object[] eSettings() {
			return eSettings;
		}

		public EList getAllStructuralFeatures() {
			return allStructuralFeatures;
		}
		
		/*
		 * Clear the cache. This is due to 
		 * structural features have changed.
		 */
		public void clearCache() {
			eSettings = null;
			setEContents(null);
			setECrossReferences(null);
			allStructuralFeatures = null;			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.emf.ecore.impl.EObjectImpl.EPropertiesHolderImpl#allocateSettings(int)
		 */
		public void allocateSettings(int maximumDynamicFeatureID) {
			if (allStructuralFeatures == null)
				allStructuralFeatures = getEClass().getEAllStructuralFeatures();			
			super.allocateSettings(maximumDynamicFeatureID);
		}
		
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.ecore.impl.EObjectImpl.EPropertiesHolderImpl#setEContents(org.eclipse.emf.common.util.EList)
		 */
		public void setEContents(EList eContents) {
			if (allStructuralFeatures == null)
				allStructuralFeatures = getEClass().getEAllStructuralFeatures();
			super.setEContents(eContents);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.emf.ecore.impl.EObjectImpl.EPropertiesHolderImpl#setECrossReferences(org.eclipse.emf.common.util.EList)
		 */
		public void setECrossReferences(EList eCrossReferences) {
			if (allStructuralFeatures == null)
				allStructuralFeatures = getEClass().getEAllStructuralFeatures();
			super.setECrossReferences(eCrossReferences);
		}

	}
	
	protected EPropertiesHolder eProperties()
	{
	  if (eProperties == null)
	  {
		eProperties = new JavaObjectInstancePropertiesHolder();
	  }
	  return eProperties;
	}
		
	/**
	 * @see org.eclipse.emf.ecore.InternalEObject#eSetClass(EClass)
	 */
	public void eSetClass(EClass eClass) {
		super.eSetClass(eClass);
		migrate();
	}

	/**
	 * @param newEClass New eClass set to. (null) when migrating while not setting a new EClass.
	 */
	protected void migrate() {
		// Note: This is extremelly implementation dependent. It may change for any implementation of EMF.	
		if (eProperties != null && (eProperties.hasSettings() || eProperties.getEContents() != null || eProperties.getECrossReferences() != null)) {
			// Maybe need to reconstruct settings or clear cache.
			JavaObjectInstancePropertiesHolder properties = (JavaObjectInstancePropertiesHolder) eProperties;
			EList oldAllFeatures = properties.getAllStructuralFeatures();
			
			// See if migration needed.
			if (properties.getEClass().getEAllStructuralFeatures() == oldAllFeatures)
				return;	// No migration needed.
			
			Object[] oldSettings = properties.eSettings();			
			properties.clearCache();	// Clear the cache so we can rebuild it.
			if (oldSettings == null) {
				return;	// It was just either contents or crossrefs, and they have been appropriately cleared.			
			}
			
			// It is assumed that any SF that (by identity) is in
			// both the old and the new eClass, then it doesn't have any internal changes. It simply changed position
			// in the settings list. Otherwise, need to see if compatible by same name, and if so, move it.			
			eSettings();	// Create new settings
			Object[] newSettings = properties.eSettings();
			int staticFeatureCnt = eStaticFeatureCount();			
			for (int oldIndex = 0; oldIndex < oldSettings.length; oldIndex++) {
				if (oldSettings[oldIndex] != null) {
					EStructuralFeature sf = (EStructuralFeature) oldAllFeatures.get(oldIndex+staticFeatureCnt);
					int newIndex = super.eDynamicFeatureID(sf);	// To avoid recurse on migrate.
					if (newIndex > -1) {
						moveESetting(oldSettings, newSettings, oldIndex, sf, newIndex);
					} else {
						// See if it exists by name and is compatible.
						EStructuralFeature newSF = properties.getEClass().getEStructuralFeature(sf.getName());
						if (newSF != null && newSF.getClass().equals(sf.getClass()) &&
							newSF.isMany() == sf.isMany() && newSF.isChangeable() == sf.isChangeable()) {
							boolean compatible = newSF.isUnique() == sf.isUnique() || !newSF.isUnique();	// If new is not unique, then doesn't matter if old and new are the same
							if (newSF instanceof EReference) {
								EReference newRef = (EReference) newSF;
								EReference ref = (EReference) sf;
								compatible = newRef.isContainment() == ref.isContainment() && newRef.getEReferenceType().isSuperTypeOf(ref.getEReferenceType());
							} else
								compatible = newSF.getEType().equals(sf.getEType());
								
							if (compatible) {
								newIndex = eDynamicFeatureID(newSF);
								moveESetting(oldSettings, newSettings, oldIndex, newSF, newIndex);
							}
						}
					}
				}
			}
		} 
	}

	private void moveESetting(Object[] oldSettings, Object[] newSettings, int oldIndex, EStructuralFeature sf, int newIndex) {
		// See if single vs many.
		if (!sf.isMany())
			newSettings[newIndex] = oldSettings[oldIndex];	// Great, we can just move it over.
		else {
			// Many is more difficult. Need to create a new dynamic list of right type, and
			// then just copy over the data from the old one. We create new one by doing a simple eGet.
			// This will construct an empty one with no notifications going out.
			// Note: This is extremelly implementation dependent. We will be throwing away the old
			// oldMany, so it is ok to reuse the actual array of data for the newMany.
			BasicEList newMany = (BasicEList) eGet(sf);
			BasicEList oldMany = (BasicEList) oldSettings[oldIndex];
			newMany.setData(oldMany.size(), oldMany.data()); 
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eDynamicFeatureID(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	protected int eDynamicFeatureID(EStructuralFeature eStructuralFeature) {
		migrate();	// See if migration needed, and do if it does.
		return super.eDynamicFeatureID(eStructuralFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eContents()
	 */
	public EList eContents() {
		migrate();			
		return super.eContents();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
	 */
	public EList eCrossReferences() {
		migrate();			
		return super.eCrossReferences();
	}

}
