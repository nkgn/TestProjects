package data;

import java.util.Random;

public class GenerateData {


    public static String projectTitle;
    public static String projectDesc;

    public static void generateMyAccountData(){

        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String s2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String s3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";

        String s4 = "abcdefghijklmnopqrstuvwxyz1234567890";


        projectTitle = randomAlphabetic(s1,5);
        //projectDesc = randomAlphabetic(s1,5);


    }
    public static  String randomAlphabetic(String candidateChars1,int inum){

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < inum; i++) {
            sb.append(candidateChars1.charAt(random.nextInt(candidateChars1.length())));
        }
        return sb.toString();

    }

}

