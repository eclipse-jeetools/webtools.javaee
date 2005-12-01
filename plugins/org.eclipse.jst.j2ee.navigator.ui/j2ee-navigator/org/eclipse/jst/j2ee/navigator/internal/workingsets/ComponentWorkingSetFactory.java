/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal.workingsets;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.internal.IWorkbenchConstants;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComponentWorkingSetFactory implements IElementFactory {

	/**
	 * 
	 */
	public ComponentWorkingSetFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	  /* (non-Javadoc)
     * @see org.eclipse.ui.IElementFactory
     */
    public IAdaptable createElement(IMemento memento) {
        String workingSetName = memento.getString(IWorkbenchConstants.TAG_NAME);
        String typeId = memento.getString(ComponentWorkingSet.TAG_TYPE_ID);
        String workingSetEditPageId = memento
                .getString(IWorkbenchConstants.TAG_EDIT_PAGE_ID);

        if (workingSetName == null)
            return null;

        ComponentWorkingSet workingSet = new ComponentWorkingSet(workingSetName, typeId, memento);
        if (workingSetEditPageId != null) {
            workingSet.setId(workingSetEditPageId);
        } else {
            // working sets created with builds 20020418 and 20020419 will not
            // have an edit page id. fix this automatically.
            workingSet.setId(ComponentWorkingSet.COMPONENT_WORKING_SET_ID); //$NON-NLS-1$
        }
        return workingSet;
    }

}
