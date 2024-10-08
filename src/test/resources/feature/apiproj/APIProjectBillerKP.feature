Feature: APIProject Services

  @apiproj_inq_sukses
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject
    Given Valid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | agentId | agentPin       | storeId | productId | customerId     | rc | rcDesc |
      | SAT     | alf4!ds2015321 | IDS     | KPSEK     | 09103021000266 | 00 | SUKSES |
      | SAT     | alf4!ds2015321 | IDS     | KPSMB     | 09032123000142 | 00 | SUKSES |
      | SAT     | alf4!ds2015321 | IDS     | KPSMT     | 09251023001220 | 00 | SUKSES |

  @apiproj_pay_sukses
  Scenario Template: [APIProject] Partner hit API Payment APIProject
    Given Valid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | customerId     | amount  | charge | totalAmount | adminFee | productId | rc | rcDesc |
      | 09103021000266 | 648470  | 25000  | 673470      | 0        | KPSEK     | 00 | SUKSES |
      | 09032123000142 | 9740000 | 250000 | 9990000     | 0        | KPSMB     | 00 | SUKSES |
      | 09251023001220 | 999000  | 159850 | 1158850     | 0        | KPSMT     | 00 | SUKSES |

  @apiproj_inq_param_failed
  Scenario Template: [APIProject] Partner hit API Inquiry APIProject with invalid <param> <descTest>
    Given Invalid Request ("<agentId>", "<agentPin>", "<storeId>", "<productId>", "<customerId>", "<param>", "<descTest>") API Inquiry APIProject
    When Partner hit API Inquiry APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry APIProject
    Examples:
      | param      | descTest      | agentId | agentPin       | storeId | productId | customerId     | rc | rcDesc                                            |
#      | customerId | already_paid | SAT     | alf4!ds2015321 | IDS     | KPSEK     | 07673124187151 | 88 | ERROR - Tagihan sudah dibayar |
#      | customerId | already_paid | SAT     | alf4!ds2015321 | IDS     | KPSMB     | 06472024000442 | 88 | ERROR - Tagihan sudah dibayar |
#      | customerId | already_paid | SAT     | alf4!ds2015321 | IDS     | KPSMT     | 04421024000487 | 50 | Gagal Melakukan Pembayaran    |
      | customerId | gagal_pemb | SAT     | alf4!ds2015321 | IDS     | KPSEK     | 09103021000266 | 50 | Gagal Melakukan Pembayaran |
      | customerId | gagal_pemb | SAT     | alf4!ds2015321 | IDS     | KPSMB     | 09032123000142 | 50 | Gagal Melakukan Pembayaran |
      | customerId | gagal_pemb | SAT     | alf4!ds2015321 | IDS     | KPSMT     | 09251023001220 | 50 | Gagal Melakukan Pembayaran |
#      | customerId | wrong_product | SAT     | alf4!ds2015321 | IDS     | KPSMT     | 04263124227737 | 35 | Pastikan Transaksi di Menu Kredit Plus Elektronik |
#      | customerId | no_tagihan    | SAT     | alf4!ds2015321 | IDS     | KPSEK     | 04263124013210 | 36 | Tagihan belum tersedia / nomor belum jatuh tempo  |
#      | customerId | wrong_product | SAT     | alf4!ds2015321 | IDS     | KPSMB     | 04001024000911 | 35 | Pastikan Transaksi di Menu Kredit Plus Motor      |
#      | customerId | no_tagihan    | SAT     | alf4!ds2015321 | IDS     | KPSMT     | 04001024000969 | 36 | Tagihan belum tersedia / nomor belum jatuh tempo  |
#      | customerId | wrong_product | SAT     | alf4!ds2015321 | IDS     | KPSEK     | 09032123000206 | 35 | Pastikan Transaksi di Menu Kredit Plus Mobil      |
#      | customerId | no_tagihan    | SAT     | alf4!ds2015321 | IDS     | KPSMB     | 04262024000980 | 36 | Tagihan belum tersedia / nomor belum jatuh tempo  |


  @apiproj_pay_param_failed
  Scenario Template: [APIProject] Partner hit API Payment APIProject with invalid <param> <descTest>
    Given Invalid Request ("<customerId>", "<amount>", "<charge>", "<totalAmount>", "<adminFee>", "<productId>", "<param>", "<descTest>") API Payment APIProject
    When Partner hit API Payment APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment APIProject
    Examples:
      | param      | descTest   | customerId       | amount | charge | totalAmount | adminFee | productId | rc | rcDesc |
      | customerId | failed_pay | 0001624688540139 | 100000 | 0      | 105000      | 5000     | KPSEK     | ?  | ?      |

  @apiproj_rev_sukses
  Scenario Template: [APIProject] Partner hit API Reversal APIProject
    Given Valid Request ("<customerId>", "<productId>") API Reversal APIProject
    When Partner hit API Reversal APIProject
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Reversal APIProject
    Examples:
      | customerId       | productId | rc | rcDesc |
      | 0001624688540139 | KPSEK     | 00 | SUKSES |