package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.cmp.wizards;

import java.sql.Types;

public class CMPUtils {

	protected static String[] jdbcTypes = { "ARRAY", "BIGINT", "BINARY", "BIT", "BLOB", "BOOLEAN", "CHAR", "CLOB", "DATALINK", "DATE",
			"DECIMAL", "DISTINCT", "DOUBLE", "FLOAT", "INTEGER", "JAVA_OBJECT", "LONGVARBINARY", "LONGVARCHAR", "NULL", "NUMERIC",
			"OTHER", "REAL", "REF", "SMALLINT", "STRUCT", "TIME", "TIMESTAMP", "TINYINT", "VARBINARY", "VARCHAR" };// EjbConstants.LABEL_JDBCTYPES;
	protected static String[] sqlTypes = { "BIGINT", "BINARY", "BIT", "BLOB", "BOOLEAN", "CHAR", "CLOB", "DATE", "DECIMAL", "DOUBLE",
			"FLOAT", "INTEGER", "JAVA_OBJECT", "LONGVARBINARY", "LONGVARCHAR", "NULL", "NUMERIC", "REAL", "REF", "SMALLINT", "STRUCT",
			"TIME", "TIMESTAMP", "TINYINT", "VARBINARY", "VARCHAR" };

	public static String getSqlType(int type) {
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

	public static String getAttributeType(int type) {
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
