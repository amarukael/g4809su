Feature: APIProject Non Alfa Services

  @apiproj_nonalfa_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa
    Given Valid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | productId | customerId   | partnerId | terminalId | rc | rcDesc |
      | TPF       | 903072400037 | LJA       | IDS        | 00 | SUKSES |
#      | TPFM      | 103072400003 | LJA       | IDS        | 00 | SUKSES |

  @apiproj_nonalfa_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa
    Given Valid Request ("<customerId>", "<totalAmount>", "<productId>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | customerId   | totalAmount | productId | rc | rcDesc |
#      | 903072400037 | 1307700     | TPF       | 00 | SUKSES |
      | 103072400003 | 704520      | TPFM      | 00 | SUKSES |

  @apiproj_nonalfa_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>", "<param>", "<descTest>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | param      | descTest           | productId | customerId | partnerId | terminalId | rc | rcDesc                           |
#      | customerId | Already_Paid       | SMF       | 04011018000447 | SHP       | IDS        | 78 | No Kontrak Sudah Dibayar         |
#      | customerId | Invalid_CustomerId | TPF       | SMF0004    | LJA       | IDS        | 30 | No Kontrak Salah/Tidak Ditemukan |
#      | customerId | Invalid_CustomerId | TPFM      | SMF0004    | LJA       | IDS        | 30 | No Kontrak Salah/Tidak Ditemukan |

  @apiproj_nonalfa_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<totalAmount>", "<productId>", "<param>", "<descTest>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | param    | descTest   | customerId     | totalAmount | productId | rc | rcDesc |
#      | dateTime | failed_pay | 04011019000696 | 712100      | TPF       | ?  | ?      |

  @apiproj_nonalfa_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | customerId   | productId | rc | rcDesc |
#      | 903072400037 | TPF       | 00 | SUKSES |
#      | 103072400003 | TPFM      | 00 | SUKSES |

  @apiproj_nonalfa_rev_param_failed
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>", "<param>", "<descTest>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | param       | descTest            | customerId     | productId | rc | rcDesc |
#      | trackingRef | invalid_trackingRef | 04011019000692 | TPF         | 00 | SUKSES |