package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientJARCreationConstants;

public class EJBClientJarCreationHelper {
	
	private IProject ejbProject = null;
	protected Map javaFilesToMove = new HashMap();
	protected Set visitedJavaTypes = new HashSet();
	protected int moveResourceCount = 0;
	protected SearchEngine searchEngine = new SearchEngine();
	private MySearchHelper searchHelper = null;	
	
	public EJBClientJarCreationHelper(IProject aEjbProject){
		ejbProject = aEjbProject;
	}
	
	public Map getFilesToMove() {
		
		
	    searchHelper = new MySearchHelper(searchEngine, computeBeanTypeNames());
	

		if( ejbProject.exists() && ejbProject.isAccessible()){
			EJBArtifactEdit edit = null;
			try {
					edit = EJBArtifactEdit.getEJBArtifactEditForRead(ejbProject);
					EJBJar ejbJar = edit.getEJBJar();
					if (ejbJar != null) {
						List enterpriseBeans = ejbJar.getEnterpriseBeans();
						for (int i = 0; i < enterpriseBeans.size(); i++) {
							EnterpriseBean ejb = (EnterpriseBean) enterpriseBeans.get(i);
							computeJavaTypes(ejb);
						}
					}
					computeRMICJavaTypes();					

			} finally {
				if(edit != null)
					edit.dispose();
					  
			}
		}
		return javaFilesToMove;
	}
	
	
	private void computeJavaTypes(EnterpriseBean ejb) {
		computeJavaTypes(ejb.getHomeInterface());
		computeJavaTypes(ejb.getRemoteInterface());
		computeJavaTypes(ejb.getLocalInterface());
		computeJavaTypes(ejb.getLocalHomeInterface());
		if (ejb.isEntity())
			computeJavaTypes(((Entity)ejb).getPrimaryKey());
	}
	
	private void computeJavaTypes(JavaClass javaClass) {
		if (javaClass == null)
			return;
		javaClass.isInterface();//force reflection prior to the move.
		IType type = EJBGenHelpers.getType(javaClass);
		computeJavaTypes(type);
	}

	private void computeJavaTypes(IType type) {
		if (type == null || visitedJavaTypes.contains(type))
			return;
		visitedJavaTypes.add(type);
		String qualifiedName = type.getFullyQualifiedName();
		try {
			IFile file = (IFile)type.getUnderlyingResource();
			if (file != null && ejbProject.equals(file.getProject())) {
				if (!file.isDerived())
					cacheType(type, file);
				computeRequiredReferencedJavaTypes(type);
			}
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
			return;
		} 
	}
	
	private void cacheType(IType type, IFile file) {
		this.moveResourceCount++;
		IPackageFragmentRoot root = (IPackageFragmentRoot) type.getPackageFragment().getParent();
		cacheFile(root, file);
	}
	
	private void cacheFile(IPackageFragmentRoot root, IFile file) {
		Set files = (Set) javaFilesToMove.get(root);
		if (files == null) {
			files = new HashSet();
			javaFilesToMove.put(root, files);
		}
		files.add(file);
	}
	
	private void computeRequiredReferencedJavaTypes(IType type)  throws JavaModelException {
		Collection result = new HashSet();
		searchHelper.searchForReferences(type, result);
		if (!result.isEmpty()) {
			Iterator iter = result.iterator();
			while (iter.hasNext())
				computeJavaTypes(((IType)iter.next()));
		}
	}	
	
	private Collection computeBeanTypeNames() {
		Collection names = null;

		if( ejbProject.exists() && ejbProject.isAccessible()){
			EJBArtifactEdit edit = null;
			try {
					edit = EJBArtifactEdit.getEJBArtifactEditForRead(ejbProject);
					EJBJar ejbJar = edit.getEJBJar();
					if (ejbJar != null) {
						List beans = ejbJar.getEnterpriseBeans();
						if (!beans.isEmpty()) {
							names = new HashSet(beans.size());
							for (int i = 0; i < beans.size(); i++) {
								EnterpriseBean bean = (EnterpriseBean) beans.get(i);
								names.add(bean.getEjbClassName());
							}
						}
					}
				

			} finally {
				if(edit != null)
					edit.dispose();
					  
			}
		}
		if (names == null)
			names = Collections.EMPTY_LIST;
		return names;			
	}
	
	private void computeRMICJavaTypes() {
		
		try{
			List roots = getResourcePackageFragmentRoots();
			for (int i = 0; i < roots.size(); i++) {
				IPackageFragmentRoot root = (IPackageFragmentRoot) roots.get(i);
				computeRMICJavaTypes(root);
			}
		}catch(CoreException e){
			Logger.getLogger().logError(e);
		}
	}
	
	private List getResourcePackageFragmentRoots() throws JavaModelException {
		IJavaProject javaProj = JemProjectUtilities.getJavaProject(ejbProject);
		List result = new ArrayList();
		IPackageFragmentRoot[] roots = javaProj.getPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			IPackageFragmentRoot root = roots[i];
			if (root.getKind() == IPackageFragmentRoot.K_SOURCE || isClassesFolder(root)) 
				result.add(root);
		}
		return result;
	}
	
	private boolean isClassesFolder(IPackageFragmentRoot root) throws JavaModelException {
		return root.getKind() == IPackageFragmentRoot.K_BINARY &&
			!root.isArchive() && !root.isExternal();
	}	
	
	private void computeRMICJavaTypes(IPackageFragmentRoot root) throws JavaModelException {
		IJavaElement[] pkgFragments = root.getChildren();
		IFile aFile = null;
		for (int i = 0; i < pkgFragments.length; i++) {
			IJavaElement[] elements = ((IPackageFragment)pkgFragments[i]).getChildren();
			for (int j = 0; j < elements.length; j++) {
				IJavaElement unit = elements[j];
				aFile = (IFile)unit.getUnderlyingResource();
				if (isRMICStub(aFile) && isFileForClientJar(aFile))
					cacheFile(root, aFile);
			}
		}
	}
	
	private boolean isRMICStub(IFile aFile) {
		String name = aFile.getName();
		String ext = aFile.getFileExtension();

		if (name != null && ext != null)
			name = name.substring(0, name.length()-ext.length()-1); 
		return name != null && 
			name.startsWith(ClientJARCreationConstants.UNDERSCORE) &&
			name.endsWith(ClientJARCreationConstants._STUB);
	}
	
	private boolean isFileForClientJar(IFile aFile){
		String name = aFile.getName();
//		 special case the was stubs  that is needed in the server side
        if(name.startsWith("_EJS") || (name.startsWith("_") && name.indexOf("InternalHome_") > 0 )) //$NON-NLS-1$ //$NON-NLS-2$
        	return false;
        return true;
	}

	
	private static class MySearchHelper extends SearchRequestor  {
		SearchEngine engine;
		Collection results;
		Collection beanTypeNames;
		MySearchHelper(SearchEngine engine, Collection beanTypeNames) {
			this.engine = engine;
			this.beanTypeNames = beanTypeNames;
		}
		void searchForReferences(IType type, Collection results) throws JavaModelException {
			this.results = results;
			//Hack because of a bug in the search engine in eclipse 2.1.1
			try {
				engine.searchDeclarationsOfReferencedTypes(type, this, null);
			} catch (ClassCastException ex) {
				Logger.getLogger().logError(ex);
			}
		}
		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			if(match.getAccuracy() == SearchMatch.A_ACCURATE && !(match.isInsideDocComment() || isBeanType((IType) match.getElement())))
				results.add(match.getElement());
		}
		/**
		 * @param type
		 * @return
		 */
		private boolean isBeanType(IType type) {
			return beanTypeNames.contains(type.getFullyQualifiedName());
		}		
		
	}	
}
