package day_6_api_runner;

import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;

public class SellersApiRunner {

    @Test
    public void test_1_getListOfSellers(){
    //https://backend.cashwise.us  /api/myaccount/sellers/all
        String path = "/api/myaccount/sellers/all";

        /** TASK
         * use APIRunner and hit GET request
         * Create loop and Assert values:
         *          private int  seller_id;
         *          private String seller_name;
         */

        APIRunner.runGET(  path );
        CustomResponse[] customResponses = APIRunner.getCustomResponseArray();

        for ( int i=0;  i <  customResponses.length; i++  ){

                System.out.println("Seller ID: " + customResponses[i].getSeller_id());
                Assert.assertNotNull(customResponses[i].getSeller_id());

                System.out.println("Seller name" + customResponses[i].getSeller_name());
                Assert.assertNotNull(  customResponses[i].getSeller_name()  );



        }


    }






}
