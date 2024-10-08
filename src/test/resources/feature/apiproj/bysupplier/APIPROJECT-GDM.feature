@apiproject
Feature: APIPROJECT

  @xmlAJ
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "AJ"
    Then I perform POST for inq pay rev XML

  @xmlALGO
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "ALGO"
    Then I perform POST for inq pay rev XML

  @xmlAYP
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "AYP"
    Then I perform POST for inq pay rev XML

  @xmlBBM
  Scenario: inqpayrevBBM
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "BBM"
    Then I perform POST for inq pay rev XML

  @xmlBMI
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "BMI"
    Then I perform POST for inq pay rev XML

  @xmlDJI
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "DJI"
    Then I perform POST for inq pay rev XML

  @xmlIDO
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "IDO"
    Then I perform POST for inq pay rev XML

  @xmlLJA
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "LJA"
    Then I perform POST for inq pay rev XML

  @xmlMBP
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "MBP"
    Then I perform POST for inq pay rev XML

  @xmlMIDI
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "MIDI"
    Then I perform POST for inq pay rev XML

  @xmlOY
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "OY"
    Then I perform POST for inq pay rev XML

  @xmlSAT
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "SAT"
    Then I perform POST for inq pay rev XML

  @xmlSHP
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "SHP"
    Then I perform POST for inq pay rev XML

  @xmlSIL
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "SIL"
    Then I perform POST for inq pay rev XML

  @xmlSLS
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "SLS"
    Then I perform POST for inq pay rev XML

  @xmlTOPED
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/API-project-GDM.xlsx" and sheet "toped"
    Then I perform POST for inq pay rev XML

  @getSIL
  Scenario: getSIL
    Given I have Excel data file "exceldata/API-PROJECT-GDM.xlsx" and sheet "SIL"
    Then I perform GET request for inq pay rev and get Response

  @getMIDI
  Scenario: getMIDI
    Given I have Excel data file "exceldata/API-PROJECT-GDM.xlsx" and sheet "MIDI"
    Then I perform GET request for inq pay rev and get Response

  @getSAT
  Scenario: getSAT
    Given I have Excel data file "exceldata/API-PROJECT-GDM-STG.xlsx" and sheet "SAT"
    Then I perform GET request for inq pay rev and get Response

