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
package org.eclipse.jst.j2ee.internal.archive;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.archive.internal.ArchiveURIConverter;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.EMF2SAXRendererFactory;
import org.eclipse.wst.common.internal.emf.resource.IRootObjectResource;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;

public class JavaEEEMFArchiveAdapterHelper {

	// TODO may not need this
	private class EMFAddapter extends AdapterImpl {
	};

	protected ResourceSet resourceSet;

	private EMFAddapter adapter = null;

	private IArchive archive = null;

	public JavaEEEMFArchiveAdapterHelper() {
	}

	public JavaEEEMFArchiveAdapterHelper(IArchive anArchive) {
		setArchive(anArchive);
	}

	public void setArchive(IArchive anArchive) {
		this.archive = anArchive;
	}

	public IArchive getArchive() {
		return archive;
	}

	public boolean containsModelObject(IPath modelObjectPath) {
		IArchive archive = getArchive();
		if (archive.containsArchiveResource(modelObjectPath)) {
			URI uri = getArchiveURIConverter().getURI(modelObjectPath);
			return containsResource(uri);
		}
		return false;
	}

	protected boolean containsResource(URI uri) {
		// TODO figure out a way to figure this out without actually
		// loading the resource
		IRootObjectResource resource = (IRootObjectResource) getResourceSet().getResource(uri, true);
		if (resource != null && resource.getRootObject() != null) {
			return true;
		}
		return false;
	}

	protected ArchiveURIConverter archiveURIConverter;

	public void setArchiveURIConverter(ArchiveURIConverter archiveURIConverter) {
		this.archiveURIConverter = archiveURIConverter;
	}

	public ArchiveURIConverter getArchiveURIConverter() {
		if (archiveURIConverter == null) {
			archiveURIConverter = new ArchiveURIConverter(getArchive());
		}
		return archiveURIConverter;
	}

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		URI uri = getArchiveURIConverter().getURI(modelObjectPath);
		IRootObjectResource resource = (IRootObjectResource) getResourceSet().getResource(uri, true);
		return resource.getRootObject();
	}

	// public Object getModelObject(IPath modelObjectPath) throws
	// ArchiveModelLoadException {
	// URI uri = getArchiveURIConverter().getURI(modelObjectPath);
	// return getResourceSet().getResource(uri, true);
	// }

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

	public void initializeResourceSet() {
		ResourceSet rs = new ResourceSetImpl() {
			private IContentDescription getContentDescription(URI uri) {
				IArchiveResource archiveResource = null;
				InputStream ioStream = null;
				try {
					IPath path = getArchiveURIConverter().getPath(uri);
					archiveResource = getArchive().getArchiveResource(path);
					ioStream = archiveResource.getInputStream();
					IContentDescription description = Platform.getContentTypeManager().getDescriptionFor(ioStream, path.lastSegment(), IContentDescription.ALL);
					return description;
				} catch (FileNotFoundException e) {
					ArchiveUtil.warn(e);
					return null;
				} catch (IOException e) {
					ArchiveUtil.warn(e);
					return null;
				} finally {
					if (ioStream != null) {
						try {
							ioStream.close();
						} catch (IOException e) {
							ArchiveUtil.warn(e);
						}
					}
				}
			}

			public Resource createResource(URI uri) {
				IContentDescription description = getContentDescription(uri);

				Resource.Factory resourceFactory = null;
				if (null != description) {
					SAXFactoryKey key = new SAXFactoryKey(uri, description);
					resourceFactory = getSAXResourceFactory(key);
					if (null == resourceFactory) {
						resourceFactory = ((WTPResourceFactoryRegistry) getResourceFactoryRegistry()).getFactory(uri, description);
						Class clazz = resourceFactory.getClass();
						boolean isTranslator = false;
						while (!isTranslator && clazz != null) {
							if (clazz == TranslatorResourceFactory.class) {
								isTranslator = true;
							} else {
								clazz = clazz.getSuperclass();
							}
						}
						if (isTranslator) {
							try {
								Constructor constructor = resourceFactory.getClass().getConstructor(RendererFactory.class);
								resourceFactory = (Resource.Factory) constructor.newInstance(EMF2SAXRendererFactory.INSTANCE);
							} catch (SecurityException e) {
								ArchiveUtil.warn(e);
							} catch (NoSuchMethodException e) {
								ArchiveUtil.warn(e);
							} catch (IllegalArgumentException e) {
								ArchiveUtil.warn(e);
							} catch (InstantiationException e) {
								ArchiveUtil.warn(e);
							} catch (IllegalAccessException e) {
								ArchiveUtil.warn(e);
							} catch (InvocationTargetException e) {
								ArchiveUtil.warn(e);
							}
						}
						setSAXResourceFactory(key, resourceFactory);
					}
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
		rs.setResourceFactoryRegistry(WTPResourceFactoryRegistry.INSTANCE);
		rs.setURIConverter(getArchiveURIConverter());
		setResourceSet(rs);
	}

	private class SAXFactoryKey {
		private URI uri = null;

		private IContentDescription description = null;

		public SAXFactoryKey(URI uri, IContentDescription description) {
			this.uri = uri;
			this.description = description;
		}
	}

	private static Map<SAXFactoryKey, Resource.Factory> localFactoryMap = new HashMap<SAXFactoryKey, Resource.Factory>();

	private static Resource.Factory getSAXResourceFactory(SAXFactoryKey key) {
		synchronized (localFactoryMap) {
			return localFactoryMap.get(localFactoryMap);
		}
	}

	private static void setSAXResourceFactory(SAXFactoryKey key, Resource.Factory resourceFactory) {
		synchronized (localFactoryMap) {
			localFactoryMap.put(key, resourceFactory);
		}
	}

}
