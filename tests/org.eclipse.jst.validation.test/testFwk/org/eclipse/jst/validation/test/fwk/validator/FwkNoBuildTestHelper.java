package org.eclipse.jst.validation.test.fwk.validator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.internal.validation.AWorkbenchMOFHelper;
import org.eclipse.wst.validation.internal.core.IFileDelta;
import org.eclipse.wst.validation.internal.operations.WorkbenchFileDelta;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

public class FwkNoBuildTestHelper extends AWorkbenchMOFHelper {
	
	public FwkNoBuildTestHelper() {
		super();
		
		registerModel(FwkTestValidator.ALL_FILES, "loadAllFiles"); //$NON-NLS-1$
		registerModel(FwkTestValidator.JAVAHELPERS, "loadJavaHelpers", new Class[]{IFileDelta.class}); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.validate.IWorkbenchHelper#getTargetObjectName(java.lang.Object)
	 */
	public String getTargetObjectName(Object arg0) {
		// No target objects outside of an IResource are used, therefore no target object names are needed.
		return null;
	}

	public IFileDelta[] loadAllFiles() {
		IProject project = getProject();
		
		final Set files = new HashSet();
		IResourceVisitor visitor = new IResourceVisitor() {
			public boolean visit(IResource res) throws CoreException {
				if(!res.isAccessible()) {
					return false; // if the resource isn't accessible then neither are its children
				}
				
				if((res instanceof IFile) && (res.getFileExtension().equals("java"))) { //$NON-NLS-1$
					WorkbenchFileDelta newFileDelta = new WorkbenchFileDelta(res.getFullPath().toString(), IFileDelta.CHANGED, res);
					files.add(newFileDelta);
				}

				return true; // visit the resource's children as well
			}
		};

		try {
			project.accept(visitor, IResource.DEPTH_INFINITE, true); // true means include phantom resources
		}
		catch(CoreException exc) {
			exc.printStackTrace();
		}
		
		IFileDelta[] result = new IFileDelta[files.size()];
		files.toArray(result);
		return result;
	}
	
	public JavaHelpers loadJavaHelpers(IFileDelta delta) {
		WorkbenchFileDelta fd = (WorkbenchFileDelta)delta;
		IResource result = fd.getResource();
		if(result != null){
			if(result instanceof IFile) {
				return JDTUtility.getJavaHelpers((IFile)result);
			}
			else {
				return null;
			}
		}
		else {
			// This validator validates only .java files, so this
			// must be an instanceof JavaClass.
			return (JavaHelpers)fd.getObject();
		}
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.validate.IWorkbenchHelper#getResource(java.lang.Object)
	 */
	public IResource getResource(Object object) {
		IResource result = super.getResource(object);
		if((result != null) && (result.exists())) {
			return result;
		}
		
		if (object == null) {
			return null;
		}

		if(object instanceof JavaHelpers) {
			// If the message was added to a JavaHelpers, this method will be called.
			return JDTUtility.getResource(getProject(), (JavaHelpers) object);
		}
		else if(object instanceof WorkbenchFileDelta) {
			// If the workbench is removing messages from files, and the object is set
			// instead of the IResource, need to process the delta's object instead of
			// its resource.
			return JDTUtility.getResource(getProject(), (JavaHelpers)((WorkbenchFileDelta)object).getObject());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.validate.AWorkbenchHelper#getLineNo(java.lang.Object)
	 */
	public int getLineNo(Object object) {
		int lineNo = super.getLineNo(object);
		if(lineNo == IMessage.LINENO_UNSET) {
			// No adapters, so fudge the line number by adding the message to the first line in the file.
			return 1;
		}
		return lineNo;
	}

}
