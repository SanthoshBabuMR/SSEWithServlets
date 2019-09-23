package com.babusa.learn;

import com.babusa.learn.domain.EventStreamMessage;
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
        Iterator<EventStreamMessage> eventQueueIterator;
        List<EventStreamMessage> messages;
        boolean hasMessagesToPush = false;

        while(true) {
            System.out.println(">> Do we have jobs to process?");
            messages = ServerEventDataQueue.get();
            hasMessagesToPush = messages.size() > 0;

            if (hasMessagesToPush) {
                System.out.println("++ Yep. Awaken to start processing eventQueue");
                synchronized (messages) {
                    eventQueueIterator = messages.iterator();
                    while (eventQueueIterator.hasNext()) {
                        EventStreamMessage eQueue = eventQueueIterator.next();
                        writer.write("event: " + eQueue.getEventType() + "\n");
                        writer.write("data: " + json.writeValueAsString(eQueue.getData()) + "\n\n");
                        eventQueueIterator.remove();
                    }
                    writer.flush();
                }
//              response.flushBuffer();
                try {
                    System.out.println("Good Job!! Take some rest :)");
                    System.out.println("");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writer.close();
            }
            else {
                System.out.println("-- Nope.");
                try {
                    System.out.println("Go sleep until needed.");
                    System.out.println("");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
