Feature: APIProject Services

  @apiproj_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | agentId | agentPin       | storeId | productId | customerId     | rc | rcDesc |
      | SAT     | alf4!ds2015321 | IDS     | SMF       | 04011019000484 | 00 | SUKSES |

  @apiproj_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | descTest          | customerId     | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
      | trackingReff_Same | 04011019000484 | 792000 | 0      | 792000      | 0        | SMF       | 00 | SUKSES |

  @apiproj_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject with invalid <param> <descTest>
    Given Invalid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>", "<param>", "<descTest>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | param      | descTest           | agentId | agentPin       | storeId | productId | customerId     | rc | rcDesc                        |
      | customerId | Already_Paid       | SAT     | alf4!ds2015321 | IDS     | SMF       | 04011019000698 | 88 | ERROR - Tagihan sudah dibayar |
      | customerId | Invalid_CustomerId | SAT     | alf4!ds2015321 | IDS     | SMF       | SMF0004        | 14 | ERROR - No kontrak salah      |

#  @apiproj_pay_param_failed
#  Scenario Template: [APIProject] Partner hit API Payment APIProject with invalid <param> <descTest>
#    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Payment APIProject
#    When Partner hit API Payment APIProject
#    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
#    Examples:
#      | param  | descTest       | customerId     | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
#      | amount | Invalid_Amount | 04011019000477 | 100000 | 0      | 100000      | 0        | SMF       | ?  | ?      |

  @apiproj_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | customerId     | productId | rc | rcDesc |
      | 04011019000484 | SMF       | 00 | SUKSES |