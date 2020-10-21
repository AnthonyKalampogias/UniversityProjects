package BasicClasses;

import javax.xml.registry.infomodel.User;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.sql.*;

public class Customer extends Users {
    private String AFM;   //the AFM var cannot change once its assigned a value
    private String firstName, lastName;
    private int customerID, UserID;


    Customer(int customerID, String firstName, String lastName, String AFM, int UserID)
    {
        this.AFM = AFM;
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.UserID = UserID;
    }
    public Customer(){
        //Default constructor to be called from outside of company personnel
    }
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";                                   //the correct driver (at least for my pc)

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/app?serverTimezone=UTC";
    // The timezone is needed (at least for my pc) because the server wont run



    public static ArrayList<String> ClientShowDetails(String providedUsername){
        //Called from CustomerPage.jsp when the client request to view his information
        ArrayList<String> UserInfo = new ArrayList<String>();//Array list where the info of user are saved
        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con3 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            Statement stmt = con3.createStatement();
            //Create Connection to the DB

            ResultSet rs = stmt.executeQuery("SELECT * FROM users,customers,phonenumbers,soldplans where users.Username='"+providedUsername+"' LIMIT 1;");
            //Execute query to import the users info

            if(rs.next()) //Now that rs holds the info the user requesting his info we add the values where they belong
            {
                String  CustomerID;
                int UserID,PhoneNumID,PlanID;
                UserInfo.add(rs.getString("Username"));
                UserInfo.add(rs.getString("FirstName"));
                UserInfo.add(rs.getString("LastName"));
                UserInfo.add(rs.getString("AFM"));
                UserInfo.add(rs.getString("JoinedWhen"));
                UserInfo.add(rs.getString("PhoneNumber"));
                UserID = rs.getInt("UserID");
                PhoneNumID = rs.getInt("PhoneNumID");
                PlanID = rs.getInt("PlanID");
                //NOTE from the previous query we don't get the Name of the program the Client is subscribed so
                stmt = con3.createStatement();
                rs = stmt.executeQuery("SELECT * FROM `plans` WHERE PlanID = "+PlanID+" LIMIT 1;");

                //Now that we have all the needed values lets return them to the Client
                if(rs.next()){
                    UserInfo.add(rs.getString("ProgramName"));
                    if (UserInfo.isEmpty())
                    {
                        return null;
                    }
                    else {
                        return UserInfo;
                    }
                }
            }
            else System.out.println("Oh no...\nSomething went wrong!!");
            con3.close();//Close the connection to the DB
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static String CustomerCalls(String ProvidedUsername){
        //Called from CustomerPage.jsp when the client request to view his call history
        String UserCallInfo="";

        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con4 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            Statement stmt = con4.createStatement();
            //Create Connection to the DB

            ResultSet rs = stmt.executeQuery(
                    "SELECT\n" +
                    "    Duration,\n" +
                    "    CallerNumber,\n" +
                    "    CalleeNumber,\n" +
                    "    CallMadeWhen\n" +
                    "FROM\n" +
                    "    users\n" +
                    "JOIN customers ON customers.UserID = users.UserID\n" +
                    "JOIN callsmade ON callsmade.CustomerID = customers.CustomerID\n" +
                    "\n" +
                    "WHERE\n" +
                    "    users.Username = '"+ProvidedUsername+"';");
            //Execute query to import the users info

            while(rs.next())
            {
                String  Duration,CallerNumber,CalleeNumber,CallMadeWhen;
                Duration = rs.getString("Duration");
                CallerNumber = rs.getString("CallerNumber");
                CalleeNumber = rs.getString("CalleeNumber");
                CallMadeWhen = rs.getString("CallMadeWhen");
                UserCallInfo += "<tr><td>"+ Duration + "</td><td>" + CallerNumber + "</td><td>" + CalleeNumber + "</td><td>" + CallMadeWhen + "</td></tr>";
            }
            con4.close();//Close the connection to the DB
            return UserCallInfo.toString();
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<String> SubscriptionViewer(String ProvidedName){
        ArrayList<String> UserReturnSubInfo = new ArrayList<String>();
        try
        {

            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con4 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            Statement stmt = con4.createStatement();
            //Create Connection to the DB

            ResultSet tr = stmt.executeQuery("SELECT\n" +
                                                 "    bills.isPaid,\n" +
                                                 "    bills.BillID,\n" +
                                                 "    bills.MoneyToPay,\n" +
                                                 "    users.Username,\n" +
                                                 "    customers.FirstName,\n" +
                                                 "    customers.LastName,\n" +
                                                 "    customers.AFM\n" +
                                                 "FROM\n" +
                                                 "    users\n" +
                                                 "JOIN customers ON customers.UserID = users.UserID\n" +
                                                 "JOIN bills ON bills.CustomerID = customers.CustomerID\n" +
                                                 "JOIN soldplans ON soldplans.PlanID = bills.PlanID\n" +
                                                 "WHERE\n" +
                                                 "    users.Username = '"+ ProvidedName +"'\n" +
                                                 "LIMIT 1 ;");
            //Execute query to import the users info

            if(tr.next())
            {
                UserReturnSubInfo.add(tr.getString("bills.BillID"));
                UserReturnSubInfo.add(tr.getString("bills.isPaid"));
                UserReturnSubInfo.add(tr.getString("bills.MoneyToPay"));
                UserReturnSubInfo.add(tr.getString("users.Username"));
                }
            con4.close();//Close the connection to the DB
            if (UserReturnSubInfo.isEmpty())
            {
                return null;
            }
            else
            {
                return UserReturnSubInfo;
            }

        }

        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<String> SubscriptionViewerWhenTheBillIsNOTPaid(String ProvidedName){
        ArrayList<String> UserReturnSubInfo = new ArrayList<String>();
        try
        {
            Class.forName (DATABASE_DRIVER);    //Specifies the driver

            Connection con4 = DriverManager.getConnection(DATABASE_URL,ConnectToDB.getProperties()); //Creates a connection
            Statement stmt = con4.createStatement();
            //Create Connection to the DB

            ResultSet tr = stmt.executeQuery("SELECT\n" +
                    "    bills.isPaid,\n" +
                    "    bills.BillID,\n" +
                    "    bills.MoneyToPay,\n" +
                    "    users.Username,\n" +
                    "    customers.FirstName,\n" +
                    "    customers.LastName,\n" +
                    "    customers.AFM\n" +
                    "FROM\n" +
                    "    users\n" +
                    "JOIN customers ON customers.UserID = users.UserID\n" +
                    "JOIN bills ON bills.CustomerID = customers.CustomerID\n" +
                    "JOIN soldplans ON soldplans.PlanID = bills.PlanID\n" +
                    "WHERE\n" +
                    "    users.Username = '"+ ProvidedName +"'\n" +
                    " and bills.ispaid = 0 LIMIT 1 ;");
            //Execute query to import the users info

            if(tr.next())
            {
                UserReturnSubInfo.add(tr.getString("bills.BillID"));
                UserReturnSubInfo.add(tr.getString("bills.isPaid"));
                UserReturnSubInfo.add(tr.getString("bills.MoneyToPay"));
                UserReturnSubInfo.add(tr.getString("users.Username"));
            }
            con4.close();//Close the connection to the DB
            if (UserReturnSubInfo.isEmpty())
            {
                return null;
            }
            else
            {
                return UserReturnSubInfo;
            }

        }

        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static void PaySubscription(String BillID) {
        try {
            Class.forName(DATABASE_DRIVER);    //Specifies the driver
            Connection con4 = DriverManager.getConnection(DATABASE_URL, ConnectToDB.getProperties()); //Creates a connection
            Statement stmt = con4.createStatement();
            System.out.println("Bill = "+BillID);
            stmt.executeUpdate("UPDATE `bills` SET bills.isPaid = 1 WHERE bills.BillID = "+ BillID+" ");
//            System.out.println("Query"+tr);
            con4.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public String toString() {
        return customerID + " " + firstName + " " + lastName + " " + AFM + " " + UserID;
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


}

