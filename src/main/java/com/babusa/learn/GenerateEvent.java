package com.babusa.learn;

import com.babusa.learn.domain.EventStream;
import com.babusa.learn.domain.Profile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GenerateEvent extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html");

        String index = req.getParameter("index");
        if (index == null) { index = ""; }
        Profile profile = new Profile(index + " babu", new String[]{"trekking", "volley ball", "singing"});
        EventStream es = new EventStream("FASTLOAD_VALIDATION", profile);
        System.out.println("push into queue - gen event");
        ServerEventDataQueue.push(es);
        res.getWriter().append("Event Generated");
    }
}

