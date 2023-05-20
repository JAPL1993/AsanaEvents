package org.derblutmond;

import org.derblutmond.log.ConsoleLogger;
import org.derblutmond.websocket.SocketClient;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        ConsoleLogger console = new ConsoleLogger(Main.class);
        SocketClient socket = new SocketClient("http://162.214.164.60:8085/");
        socket.addNamespace("microsip", false);
        socket.connect();
    }
}