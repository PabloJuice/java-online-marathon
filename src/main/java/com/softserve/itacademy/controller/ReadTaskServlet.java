package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/read-task")
public class ReadTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    @Override
    public void init() throws ServletException {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Task task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("task",task);
        request.setAttribute("isValidTask", task != null);
        request.setAttribute("requestURI", request.getRequestURI());
        request.getRequestDispatcher("/WEB-INF/pages/read-task-page.jsp").forward(request,response);
    }
}
