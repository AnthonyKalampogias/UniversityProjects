package BasicClasses;

import java.security.SecureRandom;
import java.sql.*;

public class Seller extends Users
{
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";                                   //the correct driver (at least for my pc)

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/app?serverTimezone=UTC";
    // The timezone is needed (at least for my pc) because the server wont run

    private String firstName, lastName;
    private int SellerID, UserID;

    public Seller()
    {

    }

    Seller(int SellerID, String firstName, String lastName, int UserID)
    {
        this.SellerID = SellerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.UserID = UserID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getSellerID()
    {
        return SellerID;
    }

    public void setSellerID(int customerID)
    {
        this.SellerID = SellerID;
    }

    public int getUserID()
    {
        return UserID;
    }

    public void setUserID(int userID)
    {
        this.UserID = userID;
    }

    /**
     * Creating Client by Seller
     */
    public static boolean CreateClientAccount(String usrName, String password, String type, String FName, String LName, int AFM, String programName,String date)
    {

//        Users NewClient = new Users();
//        NewClient.Register(usrName,password,type,FName,LName,AFM,programName,date);

        //First creates user (so the Customer/Seller can have the UserID (its required) )
        //Then creates the Customer/Seller and provides the newly created UserID
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

            return false;
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
            if(ConnectToDB.deleteCustomer(customerID))
            {
                System.out.println("An error occurred, we had to delete the corrupt data from the users table");
            }
            else{
                System.out.println("Could not delete data");
            }
            if(ConnectToDB.deleteUser(usrIDofCustomer))
            {
                System.out.println("An error occurred, we had to delete the corrupt data from the customers table");
            }
            else{
                System.out.println("Could not delete data");
            }

            return false;
        }

        return true;

    }



    public static boolean UpdateClienBillProgram(String firstName, String lastName, String programName){

        try
        {
            int CustomerID, PlanID;

            CustomerID = ConnectToDB.getCustomerID(firstName, lastName);

            PlanID = ConnectToDB.getPlanID(programName);

            Class.forName (DATABASE_DRIVER);

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("update soldplans set PlanID = ? where CustomerID = ?;");

            statement.setString(1, Integer.toString(PlanID));    //Passes first parameter

            statement.setString(2, Integer.toString(CustomerID));   //Passes second parameter

            statement.executeUpdate();

            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;

    }


    public static void CreateClientBillProgram(String username, String password, String firstName, String lastName, String programName, int sellerID, String name)
    {

        int planID = ConnectToDB.getPlanID(programName);

        System.out.println(planID);

        int customerID = ConnectToDB.getCustomerID(firstName, lastName);

        System.out.println(customerID);

        System.out.println(sellerID);

        try
        {

            Class.forName (DATABASE_DRIVER);

            Connection con2 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement1 = con2.prepareStatement("insert into soldplans (PlanID, CustomerID, SellerID) values (?, ?, ?);");

            statement1.setInt(1, planID);     //Passes first parameter

            statement1.setInt(2, customerID);     //Passes second parameter

            statement1.setInt(3, sellerID);          //Passes third parameter

            statement1.executeUpdate();

            con2.close();


        }
        catch (Exception e)
        {

            System.out.println(e);
        }
    }


    public static boolean issueAccountBill(String customerFirstName, String customerLastName)
    {
        int CustomerID, PlanID, SellerID, ChargePerMin, ChargePerMB, ChargePerSMS, overChargePerMin, overChargePerMB, overChargePerSMS, TotalMinutes, TotalSMS, TotalMB, MoneyToPay;
        int isPaid = 0;
        try
        {
            //The first query returns the CustomerID, PlanID, SellerID, ChargePerMin, ChargePerMB, ChargePerSMS, overChargePerMin, overChargePerMB, overChargePerSMS

            Class.forName (DATABASE_DRIVER);

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties());

            PreparedStatement statement = con.prepareStatement("select customers.CustomerID, soldplans.PlanID, soldplans.SellerID,plans.ChargePerMin, " +
                    "plans.ChargePerMB, plans.ChargePerSMS " +
                    "from customers " +
                    "left join soldplans " +
                    "on soldplans.CustomerID = customers.CustomerID " +
                    "left join callsmade " +
                    "on callsmade.CustomerID = customers.CustomerID " +
                    "left join mbspent " +
                    "on mbspent.CustomerID = customers.CustomerID " +
                    "left join smssent " +
                    "on smssent.CustomerID = customers.CustomerID " +
                    "left join plans " +
                    "on plans.PlanID = soldplans.PlanID " +
                    "where customers.FirstName = ? and LastName = ? ;");

            statement.setString(1, customerFirstName);    //Passes first parameter

            statement.setString(2, customerLastName);   //Passes second parameter

            ResultSet resultSet = statement.executeQuery();  //Executes the query

            resultSet.next();

            CustomerID = resultSet.getInt(1);

            PlanID = resultSet.getInt(2);

            SellerID = resultSet.getInt(3);

            ChargePerMin = resultSet.getInt(4);

            ChargePerMB = resultSet.getInt(5);

            ChargePerSMS = resultSet.getInt(6);


            con.close();

            //The next query returns the sum of all the minutes, sms and MB the client has consumed

            Connection con1 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties());

            PreparedStatement statement1 = con1.prepareStatement("select customers.CustomerID, SUM(callsmade.Duration) as TotalMinutes, " +
                    "SUM(mbspent.HowMany) as MBspent, " +
                    "SUM(smssent.howMany) as SMSspent " +
                    "from customers " +
                    "left join callsmade " +
                    "on callsmade.CustomerID = customers.CustomerID " +
                    "left join mbspent " +
                    "on mbspent.CustomerID = customers.CustomerID " +
                    "left join smssent " +
                    "on smssent.CustomerID = customers.CustomerID " +
                    "where  customers.FirstName = ? and LastName = ? group by CustomerID;");

            statement1.setString(1, customerFirstName);    //Passes first parameter

            statement1.setString(2, customerLastName);   //Passes second parameter

            ResultSet resultSet1 = statement1.executeQuery();  //Executes the query

            resultSet1.next();

            TotalMinutes = resultSet1.getInt(2);

            TotalMB = resultSet1.getInt(3);

            TotalSMS = resultSet1.getInt(4);

            con1.close();

            MoneyToPay = (TotalMinutes * ChargePerMin) + (TotalMB * ChargePerMB) + (TotalSMS * ChargePerSMS);

            //The third just inserts into the bills table the money to be paid, along side the PlanID, CustomerID, SellerID and if the bill is paid (which is no by default)

            Connection con2 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties());

            PreparedStatement statement2 = con2.prepareStatement("insert into bills (PlanID, CustomerID, SellerID, isPaid, MoneyToPay) values (?, ?, ?, ? ,?);");

            statement2.setInt(1, PlanID);

            statement2.setInt(2, CustomerID);

            statement2.setInt(3, SellerID);

            statement2.setInt(4, isPaid);

            statement2.setInt(5, MoneyToPay);

            statement2.executeUpdate();

            con2.close();


        }


        catch(Exception e)
        {
            System.out.println(e);
            return false;

        }

        return true;


    }


    @Override
    public String toString() {
        return SellerID + " " + firstName + " " + lastName + " " + UserID;
    }

}
