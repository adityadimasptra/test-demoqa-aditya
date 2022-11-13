package com.example.demoqa.Steps;

import com.example.demoqa.Data.DataBooksStore;
import com.example.demoqa.Pages.BooksPage;
import com.example.demoqa.Pages.SelectMenuPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BooksStore {
    WebDriver driver;
    BooksPage booksPage;
    DataBooksStore dataBooksStore;
    SelectMenuPage selectMenuPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        dataBooksStore = new DataBooksStore();
        booksPage = new BooksPage(driver);
        selectMenuPage = new SelectMenuPage(driver);
    }

    @After
    public void after() {
        driver.close();
        driver.quit();
    }

    @Given("User go to {string}")
    public void user_go_to(String url) throws Throwable {
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)", "");

        booksPage = new BooksPage(driver);
        dataBooksStore = new DataBooksStore();
    }
    @When("User in {string} page")
    public void user_in_page (String titleValue) throws Throwable {
        Assert.assertEquals(booksPage.titleBookStore.getText(), titleValue);
    }

    @And("User search book {string}")
    public void user_search_book (String search) throws Throwable {
        booksPage.inputSearchBook(search);
    }

    @And ("User click book {string}")
    public void user_click_book (String item) throws Throwable {
        dataBooksStore.setFieldTitle(booksPage.fieldTitle.getText());
        dataBooksStore.setFieldAuthor(booksPage.fieldAuthor.getText());
        dataBooksStore.setFieldPublisher(booksPage.fieldPublisher.getText());
        booksPage.selectItem(item);
    }

    @And ("User choose select value {string}")
    public void user_choose_select_value (String value) throws Throwable {
        selectMenuPage.selectValue(value);
    }
    @And ("User choose select one {string}")
    public void user_choose_select_one (String one) throws Throwable {
        selectMenuPage.selectOne(one);
    }
    @And ("User choose old style select menu {string}")
    public void user_choose_old_style_select_menu (String old) throws Throwable {
        selectMenuPage.selectOld(old);
    }
    @And ("User choose multi select drop down {string}")
    public void user_choose_multi_select_drop_down (String select) throws Throwable {
        selectMenuPage.multiSelect.click();
        selectMenuPage.selectMultipleSelect("Green");
        selectMenuPage.selectMultipleSelect("Blue");
        selectMenuPage.selectMultipleSelect("Black");
        selectMenuPage.selectMultipleSelect("Red");
    }

    @Then("User success input all select menu")
    public void user_success_input_all_select_menu() {
        selectMenuPage.optionSelectOld.isSelected();
    }

    @Then("User see book {string}")
    public void user_see_book (String item) throws Throwable {
        Assert.assertEquals(booksPage.labelTitle.getText(), item);
        Assert.assertEquals(booksPage.labelAuthor.getText(), dataBooksStore.getFieldAuthor());
        Assert.assertEquals(booksPage.labelPublisher.getText(), dataBooksStore.getFieldPublisher());
    }

    @Then("User see {string}")
    public void user_see_no_rows_found (String message) throws Throwable {
        Assert.assertEquals(booksPage.noRows.getText(), message);
    }
}
