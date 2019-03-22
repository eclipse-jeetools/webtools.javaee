/******************************************************************************
 * Copyright (c) 2005, 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.j2ee.archive;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * Used in conjunction with the <code>org.eclipse.jst.j2ee.archiveExportParticipants</code>
 * extension point in order to extend the base module archive export operation.
 * 
 * @since 3.0
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public interface IArchiveExportParticipant
{
    /**
     * Creates the data model that will be used to configure the operation.
     * 
     * @param rootArchiveExportDataModel the data model of the module archive export
     *   operation that this participant is extending
     * @return the created data model
     */
    
    IDataModel createDataModel( IDataModel rootArchiveExportDataModel );
    
    /**
     * Creates the operation that will be invoked in order to let this extension
     * participate in the export process.
     *  
     * @param extensionDataModel the data model associated with this export
     *   participant (previously returned by the createDataModel() method)
     * @return the created operation
     */
    
    IDataModelOperation createOperation( IDataModel extensionDataModel );
}
