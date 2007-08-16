/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.webservice.jaxrpcmap;

import org.eclipse.jst.j2ee.internal.common.XMLResource;

public interface JaxrpcmapResource extends XMLResource
{
  public static final int JAXRPC_MAP_TYPE = 8;

  JavaWSDLMapping getJavaWSDLMapping();
  public boolean isJaxrpcmap1_0();
  public boolean isJaxrpcmap1_1();
  
}
