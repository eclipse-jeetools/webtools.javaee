/*
 * Created on Apr 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.archive.testutilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author administrator
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public  class TestUtilities extends EcoreUtil {
	private static Map objectMap = new HashMap();
	private static Map subsetMap = new HashMap();

	
	/**
		 * Returns the first collection member that {@link EClassifier#isInstance is an instance} of the type.
		 * @param objects a collection of objects to check.
		 * @param type the type of object to find.
		 * @return the first object of the specified type.
		 */
		public static Object getObjectByType(Resource res, EClassifier type, boolean isMany) {
			if (isMany)
				return getContainedCollectionByType(res.getContents(), type);
			else
				return getContainedObjectByType(res.getContents(), type);
		}

		public static Object getContainedObjectByType(List list, EClassifier type) {
			Object found;
			found = getObjectsByType(list, type);
			if (found != null && !((Collection)found).isEmpty())
				return extractObject((List) found,type);

			for (int i = 0; i < list.size(); i++) {
				EObject obj = (EObject) list.get(i);
				found = getContainedObjectByType(obj.eContents(), type);
				if (found != null)
					return found;
			}

			return null;
		}

		public static Object getContainedCollectionByType(List list, EClassifier type) {
			List found;
			found = (List)getObjectsByType(list, type);
			if (found != null && !found.isEmpty())
				return extractSubset(found, type);

			for (int i = 0; i < list.size(); i++) {
				EObject obj = (EObject) list.get(i);
				found = (List) getContainedCollectionByType(obj.eContents(), type);
				if (found != null)
					return found;
			}

			return null;
		
		
		
		}
	
		public static List extractSubset(List found, EClassifier type) {
			if (!subsetMap.containsKey(type)){
				subsetMap.put(type,new Integer(1));
			}
			int pos = ((Integer)subsetMap.get(type)).intValue();
		
			if (pos == found.size())
				pos = 1;
			List result = new ArrayList();
			for (int i = 0; i < pos; i++)
				result.add(found.get(i));
			pos++;
			subsetMap.put(type,new Integer(pos));	
		
			return result;
		}
	
		public static Object extractObject(List found, EClassifier type) {
			if (!objectMap.containsKey(type)){
				objectMap.put(type,new Integer(0));
			}
			int pos = ((Integer)objectMap.get(type)).intValue();
		
			if (pos == found.size())
				pos = 0;
			Object holder = found.get(pos);
			pos++;
			objectMap.put(type,new Integer(pos));	
		
			return holder;
		}

	public static void reset(){
		subsetMap.clear();
		objectMap.clear();
	}
}
