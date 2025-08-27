package com.math012.order_service.infra.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffSetReset;

    @Value("${spring.kafka.producer.order.requesting.product.topic}")
    private String orderRequestingProduct;

    @Value("${spring.kafka.producer.order.send.order.for.payment.topic}")
    private String sendOrderForPayment;

    @Value("${spring.kafka.producer.order.send.order.payment.success.topic}")
    private String orderConfirmedPayment;

    @Value("${spring.kafka.producer.order.rollback.product.topic}")
    private String rollbackProduct;


    private Map<String, Object> consumerProps(){
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffSetReset);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerProps());
    }

    private Map<String, Object> producerProps(){
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String,String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic orderRequestingProductTopic(){
        return TopicBuilder.name(orderRequestingProduct)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic sendOrderForPaymentTopic(){
        return TopicBuilder.name(sendOrderForPayment)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic rollbackProductTopic(){
        return TopicBuilder.name(rollbackProduct)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderConfirmedPaymentTopic(){
        return TopicBuilder.name(orderConfirmedPayment)
                .partitions(1)
                .replicas(1)
                .build();
    }
}