Feature: APIProject Services

  @apiproj_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | agentId | agentPin       | storeId | productId | customerId   | rc | rcDesc |
      | SAT     | alf4!ds2015321 | IDS     | DTPP      | 111120000988 | 00 | SUKSES |

  @apiproj_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | customerId   | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
      | 111120000988 | 415000 | 0      | 415000      | 0        | DTPP      | 00 | SUKSES |


  @apiproj_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject with invalid <param> <descTest>
    Given Invalid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>", "<param>", "<descTest>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | param      | descTest           | agentId | agentPin       | storeId | productId | customerId   | rc | rcDesc                        |
      | signature  | Invalid            | SAT     | alf4!ds2015321 | IDS     | DTPP      | 111120000939 | 34 | ERROR-Invalid Signature       |
#      | customerId | Already_Paid       | SAT     | alf4!ds2015321 | IDS     | DTPP      | 111120000940 | 88 | ERROR - Tagihan sudah dibayar |
      | customerId | Invalid_CustomerId | SAT     | alf4!ds2015321 | IDS     | DTPP      | DTPP0004     | 14 | ERROR - No kontrak salah      |

  @apiproj_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | param  | descTest       | customerId     | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
#      | amount | Invalid_Amount | 04011019000477 | 100000 | 0      | 100000      | 0        | DTPP       | ?  | ?      |

  @apiproj_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | customerId   | productId | rc | rcDesc |
      | 111120000988 | DTPP      | 00 | SUKSES |