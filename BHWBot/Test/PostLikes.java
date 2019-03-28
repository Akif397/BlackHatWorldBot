
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
public class PostLikes {
    public void postLike(WebDriver driver) {
        List<WebElement> postLikeElements = driver.findElements(By.className("dark_postrating_thread_rating"));
        System.out.println("postlikeElements.size(): " + postLikeElements.size());
        for (WebElement postLikeElement : postLikeElements) {
            System.out.println("Likes: " + postLikeElement.getText());;
        }
//                if(postElement.getClass()){
//                    postElement.click();
//                }
//                else{
//                    System.out.println("Cannot click to the login button");
//                }
    }
    
}
