<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            font-size: 40px;
            font-weight: 700;
        }
        p{
            margin-left: 1%;
        }
    </style>
    <title>Read existing task</title>
<body>
    <header>
        <a href="home">Home</a><hr><a href="create-task">Add new Task</a><hr><a href="tasks-list">Show all Tasks</a>
    </header>
    <h1>Read existing task</h1>
    <% Task task = (Task) request.getAttribute("task");
        if(task == null){
            response.setStatus(404);
    %>
    <h1>Task with ID '<%=request.getParameter("id")%>' not found in To-Do List!</h1>
    <br>
    <h2> url: <%=request.getAttribute("requestURI")%></h2>
    <%
    }
    else{
    %>

        <p>No. : <%=task.getId()%></p>
        <p>Title : <%=task.getTitle()%></p>
        <p>Priority : <%=task.getPriority()%></p>
    <%
        };
    %>
</body>
</html>
