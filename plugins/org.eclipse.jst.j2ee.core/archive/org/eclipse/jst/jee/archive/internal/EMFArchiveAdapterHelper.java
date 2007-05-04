/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipise.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;



public class EMFArchiveAdapterHelper {

	public static final int USE_DOM_RENDERER = 0;
	public static final int USE_SSE_RENDERER = 1;
	
	
	
	// TODO may not need this
	private class EMFAddapter extends AdapterImpl {
	};

	protected ResourceSet resourceSet;

	private EMFAddapter adapter = null;

	private IArchive archive = null;

	public EMFArchiveAdapterHelper() {
	}

	public EMFArchiveAdapterHelper(IArchive anArchive) {
		setArchive(archive);
	}

	public void setArchive(IArchive archive) {
		this.archive = archive;
	}

	public IArchive getArchive() {
		return archive;
	}

	public boolean containsModelObject(IPath modelObjectPath) {
		return getArchive().containsArchiveResource(modelObjectPath) && null != getResourceSet().getResource(URI.createURI(modelObjectPath.toString()), false);
	}

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		return getResourceSet().getResource(URI.createURI(modelObjectPath.toString()), true);
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			initializeResourceSet();
			if (null == adapter) {
				adapter = new EMFAddapter();
			}
			resourceSet.eAdapters().add(adapter);
		}
		return resourceSet;
	}

	public void setResourceSet(org.eclipse.emf.ecore.resource.ResourceSet newResourceSet) {
		if (resourceSet != newResourceSet) {
			// remove adapter from old resource set
			if (resourceSet != null && adapter != null) {
				resourceSet.eAdapters().remove(adapter);
			}
			// add as adapter to new resource set if necessary
			if (newResourceSet != null && !newResourceSet.eAdapters().contains(adapter)) {
				if (adapter == null) {
					adapter = new EMFAddapter();
				}
				newResourceSet.eAdapters().add(adapter);
			}
			resourceSet = newResourceSet;
		} // no need to update if old set equals new set (by reference)
	}

	private static class ContentDescription implements IContentDescription {

		private Map map = new HashMap();

		private IContentType contentType = null;

		public ContentDescription(IContentType contentType) {
			this.contentType = contentType;
		}

		public String getCharset() {
			return (String) getProperty(CHARSET);
		}

		public Object getProperty(QualifiedName key) {
			return map.get(key);
		}

		public boolean isRequested(QualifiedName key) {
			return false;
		}

		public void setProperty(QualifiedName key, Object value) {
			map.put(key, value);
		}

		public IContentType getContentType() {
			return contentType;
		}

	};

	public static IContentDescription getContentDescription(InputStream in) {
		try {
			JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(in);
			int version = quickPeek.getVersion();
			String contentTypeId = null;
			switch (version) {
			case JavaEEQuickPeek.EJB_3_0_ID:
				contentTypeId = "org.eclipse.jst.jee.ee5ejbDD"; //$NON-NLS-1$
				break;
			case JavaEEQuickPeek.WEB_2_5_ID:
				contentTypeId = "org.eclipse.jst.jee.ee5webDD"; //$NON-NLS-1$
				break;
			case JavaEEQuickPeek.JEE_5_0_ID:
				int type = quickPeek.getType();
				switch (type) {
				case JavaEEQuickPeek.APPLICATION_CLIENT_TYPE:
					contentTypeId = "org.eclipse.jst.jee.ee5appclientDD"; //$NON-NLS-1$
					break;
				case JavaEEQuickPeek.APPLICATION_TYPE:
					contentTypeId = "org.eclipse.jst.jee.ee5earDD"; //$NON-NLS-1$
					break;
				}

			}
			if (null != contentTypeId) {
				return new ContentDescription(Platform.getContentTypeManager().getContentType(contentTypeId));
			}
		} catch (Exception ex) {
			ex.printStackTrace(); // TODO remove this
			return null;
		} finally {
			try {
				if (in != null)
					in.reset();
			} catch (IOException ex) {
				// Ignore
			}
		}
		return null;
	}

	public void initializeResourceSet() {
		ResourceSet rs = new ResourceSetImpl() {
			private IContentDescription getContentDescription(URI uri) {
				IArchiveResource archiveResource = null;
				InputStream ioStream = null;
				try {
					archiveResource = getArchive().getArchiveResource(new Path(uri.toString()));
					ioStream = archiveResource.getInputStream();

					IContentDescription description = EMFArchiveAdapterHelper.getContentDescription(ioStream);
					return description;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} finally {
					if (ioStream != null) {
						try {
							ioStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			public Resource createResource(URI uri) {
				IContentDescription description = getContentDescription(uri);
				Resource.Factory resourceFactory = null;
				if (null != description) {
					resourceFactory = ((WTPResourceFactoryRegistry) getResourceFactoryRegistry()).getFactory(uri, description);
				} else {
					resourceFactory = getResourceFactoryRegistry().getFactory(uri);
				}

				if (resourceFactory != null) {
					Resource result = resourceFactory.createResource(uri);
					getResources().add(result);
					return result;
				} else {
					return null;
				}
			}
		};
		Resource.Factory.Registry reg = createResourceFactoryRegistry();
		rs.setResourceFactoryRegistry(reg);
		rs.setURIConverter(new ArchiveURIConverter(getArchive()));
		setResourceSet(rs);
	}

	protected Resource.Factory.Registry createResourceFactoryRegistry() {
		WTPResourceFactoryRegistry reg = WTPResourceFactoryRegistry.INSTANCE;
		return reg;
	}

}
