package ServletsClasses;

import BasicClasses.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RegistrationServlet",urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //request.getRequestDispatcher("/SellerPage.jsp").forward(request, response);
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String AFM = request.getParameter("AFM");
        String type = request.getParameter("type");
        String program = request.getParameter("program");
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        int temp;
        try{
            int afm = Integer.parseInt(AFM);
            temp = afm;
        }catch (NumberFormatException ex) {
            temp = 0;
        }
        if(temp == 0)
        {
            PrintWriter writer = response.getWriter();
            String htmlRespone = "<html>";
            htmlRespone += "<body>";
            htmlRespone += "<h2>Invalid input</h2>";
            htmlRespone +="<a href=\"Registration.jsp\">OK</a>";
            htmlRespone += "</body>";
            htmlRespone += "</html>";
            writer.println(htmlRespone);
        }else{
            Users newuser = new Users();
            newuser.Register(username,password,type,firstname,lastname,temp,program,date);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
