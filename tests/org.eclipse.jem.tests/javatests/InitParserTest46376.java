/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: InitParserTest46376.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 23:00:16 $ 
 */
 
/**
 * This is to test for defect [46376].
 * 
 * SameName test. This is test where you have this:
 * 	initParserTest46376.SameNameTestClass.java
 * 	InitParserTest46376.java
 *  
 * Before [46376] the Static parser would find throw NoClassDefFoundError on initParserTest46376 when looking for
 * SameNameTestClass and never find the class.
 * 
 * To compile in Eclipse we need to have one of the classes be in the default package. Eclipse complains if we didn't.
 * But there is nothing to stop this from happening with packages too if they are spread across compile groups.  
 */
public class InitParserTest46376 {

}
