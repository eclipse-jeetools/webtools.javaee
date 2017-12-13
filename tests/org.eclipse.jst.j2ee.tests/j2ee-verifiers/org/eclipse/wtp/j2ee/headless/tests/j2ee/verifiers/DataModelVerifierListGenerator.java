/**
 * 
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.application.internal.operations.AppClientComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEUtilityJarImportDataModelProvider;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.jca.project.facet.ConnectorFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers.EJBExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers.EJBImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers.EJBProjectCreationDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.jca.verifiers.JCAExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.jca.verifiers.JCAImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.jca.verifiers.JCAProjectCreationDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.utility.verifiers.UtilityImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.utility.verifiers.UtilityProjectCreationDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.web.verifiers.WebExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.web.verifiers.WebImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.web.verifiers.WebProjectCreationDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tets.appclient.verifiers.AppClientExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tets.appclient.verifiers.AppClientImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tets.appclient.verifiers.AppClientProjectCreationDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tets.ear.verifiers.EARExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tets.ear.verifiers.EARImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tets.ear.verifiers.EARProjectCreationDataModelVerifier;


/**
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 */
public class DataModelVerifierListGenerator {
	private static Map verifiers;
	
	public static Map getVerifiers() {
		if(verifiers == null || verifiers.isEmpty()) {
			verifiers = new HashMap();
			
			verifiers.put(AppClientFacetProjectCreationDataModelProvider.class.getName(), AppClientProjectCreationDataModelVerifier.class);
			verifiers.put(AppClientComponentImportDataModelProvider.class.getName(), AppClientImportDataModelVerifier.class);
			verifiers.put(AppClientComponentExportDataModelProvider.class.getName(),AppClientExportDataModelVerifier.class);
			
			verifiers.put(EjbFacetProjectCreationDataModelProvider.class.getName(), EJBProjectCreationDataModelVerifier.class);
			verifiers.put(EJBComponentImportDataModelProvider.class.getName(), EJBImportDataModelVerifier.class);
			verifiers.put(EJBComponentExportDataModelProvider.class.getName(), EJBExportDataModelVerifier.class);
			
			verifiers.put(WebFacetProjectCreationDataModelProvider.class.getName(), WebProjectCreationDataModelVerifier.class);
			verifiers.put(WebComponentImportDataModelProvider.class.getName(), WebImportDataModelVerifier.class);
			verifiers.put(WebComponentExportDataModelProvider.class.getName(), WebExportDataModelVerifier.class);
			
			verifiers.put(ConnectorFacetProjectCreationDataModelProvider.class.getName(), JCAProjectCreationDataModelVerifier.class);
			verifiers.put(ConnectorComponentImportDataModelProvider.class.getName(), JCAImportDataModelVerifier.class);
			verifiers.put(ConnectorComponentExportDataModelProvider.class.getName(), JCAExportDataModelVerifier.class);
			
			verifiers.put(UtilityProjectCreationDataModelProvider.class.getName(), UtilityProjectCreationDataModelVerifier.class);
//			verifiers.put(J2EEUtilityJarImportDataModelProvider.class.getName(), UtilityImportDataModelVerifier.class);
			verifiers.put((new J2EEUtilityJarImportDataModelProvider()).getID(), UtilityImportDataModelVerifier.class);
			
			verifiers.put(EARFacetProjectCreationDataModelProvider.class.getName(), EARProjectCreationDataModelVerifier.class);
			verifiers.put(EARComponentImportDataModelProvider.class.getName(), EARImportDataModelVerifier.class);
			verifiers.put(EARComponentExportDataModelProvider.class.getName(), EARExportDataModelVerifier.class);
		}
		
		return verifiers;
	}
}
