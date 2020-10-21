<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/5/2020
  Time: 10:07 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="BasicClasses.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SellerPage</title>
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
        #d1,#d2,#d3,#d4 {
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
<center><div class="div1" style="background-color: white; ">
    <table class="menu" border="0" style="height: 500px; width: 800px;">
        <tr>
            <td style="width: 200px; vertical-align: top">
                <div class="btn-group">
                    <button id ="b1" class="button"  onclick="button1()">Καταχώρηση νέου πελάτη</button>
                    <button id ="b2" class="button" onclick="button2()">Προβολή διαθέσιμων πακέτων</button>
                    <button id ="b3" class="button" onclick="button3()">Αλλαγή προγράμματος πελάτη</button>
                    <button id ="b4" class="button" onclick="button4()">Έκδοση λογαριασμού</button>

                </div>
            </td>
            <td style="width: 2px">
                <div class="vl"></div>
            </td>
            <td style="vertical-align: top">
                <center><div id="d1" >
                    <br>
                    <h3>Καταχώρηση νέου πελάτη και επιλογή προγράμματος τηλεφωνίας</h3>
                    <hr style="width: 500px"><br>
                    <table border="0" style="width:400px;">
                        <form class="Credentials" action="SellerServlet" method="post">
                            <tr style="height: 40px">
                                <td style="width: 20%; text-align: right;">
                                    <label for="name"><b>Name:</b></label>
                                </td>
                                <td>
                                    <input  type="text" id="name" name="FirstName" required>
                                </td>
                            </tr>
                            <tr style="height: 40px">
                                <td style="width: 20%; text-align: right;" >
                                    <label for="surname"><b>Surname:</b></label>
                                </td>
                                <td>
                                    <input type="text" id="surname" name="LastName" required>
                                </td>
                            </tr>
                            <tr style="height: 40px">
                                <td style="width: 20%; text-align: right;" >
                                    <label for="username"><b>Username:</b></label>
                                </td>
                                <td>
                                    <input type="text" id="username" name="usrName" required>
                                </td>
                            </tr>
                            <tr style="height: 40px">
                                <td style="width: 20%; text-align: right;" >
                                    <label for="password"><b>Password:</b></label>
                                </td>
                                <td>
                                    <input type="password" id="password" name="pswd" required>
                                </td>
                            </tr>
                            <tr style="height: 40px">
                                <td style="width: 20%; text-align: right;">
                                    <label for="name"><b>AFM:</b></label>
                                </td>
                                <td>
                                    <input  type="text" id="afm" name="AFM" minlength="9" maxlength="9" required>
                                </td>
                            </tr>
                            <tr style="height: 40px">
                                <td style="width: 20%; text-align: right;" >
                                    <label for="type"><b>Type:</b></label>
                                </td>
                                <td>
                                    <input type="text" id="type" placeholder="Client" name="type" readonly>
                                </td>
                            </tr>
                            <tr style="height: 40px">
                                <td style="width: 20%; text-align: right;"  >
                                    <label for="program"><b>Program:</b></label>
                                </td>
                                <td>
                                    <select style="width: 150px" id="program" name="program" required>
                                        <option value="" selected disabled hidden>Επιλέξτε πρόγραμμα</option>
                                        <%
                                            ConnectToDB con = new ConnectToDB();
                                            StringBuilder temp = con.loadProgramsName();
                                            out.println(temp);
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr style="height: 100px;">
                                <td colspan="2" style="width: 50%">
                                    <center>
                                        <button type="submit" name="register" value="register">Καταχώρηση</button>
                                    </center>
                                </td>
                            </tr>
                        </form>
                    </table>
                </div></center>
                <div id="d2">
                    <center>
                        <br><h3>Προγράμματα</h3>
                        <hr style="width: 500px"><br>
                        <table border="1" style="width: auto; border-collapse: collapse">
                            <tr>
                                <th>Id</th>
                                <th>Όνομα Προγράμματος</th>
                                <th>Τιμή/λεπτό</th>
                                <th>Τιμή/ΜΒ</th>
                                <th>Τιμή/sms</th>
                                <th>Διαθέσιμα λεπτά</th>
                                <th>Διαθέσιμα MB</th>
                                <th>Διαθέσιμα sms</th>
                            </tr>
                            <%ConnectToDB con1 = new ConnectToDB();
                                con1.loadPrograms();
                                StringBuilder temp1 = con1.displayPrograms();
                                out.println(temp1.toString());
                            %>
                        </table>
                    </center>
                </div>
                <center><div id="d3">
                    <br>
                    <h3>Αλλαγή προγράμματος πελάτη</h3>
                    <hr style="width: 500px"><br>
                    <form method="post" action="SellerServlet">
                        <table border="0" class="changeprogram">
                            <tr>
                                <td>Όνομα:</td>
                                <td><input type="text" name="CPfirstname" required></td>
                            </tr>
                            <tr>
                                <td>Επίθετο:</td>
                                <td><input type="text" name="CPlastname" required></td>
                            </tr>
                            <tr>
                                <td>Πρόγραμμα:</td>
                                <td>
                                    <select name="progname">
                                        <%
                                            ConnectToDB con2 = new ConnectToDB();
                                            StringBuilder temp2 = con2.loadProgramsName();
                                            out.println(temp2);
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <center><button type="submit" name="change" value="change">Αλλαγή</button></center>
                                </td>
                            </tr>
                        </table>
                    </form>

                </div></center>
                <center>
                    <div id="d4">
                        <br>
                        <h3>Έκδοση λογαριασμού</h3>
                        <hr style="width: 500px"><br>
                        <form method="post" action="SellerServlet">
                            <table>
                                <tr>
                                    <td>
                                        Όνομα:
                                    </td>
                                    <td>
                                        <input type="text" name="firstname" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Επίθετο:
                                    </td>
                                    <td>
                                        <input type="text" name="lastname" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <br>
                                        <button type="submit" name="bill">Έκδοση λογαριασμού</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </center>
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

        document.getElementById("d4").style.display = "none";
        document.getElementById("b4").style.backgroundColor = "#b2dffc";

    }
    function button2() {
        document.getElementById("d2").style.display = "block";
        document.getElementById("b2").style.backgroundColor = "#22a2f7";

        document.getElementById("d1").style.display = "none";
        document.getElementById("b1").style.backgroundColor = "#b2dffc";

        document.getElementById("d3").style.display = "none";
        document.getElementById("b3").style.backgroundColor = "#b2dffc";

        document.getElementById("d4").style.display = "none";
        document.getElementById("b4").style.backgroundColor = "#b2dffc";

    }
    function button3(){
        document.getElementById("d3").style.display = "block";
        document.getElementById("b3").style.backgroundColor = "#22a2f7";

        document.getElementById("d1").style.display = "none";
        document.getElementById("b1").style.backgroundColor = "#b2dffc";

        document.getElementById("d2").style.display = "none";
        document.getElementById("b2").style.backgroundColor = "#b2dffc";

        document.getElementById("d4").style.display = "none";
        document.getElementById("b4").style.backgroundColor = "#b2dffc";
    }
    function button4() {
        document.getElementById("d4").style.display = "block";
        document.getElementById("b4").style.backgroundColor = "#22a2f7";

        document.getElementById("d1").style.display = "none";
        document.getElementById("b1").style.backgroundColor = "#b2dffc";

        document.getElementById("d2").style.display = "none";
        document.getElementById("b2").style.backgroundColor = "#b2dffc";

        document.getElementById("d3").style.display = "none";
        document.getElementById("b3").style.backgroundColor = "#b2dffc";
    }
</script>

