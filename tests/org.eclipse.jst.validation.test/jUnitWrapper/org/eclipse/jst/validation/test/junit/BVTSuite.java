package org.eclipse.jst.validation.test.junit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.internal.registry.OperationTestcase;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.internal.util.BVTValidationUtility;
import org.eclipse.wst.validation.internal.ValidatorMetaData;

/**
 * Wraps the existing validation bvt code in a JUnit artifact
 * so that the tests can be run within JUnit. Instances of this
 * class wrap all of the ValidatorSuite instances that must be run.
 */
public class BVTSuite extends TestSuite {
	private Map _validatorSuites = null;
	
	public BVTSuite() {
		_validatorSuites = new HashMap();
		loadValidatorSuites();
		loadOperationSuites();
	}
	
	private void loadValidatorSuites() {
		IProgressMonitor monitor = new NullProgressMonitor();
		
		ValidatorTestcase[] tmds = BVTValidationUtility.getValidatorTests(monitor);
		for(int i=0; i<tmds.length; i++) {
			ValidatorTestcase tmd = tmds[i];

			if(tmd.isVisible()) {
				ValidatorMetaData vmd = tmd.getValidatorMetaData();
				ValidatorSuite vs = (ValidatorSuite)_validatorSuites.get(vmd);
				if(vs == null) {
					vs = new ValidatorSuite(vmd);
				}
				ValidatorTest vt = new ValidatorTest(tmd, vs);
				vs.addTest(vt);
				_validatorSuites.put(vmd, vs);
			}
		}
		
		Iterator iterator = _validatorSuites.values().iterator();
		while(iterator.hasNext()) {
			ValidatorSuite vs = (ValidatorSuite)iterator.next();
			addTest(vs);
		}
	}
	
	private void loadOperationSuites() {
		IProgressMonitor monitor = new NullProgressMonitor();
		
		OperationTestcase[] tmds = BVTValidationUtility.getOperationTests(monitor);
		OperationSuite os = new OperationSuite();
		for(int i=0; i<tmds.length; i++) {
			OperationTestcase tmd = tmds[i];

			if(tmd.isVisible()) {
				OperationTest ot = new OperationTest(tmd, os);
				os.addTest(ot);
			}
		}
		
		addTest(os);
	}

	public static Test suite() {
		// Turn logging to SEVERE
		BVTValidationPlugin.getPlugin().getMsgLogger().setLevel(Level.SEVERE);
		return new BVTSuite();
	}

	public String toString() {
		return "Validation BVT Suite"; //$NON-NLS-1$
	}
}
