package data;

import java.util.Random;

public class GenerateMyAccData {

    public static String videoTitle;
    public static Integer videoCategory;
    public static Integer videoSubCategory;
    public static String videoDescription;
    public static String videoLocation;


    public static void generateMyAccountData(){

        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String candidateChars1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String candidateChars2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
        String candidateChars3 = "1234567890";
        String candidateChars4 = "abcdefghijklmnopqrstuvwxyz1234567890";


        videoTitle = randomAlphabetic(candidateChars2,5);
        //videoCategory = randomAlphabetic("12",1);
        videoCategory = Integer.parseInt(randomAlphabetic("23",1));
        videoSubCategory = Integer.parseInt(randomAlphabetic("12",1));
        videoDescription = randomAlphabetic(candidateChars1,15);
        videoLocation = randomAlphabetic(candidateChars2,1);


        System.out.println("videoTitle " + videoTitle);
        System.out.println("videoCategory  " + videoCategory   );
        System.out.println("videoSubCategory   " + videoSubCategory    );
        System.out.println("videoDescription    " + videoDescription     );
        System.out.println("videoLocation    " + videoLocation     );
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

