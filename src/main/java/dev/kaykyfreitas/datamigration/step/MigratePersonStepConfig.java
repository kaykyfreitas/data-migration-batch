package dev.kaykyfreitas.datamigration.step;

import dev.kaykyfreitas.datamigration.entity.Person;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class MigratePersonStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migratePersonStep(
            ItemReader<Person> personFileReader,
            ClassifierCompositeItemWriter<Person> personClassifierWriter,
            FlatFileItemWriter<Person> invalidPersonFileWriter) {
        return stepBuilderFactory
                .get("migratePersonStep")
                .<Person, Person> chunk(5000)
                .reader(personFileReader)
                .writer(personClassifierWriter)
                .stream(invalidPersonFileWriter)
                .build();
    }

}
