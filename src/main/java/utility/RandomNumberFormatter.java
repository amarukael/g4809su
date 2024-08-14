package utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomNumberFormatter {
    private Random r = new Random();
    //	private String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat formatter3 = new SimpleDateFormat("HHmmss");
    private SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formatter5 = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat formatter6 = new SimpleDateFormat("yyyyMMddHHmm");

    public String getRandomTrxId() {
        String trxid = "";
//		trxid = trxid + words.charAt(r.nextInt(words.length()));
        trxid = trxid + ("IDS");

        Date date = new Date();
        trxid = trxid + formatter2.format(date) + formatter3.format(date);

        trxid = trxid + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10));

        return trxid;
    }

    public String getCurrentDate() {
        String trxdate = "";
        Date date = new Date();

        trxdate = formatter.format(date);

        return trxdate;
    }

    public String getDateTimeReq() {
        String trxdate = "";
        Date date = new Date();

        trxdate = formatter2.format(date) + formatter3.format(date);

        return trxdate;
    }

    public String getRandomRef() {
        String trackingref = "";
//		trackingref = trackingref + words.charAt(r.nextInt(words.length()));
        trackingref = trackingref + ("IDS");

        Date date = new Date();
        trackingref = trackingref + formatter2.format(date) + formatter3.format(date);

        trackingref = trackingref + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10));

        return trackingref;
    }

    public String getTimeStamp() {
        String timestamp = "";
        Date date = new Date();

        timestamp = formatter4.format(date)+"T"+formatter5.format(date)+"+07:00";

        return timestamp;
    }

    public String getExternalID() {
        String externalID = "";
        Date date = new Date();

        externalID = formatter6.format(date);

        return externalID;
    }

    public String getOriginalPartnerReferenceNo() {
        String originalPartnerReferenceNo = "";
        Date date = new Date();

        originalPartnerReferenceNo = formatter6.format(date)+formatter3.format(date);

        return originalPartnerReferenceNo;
    }

}
