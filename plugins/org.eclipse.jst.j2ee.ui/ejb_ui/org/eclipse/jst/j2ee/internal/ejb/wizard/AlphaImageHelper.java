/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class AlphaImageHelper {

	static protected Object createAlphaImage(Class clazz, String path) {
		ImageDescriptor desc = ImageDescriptor.createFromFile(clazz, path);
		Image image = desc.createImage();
		ImageData mask = image.getImageData();
		image.dispose();
		image = new Image(null, mask.width, mask.height);
		ImageData iData = image.getImageData();
		image.dispose();

		for (int x = 0; x < mask.width; x++)
			for (int y = 0; y < mask.height; y++) {
				int pixel = mask.getPixel(x, y);
				int value = (255 - pixel % 256) / 2;
				iData.setPixel(x, y, 0);
				iData.setAlpha(x, y, value);
			}
		return new Image(null, iData);
	}

}