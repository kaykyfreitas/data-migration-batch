package dev.kaykyfreitas.datamigration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Integer id;
    private String name;
    private String email;
    private Date birth;
    private Integer age;

    public boolean isValid() {
        return !Strings.isBlank(name) && !Strings.isBlank(email) && birth != null;
    }

}
