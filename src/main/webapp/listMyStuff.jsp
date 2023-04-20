<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List my stuff</title>
<%--    <script src="actiuni.js" type="text/javascript"></script>--%>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

<%

    HttpSession s = request.getSession(); // citesc sesiunea curenta
    Object o = s.getAttribute("id"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii
    Object email = s.getAttribute("email");
    if (o == null) {
        response.sendRedirect("newLogin.html"); // il trimit la login, nici nu se executa ce e mai jos
    }
%>

Hello <b><%=email%>
</b>
</p>

<input type="text" placeholder="Search" onkeyup="search(this.value)">
</p>
<%--<input type="button" id="delete" value="Delete all" onClick="deleteAll()" />--%>

<div id="listOfToDo">
    <table border="1">
        <thead>
        <tr>
            <%--            <th onclick="sorteazaNume(this)">Obiect &dArr;</th>--%>
            <th>Item</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody id="obiect">

        </tbody>


    </table>
</div>

<script>
    loadToDo();
</script>

</p>
<input type="text" id="name" placeholder="Add my item"/>
<input type="button" id="add" value="New" onClick="newToDo()"/>

</p>
<a href="logout.jsp">Logout</a>

</body>
</html>