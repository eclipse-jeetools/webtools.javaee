/**
 * 
 */
package org.eclipse.jst.j2ee.ejb.generation.tests.common;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddSessionBeanOperation;
import org.eclipse.jst.j2ee.ejb.annotation.internal.preferences.AnnotationPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAntProjectBuilder;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author naci
 */
public class EjbModuleCreationTest extends AnnotationTest {
	
	
	public void test01CreateEjbModule() throws Exception {
		this.createEjbModuleAndProject();
		IProject project = (IProject) ResourcesPlugin.getWorkspace().getRoot().findMember(PROJECT_NAME); // Project.ge.getProject(PROJECT_NAME);
		assertNotNull(project);
	}
	
	public void test04TestXDocletBuilder(){
		
		IProject project = (IProject) ResourcesPlugin.getWorkspace().getRoot().findMember(PROJECT_NAME); // Project.ge.getProject(PROJECT_NAME);
		assertNotNull(project);
		IFile bean = project.getFile(new Path(SOURCE_FOLDER+"/com/farm/CowBean.java"));
		assertNotNull(bean);

		IVirtualResource[] vResources = ComponentCore.createResources(bean);
		assertTrue(vResources.length > 0);
		assertNotNull(vResources[0].getComponent());

		XDocletAntProjectBuilder antProjectBuilder = XDocletAntProjectBuilder.Factory.newInstance(bean);
		antProjectBuilder.buildUsingAnt(bean, new NullProgressMonitor());

		IFile bean2 = project.getFile(new Path(SOURCE_FOLDER+"/com/farm/CowUtilities.java"));
		assertNotNull(bean2);
	}	
	public void test02SetXDocletPreferences() throws Exception {
		IProject project = (IProject) ResourcesPlugin.getWorkspace().getRoot().findMember(PROJECT_NAME); // Project.ge.getProject(PROJECT_NAME);
		assertNotNull(project);

		XDocletPreferenceStore store = new XDocletPreferenceStore(project);
		AnnotationPreferenceStore.setProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER, XDOCLET);
		store.setProperty(XDocletPreferenceStore.XDOCLETBUILDERACTIVE, true);
		store.setProperty(XDocletPreferenceStore.XDOCLETUSEGLOBAL,false);
		store.setProperty(XDocletPreferenceStore.XDOCLETVERSION, TestSettings.xdocletversion);
		store.setProperty(XDocletPreferenceStore.XDOCLETHOME, TestSettings.xdocletlocation);
		store.save();
		assertEquals(store.getProperty(XDocletPreferenceStore.XDOCLETHOME),TestSettings.xdocletlocation);
	}
	
	
	public void test03CreateSessionBean() throws Exception {
		IProject project = (IProject) ResourcesPlugin.getWorkspace().getRoot().findMember(PROJECT_NAME); // Project.ge.getProject(PROJECT_NAME);
		assertNotNull(project);
		IDataModel commonDataModel = createDefaultSessionModel();
		AddSessionBeanOperation sessionBeanOperation = new AddSessionBeanOperation(commonDataModel);
		sessionBeanOperation.execute(new NullProgressMonitor(), null);

		IFile bean = project.getFile(new Path(SOURCE_FOLDER+"/com/farm/CowBean.java"));
		assertNotNull(bean);
	}


}
