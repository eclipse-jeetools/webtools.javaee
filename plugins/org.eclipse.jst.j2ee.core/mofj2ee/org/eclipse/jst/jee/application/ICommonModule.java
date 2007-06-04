package org.eclipse.jst.jee.application;

public interface ICommonModule {
	
	void setUri(String value);

	String getUri();
	
	/**
	 * Returns boolean value if this Module represents a ConnectorModule
	 * @return boolean value
	 */
	public boolean isConnectorModule();
	
	/**
	 * Returns boolean value if this Module represents a EjbModule
	 * @return boolean value
	 */
	public boolean isEjbModule();
	
	/**
	 * Returns boolean value if this Module represents a JavaClientModule(ApplicationClient)
	 * @return boolean value
	 */
	public boolean isJavaModule();
	
	/**
	 * Returns boolean value if this Module represents a WebModule
	 * @return boolean value
	 */
	public boolean isWebModule();
}
