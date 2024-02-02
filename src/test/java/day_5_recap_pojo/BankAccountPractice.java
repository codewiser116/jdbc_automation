package day_5_recap_pojo;

import com.github.javafaker.Faker;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //  STEP-1  It will run by ascending order
public class BankAccountPractice {
    Faker faker = new Faker();  //  STEP-2
    static String bankId= "";


    @Test
    public void test_1_createNewBankAccount(){
      //   getToken()   import static utilities.CashwiseAuthorization.getToken;  STEP-3

        // https://backend.cashwise.us   /api/myaccount/bankaccount
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        /*
        Serialization ==> Java request body => JSON object
         */
        RequestBody requestBody = new RequestBody(); // STEP-4  go inside RequestBody class and declare your variables



    }




}
