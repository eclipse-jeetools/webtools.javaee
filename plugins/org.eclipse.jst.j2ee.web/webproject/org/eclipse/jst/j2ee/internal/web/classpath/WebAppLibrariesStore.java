package org.eclipse.jst.j2ee.internal.web.classpath;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;

public class WebAppLibrariesStore {

	private static final String WEB_LIBRARIES_CACHE = "webLibrariesCache.index"; //$NON-NLS-1$
	private static Map<String, RestoreState> restoreStateMap;
	private static Map<String, RestoreState> saveStateMap = new HashMap<String, RestoreState>();

	static class RestoreState implements Serializable {
		private static final long serialVersionUID = 5565856269776724522L;
		transient boolean needToVerify;
		List<String> pathStrings;
		transient List<IPath> paths;

		private void writeObject(java.io.ObjectOutputStream out) throws IOException {
			pathStrings = new ArrayList<String>(paths.size());
			for (IPath p : paths) {
				pathStrings.add(p.toString());
			}
			out.defaultWriteObject();
		}

		private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
			in.defaultReadObject();
			needToVerify = true;
			paths = new ArrayList<IPath>(pathStrings.size());
			for (String s : pathStrings) {
				paths.add(new Path(s));
			}
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			} else if (o == null) {
				return false;
			} else if (o instanceof RestoreState) {
				RestoreState other = (RestoreState) o;
				if (paths == null) {
					return other.paths == null;
				}
				return paths.equals(other.paths);
			}
			return false;
		}

		@Override
		public int hashCode() {
			if (paths == null) {
				return 11;
			}
			return paths.hashCode();
		}
	}

	static {
		IPath stateLocation = WebPlugin.getDefault().getStateLocation();
		java.io.File file = stateLocation.append(WEB_LIBRARIES_CACHE).toFile();
		try{
			if (file.exists()) {
				FileInputStream fIn = null;
				boolean deleteCache = true;
				try {
					fIn = new FileInputStream(file);
					BufferedInputStream bIn = new BufferedInputStream(fIn);
					ObjectInputStream oIn = new ObjectInputStream(bIn);
					restoreStateMap = (HashMap<String, RestoreState>) oIn.readObject();
					oIn.close();
					
					//it is unfortunate this is necessary, but without iterating over the map
					//and explicitly testing each value, class cast exceptions may occur later
					//best to check them all now and recover
					Iterator<Entry<String, RestoreState>> iterator = restoreStateMap.entrySet().iterator();
					while(iterator.hasNext()){
						Entry<String, RestoreState> next = iterator.next();
						String s = next.getKey();
						RestoreState rs = next.getValue();
						if(s == null || rs == null){
							//may throw ClassCastException
						}
					}
					deleteCache = false;
				} catch (FileNotFoundException e) {
					WebPlugin.logError(e);
				} catch (IOException e) {
					WebPlugin.logError(e);
				} catch (ClassNotFoundException e) {
					WebPlugin.logError(e);
				} catch (ClassCastException e) {
					WebPlugin.logError(e);
				} finally {
					if (fIn != null) {
						try {
							fIn.close();
						} catch (IOException e) {
							WebPlugin.logError(e);
						}
					}
					if (deleteCache) {
						restoreStateMap = new HashMap<String, RestoreState>();
						file.delete();
					}
				}
			}
		} catch (Throwable e){
			try{
				WebPlugin.logError(e);
				file.delete();
			} catch (Exception e2){
				//eat it
			}
			restoreStateMap = new HashMap<String, RestoreState>();
		} finally {
			if(restoreStateMap == null) {
				restoreStateMap = new HashMap<String, RestoreState>();
			}
		}
		
	}

	public static RestoreState getRestoreState(String projectName) {
		RestoreState restoreState = null;
		synchronized (restoreStateMap) {
			restoreState = restoreStateMap.get(projectName);
		}
		if (restoreState != null) {
			synchronized (saveStateMap) {
				saveStateMap.put(projectName, restoreState);
			}
		}
		return restoreState;
	}

	static void flush(String projectName) {
		synchronized (restoreStateMap) {
			restoreStateMap.remove(projectName);
		}
		synchronized (saveStateMap) {
			saveStateMap.remove(projectName);
		}
	}
	
	private static class PersistJob extends Job {

		public PersistJob() {
			super("Persist Web App Libraries State"); //$NON-NLS-1$
			setSystem(true);
			setRule(null);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			SafeRunner.run(new ISafeRunnable() {
				public void run() throws Exception {
					persist();
				}

				public void handleException(Throwable exception) {
					WebPlugin.logError(exception);
				}
			});
			return Status.OK_STATUS;
		}
	}

	private static int persistDelay = 60000; // 1 minute
	private static PersistJob persistJob = new PersistJob();

	static void saveState(String projectName, List<IPath> paths) {
		RestoreState toSave = new RestoreState();
		toSave.paths = paths;

		boolean scheduleJob = false;

		synchronized (saveStateMap) {
			RestoreState previous = saveStateMap.get(projectName);
			if (null == previous || !previous.equals(toSave)) {
				saveStateMap.put(projectName, toSave);
				scheduleJob = true;
			}
		}
		if (scheduleJob) {
			synchronized (restoreStateMap) {
				// if it changed, do not load it from the cache again
				restoreStateMap.remove(projectName);
			}
			persistJob.schedule(persistDelay);
		}
	}

	private static void persist() {
		synchronized (saveStateMap) {
			System.out.println("persisting"); //$NON-NLS-1$
			IPath stateLocation = WebPlugin.getDefault().getStateLocation();
			java.io.File file = stateLocation.append(WEB_LIBRARIES_CACHE).toFile();
			FileOutputStream fOut = null;
			try {
				fOut = new FileOutputStream(file);
				BufferedOutputStream bOut = new BufferedOutputStream(fOut);
				ObjectOutputStream oOut = new ObjectOutputStream(bOut);
				oOut.writeObject(saveStateMap);
				oOut.close();
			} catch (FileNotFoundException e) {
				WebPlugin.logError(e);
			} catch (IOException e) {
				WebPlugin.logError(e);
			} finally {
				if (fOut != null) {
					try {
						fOut.close();
					} catch (IOException e) {
						WebPlugin.logError(e);
					}
				}
			}
		}
	}

}
