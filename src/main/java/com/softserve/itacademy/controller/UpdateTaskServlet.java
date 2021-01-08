package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/edit-task")
public class UpdateTaskServlet extends HttpServlet {
    private TaskRepository taskRepository;
    private Task task;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("task", task);
        request.setAttribute("isValidTask", task != null);
        request.setAttribute("requestURI", request.getRequestURI());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/update-task-page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        task.setTitle(request.getParameter("taskname"));
        task.setPriority(Priority.valueOf(request.getParameter("priority")));
        taskRepository.update(task);
        response.sendRedirect("/tasks-list");
    }
}