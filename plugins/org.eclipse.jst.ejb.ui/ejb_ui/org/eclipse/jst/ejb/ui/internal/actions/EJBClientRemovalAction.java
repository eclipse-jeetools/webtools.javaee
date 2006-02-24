/*
 * Created on Aug 8, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.ejb.ui.internal.actions;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.frameworks.internal.ui.WorkspaceModifyComposedOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorSubsetOperation;
import org.eclipse.wst.validation.internal.ui.plugin.ValidationUIPlugin;



public class EJBClientRemovalAction extends AbstractClientJARAction {

	
	public static String LABEL = Platform.getResourceString(
				Platform.getBundle(EJBUIPlugin.PLUGIN_ID),
				"%ejb.client.jar.remove.action.description_ui_"); //$NON-NLS-1$
	


	
	public EJBClientRemovalAction() {
		super();
		setText(LABEL);
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2ee.common.actions.BaseAction#primRun(org.eclipse.swt.widgets.Shell)
	 */
	protected void primRun(Shell shell) {
		if (!checkClientExists(shell))
			return;
		if (!checkBinaryProject(shell))
			return;
		if (!confirmProceed(shell))
			return;
		//TODO: reimplment operation
//		EJBClientProjectDataModel dataModel = new EJBClientProjectDataModel();
//		dataModel.setProperty(EJBClientProjectDataModel.EJB_PROJECT_NAME, getProject().getName());
//		dataModel.setBooleanProperty(EJBClientProjectDataModel.DELETE_WHEN_FINISHED, true);
//		EJBClientJARRemovalOperation op = new EJBClientJARRemovalOperation(dataModel, new UIOperationHandler(shell));
//		IRunnableWithProgress runnable = WTPUIPlugin.getRunnableWithProgress(op);
//		ProgressMonitorDialog dlg = new ProgressMonitorDialog(shell);
//		ValidatorManager validatorMgr = ValidatorManager.getManager();
//		try {
//			validatorMgr.suspendAllValidation(true);
//			dlg.run(false, false, runnable);
//		} catch (InvocationTargetException e) {
//			handleException(shell, e);
//		} catch (RuntimeException e) {
//			handleException(shell, e);
//		} catch (InterruptedException e) {
//			//do nothing
//		} finally {
//			validatorMgr.suspendAllValidation(false);
//			runValidationOperation(dlg);
//		}
	}
	
	protected void runValidationOperation(ProgressMonitorDialog dlg) {
		WorkspaceModifyComposedOperation runnable = new WorkspaceModifyComposedOperation();
		ValidatorSubsetOperation sop = new ValidatorSubsetOperation(getProject(), true, false);
		sop.setValidators(new String[] { "org.eclipse.jst.j2ee.core.internal.validation.EJBValidator"}); //$NON-NLS-1$ //$NON-NLS-2$
		//TODO: add extension back in for validator  "com.ibm.etools.ejb.mapvalidator.MapValidation" 
		runnable.addRunnable(ValidationUIPlugin.getRunnableWithProgress(sop)); //$NON-NLS-1$
		try {
			dlg.run(true, false, runnable);
		} catch (InvocationTargetException e) {
			Logger.getLogger(J2EEUIPlugin.PLUGIN_ID).logError(e);
		} catch (InterruptedException ie) {
		}
	}
	
	private void handleException(Shell shell, Exception e) {
		Logger.getLogger().logError(e);
		MessageDialog.openError(shell, REMOVE_ERROR_TITLE, ERROR_REMOVING_CLIENT);
	}

	/**
	 * @return
	 */
	private boolean checkClientExists(Shell shell) {
		if (getClientProject() == null) {
			MessageDialog.openError(shell, NO_CLIENT_JAR_TITLE, NO_CLIENT_JAR_MSG);
			return false;
		}
		return true; 
	}
	
	private boolean confirmProceed(Shell shell) {
		String message = MessageFormat.format(REMOVE_MESSAGE, new Object[] {getClientProject().getName()});
		return MessageDialog.openQuestion(shell, REMOVE_TITLE, message);
	}
	
	/**
	 * @return
	 */
	private IProject getClientProject() {
//		EJBNatureRuntime runtime = EJBNatureRuntime.getRuntime(getProject());
//		return runtime == null ? null : runtime.getDefinedEJBClientJARProject();
		
		return null;
	}

	private boolean checkBinaryProject(Shell shell) {
		if (JemProjectUtilities.isBinaryProject(getProject())) {
			MessageDialog.openError(shell, REMOVE_ERROR_TITLE, BINARY_EJB_PROJECT);
			return false;
		} else if (JemProjectUtilities.isBinaryProject(getClientProject())) {
			MessageDialog.openError(shell, REMOVE_ERROR_TITLE, BINARY_CLIENT_PROJECT);
			return false;
		}
		return true;		
	}

    
}
