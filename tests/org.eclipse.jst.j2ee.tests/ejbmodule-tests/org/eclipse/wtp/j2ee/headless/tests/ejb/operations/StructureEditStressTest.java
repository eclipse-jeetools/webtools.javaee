/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentExportDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.headless.tests.savestrategy.EJBImportOperationTest;
import org.eclipse.wtp.headless.tests.savestrategy.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StructureEditStressTest extends ModuleExportOperationTestCase {  
	
	public StructureEditStressTest(String name) {
		super(name);
	}
	 
	public static Test suite() {
		return new TestSuite(StructureEditStressTest.class);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getExportModel()
	 */
	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new EJBComponentExportDataModelProvider());
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTestCase getImportTestCase() {
		return new EJBImportOperationTest("");
	}
	
	public void testStress() throws Exception {
		File exportDirectory = new File(TESTS_OUTPUT_PATH);
		if (exportDirectory.isDirectory()) {
			File[] contents = exportDirectory.listFiles();
			for (int i = 0; i < contents.length; i++) {
				if (!contents[i].isDirectory())
					contents[i].delete();
			}
		}
		IProject[] projects = getExportableProjects();
		Job[] testJobs = new Job[500];
		for (int i = 0; i < testJobs.length; i++) {
			Job job = new Job("Stress Job " + i)
		      {
		        
		        protected IStatus run(IProgressMonitor monitor)
		        {
		          try
		          {
		            ComponentUtilities.getAllWorkbenchComponents();
		          }
		          catch (Exception e)
		          {
		        	  return Status.CANCEL_STATUS;
		          }
		          return Status.OK_STATUS;
		        }
		      };
			testJobs[i] = job;	
			}
		for (int j = 0; j < testJobs.length; j++) {
			Job job = testJobs[j];
			job.schedule();
		}
		for (int g = 0; g < 1000000; g++){}
	}

	public void testAllExportTestCases() throws Exception {
		
	}

	public void testAllWithExportSourceFilesWithoutOverwriteExisting() throws Exception {
		
	}

	public void testAllWithExportSourceFilesWithOverwriteExisting() throws Exception {
		
	}

	public void testAllWithoutExportSourceFilesWithoutOverwriteExisting() throws Exception {
		
	}

	public void testAllWithoutExportSourceFilesWithOverwriteExisting() throws Exception {
		
	}
	

}
