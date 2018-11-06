package com.praveenst.interviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App - Read a gist and then get ERROR, WARNING and INFO counts.
 */
public class AppTest 
{
    public static final String TEST_URL = "https://gist.githubusercontent.com/frnkdny/6ce32d992ec6576548e29312e08fb28b/raw/37252020df292befa7eb99a64d63111cc85da49e/test.log";
    public static URL url;
    public static BufferedReader inputStream;
    public static URLConnection urlConnection;

    @org.junit.BeforeClass
    public static void setupClass(){
        System.out.println("Testing Simple App");
    }

    /**
     * Establish URL connection before each test
     * @throws IOException
     */

    @org.junit.Before
    public void setup() throws IOException{
        try {
            url = new URL(TEST_URL);
            urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();

        } catch(MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

    }

    /**
     *    Test comparing the counts of strings INFO Strings
     *    expected count {'INFO': 50}
     */
    @org.junit.Test
    public void getInfoCount() {
        String word = "INFO";
        assertEquals(50, getWordCount(word));

    }

    /**
     *    Test comparing the counts of strings INFO Strings
     *    expected count {'ERROR': 3}
     */
    @org.junit.Test
    public void getErrorCount() {
        String word = "ERROR";
        assertEquals(3, getWordCount(word));
    }

    /**
     *    Test comparing the counts of strings INFO Strings
     *    expected count {'WARNING': 11}
     */
    @org.junit.Test
    public void getWarningCount() {
        String word = "WARNING";
        assertEquals(11, getWordCount(word));
    }

    /**
     * Function to take a word and return number of occurences of that word
     * fetching URL - test gist URL
     * @param word
     * @return
     */
    private int getWordCount(String word) {
        int infoCount = 0;
        String line = "";
        try {
            inputStream = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            while(line != null) {
                line = inputStream.readLine();
                if(line != null && line.contains(word))
                    infoCount++;
            }


        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        return infoCount;
    }

    /**
     *  force close URL connection by setting timeout 0
     */
    @org.junit.After
    public void tearDown() {
        urlConnection.setConnectTimeout(0);
    }

    /**
     * Cleanup any left over resources after all tests finishes.
     * @throws IOException
     */

    @org.junit.AfterClass
    public static void cleanupTests() throws IOException {
        inputStream.close();
    }
}
