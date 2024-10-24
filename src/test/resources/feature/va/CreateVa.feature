Feature: Create Va

  @create_va_example_base
  Scenario Outline: Bank hit Create VA (condition : "<condition>")
    Given "<condition>" Request ("<bankCode>","<tx_amt>","<is_open>","<va_expired_time>","<is_lifetime>","<username>","<trx_expiration_time>","<partner_id>","<extendinfo>","<terminal>") API Create VA
    When User Hit API Create Va
    Then User gets response "<rc>" API Creata VA
    Examples:
      | rc | condition            |bankCode | tx_amt | is_open | va_expired_time | is_lifetime | username | trx_expiration_time | partner_id | extendinfo     |terminal    |
      |000 |valid                 |008      |50000   |true     |1000             |false        |IDX       |500                  |IDX         |{\"id\":\"IDX\"}|IDS         |
      |032 |Invalid Format Tx_id  |008      |50000   |true     |1000             |false        |IDX       |500                  |IDX         |{\"id\":\"IDX\"}|IDS         |
      |032 |Invalid Format Date   |008      |50000   |true     |1000             |false        |IDX       |500                  |IDX         |{\"id\":\"IDX\"}|IDS         |


  @create_va_json_base
  Scenario Outline: Bank hit API Send Message
    Given Request ("<reqBody>") API Create VA
    When User Hit API Create Va
    Then User gets response "<rc>" API Creata VA
    Examples:
    | reqBody     | rc|
    | {\"tx_id\":\"IDX202403070024\",\"tx_date\":\"2024-03-0710:00:00\",\"bank_code\":\"008\",\"tx_amt\":\"50000\",\"is_open\":\"true\",\"va_expired_time\":\"100\",\"is_lifetime\":\"true\",\"username\":\"IDX\",\"trx_expiration_time\":\"500\",\"partner_id\":\"IDX\",\"extendinfo\":\"{\\\"id\\\":\\\"IDX\\\"}\",\"terminal\":\"IDS\",\"signature\":\"{{signature}}\"}|     |