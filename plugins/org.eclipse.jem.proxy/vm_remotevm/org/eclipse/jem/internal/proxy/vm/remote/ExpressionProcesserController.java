/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ExpressionProcesserController.java,v $
 *  $Revision: 1.6 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.vm.remote;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jem.internal.proxy.common.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.common.remote.ExpressionCommands;
import org.eclipse.jem.internal.proxy.initParser.tree.*;

 
/**
 * This processes the commands for expression processing and sends them over
 * to the common expression processer.
 * 
 * This will be instantiated on the start of an expression. And then
 * each expression request from the IDE will be sent into here. The
 * reason this guy doesn't hold onto the connection and process the
 * entire expression is because we need to return to the connection
 * handler to keep the connection live (there is timeouts and stuff
 * in there that we don't want to duplicate here).
 * <p>
 * If there are any expression processing errors (versus hard io errors) we
 * will save up the error but don't do any more processing other than to make
 * sure we read the complete subcommand. This is so that the inputstream is left
 * in a valid state without standed data.
 * <p>
 * The at the sync point (either get value or sync subcommand) we will send back
 * the error.
 *  
 * @since 1.0.0
 */
public class ExpressionProcesserController {

	
	protected final RemoteVMServerThread server;
	protected final ConnectionHandler connHandler;	
	protected final ExpressionProcesser exp;
	protected Commands.ValueObject workerValue = new Commands.ValueObject();	// A worker value object so we don't need to keep creating them and releasing them.
	private ClassLoader classLoader;
	
	/**
	 * Create with a default expression processer.
	 * @param server
	 * 
	 * @since 1.0.0
	 */
	public ExpressionProcesserController(RemoteVMServerThread server, ConnectionHandler connHandler) {
		this(server, connHandler, new ExpressionProcesser(Boolean.getBoolean(ExpressionCommands.EXPRESSIONTRACE), Long.getLong(ExpressionCommands.EXPRESSIONTRACE_TIMER_THRESHOLD, -1L).longValue()));
	}
	
	/**
	 * Create from a subclass with a given expression processer.
	 * 
	 * @param server
	 * @param exp
	 * 
	 * @since 1.0.0
	 */
	protected ExpressionProcesserController(RemoteVMServerThread server, ConnectionHandler connHandler, ExpressionProcesser exp) {
		this.server = server;
		this.connHandler = connHandler;
		this.exp = exp;
	}
	
	/**
	 * Set the class loader to use for finding classes. If never set, or if <code>null</code>, then
	 * <code>Class.forName</code> will be used.
	 * 
	 * @param classLoader
	 * 
	 * @since 1.0.0
	 */
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	/*
	 * Array of primitive type names. Used to look up primtive types in primitive types array. 
	 * 
	 * @since 1.0.0
	 */
	private static final List PRIMITIVE_NAMES = Arrays.asList(new String[] {"byte", "char", "short", "int", "long", "float", "double"});
	private static final Class[] PRIMITIVE_TYPES = new Class[] {Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
	/**
	 * Load the class given the name. If not found, return null.
	 * 
	 * @param className
	 * @return
	 * 
	 * @since 1.0.0
	 */
	protected Class loadClass(String className) throws ClassNotFoundException {
		if (className == null)
			return null;
		else if (className.endsWith("[]")) {
			// We have an array request instead. This is trickier.
			return loadClass(MapTypes.getJNIFormatName(className));
		} else {
			int primIndex = PRIMITIVE_NAMES.indexOf(className);
			if (primIndex >= 0)
				return PRIMITIVE_TYPES[primIndex];
			else if (classLoader == null) {
				return Class.forName(className);
			} else {
				return classLoader.loadClass(className);
			}
		}
	}

	/**
	 * Now process the input stream. If either throws occurs, this is a hard error and we must terminate
	 * the entire connection. The input stream is in an unknown state.
	 * 
	 * @param in The input stream to get the data for the current sub-command.
	 * 
	 * @throws CommandException
	 * @throws IOException
	 * @since 1.0.0
	 */
	public void process(DataInputStream in) throws CommandException, IOException {
		// In the following subcommand processing, we always read the entire subcommand from the stream.
		// This is so that any errors during processing will not mess up the stream with unread data.
		//
		// Then we check if an error has occurred in the past. If it has, we simply break. This is because
		// once an error occurred we don't want to continue wasting time evaluating, however we need to make
		// sure that the stream is read completely so that we don't have a corrupted input stream. That way
		// when all is done we can return the error and still have a valid connection socket.
		byte subcommand = in.readByte();
		try {
			switch (subcommand) {
				case InternalExpressionTypes.PUSH_TO_PROXY_EXPRESSION_VALUE:
					// Getting a proxy push. The value is sent as valueObject, so use that to read it in.
					Commands.readValue(in, workerValue);
					Object value = connHandler.getInvokableObject(workerValue);
					if (value == null)
						exp.pushExpression(null, MethodHelper.NULL_TYPE);
					else if (workerValue.isPrimitive())
						exp.pushExpression(value, workerValue.getPrimitiveType());
					else
						exp.pushExpression(value, value.getClass());
					break;

				case InternalExpressionTypes.CAST_EXPRESSION_VALUE:
					// Get a cast request. The type is sent as valueObject.
					Commands.readValue(in, workerValue);
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushCast(classValue);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					} 
					break;

				case InternalExpressionTypes.INSTANCEOF_EXPRESSION_VALUE:
					// Get a instanceof request. The type is sent as valueObject.
					Commands.readValue(in, workerValue);
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushInstanceof(classValue);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					} 
					break;

				case InternalExpressionTypes.INFIX_EXPRESSION_VALUE:
					// Get an infix request. The operator and operand type are sent as bytes.
					byte infix_operator = in.readByte();
					byte infix_operandType = in.readByte();
					exp.pushInfix(InfixOperator.get(infix_operator), InternalInfixOperandType.get(infix_operandType));
					break;

				case InternalExpressionTypes.PREFIX_EXPRESSION_VALUE:
					// Get a prefix request. The operator is sent as byte.
					byte prefix_operandType = in.readByte();
					exp.pushPrefix(PrefixOperator.get(prefix_operandType));
					break;

				case InternalExpressionTypes.ARRAY_ACCESS_EXPRESSION_VALUE:
					// Get an array access request. The index cound is sent as int.
					int arrayAccess_Indexcount = in.readInt();
					exp.pushArrayAccess(arrayAccess_Indexcount);
					break;

				case InternalExpressionTypes.ARRAY_CREATION_EXPRESSION_VALUE:
					// Get an array creation request. The type is sent as valueObject, followed by int dimension count.
					Commands.readValue(in, workerValue);
					int arrayCreation_dimCount = in.readInt();
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushArrayCreation(classValue, arrayCreation_dimCount);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					} 

					break;

				case InternalExpressionTypes.ARRAY_INITIALIZER_EXPRESSION_VALUE:
					// Get an array initializer request. The type is sent as valueObject, followed by int expression count.
					Commands.readValue(in, workerValue);
					int stripCount = in.readInt();
					int arrayInitializer_expressionCount = in.readInt();
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushArrayInitializer(classValue, stripCount, arrayInitializer_expressionCount);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					}
					break;

				case InternalExpressionTypes.CLASS_INSTANCE_CREATION_EXPRESSION_VALUE:
					// Get a class instance creation request. The type is sent as valueObject, followed by int argument count.
					Commands.readValue(in, workerValue);
					int newInstance_argCount = in.readInt();
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushClassInstanceCreation(classValue, newInstance_argCount);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					}
					break;

				case InternalExpressionTypes.TYPERECEIVER_EXPRESSION_VALUE:
					// Get a type receiver request. The type is sent as valueObject.
					Commands.readValue(in, workerValue);
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushExpression(classValue, classValue);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					}
					break;

				case InternalExpressionTypes.FIELD_ACCESS_EXPRESSION_VALUE:
					// Get a field access request. Command.ValueObject, followed by hasReceiver as boolean.
					Commands.readValue(in, workerValue);
					boolean has_fieldAccess_receiver = in.readBoolean();
					try {
						Object fieldAccess = getFieldValue(workerValue);
						exp.pushFieldAccess(fieldAccess, workerValue.getType() == Commands.STRING, has_fieldAccess_receiver);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.						
					} catch (NoSuchFieldException e1) {
						// Do nothing, already processed.
					}
					break;

				case InternalExpressionTypes.METHOD_EXPRESSION_VALUE:
					// Get a method invocation request. Sent as Commands.ValueObject, followed by hasReceiver as boolean., and argCount as int.
					Commands.readValue(in, workerValue);
					boolean has_method_receiver = in.readBoolean();
					int method_argCount = in.readInt();
					try {
						Object method = getMethodValue(workerValue);					
						exp.pushMethodInvocation(method, workerValue.getType() == Commands.STRING, has_method_receiver, method_argCount);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (NoSuchMethodException e) {
						// Do nothing, already processed.
					}						
					break;

				case InternalExpressionTypes.CONDITIONAL_EXPRESSION_VALUE:
					// Get a conditional expression request. The expression type (ie. condition/true/false) is sent as a byte
					exp.pushConditional(InternalConditionalOperandType.get(in.readByte()));
					break;
					
				case InternalExpressionTypes.ASSIGNMENT_PROXY_EXPRESSION_VALUE:
					// Get an assignment expression request. The proxy id is sent as an int.
					int proxyid = in.readInt();
					exp.pushAssignment(new RemoteExpressionProxy(proxyid));
					break;
					
				case InternalExpressionTypes.ASSIGNMENT_EXPRESSION_VALUE:
					// Get an assignment expression request. Nothing else to read from stream.
					exp.pushAssignment();
					break;
					
				case InternalExpressionTypes.PUSH_TO_EXPRESSION_PROXY_EXPRESSION_VALUE:
					// Get a push expression proxy expression. The proxy id is sent as an int.
					exp.pushExpressionProxy(in.readInt());
					break;
				
				case InternalExpressionTypes.BLOCK_BEGIN_EXPRESSION_VALUE:
					// Get a begin block proxy expression. The block id is sent as an int.
					exp.pushBlockBegin(in.readInt());
					break;
					
				case InternalExpressionTypes.BLOCK_BREAK_EXPRESSION_VALUE:
					// Get a break block proxy expression. The block id is sent as an int.
					exp.pushBlockBreak(in.readInt());
					break;
					
				case InternalExpressionTypes.BLOCK_END_EXPRESSION_VALUE:
					// Get a end block proxy expression. The block id is sent as an int.
					exp.pushBlockEnd(in.readInt());
					break;
					
				case InternalExpressionTypes.TRY_BEGIN_EXPRESSION_VALUE:
					// Get a try begin proxy expression. The try id is sent as an int.
					exp.pushTryBegin(in.readInt());
					break;
					
				case InternalExpressionTypes.TRY_CATCH_EXPRESSION_VALUE:
					int tryNumber = in.readInt();
					Commands.readValue(in, workerValue);
					proxyid = in.readInt();
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushTryCatchClause(tryNumber, classValue, proxyid != -1 ? new RemoteExpressionProxy(proxyid) : null);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					}					
					break;

				case InternalExpressionTypes.TRY_FINALLY_EXPRESSION_VALUE:
					// Get a try finally proxy expression. The try id is sent as an int.
					exp.pushTryFinallyClause(in.readInt());
					break;

				case InternalExpressionTypes.TRY_END_EXPRESSION_VALUE:
					// Get a try end proxy expression. The try id is sent as an int.
					exp.pushTryEnd(in.readInt());
					break;
					
				case InternalExpressionTypes.THROW_EXPRESSION_VALUE:
					exp.pushThrowException();
					break;

				case InternalExpressionTypes.RETHROW_EXPRESSION_VALUE:
					// Get a rethrow proxy expression. The try id is sent as an int.
					exp.pushTryRethrow(in.readInt());
					break;

				case InternalExpressionTypes.PUSH_BEANTYPE_EXPRESSIONPROXY_EXPRESSION_VALUE:
					// Get the beantype expression proxy and resolve it.
					proxyid = in.readInt();
					String typeName = Commands.readStringData(in);
					try {
						Class classValue = loadClass(typeName);
						RemoteExpressionProxy rep = new RemoteExpressionProxy(proxyid);
						rep.setProxy(classValue, Class.class);
						exp.allocateExpressionProxy(rep);
					} catch (ClassNotFoundException e) {
						exp.processException(e);
					} catch (LinkageError e) {
						exp.processException(e);
					}
					break;
					
				case InternalExpressionTypes.PUSH_METHOD_EXPRESSIONPROXY_EXPRESSION_VALUE:
					// Get the Method expression proxy and resolve it.
					// Comes over as:
					//   int for proxy id for the method
					//   beanTypeValue for declaring class (either beantype or expression proxy)
					//   string for method name
					//   int for arg count
					//   beanTypeValue(s) for arg types (as many as arg count).
					proxyid = in.readInt();
					Commands.ValueObject decClassValue =  Commands.readValue(in, new Commands.ValueObject());
					String methodName = Commands.readStringData(in);
					int argCount = in.readInt();
					Commands.ValueObject[] args = null;
					if (argCount > 0) {
						args = new Commands.ValueObject[argCount];
						for (int i = 0; i < argCount; i++) {
							args[i] = Commands.readValue(in, new Commands.ValueObject());
						}
					}
					try {
						Class decClass = getBeanTypeValue(decClassValue);
						Class[] argClasses = null;
						if (argCount>0) {
							argClasses = new Class[argCount];
							for (int i = 0; i < argCount; i++) {
								argClasses[i] = getBeanTypeValue(args[i]);
							}
						}
						// Now get the method itself.
						Method m = decClass.getMethod(methodName, argClasses);
						RemoteExpressionProxy rep = new RemoteExpressionProxy(proxyid);
						rep.setProxy(m, Method.class);
						exp.allocateExpressionProxy(rep);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					} catch (NoSuchMethodException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					}					
					break;
					
				case InternalExpressionTypes.PUSH_FIELD_EXPRESSIONPROXY_EXPRESSION_VALUE:
					// Get the Filed expression proxy and resolve it.
					// Comes over as:
					//   int for proxy id for the field
					//   beanTypeValue for declaring class (either beantype or expression proxy)
					//   string for field name
					proxyid = in.readInt();
					decClassValue =  Commands.readValue(in, new Commands.ValueObject());
					String fieldName = Commands.readStringData(in);
					try {
						Class decClass = getBeanTypeValue(decClassValue);
						// Now get the field itself.
						Field f = decClass.getField(fieldName);
						RemoteExpressionProxy rep = new RemoteExpressionProxy(proxyid);
						rep.setProxy(f, Method.class);
						exp.allocateExpressionProxy(rep);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					} catch (NoSuchFieldException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					}					
					break;					
					
				case InternalExpressionTypes.IF_TEST_EXPRESSION_VALUE:
					// Get a if test expression request. 
					exp.pushIfElse();
					break;
					
				case InternalExpressionTypes.IF_ELSE_EXPRESSION_VALUE:
					// Get a if/else expression clause request. The clause type (ie. true/false) is sent as a byte
					exp.pushIfElse(InternalIfElseOperandType.get(in.readByte()));
					break;
					
				case InternalExpressionTypes.NEW_INSTANCE_VALUE:
					// Get a new instance from string.
					String initString = Commands.readStringData(in);
					workerValue =  Commands.readValue(in, new Commands.ValueObject());
					try {
						Class classValue = getBeanTypeValue(workerValue);
						exp.pushNewInstanceFromString(initString, classValue, classLoader);
					} catch (ClassCastException e) {
						exp.processException(e);	// Let the processor know we have a stopping error.
					} catch (ClassNotFoundException e) {
						// Do nothing, already processed.
					}
					break;
					
				case InternalExpressionTypes.MARK_VALUE:
					// Do a mark.
					int markID = in.readInt();
					exp.pushMark(markID);
					break;
					
				case InternalExpressionTypes.ENDMARK_VALUE:
					// Do an end mark.
					markID = in.readInt();
					boolean restore = in.readBoolean();
					exp.pushEndmark(markID, restore);
					break;
			}
			
		} catch (RuntimeException e) {
			exp.processException(e);
		}
		
		workerValue.set();	// Clear it out so nothing being held onto.
	}
	
	/**
	 * Get the beantype (class) out of the value object sent in. It can handle the beantype sent or
	 * as an expression proxy to a beantype expression proxy.
	 * 
	 * @param value
	 * @return
	 * @throws ClassCastException means either not a type sent in, or proxy was not a type.
	 * @throws ClassNotFoundException the expression proxy did not resolve. In that case it has already been processed by the expression processor.
	 * 
	 * @since 1.1.0
	 */
	protected Class getBeanTypeValue(Commands.ValueObject value) throws ClassCastException, ClassNotFoundException {
		Object beantype = connHandler.getInvokableObject(value);
		// It is either a type directly or is an expression proxy.
		if (value.type == Commands.INT) {
			// It is an expression proxy request.
			Object[] expvalue = new Object[2];
			if (exp.getExpressionProxyValue(((Integer) beantype).intValue(), expvalue)) {
				beantype = expvalue[0]; 
			} else
				throw new ClassNotFoundException();
		}
		return (Class) beantype;
	}
		
	/**
	 * Get the method out of the value object sent in. It can handle the method sent or
	 * as an expression proxy to a method expression proxy.
	 * @param value
	 * @return method if a method or string if a string or get the method if an expression proxy.
	 * @throws NoSuchMethodException the expression proxy did not resolve. In that case it has already been processed by the expression processor.
	 * @throws ClassCastException means either not a method sent in, or proxy was not a method.
	 * 
	 * @since 1.1.0
	 */
	protected Object getMethodValue(Commands.ValueObject value) throws NoSuchMethodException, ClassCastException {
		Object method = connHandler.getInvokableObject(value);
		// It is either a method directly or is an expression proxy.
		if (value.type == Commands.INT) {
			// It is an expression proxy request.
			Object[] expvalue = new Object[2];
			if (exp.getExpressionProxyValue(((Integer) method).intValue(), expvalue)) {
				method = expvalue[0]; 
			} else
				throw new NoSuchMethodException();
		}
		return method;
	}	

	/**
	 * Get the field out of the value object sent in. It can handle the field sent or
	 * as an expression proxy to a field expression proxy.
	 * @param value
	 * @return field if a field or string if a string or get the field if an expression proxy.
	 * @throws NoSuchFieldException the expression proxy did not resolve. In that case it has already been processed by the expression processor.
	 * @throws ClassCastException means either not a field sent in, or proxy was not a field.
	 * 
	 * @since 1.1.0
	 */
	protected Object getFieldValue(Commands.ValueObject value) throws NoSuchFieldException, ClassCastException {
		Object field = connHandler.getInvokableObject(value);
		// It is either a field directly or is an expression proxy.
		if (value.type == Commands.INT) {
			// It is an expression proxy request.
			Object[] expvalue = new Object[2];
			if (exp.getExpressionProxyValue(((Integer) field).intValue(), expvalue)) {
				field = expvalue[0]; 
			} else
				throw new NoSuchFieldException();
		}
		return field;
	}	
	
	/**
	 * Pull the Expression Proxy value into the result object.
	 * @param proxyID
	 * @param result
	 * @return <code>true</code> if value could be returned, <code>false</code> if it was no expression value assigned.
	 * 
	 * @since 1.1.0
	 */
	public boolean pullExpressionProxyValue(int proxyID, Object[] result) {
		try {
			exp.pullExpressionProxyValue(proxyID, result);
			return true;
		} catch (NoExpressionValueException e) {
		}
		return false;
	}
	
	private static class RemoteExpressionProxy implements InternalExpressionProxy {

		
		private final int proxyID;
		private Object value;
		private Class type;
		private boolean set;
		
		public RemoteExpressionProxy(int proxyID) {
			this.proxyID = proxyID;
			
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#getProxyID()
		 */
		public int getProxyID() {
			return proxyID;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#getType()
		 */
		public Class getType() {
			return type;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#getValue()
		 */
		public Object getValue() {
			return value;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#isSet()
		 */
		public boolean isSet() {
			return set;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.initParser.tree.InternalExpressionProxy#setProxy(java.lang.Object, java.lang.Class)
		 */
		public void setProxy(Object value, Class type) {
			this.value = value;
			this.type = type;
			set = true;
		}
	}
	

	/**
	 * Pull the value. 
	 * 
	 * @return r[0] is the value, r[1] is the type of the value.
	 * @throws NoExpressionValueException
	 * 
	 * @since 1.0.0
	 */
	public Object[] pullValue() throws NoExpressionValueException {
		Object[] result = new Object[2];
		exp.pullValue(result);
		return result;
	}
				
	/**
	 * Close out things.
	 * 
	 * @since 1.0.0
	 */
	public void close() {
		exp.close();
	}
	
	/**
	 * Get the throwable error.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public Throwable getErrorThrowable() {
		return exp.getErrorThrowable();
	}
	
	/**
	 * Return whether there were no errors or not.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public boolean noErrors() {
		return exp.noErrors();
	}
	
	/**
	 * Return whether there is a no expression value exception or not.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public boolean isNoExpressionValue() {
		return exp.isNoExpressionValue();
	}

}
