/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ExpressionProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.util.EventObject;
import java.util.logging.Level;
 

/**
 * This is a proxy for an IExpression evaluation value. It is used as a place holder proxy value for result of an expression, and
 * then the value can be used in a later expression, or at the end, it can callback and return a proxy in the IDE 
 * side in a callback for usage later on outside of the IExpression.
 * <p>
 * After receiving the resolved event, listeners should not hold onto the ExpressionProxy because it would then be invalid. At that
 * point they should instead hold onto the resolved bean proxy.
 * 
 * @see org.eclipse.jem.internal.proxy.core.IExpression#assignExpressionProxy()
 * @see org.eclipse.jem.internal.proxy.core.IExpression#createExpressionProxyExpression(int, ExpressionProxy)
 * @since 1.1.0
 */
public class ExpressionProxy implements IProxy {

	/**
	 * The event object for the resolved event of the proxy listener.
	 * <p>
	 * The source will be the ExpressionProxy that is being resolved.
	 * @since 1.1.0
	 */
	public static class ProxyEvent extends EventObject {
		
		private final IBeanProxy proxy;

		/**
		 * @param source the expression proxy for the event.
		 * @param proxy the bean proxy the expression proxy (source) resolved to.
		 * 
		 * @since 1.1.0
		 */
		public ProxyEvent(ExpressionProxy source, IBeanProxy proxy) {
			super(source);
			this.proxy = proxy;
		}
		
		/**
		 * Construct the event with no proxy. Used for not resolved and void.
		 * @param source
		 * 
		 * @since 1.1.0
		 */
		public ProxyEvent(ExpressionProxy source) {
			this(source, null);
		}
		
		/**
		 * Get the proxy value that the expression proxy (source) resolved to.
		 * @return
		 * 
		 * @since 1.1.0
		 */
		public IBeanProxy getProxy() {
			return proxy;
		}
	}
	
	/**
	 * Listener for expression proxy events.
	 * 
	 * @since 1.1.0
	 */
	public interface ProxyListener {
		/**
		 * The expression proxy has been resolved. The event contains the resolved proxy.
		 * At this point, any listeners should no longer hold onto the ExpressionProxy
		 * because it is now invalid. They should hold onto the resolved bean proxy instead.
		 * 
		 * @param event
		 * 
		 * @since 1.1.0
		 */
		public void proxyResolved(ProxyEvent event);
		
		/**
		 * This is called for ExpressionProxies that have a callback listener, but the proxy 
		 * was never resolved. This means that the expression that assigns to the proxy was
		 * not executed. In this case the event does not have a bean proxy in it.
		 * 
		 * @param event
		 * 
		 * @since 1.1.0
		 */
		public void proxyNotResolved(ProxyEvent event);
		
		/**
		 * This is called for ExpressionProxies that were assigned to an expression that
		 * had a <code>VOID</code> return type. This is usually for method invocations that
		 * have a void return type. In this case the event does not have a bean proxy in it.
		 * @param event
		 * 
		 * @since 1.1.0
		 */
		public void proxyVoid(ProxyEvent event);
	}
	
	/**
	 * An implementation of ProxyListener that does nothing. It can be
	 * used as a superclass for individual listeners that are only interested
	 * in some of the events.
	 * 
	 * @since 1.1.0
	 */
	public static class ProxyAdapter implements ProxyListener {
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyNotResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyNotResolved(ProxyEvent event) {
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyResolved(ProxyEvent event) {
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyVoid(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyVoid(ProxyEvent event) {
		}
	}
	
	final private int proxyID;
	final private int proxyType;
	private ListenerList listenerList;
	private Expression expression;
	
	/**
	 * Create with the given proxy id.
	 * @param proxyid
	 * 
	 * @since 1.1.0
	 */
	public ExpressionProxy(int proxyid, int proxyType, Expression expression) {
		this.proxyID = proxyid;
		this.proxyType = proxyType;
		this.expression = expression;
	}
	
	/**
	 * Get the expression this proxy is for. 
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public Expression getExpression() {
		return expression;
	}
	
	/**
	 * Used internally in expression to get the proxy type. 
	 * @return
	 * 
	 * @since 1.1.0
	 */
	protected final int getProxyType() {
		return proxyType;
	}
	
	/**
	 * Return the proxy id.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public final int getProxyID() {
		return proxyID;
	}
	
	/**
	 * Add a listener. If listener already added, it has no effect.
	 * @param listener
	 * 
	 * @since 1.1.0
	 */
	public void addProxyListener(ProxyListener listener) {
		if (listenerList == null)
			listenerList = new ListenerList();
		listenerList.add(listener);
	}
	
	/**
	 * Remove a listener. If listener not already added, then no error.
	 * @param listener
	 * 
	 * @since 1.1.0
	 */
	public void removeProxyListener(ProxyListener listener) {
		if (listenerList != null)
			listenerList.remove(listener);
	}
	
	/**
	 * Used by internal subclasses or Expression to know if anyone is listening.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	protected final boolean hasListeners() {
		return listenerList != null && !listenerList.isEmpty();
	}
	
	/**
	 * The proxy has been fired (one of the events has occurred). Subclasses should clean
	 * up and not hold onto anything that would be expensive in case the proxy is still
	 * held onto be someone. For example the default clears the listener list.
	 * <p>
	 * <b>Note:</b> Subclasses must call super.dispose().
	 * 
	 * @since 1.1.0
	 */
	protected void dispose() {
		listenerList = null;
		expression = null;
	}
	
	/**
	 * Used by internal subclasses  or by Expression to fire the resolved event. Once fired all listeners are removed.
	 * This is because only one kind of firing can be done for a expression proxy and then the proxy is dead. This
	 * prevents listeners from being held on to if any one accidently still holds the proxy.
	 * @param proxy
	 * 
	 * @since 1.1.0
	 */
	protected void fireResolved(IBeanProxy proxy) {
		if (hasListeners()) {
			ProxyEvent event = new ProxyEvent(this, proxy);
			Object[] listeners = listenerList.getListeners();
			for (int i = 0; i < listeners.length; i++) {
				try {
					((ProxyListener) listeners[i]).proxyResolved(event);
				} catch (RuntimeException e) {
					ProxyPlugin.getPlugin().getLogger().log(e, Level.WARNING);
				}
			}
		}
		dispose();
	}
	
	/**
	 * Used by internal subclasses or by Expression to fire the not resolved event. Once fired all listeners are removed.
	 * This is because only one kind of firing can be done for a expression proxy and then the proxy is dead. This
	 * prevents listeners from being held on to if any one accidently still holds the proxy.
	 * 
	 * @since 1.1.0
	 */
	protected void fireNotResolved() {
		if (hasListeners()) {
			ProxyEvent event = new ProxyEvent(this);
			Object[] listeners = listenerList.getListeners();
			for (int i = 0; i < listeners.length; i++) {
				try {
					((ProxyListener) listeners[i]).proxyNotResolved(event);
				} catch (RuntimeException e) {
					ProxyPlugin.getPlugin().getLogger().log(e, Level.WARNING);
				}
				
			}
		}
		dispose();
	}
	
	/**
	 * Used by internal subclasses or by Expression to fire the void resolved event. Once fired all listeners are removed.
	 * This is because only one kind of firing can be done for a expression proxy and then the proxy is dead. This
	 * prevents listeners from being held on to if any one accidently still holds the proxy.
	 * 
	 * @since 1.1.0
	 */
	protected void fireVoidResolved() {
		if (hasListeners()) {
			ProxyEvent event = new ProxyEvent(this);
			Object[] listeners = listenerList.getListeners();
			for (int i = 0; i < listeners.length; i++) {
				try {
					((ProxyListener) listeners[i]).proxyVoid(event);
				} catch (RuntimeException e) {
					ProxyPlugin.getPlugin().getLogger().log(e, Level.WARNING);
				}
				
			}
		}
		dispose();
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isBeanProxy()
	 */
	public final boolean isBeanProxy() {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isExpressionProxy()
	 */
	public final boolean isExpressionProxy() {
		return true;
	}
	
	/**
	 * Is this proxy valid for reassignment. By default only if the type is NORMAL_EXPRESSION_PROXY.
	 * Subclasses may override and return true in their cases.
	 * 
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public boolean isValidForReassignment() {
		return getProxyType() == Expression.NORMAL_EXPRESSION_PROXY;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return super.toString()+": "+getProxyID();
	}
}
