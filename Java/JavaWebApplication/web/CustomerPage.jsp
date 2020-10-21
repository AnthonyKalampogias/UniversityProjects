<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/6/2020
  Time: 2:35 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="BasicClasses.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CustomerPage</title>
    <style>
        body{
            padding: 0;
            margin: 0;
        }
        #logout{
            padding:5px 30px ;
        }
        button{
            padding: 5px 50px;
            cursor: pointer;
        }
        .div1{
            background-color: white;
            border-radius: 5px;
            box-shadow: 0px 10px 5px grey;
            border: 1px solid;
            width: 800px;
        }
        .div1 input{
            height: 20px;
            background-color: #fafafa;
        }

        .btn-group .button {
            background-color:  #b2dffc;
            border: 1px solid lightskyblue;
            color: black;
            padding: 20px 35px;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            cursor: pointer;
            width: 200px;
            display: block;
        }
        .btn-group .button:hover {
            background-color:   #22a2f7;
        }
        #d1,#d2,#d3 {
            display: none;
        }
        .menu {
            vertical-align: top;
            border-collapse: collapse;
        }
        .vl{
            border-left: 1px solid black;
            height: 500px;
        }
        .changeprogram tr{
            height: 40px;
        }
        #UserInfoTable tr{
            height: 40px;
        }
    </style>
</head>
<body style="background-color: #fafafa">
<div style="background-color: dodgerblue">
    <table border="0" style="width:100%; height:100px;" >
        <tr>
            <td rowspan="2"><center><h3>WEBPAGE</h3></center></td>
            <%
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setHeader("Expires", "0"); // Proxies.
                if(session.getAttribute("username")==null)
                {
                    //  response.sendRedirect("index.jsp");
                }
            %>
            <td><center><b>Welcome <%=session.getAttribute("username")%></b></center></td>
        </tr>
        <tr>
            <td style="width: 30%">
                <center>
                    <form method="post" action="LogOutServlet">
                        <button type="submit" name="logout">Log out</button>
                    </form>
                </center>
            </td>
        </tr>
    </table>
</div>
<br><br><br><br><br><br>
<%/*
_________________________________________________________
                        Menu
*/%>
<center><div class="div1" style="background-color: white; ">
    <table class="menu" border="0" style="height: 500px; width: 800px;">
        <tr>
            <td style="width: 200px; vertical-align: top">
                <div class="btn-group">
                    <button id ="b1" class="button"  onclick="button1()">Προβολή λογαριασμού</button>
                    <button id ="b2" class="button" onclick="button2()">Ιστορικό κλήσεων</button>
                    <button id ="b3" class="button" onclick="button3()">Εξόφληση λογαριασμού</button>
                </div>
            </td>
            <td style="width: 2px">
                <div class="vl"></div>
            </td>
            <%/*
_________________________________________________________
                     View Account
*/%>
            <td style="vertical-align: top">
                <center><div id="d1" >
                    <br>
                    <h3>Προβολή λογαριασμού</h3>
                    <hr style="width: 500px"><br>
                    <%
                        Customer CustomerObj = new Customer();
                        ArrayList UserInfo = CustomerObj.ClientShowDetails((String)session.getAttribute("username"));
                        Boolean flag = false ;
                        if (UserInfo!= null) {
                            flag= true;
                        }
                        else {
                            flag=false;
                        }
                    %>
                    <table border="0" style="width: 500px; border-collapse: collapse" id="UserInfoTable">
                        <tr>
                            <td style="width: 20%"><b>Όνομα: </b></td>
                            <td><%if (flag) out.print(UserInfo.get(1));%></td>
                        </tr>
                        <tr>
                            <td style="width: 20%"><b>Επίθετο: </b></td>
                            <td><%if (flag) out.print(UserInfo.get(2));%></td>
                        </tr>
                        <tr>
                            <td style="width: 20%"><b>Όνομα Χρήστη: </b></td>
                            <td><%if (flag) out.print(UserInfo.get(0));%></td>
                        </tr>
                        <tr>
                            <td style="width: 20%"><b>ΑΦΜ: </b></td>
                            <td><%if (flag) out.print(UserInfo.get(3));%></td>
                        </tr>
                        <tr>
                            <td style="width: 20%"><b>Τηλέφωνο: </b></td>
                            <td><%if (flag) out.print(UserInfo.get(5));%></td>
                        </tr>
                        <tr>
                            <td style="width: 20%"><b>Πρόγραμμα: </b></td>
                            <td><%if (flag) out.print(UserInfo.get(6));%></td>
                        </tr>
                        <tr>
                            <td style="width: 20%"><b>Ημερομηνία εγγραφής: </b></td>
                            <td><%if (flag) out.print(UserInfo.get(4));%></td>
                        </tr>
                    </table>
                </div></center>
                <%/*
_________________________________________________________
               View Phone call history
*/%>
                <div id="d2">
                    <center>
                        <br><h3>Ιστορικό κλήσεων</h3>
                        <hr style="width: 500px"><br>
                        <table border="1" style="font-family: arial, sans-serif;
                                                 border-collapse: collapse;
                                                 width: 100%;">
                            <tr style="border: 1px solid #dddddd;  text-align: left;  padding: 8px;">
                                <th>Διαρκια Κλησης</th>
                                <th>Κληση απο</th>
                                <th>Κληση προς</th>
                                <th>Ημερομηνια κλησης</th>
                            </tr>
                            <%
                                try {
                                    //Calls this method to return info about the customers call history
                                    String UserCalls= CustomerObj.CustomerCalls((String)session.getAttribute("username"));
                                    if (!UserCalls.isEmpty()) {
                                        out.print(UserCalls);
                                    }
                                    else out.print("<tr><td>No records found</td></tr>");
                                }
                                catch (Exception igonred)
                                {

                                }
                            %>
                        </table>
                    </center>
                </div>
                <%/*
_________________________________________________________
                     Subscription Payment
*/%>
                <center><div id="d3">
                    <br>
                    <h3>Εξόφληση λογαριασμού</h3>
                    <hr style="width: 500px"><br>

                    <%
                        ArrayList SubPayment = CustomerObj.SubscriptionViewer((String)session.getAttribute("username"));
                        boolean Subflag = false ;
                        //Subflag is true if the DB returns an output for a users bill
                        if (SubPayment!= null){
                            Subflag= true;
                        }
                        else {
                            Subflag=false;
//                            out.print("No records found");
                        }

                        ArrayList shouldTheCustomerPay = CustomerObj.SubscriptionViewerWhenTheBillIsNOTPaid((String)session.getAttribute("username"));
                        boolean flag2 = false ;
                        //Subflag is true if the DB returns an output for a users bill
                        if (shouldTheCustomerPay!= null){
                            flag2= true;
                        }
                        else {
                            flag2=false;
//                            out.print("No records found");
                        }

                    %>
                    <table border="0" style="width: 500px; border-collapse: collapse" id="UserInfoTable">
                        <tr>
                            <td style="width: 20%"><b>Username: </b></td>
                            <td></td>
                            <td><%if (Subflag) {
                                out.print(SubPayment.get(3));
                            }%></td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td style="width: 20%"><b>Αριθμος Λογαριασμου: </b></td>
                            <td></td>
                            <td><%if (flag2){
                                out.print(shouldTheCustomerPay.get(0));
                            }
                            else if(Subflag)
                            {
                                out.print(SubPayment.get(0));
                            }

                            %></td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td style="width: 20%"><b>Εχει πληρωθει;: </b></td>
                            <td></td>
                            <td><%if (flag2)
                            {
                                if ( shouldTheCustomerPay.get(1).equals("0")){
                                    out.print("Not Payed !");
                                }
                                else  out.print("Payed !");

                            }
                            else if (Subflag)
                            {
                                if ( SubPayment.get(1).equals("0")){
                                    out.print("Not Payed !");
                                }
                                else  out.print("Payed !");
                            }
                            %></td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td style="width: 20%"><b>Ποσο: </b></td>
                            <td></td>
                            <td><%if (flag2)
                            {
                                out.print(shouldTheCustomerPay.get(2));
                            }
                            else if (Subflag)
                            {
                                out.print(SubPayment.get(2));
                            }
                            %></td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td colspan="3">For more information about your account visit the first tab "Προβολή λογαριασμού"</td>
                        </tr>
                        <%
                            String name = request.getParameter("user");
                        %>
                    </table>
                    <%

                        if (flag2){
                            try {
                                if (shouldTheCustomerPay.get(1).equals("0"))
                                    //If there is an output from the DB AND the first item from the array which is BillID is 0
                                    // meaning not paid show this form with the pay button
                                    out.print(
                                            "<form action=\"CustomerServlet\" method=\"post\">" +
                                                    "<p> <input type=\"hidden\" name=\"bill\" value="+shouldTheCustomerPay.get(0)+" readonly></p>" +
                                                    "<p>Submit button.\n" +
                                                    "<input type=\"submit\" name=\"submit\" value=\"submit\" /></p>"
                                    );
                            }
                            catch (Exception ignored)
                            {

                            }
                        }
                    %>
                </div></center>
            </td>
        </tr>
    </table>
</div></center>
</body>
</html>
<script>
    function button1() {
        document.getElementById("d1").style.display = "block";
        document.getElementById("b1").style.backgroundColor = "#22a2f7";

        document.getElementById("d2").style.display = "none";
        document.getElementById("b2").style.backgroundColor = "#b2dffc";

        document.getElementById("d3").style.display = "none";
        document.getElementById("b3").style.backgroundColor = "#b2dffc";

    }
    function button2() {
        document.getElementById("d2").style.display = "block";
        document.getElementById("b2").style.backgroundColor = "#22a2f7";

        document.getElementById("d1").style.display = "none";
        document.getElementById("b1").style.backgroundColor = "#b2dffc";

        document.getElementById("d3").style.display = "none";
        document.getElementById("b3").style.backgroundColor = "#b2dffc";

    }
    function button3(){
        document.getElementById("d3").style.display = "block";
        document.getElementById("b3").style.backgroundColor = "#22a2f7";

        document.getElementById("d1").style.display = "none";
        document.getElementById("b1").style.backgroundColor = "#b2dffc";

        document.getElementById("d2").style.display = "none";
        document.getElementById("b2").style.backgroundColor = "#b2dffc";
    }
</script>

