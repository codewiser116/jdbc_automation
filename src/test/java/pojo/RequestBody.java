package pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
// Automatically will generate getter and setter method for me
public class RequestBody {
    /**
     *  RequestBody => purpose of this class is described all variables from our request body
     *  We use this to set value our variables (Set all request body data)
     */
   //CashWiseAuthorization
    private String email;
    private String password;


    /***
     * Category-controller
     */
    private String category_title;
    private String category_description;
    private boolean flag;

    // Bank account Request body variables
    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private double balance;

   // Seller controller RequestBody variables
    private String company_name;
    private String seller_name;
    //private String email;
    private String phone_number;
    private String address;


    /**
     * {
     *     "company_name": "Apple1 inc",
     *     "seller_name": "Steve",
     *     "email": "jayszonq46@gmail.com",
     *     "phone_number": "1233454567",
     *     "address": "Monaco"
     * }
     */

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

