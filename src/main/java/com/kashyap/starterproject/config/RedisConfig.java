package com.kashyap.starterproject.config;

import com.kashyap.starterproject.entity.Department;
import com.kashyap.starterproject.springsubscriber.LogLevelSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379); // Adjust hostname and port if necessary
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Department> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Department> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic("log-level-updates"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(LogLevelSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "onMessageReceived");
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("log-level-updates");
    }
}
