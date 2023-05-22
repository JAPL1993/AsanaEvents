package org.derblutmond.websocket;

import io.socket.emitter.Emitter;
import org.derblutmond.log.ConsoleLogger;

import java.util.logging.Level;

public class InsertFolio implements Emitter.Listener {
    ConsoleLogger console = new ConsoleLogger(InsertFolio.class);
    @Override
    public void call(Object... args) {
        console.log(Level.INFO,"data: "+args[0]);
    }
}
