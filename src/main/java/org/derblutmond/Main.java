package org.derblutmond;

import org.derblutmond.log.ConsoleLogger;
import org.derblutmond.websocket.InsertFolio;
import org.derblutmond.websocket.SocketClient;
import org.derblutmond.websocket.interfaces.Namespace;

import java.util.Map;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

        ConsoleLogger console = new ConsoleLogger(Main.class);
        SocketClient socket = new SocketClient("http://localhost:8086/");
        socket.addNamespace("microsip", false);
        Integer socketSize = socket.socketMap.size();
        Integer socketConnected = 0;
        socket.connect();
        while (!socketConnected.equals(socketSize)) {
            for (Map.Entry<Object, Namespace> entry : socket.socketMap.entrySet()) {
                Namespace namespace = entry.getValue();
                if (Boolean.TRUE.equals(namespace.isConected())) {
                    socketConnected += 1;
                }
            }
        }
        console.log(Level.INFO,"All namespaces are connected");
        socket.addEvent("microsip","insertFolio", new InsertFolio());
    }
}