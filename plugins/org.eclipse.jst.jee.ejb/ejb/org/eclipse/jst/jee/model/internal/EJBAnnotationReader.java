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
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElementDelta;
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
 * Model changes can occur if a java file in the project is changed. When JDT
 * model is changed, EJBAnnotationReader will rebuild is model. The current
 * implementation is doing a best try to minimize the number of change events
 * from JDT that cause the model to be rebuild.
 * 
 * A listener is notified for changes in the model. The notification of the
 * listener may occur in the same or in another thread from the one changing the
 * resource.
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
public class EJBAnnotationReader extends AbstractAnnotationModelProvider<EJBJar>{

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

	private IProject clientProject;

	public Map<ICompilationUnit, JavaEEObject> unitToModel;

	/**
	 * An instance of {@link ManyToOneRelation}. This instance is used to
	 * maintain the references between a compilation unit which stores an
	 * interface and the beans that depend on this interface.
	 * 
	 * Since one bean can refer to many interfaces and one interface can refer
	 * to many beans a many-to-many relation must be modeled. This is done using
	 * {@link BeanInterfaceRef} and {@link ManyToOneRelation}
	 * 
	 * @see BeanInterfaceRef
	 */
	private ManyToOneRelation<BeanInterfaceRef, ICompilationUnit> beanRefToResolvedInterfaceUnit;

	private EjbAnnotationFactory annotationFactory;

	/**
	 * Constructs a new EJBAnnotation reader for this faceted project. An
	 * illegal argument if a project with value <code>null</code> is passed. No
	 * loading is done in this constructor. Loading the model is made on demand
	 * when calling {@link #getEJBJar()}.
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
	}

	/**
	 * Loads the model from the related java files.
	 * 
	 * @throws CoreException
	 */
	@Override
	public void loadModel() throws CoreException {
		IJavaProject javaProject = JavaCore.create(facetedProject.getProject());
		final Collection<ICompilationUnit> javaFiles = new HashSet<ICompilationUnit>();
		for (final IPackageFragmentRoot root : javaProject.getAllPackageFragmentRoots()) {
			visitJavaFiles(javaFiles, root);
		}

		if (clientProject != null) {
			IJavaProject clientProjectJavaView = JavaCore.create(facetedProject.getProject());
			for (final IPackageFragmentRoot root : clientProjectJavaView.getAllPackageFragmentRoots()) {
				visitJavaFiles(javaFiles, root);
			}
		}
		unitToModel = new HashMap<ICompilationUnit, JavaEEObject>();
		beanRefToResolvedInterfaceUnit = new ManyToOneRelation<BeanInterfaceRef, ICompilationUnit>();
		for (ICompilationUnit unit : javaFiles) {
			Result result = analyzeUnitForBean(unit);
			if (result == null)
				continue;
			processResult(unit, result);
		}
	}

	@Override
	protected void preLoad() {
		super.preLoad();
		modelObject = EjbFactory.eINSTANCE.createEJBJar();
		annotationFactory = EjbAnnotationFactory.createFactory();
	}
	
	/**
	 * Process the result from parsing the compilation unit. Depending on the
	 * result this might include adding a session bean, message driven bean,
	 * securityRole etc.
	 * 
	 * @see #sessionBeanFound(ICompilationUnit, SessionBean, Collection)
	 * @see #messageBeanFound(ICompilationUnit, MessageDrivenBean, Collection)
	 * @see #securityRoleFound(ICompilationUnit, SecurityRole)
	 * @param unit
	 * @param result
	 * @throws JavaModelException
	 */
	private void processResult(ICompilationUnit unit, Result result) throws JavaModelException {
		JavaEEObject modelObject = result.getMainObject();
		if (SessionBean.class.isInstance(modelObject)) {
			sessionBeanFound(unit, (SessionBean) modelObject, result.getDependedTypes());
		} else if (MessageDrivenBean.class.isInstance(modelObject)) {
			messageBeanFound(unit, (MessageDrivenBean) modelObject, result.getDependedTypes());
		}
		for (JavaEEObject additional : result.getAdditional()) {
			if (SecurityRole.class.isInstance(additional)) {
				securityRoleFound((SessionBean) unitToModel.get(unit), (SecurityRole) additional);
			}
		}
	}

	/**
	 * Analyze this compilation unit for a bean. If the file is not a valid java
	 * compilation unit or it does not contain a class the method returns
	 * <code>null</code>
	 * 
	 * Only the primary type of the compilation unit is processed.
	 * 
	 * @see EjbAnnotationFactory
	 * @param unit
	 *            the file to be processed.
	 * @return result from processing the file
	 * @throws JavaModelException
	 */
	private Result analyzeUnitForBean(ICompilationUnit compilationUnit) throws JavaModelException {
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
	 * A message driven bean was found in the given compilation unit. Add this
	 * messageDriven bean to the list of mdbs in the
	 * {@link EnterpriseBeans#getMessageDrivenBeans()}
	 * 
	 * @param unit
	 * @param messageBean
	 * @param dependedTypes
	 *            the types on which this mdb depends. This list specifies
	 *            changes on which types should lead to rebuilding the mdb.
	 * @throws JavaModelException
	 */
	private void messageBeanFound(ICompilationUnit unit, MessageDrivenBean messageBean, Collection<IType> dependedTypes)
			throws JavaModelException {
		if (modelObject.getEnterpriseBeans() == null)
			modelObject.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		modelObject.getEnterpriseBeans().getMessageDrivenBeans().add(messageBean);
		connectBeanWithTypes(unit, messageBean, dependedTypes);
	}

	/**
	 * A session bean was found in the given compilation unit. The session bean
	 * is also depended on the types in dependedTypes. Here "depended" means
	 * that if any of the types in dependedTypes is change the bean will also be
	 * rebuilded. For example a session bean is depended on the types of it
	 * local and remote interfaces.
	 * 
	 * <p>
	 * Since a bean can depended on more then one compilation unit this method
	 * also establish an appropriate connection between the bean and all the
	 * units it depends on. This are the "unit" and the units of
	 * "dependedTypes".
	 * </p>
	 * 
	 * @see #getFilesFromTypes(Collection)
	 * 
	 * @param unit
	 * @param sessionBean
	 * @param dependedTypes
	 * @throws JavaModelException
	 */
	private void sessionBeanFound(ICompilationUnit unit, SessionBean sessionBean, Collection<IType> dependedTypes)
			throws JavaModelException {
		if (modelObject.getEnterpriseBeans() == null)
			modelObject.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		modelObject.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		connectBeanWithTypes(unit, sessionBean, dependedTypes);
	}

	/**
	 * 
	 * @param unit
	 * @param bean
	 * @param dependedTypes
	 * @throws JavaModelException
	 */
	private void connectBeanWithTypes(ICompilationUnit unit, JavaEEObject bean, Collection<IType> dependedTypes)
			throws JavaModelException {
		unitToModel.put(unit, bean);
		for (IType type : dependedTypes) {
			if (type.isBinary() || type.isInterface() == false)
				continue;
			beanRefToResolvedInterfaceUnit.connect(new BeanInterfaceRef(type.getFullyQualifiedName(), bean), type
					.getCompilationUnit());
		}
	}

	/**
	 * Dispose the current instance. The actual dispose may occur in another
	 * thread. Use {@link #addListener(IModelProviderListener)} to register a
	 * listener that will be notified when the instance is disposed. After all
	 * the listeners are notified the list of listeners is cleared.
	 */
	@Override
	public void dispose() {
		beanRefToResolvedInterfaceUnit = null;
		if (unitToModel != null)
			// bug 249234. If the model is not loaded and dispose is called a
			// NPE occurs.
			unitToModel.clear();
		super.dispose();
	}

	/**
	 * @param javaProject
	 * @return true if the given project contains resources that are relative to
	 *         the model. This method returns <code>true</code> for the
	 *         ejbProject on which this instance is working a <code>true</code>
	 *         for its client project.
	 */
	@Override
	protected boolean isProjectRelative(IJavaProject javaProject) {
		if (super.isProjectRelative(javaProject) == true)
			return true;
		else if (clientProject != null && javaProject.getProject().equals(clientProject.getProject()))
			return true;
		return false;
	}

	/**
	 * {non-Javadoc}
	 * 
	 * @see org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider#processAddedCompilationUnit(org.eclipse.jst.j2ee.model.IModelProviderEvent,
	 *      org.eclipse.core.resources.ICompilationUnit)
	 */
	@Override
	protected void processAddedCompilationUnit(IModelProviderEvent modelEvent, ICompilationUnit unit)
			throws CoreException {
		if (unit == null)
			return;
		Collection<ICompilationUnit> unitsToRebuild = new HashSet<ICompilationUnit>();
		IType rootType = unit.findPrimaryType();
		if (rootType == null)
			return;
		if (rootType.isClass()) {
			Result result = analyzeUnitForBean(unit);
			if (result == null || result.isEmpty())
				return;
			processResult(unit, result);
			modelEvent.addResource(unit);
		} else if (rootType.isInterface()) {
			unitsToRebuild.addAll(processAddedInterface(rootType));
		}
		if (unitsToRebuild.isEmpty())
			return;
		for (ICompilationUnit changedUnit : unitsToRebuild) {
			processRemovedCompilationUnit(modelEvent, changedUnit);
			processAddedCompilationUnit(modelEvent, changedUnit);
		}
		unitsToRebuild.clear();

	}

	private Collection<ICompilationUnit> processAddedInterface(IType rootType) {
		Collection<ICompilationUnit> unitsToRebuild = new HashSet<ICompilationUnit>();
		String rootTypeSimpleName = rootType.getElementName();
		if (getConcreteModel().getEnterpriseBeans() == null)
			return unitsToRebuild;
		
		for (Iterator beanIter = getConcreteModel().getEnterpriseBeans().getSessionBeans().iterator(); beanIter
				.hasNext();) {
			SessionBean bean = (SessionBean) beanIter.next();
			for (Iterator interfaceIter = bean.getBusinessLocals().iterator(); interfaceIter.hasNext();) {
				String inter = (String) interfaceIter.next();
				if (rootTypeSimpleName.equals(inter))
					unitsToRebuild.add(getCompilationUnitFromModel(bean));
			}
			if (rootTypeSimpleName.equals(bean.getLocalHome()) || rootTypeSimpleName.equals(bean.getHome()))
				unitsToRebuild.add(getCompilationUnitFromModel(bean));
			findDependedFiles(bean, rootTypeSimpleName, bean.getEjbLocalRefs(), bean.getResourceRefs(), unitsToRebuild);
		}
		for (Iterator beanIter = getConcreteModel().getEnterpriseBeans().getMessageDrivenBeans().iterator(); beanIter
				.hasNext();) {
			MessageDrivenBean bean = (MessageDrivenBean) beanIter.next();
			findDependedFiles(bean, rootTypeSimpleName, bean.getEjbLocalRefs(), bean.getResourceRefs(), unitsToRebuild);
		}
		return unitsToRebuild;
	}

	private ICompilationUnit getCompilationUnitFromModel(JavaEEObject bean) {
		for (Map.Entry<ICompilationUnit, JavaEEObject> entry : unitToModel.entrySet()) {
			if (entry.getValue().equals(bean))
				return entry.getKey();
		}
		return null;
	}

	private void findDependedFiles(JavaEEObject bean, String rootTypeSimpleName, Collection<EjbLocalRef> ejbRefs,
			Collection<ResourceRef> resourceRefs, Collection<ICompilationUnit> filesToRebuild) {
		for (Iterator refsIter = ejbRefs.iterator(); refsIter.hasNext();) {
			String localRefInterface = ((EjbLocalRef) refsIter.next()).getLocal();
			if (rootTypeSimpleName.equals(localRefInterface))
				filesToRebuild.add(getCompilationUnitFromModel(bean));
		}
		for (Iterator refsIter = resourceRefs.iterator(); refsIter.hasNext();) {
			String resourceRef = ((ResourceRef) refsIter.next()).getResType();
			if (rootTypeSimpleName.equals(resourceRef)) {
				filesToRebuild.add(getCompilationUnitFromModel(bean));
			}
		}
	}

	@Override
	protected void processRemovedCompilationUnit(IModelProviderEvent modelEvent, ICompilationUnit unit)
			throws CoreException {
		if (unitToModel.containsKey(unit))
			processRemoveBean(modelEvent, unit);
		else if (beanRefToResolvedInterfaceUnit.containsTarget(unit))
			processRemovedInterface(modelEvent, unit);

	}

	/**
	 * Process a unit as a removed interface. It is the responsibility of the
	 * caller to make sure the unit really contains an interface and that this
	 * interface is really removed.
	 * 
	 * Removing an interface will also remove the connections between the
	 * interface and the beans that depend on it -
	 * {@link #beanRefToResolvedInterfaceUnit}.
	 * 
	 * Removing an interface will result in rebuilding all the modelObjects that
	 * depend on this interface.
	 * 
	 * @see #beanRefToResolvedInterfaceUnit
	 * @see #processRemovedCompilationUnit(IModelProviderEvent,
	 *      ICompilationUnit)
	 * @see #processRemoveBean(IModelProviderEvent, ICompilationUnit)
	 * @param modelEvent
	 * @param unit
	 * @throws JavaModelException
	 */
	private void processRemovedInterface(IModelProviderEvent modelEvent, ICompilationUnit unit) throws CoreException {
		Collection<BeanInterfaceRef> refs = beanRefToResolvedInterfaceUnit.getSources(unit);
		Collection<ICompilationUnit> filesToRebuild = new HashSet<ICompilationUnit>();
		for (BeanInterfaceRef ref : refs) {
			filesToRebuild.add(getCompilationUnitFromModel(ref.getModelObject()));
		}
		if (filesToRebuild.isEmpty())
			return;
		for (ICompilationUnit changedFile : filesToRebuild) {
			processRemovedCompilationUnit(modelEvent, changedFile);
			processAddedCompilationUnit(modelEvent, changedFile);
		}
		filesToRebuild.clear();
		beanRefToResolvedInterfaceUnit.disconnect(unit);
	}

	/**
	 * Process a unit as a removed bean. It is the responsibility of the caller
	 * to make sure the file really contains a bean and that this bean is really
	 * removed.
	 * 
	 * @see #processRemovedCompilationUnit(IModelProviderEvent,
	 *      ICompilationUnit)
	 * @see #processRemovedInterface(IModelProviderEvent, ICompilationUnit)
	 * @see #beanRefToResolvedInterface
	 * @param modelEvent
	 * @param unit
	 *            the removed compilation unit
	 * @throws JavaModelException
	 */
	private void processRemoveBean(IModelProviderEvent modelEvent, ICompilationUnit unit) throws JavaModelException {
		EObject modelObject = (EObject) unitToModel.get(unit);
		EcoreUtil.remove(modelObject);
		if (getConcreteModel().getEnterpriseBeans().getGroup().isEmpty())
			getConcreteModel().setEnterpriseBeans(null);
		unitToModel.remove(unit);
		modelEvent.setEventCode(modelEvent.getEventCode() | IModelProviderEvent.REMOVED_RESOURCE);
		modelEvent.addResource(unit);
		disconnectFromRoles((JavaEEObject) modelObject);
	}

	/**
	 * Process a unit as "changed". If no model object depends on this unit the
	 * unit will be process as added (since it may not have been a bean till
	 * now, but an annotation was added).
	 * 
	 * It is the responsibility of the caller to make sure the unit really
	 * contains a bean/interface and that this bean is really changed and can be
	 * accessed.
	 * 
	 * @see #processChangedBean(IModelProviderEvent, ICompilationUnit)
	 * @see #processChangedInterface(IModelProviderEvent, ICompilationUnit)
	 * @param modelEvent
	 * @param unit
	 * @throws JavaModelException
	 */
	@Override
	protected void processChangedCompilationUnit(IModelProviderEvent modelEvent, ICompilationUnit unit)
			throws CoreException {
		if (unitToModel.containsKey(unit))
			processChangedBean(modelEvent, unit);
		else if (beanRefToResolvedInterfaceUnit.containsTarget(unit))
			processChangedInterface(modelEvent, unit);
		else
			processAddedCompilationUnit(modelEvent, unit);
	}

	/**
	 * Process a unit as a changed bean. It is the responsibility of the caller
	 * to make sure the unit really contains a bean and that this bean is really
	 * changed.
	 * 
	 * Changing a bean may mean removing it from the model (if it was a bean
	 * till now, but the annotation was deleted)
	 * 
	 * @see #processChangedBean(IModelProviderEvent, ICompilationUnit)
	 * @see {@link #processChangedInterface(IModelProviderEvent, ICompilationUnit)}
	 * @param modelEvent
	 * @param unit
	 * @throws JavaModelException
	 */
	private void processChangedBean(IModelProviderEvent modelEvent, ICompilationUnit unit) throws CoreException {
		EObject oldBean = (EObject) unitToModel.get(unit);
		ICompilationUnit beanUnit = getCompilationUnitFromModel((JavaEEObject) oldBean);
		processRemovedCompilationUnit(modelEvent, beanUnit);
		processAddedCompilationUnit(modelEvent, beanUnit);
	}

	/**
	 * Process a unit as a changed interface. It is the responsibility of the
	 * caller to make sure the file really contains an interface and that this
	 * interface is really changed.
	 * 
	 * Changing an interface may mean rebuilding all the beans that depend on
	 * this interface (if it was annotated with "@Local", but this annotation
	 * was changed to "@Remote" )
	 * 
	 * @param modelEvent
	 * @param unit
	 * @throws JavaModelException
	 */
	private void processChangedInterface(IModelProviderEvent modelEvent, ICompilationUnit unit) throws CoreException {
		Collection<BeanInterfaceRef> references = beanRefToResolvedInterfaceUnit.getSources(unit);
		for (BeanInterfaceRef ref : references) {
			ICompilationUnit next = getCompilationUnitFromModel(ref.getModelObject());
			processRemovedCompilationUnit(modelEvent, next);
			processAddedCompilationUnit(modelEvent, next);
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

	@Override
	protected void processRemovedPackage(IModelProviderEvent modelEvent, IJavaElementDelta delta) throws CoreException {
		Set<ICompilationUnit> keySet = new HashSet<ICompilationUnit>(unitToModel.keySet());
		for (ICompilationUnit unit : keySet) {
			if (unit.getParent().getElementName().equals(delta.getElement().getElementName())) {
				processRemovedCompilationUnit(modelEvent, unit);
			}
		}
	}

}
