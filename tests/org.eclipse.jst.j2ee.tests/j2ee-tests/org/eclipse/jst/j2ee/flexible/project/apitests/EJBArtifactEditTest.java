package org.eclipse.jst.j2ee.flexible.project.apitests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.modulecore.util.EJBArtifactEdit;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.UnresolveableURIException;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;

public class EJBArtifactEditTest extends TestCase {

    public void test_EJBArtifactEdit() {

        ArtifactEditModel model = null;
        EJBArtifactEdit tmpEJBArtifactEdit = new EJBArtifactEdit(model);
        Assert.assertNotNull(tmpEJBArtifactEdit);
    }

    public void test_EJBArtifactEdit_2() {

        ModuleCoreNature aNature = null;
        WorkbenchComponent aModule = null;
        boolean toAccessAsReadOnly = false;
        EJBArtifactEdit tmpEJBArtifactEdit = new EJBArtifactEdit(aNature,
                aModule, toAccessAsReadOnly);
        Assert.assertNotNull(tmpEJBArtifactEdit);
    }

    public void test_getEJBJarXmiResource() {

        ModuleCoreNature aNature = null;
        WorkbenchComponent aModule = null;
        boolean toAccessAsReadOnly = false;
        EJBArtifactEdit objEJBArtifactEdit = new EJBArtifactEdit(aNature,
                aModule, toAccessAsReadOnly);
        EJBResource retValue = null;
        retValue = objEJBArtifactEdit.getEJBJarXmiResource();
        Assert.assertNotNull(retValue);
        
    }

    public void test_getJ2EEVersion() {

        ModuleCoreNature aNature = null;
        WorkbenchComponent aModule = null;
        boolean toAccessAsReadOnly = false;
        EJBArtifactEdit objEJBArtifactEdit = new EJBArtifactEdit(aNature,aModule, toAccessAsReadOnly);
        int retValue = 0;
        retValue = objEJBArtifactEdit.getJ2EEVersion();
        if(retValue == -1)
        	Assert.assertFalse("Incorrect EJB Module version",false);
    }

    public void test_getDeploymentDescriptorResource() {
        ModuleCoreNature aNature = null;
        WorkbenchComponent aModule = null;
        boolean toAccessAsReadOnly = false;
        EJBArtifactEdit objEJBArtifactEdit = new EJBArtifactEdit(aNature,
                aModule, toAccessAsReadOnly);
        Resource retValue = null;
        retValue = objEJBArtifactEdit.getDeploymentDescriptorResource();
        Assert.assertNotNull(retValue);
    }

    public void test_getEJBJar() {

        ModuleCoreNature aNature = null;
        WorkbenchComponent aModule = null;
        boolean toAccessAsReadOnly = false;
        EJBArtifactEdit objEJBArtifactEdit = new EJBArtifactEdit(aNature,
                aModule, toAccessAsReadOnly);
        EJBJar retValue = null;
        retValue = objEJBArtifactEdit.getEJBJar();
        Assert.assertNotNull(retValue);
    }

    public void test_getDeploymentDescriptorRoot() {

        ModuleCoreNature aNature = null;
        WorkbenchComponent aModule = null;
        boolean toAccessAsReadOnly = false;
        EJBArtifactEdit objEJBArtifactEdit = new EJBArtifactEdit(aNature,
                aModule, toAccessAsReadOnly);
        EObject retValue = null;
        retValue = objEJBArtifactEdit.getDeploymentDescriptorRoot();
        Assert.assertNotNull(retValue);
    }

    public void test_getEJBArtifactEditForRead() {
	    EJBArtifactEdit retValue = null;
	    try{
	        WorkbenchComponent aModule = null;
	        retValue = null;
	        retValue = EJBArtifactEdit.getEJBArtifactEditForRead(aModule);
	        Assert.assertNotNull(retValue);
	    } finally {
	    	 retValue.dispose();
	    }
    }

    public void test_getEJBArtifactEditForWrite() {
    EJBArtifactEdit retValue = null;
    try {
        WorkbenchComponent aModule = null;
        retValue = null;
        retValue = EJBArtifactEdit.getEJBArtifactEditForWrite(aModule);
        Assert.assertNotNull(retValue);
    } finally {
    	 retValue.dispose();
    }
        
    }

    public void test_isValidEJBModule() throws UnresolveableURIException {
        WorkbenchComponent aModule = null;
        boolean retValue = false;
        retValue = EJBArtifactEdit.isValidEJBModule(aModule);
        Assert.assertTrue(retValue);
    }

}
