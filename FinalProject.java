package org.examples.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.time.Instant;
import java.util.Random;
import java.util.Set;

public class FinalProject {
    public static void main(String[] args) {



    // Webdriver has been set up
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Launch Flipkart website
    driver.get("https://www.flipkart.com");
    driver.manage().window().maximize();

    // Adding  a delay to have a smooth flow
    // 3 seconds

    // Finding the search tab and typing "Laptops"
    WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
    searchBox.click();
        slowDown(2000);
        searchBox.sendKeys("Laptops");

    // Submiting the search
    searchBox.submit();


    // Scrolling  down to load more products
    js.executeScript("window.scrollBy(0, 1000);");
    slowDown(2000); // 2 seconds

    js.executeScript("window.scrollBy(0, 1000);");
    slowDown(2000); // 2 seconds

    // Waiting for the search results to load
    WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='HP Intel Core i5 12th Gen 1235U - (16 GB/512 GB SSD/Windows 11 Home) 15s-fq5330TU Thin and Light Lapto...']")));
    slowDown(2000); // 2 seconds

    // Scrolling down on a loop until the required product is visible
    boolean productFound = false;
    while (!productFound) {
        try {
            productLink = driver.findElement(By.xpath("//div[text()='HP Intel Core i5 12th Gen 1235U - (16 GB/512 GB SSD/Windows 11 Home) 15s-fq5330TU Thin and Light Lapto...']"));
            js.executeScript("arguments[0].scrollIntoView(true);", productLink);
            wait.until(ExpectedConditions.elementToBeClickable(productLink));
            js.executeScript("arguments[0].click();", productLink);
            productFound = true;
            System.out.println("Product clicked.");//verifying if product is clicked
            slowDown(3000); // 3 seconds
        } catch (Exception e) {
            js.executeScript("window.scrollBy(0, 1000);");
            slowDown(1000); // 1 second
        }
    }

    // Switch to the new window
    Set<String> windows = driver.getWindowHandles();
    for (String window : windows) {
        driver.switchTo().window(window);
    }
    slowDown(2000); // 2 seconds

    // Finding the "Add to Cart" button and click it
    WebElement addToCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class, 'QqFHMw') and contains(@class, 'vslbG+') and contains(@class, 'In9uk2')]")));
    js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
    slowDown(2000); // 2 seconds

    addToCartButton.click();
    //verifying if add to cart button is clickable
    System.out.println("Clicked 'Add to Cart' button");
    slowDown(3000); // 3 seconds

    // Close the driver
    driver.quit();
}

public static void slowDown(int milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}




