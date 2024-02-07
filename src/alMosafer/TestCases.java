package alMosafer;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends Parameter {

	@BeforeTest
	public void SetUp() {
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		WebElement PopUpScreen = driver.findElement(By.className("sc-iBmynh"));

		if (PopUpScreen.isDisplayed()) {
			WebElement SARbutton = driver.findElement(By.className("cta__saudi"));
			SARbutton.click();

		}

	}

	@Test(priority = 1)
	public void CheckDefulteLanguage() {
		String expectedLang = "EN";
		String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang").toUpperCase();
		assertEquals(ActualLang, expectedLang);
	}

	@Test(priority = 2)
	public void CheckDefulteCurrencyIsSAR() {
		String expectedCurrency = "SAR";
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		assertEquals(ActualCurrency, expectedCurrency);

	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String expectedNum = "+966554400000";
		String ActualNum = driver.findElement(By.tagName("strong")).getText();
		assertEquals(ActualNum, expectedNum);

	}

	@Test(priority = 4)
	public void CheckQitafLogoIsDisplayed() {
		WebElement Footer = driver.findElement(By.tagName("footer"));
		boolean ActualLogo = Footer.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();

		assertEquals(ActualLogo, true);

	}

	@Test(priority = 5)
	public void CheckHotelsNotSelectedByDefault() {
		String ActualSelected = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		assertEquals(ActualSelected, "false");

	}

	@Test(priority = 6)
	public void ChickingDepatureDateAndReturnDate() {
		LocalDate TodayDate = LocalDate.now();
		// Expected
		int expectedDepatureDate = TodayDate.plusDays(1).getDayOfMonth();
		int expectedReturnDate = TodayDate.plusDays(2).getDayOfMonth();
		System.out.println(expectedDepatureDate);
		System.out.println(expectedReturnDate);
		// Actual
		WebElement DivReturnDate = driver.findElement(By.cssSelector(".sc-iHhHRJ.sc-OxbzP.edzUwL"));
		String ActualReturnDate = DivReturnDate.findElement(By.cssSelector(".sc-cPuPxo.LiroG")).getText();
		WebElement DivDepatureDate = driver.findElement(By.cssSelector(".sc-iHhHRJ.sc-kqlzXE.blwiEW"));
		String ActualDepatureDate = DivDepatureDate.findElement(By.cssSelector(".sc-cPuPxo.LiroG")).getText();

		int ActualDepatureDateInt = Integer.parseInt(ActualDepatureDate);
		int ActualReturnDateInt = Integer.parseInt(ActualReturnDate);

		assertEquals(ActualDepatureDateInt, expectedDepatureDate);
		assertEquals(ActualReturnDateInt, expectedReturnDate);

	}

	@Test(priority = 7)
	public void CheckRandomMethodToChangeLanguage() {
		Random rand = new Random();
		int randomIndForWebsites = rand.nextInt(Websites.length);
		driver.get(Websites[randomIndForWebsites]);

		if (driver.getCurrentUrl().contains("ar")) {
			String ExpectedLang = "ar";
			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			assertEquals(ActualLang, ExpectedLang);

		} else {
			String ExpectedLang = "en";
			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			assertEquals(ActualLang, ExpectedLang);
		}
	}

	@Test(priority = 8)
	public void clickOnHotelTab() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		if (driver.getCurrentUrl().contains("ar")) {
			WebElement HotelInput = driver.findElement(By.cssSelector("input[placeholder='البحث عن فنادق أو وجهات']"));
			HotelInput.sendKeys(CitiesInArb[randArbCity]);
			// HotelInput.sendKeys(CitiesInArb[randArbCity]+Keys.ARROW_DOWN+Keys.ENTER);

		} else {
			WebElement HotelInput = driver
					.findElement(By.cssSelector("input[placeholder='Search for hotels or places']"));
			HotelInput.sendKeys(CitiesInEng[randEngCity]);
			// HotelInput.sendKeys(CitiesInEng[randEngCity]+Keys.ARROW_DOWN+Keys.ENTER);
			// VariableName.sendKeys(Keys.chord(Keys.ENTER));
		}
		WebElement theList = driver.findElement(By.className("UzzIN"));
		theList.findElements(By.tagName("li")).get(1).click();

	}

	@Test(priority = 9)
	public void randomlySelectTheVistorNumber() {
		WebElement visitors = driver.findElement(By.tagName("select"));
		Select selector = new Select(visitors);

		if (driver.getCurrentUrl().contains("ar")) {
			selector.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");
		} else {
			selector.selectByVisibleText("1 Room, 1 Adult, 0 Children");
		}
		WebElement searchBtn = driver.findElement(By.className("js-HotelSearchBox__SearchButton"));
		searchBtn.click();
	}

	@Test(priority = 10)
	public void makeSurePageIsFullyLoaded() throws InterruptedException {
		Thread.sleep(50000);
		String LoadedResulte = driver.findElement(By.cssSelector(".sc-kAKrxA.klWOBA")).getText();

		if (driver.getCurrentUrl().contains("ar")) {
			boolean Actualresulte = LoadedResulte.contains("وجدنا");
			assertEquals(Actualresulte, true);
		} else {
			boolean Actualresulte = LoadedResulte.contains("found");
			assertEquals(Actualresulte, true);
		}

	}

	@Test(priority = 11)
	public void sortItemsBasedOnPrice() {
		if (driver.getCurrentUrl().contains("en")) {
			WebElement LowestPriceBtn = driver.findElement(By.cssSelector(".sc-csuNZv.hcjHpm"));
			LowestPriceBtn.click();
		} else {
			WebElement LowestPriceBtn = driver.findElement(By.cssSelector(".sc-csuNZv.jyUtIz"));
			LowestPriceBtn.click();
		}
		// int x=driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9")).findElements(By.className("Price__Value")).size();
		// System.out.println(x);
		WebElement HotelContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> prices = HotelContainer.findElements(By.className("Price__Value"));
		System.out.println(prices.size());
		System.out.println(prices.get(0).getText());
		System.out.println(prices.get(prices.size()-1).getText());
		String LowestPrice = prices.get(0).getText();
		int LowestPriceInt = Integer.parseInt(LowestPrice);
		String HighestPrice = prices.get(prices.size()-1).getText();
		int HighestPriceInt = Integer.parseInt(HighestPrice);
		assertEquals(LowestPriceInt<HighestPriceInt , true);
		

	}

	@AfterTest
	public void postTesting() {
	}

}
