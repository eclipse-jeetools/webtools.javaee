/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.migration;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.SubscriptionDurabilityKind;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author DABERG
 *
 * This class is used to migrate J2EE specific objects from one
 * J2EE version to another.
 */
public class J2EESpecificationMigrator extends SpecificationMigrator implements J2EEConstants, J2EESpecificationMigrationConstants {
 
	
	/**
	 * Public constructor for migrating a single ejb
	 */
	public J2EESpecificationMigrator(String aVersion, boolean complex) {
	    super(aVersion, complex);
	}
	
	/**
	 * Public constructor for migrating the entire deployment descriptor
	 */
	public J2EESpecificationMigrator(XMLResource anXmlResource, String aVersion, boolean complex) {
	    super(anXmlResource, aVersion, complex);
	} 

	protected QueryMethod convertMethodElementToQueryMethod(MethodElement me) {
		QueryMethod queryMethod = ((EjbPackage)EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory().createQueryMethod();
		queryMethod.setParms(me.getParms());
		queryMethod.setName(me.getName());
		return queryMethod;
	}
	protected void ensureBeanVersionsSet(EJBJar jar) {
		if (jar != null) {
			List beans = jar.getEnterpriseBeans();
			int size = beans.size();
			EnterpriseBean bean;
			for (int i = 0; i < size; i++) {
				bean = (EnterpriseBean) beans.get(i);
				if (bean.isContainerManagedEntity() && ((ContainerManagedEntity)bean).isVersion1_X())
					((ContainerManagedEntity)bean).setVersion(ContainerManagedEntity.VERSION_1_X);
			}
		}
	}
	protected String format(String aPattern, String arg1) {
		return MessageFormat.format(aPattern, new String[]{arg1});
	}
	protected String format(String aPattern, String arg1, String arg2) {
		return MessageFormat.format(aPattern, new String[]{arg1, arg2});
	}
	protected J2EEMigrationStatus getMigrateToLowerLevelStatus(EJBJar ejbJar) {
		J2EEMigrationStatus status = null, beanStatus;
		if (ejbJar != null) {
			List beans = ejbJar.getEnterpriseBeans();
			int size = beans.size();
			EnterpriseBean bean;
			for (int i = 0; i < size; i++) {
				bean = (EnterpriseBean) beans.get(i);
				beanStatus = getMigrateToLowerLevelStatus(bean);
				if (status == null)
					status = beanStatus;
				else
					status = status.append(beanStatus);
			}
		}
		return status;
	}
	protected J2EEMigrationStatus getMigrateToLowerLevelStatus(EnterpriseBean bean) {
		if (bean.isMessageDriven() && isVersion1_2())
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_POSSIBLE, bean);
		if (bean.isContainerManagedEntity() && ((ContainerManagedEntity)bean).isVersion2_X() && isVersion1_2())
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_POSSIBLE, bean);
		return null;
	} 
	
	protected J2EEMigrationStatus migrate(ContainerManagedEntity aCMP) {
		boolean is1_x = aCMP.getVersion().equals(ContainerManagedEntity.VERSION_1_X);
		if (isVersion1_2() && !is1_x)
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_POSSIBLE, aCMP);
		else if ((isVersion1_4() || isVersion1_3()) && is1_x) {
			return migrate1_xCMPFor2_0Target(aCMP);
		}
		return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, aCMP);
	}
	
	protected void migrateDescriptionsTo14(XMLResource resource) {
	    ((EJBResource)resource).getEJBJar().getDescription();
	    
	}
	

	protected J2EEMigrationStatus migrateTo13(EnterpriseBean anEJB) {
		if (anEJB != null && getVersion() != null) {
			if (anEJB.isContainerManagedEntity())
				return migrate((ContainerManagedEntity) anEJB);
			return migrateBeanInterfaces(anEJB);
		}
		return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, anEJB);
	}

	/**
	 * Migrate a 1.1 CMP to a 1.1 CMP in a 2.0 EJB project.
	 * Set the cmp-version to 1.x.
	 * @param aCMP
	 * @return J2EEMigrationStatus
	 */
	protected J2EEMigrationStatus migrate1_xCMPFor2_0Target(ContainerManagedEntity aCMP) {
		//This is necessary to force a change in the version so the event is
		//not a TOUCH which will do nothing.
		aCMP.setVersion(ContainerManagedEntity.VERSION_2_X);
		if (!isComplex()) 
			//reset it if we are not doing full migration
			aCMP.setVersion(ContainerManagedEntity.VERSION_1_X);
		else
			aCMP.setAbstractSchemaName(aCMP.getName());
		
		return  new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, aCMP);
	}
	protected J2EEMigrationStatus migrateBeanInterfaces(EnterpriseBean anEJB) {
		if (isVersion1_2() && anEJB.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID && anEJB.hasLocalClient()) {
			anEJB.setLocalInterface(null);
			anEJB.setLocalHomeInterface(null);
			return new J2EEMigrationStatus(J2EEMigrationStatus.WARNING, anEJB, format(REMOVED_LOCAL_CLIENT_MSG, anEJB.getName()));
		}
		return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, anEJB);
	}

	private J2EEMigrationMultiStatus migrateBeans13(EJBJar jar) {
		J2EEMigrationMultiStatus status = new J2EEMigrationMultiStatus();
		if (jar != null) {
			List beans = jar.getEnterpriseBeans();
			int size = beans.size();
			EnterpriseBean bean;
			for (int i = 0; i < size; i++) {
				bean = (EnterpriseBean) beans.get(i);
				status.merge(migrateTo13(bean));
			}
		}
		return status;
	}
	
	private J2EEMigrationMultiStatus migrateBeans14(EJBJar jar,HashMap beanMap) {
		J2EEMigrationMultiStatus status = new J2EEMigrationMultiStatus();
		if (jar != null) {
			List beans = jar.getEnterpriseBeans();
			int size = beans.size();
			EnterpriseBean bean;
			for (int i = 0; i < size; i++) {
				bean = (EnterpriseBean) beans.get(i);
				status.merge(migrateTo14Bean(bean,beanMap));
			}
		}
		return status;
	}
	/**
	 * 
	 */
	private J2EEMigrationStatus migrateMessageDrivenTo14(MessageDriven anEJB) {
		
		ActivationConfig config = EjbFactoryImpl.getActiveFactory().createActivationConfig();
		ActivationConfigProperty property = null;
		AcknowledgeMode ackMode = anEJB.getAcknowledgeMode();
		String ackModeName = ackMode.getName();
		
		if(ackModeName != null && ackModeName.length() > 0) {
			property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			property.setName("acknowledgeMode"); property.setValue(ackModeName); //$NON-NLS-1$
			config.getConfigProperties().add(property);
			anEJB.setAcknowledgeMode(null);
		}
		
		MessageDrivenDestination destination = anEJB.getDestination();
		if(destination != null) 
			create14Destination(anEJB,destination,config);
			
		String messageSelector = anEJB.getMessageSelector();
		
		if(messageSelector != null && messageSelector.length() > 0) {
			property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			property.setName("messageSelector"); property.setValue(messageSelector); //$NON-NLS-1$
			config.getConfigProperties().add(property);
			anEJB.setMessageSelector(null);
			
		}
		anEJB.setActivationConfig(config);
		
		return  new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, anEJB);
	}
	
	/**
	 * 
	 */
	private void create14Destination(MessageDriven bean,MessageDrivenDestination destination,ActivationConfig config ) {
		DestinationType type = destination.getType();
		String typeString = null;
		if (type != null) {
			switch (type.getValue()) {
				case DestinationType.QUEUE :
					typeString =  "javax.jms.Queue"; //$NON-NLS-1$
				    break;
				case DestinationType.TOPIC :
					typeString =  "javax.jms.Topic"; //$NON-NLS-1$
					break;
			}
		}
		JavaHelpers helper = JavaRefFactory.eINSTANCE.reflectType(typeString,bean.eContainer());
		if(helper != null)
		  bean.setMessageDestination(helper.getWrapper());
		
		ActivationConfigProperty property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
		property.setName("destinationType"); property.setValue(typeString); //$NON-NLS-1$
		config.getConfigProperties().add(property);
		
		SubscriptionDurabilityKind durability = destination.getSubscriptionDurability();
		String durabilityName = durability.getName();
		if(durabilityName != null && durabilityName.length() > 0) {
			property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			property.setName("subscriptionDurability"); property.setValue(durabilityName); //$NON-NLS-1$
			config.getConfigProperties().add(property);
			bean.getDestination().setSubscriptionDurability(null);
		}
	}
	 

	protected J2EEMigrationStatus migrateTo13(ApplicationClientResource appClientResource) {
		if (!basicNeedsToMigrate()) 
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, appClientResource);
		String pubId, sysId;
		boolean isVersion1_3 = isVersion1_3();
		pubId = isVersion1_3 ? APP_CLIENT_PUBLICID_1_3 : APP_CLIENT_PUBLICID_1_2;
		sysId = isVersion1_3 ? APP_CLIENT_SYSTEMID_1_3 : APP_CLIENT_SYSTEMID_1_2;
		appClientResource.setDoctypeValues(pubId, sysId);
		return new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, appClientResource);
	}
	
	protected J2EEMigrationStatus migrateTo13(ApplicationResource appResource) {
		if (!basicNeedsToMigrate()) 
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, appResource);
		String pubId, sysId;
		boolean isVersion1_3 = isVersion1_3();
		pubId = isVersion1_3 ? APPLICATION_PUBLICID_1_3 : APPLICATION_PUBLICID_1_2;
		sysId = isVersion1_3 ? APPLICATION_SYSTEMID_1_3 : APPLICATION_SYSTEMID_1_2;
		appResource.setDoctypeValues(pubId, sysId);
		return new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, appResource);
	}
	
	protected J2EEMigrationStatus migrateTo13(EJBResource ejbResource) {
		J2EEMigrationStatus status = null;
		boolean b = basicNeedsToMigrate();
		String pubId, sysId;
		boolean isVersion1_3 = isVersion1_3();
		EJBJar jar = ejbResource.getEJBJar();
		if (b) {
			if (!isVersion1_3) {
				status = getMigrateToLowerLevelStatus(jar);
				if (status != null)
					return status;
			}
			ensureBeanVersionsSet(jar);
			pubId = isVersion1_3 ? EJBJAR_PUBLICID_2_0 : EJBJAR_PUBLICID_1_1;
			sysId = isVersion1_3 ? EJBJAR_SYSTEMID_2_0 : EJBJAR_SYSTEMID_1_1;
			ejbResource.setDoctypeValues(pubId, sysId);
		} else {
			status = new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, ejbResource);
		}
		boolean notNeeded = status != null;
		status = mergeStatuses(status, migrateBeans13(jar));
		if (!notNeeded)
			status = mergeStatuses(status, new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, ejbResource));
		return status;
	}
	
	protected J2EEMigrationStatus migrateTo13(WebAppResource webResource) {
		if (!basicNeedsToMigrate()) 
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, webResource);
		String pubId, sysId;
		boolean isVersion1_3 = isVersion1_3();
		pubId = isVersion1_3 ? WEBAPP_PUBLICID_2_3 : WEBAPP_PUBLICID_2_2;
		sysId = isVersion1_3 ? WEBAPP_SYSTEMID_2_3 : WEBAPP_SYSTEMID_2_2;
		webResource.setDoctypeValues(pubId, sysId);
		return new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, webResource);
	} 
	
	protected J2EEMigrationStatus migrateTo14(ApplicationClientResource appClientResource) {
		if (!basicNeedsMigrationTo14()) 
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, appClientResource);
		appClientResource.setModuleVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		migrateCompatibilityDescriptionGroup(appClientResource);
		EObject rootObject = appClientResource.getRootObject();
		appClientResource.removePreservingIds(rootObject);
		appClientResource.getContents().remove(rootObject);
		appClientResource.getContents().add(rootObject);
		return new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, appClientResource);
	}
	
	protected J2EEMigrationStatus migrateTo14(ApplicationResource appResource) {
		if (!basicNeedsMigrationTo14()) 
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, appResource);
		appResource.setJ2EEVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		migrateCompatibilityDescriptionGroup(appResource);
		EObject rootObject = appResource.getRootObject();
		appResource.removePreservingIds(rootObject);
		appResource.getContents().add(rootObject);
		return new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, appResource);
	}
	
	protected J2EEMigrationStatus migrateTo14(ConnectorResource connectorResource) {
		if (!basicNeedsMigrationTo14()) 
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED,connectorResource);
		connectorResource.setModuleVersionID(J2EEVersionConstants.JCA_1_5_ID);
		EObject rootObject = connectorResource.getRootObject();
		connectorResource.getContents().remove(rootObject);
		connectorResource.getContents().add(rootObject);
		Connector14SpecificationMigrator migrator = new Connector14SpecificationMigrator();
		J2EEMigrationStatus status = migrator.migrateConnectorTo14(connectorResource.getConnector());
		status = mergeStatuses(status,new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK,connectorResource));
		return status;
	}
	
	protected J2EEMigrationStatus migrateTo14(EJBResource ejbResource) {
		J2EEMigrationStatus status = null;
		boolean b = basicNeedsMigrationTo14();
		boolean isVersion1_4 = isVersion1_4();
		EJBJar jar = ejbResource.getEJBJar();
		HashMap beanCache = new HashMap();
		if(jar.getVersionID() == EJB_1_1_ID)
			createBeanCache(jar,beanCache);
		if (b) {
			if (!isVersion1_4) {
				status = getMigrateToLowerLevelStatus(jar);
				if (status != null)
					return status;
			}
			ejbResource.setModuleVersionID(J2EEVersionConstants.EJB_2_1_ID);
			migrateCompatibilityDescriptionGroup(ejbResource);
			EObject rootObject = ejbResource.getRootObject();
			ejbResource.removePreservingIds(rootObject);
			ejbResource.getContents().add(rootObject);
		
		} else {
			status = new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, ejbResource);
		}
		boolean notNeeded = status != null;
		status = mergeStatuses(status, migrateBeans14(jar,beanCache));
		if (!notNeeded)
			status = mergeStatuses(status, new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, ejbResource));
		return status;
	}
	
	protected void migrateCompatibilityDescriptionGroup(XMLResource resource) {
	    TreeIterator contents = resource.getAllContents();
	    EObject object = null;
	    while(contents.hasNext()) {
	        object = (EObject) contents.next();
	        migrateCompatibilityDescriptionGroup(object);	        
	  }
	}
	
	private static final EClass COMPATIBILITY_DESCRIPTION_GROUP_ECLASS = CommonPackage.eINSTANCE.getCompatibilityDescriptionGroup();
	 
	protected void migrateCompatibilityDescriptionGroup(EObject eObj) {
	    if(COMPATIBILITY_DESCRIPTION_GROUP_ECLASS.isInstance(eObj)) { 
	        CompatibilityDescriptionGroupImpl cg = (CompatibilityDescriptionGroupImpl)eObj;
	        if(cg.getDisplayNameGen() != null) cg.setDisplayName(cg.getDisplayNameGen());
	        if(cg.getDescriptionGen() != null) cg.setDescription(cg.getDescriptionGen());
	        if(cg.getLargeIconGen() != null) cg.setLargeIcon(cg.getLargeIconGen());
	        if(cg.getSmallIconGen() != null) cg.setSmallIcon(cg.getSmallIconGen());
	    } 
	}

    /**
	 * @param jar
	 * @param beanCache
	 */
	private void createBeanCache(EJBJar jar, HashMap beanCache) {
			List beans = jar.getEnterpriseBeans();
			EnterpriseBean bean;
			for (int i = 0; i < beans.size(); i++) {
				bean = (EnterpriseBean) beans.get(i);
				if (bean.isContainerManagedEntity() && ((ContainerManagedEntity)bean).isVersion1_X())
					beanCache.put(bean,ContainerManagedEntity.VERSION_1_X);
			}
	}

	protected J2EEMigrationStatus migrateTo14(WebAppResource webResource) {
		J2EEMigrationStatus status = null;
		try {
			if (!basicNeedsMigrationTo14()) 
				return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, webResource);
			webResource.setModuleVersionID(J2EEVersionConstants.WEB_2_4_ID);
			migrateCompatibilityDescriptionGroup(webResource);
			EObject rootObject = webResource.getRootObject();
			webResource.removePreservingIds(rootObject);
			webResource.getContents().add(rootObject);
			webResource.saveIfNecessary();
			War14SpecificationMigrator migrator = new War14SpecificationMigrator();
			status = migrator.migrateWebAppTo14(webResource.getWebApp());
			status = mergeStatuses(status,new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK, webResource));
			return status;
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		}
		return status;
	}
	private  J2EEMigrationStatus migrateTo14Bean(EnterpriseBean anEJB,HashMap beanMap) {
		if(!anEJB.isMessageDriven()) {
			if (anEJB != null && getVersion() != null) {
				if (anEJB.isContainerManagedEntity())
					return migrate((ContainerManagedEntity)anEJB,beanMap);
				return migrateBeanInterfaces(anEJB);
			}
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, anEJB);
		} 
		if(anEJB.isMessageDriven()) {
			return migrateMessageDrivenTo14((MessageDriven)anEJB);
		}
		return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED,anEJB);
	}

	/**
	 * @param entity
	 * @param beanMap
	 * @return
	 */
	protected J2EEMigrationStatus migrate(ContainerManagedEntity aCMP, HashMap beanMap) {
		String versionString = (String)beanMap.get(aCMP);
		boolean is1_x = versionString != null && versionString.equals(ContainerManagedEntity.VERSION_1_X);
		if (isVersion1_2() && !is1_x)
			return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_POSSIBLE, aCMP);
		else if ((isVersion1_4() || isVersion1_3()) && is1_x) {
			return migrate1_xCMPFor2_0Target(aCMP);
		}
		return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, aCMP);
	}
 
}


