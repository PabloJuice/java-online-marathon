<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page import="com.softserve.itacademy.model.Priority" %>
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
        #create-btn, #clear-btn{
            margin: 1% 0 0 1%;
        }
    </style>
    <%Boolean isValidTask = (Boolean) request.getAttribute("isValidTask");
        String title = (isValidTask) ? "Edit existing task" : "Task not found" ;
    %>
    <title><%= title %></title>
</head>
<body>
<header>
    <a href="home">Home</a><hr><a href="create-task">Add new Task</a><hr><a href="tasks-list">Show all Tasks</a>
</header>

<form action="/edit-task" method="post">
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
    <table>
        <h2>Edit existing Task</h2>
        <br>
        <tr>
            <td>Id: </td>
            <td>
                <input type="text" id="id" name="id" value="<%=task.getId()%>" disabled>
            </td>
        </tr>
        <tr>
            <td>Name: </td>
            <td>
                <input type="text" id="taskname" name="taskname" value="<%=task.getTitle()%>">
            </td>
        </tr>
        <tr>
            <td>Priority: </td>
            <td>
                <select name="priority" id="priority-selection">
                    <option value="LOW"<% if (task.getPriority().equals(Priority.LOW)){ %> selected="selected"<% } %>>Low</option>
                    <option value="MEDIUM"<% if (task.getPriority().equals(Priority.MEDIUM)){ %> selected="selected"<% } %>>Medium</option>
                    <option value="HIGH"<% if (task.getPriority().equals(Priority.HIGH)){ %> selected="selected"<% } %>>High</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Update" id="create-btn"></td>
            <td><input type="reset" value="Clear" id="clear-btn"></td>
        </tr>
    </table>
    <%
        };
    %>
</form>

</body>
</html>