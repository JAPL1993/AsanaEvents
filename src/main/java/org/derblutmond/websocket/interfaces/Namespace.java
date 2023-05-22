package org.derblutmond.websocket.interfaces;

public interface Namespace {
    Boolean isConected();
    void setConnected(Boolean connected);
    io.socket.client.Socket getSocket();
}
