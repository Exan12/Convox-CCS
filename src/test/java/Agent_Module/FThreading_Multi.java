package Agent_Module;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FThreading_Multi {
	
	public static void main(String[] args) {
		
		String csvFilePath = ".\\src\\test\\resources\\agents and station2_4Servers.csv";

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath));
			String line;
			
			// Number of threads
			ExecutorService executor = Executors.newFixedThreadPool(5); 

			while ((line = csvReader.readLine()) != null) {
				String[] data = line.split(",");

				// Agent is in the first column
				String agent = data[0]; 
				
				// Assuming station is in the second column
				String station = data[1]; 
					
				Runnable worker = new WorkerThread(agent, station);
				executor.execute(worker);
				
			}

			csvReader.close();
			executor.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class WorkerThread implements Runnable {
	private String agent;
	private String station;

	public WorkerThread(String agent, String station) {
		this.agent = agent;
		this.station = station;
	}

	@Override
	public void run() {

		// Login to the Multiple Agents with Multiple stations
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions fo = new FirefoxOptions();
		fo.setHeadless(false);
		WebDriver driver = new FirefoxDriver(fo);

		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20L));
			driver.get("http://103.167.216.3:8886/ConVoxCCS/index.php");

			// Login to agent panel with Multiple Stations
			WebElement clickOnAgent = driver.findElement(By.id("agent"));
			clickOnAgent.click();
			WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
			username.sendKeys(agent);
			WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
			password.sendKeys("1234");
			WebElement Station = driver.findElement(By.xpath("//input[@id='station']"));
			Station.sendKeys(station);
			WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
			loginButton.click();

		} finally {
			// driver.quit();
		}
	}
}
