Feature: APIProject Non Alfa Services

  @apiproj_nonalfa_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa
    Given Valid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | productId | customerId   | partnerId | terminalId | rc | rcDesc |
      | ADF       | 041020415943 | SHP       | IDS        | 00 | SUKSES |
      | ADF       | 041020415913 | SHP       | IDS        | 00 | SUKSES |
      | FINF      | 518000918100 | SHP       | IDS        | 00 | SUKSES |
      | FINF      | 518000918113 | SHP       | IDS        | 00 | SUKSES |

  @apiproj_nonalfa_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa
    Given Valid Request ("<customerId>", "<totalAmount>", "<productId>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | customerId   | totalAmount | productId | rc | rcDesc |
      | 041020415943 | 661625      | ADF       | 00 | SUKSES |
      | 518000918100 | 1660000     | FINF      | 00 | SUKSES |

  @apiproj_nonalfa_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>", "<param>", "<descTest>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | param      | descTest           | productId | customerId   | partnerId | terminalId | rc | rcDesc                           |
      | customerId | Already_Paid       | ADF       | 041020415988 | SHP       | IDS        | 78 | No Kontrak Sudah Dibayar         |
      | customerId | Already_Paid       | FINF      | 518000918188 | SHP       | IDS        | 78 | No Kontrak Sudah Dibayar         |
      | customerId | Invalid_CustomerId | ADF       | 041020415914 | SHP       | IDS        | 30 | No Kontrak Salah/Tidak Ditemukan |
      | customerId | Invalid_CustomerId | FINF      | 518000918114 | SHP       | IDS        | 30 | No Kontrak Salah/Tidak Ditemukan |

  @apiproj_nonalfa_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<totalAmount>", "<productId>", "<param>", "<descTest>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | param  | descTest       | customerId   | totalAmount | productId | rc | rcDesc                                                  |
      | amount | Invalid_Amount | 041020415913 | 100000      | ADF       | 6  | Error - TotalAmount salah, TotalAmount inquiry terakhir |
      | amount | Invalid_Amount | 518000918100 | 100000      | FINF      | 6  | Error - TotalAmount salah, TotalAmount inquiry terakhir |

  @apiproj_nonalfa_adv_sukses
  Scenario Template: [APIProject] Partner hit API Advice APIProject Non Alfa
    Given Valid Request ("<paymentStatus>", "<customerId>", "<productId>") API Advice APIProject Non Alfa
    When Partner hit API Advice APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Advice APIProject Non Alfa
    Examples:
      | paymentStatus | customerId   | productId | rc | rcDesc |
      | SUKSES        | 041020415943 | ADF       | 00 | SUKSES |
      | SUKSES        | 518000918100 | FINF      | 00 | SUKSES |
      | SUSPECT       | 041020415943 | ADF       | 00 | SUKSES |
      | SUSPECT       | 518000918100 | FINF      | 00 | SUKSES |

  @apiproj_nonalfa_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | customerId   | productId | rc | rcDesc |
#      | 903072400037 | ADF       | 00 | SUKSES |
#      | 103072400003 | FINF      | 00 | SUKSES |

  @apiproj_nonalfa_rev_param_failed
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>", "<param>", "<descTest>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | param       | descTest            | customerId     | productId | rc | rcDesc |
#      | trackingRef | invalid_trackingRef | 04011019000692 | ADF       | 00 | SUKSES |

  # Note ADF = 67, FINF = 68.

  Scenario: