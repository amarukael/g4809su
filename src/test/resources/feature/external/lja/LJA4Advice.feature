Feature: External Partner LJA

  @external_lja_adv_sukses @QA-TC-1201
  Scenario Outline: [External-JPA] Partner LJA hit
    Given Valid Request ("<merchant>", "<terminal>", "<pwd>", "<msisdn>", "<accNo>", "<amount>") API Advice External-LJA
    When Partner hit API Advice External-LJA
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Advice External-LJA
    Examples:
      | merchant    | terminal | pwd         | msisdn         | accNo      | amount | rc | rcDesc |
      | Idmjakarta1 | ids_bima | Idmjkt1pass | 62812000000001 | 9920010001 | 100000 | 00 |        |
      | Idmjakarta1 | ids_bima | Idmjkt1pass | 62812000000001 | 9920010002 | 100000 | 00 |        |

  @external_lja_adv_failed @QA-TC-1202
  Scenario Outline: [External-JPA] Partner LJA hit with invalid parameter
    Given Invalid Request ("<merchant>", "<terminal>", "<pwd>", "<msisdn>", "<accNo>", "<amount>", "<param>", "<descTest>") API Advice External-LJA
    When Partner hit API Advice External-LJA
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Advice External-LJA
    Examples:
      | param    | descTest       | merchant    | terminal  | pwd          | msisdn         | accNo      | amount   | rc | rcDesc             |
      | trx_type | Null           | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Transaction Failed |
      | trx_type | Empty          | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Transaction Failed |
      | merchant | Null           |             | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid merchant   |
      | merchant | Empty          |             | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid merchant   |
      | terminal | Null           | Idmjakarta1 |           | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid terminalid |
      | terminal | Empty          | Idmjakarta1 |           | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid terminalid |
      | terminal | Invalid        | Idmjakarta1 | ids_bimas | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid terminalid |
      | pwd      | Null           | Idmjakarta1 | ids_bima  |              | 62812000000001 | 9920010001 | 100000   | 05 | Invalid Password   |
      | pwd      | Empty          | Idmjakarta1 | ids_bima  |              | 62812000000001 | 9920010001 | 100000   | 05 | Invalid Password   |
      | pwd      | Invalid        | Idmjakarta1 | ids_bima  | Idmjkt1pass1 | 62812000000001 | 9920010001 | 100000   | 05 | Invalid Password   |
      | msisdn   | Null           | Idmjakarta1 | ids_bima  | Idmjkt1pass  |                | 9920010001 | 100000   | 05 | Invalid msisdn     |
      | msisdn   | Empty          | Idmjakarta1 | ids_bima  | Idmjkt1pass  |                | 9920010001 | 100000   | 05 | Invalid msisdn     |
      | msisdn   | Invalid_Format | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 812000000001   | 9920010001 | 100000   | 05 | Invalid msisdn     |
      | acc_no   | Null           | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 |            | 100000   | 05 | Invalid acc_no     |
      | acc_no   | Empty          | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 |            | 100000   | 05 | Invalid acc_no     |
      | trx_date | Null           | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid trx_date   |
      | trx_date | Empty          | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid trx_date   |
      | trx_date | Invalid_Format | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid trx_date   |
      | amount   | Null           | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 |          | 05 | Invalid amount     |
      | amount   | Empty          | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 |          | 05 | Invalid amount     |
      | amount   | Invalid_Format | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | rp100000 | 05 | Invalid amount     |
      | amount   | Invalid_Amount | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 10000    | 05 | Invalid amount     |
      | bill_ref | Null           | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid bill_ref   |
      | bill_ref | Empty          | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid bill_ref   |
      | bill_ref | Invalid        | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | trx not found      |
#      | bill_ref | Timeout        | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 |        |
      | trx_id   | Null           | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid trx_id      |
      | trx_id   | Empty          | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 | Invalid trx_id      |
#      | trx_id   | Double         | Idmjakarta1 | ids_bima  | Idmjkt1pass  | 62812000000001 | 9920010001 | 100000   | 05 |        |