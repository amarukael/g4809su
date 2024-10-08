Feature: OYPayment Services

  @oypayment_inq_sukses
  Scenario Template: [OYPayment] Partner hit API Inquiry OYPayment
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry OYPayment
    When Partner hit API Inquiry OYPayment
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry OYPayment
    Examples:
      | agentId  | agentPin | storeId | productId | customerId       | rc | rcDesc |
      | alfamart | alfa123  | DEV     | P001      | 0001624688540139 | 00 | SUKSES |
      | alfamart | alfa123  | DEV     | P001      | 0001624688540141 | 00 | SUKSES |

  @oypayment_pay_sukses
  Scenario Template: [OYPayment] Partner hit API Payment OYPayment
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>") API Payment OYPayment
    When Partner hit API Payment OYPayment
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment OYPayment
    Examples:
      | customerId       | amount | charge | totalAmount | adminFee | rc | rcDesc |
      | 0001624688540139 | 100000 | 0      | 105000      | 5000     | 00 | SUKSES |
      | 0001624688540141 | 100000 | 0      | 105000      | 5000     | 00 | SUKSES |

  @oypayment_commit_sukses
  Scenario Template: [OYPayment] Partner hit API Commit OYPayment
    Given Valid Request ("<customerId>") API Commit OYPayment
    When Partner hit API Commit OYPayment
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Commit OYPayment
    Examples:
      | customerId       | rc | rcDesc |
      | 0001624688540139 | 00 | SUKSES |
      | 0001624688540141 | 00 | SUKSES |