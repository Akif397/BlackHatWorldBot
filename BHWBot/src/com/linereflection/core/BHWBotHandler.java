/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linereflection.core;

import com.linereflection.db.DBManager.DBManager;
import com.linereflection.db.DBModel.PostDetails;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Akif
 */
public class BHWBotHandler {

    public PostDetails postDetails = null;

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

    public PostDetails postDetailsInHomePage(WebDriver driver) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BHWBotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        postDetails = new PostDetails();
        List<PostDetails> postDetailsLists = new LinkedList<PostDetails>();
        WebElement postDetailsElement = driver.findElement(By.className("discussionListItems"));
//        System.out.println("postDetailsElements.size(): " + postDetailsElements.size());

        int count = 1;
        List<WebElement> fullPostsDetailsElements = postDetailsElement.findElements(By.tagName("li"));
        System.out.println("fullPostsDetailsElements.size(): " + fullPostsDetailsElements.size());

        for (WebElement fullPostsDetailsElement : fullPostsDetailsElements) {
            try {
                //for tiltle of the post
                WebElement title = fullPostsDetailsElement.findElement(By.className("title"));
                String titleString = title.getText().replaceAll("Please Read:", " ").trim();
                postDetails.setTitle(titleString);
                System.out.println("Title no " + count + ": " + titleString);
            } catch (Exception e) {
                System.out.println("Exception in Post Title");
            }

            try {
                //for the author
                WebElement author = fullPostsDetailsElement.findElement(By.className("username"));
                String authorString = author.getText().trim();
                postDetails.setAuthor(authorString);
                System.out.println("Authorname: " + authorString);
            } catch (Exception e) {
                System.out.println("Exception in Post Authorname");
            }

            try {
                //for like
                WebElement like = fullPostsDetailsElement.findElement(By.tagName("strong"));
                int likeString = Integer.parseInt(like.getText().trim());
                postDetails.setLikes(likeString);
                System.out.println("Like: " + likeString);
            } catch (Exception e) {
                postDetails.setLikes(0);
                System.out.println("Like: 0");
            }

            try {
                //for replies
                WebElement replydiv = fullPostsDetailsElement.findElement(By.className("major"));
                WebElement reply = replydiv.findElement(By.tagName("dd"));
                int replyNumber = Integer.parseInt(reply.getText().replaceAll(",", "").trim());
                postDetails.setReplies(replyNumber);
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
                postDetails.setViews(viewsNumber);
                System.out.println("Views: " + viewsNumber);
            } catch (Exception e) {
                System.out.println("Exception in Post views");
            }

            try {
                //for the link of the title
                WebElement title = fullPostsDetailsElement.findElement(By.className("title"));
                WebElement titleLink = title.findElement(By.tagName("a"));
                String linkOfThePost = titleLink.getAttribute("href");
                postDetails.setUrl(linkOfThePost);
                System.out.println("linkOfThePost: " + linkOfThePost);

                //homepage link
                String URL = "https://www.blackhatworld.com/";

                //for open in a new window
                WebDriver tempDriver = new ChromeDriver();
                tempDriver.get(linkOfThePost);
                postDetailsInAnotherWindow(tempDriver);
                Thread.sleep(2000);
                tempDriver.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception in Post openning in a new window");
            }
            postDetails.setUserEmail(DBManager.getDBManager().getLoggedInUser().getEmail());
            count++;
            DBManager.getDBManager().insertPostDetailsToDatabase(postDetails);
//            try {
//                DBManager.getDBManager().closeConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(BHWBotHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
            System.out.println("postDetailsLists size : " + postDetailsLists.size());
        }
        return postDetails;
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
                String pageDiscusion = pageDiscussion.getText();
                String[] discussionAndTimeString = pageDiscusion.split(",");
                String discussion = discussionAndTimeString[0].trim();
                String time = discussionAndTimeString[1].trim();
                String[] onlyDiscussion = discussion.split("'");
                postDetails.setDiscussion(onlyDiscussion[1]);
                System.out.println(onlyDiscussion[1]);
            } catch (Exception e) {
                System.out.println("No discussion available.");
            }

            try {
                //for date
                WebElement dateTime = postDetailsInAnotherWindowElement.findElement(By.className("DateTime"));
                String dateAndTime = dateTime.getAttribute("title");
                String[] dateAndTimeSpliter = dateAndTime.split("at");
                String date = dateAndTimeSpliter[0].trim();
                String time = dateAndTimeSpliter[1].trim();

                LocalDate dateOnly = dateFormatter(date);
                java.util.Date dateConvertedFromLocalDate = java.sql.Date.valueOf(dateOnly);
                postDetails.setTime(time);
                postDetails.setPostdate(dateConvertedFromLocalDate);
                System.out.println("date: " + dateConvertedFromLocalDate + " and time: " + time);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("date not found");
            }

            try {
                //for tag
                WebElement postTag = postDetailsInAnotherWindowElement.findElement(By.className("tagBlock"));
                String tag = postTag.getText().replaceAll("\n", ",").trim();
                String tagOk = tag.replace("Tags:, ","");
                postDetails.setTags(tagOk);
                System.out.println(tagOk);
            } catch (Exception e) {
                postDetails.setTags("No tag");
                System.out.println("No tags available.");
            }
        }
    }

    public LocalDate dateFormatter(String rawDate) {
        String formatterdate = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(rawDate, formatter);
        return date;
    }
}
