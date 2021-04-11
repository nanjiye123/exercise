package com.exercise.testspringboot;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="userServlet",urlPatterns = "/v/user")
public class UserServlet extends HttpServlet  {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("user servlet");
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
