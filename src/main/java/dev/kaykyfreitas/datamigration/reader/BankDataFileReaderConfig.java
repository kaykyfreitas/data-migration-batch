package dev.kaykyfreitas.datamigration.reader;

import dev.kaykyfreitas.datamigration.entity.BankData;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BankDataFileReaderConfig {

    @Bean
    public FlatFileItemReader<BankData> bankDataFileReader() {
        return new FlatFileItemReaderBuilder<BankData>()
                .name("bankDataFileReader")
                .resource(new FileSystemResource("files/bank_data.csv"))
                .delimited()
                .names("personId", "agency", "account", "bank", "id")
                .addComment("--")
                .targetType(BankData.class)
                .build();
    }

}
