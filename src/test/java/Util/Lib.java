package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Lib {

	public static String ReadProperties(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(
				"/Users/Sulman/eclipse-workspace/BankOfAmerica/src/test/resources/Prop/Config.properties");
		prop.load(fs);
		String data = prop.getProperty(key);
		return data;

	}

	public static void takeascreenshot(WebDriver driver) throws Exception {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentdir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(scrFile, new File(currentdir + "/ScreenShot/" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
