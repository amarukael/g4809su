package utility;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ConvertKey {
    public PrivateKey genPrivateKey(String privateKeyContent) {
        PrivateKey key = null;
        try {
            byte[] data = Base64.getDecoder().decode(privateKeyContent);

            /* Add PKCS#8 formatting */
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new ASN1Integer(0));
            ASN1EncodableVector v2 = new ASN1EncodableVector();
            v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
            v2.add(DERNull.INSTANCE);
            v.add(new DERSequence(v2));
            v.add(new DEROctetString(data));
            ASN1Sequence seq = new DERSequence(v);
            byte[] privKey = seq.getEncoded("DER");

            PKCS8EncodedKeySpec spec = new  PKCS8EncodedKeySpec(privKey);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            key = fact.generatePrivate(spec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return key;
    }
}
