
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
public class FullPostDetails {

    public void postDetails(WebDriver driver) {
        List<WebElement> postDetailsElements = driver.findElements(By.className("discussionListItems"));
        System.out.println("postDetailsElements.size(): " + postDetailsElements.size());
        for (WebElement postDetailsElement : postDetailsElements) {
            int count = 1;
            List<WebElement> fullPostsDetailsElements = postDetailsElement.findElements(By.tagName("li"));
            System.out.println("fullPostsDetailsElements.size(): " + fullPostsDetailsElements.size());
            for(WebElement fullPostsDetailsElement : fullPostsDetailsElements){
                WebElement title = fullPostsDetailsElement.findElement(By.className("title"));
                String titleString = title.getText();
                System.out.println("Title no " + count + ": " + titleString);
                
//                WebElement likes = fullPostsDetailsElement.findElement(By.)
                count++;
            }
            
//            System.out.println("Likes: " + postDetailsElement.getText());;
        }
//                if(postElement.getClass()){
//                    postElement.click();
//                }
//                else{
//                    System.out.println("Cannot click to the login button");
//                }
    }

}
