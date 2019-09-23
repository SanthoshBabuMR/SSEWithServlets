package com.babusa.learn;

import com.babusa.learn.domain.EventStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerEventDataQueue {

    private static List<EventStream> dataQueue = new ArrayList<EventStream>();

    public static List<EventStream> get() {
        return dataQueue;
    }

    public static void push(EventStream data) throws IOException{
        dataQueue.add(data);
    }

}
