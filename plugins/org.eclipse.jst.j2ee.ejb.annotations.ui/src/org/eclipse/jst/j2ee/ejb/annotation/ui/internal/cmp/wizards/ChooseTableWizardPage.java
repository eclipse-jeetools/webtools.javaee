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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.ControlContentAssistHelper;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.JavaTypeCompletionProcessor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.CMPAttributeDelegate;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IContainerManagedEntityBeanDataModelProperties;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;

public class ChooseTableWizardPage extends DataModelWizardPage {

	protected final Image fChecked = EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/checked.gif").createImage();

	protected final Image fUnchecked = EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/unchecked.gif").createImage();

	private String[] sqlTypes = CMPUtils.sqlTypes;

	private Combo catalogButton;

	private Table attributeTable;

	private TableViewer attributeTableViewer;

	private Button add;

	private Button remove;

	private ArrayList tableList = new ArrayList();

	public void dispose() {
		super.dispose();
		fUnchecked.dispose();
		fChecked.dispose();
	}

	public ChooseTableWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IEJBAnnotationConstants.ADD_CMP_TABLE_WIZARD_PAGE_DESC);
		this.setTitle(IEJBAnnotationConstants.ADD_CMP_TABLE_WIZARD_PAGE_TITLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[] { IContainerManagedEntityBeanDataModelProperties.ATTRIBUTES,
				IContainerManagedEntityBeanDataModelProperties.TABLE };
	}

	protected List getAttributes() {
		List attr = (List) this.getDataModel().getProperty(IContainerManagedEntityBeanDataModelProperties.ATTRIBUTES);
		if (!this.getDataModel().isPropertySet(IContainerManagedEntityBeanDataModelProperties.ATTRIBUTES)) {
			this.getDataModel().setProperty(IContainerManagedEntityBeanDataModelProperties.ATTRIBUTES, attr);
		}
		return attr;
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);

		Composite group = new Composite(composite, SWT.NULL);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

		createCatalogGroup(group);
		attributeTable = createTable(composite);
		attributeTableViewer = createTableViewer(attributeTable);

		return composite;
	}

	protected void createCatalogGroup(Composite composite) {

		// description
		Label catalogLabel = new Label(composite, SWT.LEFT);
		catalogLabel.setText(IEJBAnnotationConstants.CMP_TABLE_CHOOSE_TABLE);
		catalogLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		if (((AddContainerManagedEntityEjbWizard) this.getWizard()).isJavaBean()) {
			catalogButton = new Combo(composite, SWT.DROP_DOWN);

		} else {
			catalogButton = new Combo(composite, SWT.DROP_DOWN);// |
			// SWT.READ_ONLY)
		}
		synchHelper.synchCombo(catalogButton, IContainerManagedEntityBeanDataModelProperties.TABLE, null);
		catalogButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_VERTICAL));
		catalogButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
				if (tableList.size() >= 1 && catalogButton.getSelectionIndex() != -1) {
					fillTableWith((String) tableList.get(catalogButton.getSelectionIndex()));
					attributeTable.removeAll();
					attributeTable.setData(getAttributes());
					attributeTableViewer.setInput(getAttributes());
					attributeTableViewer.refresh();
				}
			}

			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				this.widgetDefaultSelected(e);
			}
		});
		updateTables();

	}

	private void fillTableWith(String tableName) {
		Connection connection = null;
		ArrayList attributes = new ArrayList();
		if (!((AddContainerManagedEntityEjbWizard) this.getWizard()).isJavaBean()) {
			try {
				IConnectionProfile connectionInfo = getConnectionInfo();
				if (connectionInfo == null)
					return;
//				String passw = connectionInfo.getPassword();
//				if (passw == null || passw.length() == 0)
//					passw = "password";
//				connectionInfo.setPassword(passw);

				IStatus connectionStatus = connectionInfo.connect();

				if (! connectionStatus.isOK())
					return;
				IConnection dbConnection = connectionInfo.getManagedConnection("java.sql.Connection").getConnection();
				connection = (Connection)dbConnection.getRawConnection();
				
				DatabaseMetaData metaData = connection.getMetaData();
				String schema = null;
				int scIndex = tableName.indexOf(".");
				if (scIndex >= 0) {
					schema = tableName.substring(0, scIndex);
					tableName = tableName.substring(scIndex + 1);
				}

				ResultSet columns = metaData.getColumns(null, schema, tableName, "%");
				while (columns.next()) {
					int datasize = columns.getInt("COLUMN_SIZE");
					int digits = columns.getInt("DECIMAL_DIGITS");

					CMPAttributeDelegate atr = new CMPAttributeDelegate();
					atr.setName(columns.getString("COLUMN_NAME").toLowerCase());
					atr.setAttributeType(CMPUtils.getAttributeType(columns.getInt("DATA_TYPE")));
					atr.setJdbcType(CMPUtils.getSqlType(columns.getInt("DATA_TYPE")));
					atr.setSqlType(columns.getString("TYPE_NAME"));
					atr.setColumnName(columns.getString("COLUMN_NAME"));
					atr.setDecimalDigits(digits);
					atr.setColumnSize(datasize);
					attributes.add(atr);
				}
				ResultSet primaryKeys = metaData.getPrimaryKeys(null, schema, tableName);
				while (primaryKeys.next()) {
					String key = primaryKeys.getString("COLUMN_NAME");
					Iterator iterator = attributes.iterator();
					while (iterator.hasNext()) {
						CMPAttributeDelegate attr = (CMPAttributeDelegate) iterator.next();
						if (key.equals(attr.getColumnName()))
							attr.setKey(true);
					}
				}

			} catch (Throwable ex) {
				ex.printStackTrace();
				MessageDialog.openError(this.getShell(), IEJBAnnotationConstants.CANNOT_CONNECT,
						IEJBAnnotationConstants.CHECK_PROPERTIES);
			} finally {
//				if (connection != null)
//					try {
//						connection.close();
//					} catch (SQLException e) {
//					}
			}
		}
		this.getDataModel().setProperty(IContainerManagedEntityBeanDataModelProperties.ATTRIBUTES, attributes);

	}

	public void updateTables() {
		if (catalogButton == null)
			return;
		if (((AddContainerManagedEntityEjbWizard) this.getWizard()).isJavaBean()) {
			catalogButton.removeAll();
			return;
		}
		Connection connection = null;
		tableList = new ArrayList();
		try {
			IConnectionProfile connectionInfo = getConnectionInfo();
			if (connectionInfo == null)
				return;

			IStatus connectionStatus = connectionInfo.connect();

			if (! connectionStatus.isOK())
				return;
			
			IConnection dbConnection = connectionInfo.getManagedConnection("java.sql.Connection").getConnection();
			connection = (Connection)dbConnection.getRawConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			String[] names = { "TABLE" };
			ResultSet rs = metaData.getSchemas();
			ResultSet tableNames = null;
			while (rs.next()) {

				tableNames = metaData.getTables(null, rs.getString(1), "%", names);
				while (tableNames.next()) {
					tableList.add(rs.getString(1) + "." + tableNames.getString("TABLE_NAME"));
				}
			}

			ResultSet sqlTypeNames = metaData.getTypeInfo();
			ArrayList types = new ArrayList();
			while (sqlTypeNames.next()) {
				types.add(sqlTypeNames.getString("TYPE_NAME"));
			}

			sqlTypeNames = metaData.getUDTs(null, "%", "%", null);
			while (sqlTypeNames.next()) {
				types.remove(sqlTypeNames.getString("TYPE_NAME"));
			}
			sqlTypes = (String[]) types.toArray(new String[types.size()]);

		} catch (Throwable ex) {
			ex.printStackTrace();
			MessageDialog.openError(this.getShell(), IEJBAnnotationConstants.CANNOT_CONNECT, IEJBAnnotationConstants.CHECK_PROPERTIES);
		} finally {
//			if (dbConnection != null)
//				try {
//					connection.close();
//				} catch (SQLException e) {
//				}
		}
		catalogButton.setItems((String[]) tableList.toArray(new String[tableList.size()]));
		catalogButton.select(0);
		if (tableList.size() > 0) {
			fillTableWith((String) tableList.get(0));
			catalogButton.setEnabled(true);
			if (attributeTableViewer != null)
				attributeTableViewer.refresh();
		}

		if (remove != null)
			remove.setEnabled(false);

	}

	public IConnectionProfile getConnectionInfo() {
		return ((AddContainerManagedEntityEjbWizard) getWizard()).getConnectionInfo();
	}

	/**
	 * Create the Table
	 */
	private Table createTable(Composite parent) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		GridLayout layout;
		GridData gridData;
		Group libraryPanel = new Group(parent, SWT.NONE);
		libraryPanel.setText(IEJBAnnotationConstants.CMP_MANAGED_FIELDS);
		layout = new GridLayout(2, false);
		libraryPanel.setLayout(layout);
		gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 2;
		gridData.heightHint = 300;
		libraryPanel.setLayoutData(gridData);

		attributeTable = new Table(libraryPanel, style);
		gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 2;
		attributeTable.setLayoutData(gridData);

		attributeTable.setLinesVisible(true);
		attributeTable.setHeaderVisible(true);

		// 1st column with image/checkboxes - NOTE: The SWT.CENTER has no
		// effect!!
		TableColumn column = new TableColumn(attributeTable, SWT.LEFT, 0);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_NAME);
		column.setWidth(60);

		// 2 column with task Description
		column = new TableColumn(attributeTable, SWT.LEFT, 1);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_COLUMN);
		column.setWidth(60);

		column = new TableColumn(attributeTable, SWT.LEFT, 2);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_TYPE);
		column.setWidth(120);

		column = new TableColumn(attributeTable, SWT.LEFT, 3);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_JDBCTYPE);
		column.setWidth(80);

		column = new TableColumn(attributeTable, SWT.LEFT, 4);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_SQLTYPE);
		column.setWidth(80);

		// 2 column with task Description
		column = new TableColumn(attributeTable, SWT.LEFT, 5);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_COLUMNSIZE);
		column.setWidth(60);

		column = new TableColumn(attributeTable, SWT.LEFT, 6);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_DECIMALDIGITS);
		column.setWidth(60);

		column = new TableColumn(attributeTable, SWT.CENTER, 7);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_READONLY);
		column.setWidth(80);

		column = new TableColumn(attributeTable, SWT.CENTER, 8);
		column.setText(IEJBAnnotationConstants.ATTRIBUTE_ISKEY);
		column.setWidth(80);
		addAttributeButtons(libraryPanel);
		attributeTable.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				attributeTable.getSelectionCount();
				remove.setEnabled(attributeTable.getSelectionCount() > 0);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);

			}
		});
		return attributeTable;
	}

	private void addAttributeButtons(Composite parent) {
		add = new Button(parent, SWT.PUSH);
		add.setText(IEJBAnnotationConstants.ATTRIBUTE_ADD); //$NON-NLS-1$
		GridData data = new GridData();
		data.horizontalSpan = 1;
		data.horizontalAlignment = GridData.BEGINNING;
		add.setLayoutData(data);
		add.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				CMPAttributeDelegate atr = new CMPAttributeDelegate();
				getAttributes().add(atr);
				ChooseTableWizardPage.this.validatePage();
				attributeTableViewer.refresh();
			}
		});
		remove = new Button(parent, SWT.PUSH);
		remove.setText(IEJBAnnotationConstants.ATTRIBUTE_REMOVE); //$NON-NLS-1$
		data = new GridData();
		data.horizontalSpan = 1;
		data.horizontalAlignment = GridData.BEGINNING;
		remove.setLayoutData(data);
		remove.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				TableItem ti = attributeTable.getSelection()[0];
				CMPAttributeDelegate attributeDelegate = (CMPAttributeDelegate) ti.getData();
				getAttributes().remove(attributeDelegate);
				ChooseTableWizardPage.this.validatePage();
				attributeTableViewer.refresh();
				remove.setEnabled(false);

			}
		});
		remove.setEnabled(false);
	}

	/**
	 * Create the TableViewer
	 */
	private TableViewer createTableViewer(Table table) {

		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setUseHashlookup(true);

		tableViewer.setColumnProperties(columnNames);
		tableViewer.setLabelProvider(new FieldLabelProvider());
		tableViewer.setContentProvider(new FieldContentProvider());

		CellEditor[] editors = new CellEditor[columnNames.length];

		TextCellEditor textEditor = new TextCellEditor(table);
		((Text) textEditor.getControl()).setTextLimit(200);
		editors[0] = textEditor;

		TextCellEditor textEditor2 = new TextCellEditor(table);
		((Text) textEditor2.getControl()).setTextLimit(200);
		editors[1] = textEditor2;

		// //$NON-NLS-1$
		//
		TextCellEditor textEditor3 = new TextCellEditor(table);
		((Text) textEditor3.getControl()).setTextLimit(200);
		IProject project = ((AddContainerManagedEntityEjbWizard) this.getWizard()).getDefaultEjbProject();
		IPackageFragmentRoot[] sources = J2EEProjectUtilities.getSourceContainers(project);
		if (sources != null && sources.length >= 1) {
			JavaTypeCompletionProcessor fFieldTypeCompletionProcessor = new JavaTypeCompletionProcessor(false, false);
			fFieldTypeCompletionProcessor.setPackageFragment(sources[0].getPackageFragment(""));
			ControlContentAssistHelper.createTextContentAssistant(((Text) textEditor3.getControl()), fFieldTypeCompletionProcessor);
		}
		editors[2] = textEditor3;

		editors[3] = new ComboBoxCellEditor(table, CMPUtils.jdbcTypes, SWT.READ_ONLY);

		// TextCellEditor textEditor4 = new TextCellEditor(table);
		// ((Text) textEditor4.getControl()).setTextLimit(100);
		// editors[4] = textEditor4;
		editors[4] = new ComboBoxCellEditor(table, sqlTypes, SWT.READ_ONLY);

		TextCellEditor textEditor5 = new TextCellEditor(table);
		((Text) textEditor5.getControl()).setTextLimit(200);
		editors[5] = textEditor5;

		TextCellEditor textEditor6 = new TextCellEditor(table);
		((Text) textEditor6.getControl()).setTextLimit(200);
		editors[6] = textEditor6;

		editors[7] = new CheckboxCellEditor(table, SWT.CENTER);

		editors[8] = new CheckboxCellEditor(table, SWT.CENTER);

		// Assign the cell editors to the viewer
		tableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		tableViewer.setCellModifier(new FieldCellModifier());
		tableViewer.setInput(getAttributes());
		return tableViewer;
	}

	protected static String[] columnNames = { IEJBAnnotationConstants.ATTRIBUTE_NAME, IEJBAnnotationConstants.ATTRIBUTE_COLUMN,
			IEJBAnnotationConstants.ATTRIBUTE_TYPE, IEJBAnnotationConstants.ATTRIBUTE_SQLTYPE,
			IEJBAnnotationConstants.ATTRIBUTE_JDBCTYPE, IEJBAnnotationConstants.ATTRIBUTE_COLUMNSIZE,
			IEJBAnnotationConstants.ATTRIBUTE_DECIMALDIGITS, IEJBAnnotationConstants.ATTRIBUTE_READONLY,
			IEJBAnnotationConstants.ATTRIBUTE_ISKEY };

	protected int getColumnIndex(String columName) {
		if (columName == null)
			return -1;
		for (int i = 0; i < columnNames.length; i++) {
			String col = columnNames[i];
			if (columName.equals(col))
				return i;

		}
		return -1;
	}

	public class FieldContentProvider implements IStructuredContentProvider {
		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public Object[] getElements(Object parent) {
			return getAttributes().toArray();
		}
	}

	public class FieldLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {

			CMPAttributeDelegate fieldMapping = (CMPAttributeDelegate) obj;
			switch (index) {
			case 0: // Local
				return fieldMapping.getName();
			case 1: // Local
				return fieldMapping.getColumnName();
			case 2: // Local
				return fieldMapping.getAttributeType();
			case 3: // Local
				return fieldMapping.getJdbcType();
			case 4: // Local
				return fieldMapping.getSqlType();
			case 5:
				if (fieldMapping.isVariableSizedType()) {
					return "" + fieldMapping.getColumnSize();
				}
				return "";
			case 6:
				if (fieldMapping.isDecimal()) {
					return "" + fieldMapping.getDecimalDigits();
				}
				return "";
			case 7: // Local
				return "";
			case 8: // Local
				return "";
			}
			return "";
		}

		public String getColumnText(Viewer v, Object obj, int index) {
			return getColumnText(obj, index);
		}

		public Image getColumnImage(Object obj, int index) {
			CMPAttributeDelegate fieldMapping = (CMPAttributeDelegate) obj;
			switch (index) {
			case 7: // Local
				if (fieldMapping.isReadOnly())
					return fChecked;
				else
					return fUnchecked;

			case 8: // Local
				if (fieldMapping.isKey())
					return fChecked;
				else
					return fUnchecked;
			case 9: // Local
				if (fieldMapping.isTransient())
					return fChecked;
				else
					return fUnchecked;
			}
			return null;
		}

		public Image getColumnImage(Viewer v, Object obj, int index) {
			return getColumnImage(obj, index);
		}
	}

	public class FieldCellModifier implements ICellModifier {

		public boolean canModify(Object element, String property) {
			CMPAttributeDelegate attribute = (CMPAttributeDelegate) element;
			if (IEJBAnnotationConstants.ATTRIBUTE_DECIMALDIGITS.equals(property)) {
				if (attribute.isDecimal())
					return true;
				return false;
			}
			if (IEJBAnnotationConstants.ATTRIBUTE_COLUMNSIZE.equals(property)) {
				if (attribute.isVariableSizedType())
					return true;
				return false;
			}
			return true;
		}

		public Object getValue(Object element, String property) {
			int columnIndex = getColumnIndex(property);
			CMPAttributeDelegate fieldMapping = (CMPAttributeDelegate) element;

			Object result = "";
			switch (columnIndex) {

			case 0:
				return fieldMapping.getName();

			case 1:
				return fieldMapping.getColumnName();
			case 2:
				return fieldMapping.getAttributeType();
			case 3:
				String val = fieldMapping.getJdbcType();
				for (int i = 0; i < CMPUtils.jdbcTypes.length; i++) {
					String t = CMPUtils.jdbcTypes[i];
					if (t.equals(val))
						return new Integer(i);
				}
				return new Integer(0);
			case 4:
				val = fieldMapping.getSqlType();
				for (int i = 0; i < sqlTypes.length; i++) {
					String t = sqlTypes[i];
					if (t.equals(val))
						return new Integer(i);
				}
				return new Integer(0);
			case 5:
				if (fieldMapping.isVariableSizedType()) {
					return "" + fieldMapping.getColumnSize();
				}
				return "";
			case 6:
				if (fieldMapping.isDecimal()) {
					return "" + fieldMapping.getDecimalDigits();
				}
				return "";
			case 7:
				return Boolean.valueOf(fieldMapping.isReadOnly());
			case 8:
				return Boolean.valueOf(fieldMapping.isKey());
			case 9:
				return Boolean.valueOf(fieldMapping.isTransient());
			default:
				result = "";
			}
			return result;
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
		 *      java.lang.String, java.lang.Object)
		 */
		public void modify(Object element, String property, Object value) {
			int columnIndex = getColumnIndex(property);
			TableItem tItem = (TableItem) element;
			CMPAttributeDelegate fieldMapping = (CMPAttributeDelegate) tItem.getData();

			switch (columnIndex) {

			case 0:
				fieldMapping.setName((String) value);
				break;

			case 1:
				fieldMapping.setColumnName((String) value);
				break;
			case 2:
				fieldMapping.setAttributeType((String) value);
				break;
			case 3:
				fieldMapping.setJdbcType(CMPUtils.jdbcTypes[((Integer) value).intValue()]);
				int sqli = CMPUtils.getSqlTypeFromJDBC(((Integer) value).intValue());
				if (sqli >= 0) {
					fieldMapping.setSqlType(sqlTypes[sqli]);
					tItem.getParent().redraw();
				}
				break;
			case 4:
				String sqlType = sqlTypes[((Integer) value).intValue()];
				fieldMapping.setSqlType(sqlType);
				break;
			case 5:
				if (fieldMapping.isVariableSizedType()) {
					int colSize = Integer.parseInt((String) value);
					fieldMapping.setColumnSize(colSize);
				}
				break;
			case 6:
				if (fieldMapping.isDecimal()) {
					int decDigits = Integer.parseInt((String) value);
					fieldMapping.setDecimalDigits(decDigits);
				}
				break;
			case 7:
				boolean readValue = ((Boolean) value).booleanValue();
				fieldMapping.setReadOnly(readValue);
				break;
			case 8:
				boolean newValue = ((Boolean) value).booleanValue();
				fieldMapping.setKey(newValue);
				break;
			case 9:
				fieldMapping.setTransient(((Boolean) value).booleanValue());
				break;
			}
			ChooseTableWizardPage.this.validatePage();
			attributeTableViewer.refresh();
		}

	}

}