/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.deployables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.internal.deployables.LooseArchiveDeployable;
import org.eclipse.jst.j2ee.internal.deployables.LooseArchiveDeployableFactory;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.jst.server.core.ILooseArchive;
import org.eclipse.jst.server.core.ILooseArchiveSupport;
import org.eclipse.jst.server.core.IWebModule;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleType;
import org.eclipse.wst.web.internal.operation.ILibModule;

/**
 * @version 1.0
 * @author
 */
public class J2EEFlexProjWebDeployable extends J2EEFlexProjDeployable implements IWebModule, ILooseArchiveSupport, IModuleType {
    protected String contextRoot;

    protected ILooseArchive[] archives;
    private IPath rootfolder = null;

    protected Map uris = new HashMap();

    

    /**
     * @param aNature
     * @param aFactoryId
     */
    public J2EEFlexProjWebDeployable(IProject project, String aFactoryId, WorkbenchModule aWorkbenchModule) {
        super(project, aFactoryId, aWorkbenchModule);
        this.contextRoot = getUncachedContextRoot();
    }
    
	public String getId() {
		return getProject().getName();
	}

    public String getContextRoot() {
        return wbModule.getDeployedName();
    }

 

    public String getUncachedContextRoot() {
    	//return getWebNature().getContextRoot();
    	
    	return project.getName();
		
     }    
	       
	       


    public String getJ2EESpecificationVersion() {
    	String Version = "1.2"; //$NON-NLS-1$

            WebArtifactEdit webEdit = null;
           	try{
           		webEdit = WebArtifactEdit.getWebArtifactEditForRead( wbModule );
           		if(webEdit != null) {
               		int nVersion = webEdit.getJ2EEVersion();	
               		switch( nVersion ){
	    	    		case 12:
	    	    			Version = IModuleConstants.J2EE_VERSION_1_2;	    			
	    	    			break;
	    	    		case 13:
	    	    			Version = IModuleConstants.J2EE_VERSION_1_3;
	    	    			break;
	    	    		case 14:	
	    	    			Version = IModuleConstants.J2EE_VERSION_1_4;
	    	    			break;
	    	    		default:
	    	    			Version = IModuleConstants.J2EE_VERSION_1_2;
	    	    			break;               			
               		}
           		}
           	}
           	catch(Exception e){
                e.printStackTrace();
           	} finally {
           		if(webEdit != null)
           			webEdit.dispose();
           	}
  	    
        return Version;
    }

    public String getJSPFileMapping(String jspFile) {
        return null;
    }

    private int getServletVersion(){
        WebArtifactEdit webEdit = null;
        int nVersion = 22;
       	try{
       		webEdit = WebArtifactEdit.getWebArtifactEditForRead( wbModule );
       		if(webEdit != null) {
       			nVersion = webEdit.getServletVersion();
       		}
       	}	
      	catch(Exception e){
            e.printStackTrace();
       	} finally {
       		if(webEdit != null)
       			webEdit.dispose();
       	}       
       	return nVersion;
    }
    
    public String getJSPSpecificationVersion() {

    	String ret = "1.2"; //$NON-NLS-1$
    	int nVersion = getServletVersion();
       	switch( nVersion ){
    	
    		case 22:
    			ret = IModuleConstants.JSP_VERSION_1_1;
    			break;
    		case 23:
    			ret = IModuleConstants.JSP_VERSION_1_2;
    			break;
    		case 24:	
    			ret = IModuleConstants.JSP_VERSION_2_0;
    			break;
      		default:
    			ret = IModuleConstants.JSP_VERSION_1_1;
    			break;    			
    	}
    	return ret; 
    }

    public String getServletSpecificationVersion() {

    	String ret = "2.3"; //$NON-NLS-1$
    	int nVersion = getServletVersion();
    	switch( nVersion ){
    	
    		case 22:
    			ret = IModuleConstants.SERVLET_VERSION_2_2;
    			break;
    		case 23:
    			ret = IModuleConstants.SERVLET_VERSION_2_3;
    			break;
    		case 24:	
    			ret = IModuleConstants.SERVLET_VERSION_2_4;
    			break;
    		default:
    			ret = IModuleConstants.SERVLET_VERSION_2_3;
    			break;
    	}
    	return ret;    	
    }

    
    public String getServletMapping(String className) {
        return null;
    }


    public boolean isPublishRequired() {
        return false;
    }

    protected LooseArchiveDeployableFactory getLooseArchiveDeployableFactory() {
        /*
         * Iterator factories =
         * Arrays.asList(ServerCore.getModuleFactories()).iterator(); while
         * (factories.hasNext()) { ModuleFactory deployableFactory =
         * (ModuleFactory) factories.next(); ModuleFactoryDelegate
         * deployableFactoryDelegate = deployableFactory.getDelegate(); if
         * (deployableFactoryDelegate instanceof LooseArchiveDeployableFactory)
         * return (LooseArchiveDeployableFactory) deployableFactoryDelegate; }
         */
        return null;
    }

    protected ILooseArchive getArchiveDeployable(IProject aProject, LooseArchiveDeployableFactory fact) {
        return (ILooseArchive) fact.getModuleProject(aProject);
    }

    protected ILibModule[] getLibModules() {
    	return null;	
    }

    public ILooseArchive[] getLooseArchives() {
        return this.archives;
    }

    /*
     * @see com.ibm.etools.server.core.util.DeployableProject#getRootFolder()
     */
    


    public ILooseArchive[] getUncachedLooseArchives() {
        ILibModule[] libModules = getLibModules();
        if (libModules == null)
            return null;

        LooseArchiveDeployableFactory fact = getLooseArchiveDeployableFactory();
        if (fact == null)
            return null;

        List arcs = new ArrayList(libModules.length);
        for (int i = 0; i < libModules.length; i++) {
            ILibModule libModule = libModules[i];
            IProject proj = libModule.getProject();
            if (proj != null && proj.exists())
                arcs.add(getArchiveDeployable(proj, fact));
        }
        ILooseArchive[] result = new ILooseArchive[arcs.size()];
        arcs.toArray(result);
        return result;
    }

    public String getURI(ILooseArchive jar) {
        try {
            return (String) this.uris.get(jar);
        } catch (Exception e) {
        		// ignore
        }
        return null;
    }

    public String getUncachedURI(ILooseArchive jar) {
        if (!(jar instanceof LooseArchiveDeployable))
            return null;

        LooseArchiveDeployable dep = (LooseArchiveDeployable) jar;
        IProject proj = dep.getProject();
        return getURI(proj);
    }

    protected String getURI(IProject looseJARProject) {
        return null;
    }

    public boolean isBinary() {
        return false;
    }

    protected void update() {
        ILooseArchive[] oldArchives = this.archives;
        this.archives = getUncachedLooseArchives();
        if (this.archives == null)
            this.archives = new ILooseArchive[0];
        String oldContextRoot = this.contextRoot;
        this.contextRoot = getUncachedContextRoot();

        boolean changed = false;
        if (oldContextRoot == null && this.contextRoot != null)
            changed = true;
        else if (oldContextRoot != null && !oldContextRoot.equals(this.contextRoot))
            changed = true;

        // fire remove events
        List add = new ArrayList(2);
        addRemovedObjects(add, oldArchives, this.archives);

        // fire add events
        List remove = new ArrayList(2);
        addAddedObjects(remove, oldArchives, this.archives);

        // fire change events
        int size = this.archives.length;
        List change = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String newURI = getUncachedURI(this.archives[i]);
            String oldURI = getURI(this.archives[i]);

            if (oldURI != null && !oldURI.equals(newURI)) {
                change.add(this.archives[i]);
            }
            this.uris.put(this.archives[i], newURI);
        }

        if (!add.isEmpty() || !remove.isEmpty() || !change.isEmpty() || changed) {
            IModule[] added = new IModule[add.size()];
            add.toArray(added);
            IModule[] changed2 = new IModule[change.size()];
            change.toArray(changed2);
            IModule[] removed = new IModule[remove.size()];
            remove.toArray(removed);
            fireModuleChangeEvent(changed, added, changed2, removed);
        }
    }

    /**
     * Return the objects that have been added between array a and array b.
     * Assumes that there are no null objects in the array.
     */
    protected static void addAddedObjects(List list, Object[] a, Object[] b) {
        if (b == null)
            return;
        else if (a == null) {
            int size = b.length;
            for (int i = 0; i < size; i++)
                list.add(b[i]);
            return;
        }
        int size = b.length;
        for (int i = 0; i < size; i++) {
            Object obj = b[i];
            boolean found = false;
            if (a != null) {
                int size2 = a.length;
                for (int j = 0; !found && j < size2; j++) {
                    if (obj != null && obj.equals(a[j]))
                        found = true;
                }
            }
            if (!found)
                list.add(obj);
        }
    }

    /**
     * Return the objects that have been removed between array a and array b.
     * Assumes that there are no null objects in the array.
     */
    protected static void addRemovedObjects(List list, Object[] a, Object[] b) {
        if (a == null)
            return;
        else if (b == null) {
            int size = a.length;
            for (int i = 0; i < size; i++)
                list.add(a[i]);
            return;
        }
        int size = a.length;
        for (int i = 0; i < size; i++) {
            Object obj = a[i];
            boolean found = false;
            if (b != null) {
                int size2 = b.length;
                for (int j = 0; !found && j < size2; j++) {
                    if (obj != null && obj.equals(b[j]))
                        found = true;
                }
            }
            if (!found)
                list.add(obj);
        }
    }

    public String getType() {
        return "j2ee.web"; //$NON-NLS-1$
    }

    public String getVersion() {

        return "1.2"; //$NON-NLS-1$
    }

    /**
     * Returns the child modules of this module.
     * 
     * @return com.ibm.wtp.server.core.model.IModule[]
     */
    public IModule[] getChildModules() {
        List list = new ArrayList();

        if (this.archives != null) {
            int size = this.archives.length;
            for (int i = 0; i < size; i++)
                list.add(this.archives[i]);
        }

        IModule[] children = new IModule[list.size()];
        list.toArray(children);
        return children;
    }

   
    public String getModuleTypeName(){
        return getName();
    }
    
    public String getModuleTypeVersion(){
        return getVersion();
    }

	public IPath getRootfolder() {
//	    if (ModuleCoreNature.getModuleCoreNature(project) != null ) {  
//			if (wbModule != null ) {   		
//				IFolder outputContainer = ModuleCore.getOutputContainerRoot(wbModule);
//				IPath path = outputContainer.getProjectRelativePath();
//			}
//		}    
		return rootfolder;
	}

}