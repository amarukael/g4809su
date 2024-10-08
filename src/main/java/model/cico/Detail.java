package model.cico;

public class Detail {
    private String TrxId;
    private String Token;
    private String noHP;
    private String Amount;

    public String getTrxId() {
        return TrxId;
    }

    public void setTrxId(String trxId) {
        TrxId = trxId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
