package org.eclipse.jem.internal.beaninfo.vm;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ModelingBeanInfo.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */

import java.beans.*;
import java.lang.reflect.Method;
import java.util.*;
/**
 * This class is the beaninfo class that is created when
 * beaninfo modeling introspects a bean. Its purpose is
 * to gather together and analyze the beaninfo. For example,
 * it checks with the supertype beaninfo to see if all of
 * the supertype's descriptors are included in this list.
 * If they are, then it knows that it does a straight
 * inheritance of the supertype's descriptors, and those
 * descriptors can be removed from the list. This makes
 * it easier on the model side so that there isn't a
 * great proliferation of descriptors all describing the
 * same properties. In that case they can be merged from 
 * the supertype model. If some are not found, then that
 * means this beaninfo is trying to hide some of them,
 * and in that case this is the definitive list and 
 * inheritance shouldn't be used on the model side. However, 
 * for those features which are essentially the inherited 
 * feature (i.e. the BeanInfo simply filtered out some inherited
 * but not all), they will be returnable (by name). The IDE side
 * will take these that are "inherited" and will return the
 * actual structured feature from the inherited class.
 *
 * The test for seeing if the super feature is included
 * in the beaninfo is first to see if the the feature
 * is in the beaninfo by name, if it is then it is
 * marked as included. Then a check is made on equality,
 * if they are equal, then the feature is removed from the
 * beaninfo list, but the merge flag is still left on, and 
 * removed inherited feature is added to the list of inherited
 * features. If all inherited features are found, this list
 * is cleared and flag is set which simply says merge all inherited.
 * This allows merging to occur but it also allows 
 * overrides to occur.
 * 
 * Note:
 *   Test for identity is different between JDK 1.4 and above
 * and below. 1.4 and above, we can use actual equals() because
 * the same descriptor is returned from inherited features. In
 * 1.3, clones were always returned and equals() would answer false,
 * so we need to create a special equality descriptor which turns
 * the equals() into one that can test clones for semantic equality.
 */

public abstract class ModelingBeanInfo {
	
	private static boolean PRE14;
	static {
		String version = System.getProperty("java.version",""); //$NON-NLS-1$ //$NON-NLS-2$
		PRE14 = version.startsWith("1."); //$NON-NLS-1$
		if (PRE14) {
			// Continue to check, get the revision.
			int revision = 0;
			if (version.length() > 2) {
				int revEnd = version.indexOf('.', 2);
				revision = version.length() > 2 ? Integer.parseInt(revEnd != -1 ? version.substring(2, revEnd) : version.substring(2)) : 0;
				PRE14 = revision < 4;
			}
		}		
	}
	
	static class FeatureEqualitySet extends HashSet {
		private FeatureDescriptorEquality workingKey;
		
		public FeatureEqualitySet(List features) {
			super(features.size());
			// Get first feature to fiqure out type of working key. This will not be reentrant.
			workingKey = FeatureDescriptorEquality.createEquality((FeatureDescriptor) features.get(0));
			this.addAll(features);
		}
		
		/**
		 * @see java.util.Collection#add(Object)
		 */
		public boolean add(Object o) {
			return super.add(FeatureDescriptorEquality.createEquality((FeatureDescriptor) o));
		}

		/**
		 * @see java.util.Collection#contains(Object)
		 */
		public boolean contains(Object o) {
			workingKey.setFeature((FeatureDescriptor) o);
			return super.contains(workingKey);
		}

	}
	
	
	// The following fields indicate if the super info should be merged
	// in on the model side. no merge means there were no inherited methods at all. So the
	// beaninfo presented is definitive. If merge, then get...Descriptors will return just
	// the ones for this level, and getSuper...Descriptors will return the inherited ones.
	// Though in this case, if the returned super list is null, then that means use ALL of
	// the inherited ones.
	// The super list returns simply the names, don't need to have the full descriptors in that case.
	protected boolean
		fMergeInheritedEvents = false,
		fMergeInheritedMethods = false,
		fMergeInheritedProperties = false;
		
	protected final BeanInfo fTargetBeanInfo;	// The beaninfo being modeled.

	// Local descriptors	
	protected EventSetDescriptor[] fEventSets;
	protected MethodDescriptor[] fMethods;	
	protected PropertyDescriptor[] fProperties;
	
	// Inherited descriptor names, will be null if no merge or if merge all.
	
	// Methods are special. You can have duplicates, so name is not sufficient. 
	// So for methods,
	// will use an array of Strings where:
	//   For each one the full signature
	//   will be in the list, e.g. "name:methodName(argtype,..." where argtype is the fullyqualified
	//   classname (using "." notation for inner classes), and using format "java.lang.String[]" for
	//   arrays.
	//
	// This is because even if there are no duplicates, it will take less time/space to simply create the entries
	// then it would to create a set to see if there are duplicates and then create the final array.
	protected String[] fInheritedEventSets;
	protected String[] fInheritedMethods;	
	protected String[] fInheritedProperties;
	

	/**
	 * Method used to do introspection and create the appropriate ModelingBeanInfo
	 *
	 * This will always introspect.
	 */
	public static ModelingBeanInfo introspect(Class introspectClass) throws IntrospectionException {
		return introspect(introspectClass, true);
	}
	
	/**
	 * Method used to do introspection and create the appropriate ModelingBeanInfo
	 *
	 * introspectIfNoBeanInfo: If this is true, then if no explicit beaninfo was found for this
	 *   class, then introspection will be done anyway. The Introspector will use
	 *   reflection for local methods/events/properties of this class and then add
	 *   in the results of the superclass introspection. If this parameter is false,
	 *   then if the explicit beaninfo is not found, then no introspection will be
	 *   done and null will be returned.
	 */
	public static ModelingBeanInfo introspect(Class introspectClass, boolean introspectIfNoBeanInfo) throws IntrospectionException {
		if (!introspectIfNoBeanInfo) {
			// Need to check if the beaninfo is explicitly supplied.
			// If not, then we return null.
			// The checks will be the same that Introspector will use.
			
			boolean found = false;
			// 1. Is there a BeanInfo class in the same package
			if (!classExists(introspectClass.getName()+"BeanInfo", introspectClass)) { //$NON-NLS-1$
				// 2. Is this class a BeanInfo class for itself.
				if (!(BeanInfo.class).isAssignableFrom(introspectClass)) {
					// 3. Can this class be found in the Beaninfo searchpath.
					String[] searchPath = Introspector.getBeanInfoSearchPath();
					int startClassname = introspectClass.getName().lastIndexOf(".")+1; //$NON-NLS-1$
					String biName = "."+introspectClass.getName().substring(startClassname)+"BeanInfo"; //$NON-NLS-1$ //$NON-NLS-2$
					for (int i=0; i<searchPath.length; i++) {
						if (classExists(searchPath[i]+biName, introspectClass)) {
							found = true;
							break;
						}
					}
				} else
					found = true;
			} else
				found = true;
			
			if (!found)
				return null;
		}
		
		BeanInfo bInfo = Introspector.getBeanInfo(introspectClass);
		Class superClass = introspectClass.getSuperclass();
		
		if (superClass == null)
			return PRE14 ? (ModelingBeanInfo) new ModelingBeanInfoPre14(bInfo) : new ModelingBeanInfo14(bInfo);
		else
			return PRE14 ? (ModelingBeanInfo) new ModelingBeanInfoPre14(bInfo, Introspector.getBeanInfo(superClass)) : new ModelingBeanInfo14(bInfo, Introspector.getBeanInfo(superClass));
	}
	
	/**
	 * See if this class exists, first in the class loader of the sent class,
	 * then in the system loader, then the bootstrap loader, and finally the
	 * current thread context class loader.
	 */
	protected static boolean classExists(String className, Class fromClass) {
		if (fromClass.getClassLoader() != null)
			try {
				fromClass.getClassLoader().loadClass(className);
				return true;
			} catch (ClassNotFoundException e) {
			}
		if (ClassLoader.getSystemClassLoader() != null)
			try {
				ClassLoader.getSystemClassLoader().loadClass(className);
				return true;
			} catch (ClassNotFoundException e) {
			}
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
		}
		
		try {
	        // Use the classloader from the current Thread.
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			cl.loadClass(className);
			return true;
		} catch (ClassNotFoundException e) {
		}
		
		return false;
		
	}
	
	/**
	 * Used only for Object since that is the only bean that doesn't
	 * have a superclass. Superclass beaninfo required for all other
	 * classes. If this is constructed then this means no merge
	 * and the list is definitive.
	 */
	protected ModelingBeanInfo(BeanInfo beanInfo) {
		fTargetBeanInfo = beanInfo;
	}	
	
	protected ModelingBeanInfo(BeanInfo beanInfo, BeanInfo superBeanInfo) {
		this(beanInfo);
		
		// Now go through the beaninfo to determine the merge state.
		// The default is no merge.
		
		List full = addAll(beanInfo.getEventSetDescriptors());
		List inherited = addAll(superBeanInfo.getEventSetDescriptors());
		
		fMergeInheritedEvents = stripList(full, inherited);
		if (fMergeInheritedEvents) {
			if (!full.isEmpty())
				fEventSets = (EventSetDescriptor[]) full.toArray(new EventSetDescriptor[full.size()]);
			if (!inherited.isEmpty())
				createEventArray(inherited);
		}

		full = addAll(beanInfo.getMethodDescriptors());
		inherited = addAll(superBeanInfo.getMethodDescriptors());

		fMergeInheritedMethods = stripList(full, inherited);
		if (fMergeInheritedMethods) {
			if (!full.isEmpty())			
				fMethods = (MethodDescriptor[]) full.toArray(new MethodDescriptor[full.size()]);
			if (!inherited.isEmpty())
				createMethodEntries(inherited);
		}

		full = addAll(beanInfo.getPropertyDescriptors());
		inherited = addAll(superBeanInfo.getPropertyDescriptors());

		fMergeInheritedProperties = stripList(full, inherited);
		if (fMergeInheritedProperties) {
			if (!full.isEmpty())			
				fProperties = (PropertyDescriptor[]) full.toArray(new PropertyDescriptor[full.size()]);
			if (!inherited.isEmpty())
				createPropertyArray(inherited);
		}
		
	}
	
	protected void createEventArray(List features) {
		fInheritedEventSets = createDescriptorNames(features);
	}
	
	protected void createMethodEntries(List features) {
		int s = features.size();
		fInheritedMethods = new String[s];
		for (int i = 0; i < s; i++) {
			fInheritedMethods[i] = longName((MethodDescriptor) features.get(i));
		}
	}

	protected String longName(MethodDescriptor md) {
		String n = md.getName();
		StringBuffer sb = new StringBuffer(n.length() + 20);
		sb.append(n);
		sb.append(':');
		Method m = md.getMethod();
		sb.append(m.getName());
		sb.append('(');
		Class[] parms = m.getParameterTypes();
		for (int j = 0; j < parms.length; j++) {
			if (j>0)
				sb.append(',');
			if (!parms[j].isArray())
				sb.append(parms[j].getName().replace('$', '.'));
			else {
				Class finalType = parms[j].getComponentType();
				int insrt = sb.length();
				while (finalType.isArray()) {
					sb.append("[]"); //$NON-NLS-1$
					finalType = finalType.getComponentType();
				}
				sb.insert(insrt, finalType.getName().replace('$', '.'));
			}
		}
		return sb.toString();
	}
		
	protected void createPropertyArray(List features) {
		fInheritedProperties = createDescriptorNames(features);
	}		
	
	protected String[] createDescriptorNames(List features) {
		String[] result = new String[features.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = ((FeatureDescriptor) features.get(i)).getName();
		}
		return result;
	}		
	
	protected List addAll(Object[] set) {
		if (set != null) {
			ArrayList l = new ArrayList(set.length);
			for (int i = 0; i < set.length; i++) {
				l.add(set[i]);
			}
			return l;
		} else
			return Collections.EMPTY_LIST;
	}
	
	
		/**
	 * If this returns true, then all of the super class's
	 * events should be merged in. If it returns false,
	 * then the events returned are it, there are no
	 * others.
	 */
	public boolean isMergeInheritedEvents() {
		return fMergeInheritedEvents;
	}
	
	/**
	 * If this returns true, then all of the super class's
	 * methods should be merged in. If it returns false,
	 * then the methods returned are it, there are no
	 * others.
	 */	
	public boolean isMergeInheritedMethods() {
		return fMergeInheritedMethods;
	}
	
	
	/**
	 * If this returns true, then all of the super class's
	 * properties should be merged in. If it returns false,
	 * then the properties returned are it, there are no
	 * others.
	 */	
	public boolean isMergeInheritedProperties() {
		return fMergeInheritedProperties;
	}
	
    public BeanInfo[] getAdditionalBeanInfo() {
    	return fTargetBeanInfo.getAdditionalBeanInfo();
    }
    
    public BeanDescriptor getBeanDescriptor() {
    	return fTargetBeanInfo.getBeanDescriptor();
    }
    
    public EventSetDescriptor[] getEventSetDescriptors() {
    	return fMergeInheritedEvents ?
    		fEventSets : fTargetBeanInfo.getEventSetDescriptors();
    }
    
    public java.awt.Image getIcon(int iconKind) {
    	return fTargetBeanInfo.getIcon(iconKind);
    }
    
    public MethodDescriptor[] getMethodDescriptors() {
    	return fMergeInheritedMethods ?
    		fMethods : fTargetBeanInfo.getMethodDescriptors();
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
    	return fMergeInheritedProperties ?
    		fProperties : fTargetBeanInfo.getPropertyDescriptors();
    }
    
    public String[] getInheritedEventSetDescriptors() {
    	return fInheritedEventSets;
    }

    public String[] getInheritedMethodDescriptors() {
    	return fInheritedMethods;
    }
    
    
    public String[] getInheritedPropertyDescriptors() {
    	return fInheritedProperties;
    }

	protected String computeKey(FeatureDescriptor feature) {
		return feature instanceof MethodDescriptor ? longName((MethodDescriptor) feature) : feature.getName();
	}
	
	/*
	 * Strip the list down using the Equality objects. 
	 */
	protected boolean stripList(List fullList, List inheritedList) {
		// The process is to create a boolean list mirroring the inheritedList.
		// This boolean list indicates if the corresponding (by index)
		// entry from the inheritedList is to be retained in the final computed
		// list.
		//
		// A Hashmap is created where the key is the computedKey from the inheritedList
		// and the value is the index into the inheritedList. This is so that we can quickly determine if the
		// entry is matching.
		//
		// Then the fullList will be stepped through and see if there is
		// an entry in the Hashmap for it. If there is an entry, then
		// the entry is checked to see if it is semantically equal.
		// If it is, then the boolean list entry is marked so that
		// the inheritedList entry will be retained, the fullList entry removed and the counter
		// of the number of entries in the inheritedList copy is incremented.
		// If they aren't semantically equal, then we know that this is
		// an override. In that case, the fullList entry is kept, the inheritedList
		// entry is not retained, but we don't prevent merge later.
		//
		// If the fullList entry is not found in the HashMap, then we know it is not
		// from the inheritedList, so it will be retained in the fullList. 
		//
		// If we get all of the way through, then we know that what is left
		// in fullList is just this level.
		//
		// All of this is based upon the assumption that the list can
		// get quite large (which it does for javax.swing) and that
		// a simple n-squared order search would be too slow.

		if (fullList.isEmpty()) {
			return false;	// There are none in the full list, so there should be none, and don't merge.
		} else if (inheritedList.isEmpty())
			return false;	// There are no inheritedList features, so treat as no merge.
			
		// We have some features and some inheritedList features, so we need to go through the lists.

		// Create a working copy of the FeatureDescriptorEquality for fullList and stripList and just reuse them
		FeatureDescriptorEquality workingStrip = FeatureDescriptorEquality.createEquality((FeatureDescriptor) inheritedList.get(0));
		FeatureDescriptorEquality workingFull = FeatureDescriptorEquality.createEquality((FeatureDescriptor) fullList.get(0));

		int inheritedSize = inheritedList.size();
		boolean[] copy = new boolean[inheritedSize];
		
		HashMap inheritedMap = new HashMap(inheritedSize);
		for (int i = 0; i < inheritedSize; i++) {
			FeatureDescriptor f = (FeatureDescriptor) inheritedList.get(i);
			String key = computeKey(f);
			Object value = inheritedMap.get(key);
			if (value == null)
				inheritedMap.put(key, new Integer(i));
			else {
				// Shouldn't occur.
			}

		}

		// When we are done with this processing, inheritedList will contain the super that should be used, and full list will contain only the locals (those defined at this class level).;
		int inheritedRetained = 0;		
		Iterator fullItr = fullList.iterator();
		// Continue while we've retained less than the full super amount. If we've retained all of the inheritedList, there is no
		// need to continue processing the fullList because there can't possibly be any inheritedList entries left to find.
		while(inheritedRetained < inheritedSize && fullItr.hasNext()) {
			FeatureDescriptor f = (FeatureDescriptor) fullItr.next();
			boolean foundFull = false;
			Object index = inheritedMap.get(computeKey(f));
			if (index != null) {
				workingFull.setFeature(f);
				int ndx = ((Integer) index).intValue();
				workingStrip.setFeature((FeatureDescriptor) inheritedList.get(ndx));
				if (workingFull.equals(workingStrip)) {
					// They are semantically identical, so retain in the inheritedList.				
					copy[ndx] = true;
					foundFull = true;
					inheritedRetained++;
				}
			}
			
			if (foundFull) {
				// We found the inheritedList entry semantically equal in the full list somewhere, so we need to remove the full entry.
				fullItr.remove();
			}
		}

		if (inheritedRetained == inheritedSize) {
			inheritedList.clear();	// All were found in inheritedList, so just clear the inheritedList and return just what was left in the found. Those in full found in super had been removed from full during the processing.
			return true;	// We merge, but no locals.
		}

		if (inheritedRetained != 0) {
			// Some were retained, take out of the list the rest.
			// Go from end because the index would immediately become invalid if went from the beginning.
			for (int i = copy.length-1; i >=0 ; i--)
				if (!copy[i])
					inheritedList.remove(i);
		} else
			inheritedList.clear();	// None were retained, so clear the list.

		return !inheritedList.isEmpty();	// We merge if the inheritedList is not empty.
	}

}