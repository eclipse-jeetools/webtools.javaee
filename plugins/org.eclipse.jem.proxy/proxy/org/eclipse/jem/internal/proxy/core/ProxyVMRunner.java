package org.eclipse.jem.internal.proxy.core;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.internal.launching.StandardVMRunner;
import org.eclipse.jdt.launching.IVMInstall;

public class ProxyVMRunner extends StandardVMRunner {
	
	public String[] fEnvironmentVariables;

	public ProxyVMRunner(IVMInstall vmInstance) {
		super(vmInstance); 
		// TODO Remove this guy when we step up to Eclipse 3.0 because that version can handle environment variables.
		// Need to remove ProxyPlugin.exec() and ProxyPlugin.ENVIRONMENTVARIABLE too.
	}
	
	protected Process exec(String[] cmdLine, File workingDirectory) throws CoreException {
		return ProxyPlugin.exec(cmdLine,workingDirectory,fEnvironmentVariables);
	}

}
