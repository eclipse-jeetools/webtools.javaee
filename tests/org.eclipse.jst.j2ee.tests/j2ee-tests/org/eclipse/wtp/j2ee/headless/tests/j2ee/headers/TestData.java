package org.eclipse.wtp.j2ee.headless.tests.j2ee.headers;

class TestData {
	String fileName;

	int type;

	int modVersion;

	int eeVersion;
	
	boolean deploymentDescriptor;

	Class modelObjectInterface;

	public TestData(String fileName, int type, int modVersion, int eeVersion) {
		this.fileName = fileName;
		this.type = type;
		this.modVersion = modVersion;
		this.eeVersion = eeVersion;
	}

	public TestData(String fileName, int type, int modVersion, int eeVersion, Class modelTypeClass) {
		this(fileName, type, modVersion, eeVersion);
		this.modelObjectInterface = modelTypeClass;
	}
	
	public TestData(String fileName, int type, int modVersion, int eeVersion, Class modelTypeClass,boolean deploymentDescriptor) {
		this(fileName, type, modVersion, eeVersion,modelTypeClass);
		this.deploymentDescriptor = deploymentDescriptor;
	}
	
	public String toString() {
		return fileName + " - " + type + " - " + modVersion + " - " + eeVersion + " - " + deploymentDescriptor + " - " + deploymentDescriptor;
	}
}