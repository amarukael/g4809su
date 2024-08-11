package com.ids.automation.stepdefinitions.sob.mnm;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.mnm.SOBMnMSendMessagePages;
import utility.Helper;

import static org.testng.Assert.*;


public class MnMSendMessageSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    SOBMnMSendMessagePages mAndMSendMessagePage;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    private  String tmpTemplateNm;
    private String tmpType;
    private String tmpFillMode;
    private String tmpTCase;
    Integer countImage = 1;

    @Then("I {string} navigate to M&M Send Message")
    public void i_navigate_to_m_m_send_message(String condition) {
        setUpMandMSendMessage();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        // condition check
        if (condition.equals("Successfully")) {
            homePage.openNavMenu("M&M", 0, 450, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Send Messages");
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Send Messages");
            homePage.MenuDrawer();
            sobHelper.delay(1000);
            assertEquals(mAndMSendMessagePage.getSendMessage(), "Send Messages");
        } else {
            assertTrue(homePage.checkNoNavMenu("M&M"));
        }

        sobHelper.delay(1000);
    }

    @Given("I am in M&M Send Message Page with {string}")
    public void i_am_in_mm_send_message_page_with(String username) {
        loginSteps.setUp();
        loginSteps.setUpLogin(username, "m&m_send_messages");
        setUpMandMSendMessage();
        homePage.MenuDrawer();
        homePage.openNavMenu("M&M", 0, 450, "smooth");
        homePage.openNavSubMenu("Send Messages");
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Send Messages");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I am in M&M Send Message Page")
    public void i_am_in_mm_send_message_page() {
        loginSteps.setUp();
        loginSteps.setUpLogin("ryormd", "m&m_send_messages");
        setUpMandMSendMessage();
        homePage.MenuDrawer();
        homePage.openNavMenu("M&M", 0, 450, "smooth");
        homePage.openNavSubMenu("Send Messages");
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Send Messages");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @And("I choose {string} on Messaging Product, {string} on Catagory")
    public void i_choose_on_messaging_product_on_catagory(String messageProduct, String catagory) {
        sobHelper.delay(3000);
        mAndMSendMessagePage.chooseMessagingProduct(messageProduct);
        sobHelper.delay(3000);
        mAndMSendMessagePage.chooseCatagory(catagory);
        sobHelper.delay(9000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Choose Template");
        sobHelper.delay(1000);
    }

    @Given("I choose {string} on Messaging Product, {string} on Catagory, {string} on Choose Template Name")
    public void i_choose_on_messaging_product_on_catagory_on_choose_template_name(String messageProduct
            , String catagory, String templateName) {
        sobHelper.delay(3000);
        mAndMSendMessagePage.chooseMessagingProduct(messageProduct);
        sobHelper.delay(3000);
        mAndMSendMessagePage.chooseCatagory(catagory);
        sobHelper.delay(5000);
        mAndMSendMessagePage.templateNm(templateName);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Fill Fields");
        homePage.scrollBody(300);
        sobHelper.delay(4000);
    }

    @Given("I fill Mandatory Field On {string} Template with Message Body {string}")
    public void i_fill_mandatory_field_on_template(String type, String fMessageBody) {
        tmpType = type;
        int flgTitle = 0;
        if (type.equals("image") || type.equals("video") || type.equals("document")) {
            flgTitle = 1;
            String pathFile = "";
            if (type.equals("image")) {
                pathFile = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                        "/valid_image.jpg";
            } else if (type.equals("document")) {
                pathFile = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                        "/valid_doc.pdf";
            } else if (type.equals("video")) {
                pathFile = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                        "/valid_video.mp4";
            }

            mAndMSendMessagePage.addFile(pathFile);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
            countImage++;
            sobHelper.delay(800);
        } else if (type.equals("text") || type.equals("3_button_static_dynamic")) {
            // check param title
            flgTitle = 1;
            String tmpValTitle = mAndMSendMessagePage.getValTitle();
            int pTitle = StringUtils.countMatches(tmpValTitle, "{{");
            String[] fValueTitle = sobHelper.getConstructFields("title", type, "");
            for (int i = 1; i <= pTitle; i++) {
                mAndMSendMessagePage.fillTitle(i, fValueTitle[i - 1]);
            }
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
            countImage++;
            sobHelper.delay(800);
        }

        // fill param body
        if (flgTitle == 1) homePage.scrollBody(465);
        else homePage.scrollBody(360);

        String tmpValBody =  mAndMSendMessagePage.getValBody();
        System.out.println(tmpValBody);
        int pBody = StringUtils.countMatches(tmpValBody, "{{");
        String[] fValueBody = sobHelper.getConstructFields("body", type, fMessageBody);
        for (int i = 1; i <= pBody; i++) {
            mAndMSendMessagePage.fillBody(i, fValueBody[i - 1]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
        countImage++;

        if (type.contains("button_static_dynamic")) {
            // fill param button
            String tmpValBtnWeb = mAndMSendMessagePage.getValBtnWeb();
            int pBtnWeb = StringUtils.countMatches(tmpValBtnWeb, "{{");
            String[] fValueBtnWeb = sobHelper.getConstructFields("btnWeb", type, "");
            for (int i = 1; i <= pBtnWeb; i++) {
                mAndMSendMessagePage.fillBtnWeb(i, fValueBtnWeb[i - 1]);
            }
            homePage.scrollBody(50);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
            countImage++;
            sobHelper.delay(800);

            String tmpValBtnCopyCode = mAndMSendMessagePage.getValBtnCopyCode();
            int pBtnCopyCode = StringUtils.countMatches(tmpValBtnCopyCode, "{{");
            String[] fValueBtnCopyCode = sobHelper.getConstructFields("btnCopyCode", type, "");
            for (int i = 1; i <= pBtnCopyCode; i++) {
                mAndMSendMessagePage.fillBtnCopyCode(i, fValueBtnCopyCode[i - 1]);
            }
            homePage.scrollBody(50);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
            countImage++;
        }

        sobHelper.delay(1000);
    }

    @Given("I fill Mandatory Field with Invalid Data On {string} Template {string}")
    public void i_fill_mandatory_field_with_invalid_data_on_template(String type, String tCase) {
        tmpType = type;
        tmpTCase = tCase;
        int flgTitle = 0;
        if (!tCase.equals("empty_field")) {
            if (type.equals("image") || type.equals("video") || type.equals("document")) {
                flgTitle = 1;
                String pathFile = "";
                if (type.equals("image")) {
                    pathFile = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                            "/invalid_image.jpg";
                } else if (type.equals("document")) {
                    pathFile = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                            "/invalid_doc.txt";
                } else if (type.equals("video")) {
                    pathFile = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                            "/invalid_video.mp4";
                }

                mAndMSendMessagePage.addFile(pathFile);
            } else if (type.equals("text") || type.equals("3_button_static_dynamic")) {
                // check param title
                flgTitle = 1;
                String tmpValTitle = mAndMSendMessagePage.getValTitle();
                int pTitle = StringUtils.countMatches(tmpValTitle, "{{");
                String[] fValueTitle = sobHelper.getConstructInvalidFields("title", type);
                for (int i = 1; i <= pTitle; i++) {
                    mAndMSendMessagePage.fillTitle(i, fValueTitle[i - 1]);
                }
            }
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Invalid Fill Fields");
            sobHelper.delay(800);
        }

        // fill param body
        if (flgTitle == 1) homePage.scrollBody(465);
        else homePage.scrollBody(360);

        if (!tCase.equals("empty_field")) {
            String tmpValBody =  mAndMSendMessagePage.getValBody();
            System.out.println(tmpValBody);
            int pBody = StringUtils.countMatches(tmpValBody, "{{");
            String[] fValueBody = sobHelper.getConstructInvalidFields("body", type);
            for (int i = 1; i <= pBody; i++) {
                mAndMSendMessagePage.fillBody(i, fValueBody[i - 1]);
            }
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Invalid Fill Fields " + countImage);
        countImage++;

        if (!tCase.equals("empty_field")) {
            if (type.contains("button_static_dynamic")) {
                // fill param button
                String tmpValBtnWeb = mAndMSendMessagePage.getValBtnWeb();
                int pBtnWeb = StringUtils.countMatches(tmpValBtnWeb, "{{");
                String[] fValueBtnWeb = sobHelper.getConstructInvalidFields("btnWeb", type);
                for (int i = 1; i <= pBtnWeb; i++) {
                    mAndMSendMessagePage.fillBtnWeb(i, fValueBtnWeb[i - 1]);
                }
                homePage.scrollBody(50);
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
                countImage++;
                sobHelper.delay(800);

                String tmpValBtnCopyCode = mAndMSendMessagePage.getValBtnCopyCode();
                int pBtnCopyCode = StringUtils.countMatches(tmpValBtnCopyCode, "{{");
                String[] fValueBtnCopyCode = sobHelper.getConstructInvalidFields("btnCopyCode", type);
                for (int i = 1; i <= pBtnCopyCode; i++) {
                    mAndMSendMessagePage.fillBtnCopyCode(i, fValueBtnCopyCode[i - 1]);
                }
                homePage.scrollBody(50);
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
                countImage++;
            }
        }

        sobHelper.delay(1000);
    }

    @When("I choose {string} on Template Name")
    public void i_choose_on_template_name(String templateName) {
        tmpTemplateNm =  templateName;
        mAndMSendMessagePage.templateNm(templateName);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
        countImage++;
        homePage.scrollBody(200);
        sobHelper.delay(1000);

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I choose Fill Mode {string} and Fill Field To")
    public void i_choose_fill_mode_and_fill_field_to(String fillMode) {
        // fill field To
        String valTo = "";
        if (fillMode.equals("manual")) {
            homePage.scrollBody(250);
//            valTo = "628811096677," +
//                    "6285773600198," +
//                    "6281228582184," +
//                    "6282372588755," +
//                    "6285788569440," +
//                    "6289508595059," +
//                    "628116391008," +
//                    "6281228057969";
//            valTo = "6281285312957," +
//                    "6285788569440," +
//                    "6281228057969";
            valTo = "6281285312957";
        } else {
            homePage.scrollBody(420);
            valTo = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                    "/recipient.txt";
        }

        mAndMSendMessagePage.chooseFillMode(fillMode, valTo);
        homePage.scrollBody(50);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I choose Fill Mode {string} and Fill Field To with Invalid Data {string}")
    public void i_choose_fill_mode_and_fill_field_to_with_invalid_data(String fillMode, String tCase) {
        // fill field To
        tmpFillMode = fillMode;
        homePage.scrollBody(450);
        String valTo = "";
        if (!tCase.equals("empty_field")) {
            if (fillMode.equals("manual")) {
                valTo = "Recepient 628811096677,6285773600198";
            } else {
                valTo = "C:/Users/BOIDS 48/Desktop/QA-Jira/IN-54/M&M QA" +
                        "/invalid_recipient.txt";
            }

            mAndMSendMessagePage.chooseFillMode(fillMode, valTo);
            homePage.scrollBody(50);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Invalid Fill Fields " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("Hit button Send Messages")
    public void hit_button_send_messages() {
        if (tmpTCase.equals("empty_field")) {
            mAndMSendMessagePage.hitSendMessage();
            homePage.scrollBody(-250);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Invalid Fill Fields " + countImage);
            countImage++;
            sobHelper.delay(800);
            homePage.scrollBody(500);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Invalid Fill Fields " + countImage);
            countImage++;
            sobHelper.delay(800);
        }
    }

    @When("Hit button Send Messages and Submit Send Message \\({string})")
    public void hit_button_send_messages_and_submit_send_message(String condition) {
        mAndMSendMessagePage.hitSendMessage();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Validation Page");
        sobHelper.delay(800);
        if (condition.equals("Failed") || condition.equals("Failed Url")
                || condition.equals("Failed Version")) {
            System.out.println("Delay Send Message Start..");
            sobHelper.delay(15000);
            System.out.println("Delay Send Message End..");
        }
        mAndMSendMessagePage.hitBtnSubmit();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Submit");
        if (condition.equals("Failed"))
            sobHelper.delay(1000);
        else
            if (tmpType.equals("image") || tmpType.equals("video"))
                sobHelper.delay(5000);
            else if (tmpType.equals("document"))
                sobHelper.delay(10000);
            else
                sobHelper.delay(5000);
    }

    @Then("I get list Template")
    public void i_get_list_template() {
        mAndMSendMessagePage.templateNm("");
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
        countImage++;
    }

    @Then("{string} open format Template Messaging")
    public void open_format_template_messaging(String condition) {
        if (condition.equals("Successfully")) {
            assertEquals(mAndMSendMessagePage.getTitle(), "Title");
            homePage.scrollBody(280);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
            countImage++;
            sobHelper.delay(1000);

            homePage.scrollBody(300);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
            countImage++;
            sobHelper.delay(1000);

            homePage.scrollBody(400);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
            countImage++;
            sobHelper.delay(1000);

            homePage.scrollBody(430);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
            countImage++;
            sobHelper.delay(1000);

            homePage.scrollBody(100);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
            countImage++;

            if (tmpTemplateNm.contains("test_button_remid")) {
                sobHelper.delay(1000);
                homePage.scrollBody(150);
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "M&M - Choose Template " + countImage);
                countImage++;
            }
        }

        sobHelper.delay(1000);
    }

    @Then("{string} Input Fields Message")
    public void input_fields_message(String condition) {
        if (condition.equals("Successfully")) {
            if (tmpType.equals("4_button_static_dynamic")) {
                homePage.scrollBody(380);
            } else if (tmpType.equals("3_button_static_dynamic")) {
                homePage.scrollBody(565);
            } else {
                homePage.scrollBody(550);
            }
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Fill Fields " + countImage);
            countImage++;
            sobHelper.delay(800);

            assertFalse(mAndMSendMessagePage.getTitleErrors(tmpType));
            assertFalse(mAndMSendMessagePage.getBodyErrors());
            assertFalse(mAndMSendMessagePage.getToManualErrors());
            assertFalse(mAndMSendMessagePage.getToBulkErrors());

            if (tmpType.contains("button_static_dynamic")) {
                assertFalse(mAndMSendMessagePage.getBtnWeb());
                assertFalse(mAndMSendMessagePage.getBtnCopyCode());
            }
        } else {
            if (tmpType.equals("3_button_static_dynamic")) {
                homePage.scrollBody(380);
            } else {
                homePage.scrollBody(500);
            }
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Invalid Fill Fields " + countImage);
            countImage++;

            if (tmpTCase.equals("empty_field")) {
                homePage.scrollBody(150);
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "M&M - Invalid Fill Fields " + countImage);
                countImage++;
            }
            sobHelper.delay(800);

            assertTrue(mAndMSendMessagePage.getTitleErrors(tmpType));
            assertTrue(mAndMSendMessagePage.getBodyErrors());
            if (tmpFillMode.equals("manual")) {
                assertTrue(mAndMSendMessagePage.getToManualErrors());
                assertFalse(mAndMSendMessagePage.getToBulkErrors());
            } else {
                assertFalse(mAndMSendMessagePage.getToManualErrors());
                assertTrue(mAndMSendMessagePage.getToBulkErrors());
            }

            if (tmpType.contains("button_static_dynamic")) {
                assertTrue(mAndMSendMessagePage.getBtnWeb());
                assertTrue(mAndMSendMessagePage.getBtnCopyCode());
            }
        }
    }

    @Then("{string} Send Message")
    public void send_message(String condition) {
        String ssName = "";
        if (condition.equals("Failed") || condition.equals("Failed Url")
                || condition.equals("Failed Version")) {
            assertTrue(mAndMSendMessagePage.getFailedAlert(condition));
            ssName = "M&M - Failed Alert";
            sobHelper.delay(800);
        } else {
            assertTrue(mAndMSendMessagePage.getSuccessAlert());
            ssName = "M&M - Success Alert";
            sobHelper.delay(1000);
        }


        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", ssName);
    }

    public void setUpMandMSendMessage() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        mAndMSendMessagePage = new SOBMnMSendMessagePages(driver);
    }
}
