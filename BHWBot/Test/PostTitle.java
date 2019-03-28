
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Akif
 */
public class PostTitle {

    public void postTitle(WebDriver driver) {
        List<WebElement> postTitleElements = driver.findElements(By.className("title"));
        System.out.println("postTitleElements.size(): " + postTitleElements.size());
        for (WebElement postTitleElement : postTitleElements) {
            System.out.println("Title: " + postTitleElement.getText());;
        }
//                if(postElement.getClass()){
//                    postElement.click();
//                }
//                else{
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
//        }
//    }

}
