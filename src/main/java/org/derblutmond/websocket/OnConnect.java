package org.derblutmond.websocket;

import io.socket.emitter.Emitter;
import org.derblutmond.log.ConsoleLogger;

import java.util.logging.Level;

public record OnConnect(String namespace) implements Emitter.Listener {

    @Override
    public void call(Object... args) {
        ConsoleLogger console = new ConsoleLogger(OnConnect.class);
        console.log(Level.INFO, "Connected to " + namespace);
    }
}
