Feature: APIProject Services

  @apiproj_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | agentId | agentPin       | storeId | productId | customerId      | rc | rcDesc |
      | SAT     | alf4!ds2015321 | IDS     | CVK       | 060898004000200 | 00 | SUKSES |
      | SAT     | alf4!ds2015321 | IDS     | CVK       | 060898124000100 | 00 | SUKSES |
      | SAT     | alf4!ds2015321 | IDS     | CVK       | 060898044000100 | 00 | SUKSES |

  @apiproj_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | descTest          | customerId      | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
      | trackingReff_Diff | 060898004000200 | 835000 | 0      | 835000      | 0        | CVK       | 00 | SUKSES |
      | trackingReff_Diff | 060898124000100 | 332000 | 0      | 332000      | 0        | CVK       | 00 | SUKSES |

  @apiproj_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject with invalid <param> <descTest>
    Given Invalid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>", "<param>", "<descTest>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | param        | descTest            | agentId | agentPin       | storeId | productId | customerId      | rc | rcDesc                   |
      | customerId   | Invalid_CustomerId  | SAT     | alf4!ds2015321 | IDS     | CVK       | 060898004000211 | 14 | ERROR - No kontrak salah |
      | trackingReff | Double_TrackingReff | SAT     | alf4!ds2015321 | IDS     | CVK       | 060898004000200 | 5  | Double TrackingRef       |

  @apiproj_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | param        | descTest            | customerId      | amount | charge | totalAmount | adminFee | productId | rc | rcDesc                  |
      | trackingReff | Double_TrackingReff | 060898044000100 | 626000 | 0      | 626000      | 0        | CVK       | 5  | ERROR - Transaksi Gagal |

  @apiproj_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | customerId      | productId | rc | rcDesc |
      | 060898004000200 | CVK       | 00 | SUKSES |

  @apiproj_rev_failed
  Scenario Template: [APIProject] Partner hit API Reversal APIProject with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<productId>", "<param>", "<descTest>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | param        | descTest            | customerId      | productId | rc | rcDesc                  |
      | trackingReff | Invalid_TrackingRef | 060898124000100 | CVK       | 5  | ERROR - Transaksi Gagal |