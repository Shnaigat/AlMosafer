package alMosafer;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameter {

	
	WebDriver driver = new ChromeDriver() ;
	String URL = "https://global.almosafer.com/en";
		
	String [] Websites = {"https://global.almosafer.com/en","https://global.almosafer.com/ar"};
	
	
	Random randForCity = new Random();
	
	String [] CitiesInEng = {"dubai","jeddah","riyadh"} ;
	int randEngCity = randForCity.nextInt(CitiesInEng.length);
	
	String [] CitiesInArb = {"جدة","دبي"};
	int randArbCity = randForCity.nextInt(CitiesInArb.length);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
