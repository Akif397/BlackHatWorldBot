/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linereflection.core;

import com.linereflection.model.Post;
import java.util.LinkedList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Akif
 */
public class ProcessEngine {
    
    public List<Post> getAllPost(WebDriver driver){
        List<Post> postList = new LinkedList<Post>();
        
        List<WebElement> postWebElements = getViewPagedPostList(driver);
        for(WebElement element : postWebElements){
            Post post =  getPost(element, driver);
            postList.add(post);
        }
        
        
        return postList;
    }
    
    private List<WebElement> getViewPagedPostList(WebDriver driver){
        List<WebElement> postElements = null;
        
        return postElements;
        
    }

    private Post getPost(WebElement element, WebDriver driver) {
        Post  post = new Post();
        
        return post;
    }
    
    
    
}
