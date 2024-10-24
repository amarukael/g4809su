package model.ppob.inquiry;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResInquiry {
    private String trxid;
    private String trxdate;
    private String partnerid;
    private String productid;
    private String customerid;
    private String extendinfo;
    private String customername;
    private String totalamount;
    private List<T> additionaldata;
    private String rc;
    private String rcdesc;
    private String trackingref;
    private String terminalid;
    private String signature;
    private String additionaldatanew;
    private String additionaldata_new;
}
