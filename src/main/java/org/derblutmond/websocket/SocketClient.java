package org.derblutmond.websocket;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.derblutmond.log.ConsoleLogger;
import org.derblutmond.websocket.interfaces.SocketInterface;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class SocketClient implements SocketInterface {
    final String url;
    private final List<String> namespaces = new ArrayList<>();
    private final Map<String, io.socket.client.Socket> socketMap = new HashMap<String, io.socket.client.Socket>();

    ConsoleLogger console = new ConsoleLogger(SocketClient.class);
    public SocketClient(String url) {
        this.url = url;
    }

    @Override
    public void connect() {
        if(!(namespaces.size() > 0)) {
            console.log(Level.WARNING, "Please add at least one namespace");
        }else{
            for(String namespace: namespaces) {
                Socket socket = IO.socket(URI.create(url + namespace));
                socketMap.put(namespace, socket);
                socket.on(Socket.EVENT_CONNECT,new OnConnect(namespace));
                socket.connect();
            }
        }
    }

    public void addNamespace(String namespace, Boolean add) {
        namespaces.add(namespace);
        if(Boolean.TRUE.equals(add)) {
            Socket socket = IO.socket(URI.create(url + namespace));
            socketMap.put(namespace, socket);
        }
    }

    public void addEvent(String namespace, String event, Emitter.Listener eventHandler) {
        socketMap.get(namespace).on(event, eventHandler);
    }

    @Override
    public void disconnect() {

    }
}
