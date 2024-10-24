Feature: Automation Core

  Scenario: Token
  	Given I have Excel data file "Excel Data/datafile.xlsx" and sheet "token"
  	When I perform POST request "/token" and get Token

  Scenario: Inquiry
	Given I have Excel data file "Excel Data/datafile.xlsx" and sheet "inquiry"
	Then I perform POST request "/inquiry" and get Response

  @inqpayadv
  Scenario: inqpayadv
	 Given I have Excel data file "Excel Data/datafile.xlsx" and sheet "Sheet2"
	 Then I perform post for inq,pay,adv and then verify the rc

  @get
  Scenario: Get
  	Given I have Excel data file "Excel Data/datafile.xlsx" and sheet "getinq"
  	When I perform GET request "/inquiry" and get Response