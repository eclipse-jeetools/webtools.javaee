package org.eclipse.jst.jee.ui.internal.navigator.ra;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

public class RaGroupContentProvider extends AbstractConnectorGroupProvider {

	private IProject project;
	private Image CONNECTOR_IMAGE;

	public RaGroupContentProvider(JavaEEObject javaee, IProject project) {
		super(javaee);
		this.project = project;
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		children.add(new GroupRaContentProvider(javaee));
		if(((Connector)javaee).getLicense() != null){
			children.add(((Connector)javaee).getLicense());
		}
		return children;
	}

	@Override
	public Image getImage() {
		if (CONNECTOR_IMAGE == null) {
			CONNECTOR_IMAGE = JEEUIPlugin.getDefault().getImage(JEEUIPluginIcons.IMG_CONNECTOR);
		}
		return CONNECTOR_IMAGE;
	}
	
	
	@Override
	public String getText() {
		return NLS.bind(Messages.DEPLOYMENT_DESCRIPTOR, project.getName());
	}

}
