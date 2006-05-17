/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeanInfoDecoratorUtility.java,v $
 *  $Revision: 1.8 $  $Date: 2006/05/17 20:13:00 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import java.io.*;
import java.util.*;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.change.*;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.jem.internal.beaninfo.*;
import org.eclipse.jem.internal.beaninfo.common.*;
import org.eclipse.jem.internal.beaninfo.core.BeaninfoPlugin;
import org.eclipse.jem.internal.beaninfo.core.Utilities;
import org.eclipse.jem.internal.beaninfo.impl.*;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.java.*;

/**
 * This is a utility class for handling the BeanInfo decorators with respect to the overrides (explicit settings) vs. introspected/reflected (implicit
 * settings) It handles the transmission of data from the VM for introspection.
 * @since 1.1.0
 */
public class BeanInfoDecoratorUtility {

	/**
	 * Clear out the implicit settings for FeatureDecorator.
	 * 
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	public static void clear(FeatureDecorator decor) {
		long implicitSettings = decor.getImplicitlySetBits();
		// For each setting, see if it was implicitly set, and if it was, then unset it.
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_DISPLAYNAME_IMPLICIT) != 0)
			decor.unsetDisplayName();
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_SHORTDESC_IMPLICIT) != 0)
			decor.unsetShortDescription();
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_CATEGORY_IMPLICIT) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getFeatureDecorator_Category());
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_EXPERT_IMPLICIT) != 0)
			decor.unsetExpert();
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_HIDDEN_IMPLICIT) != 0)
			decor.unsetHidden();
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_PREFERRED_IMPLICIT) != 0)
			decor.unsetPreferred();
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_ATTRIBUTES_IMPLICIT) != 0) {
			for (Iterator itr = decor.getAttributes().listIterator(); itr.hasNext();) {
				FeatureAttributeMapEntryImpl entry = (FeatureAttributeMapEntryImpl)itr.next();
				if (entry.getTypedValue().isImplicitValue()) {
					itr.remove();
				}
			}
		}	
		decor
				.setImplicitlySetBits(implicitSettings
						& ~(FeatureDecoratorImpl.FEATURE_DISPLAYNAME_IMPLICIT | FeatureDecoratorImpl.FEATURE_SHORTDESC_IMPLICIT
								| FeatureDecoratorImpl.FEATURE_CATEGORY_IMPLICIT | FeatureDecoratorImpl.FEATURE_EXPERT_IMPLICIT
								| FeatureDecoratorImpl.FEATURE_HIDDEN_IMPLICIT | FeatureDecoratorImpl.FEATURE_PREFERRED_IMPLICIT | FeatureDecoratorImpl.FEATURE_ATTRIBUTES_IMPLICIT));
	}

	/**
	 * Clear out the implicit settings for BeanDecorator
	 * 
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	public static void clear(BeanDecorator decor) {
		clear((FeatureDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		// For each setting, see if it was implicitly set, and if it was, then unset it.
		if ((implicitSettings & BeanDecoratorImpl.BEAN_CUSTOMIZER_IMPLICIT) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getBeanDecorator_CustomizerClass());
		if ((implicitSettings & BeanDecoratorImpl.BEAN_MERGE_INHERITED_PROPERTIES_IMPLICIT) != 0)
			decor.unsetMergeSuperProperties();
		if ((implicitSettings & BeanDecoratorImpl.BEAN_MERGE_INHERITED_OPERATIONS_IMPLICIT) != 0)
			decor.unsetMergeSuperMethods();
		if ((implicitSettings & BeanDecoratorImpl.BEAN_MERGE_INHERITED_EVENTS_IMPLICIT) != 0)
			decor.unsetMergeSuperEvents();
		if (decor.eIsSet(BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedPropertyNames()))
			decor.eUnset(BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedPropertyNames()); // Just clear them. This is our attribute. It should
																							   // not be set overrides.
		if (decor.eIsSet(BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedMethodNames()))
			decor.eUnset(BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedMethodNames()); // Just clear them. This is our attribute. It should
																							   // not be set overrides.
		if (decor.eIsSet(BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedEventNames()))
			decor.eUnset(BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedEventNames()); // Just clear them. This is our attribute. It should not
																							// be set overrides.

		decor.setImplicitlySetBits(implicitSettings
				& ~(BeanDecoratorImpl.BEAN_CUSTOMIZER_IMPLICIT | BeanDecoratorImpl.BEAN_MERGE_INHERITED_PROPERTIES_IMPLICIT
						| BeanDecoratorImpl.BEAN_MERGE_INHERITED_OPERATIONS_IMPLICIT | BeanDecoratorImpl.BEAN_MERGE_INHERITED_EVENTS_IMPLICIT));
	}

	/**
	 * Clear out the implicit settings of the PropertyDecorator.
	 * 
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	public static void clear(PropertyDecorator decor) {
		clear((FeatureDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		// For each setting, see if it was implicitly set, and if it was, then unset it.
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_EDITOR_CLASS_IMPLICIT) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getPropertyDecorator_PropertyEditorClass());
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_READMETHOD_IMPLICIT) != 0)
			decor.unsetReadMethod();
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_WRITEMETHOD_IMPLICIT) != 0)
			decor.unsetWriteMethod();
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_FIELD_IMPLICIT) != 0) {
			decor.unsetField();		
			decor.eUnset(BeaninfoPackage.eINSTANCE.getPropertyDecorator_Field());
		}
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_BOUND_IMPLICIT) != 0)
			decor.unsetBound();
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_CONSTRAINED_IMPLICIT) != 0)
			decor.unsetConstrained();
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_DESIGNTIME_IMPLICIT) != 0)
			decor.unsetDesignTime();
		decor.setImplicitlySetBits(implicitSettings
				& ~(PropertyDecoratorImpl.PROPERTY_EDITOR_CLASS_IMPLICIT | PropertyDecoratorImpl.PROPERTY_READMETHOD_IMPLICIT
						| PropertyDecoratorImpl.PROPERTY_WRITEMETHOD_IMPLICIT | PropertyDecoratorImpl.PROPERTY_BOUND_IMPLICIT
						| PropertyDecoratorImpl.PROPERTY_CONSTRAINED_IMPLICIT | PropertyDecoratorImpl.PROPERTY_DESIGNTIME_IMPLICIT));
	}

	/**
	 * Clear out the implicit settings of the IndexedPropertyDecorator.
	 * 
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	public static void clear(IndexedPropertyDecorator decor) {
		clear((PropertyDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		// For each setting, see if it was implicitly set, and if it was, then unset it.
		if ((implicitSettings & IndexedPropertyDecoratorImpl.INDEXED_READMETHOD_IMPLICIT) != 0)
			decor.unsetIndexedReadMethod();
		if ((implicitSettings & IndexedPropertyDecoratorImpl.INDEXED_WRITEMETHOD_IMPLICIT) != 0)
			decor.unsetIndexedWriteMethod();
		decor.setImplicitlySetBits(implicitSettings
				& ~(IndexedPropertyDecoratorImpl.INDEXED_READMETHOD_IMPLICIT | IndexedPropertyDecoratorImpl.INDEXED_WRITEMETHOD_IMPLICIT));
	}

	/**
	 * Clear the method decorator of any implicit settings.
	 * 
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	public static void clear(MethodDecorator decor) {
		clear((FeatureDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		// For each setting, see if it was implicitly set, and if it was, then unset it.
		if ((implicitSettings & (MethodDecoratorImpl.METHOD_PARAMETERS_IMPLICIT | MethodDecoratorImpl.METHOD_PARAMETERS_DEFAULT)) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getMethodDecorator_SerParmDesc());
		decor.setImplicitlySetBits(implicitSettings
				& ~(MethodDecoratorImpl.METHOD_PARAMETERS_IMPLICIT | MethodDecoratorImpl.METHOD_PARAMETERS_DEFAULT));
	}

	/**
	 * Clear the event set decorator of any implicit settings.
	 * 
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	public static void clear(EventSetDecorator decor) {
		clear((FeatureDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		// For each setting, see if it was implicitly set, and if it was, then unset it.
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_ADDLISTENERMETHOD_IMPLICIT) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getEventSetDecorator_AddListenerMethod());
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_ADAPTERCLASS_IMPLICIT) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getEventSetDecorator_EventAdapterClass());
		if ((implicitSettings & (EventSetDecoratorImpl.EVENT_LISTENERMETHODS_IMPLICIT | EventSetDecoratorImpl.EVENT_LISTENERMETHODS_DEFAULT)) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getEventSetDecorator_SerListMthd());
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_REMOVELISTENERMETHOD_IMPLICIT) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getEventSetDecorator_RemoveListenerMethod());
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_DEFAULTEVENTSET_IMPLICIT) != 0)
			decor.unsetInDefaultEventSet();
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_UNICAST_IMPLICIT) != 0)
			decor.unsetUnicast();
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_LISTENERTYPE_IMPLICIT) != 0)
			decor.eUnset(BeaninfoPackage.eINSTANCE.getEventSetDecorator_ListenerType());

		decor.setImplicitlySetBits(implicitSettings
				& ~(EventSetDecoratorImpl.EVENT_ADDLISTENERMETHOD_IMPLICIT | EventSetDecoratorImpl.EVENT_ADAPTERCLASS_IMPLICIT
						| EventSetDecoratorImpl.EVENT_LISTENERMETHODS_IMPLICIT | EventSetDecoratorImpl.EVENT_LISTENERMETHODS_DEFAULT
						| EventSetDecoratorImpl.EVENT_REMOVELISTENERMETHOD_IMPLICIT | EventSetDecoratorImpl.EVENT_DEFAULTEVENTSET_IMPLICIT
						| EventSetDecoratorImpl.EVENT_UNICAST_IMPLICIT | EventSetDecoratorImpl.EVENT_LISTENERTYPE_IMPLICIT));

	}

	public static void introspect(IBeanProxy modelBeaninfoProxy, IntrospectCallBack callback) {
		ProxyIntrospectCallBack cb = new ProxyIntrospectCallBack(callback);
		modelBeaninfoProxy.getProxyFactoryRegistry().getCallbackRegistry().registerCallback(modelBeaninfoProxy, cb);
		try {
			BeaninfoProxyConstants.getConstants(modelBeaninfoProxy.getProxyFactoryRegistry()).getSendBeanInfoProxy()
					.invokeCatchThrowableExceptions(modelBeaninfoProxy);
		} finally {
			modelBeaninfoProxy.getProxyFactoryRegistry().getCallbackRegistry().deregisterCallback(modelBeaninfoProxy);
		}

	}

	/**
	 * This call back is for each requested type of record. It allows the callee to process this record.
	 * 
	 * @since 1.1.0
	 */
	public interface IntrospectCallBack {

		/**
		 * Process the BeanDecoratorRecord. The callee can decide what needs to be done with this record. It would return the BeandDecorator that needs
		 * to have the record applied to. If it returns <code>null</code> then the record will be ignored.
		 * <p>
		 * Note: This will be called on a separate thread from that which initiated the request. Therefor be careful with any locks because you may
		 * have them on a separate thread.
		 * 
		 * @param record
		 * @return BeanDecorator to be applied to, or <code>null</code> if record is to be ignored.
		 * 
		 * @since 1.1.0
		 */
		public BeanDecorator process(BeanRecord record);

		/**
		 * Process the PropertyRecord. The callee can decide what needs to be done with this record. It would return the PropertyDecorator that needs
		 * to have the record applied to. If it returns <code>null</code> then the record will be ignored.
		 * <p>
		 * Note: This will be called on a separate thread from that which initiated the request. Therefor be careful with any locks because you may
		 * have them on a separate thread.
		 * 
		 * @param record
		 * @return PropertyDecorator to be applied to, or <code>null</code> if record is to be ignored.
		 * 
		 * @since 1.1.0
		 */
		public PropertyDecorator process(PropertyRecord record);

		/**
		 * Process the IndexedPropertyRecord. The callee can decide what needs to be done with this record. It would return the
		 * IndexedPropertyDecorator that needs to have the record applied to. If it returns <code>null</code> then the record will be ignored.
		 * 
		 * <p>
		 * Note: This will be called on a separate thread from that which initiated the request. Therefor be careful with any locks because you may
		 * have them on a separate thread.
		 * 
		 * @param record
		 * @return PropertyDecorator to be applied to, or <code>null</code> if record is to be ignored. There is a possibility that a straight
		 *         PropertyDecorator can be returned instead (in the case that it was explictly set by overrides as a property but beaninfo thinks it
		 *         is an index. This can be handled by it will only set the PropertyRecord part. It normally should be an IndexedPropertyDecorator
		 *         returned.
		 * 
		 * @since 1.1.0
		 */
		public PropertyDecorator process(IndexedPropertyRecord record);

		/**
		 * Process the MethodRecord. The callee can decide what needs to be done with this record. It would return the MethodDecorator that needs to
		 * have the record applied to. If it returns <code>null</code> then the record will be ignored.
		 * 
		 * <p>
		 * Note: This will be called on a separate thread from that which initiated the request. Therefor be careful with any locks because you may
		 * have them on a separate thread.
		 * 
		 * @param record
		 * @return MethodDecorator to be applied to, or <code>null</code> if record is to be ignored.
		 * 
		 * @since 1.1.0
		 */

		public MethodDecorator process(MethodRecord record);

		/**
		 * Process the EventRecord. The callee can decide what needs to be done with this record. It would return the EventSetDecorator that needs to
		 * have the record applied to. If it returns <code>null</code> then the record will be ignored.
		 * 
		 * <p>
		 * Note: This will be called on a separate thread from that which initiated the request. Therefor be careful with any locks because you may
		 * have them on a separate thread.
		 * 
		 * @param record
		 * @return EventSetDecorator to be applied to, or <code>null</code> if record is to be ignored.
		 * 
		 * @since 1.1.0
		 */

		public EventSetDecorator process(EventSetRecord record);
	}

	private static class ProxyIntrospectCallBack implements ICallback {

		private IntrospectCallBack introspectCallback;

		public ProxyIntrospectCallBack(IntrospectCallBack introspectCallback) {
			this.introspectCallback = introspectCallback;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBack(int, org.eclipse.jem.internal.proxy.core.IBeanProxy)
		 */
		public Object calledBack(int msgID, IBeanProxy parm) {
			return null; // Not used.
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBack(int, java.lang.Object)
		 */
		public Object calledBack(int msgID, Object parm) {
			return null; // Not used.
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBack(int, java.lang.Object[])
		 */
		public Object calledBack(int msgID, Object[] parms) {
			return null; // Not used.
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jem.internal.proxy.core.ICallback#calledBackStream(int, java.io.InputStream)
		 */
		public void calledBackStream(int msgID, InputStream is) {
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(is);
				while (true) {
					int cmdId = ois.readInt();
					switch (cmdId) {
						case IBeanInfoIntrospectionConstants.BEAN_DECORATOR_SENT:
							try {
								BeanRecord br = (BeanRecord) ois.readObject();
								BeanDecorator bd = introspectCallback.process(br);
								if (bd != null) {
									clear(bd);
									applyRecord(bd, br);
								}
							} catch (IOException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassCastException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassNotFoundException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							}
							break;
						case IBeanInfoIntrospectionConstants.PROPERTY_DECORATORS_SENT:
							try {
								int propCount = ois.readInt();
								for (int i = 0; i < propCount; i++) {
									PropertyRecord pr = (PropertyRecord) ois.readObject();
									if (pr.getClass() == IndexedPropertyRecord.class) {
										IndexedPropertyRecord ipr = (IndexedPropertyRecord) pr;
										PropertyDecorator ip = introspectCallback.process(ipr);
										if (ip != null) {
											// It actually could be either a property decorator or an indexed property decorator. This could happen
											// because the overrides file has explicitly declared a PropertyDecorator, so we can't change it to an
											// Indexed.
											// So in that case we can only fill the property part.
											if (ip.eClass().getClassifierID() == BeaninfoPackage.INDEXED_PROPERTY_DECORATOR)
												applyRecord((IndexedPropertyDecorator) ip, ipr);
											else
												applyRecord(ip, pr); // It was forced to be a property and not indexed.
										}
									} else {
										PropertyDecorator p = introspectCallback.process(pr);
										if (p != null) {
											// It actually could be either a property decorator or an indexed property decorator. This could happen
											// because the overrides file has explicitly declared an IndexedPropertyDecorator, so we can't change it
											// to an
											// Property.
											// So in that case we can only fill the property part.
											applyRecord(p, pr);
										}
									}
								}
							} catch (IOException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassNotFoundException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassCastException e) {
								// In case we got bad data sent in.
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} finally {
							}
							break;

						case IBeanInfoIntrospectionConstants.METHOD_DECORATORS_SENT:
							try {
								int opCount = ois.readInt();
								for (int i = 0; i < opCount; i++) {
									MethodRecord mr = (MethodRecord) ois.readObject();
									MethodDecorator m = introspectCallback.process(mr);
									if (m != null)
										applyRecord(m, mr);
								}
							} catch (IOException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassNotFoundException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassCastException e) {
								// In case we got bad data sent in.
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							}
							break;

						case IBeanInfoIntrospectionConstants.EVENT_DECORATORS_SENT:
							try {
								int opCount = ois.readInt();
								for (int i = 0; i < opCount; i++) {
									EventSetRecord evr = (EventSetRecord) ois.readObject();
									EventSetDecorator e = introspectCallback.process(evr);
									if (e != null)
										applyRecord(e, evr);
								}
							} catch (IOException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassNotFoundException e) {
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							} catch (ClassCastException e) {
								// In case we got bad data sent in.
								BeaninfoPlugin.getPlugin().getLogger().log(e);
							}
							break;

						case IBeanInfoIntrospectionConstants.DONE:
							return;	// Good. This is a good stop.
							
						default:
							return;	// This is invalid. Should of gotton something.
					}
				}
			} catch (IOException e) {
				BeaninfoPlugin.getPlugin().getLogger().log(e);
			}
		}
	}

	/**
	 * Apply the feature record to the feature decorator. This is protected because this is an abstract and should never be called by itself.
	 * <p>
	 * 
	 * @param decor
	 * @param record
	 * 
	 * @since 1.1.0
	 */
	protected static void applyRecord(FeatureDecorator decor, FeatureRecord record) {
		// Subclasses will clear their decor, which will automatically clear the FeatureDecor part for us.
		long implicitSettings = decor.getImplicitlySetBits();
		if (record.displayName != null && !decor.isSetDisplayName()) {
			decor.setDisplayName(record.displayName);
			implicitSettings |= FeatureDecoratorImpl.FEATURE_DISPLAYNAME_IMPLICIT;
		}
		if (record.shortDescription != null && !decor.isSetShortDescription()) {
			decor.setShortDescription(record.shortDescription);
			implicitSettings |= FeatureDecoratorImpl.FEATURE_SHORTDESC_IMPLICIT;
		}
		if (record.category != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getFeatureDecorator_Category())) {
			decor.setCategory(record.category);
			implicitSettings |= FeatureDecoratorImpl.FEATURE_CATEGORY_IMPLICIT;
		}
		if (!decor.isSetExpert()) {
			if (decor.isExpert() != record.expert)
				decor.setExpert(record.expert); // Don't want to explicitly set it if it is equal to default (this will put less out to the cache file
												// and so will parse and apply faster).
			implicitSettings |= FeatureDecoratorImpl.FEATURE_EXPERT_IMPLICIT;
		}
		if (!decor.isSetHidden()) {
			if (decor.isHidden() != record.hidden)
				decor.setHidden(record.hidden);
			implicitSettings |= FeatureDecoratorImpl.FEATURE_HIDDEN_IMPLICIT;
		}
		if (!decor.isSetPreferred()) {
			if (decor.isPreferred() != record.preferred)
				decor.setPreferred(record.preferred);
			implicitSettings |= FeatureDecoratorImpl.FEATURE_PREFERRED_IMPLICIT;
		}
		if (record.attributeNames != null && !decor.isAttributesExplicitEmpty()) {
			// This is a list, so we need to read an fill in.
			EMap attrs = decor.getAttributes();
			for (int i = 0; i < record.attributeNames.length; i++) {
				FeatureAttributeMapEntryImpl entry = (FeatureAttributeMapEntryImpl) ((BeaninfoFactoryImpl) BeaninfoFactory.eINSTANCE)
						.createFeatureAttributeMapEntry();
				entry.setTypedKey(record.attributeNames[i]);
				FeatureAttributeValue fv = record.attributeValues[i];
				fv.setImplicitValue(true);
				entry.setTypedValue(fv);
				attrs.add(entry);
			}
			implicitSettings |= FeatureDecoratorImpl.FEATURE_ATTRIBUTES_IMPLICIT;
		}

		decor.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Apply the bean record to the bean decorator.
	 * 
	 * @param decor
	 * @param record
	 * 
	 * @since 1.1.0
	 */
	public static void applyRecord(BeanDecorator decor, BeanRecord record) {
		applyRecord((FeatureDecorator) decor, record);

		long implicitSettings = decor.getImplicitlySetBits();
		if (record.customizerClassName != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getBeanDecorator_CustomizerClass())) {
			decor.setCustomizerClass(createJavaClassProxy(record.customizerClassName));
			implicitSettings |= BeanDecoratorImpl.BEAN_CUSTOMIZER_IMPLICIT;
		}
		if (!decor.isSetMergeSuperProperties()) {
			if (decor.isMergeSuperProperties() != record.mergeInheritedProperties)
				decor.setMergeSuperProperties(record.mergeInheritedProperties);
			implicitSettings |= BeanDecoratorImpl.BEAN_MERGE_INHERITED_PROPERTIES_IMPLICIT;
		}
		if (!decor.isSetMergeSuperMethods()) {
			if (decor.isMergeSuperMethods() != record.mergeInheritedOperations)
				decor.setMergeSuperMethods(record.mergeInheritedOperations);
			implicitSettings |= BeanDecoratorImpl.BEAN_MERGE_INHERITED_OPERATIONS_IMPLICIT;
		}
		if (!decor.isSetMergeSuperEvents()) {
			if (decor.isMergeSuperEvents() != record.mergeInheritedEvents)
				decor.setMergeSuperEvents(record.mergeInheritedEvents);
			implicitSettings |= BeanDecoratorImpl.BEAN_MERGE_INHERITED_EVENTS_IMPLICIT;
		}
		if (record.notInheritedPropertyNames != null) {
			// This is always applied. This isn't a client override so we can just slam it.
			decor.getNotInheritedPropertyNames().addAll(Arrays.asList(record.notInheritedPropertyNames));
		}
		if (record.notInheritedOperationNames != null) {
			// This is always applied. This isn't a client override so we can just slam it.
			decor.getNotInheritedMethodNames().addAll(Arrays.asList(record.notInheritedOperationNames));
		}
		if (record.notInheritedEventNames != null) {
			// This is always applied. This isn't a client override so we can just slam it.
			decor.getNotInheritedEventNames().addAll(Arrays.asList(record.notInheritedEventNames));
		}

		decor.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Apply the PropertyRecord to the PropertyDecorator.
	 * 
	 * @param decor
	 * @param record
	 * 
	 * @since 1.1.0
	 */
	public static void applyRecord(PropertyDecorator decor, PropertyRecord record) {
		applyRecord((FeatureDecorator) decor, record);

		applyOnly(decor, record);
	}

	/*
	 * Apply only to property decorator part. Allows IndexedProperty to apply just the Property part and not do duplicate work
	 */
	private static void applyOnly(PropertyDecorator decor, PropertyRecord record) {
		long implicitSettings = decor.getImplicitlySetBits();
		if (record.propertyEditorClassName != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getPropertyDecorator_PropertyEditorClass())) {
			decor.setPropertyEditorClass(createJavaClassProxy(record.propertyEditorClassName));
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_EDITOR_CLASS_IMPLICIT;
		}
		if (record.readMethod != null && !decor.isSetReadMethod()) {
			decor.setReadMethod(createJavaMethodProxy(record.readMethod));
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_READMETHOD_IMPLICIT;
		}
		if (record.writeMethod != null && !decor.isSetWriteMethod()) {
			decor.setWriteMethod(createJavaMethodProxy(record.writeMethod));
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_WRITEMETHOD_IMPLICIT;
		}
		if (record.field != null && !decor.isSetField()) {
			decor.setField(createJavaFieldProxy(record.field));
			if (decor.isFieldReadOnly() != record.field.readOnly)
				decor.setFieldReadOnly(record.field.readOnly);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_FIELD_IMPLICIT;
		}		
		if (!decor.isSetBound()) {
			if (decor.isBound() != record.bound)
				decor.setBound(record.bound);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_BOUND_IMPLICIT;
		}
		if (!decor.isSetConstrained()) {
			if (decor.isConstrained() != record.constrained)
				decor.setConstrained(record.constrained);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_CONSTRAINED_IMPLICIT;
		}
		if (record.designTime != null && !decor.isSetDesignTime()) {
			// Design time is slightly different than the other booleans because
			// explicitly set to true/false is important versus not explicitly set at all (which is false).
			decor.setDesignTime(record.designTime.booleanValue());
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_DESIGNTIME_IMPLICIT;
		}

		decor.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.

	}

	public static void applyRecord(IndexedPropertyDecorator decor, IndexedPropertyRecord record) {
		applyRecord((FeatureDecorator) decor, record);
		applyOnly(decor, record);

		long implicitSettings = decor.getImplicitlySetBits();
		if (record.indexedReadMethod != null && !decor.isSetIndexedReadMethod()) {
			decor.setIndexedReadMethod(createJavaMethodProxy(record.indexedReadMethod));
			implicitSettings |= IndexedPropertyDecoratorImpl.INDEXED_READMETHOD_IMPLICIT;
		}
		if (record.indexedWriteMethod != null && !decor.isSetIndexedWriteMethod()) {
			decor.setIndexedWriteMethod(createJavaMethodProxy(record.indexedWriteMethod));
			implicitSettings |= IndexedPropertyDecoratorImpl.INDEXED_WRITEMETHOD_IMPLICIT;
		}
		decor.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Apply the method record to the method decorator.
	 * 
	 * @param decor
	 * @param record
	 * 
	 * @since 1.1.0
	 */
	public static void applyRecord(MethodDecorator decor, MethodRecord record) {
		applyRecord((FeatureDecorator) decor, record);

		long implicitSettings = decor.getImplicitlySetBits();
		if (record.parameters != null && !decor.isParmsExplicitEmpty()
				&& !decor.eIsSet(BeaninfoPackage.eINSTANCE.getMethodDecorator_ParameterDescriptors())) {
			// This is a list, so we need to read an fill in.
			List parms = decor.getSerParmDesc(); // So as not to have it implicitly fill it in, which it would if we called getParameterDescriptors.
			for (int i = 0; i < record.parameters.length; i++) {
				ParameterDecorator parm = BeaninfoFactory.eINSTANCE.createParameterDecorator();
				applyRecord(parm, record.parameters[i]);
				parms.add(parm);
			}
			implicitSettings |= MethodDecoratorImpl.METHOD_PARAMETERS_IMPLICIT;
			implicitSettings &= ~MethodDecoratorImpl.METHOD_PARAMETERS_DEFAULT; // Should of already been cleared, but be safe.
		}

		decor.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	public static void applyRecord(ParameterDecorator decor, ParameterRecord record) {
		applyRecord((FeatureDecorator) decor, record);

		long implicitSettings = decor.getImplicitlySetBits();
		if (record.name != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getParameterDecorator_Name())) {
			decor.setName(record.name);
			implicitSettings |= ParameterDecoratorImpl.PARAMETER_NAME_IMPLICIT;
		}
		decor.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Apply the event set record to the event set decorator.
	 * 
	 * @param decor
	 * @param record
	 * 
	 * @since 1.1.0
	 */
	public static void applyRecord(EventSetDecorator decor, EventSetRecord record) {
		applyRecord((FeatureDecorator) decor, record);

		long implicitSettings = decor.getImplicitlySetBits();
		if (record.addListenerMethod != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_AddListenerMethod())) {
			decor.setAddListenerMethod(createJavaMethodProxy(record.addListenerMethod));
			implicitSettings |= EventSetDecoratorImpl.EVENT_ADDLISTENERMETHOD_IMPLICIT;
		}
		if (record.eventAdapterClassName != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_EventAdapterClass())) {
			decor.setEventAdapterClass(createJavaClassProxy(record.eventAdapterClassName));
			implicitSettings |= EventSetDecoratorImpl.EVENT_ADAPTERCLASS_IMPLICIT;
		}
		if (record.listenerMethodDescriptors != null && !decor.isListenerMethodsExplicitEmpty()
				&& !decor.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_ListenerMethods())) {
			List methods = decor.getSerListMthd(); // So as not to have it implicitly fill it in, which it would if we called getListenerMethods.
			for (int i = 0; i < record.listenerMethodDescriptors.length; i++) {
				BeaninfoFactory bfact = BeaninfoFactory.eINSTANCE;
				MethodRecord mr = record.listenerMethodDescriptors[i];
				Method method = createJavaMethodProxy(mr.methodForDescriptor);
				// We need a method proxy, and a method decorator.
				MethodProxy mproxy = bfact.createMethodProxy();
				mproxy.setMethod(method);
				mproxy.setName(mr.name);
				MethodDecorator md = bfact.createMethodDecorator();
				applyRecord(md, mr);
				mproxy.getEAnnotations().add(md);
				methods.add(mproxy);
			}
			implicitSettings |= EventSetDecoratorImpl.EVENT_LISTENERMETHODS_IMPLICIT;
			implicitSettings &= ~EventSetDecoratorImpl.EVENT_LISTENERMETHODS_DEFAULT; // Should of already been cleared, but be safe.
		}
		if (record.listenerTypeName != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_ListenerType())) {
			decor.setListenerType(createJavaClassProxy(record.listenerTypeName));
			implicitSettings |= EventSetDecoratorImpl.EVENT_LISTENERTYPE_IMPLICIT;
		}
		if (record.removeListenerMethod != null && !decor.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_RemoveListenerMethod())) {
			decor.setRemoveListenerMethod(createJavaMethodProxy(record.removeListenerMethod));
			implicitSettings |= EventSetDecoratorImpl.EVENT_REMOVELISTENERMETHOD_IMPLICIT;
		}
		if (!decor.isSetInDefaultEventSet()) {
			if (record.inDefaultEventSet != decor.isInDefaultEventSet())
				decor.setInDefaultEventSet(record.inDefaultEventSet);
			implicitSettings |= EventSetDecoratorImpl.EVENT_DEFAULTEVENTSET_IMPLICIT;
		}
		if (!decor.isSetUnicast()) {
			if (record.unicast != decor.isUnicast())
				decor.setUnicast(record.unicast);
			implicitSettings |= EventSetDecoratorImpl.EVENT_UNICAST_IMPLICIT;
		}

		decor.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Create a java class proxy for the given name. By being a proxy we don't need to actually have the resource set. Nor do we need to fluff one up
	 * until someone actually asks for it.
	 * <p>
	 * The jniName must refer to a JavaClass or errors could occur later on.
	 * 
	 * @param jniName
	 *            classname in JNI format.
	 * @return JavaClass proxy or <code>null</code> if not a java class (it may be a type).
	 * 
	 * @since 1.1.0
	 */
	public static JavaClass createJavaClassProxy(String jniName) {
		JavaHelpers jh = createJavaTypeProxy(jniName);
		return jh instanceof JavaClass ? (JavaClass) jh : null;
	}

	/**
	 * Create a JavaHelpers proxy for the given name. By being a proxy we don't need to actually have the resource set. Nor do we need to fluff one up
	 * until someone actually asks for it.
	 * 
	 * @param jniName
	 *            typename in JNI format.
	 * @return JavaHelper proxy.
	 * 
	 * @since 1.1.0
	 */
	public static JavaHelpers createJavaTypeProxy(String jniName) {
		String formalName = MapJNITypes.getFormalTypeName(jniName);

		URI uri = Utilities.getJavaClassURI(formalName);
		JavaHelpers jh = null;
		if (MapJNITypes.isFormalTypePrimitive(formalName))
			jh = JavaRefFactory.eINSTANCE.createJavaDataType();
		else
			jh = JavaRefFactory.eINSTANCE.createJavaClass();
		((InternalEObject) jh).eSetProxyURI(uri);
		return jh;
	}

	public static Method createJavaMethodProxy(ReflectMethodRecord method) {
		String[] parmTypes = method.parameterTypeNames != null ? new String[method.parameterTypeNames.length] : null;
		if (parmTypes != null)
			for (int i = 0; i < method.parameterTypeNames.length; i++) {
				parmTypes[i] = MapJNITypes.getFormalTypeName(method.parameterTypeNames[i]);
			}
		URI uri = Utilities.getMethodURI(MapJNITypes.getFormalTypeName(method.className), method.methodName, parmTypes);
		Method methodEMF = JavaRefFactory.eINSTANCE.createMethod();
		((InternalEObject) methodEMF).eSetProxyURI(uri);
		return methodEMF;
	}
	
	public static Field createJavaFieldProxy(ReflectFieldRecord field) {
		URI uri = Utilities.getFieldURI(MapJNITypes.getFormalTypeName(field.className), field.fieldName);
		Field fieldEMF = JavaRefFactory.eINSTANCE.createField();
		((InternalEObject) fieldEMF).eSetProxyURI(uri);
		return fieldEMF;
	}	

	/**
	 * Set the properties on the PropertyDecorator. These come from reflection. Since this is a private interface between BeaninfoClassAdapter and
	 * this class, not all possible settings need to be mentioned. Only the ones that can be set by reflection. It is assumed that clear has already
	 * been done so that there are no old implicit settings. It will check if properties are set already before setting so that don't wipe out
	 * explicit settings.
	 * 
	 * @param prop
	 * @param bound
	 * @param constrained
	 * @param getter
	 * @param setter
	 * 
	 * @since 1.1.0
	 */
	public static void setProperties(PropertyDecorator prop, boolean bound, boolean constrained, Method getter, Method setter) {
		long implicitSettings = prop.getImplicitlySetBits();
		if (getter != null && !prop.isSetReadMethod()) {
			prop.setReadMethod(getter);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_READMETHOD_IMPLICIT;
		}
		if (setter != null && !prop.isSetWriteMethod()) {
			prop.setWriteMethod(setter);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_WRITEMETHOD_IMPLICIT;
		}
		if (!prop.isSetBound()) {
			if (prop.isBound() != bound)
				prop.setBound(bound);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_BOUND_IMPLICIT;
		}
		if (!prop.isSetConstrained()) {
			if (prop.isConstrained() != constrained)
				prop.setConstrained(constrained);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_CONSTRAINED_IMPLICIT;
		}
		prop.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Set the properties on the IndexedPropertyDecorator. These come from reflection. It is only the indexed portion. The base property portion
	 * should have already been set. Since this is a private interface between BeaninfoClassAdapter and this class, not all possible settings need to
	 * be mentioned. Only the ones that can be set by reflection. It is assumed that clear has already been done so that there are no old implicit
	 * settings. It will check if properties are set already before setting so that don't wipe out explicit settings.
	 * 
	 * @param prop
	 * @param indexedGetter
	 * @param indexedSetter
	 * 
	 * @since 1.1.0
	 */
	public static void setProperties(IndexedPropertyDecorator prop, Method indexedGetter, Method indexedSetter) {
		long implicitSettings = prop.getImplicitlySetBits();
		if (indexedGetter != null && !prop.isSetIndexedReadMethod()) {
			prop.setIndexedReadMethod(indexedGetter);
			implicitSettings |= IndexedPropertyDecoratorImpl.INDEXED_READMETHOD_IMPLICIT;
		}
		if (indexedSetter != null && !prop.isSetIndexedWriteMethod()) {
			prop.setIndexedWriteMethod(indexedSetter);
			implicitSettings |= IndexedPropertyDecoratorImpl.INDEXED_WRITEMETHOD_IMPLICIT;
		}
		prop.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Set the properties on the EventSetDecorator. These come from reflection. Since this is a private interface between BeaninfoClassAdapter and
	 * this class, not all possible settings need to be mentioned. Only the ones that can be set by reflection. It is assumed that clear has already
	 * been done so that there are no old implicit settings. It will check if properties are set already before setting so that don't wipe out
	 * explicit settings.
	 * 
	 * @param event
	 * @param bound
	 * @param constrained
	 * @param getter
	 * @param setter
	 * 
	 * @since 1.1.0
	 */
	public static void setProperties(EventSetDecorator event, Method addListenerMethod, Method removeListenerMethod, boolean unicast,
			JavaClass listenerType) {
		long implicitSettings = event.getImplicitlySetBits();
		if (addListenerMethod != null && !event.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_AddListenerMethod())) {
			event.setAddListenerMethod(addListenerMethod);
			implicitSettings |= EventSetDecoratorImpl.EVENT_ADDLISTENERMETHOD_IMPLICIT;
		}
		if (removeListenerMethod != null && !event.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_RemoveListenerMethod())) {
			event.setRemoveListenerMethod(removeListenerMethod);
			implicitSettings |= EventSetDecoratorImpl.EVENT_REMOVELISTENERMETHOD_IMPLICIT;
		}
		if (!event.isSetUnicast()) {
			if (event.isUnicast() != unicast)
				event.setUnicast(unicast);
			implicitSettings |= PropertyDecoratorImpl.PROPERTY_BOUND_IMPLICIT;
		}
		if (listenerType != null && !event.eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_ListenerType())) {
			event.setListenerType(listenerType);
			implicitSettings |= EventSetDecoratorImpl.EVENT_LISTENERTYPE_IMPLICIT;
		}

		event.setImplicitlySetBits(implicitSettings); // Now save was implicitly set.
	}

	/**
	 * Build the appropriate change record for the bean decorator. Either it is an add of a new bean decorator or it is the setting of the implicit
	 * settings on an non-implicit bean decorator.
	 * @param cd
	 * @param bd
	 * 
	 * @since 1.1.0
	 */
	public static void buildChange(ChangeDescription cd, BeanDecorator bd) {
		// Only do anything if merge introspection. If no merge introspection, then there is no change needed.
		if (bd.isMergeIntrospection()) {
			if (bd.getImplicitDecoratorFlag() != ImplicitItem.NOT_IMPLICIT_LITERAL) {
				// It is implicit, so do an add to end, new value.
				doAddToEnd(cd, getFeatureChangeList(cd, bd.getEModelElement()), EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), bd, true);
			} else {
				// Just do sets on implicit changed ones
				buildNonImplicitChange(cd, getFeatureChangeList(cd, bd), bd);
			}
		}

	}

	/**
	 * Build the appropriate change record for the property decorator. Either it is an add of a new property decorator or it is the setting of the implicit
	 * settings on an non-implicit property decorator. The same is true of the feature that it decorates. It may be new or it may be an existing one.
	 * @param cd
	 * @param pd
	 * 
	 * @since 1.1.0
	 */
	public static void buildChange(ChangeDescription cd, PropertyDecorator pd) {
		// Only do changes if merge introspection. If not merging, then there are no changes.
		if (pd.isMergeIntrospection()) {
			boolean indexed = pd.eClass().getClassifierID() == BeaninfoPackage.INDEXED_PROPERTY_DECORATOR;
			EStructuralFeature feature = (EStructuralFeature) pd.getEModelElement();
			switch (pd.getImplicitDecoratorFlag().getValue()) {
				case ImplicitItem.IMPLICIT_DECORATOR:
					// The decorator is implicit, so clone it, and apply to feature, and then do the standard property applies to the feature.
					List fcs = getFeatureChangeList(cd, feature);
					doAddToEnd(cd, fcs, pd.eContainingFeature(), pd, true);
					buildNonImplicitChange(cd, fcs, feature, indexed);
					break;
				case ImplicitItem.IMPLICIT_DECORATOR_AND_FEATURE:
					// The decorator AND feature are implicit. Just clone them and add to the class.
					doAddToEnd(cd, getFeatureChangeList(cd, feature.eContainer()), feature.eContainingFeature(), feature, true);
					break;
				case ImplicitItem.NOT_IMPLICIT:
					// Neither the feature nor the decorator are implicit. So need to do applies against them.
					buildNonImplicitChange(cd, getFeatureChangeList(cd, pd), pd, indexed);
					buildNonImplicitChange(cd, getFeatureChangeList(cd, feature), feature, indexed);
					break;
			}
		}
	}
	
	/**
	 * Build the appropriate change record for the event set decorator. Either it is an add of a new event set decorator or it is the setting of the implicit
	 * settings on an non-implicit event set decorator. The same is true of the feature that it decorates. It may be new or it may be an existing one.
	 * @param cd
	 * @param ed
	 * 
	 * @since 1.1.0
	 */
	public static void buildChange(ChangeDescription cd, EventSetDecorator ed) {
		// Only build changes if merge introspection. If not merge then there are no changes.
		if (ed.isMergeIntrospection()) {
			JavaEvent event = (JavaEvent) ed.getEModelElement();
			switch (ed.getImplicitDecoratorFlag().getValue()) {
				case ImplicitItem.IMPLICIT_DECORATOR:
					// The decorator is implicit, so clone it, and apply to feature, and then do the standard property applies to the feature.
					List fcs = getFeatureChangeList(cd, event);
					doAddToEnd(cd, fcs, ed.eContainingFeature(), ed, true);
					buildNonImplicitChange(cd, fcs, event);
					break;
				case ImplicitItem.IMPLICIT_DECORATOR_AND_FEATURE:
					// The decorator AND feature are implicit. Just clone them and add to the class.
					doAddToEnd(cd, getFeatureChangeList(cd, event.eContainer()), event.eContainingFeature(), event, true);
					break;
				case ImplicitItem.NOT_IMPLICIT:
					// Neither the feature nor the decorator are implicit. So need to do applies against them.
					buildNonImplicitChange(cd, getFeatureChangeList(cd, ed), ed);
					buildNonImplicitChange(cd, getFeatureChangeList(cd, event), event);
					break;
			}
		}
	}

	/**
	 * Build the appropriate change record for the method decorator. Either it is an add of a new method decorator or it is the setting of the implicit
	 * settings on an non-implicit method decorator. The same is true of the operation that it decorates. It may be new or it may be an existing one.
	 * @param cd
	 * @param md
	 * 
	 * @since 1.1.0
	 */
	public static void buildChange(ChangeDescription cd, MethodDecorator md) {
		// Only do any builds if merge introspection. If not merge introspection then nothing should be changed.
		if (md.isMergeIntrospection()) {
			EOperation oper = (EOperation) md.getEModelElement();
			switch (md.getImplicitDecoratorFlag().getValue()) {
				case ImplicitItem.IMPLICIT_DECORATOR:
					// The decorator is implicit, so clone it, and apply to feature, and then do the standard property applies to the feature.
					List fcs = getFeatureChangeList(cd, oper);
					doAddToEnd(cd, fcs, md.eContainingFeature(), md, true);
					buildNonImplicitChange(cd, fcs, oper);
					break;
				case ImplicitItem.IMPLICIT_DECORATOR_AND_FEATURE:
					// The decorator AND feature are implicit. Just clone them and add to the class.
					doAddToEnd(cd, getFeatureChangeList(cd, oper.eContainer()), oper.eContainingFeature(), oper, true);
					break;
				case ImplicitItem.NOT_IMPLICIT:
					// Neither the feature nor the decorator are implicit. So need to do applies against them.
					buildNonImplicitChange(cd, getFeatureChangeList(cd, md), md);
					buildNonImplicitChange(cd, getFeatureChangeList(cd, oper), oper);
					break;
			}
		}
	}
	
	private final static Integer ZERO = new Integer(0);
	private final static Integer ONE = new Integer(1);
	private final static Integer MINUS_ONE = new Integer(-1);
	
	/**
	 * Build the non-implicit changes into the feature. This creates changes for the implicit settings
	 * that always occur for a property decorator.
	 *  
	 * @param cd
	 * @param fcs FeatureChanges list for the feature.
	 * @param feature
	 * @param indexed <code>true</code> if this is for an indexed feature.
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, EStructuralFeature feature, boolean indexed) {
		doSet(cd, fcs, EcorePackage.eINSTANCE.getENamedElement_Name(), feature.getName(), false);
		doSet(cd, fcs, EcorePackage.eINSTANCE.getEStructuralFeature_Transient(), Boolean.FALSE, false);
		doSet(cd, fcs, EcorePackage.eINSTANCE.getEStructuralFeature_Volatile(), Boolean.FALSE, false);
		doSet(cd, fcs, EcorePackage.eINSTANCE.getEStructuralFeature_Changeable(), Boolean.valueOf(feature.isChangeable()), false);
		doSet(cd, fcs, EcorePackage.eINSTANCE.getETypedElement_EType(), feature.getEType(), false);
		if (!indexed) {
			doSet(cd, fcs, EcorePackage.eINSTANCE.getETypedElement_LowerBound(), ZERO, false);
			doSet(cd, fcs, EcorePackage.eINSTANCE.getETypedElement_UpperBound(), ONE, false);
		} else {
			doSet(cd, fcs, EcorePackage.eINSTANCE.getETypedElement_LowerBound(), ZERO, false);
			doSet(cd, fcs, EcorePackage.eINSTANCE.getETypedElement_UpperBound(), MINUS_ONE, false);
			doSet(cd, fcs, EcorePackage.eINSTANCE.getETypedElement_Unique(), Boolean.TRUE, false);
		}
	}

	/**
	 * Build the non-implicit changes into the event. This creates changes for the implicit settings
	 * that always occur for an event set decorator.
	 *  
	 * @param cd
	 * @param fcs FeatureChanges list for the feature.
	 * @param event
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, JavaEvent event) {
		doSet(cd, fcs, EcorePackage.eINSTANCE.getENamedElement_Name(), event.getName(), false);
	}

	/**
	 * Build the non-implicit changes into the operation. This creates changes for the implicit settings
	 * that always occur for an method decorator.
	 *  
	 * @param cd
	 * @param fcs FeatureChanges list for the feature.
	 * @param oper
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, EOperation oper) {
		doSet(cd, fcs, EcorePackage.eINSTANCE.getENamedElement_Name(), oper.getName(), false);
		try {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getMethodProxy_Method(), ((MethodProxy) oper).getMethod(), false);	// This is a method that is not in this resource, so no clone.
		} catch (ClassCastException e) {
			// It will be a MethodProxy 99.9% of the time, so save by not doing instanceof.
		}
	}

	/**
	 * Build up the changes for a non-implicit feature decorator. This means create changes for implicit set features.
	 * 
	 * @param cd
	 * @param fcs
	 *            the FeatureChanges list for the given decorator.
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, FeatureDecorator decor) {
		long implicitSettings = decor.getImplicitlySetBits();
		if (implicitSettings != 0)
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_ImplicitlySetBits(), new Long(implicitSettings), false);
		
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_DISPLAYNAME_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_DisplayName(), decor.getDisplayName(), false);
		}
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_SHORTDESC_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_ShortDescription(), decor.getShortDescription(), false);
		}
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_CATEGORY_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_Category(), decor.getCategory(), false);
		}
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_EXPERT_IMPLICIT) != 0 && decor.isSetExpert()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_Expert(), Boolean.valueOf(decor.isExpert()), false);
		}
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_HIDDEN_IMPLICIT) != 0 && decor.isSetHidden()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_Hidden(), Boolean.valueOf(decor.isHidden()), false);
		}
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_PREFERRED_IMPLICIT) != 0 && decor.isSetPreferred()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_Preferred(), Boolean.valueOf(decor.isPreferred()), false);
		}
		if ((implicitSettings & FeatureDecoratorImpl.FEATURE_ATTRIBUTES_IMPLICIT) != 0) {
			for (Iterator itr = decor.getAttributes().listIterator(); itr.hasNext();) {
				FeatureAttributeMapEntryImpl entry = (FeatureAttributeMapEntryImpl)itr.next();
				if (entry.getTypedValue().isImplicitValue()) {
					doAddToEnd(cd, fcs, BeaninfoPackage.eINSTANCE.getFeatureDecorator_Attributes(), entry, true);
				}
			}
		}
	}

	/**
	 * Build up the changes for a non-implicit bean decorator. This means create changes for implicit set features.
	 * 
	 * @param cd
	 * @param fcs
	 *            the FeatureChanges list for the given decorator.
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, BeanDecorator decor) {
		buildNonImplicitChange(cd, fcs, (FeatureDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		if ((implicitSettings & BeanDecoratorImpl.BEAN_CUSTOMIZER_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getBeanDecorator_CustomizerClass(), decor.getCustomizerClass(), false); // Customizer class is
																															 // not in this resource,
																															 // so we don't clone it.
		}
		if ((implicitSettings & BeanDecoratorImpl.BEAN_MERGE_INHERITED_PROPERTIES_IMPLICIT) != 0 && decor.isSetMergeSuperProperties()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getBeanDecorator_MergeSuperProperties(), Boolean.valueOf(decor.isMergeSuperProperties()), false);
		}
		if ((implicitSettings & BeanDecoratorImpl.BEAN_MERGE_INHERITED_OPERATIONS_IMPLICIT) != 0 && decor.isSetMergeSuperMethods()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getBeanDecorator_MergeSuperMethods(), Boolean.valueOf(decor.isMergeSuperMethods()), false);
		}
		if ((implicitSettings & BeanDecoratorImpl.BEAN_MERGE_INHERITED_EVENTS_IMPLICIT) != 0 && decor.isSetMergeSuperEvents()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getBeanDecorator_MergeSuperEvents(), Boolean.valueOf(decor.isMergeSuperEvents()), false);
		}
		if (!decor.getNotInheritedPropertyNames().isEmpty()) {
			doAddAllToEnd(cd, fcs, BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedPropertyNames(), decor.getNotInheritedPropertyNames(), false);
		}
		if (!decor.getNotInheritedMethodNames().isEmpty()) {
			doAddAllToEnd(cd, fcs, BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedMethodNames(), decor.getNotInheritedMethodNames(), false);
		}
		if (!decor.getNotInheritedEventNames().isEmpty()) {
			doAddAllToEnd(cd, fcs, BeaninfoPackage.eINSTANCE.getBeanDecorator_NotInheritedEventNames(), decor.getNotInheritedEventNames(), false);
		}
	}

	/**
	 * Build up the changes for a non-implicit property decorator. This means create changes for implicit set features.
	 * 
	 * @param cd
	 * @param fcs
	 *            the FeatureChanges list for the given decorator.
	 * @param decor
	 * @param indexed <code>true</code> if this is an indexed property decorator.
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, PropertyDecorator decor, boolean indexed) {
		buildNonImplicitChange(cd, fcs, decor);
		long implicitSettings = decor.getImplicitlySetBits();
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_EDITOR_CLASS_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_PropertyEditorClass(), decor.getPropertyEditorClass(), false); // Property Editor class is
																															 // not in this resource,
																															 // so we don't clone it.
		}
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_READMETHOD_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_ReadMethod(), decor.getReadMethod(), false); 
		}
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_WRITEMETHOD_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_WriteMethod(), decor.getWriteMethod(), false); 
		}
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_FIELD_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_Field(), decor.getField(), false); 
			if (decor.eIsSet(BeaninfoPackage.eINSTANCE.getPropertyDecorator_FieldReadOnly()))
				doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_FieldReadOnly(), Boolean.valueOf(decor.isFieldReadOnly()), false);
		}		
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_BOUND_IMPLICIT) != 0 && decor.isSetBound()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_Bound(), Boolean.valueOf(decor.isBound()), false);
		}
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_CONSTRAINED_IMPLICIT) != 0 && decor.isSetConstrained()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_Constrained(), Boolean.valueOf(decor.isConstrained()), false);
		}
		if ((implicitSettings & PropertyDecoratorImpl.PROPERTY_DESIGNTIME_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getPropertyDecorator_DesignTime(), Boolean.valueOf(decor.isDesignTime()), false);
		}
		
		if (indexed) {
			IndexedPropertyDecorator ipd = (IndexedPropertyDecorator) decor;
			if ((implicitSettings & IndexedPropertyDecoratorImpl.INDEXED_READMETHOD_IMPLICIT) != 0) {
				doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getIndexedPropertyDecorator_IndexedReadMethod(), ipd.getIndexedReadMethod(), false); 
			}
			if ((implicitSettings & IndexedPropertyDecoratorImpl.INDEXED_WRITEMETHOD_IMPLICIT) != 0) {
				doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getIndexedPropertyDecorator_IndexedWriteMethod(), ipd.getIndexedWriteMethod(), false); 
			}
		}
	}
	
	/**
	 * Build up the changes for a non-implicit event set decorator. This means create changes for implicit set features.
	 * 
	 * @param cd
	 * @param fcs
	 *            the FeatureChanges list for the given decorator.
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, EventSetDecorator decor) {
		buildNonImplicitChange(cd, fcs, (FeatureDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_ADDLISTENERMETHOD_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getEventSetDecorator_AddListenerMethod(), decor.getAddListenerMethod(), false); // listener method is
																															 // not in this resource,
																															 // so we don't clone it.
		}
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_ADAPTERCLASS_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getEventSetDecorator_EventAdapterClass(), decor.getEventAdapterClass(), false);
		}
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_LISTENERMETHODS_IMPLICIT) != 0) {
			doAddAllToEnd(cd, fcs, BeaninfoPackage.eINSTANCE.getEventSetDecorator_SerListMthd(), decor.getSerListMthd(), true);	// These need to be cloned because they are contained here.
		}
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_LISTENERTYPE_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getEventSetDecorator_ListenerType(), decor.getListenerType(), false);
		}
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_REMOVELISTENERMETHOD_IMPLICIT) != 0) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getEventSetDecorator_RemoveListenerMethod(), decor.getRemoveListenerMethod(), false);
		}
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_DEFAULTEVENTSET_IMPLICIT) != 0 && decor.isSetInDefaultEventSet()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getEventSetDecorator_InDefaultEventSet(), Boolean.valueOf(decor.isInDefaultEventSet()), false);
		}
		if ((implicitSettings & EventSetDecoratorImpl.EVENT_UNICAST_IMPLICIT) != 0 && decor.isSetUnicast()) {
			doSet(cd, fcs, BeaninfoPackage.eINSTANCE.getEventSetDecorator_Unicast(), Boolean.valueOf(decor.isUnicast()), false);
		}
	}

	/**
	 * Build up the changes for a non-implicit method decorator. This means create changes for implicit set features.
	 * 
	 * @param cd
	 * @param fcs
	 *            the FeatureChanges list for the given decorator.
	 * @param decor
	 * 
	 * @since 1.1.0
	 */
	protected static void buildNonImplicitChange(ChangeDescription cd, List fcs, MethodDecorator decor) {
		buildNonImplicitChange(cd, fcs, (FeatureDecorator) decor);
		long implicitSettings = decor.getImplicitlySetBits();
		if ((implicitSettings & MethodDecoratorImpl.METHOD_PARAMETERS_IMPLICIT) != 0) {
			doAddAllToEnd(cd, fcs, BeaninfoPackage.eINSTANCE.getMethodDecorator_SerParmDesc(), decor.getSerParmDesc(), true); 
		}
	}


	/**
	 * Get the feature change list for an object. Create it one if necessary.
	 * 
	 * @param cd
	 * @param object
	 * @return feature change list.
	 * 
	 * @since 1.1.0
	 */
	protected static List getFeatureChangeList(ChangeDescription cd, EObject object) {
		List fcs = (List) cd.getObjectChanges().get(object); // Get the feature changes if any.
		if (fcs == null) {
			Map.Entry entry = ChangeFactory.eINSTANCE.createEObjectToChangesMapEntry(object);
			cd.getObjectChanges().add(entry);
			fcs = (List) entry.getValue();
		}
		return fcs;
	}

	/**
	 * Return the FeatureChange record for a feature wrt/object. Create one if necessary. If it creates it, it will mark it as "set". All of our
	 * changes here are set kind of changes, not unset kind.
	 * 
	 * @param fcs
	 *            feature change list from the ChangeDescripion.getObjectChanges for the given object.
	 * @param feature
	 * @return feature change
	 * 
	 * @since 1.1.0
	 */
	protected static FeatureChange getFeatureChange(List fcs, EStructuralFeature feature) {
		if (!fcs.isEmpty()) {
			for (int i = 0; i < fcs.size(); i++) {
				FeatureChange fc = (FeatureChange) fcs.get(i);
				if (fc.getFeature() == feature)
					return fc;
			}
		}

		// Either new object changes or no feature change found. Create one.
		FeatureChange fc = ChangeFactory.eINSTANCE.createFeatureChange(feature, null, true);
		fcs.add(fc);
		return fc;
	}

	/**
	 * Create a change for add to end of the given feature (must be isMany()). If newObject is true, then this means this is not a pointer to an
	 * existing object and so it must be cloned. It is assumed that there will be no further changes to this object because those will not be known
	 * about.
	 * 
	 * @param cd
	 * @param fcs
	 *            feature change list from the ChangeDescripion.getObjectChanges for the given object.
	 * @param feature
	 *            the feature being added to.
	 * @param addedValue
	 *            the value being added.
	 * @param newValue
	 *            <code>true</code> if new object in the resource, a clone will be made. <code>false</code> if an existing object. Must be an
	 *            EObject for cloning. Best if not true for non-eobjects.
	 * @return the addedValue or the clone if it was cloned.
	 * 
	 * @since 1.1.0
	 */
	protected static Object doAddToEnd(ChangeDescription cd, List fcs, EStructuralFeature feature, Object addedValue, boolean newValue) {
		FeatureChange fc = getFeatureChange(fcs, feature);
		if (newValue) {
			try {
				addedValue = EcoreUtil.copy((EObject) addedValue);
				cd.getObjectsToAttach().add(addedValue);
			} catch (ClassCastException e) {
				// Normally should not occur, but if it does, it means we can't clone, so don't clone.
			}
		}
		List lcs = fc.getListChanges();
		// Find the one with add and -1, i.e. add to end. There should only be one.
		ListChange lc = null;
		for (int i = 0; i < lcs.size(); i++) {
			ListChange lca = (ListChange) lcs.get(i);
			if (lca.getKind() == ChangeKind.ADD_LITERAL && lca.getIndex() == -1) {
				lc = lca;
				break;
			}
		}
		if (lc == null) {
			lc = ChangeFactory.eINSTANCE.createListChange();
			lcs.add(lc);
		}

		lc.getValues().add(addedValue);
		return addedValue;
	}

	/**
	 * Create a change for add all to end of the given feature (must be isMany()). If newValue is true, then this means this is not a pointer to
	 * existing objects and so it must be cloned. It is assumed that there will be no further changes to this object because those will not be known
	 * about.
	 * 
	 * @param cd
	 * @param fcs
	 *            feature change list from the ChangeDescripion.getObjectChanges for the given object.
	 * @param feature
	 *            the feature being added to.
	 * @param addedValues
	 *            the values being added.
	 * @param newValue
	 *            <code>true</code> if new objects in the resource, clones will be made. <code>false</code> if an existing object. Must be EObject
	 *            for cloning. Best if not true for non-eobjects.
	 * @return the addedValues or the clones if it was cloned.
	 * 
	 * @since 1.1.0
	 */
	protected static Object doAddAllToEnd(ChangeDescription cd, List fcs, EStructuralFeature feature, Collection addedValues, boolean newValue) {
		FeatureChange fc = getFeatureChange(fcs, feature);
		if (newValue) {
			try {
				addedValues = EcoreUtil.copyAll(addedValues);
				cd.getObjectsToAttach().addAll(addedValues);
			} catch (ClassCastException e) {
				// Normally should not occur, but if it does, it means we can't clone, so don't clone.
			}
		}
		List lcs = fc.getListChanges();
		// Find the one with add and -1, i.e. add to end. There should only be one.
		ListChange lc = null;
		for (int i = 0; i < lcs.size(); i++) {
			ListChange lca = (ListChange) lcs.get(i);
			if (lca.getKind() == ChangeKind.ADD_LITERAL && lca.getIndex() == -1) {
				lc = lca;
				break;
			}
		}
		if (lc == null) {
			lc = ChangeFactory.eINSTANCE.createListChange();
			lcs.add(lc);
		}

		lc.getValues().addAll(addedValues);
		return addedValues;
	}

	/**
	 * Create a change for set a given feature (must be !isMany()). If newValue is true, then this means this is not a pointer to an existing object
	 * and so it must be cloned. It is assumed that there will be no further changes to this object because those will not be known about.
	 * <p>
	 * Any further sets to this feature will result in the previous setting being lost.
	 * 
	 * @param cd
	 * @param fcs
	 *            feature change list from the ChangeDescripion.getObjectChanges for the given object.
	 * @param feature
	 *            the feature being set to.
	 * @param setValue
	 *            the value being set.
	 * @param newValue
	 *            <code>true</code> if new object in the resource, a clone will be made. <code>false</code> if an existing object. Must be an
	 *            EObject for cloning. Best if not true for non-eobjects.
	 * @return the setValue or the clone if it was cloned.
	 * 
	 * @since 1.1.0
	 */
	protected static Object doSet(ChangeDescription cd, List fcs, EStructuralFeature feature, Object setValue, boolean newValue) {

		FeatureChange fc = getFeatureChange(fcs, feature);
		if (newValue) {
			try {
				setValue = EcoreUtil.copy((EObject) setValue);
				cd.getObjectsToAttach().add(setValue);
			} catch (ClassCastException e) {
				// Normally should not occur, but if it does, it means we can't clone, so don't clone.
			}
		}

		if (setValue instanceof EObject)
			fc.setReferenceValue((EObject) setValue);
		else
			fc.setDataValue(EcoreUtil.convertToString((EDataType) feature.getEType(), setValue));
		return setValue;
	}
}
