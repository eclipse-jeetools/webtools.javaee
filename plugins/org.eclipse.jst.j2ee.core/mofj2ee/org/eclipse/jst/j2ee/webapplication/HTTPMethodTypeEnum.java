/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>HTTP Method Type Enum</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getHTTPMethodTypeEnum()
 * @model
 * @generated
 */
public final class HTTPMethodTypeEnum extends AbstractEnumerator {
	/**
	 * The '<em><b>GET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The http-method contains an HTTP method (GET | POST |...)
	 * <!-- end-model-doc -->
	 * @see #GET_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GET = 0;

	/**
	 * The '<em><b>POST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POST_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int POST = 1;

	/**
	 * The '<em><b>PUT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PUT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PUT = 2;

	/**
	 * The '<em><b>DELETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DELETE = 3;

	/**
	 * The '<em><b>HEAD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HEAD_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HEAD = 4;

	/**
	 * The '<em><b>OPTIONS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPTIONS_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OPTIONS = 5;

	/**
	 * The '<em><b>TRACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRACE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TRACE = 6;

	public static final int ACL = 7;
	public static final int BASELINE_CONTROL = 8;
	public static final int BIND = 9;
	public static final int CHECKIN = 10; 
	public static final int CHECKOUT = 11;
	public static final int LABEL = 12;
	public static final int MERGE = 13;
	public static final int MKACTIVITY = 14;
	public static final int MKWORKSPACE = 15;
	public static final int REBIND = 16;
	public static final int REPORT = 17;
	public static final int SEARCH = 18;
	public static final int UNBIND = 19;
	public static final int UNCHECKOUT = 20; 
	public static final int UPDATE = 21;
	public static final int VERSION_CONTROL = 22;
	public static final int ACK = 23;
	public static final int BYE = 24;
	public static final int CANCEL = 25;
	public static final int COPY = 26;
	public static final int ERRORRESPONSE = 27;
	public static final int INFO = 28;
	public static final int INVITE = 29;
	public static final int LOCK = 30;
	public static final int MESSAGE = 31;
	public static final int MKCOL = 32;
	public static final int MOVE = 33;
	public static final int NOTIFY = 34;
	public static final int PRACK = 35;
	public static final int PROPFIND = 36;
	public static final int PROPPATCH = 37;
	public static final int PROVISIONALRESPONSE = 38;
	public static final int PUBLISH = 39;
	public static final int REDIRECTRESPONSE = 40;
	public static final int REGISTER = 41;
	public static final int REQUEST = 42;
	public static final int RESPONSE = 43;
	public static final int SUCCESSRESPONSE = 44;
	public static final int UNLOCK = 45;
	
	
	
	/**
	 * The '<em><b>GET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GET
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum GET_LITERAL = new HTTPMethodTypeEnum(GET, "GET", "GET");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>POST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POST
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum POST_LITERAL = new HTTPMethodTypeEnum(POST, "POST", "POST");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>PUT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PUT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PUT
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum PUT_LITERAL = new HTTPMethodTypeEnum(PUT, "PUT", "PUT");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>DELETE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELETE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum DELETE_LITERAL = new HTTPMethodTypeEnum(DELETE, "DELETE", "DELETE");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>HEAD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HEAD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HEAD
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum HEAD_LITERAL = new HTTPMethodTypeEnum(HEAD, "HEAD", "HEAD");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>OPTIONS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OPTIONS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPTIONS
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum OPTIONS_LITERAL = new HTTPMethodTypeEnum(OPTIONS, "OPTIONS", "OPTIONS");//$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>TRACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACE
	 * @generated
	 * @ordered
	 */
	public static final HTTPMethodTypeEnum TRACE_LITERAL = new HTTPMethodTypeEnum(TRACE, "TRACE", "TRACE");//$NON-NLS-1$ //$NON-NLS-2$

	//WEBDAV support
    public static final HTTPMethodTypeEnum ACK_LITERAL = new HTTPMethodTypeEnum(ACK, "ACK", "ACK"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum ACL_LITERAL = new HTTPMethodTypeEnum(ACL, "ACL", "ACL"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum BASELINE_CONTROL_LITERAL = new HTTPMethodTypeEnum(BASELINE_CONTROL, "BASELINE-CONTROL", "BASELINE-CONTROL"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum BIND_LITERAL = new HTTPMethodTypeEnum(BIND, "BIND", "BIND"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum BYE_LITERAL = new HTTPMethodTypeEnum(BYE, "BYE", "BYE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum CANCEL_LITERAL = new HTTPMethodTypeEnum(CANCEL, "CANCEL", "CANCEL"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum CHECKIN_LITERAL = new HTTPMethodTypeEnum(CHECKIN, "CHECKIN", "CHECKIN"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum CHECKOUT_LITERAL = new HTTPMethodTypeEnum(CHECKOUT, "CHECKOUT", "CHECKOUT"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum ERRORRESPONSE_LITERAL = new HTTPMethodTypeEnum(ERRORRESPONSE, "ERRORRESPONSE", "ERRORRESPONSE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum INFO_LITERAL = new HTTPMethodTypeEnum(INFO, "INFO", "INFO"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum INVITE_LITERAL = new HTTPMethodTypeEnum(INVITE, "INVITE", "INVITE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum LABEL_LITERAL = new HTTPMethodTypeEnum(LABEL, "LABEL", "LABEL"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum LOCK_LITERAL = new HTTPMethodTypeEnum(LOCK, "LOCK", "LOCK"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum MERGE_LITERAL = new HTTPMethodTypeEnum(MERGE, "MERGE", "MERGE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum MESSAGE_LITERAL = new HTTPMethodTypeEnum(MESSAGE, "MESSAGE", "MESSAGE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum MKACTIVITY_LITERAL = new HTTPMethodTypeEnum(MKACTIVITY, "MKACTIVITY", "MKACTIVITY"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum MKCOL_LITERAL = new HTTPMethodTypeEnum(MKCOL, "MKCOL", "MKCOL"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum MKWORKSPACE_LITERAL = new HTTPMethodTypeEnum(MKWORKSPACE, "MKWORKSPACE", "MKWORKSPACE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum MOVE_LITERAL = new HTTPMethodTypeEnum(MOVE, "MOVE", "MOVE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum NOTIFY_LITERAL = new HTTPMethodTypeEnum(NOTIFY, "NOTIFY", "NOTIFY"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum PRACK_LITERAL = new HTTPMethodTypeEnum(PRACK, "PRACK", "PRACK"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum PROPFIND_LITERAL = new HTTPMethodTypeEnum(PROPFIND, "PROPFIND", "PROPFIND"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum PROPPATCH_LITERAL = new HTTPMethodTypeEnum(PROPPATCH, "PROPPATCH", "PROPPATCH"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum PROVISIONALRESPONSE_LITERAL = new HTTPMethodTypeEnum(PROVISIONALRESPONSE, "PROVISIONALRESPONSE", "PROVISIONALRESPONSE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum PUBLISH_LITERAL = new HTTPMethodTypeEnum(PUBLISH, "PUBLISH", "PUBLISH"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum REBIND_LITERAL = new HTTPMethodTypeEnum(REBIND, "REBIND", "REBIND"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum REDIRECTRESPONSE_LITERAL = new HTTPMethodTypeEnum(REDIRECTRESPONSE, "REDIRECTRESPONSE", "REDIRECTRESPONSE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum REGISTER_LITERAL = new HTTPMethodTypeEnum(REGISTER, "REGISTER", "REGISTER"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum REPORT_LITERAL = new HTTPMethodTypeEnum(REPORT, "REPORT", "REPORT"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum REQUEST_LITERAL = new HTTPMethodTypeEnum(REQUEST, "REQUEST", "REQUEST"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum RESPONSE_LITERAL = new HTTPMethodTypeEnum(RESPONSE, "RESPONSE", "RESPONSE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum SEARCH_LITERAL = new HTTPMethodTypeEnum(SEARCH, "SEARCH", "SEARCH"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum SUCCESSRESPONSE_LITERAL = new HTTPMethodTypeEnum(SUCCESSRESPONSE, "SUCCESSRESPONSE", "SUCCESSRESPONSE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum UNBIND_LITERAL = new HTTPMethodTypeEnum(UNBIND, "UNBIND", "UNBIND"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum UNCHECKOUT_LITERAL = new HTTPMethodTypeEnum(UNCHECKOUT, "UNCHECKOUT", "UNCHECKOUT"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum UNLOCK_LITERAL = new HTTPMethodTypeEnum(UNLOCK, "UNLOCK", "UNLOCK"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum UPDATE_LITERAL = new HTTPMethodTypeEnum(UPDATE, "UPDATE", "UPDATE"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum VERSION_CONTROL_LITERAL = new HTTPMethodTypeEnum(VERSION_CONTROL, "VERSION-CONTROL", "VERSION-CONTROL"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final HTTPMethodTypeEnum COPY_LITERAL = new HTTPMethodTypeEnum(COPY, "COPY", "COPY"); //$NON-NLS-1$ //$NON-NLS-2$

	
	/**
	 * An array of all the '<em><b>HTTP Method Type Enum</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final HTTPMethodTypeEnum[] VALUES_ARRAY =
		new HTTPMethodTypeEnum[] {
			GET_LITERAL,
			POST_LITERAL,
			PUT_LITERAL,
			DELETE_LITERAL,
			HEAD_LITERAL,
			OPTIONS_LITERAL,
			TRACE_LITERAL,
			//WEBDAV support
			ACK_LITERAL,
			ACL_LITERAL,
			BASELINE_CONTROL_LITERAL,
			BIND_LITERAL,
			BYE_LITERAL,
			CANCEL_LITERAL,
			CHECKIN_LITERAL,
			CHECKOUT_LITERAL,
			COPY_LITERAL,
			ERRORRESPONSE_LITERAL,
			INFO_LITERAL,
			INVITE_LITERAL,
			LABEL_LITERAL,
			LOCK_LITERAL,
			MERGE_LITERAL,
			MESSAGE_LITERAL,
			MKACTIVITY_LITERAL,
			MKCOL_LITERAL,
			MKWORKSPACE_LITERAL,
			MOVE_LITERAL,
			NOTIFY_LITERAL,
			PRACK_LITERAL,
			PROPFIND_LITERAL,
			PROPPATCH_LITERAL,
			PROVISIONALRESPONSE_LITERAL,
			PUBLISH_LITERAL,
			REBIND_LITERAL,
			REDIRECTRESPONSE_LITERAL,
			REGISTER_LITERAL,
			REPORT_LITERAL,
			REQUEST_LITERAL,
			RESPONSE_LITERAL,
			SEARCH_LITERAL,
			SUCCESSRESPONSE_LITERAL,
			UNBIND_LITERAL,
			UNCHECKOUT_LITERAL,
			UNLOCK_LITERAL,
			UPDATE_LITERAL,
			VERSION_CONTROL_LITERAL
		};

	/**
	 * A public read-only list of all the '<em><b>HTTP Method Type Enum</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>HTTP Method Type Enum</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * @param name passes literal name
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HTTPMethodTypeEnum get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			HTTPMethodTypeEnum result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>HTTP Method Type Enum</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HTTPMethodTypeEnum getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			HTTPMethodTypeEnum result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>HTTP Method Type Enum</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * @param value passes literal value
	 * @return literal instance
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HTTPMethodTypeEnum get(int value) {
		switch (value) {
			case GET: return GET_LITERAL;
			case POST: return POST_LITERAL;
			case PUT: return PUT_LITERAL;
			case DELETE: return DELETE_LITERAL;
			case HEAD: return HEAD_LITERAL;
			case OPTIONS: return OPTIONS_LITERAL;
			case TRACE: return TRACE_LITERAL;
			//WEBDAV support
			case ACK : return ACK_LITERAL; 
			case ACL : return ACL_LITERAL;
			case BASELINE_CONTROL : return BASELINE_CONTROL_LITERAL;
			case BIND : return BIND_LITERAL;
			case BYE : return BYE_LITERAL;
			case CANCEL : return CANCEL_LITERAL;
			case CHECKIN : return CHECKIN_LITERAL;
			case CHECKOUT : return CHECKOUT_LITERAL;
			case COPY : return COPY_LITERAL;  
			case ERRORRESPONSE : return ERRORRESPONSE_LITERAL;
			case INFO : return INFO_LITERAL;
			case INVITE : return INVITE_LITERAL;
			case LABEL : return LABEL_LITERAL;
			case LOCK : return LOCK_LITERAL;			 
			case MERGE : return MERGE_LITERAL;
			case MESSAGE : return MESSAGE_LITERAL;
			case MKACTIVITY : return MKACTIVITY_LITERAL;
			case MKCOL : return MKCOL_LITERAL;			 
			case MKWORKSPACE : return MKWORKSPACE_LITERAL;
			case MOVE : return MOVE_LITERAL;		 
			case NOTIFY : return NOTIFY_LITERAL;
			case PRACK : return PRACK_LITERAL;
			case PROPFIND : return PROPFIND_LITERAL;
			case PROPPATCH : return PROPPATCH_LITERAL;
			case PROVISIONALRESPONSE : return PROVISIONALRESPONSE_LITERAL;
			case PUBLISH : return PUBLISH_LITERAL;
			case REBIND : return REBIND_LITERAL;
			case REDIRECTRESPONSE : return REDIRECTRESPONSE_LITERAL;
			case REGISTER : return REGISTER_LITERAL;
			case REPORT : return REPORT_LITERAL;
			case REQUEST : return REQUEST_LITERAL;
			case RESPONSE : return RESPONSE_LITERAL;
			case SEARCH : return SEARCH_LITERAL;
			case SUCCESSRESPONSE : return SUCCESSRESPONSE_LITERAL;
			case UNBIND : return UNBIND_LITERAL;
			case UNCHECKOUT : return UNCHECKOUT_LITERAL;
			case UNLOCK : return UNLOCK_LITERAL; 
			case UPDATE : return UPDATE_LITERAL;
			case VERSION_CONTROL : return VERSION_CONTROL_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private HTTPMethodTypeEnum(int value, String name, String literal) {
		super(value, name, literal);
	}

} //HTTPMethodTypeEnum
