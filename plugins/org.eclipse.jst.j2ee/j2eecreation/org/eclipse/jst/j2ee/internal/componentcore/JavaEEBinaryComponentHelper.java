package org.eclipse.jst.j2ee.internal.componentcore;

import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.internal.BinaryComponentHelper;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class JavaEEBinaryComponentHelper extends BinaryComponentHelper {

	private IArchive archive;

	private int localArchiveAccessCount = 0;

	public static JavaEEQuickPeek getJavaEEQuickPeek(IVirtualComponent aBinaryComponent) {
		JavaEEBinaryComponentHelper helper = null;
		try {
			helper = new JavaEEBinaryComponentHelper(aBinaryComponent);
			IArchive archive = helper.getArchive();
			JavaEEQuickPeek qp = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive);
			return qp;
		} finally {
			if (helper != null) {
				helper.dispose();
			}
		}
	}

	public JavaEEBinaryComponentHelper(IVirtualComponent aBinaryComponent) {
		super(aBinaryComponent);
	}

	@Override
	public EObject getPrimaryRootObject() {
		JavaEEQuickPeek qp = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(getArchive());
		IPath ddPath = null;
		switch (qp.getType()) {
		case JavaEEQuickPeek.APPLICATION_CLIENT_TYPE:
			ddPath = new Path(J2EEConstants.APP_CLIENT_DD_URI);
			break;
		case JavaEEQuickPeek.EJB_TYPE:
			ddPath = new Path(J2EEConstants.EJBJAR_DD_URI);
			break;
		case JavaEEQuickPeek.WEB_TYPE:
			ddPath = new Path(J2EEConstants.WEBAPP_DD_URI);
			break;
		case JavaEEQuickPeek.CONNECTOR_TYPE:
			ddPath = new Path(J2EEConstants.RAR_DD_URI);
			break;
		}
		if (ddPath != null) {
			try {
				return (EObject) getArchive().getModelObject(ddPath);
			} catch (ArchiveModelLoadException e) {
				J2EEPlugin.logError(e);
			}
		}
		return null;
	}

	@Override
	public Resource getResource(URI uri) {
		IPath path = new Path(uri.toString());
		try {
			return (Resource) ((JavaEEBinaryComponentLoadAdapter) getArchive().getLoadAdapter()).getModelObject(path);
		} catch (ArchiveModelLoadException e) {
			J2EEPlugin.logError(e);
		}
		return null;
	}

	public IArchive accessArchive() {
		IArchive archive = getArchive();
		if (null != archive) {
			ArchiveCache cache = ArchiveCache.getInstance();
			cache.accessArchive(archive);
			synchronized (this) {
				localArchiveAccessCount++;
			}
		}
		return archive;
	}

	public void releaseArchive(IArchive archive) {
		if (archive != this.archive) {
			throw new RuntimeException("The IArchive parameter must be the same IArchive retrieved from accessArchive");
		}
		if (null != archive) {
			ArchiveCache cache = ArchiveCache.getInstance();
			cache.releaseArchive(archive);
			synchronized (this) {
				localArchiveAccessCount--;
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		int count = 0;
		synchronized (this) {
			count = localArchiveAccessCount;
		}
		if (count > 0 && archive != null) {
			ArchiveCache cache = ArchiveCache.getInstance();
			for (int i = 0; i < count; i++) {
				cache.releaseArchive(archive);
				synchronized (this) {
					localArchiveAccessCount--;
				}
			}
		}
	}

	protected IArchive getArchive() {
		if (archive == null) {
			archive = getUniqueArchive();
		}
		return archive;
	}

	protected IArchive getUniqueArchive() {
		try {
			return openArchive();
		} catch (ArchiveOpenFailureException e) {
			Logger.getLogger().logError(e);
		}
		return null;
	}

	protected IArchive openArchive() throws ArchiveOpenFailureException {
		ArchiveCache cache = ArchiveCache.getInstance();
		IArchive archive = cache.getArchive(getComponent());
		if (archive == null) {
			archive = cache.openArchive(this);
		}
		if (archive != null) {
			cache.accessArchive(archive);
			synchronized (this) {
				localArchiveAccessCount++;
			}
		}
		return archive;
	}

	public static void clearDisconnectedArchivesInEAR(IVirtualComponent earComponent) {
		ArchiveCache.instance.clearDisconnectedArchivesInEAR(earComponent);
	}

	public static void clearAllArchivesInProject(IProject earProject) {
		ArchiveCache.instance.clearAllArchivesInProject(earProject);
	}

	/**
	 * This cache manages IArchives across all
	 * {@link JavaEEBinaryComponentHelper} instances. If multiple
	 * {@link JavaEEBinaryComponentHelper} instances exist for the same
	 * underlying archive file (e.g. a jar file on disk) all will use the exact
	 * same IArchive instance. Care needs to be taken in managing the opening
	 * and closing of this IArchive which should only be done through internal
	 * methods within {@link JavaEEBinaryComponentHelper}
	 */
	private static class ArchiveCache {

		private static ArchiveCache instance = null;

		public static ArchiveCache getInstance() {
			if (instance == null) {
				instance = new ArchiveCache();
			}
			return instance;
		}

		protected Map<IVirtualComponent, IArchive> componentsToArchives = new Hashtable<IVirtualComponent, IArchive>();

		protected Map<IArchive, Integer> archiveAccessCount = new Hashtable<IArchive, Integer>();

		public synchronized void accessArchive(IArchive archive) {
			Integer count = archiveAccessCount.get(archive);
			Integer newCount = new Integer(count.intValue() + 1);
			archiveAccessCount.put(archive, newCount);
			if (newCount.intValue() == 1) {
				JavaEEBinaryComponentLoadAdapter binaryAdapter = null;
				if (archive.getArchiveOptions().hasOption(JavaEEArchiveUtilities.WRAPPED_LOAD_ADAPTER)) {
					binaryAdapter = (JavaEEBinaryComponentLoadAdapter) archive.getArchiveOptions().getOption(JavaEEArchiveUtilities.WRAPPED_LOAD_ADAPTER);
				} else {
					binaryAdapter = (JavaEEBinaryComponentLoadAdapter) archive.getArchiveOptions().getOption(ArchiveOptions.LOAD_ADAPTER);
				}
				if (!binaryAdapter.isPhysicallyOpen()) {
					try {
						binaryAdapter.physicallyOpen();
					} catch (ZipException e) {
						org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
					} catch (IOException e) {
						org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
					}
				}
			}
		}

		public synchronized void releaseArchive(IArchive archive) {
			Integer count = archiveAccessCount.get(archive);
			Integer newCount = new Integer(count.intValue() - 1);
			archiveAccessCount.put(archive, newCount);
			if (newCount.intValue() == 0) {
				JavaEEBinaryComponentLoadAdapter binaryAdapter = null;
				if (archive.getArchiveOptions().hasOption(JavaEEArchiveUtilities.WRAPPED_LOAD_ADAPTER)) {
					binaryAdapter = (JavaEEBinaryComponentLoadAdapter) archive.getArchiveOptions().getOption(JavaEEArchiveUtilities.WRAPPED_LOAD_ADAPTER);
				} else {
					binaryAdapter = (JavaEEBinaryComponentLoadAdapter) archive.getArchiveOptions().getOption(ArchiveOptions.LOAD_ADAPTER);
				}
				if (binaryAdapter.isPhysicallyOpen()) {
					binaryAdapter.physicallyClose();
				}
			}
		}

		public synchronized IArchive getArchive(IVirtualComponent component) {
			IArchive archive = componentsToArchives.get(component);
			return archive;
		}

		public synchronized void clearDisconnectedArchivesInEAR(IVirtualComponent earComponent) {
			if (componentsToArchives.isEmpty()) {
				return;
			}
			Set<IVirtualComponent> liveBinaryComponnts = new HashSet<IVirtualComponent>();
			IVirtualReference[] refs = earComponent.getReferences();
			IVirtualComponent component = null;
			for (int i = 0; i < refs.length; i++) {
				component = refs[i].getReferencedComponent();
				if (component.isBinary()) {
					liveBinaryComponnts.add(component);
				}
			}
			clearArchivesInProject(earComponent.getProject(), liveBinaryComponnts);
		}

		public synchronized void clearAllArchivesInProject(IProject earProject) {
			if (componentsToArchives.isEmpty()) {
				return;
			}
			clearArchivesInProject(earProject, null);
		}

		private void clearArchivesInProject(IProject earProject, Set excludeSet) {
			Iterator iterator = componentsToArchives.entrySet().iterator();
			IVirtualComponent component = null;
			IArchive archive = null;
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				component = (IVirtualComponent) entry.getKey();
				if (component.getProject().equals(earProject) && (excludeSet == null || !excludeSet.contains(component))) {
					archive = (IArchive) entry.getValue();
					IArchiveFactory.INSTANCE.closeArchive(archive);
					iterator.remove();
					archiveAccessCount.remove(archive);
				}
			}
		}

		public synchronized IArchive openArchive(JavaEEBinaryComponentHelper helper) throws ArchiveOpenFailureException {
			IArchive archive = JavaEEArchiveUtilities.INSTANCE.openArchive(helper.getComponent());
			componentsToArchives.put(helper.getComponent(), archive);
			archiveAccessCount.put(archive, new Integer(0));
			return archive;
		}
	}

}
