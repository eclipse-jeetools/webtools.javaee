package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: MessageDialog.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.awt.*;
/**
 * This is an AWT message dialog. It will only be used
 * if not running in the workbench by the debug mode helper.
 * It won't be loaded unless AWT is available, so it can be
 * distributed without causing problems on systems that
 * don't have AWT because it won't be loaded in that case.
 */
class MessageDialog extends Dialog {
	private java.awt.Panel ivjContentsPane = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private java.awt.TextArea ivjTextArea1 = null;
	private java.awt.Button ivjButton1 = null;
	public int result = 2;	// Nothing hit is the same as button 2.
	private java.awt.Button ivjButton2 = null;

class IvjEventHandler implements java.awt.event.ActionListener, java.awt.event.WindowListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == MessageDialog.this.getButton1()) 
				connEtoC2(e);
			if (e.getSource() == MessageDialog.this.getButton2()) 
				connEtoC3(e);
		};
		public void windowActivated(java.awt.event.WindowEvent e) {};
		public void windowClosed(java.awt.event.WindowEvent e) {};
		public void windowClosing(java.awt.event.WindowEvent e) {
			if (e.getSource() == MessageDialog.this) 
				connEtoC1(e);
		};
		public void windowDeactivated(java.awt.event.WindowEvent e) {};
		public void windowDeiconified(java.awt.event.WindowEvent e) {};
		public void windowIconified(java.awt.event.WindowEvent e) {};
		public void windowOpened(java.awt.event.WindowEvent e) {};
	};
/**
 * MessageDialog constructor comment.
 * @param owner java.awt.Frame
 * @param title java.lang.String
 * @param modal boolean
 */
public MessageDialog(Frame owner, String title, boolean modal, String msg, String button1, String button2) {
	super(owner, title, modal);

	initialize();
	getTextArea1().setText(msg);
	getButton1().setLabel(button1);
	if (button2 != null)
		getButton2().setLabel(button2);
	else {
		// We only want one button, so remove button2
		getContentsPane().remove(getButton2());
	}
	
}
/**
 * Comment
 */
public void button1_ActionPerformed() {
	result = 1;
	setVisible(false);
	return;
}
/**
 * Comment
 */
public void button2_ActionPerformed() {
	result = 2;
	setVisible(false);
	return;
}
/**
 * connEtoC1:  (MessageDialog.window.windowClosing(java.awt.event.WindowEvent) --> MessageDialog.dispose()V)
 * @param arg1 java.awt.event.WindowEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.WindowEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.dispose();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC2:  (Button1.action.actionPerformed(java.awt.event.ActionEvent) --> MessageDialog.button1_ActionPerformed()V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.button1_ActionPerformed();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC3:  (Button2.action.actionPerformed(java.awt.event.ActionEvent) --> MessageDialog.button2_ActionPerformed()V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC3(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.button2_ActionPerformed();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Display dialog.
 * @param args java.lang.String[]
 */
public static int doit(String title, String message, String button1, String button2) {
	try {
		Frame f = new java.awt.Frame(title);
		f.setVisible(true);
		// Need to massage the method since AWT doesn't have wordwrap. This is for left to right only.
		String msg = message;
		if (message.length() > 95) {
			msg = ""; //$NON-NLS-1$
nextSeg:	while (message.length() > 95) {
				// Too wide, wrapping should be done. This is just a rough guess.
				for (int i=95; 0<=i; i--) {
					if (message.charAt(i) == ' ') {
						// Break here.
						msg = msg + message.substring(0, i) + '\n';
						if (i+1 < message.length())
							message = message.substring(i+1);
						else
							message = ""; //$NON-NLS-1$
						continue nextSeg;
					}
				}
				// If we got here, then there weren't any spaces in the first 80, so look right for first space.
				for (int i=96; i<message.length(); i++) {
					if (message.charAt(i) == ' ') {
						// Break here
						msg = msg + message.substring(0, i) + '\n';
						if (i+1 < message.length())
							message = message.substring(i+1);
						else
							message = ""; //$NON-NLS-1$
						continue nextSeg;
					}
				}
			}
			msg += message;	// Now put on whatever is left.
		}
		MessageDialog aMessageDialog = new MessageDialog(f,title,true, msg, button1, button2);
		aMessageDialog.pack();
		aMessageDialog.getToolkit().beep();
		aMessageDialog.setVisible(true);
		f.dispose();
		aMessageDialog.dispose();

		return aMessageDialog.result;
	} catch (Throwable exception) {
	}

	return 2;
}
/**
 * Return the Button1 property value.
 * @return java.awt.Button
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Button getButton1() {
	if (ivjButton1 == null) {
		try {
			ivjButton1 = new java.awt.Button();
			ivjButton1.setName("Button1"); //$NON-NLS-1$
			ivjButton1.setLabel("Button1"); //$NON-NLS-1$
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjButton1;
}
/**
 * Return the Button2 property value.
 * @return java.awt.Button
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Button getButton2() {
	if (ivjButton2 == null) {
		try {
			ivjButton2 = new java.awt.Button();
			ivjButton2.setName("Button2"); //$NON-NLS-1$
			ivjButton2.setLabel("Button2"); //$NON-NLS-1$
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjButton2;
}
/**
 * Return the ContentsPane property value.
 * @return java.awt.Panel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Panel getContentsPane() {
	if (ivjContentsPane == null) {
		try {
			ivjContentsPane = new java.awt.Panel();
			ivjContentsPane.setName("ContentsPane"); //$NON-NLS-1$
			ivjContentsPane.setLayout(new java.awt.FlowLayout());
			getContentsPane().add(getTextArea1(), getTextArea1().getName());
			getContentsPane().add(getButton1(), getButton1().getName());
			getContentsPane().add(getButton2(), getButton2().getName());
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjContentsPane;
}
/**
 * Return the TextArea1 property value.
 * @return java.awt.TextArea
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextArea getTextArea1() {
	if (ivjTextArea1 == null) {
		try {
			ivjTextArea1 = new TextArea();
			ivjTextArea1.setName("TextArea1"); //$NON-NLS-1$
			ivjTextArea1.setText("TextArea1"); //$NON-NLS-1$
			ivjTextArea1.setRows(20);
			ivjTextArea1.setColumns(100);
			ivjTextArea1.setEditable(false);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjTextArea1;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// ProxyPlugin.getPlugin().getMsgLogger().write(Level.WARNING,"--------- UNCAUGHT EXCEPTION ---------");
	// ProxyPlugin.getPlugin().getMsgLogger().write(Level.WARNING,exception);
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	this.addWindowListener(ivjEventHandler);
	getButton1().addActionListener(ivjEventHandler);
	getButton2().addActionListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("MessageDialog"); //$NON-NLS-1$
		setLayout(new java.awt.BorderLayout());
		setSize(426, 240);
		add(getContentsPane(), "Center"); //$NON-NLS-1$
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}
}
