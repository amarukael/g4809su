package model.ppob.inquiry.additionaldata2.plnpost;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlnpostInqAdd2 {
    @JsonProperty("id_pelanggan")
    private String idPelanggan;

    @JsonProperty("nama_pelanggan")
    private String namaPelanggan;

    @JsonProperty("nominal")
    private String nominal;

    @JsonProperty("biaya_admin")
    private String biayaAdmin;

    @JsonProperty("periode_bln_thn")
    private String periodeBlnThn;

    @JsonProperty("jumlah_tagihan")
    private String jumlahTagihan;

    @JsonProperty("total_bayar")
    private String totalBayar;
}
