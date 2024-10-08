@apiprojectrgres
Feature: APIPROJECT

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "MACF"
    Then I perform POST for inq pay rev XML

#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "MF"
#    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "SMF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "APF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "BMF"
    Then I perform POST for inq pay rev XML

#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "CVK"
#    Then I perform POST for inq pay rev XML

#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "BPRKM|BMI"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "BPRKM|LJA"
#    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "BNF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "BNF|LJA"
    Then I perform POST for inq pay rev XML

  @xmltop
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "TOP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "TOP|LJA"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "MDL|BMI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "BPFI|SHP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "NRC|SHP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "NRC|BMI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "SMMF|SHP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "SMMF|LJA"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "SMMF|BMI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "SSMMF|SHP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "SSMMF|LJA"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "SSMMF|BMI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "TNK|SLS"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "TNK|BMI"
    Then I perform POST for inq pay rev XML

#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "AMM|SLS"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "AMM|LJA"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "AMM|BMI"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "EDV|LJA"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "DNKI|BMI"
#    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/api-project-IDO.xlsx" and sheet "TKM|BMI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayadvOTNADF
    Given I have Excel data file "exceldata/otn_multifinance.xlsx" and sheet "ADF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayadvOTNFIF
    Given I have Excel data file "exceldata/otn_multifinance.xlsx" and sheet "FIF"
    Then I perform POST for inq pay rev XML