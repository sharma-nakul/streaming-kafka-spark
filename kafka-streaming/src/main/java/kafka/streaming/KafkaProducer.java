package kafka.streaming;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Naks on 27-Apr-16.
 * Kafka producer to read file
 */

@Component
public class KafkaProducer {

    //private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private Environment environment;

    private Producer<String, String> producer;


    public KafkaProducer() {

        // Setup Kafka Configuration
        KafkaConfig kafkaConfig = new KafkaConfig();
        Properties props = new Properties();
        props.put("metadata.broker.list", kafkaConfig.getKafkaBroker());
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("producer.type", "async");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<>(config);
    }


    void send(String topic, String msg) {
        KeyedMessage<String, String> data = new KeyedMessage<>(topic, msg);
        producer.send(data);
    }

    void close() {
        producer.close();
    }

}
