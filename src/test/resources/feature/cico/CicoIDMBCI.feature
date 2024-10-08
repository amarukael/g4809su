Feature: CashInCashOut Services

  @cico_generate_token_sukses
  Scenario Template: [CICO] Biller hit API Generate Token
    Given Valid Request ("<token>", "<name>", "<phone>", "<bankAccount>", "<noKtp>", "<channelTerm>", "<amount>", "<desc>", "<expiredDate>", "<productId>", "<partnerId>") API Generate Token
    When Biller hit API Generate Token
    Then Biller get response "<rc>" and response desc "<rcDesc>" API Generate Token
    Examples:
      | token | name | phone | bankAccount | noKtp | channelTerm | amount | desc | expiredDate | productId | partnerId | rc | rcDesc |
      | ?     | ?    | ?     | ?           | ?     | ?           | ?      | ?    | ?           | ?         | ?         | 00 | SUKSES |

  @cico_cashout_pay_idm_sukses
  Scenario Template: [CICO] Partner hit API CashOut Transaction tipe Payment
    Given Valid Request ("<clientId>", "<key>", "<branchId>", "<counterId>", "<productType>", "<detail>", "<timeOut>", "<versiProgram>") API CashOut Transaction tipe Payment
    When Partner hit API CashOut Transaction tipe Payment
    Then Partner get response "<rc>" and response desc "<rcDesc>" API CashOut Transaction tipe Payment
    Examples:
      | clientId | key | branchId | counterId | productType | detail                                                            | timeOut | versiProgram | rc | rcDesc |
      | ?        | ?   | ?        | ?         | ?           | {\"TrxId\":\"?\",\"Token\":\"?\",\"noHP\":\"?\",\"Amount\":\"?\"} | ?       | ?            | 00 | SUKSES |

  @cico_cashout_rev_idm_sukses
  Scenario Template: [CICO] Partner hit API CashOut Transaction tipe Reversal
    Given Valid Request ("<clientId>", "<key>", "<branchId>", "<counterId>", "<productType>", "<detail>", "<timeOut>", "<versiProgram>") API CashOut Transaction tipe Reversal
    When Partner hit API CashOut Transaction tipe Reversal
    Then Partner get response "<rc>" and response desc "<rcDesc>" API CashOut Transaction tipe Reversal
    Examples:
      | clientId | key | branchId | counterId | productType | detail                                                            | timeOut | versiProgram | rc | rcDesc |
      | ?        | ?   | ?        | ?         | ?           | {\"TrxId\":\"?\",\"Token\":\"?\",\"noHP\":\"?\",\"Amount\":\"?\"} | ?       | ?            | 00 | SUKSES |
