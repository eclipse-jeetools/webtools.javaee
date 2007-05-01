package org.eclipse.jst.jee.archive;

import java.util.HashMap;
import java.util.Map;

public class ArchiveOptions {

	// TODO add debug tracing support

	public static final String LOAD_STRATEGY = "LOAD_STRATEGY"; //$NON-NLS-1$
	
	public static final String SAVE_STRATEGY = "SAVE_STRATEGY"; //$NON-NLS-1$

	public static final String ARCHIVE_HANDLER = "ARCHIVE_HANDLER"; //$NON-NLS-1$

	private Map optionsMap = new HashMap();

	public ArchiveOptions() {
	}

	public boolean hasOption(Object optionKey) {
		return optionsMap.containsKey(optionKey);
	}

	public Object getOption(Object optionKey) {
		return optionsMap.get(optionKey);
	}

	public void setOption(Object optionKey, Object optionValue) {
		optionsMap.put(optionKey, optionValue);
	}

}
