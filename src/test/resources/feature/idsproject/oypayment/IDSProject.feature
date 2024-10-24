Feature: IDSProject Services

  @inq_sukses
  Scenario Template: [IDSProject] Partner hit API Inquiry IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>") API Inquiry IDSProject
    When Partner hit API Inquiry IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry IDSProject
    Examples:
      | storeId | productId | paymentCode      | rc | rcDesc |
      | IDM     | P001      | 0001624688540140 | 00 | SUKSES |

  @pay_sukses
  Scenario Template: [IDSProject] Partner hit API Payment IDSProject
    Given Valid Request ("<storeId>", "<productId>", "<paymentCode>", "<amount>") API Payment IDSProject
    When Partner hit API Payment IDSProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment IDSProject
    Examples:
      | storeId | productId | paymentCode      | amount | rc | rcDesc |
      | IDM     | P001      | 0001624688540140 | 105000 | 00 | SUKSES |
