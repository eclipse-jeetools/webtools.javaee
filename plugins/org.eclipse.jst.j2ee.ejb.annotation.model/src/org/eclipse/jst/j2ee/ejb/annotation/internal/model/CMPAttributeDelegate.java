/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

public class CMPAttributeDelegate {
	private String name;
	private String attributeType;
	private String sqlType;
	private boolean isKey = false;
	private boolean isTransient = false;
	private String columnName;
	private String jdbcType;
	private boolean isReadOnly;

	public CMPAttributeDelegate() {
		this.name = "aField";
		this.attributeType = "java.lang.String";
		this.jdbcType = "VARCHAR";
		this.sqlType = "VARCHAR";
		this.columnName = "ACOLUMN";
	}

	/**
	 * @return Returns the attributeType.
	 */
	public String getAttributeType() {
		return attributeType;
	}

	/**
	 * @param attributeType
	 *            The attributeType to set.
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	/**
	 * @return Returns the columnName.
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            The columnName to set.
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return Returns the isKey.
	 */
	public boolean isKey() {
		return isKey;
	}

	/**
	 * @param isKey
	 *            The isKey to set.
	 */
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	/**
	 * @return Returns the isReadOnly.
	 */
	public boolean isReadOnly() {
		return isReadOnly;
	}

	/**
	 * @param isReadOnly
	 *            The isReadOnly to set.
	 */
	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	/**
	 * @return Returns the isTransient.
	 */
	public boolean isTransient() {
		return isTransient;
	}

	/**
	 * @param isTransient
	 *            The isTransient to set.
	 */
	public void setTransient(boolean isTransient) {
		this.isTransient = isTransient;
	}

	/**
	 * @return Returns the jdbcType.
	 */
	public String getJdbcType() {
		return jdbcType;
	}

	/**
	 * @param jdbcType
	 *            The jdbcType to set.
	 */
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the sqlType.
	 */
	public String getSqlType() {
		return sqlType;
	}

	/**
	 * @param sqlType
	 *            The sqlType to set.
	 */
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
}