package org.eclipse.jst.j2ee.emfload;

import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.J2EEInit;


public class LoadWebServicesOutsideWorkspace extends TestCase {
	public static String baseDirectory = System.getProperty("user.dir")  + java.io.File.separatorChar + "testData" + java.io.File.separatorChar + "webservices" + java.io.File.separatorChar + "META-INF" + java.io.File.separatorChar;
   
	/**
	 * <!-- begin-user-doc -->
	 * Load all the argument file paths or URIs as instances of the model.
	 * <!-- end-user-doc -->
	 * @param args the file paths or URIs.
	 * @generated
	 */
	public void testLoadFile() throws Exception {
		
	// Call J2EE Init();
		
	J2EEInit.init();
	// Create a resource set to hold the resources.
	//
	ResourceSet resourceSet = new ResourceSetImpl();
	resourceSet.setResourceFactoryRegistry(new J2EEResourceFactoryRegistry());
	URIConverter conv = new URIConverterImpl();
	resourceSet.setURIConverter(conv);
	
			URI uri = URI.createFileURI(baseDirectory + "webservices.xml");

			try {
				// Demand load resource for this file.
				//
				Resource resource = resourceSet.getResource(uri, true);
				System.out.println("Loaded " + uri);

				// Validate the contents of the loaded resource.
				//
				for (Iterator j = resource.getContents().iterator(); j.hasNext(); ) {
					EObject eObject = (EObject)j.next();
					Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
					if (diagnostic.getSeverity() != Diagnostic.OK) {
						printDiagnostic(diagnostic, "");
					}
				}
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



