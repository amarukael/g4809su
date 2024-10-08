Feature: Tokped

    @
    Scenario: Inquiry
      Given I have Excel data file "Excel Data/Tokped.xlsx" and sheet "Sheet2"
      Then I perform POST request "/inq" and get Response for XML

    @soap
    Scenario: SOAP
      Given I have Excel data file "Excel Data/datafile.xlsx" and sheet "SOAP"
      Then I perform POST request "https://ids-fe1.com/IDSProject/IDM_WS" and get Response for SOAP

    @inq
    Scenario: Tokped
      Given I have Excel data file "Excel Data/Tokped.xlsx" and sheet "Voucher Belajar"
      Then I perform post for inq,pay,adv and then verify the rc

    @in
    Scenario: Tokped Get
      Given I have Excel data file "Excel Data/Tokped.xlsx" and sheet "Bejar Adv"
      Then I perform get for inq,pay,adv and then verify the rc mock