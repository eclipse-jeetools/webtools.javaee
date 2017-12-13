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
package org.eclipse.jst.jee.model.mergers.tests;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EJBRelation;
import org.eclipse.jst.javaee.ejb.EJBRelationshipRole;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.InterceptorType;
import org.eclipse.jst.javaee.ejb.InterceptorsType;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.RelationshipRoleSourceType;
import org.eclipse.jst.javaee.ejb.Relationships;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.mergers.EjbJarMerger;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;

/**
 * Tester class for EjbJar artifact.
 * 
 * Base suffix means that the base object has some info and toMerge is empty:
 * nothing should be merged
 * 
 * ToMerge suffix means that the base is empty object and toMerge has some info:
 * all from merge should be present in base.
 * 
 * Same suffix means that the information in merge and base is one and the same:
 * no merge should occurred and additional checks for doubling of the elements
 * are present.
 * 
 * Complex suffix means variety of information is present in base and to merge:
 * consistent information combined by base and toMerge should be available at
 * the end.
 * 
 * ComplexOverlapped suffix means variety of information is present in base and
 * to merge: consistent information combined by base and toMerge should be
 * available at the end. There are artifacts with one and the same name and
 * different values: values should be merged into base.
 * 
 * 
 * @author Dimitar Giormov
 * 
 */
public class EjbJarMergerTest extends TestCase {

	/**
	 * Empty Ejb Jars test without enterprise beans tag
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testZeroMerge() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		(new EjbJarMerger(base, merge, 0)).process();
		assertNull(base.getEnterpriseBeans());
		assertNull(merge.getEnterpriseBeans());
	}

	/**
	 * Empty Ejb Jars test with enterprise beans tag on toMerge
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testZeroMerge2() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
	}

	/**
	 * Empty Ejb Jars test with enterprise beans tag on base
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testZeroMerge3() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNull(merge.getEnterpriseBeans());
	}

	/**
	 * Base suffix means that the base object has some info and toMerge is
	 * empty: nothing should be merged
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testSesionBeansBase() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		SessionBean sessionBean = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean.setEjbName("name");
		base.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getSessionBeans().size());

	}

	/**
	 * ToMerge suffix means that the base is empty object and toMerge has some
	 * info: all from merge should be present in base.
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testSesionBeansMerge() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		SessionBean sessionBean = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean.setEjbName("name");
		merge.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getSessionBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getSessionBeans().size());

	}

	/**
	 * Same suffix means that the information in merge and base is one and the
	 * same: no merge should occurred and additional checks for doubling of the
	 * elements are present.
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testSesionBeansMergeSameBean() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		SessionBean sessionBean = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean.setEjbName("name");
		base.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		merge.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getSessionBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getSessionBeans().size());
		assertNotNull(getSessionBean("name", base.getEnterpriseBeans().getSessionBeans()));

	}

	private SessionBean getSessionBean(String name, List enterpriseBeans) {
		for (Object bean : enterpriseBeans) {
			SessionBean sBean = (SessionBean) bean;
			if (sBean.getEjbName().equals(name)) {
				return sBean;
			}

		}
		return null;
	}

	/**
	 * Complex suffix means variety of information is present in base and to
	 * merge: consistent information combined by base and toMerge should be
	 * available at the end.
	 * 
	 * @throws Exception
	 */
	public void testSessionBeanComplex() throws Exception {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		SessionBean sessionBean = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean.setEjbName("name");
		SessionBean sessionBean1 = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean1.setEjbName("name1");
		SessionBean sessionBean2 = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean2.setEjbName("name2");
		SessionBean sessionBean3 = EjbFactory.eINSTANCE.createSessionBean();
		sessionBean3.setEjbName("name3");
		base.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		base.getEnterpriseBeans().getSessionBeans().add(sessionBean1);
		base.getEnterpriseBeans().getSessionBeans().add(sessionBean3);

		merge.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		merge.getEnterpriseBeans().getSessionBeans().add(sessionBean2);

		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(4, base.getEnterpriseBeans().getSessionBeans().size());
		assertEquals(2, merge.getEnterpriseBeans().getSessionBeans().size());
		assertNotNull(getSessionBean("name", base.getEnterpriseBeans().getSessionBeans()));
		assertNotNull(getSessionBean("name1", base.getEnterpriseBeans().getSessionBeans()));
		assertNotNull(getSessionBean("name2", base.getEnterpriseBeans().getSessionBeans()));
		assertNotNull(getSessionBean("name3", base.getEnterpriseBeans().getSessionBeans()));
	}

	// MDB

	/**
	 * Base suffix means that the base object has some info and toMerge is
	 * empty: nothing should be merged
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testMdbBeansBase() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		MessageDrivenBean sessionBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
		sessionBean.setEjbName("name");
		base.getEnterpriseBeans().getMessageDrivenBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getMessageDrivenBeans().size());

	}

	/**
	 * @throws ModelException
	 */
	// @Test
	public void tesMdbBeansMerge() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		MessageDrivenBean sessionBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
		sessionBean.setEjbName("name");
		merge.getEnterpriseBeans().getMessageDrivenBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getMessageDrivenBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getMessageDrivenBeans().size());

	}

	/**
	 * Same suffix means that the information in merge and base is one and the
	 * same: no merge should occurred and additional checks for doubling of the
	 * elements are present.
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testMdbBeansMergeSameBean() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		MessageDrivenBean sessionBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
		sessionBean.setEjbName("name");
		base.getEnterpriseBeans().getMessageDrivenBeans().add(sessionBean);
		merge.getEnterpriseBeans().getMessageDrivenBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getMessageDrivenBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getMessageDrivenBeans().size());
		assertNotNull(getMdbBean("name", base.getEnterpriseBeans().getMessageDrivenBeans()));

	}

	private MessageDrivenBean getMdbBean(String name, List enterpriseBeans) {
		for (Object bean : enterpriseBeans) {
			MessageDrivenBean sBean = (MessageDrivenBean) bean;
			if (sBean.getEjbName().equals(name)) {
				return sBean;
			}

		}
		return null;
	}

	/**
	 * Complex suffix means variety of information is present in base and to
	 * merge: consistent information combined by base and toMerge should be
	 * available at the end.
	 * 
	 * @throws Exception
	 */
	public void testMdbBeanComplex() throws Exception {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		MessageDrivenBean messageBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
		messageBean.setEjbName("name");
		MessageDrivenBean messageBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
		messageBean1.setEjbName("name1");
		MessageDrivenBean messageBean2 = EjbFactory.eINSTANCE.createMessageDrivenBean();
		messageBean2.setEjbName("name2");
		MessageDrivenBean messageBean3 = EjbFactory.eINSTANCE.createMessageDrivenBean();
		messageBean3.setEjbName("name3");
		base.getEnterpriseBeans().getMessageDrivenBeans().add(messageBean);
		base.getEnterpriseBeans().getMessageDrivenBeans().add(messageBean1);
		base.getEnterpriseBeans().getMessageDrivenBeans().add(messageBean3);

		merge.getEnterpriseBeans().getMessageDrivenBeans().add(messageBean);
		merge.getEnterpriseBeans().getMessageDrivenBeans().add(messageBean2);

		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(4, base.getEnterpriseBeans().getMessageDrivenBeans().size());
		assertEquals(2, merge.getEnterpriseBeans().getMessageDrivenBeans().size());
		assertNotNull(getMdbBean("name", base.getEnterpriseBeans().getMessageDrivenBeans()));
		assertNotNull(getMdbBean("name1", base.getEnterpriseBeans().getMessageDrivenBeans()));
		assertNotNull(getMdbBean("name2", base.getEnterpriseBeans().getMessageDrivenBeans()));
		assertNotNull(getMdbBean("name3", base.getEnterpriseBeans().getMessageDrivenBeans()));
	}

	// Entities

	/**
	 * Base suffix means that the base object has some info and toMerge is
	 * empty: nothing should be merged
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testEntityBeansBase() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		EntityBean sessionBean = EjbFactory.eINSTANCE.createEntityBean();
		sessionBean.setEjbName("name");
		base.getEnterpriseBeans().getEntityBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getEntityBeans().size());

	}

	/**
	 * * ToMerge suffix means that the base is empty object and toMerge has some
	 * info: all from merge should be present in base.
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testEntityBeansMerge() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		EntityBean sessionBean = EjbFactory.eINSTANCE.createEntityBean();
		sessionBean.setEjbName("name");
		merge.getEnterpriseBeans().getEntityBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getEntityBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getEntityBeans().size());

	}

	/**
	 * Same suffix means that the information in merge and base is one and the
	 * same: no merge should occurred and additional checks for doubling of the
	 * elements are present.
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testEntityBeansMergeSameBean() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		EntityBean sessionBean = EjbFactory.eINSTANCE.createEntityBean();
		sessionBean.setEjbName("name");
		base.getEnterpriseBeans().getEntityBeans().add(sessionBean);
		merge.getEnterpriseBeans().getEntityBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(1, base.getEnterpriseBeans().getEntityBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getEntityBeans().size());
		assertNotNull(getEntityBean("name", base.getEnterpriseBeans().getEntityBeans()));

	}

	private EntityBean getEntityBean(String name, List enterpriseBeans) {
		for (Object bean : enterpriseBeans) {
			EntityBean sBean = (EntityBean) bean;
			if (sBean.getEjbName().equals(name)) {
				return sBean;
			}

		}
		return null;
	}

	/**
	 * 
	 * Complex suffix means variety of information is present in base and to
	 * merge: consistent information combined by base and toMerge should be
	 * available at the end.
	 * 
	 * @throws Exception
	 */
	public void testEntityBeanComplex() throws Exception {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		EntityBean entityBean = EjbFactory.eINSTANCE.createEntityBean();
		entityBean.setEjbName("name");
		EntityBean entityBean1 = EjbFactory.eINSTANCE.createEntityBean();
		entityBean1.setEjbName("name1");
		EntityBean entityBean2 = EjbFactory.eINSTANCE.createEntityBean();
		entityBean2.setEjbName("name2");
		EntityBean entityBean3 = EjbFactory.eINSTANCE.createEntityBean();
		entityBean3.setEjbName("name3");
		base.getEnterpriseBeans().getEntityBeans().add(entityBean);
		base.getEnterpriseBeans().getEntityBeans().add(entityBean1);
		base.getEnterpriseBeans().getEntityBeans().add(entityBean3);

		merge.getEnterpriseBeans().getEntityBeans().add(entityBean);
		merge.getEnterpriseBeans().getEntityBeans().add(entityBean2);

		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(4, base.getEnterpriseBeans().getEntityBeans().size());
		assertEquals(2, merge.getEnterpriseBeans().getEntityBeans().size());
		assertNotNull(getEntityBean("name", base.getEnterpriseBeans().getEntityBeans()));
		assertNotNull(getEntityBean("name1", base.getEnterpriseBeans().getEntityBeans()));
		assertNotNull(getEntityBean("name2", base.getEnterpriseBeans().getEntityBeans()));
		assertNotNull(getEntityBean("name3", base.getEnterpriseBeans().getEntityBeans()));
	}

	// @Test
	public void testGenericCopyBase() throws Exception {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		// Interceptors
		InterceptorsType interceptorsType = EjbFactory.eINSTANCE.createInterceptorsType();
		InterceptorType type = EjbFactory.eINSTANCE.createInterceptorType();
		interceptorsType.getInterceptors().add(type);
		type.setInterceptorClass("test");
		base.setInterceptors(interceptorsType);

		// Relationships
		Relationships relationships = EjbFactory.eINSTANCE.createRelationships();
		EJBRelation relation = EjbFactory.eINSTANCE.createEJBRelation();
		EJBRelationshipRole relationshipRole = EjbFactory.eINSTANCE.createEJBRelationshipRole();
		RelationshipRoleSourceType roleSourceType = EjbFactory.eINSTANCE.createRelationshipRoleSourceType();
		roleSourceType.setEjbName("testEjbName");
		String roleName = "RelationshpiRoleName";
		relationshipRole.setEjbRelationshipRoleName("nm");
		relationshipRole.setRelationshipRoleSource(roleSourceType);
		relation.getEjbRelationshipRoles().add(relationshipRole);
		relation.setEjbRelationName(roleName);
		relationships.getEjbRelations().add(relation);
		base.setRelationships(relationships);
		// Client jar
		String clientJar = "ejbClientJar";
		base.setEjbClientJar(clientJar);

		// version
		String version = "99";
		base.setVersion(version);

		(new EjbJarMerger(base, merge, 0)).process();
		// Interceptors
		assertNotNull(base.getInterceptors());
		assertNotNull(base.getInterceptors().getInterceptors());
		assertEquals(1, base.getInterceptors().getInterceptors().size());
		assertEquals("test", ((InterceptorType) base.getInterceptors().getInterceptors().get(0)).getInterceptorClass());

		// Relationships
		assertNotNull(base.getRelationships());
		assertNotNull(base.getRelationships().getEjbRelations());
		assertEquals(1, base.getRelationships().getEjbRelations().size());
		assertEquals(roleName, ((EJBRelation) base.getRelationships().getEjbRelations().get(0)).getEjbRelationName());
		// Client jar
		assertEquals(clientJar, base.getEjbClientJar());

	}

	// @Test
	public void testGenericCopyMerge() throws Exception {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();

		// Interceptors
		InterceptorsType interceptorsType = EjbFactory.eINSTANCE.createInterceptorsType();
		InterceptorType type = EjbFactory.eINSTANCE.createInterceptorType();
		interceptorsType.getInterceptors().add(type);
		type.setInterceptorClass("test");
		merge.setInterceptors(interceptorsType);

		// Relationships
		Relationships relationships = EjbFactory.eINSTANCE.createRelationships();
		EJBRelation relation = EjbFactory.eINSTANCE.createEJBRelation();
		EJBRelationshipRole relationshipRole = EjbFactory.eINSTANCE.createEJBRelationshipRole();
		RelationshipRoleSourceType roleSourceType = EjbFactory.eINSTANCE.createRelationshipRoleSourceType();
		roleSourceType.setEjbName("testEjbName");
		String roleName = "RelationshpiRoleName";
		relationshipRole.setEjbRelationshipRoleName("nm");
		relationshipRole.setRelationshipRoleSource(roleSourceType);
		relation.getEjbRelationshipRoles().add(relationshipRole);
		relation.setEjbRelationName(roleName);
		relationships.getEjbRelations().add(relation);
		merge.setRelationships(relationships);
		// Client jar
		String clientJar = "ejbClientJar";
		merge.setEjbClientJar(clientJar);

		(new EjbJarMerger(base, merge, 0)).process();

		// Interceptors
		assertNotNull(base.getInterceptors());
		assertNotNull(base.getInterceptors().getInterceptors());
		assertEquals(1, base.getInterceptors().getInterceptors().size());
		assertEquals("test", ((InterceptorType) base.getInterceptors().getInterceptors().get(0)).getInterceptorClass());
		// Relationships
		assertNotNull(base.getRelationships());
		assertNotNull(base.getRelationships().getEjbRelations());
		assertEquals(1, base.getRelationships().getEjbRelations().size());
		assertEquals(roleName, ((EJBRelation) base.getRelationships().getEjbRelations().get(0)).getEjbRelationName());
		// Client jar
		assertEquals(clientJar, base.getEjbClientJar());

	}
	
	
	/**
	 * Tests weather the merger will ignore incorrect bean (ejbName is missing)
	 * Session beans
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testSesionBeansNullNameMerge() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		SessionBean sessionBean = EjbFactory.eINSTANCE.createSessionBean();
		base.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		merge.getEnterpriseBeans().getSessionBeans().add(sessionBean);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(0, base.getEnterpriseBeans().getSessionBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getSessionBeans().size());

	}
	
	

	/**
	 * Tests weather the merger will ignore incorrect bean (ejbName is missing)
	 * For MdBeans
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testMdBeansNullNameMergeSameBean() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		MessageDrivenBean mdb = EjbFactory.eINSTANCE.createMessageDrivenBean();
		base.getEnterpriseBeans().getMessageDrivenBeans().add(mdb);
		merge.getEnterpriseBeans().getMessageDrivenBeans().add(mdb);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(0, base.getEnterpriseBeans().getMessageDrivenBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getMessageDrivenBeans().size());

	}
	
	

	/**
	 * Tests weather the merger will ignore incorrect bean (ejbName is missing)
	 * EntityBeans
	 * 
	 * @throws ModelException
	 */
	// @Test
	public void testEntityBeansNullNameMerge() throws ModelException {
		EJBJar base = EjbFactory.eINSTANCE.createEJBJar();
		EJBJar merge = EjbFactory.eINSTANCE.createEJBJar();
		base.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		merge.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		EntityBean entity = EjbFactory.eINSTANCE.createEntityBean();
		base.getEnterpriseBeans().getEntityBeans().add(entity);
		merge.getEnterpriseBeans().getEntityBeans().add(entity);
		(new EjbJarMerger(base, merge, 0)).process();
		assertNotNull(base.getEnterpriseBeans());
		assertNotNull(merge.getEnterpriseBeans());
		assertEquals(0, base.getEnterpriseBeans().getEntityBeans().size());
		assertEquals(1, merge.getEnterpriseBeans().getEntityBeans().size());

	}
}
