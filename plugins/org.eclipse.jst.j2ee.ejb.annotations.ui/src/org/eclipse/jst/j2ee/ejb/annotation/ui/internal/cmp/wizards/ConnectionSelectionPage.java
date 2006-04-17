/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.cmp.wizards;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.rdb.connection.internal.ui.util.resource.ResourceLoader;
import org.eclipse.wst.rdb.connection.internal.ui.wizards.ConnectionDisplayProperty;
import org.eclipse.wst.rdb.connection.internal.ui.wizards.shared.UserIdentification;
import org.eclipse.wst.rdb.internal.core.RDBCorePlugin;
import org.eclipse.wst.rdb.internal.core.connection.ConnectionInfo;
import org.eclipse.wst.rdb.server.internal.ui.wizards.NewConnectionWizard;

public class ConnectionSelectionPage extends DataModelWizardPage implements SelectionListener {

	private static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;
	protected boolean myFirstTime = true;
	protected Button reconnectButton;
	private Button newConnectionButton;

	private boolean connected;
	private List existingConnectionsList;
	private Hashtable existingConnections;

	private Label propertiesLabel;

	private Table connectionPropertiesTable;

	public ConnectionSelectionPage(IDataModel model, String pageName, String title, ImageDescriptor titleImage) {
		super(model, pageName, title, titleImage);
	}

	public ConnectionSelectionPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IEJBAnnotationConstants.CMP_CONNECTION_PAGE_DESC);
		this.setTitle(IEJBAnnotationConstants.CMP_CONNECTION_PAGE_TITLE);
	}

	public Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		newConnectionButton = new Button(composite, SWT.PUSH);
		newConnectionButton.setText(IEJBAnnotationConstants.CMP_CONNECTION_NEW_BUTTON); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		newConnectionButton.setLayoutData(gd);

		Composite indentationComposite = new Composite(composite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.verticalSpacing = 5;
		indentationComposite.setLayout(layout);
		indentationComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Group existingConnectionsGroup = new Group(indentationComposite, SWT.NONE);
		existingConnectionsGroup.setText(IEJBAnnotationConstants.CMP_CONNECTION_AVAILABLE); //$NON-NLS-1$
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		existingConnectionsGroup.setLayout(layout);
		gd = new GridData(GridData.FILL_BOTH);
		existingConnectionsGroup.setLayoutData(gd);

		existingConnectionsList = new List(existingConnectionsGroup, SWT.BORDER | SWT.V_SCROLL);
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		gd.heightHint = 100;
		existingConnectionsList.setLayoutData(gd);

		propertiesLabel = new Label(existingConnectionsGroup, SWT.NONE);
		propertiesLabel.setText(IEJBAnnotationConstants.CMP_CONNECTION_PROPERTIES); //$NON-NLS-1$
		gd = new GridData();
		propertiesLabel.setLayoutData(gd);

		connectionPropertiesTable = new Table(existingConnectionsGroup, SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		connectionPropertiesTable.setLayoutData(gd);
		connectionPropertiesTable.setLinesVisible(true);
		connectionPropertiesTable.setHeaderVisible(true);

		TableColumn tc1 = new TableColumn(connectionPropertiesTable, SWT.NONE);
		tc1.setText(IEJBAnnotationConstants.CMP_CONNECTION_PROPERTY); //$NON-NLS-1$
		tc1.setResizable(true);
		tc1.setWidth(140);

		TableColumn tc2 = new TableColumn(connectionPropertiesTable, SWT.NONE);
		tc2.setText(IEJBAnnotationConstants.CMP_CONNECTION_VALUE); //$NON-NLS-1$
		tc2.setResizable(true);
		tc2.setWidth(250);

		initializeDialogUnits(composite);
		setControl(composite);

		newConnectionButton.addListener(SWT.Selection, this);
		existingConnectionsList.addListener(SWT.Selection, this);

		newConnectionButton.setSelection(true);
		initializeValues();
		setPageComplete(true);
		addReconnectButton(composite);
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */

	public void handleEvent(Event event) {
		Widget source = event.widget;
		if (source == newConnectionButton) {
			ConnectionInfo[] infos = RDBCorePlugin.getDefault().getConnectionManager().getAllNamedConnectionInfo();
			java.util.List nameList = new ArrayList(infos.length);
			for (int i = 0, n = infos.length; i < n; i++) {
				if (!nameList.contains(infos[i].getName())) {
					nameList.add(infos[i].getName().toLowerCase());
				}
			}
			String[] names = (String[]) nameList.toArray(new String[nameList.size()]);

			NewConnectionWizard wizard = new NewConnectionWizard(null, true);
			wizard.init(PlatformUI.getWorkbench(), null);
			wizard.setNeedsProgressMonitor(false);
			wizard.setExistingConnectionNames(Arrays.asList(names));
			WizardDialog dialog = new WizardDialog(this.getShell(), wizard);
			dialog.create();
			dialog.open();
			initializeValues();
		} else if (source == existingConnectionsList) {
			updateConnectionProperties();
			setPageComplete(true);
			reconnectButton.setEnabled(true);
		}

		reconnectButton.setEnabled(true);
		isValidConnection();
	}

	/**
	 * Adds the reconnection button
	 * 
	 * @param parent
	 *            the parent composite
	 */
	private void addReconnectButton(Composite parent) {
		reconnectButton = new Button(parent, SWT.PUSH);
		reconnectButton.setText(resourceLoader.queryString("CUI_CONN_SELECT_EXT_RECONNECT_BTN_LBL")); //$NON-NLS-1$
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.horizontalAlignment = GridData.BEGINNING;
		reconnectButton.setLayoutData(data);
		reconnectButton.addSelectionListener(this);
	}

	public boolean isConnected() {
		return connected;
	}

	/**
	 * Calls when default selection occurs (eg return in Text)
	 * 
	 * @param evt
	 *            the Selection event
	 */
	public void widgetDefaultSelected(SelectionEvent evt) {
		// ignore
	}

	/**
	 * Calls when selection occurs
	 * 
	 * @param evt
	 *            the selection event
	 */
	public void widgetSelected(SelectionEvent evt) {
		Object source = evt.getSource();
		if (source == reconnectButton) {
			ConnectionInfo info = getSelectedConnection();
			UserIdentification userDialog = new UserIdentification(info.getUserName());
			if (userDialog.open() == Window.OK) {
				String us = userDialog.getUserNameInformation();
				String pa = userDialog.getPasswordInformation();
				info.setUserName(us);
				info.setPassword(pa);
				// try to connect
				try {
					this.getSelectedConnection().setPassword(pa);
					this.getSelectedConnection().setUserName(us);
					this.getSelectedConnection().connect();
					reconnectButton.setEnabled(false);
					connected = true;
				} catch (Exception ex) {
					reconnectButton.setEnabled(true);
					connected = false;
					MessageDialog.openError(this.getShell(), resourceLoader.queryString("CUI_CONN_SELECT_EXT_ERROR_DIALOG_TITLE"), //$NON-NLS-1$
							ex.getMessage());
				}
				isValidConnection();
			}
		}
	}

	private void initializeValues() {
		existingConnectionsList.removeAll();
		ConnectionInfo[] connInfos = getConnectionsToDisplay();
		if (connInfos != null) {
			existingConnections = new Hashtable();
			java.util.List sortedConnections = Arrays.asList(connInfos);
			sortConnections(sortedConnections);
			Iterator connections = sortedConnections.iterator();
			while (connections.hasNext()) {
				ConnectionInfo con = (ConnectionInfo) connections.next();
				existingConnections.put(con.getName(), con);
				existingConnectionsList.add(con.getName());
			}
		}

		if (existingConnectionsList.getItemCount() > 0) {
			existingConnectionsList.select(0);
			updateConnectionProperties();
		}
		newConnectionButton.setSelection(true);
	}

	protected void sortConnections(java.util.List connections) {
		Comparator c = new Comparator() {
			public int compare(Object o1, Object o2) {
				String s1 = ((ConnectionInfo) o1).getName();
				String s2 = ((ConnectionInfo) o2).getName();
				return s1.compareToIgnoreCase(s2);
			}
		};

		Collections.sort(connections, c);
	}

	/**
	 * Returns either the existing connection selected by the user. If the user
	 * has indicated they would like to use a new connection then this method
	 * returns null.
	 * 
	 * @return A ConnectionInfo object representing the users selection in the
	 *         wizard page
	 */
	public ConnectionInfo getSelectedConnection() {
		if (existingConnections == null || existingConnectionsList == null || existingConnectionsList.getSelection().length == 0)
			return null;
		ConnectionInfo connection = (ConnectionInfo) existingConnections.get(existingConnectionsList.getSelection()[0]);
		return connection;
	}

	/**
	 * Returns the list of existing connections to display to the user. Override
	 * this method to provide a filtered list of connections.
	 * 
	 * @return A array of ConnectionInfo objects that should be displayed in the
	 *         existing connections list
	 */
	protected ConnectionInfo[] getConnectionsToDisplay() {
		return RDBCorePlugin.getDefault().getConnectionManager().getAllNamedConnectionInfo();
	}

	/**
	 * Refreshes this page
	 */
	public void refresh() {
		reconnectButton.setEnabled(true);
		connected = false;
		initializeValues();
		isValidConnection();
	}

	private void updateConnectionProperties() {
		connectionPropertiesTable.removeAll();
		if (existingConnectionsList.getSelectionIndex() > -1) {
			ConnectionInfo selectedConnection = (ConnectionInfo) existingConnections.get((String) existingConnectionsList
					.getSelection()[0]);
			if (selectedConnection != null) {

				ConnectionDisplayProperty[] properties = getConnectionDisplayProperties(selectedConnection);
				if (properties != null) {
					int propertyCount = properties.length;
					for (int index = 0; index < propertyCount; index++) {
						TableItem tableItem = new TableItem(connectionPropertiesTable, SWT.NONE);
						tableItem.setText(new String[] { properties[index].getPropertyName(), properties[index].getValue() });
					}
				}
			}
		}
	}

	private ConnectionDisplayProperty[] getConnectionDisplayProperties(ConnectionInfo connectionInfo) {
		ConnectionDisplayProperty[] properties = null;
		Vector propertiesCollection = new Vector();

		propertiesCollection.add(new ConnectionDisplayProperty(resourceLoader
				.queryString("_UI_JDBC_DRIVER_CLASS_CONNECTION_PROPERTY_NAME"), connectionInfo.getDriverClassName())); //$NON-NLS-1$
		propertiesCollection.add(new ConnectionDisplayProperty(resourceLoader
				.queryString("_UI_CLASS_LOCATION_CONNECTION_PROPERTY_NAME"), connectionInfo.getLoadingPath())); //$NON-NLS-1$
		propertiesCollection.add(new ConnectionDisplayProperty(
				resourceLoader.queryString("_UI_URL_CONNECTION_PROPERTY_NAME"), connectionInfo.getURL())); //$NON-NLS-1$
		propertiesCollection.add(new ConnectionDisplayProperty(
				resourceLoader.queryString("_UI_USER_ID_CONNECTION_PROPERTY_NAME"), connectionInfo.getUserName())); //$NON-NLS-1$

		properties = new ConnectionDisplayProperty[propertiesCollection.size()];
		propertiesCollection.copyInto(properties);

		return properties;
	}

	protected String[] getValidationPropertyNames() {
		return null;
	}

	public boolean isPageComplete() {
		return connected;
		// return this.isValidConnection();
	}

	protected boolean isValidConnection() {
		Connection connection = null;
		try {
			ConnectionInfo connectionInfo = this.getSelectedConnection();
			if (connectionInfo != null) {
				connection = this.getSelectedConnection().connect();
			}
		} catch (Throwable e) {
		} finally {
			try {
				if (connection != null) {
					connection.close();
				} else {
					this.setErrorMessage(IEJBAnnotationConstants.ERR_CMP_NO_CONNECTION);
					connected = false;
					this.setPageComplete(false);
					return false;
				}
			} catch (SQLException e) {
				return false;
			}
		}
		this.setErrorMessage(null);
		setMessage(IEJBAnnotationConstants.ERR_CMP_CONNECTION_SUCCESS, IStatus.OK);
		connected = true;
		this.setPageComplete(true);
		return true;
	}

}