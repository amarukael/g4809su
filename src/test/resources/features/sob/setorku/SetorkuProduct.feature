@SOB_Setorku_Product @SOB @SetorKu
Feature: Setorku Product

  @navigate_setorku_product
  Scenario Outline: [SOB][SetorKu][FE] Product - Navigate into SetorKu Product Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Product"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      # | Failed       | rhesa    |

  @setorku_product_filter_by_field
  Scenario Outline: [SOB][SetorKu][FE] Product - Get data product using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    * I click filter button on "SetorKu Product"
    * I click field "<label>" and fill with "<value>" on Setorku Product
    When I click apply button filter
    Then Datatable show data "SetorKu Product"

    Examples:
      | label               | value                           |
      | Company Description | MCF                             |
      | Company Description | M                               |
      | Company Description | F                               |
      | Company Description | randomCase,MCF                  |
      | Product Name        | Mega Central Finance            |
      | Product Name        | randomCase,Mega Central Finance |
      | Product Name        | Mega                            |
      | Product Name        | Finance                         |
      | Company Description | random,45                       |
      | Company Description | random,46                       |
      | Product Name        | random,100                      |
      | Product Name        | random,101                      |

  @setorku_product_filter_by_all_field
  Scenario Outline: [SOB][SetorKu][FE] Product - Get data product using Filter by all field
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    * I click filter button on "SetorKu Product"
    * I click field "Company Description" and fill with "<desc>" on Setorku Product
    * I click field "Product Name" and fill with "<name>" on Setorku Product
    When I click apply button filter
    Then Datatable show data "SetorKu Product"

    Examples:
      | desc      | name              |
      | OTO       | Oto finance Mobil |
      | OT        | Oto finance Mobil |
      | OTO       | Oto               |
      | random,45 | Oto finance Mobil |
      | OTO       | random,100        |
      | random,46 | random,101        |

  @setorku_product_add_product
  Scenario Outline: [SOB][SetorKu][FE] Product - Add Product - Space
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    * I click add product button on page
    * I click field "Company ID" and fill with "<compID>" on Setorku Product
    * I click field "Company Description" and fill with "<compDesc>" on Setorku Product
    * I click field "Product ID" and fill with "<prodId>" on Setorku Product
    * I click field "Product Name" and fill with "<prodName>" on Setorku Product
    * I click field "Prefix" and fill with "<prefix>" on Setorku Product
    * I click field "Secret Key" and fill with "<secret>" on Setorku Product
    When I click button submit form
    Then "SetorKu Product" Show Alert Success

    Examples:
      | compID          | compDesc        | prodId          | prodName         | prefix         | secret          |
      | space,3         |        1itsDesc |             166 |         1itsName |            166 |      1itsSecret |
      |             166 | space,3         |             266 |         2itsName |            266 |      2itsSecret |
      |             266 |        2itsDesc | space,3         |         3itsName |            366 |      3itsSecret |
      |             366 |        3itsDesc |             366 | space,3          |            466 |      4itsSecret |
      |             466 |        4itsDesc |             466 |         5itsName | space,3        |      5itsSecret |
      |             566 |        5itsDesc |             566 |         6itsName |            566 | space,3         |
      |                 |                 |                 |                  |                |                 |
      | random,12       | random,46       | random,12       | random,101       | random,6       | random,46       |
      | random,11       | itsDesc2        |             667 | itsName2         |            667 | itsSecret       |
      |             667 | random,45       |             668 | itsName3         |            668 | itsSecret       |
      |             668 | itsDesc3        | random,11       | itsName4         |            669 | itsSecret       |
      |             669 | itsDesc4        |             669 | random,100       |            661 | itsSecret       |
      |             661 | itsDesc5        |             661 | itsName5         | random,5       | itsSecret       |
      |             662 | itsDesc6        |             662 | itsName6         |            662 | random,45       |
      | numeric,11      | itsDesc2        |             667 | itsName2         |            667 | itsSecret       |
      | alphanumeric,11 | itsDesc2        |             667 | itsName2         |            667 | itsSecret       |
      |             667 | alphanumeric,45 |             668 | itsName3         |            668 | itsSecret       |
      |             668 | itsDesc3        | alphanumeric,11 | itsName4         |            669 | itsSecret       |
      |             669 | itsDesc4        |             669 | alphanumeric,100 |            661 | itsSecret       |
      |             661 | itsDesc5        |             661 | itsName5         | alphanumeric,5 | itsSecret       |
      |             662 | itsDesc6        |             662 | itsName6         |            662 | alphanumeric,45 |
      |             666 | itsDesc         |             666 | itsName1         |            666 | itsSecret       |
      |             666 | itsDesc         |             666 | itsName1         |            666 | itsSecret       |
      |             666 | its1Desc        |             676 | its1Name1        |            676 | its1Secret      |
      |             676 | itsDesc         |             686 | its2Name1        |            686 | its2Secret      |
      |             686 | its2Desc        |             666 | its3Name1        |            696 | its3Secret      |
      |             696 | its3Desc        |             616 | itsName1         |            616 | its4Secret      |
      |             616 | its4Desc        |             626 | its4Name1        |            666 | its5Secret      |
      |             626 | its5Desc        |             636 | it5Name1         |            626 | itsSecret       |

  @setorku_product_edit_product
  Scenario Outline: [SOB][SetorKu][FE] Product - Edit product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    When I click filter button on "SetorKu Product"
    * I click field "Product Name" and fill with "its" on Setorku Product
    When I click apply button filter
    Then I click button edit on row "<row>" SetorKu Product
    * I click field "Company Description" and fill with "<compDesc>" on Setorku Product
    * I click field "Product Name" and fill with "<prodName>" on Setorku Product
    * I click field "Prefix" and fill with "<prefix>" on Setorku Product
    When I click button submit form
    Then "SetorKu Product" Show Alert Success

    Examples:
      | row | compDesc        | prodName         | prefix         |
      |   2 | itsEditTest1    | itsTestEdit1     |            999 |
      |   2 | random,45       |                  |                |
      |   2 | alphanumeric,45 |                  |                |
      |   2 | space,3         |                  |                |
      |   2 |                 | random,100       |                |
      |   2 |                 | alphanumeric,100 |                |
      |   2 |                 | space,5          |                |
      |   2 |                 |                  | random,5       |
      |   2 |                 |                  | alphanumeric,5 |
      |   2 |                 |                  | space,3        |
      |   2 | alphanumeric,46 | alphanumeric,100 | alphanumeric,6 |

  @setorku_product_delete_product
  Scenario Outline: [SOB][SetorKu][FE] Product - Delete Product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    When I click filter button on "SetorKu Product"
    * I click field "Product Name" and fill with "its" on Setorku Product
    When I click apply button filter
    Then I click button delete on row "<row>" SetorKu Product
    Then I Hit "<btn>" Button in Status Confirmation
    Given SetorKu Product "<snackbar>" snackbar success

    Examples:
      | row | btn    | snackbar |
      |   1 | delete | show     |
      |   2 | cancel | not show |
