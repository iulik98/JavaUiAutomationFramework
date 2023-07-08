package com.opencart.pageobjects;

import com.opencart.managers.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminPage {

    private static final Logger logger = LogManager.getLogger(AdminPage.class);

    @FindBy(xpath = "//i[@class='fas fa-user']")
    private WebElement customerTab;
    @FindBy(css = "ul[id='collapse-5'] li:nth-child(1) a")
    private WebElement customerSubTab;

    @FindBy(xpath = "//div[@id='filter-customer']//input[@id='input-email']")
    private WebElement filterEmail;

    @FindBy(id = "button-filter")
    private WebElement filterBtn;

    public AdminPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOnCustomerTab(){
        customerTab.click();
        logger.info("Customer Tab was clicked");
    }

    public void clickOnCustomerSubTab(){
        try {
            customerSubTab.click();
            logger.info("CustomerSubTab was clicked");
        } catch (ElementNotInteractableException e){
            logger.info("This method triggered an exception with this message: " + e.getMessage());
        }

    }
    public void clickOnFilterBtn(){
        filterBtn.click();
    }

    public boolean isUserPresentInTheDB(WebDriver driver, String email) throws InterruptedException {
        clickOnCustomerTab();
        Thread.sleep(3000);
        clickOnCustomerSubTab();
        Thread.sleep(2000);
        filterEmail.sendKeys(email);
        clickOnFilterBtn();
        Thread.sleep(2000);
        List<WebElement> nrOfRecordsFound = driver.findElements(By.xpath("//div[@id='customer']//tbody/tr"));
        return nrOfRecordsFound!=null && nrOfRecordsFound.size() > 0;
    }

}
