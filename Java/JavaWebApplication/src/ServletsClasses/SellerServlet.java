package ServletsClasses;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import BasicClasses.*;

//Inbound Connection from seller.jsp to register a new Client
@WebServlet(name = "SellerServlet" , urlPatterns = {"/SellerServlet"})
public class SellerServlet extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        String registerButton = request.getParameter("register");
        String changeButton = request.getParameter("change");
        String billButton = request.getParameter("bill");
//        ConnectToDB con1 = new ConnectToDB();
//        Users user1 = new Users();
          if(registerButton!=null)
          {
              response.setContentType("text/html;charset=UTF-8");
              PrintWriter out = response.getWriter();
          //Import Client credentials
              String username = request.getParameter("usrName");
              String password = request.getParameter("pswd");
              String firstName = request.getParameter("FirstName");
              String lastName = request.getParameter("LastName");
              String type = "Client";
              String AFM = request.getParameter("AFM");
              String programName = request.getParameter("program");
              int temp;
              try{
                  int afm = Integer.parseInt(AFM);
                  temp = afm;
              }catch (NumberFormatException ex) {
                  temp = 0;
              }

              //Create the register date of user
              Date dNow = new Date( );
              SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
              String date = ft.format(dNow);

              if(temp == 0)
              {
                  PrintWriter writer = response.getWriter();
                  String htmlRespone = "<html>";
                  htmlRespone += "<h2>Invalid input<h2/>";
                  htmlRespone +="<a href=\"SellerPage.jsp\">Back to home</a>";
                  htmlRespone += "</html>";
                  writer.println(htmlRespone);
              }
              else if (Seller.CreateClientAccount(username,password, type, firstName, lastName, temp, programName,date))
              {
                  PrintWriter writer = response.getWriter();
                  String htmlRespone = "<html>";
                  htmlRespone += "<h2>Successfully created a customer with First Name: " + firstName + "<br/>";
                  htmlRespone += "Last Name: " + lastName + "</h2>";
                  htmlRespone +="<a href=\"SellerPage.jsp\">Back to home</a>";
                  htmlRespone += "</html>";
                  writer.println(htmlRespone);
              }
              else
              {
                  PrintWriter writer = response.getWriter();
                  String htmlRespone = "<html>";
                  htmlRespone += "<h2>Invalid input<h2/>";
                  htmlRespone +="<a href=\"SellerPage.jsp\">Back to home</a>";
                  htmlRespone += "</html>";
                  writer.println(htmlRespone);
              }

            //request.getRequestDispatcher("/index.jsp").forward(request, response);
          }
          else if(changeButton!=null)
          {
              String firstname = request.getParameter("CPfirstname");
              String lastname = request.getParameter("CPlastname");
              String progname = request.getParameter("progname");

              int temp = ConnectToDB.getCustomerID(firstname, lastname);

              if(Seller.UpdateClienBillProgram(firstname, lastname, progname) && temp !=0)
              {
//                  request.getRequestDispatcher("/index.jsp").forward(request, response);
                  PrintWriter writer = response.getWriter();
                  String htmlRespone = "<html>";
                  htmlRespone += "<h2>Updated program for customer with first name: " + firstname + "<br/>";
                  htmlRespone += "Last Name: " + lastname + "</h2>";
                  htmlRespone +="<a href=\"SellerPage.jsp\">Back to home</a>";
                  htmlRespone += "</html>";
                  writer.println(htmlRespone);
              }
              else
              {
                  PrintWriter writer = response.getWriter();
                  String htmlRespone = "<html>";
                  htmlRespone += "<h2>Invalid input<h2/>";
                  htmlRespone +="<a href=\"SellerPage.jsp\">Back to home</a>";
                  htmlRespone += "</html>";
                  writer.println(htmlRespone);
              }


          }
          else if(billButton!=null){
              String firstname = request.getParameter("firstname");
              String lastname = request.getParameter("lastname");

              if (Seller.issueAccountBill(firstname, lastname))
              {
                  PrintWriter writer = response.getWriter();
                  String htmlRespone = "<html>";
                  htmlRespone += "<h2>Issued bill for customer with first name: " + firstname + "<br/>";
                  htmlRespone += "Last Name: " + lastname + "</h2>";
                  htmlRespone +="<a href=\"SellerPage.jsp\">Back to home</a>";
                  htmlRespone += "</html>";
                  writer.println(htmlRespone);
              }
              else
              {
                  PrintWriter writer = response.getWriter();
                  String htmlRespone = "<html>";
                  htmlRespone += "<h2>Invalid input<h2/>";
                  htmlRespone +="<a href=\"SellerPage.jsp\">Back to home</a>";
                  htmlRespone += "</html>";
                  writer.println(htmlRespone);
              }

//              request.getRequestDispatcher("/index.jsp").forward(request, response);
          }



    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
