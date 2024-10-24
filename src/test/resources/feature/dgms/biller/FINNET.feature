Feature: Finnet

    @finnet_bpjstk_bpu
    Scenario: Finnet BPJSKS BPU
        Given I have Excel data file "Excel Data/finnet.xlsx" and sheet "BPJSTK_BPU"
        Then I perform post for inq,pay,adv and then verify the rc

    @finnet_bpjstk_pu
    Scenario: Finnet BPJSKS PU
        Given I have Excel data file "Excel Data/finnet.xlsx" and sheet "BPJSTK_PU"
        Then I perform post for inq,pay,adv and then verify the rc