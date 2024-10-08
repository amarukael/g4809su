Feature: Dana Kini
  # @xml
  # Scenario Outline: InquiryPayment_DanaKini
  #   Given I perform inquiry with partnerid "<s>", productid "<s1>", customerid "<s2>"
  #   * I got response inquiry with expected rc "<rc>"
  #   When I perform payment with partnerid "<s>", productid "<s1>", customerid "<s2>"
  #   # * I got response payment with expected rc "<rcpay>"
  #   Examples:
  #     | s   | s1  | s2       | rc | rcpay |
  #     | BBM | DKF | 00010003 | 00 |    00 |
  #     | BBM | DKF | 90000002 | 78 |     6 |
  #     | BBM | DKF | 91000001 | 00 |    00 |
  #     | BBM | DKF | 91000002 | 00 |    00 |
  #     | BBM | DKF | 90000003 | 00 |    00 |
  #     | BBM | DKF | 90000000 | 00 |    00 |

  @alfa
  Scenario Outline: InquiryPayment_DanaKini "<desc>"
    Given I perform inquiry with partnerid "<s>", productid "<s1>", customerid "<s2>"
    * I got response inquiry with expected rc "<rc>"
    # When I perform payment with partnerid "<s>", productid "<s1>", customerid "<s2>"
    # * I got response payment with expected rc "<rcpay>"

    Examples:
      | s   | s1  | s2       | rc | rcpay | desc     |
      | SAT | DKF | 00030001 | 88 |       | Terbayar |
      # | SAT | DKF | 00010002 | 14 |       | No kontrak salah |
      # | SAT | DKF | 00010001 | 00 |       | Sukses           |
      # | SAT | DKF | 90000002 | 00 |       | Sukses           |
      # | SAT | DKF | 90000002 | 00 |       | Sukses           |
      # | SAT | DKF | 91000001 | 76 |       | Timeout          |
      # | SAT | DKF | 91000002 |  6 |       | Error Lain       |
      # | SAT | DKF | 90000003 | 00 |    00 | Pay - Sukses     |
      # | SAT | DKF | 90000000 | 00 |    30 | Pay - Not Found  |
      # | SAT | DKF | 91000001 | 00 |    76 | Pay - Timeout    |
      # | SAT | DKF | 91000002 | 00 |     6 | Pay - Error Lain |
