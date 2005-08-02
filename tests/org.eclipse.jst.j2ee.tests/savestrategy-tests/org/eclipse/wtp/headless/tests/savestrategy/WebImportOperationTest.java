/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.headless.tests.savestrategy;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class WebImportOperationTest extends ModuleImportOperationTestCase {

	public final String WEB_TESTS_PATH;

	public WebImportOperationTest(String name) {
		super(name);
		String relativeImportTestsPath = "TestData" + File.separator + getDirectory() + File.separator;
		URL fullImportTestsPath = HeadlessTestsPlugin.getDefault().find(new Path(relativeImportTestsPath));
		WEB_TESTS_PATH = fullImportTestsPath.getPath();
	}

	public static Test suite() {
		return new TestSuite(WebImportOperationTest.class);
	}

	protected String getDirectory() {
		return "WARImportTests";
	}

	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getWarsInDirectory(HeadlessTestsPlugin.getDefault(), WEB_TESTS_PATH);
	}

	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new WebComponentImportDataModelProvider());
	}

	public void testThreading() throws Exception {
		final List projects = getImportableArchiveFileNames();
		final IProject[] projectArray = new IProject[projects.size()];
		final int iterationMax = 20;
		for (int iterationCount = 0; iterationCount < iterationMax; iterationCount++) {
			System.out.println("Importing Projects " + iterationCount);
			for (int i = 0; i < projects.size(); i++) {
				String jarName = projects.get(i).toString();
				String projectName = jarName.substring(jarName.lastIndexOf(File.separator) + 1, jarName.length() - 4);
				IDataModel dataModel = getModelInstance();
				dataModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, jarName);
				dataModel.setProperty(IJ2EEComponentImportDataModelProperties.COMPONENT_NAME, projectName);
				runDataModel(dataModel);
				projectArray[i] = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			}
			final Thread[] threads = new Thread[10 + 10 * iterationCount];

			class ReadyCounter {

				private boolean kill = false;
				private boolean ready = false;
				private int count = 0;

				private int requiredCount = 0;

				public ReadyCounter(int requiredCount) {
					this.requiredCount = requiredCount;
				}

				public synchronized void increment() {
					if (ready) {
						throw new RuntimeException();
					}
					count++;
					if (count == requiredCount) {
						ready = true;
					}
				}

				public synchronized void decrement() {
					count--;
				}

				public int getCurrentCount() {
					return count;
				}

				public synchronized boolean isReady() {
					return ready || kill;
				}

				public synchronized boolean isZero() {
					return count == 0;
				}

				public boolean isKill() {
					return kill;
				}

				public void kill() {
					System.out.println("Killing!!!!");
					kill = true;
				}

			}

			final ReadyCounter readyCounter = new ReadyCounter(threads.length);

			for (int threadCount = 0; threadCount < threads.length; threadCount++) {
				final int threadNum = threadCount;
				threads[threadCount] = new Thread(new Runnable() {

					private void kill(Exception e) {
						e.printStackTrace();
						readyCounter.kill();
						sleep(1);
					}

					public void run() {
						System.out.println("Thread " + threadNum + " started.");
						try {
							readyCounter.increment();
							for (int waitCount = 1; !readyCounter.isReady(); waitCount *= 2) {
								System.out.println("Thread " + threadNum + " waiting  " + waitCount + " currentCount=" + readyCounter.getCurrentCount());
								sleep(waitCount);
							}
							for (int iterationCount = 0; iterationCount < iterationMax; iterationCount++) {
								StructureEdit[][] readStructureEdits = new StructureEdit[iterationMax][projects.size()];
								StructureEdit[][] writeStructureEdits = new StructureEdit[readStructureEdits.length][projectArray.length];

								try {
									for (int structureEditCount = 0; structureEditCount < readStructureEdits.length; structureEditCount++) {
										for (int projectCount = 0; projectCount < projectArray.length; projectCount++) {
											readStructureEdits[structureEditCount][projectCount] = StructureEdit.getStructureEditForRead(projectArray[projectCount]);
											writeStructureEdits[structureEditCount][projectCount] = StructureEdit.getStructureEditForWrite(projectArray[projectCount]);
										}
									}

									for (int structureEditCount = 0; structureEditCount < readStructureEdits.length; structureEditCount++) {
										for (int projectCount = 0; projectCount < projects.size(); projectCount++) {
											if (null != readStructureEdits[structureEditCount][projectCount]) {
												readStructureEdits[structureEditCount][projectCount].getComponentModelRoot();
											}
											if (null != writeStructureEdits[structureEditCount][projectCount]) {
												writeStructureEdits[structureEditCount][projectCount].findComponentsByType("jst.web");
											}
										}
									}


								} catch (IllegalStateException e) {
									if (!e.getMessage().equals("Edit Model already disposed")) {
										kill(e);
									}
								} catch (RuntimeException e) {
									if (!e.getMessage().equals("This resource has been deleted and can no longer be used.")) {
										kill(e);
									}
								} catch (Exception e) {
									kill(e);
								} finally {
									for (int structureEditCount = 0; structureEditCount < readStructureEdits.length; structureEditCount++) {
										for (int projectCount = 0; projectCount < projectArray.length; projectCount++) {
											if (null != readStructureEdits[structureEditCount][projectCount]) {
												readStructureEdits[structureEditCount][projectCount].dispose();
											}
											if (null != writeStructureEdits[structureEditCount][projectCount]) {
												writeStructureEdits[structureEditCount][projectCount].dispose();
											}
										}
									}
								}
							}
						} finally {
							System.out.println("Thread " + threadNum + " finshed.");
							readyCounter.decrement();
						}
					}
				});
			}
			System.out.println("Starting Threads " + iterationCount);
			for (int threadCount = 0; threadCount < threads.length; threadCount++) {
				threads[threadCount].start();
			}
			while (!readyCounter.isReady()) {
				System.out.println("Main Thread Waiting ---------");
				sleep(1);
			}
			sleep(1);
			System.out.println("Before Deleting Projects " + iterationCount);
			ProjectUtility.deleteAllProjects();
			System.out.println("After Deleting Projects " + iterationCount);
			while (!readyCounter.isZero()) {
				sleep(1);
			}
			if (readyCounter.isKill()) {
				fail();
			}
		}
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
