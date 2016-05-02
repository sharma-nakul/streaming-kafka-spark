package kafka.streaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by Naks on 27-Apr-16.
 * Configuration class for kafka
 */

public class KafkaConfig {

    @Autowired
    private Environment environment;

    private String kafkaBroker;
    private String kafkaTopic;

    private static final String HOSTNAME = "localhost";
    private static final String PORT = "9094";

    public KafkaConfig(String topic) {
        this.kafkaBroker = HOSTNAME + ":" + PORT;
        this.kafkaTopic = topic;
    }

    public KafkaConfig() {
        this.kafkaBroker = HOSTNAME + ":" + PORT;
        this.kafkaTopic = "test";
    }


    public String getKafkaBroker() {
        return kafkaBroker;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }
}
