/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal.workingsets;

import java.util.ArrayList;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.Assert;
import org.eclipse.jst.common.navigator.internal.ui.workingsets.AbstractWorkingSetManager;
import org.eclipse.jst.common.navigator.internal.ui.workingsets.ICommonWorkingSet;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.internal.IWorkbenchConstants;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.WorkingSetDescriptor;
import org.eclipse.ui.internal.registry.WorkingSetRegistry;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComponentWorkingSet   implements ICommonWorkingSet,IActionFilter{
	
	static final String FACTORY_ID = "org.eclipse.jst.j2ee.navigator.internal.workingsets.ComponentWorkingSetFactory"; //$NON-NLS-1$
	
	private static final String COMPONENT_TYPE_ID = "componentTypeId"; //$NON-NLS-1$
	
	private static final String EDIT_PAGE_ID = "editPageId"; //$NON-NLS-1$
	
	public static final String COMPONENT_WORKING_SET_ID = "org.eclipse.jst.j2ee.navigator.ui.ComponentWorkingSetPage";
	
	private String name;

    private ArrayList elements;

    private String editPageId;

    private IMemento workingSetMemento;
    
    private IWorkingSetManager manager;
	
    private String typeId;
       
    public static final String TAG_TYPE_ID = "typeId"; //$NON-NLS-1$
	    
	
    /**
     * Creates a new working set.
     * 
     * @param name the name of the new working set. Should not have 
     * 	leading or trailing whitespace.
     * @param element the content of the new working set. 
     * 	May be empty but not <code>null</code>.
     */
    public ComponentWorkingSet(String aName, IAdaptable[] elements) {
	   name = aName;
	   internalSetElements(elements);
	   
    }

    /**
     * Creates a new working set from a memento.
     * 
     * @param name the name of the new working set. Should not have 
     * 	leading or trailing whitespace.
     * @param memento persistence memento containing the elements of  
     * 	the working set.
     */
    ComponentWorkingSet(String aName, String aTypeId, IMemento memento) {
    	name = aName;
        typeId = aTypeId;
        workingSetMemento = memento;
		internalSetElements(new IAdaptable[0]);
    }

    /**
	 * @param descriptor2
	 */
	public ComponentWorkingSet(ComponentWorkingSetDescriptor aDescriptor) {
		name = aDescriptor.getLabel();
		typeId = aDescriptor.getTypeId();
		editPageId = aDescriptor.getId();
		internalSetElements(new IAdaptable[0]);
	}

	/**
     * Tests the receiver and the object for equality
     * 
     * @param object object to compare the receiver to
     * @return true=the object equals the receiver, the name is the same.
     * 	false otherwise
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof ComponentWorkingSet) {
            ComponentWorkingSet workingSet = (ComponentWorkingSet) object;
            String objectPageId = workingSet.getId();
            String pageId = getId();
            boolean pageIdEqual = (objectPageId == null && pageId == null)
                    || (objectPageId != null && objectPageId.equals(pageId));
            String objectTypeId = workingSet.getTypeId();
            String typeId = getTypeId();
            boolean typeIdEqual = (objectTypeId == null && typeId == null)
            || (objectTypeId != null && objectTypeId.equals(typeId));
            return workingSet.getName().equals(getName())
                    && workingSet.getElementsArray().equals(getElementsArray())
                    && pageIdEqual
					&& typeIdEqual;
        }
        return false;
    }

    /**
	 * {@inheritDoc}
	 */
	public boolean isEditable() {
		WorkingSetRegistry registry = WorkbenchPlugin.getDefault().getWorkingSetRegistry();
		String id= getId();
		if (id == null)
			return false;
		WorkingSetDescriptor descriptor= registry.getWorkingSetDescriptor(id);
		if (descriptor == null)
			return false;
		return descriptor.isEditable();
	}
//    
    /**
     * Returns the receiver if the requested type is either IWorkingSet 
     * or IPersistableElement.
     * 
     * @param adapter the requested type
     * @return the receiver if the requested type is either IWorkingSet 
     * 	or IPersistableElement.
     */
    public Object getAdapter(Class adapter) {
        if (adapter == IWorkingSet.class
                || adapter == IPersistableElement.class) {
            return this;
        }
		return InternalPlatform.getDefault().getAdapterManager().getAdapter(this, adapter);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkingSet
     */
    public IAdaptable[] getElements() {
        ArrayList list = getElementsArray();
        return (IAdaptable[]) list.toArray(new IAdaptable[list.size()]);
    }

    /**
     * Returns the elements array list. Lazily restores the elements from
     * persistence memento. 
     * 
     * @return the elements array list
     */
    private ArrayList getElementsArray() {
        if (elements == null) {
            restoreWorkingSet();
            workingSetMemento = null;
        }
        return elements;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IPersistableElement
     */
    public String getFactoryId() {
        return FACTORY_ID;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkingSet
     */
    public String getId() {
    	if (editPageId == null)
    		editPageId = COMPONENT_WORKING_SET_ID;
        return editPageId;
    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkingSet
    */
    public String getName() {
        return name;
    }

    /**
     * Returns the hash code.
     * 
     * @return the hash code.
     */
    public int hashCode() {
        int hashCode = name.hashCode() & typeId.hashCode();

        if (editPageId != null) {
            hashCode &= editPageId.hashCode();
        }
        return hashCode;
   }

    /**
     * Recreates the working set elements from the persistence memento.
     */
    private void restoreWorkingSet() {
    	//updateElements();
    }

    /**
     * Implements IPersistableElement.
     * Persist the working set name and working set contents. 
     * The contents has to be either IPersistableElements or provide 
     * adapters for it to be persistent.
     * 
     * @see org.eclipse.ui.IPersistableElement#saveState(IMemento)
     */
    public void saveState(IMemento memento) {
        if (workingSetMemento != null) {
            // just re-save the previous memento if the working set has 
            // not been restored
            memento.putMemento(workingSetMemento);
        } else {
            memento.putString(IWorkbenchConstants.TAG_NAME, getName());
            memento.putString(IWorkbenchConstants.TAG_EDIT_PAGE_ID, getId());
            memento.putString(TAG_TYPE_ID,typeId);
       }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkingSet
     */
    public void setElements(IAdaptable[] newElements) {
        internalSetElements(newElements);
        fireWorkingSetChanged(IWorkingSetManager.CHANGE_WORKING_SET_CONTENT_CHANGE, null);
    }

    /**
     * Create a copy of the elements to store in the receiver.
     * 
     * @param elements the elements to store a copy of in the 
     * 	receiver.
     */
    private void internalSetElements(IAdaptable[] newElements) {
        Assert.isNotNull(newElements,
                "Working set elements array must not be null"); //$NON-NLS-1$

        elements = new ArrayList(newElements.length);
        for (int i = 0; i < newElements.length; i++) {
            elements.add(newElements[i]);
        }
    }
//
    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkingSet
     */
    public void setId(String pageId) {
        editPageId = pageId;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkingSet
     */
    public void setName(String newName) {
        Assert.isNotNull(newName, "Working set name must not be null"); //$NON-NLS-1$
        
        name = newName;
        fireWorkingSetChanged(IWorkingSetManager.CHANGE_WORKING_SET_NAME_CHANGE, null);
    }
    
    public void connect(IWorkingSetManager manager) {
		Assert.isTrue(this.manager == null, "A working set can only be connected to one manager"); //$NON-NLS-1$
    	this.manager= manager;
    }
    
      public void disconnect() {
    	this.manager= null;
		
      }
  
  
	private void fireWorkingSetChanged(String property, Object oldValue) {
    	AbstractWorkingSetManager receiver= manager != null
			? (AbstractWorkingSetManager)manager
			: (AbstractWorkingSetManager)WorkbenchPlugin.getDefault().getWorkingSetManager();
		receiver.workingSetChanged(this, property, oldValue);
    }
    
    
    
    public ImageDescriptor getImage() {
    	ComponentWorkingSetRegistry registry = ComponentWorkingSetRegistry.getInstance();
		ComponentWorkingSetDescriptor descriptor = null;
		
		descriptor = registry.getWorkingSetDescriptor(getId(), typeId);
		if (descriptor == null) {
			return null;
		}
		return descriptor.getIcon();
	 	    
    }
	 
	public String getTypeId() {
		return typeId;
	}
	


	

	public boolean testAttribute(Object target, String name, String value) {
		if (COMPONENT_TYPE_ID.equals(name))
			return getTypeId().equals(value);
		if (EDIT_PAGE_ID.equals(name))
			return getId().equals(value);
		
		return false;
		
	}

	
	


}
