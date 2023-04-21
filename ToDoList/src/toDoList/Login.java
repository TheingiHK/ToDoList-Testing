package toDoList;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;


public class Login {
	
	static String Username="Testing-TG";
	static String Password="Amk@560546";

	public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub		
			
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium-Automation\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
				
			String URL="https://todo-list-login.firebaseapp.com/#!/";
			driver.get(URL);
			
			Thread.sleep(1000);
		
			//1. Login using your github account.
			driver.findElement(By.xpath("//a//span [@class='fa fa-github']")).click();
			
			Thread.sleep(3000);
			
			String currentwindow = driver.getWindowHandle();
			
		    Set<String> allWindows = driver.getWindowHandles();
		    Iterator<String> i = allWindows.iterator();
		    while(i.hasNext()){
		         String childwindow = i.next();
					
					  if(!childwindow.equalsIgnoreCase(currentwindow)) {
					  driver.switchTo().window(childwindow);		  		  
					
					  }
					  else {
					  System.out.println("There are no children"); 
					  }			 
		     }	      	       
		      
		    Thread.sleep(2000);
	
		    driver.manage().window().maximize();
		    driver.findElement(By.id("login_field")).sendKeys(Username);
		    driver.findElement(By.id("password")).sendKeys(Password);
		    driver.findElement(By.xpath("//input[@type='submit']")).click();
				  			  		  
		    Thread.sleep(2000);	 
				  
		    driver.switchTo().window(currentwindow);			  
				  
		    //2. create 10 to do list with random strings
		    for(int c=0;c<=9;c++) {
					Thread.sleep(1000);
					String randomName=generateRandom();				  
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(randomName);
					driver.findElement(By.xpath("(//button)[2]")).click();
			}		
				  
			Thread.sleep(1000);
				  
			//3. upon completion log out.
			driver.findElement(By.xpath("(//button)[1]")).click();
				  
			Thread.sleep(1000);
				
			//4. Login again with the same account.
			driver.findElement(By.xpath("//a//span [@class='fa fa-github']")).click();	  
				 		      
			Thread.sleep(3000);	 
			driver.switchTo().window(currentwindow);	
			      
			//5. Delete your list from 5 - 10.		      
			for(int t=0;t<=5;t++) {	
			    	driver.findElement(By.xpath("(//button)[6]")).click();
			    	Thread.sleep(1000);
			}	      	      
			      
			      
			//6. Logout upon completion
			driver.findElement(By.xpath("(//button)[1]")).click();
				
			driver.close();
	}
	
	
	public static String generateRandom() {
			
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		  // create random string builder
	    StringBuilder sb = new StringBuilder();
	
	    // create an object of Random class
	    Random random = new Random();
	
	    // specify length of random string
	    int length = 7;
	
	    for(int i = 0; i < length; i++) {
	      // generate random index number
	      int index = random.nextInt(alphabet.length());
	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);
	      // append the character to string builder
	      sb.append(randomChar);
	    }
	
	    String randomString = sb.toString();
	    System.out.println("Random String is: " + randomString);
		return randomString;
	
	}

}
