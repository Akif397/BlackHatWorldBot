/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linereflection.core;

import com.sun.corba.se.impl.protocol.giopmsgheaders.ReplyMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Akif
 */
public class BHWBotHandler {

    public void loginBHW(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 3000);
        driver.findElement(By.id("loginBarHandle")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BHWBotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebElement userNameElement = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
        userNameElement.sendKeys("akifmuhtasim397@gmail.com");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BHWBotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordElement.sendKeys("A1234321");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BHWBotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebElement loginButtonElement = driver.findElement(By.className("button"));
        loginButtonElement.click();
    }

    public void postDetailsInHomePage(WebDriver driver, WebDriver tempDriver) {
        List<WebElement> postDetailsElements = driver.findElements(By.className("discussionListItems"));
        System.out.println("postDetailsElements.size(): " + postDetailsElements.size());
        for (WebElement postDetailsElement : postDetailsElements) {
            int count = 1;
            List<WebElement> fullPostsDetailsElements = postDetailsElement.findElements(By.tagName("li"));
            System.out.println("fullPostsDetailsElements.size(): " + fullPostsDetailsElements.size());
            for (WebElement fullPostsDetailsElement : fullPostsDetailsElements) {
                try {
                    //for tiltle of the post
                    WebElement title = fullPostsDetailsElement.findElement(By.className("title"));
                    String titleString = title.getText().replaceAll("Please Read:", " ").trim();
                    System.out.println("Title no " + count + ": " + titleString);
                } catch (Exception e) {
                    System.out.println("Exception in Post Title");
                }

                try {
                    //for the author
                    WebElement author = fullPostsDetailsElement.findElement(By.className("username"));
                    String authorString = author.getText().trim();
                    System.out.println("Authorname: " + authorString);
                } catch (Exception e) {
                    System.out.println("Exception in Post Authorname");
                }

                try {
                    //for date
                    WebElement date = fullPostsDetailsElement.findElement(By.className("startDate"));
                    String dateString = date.getText().replace(',', ' ').trim();
                    System.out.println("Date: " + dateString);
                } catch (Exception e) {
                    System.out.println("Exception in Post Date");
                }

                try {
                    //for like
                    WebElement like = fullPostsDetailsElement.findElement(By.tagName("strong"));
                    int likeString = Integer.parseInt(like.getText().trim());
                    System.out.println("Like: " + likeString);
                } catch (Exception e) {
                    System.out.println("Like: 0");
                }

                try {
                    //for replies
                    WebElement replydiv = fullPostsDetailsElement.findElement(By.className("major"));
                    WebElement reply = replydiv.findElement(By.tagName("dd"));
                    int replyNumber = Integer.parseInt(reply.getText().replaceAll(",", "").trim());
                    System.out.println("Reply: " + replyNumber);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Exception in Post replies");
                }

                try {
                    //for views
                    WebElement viewsdiv = fullPostsDetailsElement.findElement(By.className("minor"));
                    WebElement views = viewsdiv.findElement(By.tagName("dd"));
                    int viewsNumber = Integer.parseInt(views.getText().replaceAll(",", "").trim());
                    System.out.println("Views: " + viewsNumber);
                } catch (Exception e) {
                    System.out.println("Exception in Post views");
                }

                try {
                    //for the link of the title
                    WebElement title = fullPostsDetailsElement.findElement(By.className("title"));
                    WebElement titleLink = title.findElement(By.tagName("a"));
                    String linkOfThePost = titleLink.getAttribute("href");
                    System.out.println("linkOfThePost: " + linkOfThePost);

                    //homepage link
                    String URL = "https://www.blackhatworld.com/";

                    //for open in a new window
                    tempDriver = new ChromeDriver();
                    tempDriver.get(linkOfThePost);
                    postDetailsInAnotherWindow(tempDriver);
                    Thread.sleep(2000);
                    tempDriver.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Exception in Post openning in a new window");
                }
                
                count++;
            }
        }
    }

    public void postDetailsInAnotherWindow(WebDriver tempDriver) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BHWBotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<WebElement> postDetailsInAnotherWindowElements = tempDriver.findElements(By.className("mainContent"));
        System.out.println("postDetailsInAnotherWindowElements.size()" + postDetailsInAnotherWindowElements.size());
        for (WebElement postDetailsInAnotherWindowElement : postDetailsInAnotherWindowElements) {
            try {
                //for discussion
                WebElement pageDiscussion = postDetailsInAnotherWindowElement.findElement(By.id("pageDescription"));
                String pageDescription = pageDiscussion.getText();
                System.out.println(pageDescription);
            } catch (Exception e) {
                System.out.println("No discussion available.");
            }

            try {
                //for tag
                WebElement postTag = postDetailsInAnotherWindowElement.findElement(By.className("tagBlock"));
                String tag = postTag.getText().replaceAll("\n", ", ").trim();
                String tagOk = tag.replace("Tags:, ", "");

                System.out.println(tagOk);
            } catch (Exception e) {
                System.out.println("No tags available.");
            }
        }
    }
}
