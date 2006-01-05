package org.eclipse.jst.j2ee.archive.testutilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jem.java.internal.impl.JavaClassImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

public class EMFAttributeFeatureGenerator {
	public static String avClass[];
	protected static int count = 0;
	protected static Random randomGenerator = new Random();
	protected static int maxNumberOfProjectsCreated = 1000;
	protected static Map enumMap = new HashMap();
	protected static int paramCount;
	protected static String[] langArray = new String[] { "EN","FR","DE","JP","CN","BR","SP"};
	protected static int langCount = 0;  
	protected static Map booleanMap = new HashMap();
	protected static Map attIndex = new HashMap();
	protected static boolean specialNumberGen = false;
	protected static String httpArray[] = null;
	protected static int httpArrayIndex = 0;
	
	public static int version;
	public static int moduleType;
	//static versions
	public static final int VERSION_1_2 = 0;
	public static final int VERSION_1_3 = 1;
	public static final int VERSION_1_4 = 2;

	public static final int APPICATION = 0;
	public static final int APP_CLIENT = 1;
	public static final int CONNECTOR = 2;
	public static final int EJB = 3;
	public static final int WEB = 4;
	
    public EMFAttributeFeatureGenerator() {
        super();
    }
    
    public static void resetCount() {
    	count = 0;
    	return;
    }
	public static Object createAttribute(EAttribute att, EObject obj){
		return createAttribute(new EAttributeDescriptor(att,obj.eClass()));
	}
	
	public static Object createAttribute(EAttribute att, EClass eClass){
		return createAttribute(new EAttributeDescriptor(att,eClass));
	}
	
	public static Object createAttribute(EAttribute att, EObject obj, boolean special){
		specialNumberGen = special;
		return createAttribute(new EAttributeDescriptor(att,obj.eClass()));
	}
			
	public static Object createAttribute(EAttribute att, EObject obj,  boolean special, int ver, int modType) {
	   specialNumberGen = special;
	   version = ver;
	   moduleType = modType;
	   return createAttribute(new EAttributeDescriptor(att,obj.eClass()));
   }

    protected static Object createAttribute(EAttributeDescriptor att) {
		EClassifier meta = att.getFeature().getEType();
		if (meta instanceof EEnum && att.getFeature().isMany()){
			List collection = new ArrayList();
			collection.add(createEnum(att));
			return collection;
		}
		else if (meta instanceof EEnum)
			return createEnum(att);
		else if ((version == VERSION_1_3 || version == VERSION_1_2) && att.getFeature().equals(WebapplicationPackage.eINSTANCE.getHTTPMethodType_HttpMethod()))
			return createSpecialHttpMethodsFor13();
		else if (version == VERSION_1_3 && att.getFeature().equals(CommonPackage.eINSTANCE.getEJBLocalRef_Local()))
			return generateRandomAttributeString(new EAttributeDescriptor(CommonPackage.eINSTANCE.getEjbRef_Remote(), att.getMetaClass()));
		else if (version == VERSION_1_3 && att.getFeature().equals(CommonPackage.eINSTANCE.getEJBLocalRef_LocalHome()))
			return generateRandomAttributeString(new EAttributeDescriptor(CommonPackage.eINSTANCE.getEjbRef_Home(),att.getMetaClass()));
		else if (att.getFeature().getName().equals("parms"))
			return createParams(att);
		else if (att.getFeature().getName().startsWith("location") && version == VERSION_1_4)
			return createLocation(att);
		else if (att.getFeature().getName().startsWith("locale") && version == VERSION_1_4)
			return createLocale();
		else if (att.getFeature().getName().equals("lang"))
			return createLang(att);
		else if (att.getFeature().getName().equals("version"))
			if (att.getFeature() == EjbPackage.eINSTANCE.getContainerManagedEntity_Version()) 
				return "2.x";
			else
				return createVersion(att);
		else if (att.getFeature().getName().equals("mimeType") && version == VERSION_1_4)
			return "text/plain";
		else if (att.getFeature().getName().equals("errorCode") && version == VERSION_1_4)
			return "404";
		else if (att.getFeature().getName().equals("formLoginPage") && version == VERSION_1_4)
			return "/testFormLoginPage";
		else if (att.getFeature().getName().equals("formErrorPage") && version == VERSION_1_4)
			return "/testFormErrorpage";
		else if(att.getFeature().getName().equals("link") && version == VERSION_1_4)
			return "roleName_0";
		else if(att.getFeature().getName().equals("specVersion") && version == VERSION_1_4)
			return "1.5";
		else if (att.getFeature().isMany())
			return createCollection(att);
		switch (meta.getClassifierID()) {
			case EcorePackage.ESTRING:
				return generateRandomAttributeString(att);
			case EcorePackage.EBOOLEAN_OBJECT:
			case EcorePackage.EBOOLEAN:
				return generateRandomBoolean(att);
			case EcorePackage.EINTEGER_OBJECT:
			case EcorePackage.EINT:
				return generateRandomInteger(att);
			case EcorePackage.EFLOAT_OBJECT:
			case EcorePackage.EFLOAT:
				return generateRandomFloat(att);
			case EcorePackage.ECHARACTER_OBJECT:
			case EcorePackage.ECHAR:
				return generateRandomChar(att);
			case EcorePackage.ELONG_OBJECT:
			case EcorePackage.ELONG:
				return generateRandomLong();
			case EcorePackage.EBYTE_OBJECT:
			case EcorePackage.EBYTE:
				return generateRandomByte();
			case EcorePackage.EDOUBLE_OBJECT:
			case EcorePackage.EDOUBLE:
				return generateRandomDouble();
			case EcorePackage.ESHORT_OBJECT:
			case EcorePackage.ESHORT:
				return generateRandomShort(att);
			case EcorePackage.EJAVA_OBJECT:
				return generateRandomObject(att);
			}
		return null;
    }

	/**
     * @return
     */
    private static Object createLocale() {
        return "en_US";
    }

    /**
     * @param att
     * @return
     */
    private static Object createLocation(EAttributeDescriptor att) {
        String temp = generateRandomAttributeString(att);
        return "/." + temp;
    }

    public static EObject createJavaClassProxy(EStructuralFeature ref, EObject eObject) {
		if (avClass == null) {
			avClass = new String[] { "java.util.HashTable", "java.util.List", "java.sql.Data", "java.lang.Integer", "java.lang.String" };
		}
		EAttributeDescriptor feature = new EAttributeDescriptor(ref,eObject.eClass());
		if (!attIndex.containsKey(feature))
			attIndex.put(feature, new Integer(0));
		int classIndex = ((Integer)attIndex.get(feature)).intValue();
		String name = avClass[classIndex];
		classIndex++;
		if (classIndex == 5)
			classIndex = 0;
		attIndex.put(feature,new Integer(classIndex));
		return JavaClassImpl.createClassRef(name);
	}
	/**
	 * @return
	 */
	protected static Object createSpecialHttpMethodsFor13() {
		if (httpArray == null)
			httpArray = new String[] {
				"GET",
				"POST",
				"PUT",
				"DELETE",
				"HEAD",
				"OPTIONS",
				"TRACE"
			};
		if (httpArrayIndex >= httpArray.length)
			httpArrayIndex = 0;
		Object holder = httpArray[httpArrayIndex]; 	
		httpArrayIndex++;
		return holder;
	}

	/**
     * @param att
     * @return
     */
    protected static String createVersion(EAttributeDescriptor att) {
		String versionHolder = "";
		switch (moduleType) {
		case APPICATION :
			if(version == VERSION_1_2) {
				versionHolder = "1.2";
				break;
			}
			else if(version == VERSION_1_3){
				versionHolder = "1.3";
				break;
			}
			else {
				versionHolder = "1.4";
				break;
			}
		case APP_CLIENT :
			if(version == VERSION_1_2) {
				versionHolder = "1.2";
				break;
			}
			else if(version == VERSION_1_3) {
				versionHolder = "1.3";
				break;
			}
			else {
				versionHolder = "1.4";
				break;
			}
		case EJB :
			if(version == VERSION_1_2) {
				versionHolder = "1.1";
				break;
			}
			else if(version == VERSION_1_3) {
				versionHolder = "2.0";
				break;
			}
			else {
				versionHolder = "2.1";
				break;
			}
		case CONNECTOR :
			if(version == VERSION_1_2) {
				versionHolder = "1.0";
				break;
			}
			else if(version == VERSION_1_3) {
				versionHolder = "1.0";
				break;
			}
			else { 
				versionHolder = "1.5";
				break;
			}
		case WEB :
			if(version == VERSION_1_2) {
				versionHolder = "2.2";
				break;
			}
			else if(version == VERSION_1_3) {
				versionHolder = "2.3";
				break;
			}
			else { 
				versionHolder = "2.4";
				break;
			}
		}
		
		return versionHolder;
    }

    protected static Object createParams(EAttributeDescriptor att) {
		if (paramCount == 0){
			paramCount++;
			return null;
		}
		else if (paramCount == 1){
			paramCount++;
			return "";
		}
		String r = "";
		for (int i = 1 ; i < paramCount ;i++){
			r += generateRandomAttributeString(att) + " ";
		}
		paramCount++;
		if (paramCount == 5)
			paramCount = 0;
		return r;
		
	}


	protected static Object createLang(EAttributeDescriptor att) {
		String name = langArray[langCount];
		langCount++;
		if (langCount == 7)
			langCount = 0;
		return name;
	}

	protected static Object createCollection(EAttributeDescriptor att) {
		ArrayList list = new ArrayList();
		list.add(generateRandomAttributeString(att));
		return list;
	}


	protected static Object generateRandomObject(EAttributeDescriptor att) {
	    return generateRandomAttributeString(att);
	}

	protected static Object createEnum(EAttributeDescriptor att) {
		EEnum anEnum = (EEnum) att.getFeature().getEType();
		if (!enumMap.containsKey(att)){
			if (att.getFeature().getName().equals("authMethod"))
				enumMap.put(att,new Integer(1));
			else 
				enumMap.put(att,new Integer(0));
		}
		int pos = ((Integer)enumMap.get(att)).intValue();
		EList literals = anEnum.getELiterals();
		int size = literals.size();
		if (pos == size)
			pos = 0;
		Object holder = anEnum.getEEnumLiteral(pos).getInstance();
		pos++;
		enumMap.put(att,new Integer(pos));
		return holder;
	    
	}
	public static Object generateRandomClassname(EAttributeDescriptor att) {
		return "com.foo." + generateRandomAttributeString(att);
	}

	//REVISIT
    protected static Object generateRandomShort(EAttributeDescriptor att) {
        return new Short(generateRandomAttributeString(att));
    }

    protected static Object generateRandomDouble() {
        return new Double(randomGenerator.nextDouble());
    }
	//REVISIT
    protected static Object generateRandomByte() {
        return new Byte("byte");
    }

    protected static Object generateRandomLong() {
        return new Long(randomGenerator.nextLong());
    }

    protected static Object generateRandomChar(EAttributeDescriptor att) {
        return new Character(generateRandomAttributeString(att).charAt(0));
    }


    protected static Object generateRandomFloat(EAttributeDescriptor att) {
        return new Float(randomGenerator.nextFloat());
    }

    protected static Object generateRandomInteger(EAttributeDescriptor att) {
    	return new Integer(generateNumber(att));
    }
    
    
    protected static Object generateRandomBoolean(EAttributeDescriptor att) {
		if (!booleanMap.containsKey(att)){
			booleanMap.put(att,new Boolean(true));
		}
		Boolean holder = (Boolean)booleanMap.get(att);
		booleanMap.put(att, new Boolean(!holder.booleanValue()));
		return holder;
    }

    public static String generateRandomAttributeString(EAttributeDescriptor att) {
		return att.getFeature().getName() + "_" + generateNumber(att);
	}
	protected static int generateNumber(EAttributeDescriptor att) {
		if(!specialNumberGen)
			return count ++;
		else{
			if(!attIndex.containsKey(att))
				attIndex.put(att, new Integer(0));
			int holder = ((Integer) attIndex.get(att)).intValue();
			attIndex.put(att, new Integer(holder+1));
			return holder;
		}
	}

	public static void reset(){
		count = 0;
		enumMap = new HashMap();
		httpArrayIndex = 0;
		paramCount = 0;
		langCount = 0;  
		booleanMap = new HashMap();
		attIndex = new HashMap();
		specialNumberGen = false;
		TestUtilities.reset();
		J2EEVersionCheck.cur_Tags = null;

	}
}
