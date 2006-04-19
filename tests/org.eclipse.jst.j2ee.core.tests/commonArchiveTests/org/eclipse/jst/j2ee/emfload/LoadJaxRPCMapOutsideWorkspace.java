package org.eclipse.jst.j2ee.emfload;

import java.net.URL;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResource;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResourceFactory;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRendererFactory;


public class LoadJaxRPCMapOutsideWorkspace extends TestCase {
	
	//public static String baseDirectory = System.getProperty("user.dir")  + java.io.File.separatorChar + "testData" + java.io.File.separatorChar + "webservices" + java.io.File.separatorChar + "META-INF" + java.io.File.separatorChar;
   
	/**
	 * <!-- begin-user-doc -->
	 * Load all the argument file paths or URIs as instances of the model.
	 * <!-- end-user-doc -->
	 * @param args the file paths or URIs.
	 * @generated
	 */
	public void testLoadFile() throws Exception {
		
		IPluginDescriptor pluginDescriptor = Platform.getPluginRegistry().getPluginDescriptor("org.eclipse.jst.j2ee.core.tests");
        URL url = pluginDescriptor.getInstallURL(); 
        String baseDirectory = Platform.asLocalURL(url).getFile() + "testData" + java.io.File.separatorChar + "webservices" + java.io.File.separatorChar + "META-INF" + java.io.File.separatorChar;
		
	// Call J2EE Init();
		
	J2EEInit.init();
	// Create a resource set to hold the resources.
	//
	ResourceSet resourceSet = new ResourceSetImpl();
	J2EEResourceFactoryRegistry registry = new J2EEResourceFactoryRegistry();
	resourceSet.setResourceFactoryRegistry(registry);
	URIConverter conv = new URIConverterImpl();
	resourceSet.setURIConverter(conv);
	EMF2DOMRendererFactory.INSTANCE.setValidating(false);
	
			URI uri = URI.createFileURI(baseDirectory + "AnnuityServices_mapping.xml");
			registry.registerLastFileSegment(uri.lastSegment(), new JaxrpcmapResourceFactory(EMF2DOMRendererFactory.INSTANCE));

			try {
				// Demand load resource for this file.
				//
				JaxrpcmapResource jaxrpcmapRes = (JaxrpcmapResource)resourceSet.getResource(uri, true);
				System.out.println("Loaded " + uri);
				jaxrpcmapRes.getContents();

			}
			catch (RuntimeException exception) {
				System.out.println("Problem loading " + uri);
				exception.printStackTrace();
			}
		}
/**
 * <!-- begin-user-doc -->
 * Prints diagnostics with indentation.
 * <!-- end-user-doc -->
 * @param diagnostic the diagnostic to print.
 * @param indent the indentation for printing.
 * @generated
 */
protected static void printDiagnostic(Diagnostic diagnostic, String indent) {
	System.out.print(indent);
	System.out.println(diagnostic.getMessage());
	for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); ) {
		printDiagnostic((Diagnostic)i.next(), indent + "  ");
	}
}
	}



