package com.kashyap.starterproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

@RestController
@RequestMapping("/api/logs")
public class LogLevelController {

    @Autowired
    private LoggingSystem loggingSystem;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String REDIS_CHANNEL = "log-level-updates";

    /**
     * Set the log level for a specific logger.
     *
     * @param loggerName Logger name or package (e.g., "com.kashyap.starterproject").
     * @param level      Log level (e.g., INFO, DEBUG, WARN, etc.).
     * @return Status message.
     */
    /*@PostMapping("/set")
    public String setLogLevel(@RequestParam String loggerName, @RequestParam LogLevel level) {
        loggingSystem.setLogLevel(loggerName, level);
        return "Log level for '" + loggerName + "' set to " + level;
    }*/

    @PostMapping("/set")
    public String setLogLevel(@RequestParam String loggerName, @RequestParam LogLevel level) {
        loggingSystem.setLogLevel(loggerName, level);
        // Publish update to Redis
        redisTemplate.convertAndSend(REDIS_CHANNEL, loggerName + ":" + level);
        return "Log level for '" + loggerName + "' set to " + level;
    }

    /**
     * Get the current log level of a logger.
     *
     * @param loggerName Logger name or package (e.g., "com.kashyap.starterproject").
     * @return Current log level.
     */
    @GetMapping("/get")
    public String getLogLevel(@RequestParam String loggerName) {
        LoggerConfiguration config = loggingSystem.getLoggerConfiguration(loggerName);
        return "Current log level for '" + loggerName + "' is " + (config != null ? config.getEffectiveLevel() : "NOT FOUND");
    }

    /**
     * Reset the log level to the default level.
     *
     * @param loggerName Logger name or package (e.g., "com.kashyap.starterproject").
     * @return Status message.
     */
    @PostMapping("/reset")
    public String resetLogLevel(@RequestParam String loggerName) {
        loggingSystem.setLogLevel(loggerName, null); // Null resets to default level
        return "Log level for '" + loggerName + "' reset to default.";
    }
}
