/*
 * Created on Jan 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.internal.util;

import java.util.HashMap;

/**
 * @author vijayb
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
        // TODO Auto-generated constructor stub
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
