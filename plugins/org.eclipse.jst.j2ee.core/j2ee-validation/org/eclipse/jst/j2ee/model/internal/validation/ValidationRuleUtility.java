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
package org.eclipse.jst.j2ee.model.internal.validation;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.ArrayType;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaPackage;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.JavaVisibilityKind;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.internal.impl.LocalModelledPersistentAttributeFilter;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

/**
 * @version 	1.0
 * @author
 */
public final class ValidationRuleUtility {
	public static long method_and_field_counter = 0x1; // used by IMethodAndFieldConstants.java
	private static final String[] _reservedWords = new String[]{"SELECT", "FROM", "WHERE", "DISTINCT", "OBJECT", "NULL", "TRUE", "FALSE", "NOT", "AND", "OR", "BETWEEN", "LIKE", "IN", "AS", "UNKNOWN", "EMPTY", "MEMBER", "OF", "IS"}; //$NON-NLS-1$  //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$ //$NON-NLS-18$ //$NON-NLS-19$ //$NON-NLS-20$
	private static final String COMMA = ", "; //$NON-NLS-1$

	private static LogEntry logEntry = null;
	private static Logger logger = null;
	
	public static HashMap helperMap = null;
	private static HashSet commonClassNames = null;
	
	public static JavaClass getCMRFieldType(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, CMRField field) {
		CommonRelationshipRole role = field.getRole();
		if(!role.isMany()) {
			ContainerManagedEntity cmp = role.getTypeEntity();
			if(cmp == null) {
				return null;
			}
			
			JavaClass local = cmp.getLocalInterface();
			// If the user has made a mistake in ejb-jar.xml, and they are trying to make
			// a relationship to a cmp which does not have a local interface, an InvalidInputException
			// will be thrown. Let the DD validation catch that error; just return
			// when this happens.
			try {
				ValidationRuleUtility.isValidTypeHierarchy(cmp, local);
			}
			catch(InvalidInputException e) {
				return null;
			}
			return local;
		}
			// <cmr-field-type> is either a java.util.Collection or a java.util.Set (p. 464)
			return field.getCollectionType();
	}

	

	
	public static final boolean followsFinderExceptionRules(EnterpriseBean bean, Method method) throws InvalidInputException {
		return ValidationRuleUtility.throwsFinderException(bean, method);
	}

	public static final boolean followsObjectNotFoundExceptionRules(EnterpriseBean bean, Method method) throws InvalidInputException {
		JavaHelpers javaUtilEnumeration = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_ENUMERATION, bean);
		if(ValidationRuleUtility.isAssignableFrom(method.getReturnType(), javaUtilEnumeration)) {
			// Only single object finder and select methods may throw this exception
			return !ValidationRuleUtility.throwsObjectNotFoundException(bean, method);
		}
		
		JavaHelpers javaUtilCollection = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION, bean);
		if(ValidationRuleUtility.isAssignableFrom(method.getReturnType(), javaUtilCollection)) {
			// Only single object finder and select methods may throw this exception
			return !ValidationRuleUtility.throwsObjectNotFoundException(bean, method);
		}
		
		// It's a single finder/select method, so it can throw the exception, though it is not required to.
		return true;
	}

	/**
	 * If it exists, return the Method from the method list which has the given
	 * name and parameter types (in the same order as the array elements).
	 *
	 * This method is a duplicate of getMethodExtended(String, String[]).
	 * For performance reasons, the code was duplicated instead of converting the
	 * JavaParameter[] into a String[].
	 */
	private static Method getMethod(String name, JavaParameter[] parameterTypes, List methodList) {
		if(methodList == null) {
			return null;
		}
		
		Method method = null;
		JavaParameter[] params = null;
		boolean found = false;
	
		Iterator iterator = methodList.iterator();
		while(iterator.hasNext()) {
			method = (Method)iterator.next();
			
			if (method.getName().equals(name)) {
				params = method.listParametersWithoutReturn();
				
				if (params.length == parameterTypes.length) {
					found = true; //Maybe; we need more info
					
					for (int j = 0; j < params.length; j++) {
						//if any one of the parameters doesn't match then flip back to false
						JavaHelpers parm = ((JavaHelpers)params[j].getEType());
						JavaHelpers type = ((JavaHelpers)parameterTypes[j].getEType());
						if(!isSameType(parm, type)) {
							found = false;
							break;
						}
					}
					
					if (found) {
						return method;
					}
				}
			}
		}
		return null;
	}
	
	private static Method getMethod(String name, JavaHelpers[] parameterTypes, List methodList) {
		if(methodList == null) {
			return null;
		}
		
		Method method = null;
		JavaParameter[] params = null;
		boolean found = false;
	
		Iterator iterator = methodList.iterator();
		while(iterator.hasNext()) {
			method = (Method)iterator.next();
			
			if (method.getName().equals(name)) {
				params = method.listParametersWithoutReturn();
				
				if (params.length == parameterTypes.length) {
					found = true; //Maybe; we need more info
					
					for (int j = 0; j < params.length; j++) {
						//if any one of the parameters doesn't match then flip back to false
						JavaHelpers parm = ((JavaHelpers)params[j].getEType());
						JavaHelpers type = parameterTypes[j];
						if(!isSameType(parm, type)) {
							found = false;
							break;
						}
					}
					
					if (found) {
						return method;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * From JavaClass clazz, return a method, named methodNameToFind,
	 * which has exactly the same arguments as methodToMatch.
	 *
	 * If no method exists with that name and argument list, return null.
	 * 
	 * This method does not check the exceptions or return type.
	 */
	public static Method getMethod(Method methodToMatch, String methodNameToFind, List methodsExtendedList) {
		if(methodsExtendedList == null) {
			// It is not invalid to call this method with a null List. A null List
			// means that a type whose methods need to be searched doesn't exist,
			// such as searching a local view, but the bean has only a remote view.
			return null;
		}
		return getMethod(methodNameToFind, methodToMatch.listParametersWithoutReturn(), methodsExtendedList);
	}
	
	/**
	 * From JavaClass clazz, return a method, named methodNameToFind,
	 * which has exactly the same arguments as methodToMatch.
	 *
	 * If no method exists with that name and argument list, return null.
	 * 
	 * This method does not check the exceptions or return type.
	 */
	public static Method getMethodExtended(JavaClass clazz, Method methodToMatch, String methodNameToFind) throws InvalidInputException {
		if ((clazz == null) || (methodToMatch == null) || (methodNameToFind == null)) {
			return null;
		}

		return getMethodExtended(clazz, methodNameToFind, methodToMatch.listParametersWithoutReturn());
	}
	
	/**
	 * Get the method of this name and these parameters. It will look up the supertype hierarchy.
	 *
	 * This method is a duplicate of getMethodExtended(String, String[]).
	 * For performance reasons, the code was duplicated instead of converting the
	 * JavaParameter[] into a String[].
	 */
	public static Method getMethodExtended(JavaClass clazz, String methodName, JavaParameter[] parameterTypes) {
		if (clazz.isInterface()) {
			// Check the current class first. If the method has been overriden,
			// don't want to return the parent's method instead of the child's.
			return getMethodInterfaceExtended(clazz, methodName, parameterTypes);
		}
		// Check the current class first. If the method has been overriden,
		// don't want to return the parent's method instead of the child's.
		Method method = null;
		List methods = null;
		do {
			methods = clazz.getMethods();
			method = getMethod(methodName, parameterTypes, methods);
			if (method != null) {
				return method;
			}

			// else, search the parent	
			clazz = clazz.getSupertype();
		}
		while ((method == null) && (clazz != null));

		return null;
	}
	
	/**
	 * Get the method of this name and these parameters. It will look up the supertype hierarchy.
	 *
	 * This method is a duplicate of getMethodExtended(String, JavaParameter[]).
	 * For performance reasons, the code was duplicated instead of converting the
	 * JavaHelpers[] into a JavaParameter[].
	 */
	public static Method getMethodExtended(JavaClass clazz, String methodName, JavaHelpers[] parameterTypes) {
		return getMethodExtended(clazz, methodName, parameterTypes, null);
	}
	
	/**
	 * Get the method of this name and these parameters. It will look up the supertype hierarchy.
	 *
	 * This method is a duplicate of getMethodExtended(String, JavaParameter[]).
	 * For performance reasons, the code was duplicated instead of converting the
	 * JavaHelpers[] into a JavaParameter[].
	 * 
	 * If retType is null it means that the return type of the method doesn't need to be checked.
	 * Otherwise, check that the return type matches too.
	 */
	public static Method getMethodExtended(JavaClass clazz, String methodName, JavaHelpers[] parameterTypes, JavaHelpers retType) {
		if (clazz.isInterface()) {
			// Check the current class first. If the method has been overriden,
			// don't want to return the parent's method instead of the child's.
			return getMethodInterfaceExtended(clazz, methodName, parameterTypes);
		}
		// Check the current class first. If the method has been overriden,
		// don't want to return the parent's method instead of the child's.
		Method method = null;
		List methods = null;
		do {
			methods = clazz.getMethods();
			method = getMethod(methodName, parameterTypes, methods);
			if (method != null) {
				if(retType != null) {
					JavaHelpers methRetType = method.getReturnType();
					if(isAssignableFrom(methRetType, retType)) {
						return method;
					}
				}
				else {
					return method;
				}
			}

			// else, search the parent	
			clazz = clazz.getSupertype();
		}
		while ((method == null) && (clazz != null));

		return null;
	}
	
	/**
	 * Get the method of this name and these parameters. It will look up the supertype hierarchy.
	 *
	 * This method is a duplicate of getMethodInterfaceExtended(String, String[], JavaClass).
	 * For performance reasons, the code was duplicated instead of converting the
	 * JavaParameter[] into a String[].
	 */
	public static Method getMethodInterfaceExtended(JavaClass clazz, String methodName, JavaParameter[] parameterTypes) {
		if (clazz == null) {
			return null;
		}

		// Check the current class first. If the method has been overriden,
		// don't want to return the parent's method instead of the child's.
		Method method = getMethod(methodName, parameterTypes, clazz.getMethods());
		if (method != null) {
			return method;
		}
		// check parents
		List classesToCheck = clazz.getImplementsInterfaces();
		Iterator clazzIterator = classesToCheck.iterator();
		while (clazzIterator.hasNext()) {
			clazz = (JavaClass) clazzIterator.next();
			method = getMethodInterfaceExtended(clazz, methodName, parameterTypes);
			if (method != null) {
				return method;
			}
		}

		return null;
	}
	
	public static Method getMethodInterfaceExtended(JavaClass clazz, String methodName, JavaHelpers[] parameterTypes) {
		if (clazz == null) {
			return null;
		}

		// Check the current class first. If the method has been overriden,
		// don't want to return the parent's method instead of the child's.
		Method method = getMethod(methodName, parameterTypes, clazz.getMethods());
		if (method != null) {
			return method;
		}
		// check parents
		List classesToCheck = clazz.getImplementsInterfaces();
		Iterator clazzIterator = classesToCheck.iterator();
		while (clazzIterator.hasNext()) {
			clazz = (JavaClass) clazzIterator.next();
			method = getMethodInterfaceExtended(clazz, methodName, parameterTypes);
			if (method != null) {
				return method;
			}
		}

		return null;
	}

	/**
	 * Return an array of the exceptions thrown by method but not methodToMatch.
	 * If there are no such exceptions, return null. (Check the list of exceptions
	 * in the method for each exception, or a parent of the exception.)
	 */
	public static final Set getNotSubsetExceptions(EnterpriseBean bean, Method method, Method methodToMatch) {
		if((methodToMatch == null) || (method == null)) {
			return Collections.EMPTY_SET;
		}
		
		// All the exceptions defined in the throws clause of a method of the 
		// entity bean class must be included in the throws clause of the matching 
		// method of the home interface.
		//
		// Section 12.2.2
		// [15] A checked exception is one that is not a subclass of java.lang.RuntimeException
		// "Note that the javax.ejb.EJBException is a subclass of the java.lang.RuntimeException,
		// and therefore it does not have to be listed in the throws clauses of the business
		// methods."
		HashSet exceptions = new HashSet();
		List methToMatchExceptions = methodToMatch.getJavaExceptions();
		List foundMethExceptions = method.getJavaExceptions();
		for( int i = 0; i < foundMethExceptions.size(); i++ ) {
			JavaClass exc = (JavaClass)foundMethExceptions.get(i);
			if ( !isRuntimeException(exc, bean) && !isRemoteException(exc, bean) && !isExceptionInList(exc, methToMatchExceptions) ) {
				exceptions.add(exc);
			}
		}
	
		return exceptions;
	}
	
	/**
	 * Return an array of the exceptions thrown by method but not methodToMatch.
	 * If there are no such exceptions, return null. (Check the list of exceptions
	 * in the method for each exception, or a child of the exception.)
	 */
	public static final Set getNotSubsetSubtypeExceptions(EnterpriseBean bean, Method method, Method methodToMatch) {
		if((methodToMatch == null) || (method == null)) {
			return Collections.EMPTY_SET;
		}
		
		// All the exceptions defined in the throws clause of a method of the 
		// entity bean class must be included in the throws clause of the matching 
		// method of the home interface.
		//
		// Section 12.2.2
		// [15] A checked exception is one that is not a subclass of java.lang.RuntimeException
		// "Note that the javax.ejb.EJBException is a subclass of the java.lang.RuntimeException,
		// and therefore it does not have to be listed in the throws clauses of the business
		// methods."
		HashSet exceptions = new HashSet();
		List methToMatchExceptions = methodToMatch.getJavaExceptions();
		List foundMethExceptions = method.getJavaExceptions();
		for( int i = 0; i < foundMethExceptions.size(); i++ ) {
			JavaClass exc = (JavaClass)foundMethExceptions.get(i);
			if ( !isRuntimeException(exc, bean) && !isRemoteException(exc, bean) && !isSubtypeExceptionInList(exc, methToMatchExceptions) ) {
				exceptions.add(exc);
			}
		}
	
		return exceptions;
	}
	
	/**
	 * 	Exclude root home interface methods 
	 */
	public static boolean isEJBHomeMethod(EnterpriseBean bean, Method method) {
		return isSameType(bean, method.getContainingJavaClass(), ITypeConstants.CLASSNAME_JAVAX_EJB_EJBHOME);
	}
	
	/**
	 * 	Exclude root remote interface methods 
	 */
	public static boolean isEJBObjectMethod(EnterpriseBean bean, Method method) {
		return isSameType(bean, method.getContainingJavaClass(), ITypeConstants.CLASSNAME_JAVAX_EJB_EJBOBJECT);
	}
	
	/**
	 * Return true if the exception, or a parent of the exception, is in the list of exceptions.
	 */	
	public static final boolean isExceptionInList(JavaClass exc, List exceptionList) {
	       if((exc == null) || (exceptionList == null)) {
	              return false;
	       }
	       
	       if(!exc.isExistingType()) {
	              return false;
	       }
	       
	       Iterator iterator = exceptionList.iterator();
	       while(iterator.hasNext()) {
	              JavaClass exception = (JavaClass)iterator.next();
	              if(isAssignableFrom(exc, exception)) {
	                     // If there is a reflection problem, do not swallow the exception or a
	                     // faulty message may appear. (i.e., "exc" is not thrown by "foo" when it
	                     // really is but one of foo's exceptions wasn't reflected.
	                     return true;
	              }
	       }
	       return false;
	}
	
	/**
	 * Return true if the exception, or a child of the exception, is in the list of exceptions.
	 */	
	public static final boolean isSubtypeExceptionInList(JavaClass exc, List exceptionList) {
	       if((exc == null) || (exceptionList == null)) {
	              return false;
	       }
	       
	       if(!exc.isExistingType()) {
	              return false;
	       }
	       
	       Iterator iterator = exceptionList.iterator();
	       while(iterator.hasNext()) {
	              JavaClass exception = (JavaClass)iterator.next();
	              if(isAssignableFrom(exception, exc)) {
	                     // If there is a reflection problem, do not swallow the exception or a
	                     // faulty message may appear. (i.e., "exc" is not thrown by "foo" when it
	                     // really is but one of foo's exceptions wasn't reflected.
	                     return true;
	              }
	       }
	       return false;
	}

	/**
	 * Often, for validation messages, the validation message contains
	 * the name of a class. But, sometimes, the validation message is
	 * being generated because a class is null when it shouldn't be.
	 * This method is a helper method which makes the construction of
	 * the validation message a little less cluttered (i.e., instead
	 * of checking if the class is null before constructing the
	 * message, just call this method.)
	 */
	public static final String getQualifiedName(JavaHelpers helper) {
		if(helper == null) {
			return ""; //$NON-NLS-1$
		}
		return helper.getJavaName();
	}

	/**
	 * Return the method's parameters in a displayable format. e.g., if
	 * the method signature is "public Integer foo(String a, String b, String c)"
	 * then this method returns "String, String, String".
	 */	
	public static final String getParmsAsString(Method method) {
		if(method == null) {
			return ""; //$NON-NLS-1$
		}
		
		return getParmsAsString(method.listParametersWithoutReturn());
	}
	
	/**
	 * Return the parameters in a displayable format. 
	 * @see getParmsAsString(Method)
	 */
	public static final String getParmsAsString(JavaParameter[] parm) {
		if(parm.length == 0) {
			return ""; //$NON-NLS-1$
		}
		
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<parm.length; i++) {
			JavaHelpers type = parm[i].getJavaType();
			buffer.append(((type == null) ? "null" : type.getJavaName())); //$NON-NLS-1$
			buffer.append(COMMA);
		}
		return buffer.substring(0, buffer.length() - 2); // since "COMMA" is 2 char long, and the last COMMA isn't needed at the end, return everything up to but excluding the last COMMA
	}

	/**
	 * Return the parameters in a displayable format. 
	 * @see getParmsAsString(Method)
	 */
	public static final String getParmsAsString(JavaHelpers[] parm) {
		if(parm.length == 0) {
			return ""; //$NON-NLS-1$
		}
		
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<parm.length; i++) {
			JavaHelpers type = parm[i];
			buffer.append(((type == null) ? "null" : type.getJavaName())); //$NON-NLS-1$
			buffer.append(COMMA);
		}
		return buffer.substring(0, buffer.length() - 2); // since "COMMA" is 2 char long, and the last COMMA isn't needed at the end, return everything up to but excluding the last COMMA
	}
	
	/**
	 * Returns the name of the type, e.g. "com.ibm.foo.MyFoo"
	 * If the type is an array, the array type will be returned.
	 * e.g. if the type is java.lang.String[], java.lang.String[] will
	 * be returned, not the java.lang.String type.
	 */
	public static final JavaHelpers getType(EClassifier classifier) {
		if(classifier == null) {
			return null;
		}
	
		JavaHelpers type = ((JavaHelpers)classifier);
	
		return type;
	}

	 /**
     * javaClassName must be fully qualified
     */
    public static JavaHelpers getType(String javaClassName, EnterpriseBean ejb) throws InvalidInputException {
		if ((javaClassName == null) || (javaClassName.equals("") || (ejb == null))) //$NON-NLS-1$
			throw new InvalidInputException();

		Resource resource = ejb.eResource();
		if (resource == null) {
			throw new InvalidInputException();
		}

		JavaHelpers helper = null;
		if(commonClassNames == null || commonClassNames.isEmpty()) {
			initializeCommonClassNames();
		}
		if (commonClassNames.contains(javaClassName)) {
			if (helperMap == null)
				helperMap = new HashMap();
			ResourceSet rSet = resource.getResourceSet();
			Object obj = helperMap.get(javaClassName);
			if (obj != null) 
				return (JavaHelpers) obj;
			 else {
				helper = getType(javaClassName,rSet);
				helperMap.put(javaClassName, helper);
			}
		} else
			helper = getType(javaClassName, resource.getResourceSet());
		return helper;
	}
	
	private static void initializeCommonClassNames() {
		if(commonClassNames == null)
			commonClassNames = new HashSet();
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_IO_IOEXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_IO_SERIALIZABLE);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_LANG_EXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_LANG_RUNTIMEEXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_RMI_REMOTE);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_RMI_REMOTEEXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_UTIL_ENUMERATION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVA_UTIL_SET);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_CREATEEXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_ENTITYBEAN);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBEXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBHOME);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBLOCALHOME);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBOBJECT);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBLOCALOBJECT);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_ENTITYCONTEXT);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_FINDEREXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_MESSAGEDRIVENBEAN);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_OBJECTNOTFOUNDEXCEPTION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONBEAN);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONCONTEXT);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_EJB_SESSIONSYNCHRONIZATION);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_JMS_MESSAGE);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_JMS_MESSAGELISTENER);
		commonClassNames.add(ITypeConstants.CLASSNAME_JAVAX_TRANSACTION_USERTRANSACTION);
	}
	
	/**
	 * javaClassName must be fully qualified
	 */
	public static JavaHelpers getType(String javaClassName, ResourceSet resourceSet) throws InvalidInputException {
		return getType(javaClassName, resourceSet, true);
	}
	
	/**
	 * javaClassName must be fully qualified
	 */
	public static JavaHelpers getType(String javaClassName, ResourceSet resourceSet, boolean throwExcIfNotValid) throws InvalidInputException {
		if((javaClassName == null)  || (javaClassName.equals("") || (resourceSet == null))) { //$NON-NLS-1$
			Logger aLogger = getMsgLogger();
			if(aLogger.isLoggingLevel(Level.FINEST)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("ValidationRuleUtility.getType(String, ResourceSet, boolean)"); //$NON-NLS-1$
				String text = "invalid parameter; javaClassName = {0} and resourceSet = {1}";
				//entry.setText("invalid parameter; javaClassName = {0} and resourceSet = {1}"); //$NON-NLS-1$
				//entry.setTokens(new String[]{javaClassName, String.valueOf(resourceSet)});
				String result = MessageFormat.format(text,
						 new String[]{javaClassName, String.valueOf(resourceSet)});
				entry.setText(result);
				
				entry.appendStackTrace();
				aLogger.write(Level.FINEST, entry);
			}
			throw new InvalidInputException();
		}
	
		// If the class doesn't exist, it will be reflected
		JavaHelpers helper = JavaRefFactory.eINSTANCE.reflectType(javaClassName, resourceSet);
		if(throwExcIfNotValid) {
			isValidType(helper); // check that the type has been reflected properly
		}
		
		return helper;
	}
	
	/**
     * @return
     */
    private static LogEntry getLogEntry() {
		if(logEntry == null) {
		    logEntry = new LogEntry(IEJBValidatorConstants.BUNDLE_NAME);
		}
		logEntry.reset(); // reset the values so that we're not logging stale data
		return logEntry;
    }

    /**
     * @return
     */
    private static Logger getMsgLogger() {
        if(logger == null)
            logger = J2EECorePlugin.getPlugin().getMsgLogger();
        return logger;
    }

    public static final JavaHelpers getType(Field field) {
		if(field == null) {
			return null;
		}
		
		return (JavaHelpers)field.getEType();
	}
	
	/**
	 * Return true if the JavaClass passed in has the Method defined
	 * on it. Return false if the Method is defined on a supertype or 
	 * is not defined on the class at all.
	 */
	public static final boolean onClass(JavaClass clazz, Method method) {
		if((clazz == null) || (method == null)) {
			return false;
		}
		
		return clazz.equals(method.getJavaClass()); 
	}
	
	/**
	 * Return true if the JavaClass passed in has the Field defined
	 * on it. Return false if the Field is defined on a supertype or 
	 * is not defined on the class at all.
	 */
	public static final boolean onClass(JavaClass clazz, Field field) {
		if((clazz == null) || (field == null)) {
			return false;
		}
		
		return clazz.equals(field.getJavaClass()); 
	}
	
	public static final boolean isApplicationException(JavaClass exception, EnterpriseBean bean) {
		if(exception == null) {
			return false;
		}
		
		// Exempt EJBException from the application exception check because it'll drive users crazy to see 
		// it flagged as an application exception.
		try {
			if(ValidationRuleUtility.isSameType(exception, getType(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBEXCEPTION, bean))) {
				return false;
			}
		}
		catch(InvalidInputException e) {
			// logged in getType()
			// if EJBException can't be found, then there's probably a system JAR missing
		}
		
		// 18.1.1 Application exceptions
		// An application exception is an exception defined in the throws clause of a method of the enterprise
		// Bean's home and component interfaces, other than the java.rmi.RemoteException.
		return !isRemoteException(exception, bean);
	}
	
	public static final boolean isRemoteMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		try {
			JavaHelpers javaRmiRemote = ValidationRuleUtility.getType(ITypeConstants.CLASSNAME_JAVA_RMI_REMOTE, bean);
			if(isAssignableFrom(clazz, javaRmiRemote)) {
				return true;
			}
		}
		catch(InvalidInputException e) {
			return false;
		}
		
		return false;
	}
	
	public static final boolean isReservedWord(String name) {
		if(name == null) {
			return false;
		}
		
		String upperName = name.toUpperCase();
		for(int i=0; i<_reservedWords.length; i++) {
			if(upperName.equals(_reservedWords[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static final boolean isRuntimeException(JavaClass clazz, EnterpriseBean bean) {
		if(clazz == null) {
			return false;
		}
		
		try {
			JavaHelpers runtimeExc = getType(ITypeConstants.CLASSNAME_JAVA_LANG_RUNTIMEEXCEPTION, bean);
			return isException(clazz, runtimeExc.getWrapper());
		}
		catch(InvalidInputException e) {
			return false;
		}
	}
	
	public static final boolean isRemoteException(JavaClass clazz, EnterpriseBean bean) {
		if(clazz == null) {
			return false;
		}
		
		try {
			JavaHelpers remoteExc = getType(ITypeConstants.CLASSNAME_JAVA_RMI_REMOTEEXCEPTION, bean);
			return isException(clazz, remoteExc.getWrapper());
		}
		catch(InvalidInputException e) {
			return false;
		}
	}

	/**
	 * Return true if the clazz is an instance of the exception.
	 */	
	public static final boolean isException(JavaClass clazz, JavaClass exception) {
		if((clazz == null) || (exception == null)) {
			return false;
		}
		
		return isAssignableFrom(clazz, exception);
	}
	
	/**
	 * Return true if the type is either java.util.Collection, or a type which inherits/extends java.util.Collection.
	 * The bean is needed to retrieve the java.util.Collection type.
	 */
	public static boolean isAssignableFromCollection(JavaHelpers type, EnterpriseBean bean) throws InvalidInputException {
		JavaHelpers javaUtilCollection = getType(ITypeConstants.CLASSNAME_JAVA_UTIL_COLLECTION, bean);
		return isAssignableFrom(type, javaUtilCollection);
	}
	
	/**
	 * Return true if the type is either java.util.Enumeration, or a type which inherits/extends java.util.Enumeration.
	 * The bean is needed to retrieve the java.util.Enumeration type.
	 */
	public static boolean isAssignableFromEnumeration(JavaHelpers type, EnterpriseBean bean) throws InvalidInputException {
		JavaHelpers javaUtilEnumeration = getType(ITypeConstants.CLASSNAME_JAVA_UTIL_ENUMERATION, bean);
		return isAssignableFrom(type, javaUtilEnumeration);
	}
	
	/**
	 * Return true if the type is either java.util.Set, or a type which inherits/extends java.util.Set.
	 * The bean is needed to retrieve the java.util.Set type.
	 */
	public static boolean isAssignableFromSet(JavaHelpers type, EnterpriseBean bean) throws InvalidInputException {
		JavaHelpers javaUtilSet = getType(ITypeConstants.CLASSNAME_JAVA_UTIL_SET, bean);
		return isAssignableFrom(type, javaUtilSet);
	}
	
	/**
	 * Returns true if the type is either the compare type, or inherits/extends the compare type.
	 */
	public static boolean isAssignableFrom(JavaHelpers type, JavaHelpers compareType) {
		if((type == null) || (compareType == null)) {
			return false;
		}
	
		if(type.isPrimitive() || compareType.isPrimitive()) {
			if(type.isPrimitive() && compareType.isPrimitive()) {
				return type.getPrimitive().equals(compareType.getPrimitive());
			}
			return false;
		}
		else if(type.isArray() && compareType.isArray()) {
			JavaClass classType = type.getWrapper();
			JavaClass classCompareType = compareType.getWrapper();
			if((classType == null) || (compareType == null)) {
				return false;
			}
			JavaHelpers finalType = ((ArrayType)classType).getFinalComponentType();
			JavaHelpers finalCompareType = ((ArrayType)classCompareType).getFinalComponentType();
			return isAssignableFrom(finalType, finalCompareType);
		}
		else {
			return compareType.getWrapper().isAssignableFrom(type.getWrapper());
		}
	}

	/**
	 * Return true if the given method is associated with a query element in ejb-jar.xml.
	 */
	public static final boolean isAssociatedWithQuery(ContainerManagedEntity cmp, Method method) {
		// Check method is associated with a query element in ejb-jar.xml.
		List queries = cmp.getQueries();
		Iterator iterator = queries.iterator();
		boolean found = false;
		while(iterator.hasNext()) {
			Query query = (Query)iterator.next();
			QueryMethod queryMethod = query.getQueryMethod();
			if(queryMethod == null) {
				// Let the EJBJarVRule report this
				continue;
			}
			
			if((queryMethod.getName() == null) || queryMethod.getName().equals("")) { //$NON-NLS-1$
				// Let the EJBJarVRule report this
				continue;
			}
			
			if(queryMethod.getName().equals(method.getName())) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public static final boolean isBeanManagedSession(EnterpriseBean bean) {
		if(bean == null) {
			return false;
		}
		
		if(bean.isEntity()) {
			return false;
		}
	
		return (((Session)bean).getTransactionType().getValue() == TransactionType.BEAN);
	}
	
	/**
	 * Return true if this method is the static initializer used for interfaces ("<clinit>")
	 */
	public static boolean isClinitMethod(EnterpriseBean bean, Method method) {
		if (method == null) {
			return false;
		}

		return method.getName().equals("<clinit>"); //$NON-NLS-1$
	}

	public static final boolean isJNDINamingContext(Field field) {
		return true;
	}
	
	/**
	 * Return true if the field is defined on java.lang.Object.
	 */
	public static boolean isJavaLangObjectField(EnterpriseBean bean, Field field) {
		return isSameType(bean, field.getContainingJavaClass(), ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT);
	}
	
	/**
	 * Return true if the method is defined on named class.
	 */
	public static boolean isOwnedBy(EnterpriseBean bean, Method method, String className) {
		return isSameType(bean, method.getContainingJavaClass(), className);
	}
	
	/**
	 * Return true if given class is the same as the named class.
	 */
	public static boolean isSameType(EnterpriseBean bean, JavaClass owner, String className) {
		try {
			JavaHelpers helper = getType(className, bean);
			if(helper == null) {
				return false;
			}
			
			JavaClass object = helper.getWrapper();
			
			return isSameType(owner, object);
		}
		catch(InvalidInputException e) {
			return false;
		}
	}
	
	/**
	 * Return true if given class is the same as the named class.
	 */
	public static boolean isSameType(JavaHelpers owner, JavaHelpers object) {
		if(owner == null) {
			return false;
		}
		
		if(object == null) {
			return false;
		}
		
		if(owner.isPrimitive() && object.isPrimitive()) {
			// Both are JavaDataType
			return owner.getPrimitive().equals(object.getPrimitive());
		}
		else if(owner.isArray() && object.isArray()) {
			// Both are ArrayType
			ArrayType ownerArray = (ArrayType)owner;
			ArrayType objectArray = (ArrayType)object;
			return isSameType(ownerArray.getFinalComponentType(), objectArray.getFinalComponentType());
		}
		else if((owner.getWrapper() != null) && (object.getWrapper() != null)) {
			// Both are JavaClass
			return owner.getWrapper().equals(object.getWrapper());
		}
		else {
			// mismatching JavaHelpers type
			return false;
		}
	}
	
	/**
	 * Return true if the method is defined on java.lang.Object.
	 */
	public static boolean isJavaLangObjectMethod(EnterpriseBean bean, Method method) {
		return isSameType(bean, method.getContainingJavaClass(), ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT);
	}
	
	public static final boolean isLegalRMI_IIOPInheritance(JavaClass clazz) {
		return true;
	}
	
	public static final boolean isLegalRMI_IIOPType(EnterpriseBean bean, JavaHelpers helper) throws InvalidInputException {
		if(helper == null) {
			return false;
		}
	
		return isSerializable(helper, bean);
	}
	
	public static final boolean isLocalType(EnterpriseBean bean, JavaHelpers type) {
		try {
			if(isSameType(type, getType(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT, bean))) {
				// If there's an undefined primary key (java.lang.Object), don't flag it as a local type.
				return false;
			}
		}
		catch(InvalidInputException e) {
			// Let the DD validation report this.
			return false;
		}
		
		if(isAssignableFrom(bean.getLocalInterface(), type)) {
			return true;
		}
		
		if(isAssignableFrom(bean.getLocalHomeInterface(), type)) {
			return true;
		}
		
		// Bit of a performance hack here.
		// Don't want to load and parse the entire EJBJar just to check if
		// this type is local, so assume that if the extends 
		// javax.ejb.EJBLocalHome or javax.ejb.EJBLocalObject, then it's local.
		try {
			JavaHelpers ejbLocalHome = getType(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBLOCALHOME, bean);
			if(isAssignableFrom(ejbLocalHome, type)) {
				return true;
			}
		}
		catch(InvalidInputException exc) {
			// ignore and try other type
		}
		
		try {
			JavaHelpers ejbLocalObject = getType(ITypeConstants.CLASSNAME_JAVAX_EJB_EJBLOCALOBJECT, bean);
			if(isAssignableFrom(ejbLocalObject, type)) {
				return true;
			}
		}
		catch(InvalidInputException exc) {
			// ignore
		}
		
		return false;
	}
	

	
	public static final boolean usesUnknownPrimaryKey(EnterpriseBean bean) {
		if(bean.isContainerManagedEntity()) {
			return usesUnknownPrimaryKey((ContainerManagedEntity)bean);
		}
		return false;
	}
	
	public static final boolean usesUnknownPrimaryKey(ContainerManagedEntity cmp) {
		if(cmp == null) {
			return false;
		}
		
		try {
			if(isSameType(cmp.getPrimaryKey(), getType(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT, cmp))) {
				return true;
			}
		}
		catch(InvalidInputException e) {
			// If java.lang.Object can't be found, there's bigger problems than validating
			// it mistakenly
			return false;
		}

		if(cmp.getVersionID() == J2EEVersionConstants.EJB_2_0_ID) {
			List cmpFields = cmp.getFilteredFeatures(LocalModelledPersistentAttributeFilter.singleton());
			if(cmpFields != null && !cmpFields.isEmpty()) {
			Iterator iterator = cmpFields.iterator();
			while(iterator.hasNext()) {
				CMPAttribute attrib = (CMPAttribute)iterator.next();
				if(isUnknownPrimaryKey(attrib)) {
					return true;
				}
			}
		  }
		}
		return false;
	}
	
	public static final boolean isUnidirectionalRelationship(IEJBValidationContext vc, EJBRelationshipRole role) {
		// Page 131 of EJB 2.0:
		// A unidirectional relationship is implemented with a cmr-field on the entity bean instance from which
		// navigation can take place, and no related cmr-field on the entity bean instance that is the target of the
		// relationship. Unidirectional relationships are typically used when the Bean Provider wishes to restrict
		// the visibility of a relationship.
		Entity sourceEntity = role.getSource().getEntityBean();
		if(sourceEntity == null) {
			return false;
		}
		
		if(!sourceEntity.isContainerManagedEntity()) {
			return false;
		}
		
		CMRField sourceCMRField = role.getCmrField();
		if(sourceCMRField == null) {
			return false;
		}
		
		EJBRelationshipRole opposite = role.getOpposite();
		if(opposite == null) {
			return false;
		}
		
		CMRField oppositeCMRField = opposite.getCmrField();
		if(oppositeCMRField != null) {
			return false;
		}
		
		return true;
		
	}
	
	
	
	public static final boolean isUnknownPrimaryKey(CMPAttribute attrib) {
		if(attrib == null) {
			return false;
		}
		
		// Section 10.8.3
		// By definition, the deployer's generated CMP field's name is defined in the 
		// ContainerManagedEntity.WAS_GENERATED_STRING_FIELD constant.
		return ContainerManagedEntity.WAS_GENERATED_STRING_FIELD.equals(attrib.getName());
	}
	
	public static final boolean isPrimitivePrimaryKey(ContainerManagedEntity cmp) {
		if(cmp == null) {
			return false;
		}
	
		if((cmp.getPrimKeyField() != null) && !cmp.getPrimKeyField().eIsProxy()) {
			return true;
		}
	
		return false;
	}
	
	public static final boolean isPublic(Field field) {
		if ( field == null )
			return false;

		return (JavaVisibilityKind.PUBLIC_LITERAL == field.getJavaVisibility());
	}

	public static final boolean isPublic( Method method ) {
		if ( method == null )
			return false;

		return (JavaVisibilityKind.PUBLIC_LITERAL == method.getJavaVisibility());
	}

	/**
	 * Verifies that a given Class type is serializable.
	 *
	 * RMI 1.3 specification
	 * 2.6 Parameter Passing in Remote Method Invocation
	 * An argument to, or a return value from, a remote object can be any object that
	 * is serializable. This includes primitive types, remote objects, and non-remote
	 * objects that implement the java.io.Serializable interface.
	 */
	public static final boolean isSerializable(JavaHelpers cl, EnterpriseBean bean) throws InvalidInputException {
		if(cl == null) {
			// assume it's void
			return true;
		}
	
		// Check if the type reflected properly
		isValidType(cl);
		
		if ( cl.isPrimitive() ) {
			// JavaHelpers is a JavaDataType
			return true;
		}
		else if(cl.isArray()) {
			ArrayType array = (ArrayType)cl;
			if(array.isPrimitiveArray()) {
				return true;
			}
			// else, not primitive, so check its contents
			cl = array.getFinalComponentType();
		}
	
		JavaHelpers remote = getType(ITypeConstants.CLASSNAME_JAVA_RMI_REMOTE, bean);
		if(isAssignableFrom(cl, remote)) {
			return true;
		}
		
		JavaHelpers serial = getType(ITypeConstants.CLASSNAME_JAVA_IO_SERIALIZABLE, bean);
		return isAssignableFrom(cl, serial);
	}
	
	public static final boolean isStateful(EnterpriseBean bean) {
		if(bean == null) {
			return false;
		}
		
		if(bean.isEntity()) {
			return false;
		}
	
		return (((Session)bean).getSessionType().getValue() == SessionType.STATEFUL);
	}
	
	public static final boolean isStateless(EnterpriseBean bean) {
		if(bean == null) {
			return false;
		}
		
		if(bean.isEntity()) {
			return false;
		}
	
		return (((Session)bean).getSessionType().getValue() == SessionType.STATELESS);
	}
		
	public static boolean isUnnamedPackage(JavaPackage pkg) {
		if(pkg == null) {
			return true;
		}
		
		String pkgName = pkg.getName();
		return ((pkgName == null) || (pkgName.equals(""))); //$NON-NLS-1$
	}
	
	public static final boolean isValidJavaIdentifier(String name) {
		if(name == null) {
			return false;
		}
		
		char[] nameChar = name.toCharArray();
		if(nameChar.length < 1) {
			return false;
		}
		
		if(!Character.isJavaIdentifierStart(nameChar[0])) {
			return false;
		}
		boolean isValidJavaIdentifier = true;
		for(int i=1; i<nameChar.length; i++) {
			if(!Character.isJavaIdentifierPart(nameChar[i])) {
				isValidJavaIdentifier = false;
				break;
			}
		}
		return isValidJavaIdentifier;
	}
	
	/**
	 * 6.5.3 The optional SessionSynchronization interface
	 *...
	 * Only a stateful Session bean with container-managed transaction demarcation may 
	 * implement the SessionSynchronization interface.
	 *...
	 * There is no need for a Session bean with bean-managed transaction to rely on the 
	 * synchronization call backs because the bean is in control of the commit the bean 
	 * knows when the transaction is about to be committed and it knows the outcome of the 
	 * transaction commit.
	 *...
	 */
	public static final boolean isValidSessionTypeElement(Session session) {
		if(session == null) {
			return false;
		}
		
		// check syntax
		if(!session.isSetSessionType()) {
			return false;
		}
		
		if(session.getSessionType() == null) {
			return false;
		}
		
		if(session.getSessionType().getValue() == SessionType.STATEFUL) {
			return true;
		}
		
		if(session.getSessionType().getValue() == SessionType.STATELESS) {
			return true;
		}
	
		return false;
	}
	
	public static final boolean isValidTransactionTypeElement(Session sessionBean) {
		if(sessionBean == null) {
			return false;
		}
		
		if(!sessionBean.isSetTransactionType()) {
			return false;
		}
		
		if(sessionBean.getTransactionType() == null) {
			return false;
		}
		
		if(sessionBean.getTransactionType().getValue() == TransactionType.BEAN) {
			return true;
		}
		
		if(sessionBean.getTransactionType().getValue() == TransactionType.CONTAINER) {
			return true;
		}
	
		return false;	
	}
	
	/**
	 * If the type is not reflected properly, throw an InvalidInputException.
	 */
	public static final void isValidType(JavaHelpers type) throws InvalidInputException {
		if(type == null) {
			// Cheating here. Should technically access the MsgLogger from the IEJBValidationContext
			// type, but when I started changing this method and the isValidTypeHierarchy method,
			// I ended up changing 42 files because these two methods are called directly or
			// indirectly from almost everywhere. That was too much change, so I cheated.
			Logger aLogger = getMsgLogger();
			if(aLogger.isLoggingLevel(Level.FINEST)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("ValidationRuleUtility.isValidType"); //$NON-NLS-1$
				entry.setText("type is null"); //$NON-NLS-1$
				entry.appendStackTrace();
				aLogger.write(Level.FINEST, entry);
			}
			throw new InvalidInputException();
		}
	
		if(type.getWrapper() != null) {
			// check if the type reflected properly. If there's any compile errors, or references to unresolved
			// classes, can output some mistaken error messages.
			// e.g. class is not serializable
			JavaClass wrapper = type.getWrapper();
			if(type.isArray()) {
				ArrayType array = (ArrayType)type;
				isValidType(array.getFinalComponentType());
			}
			else {
				if(!wrapper.isExistingType()) {
					Logger aLogger = getMsgLogger();
					if(aLogger.isLoggingLevel(Level.FINEST)) {
						LogEntry entry = getLogEntry();
						entry.setSourceID("ValidationRuleUtility.isValidType"); //$NON-NLS-1$
						entry.setText("!wrapper.isExistingType(); type is " + wrapper.getJavaName()); //$NON-NLS-1$ 
						aLogger.write(Level.FINEST, entry);
					}
					throw new InvalidInputException(wrapper);
				}
			}
		}
	}
	
	/**
	 * If the type is not reflected properly, throw an InvalidInputException.
	 * This method, unlike isValidType, also checks every interface and class
	 * parent of the passed-in type.
	 */
	public static final void isValidTypeHierarchy(EnterpriseBean bean, JavaHelpers type) throws InvalidInputException {
		if(type == null) {
			// Cheating here. Should technically access the MsgLogger from the IEJBValidationContext
			// type, but when I started changing this method and the isValidType method,
			// I ended up changing 42 files because these two methods are called directly or
			// indirectly from almost everywhere. That was too much change, so I cheated.
			Logger aLogger = getMsgLogger();
			if(aLogger.isLoggingLevel(Level.FINEST)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("ValidationRuleUtility.isValidTypeHierarchy"); //$NON-NLS-1$
				entry.setText("type is null"); //$NON-NLS-1$
				entry.appendStackTrace();
				aLogger.write(Level.FINEST, entry);
			}
			throw new InvalidInputException();
		}
	
		// First, check this helper itself.
		isValidType(type);
		JavaHelpers javaLangObject = getType(ITypeConstants.CLASSNAME_JAVA_LANG_OBJECT, bean);
		if(isSameType(type, javaLangObject)) {
			return;
		}
		
		// If this helper exists, and is not java.lang.Object, then check every supertype.
		JavaClass classType = type.getWrapper();
		if(classType == null) {
			Logger aLogger = getMsgLogger();
			if(aLogger.isLoggingLevel(Level.FINEST)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("ValidationRuleUtility.isValidTypeHierarchy"); //$NON-NLS-1$
				entry.setText("type.getWrapper() returns null; type is " + type.getJavaName()); //$NON-NLS-1$
				aLogger.write(Level.FINEST, entry);
			}
			throw new InvalidInputException();
		}
	
		// Check the superclass. Since type is not java.lang.Object, there is at least
		// the java.lang.Object superclass.
		if(!classType.isInterface()) {
			JavaClass superType = classType.getSupertype();
			isValidTypeHierarchy(bean, superType);
		}
	
		// Check the superinterfaces, if they exist.
		List superInterfaces = classType.getImplementsInterfaces();
		if(superInterfaces != null) {
			Iterator iterator = superInterfaces.iterator();
			while(iterator.hasNext()) {
				JavaClass superIntf = (JavaClass)iterator.next();
				isValidTypeHierarchy(bean, superIntf);
			}
		}
	}
	
	/**
	 * RMI 1.3 page 6 section 2.4.1, 
	 *    A remote method declaration must include the exception java.rmi.RemoteException (or one
	 *    of its upserclasses such as java.io.IOException or java.lang.Exception) in its throws
	 *    clause, in addition to any application-specific exceptions (note that application 
	 *    specific exceptions do not have to extend java.rmi.RemoteException).
	 */
	public static final boolean throwsRemoteExceptionOrParent(EnterpriseBean bean, Method method) throws InvalidInputException {
		return throwsExceptionOrParent(bean, method, ITypeConstants.CLASSNAME_JAVA_RMI_REMOTEEXCEPTION);
	}

	/**
	 * Convenience method which checks if RemoteException (exactly, not Exception or IOException)
	 * is in the method signature.
	 */	
	public static final boolean doesNotThrowRemoteException(EnterpriseBean bean, Method method) throws InvalidInputException {
		return !throwsException(bean, method, ITypeConstants.CLASSNAME_JAVA_RMI_REMOTEEXCEPTION);
	}
	
	public static final boolean throwsObjectNotFoundException(EnterpriseBean bean, Method method) throws InvalidInputException {
		return throwsException(bean, method, ITypeConstants.CLASSNAME_JAVAX_EJB_OBJECTNOTFOUNDEXCEPTION);
	}
	
	public static final boolean throwsFinderException(EnterpriseBean bean, Method method) throws InvalidInputException {
		return throwsException(bean, method, ITypeConstants.CLASSNAME_JAVAX_EJB_FINDEREXCEPTION);
	}
	
	public static final boolean throwsCreateException(EnterpriseBean bean, Method method) throws InvalidInputException {
		return throwsException(bean, method, ITypeConstants.CLASSNAME_JAVAX_EJB_CREATEEXCEPTION);
	}
	
	public static final boolean throwsApplicationException(Method method, EnterpriseBean bean) {
		List exceptions = method.getJavaExceptions();
		if(exceptions.size() == 0) {
			return false;
		}
		
		// CHKJ2419 = {0} must be a subclass of java.lang.Exception. Read section 18.1.1, 18.2.1 of the EJB 2.0 specification.
		Iterator iterator = exceptions.iterator();
		while(iterator.hasNext()) {
			JavaClass exception = (JavaClass)iterator.next();
			
			if(isApplicationException(exception, bean)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return true if the given method throws the named exception or a class which extends the named exception.
	 */	
	public static final boolean throwsException(EnterpriseBean bean, Method method, String exceptionName) throws InvalidInputException {
		if(method == null) {
			return false;
		}
		
		List exceptions = method.getJavaExceptions();
		Iterator iterator = exceptions.iterator();
		JavaClass exception = getType(exceptionName, bean).getWrapper();
		while(iterator.hasNext()) {
			JavaClass exc = (JavaClass)iterator.next();
			if(isAssignableFrom(exc, exception)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Return true if the method returns the named exception, an exception which extends the named exception, or one of the parents of the named exception (e.g. java.lang.Exception).
	 * Use this method if it needs to be detected that an exception can be thrown either directly or through something generic like java.lang.Exception.
	 */
	public static final boolean throwsExceptionOrParent(EnterpriseBean bean, Method method, String exceptionName) throws InvalidInputException {
		if(method == null) {
			return false;
		}
		
		JavaClass exception = getType(exceptionName, bean).getWrapper();

		List exceptions = method.getJavaExceptions();
		Iterator iterator = exceptions.iterator();
		while(iterator.hasNext()) {
			JavaClass exc = (JavaClass)iterator.next();
			if(isAssignableFrom(exc, exception)) {
				// Assignable from the named exception, e.g. RemoteException or a child of RemoteException
				return true;
			}
			
			if(isAssignableFrom(exception, exc)) {
				// The named exception is assignable from the method exception, e.g. java.lang.Exception
				return true;
			}
		}
		
		return false;
	}
	
	public static final boolean usesCollectionClass(EnterpriseBean bean, Method method) {
		if((bean == null) || (method == null)) {
			return false;
		}
		
		JavaParameter[] parms = method.listParametersWithoutReturn();
		if(parms != null) {
			for(int i=0; i<parms.length; i++) {
				JavaParameter parm = parms[i];
				try {
					if(isAssignableFromCollection(parm.getJavaType(), bean)) {
						return true;
					}
				}
				catch(InvalidInputException e) {
					// ignore and test next parm
					continue;
				}
			}
		}
		
		// Now check the return type
		try {
			if(isAssignableFromCollection(method.getReturnType(), bean)) {
				return true;
			}
		}
		catch(InvalidInputException e) {
			// ignore
		}
			
		return false;
	}

	public static final boolean usesLocalTypes(EnterpriseBean bean, Method method) {
		if((bean == null) || (method == null)) {
			return false;
		}
		
		JavaParameter[] parms = method.listParametersWithoutReturn();
		if(parms != null) {
			for(int i=0; i<parms.length; i++) {
				JavaParameter parm = parms[i];
				if(isLocalType(bean, parm.getJavaType())) {
					return true;
				}
			}
		}

		// Now check the return type
		if(isLocalType(bean, method.getReturnType())) {
			return true;
		}
			
		return false;
	}
}

