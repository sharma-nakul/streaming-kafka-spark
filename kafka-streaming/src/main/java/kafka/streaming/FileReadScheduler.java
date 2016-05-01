package kafka.streaming;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Naks on 27-Apr-16.
 * Schedule the file reading - act like a simulator for live sensor data
 */

@Component
class FileReadScheduler {
    private static final Logger logger = Logger.getLogger(FileReadScheduler.class);

    @Scheduled(fixedRate = 1200)
    public void reportExpiredPolls() {
        try {
            FileHandling fh = new FileHandling();
            fh.produceMessages();
        } catch (NullPointerException e) {
            logger.info("NullPointException: Error sending file to Kafka producer list");
        } catch (Exception e) {
            logger.info("Exception: " + e.getMessage());
        }
    }
}
