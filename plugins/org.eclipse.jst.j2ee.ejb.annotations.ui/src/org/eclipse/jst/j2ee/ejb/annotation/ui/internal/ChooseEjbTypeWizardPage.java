/**
 * 
 */
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal;

import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EjbCommonDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;

/**
 * @author naci
 *
 */
public class ChooseEjbTypeWizardPage extends WTPWizardPage {


	protected Button sessionType;
	protected Button messageDrivenType;

	
	protected ChooseEjbTypeWizardPage(WTPOperationDataModel model, String pageName) {
		super(model, pageName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{
				EjbCommonDataModel.EJB_TYPE};	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite aComposite = new Composite(parent, SWT.NULL);
		aComposite.setLayout(new GridLayout());
		aComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		aComposite.setSize(aComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		aComposite.setFont(parent.getFont());
		createEjbTypeGroup(aComposite);
		setControl(aComposite);
		return aComposite;

	}

	protected void createEjbTypeGroup(Composite parent) {
		Composite ejbTypeGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		ejbTypeGroup.setLayout(layout);
		ejbTypeGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));


		sessionType = new Button(ejbTypeGroup, SWT.RADIO);
		sessionType.setText("SessionBean"); //$NON-NLS-1$
	
		messageDrivenType = new Button(ejbTypeGroup, SWT.RADIO);
		messageDrivenType.setText("MessageDrivenBean"); //$NON-NLS-1$

		sessionType.setSelection(true);
		messageDrivenType.setSelection(false);
		
		sessionType.addSelectionListener(new SelectionListener(){

			public void widgetSelected(SelectionEvent e) {
				if(sessionType.getSelection()){
					ChooseEjbTypeWizardPage.this.model.setProperty(EjbCommonDataModel.EJB_TYPE,"SessionBean");
					SessionBeanDataModel sessionBeanDataModel = (SessionBeanDataModel) model.getNestedModel("SessionBeanDataModel");
					NewJavaClassDataModel nestedModel = ((EjbCommonDataModel) model).getJavaClassModel();
					nestedModel.setProperty(NewJavaClassDataModel.SUPERCLASS, sessionBeanDataModel.getEjbSuperclassName());
					nestedModel.setProperty(NewJavaClassDataModel.INTERFACES, sessionBeanDataModel.getEJBInterfaces());
					nestedModel.setBooleanProperty(NewJavaClassDataModel.MODIFIER_ABSTRACT,true);
					((AddEjbWizard)ChooseEjbTypeWizardPage.this.getWizard()).newEjbClassOptionsWizardPage.refreshInterfaces(sessionBeanDataModel.getEJBInterfaces());
					
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				this.widgetSelected(e);
			}});
		
		messageDrivenType.addSelectionListener(new SelectionListener(){

			public void widgetSelected(SelectionEvent e) {
				if(messageDrivenType.getSelection()){
					ChooseEjbTypeWizardPage.this.model.setProperty(EjbCommonDataModel.EJB_TYPE,"MessageDrivenBean");
					MessageDrivenBeanDataModel messageDrivenBeanDataModel = (MessageDrivenBeanDataModel) model.getNestedModel("MessageDrivenBeanDataModel");
					NewJavaClassDataModel nestedModel = ((EjbCommonDataModel) model).getJavaClassModel();
					nestedModel.setProperty(NewJavaClassDataModel.SUPERCLASS, messageDrivenBeanDataModel.getEjbSuperclassName());
					nestedModel.setProperty(NewJavaClassDataModel.INTERFACES, messageDrivenBeanDataModel.getEJBInterfaces());
					nestedModel.setBooleanProperty(NewJavaClassDataModel.MODIFIER_ABSTRACT,false);
					((AddEjbWizard)ChooseEjbTypeWizardPage.this.getWizard()).newEjbClassOptionsWizardPage.refreshInterfaces(messageDrivenBeanDataModel.getEJBInterfaces());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				this.widgetSelected(e);
			}});
		
	}
	
	public String getEJBType()
	{
		return model.getStringProperty(EjbCommonDataModel.EJB_TYPE);
	}
	
	public boolean isPageComplete() {
		return true;
	}
	
	

}
