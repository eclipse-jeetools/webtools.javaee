/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  Created Apr 27, 2005 by Gili Mendel
 * 
 *  $RCSfile: OverlayComposite.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/02 21:36:10 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import java.util.*;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
 
//TODO:  This class was dropped in 3.1 M7 from org.eclipse.ui.internal.misc
//       We made  local copy for the time being.  Need to investigate if this is file
//       for us given that the OverlayIcon (replacement) is internal
public class OverlayComposite extends CompositeImageDescriptor {

    private ImageData backgroundImage;

    private ImageData leftImage;

    private ImageData rightImage;

    private ImageData topImage;

    private ImageData bottomImage;

    private List foregroundImages = new ArrayList();

    /**
     *	Create an instance of this class.
     */
    public OverlayComposite(ImageData background) {
        backgroundImage = background;
    }

    /**
     *	Add the passed image to this descriptor's collection of images to 
     * be composed together to create an image.
     */
    public void addForegroundImage(ImageData image) {
        foregroundImages.add(image);
    }

    /**
     *Superimpose self's images within the given bounds by means of #drawImage
     *
     *@see CompositeImage#drawImage(ImageData src,int ox,int oy)
     */
    protected void drawCompositeImage(int width, int height) {
        //draw background
        drawImage(backgroundImage, getLeftBound(), getTopBound());

        //draw foreground images
        Iterator e = foregroundImages.iterator();
        while (e.hasNext())
            drawImage(((ImageData) e.next()), getLeftBound(), getTopBound());

        //draw extensions
        if (topImage != null)
            drawImage(topImage, getLeftBound(), 0);
        if (bottomImage != null)
            drawImage(bottomImage, getLeftBound(), height - bottomImage.height);
        if (leftImage != null)
            drawImage(leftImage, 0, getTopBound());
        if (rightImage != null)
            drawImage(rightImage, width - rightImage.width, getTopBound());

    }

    /**
     * @see Object#equals
     */
    public boolean equals(Object o) {
        if (!(o instanceof OverlayComposite)) {
            return false;
        }
        OverlayComposite other = (OverlayComposite) o;

        return equals(backgroundImage, other.backgroundImage)
                && equals(leftImage, other.leftImage)
                && equals(rightImage, other.rightImage)
                && equals(topImage, other.topImage)
                && equals(bottomImage, other.bottomImage)
                && equals(foregroundImages, other.foregroundImages);
    }

    /**
     * Private utility for comparing two possibly-null objects.
     */
    private boolean equals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    /**
     *	Answer the left-most coordinate that the main image can draw to
     *
     *	@return int
     */
    protected int getLeftBound() {
        if (leftImage == null)
            return 0;

        return leftImage.width;
    }

    /**
     *	Answer self's size, as determined by the size of the initially-
     *	provided base-level image
     */
    protected Point getSize() {
        //start with basic size
        Point size = new Point(backgroundImage.width, backgroundImage.height);

        //overlays may increase size.
        if (topImage != null)
            size.y += topImage.height;

        if (bottomImage != null)
            size.y += bottomImage.height;

        if (leftImage != null)
            size.x += leftImage.width;

        if (rightImage != null)
            size.x += rightImage.width;

        return size;
    }

    /**
     *	Answer the top-most coordinate that the main image can draw to
     *
     *	@return int
     */
    protected int getTopBound() {
        if (topImage == null)
            return 0;

        return topImage.height;
    }

    /**
     * @see Object#hashCode
     */
    public int hashCode() {
        return hashCode(backgroundImage) + hashCode(leftImage)
                + hashCode(rightImage) + hashCode(topImage)
                + hashCode(bottomImage) + hashCode(foregroundImages);
    }

    /**
     * Private utility for getting the hashCode for an
     * object that may be null.
     */
    private int hashCode(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    /**
     *  Set the image to be drawn below the primary overlay region.
     */
    public void setBottomExtension(ImageData value) {
        bottomImage = value;
    }

    /**
     *  Set the image to be drawn to the left of the primary overlay region.
     */
    public void setLeftExtension(ImageData value) {
        leftImage = value;
    }

    /**
     *  Set the image to be drawn to the right of the primary overlay region.
     */
    public void setRightExtension(ImageData value) {
        rightImage = value;
    }

    /**
     *  Set the image to be drawn above the primary overlay region.
     */
    public void setTopExtension(ImageData value) {
        topImage = value;
    }

}
