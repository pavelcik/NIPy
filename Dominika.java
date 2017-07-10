package CzytanieExcela;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



	

//http://www.finanse.mf.gov.pl/web/wp/pp/sprawdzanie-statusu-podmiotu-w-vat

public class Dominika implements CzytanieExcela {

	
		HSSFRow row;
    		HSSFCell cell;
		int value1;
		static ArrayList<String> nipy = new ArrayList<String>();				
		int i;	
		DesiredCapabilities ieCaps;
	
	   	 static File plikznipami;
	    	LocalDate data = LocalDate.now();
	    
	
	    
		private void init() {
			
		
		
			
			
			
		}
	 
		
		

		private void makeAnArray() throws IOException {
			PrintStream test = new PrintStream(new FileOutputStream(System.getenv("ProgramFiles(X86)")+"/NIPy/testowanie.txt"));
			System.setOut(test);
			System.setErr(test);
			FileReader reader = new FileReader(System.getenv("ProgramFiles(X86)")+"/NIPy/output.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String value = null;
			while((value = bufferedReader.readLine()) !=null) {
				nipy.add(value);
				
			}
			
			
			bufferedReader.close();
			
			System.out.println(nipy);
			
		
			
			
		}
		
		
	
		
		private void check() throws InterruptedException, IOException {
			
			
	
	
	WebDriver driver;
	System.setProperty("webdriver.chrome.driver",System.getenv("ProgramFiles(X86)")+"/NIPy/chromedriver.exe");

	
	ChromeOptions o = new ChromeOptions();
	o.addArguments("disable-extensions");
	o.addArguments("--start-maximized");
	
	driver = new ChromeDriver(o);
	driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
	
	
			
			driver.get("http://www.finanse.mf.gov.pl/web/wp/pp/sprawdzanie-statusu-podmiotu-w-vat");
			
			

			
			
			
		 
		Thread.sleep(4000);
			for(i=0;i<nipy.size();i++) {
				if(nipy.get(i)!=null) {
					/*
					WebDriverWait pole1 = new WebDriverWait(driver,50);
					pole1.until(ExpectedConditions.visibilityOfElementLocated(By.id("b-7")));*/
				Thread.sleep(3000);
			WebElement pole = driver.findElement(By.id("b-7"));
		
			pole.sendKeys(nipy.get(i));
			Thread.sleep(2500);
			WebDriverWait przycisk = new WebDriverWait(driver,50);
			przycisk.until(ExpectedConditions.elementToBeClickable(By.id("b-8"))).click();
			
		
			
			
			
			Thread.sleep(1000);
			 
			String sprawdzenie = driver.findElement(By.id("caption2_b-3")).getText();
			
			System.out.println(sprawdzenie);
			if(sprawdzenie.contains("nie jest"))
					sprawdzenie="NIEZAREJESTROWANY";
			else
				sprawdzenie="ZAREJESTROWANY";
			
			FileInputStream fsIP = new FileInputStream(plikznipami);
			HSSFWorkbook wb = new HSSFWorkbook(fsIP);
			HSSFSheet worksheet = wb.getSheetAt(0);
			
			cell = worksheet.getRow(i+1).getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			
			
			cell.setCellValue(sprawdzenie+" na dzieñ "+data);
			fsIP.close();
			FileOutputStream output_file = new FileOutputStream(plikznipami);
			wb.write(output_file);
			output_file.close();
			wb.close();

			 Thread.sleep(4000);
			
			WebElement wyczysc = driver.findElement(By.id("b-9"));
			
			
				
			Thread.sleep(2000);
		wyczysc.click();
		
		System.out.println(nipy.get(i));
				}
				else break;
			
		 }
			driver.close();
		}
		
		
	 
		public static void main(String[] args) throws IOException, InterruptedException {
			
			
			DominikaGUI gui = new DominikaGUI();
	 
			Dominika app = new Dominika();
			
			app.czytajExcela();
			
			app.makeAnArray();		
			
	 
			app.init();
			
			
			app.check();
			
			gui.displayInfo("Sprawdzenie zakoñczy³o siê.Plik zaktualizowany", "Koniec");
			
			gui.closeWindow();
			
			
		
	 
		
		}
	 
	}
	 


