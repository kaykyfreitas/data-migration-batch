package dev.kaykyfreitas.datamigration.writer;

import dev.kaykyfreitas.datamigration.entity.Person;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Person> personClassifierWriter(
            JdbcBatchItemWriter<Person> personDBWriter,
            FlatFileItemWriter<Person> invalidPersonFileWriter) {
        return new ClassifierCompositeItemWriterBuilder<Person>()
                .classifier(classifier(personDBWriter, invalidPersonFileWriter))
                .build();
    }

    private Classifier<Person, ItemWriter<? super Person>> classifier(
            JdbcBatchItemWriter<Person> personDBWriter,
            FlatFileItemWriter<Person> invalidPersonFileWriter) {
        return new Classifier<Person, ItemWriter<? super Person>>() {
            @Override
            public ItemWriter<? super Person> classify(Person person) {
                if(person.isValid()) {
                    return personDBWriter;
                } else {
                    return invalidPersonFileWriter;
                }
            }
        };
    }

}
