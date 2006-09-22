package org.eclipse.jst.j2ee.internal.componentcore;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ArchiveImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class UtilityBinaryComponentHelper extends EnterpriseBinaryComponentHelper {

	public UtilityBinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	protected static class Discriminator extends ArchiveTypeDiscriminatorImpl {

		private static Discriminator instance;

		public static Discriminator getInstance() {
			if (instance == null) {
				instance = new Discriminator();
			}
			return instance;
		}

		public Archive createConvertedArchive() {
			ReferenceCountedArchiveImpl archive = new ReferenceCountedArchiveImpl();
			return archive;
		}

		public boolean canImport(Archive anArchive) {
			return true;
		}

		public ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
			return null;
		}

		public String getUnableToOpenMessage() {
			return "";//$NON-NLS-1$
		}
	}

	protected static class ReferenceCountedArchiveImpl extends ArchiveImpl implements IReferenceCountedArchive {

		private int count = 0;

		public void access() {
			synchronized (this) {
				count++;
			}
		}

		public void close() {
			synchronized (this) {
				count--;
				if (count > 0) {
					return;
				}
			}
			physicallyClose(this);
		}
		
		public void forceClose(){
			count = 0;
			super.close();
		}
	}

	protected ArchiveTypeDiscriminator getDiscriminator() {
		return Discriminator.getInstance();
	}
	
	protected IReferenceCountedArchive getUniqueArchive() {
		try {
			return openArchive();
		} catch (OpenFailureException e) {
		}
		return null;
	}
	
	public EObject getPrimaryRootObject() {
		return null;
	}

}