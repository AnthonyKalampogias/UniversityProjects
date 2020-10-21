<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/5/2020
  Time: 10:07 μμ
  To change this template use File | Settings | File Templates.
--%>
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
            padding: 8px 80px;
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

    </style>
</head>
<body style="background-color: #fafafa">
    <div style="background-color: dodgerblue">
        <table border="0" style="width:100%; height:100px;" >
            <tr>
                <td rowspan="2"><center><h3>WEBPAGE</h3></center></td>
                <% String name = request.getParameter("user"); %>
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
        <table class="menu" border="0" style="height: 500px; width: 800px;">
            <tr>
                <td style="width: 200px; vertical-align: top">
                    <div class="btn-group">
                           <button id ="b1" class="button"  onclick="button1()">Καταχώρηση νέου πελάτη</button>
                           <button id ="b2" class="button" onclick="button2()">Προβολή διαθέσιμων πακέτων</button>
                           <button id ="b3" class="button" onclick="button3()">Αλλαγή προγράμματος πελάτη</button>
                    </div>
                </td>
                <td style="width: 2px">
                    <div class="vl"></div>
                </td>
                <td style="vertical-align: top">
                    <center><div id="d1" >
                        <br>
                        <h3>Καταχώρηση νέου πελάτη</h3>
                        <hr style="width: 500px"><br>
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
                                <tr style="height: 40px">
                                    <td style="width: 20%; text-align: right;"  >
                                        <label for="program"><b>Program:</b></label>
                                    </td>
                                    <td>
                                        <select style="width: 150px" id="program" name="program" >
                                            <option value="" selected disabled hidden>Επιλέξτε πρόγραμμα</option>
                                            <option>asdfs</option>
                                            <option>asdfas</option>
                                            <option>asfdas</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr style="height: 100px;">
                                    <td colspan="2" style="width: 50%">
                                        <center>
                                            <button type="submit" name="register" >Καταχώρηση</button>
                                        </center>
                                    </td>
                                </tr>
                            </form>
                        </table>
                    </div></center>
                    <div id="d2">asdfdasfasdfsafasd</div>
                    <center><div id="d3">
                        <br>
                        <h3>Αλλαγή προγράμματος πελάτη</h3>
                        <br>
                        <label for="users">Επιλογή πελάτη:</label>
                        <select style="width: 200px" name="users" id="users">
                            <option>jgfjfg</option>
                            <option>fghf</option>
                            <option>asdfas</option>
                            <option>asdfsa</option>
                        </select>
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
        document.getElementById("b1").style.backgroundColor = "#990000";

        document.getElementById("d2").style.display = "none";
        document.getElementById("b2").style.backgroundColor = "#cc0000";

        document.getElementById("d3").style.display = "none";
        document.getElementById("b3").style.backgroundColor = "#cc0000";

    }
    function button2() {
        document.getElementById("d2").style.display = "block";
        document.getElementById("b2").style.backgroundColor = "#990000";

        document.getElementById("d1").style.display = "none";
        document.getElementById("b1").style.backgroundColor = "#cc0000";

        document.getElementById("d3").style.display = "none";
        document.getElementById("b3").style.backgroundColor = "#cc0000";

    }
    function button3(){
        document.getElementById("d3").style.display = "block";
        document.getElementById("b3").style.backgroundColor = "#990000";

        document.getElementById("d1").style.display = "none";
        document.getElementById("b1").style.backgroundColor = "#cc0000";

        document.getElementById("d2").style.display = "none";
        document.getElementById("b2").style.backgroundColor = "#cc0000";
    }
</script>
