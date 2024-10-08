package model.tokopedia;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Attribute {
    public String product_code;
    public String client_number;
    public String amount;

    public Attribute(String product_code, String client_number, String amount){
        this.product_code = product_code;
        this.client_number = client_number;
        this.amount = amount;
    }

    // Constructors for optional parameters
    public Attribute(String product_code){
        this.product_code = product_code;
    }

//    public Attribute(String product_code, String client_number){
//        this.product_code = product_code;
//        this.client_number = client_number;
//    }
}
