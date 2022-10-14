package dev.kaykyfreitas.datamigration.writer;

import dev.kaykyfreitas.datamigration.entity.Person;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class PersonDBWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Person> personDBWriter(@Qualifier("appDataSource")DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                .sql("INSERT INTO person (id, name, email, birth, age) values (?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<Person> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<Person>() {
            @Override
            public void setValues(Person person, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, person.getId());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.setDate(4, new Date(person.getBirth().getTime()));
                preparedStatement.setInt(5, person.getAge());
            }
        };
    }

}
