Feature: APIProject Services

  @apiproj_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | agentId | agentPin       | storeId | productId | customerId | rc | rcDesc |
      | SAT     | alf4!ds2015321 | IDS     | ?         | ?          | 00 | SUKSES |
      | SAT     | alf4!ds2015321 | IDS     | ?         | ?          | 00 | SUKSES |

  @apiproj_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | descTest          | customerId | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
      | trackingReff_Same | ?          | ?      | ?      | ?           | ?        | ?         | 00 | SUKSES |
      | trackingReff_Diff | ?          | ?      | ?      | ?           | ?        | ?         | 00 | SUKSES |

  @apiproj_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject with invalid <param> <descTest>
    Given Invalid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>", "<param>", "<descTest>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | param      | descTest           | agentId | agentPin       | storeId | productId | customerId | rc | rcDesc                   |
      | customerId | Already_Paid       | SAT     | alf4!ds2015321 | IDS     | ?         | ?          | 78 | No Kontrak Sudah Dibayar |
      | customerId | Invalid_CustomerId | SAT     | alf4!ds2015321 | IDS     | ?         | ?          | 14 | ERROR - No kontrak salah |

  @apiproj_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | param      | descTest       | customerId | amount | charge | totalAmount | adminFee | productId | rc | rcDesc                                          |
      | customerId | Already_Paid   | ?          | ?      | ?      | ?           | ?        | ?         | 88 | ERROR - Tagihan sudah dibayar                   |
      | amount     | Invalid_Amount | ?          | ?      | ?      | ?           | ?        | ?         | 5  | ERROR - Jatuh tempo no kontrak melebihi 90 hari |

  @apiproj_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | customerId | productId | rc | rcDesc                                           |
      | ?          | ?         | 00 | SUKSES-Proses reversal sebelumnya telah berhasil |

  @apiproj_rev_failed
  Scenario Template: [APIProject] Partner hit API Reversal APIProject with invalid parameter
    Given Invalid Request ("<customerId>", "<productId>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | param        | descTest | customerId | productId | rc | rcDesc                                           |
      | trackingReff | Invalid  | ?          | MTF       | 00 | SUKSES-Proses reversal sebelumnya telah berhasil |