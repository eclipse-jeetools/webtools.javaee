/*******************************************************************************
 * Copyright (c) 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.contenttype;

import java.io.IOException;
import java.io.InputStream;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;

/** Recognizes Servlet 6.1 web and fragment descriptors. */
public final class JEE11ContentDescriber implements IContentDescriber {
    public static final QualifiedName JEEVERSION = new QualifiedName("jee-version", "11.0"); //$NON-NLS-1$ //$NON-NLS-2$
    @Override
    public int describe(InputStream contents, IContentDescription description) throws IOException {
        JavaEEQuickPeek peek = new JavaEEQuickPeek(contents);
        if ((peek.getType() == JavaEEQuickPeek.WEB_TYPE && peek.getVersion() == JavaEEQuickPeek.WEB_6_1_ID)
                || (peek.getType() == JavaEEQuickPeek.WEBFRAGMENT_TYPE && peek.getVersion() == JavaEEQuickPeek.WEBFRAGMENT_6_1_ID)) {
            return VALID;
        }
        return INVALID;
    }
    @Override
    public QualifiedName[] getSupportedOptions() { return new QualifiedName[] { JEEVERSION }; }
}
