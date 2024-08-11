@SOB_Dana_Product_List
Feature: Dana Product List

  @navigate_dana_product
  Scenario Outline: Navigate into Dana Product List -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to Dana Product List
    Examples:
      | condition    | username |
      | Successfully | adminqa  |

  @dana_product_show_action_column
  Scenario: Filter data and Action column -- Successfully
    Given I am in Dana Product List
    * I hit Filter Button on Dana Product List
    When I hit Apply Button on Dana Product List
    Then Dana Display Action Column in Product List
    Then Dana Datatable show Product List

  @dana_product_filter_data
  Scenario Outline: Filter data by Field <fields> -- <condition>
    Given I am in Dana Product List
    * I hit Filter Button on Dana Product List
    * I choose "<fields>" and fill with "<value>" on Dana Product
    When I hit Apply Button on Dana Product List
    Then Dana Datatable "<condition>" show Product List
    Examples:
      | fields              | value      | condition    |
      | Product ID          | BPRKM      | Successfully |
      | New Product ID      | 21007      | Successfully |
      | Supplier ID         | 2          | Successfully |
      | Product Description | PDAM Aetra | Successfully |
      | Status              | Inactive   | Successfully |
      | Product ID          | 1          | Failed       |
      | New Product ID      | a          | Failed       |
      | Supplier ID         | a          | Failed       |


  @dana_changing_product_to_active_or_inactive
  Scenario Outline: Changing Product to "<switch>"
    Given I am in Dana Product List
    * I hit Filter Button on Dana Product List
    When I hit Apply Button on Dana Product List
    Then I Hit Switch Button Product Row "<row>" to "<switch>" Product
    Then I Hit "<btn>" Button in Status Confirmation and Show Alert
    Examples:
      | row | switch   | btn |
      | 1   | active   | yes |
      | 2   | inactive | yes |

  @dana_changing_size_datatable
  Scenario Outline: Changing Size Datatable
    Given I am in Dana Product List
    * I hit Filter Button on Dana Product List
    When I hit Apply Button on Dana Product List
    Then I Hit Size Datatable Button and Select "<size>"
    Then Dana Datatable show Product List
    Examples:
      | size |
      | 5    |
      | 10   |
      | 20   |

  @dana_next_or_prev_datatable
  Scenario Outline: <btn> Page Datatable
    Given I am in Dana Product List
    * I hit Filter Button on Dana Product List
    When I hit Apply Button on Dana Product List
    When I hit "<btn>" Button "<asmuch>" times
    Examples:
      | btn  | asmuch |
      | next | 2      |
      | prev | 2      |