/***********************************************************************
 * Copyright (c) 2008, 2019 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.mergers;

import java.util.List;

/**
 * Base interface for all merger classes.
 * 
 * @author Dimitar Giormov
 */
public interface IMerger {

  /**
   * Process method is the actual merge process. It will generate list of warnngs or throw exception.
   *  
   * @return list of warnings gathered during execution.
   * @throws Exception
   */
  public abstract List process() throws Exception;
}
