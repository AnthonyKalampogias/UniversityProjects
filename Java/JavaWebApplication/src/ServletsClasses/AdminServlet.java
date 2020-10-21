package ServletsClasses;

import BasicClasses.Admin;
import BasicClasses.ConnectToDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AdminServlet",urlPatterns ={"/AdminServlet"} )
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CreateNewProgramButton = request.getParameter("CreateNewProgram");
        String RegisterSellerButton = request.getParameter("register");
        String DeleteSellerButton = request.getParameter("delete");
        if(RegisterSellerButton !=null){
            String firstname = request.getParameter("FirstName");
            String lastname = request.getParameter("LastName");
            String username = request.getParameter("usrName");
            String password = request.getParameter("pwd");
            String type = request.getParameter("type");

            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            String date = ft.format(dNow);

            if (Admin.CreateSeller(username,password,firstname,lastname,date,type))
            {
                PrintWriter out = response.getWriter();

                PrintWriter writer = response.getWriter();
                String htmlRespone = "<html>";
                htmlRespone += "<h2>Successfully created a seller with First Name: " + firstname + "<br/>";
                htmlRespone += "Last Name: " + lastname + "</h2>";
                htmlRespone +="<a href=\"AdminPage.jsp\">Back to home.</a>";
                htmlRespone += "</html>";
                writer.println(htmlRespone);
            }
            else
            {
                PrintWriter writer = response.getWriter();
                String htmlRespone = "<html>";
                htmlRespone += "<h2>Invalid input<h2/>";
                htmlRespone +="<a href=\"AdminPage.jsp\">Back to home.</a>";
                htmlRespone += "</html>";
                writer.println(htmlRespone);
            }




        }else if(DeleteSellerButton !=null){
            String firstname = request.getParameter("FirstName");
            String lastname =  request.getParameter("LastName");

            int sellerID = ConnectToDB.getSellerIDByNameAndSurName(firstname, lastname);
            if (ConnectToDB.deleteSeller(sellerID) && sellerID!=0)
            {
                PrintWriter out = response.getWriter();

                PrintWriter writer = response.getWriter();
                String htmlRespone = "<html>";
                htmlRespone += "<h2>Successfully deleted the seller with First Name: " + firstname + "<br/>";
                htmlRespone += "Last Name: " + lastname + "</h2>";
                htmlRespone +="<a href=\"AdminPage.jsp\">Back to home.</a>";
                htmlRespone += "</html>";
                writer.println(htmlRespone);
            }
            else{
                PrintWriter writer = response.getWriter();
                String htmlRespone = "<html>";
                htmlRespone += "<h2>Invalid input<h2/>";
                htmlRespone +="<a href=\"AdminPage.jsp\">Back to home.</a>";
                htmlRespone += "</html>";
                writer.println(htmlRespone);
            }


        }
        else if(CreateNewProgramButton!=null){
            String progname = request.getParameter("ProgName");
            String minutes = request.getParameter("min");
            String mb = request.getParameter("mb");
            String sms = request.getParameter("sms");
            String ChargePerMin = request.getParameter("ChargePerMin");
            String ChargePerMb = request.getParameter("ChargePerMb");
            String ChargePerSms = request.getParameter("ChargePerSMS");
            Admin.createPlan(progname, ChargePerMin ,ChargePerMb, ChargePerSms, minutes, mb, sms);

            PrintWriter out = response.getWriter();

            PrintWriter writer = response.getWriter();
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Successfully created a new plan with plan name: " + progname + "<br/>";
            htmlRespone +="<a href=\"AdminPage.jsp\">Back to home.</a>";
            htmlRespone += "</html>";
            writer.println(htmlRespone);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

