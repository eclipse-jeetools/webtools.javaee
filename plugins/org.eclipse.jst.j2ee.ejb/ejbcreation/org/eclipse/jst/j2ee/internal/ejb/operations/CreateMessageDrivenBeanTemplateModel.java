/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 28, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.DestinationType;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CreateMessageDrivenBeanTemplateModel extends CreateEnterpriseBeanTemplateModel {
	/**
	 * @param model
	 */
	public CreateMessageDrivenBeanTemplateModel(CreateMessageDrivenBeanDataModel model) {
		super(model);
	}

	public String getTransactionType() {
		return getProperty(CreateMessageDrivenBeanDataModel.TRANSACTION_TYPE_NAME);
	}

	public boolean hasAcknowledgeMode() {
		return model.isSet(CreateMessageDrivenBeanDataModel.ACKNOWLEDGE_MODE);
	}

	public String getAcknowledgeMode() {
		AcknowledgeMode mode = (AcknowledgeMode) model.getProperty(CreateMessageDrivenBeanDataModel.ACKNOWLEDGE_MODE);
		if (mode != null) {
			switch (mode.getValue()) {
				case AcknowledgeMode.AUTO_ACKNOWLEDGE :
					return "Auto-acknowledge"; //$NON-NLS-1$
				case AcknowledgeMode.DUPS_OK_ACKNOWLEDGE :
					return "Dups-ok-acknowledge"; //$NON-NLS-1$
			}
		}
		return ""; //$NON-NLS-1$
	}

	public String getDestinationType() {
		DestinationType type = (DestinationType) model.getProperty(CreateMessageDrivenBeanDataModel.DESTINATION_TYPE);
		if (type != null) {
			switch (type.getValue()) {
				case DestinationType.QUEUE :
					return "javax.jms.Queue"; //$NON-NLS-1$
				case DestinationType.TOPIC :
					return "javax.jms.Topic"; //$NON-NLS-1$
			}
		}
		return ""; //$NON-NLS-1$
	}

	public boolean hasSubscriptionDurability() {
		return model.isSet(CreateMessageDrivenBeanDataModel.SUBSCRIPTION_DURABILITY);
	}

	public String getSubscriptionDurability() {
		return getProperty(CreateMessageDrivenBeanDataModel.SUBSCRIPTION_DURABILITY_NAME);
	}

	public String getMessageSelector() {
		return getProperty(CreateMessageDrivenBeanDataModel.MESSAGE_SELECTOR);
	}
}