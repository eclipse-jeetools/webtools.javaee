package org.eclipse.jst.jee.application;

import java.util.List;


public interface ICommonApplication {
	
	public ICommonModule getFirstEARModule(String uri);
	public List getEARModules();

}
