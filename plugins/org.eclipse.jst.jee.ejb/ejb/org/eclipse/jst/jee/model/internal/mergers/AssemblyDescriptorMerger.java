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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.ejb.AssemblyDescriptor;

/**
 * Assembly Descriptor Merger is used to merge the artifacts under assembly descriptor tag. 
 * 
 * @author Dimitar Giormov
 */
public class AssemblyDescriptorMerger extends ModelElementMerger {

  /**
   * @param _base - is the base element, into which the values will be merged.
   * @param merge - is the conflicting element, which will be merged into base.
   * @param _kind
   */
  public AssemblyDescriptorMerger(JavaEEObject _base, JavaEEObject merge,
      int _kind) {
    super(_base, merge, _kind);
  }

  /**
   * Returns the base element, into which the values will be merged.
   * 
   * @return base and result element.
   */
  AssemblyDescriptor getBaseAssemblyDescriptor(){
    return (AssemblyDescriptor) getBase();
  }

  /**
   * Returns the conflicting element, which will be merged into base.
   * @return to merge element.
   */
  AssemblyDescriptor getToMergeAssemblyDescriptor(){
    return (AssemblyDescriptor) getToMerge();
  }

  /* (non-Javadoc)
   * @see org.eclipse.jst.javaee.ejb.model.mergers.ModelElementMerger#process()
   */
  @Override
  public List process() throws ModelException {
    List warnings = new ArrayList();
    mergeSecurityRoles(warnings);
    mergeTransactionAttribute(warnings);
    copyGeneralData(warnings);
    return warnings;
  }
  //TODO 
  private void mergeTransactionAttribute(List warnings) {
//    List containerTransactionsBase = getBaseAssemblyDescriptor().getContainerTransactions();
//    List containerTransactionsToMerge = getToMergeAssemblyDescriptor().getContainerTransactions();
//    if (containerTransactionsToMerge == null){
//      return;
//    }
  }

  private void mergeSecurityRoles(List warnings) {
    List securityRolesBase = getBaseAssemblyDescriptor().getSecurityRoles();
    List securityRolesToMerge = getToMergeAssemblyDescriptor().getSecurityRoles();
    if (securityRolesToMerge == null){
      return;
    }
    for (Object sRoles : securityRolesToMerge) {
      if(!containsSecRole((SecurityRole) sRoles, securityRolesBase)){
        securityRolesBase.add(EcoreUtil.copy((EObject) sRoles));
      }
    }
  }
  

  private boolean containsSecRole(SecurityRole secRole, List secRoles){
    for (Object sRoles : secRoles) {
      if(((SecurityRole)sRoles).getRoleName() == null || secRole.getRoleName() == null){
        return ((SecurityRole)sRoles).getRoleName() == secRole.getRoleName();
      }
      if (((SecurityRole)sRoles).getRoleName().equals(secRole.getRoleName())){
        return true;
      }
    }
    return false;
  }
  
  private void copyGeneralData(List warnings){

	    if (getToMergeAssemblyDescriptor().getApplicationExceptions() != null && getToMergeAssemblyDescriptor().getApplicationExceptions().size() > 0){
	      copyMissingContentInBase(getToMergeAssemblyDescriptor().getApplicationExceptions(), getBaseAssemblyDescriptor().getApplicationExceptions());
	    }

	    if (getToMergeAssemblyDescriptor().getInterceptorBindings() != null && getToMergeAssemblyDescriptor().getInterceptorBindings().size() > 0){
		      copyMissingContentInBase(getToMergeAssemblyDescriptor().getInterceptorBindings(), getBaseAssemblyDescriptor().getInterceptorBindings());
		}

	    if (getToMergeAssemblyDescriptor().getContainerTransactions() != null && getToMergeAssemblyDescriptor().getContainerTransactions().size() > 0){
		      copyMissingContentInBase(getToMergeAssemblyDescriptor().getContainerTransactions(), getBaseAssemblyDescriptor().getContainerTransactions());
		}

	    if (getToMergeAssemblyDescriptor().getMessageDestinations() != null && getToMergeAssemblyDescriptor().getMessageDestinations().size() > 0){
		      copyMissingContentInBase(getToMergeAssemblyDescriptor().getMessageDestinations(), getBaseAssemblyDescriptor().getMessageDestinations());
		}

	    if (getToMergeAssemblyDescriptor().getMethodPermissions() != null && getToMergeAssemblyDescriptor().getMethodPermissions().size() > 0){
		      copyMissingContentInBase(getToMergeAssemblyDescriptor().getMethodPermissions(), getBaseAssemblyDescriptor().getMethodPermissions());
		}

	    if (getToMergeAssemblyDescriptor().getExcludeList() != null && getToMergeAssemblyDescriptor().getExcludeList().getMethods().size() > 0){
		      copyMissingContentInBase(getToMergeAssemblyDescriptor().getExcludeList().getMethods(), getBaseAssemblyDescriptor().getExcludeList().getMethods());
		}

	  }

	  private void copyMissingContentInBase(List listSource, List target) {
	    for (Object object : listSource) {
	        target.add(EcoreUtil.copy((EObject) object));        
	    }
	  }
}
