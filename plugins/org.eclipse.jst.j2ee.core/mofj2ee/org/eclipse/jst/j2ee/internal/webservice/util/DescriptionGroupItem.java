/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

public final class DescriptionGroupItem extends AbstractEnumerator
{
  public static final int DESCRIPTION = 0;

  public static final int DISPLAY_NAME = 1;

  public static final int SMALL_ICON = 2;

  public static final int LARGE_ICON = 3;

  public static final DescriptionGroupItem DESCRIPTION_LITERAL = new DescriptionGroupItem(DESCRIPTION, "description");//$NON-NLS-1$
  public static final DescriptionGroupItem DISPLAY_NAME_LITERAL = new DescriptionGroupItem(DISPLAY_NAME, "display-name");//$NON-NLS-1$
  public static final DescriptionGroupItem SMALL_ICON_LITERAL = new DescriptionGroupItem(SMALL_ICON, "small-icon");//$NON-NLS-1$
  public static final DescriptionGroupItem LARGE_ICON_LITERAL = new DescriptionGroupItem(LARGE_ICON, "large-icon");//$NON-NLS-1$

  private static final DescriptionGroupItem[] VALUES_ARRAY =
    new DescriptionGroupItem[]
    {
      DESCRIPTION_LITERAL,
      DISPLAY_NAME_LITERAL,
      SMALL_ICON_LITERAL,
      LARGE_ICON_LITERAL
    };

  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  public static DescriptionGroupItem get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      DescriptionGroupItem result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  public static DescriptionGroupItem get(int value)
  {
    switch (value)
    {
      case DESCRIPTION: return DESCRIPTION_LITERAL;
      case DISPLAY_NAME: return DISPLAY_NAME_LITERAL;
      case SMALL_ICON: return SMALL_ICON_LITERAL;
      case LARGE_ICON: return LARGE_ICON_LITERAL;      
    }
    return null;	
  }

  private DescriptionGroupItem(int value, String name)
  {
    super(value, name);
  }

}

