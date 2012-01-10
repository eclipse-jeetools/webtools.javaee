/*******************************************************************************
 * Copyright (c) 2008, 2009 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ear;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.navigator.internal.ApplicationViewerSorter;

public class Application5ViewerSorter extends ApplicationViewerSorter {
  
  @Override
public int compare(Viewer viewer, Object e1, Object e2) {
    if(e1 instanceof BundledNode){
        return -1;
    } else if(e2 instanceof BundledNode){
      return 1;
    }
    return super.compare(viewer, e1, e2);
}

}
