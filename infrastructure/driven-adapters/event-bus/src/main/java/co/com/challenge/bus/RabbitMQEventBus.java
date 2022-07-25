package co.com.challenge.bus;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.infraestructure.bus.EventBus;
import co.com.sofka.infraestructure.bus.notification.ErrorNotification;
import co.com.sofka.infraestructure.bus.notification.SuccessNotification;
import co.com.sofka.infraestructure.bus.serialize.ErrorNotificationSerializer;
import co.com.sofka.infraestructure.bus.serialize.SuccessNotificationSerializer;
import co.com.sofka.infraestructure.event.ErrorEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RabbitMQEventBus implements EventBus {
    public static final String EXCHANGE = "juego-heroes";
    private static final String ORIGIN = "juego";
    private static final String TOPIC_ERROR = "juego.error";
    private static final String TOPIC_BUSINESS_ERROR = "juego.business.error";
    private static final Logger logger = Logger.getLogger(RabbitMQEventBus.class.getName());
    private final RabbitTemplate rabbitTemplate;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RabbitMQEventBus(RabbitTemplate rabbitTemplate, MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        var notification = SuccessNotification.wrapEvent(ORIGIN, domainEvent);
        var notificationSerialization = SuccessNotificationSerializer.instance().serialize(notification);
        rabbitTemplate.convertAndSend(EXCHANGE, domainEvent.type, notificationSerialization.getBytes());
        mongoTemplate.save(domainEvent, domainEvent.type);
    }

    @Override
    public void publishError(ErrorEvent errorEvent) {
        if (errorEvent.error instanceof BusinessException) {
            publishToTopic(TOPIC_BUSINESS_ERROR, errorEvent);
        } else {
            publishToTopic(TOPIC_ERROR, errorEvent);
        }
        logger.log(Level.SEVERE, errorEvent.error.getMessage());
    }

    public void publishToTopic(String topic, ErrorEvent errorEvent) {
        var notification = ErrorNotification.wrapEvent(ORIGIN, errorEvent);
        var notificationSerialization = ErrorNotificationSerializer.instance().serialize(notification);
        rabbitTemplate.convertAndSend(EXCHANGE, errorEvent.identify, notificationSerialization.getBytes());
        logger.warning("###### Error Event published to " + "getMessage = ".concat(errorEvent.error.getMessage()).concat(", LocalizedMessage = ").concat(errorEvent.error.getStackTrace().toString()));
    }
}
