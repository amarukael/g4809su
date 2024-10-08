Feature: Dims MultiCredential
  Testing MultiCredential for API Dims

  @auth_multi_credendetial_sukses
 Scenario Template: Partner hit API Authorization DIMS Credential <partner> -- successfully
    Given Valid Request API Authorization DIMS with Partner "<partner>"
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS
    Examples:
      | partner |
      | DMY     |
      | IDS     |

  @inq_multi_credendetial_sukses
 Scenario Template: Partner hit API Account Inquiry DIMS Credential <partner> -- <condition>
    Given Valid Request ("2", "002", "BRI", "510654300", "04") API Account Inquiry DIMS with Partner "<partner>"
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | condition     | partner | rc      | httpCode |
      | successfully  | DMY     | 2001500 | 200      |
      | failed        | IDS     | 4041503 | 404      |

  @pay_multi_credendetial_sukses
 Scenario Template: Partner hit API Payment DIMS Credential <partner> -- successfully
    Given Valid Request ("1", "002", "510654300", "", "1", "1", "04", "", "IDR", "10000.00", "", "") API Payment DIMS with Partner "<partner>"
    When Partner hits API Payment DIMS
    Then Partner gets the Success response "2001600" and http code "200" API Payment DIMS
    Examples:
      | partner |
      | DMY     |
#      | IDS     |


  @adv_multi_credendetial_sukses
 Scenario Template: Partner hit API Advice DIMS Credential <partner> -- successfully
    Given Valid Request ("IDR", "10000.00") API Advice DIMS with Partner "<partner>"
    When Partner hits API Advice DIMS
    Then Partner gets the Success response "2001700" and http code "200" API Advice DIMS
    Examples:
      | partner |
      | DMY     |
#      | IDS     |