/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.util.Random;

/**
 * @author blancett
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
public class RandomObjectGenerator {
    protected static RandomObjectGenerator randomObjectGenerator;
    private static Random randomGenerator = new Random();
    private static int maxNumOfLettersInWords = 15;
    private static int maxNumberOfProjectsCreated = 5;
    private static int upperCaseSeed = 65; //ASCII 'A'
    private static int lowerCaseSeed = 97; //ASCII 'a'
    private static int invalidCharSeed = 33;
    private static int numberSeed = 48;
    private static int alphabetRange = 25;
    private static int symbolRange = 15;
    private static int numberRange = 9;

    public static String createCorrectRandomProjectNames() {
        int numOfletterInWord = Math.max(randomGenerator.nextInt(maxNumOfLettersInWords + 1), 2);
        String string = new String();
        for (int i = 0; i < numOfletterInWord; i++) {
            string = string + createRandomCharacterInAlphabetRange();
        }
        return string;
    }

    public static String createRandomNumbers() {
        char randomChar;
        int randomInt = randomGenerator.nextInt(numberRange + 1);
        randomChar = (char) (randomInt + numberSeed);
        return (new Character(randomChar)).toString();
    }

    public static String createRandomInvaildCharacters() {
        char randomChar;
        int randomInt = randomGenerator.nextInt(symbolRange + 1);
        randomChar = (char) (randomInt + invalidCharSeed);
        return (new Character(randomChar)).toString();
    }
    public static int createRandomProjectNumber() {
        return randomGenerator.nextInt(maxNumberOfProjectsCreated + 1);
    }

    public static String createRandomCharacterInAlphabetRange() {
        char randomChar;
        int randomInt = randomGenerator.nextInt(alphabetRange + 1);
        if ((randomInt % 2) == 0)
            randomChar = (char) (randomInt + lowerCaseSeed);
        else
            randomChar = (char) (randomInt + upperCaseSeed);
        return Character.toString(randomChar);
    }

    public static String createInvalidRandomProjectName() {
        int numOfletterInWord = randomGenerator.nextInt(maxNumOfLettersInWords + 1);
        String string = new String();
        for (int i = 0; i < numOfletterInWord; i++) {
            string.concat(createRandomInvaildCharacters());
        }
        return string;
    }

    public static String createCorrectRandomProjectNamesAllChars() {
        int numOfletterInWord = Math.max(randomGenerator.nextInt(maxNumOfLettersInWords + 1), 2);
        String string = new String();
        for (int i = 0; i < numOfletterInWord; i++) {
            if (randomGenerator.nextInt() % 5 == 0) {
                Character c = new Character('$');
                string = string + c.toString();
            }

            if (randomGenerator.nextInt() % 2 == 0) {
                Character c = new Character('_');
                string = string + c.toString();
            }
            string += createRandomCharacterInAlphabetRange();
            if (randomGenerator.nextInt() % 3 == 0)
                string = string + createRandomNumbers();
        }
        return string.substring(0, numOfletterInWord - 1);
    }

    public static RandomObjectGenerator getInstance() {
        if (randomObjectGenerator == null)
            randomObjectGenerator = new RandomObjectGenerator();
        return randomObjectGenerator;
    }

}
