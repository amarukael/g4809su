Feature: Portal Service Disi

  @portal_refund_withdraw_success
  Scenario: Refund withdraw by Portal API Disi -- Successsfully
    Given Valid data ("DGSHO", "DGS", "DGSDB240327114447621") API Portal Disi
    When Internal hits API Portal Disi
    Then Internal gets "00" desc "SUKSES" API Portal Disi

  @portal_refund_withdraw_failed
  Scenario Outline: Refund withdraw by Portal API Disi -- Failed
    Given Invalid Request "<param>" "<descTest>" ("<merchantId>", "<partnerId>", "<trackingRef>") API Portal Disi
    When Internal hits API Portal Disi
    Then Internal gets "<rc>" desc "<rcDesc>" API Portal Disi
    Examples:
      | param       | descTest | merchantId | partnerId | trackingRef           | rc  | rcDesc                                                      |
      | partnerId   | Invalid  | DGSHO      | DGS1      | DGSDB240326173047677  | 05  | Invalid Request Tipe, General Error (Partner ID Not Found)  |
      | userId      | Invalid  | DGSHO1     | DGS       | DGSDB240326173047677  | 05  | Invalid Request Tipe, General Error (User Not Found)        |
      | trackingRef | Invalid  | DGSHO      | DGS       | DGSDB2403261730476771 | 05  | Invalid Request Tipe, General Error (Reference No Not Found)|
