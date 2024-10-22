package model.ppob.inquiry.additionaldata2.plnpost;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlnpostPayAdd2 {

    @JsonProperty("id_pelanggan")
    private String idPelanggan;

    @JsonProperty("nama_pelanggan")
    private String namaPelanggan;

    @JsonProperty("tarifdaya")
    private String tarifDaya;

    @JsonProperty("nominal")
    private String nominal;

    @JsonProperty("biaya_admin")
    private String biayaAdmin;

    @JsonProperty("periode_bln_thn")
    private String periodeBlnThn;

    @JsonProperty("total_bayar")
    private String totalBayar;

    @JsonProperty("stand_meter")
    private String standMeter;

    @JsonProperty("tanggal_lunas")
    private String tanggalLunas;

    @JsonProperty("pesan_biller")
    private String pesanBiller;

    @JsonProperty("reff")
    private String reff;

    @JsonProperty("pesan_tunggakan")
    private String pesanTunggakan;
}
