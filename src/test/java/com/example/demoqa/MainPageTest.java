package com.example.demoqa;
import com.example.demoqa.Data.DataBooksStore;
import com.example.demoqa.Pages.BooksPage;
import com.example.demoqa.Pages.SelectMenuPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainPageTest {
    WebDriver driver;
    SelectMenuPage selectMenuPage;
    BooksPage booksPage;
    DataBooksStore dataBooksStore;

    @BeforeClass
    public void setUpAll() {

    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        selectMenuPage = new SelectMenuPage(driver);
        booksPage = new BooksPage(driver);
        dataBooksStore = new DataBooksStore();
    }

    @AfterMethod
    public void done () {
        driver.close();
        driver.quit();
    }

    @Test
    public void SelectMenu() {
        driver.get("https://demoqa.com/select-menu");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)", "");
        selectMenuPage.selectValue("Another root option");
        selectMenuPage.selectOne("Other");
        selectMenuPage.selectOld("Aqua");
        selectMenuPage.multiSelect.click();
        selectMenuPage.selectMultipleSelect("Green");
        selectMenuPage.selectMultipleSelect("Blue");
        selectMenuPage.selectMultipleSelect("Black");
        selectMenuPage.selectMultipleSelect("Red");
    }

    @Test
    public void BooksNotFound() {
        driver.get("https://demoqa.com/books");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)", "");
        booksPage.inputSearchBook("qa engineer");
        booksPage.noRows.isDisplayed();
    }

    @Test
    public void BooksFound() {
        driver.get("https://demoqa.com/books");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)", "");
        booksPage.inputSearchBook("Git Pocket Guide");

        dataBooksStore.setFieldTitle(booksPage.fieldTitle.getText());
        dataBooksStore.setFieldAuthor(booksPage.fieldAuthor.getText());
        dataBooksStore.setFieldPublisher(booksPage.fieldPublisher.getText());
        booksPage.selectItem(dataBooksStore.getFieldTitle());

        Assert.assertEquals(booksPage.labelTitle.getText(), dataBooksStore.getFieldTitle());
        Assert.assertEquals(booksPage.labelAuthor.getText(), dataBooksStore.getFieldAuthor());
        Assert.assertEquals(booksPage.labelPublisher.getText(), dataBooksStore.getFieldPublisher());
    }
}
