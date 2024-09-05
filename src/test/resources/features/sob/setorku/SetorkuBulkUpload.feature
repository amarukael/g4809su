@SOB_Setorku_bulk_upload
Feature: Setorku Bulk Upload

  @navigate_setorku_bulk_upload
  Scenario Outline: [SOB SetorKu] Navigate into SetorKu Bulk Upload Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Bulk Upload"

    Examples:
      | condition    | username  |
      | Successfully | testotoqa |
      # | Failed       | rhesa    |

  @setorku_bulk_upload_filter_by_field
  Scenario Outline: [SOB SetorKu] Get data Bulk Upload using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Bulk Upload"
    * I click filter button on "SetorKu Bulk Upload"
    * I click field "Status" and fill with "<value>" on Setorku Bulk Upload
    When I click apply button filter
    Then Datatable show data "SetorKu Bulk Upload"

    Examples:
      | value     |
      | Waiting   |
      | Generated |
      | Canceled  |

  @setorku_bulk_upload_add_bulk_upload
  Scenario Outline: [SOB SetorKu] Add Bulk Upload -- "<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Bulk Upload"
    * I click add Bulk Upload button on SetorKu Bulk Upload
    * I click field "Name" and fill with "<name>" on Setorku Bulk Upload
    When I click button submit form
    Then "SetorKu Bulk Upload" Show Alert Success

    Examples:
      | name          | condition |
      | TestBulkUp997 | Success   |
      | space,10      | Success   |
      | random,26     | Success    |
      |               | Failed    |

  @setorku_bulk_upload_add_detail_bulk_upload
  Scenario Outline: [SOB SetorKu] Add Detail Bulk Upload -- "<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Bulk Upload"
    * I click filter button on "SetorKu Bulk Upload"
    * I click field "Status" and fill with "Waiting" on Setorku Bulk Upload
    When I click apply button filter
    Then Datatable show data "SetorKu Bulk Upload"
    Given I click detail on row "<row>" Setorku Bulk Upload
    When I click add data button on Setorku Detail Bulk
    * I click field "Merchant Transaction ID" and fill with "<merchTransId>" on Setorku Bulk Upload
    * I click field "User Code" and fill with "<usercode>" on Setorku Bulk Upload
    * I click field "Username" and fill with "<username>" on Setorku Bulk Upload
    * I click field "Customer ID" and fill with "<custId>" on Setorku Bulk Upload
    * I click field "Total Amount" and fill with "<totalAmount>" on Setorku Bulk Upload
    * I click field "Period" and fill with "<period>" on Setorku Bulk Upload
    When I click button submit form
    Then "SetorKu Bulk Upload" Show Alert Success

    Examples:
      | row | merchTransId | usercode  | username   | custId    | totalAmount | period | condition |
      |   1 | timestamp,3  | number,10 | randomuser | number,10 |      100000 |      3 | Success   |
      |   4 |              | number,10 | randomuser | number,10 |      100000 |      3 | Success   |
      |   4 | timestamp,3  | number,10 | space,5    | number,10 |      100000 |      3 | Success   |
      |   4 | timestamp,3  | number,10 | randomuser | number,10 | number,5    |      3 | Success   |
      |   4 | timestamp,3  | number,10 | randomuser | number,10 |     5000000 |      3 | Success   |
      |   4 | timestamp,3  | number,10 | randomuser | number,10 |      100000 |     12 | Success   |
      |   4 | timestamp,3  | number,10 | randomuser | number,10 |      100000 |     13 | Success   |
      |   2 | number,21    | number,10 | randomuser | number,10 |      100000 |      3 | Success   |
      |   2 | timestamp,3  | space,5   | randomuser | number,10 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | random,255 | number,10 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | random,10  | number,10 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 |            | number,10 |      100000 |      3 | Failed    |
      |   2 | space,5      | number,10 | randomuser | number,10 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | random,10 | randomuser | number,10 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | random,15 | randomuser | number,10 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  |           | randomuser | number,10 |      100000 |      3 | Failed    |
      |   2 | number,9     | number,10 | randomuser | number,10 |      100000 |      3 | Failed    |
      |   2 | random,15    | number,10 | randomuser | number,10 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | random,30 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | random,15 |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser |           |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | space,5   |      100000 |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | number,10 | random,6    |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | number,10 | random,10   |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | number,10 | number,10   |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | number,10 |             |      3 | Failed    |
      |   2 | timestamp,3  | number,10 | randomuser | number,10 | space,5     |      3 | Failed    |

  @setorku_bulk_upload_action_bulk_upload
  Scenario Outline: [SOB SetorKu] Action Bulk Upload -- "<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Bulk Upload"
    * I click filter button on "SetorKu Bulk Upload"
    When I click apply button filter
    Then Datatable show data "SetorKu Bulk Upload"
    Given I click "<btn>" button on row "<row>" Setorku Bulk Upload
    Then I Hit "<final>" Button in Status Confirmation
    When "SOB" Show Alert Success
    Then I click detail on row "<row>" Setorku Bulk Upload
    Given I validate payment code show on Setorku Bulk Upload

    Examples:
      | btn     | row | final  | condition |
      | approve |   1 | yes    | Success   |
      | reject  |   2 | yes    | Success   |
      | approve |   4 | cancel | Success   |
      | approve |   3 | yes    | Failed    |

  @setorku_bulk_upload_add_bulk_upload_ehe
  Scenario Outline: [SOB SetorKu] Add Bulk Upload -- "<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Bulk Upload"
    * I click add Bulk Upload button on SetorKu Bulk Upload
    * I click field "Name" and fill with "<name>" on Setorku Bulk Upload
    When I click button submit form
    * I click add Bulk Upload button on SetorKu Bulk Upload
    * I click field "Name" and fill with "<name>" on Setorku Bulk Upload
    When I click button submit form
    * I click add Bulk Upload button on SetorKu Bulk Upload
    * I click field "Name" and fill with "<name>" on Setorku Bulk Upload
    When I click button submit form
    * I click add Bulk Upload button on SetorKu Bulk Upload
    * I click field "Name" and fill with "<name>" on Setorku Bulk Upload
    When I click button submit form
    * I click add Bulk Upload button on SetorKu Bulk Upload
    * I click field "Name" and fill with "<name>" on Setorku Bulk Upload
    When I click button submit form
    * I click add Bulk Upload button on SetorKu Bulk Upload
    * I click field "Name" and fill with "<name>" on Setorku Bulk Upload
    When I click button submit form

    Examples:
      | name     | condition |
      | random,5 | Success   |
