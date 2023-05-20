package org.derblutmond.websocket;

import io.socket.emitter.Emitter;
import org.derblutmond.log.ConsoleLogger;

import java.util.logging.Level;

public class OnConnect implements Emitter.Listener {
    final public String namespace;

    public OnConnect(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public void call(Object... args) {
        ConsoleLogger console = new ConsoleLogger(OnConnect.class);
        console.log(Level.INFO, "Connected to "+namespace);
    }
}
