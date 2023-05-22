package org.derblutmond.websocket;

import io.socket.emitter.Emitter;
import org.derblutmond.log.ConsoleLogger;

import java.util.logging.Level;

public record OnConnect(String namespace, SocketClient socketClient) implements Emitter.Listener {

    @Override
    public void call(Object... args) {
        ConsoleLogger console = new ConsoleLogger(OnConnect.class);
        socketClient.socketMap.get(namespace).setConnected(true);
        console.log(Level.INFO, "Connected to " + namespace);
    }
}
