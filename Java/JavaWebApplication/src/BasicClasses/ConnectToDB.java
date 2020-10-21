package BasicClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class ConnectToDB
{
    
    //Create connection to the DB
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";                                   //the correct driver (at least for my pc)
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/app?serverTimezone=UTC";   //The "ProgInTheWeb" is the database name, so change accordingly
    // The timezone is needed (at least for my pc) because the server wont run
    private static final String USERNAME = "root";                                                              // the usrName of your database (the one you gave during the installation)
    private static final String PASSWORD = "samplePassPhrase1234!";                                            //the password you gave during the installation
    public ArrayList<Program> programs = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<>();
    public ArrayList<Seller> sellers = new ArrayList<>();
    public ArrayList<Users> usersArrayList = new ArrayList<>();
    public ArrayList<String> programsName;


    // init properties object

    private static Properties properties;

    public ConnectToDB()
    {

    }

    // create properties

    static Properties getProperties()   //helper function to set the different properties like userName, pass etc
    {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);

        }
        return properties;
    }

    public static boolean IsDBAlive()       //Tries to connect to the DB, if it can, returns true. Else it returns false
    {
        try
        {
            Class.forName (DATABASE_DRIVER);
            Connection con = DriverManager.getConnection(DATABASE_URL,getProperties());
            con.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }

    }

    public void loadUsers()
    {
        try
        {
            Class.forName (DATABASE_DRIVER);

            Connection con = DriverManager.getConnection(DATABASE_URL,getProperties());

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from users;");

            while(rs.next())
            {
                String usrName, salt,salted_hash, type;
                int usrID;
                usrID = rs.getInt(1);
                usrName = rs.getString(2);
                salt = rs.getString(3);
                salted_hash = rs.getString(4);
                type = rs.getString(4);
                Users users = new Users(usrID, usrName,salt,salted_hash, type);
                usersArrayList.add(users);

            }
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
    }

    public void displayUsers()      //reads the program objects from the arraylist and puts them in the string builder
    {                                           //then returns the string builder
        if (usersArrayList.size() > 0)
        {
            for (Users user: usersArrayList)
            {
                System.out.println(user.toString());
            }
        }
        else
        {
            System.out.println("");
        }

    }

    public void loadSellers()
    {
        try
        {
            Class.forName (DATABASE_DRIVER);

            Connection con = DriverManager.getConnection(DATABASE_URL,getProperties());

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from sellers;");

            while(rs.next())
            {
                String FirstName, LastName;
                int SellerID, UserID;
                SellerID = rs.getInt(1);
                FirstName = rs.getString(2);
                LastName = rs.getString(3);
                UserID = rs.getInt("UserID");
                Seller seller = new Seller(SellerID, FirstName, LastName, UserID);
                sellers.add(seller);
//                System.out.println(progName+" "+ chrgPerMin+" "+chrgPerMB+" "+minsToTalk+" "+mBtoSpend);
//                private ArrayList<Program> programs = new ArrayList<>();

            }
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
    }

    public void displaySellers()      //reads the program objects from the arraylist and puts them in the string builder
    {                                           //then returns the string builder
        if (sellers.size() > 0)
        {
            for (Seller seller: sellers)
            {
                System.out.println(seller.toString());
            }
        }
        else
        {
            System.out.println("");
        }

    }

    public void loadCustomers()
    {
        try
        {
            Class.forName (DATABASE_DRIVER);

            Connection con = DriverManager.getConnection(DATABASE_URL,getProperties());

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from customers;");

            while(rs.next())
            {
                String FirstName, LastName, AFM;
                int CustomerID, UserID;
                CustomerID = rs.getInt(1);
                FirstName = rs.getString(2);
                LastName = rs.getString(3);
                AFM = rs.getString(4);
                UserID = rs.getInt("UserID");
                Customer customer = new Customer(CustomerID, FirstName, LastName, AFM, UserID);
                customers.add(customer);
//                System.out.println(progName+" "+ chrgPerMin+" "+chrgPerMB+" "+minsToTalk+" "+mBtoSpend);
//                private ArrayList<Program> programs = new ArrayList<>();

            }
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
    }

    public void displayCustomers()      //reads the program objects from the arraylist and puts them in the string builder
    {                                           //then returns the string builder
        if (customers.size() > 0)
        {
            for (Customer customer : customers)
            {
                System.out.println(customer.toString());
            }
        }
        else
        {
            System.out.println("");
        }

    }


    public void loadPrograms()      //loads all programs from the database and puts them in an arraylist made of program objects
    {

        try
        {
            Class.forName (DATABASE_DRIVER);
            Connection con = DriverManager.getConnection(DATABASE_URL,getProperties());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from plans;");
            while(rs.next())
            {
                String progName;
                int chrgPerMin, chrgPerMB,chrgPerSms, minsToTalk, mBtoSpend,smsToSpend, planID;
                planID = rs.getInt(1);
                progName = rs.getString("ProgramName");
                chrgPerMin = rs.getInt(3);
                chrgPerMB = rs.getInt(4);
                chrgPerSms = rs.getInt(5);
                minsToTalk = rs.getInt(6);
                mBtoSpend = rs.getInt(7);
                smsToSpend = rs.getInt(8);
                Program program1 = new Program(planID, progName, chrgPerMin, chrgPerMB,chrgPerSms, minsToTalk, mBtoSpend,smsToSpend);
                programs.add(program1);

            }
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }

    public StringBuilder displayPrograms()      //reads the program objects from the arraylist and puts them in the string builder
    {                                           //then returns the string builder
        StringBuilder temp;
        temp = new StringBuilder();
        if (programs.size() > 0)
        {
            for (Program program : programs)
            {
                temp.append(program.toString());
            }
        }
        else
        {
            System.out.println("");
        }

        return temp;
    }
    public StringBuilder loadProgramsName()      //loads all programs from the database and puts them in an arraylist made of program objects
    {
        programsName = new ArrayList<>();
        StringBuilder temp;
        temp = new StringBuilder();
        try
        {
            Class.forName (DATABASE_DRIVER);
            Connection con = DriverManager.getConnection(DATABASE_URL,getProperties());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from plans;");
            while(rs.next())
            {
                String progName;
                progName = rs.getString("ProgramName");
                programsName.add("<option>"+progName+"</option>");
            }
            if (programsName.size() > 0)
            {
                for (String program : programsName)
                {
                    temp.append(program);
                }
            }
            else
            {
                System.out.println("");
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return temp ;
    }


    public static int getUserID(String usrNameOfSellerORadmnORclient)
    {
        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("select * from users where UserName = ? ;");

            statement.setString(1, usrNameOfSellerORadmnORclient);    //Passes first parameter

            ResultSet resultSet = statement.executeQuery();  //Executes the query

            resultSet.next();

            int usrID = resultSet.getInt(1);

            con.close();

            return usrID;

        }
        catch (Exception e)
        {
            return 0;
        }

    }

    public static String getUserType(String usrNameOfSellerORadmnORclient)
    {
        try
        {
            Class.forName(DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL, ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("select * from users where UserName = ?;");

            statement.setString(1, usrNameOfSellerORadmnORclient);    //Passes first parameter

            ResultSet resultSet = statement.executeQuery();  //Executes the query

            resultSet.next();

            String usrType = resultSet.getString(5);

            con.close();

            return usrType;

        }
        catch (Exception e)
        {
            return null;
        }
    }

    //may delete this method
    public static int getSellerIDByUsrName(String usrName) //method overload? so as to not have to nearly identical methods
    {
        int sellerID;

        int usrID = getUserID(usrName);

        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("select * from sellers where UserID = ?;");

            statement.setInt(1, usrID);    //Passes first parameter

            ResultSet resultSet = statement.executeQuery();  //Executes the query

            resultSet.next();

            sellerID = resultSet.getInt(1);

            con.close();

        }
        catch (Exception e)
        {
            System.out.println("Me is Here?");
            return 0;
        }
//        userProperties.clear();
        System.out.println("AM i here?");
        return sellerID;

    }

    public static int getSellerIDByNameAndSurName(String name, String surname) //method overload? so as to not have to nearly identical methods
    {
        int sellerID;
        try
        {

            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("select * from sellers where firstname = ? and lastname = ? ");

            statement.setString(1, name);    //Passes first parameter

            statement.setString(2, surname);    //Passes first parameter

            ResultSet resultSet = statement.executeQuery();  //Executes the query

            resultSet.next();

            sellerID = resultSet.getInt(1);

            System.out.println("sellerid is: ");
            System.out.println(sellerID);

            con.close();

        }
        catch (Exception e)
        {
            System.out.println("Me is Here?");
            return 0;
        }
//        userProperties.clear();
        System.out.println("AM i here?");
        return sellerID;

    }

    public static boolean deleteUser(int userID)
    {
        try {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("delete from users where UserID = ?;");

            statement.setInt(1, userID);    //Passes first parameter

            statement.executeUpdate();

            con.close();
        }

        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public static boolean deleteCustomer(int customerID)
    {
        try {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("delete from customers where customerid = ?;");

            statement.setInt(1, customerID);    //Passes first parameter

            statement.executeUpdate();

            con.close();
        }

        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public static boolean deleteSeller(int sellerID)
    {
        try {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("delete from sellers where sellerid = ?;");

            statement.setInt(1, sellerID);    //Passes first parameter

            statement.executeUpdate();

            con.close();
        }

        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public static int getSellerIDByUsrID(int usrIDofSeller)
    {
        int sellerID;

        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("select * from sellers where UserID = ?;");

            statement.setInt(1, usrIDofSeller);    //Passes first parameter

            ResultSet resultSet = statement.executeQuery();  //Executes the query

            resultSet.next();

            sellerID = resultSet.getInt(1);

            con.close();

        }
        catch (Exception e)
        {
            System.out.println("Me is Here?");
            return 0;
        }
//        userProperties.clear();
        System.out.println("AM i here?");
        return sellerID;

    }

    public static int getPlanID(String ProgramName)
    {
        System.out.println("Got to PlanID");
        int PlanID;

        try
        {
            Class.forName (DATABASE_DRIVER);

            Connection con = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection

            PreparedStatement statement1 = con.prepareStatement("select * from plans where ProgramName = ? ;");

            statement1.setString(1, ProgramName);

            ResultSet resultSet2 = statement1.executeQuery();  //Executes the query

            resultSet2.next();

            PlanID = resultSet2.getInt(1);

            con.close();

            return PlanID;

        }
        catch (Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }

    public static int getCustomerID(String fName, String  lName)
    {
        System.out.println("Got to customer ID");

        try
        {
            Class.forName(DATABASE_DRIVER);

            Connection con = DriverManager.getConnection(DATABASE_URL, ConnectToDB.getProperties()); //Creates a connection

            int CustomerID;

            //Standard SQL statement (always WITH parameters)
            PreparedStatement statement = con.prepareStatement("select * from customers where FirstName = ? and LastName = ?;");

            statement.setString(1, fName);    //Passes first parameter

            statement.setString(2, lName);   //Passes second parameter

            ResultSet resultSet = statement.executeQuery();  //Executes the query

            resultSet.next();

            CustomerID = resultSet.getInt(1);

            con.close();

            return CustomerID;
        }

        catch (Exception e)
        {
            System.out.println(e);
            return 0;
        }

    }



}

