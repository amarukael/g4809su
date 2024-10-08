Feature: IDSRestProjectDana Services

  @idsrestprojectdana_inq_failed
  Scenario Template: [IDSRestProjectDana] Partner hit API Inquiry IDSRestProjectDana with invalid parameter
    Given Invalid Request ("<customerId>", "<productId>", "<param>", "<descTest>") API Inquiry IDSRestProjectDana
    When Partner hit API Inquiry IDSRestProjectDana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry IDSRestProjectDana
    Examples:
      | param         | descTest | customerId | productId | rc | rcDesc                                          |
      | AmountPinalty | Invalid  | 10100696   | 1016      | 5  | ERROR - Pembayaran harap dilakukan diloket PDAM |

  @idsrestprojectdana_pay_failed
  Scenario Template: [IDSRestProjectDana] Partner hit API Payment IDSRestProjectDana with invalid parameter
    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Payment IDSRestProjectDana
    When Partner hit API Payment IDSRestProjectDana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment IDSRestProjectDana
    Examples:
      | param      | descTest | customerId | amount | charge | totalAmount | adminFee | productId | rc | rcDesc                    |
      | AgentTrxID | Invalid  | 201801002  | 237000 | 5000   | 242000      | 0        | 1013      | 5  | ERROR - Inquiry Not Found |

  @idsrestprojectdana_adv_failed
  Scenario Template: [IDSRestProjectDana] Partner hit API Advice IDSRestProjectDana with invalid parameter
    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Advice IDSRestProjectDana
    When Partner hit API Advice IDSRestProjectDana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Advice IDSRestProjectDana
    Examples:
      | param      | descTest | customerId | amount | charge | totalAmount | adminFee | productId | rc | rcDesc                    |
      | AgentTrxID | Invalid  | 201801002  | 237000 | 5000   | 242000      | 0        | 1013      | 5  | ERROR - Inquiry Not Found |