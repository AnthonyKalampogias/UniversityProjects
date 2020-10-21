package BasicClasses;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.stream.Stream;

public class Admin extends Seller {

    /**
     * Creating Admin
     */
    protected void CreateAdmin(){
        System.out.println("\nCreating Admin...\n");

    }

    /**
     * Creating Seller
     */
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";                                   //the correct driver (at least for my pc)
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/app?serverTimezone=UTC";   //The "ProgInTheWeb" is the database name, so change accordingly


public static boolean CreateSeller(String usrName, String password, String FName, String LName,String date,String type)
    {

        String salt,salted_hash;

        SecureRandom random = new SecureRandom();
        byte[] randomSalt = new byte[20];
        random.nextBytes(randomSalt);

        salt = randomSalt.toString();
        salted_hash = Encryption.getHashMD5(password,salt);

        int usrIDofCustomer = 0;

        //Create user
        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("insert into users (UserName, salt,salted_hash, Type) values (?, ?, ?, ?);");

            statement.setString(1, usrName);    //Passes first parameter

            statement.setString(2, salt);   //Passes second parameter

            statement.setString(3, salted_hash);       //Passes third parameter

            statement.setString(4, type);       //Passes fourth parameter


            statement.executeUpdate();

            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        //Create Customer/Seller
        //I need the UserID so i search the DB and find the newly created user

        try
        {
            usrIDofCustomer = ConnectToDB.getUserID(usrName); //gets the userID

            Connection con2 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement1 = con2.prepareStatement("insert into sellers (FirstName, LastName,JoinedWhen, UserID) values (?, ?, ?, ?);");

            statement1.setString(1, FName);     //Passes first parameter

            statement1.setString(2, LName);     //Passes second parameter

            statement1.setString(3, date);          //Passes third parameter

            statement1.setInt(4, usrIDofCustomer);



            statement1.executeUpdate();

            con2.close();
        }
        catch (Exception e)
        {
            System.out.println(e);

            if(ConnectToDB.deleteSeller(ConnectToDB.getSellerIDByUsrID(usrIDofCustomer)))
            {
                System.out.println("An error occurred, we had to delete the corrupt data from the sellers table");
            }
            else{
                System.out.println("Could not delete data");
            }
            if(ConnectToDB.deleteUser(usrIDofCustomer))
            {
                System.out.println("An error occurred, we had to delete the corrupt data from the users table");
            }
            else{
                System.out.println("Could not delete data");
            }

            return false;
        }

        //the SellerID is needed to add the program to the soldplans table
        return true;

    }



    public static void createPlan(String ProgramName, String ChargePerMin, String ChargePerMB, String ChargePerSMS, String MinutesToTalk, String MBtoSpend, String smsToSpend)
    {
        try
        {

            Class.forName (DATABASE_DRIVER);

            Connection con2 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement1 = con2.prepareStatement("insert into plans (ProgramName, ChargePerMin, ChargePerMB, ChargePerSMS, MinutesToTalk, MBtoSpend, smsToSpend) values (?, ?, ?, ?, ?, ?, ?);");

            statement1.setString(1, ProgramName);     //Passes first parameter

            statement1.setString(2, ChargePerMin);

            statement1.setString(3, ChargePerMB);

            statement1.setString(4, ChargePerSMS);

            statement1.setString(5, MinutesToTalk);

            statement1.setString(6, MBtoSpend);

            statement1.setString(7, smsToSpend);

            statement1.executeUpdate();

            con2.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    protected void ViewActiveUsers(){
        System.out.println("Counting all Users...\n"+usersCounter);
    }

}


