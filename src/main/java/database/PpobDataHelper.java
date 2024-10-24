package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import constant.ConstantPpob;
import model.ppob.database.PpobTbInquiry;
import model.ppob.database.PpobTbLogpay;
import model.ppob.database.PpobTbRefund;
import model.ppob.database.PpobTbSuspect;

public class PpobDataHelper {
    static String dbEnvironmentName = ConstantPpob.dbEnvironmentName;
    static String environmentSvr = ConstantPpob.environmentSvr;

    private Connection getConnection() throws Exception {
        LoadDataSource ds = new LoadDataSource(dbEnvironmentName, environmentSvr);
        return ds.getConnection();
    }

    private String executeQuery(String sql, String... parameters) {
        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setString(i + 1, parameters[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getSecretKeyByPartnerId(String partnerIdString) {
        String strSQL = "SELECT secretkey FROM tb_m_agentppobv2 WHERE partnerid = ?";
        return executeQuery(strSQL, partnerIdString);
    }

    public PpobTbInquiry get_data_inquiry_by_trackingref(String trackingRef) {
        String strSQL = "SELECT * FROM tb_r_inquiry WHERE tracking_ref = ?";
        PpobTbInquiry tb_inquiry = null;

        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(strSQL)) {
            pstmt.setString(1, trackingRef);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tb_inquiry = new PpobTbInquiry(
                        rs.getString("tracking_ref"),
                        rs.getString("customerid"),
                        rs.getString("customername"),
                        rs.getBigDecimal("amount"),
                        rs.getString("partnerid"),
                        rs.getString("productid"),
                        rs.getString("createdtime"),
                        rs.getBigDecimal("nominal"),
                        rs.getBigDecimal("adminfee"),
                        rs.getInt("issync"),
                        rs.getString("extendinfo"),
                        rs.getString("hpno"),
                        rs.getString("rc"),
                        rs.getString("rcdesc"),
                        rs.getString("additionaldata"),
                        rs.getString("additionaldata_pay"),
                        rs.getString("email"),
                        rs.getString("oritransdate"),
                        rs.getString("customerid_2"),
                        rs.getString("customerid_3"),
                        rs.getString("reffno"),
                        rs.getString("reffno_2"),
                        rs.getString("reffno_3"),
                        rs.getString("switchid"),
                        rs.getString("customerid_1"),
                        rs.getString("response_biller"),
                        rs.getString("rc_biller"),
                        rs.getString("rcdesc_biller"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb_inquiry;
    }

    public PpobTbLogpay get_data_logpay_by_trackingref(String trackingRef) {
        String strSQL = "SELECT * FROM tb_r_logpaydata WHERE tracking_ref = ?";
        PpobTbLogpay tb_logpay = null;

        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(strSQL)) {
            pstmt.setString(1, trackingRef);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tb_logpay = new PpobTbLogpay(
                        rs.getString("trxid"),
                        rs.getString("tracking_ref"),
                        rs.getString("partnerid"),
                        rs.getString("customerid"),
                        rs.getBigDecimal("amount"),
                        rs.getString("receiptno"),
                        rs.getString("additionaldata"),
                        rs.getDate("transactiondate"),
                        rs.getDate("CreatedOn"),
                        rs.getString("productid"),
                        rs.getString("stan"),
                        rs.getString("extendinfo"),
                        rs.getString("orderid"),
                        rs.getString("switchid"),
                        rs.getString("supplierref"),
                        rs.getBigDecimal("nominal"),
                        rs.getBigDecimal("adminfee"),
                        rs.getDate("createdon_suspect"),
                        rs.getString("finished_by"),
                        rs.getString("prev_rc"),
                        rs.getString("prev_supplier_rc"),
                        rs.getString("prev_supplier_rcdesc"),
                        rs.getBigDecimal("admin_charge"),
                        rs.getInt("order_id_is_null"),
                        rs.getString("supplier_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb_logpay;
    }

    public PpobTbSuspect get_data_suspect_by_trackingref(String trackingRef) {
        String strSQL = "SELECT * FROM tb_r_logsuspectdata WHERE tracking_ref = ?";
        PpobTbSuspect tb_suspect = null;

        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(strSQL)) {
            pstmt.setString(1, trackingRef);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tb_suspect = new PpobTbSuspect(
                        rs.getString("trxid"),
                        rs.getString("tracking_ref"),
                        rs.getString("partnerid"),
                        rs.getString("customerid"),
                        rs.getBigDecimal("amount"),
                        rs.getString("receiptno"),
                        rs.getString("additionaldata"),
                        rs.getDate("transactiondate"),
                        rs.getDate("createdon"),
                        rs.getString("productid"),
                        rs.getString("stan"),
                        rs.getString("extendinfo"),
                        rs.getString("orderid"),
                        rs.getString("switchid"),
                        rs.getString("supplierref"),
                        rs.getBigDecimal("nominal"),
                        rs.getBigDecimal("adminfee"),
                        rs.getString("rc"),
                        rs.getString("supplier_rc"),
                        rs.getString("supplier_rcdesc"),
                        rs.getInt("order_id_is_null"),
                        rs.getString("supplier_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb_suspect;
    }

    public PpobTbRefund get_data_refund_by_trackingref(String trackingRef) {
        String strSQL = "SELECT * FROM tb_r_logpayrefund WHERE tracking_ref = ?";
        PpobTbRefund tbRefund = null;

        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(strSQL)) {
            pstmt.setString(1, trackingRef);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tbRefund = new PpobTbRefund(
                        rs.getString("trxid"),
                        rs.getString("tracking_ref"),
                        rs.getString("partnerid"),
                        rs.getString("customerid"),
                        rs.getBigDecimal("amount"),
                        rs.getString("receiptno"),
                        rs.getString("additionaldata"),
                        rs.getTimestamp("transactiondate"),
                        rs.getTimestamp("CreatedOn"),
                        rs.getString("productid"),
                        rs.getString("stan"),
                        rs.getString("extendinfo"),
                        rs.getString("orderid"),
                        rs.getString("switchid"),
                        rs.getString("supplierref"),
                        rs.getBigDecimal("nominal"),
                        rs.getBigDecimal("adminfee"),
                        rs.getTimestamp("createdon_suspect"),
                        rs.getString("finished_by"),
                        rs.getString("rc"),
                        rs.getString("supplier_rc"),
                        rs.getString("supplier_rcdesc"),
                        rs.getString("prev_rc"),
                        rs.getString("prev_supplier_rc"),
                        rs.getString("prev_supplier_rcdesc"),
                        rs.getInt("order_id_is_null"),
                        rs.getString("supplier_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbRefund;
    }

    public String getAdditionalDataTypeByTrackingRef(String trackingRef, String table) {
        String column;
        switch (table.toLowerCase()) {
            case "inquiry":
                column = "additional_type_inq";
                break;
            case "payment":
                column = "additional_type_pay";
                break;
            case "advice":
                column = "additional_type_adv";
                break;
            default:
                return "";
        }

        String strSQL = String.format(
                "SELECT tmad.%s FROM tb_r_%s tri JOIN tb_m_biller_pbob tmbp ON tmbp.productid = tri.productid JOIN tb_m_agentppob_detail tmad ON tmad.partnerid = tri.partnerid AND tmad.ppobid = tmbp.pbobid WHERE tri.tracking_ref = ?",
                column, table);
        return executeQuery(strSQL, trackingRef);
    }

    public String get_categoryname_by_switchid(String switchid, String partnerid) {
        String strSQL = """
                SELECT
                	IF(tmcl2.category_name IS NOT NULL, tmcl2.category_name,tmcl.category_name) AS category
                FROM
                    tb_m_switchppobv2 tms
                JOIN
                    tb_m_biller_pbob tmbp ON tms.ppobid = tmbp.productid
                JOIN
                    tb_m_category_layer tmcl ON tmbp.subcategoryid = tmcl.category_id
                LEFT JOIN
                    tb_m_category_layer tmcl2 ON
                        IF(tmcl.subcategory_1_id IS NOT NULL OR tmcl.subcategory_1_id != '',
                           tmcl.subcategory_1_id = tmcl2.category_id,
                           FALSE)
                WHERE
                    tms.switchid = ?
                    AND tms.partnerid = ?;
                """;
        return executeQuery(strSQL, switchid, partnerid);
    }

}