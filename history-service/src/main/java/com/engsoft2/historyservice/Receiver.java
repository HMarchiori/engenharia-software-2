package com.engsoft2.historyservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Receiver {

    private static final Logger logger = LogManager.getLogger(Receiver.class);

    private final HistoryRepository repository;

    public Receiver(HistoryRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.queueName)
    public void receive(HistoryDTO dto) {
        logger.info("Mensagem recebida do RabbitMQ: {}", dto);

        History history = new History(
                dto.getFrom(),
                dto.getTo(),
                LocalDateTime.now()
        );

        repository.save(history);
    }
}