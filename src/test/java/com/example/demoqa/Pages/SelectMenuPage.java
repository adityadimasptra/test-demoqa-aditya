package com.example.demoqa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

// page_url = https://demoqa.com/select-menu
public class SelectMenuPage {
    WebDriver driver;

    public SelectMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "withOptGroup")
    public WebElement optionSelectValue;

    @FindBy(id = "selectOne")
    public WebElement optionSelectOne;

    @FindBy(id = "oldSelectMenu")
    public WebElement optionSelectOld;

    @FindBy(xpath = "//div[@class=\" css-yk16xz-control\"][.//*[@id='react-select-4-input']]")
    public WebElement multiSelect;

    public void selectValue(String value) {
        optionSelectValue.click();
        driver.findElement(By.xpath("//div[text()='"+value+"']")).click();
    }

    public void selectOne(String value) {
        optionSelectOne.click();
        driver.findElement(By.xpath("//div[text()='"+value+"']")).click();
    }

    public void selectOld(String value) {
        Select select = new Select(optionSelectOld);
        select.selectByVisibleText(value);
    }

    public void selectMultipleSelect(String value) {
        driver.findElement(By.xpath("//div[text()='"+value+"']")).click();
    }
}