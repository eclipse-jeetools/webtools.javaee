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
package org.eclipse.jst.j2ee.internal.java.codegen;



import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.codegen.AnalysisResult;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.codegen.Navigator;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * A Java compilation unit generator is used to create or update a Java compilation unit. If the
 * generator is updating an existing unit, it can instantiate a merge stategy and have it used by
 * all it's children for merge processing. This generator is usually the child of a compilation unit
 * group generator.
 * 
 * <p>
 * Subclasses must implement these methods:
 * <ul>
 * <li>getName - returns the simple name of the compilation unit
 * <li>getPackageName - returns the package name for the compilation unit
 * </ul>
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>initialize - create and initialize child generators
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>terminate - to null object references and release resources
 * </ul>
 * <p>
 * Subclasses may occasionally override these methods:
 * <ul>
 * <li>createMergeStrategy - to create a specialized merge strategy for the unit
 * <li>createHistoryDescriptor - to take specialized properties into account when looking for the
 * old member
 * <li>getSource - to create a simple unit without child generators
 * </ul>
 */
public abstract class JavaCompilationUnitGenerator extends JavaElementGenerator {
	private ICompilationUnit fCU = null;
	private IJavaMergeStrategy fMergeStrategy = null;
	private JavaCompilationUnitHistoryDescriptor fHistoryDescriptor = null;
	private MergeResults fMergeResults = null;
	private List fAnalysisImports = null;
	private static final String JAVA_PACKAGE_FRAGMENT = "::JavaPackageFragment";//$NON-NLS-1$
	private static final String PACKAGE_STATEMENT_TEMPLATE = "package %0;" + IJavaGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	private static final String MOVE_OR_RENAME_COLLISION_WARNING = JavaCodeGenResourceHandler.getString("If_this_generation_continu_WARN_"); //$NON-NLS-1$ = "If this generation continues, the Java source file \"{0}\" will be overwritten."
	private static final String REQUIRES_JAVA_TOP_LEVEL_HELPER_ERROR = JavaCodeGenResourceHandler.getString("Java_generation_requires_a_EXC_"); //$NON-NLS-1$ = "Java generation requires a JavaTopLevelGenerationHelper."
	//this reason code is intended to be used in the AnalysisResult
	//to indicate that the type has changed.
	public static final int COMPILATION_UNIT_CHANGED_REASON_CODE = AnalysisResult.MIN_FW_REASON_CODE + 1;

	/**
	 * JavaTypeGenerator default constructor.
	 */
	public JavaCompilationUnitGenerator() {
		super();
	}

	/**
	 * Adds the import to the compilation unit if it is not already present. If this call is made
	 * during analyze, the add is saved to be added during run.
	 */
	public void addImport(String anImport) throws GenerationException {
		if (primGetCompilationUnit() == null) {
			getAnalysisImports().add(new AnalysisImport(true, anImport, this));
		} else {
			if (!getCompilationUnit().getImport(anImport).exists()) {
				prepare();
				try {
					getCompilationUnit().createImport(anImport, null, null);
				} catch (JavaModelException exc) {
					throw new GenerationException(exc);
				}
			}
		}
	}

	/**
	 * Analyze this Java compilation unit and merges where needed. Uses the
	 * {@link JavaCompilationUnitGenerator#createCompilationUnitDescriptor()},
	 * {@link JavaCompilationUnitGenerator#createHistoryDescriptor(JavaCompilationUnitDescriptor)},
	 * and
	 * {@link JavaCompilationUnitGenerator#calculateMergeResults(JavaCompilationUnitHistoryDescriptor, JavaCompilationUnitDescriptor)}
	 * methods.
	 * 
	 * @see IBaseGenerator
	 */
	public AnalysisResult analyze() throws GenerationException {
		// Get a description of the compilation unit.
		JavaCompilationUnitDescriptor desc = createCompilationUnitDescriptor();

		// Check for an existing CU that matches the one we want.
		setHistoryDescriptor(createHistoryDescriptor(desc));
		fMergeResults = calculateMergeResults(getHistoryDescriptor(), desc);

		AnalysisResult result = fMergeResults.getAnalysisResult();
		if (result == null && fMergeResults.isChangeNeeded()) {
			result = new AnalysisResult();
			result.setStatus(AnalysisResult.INFORMATIONAL);
			result.setReasonCode(JavaCompilationUnitGenerator.COMPILATION_UNIT_CHANGED_REASON_CODE);
		}
		setAnalysisResult(result);

		return super.analyze();
	}

	/**
	 * Compares the compilation unit history and the new intentions to arrive at the needed actions
	 * described by the merge results. In this case, if the merged flag on the results is true, it
	 * means the name of the compilation unit is changing and the Java model operations to move
	 * and/or rename the unit need to be used in run().
	 */
	protected MergeResults calculateMergeResults(JavaCompilationUnitHistoryDescriptor cuHistory, JavaCompilationUnitDescriptor desc) throws GenerationException {
		MergeResults mr = new MergeResults();
		if (cuHistory.isDeleteOnly()) {
			mr.setGenerate(cuHistory.getOldCompilationUnit() != null);
			mr.setDeleteOnly(true);
		} else {
			if (cuHistory.getOldCompilationUnit() == null) {
				IGenerationBuffer sourceBuf = getGenerationBuffer();
				getSource(sourceBuf, desc);
				mr.setSource(sourceBuf.toString());
			} else {
				boolean sameName = cuHistory.getQualifiedName().equals(desc.getQualifiedName());
				if (sameName)
					mr.setGenerate(false);
				else {
					// An interesting case. The history indicates the CU exists, but we
					// want to move and/or rename it.
					mr.setMerged(true);
					// Need to check for a CU name collision.
					IPackageFragment pkgToCheck = findPackageFragment(desc.getPackageName());
					if (pkgToCheck != null) {
						ICompilationUnit collisionCU = pkgToCheck.getCompilationUnit(desc.getName() + IJavaGenConstants.JAVA_FILE_EXTENSION);
						if (collisionCU.exists()) {
							AnalysisResult ar = new AnalysisResult();
							ar.setStatus(AnalysisResult.WARNING);
							ar.setFrom(cuHistory.getQualifiedName());
							ar.setTo(desc.getQualifiedName());
							ar.setReason(MOVE_OR_RENAME_COLLISION_WARNING);
							ar.setReasonCode(IJavaGenConstants.CU_MOVE_COPY_COLLISION_WARNING_CODE);
							mr.setAnalysisResult(ar);
						}
					}
				}
			}
		}

		return mr;
	}

	/**
	 * Gathers the information describing the target to be generated and returns the descriptor.
	 * 
	 * @see JavaCompilationUnitDescriptor
	 */
	protected JavaCompilationUnitDescriptor createCompilationUnitDescriptor() {
		JavaCompilationUnitDescriptor result = new JavaCompilationUnitDescriptor();
		result.setName(getName());
		result.setPackageName(getPackageName());
		return result;
	}

	/**
	 * Creates the history descriptor for the target. Puts the old compilation unit in the history
	 * if it exists.
	 * 
	 * @see JavaCompilationUnitHistoryDescriptor
	 */
	protected final JavaCompilationUnitHistoryDescriptor createDefaultHistoryDescriptor(JavaCompilationUnitDescriptor desc) throws GenerationException {
		JavaCompilationUnitHistoryDescriptor historyDesc = new JavaCompilationUnitHistoryDescriptor();
		historyDesc.setName(desc.getName());
		historyDesc.setPackageName(desc.getPackageName());
		historyDesc.setDeleteOnly(getTopLevelHelper().isDeleteAll());
		getMatchingCompilationUnit(historyDesc);
		return historyDesc;
	}

	/**
	 * Creates the history descriptor for the target if it does not exist. Puts the old compilation
	 * unit in the history if it exists.
	 * 
	 * @see JavaCompilationUnitHistoryDescriptor
	 */
	protected JavaCompilationUnitHistoryDescriptor createHistoryDescriptor(JavaCompilationUnitDescriptor desc) throws GenerationException {
		JavaCompilationUnitHistoryDescriptor historyDesc = getHistoryDescriptor();
		if (historyDesc == null)
			historyDesc = createDefaultHistoryDescriptor(desc);
		else
			getMatchingCompilationUnit(historyDesc);
		return historyDesc;
	}

	/**
	 * Subclasses can override to create a proper merge strategy. The default is to get it from the
	 * top level helper.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.IJavaMergeStrategy
	 */
	protected IJavaMergeStrategy createMergeStrategy() throws GenerationException {
		IJavaMergeStrategy strategy = null;
		try {
			JavaTopLevelGenerationHelper topHelper = (JavaTopLevelGenerationHelper) getTopLevelHelper();
			strategy = topHelper.createMergeStrategy();
		} catch (ClassCastException exc) {
			throw new GenerationException(REQUIRES_JAVA_TOP_LEVEL_HELPER_ERROR, exc);
		}
		return strategy;
	}

	/**
	 * Deletes the compilation unit via the working copy provider.
	 * 
	 * @param aCU
	 *            the compilation unit to delete
	 * @param monitor
	 *            the progress monitor for the generation
	 */
	protected void delete(ICompilationUnit aCU, IProgressMonitor monitor) throws JavaModelException {
		getWorkingCopyProvider().delete(aCU, monitor);
	}

	/**
	 * Runs the pending imports from the analyze phase.
	 */
	private void doAnalysisImports() throws GenerationException {
		if (fAnalysisImports != null) {
			Iterator importIter = fAnalysisImports.iterator();
			while (importIter.hasNext())
				((AnalysisImport) importIter.next()).execute();
		}
	}

	/**
	 * For the given package name, the first package fragment in the project by that name is
	 * returned. Returns null if it does not exist.
	 * 
	 * @return org.eclipse.jdt.core.api.IPackageFragment
	 */
	private IPackageFragment findPackageFragment(String packageName) throws GenerationException {
		// Search the project's packages for a source package fragment
		// by this name.
		return findPackageFragment(packageName, getJavaProject());
	}

	/**
	 * For the given package name, the first package fragment in the project by that name is
	 * returned. Returns null if it does not exist.
	 * 
	 * @return org.eclipse.jdt.core.api.IPackageFragment
	 */
	static public IPackageFragment findPackageFragment(String packageName, IJavaProject jProject) throws GenerationException {
		if (jProject == null)
			return null;

		IPackageFragment pkgFrag = null;
		IPackageFragment[] pkgFrags = null;
		try {
			pkgFrags = jProject.getPackageFragments();
			for (int i = 0; i < (pkgFrags.length) && (pkgFrag == null); i++) {
				if ((pkgFrags[i].getKind() == IPackageFragmentRoot.K_SOURCE) && (pkgFrags[i].getElementName().equals(packageName)))
					pkgFrag = pkgFrags[i];
			}
		} catch (JavaModelException e) {
			throw new GenerationException(e);
		};
		return pkgFrag;
	}

	/**
	 * Returns the list holding the import adds and removes from the analyze phase.
	 * 
	 * @return java.util.List
	 */
	protected java.util.List getAnalysisImports() {
		if (fAnalysisImports == null)
			fAnalysisImports = new java.util.ArrayList();
		return fAnalysisImports;
	}

	/**
	 * Returns the compilation unit for this generator. Will return null during analyze for new
	 * units. If {@link JavaCompilationUnitGenerator#isPrepared()}returns true, this method will
	 * return a working copy.
	 */
	public ICompilationUnit getCompilationUnit() {
		ICompilationUnit result = fCU;
		if ((result == null) && getHistoryDescriptor() != null)
			result = getHistoryDescriptor().getOldCompilationUnit();
		return result;
	}

	/**
	 * Asks the working copy provider for the working copy already open for this unit. Does not open
	 * a new one.
	 */
	protected ICompilationUnit getExistingWorkingCopy(ICompilationUnit cu) throws GenerationException {
		try {
			return getWorkingCopyProvider().getExistingWorkingCopy(cu);
		} catch (CoreException exc) {
			throw new GenerationException(exc);
		}
	}

	/**
	 * Returns the target's history descriptor.
	 */
	public JavaCompilationUnitHistoryDescriptor getHistoryDescriptor() {
		return fHistoryDescriptor;
	}

	/**
	 * Sets the old compilation unit in the history if the compilation unit exists. If the working
	 * copy provider has a working copy already open, the working copy is used as the old
	 * compilation unit.
	 */
	protected void getMatchingCompilationUnit(JavaCompilationUnitHistoryDescriptor cuHistory) throws GenerationException {
		ICompilationUnit cu = primGetMatchingCompilationUnit(cuHistory);
		ICompilationUnit wcu = null;
		if (cu != null)
			wcu = getExistingWorkingCopy(cu);
		if (wcu == null)
			cuHistory.setOldCompilationUnit(cu);
		else
			cuHistory.setOldCompilationUnit(wcu);
	}

	/**
	 * Returns the merge strategy associated with this compilation unit generator. Calls
	 * {@link JavaCompilationUnitGenerator#createMergeStrategy()}if the merge strategy has not been
	 * set.
	 */
	public IJavaMergeStrategy getMergeStrategy() throws GenerationException {
		if (fMergeStrategy == null)
			fMergeStrategy = createMergeStrategy();
		return fMergeStrategy;
	}

	/**
	 * Subclasses must implement to return the simple name of the type being generated.
	 * 
	 * @return java.lang.String
	 */
	protected abstract String getName();

	/**
	 * For the given package name, the first package fragment in the project by that name is
	 * returned. It is created if it does not exist.
	 * 
	 * @return org.eclipse.jdt.core.api.IPackageFragment
	 */
	private IPackageFragment getPackageFragment(String packageName) throws GenerationException {
		Navigator navigator = getTargetContext().getNavigator();
		String cacheKey = packageName + "." + getName() + JAVA_PACKAGE_FRAGMENT; //$NON-NLS-1$
		IPackageFragment pkgFrag = (IPackageFragment) navigator.getCookie(cacheKey);

		if (pkgFrag == null) {
			pkgFrag = primGetPackageFragment(packageName);
			navigator.setCookie(cacheKey, pkgFrag);
		}
		return pkgFrag;
	}

	/**
	 * Subclasses must implement to return the package name.
	 * 
	 * @return java.lang.String
	 */
	protected abstract String getPackageName();

	/**
	 * Puts the compilation unit package statement in the source buffer.
	 */
	protected void getPackageStatement(IGenerationBuffer sourceBuf, JavaCompilationUnitDescriptor desc) {
		String pkgName = desc.getPackageName();
		if ((pkgName != null) && (pkgName.length() != 0)) {
			sourceBuf.format(PACKAGE_STATEMENT_TEMPLATE, new String[]{pkgName});
		}
	}

	/**
	 * This implementation returns null for no source. Subclasses can override this to provide an
	 * initial set of members for a new compilation unit. Usually this is done via child generators,
	 * but this provides a simple method using just a string.
	 */
	protected String getSource() throws GenerationException {
		return null;
	}

	/**
	 * This implementation adds the package statment to the buffer. It then checks for a "String
	 * getSource()" override. If there was an override, its result is put in the buffer. If there
	 * was not, the dependent children are run to create the source.
	 */
	protected void getSource(IGenerationBuffer sourceBuf, JavaCompilationUnitDescriptor desc) throws GenerationException {
		getPackageStatement(sourceBuf, desc);
		String source = getSource();
		if (source == null)
			runDependents(sourceBuf);
		else
			sourceBuf.append(source);
	}

	/**
	 * Asks the working copy provider for the working copy already open for this unit or it gets a
	 * new working copy from the provider.
	 */
	protected ICompilationUnit getWorkingCopy(boolean forNewCU) throws GenerationException {
		try {
			return getWorkingCopyProvider().getWorkingCopy(getCompilationUnit(), forNewCU);
		} catch (CoreException exc) {
			throw new GenerationException(exc);
		}
	}

	/**
	 * For a compilation unit generator, being prepared means we have obtained a working copy from
	 * the working copy provider.
	 * 
	 * @see JavaElementGenerator
	 */
	public boolean isPrepared() {
		return ((fCU != null) && (fCU.isWorkingCopy()));
	}

	/**
	 * For a compilation unit generator, being prepared means we have obtained a working copy from
	 * the working copy provider.
	 * 
	 * @exception GenerationException
	 *                Thrown if this is called outside the scope of the run method.
	 * @see JavaElementGenerator
	 */
	public void prepare() throws GenerationException {
		if (!isPrepared()) {
			if (fCU == null)
				throw new GenerationException(JavaCodeGenResourceHandler.getString("The_prepare_method_can_o_EXC_")); //$NON-NLS-1$ = "The prepare() method can only be called during run()."

			// If the get did not create it new, get a working copy.
			if (!fCU.isWorkingCopy())
				fCU = getWorkingCopy(false);
		}
	}

	/**
	 * Returns the raw compilation unit property without trying to resolve it.
	 */
	ICompilationUnit primGetCompilationUnit() {
		return fCU;
	}

	/**
	 * Gets the old compilation unit from the Java Model.
	 */
	protected ICompilationUnit primGetMatchingCompilationUnit(JavaCompilationUnitHistoryDescriptor cuHistory) throws GenerationException {
		String packageName = cuHistory.getPackageName();
		String typeName = cuHistory.getName();
		ICompilationUnit cu = getCompilationUnit(packageName, typeName);
		if (cu != null && !cu.exists())
			cu = null;
		return cu;
	}

	/**
	 * @param packageName
	 * @param aName
	 * @return
	 */
	private ICompilationUnit getCompilationUnit(String packageName, String aName) throws GenerationException {
		if (packageName == null || aName == null)
			return null;
		Navigator navigator = getTargetContext().getNavigator();
		String cacheKey = packageName + "." + aName; //$NON-NLS-1$
		ICompilationUnit cu = (ICompilationUnit) navigator.getCookie(cacheKey);
		if (cu == null) {
			try {
				cu = findCompilationUnit(packageName, aName);
			} catch (JavaModelException e) {
				throw new GenerationException(e);
			}
			if (cu != null)
				navigator.setCookie(cacheKey, cu);
		}
		return cu;
	}

	/**
	 * @param packageName
	 * @param aName
	 * @return
	 */
	private ICompilationUnit findCompilationUnit(String packageName, String aName) throws GenerationException, JavaModelException {
		JavaTopLevelGenerationHelper helper;
		try {
			helper = (JavaTopLevelGenerationHelper) getTopLevelHelper();
		} catch (ClassCastException exc) {
			throw new GenerationException(REQUIRES_JAVA_TOP_LEVEL_HELPER_ERROR, exc);
		}
		IJavaProject javaProject = helper.getJavaProject();
		IType type = javaProject.findType(packageName, aName);
		if (type != null)
			return type.getCompilationUnit();
		return null;
	}

	/**
	 * For the given package name, the first package fragment in the project by that name is
	 * returned. It is created if it does not exist.
	 * 
	 * @return org.eclipse.jdt.core.api.IPackageFragment
	 */
	private IPackageFragment primGetPackageFragment(String packageName) throws GenerationException {
		IPackageFragment pkgFrag = null;
		try {
			IPackageFragmentRoot pkgFragRoot = getDefaultPackageFragmentRoot();
			if (pkgFragRoot != null) {
				pkgFrag = pkgFragRoot.getPackageFragment(packageName);
				if (!pkgFrag.exists()) {
					ensureFoldersNotReadOnly(pkgFrag);
					IProgressMonitor pm = getTargetContext().getProgressMonitor();
					pkgFrag = pkgFragRoot.createPackageFragment(packageName, false, pm);
				}
			}
			if (pkgFrag == null) {
				pkgFrag = findPackageFragment(packageName);
			} else {
				IResource res = pkgFrag.getResource();
				if (res != null && res.isReadOnly())
					res.setReadOnly(false);
			}
		} catch (JavaModelException exc) {
			throw new GenerationException(exc);
		}
		return pkgFrag;
	}

	/**
	 * Return the default IPackageFragmentRoot from the top level generation helper. Subclasses may
	 * override for specialization. If a subclass wants the first available PackageFragmenetRoot,
	 * use the getFirstAvailablePackageFragmentRoot method.
	 * 
	 * @return IPackageFragmentRoot
	 * @throws GenerationException
	 * @link getJavaProject()
	 * @link getFirstAvailablePackageFragmentRoot()
	 */
	protected IPackageFragmentRoot getDefaultPackageFragmentRoot() throws GenerationException {
		try {
			return ((JavaTopLevelGenerationHelper) getTopLevelHelper()).getDefaultPackageFragmentRoot();
		} catch (ClassCastException exc) {
			throw new GenerationException(REQUIRES_JAVA_TOP_LEVEL_HELPER_ERROR, exc);
		}
	}

	protected IPackageFragmentRoot getFirstAvailablePackageFragmentRoot(IJavaProject javaProject) throws GenerationException {
		if (javaProject != null) {
			try {
				IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
				IPackageFragmentRoot root;
				for (int i = 0; i < roots.length; i++) {
					root = roots[i];
					if (root.getKind() == IPackageFragmentRoot.K_SOURCE)
						return root;
				}
			} catch (JavaModelException e) {
				throw new GenerationException(e);
			}
		}
		return null;
	}

	/**
	 * Return the IJavaProject from the top level generation helper. Subclasses may override for
	 * specialization. If getDefaultPackageFragmentRoot() returns null, this method will be used to
	 * find the JavaProject for which to generate the CompilationUnit. The first source folder will
	 * be used.
	 * 
	 * @return IJavaProject
	 * @throws GenerationException
	 * @link getDefaultPackageFragmentRoot()
	 */
	protected IJavaProject getJavaProject() throws GenerationException {
		try {
			return ((JavaTopLevelGenerationHelper) getTopLevelHelper()).getJavaProject();
		} catch (ClassCastException exc) {
			throw new GenerationException(REQUIRES_JAVA_TOP_LEVEL_HELPER_ERROR, exc);
		}
	}

	/**
	 * Method ensureFoldersNotReadOnly.
	 * 
	 * @param pkgFragRoot
	 * @param packageName
	 */
	private void ensureFoldersNotReadOnly(IPackageFragment pkgFrag) throws JavaModelException {
		IResource res = pkgFrag.getResource();
		ProjectUtilities.ensureContainerNotReadOnly(res);
	}

	/**
	 * Removes the import from the compilation unit if it is present. If this call is made during
	 * analyze, the remove is saved to be removed during run.
	 */
	public void removeImport(String anImport) throws GenerationException {
		if (primGetCompilationUnit() == null) {
			getAnalysisImports().add(new AnalysisImport(false, anImport, this));
		} else {
			if (getCompilationUnit().getImport(anImport).exists()) {
				prepare();
				try {
					getCompilationUnit().getImport(anImport).delete(false, null);
				} catch (JavaModelException exc) {
					throw new GenerationException(exc);
				}
			}
		}
	}

	/**
	 * Create or delete compilation unit if needed. If not delete only, execute the import add and
	 * removes that were saved during analyze and run the children.
	 * 
	 * @see IBaseGenerator
	 */
	public void run() throws GenerationException {
		// If generating (includes delete only)...
		if (fMergeResults.isGenerate()) {
			IProgressMonitor pm = getTargetContext().getProgressMonitor();
			try {
				// If is delete only, do that...
				if (fMergeResults.isDeleteOnly()) {
					ICompilationUnit cu = getHistoryDescriptor().getOldCompilationUnit();
					delete(cu, pm);
				} else {
					// The isMerged flag is only set if analyze() detected that
					// there is an old unit that needs to be moved and/or renamed.
					if (fMergeResults.isMerged()) {
						JavaCompilationUnitDescriptor desc = createCompilationUnitDescriptor();
						JavaCompilationUnitHistoryDescriptor hist = getHistoryDescriptor();
						ICompilationUnit oldUnit = hist.getOldCompilationUnit();
						boolean rename = desc.getName().equals(hist.getName());
						boolean move = desc.getPackageName().equals(hist.getPackageName());
						boolean copy = getMergeStrategy().isDefaultPreserveNonCollisionOldMembers();
						if (copy) {
							oldUnit.copy(getPackageFragment(desc.getPackageName()), null, (rename ? desc.getName() : null), true, pm);

						} else if (move) {
							oldUnit.move(getPackageFragment(desc.getPackageName()), null, (rename ? desc.getName() : null), true, pm);
						} else {
							oldUnit.rename(desc.getName(), true, pm);
						}
						fCU = createDefaultHistoryDescriptor(desc).getOldCompilationUnit();
					} else {
						// Need to create a new compilation unit.
						String pkgName = null;
						IPackageFragment packageFragment = null;
						String typeFileName = null;
						pkgName = getPackageName();
						try {
							if (pkgName != null)
								pkgName = pkgName.trim();
							if ((pkgName == null) || (pkgName.length() == 0))
								pkgName = IJavaGenConstants.EMPTY_STRING;
							packageFragment = getPackageFragment(pkgName);
							typeFileName = getName() + IJavaGenConstants.JAVA_FILE_EXTENSION;
							fCU = packageFragment.createCompilationUnit(typeFileName, fMergeResults.getSource(), false, pm);
							fCU = getWorkingCopy(true);
						} catch (JavaModelException jme) {
							IStatus status = jme.getStatus();
							if (status == null || !(status.getException() instanceof FileNotFoundException))
								throw jme;
							IResource res = packageFragment.getUnderlyingResource();
							if (res != null) {
								IPath path = res.getLocation();
								path = path.append(typeFileName);
								if (path.toOSString().length() > 259)
									throw new FileNameTooLongGenerationException(getExceptionIndicator(jme), jme);
							}
							throw jme;
						}
					}
				}
			} catch (JavaModelException exc) {
				fCU = null;
				throw new GenerationException(getExceptionIndicator(exc), exc);
			}
		}

		// If not deleted, make sure the cu is set and
		// do pending imports and run the children.
		if (!fMergeResults.isDeleteOnly()) {
			if (fCU == null) {
				fCU = getHistoryDescriptor().getOldCompilationUnit();
				// If the old unit is a working copy, we need to reaccess it
				// in order to have the correct reference count for run().
				if ((fCU != null) && (fCU.isWorkingCopy())) {
					fCU = primGetMatchingCompilationUnit(getHistoryDescriptor());
					if (fCU != null)
						fCU = getWorkingCopy(false);
				}
			}
			doAnalysisImports();
			super.run();
		}
	}

	private String getExceptionIndicator(JavaModelException e) {
		StringBuffer b = new StringBuffer();
		b.append(getName());
		b.append("#"); //$NON-NLS$ //$NON-NLS-1$
		b.append(getName());
		b.append(" :: "); //$NON-NLS$ //$NON-NLS-1$
		b.append(e.getMessage());
		return b.toString();
	}

	/**
	 * Sets the history descriptor. This method is sometimes used from the initialize method in
	 * order to set a particular history before the analyze phase sets one.
	 */
	public void setHistoryDescriptor(JavaCompilationUnitHistoryDescriptor newHistoryDescriptor) {
		fHistoryDescriptor = newHistoryDescriptor;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void terminate() throws GenerationException {
		super.terminate();
		fMergeStrategy = null;
		fMergeResults = null;
		fHistoryDescriptor = null;
		fCU = null;
		fAnalysisImports = null;
	}

	protected void analyzeChildren(AnalysisResult result) throws GenerationException {
		super.analyzeChildren(result);
		if (hasTypeChangedCode(result)) {
			ICompilationUnit oldCU = getHistoryDescriptor().getOldCompilationUnit();
			if (oldCU != null) {
				ICompilationUnit cu = oldCU;
				if (cu.isWorkingCopy())
					cu = cu.getPrimary();
				IResource res = null;
				try {
					if (cu != null)
						res = cu.getUnderlyingResource();
				} catch (JavaModelException e) {
				}
				if (res != null && res.getType() == IResource.FILE)
					result.setReason(res);
			}
		}
	}

	private boolean hasTypeChangedCode(AnalysisResult result) {
		if (result.getReasonCode() == COMPILATION_UNIT_CHANGED_REASON_CODE)
			return true;
		List list = result.getChildResults();
		int size = list.size();
		AnalysisResult childResult = null;
		for (int i = 0; i < size; i++) {
			childResult = (AnalysisResult) list.get(i);
			if (hasTypeChangedCode(childResult)) {
				result.setReasonCode(COMPILATION_UNIT_CHANGED_REASON_CODE); //ensure that is it set
				// on the parent
				return true;
			}
		}
		return false;
	}
}