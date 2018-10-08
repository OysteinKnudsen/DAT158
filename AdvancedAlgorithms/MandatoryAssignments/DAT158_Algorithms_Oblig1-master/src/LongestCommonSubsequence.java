import java.util.Random;
import java.util.stream.Collectors;


public class LongestCommonSubsequence {

    /* A subsequence is a sequence that can be derived from another sequence
    * By deleting some or no elements without changing the order of the remaining elements*/

    private static int computeLongestCommonSubsequenceDynamically(String firstString, String secondString){

        int lengthOfFirstString = firstString.length();
        int lengthOfSecondString = secondString.length();
        int[][] dp = new int[lengthOfFirstString+1][lengthOfSecondString+1];

        for(int i=0; i<=lengthOfFirstString; i++){
            for(int j=0; j<=lengthOfSecondString; j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }else if(firstString.charAt(i-1)==secondString.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[lengthOfFirstString][lengthOfSecondString];


    }

    private static String computeLongestCommonSubsequenceRecursively(String firstString, String secondString){
        int aLen = firstString.length();
        int bLen = secondString.length();
        if(aLen == 0 || bLen == 0){
            return "";
        }else if(firstString.charAt(aLen-1) == secondString.charAt(bLen-1)){
            return computeLongestCommonSubsequenceRecursively(firstString.substring(0,aLen-1),secondString.substring(0,bLen-1))
                    + firstString.charAt(aLen-1);
        }else{
            String x = computeLongestCommonSubsequenceRecursively(firstString, secondString.substring(0,bLen-1));
            String y = computeLongestCommonSubsequenceRecursively(firstString.substring(0,aLen-1), secondString);
            return (x.length() > y.length()) ? x : y;
        }
    }

    private static String generateString(int length){

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";
        String str = new Random().ints(length, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());

        return str;
    }
    private static void showDynamicResults(String firstString, String secondString){
        long startTime = System.nanoTime();
        int longestCommonSequence = computeLongestCommonSubsequenceDynamically(firstString,secondString);
        long stopTime = System.nanoTime();
        long timeElapsedMS = (stopTime-startTime)/1000000;
        String statement = "Results of dynamic solution to LCS \n" + "Length of first string: " + firstString.length() + "\n" +
                "Length of second string: " + secondString.length() + "\n" +
                "The longest common subsequence is: " + longestCommonSequence + " characters long" + "\n"+
                "Time taken to compute the sequence: " + timeElapsedMS + " ms \n";
        System.out.println(statement);

    }
    private static void showRecursiveResults(String firstString, String secondString){
        long startTime = System.nanoTime();
        String commonSequence =  computeLongestCommonSubsequenceRecursively(firstString,secondString);
        int longestCommonSequence = commonSequence.length();
        long stopTime = System.nanoTime();
        long timeElapsedMS = (stopTime-startTime)/1000000;
        String statement = "Results of recursive solution to LCS \n" + "Length of first string: " + firstString.length() + "\n" +
                "Length of second string: " + secondString.length() + "\n" +
                "The longest common subsequence is: " + longestCommonSequence + " characters long" + "\n"+
                "Time taken to compute the sequence: " + timeElapsedMS + " ms \n";
        System.out.println(statement);
    }
    private static void executeTest(int startingLength, int endingLength, int rate){
        for (int i = startingLength; i < endingLength; i += rate){
            String firstString = generateString(i);
            String secondString = generateString(i);
            showDynamicResults(firstString, secondString);
            showRecursiveResults(firstString, secondString);
        }


    }
    public static void main (String[]args){
    executeTest(5,50,5);
    }
}
