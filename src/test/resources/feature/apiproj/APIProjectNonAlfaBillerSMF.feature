Feature: APIProject Non Alfa Services

  @apiproj_nonalfa_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa
    Given Valid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | productId | customerId     | partnerId | terminalId | rc | rcDesc |
      | SMF       | 04011019000485 | SHP       | IDS        | 00 | SUKSES |
      | SMF       | 04011019000481 | SHP       | IDS        | 00 | SUKSES |

  @apiproj_nonalfa_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa
    Given Valid Request ("<customerId>", "<totalAmount>", "<productId>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | customerId     | totalAmount | productId | rc | rcDesc |
      | 04011019000485 | 528500      | SMF       | 00 | SUKSES |

  @apiproj_nonalfa_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<productId>", "<customerId>", "<partnerId>", "<terminalId>", "<param>", "<descTest>") API Inquiry APIProject Non Alfa
    When Partner hit API Inquiry APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject Non Alfa
    Examples:
      | param      | descTest           | productId | customerId     | partnerId | terminalId | rc | rcDesc                           |
      | customerId | Already_Paid       | SMF       | 04011019000698 | SHP       | IDS        | 78 | No Kontrak Sudah Dibayar         |
      | customerId | Invalid_CustomerId | SMF       | SMF0004        | SHP       | IDS        | 30 | No Kontrak Salah/Tidak Ditemukan |

  @apiproj_nonalfa_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject Non Alfa with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<totalAmount>", "<productId>", "<param>", "<descTest>") API Payment APIProject Non Alfa
    When Partner hit API Payment APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject Non Alfa
    Examples:
      | param  | descTest       | customerId     | totalAmount | productId | rc | rcDesc                                                  |
      | amount | Invalid_Amount | 04011019000481 | 100000      | SMF       | 6  | Error - TotalAmount salah, TotalAmount inquiry terakhir |

  @apiproj_nonalfa_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | customerId     | productId | rc | rcDesc |
#      | 04011019000701 | SMF       | 00 | SUKSES |

  @apiproj_nonalfa_rev_param_failed
  Scenario Template: [APIProject] Partner hit API Reversal APIProject Non Alfa
    Given Valid Request ("<customerId>", "<productId>", "<param>", "<descTest>") API Reversal APIProject Non Alfa
    When Partner hit API Reversal APIProject Non Alfa
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject Non Alfa
    Examples:
      | param       | descTest            | customerId     | productId | rc | rcDesc |
#      | trackingRef | invalid_trackingRef | 04011019000692 | SMF         | 00 | SUKSES |