package org.eclipse.jst.validation.sample.parser;
/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002, 2003 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 *
 * DISCLAIMER OF WARRANTIES.
 * The following [enclosed] code is sample code created by IBM
 * Corporation. This sample code is not part of any standard or IBM
 * product and is provided to you solely for the purpose of assisting
 * you in the development of your applications.  The code is provided
 * "AS IS". IBM MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE, REGARDING THE FUNCTION OR PERFORMANCE OF
 * THIS CODE.  THIS CODE MAY CONTAIN ERRORS.  IBM shall not be liable
 * for any damages arising out of your use of the sample code, even
 * if it has been advised of the possibility of such damages.
 * 
 */

import java.io.File;

/**
 * This class is used to perform validation or .properties file queries
 * without using an IValidator. 
 */
public class Main {
	public static void main(String[] argv) {
		if(argv.length < 1) {
			System.out.println("prop -tr [-a | -ac | -am | -an | -al PREFIX | -ap PREFIX | -au | -b | -di | -dp | -l | -le <positive integer> | -p ] f:\\dir1\\dir2\\myPropertiesFile.properties"); //$NON-NLS-1$
			System.out.println("or "); //$NON-NLS-1$
			System.out.println("prop [-c|-ct] f:\\dir1\\dir2\\myPropertiesFile.properties f:\\dir1\\dir2\\myNewPropertiesFile.properties"); //$NON-NLS-1$
			System.out.println("   -a: print all error/warning/info prefixes in the file"); //$NON-NLS-1$
			System.out.println("  -ac: print a count of messages with and without prefixes in the file"); //$NON-NLS-1$
			System.out.println("  -am: print all messages with a prefix"); //$NON-NLS-1$
			System.out.println("  -an: print all messages without an error prefix"); //$NON-NLS-1$
			System.out.println("  -al: print the last error/warning/info prefix, starting with PREFIX, in the file"); //$NON-NLS-1$
			System.out.println("  -ap: print any error/warning/info prefixes, starting with PREFIX, in the file"); //$NON-NLS-1$
			System.out.println("  -au: print all error/warning/info prefixes that are not used in the file"); //$NON-NLS-1$
			System.out.println("   -b: print any blank messages in the properties file"); //$NON-NLS-1$
			System.out.println("   -c: compare the two property files listed. The first is the older, and the second is the newer."); //$NON-NLS-1$
			System.out.println("  -ct: compare the two property files listed. The first is the older, and the second is the newer. Output a list of the added, deleted, and changed strings, but print only the message id."); //$NON-NLS-1$
			System.out.println("  -di: print any duplicate message ids and syntax errors in the properties file"); //$NON-NLS-1$
			System.out.println("  -dp: print any duplicate error/warning/info prefixes in the file"); //$NON-NLS-1$
			System.out.println("   -l: print the length of each message in the file"); //$NON-NLS-1$
			System.out.println("  -le: print the length of each message in the file if each parameter is <positive integer> characters long."); //$NON-NLS-1$
			System.out.println("   -n: print the number of words in the file, including message prefixes and parameters"); //$NON-NLS-1$
			System.out.println("   -p: print the contents of the property file, sorted by message id"); //$NON-NLS-1$
			System.out.println("  -tr: print trace information while parsing the .properties file"); //$NON-NLS-1$
			System.out.println();
			System.out.println("The default action is to print the syntax errors, duplicates, and other types of errors in the given .properties file."); //$NON-NLS-1$
			System.exit(-1);
		}

		// This is not robust error-checking by any stretch of the imagination!
		int index = 0;
		String option = argv[index++].toLowerCase();
		boolean trace = false;
		if(option.equals("-tr")) { //$NON-NLS-1$
			trace = true;
			option = argv[index++].toLowerCase();
		}
		
		if (option.equals("-a") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printAllErrorPrefix(trace, files);
		}
		else if (option.equals("-ac") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printPrefixCount(trace, files);
		}
		else if (option.equals("-al") && (argv.length >= 3)) { //$NON-NLS-1$
			String[] files = new String[argv.length-2];
			System.arraycopy(argv, 2, files, 0, files.length);
			printErrorPrefix(trace, argv[index], files);
		}
		else if (option.equals("-am") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printErrorMessages(trace, files);
		}
		else if (option.equals("-an") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printMessagesWithoutPrefixes(trace, files);
		}
		else if (option.equals("-ap") && (argv.length >= 3)) { //$NON-NLS-1$
			String[] files = new String[argv.length-2];
			System.arraycopy(argv, 2, files, 0, files.length);
			printErrorPrefixRange(trace, argv[index], files);
		}
		else if (option.equals("-au") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printAllMissingErrorPrefix(trace, files);
		}
		else if (option.equals("-b") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printBlanks(trace, files);
		}
		else if (option.equals("-c") && (argv.length == 3)) { //$NON-NLS-1$
			File oldFile = new File(argv[index++]);
			File newFile = new File(argv[index]);
			compareFiles(trace, oldFile, newFile);
		}
		else if (option.equals("-ct") && (argv.length == 3)) { //$NON-NLS-1$
			File oldFile = new File(argv[index++]);
			File newFile = new File(argv[index]);
			compareFilesForTranslation(trace, oldFile, newFile);
		}
		else if (option.equals("-di") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printDuplicateIds(trace, files);
		}
		else if (option.equals("-dp") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printDuplicatePrefixes(trace, files);
		}
		else if (option.equals("-l") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printStringLength(trace, files);
		}
		else if (option.equals("-le") && (argv.length >= 3)) { //$NON-NLS-1$
			String[] files = new String[argv.length-2];
			System.arraycopy(argv, 2, files, 0, files.length);
			printExpectedStringLength(trace, argv[index], files);
		}
		else if (option.equals("-n") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printWordCount(trace, files);
		}
		else if (option.equals("-p") && (argv.length >= 2)) { //$NON-NLS-1$
			String[] files = new String[argv.length-1];
			System.arraycopy(argv, 1, files, 0, files.length);
			printFileContents(trace, files);
		}
		else {
			printDefault(trace, argv);
		}
	}

	public static void printAllErrorPrefix(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printAllMessagePrefixes();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printAllMissingErrorPrefix(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printAllMissingMessagePrefixes();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printBlanks(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printAllMessagesWhichAreBlank();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printMessagesWithoutPrefixes(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printAllMessagesWithoutAMessagePrefix();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void compareFiles(boolean trace, File oldFile, File newFile) {
		if(oldFile.isFile() && newFile.isFile()) {
			try {
				PropertyFile oldPropFile = new PropertyFile(oldFile.getAbsolutePath(), trace);
				PropertyFile newPropFile = new PropertyFile(newFile.getAbsolutePath(), trace);
				CompareProperties cp = new CompareProperties(oldPropFile, newPropFile);
				cp.printResults();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(oldFile.isDirectory() && newFile.isDirectory()) {
			String[] oldChildren = oldFile.list();
			for(int i=0; i<oldChildren.length; i++) {
				File newChild = new File(newFile, oldChildren[i]);
				if(newChild.exists()) {
					// Found a file or directory that is named the same in the old & new. Compare.
					compareFiles(trace, new File(oldFile, oldChildren[i]), newChild);
				}
			}
		}
		else {
			System.out.println("Compare two files or two directories. Do not compare a file to a directory (" + oldFile.getName() + ", " + newFile.getName() + ")."); //$NON-NLS-1$  //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	public static void compareFilesForTranslation(boolean trace, File oldFile, File newFile) {
		if(oldFile.isFile() && newFile.isFile()) {
			try {
				PropertyFile oldPropFile = new PropertyFile(oldFile.getAbsolutePath(), trace);
				PropertyFile newPropFile = new PropertyFile(newFile.getAbsolutePath(), trace);
				CompareProperties cp = new CompareProperties(oldPropFile, newPropFile);
				cp.printResultsForTranslation();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(oldFile.isDirectory() && newFile.isDirectory()) {
			String[] oldChildren = oldFile.list();
			for(int i=0; i<oldChildren.length; i++) {
				File newChild = new File(newFile, oldChildren[i]);
				if(newChild.exists()) {
					// Found a file or directory that is named the same in the old & new. Compare.
					compareFilesForTranslation(trace, new File(oldFile, oldChildren[i]), newChild);
				}
			}
		}
		else {
			System.out.println("Compare two files or two directories. Do not compare a file to a directory (" + oldFile.getName() + ", " + newFile.getName() + ")."); //$NON-NLS-1$  //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	public static void printDuplicateIds(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printDuplicateMessageId();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printErrorPrefix(boolean trace, String prefix, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printLastMessagePrefixStartingWith(prefix);
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printErrorPrefixRange(boolean trace, String prefix, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printMessagePrefixStartingWith(prefix);
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printDuplicatePrefixes(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printDuplicateMessagePrefix();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printStringLength(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printStringLength();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printExpectedStringLength(boolean trace, String slength, String[] files) {
		int length = 0;
		
		try {
			length = new Integer(slength).intValue();
		}
		catch(NumberFormatException exc) {
			System.out.println();
			System.out.println("Parameter must be an integer."); //$NON-NLS-1$
			System.out.println();
			return;
		}
		
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printExpectedStringLength(length);
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printErrorMessages(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printAllMessagesWithAMessagePrefix();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printWordCount(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				System.out.println(propFile.getQualifiedFileName() + ":: Number of words :: " + propFile.getNumWords()); //$NON-NLS-1$
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printPrefixCount(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				System.out.println("Usually the number of unique prefixes and the number of prefixes is the same, but if a .properties file has variations of a message (slightly different wording for a different context), then the number of prefix messages will be larger than the number of unique prefixes."); //$NON-NLS-1$
				System.out.println(propFile.getQualifiedFileName() + ":: Number of unique prefixes :: " + propFile.getNumUniquePrefixes()); //$NON-NLS-1$
				System.out.println(propFile.getQualifiedFileName() + ":: Number of messages with prefixes :: " + propFile.getNumPrefixes()); //$NON-NLS-1$
				System.out.println(propFile.getQualifiedFileName() + ":: Number of messages without prefixes :: " + propFile.getNumWithoutPrefixes()); //$NON-NLS-1$
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public static void printFileContents(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printContents();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printDefault(boolean trace, String[] files) {
		for (int i = 0; i < files.length; i++) {
			try {
				// print all errors, warnings, etc.
				System.out.println("******************************************"); //$NON-NLS-1$
				PropertyFile propFile = new PropertyFile(files[i], trace);
				propFile.printSyntaxWarnings();
				propFile.printDuplicateMessageId();
				propFile.printDuplicateMessagePrefix();
				System.out.println("******************************************"); //$NON-NLS-1$
				System.out.println();
			}
			catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void print(String[] argv) {
		for(int i =0;i<argv.length; i++) {
			System.out.println(i + ". " + argv[i]); //$NON-NLS-1$
		}
	}
}