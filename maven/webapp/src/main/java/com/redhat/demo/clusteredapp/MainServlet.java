package com.redhat.demo.clusteredapp;


import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/")
public class MainServlet extends HttpServlet {


    @Inject
    SessionCounter sessionCounter;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
                String inputCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\n";
       int stringLength = 200000;
      
       //response.getWriter().println("Hello, my node name is " + System.getProperty("jboss.node.name") + ", session counter is " + sessionCounter.getAndIncrement());
       response.getWriter().println("Randomly generated string to drive up CPU usage is: " + generateRandomString(inputCharacters, stringLength));
        // response.getWriter().println("Hello, my node name is " + System.getProperty("jboss.node.name") + ", session counter is " + sessionCounter.getAndIncrement());
    }

    public static String generateRandomString(String inputCharacters, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(inputCharacters.charAt(random.nextInt(inputCharacters.length())));
        }
        return stringBuilder.toString();
    }
}
