/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.IModelProviderListener;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider;
import org.eclipse.jst.jee.model.internal.common.ManyToOneRelation;
import org.eclipse.jst.jee.model.internal.common.Result;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;

/**
 * This class provides a javaee ejb EMF model builded from the *.java files
 * located in the project, over which the instance is working on.
 * 
 * The project is passed in the constructor
 * {@link #EJBAnnotationReader(IFacetedProject, IProject))}.
 * 
 * The loaded model is returned from {@link #getEJBJar()}.
 * 
 * Model changes can occur if a java file in the project is changed. When
 * resources are changed, EJBAnnotationReader will rebuild is model. The current
 * implementation is doing a best try to minimize the number of resource change
 * events from the platform that cause the model to be rebuild.
 * 
 * A listener is notified for changes in the model. The notification of the
 * listener may occur in the same or in another thread from the one changing the
 * resource.
 * 
 * Listeners can be registered to #EJBAnnotationReader with
 * {@link #addListener(IModelProviderListener)}.
 * 
 * <p>
 * The current implementation of EJBAnnotationReader is listening for
 * {@link IResourceChangeEvent#POST_BUILD}. This is so since if the java files
 * can not be compiled and build, I make not assumption a valid model can be
 * created from them.
 * 
 * More importantly the Java editor is a resource change listener and it is
 * updating the java model when a resource change event is received. If we are
 * listening for {@link IResourceChangeEvent#POST_CHANGE} instead of
 * {@link IResourceChangeEvent#POST_BUILD} EJBAnnotationReader will not work if
 * it receive a resource change event before the Java Editor. The same incorrect
 * behavior may occur if listening for {@link IResourceChangeEvent#POST_CHANGE}
 * and the user copy/pastes over a file from which a model object is created.
 * </p>
 * 
 * <p>
 * The class is maintaining a reference between the bean and the interfaces it
 * depends on. A bean depends on the resources of it business interfaces, of the
 * interfaces of the resource and ejb annotation and probably other. This is an
 * implementation detail. What the EJBAnnotationReader guarantees is that when a
 * file is changed and there is a model object depending on this file the ejbJar
 * will be successfully rebuilded.
 * 
 * For example two bean can implements the same interface
 * <p>
 * <code>class Bean1 implements Interface {}</code>
 * <code>class Bean2 implements Interface {}</code>
 * <code>@Local interface Interface {}</code>
 * 
 * If in this moment Interface is changed to :
 * <code>@Remote interface Interface {}</code>
 * 
 * then Bean1 and Bean2 will be rebuilded and will contain Interface1 in their
 * business remotes list. Not it their business locals list
 * </p>
 * </p>
 * 
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EJBAnnotationReader extends AbstractAnnotationModelProvider<EJBJar> {

	private static final String JAVA_EXTENSION = "java"; //$NON-NLS-1$

	/**
	 * An abstraction of a reference between a model object and an interface.
	 * This class can be used in collections as a key for differing a reference
	 * from a bean to an interface.
	 * 
	 * <p>
	 * One bean can refer to many interfaces. One interface can be referred by
	 * many beans. This leads to a situation of many to many relation which is
	 * model using {@link ManyToOneRelation} with this BeanInterfaceRef class.
	 * Many unique BeanInterfaceRefs can refer to one interface.
	 * </p>
	 * 
	 * One BeanInterfaceRef is considered equal to another BeanInterfaceRef if
	 * they refer to the same "modelObject" with the same "interface" value.
	 * 
	 * @see EJBAnnotationReader#beanRefToResolvedInterface
	 * @author Kiril Mitov k.mitov@sap.com
	 * 
	 */
	private static class BeanInterfaceRef {

		private String interfacee;
		private JavaEEObject modelObject;

		public BeanInterfaceRef(String interfacee, JavaEEObject modelObject) {
			super();
			this.interfacee = interfacee;
			this.modelObject = modelObject;
		}

		public String getInterfacee() {
			return interfacee;
		}

		public JavaEEObject getModelObject() {
			return modelObject;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((interfacee == null) ? 0 : interfacee.hashCode());
			result = prime * result + ((modelObject == null) ? 0 : modelObject.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BeanInterfaceRef other = (BeanInterfaceRef) obj;
			if (interfacee == null) {
				if (other.interfacee != null)
					return false;
			} else if (!interfacee.equals(other.interfacee))
				return false;
			if (modelObject == null) {
				if (other.modelObject != null)
					return false;
			} else if (!modelObject.equals(other.modelObject))
				return false;
			return true;
		}

	}

	private static int instances = 0;

	private IProject clientProject;

	private Map<IFile, JavaEEObject> resourceToModel;

	/**
	 * An instance of {@link ManyToOneRelation}. This instance is used to
	 * maintain the references between a file which stores an interface and the
	 * beans that depend on this interface.
	 * 
	 * Since one bean can refer to many interfaces and one interface can refer
	 * to many beans a many-to-many relation must be modeled. This is done using
	 * {@link BeanInterfaceRef} and {@link ManyToOneRelation}
	 * 
	 * @see BeanInterfaceRef
	 */
	private ManyToOneRelation<BeanInterfaceRef, IFile> beanRefToResolvedInterface;

	private EjbAnnotationFactory annotationFactory;

	/**
	 * Constructs a new EJBAnnotation reader for this faceted project. An
	 * illegal argument if a project with value <code>null</code> is passed.
	 * No loading is done in this constructor. Loading the model is made on
	 * demand when calling {@link #getEJBJar()}.
	 * 
	 * It is possible to specify the additional clientProject for this
	 * ejbProject. A client project can contain part of the java files that make
	 * up the model. In this case EJBAnnotationReader will also listen for
	 * resources changes in the client project.
	 * 
	 * @param project
	 *            the ejb project. Can not be <code>null</code>
	 * @param clientProject
	 *            the client ejb project or <code>null</code> if there is no
	 *            client project.
	 */
	public EJBAnnotationReader(IFacetedProject project, IProject clientProject) {
		super(project);
		this.clientProject = clientProject;
		instances++;
	}

	/**
	 * Loads the model from the related java files.
	 * 
	 * @throws CoreException
	 */
	protected void loadModel() throws CoreException {
		IJavaProject javaProject = JavaCore.create(facetedProject.getProject());
		final Collection<IFile> javaFiles = new HashSet<IFile>();
		for (final IPackageFragmentRoot root : javaProject.getAllPackageFragmentRoots()) {
			visitJavaFiles(javaFiles, root);
		}

		if (clientProject != null) {
			IJavaProject clientProjectJavaView = JavaCore.create(facetedProject.getProject());
			for (final IPackageFragmentRoot root : clientProjectJavaView.getAllPackageFragmentRoots()) {
				visitJavaFiles(javaFiles, root);
			}
		}
		resourceToModel = new HashMap<IFile, JavaEEObject>();
		beanRefToResolvedInterface = new ManyToOneRelation<BeanInterfaceRef, IFile>();
		for (IFile file : javaFiles) {
			Result result = analyzeFileForBean(file);
			if (result == null)
				continue;
			processResult(file, result);
		}
	}

	protected void preLoad() {
		modelObject = EjbFactory.eINSTANCE.createEJBJar();
		modelObject.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		annotationFactory = EjbAnnotationFactory.createFactory();
	}

	private void visitJavaFiles(final Collection<IFile> javaFiles, final IPackageFragmentRoot root)
			throws CoreException {
		if (root.getKind() != IPackageFragmentRoot.K_SOURCE)
			return;
		root.getCorrespondingResource().accept(new IResourceProxyVisitor() {
			public boolean visit(IResourceProxy proxy) throws CoreException {
				if (proxy.getType() == IResource.FILE) {
					if (proxy.getName().endsWith("." + JAVA_EXTENSION)) { //$NON-NLS-1$
						IFile file = (IFile) proxy.requestResource();
						if (root.getJavaProject().isOnClasspath(file))
							javaFiles.add(file);
					}
					return false;
				}
				return true;
			}
		}, IContainer.NONE);

	}

	/**
	 * Process the result from parsing the file. Depending on the result this
	 * might include adding a session bean, message driven bean, securityRole
	 * etc.
	 * 
	 * @see #sessionBeanFound(IFile, SessionBean, Collection)
	 * @see #messageBeanFound(IFile, MessageDrivenBean, Collection)
	 * @see #securityRoleFound(IFile, SecurityRole)
	 * @param file
	 * @param result
	 * @throws JavaModelException
	 */
	private void processResult(IFile file, Result result) throws JavaModelException {
		JavaEEObject modelObject = result.getMainObject();
		if (SessionBean.class.isInstance(modelObject)) {
			sessionBeanFound(file, (SessionBean) modelObject, result.getDependedTypes());
		} else if (MessageDrivenBean.class.isInstance(modelObject)) {
			messageBeanFound(file, (MessageDrivenBean) modelObject, result.getDependedTypes());
		}
		for (JavaEEObject additional : result.getAdditional()) {
			if (SecurityRole.class.isInstance(additional)) {
				securityRoleFound((SessionBean) resourceToModel.get(file), (SecurityRole) additional);
			}
		}
	}

	/**
	 * Analyze this file for a bean. If the file is not a valid java compilation
	 * unit or it does not contain a class the method returns <code>null</code>
	 * 
	 * Only the primary type of the compilation unit is processed.
	 * 
	 * @see EjbAnnotationFactory
	 * @param file
	 *            the file to be processed.
	 * @return result from processing the file
	 * @throws JavaModelException
	 */
	private Result analyzeFileForBean(IFile file) throws JavaModelException {
		org.eclipse.core.runtime.Assert.isTrue(JAVA_EXTENSION.equals(file.getFileExtension()),
				"A file with extension different from \"java\" is analyzed for beans"); //$NON-NLS-1$
		ICompilationUnit compilationUnit = JavaCore.createCompilationUnitFrom(file);
		if (compilationUnit == null)
			return null;
		IType rootType = compilationUnit.findPrimaryType();
		/*
		 * If the compilation unit is not valid and can not be compiled the
		 * rootType may be null. This can happen if a class is define as follows
		 * <code> @Stateless public SomeClass implements SomeInterface{}</code>.
		 * Here the "class" word is missed and the type is not valid.
		 */
		if (rootType == null || !rootType.isClass())
			return null;
		return annotationFactory.createJavaeeObject(rootType);
	}

	/**
	 * A message driven bean was found in the given file. Add this messageDriven
	 * bean to the list of mdbs in the
	 * {@link EnterpriseBeans#getMessageDrivenBeans()}
	 * 
	 * @param file
	 * @param messageBean
	 * @param dependedTypes
	 *            the types on which this mdb depends. This list specifies
	 *            changes on which types should lead to rebuilding the mdb.
	 * @throws JavaModelException
	 */
	private void messageBeanFound(IFile file, MessageDrivenBean messageBean, Collection<IType> dependedTypes)
			throws JavaModelException {
		if (modelObject.getEnterpriseBeans() == null)
			modelObject.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		modelObject.getEnterpriseBeans().getMessageDrivenBeans().add(messageBean);
		connectBeanWithTypes(file, messageBean, dependedTypes);
	}

	/**
	 * A session bean was found in the give file. The session bean is also
	 * depended on the types in dependedTypes. Here "depended" means that if any
	 * of the types in dependedTypes is change the bean will also be rebuilded.
	 * For example a session bean is depended on the types of it local and
	 * remote interfaces.
	 * 
	 * <p>
	 * Since that a bean can depended on more then on files this method also
	 * establish an appropriate connection between the bean and all the files it
	 * depends on. This are the "file" and the files of "dependedTypes".
	 * </p>
	 * 
	 * @see #getFilesFromTypes(Collection)
	 * 
	 * @param file
	 * @param sessionBean
	 * @param dependedTypes
	 * @throws JavaModelException
	 */
	private void sessionBeanFound(IFile file, SessionBean sessionBean, Collection<IType> dependedTypes)
			throws JavaModelException {
		if (modelObject.getEnterpriseBeans() == null)
			modelObject.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		modelObject.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		connectBeanWithTypes(file, sessionBean, dependedTypes);
	}

	/**
	 * 
	 * @param file
	 * @param bean
	 * @param dependedTypes
	 * @throws JavaModelException
	 */
	private void connectBeanWithTypes(IFile file, JavaEEObject bean, Collection<IType> dependedTypes)
			throws JavaModelException {
		resourceToModel.put(file, bean);
		Collection<IFile> files = new HashSet<IFile>(dependedTypes.size());
		for (IType type : dependedTypes) {
			if (type.isBinary() || type.isInterface() == false)
				continue;
			IResource resource = type.getCompilationUnit().getCorrespondingResource();
			if (resource != null && resource.exists())
				beanRefToResolvedInterface.connect(new BeanInterfaceRef(type.getFullyQualifiedName(), bean),
						(IFile) resource);
		}
	}

	/**
	 * Dispose the current instance. The actual dispose may occur in another
	 * thread. Use {@link #addListener(IModelProviderListener)} to register a
	 * listener that will be notified when the instance is disposed. After all
	 * the listeners are notified the list of listeners is cleared.
	 * 
	 */
	public void dispose() {
		Job disposeJob = new Job(Messages.getString("EJBAnnotationReader.DisposeEjbAnnotationReader")) { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				IModelProviderEvent modelEvent = createModelProviderEvent();
				modelEvent.addResource(facetedProject.getProject());
				modelEvent.setEventCode(modelEvent.getEventCode() | IModelProviderEvent.PRE_DISPOSE);
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(EJBAnnotationReader.this);
				resourceToModel = null;
				beanRefToResolvedInterface = null;
				modelObject = null;
				notifyListeners(modelEvent);
				clearListeners();
				return Status.OK_STATUS;
			}

		};
		disposeJob.schedule();
	}

	/**
	 * @param project
	 * @return true if the given project contains resources that are relative to
	 *         the model. This method returns <code>true</code> for the
	 *         ejbProject on which this instance is working a <code>true</code>
	 *         for its client project.
	 */
	protected boolean isProjectRelative(IProject project) {
		if (super.isProjectRelative(project) == true)
			return true;
		else if (clientProject != null && project.equals(clientProject.getProject()))
			return true;
		return false;
	}

	/**
	 * {non-Javadoc}
	 * 
	 * <p>
	 * Changing the model may occur in different threads.
	 * {@link #processAddedFile(IModelProviderEvent, IFile)} is synchronized so
	 * that no other thread can add a file to the model in the same moment.
	 * </p>
	 * 
	 * @see org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider#processAddedFile(org.eclipse.jst.j2ee.model.IModelProviderEvent,
	 *      org.eclipse.core.resources.IFile)
	 */
	protected synchronized void processAddedFile(IModelProviderEvent modelEvent, IFile file) throws CoreException {
		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(file);
		if (unit == null)
			return;
		Collection<IFile> filesToRebuild = new HashSet<IFile>();
		IType rootType = unit.getTypes()[0];
		if (rootType.isClass()) {
			Result result = analyzeFileForBean(file);
			if (result == null)
				return;
			processResult(file, result);
			modelEvent.addResource(file);
		} else if (rootType.isInterface()) {
			String rootTypeSimpleName = rootType.getElementName();
			for (Iterator beanIter = getConcreteModel().getEnterpriseBeans().getSessionBeans().iterator(); beanIter
					.hasNext();) {
				SessionBean bean = (SessionBean) beanIter.next();
				for (Iterator interfaceIter = bean.getBusinessLocals().iterator(); interfaceIter.hasNext();) {
					String inter = (String) interfaceIter.next();
					if (rootTypeSimpleName.equals(inter))
						filesToRebuild.add(getResourceFromModel(bean));
				}
				if (rootTypeSimpleName.equals(bean.getLocalHome()) || rootTypeSimpleName.equals(bean.getHome()))
					filesToRebuild.add(getResourceFromModel(bean));
				findDependedFiles(bean, rootTypeSimpleName, bean.getEjbLocalRefs(), bean.getResourceRefs(),
						filesToRebuild);
			}
			for (Iterator beanIter = getConcreteModel().getEnterpriseBeans().getMessageDrivenBeans().iterator(); beanIter
					.hasNext();) {
				MessageDrivenBean bean = (MessageDrivenBean) beanIter.next();
				findDependedFiles(bean, rootTypeSimpleName, bean.getEjbLocalRefs(), bean.getResourceRefs(),
						filesToRebuild);
			}
		}
		if (filesToRebuild.isEmpty())
			return;
		for (IFile changedFile : filesToRebuild) {
			processRemovedFile(modelEvent, changedFile);
			processAddedFile(modelEvent, changedFile);
		}
		filesToRebuild.clear();
	}

	private IFile getResourceFromModel(JavaEEObject bean) {
		for (Map.Entry<IFile, JavaEEObject> entry : resourceToModel.entrySet()) {
			if (entry.getValue().equals(bean))
				return entry.getKey();
		}
		return null;
	}

	private void findDependedFiles(JavaEEObject bean, String rootTypeSimpleName, Collection<EjbLocalRef> ejbRefs,
			Collection<ResourceRef> resourceRefs, Collection<IFile> filesToRebuild) {
		for (Iterator refsIter = ejbRefs.iterator(); refsIter.hasNext();) {
			String localRefInterface = ((EjbLocalRef) refsIter.next()).getLocal();
			if (rootTypeSimpleName.equals(localRefInterface))
				filesToRebuild.add(getResourceFromModel(bean));
		}
		for (Iterator refsIter = resourceRefs.iterator(); refsIter.hasNext();) {
			String resourceRef = ((ResourceRef) refsIter.next()).getResType();
			if (rootTypeSimpleName.equals(resourceRef)) {
				filesToRebuild.add(getResourceFromModel(bean));
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * <p>
	 * Changing the model may occur in different threads.
	 * {@link #processRemovedFile(IModelProviderEvent, IFile)} is synchronized
	 * so that now other thread can remove a file from the model in the same
	 * moment.
	 * </p>
	 * 
	 * 
	 * @see #processRemoveBean(IModelProviderEvent, IFile)
	 * @see #processRemoveInterface(IModelProviderEvent, IFile)
	 * @see org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider#processRemovedFile(org.eclipse.jst.j2ee.model.IModelProviderEvent,
	 *      org.eclipse.core.resources.IFile)
	 * @param modelEvent
	 * @param file
	 *            the file to be removed.
	 * @throws JavaModelException
	 *             if there was an error during parsing the file
	 */
	protected synchronized void processRemovedFile(IModelProviderEvent modelEvent, IFile file) throws CoreException {
		if (resourceToModel.containsKey(file))
			processRemoveBean(modelEvent, file);
		else if (beanRefToResolvedInterface.containsTarget(file))
			processRemoveInterface(modelEvent, file);

	}

	/**
	 * Process a file as a removed interface. It is the responsibility of the
	 * caller to make sure the file really contains an interface and that this
	 * interface is really removed.
	 * 
	 * Removing an interface will also remove the connections between the
	 * interface and the beans that depend on it -
	 * {@link #beanRefToResolvedInterface}.
	 * 
	 * Removing an interface will result in rebuilding all the modelObjects that
	 * depend on this interface.
	 * 
	 * <p>
	 * The method is not synchronous. It is again a responsibility of the caller
	 * to assure no thread will work on the same model. This is easily assured
	 * by calling this method from a synchronized one such as
	 * {@link #processRemovedFile(IModelProviderEvent, IFile)}
	 * </p>
	 * 
	 * @see #beanRefToResolvedInterface
	 * @see #processRemovedFile(IModelProviderEvent, IFile)
	 * @see #processRemoveBean(IModelProviderEvent, IFile)
	 * @param modelEvent
	 * @param file
	 * @throws JavaModelException
	 */
	private void processRemoveInterface(IModelProviderEvent modelEvent, IFile file) throws CoreException {
		Collection<BeanInterfaceRef> refs = beanRefToResolvedInterface.getSources(file);
		Collection<IFile> filesToRebuild = new HashSet<IFile>();
		for (BeanInterfaceRef ref : refs) {
			filesToRebuild.add(getResourceFromModel(ref.getModelObject()));
		}
		if (filesToRebuild.isEmpty())
			return;
		for (IFile changedFile : filesToRebuild) {
			processRemovedFile(modelEvent, changedFile);
			processAddedFile(modelEvent, changedFile);
		}
		filesToRebuild.clear();
		beanRefToResolvedInterface.disconnect(file);
	}

	/**
	 * Process a file as a removed bean. It is the responsibility of the caller
	 * to make sure the file really contains a bean and that this bean is really
	 * removed.
	 * 
	 * <p>
	 * The method is not synchronous. It is again a responsibility of the caller
	 * to assure no thread will work on the same model. This is easily assured
	 * by calling this method from a synchronized one such as
	 * {@link #processRemovedFile(IModelProviderEvent, IFile)}
	 * </p>
	 * 
	 * @see #processRemovedFile(IModelProviderEvent, IFile)
	 * @see #processRemoveInterface(IModelProviderEvent, IFile)
	 * @see #beanRefToResolvedInterface
	 * @param modelEvent
	 * @param file
	 *            the file containing the removed bean
	 * @throws JavaModelException
	 */
	private void processRemoveBean(IModelProviderEvent modelEvent, IFile file) throws JavaModelException {
		EObject modelObject = (EObject) resourceToModel.get(file);
		EcoreUtil.remove(modelObject);

		resourceToModel.remove(getResourceFromModel((JavaEEObject) modelObject));
		modelEvent.setEventCode(modelEvent.getEventCode() | IModelProviderEvent.REMOVED_RESOURCE);
		modelEvent.addResource(file);
		disconnectFromRoles((JavaEEObject) modelObject);
	}

	/**
	 * Process a file as "changed". If no model object depends on this file the
	 * file will be process as added (since it may not have been a bean till
	 * now, but an annotation was added). It is the responsibility of the caller
	 * to make sure the file really contains a bean/interface and that this bean
	 * is really changed and can be accessed.
	 * 
	 * <p>
	 * Changing the model may occur in different threads.
	 * {@link #processChangedFile(IModelProviderEvent, IFile)} is synchronized
	 * so that now other thread can change a file from the model in the same
	 * moment.
	 * </p>
	 * 
	 * @see #processChangedBean(IModelProviderEvent, IFile)
	 * @see #processChangedInterface(IModelProviderEvent, IFile)
	 * @param modelEvent
	 * @param file
	 * @throws JavaModelException
	 */
	protected synchronized void processChangedFile(IModelProviderEvent modelEvent, IFile file) throws CoreException {
		if (resourceToModel.containsKey(file))
			processChangedBean(modelEvent, file);
		else if (beanRefToResolvedInterface.containsTarget(file))
			processChangedInterface(modelEvent, file);
		else
			processAddedFile(modelEvent, file);
	}

	/**
	 * Process a file as a changed bean. It is the responsibility of the caller
	 * to make sure the file really contains a bean and that this bean is really
	 * changed.
	 * 
	 * <p>
	 * The method is not synchronous. It is again a responsibility of the caller
	 * to assure no thread will work on the same model. This is easily assured
	 * by calling this method from a synchronized one such as
	 * {@link #processChangedFile(IModelProviderEvent, IFile)}
	 * </p>
	 * 
	 * Changing a bean may mean removing it from the model (if it was a bean
	 * till now, but the annotation was deleted)
	 * 
	 * @see #processChangedFile(IModelProviderEvent, IFile)
	 * @see {@link #processChangedInterface(IModelProviderEvent, IFile)}
	 * @param modelEvent
	 * @param file
	 * @throws JavaModelException
	 */
	private void processChangedBean(IModelProviderEvent modelEvent, IFile file) throws CoreException {
		EObject oldBean = (EObject) resourceToModel.get(file);
		IFile beanFile = getResourceFromModel((JavaEEObject) oldBean);
		processRemovedFile(modelEvent, beanFile);
		processAddedFile(modelEvent, beanFile);
	}

	/**
	 * Process a file as a changed interface. It is the responsibility of the
	 * caller to make sure the file really contains an interface and that this
	 * interface is really changed.
	 * 
	 * <p>
	 * The method is not synchronous. It is again a responsibility of the caller
	 * to assure no thread will work on the same model. This is easily assured
	 * by calling this method from a synchronized one such as
	 * {@link #processChangedFile(IModelProviderEvent, IFile)}
	 * </p>
	 * 
	 * Changing an interface may mean rebuilding all the beans that depend on
	 * this interface (if it was annotated with "@Local", but this annotation
	 * was changed to "@Remote" )
	 * 
	 * @param modelEvent
	 * @param file
	 * @throws JavaModelException
	 */
	private void processChangedInterface(IModelProviderEvent modelEvent, IFile file) throws CoreException {
		Collection<BeanInterfaceRef> references = beanRefToResolvedInterface.getSources(file);
		for (BeanInterfaceRef ref : references) {
			IFile next = getResourceFromModel(ref.getModelObject());
			processRemovedFile(modelEvent, next);
			processAddedFile(modelEvent, next);
		}
	}

	public void modify(Runnable runnable, IPath modelPath) {
	}

	public IStatus validateEdit(IPath modelPath, Object context) {
		return null;
	}

	@Override
	protected Collection<SecurityRoleRef> getSecurityRoleRefs(JavaEEObject target) {
		if (SessionBean.class.isInstance(target))
			return ((SessionBean) target).getSecurityRoleRefs();
		return null;
	}

	@Override
	protected Collection<SecurityRole> getSecurityRoles() {
		if (modelObject.getAssemblyDescriptor() == null)
			modelObject.setAssemblyDescriptor(EjbFactory.eINSTANCE.createAssemblyDescriptor());
		return modelObject.getAssemblyDescriptor().getSecurityRoles();
	}

}
