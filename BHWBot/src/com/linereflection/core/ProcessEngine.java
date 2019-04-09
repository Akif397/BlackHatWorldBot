package com.linereflection.core;

import com.linereflection.db.DBModel.PostDetails;
import java.util.LinkedList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Akif
 */
public class ProcessEngine {
    
    public List<PostDetails> getAllPost(WebDriver driver){
        List<PostDetails> postList = new LinkedList<PostDetails>();
        
        List<WebElement> postWebElements = getViewPagedPostList(driver);
        for(WebElement element : postWebElements){
            PostDetails post =  getPost(element, driver);
            postList.add(post);
        }
        
        
        return postList;
    }
    
    private List<WebElement> getViewPagedPostList(WebDriver driver){
        List<WebElement> postElements = null;
        
        return postElements;
        
    }

    private PostDetails getPost(WebElement element, WebDriver driver) {
        PostDetails  post = new PostDetails();
        
        return post;
    }
    
    
    
}
