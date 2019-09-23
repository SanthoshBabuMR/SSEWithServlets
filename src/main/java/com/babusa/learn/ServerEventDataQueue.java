package com.babusa.learn;

import com.babusa.learn.domain.EventStreamMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerEventDataQueue {

    private static List<EventStreamMessage> messages = Collections.synchronizedList(new ArrayList<EventStreamMessage>());

    public static List<EventStreamMessage> get() {
        return messages;
    }

    public static void push(EventStreamMessage data) throws IOException{
        synchronized (messages) {
            messages.add(data);
        }
    }

}
