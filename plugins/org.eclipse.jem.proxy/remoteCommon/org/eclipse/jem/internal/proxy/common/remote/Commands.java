package org.eclipse.jem.internal.proxy.common.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: Commands.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.io.*;
import java.util.HashMap;
import org.eclipse.jem.internal.proxy.common.CommandException;
/**
 * The commands that can be passed back and forth between
 * client and server. And other constants.
 *
 * - Contains helper methods for reading/writing commands.
 */
public class Commands {
	public final static HashMap MAP_SHORTSIG_TO_TYPE = new HashMap(8);
	public final static HashMap MAP_TYPENAME_TO_SHORTSIG = new HashMap(8);
	
	static {
		MAP_SHORTSIG_TO_TYPE.put("B", Byte.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("C", Character.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("D", Double.TYPE);		 //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("F", Float.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("I", Integer.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("J", Long.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("S", Short.TYPE); //$NON-NLS-1$
		MAP_SHORTSIG_TO_TYPE.put("Z", Boolean.TYPE); //$NON-NLS-1$
		
		MAP_TYPENAME_TO_SHORTSIG.put("byte","B"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("char","C"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("double","D"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("float","F"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("int","I"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("long","J"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("short","S"); //$NON-NLS-1$ //$NON-NLS-2$
		MAP_TYPENAME_TO_SHORTSIG.put("boolean","Z"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	// The stream between server and clients are Data...Streams.
	// If someone gets out of sync and can't determine where the
	// start/end of a command is, then simply close the connection
	// and open up a fresh one. This should work because there
	// should never be more than one command on the pipe at a
	// time since the client shouldn't send a new one until
	// it has received a response to the previous one, if the
	// command has a response.
	
	// The commands will be written in writeByte format .
	public final static byte
		GET_CLASS = 1,		// Get the class object,
		VALUE = 2,		// Returning a value
		QUIT_CONNECTION = 4,	// Close this connection
		TERMINATE_SERVER = 5,	// Terminate the entire server.
		ERROR = 6,		// Returning an error
		RELEASE_OBJECT = 7,	// An object is no longer needed on the client side, so
					// it can be removed from the server id table and released.
		GET_CLASS_RETURN = 8,	// The return command from GET_CLASS
		GET_METHOD = 9,	// Return the id for a method
		GET_CTOR = 10,		// Return the id for a constructor		
		NEW_INIT_STRING = 11,	// Create a new bean using the init string
		GET_CLASS_FROM_ID = 12,	// We have an ID, return the class info for this id.
		GET_CLASS_ID_RETURN = 13,	// The return command from GET_CLASS_FROM_ID
		GET_OBJECT_DATA = 14,	// We have an ID, but we don't have the info, return it. This is a 
					// corrective command only. This would happen if for some strange
					// reason the proxy has been removed but has not been released. This
					// really shouldn't happen except as a possible race condition between
					// GC and returning id from the server.
		INVOKE = 15,		// Invoke a method.
		
		// These commands are to the Master Server thread in the IDE.
		ALIVE = 16,	// Are you alive?
		REMOTE_STARTED = 17,	// Remote VM has started.
		GET_CALLBACK_PORT = 18;	// Get the callback port number. Should only be used once per server, it should cache it.
		
	// Callback commands
	public final static byte
		CALLBACK = (byte) 255,	// A callback has come in.
		CALLBACK_DONE = (byte) 254,	// A callback done command, sent to the remote vm upon callback completion.
		CALLBACK_STREAM = (byte) 253,	// A callback for a byte stream has come in.
								// This is a special callback. When this comes in a special
								// input stream will be created that will take over control of
								// the connection until the stream is terminated on the remote
								// side. At this time the connection will be returned.
		CALLBACK_STREAM_TERMINATE = (byte) 252;	// A callback stream is asked to terminate early.
		
	// The error values from the command on the server.
	public final static int
		UNKNOWN_COMMAND_SENT = 1,	// An unknown command was sent to the server. Value is void.
		GET_CLASS_NOT_FOUND = 2,	// The class was not found in GetClass. Value is void.
		CANNOT_EVALUATE_STRING = 3,	// Evaluator couldn't evaluate the init string. Too complicated. Value is a throwable of the wrappered Init string error.
		CLASS_CAST_EXCEPTION = 4,	// The result is not assignable to the expected type. Value is void.
		GET_METHOD_NOT_FOUND = 5,	// Method requested wasn't found. Value is void.
		THROWABLE_SENT = 6;			// A Throwable is being sent back as the error, not as just data for the error. Value is the Throwable.
		
	// Predefined standard id's for standard classes/objects. Both sides will assume these id's have been assigned
	// to these classes/types/objects
	public final static int
		NOT_AN_ID = -1,	// This value means it is not an id. It is never a valid id.
		VOID_TYPE = 0,
		BOOLEAN_TYPE = 1,
		BOOLEAN_CLASS = 2,
		INTEGER_TYPE = 3,
		INTEGER_CLASS = 4,
		BYTE_TYPE = 5,
		BYTE_CLASS = 6,
		CHARACTER_TYPE = 7,
		CHARACTER_CLASS = 8,
		DOUBLE_TYPE = 9,
		DOUBLE_CLASS = 10,
		FLOAT_TYPE = 11,
		FLOAT_CLASS = 12,
		SHORT_TYPE = 13,
		SHORT_CLASS = 14,
		LONG_TYPE = 15,
		LONG_CLASS = 16,
		STRING_CLASS = 17,
		BIG_DECIMAL_CLASS = 18,
		BIG_INTEGER_CLASS = 19,
		NUMBER_CLASS = 20,
		THROWABLE_CLASS = 21,
		CLASS_CLASS = 22,
		OBJECT_CLASS = 23,
		ACCESSIBLEOBJECT_CLASS = 24,
		METHOD_CLASS = 25,
		FIELD_CLASS = 26,
		CONSTRUCTOR_CLASS = 27,
		GET_METHOD_ID = 28,	// Class.getMethod(...) predefined id.
		IVMSERVER_CLASS = 29,	// IVMServer.class
		ICALLBACK_CLASS = 30,	// ICallback.class
		REMOTESERVER_ID = 31,	// id of RemoteVMServerThread instance.
		REMOTEVMSERVER_CLASS = 32,	// RemoteVMServer.class
		INITIALIZECALLBACK_METHOD_ID = 33,	// ICallback.initializeCallback method.
		THREAD_CLASS = 33,
		FIRST_FREE_ID = 34;
				
	// The type flags written in writeByte format
	public final static byte
		VOID = VOID_TYPE,	// null - nothing follows
		BYTE = BYTE_TYPE,	// byte - writeByte
		L_BYTE = BYTE_CLASS,	// java.lang.Byte - writeByte
		CHAR = CHARACTER_TYPE,	// char - writeChar
		L_CHAR = CHARACTER_CLASS,	// java.lang.Character - writeChar
		DOUBLE = DOUBLE_TYPE,	// double - writeDouble
		L_DOUBLE = DOUBLE_CLASS,	// java.lang.Double - writeDouble
		FLOAT = FLOAT_TYPE,	// float - writeFloat
		L_FLOAT = FLOAT_CLASS,	// java.lang.Float - writeFloat
		INT = INTEGER_TYPE,	// int - writeInt
		L_INT = INTEGER_CLASS,	// java.lang.Integer - writeInt
		LONG = LONG_TYPE,	// long - writeLong
		L_LONG = LONG_CLASS,	// java.lang.Long - writeLong
		SHORT = SHORT_TYPE,	// short - writeShort
		L_SHORT = SHORT_CLASS,	// java.lang.Short - writeShort
		BOOL = BOOLEAN_TYPE,	// boolean - writeBoolean
		L_BOOL = BOOLEAN_CLASS,	// java.lang.Boolean - writeBoolean
		STRING = STRING_CLASS,	// java.lang.String - writeUTF
		OBJECT = OBJECT_CLASS,	// Object - special, see below (Object can be used to return an array (except if the array contains any object_ids, that has a special type)
		OBJECT_ID = 50,	// Object identity key - writeInt
		NEW_OBJECT_ID = 51,	// New Object identity (this is a new object that didn't exist before)
		THROW = 52,	// An exception occured. The value is a throwable, it is of the same format as NEW_OBJECT_ID.
		ARRAY_IDS = 53;	// An array of values, where there are at least one ID in the array. If there were no
						// ID's (i.e. all just values), then use OBJECT type intead and have it written as
						// writeObject.
				
		
		
	// Unless specified below, the commands are one byte long.
	// Also, unless specified below, the commands do not return a confirmation response.
	//
	// NOTE: VERY IMPORTANT, after every command, flush() should be used so that the
	// the data is immediately sent to the server. 	
	//
	// n means int (e.g. 1)
	// nb means byte (e.g. 1b)
	// 'x' means Unicode char (i.e. writeChar())
	// "xxx" means UTF8 string (i.e. writeUTF)
	// bool means a one byte boolean value
	//
	// The commas aren't actually written, they are used as separaters in the comments below
	//
	// GET_CLASS: 1b, "classname"
	//		Will return on the output stream GET_CLASS_RETURN command:
	//		8b, n1, bool1, bool2, "superclassname"
	//		The "n1" is the class id.
	//		The bool1 is whether this class is an interface (true if it is).
	//		The bool2 is whether this class is abstract (true if it is).	
	//		The "superclassname" is the class name of the super class (0 length if no superclass)
	//		If the class is not found, then it will return an error with a value for the error.
	//
	// GET_CLASS_FROM_ID: 12b, n
	//		Where "n" is the class id.
	//		Will return on the output stream GET_CLASS_ID_RETURN command:	
	//		13b, "classname", bool1, bool2, "superclassname"
	//		The bool1 is whether this class is an interface (true if it is).
	//		The bool2 is whether this class is abstract (true if it is).	
	//
	// VALUE: 2b, tb, value
	//		Where tb is the type in byte, and value is the appropriate value shown in
	//		table above.
	//		OBJECT_ID: 50b, n
	//			Where "n" is the object id.
	//		NEW_OBJECT_ID: 51b, n1, n2
	//			Where "n1" is class ObjectID of the object that the object_id ("n2") is made of.
	//		OBJECT: 19b, n, writeObject
	//			Where "n" is the classObjectID of the class of the type of the object.
	//			NOTE: Object should be used only very rarely. Identity is lost, i.e.
	//			a copy is made each time and it can't be referenced back on the remote
	//			VM.
	//		ARRAY_IDS: 52b, id, n, [tb, value, ...]
	//			This is a very special array. It contains at least one ID. Therefor all of the 
	//			First level entries are value objects. 
	//			"id" is the id of the component type of the array(e.g. id for Object, or if multi-dimensional String[] (this will produce String[][]).
	//			"n" is the number of entries in the array. Followed by the values, one of the
	//			values could be an ARRAY_IDS too. The reading/writing of these are special because
	//			there is a callback mechanism to process the individual entries. This is so that
	//			temp arrays of ValueObjects won't need to be created to handle this, so it can
	//			go directly from the array to/from the stream.
	//		
	// RELEASE_OBJECT: 7b, n
	//		Where the n is the object id to release. There is no confirmation to read back.
	//
	// ERROR: 6b, n, tb, ...
	//      n is the error code for this error.
	//		tb is a type flag, followed by the value. The value is dependent upon
	//		the command that this is error is from. If a THROW, then the THROW is ALWAYS a new
	//		ID, it can never be an existing id.
	//      
	//
	// TO_BEAN_STRING: 9b, n
	//		Where n is the object id to produce the bean string for.
	//		It will return a VALUE command where the type is String.
	//
	// NEW_INSTANCE: 10b, n
	//		Where n is the class object id of the class to create a new instance of using the default ctor.
	//		It will return either a VALUE command containing the new value (of type OBJECT_ID/NEW_OBJECT_ID if not
	//		one of the constant types with the true classID in it) or an ERROR command. (The ERROR could
	//		be a THROW type). If the object created is not assignable to the type passed in, then
	//		an ERROR is returned with CLASS_CAST_EXCEPTION flag.
	//
	// NEW_INIT_STRING: 11b, n, "initstring"
	//		Where n is the class object id of the class this initstring is supposed to create for.
	//		It will return either a VALUE command containing the new value (of type OBJECT_ID/NEW_OBJECT_ID if not
	//		one of the constant types with the true classID in it) or an ERROR command. (The ERROR could
	//		be a THROW type). The error could also be CANNOT_EVALUATE_STRING. This means that the string was too
	//		complicated for the evaluator and needs to be compiled and tried again. (TBD)
 	//		If the object created is not assignable to the type passed in, then
	//		an ERROR is returned with CLASS_CAST_EXCEPTION flag.
	//
	// GET_OBJECT_DATA: 14b, n
	//		Where n is the id of the object being requested. It will return a NEW_OBJECT_ID value with the info.
	//
	// GET_METHOD: 9b, classId, "methodName", n1, [n2]...
	//		Where classID is the id of the class the method should be found in.
	//		Where n1 is the number of parm types following, and n2 is replicated that many times,
	//		each entry is the id of class for the parm type. (0 is valid which means there are no parms).
	//		The return will be a VALUE command containing the OBJECT_ID of the method.
	//
	// GET_CTOR: 10b, classId, n1, [n2]...
	//		Where classID is the id of the class the method should be found in.
	//		Where n1 is the number of parm types following, and n2 is replicated that many times,
	//		each entry is the id of class for the parm type. (0 is valid which means there are no parms).
	//		The return will be a VALUE command containing the OBJECT_ID of the method.
	//	
	// GET_FIELD:
	//
	// GET_CTOR:
	//
	// INVOKE: 15b, n1, tb, value1, value2
	//		Where "n1" is the id of the method to invoke.
	//		tb, value1 is the value of who to invoke against (it is usually an OBJECT_ID for tb)
	//		n2 is the number of parms
	//      value2 is an ARRAY_IDS type or an OBJECT array of values if all constants.
	//		What is returned is a VALUE command containing the return value, (the value will be null (VOID) if
	//		there is no return type (i.e. the method was void). So null can be returned either if the value
	//		was null or if the return type was void.
	//
	// Callback commands:
	//
	// CALLBACK: 255b, n1, n2, value1
	//      Where
	//        "n1" is the id of callback type (these are registered with the callback server)
	//        "n2" is the msgId for the callback (These are entirely callback dependent and are maintained by the callback developer)
	//        value1 is an ARRAY_IDS type or an OBJECT array of values if all constants. These are
	//          parms to send to the callback msg.
	//      It will return a CALLBACK_DONE.
	//
	// CALLBACK_DONE: 254b, value.
	//
	// CALLBACK_STREAM: 253b, n1, n2
	//      Where
	//        "n1" is the id of callback type (these are registered with the callback server)
	//        "n2" is the msgId for the callback (These are entirely callback dependent and are maintained by the callback developer)
	//		It will create a CallbackInputStream and notify the registered callback that the
	//		stream is available. It will send a callback_done when it has accepted the request
	//		but before it notifies the registered callback with the stream. This lets the remote
	//		vm know that it can start sending data.
	

	// To the MasterServer socket:
	// The MasterServer socket will expect input in DataInputStream format, and DataOutputStream for return.
	// The socket will be short-lived. It will be for one transaction only. Each request will return a new socket.
	//
	// ALIVE: 16b, n1
	//      Where
	//        "n1" is the id of the registry this is asking to test for aliveness
	//      Will return bool, where false if registry is not alive, true if it is alive.
	// REMOTE_STARTED: 17b, n1, n2
	//      Where
	//        "n1" is the id of the registry this is telling that it is started
	//        "n2" is the serversocket port number of the server socket in this remote vm.
	//      Will return bool, where false if registry is not alive, true if it is alive. If false, then terminate the server because nothing to talk to.
	// GET_CALLBACK_PORT: 18b, n1
	//      Where
	//        "n1" is the id of the registry this is asking for the callback server port.
	//      Will return int, where the value is the callback server port number. -1 if there is no callback server port.		
	
	/**
	 * This class is the return from a read value. It contains the
	 * type of the value and the value itself. Since primitives can be
	 * returned also, there is a slot for each one and the type should
	 * be checked to see which one is set.
	 *
	 * Also, if the type is OBJECT, then the anObject has the object in it, AND
	 * the classID field has the object_id of the class of the object so that the
	 * appropriate beantypeproxy can be found to use that object. Also, only
	 * IREMConstantBeanTypeProxies can be of type OBJECT. That is because those
	 * are the only ones that know how to take the value object and interpret it.
	 *
	 * If the type is OBJECT_ID or NEW_OBJECT_ID, then the objectID field will be set with
	 * the id.
	 * If the type is NEW_OBJECT_ID, then the classID field will
	 * have the class objectID of the class of the object for which object_id proxies.
	 *
	 * THROW is treated like NEW_OBJECT_ID in what fields are set since it is a new object.
	 *
	 * Note: so as not to create unnecessary objects, if the Object type of the primitive is being
	 * sent, then the primitive field will be set instead, though the type
	 * will still be the Object type (i.e. if type = L_BYTE, the aByte will
	 * have the value in it).
	 */
	public static class ValueObject {
		public byte type;	// Same as the types above
		public byte aByte;
		public char aChar;
		public double aDouble;
		public float aFloat;
		public int anInt;
		public short aShort;
		public long aLong;
		public boolean aBool;
		public int objectID;	// The object id for either OBJECT_ID or NEW_OBJECT_ID.
		public int classID;		// The class object id of the value in Object if the type is Object
		public Object anObject;	// String also will be in here
		
		public ValueObject() {
			type = VOID;
		}
		
		/**
		 * Special getter to get the type as an Object, this is used by invoke for example.
		 */
		public Object getAsObject() {
			switch (type) {
				case VOID:
					return null;
				case BYTE:
				case L_BYTE:
					return new Byte(aByte);
				case CHAR:
				case L_CHAR:
					return new Character(aChar);
				case DOUBLE:
				case L_DOUBLE:
					return new Double(aDouble);
				case FLOAT:
				case L_FLOAT:
					return new Float(aFloat);
				case INT:
				case L_INT:
					return new Integer(anInt);
				case SHORT:
				case L_SHORT:
					return new Short(aShort);
				case LONG:
				case L_LONG:
					return new Long(aLong);
				case BOOL:
				case L_BOOL:
					return aBool ? Boolean.TRUE : Boolean.FALSE;
				case STRING:
					return (String) anObject;
				case OBJECT:
					return anObject;
				
				default: 
					return null;	// Can't handle others. Those need to be checked before calling.
			}
		}
		
		/**
		 * Special setter to set the value depending upon the type.
		 */
		public void setAsObject(Object value, int valueClassID) {
			switch (valueClassID) {
				case VOID:
					set();
					break;
				case BYTE_CLASS:
					set((Byte) value);
					break;
				case CHARACTER_CLASS:
					set((Character) value);
					break;
				case DOUBLE_CLASS:
					set((Double) value);
					break;
				case FLOAT_CLASS:
					set((Float) value);
					break;
				case INTEGER_CLASS:
					set((Integer) value);
					break;
				case SHORT_CLASS:
					set((Short) value);
					break;
				case LONG_CLASS:
					set((Long) value);
					break;
				case BOOLEAN_CLASS:
					set((Boolean) value);
					break;
				case STRING_CLASS:
					set((String) value);
					break;
				default:
					set(value, valueClassID);
					break;
			}
		}
			
		public void set() {
			type = VOID;
			anObject = null;
		}
		public void set(byte value) {
			type = BYTE;
			aByte = value;
			anObject = null;
		}
		public void set(Byte value) {
			type = L_BYTE;
			aByte = value.byteValue();
			anObject = null;
		}
		public void set(char value) {
			type = CHAR;
			aChar = value;
			anObject = null;
		}
		public void set(Character value) {
			type = L_CHAR;
			aChar = value.charValue();
			anObject = null;
		}
		public void set(double value) {
			type = DOUBLE;
			aDouble = value;
			anObject = null;
		}
		public void set(Double value) {
			type = L_DOUBLE;
			aDouble = value.doubleValue();
			anObject = null;
		}
		public void set(float value) {
			type = FLOAT;
			aFloat = value;
			anObject = null;
		}
		public void set(Float value) {
			type = L_FLOAT;
			aFloat = value.floatValue();
			anObject = null;
		}
		public void set(int value) {
			type = INT;
			anInt = value;
			anObject = null;
		}
		public void set(Integer value) {
			type = L_INT;
			anInt = value.intValue();
			anObject = null;
		}
		public void set(short value) {
			type = SHORT;
			aShort = value;
			anObject = null;
		}
		public void set(Short value) {
			type = L_SHORT;
			aShort = value.shortValue();
			anObject = null;
		}
		public void set(long value) {
			type = LONG;
			aLong = value;
			anObject = null;
		}
		public void set(Long value) {
			type = L_LONG;
			aLong = value.longValue();
			anObject = null;
		}
		public void set(boolean value) {
			type = BOOL;
			aBool = value;
			anObject = null;
		}
		public void set(Boolean value) {
			type = L_BOOL;
			aBool = value.booleanValue();
			anObject = null;
		}
		public void set(String value) {
			type = STRING;
			anObject = value;
		}				
		public void set(Object value, int classObjectID) {
			type = OBJECT;
			classID = classObjectID;
			anObject = value;
		}
		public void setObjectID(int value) {
			type = OBJECT_ID;
			objectID = value;
			anObject = null;
		}
		
		// Use this if the object is an array containing IDs. The retriever
		// will be used to get the next value to write to the stream.
		public void setArrayIDS(ValueRetrieve retriever, int arraySize, int componentType) {
			type = ARRAY_IDS;
			classID = componentType;
			anInt = arraySize;
			anObject = retriever;
		}
		
		
		// Use this if this is a new object so that we can get the correct class type.
		public void setObjectID(int value, int classObjectID) {
			type = NEW_OBJECT_ID;
			objectID = value;
			classID = classObjectID;
			anObject = null;
		}									
		
		// Use this to indicate an exception occured.
		public void setException(int throwID, int throwClassID) {
			type = THROW;
			objectID = throwID;
			classID = throwClassID;
			anObject = null;
		}
	}
	
	/************************
	 * Helpful commands.
	 * - If a command throws any exception except CommandErrorException, or
	 *   UnexpectedCommandException with recoverable true, then the connection is in a bad state
	 *   and needs to be closed.
	 ************************/
	
	/**
	 * Use this to read a value (inputstream should be pointing to the type byte as the next byte to read).
	 * The primitive fields of "value" will not be changed if they are not the
	 * type of the value being read. However, anObject will be set to null.
	 */
	 
	/**
	 * Error flags for UnexpectedCommandExceptions that can be thrown.
	 */
	public static final Object UNKNOWN_READ_TYPE = "UNKNOWN_READ_TYPE";		// The read type byte was not a valid type //$NON-NLS-1$
	public static final Object UNKNOWN_WRITE_TYPE = "UNKNOWN_WRITE_TYPE";		// The write type byte was not a valid type	 //$NON-NLS-1$
	public static final Object TYPE_INVALID_FOR_COMMAND = "TYPE_INVALID_FOR_COMMAND";		// The data type read is not valid for this command //$NON-NLS-1$
	public static final Object UNKNOWN_COMMAND = "UNKNOWN_COMMAND";			// The command flag is unknown //$NON-NLS-1$
	public static final Object SOME_UNEXPECTED_EXCEPTION = "SOME_UNEXPECTED_EXCEPTION";	// There was some kind of exception that wasn't expected. The data will be the exception. //$NON-NLS-1$
	public static final Object TOO_MANY_BYTES = "TOO_MANY_BYTES";			// Too many bytes were sent on a writeBytes. It was //$NON-NLS-1$
																		// more than could be read into the buffer. The data will be the size sent.
	
	
	public static void readValue(DataInputStream is, ValueObject value) throws CommandException {
		try {
			value.anObject = null;
			value.type = is.readByte();
			switch (value.type) {
				case BYTE:
				case L_BYTE:
					value.aByte = is.readByte();
					break;
				case CHAR:
				case L_CHAR:
					value.aChar = is.readChar();
					break;
				case DOUBLE:
				case L_DOUBLE:
					value.aDouble = is.readDouble();
					break;
				case FLOAT:
				case L_FLOAT:
					value.aFloat = is.readFloat();
					break;
				case INT:
				case L_INT:
					value.anInt = is.readInt();
					break;
				case OBJECT_ID:
					value.objectID = is.readInt();
					break;					
				case NEW_OBJECT_ID:
					value.classID = is.readInt();
					value.objectID = is.readInt();
					break;
				case THROW:
					value.classID = is.readInt();
					value.objectID = is.readInt();
					break;				
				case SHORT:
				case L_SHORT:
					value.aShort = is.readShort();
					break;
				case LONG:
				case L_LONG:
					value.aLong = is.readLong();
					break;
				case BOOL:
				case L_BOOL:
					value.aBool = is.readBoolean();
					break;
				case STRING:
					value.anObject = is.readUTF();
					break;
				case OBJECT:
					value.classID = is.readInt();	// Read the class id
					ObjectInputStream oi = new ObjectInputStream(is);
					value.anObject = oi.readObject();	// Read the object itself
					oi = null;	// Don't close it, that would close the stream itself.
					break;
				case ARRAY_IDS:
					// The header for an array of ids.
					value.classID = is.readInt();	// The component type of the array
					value.anInt = is.readInt();	// The size of the array.
					// At this point, it is the responsibility of the caller to use readArray to read in the array.
					break;
				case VOID:
					break;
				default:
					throw new UnexpectedCommandException(UNKNOWN_READ_TYPE, false, new Byte(value.type));
			}
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}
	}
	
	/**
	 * Special interface used to read back arrays. It will be called when 
	 */
	public static interface ValueSender {
		// This is called for each entry from the array. It is assumed that the ValueSender has
		// the array that is being built.
		public void sendValue(ValueObject value);
		
		// This is called when an ARRAY_IDS is found within the reading of the array (i.e. nested arrays)
		// It is asking for a new ValueSender to use while this nested array. The arrayValue contains
		// the valueobject for the array header (i.e. the class id of the array and the number of elements).
		// It is the responsibility of the ValueSender to store this array in the array that is being built.
		public ValueSender nestedArray(ValueObject arrayValue);
		
	}	
	
	/*
	 * NOTE: It is important that on the IDE side that this is called within a transaction. 
	 * If not, there could be some corruption if proxy cleanup occurs in the middle.
	 */
	public static void readArray(DataInputStream is, int arraySize, ValueSender valueSender, ValueObject value) throws CommandException {
		// Anything exception other than a CommandException, we will try to flush the input so that
		// it can continue with the next command and not close the connection.
		RuntimeException exception = null;
		for (int i=0; i<arraySize; i++) {
			readValue(is, value);
			if (exception == null) 
				try {
					if (value.type != ARRAY_IDS)
						valueSender.sendValue(value);
					else {
						// We have a nested array.
						ValueSender nestedSender = null;
						try {
							nestedSender = valueSender.nestedArray(value);
						} catch (RuntimeException e) {
							// We still need to read in the array to flush. Create
							// a dummy sender that accepts everything sent to it.
							exception = e;
							nestedSender = new ValueSender() {
								public void sendValue(ValueObject value) {
								}
								public ValueSender nestedArray(ValueObject arrayValue) {
									return this;
								}
							};
						}
						readArray(is, value.anInt, nestedSender, value);
						if (exception != null)
							throw exception;	// An exception ocurred in new sender request.
					}
				} catch (RuntimeException e) {
					// We want to flush the queue, so save the exception for later.
					exception = e;
				}
		}
		if (exception != null)
			throw exception;
	}
				
	
	/**
	 * Special interface to handle writing the ARRAY_IDS type.
	 * An instance of this object will be in the valueObject sent to writeValue when the type of the value
	 * is ARRAY_IDS. Then write value will know to call this interface to write out the values.
	 */
	public static interface ValueRetrieve {
		// This interface is used to retreive the next value object so that it can be written to the output stream.
		// Return null when there are no more.
		public ValueObject nextValue() throws EOFException;
	}	
	
	public static void writeValue(DataOutputStream os, ValueObject value, boolean asValueCommand) throws CommandException  {
		try {
			if (asValueCommand)
				os.writeByte(VALUE);
			switch (value.type) {
				case BYTE:
				case L_BYTE:
					os.writeByte(value.type);
					os.writeByte(value.aByte);
					break;
				case CHAR:
				case L_CHAR:
					os.writeByte(value.type);			
					os.writeChar(value.aChar);
					break;
				case DOUBLE:
				case L_DOUBLE:
					os.writeByte(value.type);			
					os.writeDouble(value.aDouble);
					break;
				case FLOAT:
				case L_FLOAT:
					os.writeByte(value.type);			
					os.writeFloat(value.aFloat);
					break;
				case INT:
				case L_INT:
					os.writeByte(value.type);			
					os.writeInt(value.anInt);
					break;
				case OBJECT_ID:
					os.writeByte(value.type);
					os.writeInt(value.objectID);
					break;					
				case NEW_OBJECT_ID:
					os.writeByte(value.type);
					os.writeInt(value.classID);
					os.writeInt(value.objectID);
					break;
				case THROW:
					os.writeByte(value.type);
					os.writeInt(value.classID);
					os.writeInt(value.objectID);
					break;
				case SHORT:
				case L_SHORT:
					os.writeByte(value.type);			
					os.writeShort(value.aShort);
					break;
				case LONG:
				case L_LONG:
					os.writeByte(value.type);			
					os.writeLong(value.aLong);
					break;
				case BOOL:
				case L_BOOL:
					os.writeByte(value.type);			
					os.writeBoolean(value.aBool);
					break;
				case STRING:
					os.writeByte(value.type);			
					os.writeUTF((String) value.anObject);
					break;
				case OBJECT:
					os.writeByte(value.type);
					os.writeInt(value.classID);	// Write the class ID.
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(value.anObject);
					oos.flush();
					oos = null;	// Don't close it, that would close the stream itself.
					break;					
				case ARRAY_IDS:
					// We are writing out an array with ID's in it. The fields of the vale object will be:
					// 	classID: The class id of the component type of the array.
					//  anObject: Contains the ValueRetriever to get the next value.
					os.writeByte(value.type);
					os.writeInt(value.classID);
					os.writeInt(value.anInt);
					// Now comes the kludgy part, writing the values.
					ValueRetrieve retriever = (ValueRetrieve) value.anObject;
					ValueObject nextEntry = null;
					while ((nextEntry = retriever.nextValue()) != null)
						writeValue(os, nextEntry, false);
					break;
				case VOID:
					os.writeByte(value.type);			
					break;
				default:
					os.writeByte(VOID);
					throw new UnexpectedCommandException(UNKNOWN_WRITE_TYPE, true, value);					
			}
			if (asValueCommand)
				os.flush();
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}	
	
	/**
	 * For reading a large number of bytes. This is a value type, not a command. The command 
	 * needs to be handled separately. It returns the number of bytes read. -1 if there
	 * is no more data to send and the stream should closed. If read something but not all,
	 * then just what it could read will be returned. The next read will return -1 for EOF.
	 *
	 * It will read from the format:
	 *   int - number of bytes to read (retrieved from the stream).
	 *   bytes - the actual bytes.
	 *
	 * Note: A command exception will be thrown if the number of bytes to read
	 *       is larger than the size of the byte array.
	 */
	public static int readBytes(DataInputStream is, byte[] bytes) throws CommandException  {
		try {
			int bytesToRead = -1;
			try {
				bytesToRead = is.readInt();
			} catch (EOFException e) {
			}
			if (bytesToRead == -1)
				return -1;
			if (bytesToRead > bytes.length)
				throw new UnexpectedCommandException(TOO_MANY_BYTES, false, new Integer(bytesToRead));
			int start = 0;
			int toRead = bytesToRead;
			while (toRead > 0) {
				int bytesRead = is.read(bytes, start, toRead);
				if (bytesRead == -1)
					return bytesToRead != toRead ? bytesToRead-toRead : -1;	// Actual number read, or if none read, then EOF
				start+=bytesRead;
				toRead-=bytesRead;
			}
			return bytesToRead;
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
		
	/**
	 * For writing a large number of bytes. This is a value type, not a command. The command 
	 * needs to be handled separately.
	 *
	 * It will write it in the format:
	 *   int - number of bytes
	 *   bytes - the actual bytes.
	 *
	 */
	public static void writeBytes(DataOutputStream os, byte[] bytes, int bytesToWrite) throws CommandException  {
		try {
			os.writeInt(bytesToWrite);
			os.write(bytes, 0, bytesToWrite);
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}	
		
	/************************
	 * Send command helpers
	 ************************/
	 
	public static void sendQuitCommand(DataOutputStream os) throws IOException {
		os.writeByte(QUIT_CONNECTION);
		os.flush();
			
	}
	
	public static void sendTerminateCommand(DataOutputStream os) throws IOException {
		os.writeByte(TERMINATE_SERVER);
		os.flush();
	}
	
	public static void releaseObjectCommand(DataOutputStream os, int id) throws IOException {
		os.writeByte(Commands.RELEASE_OBJECT);
		os.writeInt(id);
		os.flush();
	}

	/**
	 * Send a callback request. The value is to be send separately.
	 */
	public static void sendCallbackCommand(DataOutputStream os, int callbackID, int msgID) throws CommandException {
		try {
			os.writeByte(CALLBACK);
			os.writeInt(callbackID);
			os.writeInt(msgID);
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
		
	public static void sendCallbackDoneCommand(DataOutputStream os, ValueObject value) throws CommandException {
		try {
			os.writeByte(CALLBACK_DONE);
			writeValue(os, value, false);
			os.flush();
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
	
	/**
	 * Send a start callback stream request. The data will be written separately.
	 * There will not be a callback done command. It will return as soon as the command
	 * is sent.
	 */
	public static void sendCallbackStreamCommand(DataOutputStream os, int callbackID, int msgID) throws CommandException {
		try {
			os.writeByte(CALLBACK_STREAM);
			os.writeInt(callbackID);
			os.writeInt(msgID);
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
		
	/**
	 * Read back command, expecting either a VALUE or an ERROR. You can request that
	 * it be of a specific type (if any type can be accepted then enter -1 for the type).
	 */
	public static final byte NO_TYPE_CHECK = -1;
	public static void readBackValue(DataInputStream is, ValueObject value, byte expectedType) throws CommandException {
		try {		
			byte v = is.readByte();
			switch (v) {
				case VALUE:
					readValue(is, value);
					if (expectedType != NO_TYPE_CHECK && 
							!(expectedType == value.type || (expectedType == Commands.OBJECT_ID && value.type == NEW_OBJECT_ID)))
						throw new UnexpectedCommandException(TYPE_INVALID_FOR_COMMAND, true, value);
					break;
				case ERROR:
					int code = is.readInt();
					readValue(is, value);
					throw new CommandErrorException(code, value);
				default:
					throw new UnexpectedCommandException(UNKNOWN_COMMAND, false, new Byte(v));
			}
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}				
	}
		
	
	/**
	 * GetClass command returns a GetClassReturn object.
	 */
	public static class GetClassReturn {
		public int classID;
		public boolean isInterface;
		public boolean isAbstract;
		public String superClassname;
	}
	
	public static GetClassReturn sendGetClassCommand(DataOutputStream os, DataInputStream is, String className) throws CommandException {
		try {
			os.writeByte(GET_CLASS);
			os.writeUTF(className);
			os.flush();
			
			GetClassReturn ret = new GetClassReturn();
			byte v = is.readByte();
			switch (v) {
				case GET_CLASS_RETURN:
					ret.classID = is.readInt();	// Get the new class id.
					ret.isInterface = is.readBoolean();	// Get the isInterface flag
					ret.isAbstract = is.readBoolean();	// Get the isAbstract flag.					
					ret.superClassname = is.readUTF();	// Get the super class name.
					return ret;
				case ERROR:
					int code = is.readInt();
					ValueObject value = new ValueObject();
					readValue(is, value);
					throw new CommandErrorException(code, value);
				default:
					throw new UnexpectedCommandException(UNKNOWN_COMMAND, false, new Byte(v));
			}
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
	
	/**
	 * GetClassFromID command returns a GetClassIDReturn object.
	 */
	public static class GetClassIDReturn {
		public String className;
		public boolean isInterface;
		public boolean isAbstract;
		public String superClassname;
	}
		
	public static GetClassIDReturn sendGetClassFromIDCommand(DataOutputStream os, DataInputStream is, int classID) throws CommandException {
		try {
			os.writeByte(GET_CLASS_FROM_ID);
			os.writeInt(classID);
			os.flush();
			
			GetClassIDReturn ret = new GetClassIDReturn();
			byte v = is.readByte();
			switch (v) {
				case GET_CLASS_ID_RETURN:
					ret.className = is.readUTF();	// Get the new class name.
					ret.isInterface = is.readBoolean();	// Get the isInterface flag
					ret.isAbstract = is.readBoolean();	// Get the isAbstract flag.
					ret.superClassname = is.readUTF();	// Get the super class name.
					return ret;
				case ERROR:
					int code = is.readInt();
					ValueObject value = new ValueObject();
					readValue(is, value);
					throw new CommandErrorException(code, value);
				default:
					throw new UnexpectedCommandException(UNKNOWN_COMMAND, false, new Byte(v));
			}
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
		
	public static void sendGetObjectData(DataOutputStream os, DataInputStream is, int objectID, ValueObject valueReturn) throws CommandException {
		try {
			os.writeByte(GET_OBJECT_DATA);
			os.writeInt(objectID);
			os.flush();
			readBackValue(is, valueReturn, NEW_OBJECT_ID);
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}		
	
	public static void sendErrorCommand(DataOutputStream os, int code, ValueObject errorValue) throws CommandException {
		try {
			os.writeByte(ERROR);
			os.writeInt(code);
			writeValue(os, errorValue, false);
			os.flush();
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}		
	
	public static void sendNewInstance(DataOutputStream os, DataInputStream is, int classId, String initializationString, ValueObject newValueReturn) throws CommandException {
		try {
			os.writeByte(NEW_INIT_STRING);
			os.writeInt(classId);
			os.writeUTF(initializationString);
			os.flush();
			readBackValue(is, newValueReturn, NO_TYPE_CHECK);
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}	
	
	
	public static void sendInvokeMethodCommand(DataOutputStream os, DataInputStream is, int methodID, ValueObject invokeOn, ValueObject parms, ValueObject valueReturn) throws CommandException {
		try {
			os.writeByte(INVOKE);
			os.writeInt(methodID);
			writeValue(os, invokeOn, false);
			writeValue(os, parms, false);
			os.flush();
			readBackValue(is, valueReturn, NO_TYPE_CHECK);
		} catch (CommandException e) {
			// rethrow this exception since we want these to go on out.
			throw e;
		} catch (Exception e) {
			// Wrapper this one.
			throw new UnexpectedExceptionCommandException(false, e);
		}		
	}
	
}