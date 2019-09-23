package com.babusa.learn;

import com.babusa.learn.domain.Profile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserProfileHandler extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = (String) req.getParameter("user-name");
        String[] userHobbies = ((String) req.getParameter("user-hobbies")).split(", ?");
        Profile profile = new Profile(userName, userHobbies);
        req.setAttribute("profile", profile);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userprofile");
        dispatcher.forward(req,res);
    }
}
