package org.eclipse.jst.jee.model.web.tests;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.etools.common.test.apitools.ProjectUnzipUtil;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;
import org.w3c.dom.NodeList;


@SuppressWarnings("restriction")
public class TestWebXmlModelAfterUpdate extends TestCase
{
    private static final String PROJECT_NAME = "TestJSTL_Web25";
    private static final Path PROJECT_ZIP_LOCATION = new Path("TestData/WebEditDdExternal/TestJSTL_Web25.zip");
    private static String[] projectNames = new String[]{PROJECT_NAME}; 

    private static final String TRINIDAD_FILTER_NAME = "trinidad";
    private static final String TRINIDAD_FILTER_CLASS = "trinidadClass";
    private static final String DUMMY_FILTER_NAME = "dummyFilter";
    private static final String DUMMY_FILTER_CLASS = "dummyFilterClass";

    private IProject _iProject;

    public static Test suite() {
		TestSuite suite = new TestSuite(TestWebXmlModelAfterUpdate.class);
		return suite;
	}

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        ProjectUnzipUtil util = new ProjectUnzipUtil(getLocalPath(PROJECT_ZIP_LOCATION), projectNames);
		util.createProjects();
		_iProject = ProjectUtilities.getProject(projectNames[0]);
    }


    /**
     * Writes a filter to web.xml using TextFileBuffer, then tries to remove it
     * from web.xml using the WTP web.xml model (WebApp).
     *
     * @throws Exception
     */
    public void testModelAfterWebXmlConfigOperation () 
    throws CoreException, UnsupportedEncodingException, IOException, 
           MalformedTreeException, BadLocationException
    {
        // Use WTP web.xml model to add dummy filter
        addDummyFilterUsingWebapp();

        // Use non-WTP web.xml model to add Trinidad filter
        addTrinidadFilterUsingNonWebapp();

        // Use WTP web.xml model to remove Trinidad filter
        // (Currently fails as Trinidad filter cannot be found in the model)
        removeTrinidadFilterUsingWebapp();
    }


    private void removeTrinidadFilterUsingWebapp ()
    {
        final IModelProvider modelProvider = ModelProviderManager.getModelProvider(_iProject);
        modelProvider.modify(new Runnable()
        {

            public void run ()
            {
                final WebApp webapp = (WebApp) modelProvider.getModelObject();
                assertNotNull("Expected non-null webapp", webapp);

                final List<Filter> filters = webapp.getFilters();
                assertNotNull("Expected non-null list of filters", filters);

                final Filter filterToRemove = findFilter(webapp, TRINIDAD_FILTER_NAME);
                assertNotNull("Expected Trinidad filter to be non-null", filterToRemove);

                assertTrue(filters.remove(filterToRemove));
            }

        }, new Path("WEB-INF/web.xml"));
    }


    private void addDummyFilterUsingWebapp ()
    {
        final IModelProvider modelProvider = ModelProviderManager.getModelProvider(_iProject);
        modelProvider.modify(new Runnable()
        {

            public void run ()
            {
                final Filter filter = WebFactory.eINSTANCE.createFilter();
                filter.setFilterName(DUMMY_FILTER_NAME);
                filter.setFilterClass(DUMMY_FILTER_CLASS);

                final WebApp webapp = (WebApp) modelProvider.getModelObject();
                webapp.getFilters().add(filter);
            }

        }, new Path("WEB-INF/web.xml"));
    }


    private void addTrinidadFilterUsingNonWebapp ()
    throws CoreException, UnsupportedEncodingException, IOException, MalformedTreeException, BadLocationException
    {

        final IFile webXmlIfile = _iProject.getFile("WebContent/WEB-INF/web.xml"); //$NON-NLS-1$
        assertTrue(webXmlIfile.exists());

        final IDOMModel model = (IDOMModel) StructuredModelManager.getModelManager().getModelForEdit(webXmlIfile);
        assertNotNull("Expected non-null web.xml DOM model", model);

        final String textToInsert = "<filter>\n"
                                    + "    <filter-name>" + TRINIDAD_FILTER_NAME + "</filter-name>\n"
                                    + "    <filter-class>" + TRINIDAD_FILTER_CLASS + "</filter-class>\n"
                                    + "</filter>\n";

        // Start model change
        model.aboutToChangeModel();


        // Change model (Add a "trinidad" filter tag before the closing </web-app>)

        final IDOMDocument document = model.getDocument();
        final String namespacePrefix = "ns"; //$NON-NLS-1$
        final String namespace = "http://java.sun.com/xml/ns/javaee"; //$NON-NLS-1$
        final String XPATH_WEBAPP = "//ns:web-app";
        final MapNamespaceContext namespaceContextMap = new MapNamespaceContext();
        namespaceContextMap.put(namespacePrefix, namespace);

        final NodeList nodeList = resolveXpath(document, XPATH_WEBAPP, namespaceContextMap);
        final IDOMNode node = (IDOMNode) nodeList.item(0);
        final int offset = node.getEndStructuredDocumentRegion().getStartOffset(); // just before closing </web-app> tag

        final TextEdit edit = new MultiTextEdit();
        final InsertEdit insertEdit = new InsertEdit(offset, textToInsert);
        edit.addChild(insertEdit);

        FileBuffers.getTextFileBufferManager().connect(webXmlIfile.getFullPath(), LocationKind.IFILE, new NullProgressMonitor());
        final ITextFileBuffer buffer = FileBuffers.getTextFileBufferManager().getTextFileBuffer(webXmlIfile.getFullPath(), LocationKind.IFILE);
        final IDocument idocument = buffer.getDocument();
        edit.apply(idocument);


        // End model change
        model.save();
        model.changedModel();
    }


    private static Filter findFilter (final WebApp webapp,
                                      final String filterName)
    {
        for (final Object filter : webapp.getFilters())
        {
            if (((Filter) filter).getFilterName().equals(filterName))
                return (Filter) filter;
        }

        return null;
    }


    private static NodeList resolveXpath (final IDOMDocument document,
                                          final String xPathExpression,
                                          final NamespaceContext namespaceContext)
    {
        final XPathFactory xPathFactory = XPathFactory.newInstance();
        final XPath xPath = xPathFactory.newXPath();


        if (namespaceContext != null)
            xPath.setNamespaceContext(namespaceContext);

        NodeList nodeList = null;
        try
        {
            final Object nodeEval = xPath.evaluate(xPathExpression, document, XPathConstants.NODESET);
            if (nodeEval instanceof NodeList)
                nodeList = (NodeList) nodeEval;
        }
        catch (final XPathExpressionException e)
        {
            e.printStackTrace();
        }

        return nodeList;
    }


    private static class MapNamespaceContext implements NamespaceContext
    {
        private final Map<String, String> prefixToUri = new HashMap<String, String>();
        private final Map<String, String> uriToPrefix = new HashMap<String, String>();


        public String getNamespaceURI (final String prefix)
        {
            return prefixToUri.get(prefix);
        }


        public String getPrefix (final String namespaceURI)
        {
            return uriToPrefix.get(namespaceURI);
        }


        public Iterator<String> getPrefixes (final String namespaceURI)
        {
            return prefixToUri.keySet().iterator();
        }


        /**
         * @param prefix
         * @param uri
         */
        public void put (final String prefix, final String uri)
        {
            prefixToUri.put(prefix, uri);
            uriToPrefix.put(uri, prefix);
        }
    }

    private static IPath getLocalPath(IPath path) {
		URL url = FileLocator.find(HeadlessTestsPlugin.getDefault().getBundle(), path, null);
		try {
			url = FileLocator.toFileURL(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Path(url.getPath());
	}

}