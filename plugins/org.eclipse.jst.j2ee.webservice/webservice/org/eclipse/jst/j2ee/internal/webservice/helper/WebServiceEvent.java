package org.eclipse.jst.j2ee.internal.webservice.helper;

public class WebServiceEvent {

	public static final int REFRESH = 1;
	
	private int eventType = 0;
	
	public WebServiceEvent(int anEventType) {
		super();
		eventType = anEventType;
	}
	
	public int getEventType() {
		return eventType;
	}

}
