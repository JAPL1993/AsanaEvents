package org.derblutmond.websocket;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.derblutmond.log.ConsoleLogger;
import org.derblutmond.websocket.interfaces.Namespace;
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
    public final HashMap<Object, Namespace> socketMap = new HashMap<Object, Namespace>();

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
                Namespace connection = new org.derblutmond.websocket.Namespace(socket);
                socketMap.put(namespace, connection);
                socket.on(Socket.EVENT_CONNECT,new OnConnect(namespace, this));
                socket.connect();
            }
        }
    }

    public void addNamespace(String namespace, Boolean add) {
        namespaces.add(namespace);
        if(Boolean.TRUE.equals(add)) {
            Socket socket = IO.socket(URI.create(url + namespace));
            Namespace connection = new org.derblutmond.websocket.Namespace(socket);
            socketMap.put(namespace, connection);
            socket.on(Socket.EVENT_CONNECT,new OnConnect(namespace, this));
        }
    }

    public void addEvent(String namespace, String event, Emitter.Listener eventHandler) {
        socketMap.get(namespace).getSocket().on(event, eventHandler);
    }

    @Override
    public void disconnect() {
        for(Map.Entry<Object, Namespace> entry: socketMap.entrySet()) {
            org.derblutmond.websocket.Namespace namespace = (org.derblutmond.websocket.Namespace) entry.getValue();
            namespace.setConnected(false);
            namespace.getSocket().off();
            namespace.getSocket().disconnect();
        }
    }

    @Override
    public void emit(String namespace, String event, Object data) {
    if(socketMap.get(namespace).isConected()) {
        socketMap.get(namespace).getSocket().emit(event,data);
    }
    }

    @Override
    public void deleteEvent(String namespace, String event) {
        socketMap.get(namespace).getSocket().off(event);
    }
}
