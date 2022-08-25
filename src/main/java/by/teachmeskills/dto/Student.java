package by.teachmeskills.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {

    private int id;
    private String name;
    private int groupNumber;
}
