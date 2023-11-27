/*
This scraper used Selenium, however, since Selenium isn't up-to-date with the latest
Chrome update, it can't create a session, thus, I can't scrape from it.
 */

package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;
import java.util.*;


public class WebScraper_Selenium {
    public static void getWebScheduleData(String deptName) {

        // In the second parameter, I put my chromedriver location in my computer
        // because I was using Selenium chromedriver
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Lynn Thike Aung\\.wdm\\drivers\\chromedriver" +
                        "\\win32\\110.0.5481\\chromedriver.exe");

        WebDriver driver = getWebDriverForWS(deptName);

        // Set a timeout for page loading
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Find all rows of tables
        // I used something called find by xpath where I saw the html structure in the webschedule page
        List<WebElement> tablerows = driver.findElements(By.xpath("//tbody/tr"));
        List<String> data = new ArrayList<>();

        // For each row, find the status, CRN, course title, and professor
        // All of these were in the selenium documentation
        for (WebElement tablerow : tablerows) {
            WebElement status = tablerow.findElement(
                    By.className("text-success"));
            WebElement CRN = tablerow.findElement(
                    By.xpath(".//td[@class='hidden-xs'][3]"));
            WebElement courseTitle = tablerow.findElement(
                    By.xpath(".//td[4]"));
            WebElement professor = tablerow.findElement(
                    By.xpath(".//td[@class='hidden-xs'][6]"));
            WebElement classDays = tablerow.findElement(
                    By.xpath(".//td[@class='hidden-xs'][5]"));


            String statusText = status.getText();
            String CRNText = CRN.getText();
            String courseTitleText = courseTitle.getText().split("\n")[0];
            String professorText = professor.getText();
            String classDaysText = classDays.getText();

            String dataItem = "Status: " + statusText + ", CRN: " + CRNText +
                    ", Course Title: " + courseTitleText +
                    ", Professor: " + professorText +
                    ", Days: " + classDaysText;

            data.add(dataItem);
        }

        // Close the WebDriver
        driver.quit();

        // Print the data
        for (String item : data) {
            System.out.println(item);
        }
    }

    private static WebDriver getWebDriverForWS(String deptName) {
        // Set Chrome options
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        // make driver
        WebDriver driver = new ChromeDriver(options);

        String site = "https://webschedule.smccd.edu/list_classes_default_search.php?term_c" +
                "ode=202403&old_term=&college_code%5B%5D=3&college_code%5B%5D" +
                "=4&college_code%5B%5D=2&days%5B%5D=Any&days%5B%5D=Monday&days" +
                "%5B%5D=Tuesday&days%5B%5D=Wednesday&days%5B%5D=Thursday&days%" +
                "5B%5D=Friday&days%5B%5D=Saturday&days%5B%5D=Sunday&units%5B%5" +
                "D=all&units%5B%5D=.5&units%5B%5D=1&units%5B%5D=1.5&units%5B%5" +
                "D=2&units%5B%5D=2.5&units%5B%5D=3&units%5B%5D=3.5&units%5B%5D" +
                "=4&units%5B%5D=4.5&units%5B%5D=5&units%5B%5D=6&meet_begin_tim" +
                "e=6%3A00+AM&meet_end_time=11%3A59+PM&class_type=all&igetc%5B%5" +
                "D=&csu%5B%5D=&department_desc%5B%5D=";

        //Parse the dept name into two if needed to be
        String [] name = parseString(deptName);
        // Append the department names to the site
        StringBuilder result = new StringBuilder(site);
        for (int i=0;i<name.length;i++)
        {
            result.append(name[i]);

            if (i < name.length - 1) {
                result.append("+");
            }
        }
        String finalResult = result.toString();

        String siteEnd = "&keywords=&course_desc_keyword=";

        driver.get(finalResult+siteEnd);
        return driver;
    }

    private static void getRMPData(String professorName) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Lynn Thike Aung\\.wdm\\drivers\\chromedriver" +
                        "\\win32\\110.0.5481\\chromedriver.exe");

        WebDriver driver = getWebDriverForRMP();

        // Set a timeout for page loading
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Close the privacy popup
        WebElement closeButton = driver.findElement(
                By.xpath("button[@class='Buttons__Button-sc-19xdot-1 " +
                        "CCPAModal__StyledCloseButton-sc-10x9kq-2 eAIiLw']"));
        closeButton.click();
        // Make the driver keep pressing the 'Show More' button so that
        // all the professors appear
        String xpath = "button[@class='Buttons__Button-sc-19xdot-1 " +
                "PaginationButton__StyledPaginationButton-txi1dr-1 eUNaBX']";
        WebElement showMoreButton = driver.findElement(
                By.xpath(xpath));
        // Wait 10 seconds
        while (isElementPresent(driver, xpath)) {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
            showMoreButton.click();
        }

        //Find the cards of all professors
        List<WebElement> cards = driver.findElements(
                By.xpath("//a[@class='TeacherCard__Styled" +
                        "TeacherCard-syjs0d-0 dLJIlx']"));

        // Find the card with the professor Name
        WebElement correctCard = null;
        for (WebElement card : cards) {

            WebElement newProfessorName = card.findElement(
                    By.xpath(".//div[@class='CardName__Styled" +
                            "CardName-sc-1gyrgim-0 cJdVEK']"));
            String professorNameText = newProfessorName.getText();
            if (professorName.equals(professorNameText)){
                correctCard = card;
            }
        }
        //Get the ratings and the number of ratings
        assert correctCard != null;
        WebElement ratings = correctCard.findElement(
                By.xpath(".//div[@class='CardNumRating__" +
                        "CardNumRatingNumber-sc-17t4b9u-2 icXUyq']"));
        WebElement numOfRatings = correctCard.findElement(
                By.xpath(".//div[@class='CardNumRating__" +
                        "CardNumRatingCount-sc-17t4b9u-3 jMRwbg']"));

        driver.quit();
        String ratingsText = ratings.getText();
        String numOfRatingsText = numOfRatings.getText();

        System.out.println(professorName + ", " + ratingsText + ", " + numOfRatingsText);
    }

    private static boolean isElementPresent(WebDriver driver, String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private static WebDriver getWebDriverForRMP() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        // make driver
        ChromeDriver driver = new ChromeDriver(options);

        String site = "https://www.ratemyprofessors.com/search/professors/2811?q=*";

        driver.get(site);
        return driver;
    }

    private static String[] parseString(String data) {

        return data.split("\\s+");
    }

}

