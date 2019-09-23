package com.babusa.learn;

import com.babusa.learn.domain.EventStream;
import com.babusa.learn.domain.Profile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserProfilePrompt extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Profile profile = new Profile("santhosh", new String[]{"trekking", "volley ball"});
        EventStream es = new EventStream("FASTLOAD_VALIDATION", profile);
        System.out.println("push into queue - UserProfilePrompt");
        ServerEventDataQueue.push(es);
        req.getRequestDispatcher("/user-profile-prompt.jsp").forward(req, res);
    }
}
