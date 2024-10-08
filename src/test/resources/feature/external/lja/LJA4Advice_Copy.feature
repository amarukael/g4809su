Feature: External Partner LJA

  @external_lja_adv_sukses
  Scenario Outline: [External-JPA] Partner hit API Advice External-LJA
    Given Valid Request ("<merchant>", "<terminal>", "<pwd>", "<msisdn>", "<accNo>", "<amount>") API Advice External-LJA
    When Partner hit API Advice External-LJA
    Then Partner gets response "<rc>" API Advice External-LJA
    Examples:
      | merchant | terminal | pwd | msisdn | accNo | amount | rc |
#      | ?        | ?        | ?   | ?      | ?     | ?      | 00 |

  @external_lja_adv_failed
  Scenario Outline: [External-JPA] Partner hit API Advice External-LJA with invalid <param> <descTest>
    Given Invalid Request ("<merchant>", "<terminal>", "<pwd>", "<msisdn>", "<accNo>", "<amount>", "<param>", "<descTest>") API Advice External-LJA
    When Partner hit API Advice External-LJA
    Then Partner gets response "<rc>" API Advice External-LJA
    Examples:
      | param    | descTest       | merchant | terminal  | pwd          | msisdn       | accNo | amount   | rc |
#      | trx_type | Null           | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | trx_type | Empty          | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | merchant | Null           |          | ?         | ?            | ?            | ?     | ?        | 05 |
#      | merchant | Empty          |          | ?         | ?            | ?            | ?     | ?        | 05 |
#      | terminal | Null           | ?        |           | ?            | ?            | ?     | ?        | 05 |
#      | terminal | Empty          | ?        |           | ?            | ?            | ?     | ?        | 05 |
#      | terminal | Invalid        | ?        | ids_bimas | ?            | ?            | ?     | ?        | 05 |
#      | pwd      | Null           | ?        | ?         |              | ?            | ?     | ?        | 05 |
#      | pwd      | Empty          | ?        | ?         |              | ?            | ?     | ?        | 05 |
#      | pwd      | Invalid        | ?        | ?         | Idmjkt1pass1 | ?            | ?     | ?        | 05 |
#      | msisdn   | Null           | ?        | ?         | ?            |              | ?     | ?        | 05 |
#      | msisdn   | Empty          | ?        | ?         | ?            |              | ?     | ?        | 05 |
#      | msisdn   | Invalid_Format | ?        | ?         | ?            | 812000000001 | ?     | ?        | 05 |
#      | acc_no   | Null           | ?        | ?         | ?            | ?            |       | ?        | 05 |
#      | acc_no   | Empty          | ?        | ?         | ?            | ?            |       | ?        | 05 |
#      | trx_date | Null           | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | trx_date | Empty          | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | trx_date | Invalid_Format | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | amount   | Null           | ?        | ?         | ?            | ?            | ?     |          | 05 |
#      | amount   | Empty          | ?        | ?         | ?            | ?            | ?     |          | 05 |
#      | amount   | Invalid_Format | ?        | ?         | ?            | ?            | ?     | rp100000 | 05 |
#      | amount   | Invalid_Amount | ?        | ?         | ?            | ?            | ?     | 10000    | 05 |
#      | bill_ref | Null           | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | bill_ref | Empty          | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | bill_ref | Invalid        | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | trx_id   | Null           | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | trx_id   | Empty          | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | trx_id   | Double         | ?        | ?         | ?            | ?            | ?     | ?        | 05 |
#      | bill_ref | Timeout        | ?        | ?         | ?            | ?            | ?     | ?        | 05 |