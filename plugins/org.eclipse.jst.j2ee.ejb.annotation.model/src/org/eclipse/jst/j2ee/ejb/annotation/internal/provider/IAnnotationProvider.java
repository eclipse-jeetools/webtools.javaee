/**
 * 
 */
package org.eclipse.jst.j2ee.ejb.annotation.internal.provider;

/**
 * @author naci
 *
 */
public interface IAnnotationProvider {
	
	public boolean isEjbAnnotationProvider();
	public boolean isServletAnnotationProvider();
	public boolean isWebServiceAnnotationProvider();
	public boolean isValid();
	public String  getName();

}
