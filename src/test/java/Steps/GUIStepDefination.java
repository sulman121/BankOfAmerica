package Steps;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ExcelReader.ReadExcel;
import Util.Lib;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GUIStepDefination extends Lib {

	WebDriver driver;
	ReadExcel Readexcel = new ReadExcel("/Users/Sulman/eclipse-workspace/BankOfAmerica/TEST.xlsx");
	Actions ac;
	Select select;

	@Before
	public void pre_testing() throws IOException {

		if (ReadProperties("browser").equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
//		} else if (ReadProperties("browser").equals("FireFox"))
//			WebDriverManager.firefoxdriver().setup();
//		driver = new FirefoxDriver();

			driver.get(ReadProperties("url"));
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}

	@Given("user is at homepage")
	public void user_is_at_homepage() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Bank of America - Banking, Credit Cards, Loans and Investing");

	}

	@Then("user click on the creditCard Tab")
	public void user_click_on_the_creditCard_Tab() throws IOException {
		driver.findElement(By.xpath(ReadProperties("CreditCard"))).click();

	}

	@Then("user click on Shop All Cards")
	public void user_click_on_Shop_All_Cards() throws IOException {
		driver.findElement(By.id("btnCompareCreditCards")).click();
		String title = driver.getTitle();
		System.out.println(title);

	}

	@Then("user click on the CashReward Cards")
	public void user_click_on_the_CashReward_Cards() throws IOException {
		WebElement ele = driver.findElement(By.xpath("//a[contains(@class,'cash')]"));

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);

		driver.findElement(By.xpath(ReadProperties("APPLY"))).click();

	}

	@Then("user Verify the Title")
	public void user_Verify_the_Title() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Your Application", "Title Doesn't Match");

	}

//	============================================================>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<Second Feature<<<<<<<<<<<<<<<<<>>>>>>>>>>================================================================================

	@Then("user is on application page and verify the title")
	public void user_is_on_application_page_and_verify_the_title() {

		String title = driver.getTitle();
		System.out.println(title);
	}

	@Then("user enter First and Last name")
	public void user_enter_First_and_Last_name() throws IOException {

		driver.findElement(By.xpath(ReadProperties("FirstName")))
				.sendKeys(Readexcel.getCellData("Sheet1", "FirstName", 2));
		driver.findElement(By.xpath(ReadProperties("LastName")))
				.sendKeys(Readexcel.getCellData("Sheet1", "LastName", 2));
	}

	@Then("user enter Residential Address")
	public void user_enter_Residential_Address() throws IOException {
		driver.findElement(By.xpath(ReadProperties("Address"))).sendKeys(Readexcel.getCellData("Sheet1", "Address", 2));

	}

	@Then("user enter city State zipcode")
	public void user_enter_city_State_zipcode() throws IOException, InterruptedException {
		driver.findElement(By.xpath(ReadProperties("City"))).sendKeys(Readexcel.getCellData("Sheet1", "City", 2));

		driver.findElement(By.xpath(ReadProperties("State"))).sendKeys(Readexcel.getCellData("Sheet1", "State", 2));

		Thread.sleep(2000);
		driver.findElement(By.xpath(ReadProperties("Zipcode"))).sendKeys(Readexcel.getCellData("Sheet1", "Zipcode", 2));
	}

	@Then("user select the tab if he want to send the statement on different address")
	public void user_select_the_tab_if_he_want_to_send_the_statement_on_different_address() throws IOException {

		driver.findElement(By.xpath(ReadProperties("sendstatementtodifferent"))).click();
		driver.findElement(By.xpath(ReadProperties("sendstatementtodifferent"))).click();
	}

	@Then("user enter the phone number and than select the type")
	public void user_enter_the_phone_number_and_than_select_the_type() throws IOException {

		driver.findElement(By.xpath(ReadProperties("phonetype")))
				.sendKeys(Readexcel.getCellData("Sheet1", "phonetype", 2));

		driver.findElement(By.xpath(ReadProperties("phonenumber")))
				.sendKeys(Readexcel.getCellData("Sheet1", "phonenumber", 2));
	}

	@Then("user enter the email address")
	public void user_enter_the_email_address() throws IOException {
		driver.findElement(By.xpath(ReadProperties("Email"))).sendKeys(Readexcel.getCellData("sheet1", "Email", 2));

	}

	@Then("user select the living status and select his country citizen ship")
	public void user_select_the_living_status_and_select_his_country_citizen_ship() throws IOException {
		driver.findElement(By.xpath(ReadProperties("citizen"))).click();

	}

	@Then("user select the citizenship status and enter the SSN Number")
	public void user_select_the_citizenship_status_and_enter_the_SSN_Number() throws IOException {
		driver.findElement(By.xpath(ReadProperties("SSN"))).sendKeys(Readexcel.getCellData("Sheet1", "SSN", 2));

	}

	@Then("user select the dual citizenship option")
	public void user_select_the_dual_citizenship_option() throws IOException {
		driver.findElement(By.xpath(ReadProperties("DualCitizen"))).click();

	}

	@Then("user enter the date of birth")
	public void user_enter_the_date_of_birth() throws IOException {
		driver.findElement(By.xpath(ReadProperties("DOB"))).sendKeys(Readexcel.getCellData("Sheet1", "DOB", 2));
	}

	@Then("user select the employment status and occupation")
	public void user_select_the_employment_status_and_occupation() throws IOException {

		select = new Select(driver.findElement(By.xpath(ReadProperties("employementstatus"))));

		select.selectByVisibleText("Employed");

	}

	@Then("user enter the Total Annual Income")
	public void user_enter_the_Total_Annual_Income() throws IOException {

		driver.findElement(By.xpath(ReadProperties("Annualincome")))
				.sendKeys(Readexcel.getCellData("Sheet1", "Annualincome", 2));

	}

	@Then("user select the Source of Income")
	public void user_select_the_Source_of_Income() throws IOException {

		select = new Select(driver.findElement(By.xpath(ReadProperties("SourceofIncome"))));
		// select.selectByIndex(1);

		List<WebElement> list = select.getOptions();

		for (WebElement str : list) {
			System.out.println(str.getText());

		}

//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i).getText());
//		}
	}

	@Then("user Select the Housing Payment")
	public void user_Select_the_Housing_Payment() throws IOException {
		driver.findElement(By.xpath(ReadProperties("Houseingpayment")))
				.sendKeys(Readexcel.getCellData("Sheet1", "Housingpayment", 2));
	}

	@Then("user Accept the terms and conditions and click on continue")
	public void user_Accept_the_terms_and_conditions_and_click_on_continue() throws IOException {
		driver.findElement(By.xpath(ReadProperties("TermsCondition"))).click();
		driver.findElement(By.xpath(ReadProperties("Continue"))).click();
		String errormsg = driver.findElement(By.xpath(ReadProperties("error"))).getText();
		System.out.println(errormsg);

	}

	@Then("Check the color of the textbox if you did not enter any value")
	public void colorcheck() {
		String color = driver.findElement(By.id("zz_name_tb_icai_fnm_v_1")).getCssValue("color");
		System.out.println(color);
		String backcolor = driver.findElement(By.id("zz_name_tb_icai_fnm_v_1")).getCssValue("background-color");
		System.out.println(backcolor);
	}

	@Then("user click on the view all cards")
	public void user_click_on_the_view_all_cards() {
		driver.findElement(By.id("all-cards-tab")).click();

	}

	@Then("user click on the World Wildlife Fund")
	public void user_click_on_the_World_Wildlife_Fund() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.id("learn_more_title_4031563~2R~en_US")).click();

	}

	@Then("user click on the rewards")
	public void user_click_on_the_rewards() throws Exception {
		driver.findElement(By.id("rewards-section-link")).click();
		String title = driver.getTitle();
		System.out.println(title);
		takeascreenshot(driver);

	}

//	============================================================>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<Practice  Feature<<<<<<<<<<<<<<<<<>>>>>>>>>>================================================================================

	@Then("user enter username and password")
	public void enter_usernameand_password() throws InterruptedException {
		driver.findElement(By.id("onlineId1")).sendKeys("sulman121");
		int underline = driver.findElement(By.id("onlineId1")).getSize().getWidth();
		System.out.println(underline);
		String color = driver.findElement(By.id("onlineId1")).getCssValue("color");
		System.out.println(color);

		driver.findElement(By.name("passcode1")).sendKeys("Hello");

		String attribute = driver.findElement(By.id("onlineId1")).getAttribute("value");
		System.out.println(attribute);

		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println("Total links are " + links.size());

		for (int i = 0; i < links.size(); i++) {

			WebElement ele = links.get(i);

			String url = ele.getAttribute("href");

			verifyLinkActive(url);

		}

	}

	public static void verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {

		}

	}

	@After
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
