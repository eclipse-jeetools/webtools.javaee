/*
 * Created on May 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.archive.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipInputStream;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.internal.WrappedException;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;

/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DefectVerificationTests extends TestCase {

	/**
	 *  
	 */
	public DefectVerificationTests() {
		super();
	}

	/**
	 * @param name
	 */
	public DefectVerificationTests(String name) {
		super(name);
	}

	public static junit.framework.Test suite() {
	    /*TestSuite suite = new TestSuite();
	    suite.addTest(new DefectVerificationTests("testFilterMappingTranslator"));
	    return suite;*/
		return new TestSuite(DefectVerificationTests.class); 
	}
	

	public void testFilterMappingTranslator() throws Exception {
/*		ArchiveOptions options = new ArchiveOptions();
		options.setRendererType(ArchiveOptions.DOM);
		final String earPath = AutomatedBVT.baseDirectory + "sample.ear";
		CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
		EARFile earFile = pkg.getCommonarchiveFactory().openEARFile(options,
				earPath);
		// open the ear
		System.out.println("opening " + archive + " ... ");*/
	    
	    String archive = AutomatedBVT.baseDirectory + "QIDefectsNew.ear";
	    String name = "QIDefects.war";
		  /*
		   * crack open the ear file using the common archive:
		   */
		  CommonarchiveFactory archiveFactory = (CommonarchiveFactory) CommonarchivePackage.eINSTANCE.getEFactoryInstance();
		  ArchiveOptions options = new ArchiveOptions();
		  options.setRendererType(ArchiveOptions.SAX);
		  options.setUseJavaReflection(false);
		  //   options.setDiscriminateNestedArchives(extraProcessing);
		  		
		  		EARFile earFile = archiveFactory.openEARFile(options, archive);
				WebModuleRef warFile = null;
				for (Iterator i = earFile.getModuleRefs().iterator(); i.hasNext();) {
				    ModuleRef mref = (ModuleRef) i.next();
				    if (name.equals(mref.getUri())) { 
				        warFile =  (WebModuleRef) mref;
				    }
				}
   
				WebApp dd = (WebApp)warFile.getDeploymentDescriptor();
   
				EList filterMappings = dd.getFilterMappings();
				assertTrue("The test requires two filter mappings.", filterMappings.size() == 2); 
				FilterMapping filterMapping1 = (FilterMapping)filterMappings.get(0);
				assertNotNull("A URL Pattern was not found for filter with name " + filterMapping1.getFilter().getName(),filterMapping1.getUrlPattern());
				FilterMapping filterMapping2 = (FilterMapping)filterMappings.get(1);
				assertNotNull("A Servlet name was not found for filter with name " + filterMapping2.getFilter().getName(),filterMapping2.getServletName());
				assertNotNull("A Servlet was not found for filter with name " + filterMapping2.getFilter().getName(),filterMapping2.getServlet());
				      
	}

	public void testFormerDefect297() throws Exception {
		ArchiveOptions options = new ArchiveOptions();
		options.setRendererType(ArchiveOptions.DOM);
		final String earPath = AutomatedBVT.baseDirectory + "sample.ear";
		CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
		EARFile earFile = pkg.getCommonarchiveFactory().openEARFile(options,
				earPath);
		earFile.extractNoReopen(Archive.EXPAND_ALL);
	}

	public void testFormerDefect1459() throws Exception {
		ArchiveOptions options = new ArchiveOptions();
		options.setRendererType(ArchiveOptions.DOM);
		CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
		CommonarchiveFactory factory = pkg.getCommonarchiveFactory();

		EARFile earFile = factory.createEARFileInitialized(options, "Test.ear");
		earFile.saveAs("C:\\Test.ear");
	}

	/*
	 * public void testCorruptedEAR() throws Exception { ArchiveOptions options =
	 * new ArchiveOptions(); options.setRendererType(ArchiveOptions.DOM); final
	 * String earPath = AutomatedBVT.baseDirectory + "occam.ear";
	 * CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
	 * CommonarchiveFactory factory = pkg.getCommonarchiveFactory();
	 * 
	 * EARFile earFile = factory.openEARFile(options, earPath);
	 * 
	 * List files = earFile.getFiles();
	 * 
	 * for(int i=0; i <files.size(); i++) { System.err.println(files.get(i));
	 * readFile( (File) files.get(i)); if(files.get(i) instanceof WARFile) {
	 * WARFile war = (WARFile) files.get(i); List warFiles = war.getFiles();
	 * for(int j=0; j <warFiles.size(); j++) {
	 * System.err.println(warFiles.get(j)); readFile((File)warFiles.get(j)); } } }
	 * System.out.println("Done"); }
	 */

	/**
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void readFile(File file) throws FileNotFoundException, IOException {
		InputStream is = file.getInputStream();
		ZipInputStream zis = new ZipInputStream(is);
		try {
			while (zis.getNextEntry() != null);

		} finally {
			if (is != null)
				is.close();
			if (zis != null)
				zis.close();
			is = null;
			zis = null;
		}
	}

	public void testSavingEARWhileDeploying() throws Exception {
		try {
			ArchiveOptions options = new ArchiveOptions();
			options.setRendererType(ArchiveOptions.SAX);
			options.setUseJavaReflection(true);
			final String earPath = AutomatedBVT.baseDirectory
					+ "AuctionApp.ear";
			CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
			EARFile earFile = pkg.getCommonarchiveFactory().openEARFile(
					options, earPath);
			printFiles(earFile);
			earFile.save();

			earFile.reopen();
		} catch (WrappedException we) {
			Logger.getLogger().logError(we);
			Logger.getLogger().logError(we.getMessage());
			if (we.getCause() != null)
				Logger.getLogger().logError(we.getCause());
			if (we.getNestedException() != null)
				Logger.getLogger().logError(we.getNestedException());
			if (we.getNestedException() != null)
				Logger.getLogger().logError(we.getConcatenatedMessages());
			throw we;
		}
	}

	public void testCreatingEARFile() throws Exception {

		CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
		CommonarchiveFactory factory = pkg.getCommonarchiveFactory();
		final String earPath = AutomatedBVT.baseDirectory
				+ "InitializedEAR.ear";

		ArchiveOptions options = new ArchiveOptions();
		options.setIsReadOnly(true);
		options.setRendererType(ArchiveOptions.DOM);

		// new ear file
		EARFile earFile = factory.createEARFileInitialized(options, earPath);

		Application appl = earFile.getDeploymentDescriptor();

	}

	public void testServiceQNameTranslator() throws Exception {
        try {
			ArchiveOptions options = new ArchiveOptions();
			options.setRendererType(ArchiveOptions.SAX); 
			final String earPath = AutomatedBVT.baseDirectory + "WSSecured_InsuranceServices_EJB_WS.ear";
			CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
			CommonarchiveFactory factory = pkg.getCommonarchiveFactory();
			EARFile earFile = factory.openEARFile(options, earPath);
			printFiles(earFile);
			String tmp = "C:\\temp\\"+System.currentTimeMillis();
			earFile.extractTo(tmp, 0);  
				
			List ejbModuleRefs = earFile.getEJBModuleRefs();

			for(int i=0; i<ejbModuleRefs.size(); i++) {
				EJBModuleRef ejbmodref = (EJBModuleRef) ejbModuleRefs.get(i);
				if(ejbmodref.getUri().equals("WSInsSession20EJB.jar")) {
					Resource res = ejbmodref.getMofResource("META-INF/webservicesclient.xml"); 
					System.err.println(res.getURI());
					 
				}
			}
			/*
			 * Application app = earFile.getDeploymentDescriptor(); List modules =
			 * app.getModules(); for(int i=0; i <modules.size(); i++) {
			 * System.err.println(modules.get(i)); Module
			 * mod=(Module)modules.get(i); if( mod.isEjbModule() ) { EjbModule
			 * ejbModule = (EjbModule) mod; EJBJarFile ejbJarFile =
			 * factory.openEJBJarFile(options, "WSInsSession20EJB.jar"); EJBJar
			 * ejbjar = ejbJarFile.getDeploymentDescriptor(); }
			 *  }
			 */
		} catch (WrappedException we) {
			Logger.getLogger().logError(we);
			Logger.getLogger().logError(we.getMessage());
			if(we.getCause() != null) 
				Logger.getLogger().logError(we.getCause());
			if(we.getNestedException() != null)
				Logger.getLogger().logError(we.getNestedException());
			if(we.getNestedException() != null)
				Logger.getLogger().logError(we.getConcatenatedMessages());
			throw we;
		}  
    }	
	
	public void testReadCrimsonCase() throws Exception {
		try {
			ArchiveOptions options = new ArchiveOptions();
			options.setRendererType(ArchiveOptions.DOM); 
			final String earPath = AutomatedBVT.baseDirectory
					+ "ejb_sam_Hello.ear";
			CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
			EARFile earFile = pkg.getCommonarchiveFactory().openEARFile(
					options, earPath);
			Application app = earFile.getDeploymentDescriptor();
			assertNotNull("Could not read deployment descriptor.", app);
			System.err.println("\n\n******************Printing files from " + app.getDisplayName());
			assertNotNull("Could not read display name from deployment descriptor.", app.getDisplayName());
			printFiles(earFile);
			earFile.extractTo(AutomatedBVT.baseDirectory + "testOutput/temp", 0);
			earFile.reopen();
		} catch (WrappedException we) {
			Logger.getLogger().logError(we);
			Logger.getLogger().logError(we.getMessage());
			if (we.getCause() != null)
				Logger.getLogger().logError(we.getCause());
			if (we.getNestedException() != null)
				Logger.getLogger().logError(we.getNestedException());
			if (we.getNestedException() != null)
				Logger.getLogger().logError(we.getConcatenatedMessages());
			throw we;
		}
	}
	
	/*public void testReadInitParms13() throws Exception {
	    readInitParms(true);
	}
	public void testReadInitParms14() throws Exception{
	    readInitParms(false);
	}	*/
	
	/*public void readInitParms(boolean is13) throws Exception {
		try { 
		    System.out.println("Using J2EE " + (is13?"1.3" :"1.4") );
			ArchiveOptions options = new ArchiveOptions();
			options.setRendererType(ArchiveOptions.DOM); 
			final String earPath = AutomatedBVT.baseDirectory
					+ ((is13) ? "filter13war.ear" :  "servlet_js_filter.ear");
			CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
			EARFile earFile = pkg.getCommonarchiveFactory().openEARFile(
					options, earPath);
			WARFile warFile = (WARFile) (is13 ? earFile.getFile("FilterWar.war") :earFile.getFile("servlet_js_filter_web.war"));
			WebApp webapp = warFile.getDeploymentDescriptor(); 
			assertNotNull("The web app DD could not be located", webapp);
			EList filters = webapp.getFilters();
			Filter f = null;
			EList parms, paramValues;
			for(int i=0; i<filters.size(); i++) {
			    f = (Filter) filters.get(i);
			    System.out.println(f);
			    EList parms = f.getInitParams();
			    System.out.println("There are " + parms.size() + " InitParams");
			    for(int j=0; j<parms.size(); j++) {
			        InitParam p = (InitParam) parms.get(j);
			        System.out.println(p);
			    }
			    EList paramValues = f.getInitParamValues();
			    System.out.println("There are " + paramValues.size() + " InitParamValues");
			    for(int j=0; j<paramValues.size(); j++) {
			        ParamValue p = (ParamValue) paramValues.get(j);
			        System.out.println(p);
			    }
			    
			    System.out.println("Demo version case");
			    
			    
			    WebApp container = (WebApp) f.eContainer();
			    switch(container.getJ2EEVersionID()) {
			        case J2EEVersionConstants.J2EE_1_2_ID: 
			        case J2EEVersionConstants.J2EE_1_3_ID:

					    parms = f.getInitParams();
					    System.out.println("There are " + parms.size() + " InitParams");
					    assertEquals("There must be 2 Filter Init Params", parms.size(), 2);
					    for(int j=0; j<parms.size(); j++) {
					        InitParam ip = (InitParam) parms.get(j);
					        System.out.println(p);
					        assertTrue("The param names much match", ("param"+j+"name").equals(ip.getParamName()));
					        assertTrue("The param values much match", ("param"+j+"value").equals(ip.getParamValue()));
					    }
					    break;
			        case J2EEVersionConstants.J2EE_1_4_ID: default: {

					    paramValues = f.getInitParamValues();
			        	assertEquals("There must be 1 Filter Init Param Values", paramValues.size(), 1);
					    System.out.println("There are " + paramValues.size() + " InitParamValues");
					    
				        ParamValue pv = (ParamValue) paramValues.get(0); 
				        System.out.println(pv); 
				        assertTrue("The param names much match", ("attribute").equals(pv.getName()));
				        String expectedValue = (i == 0) ? "com.sun.ts.tests.servlet.api.javax_servlet.filter.DoFilter_Filter.SERVLET_MAPPED" : "com.sun.ts.tests.servlet.api.javax_servlet.filter.InitFilter_Filter.SERVLET_MAPPED"; 
				        assertEquals("The param values much match", expectedValue , pv.getValue());
			        }
					    break;
			    }
			}
			
			    
		} catch (WrappedException we) {
			Logger.getLogger().logError(we);
			Logger.getLogger().logError(we.getMessage());
			if (we.getCause() != null)
				Logger.getLogger().logError(we.getCause());
			if (we.getNestedException() != null)
				Logger.getLogger().logError(we.getNestedException());
			if (we.getNestedException() != null)
				Logger.getLogger().logError(we.getConcatenatedMessages());
			throw we;
		}
	}*/
	 
	
	private void printFiles(Container ar) {
		List files = ar.getFiles();

		for (int i = 0; i < files.size(); i++) {
			System.err.println(files.get(i));
			if (files.get(i) instanceof Container) {
				Container tainer = (Container) files.get(i);
				printFiles(tainer);
			}
		}
	}

}