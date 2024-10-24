Feature: IDSProject Services

  @idsproject_inq_sukses @QA-TC-601
  Scenario Template: [IDSProject] Partner hit API Inquiry IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>") API Inquiry IDSProject
    When Partner hit API Inquiry IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry IDSProject
    Examples:
      | storeId | productId | paymentCode  | rc | rcDesc |
      | IDM     | 70        | 111120000999 | 0  | SUKSES |
      | IDM     | 70        | 111120001000 | 0  | SUKSES |

  @idsproject_pay_sukses @QA-TC-602
  Scenario Template: [IDSProject] Partner hit API Payment IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>", "<descTest>") API Payment IDSProject
    When Partner hit API Payment IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment IDSProject
    Examples:
      | descTest          | storeId | productId | paymentCode  | amount | rc | rcDesc |
      | trackingReff_Same | IDM     | 70        | 111120000999 | 685000 | 0  | SUKSES |
      | trackingReff_Diff | IDM     | 70        | 111120001000 | 240000 | 0  | SUKSES |

  @idsproject_inq_param_failed @QA-TC-937
  Scenario Template: [IDSProject] Partner hit API Inquiry IDSProject with invalid <param> <descTest>
    Given Invalid Request ("<storeId>", "<productId>", "<paymentCode>", "<param>", "<descTest>") API Inquiry IDSProject
    When Partner hit API Inquiry IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry IDSProject
    Examples:
      | param       | descTest | storeId | productId | paymentCode | rc | rcDesc                           |
#      | paymentCode | Already_Paid        | IDM     | 70        | 04011019000698 | 78 | No Kontrak Sudah Dibayar         |
      | paymentCode | Invalid  | IDM     | 70        | DTPP0004    | 30 | No Kontrak Salah/Tidak Ditemukan |

  @idsproject_pay_param_failed
  Scenario Template: [IDSProject] Partner hit API Payment IDSProject with invalid <param> <descTest>
    Given Invalid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>", "<param>", "<descTest>") API Payment IDSProject
    When Partner hit API Payment IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment IDSProject
    Examples:
      | param       | descTest | storeId | productId | paymentCode  | amount | rc | rcDesc |
#      | trackingRef | Invalid  | IDM     | 70        | 111120000917 | 420000 | ?  | ?      |
#      | amount      | Invalid  | IDM     | 70        | 111120000974 | 100000 | 0  | SUKSES |

  @idsproject_rev_sukses @QA-TC-938
  Scenario Template: [IDSProject] Partner hit API Reversal IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>") API Reversal IDSProject
    When Partner hit API Reversal IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal IDSProject
    Examples:
      | storeId | productId | paymentCode  | amount | rc | rcDesc |
      | IDM     | 70        | 111120000999 | 685000 | 0  | SUKSES |
      | IDM     | 70        | 111120001000 | 240000 | 0  | SUKSES |

  @idsproject_rev_failed @QA-TC-1391
  Scenario Template: [IDSProject] Partner hit API Reversal IDSProject with invalid <param> <descTest>
    Given Invalid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>", "<param>", "<descTest>") API Reversal IDSProject
    When Partner hit API Reversal IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal IDSProject
    Examples:
      | param       | descTest | storeId | productId | paymentCode  | amount | rc | rcDesc                   |
      | trackingRef | Invalid  | IDM     | 70        | 111120001000 | 240000 | 40 | Gagal melakukan reversal |