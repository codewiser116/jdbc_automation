package pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
// Automatically will generate getter and setter method for me
public class RequestBody {
    /**
     *  RequestBody => purpose of this class is described all variables from our request body
     *
     */

    private String email;
    private String password;


    /***
     * Category-controller
     */
    private String category_title;
    private String category_description;
    private boolean flag;

}



    /*
    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     */

