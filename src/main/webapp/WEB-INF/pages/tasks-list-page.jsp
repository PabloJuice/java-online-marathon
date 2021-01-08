<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
<style type="text/css">
    body{
        display: flex;
        flex-direction: column;
        padding: 1%;
        text-align: left;
        font-family: "Roboto";
    }
    header{
        height: 6%;
        display: flex;
        flex-direction: row;

    }
    header>a{
        font-size: 20px;
        margin: 8px;
        outline: none;
        text-decoration: none;
        color: black;
    }
    header>hr{
        color: black;
        margin: 5px;
    }
    h1{
        text-align: center;
        font-size: 40px;
        font-weight: 700;
    }
    table.task-list-table {
        align-self: center;
        border: 1px solid #1C6EA4;
        width: 75%;
        height: 70px;
        text-align: center;
        border-collapse: collapse;
    }
    table.task-list-table td, table.task-list-table th {
        border: 1px solid #AAAAAA;
        padding: 3px 2px;
    }
    table.task-list-table tbody td {
        font-size: 13px;
    }
    table.task-list-table>tbody>td {
        font-size: 13px;
    }
    table.task-list-table thead {
        background: #000000;
    }
    table.task-list-table thead th {
        font-size: 15px;
        font-weight: bold;
        color: #FFFFFF;
        text-align: center;
    }
    .table-id{
        width: 10%;
    }
    .table-operation{
        width: 8%;
    }

</style>
</head>
<body>
<header>
    <a href="home">Home</a><hr><a href="create-task">Add new Task</a><hr><a href="tasks-list">Show all Tasks</a>
</header>
<h1>List of Tasks</h1>
<table class="task-list-table">
    <thead>
        <tr>
            <th class="table-id">№</th>
            <th>Name</th>
            <th>Priority</th>
            <th colspan="3">Operations</th>
        </tr>
    </thead>
    <tbody>
        <%
            for(Task task : (List<Task>)request.getAttribute("tasks")) {
            %>
        <tr>
            <td class="table-id"><%=task.getId()%></td>
            <td><%=task.getTitle()%></td>
            <td><%=task.getPriority()%></td>
            <td class="table-operation">
                <a href="/read-task?id=<%=task.getId()%>">Read</a>
            </td>
            <td class="table-operation">
                <a href="/edit-task?id=<%=task.getId()%>">Edit</a>
            </td>
            <td class="table-operation">
                <a href="/delete-task?id=<%=task.getId()%>">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </tbody>

</table>
</body>
</html>
