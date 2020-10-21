package BasicClasses;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;


public class Users
{
    private String username,name,surname,type, salt,salted_hash;                                                                  // Type is the property of user(ex. client, seller, administrator)

    int usrID;

    public static int usersCounter = 0;                                                                                   //counter to count the registration of users

    private ArrayList<String> userProperties = new ArrayList<>();

    public static String UserIDofSellerOrAdminOrCustomer;
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";                                   //the correct driver (at least for my pc)

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/app?serverTimezone=UTC";

    /**
     * User (be it admin, seller or customer) logs in.
     * The input is passed to the Users.logIn method which checks if the user is in the DB, if he is it returns an arraylist where
     * the first element is the type of the user, and the second is the UserID.
     * */


    public ArrayList<String> Login(String username, String password){
        String hash="";
        String salt="";
        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            PreparedStatement statement = con.prepareStatement("select * from users where Username = ?");

            statement.setString(1, username);    //Passes first parameter

            ResultSet rs = statement.executeQuery();  //Executes the query

            if(rs.next())
            {
                salt = rs.getString(3);
                hash = rs.getString(4);
            }
            else
            {
                return userProperties;
            }
            if(Encryption.getHashMD5(password,salt).equals(hash)){
                userProperties.add(ConnectToDB.getUserType(username));
                userProperties.add(Integer.toString(ConnectToDB.getUserID(username)));
                UserIDofSellerOrAdminOrCustomer = userProperties.get(1);
            }
            else{
                return userProperties;
            }



            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return userProperties;
        }
        return userProperties;
    }

    /**
     * Count the number of users that are getting register
     */
    public Users(){
        //usersCounter ++; //With every registration the counter increase by one
    }

    public Users(int userID, String usrName, String salt,String salted_hash, String Type)
    {
        usersCounter++;
        this.username = usrName;
        this.salt = salt;
        this.salted_hash = salted_hash;
        this.type = Type;
        this.usrID = userID;
    }

    /**
     * A form where a user can sign up
     * @param type Type is the property of user(ex. client, seller, administrator).Type is imported from either a Seller or Admin
     */
    public void Register(String usrName, String password, String type, String FName, String LName, int AFM, String programName,String date)
    {
        //First creates user (so the Customer/Seller can have the UserID (its required) )
        //Then creates the Customer/Seller and provides the newly created UserID
        String salt,salted_hash;

        SecureRandom random = new SecureRandom();
        byte[] randomSalt = new byte[20];
        random.nextBytes(randomSalt);

        salt = randomSalt.toString();
        salted_hash = Encryption.getHashMD5(password,salt);

        int usrIDofCustomer;

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
        }

        //Create Customer/Seller
        //I need the UserID so i search the DB and find the newly created user

        try
        {
            usrIDofCustomer = ConnectToDB.getUserID(usrName); //gets the userID

            Connection con2 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement1 = con2.prepareStatement("insert into customers (FirstName, LastName, AFM,JoinedWhen, UserID) values (?, ?, ?, ?, ?);");

            statement1.setString(1, FName);     //Passes first parameter

            statement1.setString(2, LName);     //Passes second parameter

            statement1.setInt(3, AFM);          //Passes third parameter

            statement1.setString(4, date);

            statement1.setInt(5, usrIDofCustomer);

            statement1.executeUpdate();

            con2.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        int planID = ConnectToDB.getPlanID(programName);

        System.out.println(planID);

        int customerID = ConnectToDB.getCustomerID(FName, LName);

        System.out.println(customerID);


        try
        {

            Connection con2 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement1 = con2.prepareStatement("insert into soldplans (PlanID, CustomerID, SellerID) values (?, ?, ?);");

            statement1.setInt(1, planID);     //Passes first parameter

            statement1.setInt(2, customerID);     //Passes second parameter

            statement1.setInt(3, 1);          //Passes third parameter

            statement1.executeUpdate();

            con2.close();

            System.out.println("Succesfully Created new client bill");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong!");
            System.out.println(e);
        }

        //the SellerID is needed to add the program to the soldplans table


    }


    @Override
    public String toString() {
        return usrID + " " + username + " " + salt+" "+salted_hash + " " + type;
    }


    public void logout()
    {
        System.out.println("You logged out" + "\n");
    }


}
