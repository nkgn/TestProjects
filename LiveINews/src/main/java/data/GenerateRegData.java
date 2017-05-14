package data;

import java.util.Random;

public class GenerateRegData {

    public static Integer accountType;
    public static String email;
    public static String password;
    public static String accountName;
    public static String firstName;
    public static String lastName;
    public static String address;
    public static String phone;
    public static Integer country;
    public static Integer state;
    public static Integer city;
    public static String zipCode;
    public static String paypal;
    public static String identity;
    public static String contact;
    public static String legalContact;
    public static String companyName;


    public static void generateRegistrationData(){

        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String candidateChars1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String candidateChars2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
        String candidateChars3 = "1234567890";
        String candidateChars4 = "abcdefghijklmnopqrstuvwxyz1234567890";


        accountName = randomAlphabetic(candidateChars2,5);
        //accountName ="JNitu";
        firstName = randomAlphabetic(candidateChars2,5);
        lastName = randomAlphabetic(candidateChars2,5);
        address = randomAlphabetic(candidateChars1,15);
        password = randomAlphabetic(candidateChars1,6);
        //password="j_nitu";
        companyName= randomAlphabetic(candidateChars1,10);
        //email = randomAlphabetic(candidateChars4,5) + "@getnada.com";
        email = randomAlphabetic(candidateChars4,5);// + "@mailinator.com";


        //phone = randomAlphabetic("0123456789",10);

        String phone1=randomAlphabetic("0123456789",3);
        String phone2=randomAlphabetic("0123456789",3);
        String phone3=randomAlphabetic("0123456789",4);
        phone= phone1 + "-" + phone2 + "-" + phone3;

        zipCode = randomAlphabetic(candidateChars3,5) ;
        paypal = randomAlphabetic(candidateChars4,5) + "@getnada.com";

        accountType = Integer.parseInt(randomAlphabetic("0123",1));
        country = Integer.parseInt(randomAlphabetic("123",1));
        state = Integer.parseInt(randomAlphabetic("12",1));
        city = Integer.parseInt(randomAlphabetic("12",1));

        String identity1 =randomAlphabetic("1234567890",2) ;
        String identity2 =randomAlphabetic("0123456789",7) ;
        identity = identity1 + "-" + identity2 ;
        contact =randomAlphabetic(candidateChars1,12) ;
        legalContact =  randomAlphabetic(candidateChars1,12) ;
        System.out.println("accountName " + accountName);
        System.out.println("firstName  " + firstName   );
        System.out.println("lastName   " + lastName    );
        System.out.println("address    " + address     );
        System.out.println("password   " + password    );
        System.out.println("email      " + email       );
        System.out.println("phone      " + phone       );
        System.out.println("zipCode    " + zipCode     );
        System.out.println("paypal     " + paypal      );
        System.out.println("accountType " + accountType);
        System.out.println("country    " + country     );
        System.out.println("state      " + state       );
        System.out.println("city       " + city        );
        System.out.println("identity       " + identity        );
        System.out.println("contact       " + contact        );
        System.out.println("legal contact       " + legalContact        );
        System.out.println("Company name       " + companyName        );
    }
    public static  String randomAlphabetic(String candidateChars1,int inum){

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < inum; i++) {
            sb.append(candidateChars1.charAt(random.nextInt(candidateChars1
                    .length())));
        }
        return sb.toString();

    }

}

