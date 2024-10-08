Feature: OTN Multifinance

  @xml
  Scenario Outline: InquiryPayment_FIANKA
    Given I perform inquiry with partnerid "<s>", productid "<s1>", customerid "<s2>"
    * I got response inquiry with expected rc "<rc>"
    When I perform payment with partnerid "<s>", productid "<s1>", customerid "<s2>"
    * I got response payment with expected rc "<rcpay>"

    Examples:
      | s   | s1   | s2         | rc | rcpay |
      | ASM | FNKA | 1714621907 | 00 |    00 |
