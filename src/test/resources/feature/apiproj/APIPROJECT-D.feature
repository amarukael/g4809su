@apiprojectrgres
Feature: APIPROJECT

#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "KPMIDI"
#    Then I perform POST for inq pay rev XML

#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "KPALFA"
#    Then I perform POST for inq pay rev XML

#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/apiproject-master-dataset.xlsx" and sheet "test"
#    Then I perform POST for inq pay rev XML NEW
#

#    @Ttet
#  Scenario: testalfa
#    Given Valid Master ApiProjectAlfa "exceldata/apiproject-master-dataset.xlsx" and Sheet "testalfa"
#    When I perform testing prefer from Excel data
#    Then I get the appropriate response

#  @Ttet
#  Scenario: testalfa
#    Given Valid Master ApiProjectAlfa CSV "exceldata/apiproject-master-dataset.csv"
#    When I perform testing prefer from Excel data
#    Then I get the appropriate response
#
#
#  @Ttet
#  Scenario: testalfa
#    Given Valid Master ApiProjectAlfa CSV "exceldata/apiproject-master-dataset.csv"
#    When I perform testing prefer from Excel data
#    Then I get the appropriate response
#
#
#    C:/Users/naufal.pujianputra/Desktop/Automation File/TestAPINONALFA.csv

  Scenario: test apiproject non-alfa
    Given I have Excel data file for testing ApiProject Non-Alfa "C:/Users/naufal.pujianputra/Desktop/Automation File/TestAPINONALFA.csv"
    When I perform testing prefer from Excel data for ApiProject Non-Alfa
    Then I get the appropriate response for ApiProject Non-Alfa
    Then I create report automation ApiProject Non-Alfa

#  Scenario: test apiproject alfa
#    Given I have Excel data file for testing ApiProject Alfa "src/test/resources/exceldata/apiproject-alfa-master-dataset.csv"
#    When I perform testing prefer from Excel data for ApiProject Alfa
#    Then I get the appropriate response for ApiProject Alfa
#    Then I create report automation ApiProject Alfa

#  C:/Users/naufal.pujianputra/Desktop/Automation File/apiproject-nonalfa-master-dataset - Inquiry.csv
#  src/test/resources/exceldata/apiproject-nonalfa-master-dataset2.csv

##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "MACF"
##    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "MF"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "SMF"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "APF"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "BMF"
#    Then I perform POST for inq pay rev XML
#
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "CVK"
##    Then I perform POST for inq pay rev XML
#
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "BPRKM|BMI"
##    Then I perform POST for inq pay rev XML
##
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "BPRKM|LJA"
##    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "BNF"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "BNF|LJA"
#    Then I perform POST for inq pay rev XML
#
#  @xmltop
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "TOP"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "TOP|LJA"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "MDL|BMI"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "BPFI|SHP"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "NRC|SHP"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "NRC|BMI"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "SMMF|SHP"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "SMMF|LJA"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "SMMF|BMI"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "SSMMF|SHP"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "SSMMF|LJA"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "SSMMF|BMI"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "TNK|SLS"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "TNK|BMI"
#    Then I perform POST for inq pay rev XML
#
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "AMM|SLS"
##    Then I perform POST for inq pay rev XML
##
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "AMM|LJA"
##    Then I perform POST for inq pay rev XML
##
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "AMM|BMI"
##    Then I perform POST for inq pay rev XML
##
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "EDV|LJA"
##    Then I perform POST for inq pay rev XML
##
##  @xml
##  Scenario: inqpayrev
##    Given I have Excel data file "exceldata/regres.xlsx" and sheet "DNKI|BMI"
##    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayrev
#    Given I have Excel data file "exceldata/regres.xlsx" and sheet "TKM|BMI"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayadvOTNADF
#    Given I have Excel data file "exceldata/otn_multifinance.xlsx" and sheet "ADF"
#    Then I perform POST for inq pay rev XML
#
#  @xml
#  Scenario: inqpayadvOTNFIF
#    Given I have Excel data file "exceldata/otn_multifinance.xlsx" and sheet "FIF"
#    Then I perform POST for inq pay rev XML