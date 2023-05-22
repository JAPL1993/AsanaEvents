package org.derblutmond.websocket;

import io.socket.client.Socket;

public class Namespace implements org.derblutmond.websocket.interfaces.Namespace {
    private Boolean isConnected = false;
    private final Socket socket;

    public Namespace(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Boolean isConected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    @Override
    public Socket getSocket() {
        return socket;
    }
}
