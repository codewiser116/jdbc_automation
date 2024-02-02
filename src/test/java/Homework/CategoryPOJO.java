package Homework;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // it will ignore all unknown key(variable)
public class CategoryPOJO {



    // Category-controller
    private int category_id;
    private String category_title;
    private String category_description;


}
