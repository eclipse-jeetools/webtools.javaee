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
package org.eclipse.jst.jee.model.internal.mergers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.InterceptorsType;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.Relationships;
import org.eclipse.jst.javaee.ejb.SessionBean;

/**
 * Ejb Jar merger merges the Ejb jar artifact it will call 
 * the assembly descriptor merger or corresponding bean merger 
 * if conflict is detected.
 *  
 * @author Dimitar Giormov
 */
public class EjbJarMerger extends ModelElementMerger {

	/**
	 * Constructor which gets the base and to merge ejb jar element.
	 * 
	 * @param _base - is the base element, into which the values will be merged.
	 * @param merge - is the conflicting element, which will be merged into base.
	 * @param kind
	 */
	public EjbJarMerger(JavaEEObject _base, JavaEEObject merge, int kind) {
		super(_base, merge, kind);
	}

	/**
	 * Returns the base element, into which the values will be merged.
	 * 
	 * @return base element, into which the values will be merged.
	 */
	EJBJar getBaseEjbJar(){
		return (EJBJar) getBase();
	}

	/**
	 * Returns the conflicting element, which will be merged into base.
	 * 
	 * @return the conflicting element, which will be merged into base.
	 */
	EJBJar getToMergeEjbJar(){
		return (EJBJar) getToMerge();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.ejb.model.mergers.ModelElementMerger#process()
	 */
	@Override
	public List process() throws ModelException {
		List warnings = new ArrayList();
		try {
			copyGeneralData(warnings);
			mergeSessionBeans(warnings);
			mergeMdbs(warnings);
			mergeEntities(warnings);
			mergeAssemblyDescriptor(warnings);
		} catch (Exception e) {
			throw new ModelException(e);
		}
		return warnings;
	}


	private void mergeAssemblyDescriptor(List warnings) throws ModelException {
		if(getToMergeEjbJar().getAssemblyDescriptor() == null){
			return;
		}
		if(getBaseEjbJar().getAssemblyDescriptor() == null){
			getBaseEjbJar().setAssemblyDescriptor(EjbFactory.eINSTANCE.createAssemblyDescriptor()); 
		}
		AssemblyDescriptorMerger adMerger = new AssemblyDescriptorMerger(getBaseEjbJar().getAssemblyDescriptor(), 
				getToMergeEjbJar().getAssemblyDescriptor(), ModelElementMerger.ADD);
		warnings.addAll(adMerger.process());

	}

	private void mergeEntities(List warnings) {

		if(!isEnterpriseBeansTagPresent()){
			return;
		}

		List entityBeansToMerge = getToMergeEjbJar().getEnterpriseBeans().getEntityBeans();      
		List entityBeansBase = getBaseEjbJar().getEnterpriseBeans().getEntityBeans();
		if(!entityBeansToMerge.isEmpty()) {
			for (Object entity : entityBeansToMerge) {
				EntityBean entityType = (EntityBean) entity;
				EntityBean entityBean = getEntityBean(entityType.getEjbName(), entityBeansBase);
				if (entityBean == null && isValidArtifact(entity)){
					entityBeansBase.add(EcoreUtil.copy((EObject) entity));          
				}
			}
		}

	}

	private boolean isEnterpriseBeansTagPresent() {
		if(getToMergeEjbJar().getEnterpriseBeans() == null){
			return false;
		}
		if(getBaseEjbJar().getEnterpriseBeans() == null){
			getBaseEjbJar().setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		}
		if((getToMergeEjbJar().getEnterpriseBeans().getEntityBeans() == null || 
				getToMergeEjbJar().getEnterpriseBeans().getEntityBeans().isEmpty()) && 
				(getToMergeEjbJar().getEnterpriseBeans().getSessionBeans() == null || 
						getToMergeEjbJar().getEnterpriseBeans().getSessionBeans().isEmpty()) &&
						(getToMergeEjbJar().getEnterpriseBeans().getMessageDrivenBeans() == null || 
								getToMergeEjbJar().getEnterpriseBeans().getMessageDrivenBeans().isEmpty())
		){
			return false;
		}

		return true;
	}

	private void mergeSessionBeans(List warnings) throws Exception {
		if(!isEnterpriseBeansTagPresent()){
			return;
		}

		List sessionBeansToMerge = getToMergeEjbJar().getEnterpriseBeans().getSessionBeans();      
		List sessionBeansBase = getBaseEjbJar().getEnterpriseBeans().getSessionBeans();
		for (Object session : sessionBeansToMerge) {
			SessionBean sessionType = (SessionBean) session;

			SessionBean baseBean = getSessionBean(sessionType.getEjbName(), sessionBeansBase);
			if (baseBean != null){
				IMerger m = getSessionMerger(baseBean, sessionType, getKind());
				if(m != null){
					warnings.addAll(m.process());
					continue;
				}
			} else {
				if (isValidArtifact(session)){
					sessionBeansBase.add(EcoreUtil.copy((EObject) session));
				}
			}      
		}
	}

	private IMerger getSessionMerger(SessionBean baseBean, SessionBean toMergeType, int kind) {
		return new SessionBeanMerger(baseBean, toMergeType, kind);
	}

	private void mergeMdbs(List warnings) throws Exception {
		if(!isEnterpriseBeansTagPresent()){
			return;
		}

		List mdBeansToMerge = getToMergeEjbJar().getEnterpriseBeans().getMessageDrivenBeans();      
		List mdBeansBase = getBaseEjbJar().getEnterpriseBeans().getMessageDrivenBeans();
		for (Object mdb : mdBeansToMerge) {
			MessageDrivenBean sessionType = (MessageDrivenBean) mdb;
			MessageDrivenBean baseBean = getMdbBean(sessionType.getEjbName(), mdBeansBase);
			if (baseBean != null){
				IMerger m = getMdbMerger(baseBean, sessionType, getKind());
				if(m != null){
					warnings.addAll(m.process());
				}
			} else {
				if (isValidArtifact(mdb)){
					mdBeansBase.add(EcoreUtil.copy((EObject) mdb));
				}

			}
		}
	}

	private IMerger getMdbMerger(MessageDrivenBean baseBean, MessageDrivenBean toMergeType, int kind) {
		return new MessageDrivenBeanMerger(baseBean, toMergeType, kind);
	}

	private MessageDrivenBean getMdbBean(String name, List enterpriseBeans){
		for (Object bean : enterpriseBeans) {
			MessageDrivenBean sBean = (MessageDrivenBean) bean;
			if (sBean.getEjbName().equals(name)){
				return sBean;
			}

		}
		return null;
	}

	private SessionBean getSessionBean(String name, List enterpriseBeans){
		for (Object bean : enterpriseBeans) {
			SessionBean sBean = (SessionBean) bean;
			if (sBean.getEjbName().equals(name)){
				return sBean;
			}

		}
		return null;
	}

	private EntityBean getEntityBean(String name, List enterpriseBeans){
		for (Object bean : enterpriseBeans) {
			EntityBean sBean = (EntityBean) bean;
			if (sBean.getEjbName().equals(name)){
				return sBean;
			}

		}
		return null;
	}

	private void copyGeneralData(List warnings){

		copyJavaEEGroup();
		if (getToMergeEjbJar().getRelationships() != null && getToMergeEjbJar().getRelationships().getEjbRelations() != null
				&& getToMergeEjbJar().getRelationships().getEjbRelations().size() > 0){
			getBaseEjbJar().setRelationships((Relationships) EcoreUtil.copy((EObject) getToMergeEjbJar().getRelationships()));
		}
		if (getToMergeEjbJar().getEjbClientJar() != null){
			getBaseEjbJar().setEjbClientJar(getToMergeEjbJar().getEjbClientJar());
		}

		if (getToMergeEjbJar().getInterceptors() != null && getToMergeEjbJar().getInterceptors().getInterceptors() != null
				&& getToMergeEjbJar().getInterceptors().getInterceptors().size() > 0){
			getBaseEjbJar().setInterceptors((InterceptorsType) (EcoreUtil.copy((EObject) getToMergeEjbJar().getInterceptors())));
		}

		    if (getToMergeEjbJar().isSetVersion()){
			      getBaseEjbJar().setVersion(getToMergeEjbJar().getVersion());
			    }
	}

	private void copyAllContentInBase(List listSource, List target) {
		for (Object object : listSource) {
			target.add(EcoreUtil.copy((EObject) object));
		}

	}


	private boolean isValidArtifact(Object javaEEObject){
		if (javaEEObject instanceof SessionBean){
			return ( (SessionBean)javaEEObject).getEjbName() != null;
		} else if (javaEEObject instanceof EntityBean){        
			return ((EntityBean)javaEEObject).getEjbName() != null;
		} else if (javaEEObject instanceof MessageDrivenBean){
			return ((MessageDrivenBean)javaEEObject).getEjbName() != null;
		} 
		return true;
	}

	private void copyJavaEEGroup(){
		if (getToMergeEjbJar().getDescriptions() != null){
			copyAllContentInBase(getToMergeEjbJar().getDescriptions(), getBaseEjbJar().getDescriptions());
		}

		if (getToMergeEjbJar().getDisplayNames() != null){
			copyAllContentInBase(getToMergeEjbJar().getDisplayNames(), getBaseEjbJar().getDisplayNames());
		}

		if (getToMergeEjbJar().getIcons() != null){
			copyAllContentInBase(getToMergeEjbJar().getIcons(), getBaseEjbJar().getIcons());
		}
	}

}
