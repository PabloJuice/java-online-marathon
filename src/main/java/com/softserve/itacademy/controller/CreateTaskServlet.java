package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/create-task" )
public class CreateTaskServlet  extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/create-task-page.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String taskName = request.getParameter("taskname");
        Priority priority = Priority.valueOf(request.getParameter("priority"));
        Task task = new Task(taskName, priority);
        if (taskRepository.create(task)){
            response.sendRedirect("/tasks-list");
        }
        else {
            Task.deleteInstance();
            request.setAttribute("message", "Task with given name already exists!");
            request.getRequestDispatcher("/WEB-INF/pages/create-task-page.jsp").forward(request, response);
        }
    }

}