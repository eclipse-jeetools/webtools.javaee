/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal;

import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jee.ui.internal.messages"; //$NON-NLS-1$
	

	private Messages() {
		// Do not instantiate
	}
	
	public static String INVALID_DEP_DESC_SELECTION_TITLE;
	public static String INFORM_INVALID_DEP_DESC_SELECTION;
	public static String DEPLOYMENT_DESCRIPTOR;
	public static String SESSION_BEANS;
	public static String ENTITY_BEANS;
	public static String MESSAGE_DRIVEN_BEANS;
	public static String MessageAdapterNode_MESSAGE_ADAPTER;
	public static String MessageListenerNode_MessageListener;
	public static String ActicationSpecNode_ActivationSpec;
	public static String ACTIVATION_CONFIG_PROPERTIES;
	public static String ENTERPRISE_BEAN_CLASS_DESCRIPTION;
	
	public static String SERVLET_ITEM_PROVIDER;
	public static String LISTENER_ITEM_PROVIDER;
	public static String FILTERS_ITEM_PROVIDER;
	public static String FILTER_MAPPING_ITEM_PROVIDER;
	public static String SERVLET_MAPPING_ITEM_PROVIDER;
	public static String ERROR_PAGES_ITEM_PROVIDER;
	public static String WELCOME_PAGES_ITEM_PROVIDER;
	public static String CONTEXT_PARAMS_ITEM_PROVIDER;
	
	
	
	
	public static String REFERENCES;
	public static String BUNDLED_LIBRARIES_NODE;
	public static String LIBRARY_DIRECTORY;
	public static String EAR_MODULES_NODE;
	public static String EJBLOCAL_REFERENCES;
	public static String EJB_REFERENCES;
	public static String ENVIROMENT_ENTRIES;
	public static String RESOURCE_ENVIROMENT_ENTRIES;
	public static String RESOURCE_REFERENCES;
	public static String SERVICE_REFERENCES;
	public static String AddProjectToEARDropAssistant_The_dragged_project_cannot_be_added_;
	public static String AddProjectToEARDropAssistant_The_project_0_cannot_be_accesse_;
	public static String AddProjectToEARDropAssistant_Could_not_add_module_to_Enterprise_;
	public static String AddModuleToEarDropAssistant_Adding_module_to_ea_;
	public static String ImportJEEModuleDropAssistant_Importing_Java_Enterprise_Edition_artifacts;
	public static String ImportJEEModuleDropAssistant_Could_not_recognize_extension_0_;
	public static String InboundRaNode_Inbound_Resource_adapter;
	
	public static String ConnectorLabelProvider_ADMIN_OBJECT;
	public static String ConnectorLabelProvider_CONFIG_PROPERTY;
	public static String ConnectorLabelProvider_ConnectionDefinition;
	public static String ConnectorLabelProvider_LICENSE_ARTIFACT;
	public static String ConnectorLabelProvider_RequiredConfigProperty;
	public static String GroupRaContentProvider_RESOURCE_ADAPTER;
	public static String OutboundRaNode_OutboundResourceAdapter;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
