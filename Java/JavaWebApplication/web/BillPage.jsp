<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/6/2020
  Time: 5:42 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="BasicClasses.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
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
            background-color: #cc0000;
            border: 1px solid red;
            color: white;
            padding: 20px 35px;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            cursor: pointer;
            width: 200px;
            display: block;
        }
        .btn-group .button:hover {
            background-color: #990000;
        }
        #d1,#d2,#d3,afm{
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
            <% String name = request.getParameter("user"); %>
            <%Users usr1 = new Users();%>
            <td><center><b>Welcome <% out.println(name);%></b></center></td>
        </tr>
        <tr>
            <td style="width: 30%">
                <center>
                    <button type="submit" id="logout"><a href="index.jsp" style="text-decoration: none">Log out</a></button>
                </center>
            </td>
        </tr>
    </table>
</div>
<br><br><br><br><br><br>
<center><div class="div1" style="background-color: white; ">
    <table border="1" style="width: 600px; border-collapse: collapse">
        <tr>
            <th colspan="2">Λογαριασμός</th>
        </tr>
        <tr>
            <td style="background-color: aquamarine">Ονοματεπώνυμο</td>
            <td>George Kaloudis</td>
        </tr>
        <tr>
            <td style="background-color: aquamarine">ΑΦΜ</td>
            <td><12343565462</td>
        </tr>
        <tr>
            <td style="background-color: aquamarine">Πρόγραμμα</td>
            <td>George Kaloudis</td>
        </tr>
        <tr>
            <td style="background-color: aquamarine">Αριθμός τηλεφώνου</td>
            <td>213432142314</td>
        </tr>
        <tr>
            <td colspan="2" style="background-color: aquamarine">Ανάλυση Λογαριασμού</td>
        </tr>
        <tr>
            <td colspan="2">
                <p>sadfsdafasd</p>
                <p>ljfhngokhk</p>
            </td>
        </tr>
        <tr>
            <td rowspan="2"></td>
            <td style="background-color: aquamarine">Ποσό</td>
        </tr>
        <tr>
            <td>90</td>
        </tr>

    </table>
    <button><a href="SellerPage.jsp">OK</a></button>
</div></center>
</body>
</html>

