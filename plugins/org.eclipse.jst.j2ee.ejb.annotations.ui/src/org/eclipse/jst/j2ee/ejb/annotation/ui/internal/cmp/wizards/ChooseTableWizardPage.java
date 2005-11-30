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
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

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
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.swt.SWT;
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
import org.eclipse.wst.rdb.internal.core.connection.ConnectionInfo;

public class ChooseTableWizardPage extends DataModelWizardPage {

	protected static String[] jdbcTypes = { "ARRAY", "BIGINT", "BINARY", "BIT", "BLOB", "BOOLEAN", "CHAR", "CLOB", "DATALINK", "DATE",
			"DECIMAL", "DISTINCT", "DOUBLE", "FLOAT", "INTEGER", "JAVA_OBJECT", "LONGVARBINARY", "LONGVARCHAR", "NULL", "NUMERIC",
			"OTHER", "REAL", "REF", "SMALLINT", "STRUCT", "TIME", "TIMESTAMP", "TINYINT", "VARBINARY", "VARCHAR" };// EjbConstants.LABEL_JDBCTYPES;
	protected final Image fChecked = EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/checked.gif").createImage();// Util.createImage("icons/checked.gif",getClass());
	protected final Image fUnchecked = EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/unchecked.gif").createImage();// Util.createImage("icons/unchecked.gif",

	// getClass());

	class RowAttribute {
		public String name;
		public String attributeType;
		public String sqlType;
		public boolean isKey = false;
		public boolean isTransient = false;
		public String columnName;
		public String jdbcType;
		public boolean isReadOnly;
	}

	private String[] sqlTypes = { "BIGINT", "BINARY", "BIT", "BLOB", "BOOLEAN", "CHAR", "CLOB", "DATE", "DECIMAL", "DOUBLE", "FLOAT",
			"INTEGER", "JAVA_OBJECT", "LONGVARBINARY", "LONGVARCHAR", "NULL", "NUMERIC", "REAL", "REF", "SMALLINT", "STRUCT", "TIME",
			"TIMESTAMP", "TINYINT", "VARBINARY", "VARCHAR" };
	private Combo catalogButton;
	private Combo tableButton;
	private Table attributeTable;
	private TableViewer attributeTableViewer;
	private Button add;
	private Button remove;
	private ArrayList tableList = new ArrayList();

	private ArrayList attributeList = new ArrayList();

	public void dispose() {
		super.dispose();
		fUnchecked.dispose();
		fChecked.dispose();
	}

	public ChooseTableWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_DESC);
		this.setTitle(IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[] {};
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);

		Composite group = new Composite(composite, SWT.NULL);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));

		createCatalogGroup(group);
		attributeTable = createTable(composite);
		attributeTableViewer = createTableViewer(attributeTable);

		return composite;
	}

	protected void createCatalogGroup(Composite composite) {

		// description
		Label catalogLabel = new Label(composite, SWT.LEFT);
		catalogLabel.setText("Choose Table:");
		catalogLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		catalogButton = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		updateTables();
		catalogButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		catalogButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
				fillTableWith((String) tableList.get(catalogButton.getSelectionIndex()));
				attributeTable.removeAll();
				attributeTable.setData(attributeList);
				attributeTableViewer.setInput(attributeList);
				attributeTableViewer.refresh();
			}

			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				this.widgetDefaultSelected(e);
			}
		});

	}

	private void fillTableWith(String tableName) {
		attributeList = new ArrayList();
		Connection connection = null;
		try {
			ConnectionInfo connectionInfo = getConnectionInfo();
			if (connectionInfo == null)
				return;
			String passw = connectionInfo.getPassword();
			if (passw == null || passw.length() == 0)
				passw = "password";
			connectionInfo.setPassword(passw);

			connection = connectionInfo.connect();

			if (connection == null)
				return;
			DatabaseMetaData metaData = connection.getMetaData();
			String schema = null;
			int scIndex = tableName.indexOf(".");
			if (scIndex >= 0) {
				schema = tableName.substring(0, scIndex);
				tableName = tableName.substring(scIndex + 1);
			}

			ResultSet columns = metaData.getColumns(null, schema, tableName, "%");
			while (columns.next()) {
				RowAttribute atr = new RowAttribute();
				atr.name = columns.getString("COLUMN_NAME");
				atr.attributeType = getAttributeType(columns.getInt("DATA_TYPE"));
				atr.jdbcType = getSqlType(columns.getInt("DATA_TYPE"));
				atr.sqlType = columns.getString("TYPE_NAME");
				atr.columnName = columns.getString("COLUMN_NAME");
				attributeList.add(atr);
			}
			ResultSet primaryKeys = metaData.getPrimaryKeys(null, schema, tableName);
			while (primaryKeys.next()) {
				String key = primaryKeys.getString("COLUMN_NAME");
				Iterator iterator = attributeList.iterator();
				while (iterator.hasNext()) {
					RowAttribute attr = (RowAttribute) iterator.next();
					if (key.equals(attr.name))
						attr.isKey = true;
				}
			}

		} catch (Throwable ex) {
			ex.printStackTrace();
			MessageDialog.openError(this.getShell(), "Cannot Connect", "Check your properties");
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
	}

	public void updateTables() {
		if (catalogButton == null)
			return;
		Connection connection = null;
		tableList = new ArrayList();
		try {
			ConnectionInfo connectionInfo = getConnectionInfo();
			if (connectionInfo == null)
				return;
			String passw = connectionInfo.getPassword();
			if (passw == null || passw.length() == 0)
				passw = "password";
			connectionInfo.setPassword(passw);

			connection = connectionInfo.connect();

			if (connection == null)
				return;
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
			MessageDialog.openError(this.getShell(), "Cannot Connect", "Check your properties");
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
		catalogButton.setItems((String[]) tableList.toArray(new String[tableList.size()]));
		catalogButton.select(0);

	}

	public boolean isPageComplete() {
		return true;
		// return ((AddContainerManagedEntityEjbWizard)
		// getWizard()).testConnection();
	}

	public boolean canFinish() {
		return false;
	}

	public ConnectionInfo getConnectionInfo() {
		return ((AddContainerManagedEntityEjbWizard) getWizard()).getConnectionInfo();
	}

	/**
	 * Create the Table
	 */
	private Table createTable(Composite parent) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		final int NUMBER_COLUMNS = 2;
		GridLayout layout;
		GridData gridData;
		Group libraryPanel = new Group(parent, SWT.NONE);
		libraryPanel.setText("Managed Fields");
		layout = new GridLayout(2, false);
		libraryPanel.setLayout(layout);
		gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 3;
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
		column.setText("Name");
		column.setWidth(60);

		// 2 column with task Description
		column = new TableColumn(attributeTable, SWT.LEFT, 1);
		column.setText("Column");
		column.setWidth(60);

		column = new TableColumn(attributeTable, SWT.LEFT, 2);
		column.setText("Type");
		column.setWidth(120);

		column = new TableColumn(attributeTable, SWT.LEFT, 3);
		column.setText("JDBC Type");
		column.setWidth(80);

		column = new TableColumn(attributeTable, SWT.LEFT, 4);
		column.setText("SQL Type");
		column.setWidth(80);

		column = new TableColumn(attributeTable, SWT.CENTER, 5);
		column.setText("Read Only");
		column.setWidth(80);

		column = new TableColumn(attributeTable, SWT.CENTER, 6);
		column.setText("Primary key");
		column.setWidth(80);
		return attributeTable;
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

		// JavaTypeCompletionProcessor fFieldTypeCompletionProcessor = new
		// JavaTypeCompletionProcessor(false,
		// false);
		// fEnclosingTypeCompletionProcessor.setPackageFragment(getPackageFragmentRoot().getPackageFragment(""));
		// //$NON-NLS-1$
		//
		TextCellEditor textEditor3 = new TextCellEditor(table);
		((Text) textEditor3.getControl()).setTextLimit(200);
		// ControlContentAssistHelper.createTextContentAssistant(
		// ((Text) textEditor3.getControl()),
		// fFieldTypeCompletionProcessor);
		editors[2] = textEditor3;

		editors[3] = new ComboBoxCellEditor(table, jdbcTypes, SWT.READ_ONLY);

		// TextCellEditor textEditor4 = new TextCellEditor(table);
		// ((Text) textEditor4.getControl()).setTextLimit(100);
		// editors[4] = textEditor4;
		editors[4] = new ComboBoxCellEditor(table, sqlTypes, SWT.READ_ONLY);

		editors[5] = new CheckboxCellEditor(table, SWT.CENTER);

		editors[6] = new CheckboxCellEditor(table, SWT.CENTER);

		// Assign the cell editors to the viewer
		tableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		tableViewer.setCellModifier(new FieldCellModifier());
		tableViewer.setInput(getTableRows());
		return tableViewer;
	}

	public ArrayList getTableRows() {
		return attributeList;
	}

	protected static String[] columnNames = { "Field Name", "Column Name", "Type", "JDBC Type", "SQL Type", "Read Only", "Primary Key" };

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
			return attributeList.toArray();
		}
	}

	public class FieldLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {

			RowAttribute fieldMapping = (RowAttribute) obj;
			switch (index) {
			case 0: // Local
				return fieldMapping.name;
			case 1: // Local
				return fieldMapping.columnName;
			case 2: // Local
				return fieldMapping.attributeType;
			case 3: // Local
				return fieldMapping.jdbcType;
			case 4: // Local
				return fieldMapping.sqlType;
			case 5: // Local
				return "";
			case 6: // Local
				return "";
			}
			return "";
		}

		public String getColumnText(Viewer v, Object obj, int index) {
			return getColumnText(obj, index);
		}

		public Image getColumnImage(Object obj, int index) {
			RowAttribute fieldMapping = (RowAttribute) obj;
			switch (index) {
			case 5: // Local
				if (fieldMapping.isReadOnly)
					return fChecked;
				else
					return fUnchecked;

			case 6: // Local
				if (fieldMapping.isKey)
					return fChecked;
				else
					return fUnchecked;
			case 7: // Local
				if (fieldMapping.isTransient)
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
			return true;
		}

		public Object getValue(Object element, String property) {
			int columnIndex = getColumnIndex(property);
			RowAttribute fieldMapping = (RowAttribute) element;

			Object result = "";
			switch (columnIndex) {

			case 0:
				return fieldMapping.name;

			case 1:
				return fieldMapping.columnName;
			case 2:
				return fieldMapping.attributeType;
			case 3:
				String val = fieldMapping.jdbcType;
				for (int i = 0; i < jdbcTypes.length; i++) {
					String t = jdbcTypes[i];
					if (t.equals(val))
						return new Integer(i);
				}
				return new Integer(0);
			case 4:
				val = fieldMapping.sqlType;
				for (int i = 0; i < sqlTypes.length; i++) {
					String t = sqlTypes[i];
					if (t.equals(val))
						return new Integer(i);
				}
				return new Integer(0);
			case 5:
				return Boolean.valueOf(fieldMapping.isReadOnly);
			case 6:
				return Boolean.valueOf(fieldMapping.isKey);
			case 7:
				return Boolean.valueOf(fieldMapping.isTransient);
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
			RowAttribute fieldMapping = (RowAttribute) tItem.getData();

			Object result = "";
			switch (columnIndex) {

			case 0:
				fieldMapping.name = (String) value;
				break;

			case 1:
				fieldMapping.columnName = (String) value;
				break;
			case 2:
				fieldMapping.attributeType = (String) value;
				break;
			case 3:
				fieldMapping.jdbcType = jdbcTypes[((Integer) value).intValue()];
				break;
			case 4:
				fieldMapping.sqlType = sqlTypes[((Integer) value).intValue()];
				break;
			case 5:
				boolean readValue = ((Boolean) value).booleanValue();
				fieldMapping.isReadOnly = readValue;
				break;
			case 6:
				boolean newValue = ((Boolean) value).booleanValue();
				fieldMapping.isKey = newValue;
				break;
			case 7:
				fieldMapping.isTransient = ((Boolean) value).booleanValue();
				break;
			default:
				result = "";
			}
			attributeTableViewer.refresh();
		}

	}

	private String getSqlType(int type) {
		switch (type) {
		case Types.ARRAY:
			return "ARRAY";
		case Types.BIGINT:
			return "BIGINT";
		case Types.BINARY:
			return "BINARY";
		case Types.BIT:
			return "BIT";
		case Types.BLOB:
			return "BLOB";
		case Types.BOOLEAN:
			return "BOOLEAN";
		case Types.CHAR:
			return "CHAR";
		case Types.CLOB:
			return "CLOB";
		case Types.DATALINK:
			return "DATALINK";
		case Types.DATE:
			return "DATE";
		case Types.DECIMAL:
			return "DECIMAL";
		case Types.DISTINCT:
			return "DISTINCT";
		case Types.DOUBLE:
			return "DOUBLE";
		case Types.FLOAT:
			return "FLOAT";
		case Types.INTEGER:
			return "INTEGER";
		case Types.JAVA_OBJECT:
			return "JAVA_OBJECT";
		case Types.LONGVARBINARY:
			return "LONGVARBINARY";
		case Types.LONGVARCHAR:
			return "LONGVARCHAR";
		case Types.NULL:
			return "NULL";
		case Types.NUMERIC:
			return "NUMERIC";
		case Types.OTHER:
			return "OTHER";
		case Types.REAL:
			return "REAL";
		case Types.REF:
			return "REF";
		case Types.SMALLINT:
			return "SMALLINT";
		case Types.STRUCT:
			return "STRUCT";
		case Types.TIME:
			return "TIME";
		case Types.TIMESTAMP:
			return "TIMESTAMP";
		case Types.VARBINARY:
			return "VARBINARY";
		case Types.VARCHAR:
			return "VARCHAR";

		}
		return "NULL";
	}

	private String getAttributeType(int type) {
		switch (type) {
		case Types.ARRAY:
			return "java.lang.Object[]";
		case Types.BIGINT:
			return "java.lang.Long";
		case Types.BINARY:
			return "java.lang.Byte[]";
		case Types.BIT:
			return "java.lang.Byte";
		case Types.BLOB:
			return "java.lang.Byte[]";
		case Types.BOOLEAN:
			return "java.lang.Boolean";
		case Types.CHAR:
			return "java.lang.Character";
		case Types.CLOB:
			return "java.lang.Character[]";
		case Types.DATALINK:
			return "java.lang.String";
		case Types.DATE:
			return "java.sql.Date";
		case Types.DECIMAL:
			return "java.math.BigDecimal";
		case Types.DISTINCT:
			return "java.lang.String";
		case Types.DOUBLE:
			return "java.lang.Double";
		case Types.FLOAT:
			return "java.lang.Float";
		case Types.INTEGER:
			return "java.lang.Integer";
		case Types.JAVA_OBJECT:
			return "java.lang.Object";
		case Types.LONGVARBINARY:
			return "java.lang.String";
		case Types.LONGVARCHAR:
			return "java.lang.String";
		case Types.NULL:
			return "java.lang.String";
		case Types.NUMERIC:
			return "java.math.BigDecimal";
		case Types.OTHER:
			return "java.lang.String";
		case Types.REAL:
			return "java.math.BigDecimal";
		case Types.REF:
			return "java.lang.String";
		case Types.SMALLINT:
			return "java.lang.Integer";
		case Types.STRUCT:
			return "java.lang.Object";
		case Types.TIME:
			return "java.sql.Time";
		case Types.TIMESTAMP:
			return "java.sql.Timestamp";
		case Types.VARBINARY:
			return "java.lang.Object";
		case Types.VARCHAR:
			return "java.lang.String";

		}
		return "java.lang.String";
	}
}