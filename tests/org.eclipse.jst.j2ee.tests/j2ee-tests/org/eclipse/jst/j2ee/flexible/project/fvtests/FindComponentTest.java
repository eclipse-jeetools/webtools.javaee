/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.impl.ResourceTreeNode;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author blancett
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class FindComponentTest extends AbstractModuleCreationTest {
	
    public static Test suite() {
        return new SimpleTestSuite(FindComponentTest.class);
    }
    	
	
	public void testWebModuleCreation() throws Exception {
		setupWebModule();
		StructureEdit edit = null;
		IVirtualComponent comp = ComponentUtilities.getComponent("FirstWebModule");
		ComponentResource[] comp1,comp2;
		IProject project = comp.getProject();
		IPath goodFile = new Path("FirstWebModule/WebContent/test1");
		IPath anotherGoodFile = new Path("FirstWebModule/WebContent/wow/test1");
		IPath anotherGoodFile2 = new Path("FirstWebModule/WebContent/wow/test1");
		IPath badFile = new Path("SecondWebModule/test1");
		IPath anotherBadFile = new Path("FirstWebModule/MyWebContent/wow");
		try{ 
		edit = StructureEdit.getStructureEditForRead(project);
		Assert.assertTrue(edit.findComponent(goodFile,ResourceTreeNode.CREATE_NONE) != null);
		Assert.assertTrue(edit.findComponent(anotherGoodFile,ResourceTreeNode.CREATE_NONE) != null);
		Assert.assertTrue(edit.findComponent(anotherGoodFile2,ResourceTreeNode.CREATE_NONE) != null);
		Assert.assertFalse(edit.findComponent(badFile,ResourceTreeNode.CREATE_NONE) != null);
		Assert.assertFalse(edit.findComponent(anotherBadFile,ResourceTreeNode.CREATE_NONE) != null);
		} finally {
			if (edit != null)
				edit.dispose();
		}
		}
	}

}
