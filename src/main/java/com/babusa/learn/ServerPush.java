package com.babusa.learn;

import com.babusa.learn.domain.EventStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class ServerPush extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set header fields
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/event-stream");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        String eventStream;
        PrintWriter writer = response.getWriter();

        ObjectMapper json = new ObjectMapper();

        while(true) {
            List<EventStream> eventQueue = ServerEventDataQueue.get();

            int count = eventQueue.size();
            System.out.println("start size: " + eventQueue.size());
            if (count == 0) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Iterator<EventStream> eventQueueIterator = eventQueue.iterator();
            while (eventQueueIterator.hasNext()) {
                EventStream eQueue = eventQueueIterator.next();
                writer.write("event: " + eQueue.getEventType() + "\n");
                writer.write("data: " + json.writeValueAsString(eQueue.getData()) + "\n\n");
                eventQueueIterator.remove();
            }

            writer.flush();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.close();
        }
    }


}
