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
package org.eclipse.jst.j2ee.internal.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.FileSystemElement;
import org.eclipse.ui.dialogs.WizardResourceImportPage;
import org.eclipse.ui.internal.dialogs.DialogUtil;
import org.eclipse.ui.internal.ide.dialogs.IElementFilter;
import org.eclipse.ui.internal.ide.dialogs.ResourceTreeAndListGroup;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.model.WorkbenchViewerSorter;
import org.eclipse.ui.wizards.datatransfer.FileSystemStructureProvider;
import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;


/**
 * Page 1 of the base resource import-from-file-system Wizard
 */
/* package */
class WizardClassesImportPage1 extends WizardResourceImportPage implements Listener {

	// widgets
	protected Combo sourceNameField;
	protected Button overwriteExistingResourcesCheckbox;
	protected Button createContainerStructureButton;
	protected Button createOnlySelectedButton;
	protected Button sourceBrowseButton;
	//protected Button selectTypesButton;
	protected Button selectAllButton;
	protected Button deselectAllButton;
	//A boolean to indicate if the user has typed anything
	private boolean entryChanged = false;

	// dialog store id constants
	private final static String STORE_SOURCE_NAMES_ID = "WizardFileSystemResourceImportPage1.STORE_SOURCE_NAMES_ID"; //$NON-NLS-1$
	//$NON-NLS-1$
	//private final static String STORE_OVERWRITE_EXISTING_RESOURCES_ID =
	// "WizardFileSystemResourceImportPage1.STORE_OVERWRITE_EXISTING_RESOURCES_ID"; //$NON-NLS-1$
	//$NON-NLS-1$
	//private final static String STORE_CREATE_CONTAINER_STRUCTURE_ID =
	// "WizardFileSystemResourceImportPage1.STORE_CREATE_CONTAINER_STRUCTURE_ID"; //$NON-NLS-1$
	//$NON-NLS-1$

	//private static final String SELECT_TYPES_TITLE = "DataTransfer.selectTypes"; //$NON-NLS-1$
	private static final String SELECT_ALL_TITLE = J2EEUIMessages.getResourceString("DataTransfer.selectAll"); //$NON-NLS-1$
	private static final String DESELECT_ALL_TITLE = J2EEUIMessages.getResourceString("DataTransfer.deselectAll"); //$NON-NLS-1$
	private static final String SELECT_SOURCE_MESSAGE = J2EEUIMessages.getResourceString("FileImport.selectSource"); //$NON-NLS-1$
	protected static final String SOURCE_EMPTY_MESSAGE = J2EEUIMessages.getResourceString("FileImport.sourceEmpty"); //$NON-NLS-1$

	private IPath importedClassesPath;
	//protected Button importFromDir;
	//protected Button importFromZip;


	private ZipFileStructureProvider providerCache;
	ZipFileStructureProvider currentProvider;

	private static final String FILE_IMPORT_MASK = "*.jar;*.zip"; //$NON-NLS-1$

	private List dragAndDropFileNames = null;

	boolean createFullStructure = false;
	private String packageBaseDirName = null;

	//private MinimizedFileSystemElement test = null;

	//private Composite dummyParent = null;

	//private final static int SIZING_SELECTION_WIDGET_WIDTH = 400;
	//private final static int SIZING_SELECTION_WIDGET_HEIGHT = 150;

	private String packageDirStruc = null;

	/**
	 * Creates an instance of this class
	 */
	protected WizardClassesImportPage1(String name, IWorkbench aWorkbench, IStructuredSelection selection) {
		super(name, selection);
	}

	/**
	 * Creates an instance of this class
	 * 
	 * @param aWorkbench
	 *            IWorkbench
	 * @param selection
	 *            IStructuredSelection
	 */
	public WizardClassesImportPage1(IWorkbench aWorkbench, IStructuredSelection selection, IPath importedClassesPath, List fileNames) {
		this("fileSystemImportPage1", aWorkbench, selection); //$NON-NLS-1$
		setTitle(J2EEUIMessages.getResourceString("DataTransfer.fileSystemTitle")); //$NON-NLS-1$
		setDescription(J2EEUIMessages.getResourceString("FileImport.importFileSystem")); //$NON-NLS-1$
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor("import_class_file_wiz_ban")); //$NON-NLS-1$
		this.importedClassesPath = importedClassesPath;
		if (fileNames != null && fileNames.size() != 0) {
			dragAndDropFileNames = fileNames;
		}
	}

	public void blankPage() {
		if (this.selectionGroup != null)
			this.selectionGroup.setRoot(null);
		if (sourceNameField != null)
			sourceNameField.setText(""); //$NON-NLS-1$
	}

	protected void createFileSelectionGroup(Composite parent) {

		//Just create with a dummy root.
		this.selectionGroup = new ResourceTreeAndListGroup(parent, new FileSystemElement("Dummy", null, true), //$NON-NLS-1$
					getFolderProvider(), new WorkbenchLabelProvider(), getFileProvider(),
					//new WorkbenchLabelProviderForClassImport(),
					new WorkbenchLabelProvider(), SWT.NONE, DialogUtil.inRegularFontMode(parent));

		ICheckStateListener listener = new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				updateWidgetEnablements();
			}
		};

		WorkbenchViewerSorter sorter = new WorkbenchViewerSorter();
		this.selectionGroup.setTreeSorter(sorter);
		this.selectionGroup.setListSorter(sorter);
		this.selectionGroup.addCheckStateListener(listener);
	}

	/**
	 * Creates a new button with the given id.
	 * <p>
	 * The <code>Dialog</code> implementation of this framework method creates a standard push
	 * button, registers for selection events including button presses and registers default buttons
	 * with its shell. The button id is stored as the buttons client data. Note that the parent's
	 * layout is assumed to be a GridLayout and the number of columns in this layout is incremented.
	 * Subclasses may override.
	 * </p>
	 * 
	 * @param parent
	 *            the parent composite
	 * @param id
	 *            the id of the button (see <code>IDialogConstants.*_ID</code> constants for
	 *            standard dialog button ids)
	 * @param label
	 *            the label from the button
	 * @param defaultButton
	 *            <code>true</code> if the button is to be the default button, and
	 *            <code>false</code> otherwise
	 */
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;

		Button button = new Button(parent, SWT.PUSH);

		GridData buttonData = new GridData(GridData.FILL_HORIZONTAL);
		button.setLayoutData(buttonData);

		button.setData(new Integer(id));
		button.setText(label);

		if (defaultButton) {
			Shell shell = parent.getShell();
			if (shell != null) {
				shell.setDefaultButton(button);
			}
			button.setFocus();
		}
		return button;
	}

	/**
	 * Creates the buttons for selecting specific types or selecting all or none of the elements.
	 * 
	 * @param parent
	 *            the parent control
	 */
	protected final void createButtonsGroup(Composite parent) {
		// top level group
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;

		layout.makeColumnsEqualWidth = true;
		buttonComposite.setLayout(layout);
		buttonComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));

		// types edit button
		//		selectTypesButton = createButton(buttonComposite, IDialogConstants.SELECT_TYPES_ID,
		// SELECT_TYPES_TITLE, false);

		SelectionListener listener = new SelectionAdapter() {
			//			public void widgetSelected(SelectionEvent e) {
			//				handleTypesEditButtonPressed();
			//			}
		};
		//		selectTypesButton.addSelectionListener(listener);

		selectAllButton = createButton(buttonComposite, IDialogConstants.SELECT_ALL_ID, SELECT_ALL_TITLE, false);

		listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setAllSelections(true);
			}
		};
		selectAllButton.addSelectionListener(listener);

		deselectAllButton = createButton(buttonComposite, IDialogConstants.DESELECT_ALL_ID, DESELECT_ALL_TITLE, false);

		listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setAllSelections(false);
			}
		};
		deselectAllButton.addSelectionListener(listener);

	}

	/**
	 * (non-Javadoc) Method declared on IDialogPage.
	 */
	public void createControl(Composite parent) {
		//super.createControl(parent);
		initializeDialogUnits(parent);

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		composite.setFont(parent.getFont());

		//dummyParent = composite;

		createSourceGroup(composite);

		//createSpacer(composite);

		//createPlainLabel(composite,
		// WorkbenchMessages.getString("WizardImportPage.destinationLabel")); //$NON-NLS-1$
		//createDestinationGroup(composite);

		createOptionsGroup(composite);

		restoreWidgetValues();
		updateWidgetEnablements();
		setPageComplete(determinePageCompletion());

		setControl(composite);

		validateSourceGroup();
		//WorkbenchHelp.setHelp(getControl(),
		// IDataTransferHelpContextIds.FILE_SYSTEM_IMPORT_WIZARD_PAGE);
	}

	protected void createOptionsGroup(Composite parent) {
		Composite optionsGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		optionsGroup.setLayout(layout);
		optionsGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		createOptionsGroupButtons(optionsGroup);

	}

	/**
	 * Create the import options specification widgets.
	 */
	protected void createOptionsGroupButtons(Composite optionsGroup) {

		// overwrite... checkbox
		overwriteExistingResourcesCheckbox = new Button(optionsGroup, SWT.CHECK);
		overwriteExistingResourcesCheckbox.setText(J2EEUIMessages.getResourceString("FileImport.overwriteExisting")); //$NON-NLS-1$
	}

	protected boolean isSetImportFromDir() {
		ClassesImportWizard ciw = (ClassesImportWizard) getWizard();
		return ciw.mainPage.isSetImportFromDir();
	}

	public String getClassFileDirectory(String s) {
		int index = s.lastIndexOf(File.separatorChar);
		return s.substring(0, index + 1);
	}

	/**
	 * Create the group for creating the root directory
	 */
	protected void createRootDirectoryGroup(Composite parent) {
		Composite sourceContainerGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		sourceContainerGroup.setLayout(layout);
		sourceContainerGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		new Label(sourceContainerGroup, SWT.NONE).setText(getSourceLabel());

		// source name entry field
		sourceNameField = new Combo(sourceContainerGroup, SWT.BORDER);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		sourceNameField.setLayoutData(data);

		setSourceNameFieldForDragAndDrop();

		sourceNameField.addListener(SWT.Modify, this);

		sourceNameField.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateFromSourceField();

			}
		});

		sourceNameField.addKeyListener(new KeyListener() {
			/*
			 * @see KeyListener.keyPressed
			 */
			public void keyPressed(KeyEvent e) {
				//If there has been a key pressed then mark as dirty
				entryChanged = true;

			}

			/*
			 * @see KeyListener.keyReleased
			 */
			public void keyReleased(KeyEvent e) {
				//do nothing
			}
		});

		sourceNameField.addFocusListener(new FocusListener() {
			/*
			 * @see FocusListener.focusGained(FocusEvent)
			 */
			public void focusGained(FocusEvent e) {
				//Do nothing when getting focus
				if (dragAndDropFileNames != null) {
					sourceNameField.setEnabled(false);
				}

			}

			/*
			 * @see FocusListener.focusLost(FocusEvent)
			 */
			public void focusLost(FocusEvent e) {
				//Clear the flag to prevent constant update
				if (entryChanged) {

					entryChanged = false;
					updateFromSourceField();
				}

			}
		});

		// source browse button
		sourceBrowseButton = new Button(sourceContainerGroup, SWT.PUSH);
		sourceBrowseButton.setText(J2EEUIMessages.getResourceString("DataTransfer.browse")); //$NON-NLS-1$
		sourceBrowseButton.addListener(SWT.Selection, this);
		sourceBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		if (dragAndDropFileNames != null) {
			sourceBrowseButton.setEnabled(false);
		}

	}

	private void setSourceNameFieldForDragAndDrop() {
		if (dragAndDropFileNames != null) {
			String fileName = (String) (dragAndDropFileNames.get(0));
			sourceNameField.setText(fileName);
			File f = new File(fileName);
			if (f.isFile()) {
				if (ImportUtil.getExtension(f).equals("zip") || ImportUtil.getExtension(f).equals("jar")) { //$NON-NLS-1$ //$NON-NLS-2$
					//importFromZip.setSelection(true);
					//importFromDir.setSelection(false);
				} else if (ImportUtil.getExtension(f).equals("class")) { //$NON-NLS-1$
					sourceNameField.setText(getClassFileDirectory(fileName));

					//get com.ibm.abc.ClassName
					PackageNameResolver nameResolver = new PackageNameResolver();
					String qualifiedClassName = nameResolver.getClassName(fileName);
					if (qualifiedClassName != null) {

						//get com
						int index = qualifiedClassName.indexOf('.');
						if (index == -1) {
							String textToSet = fileName.substring(0, 1 + fileName.lastIndexOf(File.separatorChar));
							sourceNameField.setText(textToSet);
							return;
						}
						String baseDir = qualifiedClassName.substring(0, index);

						//get com.ibm.abc
						index = qualifiedClassName.lastIndexOf('.');
						String packageName = qualifiedClassName.substring(0, index);
						//get com/ibm/abc
						packageDirStruc = packageName.replace('.', File.separatorChar);

						//get C:\com
						index = fileName.indexOf(baseDir);
						//if packageDirStuc exists then set the sourceDir to com, else
						//set the directory to the parent directory of the class
						if (fileName.indexOf(packageDirStruc) != -1) {
							int baseDirLength = baseDir.length();
							String textToSet = fileName.substring(0, index + baseDirLength);
							index = packageName.indexOf('.');
							if (index == -1)
								packageBaseDirName = packageName;
							else
								packageBaseDirName = packageName.substring(0, index);

							f = new File(textToSet);
							if (f.getParent() != null)
								f = new File(f.getParent());
							textToSet = f.getAbsolutePath(); //want to set the root directory to
							// com's parent
							sourceNameField.setText(textToSet);
						}
					}

				}
			}
		}
	}

	/**
	 * Update the receiver from the source name field.
	 */

	private void updateFromSourceField() {

		setSourceName(sourceNameField.getText());
		//Update enablements when this is selected
		updateWidgetEnablements();
	}

	/**
	 * Creates and returns a <code>FileSystemElement</code> if the specified file system object
	 * merits one. The criteria for this are: Also create the children.
	 */
	protected MinimizedFileSystemElement createRootElement(Object fileSystemObject, IImportStructureProvider provider) {
		boolean isContainer = provider.isFolder(fileSystemObject);
		String elementLabel = provider.getLabel(fileSystemObject);

		// Use an empty label so that display of the element's full name
		// doesn't include a confusing label
		MinimizedFileSystemElement dummyParent = new MinimizedFileSystemElement("", null, true); //$NON-NLS-1$
		dummyParent.setPopulated();
		MinimizedFileSystemElement result = new MinimizedFileSystemElement(elementLabel, dummyParent, isContainer);

		result.setPackageBaseDirName(packageBaseDirName);
		result.setFileSystemObject(fileSystemObject);

		//Get the files for the element so as to build the first level
		result.getFiles(provider);

		return dummyParent;
	}

	/**
	 * Create the import source specification widgets
	 */
	protected void createSourceGroup(Composite parent) {
		//createImportTypeGroup(parent);
		createRootDirectoryGroup(parent);
		createFileSelectionGroup(parent);

		createButtonsGroup(parent);
	}

	/**
	 * Enable or disable the button group.
	 */
	protected void enableButtonGroup(boolean enable) {
		//		selectTypesButton.setEnabled(enable);
		selectAllButton.setEnabled(enable);
		deselectAllButton.setEnabled(enable);
	}

	/**
	 * Answer a boolean indicating whether the specified source currently exists and is valid
	 */
	protected boolean ensureSourceIsValid() {
		if (isSetImportFromDir()) {
			if (getSourceDirectory() != null && new File(getSourceDirectoryName()).isDirectory())
				return true;
			displayErrorDialog(getString("FileImport.invalidSource")); //$NON-NLS-1$
			sourceNameField.setFocus();
			return false;
		}
		ZipFile specifiedFile = getSpecifiedSourceFile();

		if (specifiedFile == null) {
			displayErrorDialog(getString("FileImport.invalidSource")); //$NON-NLS-1$
			sourceNameField.setFocus();
			return false;
		}
		return closeZipFile(specifiedFile);
	}

	/**
	 * Execute the passed import operation. Answer a boolean indicating success.
	 */
	protected boolean executeImportOperation(ImportOperation op) {
		initializeOperation(op);
		if (createFullStructure) {
			op.setCreateContainerStructure(true);
		} else {
			op.setCreateContainerStructure(false);
		}

		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			displayErrorDialog(e.getTargetException());
			return false;
		}

		IStatus status = op.getStatus();
		if (!status.isOK()) {
			ErrorDialog.openError(getContainer().getShell(), getString("FileImport.importProblems"), //$NON-NLS-1$
						null, // no special message
						status);
			return false;
		}

		return true;
	}

	// need the following private stuff just because the DataTransferMessages class is not public!
	//private static final String RESOURCE_BUNDLE = "org.eclipse.ui.wizards.datatransfer.messages";
	// //$NON-NLS-1$
	//private static ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);

	private static String getString(String key) {
		try {
			return J2EEUIMessages.getResourceString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * The Finish button was pressed. Try to do the required work now and answer a boolean
	 * indicating success. If false is returned then the wizard will not close.
	 * 
	 * @return boolean
	 */
	public boolean finish() {
		if (!ensureSourceIsValid())
			return false;

		clearProviderCache();

		saveWidgetValues();

		Iterator resourcesEnum = getSelectedResources().iterator();
		List fileSystemObjects = new ArrayList();
		while (resourcesEnum.hasNext()) {
			fileSystemObjects.add(((FileSystemElement) resourcesEnum.next()).getFileSystemObject());
		}

		if (fileSystemObjects.size() > 0) {
			if (getSourceDirectory() != null) {
				return importResources(fileSystemObjects);
			}
			return importResourcesFromZip(fileSystemObjects);
		}

		MessageDialog.openInformation(getContainer().getShell(), getString("DataTransfer.information"), //$NON-NLS-1$
					getString("FileImport.noneSelected")); //$NON-NLS-1$

		return false;
	}

	/**
	 * Returns a content provider for <code>FileSystemElement</code> s that returns only files as
	 * children.
	 */

	protected ITreeContentProvider getFileProvider() {
		return new WorkbenchContentProvider() {
			public Object[] getChildren(Object o) {
				if (o instanceof MinimizedFileSystemElement) {
					MinimizedFileSystemElement element = (MinimizedFileSystemElement) o;
					if (currentProvider != null) {
						return element.getFiles(currentProvider).getChildren(element);
					}
					return element.getFiles(FileSystemStructureProvider.INSTANCE).getChildren(element);
				}
				return new Object[0];
			}

			public Object[] getElements(Object element) {
				Object[] superObjects = super.getElements(element);
				if (dragAndDropFileNames != null && getSourceDirectory() != null) {
					MinimizedFileSystemElement anElement = null;
					int newObjectsIndex = 0;
					for (int i = 0; i < superObjects.length; i++) {
						anElement = (MinimizedFileSystemElement) superObjects[i];

						File file = (File) anElement.getFileSystemObject();

						for (int k = 0; k < dragAndDropFileNames.size(); k++) {
							if (file.getAbsolutePath().equals(dragAndDropFileNames.get(k))) {
								newObjectsIndex++;
							}
						}
					}
					if (newObjectsIndex > 0) {
						Object[] newObjects = new Object[newObjectsIndex];
						newObjectsIndex = 0;

						for (int i = 0; i < superObjects.length; i++) {
							anElement = (MinimizedFileSystemElement) superObjects[i];

							File file = (File) anElement.getFileSystemObject();

							for (int k = 0; k < dragAndDropFileNames.size(); k++) {
								if (file.getAbsolutePath().equals(dragAndDropFileNames.get(k))) {

									newObjects[newObjectsIndex++] = anElement;

								}
							}

						}
						return newObjects;
					}
				}
				return superObjects;
			}
		};
	}

	/**
	 * Answer the root FileSystemElement that represents the contents of the currently-specified
	 * source. If this FileSystemElement is not currently defined then create and return it.
	 */
	protected MinimizedFileSystemElement getFileSystemTree() {
		if (isSetImportFromDir()) {
			File sourceDirectory = getSourceDirectory();
			if (sourceDirectory != null) {
				return selectFiles(sourceDirectory, FileSystemStructureProvider.INSTANCE);
			}
			if (sourceNameField.getText().length() > 0) {
				displayErrorDialog(getString("FileImport.invalidSource")); //$NON-NLS-1$
				sourceNameField.setFocus();
			}
			return null;
		}
		ZipFile sourceFile = getSpecifiedSourceFile();
		if (sourceFile == null) {
			//Clear out the provider as well
			this.currentProvider = null;
			if (sourceNameField.getText().length() > 0) {
				displayErrorDialog(getString("FileImport.invalidSource")); //$NON-NLS-1$
				sourceNameField.setFocus();
			}
			return null;
		}

		ZipFileStructureProvider provider = getStructureProvider(sourceFile);
		this.currentProvider = provider;
		return selectFiles(provider.getRoot(), provider);
	}

	/**
	 * Returns a content provider for <code>FileSystemElement</code> s that returns only folders
	 * as children.
	 */
	protected ITreeContentProvider getFolderProvider() {
		return new WorkbenchContentProvider() {
			public Object[] getChildren(Object o) {
				if (o instanceof MinimizedFileSystemElement) {
					MinimizedFileSystemElement element = (MinimizedFileSystemElement) o;
					if (currentProvider != null) {
						return element.getFolders(currentProvider).getChildren(element);
					}
					return element.getFolders(FileSystemStructureProvider.INSTANCE).getChildren(element);
				}
				return new Object[0];
			}

			public boolean hasChildren(Object o) {
				if (o instanceof MinimizedFileSystemElement) {
					MinimizedFileSystemElement element = (MinimizedFileSystemElement) o;
					if (element.isPopulated())
						return getChildren(element).length > 0;
					//If we have not populated then wait until asked
					return true;
				}
				return false;
			}

		};
	}

	/**
	 * Returns a File object representing the currently-named source directory iff it exists as a
	 * valid directory, or <code>null</code> otherwise.
	 */
	protected File getSourceDirectory() {
		return getSourceDirectory(this.sourceNameField.getText());
	}

	/**
	 * Returns a File object representing the currently-named source directory iff it exists as a
	 * valid directory, or <code>null</code> otherwise.
	 * 
	 * @param path
	 *            a String not yet formatted for java.io.File compatability
	 */
	private File getSourceDirectory(String path) {
		if (isSetImportFromDir()) {
			File sourceDirectory = new File(getSourceDirectoryName(path));
			if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
				return null;
			}

			return sourceDirectory;
		}
		return null;
	}

	/**
	 * Answer the directory name specified as being the import source. Note that if it ends with a
	 * separator then the separator is first removed so that java treats it as a proper directory
	 */
	private String getSourceDirectoryName() {
		return getSourceDirectoryName(this.sourceNameField.getText());
	}

	/**
	 * Answer the directory name specified as being the import source. Note that if it ends with a
	 * separator then the separator is first removed so that java treats it as a proper directory
	 */
	private String getSourceDirectoryName(String sourceName) {
		IPath result = new Path(sourceName.trim());

		if (result.getDevice() != null && result.segmentCount() == 0) // something like "c:"
			result = result.addTrailingSeparator();
		else
			result = result.removeTrailingSeparator();

		return result.toOSString();
	}

	/**
	 * Answer the string to display as the label for the source specification field
	 */
	protected String getSourceLabel() {
		return J2EEUIMessages.getResourceString("DataTransfer.directory"); //$NON-NLS-1$
	}

	/**
	 * Handle all events and enablements for widgets in this dialog
	 * 
	 * @param event
	 *            Event
	 */
	public void handleEvent(Event event) {
		if (event.widget == sourceBrowseButton) {
			if (isSetImportFromDir()) {
				handleSourceBrowseButtonPressed();
			} else {
				handleSourceBrowseButtonPressedForZip();
			}
		}

		super.handleEvent(event);

	}

	/**
	 * Open an appropriate source browser so that the user can specify a source to import from
	 */
	protected void handleSourceBrowseButtonPressed() {
		String currentSource = this.sourceNameField.getText();
		DirectoryDialog dialog = new DirectoryDialog(sourceNameField.getShell(), SWT.SAVE);
		dialog.setMessage(SELECT_SOURCE_MESSAGE);
		dialog.setFilterPath(getSourceDirectoryName(currentSource));

		String selectedDirectory = dialog.open();
		if (selectedDirectory != null) {
			//Just quit if the directory is not valid
			if ((getSourceDirectory(selectedDirectory) == null) || selectedDirectory.equals(currentSource))
				return;
			//If it is valid then proceed to populate
			setErrorMessage(null);
			setSourceName(selectedDirectory);
			selectionGroup.setFocus();
		}
	}

	/**
	 * Open a registered type selection dialog and note the selections in the receivers
	 * types-to-export field., Added here so that inner classes can have access
	 */
	protected void handleTypesEditButtonPressed() {

		super.handleTypesEditButtonPressed();
	}

	/**
	 * Import the resources with extensions as specified by the user
	 */
	protected boolean importResources(List fileSystemObjects) {
		Iterator i = fileSystemObjects.iterator();
		while (i.hasNext()) {
			File f = (File) i.next();
			List singleItemList = new ArrayList();
			singleItemList.add(f);
			String textToSet = getPackageName(f);
			if (textToSet != null) {
				File newSource = new File(textToSet);
				executeImportOperation(new ImportOperation(getContainerFullPath(), newSource, FileSystemStructureProvider.INSTANCE, this, singleItemList));
			} else {
				executeImportOperation(new ImportOperation(getContainerFullPath(), getSourceDirectory(), FileSystemStructureProvider.INSTANCE, this, singleItemList));

			}
		}
		return true;

	}

	protected String getPackageName(File f) {
		if (ImportUtil.getExtension(f).equals("class")) { //$NON-NLS-1$
			String fileName = f.getAbsolutePath();
			//get com.ibm.abc.ClassName
			PackageNameResolver nameResolver = new PackageNameResolver();
			String qualifiedClassName = nameResolver.getClassName(fileName);
			if (qualifiedClassName != null) {

				//get com
				int index = qualifiedClassName.indexOf('.');
				if (index == -1) {
					return fileName.substring(0, 1 + fileName.lastIndexOf(File.separatorChar));
				}
				String baseDir = qualifiedClassName.substring(0, index);

				//get com.ibm.abc
				index = qualifiedClassName.lastIndexOf('.');
				String packageName = qualifiedClassName.substring(0, index);

				//get com/ibm/abc
				packageDirStruc = packageName.replace('.', File.separatorChar);

				//get C:\com
				index = fileName.indexOf(baseDir);
				//if packageDirStuc exists then set the sourceDir to com, else
				//set the directory to the parent directory of the class
				if (fileName.indexOf(packageDirStruc) != -1) {
					int baseDirLength = baseDir.length();
					String textToSet = fileName.substring(0, index + baseDirLength);
					index = packageName.indexOf('.');
					if (index == -1)
						packageBaseDirName = packageName;
					else
						packageBaseDirName = packageName.substring(0, index);

					f = new File(textToSet);
					if (f.getParent() != null)
						f = new File(f.getParent());
					textToSet = f.getAbsolutePath(); //want to set the root directory to com's
					// parent
					//sourceNameField.setText(textToSet);
					return textToSet;
				}
			}
		}
		return null;
	}

	/**
	 * Initializes the specified operation appropriately.
	 */
	protected void initializeOperation(ImportOperation op) {

		/*
		 * op.setCreateContainerStructure( createContainerStructureButton.getSelection());
		 */
		op.setOverwriteResources(overwriteExistingResourcesCheckbox.getSelection());
	}

	/**
	 * Returns whether the extension provided is an extension that has been specified for export by
	 * the user.
	 * 
	 * @param extension
	 *            the resource name
	 * @return <code>true</code> if the resource name is suitable for export based upon its
	 *         extension
	 */
	protected boolean isExportableExtension(String extension) {
		if (selectedTypes == null) // ie.- all extensions are acceptable
			return true;

		Iterator aenum = selectedTypes.iterator();
		while (aenum.hasNext()) {
			if (extension.equalsIgnoreCase((String) aenum.next()))
				return true;
		}

		return false;
	}

	/**
	 * Repopulate the view based on the currently entered directory.
	 */
	protected void resetSelection() {

		MinimizedFileSystemElement currentRoot = getFileSystemTree();
		this.selectionGroup.setRoot(currentRoot);
		if (dragAndDropFileNames != null) {

			if (dragAndDropFileNames.get(0).toString().endsWith(".zip") == false && dragAndDropFileNames.get(0).toString().endsWith(".jar") == false) { //$NON-NLS-1$ //$NON-NLS-2$
				this.selectionGroup.expandAll();
			}

			MinimizedFileSystemElement temp = (MinimizedFileSystemElement) currentRoot.getFolders().getChildren()[0];

			List dirList = pathToArray();

			for (int i = 0; i < dirList.size(); i++) {
				String s = (String) dirList.get(i);
				Object[] folders = temp.getFolders().getChildren();
				for (int k = 0; k < folders.length; k++) {
					if (((File) ((MinimizedFileSystemElement) folders[k]).getFileSystemObject()).getName().equals(s)) {
						temp = (MinimizedFileSystemElement) temp.getFolders().getChildren()[k];
						break;
					}
				}

			}

			if (dragAndDropFileNames.get(0).toString().endsWith(".zip") == false && dragAndDropFileNames.get(0).toString().endsWith(".jar") == false) { //$NON-NLS-1$ //$NON-NLS-2$
				this.selectionGroup.initialCheckTreeItem(temp);
			}

			//If can figure out how to pre-highlight dir, then use following code
			//to check dragged files only. Also need to show all files in tree.
			/*
			 * String fileName = null; MinimizedFileSystemElement name = null; int numFiles =
			 * temp.getFiles().getChildren().length; Object[] files = temp.getFiles().getChildren();
			 * for(int i = 0; i < dragAndDropFileNames.size(); i++) { fileName = (new
			 * File((String)dragAndDropFileNames.get(i))).getName(); for(int k = 0; k < numFiles;
			 * k++) { if(fileName.equals(((File) ((MinimizedFileSystemElement)
			 * files[0]).getFileSystemObject()).getName())) {
			 * this.selectionGroup.initialCheckTreeItem(temp); break; } } }
			 */

			//this.selectionGroup.setAllSelections(true);
		}
	}

	private List pathToArray() {
		String s = (String) dragAndDropFileNames.get(0);

		PackageNameResolver nameResolver = new PackageNameResolver();
		String qualifiedClassName = nameResolver.getClassName(s);

		int slashCounts = 0;
		if (qualifiedClassName != null) {
			int index = qualifiedClassName.indexOf('.');
			if (index == -1) {
				return Collections.EMPTY_LIST;
			}
			String baseDir = qualifiedClassName.substring(0, index);
			for (int i = s.indexOf(baseDir); i < s.lastIndexOf(File.separatorChar); i++) {
				if (s.charAt(i) == File.separatorChar)
					slashCounts++;
			}
		}

		List dirNamesArray = new ArrayList(slashCounts);
		if (s.endsWith(".zip") || s.endsWith(".jar")) { //$NON-NLS-1$ //$NON-NLS-2$
			return dirNamesArray;
		}
		int startIndex = 0;
		int endIndex;
		for (int i = 0; i <= slashCounts; i++) {
			endIndex = qualifiedClassName.indexOf('.', startIndex);
			dirNamesArray.add(qualifiedClassName.substring(startIndex, endIndex));
			startIndex = endIndex + 1;
		}
		return dirNamesArray;
	}

	/**
	 * Use the dialog store to restore widget values to the values that they held last time this
	 * wizard was used to completion
	 */
	protected void restoreWidgetValues() {
		IDialogSettings settings = getDialogSettings();
		if (settings != null) {
			String[] sourceNames = settings.getArray(STORE_SOURCE_NAMES_ID);
			if (sourceNames == null)
				return; // ie.- no values stored, so stop

			// set filenames history
			for (int i = 0; i < sourceNames.length; i++)
				sourceNameField.add(sourceNames[i]);
		}
	}

	/**
	 * Since Finish was pressed, write widget values to the dialog store so that they will persist
	 * into the next invocation of this wizard page
	 */
	protected void saveWidgetValues() {
		IDialogSettings settings = getDialogSettings();
		if (settings != null) {
			// update source names history
			String[] sourceNames = settings.getArray(STORE_SOURCE_NAMES_ID);
			if (sourceNames == null)
				sourceNames = new String[0];

			sourceNames = addToHistory(sourceNames, getSourceDirectoryName());
			settings.put(STORE_SOURCE_NAMES_ID, sourceNames);

		}
	}

	/**
	 * Invokes a file selection operation using the specified file system and structure provider. If
	 * the user specifies files to be imported then this selection is cached for later retrieval and
	 * is returned.
	 */
	protected MinimizedFileSystemElement selectFiles(final Object rootFileSystemObject, final IImportStructureProvider structureProvider) {

		final MinimizedFileSystemElement[] results = new MinimizedFileSystemElement[1];

		BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
			public void run() {
				//Create the root element from the supplied file system object
				results[0] = createRootElement(rootFileSystemObject, structureProvider);
			}
		});

		return results[0];
	}

	/**
	 * Set all of the selections in the selection group to value. Implemented here to provide access
	 * for inner classes.
	 * 
	 * @param value
	 *            boolean
	 */
	protected void setAllSelections(boolean value) {
		super.setAllSelections(value);
	}

	/**
	 * Sets the source name of the import to be the supplied path. Adds the name of the path to the
	 * list of items in the source combo and selects it.
	 * 
	 * @param path
	 *            the path to be added
	 */
	protected void setSourceName(String path) {

		if (path.length() > 0) {

			String[] currentItems = this.sourceNameField.getItems();
			int selectionIndex = -1;
			for (int i = 0; i < currentItems.length; i++) {
				if (currentItems[i].equals(path))
					selectionIndex = i;
			}
			if (selectionIndex < 0) {
				int oldLength = currentItems.length;
				String[] newItems = new String[oldLength + 1];
				System.arraycopy(currentItems, 0, newItems, 0, oldLength);
				newItems[oldLength] = path;
				this.sourceNameField.setItems(newItems);
				selectionIndex = oldLength;
			}
			this.sourceNameField.select(selectionIndex);

			resetSelection();
		}
	}

	/**
	 * Update the tree to only select those elements that match the selected types
	 */
	protected void setupSelectionsBasedOnSelectedTypes() {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(getContainer().getShell());
		final Map selectionMap = new Hashtable();

		final IElementFilter filter = new IElementFilter() {

			public void filterElements(Collection files, IProgressMonitor monitor) throws InterruptedException {
				if (files == null) {
					throw new InterruptedException();
				}
				Iterator filesList = files.iterator();
				while (filesList.hasNext()) {
					if (monitor.isCanceled())
						throw new InterruptedException();
					checkFile(filesList.next());
				}
			}

			public void filterElements(Object[] files, IProgressMonitor monitor) throws InterruptedException {
				if (files == null) {
					throw new InterruptedException();
				}
				for (int i = 0; i < files.length; i++) {
					if (monitor.isCanceled())
						throw new InterruptedException();
					checkFile(files[i]);
				}
			}

			private void checkFile(Object fileElement) {
				MinimizedFileSystemElement file = (MinimizedFileSystemElement) fileElement;
				if (isExportableExtension(file.getFileNameExtension())) {
					List elements = new ArrayList();
					FileSystemElement parent = file.getParent();
					if (selectionMap.containsKey(parent))
						elements = (List) selectionMap.get(parent);
					elements.add(file);
					selectionMap.put(parent, elements);
				}
			}

		};

		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			public void run(final IProgressMonitor monitor) throws InterruptedException {
				monitor.beginTask("ImportPage.filterSelections", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
				getSelectedResources(filter, monitor);
			}
		};

		try {
			dialog.run(true, true, runnable);
		} catch (InvocationTargetException exception) {
			//Couldn't start. Do nothing.
			return;
		} catch (InterruptedException exception) {
			//Got interrupted. Do nothing.
			return;
		}
		// make sure that all paint operations caused by closing the progress
		// dialog get flushed, otherwise extra pixels will remain on the screen until
		// updateSelections is completed
		getShell().update();
		// The updateSelections method accesses SWT widgets so cannot be executed
		// as part of the above progress dialog operation since the operation forks
		// a new process.
		if (selectionMap != null) {
			updateSelections(selectionMap);
		}
	}

	/*
	 * (non-Javadoc) Method declared on IDialogPage. Set the selection up when it becomes visible.
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		resetSelection();
		if (visible)
			this.sourceNameField.setFocus();
	}

	/**
	 * Update the selections with those in map . Implemented here to give inner class visibility
	 * 
	 * @param map
	 *            Map - key tree elements, values Lists of list elements
	 */
	protected void updateSelections(Map map) {
		super.updateSelections(map);
	}

	/**
	 * Check if widgets are enabled or disabled by a change in the dialog. Provided here to give
	 * access to inner classes.
	 * 
	 * @param event
	 *            Event
	 */
	protected void updateWidgetEnablements() {

		super.updateWidgetEnablements();
	}

	/**
	 * Answer a boolean indicating whether self's source specification widgets currently all contain
	 * valid values.
	 */
	protected boolean validateSourceGroup() {
		if (getSourceDirectory() != null) {
			File sourceDirectory = getSourceDirectory();
			if (sourceDirectory == null) {
				setMessage(SOURCE_EMPTY_MESSAGE);
				enableButtonGroup(false);
				return false;
			}

			if (sourceConflictsWithDestination(new Path(sourceDirectory.getPath()))) {
				setErrorMessage(getSourceConflictMessage()); //$NON-NLS-1$
				enableButtonGroup(false);
				return false;
			}

			enableButtonGroup(true);
			return true;
		}
		//If there is nothing being provided to the input then there is a problem
		if (this.currentProvider == null) {
			setMessage(SOURCE_EMPTY_MESSAGE);
			enableButtonGroup(false);
			return false;
		}
		enableButtonGroup(true);
		return true;
	}

	/**
	 * Returns whether the source location conflicts with the destination resource. This will occur
	 * if the source is already under the destination.
	 * 
	 * @param sourcePath
	 *            the path to check
	 * @return <code>true</code> if there is a conflict, <code>false</code> if not
	 */
	protected boolean sourceConflictsWithDestination(IPath sourcePath) {

		IContainer container = getSpecifiedContainer();
		if (container == null)
			return false;
		return getSpecifiedContainer().getLocation().isPrefixOf(sourcePath);
	}

	protected IPath getResourcePath() {
		return importedClassesPath;
	}

	//-------------------------------------------------------------------------------------

	//makesure to call mainpage.cancel()
	public boolean cancel() {
		clearProviderCache();
		return true;
	}

	/**
	 * Clears the cached structure provider after first finalizing it properly.
	 */
	protected void clearProviderCache() {
		if (providerCache != null) {
			closeZipFile(providerCache.getZipFile());
			providerCache = null;
		}
	}

	/**
	 * Attempts to close the passed zip file, and answers a boolean indicating success.
	 */
	protected boolean closeZipFile(ZipFile file) {
		try {
			file.close();
		} catch (IOException e) {
			//displayErrorDialog(DataTransferMessages.format("ZipImport.couldNotClose", new
			// Object[] { file.getName()})); //$NON-NLS-1$
			return false;
		}

		return true;
	}

	/**
	 * Answer a handle to the zip file currently specified as being the source. Return null if this
	 * file does not exist or is not of valid format.
	 */

	protected ZipFile getSpecifiedSourceFile() {
		return getSpecifiedSourceFile(sourceNameField.getText());
	}

	/**
	 * Answer a handle to the zip file currently specified as being the source. Return null if this
	 * file does not exist or is not of valid format.
	 */

	private ZipFile getSpecifiedSourceFile(String fileName) {
		if (fileName.length() == 0)
			return null;

		try {
			return new ZipFile(fileName);
		} catch (ZipException e) {
			//displayErrorDialog(DataTransferMessages.getString("ZipImport.badFormat"));
			// //$NON-NLS-1$
		} catch (IOException e) {
			//displayErrorDialog(DataTransferMessages.getString("ZipImport.couldNotRead"));
			// //$NON-NLS-1$
		}

		sourceNameField.setFocus();
		return null;
	}

	/**
	 * Returns a structure provider for the specified zip file.
	 */
	protected ZipFileStructureProvider getStructureProvider(ZipFile targetZip) {
		if (providerCache == null)
			providerCache = new ZipFileStructureProvider(targetZip);
		else if (!providerCache.getZipFile().getName().equals(targetZip.getName())) {
			clearProviderCache(); // ie.- new value, so finalize&remove old value
			providerCache = new ZipFileStructureProvider(targetZip);
		} else if (!providerCache.getZipFile().equals(targetZip))
			closeZipFile(targetZip); // ie.- duplicate handle to same .zip

		return providerCache;
	}

	/**
	 * Open a FileDialog so that the user can specify the source file to import from
	 */
	protected void handleSourceBrowseButtonPressedForZip() {
		String selectedFile = queryZipFileToImport();
		if (selectedFile != null) {
			if (!selectedFile.equals(sourceNameField.getText())) {
				//Be sure it is valid before we go setting any names
				ZipFile sourceFile = getSpecifiedSourceFile(selectedFile);
				if (sourceFile != null) {
					closeZipFile(sourceFile);
					setSourceName(selectedFile);
					selectionGroup.setFocus();
				}
			}
		}
	}

	/**
	 * Import the resources with extensions as specified by the user
	 */
	protected boolean importResourcesFromZip(List fileSystemObjects) {

		ZipFile zipFile = getSpecifiedSourceFile();
		ZipFileStructureProvider structureProvider = getStructureProvider(zipFile);

		boolean result = executeImportOperation(new ImportOperation(getContainerFullPath(), structureProvider.getRoot(), structureProvider, this, fileSystemObjects));

		closeZipFile(zipFile);

		return result;
	}

	/**
	 * Opens a file selection dialog and returns a string representing the selected file, or
	 * <code>null</code> if the dialog was canceled.
	 */
	protected String queryZipFileToImport() {
		FileDialog dialog = new FileDialog(sourceNameField.getShell(), SWT.OPEN);
		dialog.setFilterExtensions(new String[]{FILE_IMPORT_MASK});

		String currentSourceString = sourceNameField.getText();
		int lastSeparatorIndex = currentSourceString.lastIndexOf(File.separator);
		if (lastSeparatorIndex != -1)
			dialog.setFilterPath(currentSourceString.substring(0, lastSeparatorIndex));

		return dialog.open();
	}

}