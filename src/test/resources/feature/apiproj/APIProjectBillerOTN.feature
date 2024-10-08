Feature: APIProject Services

  @apiproj_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | agentId | agentPin       | storeId | productId | customerId   | rc | rcDesc |
      | SAT     | alf4!ds2015321 | IDS     | ADF       | 041020415943 | 00 | SUKSES |
      | SAT     | alf4!ds2015321 | IDS     | FINF      | 518000918100 | 00 | SUKSES |

  @apiproj_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | descTest          | customerId   | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
#      | trackingReff_Same | 201042400529 | 630000 | 0      | 630000      | 0        | ADF       | 00 | SUKSES |
#      | trackingReff_Same | 103072400001 | 933920 | 0      | 933920      | 0        | FINF      | 00 | SUKSES |

  @apiproj_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject with invalid <param> <descTest>
    Given Invalid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>", "<param>", "<descTest>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | param      | descTest           | agentId | agentPin       | storeId | productId | customerId | rc | rcDesc                           |
#      | customerId | Already_Paid       | SAT     | alf4!ds2015321 | IDS     | TPF       | 201042400529 | 78 | No Kontrak Sudah Dibayar         |
#      | customerId | Already_Paid       | SAT     | alf4!ds2015321 | IDS     | TPFM      | 103072400001 | 78 | No Kontrak Sudah Dibayar         |
      | customerId | Invalid_CustomerId | SAT     | alf4!ds2015321 | IDS     | ADF       | SMF0004    | 30 | No Kontrak Salah/Tidak Ditemukan |
      | customerId | Invalid_CustomerId | SAT     | alf4!ds2015321 | IDS     | FINF      | SMF0004    | 30 | No Kontrak Salah/Tidak Ditemukan |

#  @apiproj_pay_param_failed
#  Scenario Template: [APIProject] Partner hit API Payment APIProject with invalid <param> <descTest>
#    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Payment APIProject
#    When Partner hit API Payment APIProject
#    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
#    Examples:
#      | param      | descTest   | customerId       | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
#      | customerId | failed_pay | 0001624688540139 | 100000 | 0      | 105000      | 5000     | TPF       | ?  | ?      |

  @apiproj_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | customerId   | productId | rc | rcDesc |
#      | 201042400529 | ADF       | 00 | SUKSES |
#      | 103072400001 | FINF      | 00 | SUKSES |