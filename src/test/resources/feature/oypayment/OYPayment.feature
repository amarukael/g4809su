Feature: OYPayment Services

  @oypayment_updatepay_sukses
  Scenario Template: [OYPayment] Partner hit API Update Payment OYPayment
    Given Valid Request ("<agentId>", "<customerId>", "<totalAmount>", "<reciptNo>", "<status>") API Update Payment OYPayment
    When Partner hit API Update Payment OYPayment
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Update Payment OYPayment
    Examples:
      | agentId | customerId                        | totalAmount   | reciptNo                    | status    | rc    | rcDesc           |
      | SAT     | 0001624240809001,0001624240809001 | 120000,120000 | QArn240809001,QArn240809002 | open,open | 20000 | SUCCESS - Update |

  @oypayment_updatepay_failed
  Scenario Template: [OYPayment] Partner hit API Update Payment OYPayment with invalid parameter
    Given Invalid Request ("<agentId>", "<customerId>", "<totalAmount>", "<reciptNo>", "<status>", "<param>", "<descTest>") API Update Payment OYPayment
    When Partner hit API Update Payment OYPayment
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Update Payment OYPayment
    Examples:
      | param        | descTest | agentId | customerId       | totalAmount | reciptNo      | status | rc    | rcDesc         |
      | trxDate      | Empty    | SAT     | 0001624240809001 | 120000      | QArn240809001 | open   | 40445 | Data Not Found |
      | trackingReff | Empty    | SAT     | 0001624240809001 | 120000      | QArn240809001 | open   | 40445 | Data Not Found |
      | agentID      | Empty    | SAT     | 0001624240809001 | 120000      | QArn240809001 | open   | 40445 | Data Not Found |
      | customerId   | Empty    | SAT     | 0001624240809001 | 120000      | QArn240809001 | open   | 40445 | Data Not Found |
      | totalAmount  | Empty    | SAT     | 0001624240809001 | 120000      | QArn240809001 | open   | 40445 | Data Not Found |
      | reciptNo     | Empty    | SAT     | 0001624240809001 | 120000      | QArn240809001 | open   | 40445 | Data Not Found |
      | status       | Empty    | SAT     | 0001624240809001 | 120000      | QArn240809001 | open   | 40445 | Data Not Found |
