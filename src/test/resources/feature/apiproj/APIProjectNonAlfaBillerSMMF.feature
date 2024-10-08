Feature: APIProject Non Alfa Services

  @apiproj_nonalfa_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa
    Given Valid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | productId | customerId   | partnerId | terminalId | rc | rcDesc |
      | SMMF      | 123000000252 | BMI       | IDS        | 00 | SUKSES |
#      | SMMF      | 123000000253 | BMI       | IDS        | 00 | SUKSES |
#      | SMMF      | 123000000254 | BMI       | IDS        | 00 | SUKSES |

  @apiproj_nonalfa_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa
    Given Valid Request ("<customerId>", "<totalAmount>", "<productId>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | customerId   | totalAmount | productId | rc | rcDesc |
      | 123000000252 | 10558800    | SMMF      | 00 | SUKSES |

  @apiproj_nonalfa_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>", "<param>", "<descTest>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | param      | descTest           | productId | customerId   | partnerId | terminalId | rc | rcDesc                           |
#      | customerId | Invalid_CustomerId | SMMF      | 123000000252 | BMI       | IDS        | 78 | No Kontrak Sudah Dibayar         |
      | customerId | Invalid_CustomerId | SMMF      | SMMF0004     | BMI       | IDS        | 30 | No Kontrak Salah/Tidak Ditemukan |

  @apiproj_nonalfa_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<totalAmount>", "<productId>", "<param>", "<descTest>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | param  | descTest       | customerId   | totalAmount | productId | rc | rcDesc                            |
#      | customerId | Already_Paid   | 9882100460 | 10750000    | SMMF      | 78 | No Kontrak Sudah Dibayar  |
      | amount | Invalid_Amount | 123000000252 | 100000      | SMMF      | 05 | jumlah bayar tidak sesuai inquiry |

  @apiproj_nonalfa_adv_sukses
  Scenario Template: [APIProject] Partner hit API Advice APIProject Non Alfa
    Given Valid Request ("<paymentStatus>", "<customerId>", "<productId>") API Advice APIProject Non Alfa
    When Partner hit API Advice APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Advice APIProject Non Alfa
    Examples:
      | paymentStatus | customerId   | productId | rc | rcDesc |
      | SUKSES        | 123000000252 | SMMF      | 00 | SUKSES |
#      | SUKSES        | 518000918100 | FINF      | 00 | SUKSES |
#      | SUSPECT       | 041020415943 | ADF       | 00 | SUKSES |
#      | SUSPECT       | 518000918100 | FINF      | 00 | SUKSES |

  @apiproj_nonalfa_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | customerId | productId | rc | rcDesc                 |
#      | 9232001275 | SMMF       | 01 | Sukses Proses Reversal |

  @apiproj_nonalfa_rev_param_failed
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>", "<param>", "<descTest>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | param       | descTest            | customerId     | productId | rc | rcDesc |
#      | trackingRef | invalid_trackingRef | 04011019000692 | SMF       | 00 | SUKSES |

#  *Note:
#    untuk CA BMI ada Advice ke IDS