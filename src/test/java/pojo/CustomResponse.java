package pojo;

import lombok.Data;

@Data
public class CustomResponse { // ResponseBody

    /**
     * CustomResponse==> we use this class to read value of our Response
     */

    // Category-controller
    private int category_id;
    private String category_title;
    private String category_description;







}
