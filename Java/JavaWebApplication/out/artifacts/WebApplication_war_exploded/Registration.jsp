<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/5/2020
  Time: 6:18 μμ
  To change this template use File | Settings | File Templates.
--%>
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
        <table border="0" style="width:400px;">
            <form class="Credentials" action="SellerServlet" method="post">
                <tr style="height: 40px">
                    <td style="width: 20%; text-align: right;">
                        <label for="name"><b>Name:</b></label>
                    </td>
                    <td>
                        <input  type="text" id="name" required>
                    </td>
                </tr>
                <tr style="height: 40px">
                    <td style="width: 20%; text-align: right;" >
                        <label for="surname"><b>Surname:</b></label>
                    </td>
                    <td>
                        <input type="text" id="surname"  required>
                    </td>
                </tr>
                <tr style="height: 40px">
                    <td style="width: 20%; text-align: right;" >
                        <label for="username"><b>Username:</b></label>
                    </td>
                    <td>
                        <input type="text" id="username"  required>
                    </td>
                </tr>
                <tr style="height: 40px">
                    <td style="width: 20%; text-align: right;" >
                        <label for="password"><b>Password:</b></label>
                    </td>
                    <td>
                        <input type="password" id="password"  required>
                    </td>
                </tr>
                <tr style="height: 40px">
                    <td style="width: 20%; text-align: right;" >
                        <label for="type"><b>Type:</b></label>
                    </td>
                    <td>
                        <input type="text" id="type" placeholder="Client" readonly>
                    </td>
                </tr>
                <tr style="height: 80px">
                    <td colspan="2" style="width: 50%">
                        <center>
                            <button type="submit" name="register" >Register</button>
                        </center>
                    </td>
                </tr>
            </form>
        </table>
    </div></center>
    <br><br>
</body>
</html>
