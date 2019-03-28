/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginBHW {

    public void loginButton(WebDriver driver) {
//        List<WebElement> loginElements = new LinkedList<>();
//        loginElements = driver.findElements(By.cssSelector("#login > div > dl:nth-child(2) > dd > ul"));
//        for(int i = 0; i<= loginElements.size(); i++){
//            WebElement loginSelect = loginElements.get(i);
//            String innerHtml = loginSelect.getAttribute(string);
//        }
        System.out.println("Enter to the loginButton().");
        List<WebElement> loginElements = driver.findElements(By.cssSelector("#login > div > dl.ctrlUnit.submitUnit"));
//        List<WebElement> loginElements = driver.findElements(By.tagName("input"));
        System.out.println(loginElements.size());
        for (WebElement logiElement : loginElements) {
            System.out.println(logiElement.getTagName());
            List<WebElement> loginButtonElements = driver.findElements(By.tagName("input"));;
            
            for(WebElement loginButtonElement : loginButtonElements) {
                try {
                    if (loginButtonElement.getTagName().trim().equals("input")) {
                    loginButtonElement.click();
                }
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
//                else {
//                    System.out.println("Cannot click to the login button");
//                }
            }
//            if (logiElement.get) {
//                System.out.println(logiElement.getTagName());
//                logiElement.click();
//            }
//            else{
//                System.out.println("Enter to the else");
//            }
        }

    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.blackhatworld.com/");
        
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        driver.findElement(By.id("loginBarHandle")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginBHW.class.getName()).log(Level.SEVERE, null, ex);
        }
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
        element.sendKeys("akifmuhtasim397@gmail.com");
        driver.findElement(By.name("password")).sendKeys("A1234321");

        LoginBHW loginBHW = new LoginBHW();
        PostTitle post = new PostTitle();
        PostLikes postLikes = new PostLikes();
        FullPostDetails fullPostDetails = new FullPostDetails();
        
        loginBHW.loginButton(driver);
//        fullPostDetails.postDetails(driver);
//        post.postTitle(driver);
//        postLikes.postLike(driver);
//        fullPostDetails.postDetails(driver);

    }
//List<WebElement> options= driver.findElements(By.xpath("//[@id='Countryitems_popup']/div[1]/ul//li/span"));
//            for(WebElement option : options) {
//                if(option.getText().trim().equals("America")) {
//                    option.click();
//                    break;
//                }
//            } 
}
