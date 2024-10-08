Feature: APIPROJECT

  @xml
  Scenario: inqpayrevESTA
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "ESTA"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevDNKI
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "DNKI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevBMF
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "BMF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevSMMF
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "SMMF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevSSMMF
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "SSMMF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevBPRKMCS
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "BPRKMCS"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevBPR
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "BPR"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevCVK
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "CVK-SLS"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevMDL
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "MDL"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevNRC
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "NRC"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevTKM
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "TKM"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevAMM
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "AMM"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevTNK
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "TNK"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevBPFI
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "BPFI"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevSTR
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "STR"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevTOP
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "TOP"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevBUFMB
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "BUFMB"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevBNF
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "BNF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevKPSMT
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "KPSMT"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevKPSEK
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "KPSEK"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayrevKPSMB
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "KPSMB"
    Then I perform POST for inq pay rev XML

    #////////////////////////////////////////#
#
#  @json
#  Scenario: json
#    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "BMF"
#    Then I perform post for inq,pay,adv and then verify the rc
#
  @get
  Scenario: get
    Given I have Excel data file "exceldata/apiproject.xlsx" and sheet "CVK"
    Then I perform GET request for inq pay rev and get Response
#
#  @soap
#  Scenario: soap
#    Given I have Excel data file "exceldata/mandiri.xlsx" and sheet "mandiridirect"
#    Then I perform POST request "http://117.54.12.140:8080/PaymentGatewaySoap/SOAPMandiri_WS" and get Response for SOAP
