package com.example.demoqa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = demoqa.com/books?book=9781449325862
public class BooksPage {
    WebDriver driver;
    public BooksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "searchBox")
    public WebElement searchBook;

    @FindBy(xpath = "//div[@class=\"rt-noData\"]")
    public WebElement noRows;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[2]")
    public WebElement fieldTitle;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[3]")
    public WebElement fieldAuthor;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[4]")
    public WebElement fieldPublisher;

    @FindBy(xpath = "//div[@id=\"title-wrapper\"]/div[2]/label")
    public WebElement labelTitle;

    @FindBy(xpath = "//div[@id=\"author-wrapper\"]/div[2]/label")
    public WebElement labelAuthor;

    @FindBy(xpath = "//div[@id=\"publisher-wrapper\"]/div[2]/label")
    public WebElement labelPublisher;

    @FindBy(xpath = "//div[@class=\"main-header\"]")
    public WebElement titleBookStore;

    public void selectItem (String value) {
        driver.findElement(By.xpath("//a[text()='"+value+"']")).click();
    }

    public void inputSearchBook (String value) {
        searchBook.sendKeys(value);
    }
}