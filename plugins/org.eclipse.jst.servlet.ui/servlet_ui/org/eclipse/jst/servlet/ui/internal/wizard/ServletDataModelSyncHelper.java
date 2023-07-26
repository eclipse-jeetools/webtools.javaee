/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

public class ServletDataModelSyncHelper extends DataModelSynchHelper {
	protected Map widgetToValueHash;
	protected MultipleCheckboxSelectionListener multipleCheckboxSelectionListener;

	public ServletDataModelSyncHelper(IDataModel model) {
		super(model);
	}
	
	/**
	 * Sets the UI to have the property value defined in the model
	 * 
	 * @param propertyName
	 * @link IDataModelListener for the flag values.
	 */
	@Override
	public void synchUIWithModel(final String propertyName, final int flag) {
		if (null != propertyToWidgetHash && propertyToWidgetHash.containsKey(propertyName)) {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					try {
						Object obj = propertyToWidgetHash.get(propertyName);
						if (obj != null) {
							if (obj instanceof List) {
								List widgets = (List) obj;
								for (int i = 0; i < widgets.size(); i++) {
									currentWidget = (Widget) widgets.get(i);
									synchUIWithModel(propertyName, flag, currentWidget);
								}
							} else {
								currentWidget = (Widget) obj;
								synchUIWithModel(propertyName, flag, currentWidget);
							}
						}
					} finally {
						currentWidget = null;
					}
				}
			});
		}
	}
	
	protected void synchUIWithModel(final String propertyName, final int flag, Widget currentWidget) {
		if (currentWidget != null && currentWidget != currentWidgetFromEvent) {
			//We must hold a copy in a temp variable because setting the widget value
			//may trigger an event that will cause this method to be called again.
			Widget widget = currentWidget;
			try {
				ignoreModifyEvent = true;
				if (currentWidget instanceof Text)
					setWidgetValue(propertyName, flag, (Text) currentWidget);
				else if (currentWidget instanceof Combo) {
					setWidgetValue(propertyName, flag, (Combo) currentWidget);
				} else if (currentWidget instanceof Button)
					setWidgetValue(propertyName, flag, (Button) currentWidget);
				else if (currentWidget instanceof Label)
					setWidgetValue(propertyName, flag, (Label) currentWidget);
				else if (currentWidget instanceof List)
					setWidgetValue(propertyName, flag, (org.eclipse.swt.widgets.List) currentWidget);
				else if (currentWidget instanceof Table)
					setWidgetValue(propertyName, flag, (Table) currentWidget);
				else if (currentWidget instanceof Tree)
					setWidgetValue(propertyName, flag, (Tree) currentWidget);
			} finally {
				ignoreModifyEvent = false;
			}
			//Pass the copy of the currentWidget
			setEnablement((Control) widget, dataModel.isPropertyEnabled(propertyName));
		}
	}
	
	@Override
	protected void setWidgetValue(String propertyName, int flag, Button button) {
		if ((button.getStyle() & SWT.CHECK) == SWT.CHECK) {
			Object propertyValue = dataModel.getProperty(propertyName);
			if (propertyValue instanceof java.util.List) {
				List value = (List) propertyValue;
				Object buttonValue = widgetToValueHash.get(button);
				boolean exists = value.contains(buttonValue);
				if (button.getSelection() != exists) {
					button.setSelection(exists);
				}
				return;
			}
		}
	}
	
	private class MultipleCheckboxSelectionListener implements SelectionListener {
		@Override
		public void widgetSelected(SelectionEvent e) {
			Button button = (Button) e.getSource();
			if (currentWidget == button)
				return;
			try {
				currentWidgetFromEvent = button;
				String propertyName = (String) widgetToPropertyHash.get(button);
				Object propertyValue = widgetToValueHash.get(button);
				
				Object contents = dataModel.getProperty(propertyName);
				
				if (contents == null) {
					contents = new ArrayList();
				}
				if (contents instanceof java.util.List) {
					List list = (List) contents;
					if (button.getSelection()) {
						if (!list.contains(propertyValue)) 
							list.add(propertyValue);
					} else {
						list.remove(propertyValue);
					}
					setProperty(propertyName, list);
					dataModel.notifyPropertyChange(propertyName, DataModelEvent.VALUE_CHG);
				}
			} finally {
				currentWidgetFromEvent = null;
			}
		}

		

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
	
	public void synchCheckbox(Button checkbox, String textValue, String propertyName, Control[] dependentControls) {
		setCompositeValue(checkbox, textValue);
		synchComposite(checkbox, propertyName, dependentControls);
		if (multipleCheckboxSelectionListener == null) {
			multipleCheckboxSelectionListener = new MultipleCheckboxSelectionListener();
		}
		checkbox.addSelectionListener(multipleCheckboxSelectionListener);
	}
	
	protected void setCompositeValue(Widget widget, Object value) {
		if (widgetToValueHash == null)
			widgetToValueHash = new HashMap();
		widgetToValueHash.put(widget, value);
	}
	
	@Override
	protected void synchComposite(Widget widget, String propertyName, Control[] depControls) {
		if (null == widgetToPropertyHash)
			widgetToPropertyHash = new HashMap();
		if (propertyToWidgetHash == null)
			propertyToWidgetHash = new HashMap();
		widgetToPropertyHash.put(widget, propertyName);
		Object value = propertyToWidgetHash.get(propertyName);
		List widgets;
		if (value == null || !(value instanceof List)) {
			widgets = new ArrayList();
		} else {
			widgets = (List) value;
		}
		widgets.add(widget);
		propertyToWidgetHash.put(propertyName, widgets);
		
		if (depControls != null) {
			if (widgetToDepControls == null)
				widgetToDepControls = new HashMap();
			widgetToDepControls.put(widget, depControls);
		}
		synchUIWithModel(propertyName, DataModelEvent.VALUE_CHG);
	}
}
