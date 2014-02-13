/*******************************************************************************
 * Copyright (c) 2011, 2014 Oracle and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Ludovic Champenois ludo@java.net
 * IBM - Async and Non-persistent support
 *******************************************************************************/

package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.MAPPED_NAME;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.j2ee.internal.common.operations.Method;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

@SuppressWarnings("restriction")
public class AddEjbTimerTemplateModel extends CreateEnterpriseBeanTemplateModel {

	public static final String QUALIFIED_SCHEDULE = "javax.ejb.Schedule"; //$NON-NLS-1$
	public static final String QUALIFIED_TIMER = "javax.ejb.Timer"; //$NON-NLS-1$
	public static final String QUALIFIED_STATELESS = "javax.ejb.Stateless"; //$NON-NLS-1$

	public static final String SCHEDULED_TIMEOUT = "scheduledTimeout"; //$NON-NLS-1$

	public AddEjbTimerTemplateModel(IDataModel adataModel) {
		super(adataModel);
	}

	@Override
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();

		collection.add(QUALIFIED_SCHEDULE);
		collection.add(QUALIFIED_STATELESS);
		collection.add(QUALIFIED_TIMER);

		return collection;
	}

	public Map<String, String> getClassAnnotationParams() {
		Map<String, String> result = new Hashtable<String, String>();

		String dispName = getProperty(EJB_NAME).trim();
		if (!dispName.equals(getClassName()) && (dispName.length() > 0)) {
			result.put(ATT_NAME, QUOTATION_STRING + dispName + QUOTATION_STRING);
		}
		String mappedName = getProperty(MAPPED_NAME).trim();
		if (mappedName != null && mappedName.length() > 0) {
			result.put(ATT_MAPPED_NAME, QUOTATION_STRING + mappedName + QUOTATION_STRING);
		}

		return result;
	}

	@Override
	public String getProperty(String propertyName) {
		return dataModel.getStringProperty(propertyName);
	}

	@Override
	public Collection<Method> getUnimplementedMethods() {
		Collection<Method> unimplementedMethods = super
				.getUnimplementedMethods();
		Iterator<Method> iterator = unimplementedMethods.iterator();

		while (iterator.hasNext()) {
			Method method = iterator.next();
			if (SCHEDULED_TIMEOUT.equals(method.getName())) {
				iterator.remove();
			}
		}

		return unimplementedMethods;
	}
	
	public boolean isNonPersistent() {
		return (Boolean) dataModel.getProperty(AddEjbTimerDataModelProvider.NON_PERSISTENT);
	}

}
