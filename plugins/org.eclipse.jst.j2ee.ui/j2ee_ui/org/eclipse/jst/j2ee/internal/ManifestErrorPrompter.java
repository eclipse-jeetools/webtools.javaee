/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

import org.eclipse.jst.j2ee.internal.plugin.ErrorDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManifestErrorPrompter implements ICommonManifestUIConstants {

	/**
	 * Constructor for ManifestErrorPrompter.
	 */
	private ManifestErrorPrompter() {
		super();
	}
	
	public static boolean showManifestException(Shell shell, String baseMessage, boolean includeCancel, Throwable caught) {
		StringBuffer msg = new StringBuffer(baseMessage);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_1);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_2);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_3);
		msg.append("\n"); //$NON-NLS-1$
		msg.append(MANIFEST_PROBLEM_4);
		return ErrorDialog.openError(shell,
		   ERROR_READING_MANIFEST_DIALOG_TITLE,
		   msg.toString(),
		   caught,
		   0, includeCancel);
	}

}
