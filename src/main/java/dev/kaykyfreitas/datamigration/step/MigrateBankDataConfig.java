package dev.kaykyfreitas.datamigration.step;

import dev.kaykyfreitas.datamigration.entity.BankData;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@RequiredArgsConstructor
public class MigrateBankDataConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrateBankDataStep(ItemReader<BankData> bankDataFileReader, ItemWriter<BankData> bankDataDBWriter) {
        return stepBuilderFactory
                .get("migrateBankDataStep")
                .<BankData, BankData> chunk(5000)
                .reader(bankDataFileReader)
                .writer(bankDataDBWriter)
                .build();
    }

}
