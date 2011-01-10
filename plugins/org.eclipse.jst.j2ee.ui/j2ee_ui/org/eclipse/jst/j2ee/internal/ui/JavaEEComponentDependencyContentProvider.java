package org.eclipse.jst.j2ee.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants.DependencyAttributeType;
import org.eclipse.jst.j2ee.internal.ui.J2EEModuleDependenciesPropertyPage.ClasspathEntryProxy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.ui.ModuleCoreUIPlugin;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.ComponentDependencyContentProvider;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage;

/**
 * Same as {@link ComponentDependencyContentProvider} except this pulls in
 * references tagged in the classpath to be pulled up by modules with ../
 * 
 * @author jsholl
 * 
 */
public class JavaEEComponentDependencyContentProvider extends ComponentDependencyContentProvider {

	public JavaEEComponentDependencyContentProvider(AddModuleDependenciesPropertiesPage addModuleDependenciesPropertiesPage) {
		super(addModuleDependenciesPropertiesPage);
	}

	public class ConsumedClasspathEntryProxy {
		public IClasspathEntry entry;
		public ConsumedClasspathEntryProxy(IClasspathEntry entry){
			this.entry = entry;
		}
	}
	
	protected List<ClasspathEntryProxy> classpathEntries = null;

	public void setClasspathEntries(List<ClasspathEntryProxy> classpathEntries) {
		this.classpathEntries = classpathEntries;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		@SuppressWarnings("restriction")
		Object[] superElements = super.getElements(inputElement);
		List<Object> elements = new ArrayList<Object>();
		for (Object obj : superElements) {
			elements.add(obj);
		}
		elements.addAll(classpathEntries);
		return elements.toArray();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof ClasspathEntryProxy || element instanceof ConsumedClasspathEntryProxy) {
			IClasspathEntry classpathEntry = ((element instanceof ConsumedClasspathEntryProxy) ? ((ConsumedClasspathEntryProxy)element).entry : ((ClasspathEntryProxy) element).entry);
			if (columnIndex == AddModuleDependenciesPropertiesPage.SOURCE_COLUMN) {
				final IJavaProject jproj = JavaCore.create(getComponent().getProject());
				return AddJavaBuildPathEntriesWizardFragment.getClasspathEntryText(jproj, classpathEntry);
			} else if (columnIndex == AddModuleDependenciesPropertiesPage.DEPLOY_COLUMN) {
				IClasspathAttribute attribute = ClasspathDependencyUtil.checkForComponentDependencyAttribute(classpathEntry, DependencyAttributeType.CLASSPATH_COMPONENT_DEPENDENCY);
				if (attribute != null) {
					IPath runtimePath = new Path(attribute.getValue());
					if(runtimePath.isRoot())
						return runtimePath.toString();
					return runtimePath.makeRelative().toString();
				}
				return null;
			}
		}
		return super.getColumnText(element, columnIndex);
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (element instanceof ClasspathEntryProxy || element instanceof ConsumedClasspathEntryProxy) {
			if (columnIndex == AddModuleDependenciesPropertiesPage.SOURCE_COLUMN) {
				final IJavaProject jproj = JavaCore.create(getComponent().getProject());
				IClasspathEntry classpathEntry = ((element instanceof ConsumedClasspathEntryProxy) ? ((ConsumedClasspathEntryProxy)element).entry : ((ClasspathEntryProxy) element).entry);
				return AddJavaBuildPathEntriesWizardFragment.getClasspathEntryImage(jproj, classpathEntry);
			} else if(columnIndex == AddModuleDependenciesPropertiesPage.DEPLOY_COLUMN) {
				if(element instanceof ConsumedClasspathEntryProxy){
					IClasspathEntry classpathEntry = ((ConsumedClasspathEntryProxy)element).entry;
					if(classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY){
						final IJavaProject jproj = JavaCore.create(getComponent().getProject());
						return AddJavaBuildPathEntriesWizardFragment.getClasspathEntryImage(jproj, classpathEntry);
					}
				}
				return ModuleCoreUIPlugin.getInstance().getImage("folder"); //$NON-NLS-1$
			} 
		}
		return super.getColumnImage(element, columnIndex);
	}

	
	@Override
	public boolean hasChildren(Object element) {
		Object [] children = getChildren(element);
		return children != null && children.length > 0;
	}
	
	@Override
	public Object getParent(Object element) {
		return null;
	}
	
	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof IVirtualReference){
			IVirtualReference ref = (IVirtualReference)parentElement;
			IPath refRuntimePath = ref.getRuntimePath();
			IVirtualComponent comp = ref.getReferencedComponent();
			if(!comp.isBinary()){
				List <IClasspathEntry> originalClasspathEntries = J2EEModuleDependenciesPropertyPage.readRawEntries(comp);
				List <ConsumedClasspathEntryProxy> relativeClasspathEntries = new ArrayList<ConsumedClasspathEntryProxy>();
				for(IClasspathEntry classpathEntry : originalClasspathEntries){
					IClasspathAttribute attribute = ClasspathDependencyUtil.checkForComponentDependencyAttribute(classpathEntry, DependencyAttributeType.CLASSPATH_COMPONENT_DEPENDENCY);
					if (attribute != null) {
						String rawValue = attribute.getValue();
						if(rawValue.startsWith("../")){ //$NON-NLS-1$
							IPath basePath = new Path(rawValue);
							IPath path = refRuntimePath.append(basePath.removeFirstSegments(1));
							if(classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY){
								path = path.append(classpathEntry.getPath().lastSegment());
							}
							IClasspathEntry relativeEntry = ClasspathDependencyUtil.modifyDependencyPath(classpathEntry, path);
							relativeClasspathEntries.add(new ConsumedClasspathEntryProxy(relativeEntry));
						}
					}
				}
				return relativeClasspathEntries.toArray();
			}
		}
		return super.getChildren(parentElement);
	}
}
