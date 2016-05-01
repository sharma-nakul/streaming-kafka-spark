package kafka.streaming;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kafka.model.PublicWeatherNC;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Naks on 27-Apr-16.
 * File operations; reading sensor data
 */

@Component
class FileHandling {

    private KafkaConfig kafka;
    private KafkaProducer producer;
//    kafka-streaming\src\main\resources\input_file.txt
    private static final String INPUT_FILE = "kafka-streaming\\src\\main\\resources\\input_file.txt";

    FileHandling() {
        producer = new KafkaProducer();
        kafka = new KafkaConfig("weather_north_california");
    }

    void produceMessages() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line = br.readLine();
            while (line != null) {
                /*String json=csvToJson(line);
                producer.send(kafka.getKafkaTopic(), json);*/
                producer.send(kafka.getKafkaTopic(), line);
                line = br.readLine();
                Thread.sleep(2000); // wait for 2 seconds before reading next line
            }
           /* PrintWriter writer = new PrintWriter(INPUT_FILE);
            writer.print("");
            writer.close();*/
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    private String csvToJson(String csvLine){
        try
        {
            String[] s=csvLine.split(",");
            PublicWeatherNC pw=new PublicWeatherNC();
            pw.setCounty(s[0]);
            pw.setCity(s[1]);
            pw.setYear(s[2]);
            pw.setLowestTemp(Integer.parseInt(s[3]));
            pw.setHighestTemp(Integer.parseInt(s[4]));
            /*pw.setWarmestTemp(Integer.parseInt(s[5]));
            pw.setColdestTemp(Integer.parseInt(s[6]));
            pw.setAverageMaximumTemp(Double.parseDouble(s[7]));
            pw.setAverageMinimumTemp();*/

            Gson gson=new GsonBuilder().create();
            return gson.toJson(pw);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

}
