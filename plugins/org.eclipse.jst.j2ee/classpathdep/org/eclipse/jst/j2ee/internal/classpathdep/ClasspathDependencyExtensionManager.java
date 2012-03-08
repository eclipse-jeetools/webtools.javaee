/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionConverter;
import org.eclipse.core.expressions.ExpressionTagNames;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyExtension;

public class ClasspathDependencyExtensionManager 
{
  private static ClasspathDependencyExtensionManager instance;
  private List<ElementEntry> entries;
  
  private ClasspathDependencyExtensionManager()
  {	 
    entries = new ArrayList<ElementEntry>();
  }
  
  public static ClasspathDependencyExtensionManager instance()
  {
    if( instance == null )
    {
      instance = new ClasspathDependencyExtensionManager();
      instance.load();
    }
    
    return instance;
  }
   
  /**
   * 
   * @param project the project
   * @param entry the entry
   * @return returns true if one extender indicates that it can handle this entry
   */
  public boolean doesProjectHandleExport( IProject project, IClasspathEntry entry )
  {
	boolean result = false;
	
	// If one extender returns true we will return true for this call.
	for( ElementEntry listEntry : entries )
	{
	  if( listEntry.isEnabled( project ) )	
	  {
	    IClasspathDependencyExtension extension = listEntry.getExtensionClass();
	    
	    if( extension != null )
	    {
	      if( extension.projectHandlesExport( project, entry ) )
	      {
	    	// We found an extension that says that it will handle the export for this entry.
	        result = true;
	        break;
	      }
	    }
	  }
	}
	
	return result;
  }
  
  /**
   * 
   * @param project the project
   * @param entry the entry
   * @return returns the dependency value from an extender.
   */
  public String getDependencyValue( IProject project, IClasspathEntry entry )
  {
	String result = null;
	
	// If one extender returns true we will return true for this call.
	for( ElementEntry listEntry : entries )
	{
	  if( listEntry.isEnabled( project ) )	
	  {
	    IClasspathDependencyExtension extension = listEntry.getExtensionClass();
	    
	    if( extension != null )
	    {
	      result = extension.getClasspathdependencyAttribute( project, entry );
	      
	      if( result != null )
	      {
	    	// We found an extension that has a value for this attribute.
	        break;
	      }
	    }
	  }
	}
	
	return result;
  }

  private void load()
  {
	IExtensionRegistry      registry = Platform.getExtensionRegistry();
	IConfigurationElement[] elements = registry.getConfigurationElementsFor( "org.eclipse.jst.j2ee.ClasspathDependencyExtension" ); //$NON-NLS-1$
		
	for( IConfigurationElement element : elements ) 
	{
	  if( element.getName().equals( "classpathdependency" ))   //$NON-NLS-1$
	  {
	    IConfigurationElement[] childElements = element.getChildren();
	    ElementEntry            elementEntry  = new ElementEntry();
	    
     	elementEntry.element = element;
     	
	    for( IConfigurationElement childElement : childElements )
	    {
	      String elementName = childElement.getName();
	      
	      if( elementName.equals( ExpressionTagNames.ENABLEMENT ) ) 
	      {
	        try
	        {
	          elementEntry.enablementExpression = ExpressionConverter.getDefault().perform( childElement );	
	        }
	        catch( CoreException exc )
	        {
	          // Do nothing.  Element entry will get a null entered for the enblement expression. 	
	        }
	      }
	    }
	    
	    entries.add( elementEntry );	
	  }
	} 
  }
  
  private class ElementEntry
  {
	public Expression            enablementExpression;
	public IConfigurationElement element;
	
	private IClasspathDependencyExtension extension;
    
    public IClasspathDependencyExtension getExtensionClass()
    {
      if( element == null ) return null;
      
      if( extension == null )
      {
        try
        {
          extension = (IClasspathDependencyExtension)element.createExecutableExtension( "class" );   //$NON-NLS-1$
        }
        catch( CoreException exc )
        {
          return null;
        }
      }
      
      return extension;
    }  
    
    public boolean isEnabled( IProject project ) 
    {
  	  if( enablementExpression == null ) return true;
  	
  	  boolean            result  = false;
  	  IEvaluationContext context = new EvaluationContext( null, project );
  	
  	  context.setAllowPluginActivation( true );
  	    
  	  try
  	  {
  	    EvaluationResult expResult = enablementExpression.evaluate( context );
  	        
  	    // If any expression returns TRUE or NOT_LOADED we will return true as
  	    // the result.
  	    if( expResult != EvaluationResult.FALSE )
  	    {
  	      result = true;
  	    }
  	  }
  	  catch( CoreException exc )
  	  {
  	    result = false;	  
  	  }
       
  	  return result;
    }
  }
}
