/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.mergers;

/**
 * ModelException is thrown when invalid values or inconsistencies are detected in the model.
 * 
 * @author Dimitar Giormov
 */
public class ModelException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -1239223181526060738L;


  public ModelException(Exception e) {
    super(e);
  }


  public ModelException() {
    super();
  }

}
