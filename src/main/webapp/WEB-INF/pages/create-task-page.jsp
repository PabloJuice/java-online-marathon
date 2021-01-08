<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home page</title>
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
        .creation-div{
            display: flex;
            flex-direction: column;
        }
        .creation-div>div{
            display: flex;
            flex-direction: row;
        }
        .creation-div>div>*{
            margin: 1%;
        }
        #username{
            margin: 0.6%;
            width: 15%;
        }
        #create-btn, #clear-btn{
            margin: 1% 0 0 1%;
        }
        #priority-selection{
            margin: 0.6%;
            width: 5%;
        }
    </style>
</head>
<body>
    <header>
        <a href="home">Home</a><hr><a href="create-task">Add new Task</a><hr><a href="tasks-list">Show all Tasks</a>
    </header>

    <h1>Create new Task</h1>
    <% if (request.getAttribute("message") != null) { %>
    <p><%= request.getAttribute("message") %></p>
    <% } %>
    <form action="/create-task" method="post">
        <div class="creation-div">
            <div>
                <p>Name: </p><input type="text" id="username" name="taskname">
            </div>
            <div>
                <p>Priority: </p>
                <select name="priority" id="priority-selection">
                    <option value="LOW">Low</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="HIGH">High</option>
                </select>
            </div>
            <div>
                <input type="submit" id="create-btn" value="Create">
                <input type="reset" id="clear-btn" value="Clear">
            </div>
        </div>
    </form>

</body>
</html>
