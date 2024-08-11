Feature: OtoTrans Bulk Disbursement

  @navigate_ototrans_bulk_disbursement
  Scenario Outline: [SOB OTOTrans] Navigate into OTOTrans Bulk Disbursement Page
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to OTOTrans Bulk Disbursement
    Examples:
      | condition    | username |
      | Successfully | adminqa  |

  @ototrans_create_trans_bulk_disbursement
  Scenario Outline: [SOB OTOTrans] Upload Bulk Disbursement
    Given I am in OtoTrans Bulk Disbursement Page
    * I hit ADD DISBURSEMENT Button on OtoTrans Bulk Disbursement
    * I fill Mandatory Fields ("<partnerId>", "<transNm>", "<transDesc>", "<uploadFormat>", "<typeAction>") On Create New Transaction Section
    When i hit SUBMIT Button
    Then "<condition>" create new transaction Bulk Disbursement
    Examples:
      | condition    | partnerId              | transNm              | transDesc     | uploadFormat | typeAction |
      | Successfully | INOVASI DAYA SOLUSI TO | Test OtoXDims        | test new dims | CSV          | approve    |
      | Successfully | INOVASI DAYA SOLUSI TO | Test Failed OtoXDims | test new dims | CSV          | failed     |

  @ototrans_failed_bulk_disbursement
  Scenario Outline: [SOB OTOTrans] Action Failed Bulk Disbursement
    Given I am in OtoTrans Bulk Disbursement Page
    * I hit Filter Button on OtoTrans Bulk Disbursement
    * I fill Partner ID Field ("<partnerId>") on Filter Field on OtoTrans Bulk Disbursement
    * I hit Apply Button on OtoTrans Bulk Disbursement
    When I hit Failed Button "<transNm>" on DataTable on OtoTrans Bulk Disbursement
    Then I hit Confrim Button on Validation Section on OtoTrans Bulk Disbursement
    * "<condition>" Action Fail Bulk Disbursement
    Examples:
      | condition    | partnerId              | transNm              |
      | Successfully | INOVASI DAYA SOLUSI TO | Test Failed OtoXDims |

  @ototrans_success_bulk_disbursement
  Scenario Outline: [SOB OTOTrans] Action Approve Bulk Disbursement
    Given I am in OtoTrans Bulk Disbursement Page
    * I hit Filter Button on OtoTrans Bulk Disbursement
    * I fill Partner ID Field ("<partnerId>") on Filter Field on OtoTrans Bulk Disbursement
    * I hit Apply Button on OtoTrans Bulk Disbursement
    When I hit Approve Button "<transNm>" on DataTable on OtoTrans Bulk Disbursement
    Then I fill OTP on OtoTrans Bulk Disbursement
    * I hit SUBMIT Button on Validation Section on OtoTrans Bulk Disbursement
    * "<condition>" Action Success Bulk Disbursement
    Examples:
      | condition    | partnerId              | transNm       |
      | Successfully | INOVASI DAYA SOLUSI TO | Test OtoXDims |
