/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.classgen;

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.codegen.jmerge.JMerger;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.corext.codemanipulation.ImportsStructure;
import org.eclipse.jdt.internal.corext.util.CodeFormatterUtil;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.ui.preferences.JavaPreferencesSettings;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.j2ee.ejb.annotations.IEnterpriseBeanDelegate;
import org.eclipse.text.edits.TextEdit;




public class EjbBuilder {

	protected IProgressMonitor monitor;
	protected IPackageFragmentRoot packageFragmentRoot;
	protected String packageName = "";
	protected String typeName = "";
	protected String typeStub;
	protected String typeComment;
	protected String methodStub;
	protected String fields;
	protected IConfigurationElement configurationElement;
	protected JControlModel mergeOptions;

	protected IEnterpriseBeanDelegate enterpriseBeanDelegate; 
	
	/**
	 * @return Returns the mergeOptions.
	 * @throws CoreException 
	 * @throws InvalidRegistryObjectException 
	 */
	public JControlModel getMergeOptions() throws InvalidRegistryObjectException, CoreException {
		if (mergeOptions == null) {
			if(getConfigurationElement() != null){
				String declaringPlugin = getConfigurationElement().getDeclaringExtension().getNamespace();
				String uri = Platform.getBundle(declaringPlugin).getEntry("/ejb-merge.xml").toString();
				mergeOptions = new JControlModel(uri);
				
			}else{
				mergeOptions = new JControlModel("");
			}
		}

		return mergeOptions;
	}
	/**
	 * @param mergeOptions The mergeOptions to set.
	 */
	public void setMergeOptions(JControlModel mergeOptions) {
		this.mergeOptions = mergeOptions;
	}
	IType fCreatedType ;
	
	public EjbBuilder() {
		super();
	}
	
	public String merge(String sourceSrc, String targetSrc) throws InvalidRegistryObjectException, CoreException 
	{

		if(targetSrc == null || targetSrc.length() == 0)
			return sourceSrc;
		
		String modifiedTargetSrc = insertMissingComments(sourceSrc, targetSrc);
	
			
        JMerger jMerger = new JMerger();
        jMerger.setControlModel(getMergeOptions());
        jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(sourceSrc));
        jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForContents(modifiedTargetSrc));
        jMerger.merge();
        
        return jMerger.getTargetCompilationUnitContents();

	}
	
	/**
	 * @param sourceSrc
	 * @param targetSrc
	 */
	private String insertMissingComments(String sourceSrc, String targetSrc) {
		// EMF JMerge does not transfer non-existent member comments
		// without e-completely overwriting the memeber.  This phase 
		// makes sure that if the target member has no comment, source
		// comment is inserted before the merge
		IDocument document = new Document(targetSrc);
		ASTParser sourceParser = ASTParser.newParser(AST.JLS2);
		sourceParser.setSource(sourceSrc.toCharArray());
		final CompilationUnit sourceAst = (CompilationUnit) sourceParser.createAST(null);
		ASTParser targetParser = ASTParser.newParser(AST.JLS2);
		targetParser.setSource(targetSrc.toCharArray());
		final CompilationUnit targetAst = (CompilationUnit) targetParser.createAST(null);
		targetAst.recordModifications();
		
		class InsertComments extends ASTVisitor {
			public InsertComments() {
				super(true);
			}
	
			public boolean visit(TypeDeclaration type) {
				Javadoc javadoc = type.getJavadoc();
				if( !isGenerated(javadoc)){
					Javadoc srcJavaDoc = getTypeJavaDoc(sourceAst,type);
					Javadoc newJavadoc = (Javadoc) ASTNode.copySubtree(targetAst.getAST(), srcJavaDoc);
					type.setJavadoc(newJavadoc);
				}
				return true;
			}
	

			public boolean visit(MethodDeclaration node) {
				Javadoc javadoc = node.getJavadoc();
				if( !isGenerated(javadoc) ){
					Javadoc srcJavaDoc = getMethodJavaDoc(sourceAst,node);
					Javadoc newJavadoc = (Javadoc) ASTNode.copySubtree(targetAst.getAST(), srcJavaDoc);
					node.setJavadoc(newJavadoc);
				}				
				return true;
			}


		}
	
		targetAst.accept(new InsertComments());
		
//		ASTRewrite rewriter = ASTRewrite.create(targetAst.getAST());
//		rewriter.
		TextEdit edit = targetAst.rewrite(document, JavaCore.getOptions());
		try {
			edit.apply(document);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return document.get();

	}
	
    /**
     * @param javadoc
     * @return
     */
    private boolean isGenerated(Javadoc javadoc) {
        if( javadoc == null)
            return false;
        Iterator tags = javadoc.tags().iterator();
        while (tags.hasNext()) {
            TagElement	tag = (TagElement) tags.next();
            if( tag.getTagName() != null && tag.getTagName().equals("@generated") )
                return true;
        }
        return false;
    }	
	private Javadoc getTypeJavaDoc(CompilationUnit cu, TypeDeclaration decl) {
		
		class TypeFinder extends ASTVisitor {
			String name;
			TypeDeclaration found = null;
			
			TypeFinder(String searchName,boolean visitDocTags)
			{
				super(visitDocTags);
				name = searchName;
			}
			
			public TypeDeclaration getType()
			{
				return found;
			}

			public boolean visit(TypeDeclaration type) {
				if(found == null)
				{
					if( name.equals( type.getName().getFullyQualifiedName() )){
						found = type;
					}
				}
				return true;
			}

			public boolean visit(MethodDeclaration node) {
				return true;
			}
		}
		
		TypeFinder finder = new TypeFinder(decl.getName().getFullyQualifiedName(),true);
		cu.accept(finder);
		TypeDeclaration declaration = finder.getType();
		return (declaration == null ? null : declaration.getJavadoc());
	}
	private Javadoc getMethodJavaDoc(CompilationUnit cu, MethodDeclaration decl) {
		
		String srcFakeSignature = this.makeSignature(decl);
		class MethodFinder extends ASTVisitor {
			String name;
			MethodDeclaration found = null;
			
			MethodFinder(String searchName,boolean visitDocTags)
			{
				super(visitDocTags);
				name = searchName;
			}
			
			public MethodDeclaration getMethod()
			{
				return found;
			}

			public boolean visit(TypeDeclaration type) {
				
				return true;
			}

			public boolean visit(MethodDeclaration node) {
				if(found == null)
				{
					String signature = makeSignature(node);
					if( name.equals(signature)){
						found = node;
					}
				}				
				return true;
			}
		}
		
		MethodFinder finder = new MethodFinder(srcFakeSignature,true);
		cu.accept(finder);
		MethodDeclaration declaration = finder.getMethod();
		return (declaration == null ? null : declaration.getJavadoc());
	}
	
	/**
	 * @param decl
	 * @return
	 */
	private String makeSignature(MethodDeclaration decl) {
		
		String signature = ""+decl.getReturnType();
		signature += decl.getName().getFullyQualifiedName();
		Iterator params = decl.parameters().iterator();
		while (params.hasNext()) {
			SingleVariableDeclaration var = (SingleVariableDeclaration) params.next();
			signature += "_"+var.getType();
			
		}
		return signature;
	}
	/**
	 * Creates the new type using the entered field values.
	 * 
	 * @param monitor a progress monitor to report progress.
	 */
	public void createType() throws CoreException, InterruptedException {		
		monitor = getMonitor();
		
		monitor.beginTask("Creating a new type", 10);
		
		ICompilationUnit createdWorkingCopy= null;
		try {
			IPackageFragmentRoot root= getPackageFragmentRoot();  // Current source folder
			IPackageFragment pack= root.createPackageFragment(this.packageName,true,monitor);    // Package that contains the class			
			monitor.worked(1);
			
			String clName= this.typeName;
			
			IType createdType;
			ImportsManager imports;
			int indent= 0;
	

			
			String lineDelimiter= null;	
			lineDelimiter= System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
					
			ICompilationUnit parentCU= pack.getCompilationUnit(clName + ".java");
			if(!parentCU.exists())
			  parentCU= pack.createCompilationUnit(clName + ".java", "", false, new SubProgressMonitor(monitor, 2)); //$NON-NLS-1$ //$NON-NLS-2$
			String targetSource = parentCU.getSource();
			
			// create a working copy with a new owner
			createdWorkingCopy= parentCU.getWorkingCopy(null);
			
			// use the compiler template a first time to read the imports
			String content= CodeGeneration.getCompilationUnitContent(createdWorkingCopy, null, "", lineDelimiter); //$NON-NLS-1$
			if (content != null) {
				createdWorkingCopy.getBuffer().setContents(content);
			}
							
			imports= new ImportsManager(createdWorkingCopy);
			// add an import that will be removed again. Having this import solves 14661
			imports.addImport(JavaModelUtil.concatenateName(pack.getElementName(), this.typeName));
			
			String cuContent = content + lineDelimiter + typeComment + lineDelimiter + typeStub;
			createdWorkingCopy.getBuffer().setContents(cuContent);
			createdType= createdWorkingCopy.getType(clName);
			
			// add imports for superclass/interfaces, so types can be resolved correctly
			ICompilationUnit cu= createdType.getCompilationUnit();	
			boolean needsSave= !cu.isWorkingCopy();
			imports.create(needsSave, new SubProgressMonitor(monitor, 1));
	
			JavaModelUtil.reconcile(cu);
		
			createTypeMembers(createdType, imports, new SubProgressMonitor(monitor, 1));
	
			// add imports
			imports.create(needsSave, new SubProgressMonitor(monitor, 1));
			
			if (removeUnused(cu, imports)) {
				imports.create(needsSave, null);
			}
			
			JavaModelUtil.reconcile(cu);
			
			ISourceRange range= createdType.getSourceRange();
			
			IBuffer buf= cu.getBuffer();
			String originalContent= buf.getText(range.getOffset(), range.getLength());
			
			String formattedContent= CodeFormatterUtil.format(CodeFormatter.K_CLASS_BODY_DECLARATIONS, originalContent, indent, null, lineDelimiter, pack.getJavaProject()); 
			buf.replace(range.getOffset(), range.getLength(), formattedContent);
			cu.getBuffer().setContents(buf.getContents());
			String merged = merge(cu.getBuffer().getContents(),targetSource);
			cu.getBuffer().setContents(merged);
			cu.commitWorkingCopy(false, new SubProgressMonitor(monitor, 1));
			 
			fCreatedType = (IType) createdType.getPrimaryElement();

		} finally {
			if (createdWorkingCopy != null) {
				createdWorkingCopy.discardWorkingCopy();
			}
			monitor.done();
		}
	}	
	
	/**
	 * @param createdType
	 * @param imports
	 * @param monitor2
	 */
	private void createTypeMembers(IType createdType, ImportsManager imports, SubProgressMonitor monitor2) throws JavaModelException {
		if( fields != null && fields.length() > 0 )
			createdType.createField(fields,null, false, this.getMonitor());
		if( methodStub != null && methodStub.length() > 0 )
			createdType.createMethod(methodStub, null, false, this.getMonitor());
	}

	/**
	 * @return
	 */
	private IPackageFragmentRoot getPackageFragmentRoot() {
		return packageFragmentRoot;
	}

	private boolean removeUnused(ICompilationUnit cu, ImportsManager imports) {
		ASTParser parser= ASTParser.newParser(AST.JLS2);
		parser.setSource(cu);
		parser.setResolveBindings(true);
		CompilationUnit root= (CompilationUnit) parser.createAST(null);
		IProblem[] problems= root.getProblems();
		boolean importRemoved= false;
		for (int i= 0; i < problems.length; i++) {
			if (problems[i].getID() == IProblem.UnusedImport) {
				String imp= problems[i].getArguments()[0];
				imports.removeImport(imp);
				importRemoved=true;
			}
		}
		return importRemoved;
	}

	/**
	 * @return Returns the monitor.
	 */
	public IProgressMonitor getMonitor() {
		if (monitor == null) {
			monitor= new NullProgressMonitor();
		}
		return monitor;
	}
	/**
	 * @param monitor The monitor to set.
	 */
	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}
	
	public static class ImportsManager {

		private ImportsStructure fImportsStructure;
		private HashSet fAddedTypes;

		/* package */ ImportsManager(ICompilationUnit createdWorkingCopy) throws CoreException {
			IPreferenceStore store= PreferenceConstants.getPreferenceStore();
			String[] prefOrder= JavaPreferencesSettings.getImportOrderPreference(createdWorkingCopy.getPrimaryElement().getJavaProject());
			int threshold= JavaPreferencesSettings.getImportNumberThreshold(createdWorkingCopy.getPrimaryElement().getJavaProject());			
			fAddedTypes= new HashSet();
			
			fImportsStructure= new ImportsStructure(createdWorkingCopy, prefOrder, threshold, true);
		}

		/* package */ ImportsStructure getImportsStructure() {
			return fImportsStructure;
		}
				
		/**
		 * Adds a new import declaration that is sorted in the existing imports.
		 * If an import already exists or the import would conflict with another import
		 * of an other type with the same simple name  the import is not added.
		 * 
		 * @param qualifiedTypeName The fully qualified name of the type to import
		 * (dot separated)
		 * @return Returns the simple type name that can be used in the code or the
		 * fully qualified type name if an import conflict prevented the import
		 */				
		public String addImport(String qualifiedTypeName) {
			fAddedTypes.add(qualifiedTypeName);
			return fImportsStructure.addImport(qualifiedTypeName);
		}
		
		/* package */ void create(boolean needsSave, SubProgressMonitor monitor) throws CoreException {
			fImportsStructure.create(needsSave, monitor);
		}
		
		/* package */ void removeImport(String qualifiedName) {
			if (fAddedTypes.contains(qualifiedName)) {
				fImportsStructure.removeImport(qualifiedName,false);
			}
		}
		
	}	

	/**
	 * @return Returns the fCreatedType.
	 */
	public IType getCreatedType() {
		return fCreatedType;
	}
	/**
	 * @param createdType The fCreatedType to set.
	 */
	public void setCreatedType(IType createdType) {
		fCreatedType = createdType;
	}
	/**
	 * @return Returns the methodStub.
	 */
	public String getMethodStub() {
		return methodStub;
	}
	/**
	 * @param methodStub The methodStub to set.
	 */
	public void setMethodStub(String methodStub) {
		this.methodStub = methodStub;
	}
	/**
	 * @return Returns the packageName.
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * @param packageName The packageName to set.
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/**
	 * @return Returns the typeComment.
	 */
	public String getTypeComment() {
		return typeComment;
	}
	/**
	 * @param typeComment The typeComment to set.
	 */
	public void setTypeComment(String typeComment) {
		this.typeComment = typeComment;
	}
	/**
	 * @return Returns the typeName.
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName The typeName to set.
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return Returns the typeStub.
	 */
	public String getTypeStub() {
		return typeStub;
	}
	/**
	 * @param typeStub The typeStub to set.
	 */
	public void setTypeStub(String typeStub) {
		this.typeStub = typeStub;
	}
	/**
	 * @param packageFragmentRoot The packageFragmentRoot to set.
	 */
	public void setPackageFragmentRoot(IPackageFragmentRoot packageFragmentRoot) {
		this.packageFragmentRoot = packageFragmentRoot;
	}
	/**
	 * @return Returns the fields.
	 */
	public String getFields() {
		return fields;
	}
	/**
	 * @param fields The fields to set.
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}
	/**
	 * @return Returns the configurationElement.
	 */
	public IConfigurationElement getConfigurationElement() {
		return configurationElement;
	}
	/**
	 * @param configurationElement The configurationElement to set.
	 */
	public void setConfigurationElement(
			IConfigurationElement configurationElement) {
		this.configurationElement = configurationElement;
	}
	/**
	 * @return Returns the IEnterpriseBeanDelegate.
	 */
	public IEnterpriseBeanDelegate getEnterpriseBeanDelegate() {
		return enterpriseBeanDelegate;
	}
	/**
	 * @param IEnterpriseBeanDelegate The IEnterpriseBeanDelegate to set.
	 */
	public void setEnterpriseBeanDelegate(IEnterpriseBeanDelegate IEnterpriseBeanDelegate) {
		this.enterpriseBeanDelegate = IEnterpriseBeanDelegate;
	}
}
