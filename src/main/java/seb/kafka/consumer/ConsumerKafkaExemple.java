package seb.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;
import java.util.Properties;

public class ConsumerKafkaExemple {

    public static void main(String[] args) {
       Properties props = new Properties();

       props.put("bootstrap.servers", "localhost:9092");
       props.put("group.id", "test group id");
       props.put("enable.auto.commit", "true");
       props.put("auto.commit.interval.ms", "1000");
       props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
       props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

       KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
       consumer.subscribe(List.of("test-autre2"));

       while (true) {
           ConsumerRecords<String, String> records = consumer.poll(100);
           for (ConsumerRecord<String, String> record : records){
               System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
           }
       }
    }
}
