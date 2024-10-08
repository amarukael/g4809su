Feature: APIProject Services

  @apiproj_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | agentId | agentPin       | storeId | productId | customerId     | rc | rcDesc |
#      | SAT     | alf4!ds2015321 | IDS     | TPF       | 903072400037 | 00 | SUKSES |
#      | SAT     | alf4!ds2015321 | IDS     | TPFM      | 103072400003 | 00 | SUKSES |

  @apiproj_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | customerId   | amount  | charge | totalAmount | adminFee | rc | rcDesc |
#      | 903072400037 | 1300000 | 0      | 1300000     | 0        | 00 | SUKSES |
#      | 103072400003 | 693020  | 0      | 693020      | 0        | 00 | SUKSES |

  @apiproj_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject with invalid <param> <descTest>
    Given Invalid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>", "<param>", "<descTest>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | param      | descTest           | agentId | agentPin       | storeId | productId | customerId   | rc | rcDesc                           |
#      | customerId | Already_Paid       | SAT     | alf4!ds2015321 | IDS     | TPF       | 201042400529 | 78 | No Kontrak Sudah Dibayar         |
#      | customerId | Already_Paid       | SAT     | alf4!ds2015321 | IDS     | TPFM      | 103072400001 | 78 | No Kontrak Sudah Dibayar         |
#      | customerId | Invalid_CustomerId | SAT     | alf4!ds2015321 | IDS     | TPF       | SMF0004      | 30 | No Kontrak Salah/Tidak Ditemukan |
#      | customerId | Invalid_CustomerId | SAT     | alf4!ds2015321 | IDS     | TPFM      | SMF0004      | 30 | No Kontrak Salah/Tidak Ditemukan |

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
#      | 903072400037 | TPF       | 00 | SUKSES |
#      | 103072400003 | TPFM      | 00 | SUKSES |