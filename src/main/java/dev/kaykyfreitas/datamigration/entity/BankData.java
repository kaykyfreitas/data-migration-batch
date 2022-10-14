package dev.kaykyfreitas.datamigration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankData {

    private Integer id;
    private Integer personId;
    private Integer agency;
    private Integer account;
    private Integer bank;

}
