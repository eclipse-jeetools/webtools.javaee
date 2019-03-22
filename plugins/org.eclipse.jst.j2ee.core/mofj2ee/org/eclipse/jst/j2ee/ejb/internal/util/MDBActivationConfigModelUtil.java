/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 30, 2005
 */
package org.eclipse.jst.j2ee.ejb.internal.util;

import java.util.HashMap;

/**
 * @author vijayb
 */
public class MDBActivationConfigModelUtil {
    
    public static HashMap activationConfigMap;
    
    public static final String ackModeKey = "acknowledgeMode"; //$NON-NLS-1$

    public static final String destinationTypeKey = "destinationType";//$NON-NLS-1$

    public static final String durabilityKey = "subscriptionDurability";//$NON-NLS-1$

    public static final String messageSelectorKey = "messageSelector";//$NON-NLS-1$

    public static final String[] ackModeValues = new String[] { "Auto-acknowledge", "Dups-ok-acknowledge" }; //$NON-NLS-1$ //$NON-NLS-2$

    public static final String[] destinationTypeValues = new String[] { "javax.jms.Queue", "javax.jms.Topic" };//$NON-NLS-1$ //$NON-NLS-2$

    public static final String[] durabilityValue = new String[] { "Durable", "NonDurable" };//$NON-NLS-1$ //$NON-NLS-2$

    /**
     * 
     */
    public MDBActivationConfigModelUtil() {
        super();
    }
    
    public static HashMap createStandardActivationConfigMap() {
        activationConfigMap = new HashMap();
        activationConfigMap.put(ackModeKey, ackModeValues);
        activationConfigMap.put(destinationTypeKey, destinationTypeValues);
        activationConfigMap.put(durabilityKey, durabilityValue);
        activationConfigMap.put(messageSelectorKey, ""); //$NON-NLS-1$
        return activationConfigMap;
    }

}
