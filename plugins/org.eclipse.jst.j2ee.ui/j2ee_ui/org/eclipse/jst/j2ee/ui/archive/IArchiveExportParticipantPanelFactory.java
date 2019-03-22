/******************************************************************************
 * Copyright (c) 2005, 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.archive;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Used in conjunction with the <code>org.eclipse.jst.j2ee.ui.archiveExportParticipantPanels</code>
 * extension point in order to extend the UI of the base module archive export wizard.
 * 
 * @since 3.0
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public interface IArchiveExportParticipantPanelFactory
{
    /**
     * Creates a composite containing the UI elements appropriate for the export participant
     * that this panel factory is registered with.
     * 
     * @param parent the parent composite
     * @param dataModel the data model of the export participant
     * @return the created composite
     */
    
    Composite createPanel( Composite parent,
                           IDataModel dataModel );
}
