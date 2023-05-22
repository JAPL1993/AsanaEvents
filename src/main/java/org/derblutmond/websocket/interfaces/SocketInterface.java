package org.derblutmond.websocket.interfaces;

public interface SocketInterface {
    public void connect();
    public void disconnect();

    public void emit(String namespace,String event, Object data);
     public void deleteEvent(String namespace,String event);
}
