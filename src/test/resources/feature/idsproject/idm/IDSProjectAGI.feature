Feature: IDSProject Services

  @idsproject_inq_sukses
  Scenario Template: [IDSProject] Partner hit API Inquiry IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>") API Inquiry IDSProject
    When Partner hit API Inquiry IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry IDSProject
    Examples:
      | storeId | productId | paymentCode     | rc | rcDesc |
      | IDM     | 72        | 060898124000200 | 0  | SUKSES |
      | IDM     | 72        | 060898124000100 | 0  | SUKSES |
      | IDM     | 72        | 060898044000100 | 0  | SUKSES |

  @idsproject_pay_sukses
  Scenario Template: [IDSProject] Partner hit API Payment IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>", "<descTest>") API Payment IDSProject
    When Partner hit API Payment IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment IDSProject
    Examples:
      | descTest          | storeId | productId | paymentCode     | amount | rc | rcDesc |
      | trackingReff_Diff | IDM     | 72        | 060898124000200 | 529000 | 0  | SUKSES |
      | trackingReff_Diff | IDM     | 72        | 060898124000100 | 337000 | 0  | SUKSES |

  @idsproject_inq_param_failed
  Scenario Template: [IDSProject] Partner hit API Inquiry IDSProject with invalid <param> <descTest>
    Given Invalid Request ("<storeId>", "<productId>", "<paymentCode>", "<param>", "<descTest>") API Inquiry IDSProject
    When Partner hit API Inquiry IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry IDSProject
    Examples:
      | param       | descTest            | storeId | productId | paymentCode     | rc | rcDesc                           |
      | paymentCode | Invalid_PaymentCode | IDM     | 72        | 060898004000211 | 30 | No Kontrak Salah/Tidak Ditemukan |
      | trackingRef | Double_TrackingReff | IDM     | 72        | 060898124000200 | 6  | Double TrackingRef               |

  @idsproject_pay_param_failed
  Scenario Template: [IDSProject] Partner hit API Payment IDSProject with invalid <param> <descTest>
    Given Invalid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>", "<param>", "<descTest>") API Payment IDSProject
    When Partner hit API Payment IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment IDSProject
    Examples:
      | param       | descTest            | storeId | productId | paymentCode     | amount | rc | rcDesc                     |
      | trackingRef | Double_TrackingReff | IDM     | 72        | 060898044000100 | 631000 | 50 | Gagal melakukan pembayaran |

  @idsproject_rev_sukses
  Scenario Template: [IDSProject] Partner hit API Reversal IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>") API Reversal IDSProject
    When Partner hit API Reversal IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal IDSProject
    Examples:
      | storeId | productId | paymentCode     | amount | rc | rcDesc |
      | IDM     | 72        | 060898124000200 | 529000 | 0  | SUKSES |

  @idsproject_rev_failed
  Scenario Template: [IDSProject] Partner hit API Reversal IDSProject with invalid <param> <descTest>
    Given Invalid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>", "<param>", "<descTest>") API Reversal IDSProject
    When Partner hit API Reversal IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal IDSProject
    Examples:
      | param       | descTest            | storeId | productId | paymentCode     | amount | rc | rcDesc                   |
      | trackingRef | Invalid_TrackingRef | IDM     | 72        | 060898124000100 | 332000 | 40 | Gagal melakukan reversal |