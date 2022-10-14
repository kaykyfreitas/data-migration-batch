package dev.kaykyfreitas.datamigration.reader;

import dev.kaykyfreitas.datamigration.entity.Person;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.util.Date;

@Configuration
public class PersonFileReaderConfig {

    @Bean
    public FlatFileItemReader<Person> personFileReader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personFileReader")
                .resource(new FileSystemResource("files/person.csv"))
                .delimited()
                .names("name", "email", "birth", "age", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<Person> fieldSetMapper() {
        return new FieldSetMapper<Person>() {
            @Override
            public Person mapFieldSet(FieldSet fieldSet) throws BindException {
                Person person = new Person();
                person.setName(fieldSet.readString("name"));
                person.setEmail(fieldSet.readString("email"));
                person.setBirth(new Date(fieldSet.readDate("birth", "yyyy-MM-dd HH:mm:ss").getTime()));
                person.setAge(fieldSet.readInt("age"));
                person.setId(fieldSet.readInt("id"));
                return person;
            }
        };
    }

}
