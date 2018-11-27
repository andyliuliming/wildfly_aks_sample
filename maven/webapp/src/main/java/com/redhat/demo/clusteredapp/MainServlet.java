package com.redhat.demo.clusteredapp;


import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {


    @Inject
    SessionCounter sessionCounter;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("Hello, my node name is " + System.getProperty("jboss.node.name") + ", session counter is " + sessionCounter.getAndIncrement());
    }
}
