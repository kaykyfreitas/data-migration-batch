package dev.kaykyfreitas.datamigration.writer;

import dev.kaykyfreitas.datamigration.entity.Person;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class InvalidPersonFileWriterConfig {

    @Bean
    public FlatFileItemWriter<Person> invalidPersonFileWriter() {
        return new FlatFileItemWriterBuilder<Person>()
                .name("invalidPersonFileWriter")
                .resource(new FileSystemResource("files/invalid_person.csv"))
                .delimited()
                .names("id")
                .build();
    }

}
