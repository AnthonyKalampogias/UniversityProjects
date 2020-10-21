package ServletsClasses;

import BasicClasses.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CustomerServlet", urlPatterns ={"/CustomerServlet"} )
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Called when the Client requests to Pay his bill
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //No need for object we can instantly call the method
        Customer.PaySubscription(request.getParameter("bill"));
        request.getRequestDispatcher("/CustomerPage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
