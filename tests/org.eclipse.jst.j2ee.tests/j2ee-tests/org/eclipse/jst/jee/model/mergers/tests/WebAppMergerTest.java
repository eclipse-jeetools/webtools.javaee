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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.AuthConstraint;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.SecurityConstraint;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.TransportGuaranteeType;
import org.eclipse.jst.javaee.web.UserDataConstraint;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jee.model.internal.mergers.ModelElementMerger;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;
import org.eclipse.jst.jee.model.internal.mergers.WebAppMerger;

/**
 * Tester class for WebAPp artifact.
 * 
 * Base suffix means that the base object has some info and 
 * toMerge is empty: nothing should be merged
 * 
 * ToMerge suffix means that the base is empty object and 
 * toMerge has some info: all from merge should be present in base.
 * 
 * Same suffix means that the information in merge and base is one 
 * and the same: no merge should occurred and additional checks for 
 * doubling of the elements are present.
 * 
 * Complex suffix means variety of information is present in base 
 * and to merge: consistent information combined by base and toMerge
 * should be available at the end.
 * 
 * ComplexOverlapped suffix means variety of information is present 
 * in base and to merge: consistent information combined by base and toMerge
 * should be available at the end. There are artifacts with one and the same 
 * name and different values: values should be merged into base.
 *  
 * 
 * @author Dimitar Giormov
 *
 */
public class WebAppMergerTest extends TestCase{

	/**
	 * Tests the merger with Base security role only.
	 * No merge is necessary
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleSecurityRoleCaseBase() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
		role.setRoleName("test1");

		descriptorBase.getSecurityRoles().add(role);
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(descriptorBase.getSecurityRoles());
		Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 1);
		Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 0);
		Assert.assertTrue(((SecurityRole)descriptorBase.getSecurityRoles().get(0)).getRoleName().equals("test1"));
	}

	/**
	 * Tests the merger with toMerge security role only.
	 * The role should be copied in base.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleSecurityRoleCaseToMerge() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
		role.setRoleName("test1");

		descriptorToMerge.getSecurityRoles().add(role);

		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(descriptorBase.getSecurityRoles());
		Assert.assertEquals(1, descriptorBase.getSecurityRoles().size());
		Assert.assertEquals(1, descriptorToMerge.getSecurityRoles().size());
		Assert.assertTrue(((SecurityRole)descriptorBase.getSecurityRoles().get(0)).getRoleName().equals("test1"));
	}

	/**
	 * Tests the merger with one and the same security role.
	 * The result should be non merged 1 security role.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleSecurityRoleCaseSameRole() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
		role.setRoleName("test1");
		descriptorBase.getSecurityRoles().add(role);
		descriptorToMerge.getSecurityRoles().add(role);

		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(descriptorBase.getSecurityRoles());
		Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 1);
		Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 1);
		Assert.assertTrue(((SecurityRole)descriptorBase.getSecurityRoles().get(0)).getRoleName().equals("test1"));
	}

	/**
	 * Tests the merger with variety of security roles.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleSecurityRoleCaseComplex() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		SecurityRole role0 = JavaeeFactory.eINSTANCE.createSecurityRole();
		SecurityRole role1 = JavaeeFactory.eINSTANCE.createSecurityRole();
		SecurityRole role2 = JavaeeFactory.eINSTANCE.createSecurityRole();
		SecurityRole role3 = JavaeeFactory.eINSTANCE.createSecurityRole();
		SecurityRole role4 = JavaeeFactory.eINSTANCE.createSecurityRole();
		SecurityRole role5 = JavaeeFactory.eINSTANCE.createSecurityRole();
		role0.setRoleName("test1");
		role1.setRoleName("test2");
		role2.setRoleName("test3");
		role3.setRoleName("test4");
		role4.setRoleName("test5");
		role5.setRoleName("test6");



		descriptorBase.getSecurityRoles().add(role1);
		descriptorBase.getSecurityRoles().add(role3);
		descriptorBase.getSecurityRoles().add(role4);
		descriptorBase.getSecurityRoles().add(role5);


		descriptorToMerge.getSecurityRoles().add(role0);
		descriptorToMerge.getSecurityRoles().add(role1);
		descriptorToMerge.getSecurityRoles().add(role2);




		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(descriptorBase.getSecurityRoles());
		Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 6);
		Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 3);
		Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role0.getRoleName()));
		Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role1.getRoleName()));
		Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role2.getRoleName()));
		Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role3.getRoleName()));
		Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role4.getRoleName()));
		Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role5.getRoleName()));
	}

	private boolean containsSecRole(List list, String roleName) {


		for (Object object : list) {
			SecurityRole role = (SecurityRole) object;
			if(roleName == null){
				if(roleName == role.getRoleName()){
					return true;
				}
			} else if(roleName.equals(role.getRoleName())){
				return true;
			}
		}
		return false;
	}


	/**
	 * Tests the merger with Base security role only.
	 * No merge is necessary
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleRunAsCaseBase() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		RunAs role = JavaeeFactory.eINSTANCE.createRunAs();
		Servlet servletBase = WebFactory.eINSTANCE.createServlet();
		servletBase.setServletName("servName");
		Servlet servletMerge = WebFactory.eINSTANCE.createServlet();
		servletMerge.setServletName("servName");
		role.setRoleName("test1");
		servletBase.setRunAs(role);

		descriptorBase.getServlets().add(servletBase);
		descriptorToMerge.getServlets().add(servletMerge);


		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(servletBase.getRunAs());
		Assert.assertTrue(((RunAs)servletBase.getRunAs()).getRoleName().equals("test1"));
	}

	/**
	 * Tests the merger with toMerge security role only.
	 * The role should be copied in base.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleRunAsCaseToMerge() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		RunAs role = JavaeeFactory.eINSTANCE.createRunAs();
		Servlet servletBase = WebFactory.eINSTANCE.createServlet();
		servletBase.setServletName("servName");
		Servlet servletMerge = WebFactory.eINSTANCE.createServlet();
		servletMerge.setServletName("servName");
		role.setRoleName("test1");
		servletMerge.setRunAs(role);

		descriptorBase.getServlets().add(servletBase);
		descriptorToMerge.getServlets().add(servletMerge);


		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(servletBase.getRunAs());
		Assert.assertTrue(((RunAs)servletBase.getRunAs()).getRoleName().equals("test1"));
		Assert.assertEquals(1, descriptorBase.getServlets().size());
	}


	/**
	 * Tests the merger with null named servlet in toMerge array, the merger should ignore such entries.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleNullServletToMerge() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		Servlet servletBase = WebFactory.eINSTANCE.createServlet();

		descriptorToMerge.getServlets().add(servletBase);


		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertEquals(0, descriptorBase.getServlets().size());
	}

	/**
	 * Tests the merger with null named servlet in Base, as this is extremely unlikely
	 * the merger should not stop merging because of such error.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleNullNamedServletInBase() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		Servlet servletBase = WebFactory.eINSTANCE.createServlet();
		Servlet servletMerge = WebFactory.eINSTANCE.createServlet();
		servletMerge.setServletName("servName");

		descriptorBase.getServlets().add(servletBase);
		descriptorToMerge.getServlets().add(servletMerge);


		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertEquals(2, descriptorBase.getServlets().size());
	}

	/**
	 * Tests the merger with one and the same security role.
	 * The result should be non merged 1 security role.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleRunAsCaseSameRole() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		RunAs role = JavaeeFactory.eINSTANCE.createRunAs();
		Servlet servletBase = WebFactory.eINSTANCE.createServlet();
		servletBase.setServletName("servName");
		Servlet servletMerge = WebFactory.eINSTANCE.createServlet();
		servletMerge.setServletName("servName");
		role.setRoleName("test1");
		servletBase.setRunAs(role);
		servletMerge.setRunAs(role);

		descriptorBase.getServlets().add(servletBase);
		descriptorToMerge.getServlets().add(servletMerge);


		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(servletBase.getRunAs());
		Assert.assertTrue(((RunAs)servletBase.getRunAs()).getRoleName().equals("test1"));
	}



	/**
	 * Tests the merger with Base security constraints only.
	 * No merge is necessary
	 * 
	 * @throws ModelException
	 */
//	@Test
	@SuppressWarnings("unchecked")
	public void testSingleSecurityConstraintCaseBase() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		SecurityConstraint sc = WebFactory.eINSTANCE.createSecurityConstraint();
		AuthConstraint createAuthConstraint = WebFactory.eINSTANCE.createAuthConstraint();
		createAuthConstraint.getRoleNames().add("test");
		sc.setAuthConstraint(createAuthConstraint);
		UserDataConstraint userDataConstraint = WebFactory.eINSTANCE.createUserDataConstraint();
		userDataConstraint.setTransportGuarantee(TransportGuaranteeType.CONFIDENTIAL_LITERAL);
		sc.setUserDataConstraint(userDataConstraint);

		descriptorBase.getSecurityConstraints().add(sc);
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
		result.process();
		Assert.assertNotNull(descriptorBase.getSecurityConstraints());
		Assert.assertTrue(descriptorBase.getSecurityConstraints().size() == 1);
		Assert.assertTrue(descriptorToMerge.getSecurityConstraints().size() == 0);
		Assert.assertTrue(((SecurityConstraint)descriptorBase.getSecurityConstraints().get(0)).getAuthConstraint().getRoleNames().get(0).equals("test"));
	}

	/**
	 * Tests the merger with toMerge security constraints only.
	 * The role should be copied in base.
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testSingleSecurityConstraintCaseToMerge() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		SecurityConstraint sc = WebFactory.eINSTANCE.createSecurityConstraint();
		AuthConstraint createAuthConstraint = WebFactory.eINSTANCE.createAuthConstraint();
		createAuthConstraint.getRoleNames().add("test");
		sc.setAuthConstraint(createAuthConstraint);
		UserDataConstraint userDataConstraint = WebFactory.eINSTANCE.createUserDataConstraint();
		userDataConstraint.setTransportGuarantee(TransportGuaranteeType.CONFIDENTIAL_LITERAL);
		sc.setUserDataConstraint(userDataConstraint);

		descriptorBase.getSecurityConstraints().add(sc);
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
		WebAppMerger result = new WebAppMerger(descriptorToMerge, descriptorBase, 0);
		result.process();
		Assert.assertNotNull(descriptorToMerge.getSecurityConstraints());
		Assert.assertTrue(descriptorToMerge.getSecurityConstraints().size() == 1);
		Assert.assertTrue(((SecurityConstraint)descriptorToMerge.getSecurityConstraints().get(0)).getAuthConstraint().getRoleNames().get(0).equals("test"));
	}

	/**
	 * Tests the merger "add" behavior with filter mappings with
	 * different url-patterns
	 * No merge is necessary
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testFilterMappingsCaseSameAdd() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();

		Filter filter = WebFactory.eINSTANCE.createFilter();
		filter.setFilterName("TestFilter");
		filter.setFilterClass("test.TestFilter");
		List<Filter> filters = descriptorBase.getFilters();
		filters.add(filter);

		UrlPatternType urlPattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern.setValue("/pattern1/*");
		FilterMapping filterMapping = WebFactory.eINSTANCE.createFilterMapping();
		filterMapping.setFilterName("TestFilter");
		List<UrlPatternType> urlPatterns = filterMapping.getUrlPatterns();
		urlPatterns.add(urlPattern);
		List<FilterMapping> filterMappings = descriptorBase.getFilterMappings();
		filterMappings.add(filterMapping);

		Filter filter2 = WebFactory.eINSTANCE.createFilter();
		filter2.setFilterName("TestFilter");
		filter2.setFilterClass("test.TestFilter2");
		List<Filter> filters2 = descriptorToMerge.getFilters();
		filters2.add(filter2);

		UrlPatternType urlPattern2 = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern2.setValue("/pattern2/*");
		FilterMapping filterMapping2 = WebFactory.eINSTANCE.createFilterMapping();
		filterMapping2.setFilterName("TestFilter");
		List<UrlPatternType> urlPatterns2 = filterMapping2.getUrlPatterns();
		urlPatterns2.add(urlPattern2);
		List<FilterMapping> filterMappings2 = descriptorToMerge.getFilterMappings();
		filterMappings2.add(filterMapping2);

		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, ModelElementMerger.ADD);
		result.process();
		Assert.assertNotNull(descriptorBase.getFilters());
		Assert.assertEquals(1, descriptorBase.getFilters().size());
		Assert.assertEquals(1, descriptorToMerge.getFilters().size());
		Assert.assertEquals(1, descriptorToMerge.getFilterMappings().size());
		Filter f = descriptorBase.getFilters().get(0);
		Assert.assertNotNull(f);
		Assert.assertTrue(f.getFilterName().equals("TestFilter"));
		Assert.assertNotNull(descriptorBase.getFilterMappings());
		Assert.assertEquals(1, descriptorBase.getFilterMappings().size());
		FilterMapping m = descriptorBase.getFilterMappings().get(0);
		Assert.assertTrue(m.getFilterName().equals("TestFilter"));
		Assert.assertNotNull(m.getUrlPatterns());
		Assert.assertEquals(1, m.getUrlPatterns().size());
		Assert.assertTrue(m.getUrlPatterns().get(0).getValue().equals("/pattern1/*"));
	}

	/**
	 * Tests the merger "copy" behavior with filter mappings with
	 * different url-patterns
	 * The filter mapping should be copied to base
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testFilterMappingsCaseSameCopy() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();

		Filter filter = WebFactory.eINSTANCE.createFilter();
		filter.setFilterName("TestFilter");
		filter.setFilterClass("test.TestFilter");
		List<Filter> filters = descriptorBase.getFilters();
		filters.add(filter);

		UrlPatternType urlPattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern.setValue("/pattern1/*");
		FilterMapping filterMapping = WebFactory.eINSTANCE.createFilterMapping();
		filterMapping.setFilterName("TestFilter");
		List<UrlPatternType> urlPatterns = filterMapping.getUrlPatterns();
		urlPatterns.add(urlPattern);
		List<FilterMapping> filterMappings = descriptorBase.getFilterMappings();
		filterMappings.add(filterMapping);

		Filter filter2 = WebFactory.eINSTANCE.createFilter();
		filter2.setFilterName("TestFilter");
		filter2.setFilterClass("test.TestFilter2");
		List<Filter> filters2 = descriptorToMerge.getFilters();
		filters2.add(filter2);

		UrlPatternType urlPattern2 = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern2.setValue("/pattern2/*");
		FilterMapping filterMapping2 = WebFactory.eINSTANCE.createFilterMapping();
		filterMapping2.setFilterName("TestFilter");
		List<UrlPatternType> urlPatterns2 = filterMapping2.getUrlPatterns();
		urlPatterns2.add(urlPattern2);
		List<FilterMapping> filterMappings2 = descriptorToMerge.getFilterMappings();
		filterMappings2.add(filterMapping2);

		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, ModelElementMerger.COPY);
		result.process();
		Assert.assertNotNull(descriptorBase.getFilters());
		Assert.assertEquals(1, descriptorBase.getFilters().size());
		Assert.assertEquals(1, descriptorToMerge.getFilters().size());
		Assert.assertEquals(1, descriptorToMerge.getFilterMappings().size());
		Filter f = descriptorBase.getFilters().get(0);
		Assert.assertNotNull(f);
		Assert.assertTrue(f.getFilterName().equals("TestFilter"));
		Assert.assertNotNull(descriptorBase.getFilterMappings());
		Assert.assertEquals(2, descriptorBase.getFilterMappings().size());
		FilterMapping m = descriptorBase.getFilterMappings().get(0);
		Assert.assertTrue(m.getFilterName().equals("TestFilter"));
		Assert.assertNotNull(m.getUrlPatterns());
		Assert.assertEquals(1, m.getUrlPatterns().size());
		Assert.assertTrue(m.getUrlPatterns().get(0).getValue().equals("/pattern1/*"));
		m = descriptorBase.getFilterMappings().get(1);
		Assert.assertTrue(m.getFilterName().equals("TestFilter"));
		Assert.assertNotNull(m.getUrlPatterns());
		Assert.assertEquals(1, m.getUrlPatterns().size());
		Assert.assertTrue(m.getUrlPatterns().get(0).getValue().equals("/pattern2/*"));
	}

	/**
	 * Tests the merger "add" behavior with servlet mappings with
	 * different url-patterns
	 * No merge is necessary
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testServletMappingsCaseSame() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();

		Servlet servlet = WebFactory.eINSTANCE.createServlet();
		servlet.setServletName("TestServlet");
		servlet.setServletClass("test.TestServlet");
		List<Servlet> servlets = descriptorBase.getServlets();
		servlets.add(servlet);

		UrlPatternType urlPattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern.setValue("/pattern1");
		ServletMapping servletMapping = WebFactory.eINSTANCE.createServletMapping();
		servletMapping.setServletName("TestServlet");
		List<UrlPatternType> urlPatterns = servletMapping.getUrlPatterns();
		urlPatterns.add(urlPattern);
		List<ServletMapping> servletMappings = descriptorBase.getServletMappings();
		servletMappings.add(servletMapping);

		Servlet servlet2 = WebFactory.eINSTANCE.createServlet();
		servlet2.setServletName("TestServlet");
		servlet2.setServletClass("test.TestServlet2");
		List<Servlet> servlets2 = descriptorToMerge.getServlets();
		servlets2.add(servlet2);

		UrlPatternType urlPattern2 = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern2.setValue("/pattern2");
		ServletMapping servletMapping2 = WebFactory.eINSTANCE.createServletMapping();
		servletMapping2.setServletName("TestServlet");
		List<UrlPatternType> urlPatterns2 = servletMapping2.getUrlPatterns();
		urlPatterns2.add(urlPattern2);
		List<ServletMapping> servletMappings2 = descriptorToMerge.getServletMappings();
		servletMappings2.add(servletMapping2);

		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, ModelElementMerger.ADD);
		result.process();
		Assert.assertNotNull(descriptorBase.getServlets());
		Assert.assertEquals(1, descriptorBase.getServlets().size());
		Assert.assertEquals(1, descriptorToMerge.getServlets().size());
		Assert.assertEquals(1, descriptorToMerge.getServletMappings().size());
		Servlet s = descriptorBase.getServlets().get(0);
		Assert.assertNotNull(s);
		Assert.assertTrue(s.getServletName().equals("TestServlet"));
		Assert.assertNotNull(descriptorBase.getServletMappings());
		Assert.assertEquals(1, descriptorBase.getServletMappings().size());
		ServletMapping m = descriptorBase.getServletMappings().get(0);
		Assert.assertTrue(m.getServletName().equals("TestServlet"));
		Assert.assertNotNull(m.getUrlPatterns());
		Assert.assertEquals(1, m.getUrlPatterns().size());
		Assert.assertTrue(m.getUrlPatterns().get(0).getValue().equals("/pattern1"));
	}

	/**
	 * Tests the merger "copy" behavior with servlet mappings with
	 * different url-patterns
	 * The servlet mapping should be copied to base
	 * 
	 * @throws ModelException
	 */
	//@Test
	public void testServletMappingsCaseSameCopy() throws ModelException{
		WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
		WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();

		Servlet servlet = WebFactory.eINSTANCE.createServlet();
		servlet.setServletName("TestServlet");
		servlet.setServletClass("test.TestServlet");
		List<Servlet> servlets = descriptorBase.getServlets();
		servlets.add(servlet);

		UrlPatternType urlPattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern.setValue("/pattern1");
		ServletMapping servletMapping = WebFactory.eINSTANCE.createServletMapping();
		servletMapping.setServletName("TestServlet");
		List<UrlPatternType> urlPatterns = servletMapping.getUrlPatterns();
		urlPatterns.add(urlPattern);
		List<ServletMapping> servletMappings = descriptorBase.getServletMappings();
		servletMappings.add(servletMapping);

		Servlet servlet2 = WebFactory.eINSTANCE.createServlet();
		servlet2.setServletName("TestServlet");
		servlet2.setServletClass("test.TestServlet2");
		List<Servlet> servlets2 = descriptorToMerge.getServlets();
		servlets2.add(servlet2);

		UrlPatternType urlPattern2 = JavaeeFactory.eINSTANCE.createUrlPatternType();
		urlPattern2.setValue("/pattern2");
		ServletMapping servletMapping2 = WebFactory.eINSTANCE.createServletMapping();
		servletMapping2.setServletName("TestServlet");
		List<UrlPatternType> urlPatterns2 = servletMapping2.getUrlPatterns();
		urlPatterns2.add(urlPattern2);
		List<ServletMapping> servletMappings2 = descriptorToMerge.getServletMappings();
		servletMappings2.add(servletMapping2);

		WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, ModelElementMerger.COPY);
		result.process();
		Assert.assertNotNull(descriptorBase.getServlets());
		Assert.assertEquals(1, descriptorBase.getServlets().size());
		Assert.assertEquals(1, descriptorToMerge.getServlets().size());
		Assert.assertEquals(1, descriptorToMerge.getServletMappings().size());
		Servlet s = descriptorBase.getServlets().get(0);
		Assert.assertNotNull(s);
		Assert.assertTrue(s.getServletName().equals("TestServlet"));
		Assert.assertNotNull(descriptorBase.getServletMappings());
		Assert.assertEquals(2, descriptorBase.getServletMappings().size());
		ServletMapping m = descriptorBase.getServletMappings().get(0);
		Assert.assertTrue(m.getServletName().equals("TestServlet"));
		Assert.assertNotNull(m.getUrlPatterns());
		Assert.assertEquals(1, m.getUrlPatterns().size());
		Assert.assertTrue(m.getUrlPatterns().get(0).getValue().equals("/pattern1"));
		m = descriptorBase.getServletMappings().get(1);
		Assert.assertTrue(m.getServletName().equals("TestServlet"));
		Assert.assertNotNull(m.getUrlPatterns());
		Assert.assertEquals(1, m.getUrlPatterns().size());
		Assert.assertTrue(m.getUrlPatterns().get(0).getValue().equals("/pattern2"));
	}

}
