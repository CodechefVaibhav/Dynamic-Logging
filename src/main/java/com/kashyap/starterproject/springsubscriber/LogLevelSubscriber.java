package com.kashyap.starterproject.springsubscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.logging.LogLevel;

@Component
public class LogLevelSubscriber {

    @Autowired
    private LoggingSystem loggingSystem;

    @EventListener
    public void onMessageReceived(String payload) {
        String[] parts = payload.split(":");
        if (parts.length == 2) {
            String loggerName = parts[0];
            LogLevel level = LogLevel.valueOf(parts[1].toUpperCase());
            loggingSystem.setLogLevel(loggerName, level);
            System.out.println("Updated log level for " + loggerName + " to " + level);
        } else {
            System.err.println("Invalid message format: " + payload);
        }
    }
}
