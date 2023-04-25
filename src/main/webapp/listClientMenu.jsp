<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" >
    <title>List my stuff</title>
    <script src="actions.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>


<%

    HttpSession s = request.getSession(); // Read current session
    Object o = s.getAttribute("id"); // If power session exist object named ID, I will decide next
    Object email = s.getAttribute("email");
    if (o == null) {
        response.sendRedirect("newLogin.html"); // send to log in, code below won't execute
    }
%>

Hello <b><%=email%>
</b>
</p>

<input type="text" placeholder="Search" onkeyup="search(this.value)">

<%--<input type="button" id="delete" value="Delete all" onClick="deleteAll()" />--%>
<p>

<div id="listOfToDo1">
    <table border="1">
        <thead>
        <tr>
            <%--            <th onclick="sorteazaNume(this)">Obiect &dArr;</th>--%>
            <th>Item</th>
            <th>Date</th>
                <th>Room</th>
                <th>Watts</th>
                <th>Quantity</th>
            <th>Update</th>
                <th>Power</th>
                <th>Toggle</th>
        </tr>
        </thead>
        <tbody id="obiect1" >
        </tbody>
        <tbody id="toggleOn">
        </tbody>
    </table>
</div>
<script>
    loadItemToDo();
</script>
<div id="listOfToDo2">
    <table border="1">
        <thead>
        <tr>
            <%--            <th onclick="sorteazaNume(this)">Obiect &dArr;</th>--%>
            <th>Room</th>
        </tr>
        </thead>
        <tbody id="obiect2">

        </tbody>


    </table>
</div>

<script>
    loadRoomToDo()
</script>
</script>

<p>
    <label for="name1"></label><input type="text" id="name1" placeholder="Add my item"/>
    <label for="room1"></label><input type="text" id="room1" placeholder="Add my room"/>
    <label for="watts1"></label><input type="text" id="watts1" placeholder="Add my watts"/>
<input type="button" id="add1" value="New" onClick="newItemToDo()"/>
</p>
<p>
    <label for="name2"></label><input type="text" id="name2" placeholder="Add my room"/>
<input type="button" id="add2" value="New" onClick="newRoomToDo()"/>
<%--    <button type='submit' onClick={updatePower()}>POWER</button>--%>
</p>
<a href="logout.jsp">Logout</a>
</body>


</html>