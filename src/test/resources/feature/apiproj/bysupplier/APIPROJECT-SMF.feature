@apiproject-SMF
Feature: apiproject-SMF

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "AJ"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "PRL"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "IDO"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "MBP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "AYP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "ALGO"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "DJI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "BMI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "toped"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "BBM"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "SLS"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "SHP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "OY"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "LJA"
    Then I perform POST for inq pay rev XML

  @get
  Scenario: get
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "SAT"
    Then I perform GET request for inq pay rev and get Response

  @get
  Scenario: get
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "MIDI"
    Then I perform GET request for inq pay rev and get Response

  @get
  Scenario: get
    Given I have Excel data file "exceldata/apiproject-SMF.xlsx" and sheet "SIL"
    Then I perform GET request for inq pay rev and get Response
