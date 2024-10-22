package utility;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Rand {
	private final Random r = new Random();
	// private String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
	private final SimpleDateFormat formatter3 = new SimpleDateFormat("HHmmss");
	private final SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat formatter5 = new SimpleDateFormat("HH:mm:ss");
	private final SimpleDateFormat formatter6 = new SimpleDateFormat("yyyyMMddHHmm");
	private final SimpleDateFormat formatter7 = new SimpleDateFormat("yyMMddHHmmssSSS");
	private final SimpleDateFormat formatter8 = new SimpleDateFormat("yyMMdd");
	private static final String alfaNum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String num = "0123456789";
	private static SecureRandom rnd = new SecureRandom();

	public String getRandomTrxId() {
		String trxid = "";
		// trxid = trxid + words.charAt(r.nextInt(words.length()));
		trxid = trxid + ("IDS");

		Date date = new Date();
		trxid = trxid + formatter2.format(date) + formatter3.format(date);

		trxid = trxid + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10));

		return trxid;
	}

	public String getRandomTrxId(String prefix, String suffix) {
		prefix = prefix == null ? "" : prefix;
		suffix = suffix == null ? "" : suffix;

		String trxid = "";
		// trxid = trxid + words.charAt(r.nextInt(words.length()));

		Date date = new Date();
		trxid = prefix + trxid + formatter2.format(date) + formatter3.format(date);

		trxid = trxid + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10)) + suffix;

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
		// trackingref = trackingref + words.charAt(r.nextInt(words.length()));
		trackingref = trackingref + ("IDS");

		Date date = new Date();
		trackingref = trackingref + formatter2.format(date) + formatter3.format(date);

		trackingref = trackingref + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10));

		return trackingref;
	}

	public String getRandomRef2(Integer lenghtData) {
		String trackingref = "";
		// trackingref = trackingref + words.charAt(r.nextInt(words.length()));
		// trackingref = trackingref + ("IDS");

		Date date = new Date();
		trackingref = trackingref + formatter2.format(date);

		trackingref = trackingref + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10));

		return trackingref;
	}

	public String getTimeStamp() {
		String timestamp = "";
		Date date = new Date();

		timestamp = formatter4.format(date) + "T" + formatter5.format(date) + "+07:00";

		return timestamp;
	}

	public String getExternalID(int flg) {
		String externalID = "";
		Date date = new Date();

		if (flg == 0)
			externalID = formatter7.format(date);
		if (flg == 1)
			externalID = formatter8.format(date) + randStrNum(6);
		else
			externalID = randStrNum(6) + formatter8.format(date);

		return externalID;
	}

	public String getOriginalPartnerReferenceNo() {
		String originalPartnerReferenceNo = "";
		Date date = new Date();

		originalPartnerReferenceNo = formatter6.format(date) + formatter3.format(date);

		return originalPartnerReferenceNo;
	}

	public String randStrAlfaNum(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(alfaNum.charAt(rnd.nextInt(alfaNum.length())));

		return sb.toString();
	}

	public String randStrNum(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(num.charAt(rnd.nextInt(num.length())));

		return sb.toString();
	}
}
