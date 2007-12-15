package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

class ComboIndexSynchHelper extends DataModelSynchHelper {
	
	private class ComboIndexListener implements SelectionListener {

		public void widgetSelected(SelectionEvent e) {
			Combo combo = (Combo) e.getSource();
			if (currentWidget == combo)
				return;
			String propertyName = (String) widgetToPropertyHash.get(combo);
			int selectionIndex = combo.getSelectionIndex(); 
			if (selectionIndex >= 0) {
//				DataModelPropertyDescriptor[] descriptors = dataModel.getValidPropertyDescriptors(propertyName);
//				String description = combo.getItem(combo.getSelectionIndex());
//				for (int i = 0; i < descriptors.length; i++) {
//					if (description.equals(descriptors[i].getPropertyDescription())) {
//						setProperty(propertyName, descriptors[i].getPropertyValue());
//						return;
//					}
//				}
				setProperty(propertyName, selectionIndex);
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}

	private ComboIndexListener comboIndexListener;

	ComboIndexSynchHelper(IDataModel model) {
		super(model);
	}
	
	/**
	 * Use this to synch the value of the specified Combo with the specified propertyName. The
	 * possible values displayed to the user are determined by return of
	 * IDataModel.getValidPropertyDescriptors(String).
	 * 
	 * @param combo
	 * @param propertyName
	 * @param dependentControls
	 */
	public void synchComboIndex(Combo combo, String propertyName, Control[] dependentControls) {
		synchComposite(combo, propertyName, dependentControls);
		if (null == comboIndexListener) {
			comboIndexListener = new ComboIndexListener();
		}
		combo.addSelectionListener(comboIndexListener);
	}

}
