package org.eclipse.wtp.j2ee.headless.tests.exportmodel;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.flat.FilterResourceParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;

public class ExportModelTest extends TestCase {
    public void testSimpleExportModel() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("aEAR", null, null, null, JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    	IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject("aEAR");
    	assertTrue(p.exists());
    	runVerify(p);
    	assertEquals(2, countRecurse(p));
    	p.getFolder("garbage").create(true, true, null);
    	runVerify(p);
    	assertEquals(2, countRecurse(p));
    	IFile f = p.getFile(new Path("garbage/file.txt"));
    	f.create(new ByteArrayInputStream("hello".getBytes()), true, null);
    	runVerify(p);
    	assertEquals(2, countRecurse(p));
    	IFile f2 = p.getFile(new Path("EarContent/file.txt"));
    	f2.create(new ByteArrayInputStream("hello2".getBytes()), true, null);
    	runVerify(p);
    	assertEquals(3, countRecurse(p));
    	addMapping(p, new Path("/garbage"), new Path("some/output"));
    	assertEquals(6, countRecurse(p)); // (added some, some/output, and some/output/file.txt)
    	p.delete(true, null);
    }
    
    public void testConsumesExportModel() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("bEAR", null, null, null, JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    	IProject ear = ResourcesPlugin.getWorkspace().getRoot().getProject("bEAR");
    	assertTrue(ear.exists());
    	runVerify(ear);
    	assertEquals(2, countRecurse(ear));
    	IVirtualComponent earComponent = ComponentCore.createComponent(ear);
    	IProject pojp = ResourcesPlugin.getWorkspace().getRoot().getProject("pojp");
    	pojp.create(null);
    	assertTrue(pojp.exists());
    	pojp.open(null);
    	IFile pojpTxt = pojp.getFile(new Path("pojp.txt"));
    	pojpTxt.create(new ByteArrayInputStream("pojp".getBytes()), true, null);
    	assertTrue(pojpTxt.exists());
    	assertEquals(2, countRecurse(ear));
    	TestExportVirtualComponent pojpComponent = new TestExportVirtualComponent(pojpTxt);
    	addReference(earComponent, pojpComponent, "path/to", "");
    	assertEquals(5, countRecurse(ear)); // META-INF, META-INF/application.xml, path, path/to, path/to/pojp.txt
    	ear.delete(true, null);
    	pojp.delete(true, null);
    }
    
	protected void addReference(IVirtualComponent parent, IVirtualComponent child, 
			String path, String archiveName) throws CoreException {
		IDataModelProvider provider = new CreateReferenceComponentsDataModelProvider();
		IDataModel dm = DataModelFactory.createDataModel(provider);
		
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, parent);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Arrays.asList(child));
		
		//[Bug 238264] the uri map needs to be manually set correctly
		Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
		uriMap.put(child, archiveName);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, path);

		IStatus stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		if (!stat.isOK())
			throw new CoreException(stat);
		try {
			dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
		}	
	}
    
    public void testSuffixFilterExportModel() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("aEAR", null, null, null, JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    	IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject("aEAR");
    	assertTrue(p.exists());
    	runVerify(p);
    	assertEquals(2, countRecurse(p));
    	IFile f = p.getFile(new Path("EarContent/file.txt"));
    	f.create(new ByteArrayInputStream("hello".getBytes()), true, null);
    	IFile f2 = p.getFile(new Path("EarContent/file.java"));
    	f2.create(new ByteArrayInputStream("hello2".getBytes()), true, null);
    	runVerify(p);
    	assertEquals(4, countRecurse(p));
    	assertEquals(4, countRecurse(p, FilterResourceParticipant.createSuffixFilterParticipant(new String[]{})));
    	assertEquals(3, countRecurse(p, FilterResourceParticipant.createSuffixFilterParticipant(new String[]{".txt"})));
    	assertEquals(3, countRecurse(p, FilterResourceParticipant.createSuffixFilterParticipant(new String[]{".java"})));
    	assertEquals(2, countRecurse(p, FilterResourceParticipant.createSuffixFilterParticipant(new String[]{".java", ".txt"})));
    	assertEquals(1, countRecurse(p, FilterResourceParticipant.createSuffixFilterParticipant(new String[]{".java", ".txt", ".xml"})));
    }
    
    protected static void runVerify(IProject p) throws Exception {
    	IVirtualComponent component = ComponentCore.createComponent(p);
    	assertTrue(component.exists());
    	IFlatVirtualComponent model = new FlatVirtualComponent(component);
    	IFlatResource[] resources = model.fetchResources();
    	ComponentResource[] mappings = getMappings(p);
    	verifyMappingsAndOutput(p, resources, mappings);
    }
    
    protected static int countRecurse(IProject p) throws Exception {
    	IVirtualComponent component = ComponentCore.createComponent(p);
    	assertTrue(component.exists());
    	IFlatVirtualComponent model = new FlatVirtualComponent(component);
    	IFlatResource[] resources = model.fetchResources();
    	return countRecurse(resources);
    }
    
    protected static int countRecurse(IProject p, IFlattenParticipant participant) throws Exception {
    	IVirtualComponent component = ComponentCore.createComponent(p);
    	assertTrue(component.exists());
    	FlatComponentTaskModel tm = new FlatComponentTaskModel();
    	tm.put(IFlatVirtualComponent.PARTICIPANT_LIST, participant);
    	IFlatVirtualComponent model = new FlatVirtualComponent(component, tm);
    	IFlatResource[] resources = model.fetchResources();
    	return countRecurse(resources);
    }
    
    
    
    protected static int countRecurse(IFlatResource[] resources) {
    	int count = 0;
    	for( int i = 0; i < resources.length; i++ ) {
    		if( resources[i] != null ) {
    			count++;
    			if( resources[i] instanceof IFlatFolder)
    				count += countRecurse(((IFlatFolder)resources[i]).members());
    		}
    	}
    	return count;
    }
    
    protected static void verifyMappingsAndOutput(IProject p, IFlatResource[] resources, ComponentResource[] mappings) {
    	for( int i = 0; i < resources.length; i++ ) {
    		IPath deployPath = resources[i].getModuleRelativePath().append(resources[i].getName());
    		IResource workspaceResource = (IResource)resources[i].getAdapter(IResource.class);
    		IPath workspacePath = workspaceResource.getFullPath();
    		assertNotNull(workspaceResource);
    		boolean found = false;
    		for( int j = 0; j < mappings.length && !found; j++ ) {
    			IPath tmp = new Path(p.getName()).makeAbsolute().append(mappings[j].getSourcePath()).append(deployPath);
    			found |= (tmp.equals(workspacePath));
    		}
    		assertTrue(found);
    		if( resources[i] instanceof IFlatFolder)
    			verifyMappingsAndOutput(p, ((IFlatFolder)resources[i]).members(), mappings);
    	}
    }
    
    protected static ComponentResource[] getMappings(IProject project) {
		StructureEdit structureEdit = null;
		structureEdit = StructureEdit.getStructureEditForRead(project);
		WorkbenchComponent component = structureEdit.getComponent();
		EList list = component.getResources();
		List<ComponentResource> newList = new ArrayList<ComponentResource>();
		for(Iterator i = list.iterator(); i.hasNext();) {
			newList.add((ComponentResource)i.next());
		}
		return (ComponentResource[]) newList.toArray(new ComponentResource[newList
				.size()]);
    }
    
    protected static void addMapping(IProject project, IPath src, IPath runtime) throws CoreException {
    	IVirtualComponent vc = ComponentCore.createComponent(project);
    	IVirtualFolder vf = vc.getRootFolder();
    	vf.getFolder(runtime).createLink(src, 0, null);
    }
}
