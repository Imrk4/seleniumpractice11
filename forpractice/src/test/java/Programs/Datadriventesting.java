package Programs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Datadriventesting {
	
	WebDriver driver;
	
	@BeforeTest
	public void Bt()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/index.php");	
	}

	@Test
	public void Ecxelsheet() throws InvalidFormatException, IOException, InterruptedException
	{
		//we have to create excel file 
		//1--path of the file store it in a variable
		File path = new File("C:\\Users\\USER\\Desktop\\testtdataa.xlsx");
		//2--fetching file from that path
		FileInputStream file = new FileInputStream(path);
		//3-- from that path xlxs saved file or that book is stored in book
		XSSFWorkbook book = new XSSFWorkbook(path);
		//4-- we are fetching sheet
		XSSFSheet sheet = book.getSheet("Guru99login");
		//5-- counting how many rows are there
		int rows = sheet.getLastRowNum();
		
		 for (int i=0; i<=rows; i++)
		 {
			 // to store the first cell value as username and second value as password
			 //
			String name= sheet.getRow(i).getCell(0).getStringCellValue();
			 String password =  sheet.getRow(i).getCell(1).getStringCellValue();
			 
			 driver.findElement(By.name("userName")).sendKeys(name);
			 driver.findElement(By.name("password")).sendKeys(password);
			 driver.findElement(By.name("submit")).click(); 
			 Thread.sleep(4000);
				driver.findElement(By.partialLinkText("SIGN-OFF")).click();
				Thread.sleep(4000);
				
				//driver.close();			 
		 }
		
		
		
		
		
		
	}
}
