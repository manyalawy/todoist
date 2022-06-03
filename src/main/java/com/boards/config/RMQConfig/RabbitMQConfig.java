package com.boards.config.RMQConfig;

import org.springframework.amqp.core.Binding;

// import javax.naming.Binding;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String RPC_MESSAGE_QUEUE = "board_queue";
    public static final String RPC_REPLY_MESSAGE_QUEUE = "board_queue_reply";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    /** *
     * Configure the Send Message Queue
     */
    @Bean
    Queue boardMsgQueue() {
        return new Queue(RPC_MESSAGE_QUEUE);
    }
    /** *
     * Return Queue Configuration
     */
    @Bean
    Queue boardReplyQueue() {
        return new Queue(RPC_REPLY_MESSAGE_QUEUE);
    }
    /** *
     * Switch setting
     */
    @Bean
    TopicExchange boardTopicExchange() {
        return new TopicExchange(RPC_EXCHANGE);
    }
    /** *
     * Queuing and Switch Link Request
     */
    @Bean
    Binding boardMsgBinding() {
        return BindingBuilder.bind(boardMsgQueue())
                .to(boardTopicExchange())
                .with(RPC_MESSAGE_QUEUE);
    }
    /** *
     * Back to Queue and Switch Link
     */
    @Bean
    Binding boardReplyBinding() {
        return BindingBuilder.bind(boardReplyQueue())
                .to(boardTopicExchange())
                .with(RPC_REPLY_MESSAGE_QUEUE);
    }

}