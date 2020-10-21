package ServletsClasses;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import BasicClasses.*;

@WebServlet(name = "LoginServlet",urlPatterns ={"/LoginServlet"} )
public class LoginServlet extends HttpServlet
{
    Users usr1 = new Users();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    //Get username and password and send them to a function to log in the user
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");
        //SecureRandom random = new SecureRandom();
        //byte[] randomSalt = new byte[20];
       // random.nextBytes(randomSalt);
        //String salt = randomSalt.toString();
       // out.println(password);
        //out.println(salt);
        //out.println(usr1.Login(username,password));
        //out.println(Encryption.getHashMD5(password,salt));
        ArrayList<String> arrayList = usr1.Login(username,password);    //logIn method returns a string with the type of the user that logged in. Else it returns null
        if (arrayList.isEmpty())
        {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            arrayList.clear();
        }
        HttpSession session=request.getSession();
        if(arrayList.get(0).equals("Seller"))
        {
            session.setAttribute("username",username);
            session.setAttribute("type",arrayList.get(0));
            request.getRequestDispatcher("/SellerPage.jsp").forward(request, response);
            arrayList.clear();
        }
        else if(arrayList.get(0).equals("Client"))
        {
            session.setAttribute("username",username);
            session.setAttribute("type",arrayList.get(0));
            request.getRequestDispatcher("/CustomerPage.jsp").forward(request, response);
            arrayList.clear();
        }
        else if(arrayList.get(0).equals("Admin"))
        {
            session.setAttribute("username",username);
            session.setAttribute("type",arrayList.get(0));
            request.getRequestDispatcher("/AdminPage.jsp").forward(request, response);
            arrayList.clear();
        }
        else
        {
            //Add Error Message
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            arrayList.clear();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}

