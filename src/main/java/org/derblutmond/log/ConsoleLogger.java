package org.derblutmond.log;

import org.derblutmond.log.interfaces.LoggerInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogger implements LoggerInterface {
    final Logger log;

    public ConsoleLogger(Class<?> clazz) {
        this.log = Logger.getLogger(clazz.getName());
    }

    public void log(Level level, String message) {
        log.log(level, message);
    }
}
