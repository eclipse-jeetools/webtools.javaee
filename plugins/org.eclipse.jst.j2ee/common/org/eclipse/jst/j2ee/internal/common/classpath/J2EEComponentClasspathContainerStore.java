package org.eclipse.jst.j2ee.internal.common.classpath;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainer.LastUpdate;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

public class J2EEComponentClasspathContainerStore {
	private static final String EAR_LIBRARIES_CACHE = "earLibrariesCache.index"; //$NON-NLS-1$
	private static Map<String, LastUpdate> restoreStateMap;
	private static Map<String, LastUpdate> saveStateMap = new HashMap<String, LastUpdate>();

	static {
		IPath stateLocation = J2EEPlugin.getDefault().getStateLocation();
		java.io.File file = stateLocation.append(EAR_LIBRARIES_CACHE).toFile();
		try {
			if (file.exists()) {
				FileInputStream fIn = null;
				boolean deleteCache = true;
				try {
					fIn = new FileInputStream(file);
					BufferedInputStream bIn = new BufferedInputStream(fIn);
					ObjectInputStream oIn = new ObjectInputStream(bIn);
					restoreStateMap = (HashMap<String, LastUpdate>) oIn.readObject();
					oIn.close();
					
					//it is unfortunate this is necessary, but without iterating over the map
					//and explicitly testing each value, class cast exceptions may occur later
					//best to check them all now and recover
					Iterator<Entry<String, LastUpdate>> iterator = restoreStateMap.entrySet().iterator();
					while(iterator.hasNext()){
						Entry<String, LastUpdate> next = iterator.next();
						String s = next.getKey();
						LastUpdate lu = next.getValue();
						if(s == null || lu == null){
							//may throw ClassCastException
						}
					}
					deleteCache = false;
				} catch (FileNotFoundException e) {
					J2EEPlugin.logError(e);
				} catch (IOException e) {
					J2EEPlugin.logError(e);
				} catch (ClassNotFoundException e) {
					J2EEPlugin.logError(e);
				} catch (ClassCastException e){
					J2EEPlugin.logError(e);
				}
				finally {
					if (fIn != null) {
						try {
							fIn.close();
						} catch (IOException e) {
							J2EEPlugin.logError(e);
						}
					}
					if(deleteCache){
						restoreStateMap = new HashMap<String, LastUpdate>();
						file.delete();
					}
				}
			}
		} catch (Throwable e){
			try{
				J2EEPlugin.logError(e);
				file.delete();
			} catch (Exception e2){
				//eat it
			}
			restoreStateMap = new HashMap<String, LastUpdate>();
		} finally {
			if (restoreStateMap == null) {
				restoreStateMap = new HashMap<String, LastUpdate>();
			}
		}
	}

	private static void persist() {
		synchronized (saveStateMap) {
			IPath stateLocation = J2EEPlugin.getDefault().getStateLocation();
			java.io.File file = stateLocation.append(EAR_LIBRARIES_CACHE).toFile();
			FileOutputStream fOut = null;
			try {
				fOut = new FileOutputStream(file);
				BufferedOutputStream bOut = new BufferedOutputStream(fOut);
				ObjectOutputStream oOut = new ObjectOutputStream(bOut);
				oOut.writeObject(saveStateMap);
				oOut.close();
			} catch (FileNotFoundException e) {
				J2EEPlugin.logError(e);
			} catch (IOException e) {
				J2EEPlugin.logError(e);
			} finally {
				if (fOut != null) {
					try {
						fOut.close();
					} catch (IOException e) {
						J2EEPlugin.logError(e);
					}
				}
			}
		}
	}

	static void flush(String projectName) {
		synchronized (restoreStateMap) {
			restoreStateMap.remove(projectName);
		}
		synchronized (saveStateMap) {
			saveStateMap.remove(projectName);
		}
	}

	static void flushAll() {
		synchronized (restoreStateMap) {
			restoreStateMap.clear();
		}
		synchronized (saveStateMap) {
			saveStateMap.clear();
		}
		IPath stateLocation = J2EEPlugin.getDefault().getStateLocation();
		java.io.File file = stateLocation.append(EAR_LIBRARIES_CACHE).toFile();
		file.delete();
	}

	static LastUpdate getRestoreState(String projectName) {
		LastUpdate lastUpdate = null;
		synchronized (restoreStateMap) {
			lastUpdate = restoreStateMap.get(projectName);
		}
		if (lastUpdate != null) {
			synchronized (saveStateMap) {
				saveStateMap.put(projectName, lastUpdate);
			}
		}
		return lastUpdate;
	}

	private static class PersistJob extends Job {

		public PersistJob() {
			super("Persist EAR Libraries State"); //$NON-NLS-1$
			setSystem(true);
			setRule(null);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			SafeRunner.run(new ISafeRunnable() {
				@Override
				public void run() throws Exception {
					persist();
				}

				@Override
				public void handleException(Throwable exception) {
					J2EEPlugin.logError(exception);
				}
			});
			return Status.OK_STATUS;
		}
	}

	private static int persistDelay = 60000; // 1 minute
	private static PersistJob persistJob = new PersistJob();

	static void saveState(String projectName, LastUpdate lastUpdate) {
		boolean scheduleJob = false;
		synchronized (saveStateMap) {
			LastUpdate previousUpdate = saveStateMap.get(projectName);
			//this check is to avoid I/O if possible
			if(null == previousUpdate || !previousUpdate.equals(lastUpdate)){
				saveStateMap.put(projectName, lastUpdate);
				scheduleJob = true;
			}
		}
		if(scheduleJob){
			synchronized (restoreStateMap) {
				//if it changed, do not load it from the cache again
				restoreStateMap.remove(projectName);
			}
			persistJob.schedule(persistDelay);
		}
	}

}
