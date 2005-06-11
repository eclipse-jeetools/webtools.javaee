/**
 * 
 */
package org.eclipse.jst.j2ee.ejb.generation.tests.common;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EnterpriseBeanClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddSessionBeanOperation;
import org.eclipse.jst.j2ee.ejb.annotation.internal.preferences.AnnotationPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAntProjectBuilder;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * @author naci
 *
 */
public class EjbModuleCreationTest extends AnnotationTest {
	
	public void testEjbModuleCreation() throws Exception
	{
		this.createEjbModuleAndProject();
		AnnotationPreferenceStore.setProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER,XDOCLET);
		XDocletPreferenceStore.setPropertyActive(XDocletPreferenceStore.XDOCLETBUILDERACTIVE,true);
		XDocletPreferenceStore.setProperty(XDocletPreferenceStore.XDOCLETVERSION, TestSettings.xdocletversion);
		XDocletPreferenceStore.setProperty(XDocletPreferenceStore.XDOCLETHOME,TestSettings.xdocletlocation);
		EnterpriseBeanClassDataModel commonDataModel = createDefaultSessionModel();
		AddSessionBeanOperation sessionBeanOperation = new AddSessionBeanOperation(commonDataModel);
		sessionBeanOperation.doRun(new NullProgressMonitor());
		IProject project = 	null ; //Project.getProject(PROJECT_NAME);
		IFile bean = project.getFile(new Path("/zoo/ejbModule/com/farm/CowBean.java"));
		StructureEdit moduleCore = null;

		try {
			IVirtualResource[] vResources = ComponentCore.createResources(bean);
			System.out.print(vResources[0].getComponent());
			if( vResources.length == 0)
				System.out.print("Cannot find bean");
			
		} finally {
			if (moduleCore!=null)
				moduleCore.dispose();
		}		
	
		XDocletAntProjectBuilder antProjectBuilder = XDocletAntProjectBuilder.Factory.newInstance(bean);
		antProjectBuilder.buildUsingAnt(bean,new NullProgressMonitor());
	}
	
	public void testSessionBeanCreation()
	{
		
	}

}
