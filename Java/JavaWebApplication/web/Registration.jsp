<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/5/2020
  Time: 6:18 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="BasicClasses.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        body{
            padding: 0;
            margin: 0;
        }
        button{
            padding: 8px 80px;
            cursor: pointer;
        }
        .div1{
            background-color: white;
            border-radius: 5px;
            box-shadow: 0px 10px 5px grey;
            border: 1px solid;
            width: 500px;

        }
        .div1 input{
            height: 20px;
            background-color: #fafafa;
        }
    </style>
</head>
<body style="background-color: #fafafa">
<div style="background-color: dodgerblue">
    <table border="0" style="width:100%; height:100px;">
        <tr>
            <td><center><h3>WEBPAGE</h3></center></td>
        </tr>
    </table>
</div>
<br><br><br><br><br><br>
<center><div class="div1">
    <div style="background-color: dodgerblue;height: 30px">Registration</div>
    <br>
    <form class="Credentials" action="RegistrationServlet" method="post">
        <table border="0" style="width:400px;">
            <tr style="height: 40px">
                <td style="width: 20%; text-align: right;">
                    <b>Name:</b>
                </td>
                <td>
                    <input  type="text" name="firstname" required>
                </td>
            </tr>
            <tr style="height: 40px">
                <td style="width: 20%; text-align: right;" >
                    <b>Surname:</b>
                </td>
                <td>
                    <input type="text" name="lastname"  required>
                </td>
            </tr>
            <tr style="height: 40px">
                <td style="width: 20%; text-align: right;" >
                    <b>Username:</b>
                </td>
                <td>
                    <input type="text" name="username"  required>
                </td>
            </tr>
            <tr style="height: 40px">
                <td style="width: 20%; text-align: right;" >
                    <b>Password:</b>
                </td>
                <td>
                    <input type="password" name="password"  required>
                </td>
            </tr>
            <tr style="height: 40px">
                <td style="width: 20%; text-align: right;">
                    <b>AFM:</b>
                </td>
                <td>
                    <input  type="text" name="AFM" minlength="9" maxlength="9" required>
                </td>
            </tr>
            <tr style="height: 40px">
                <td style="width: 20%; text-align: right;" >
                    <b>Type:</b>
                </td>
                <td>
                    <input type="text" name="type" placeholder="Client" value="Client" readonly>
                </td>
            </tr>
            <tr style="height: 40px">
                <td style="width: 20%; text-align: right;"  >
                    <label for="program"><b>Program:</b></label>
                </td>
                <td>
                    <select style="width: 150px" id="program" name="program" required >
                        <option value="" selected disabled hidden>Επιλέξτε πρόγραμμα</option>
                        <%
                            ConnectToDB con = new ConnectToDB();
                            StringBuilder temp = con.loadProgramsName();
                            out.println(temp);
                        %>
                    </select>
                </td>
            </tr>
            <tr style="height: 80px">
                <td colspan="2" style="width: 50%">
                    <center>
                        <button type="submit" name="register" >Register</button>
                    </center>
                </td>
            </tr>
            <tr style="height: 80px">
                <td colspan="2" style="width: 50%">
                    <center>
                        <a href="index.jsp">Back</a>
                    </center>
                </td>
            </tr>
        </table>
    </form>
</div></center>
<br><br>
</body>
</html>
