package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddEmployee extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
       WebElement pim= driver.findElement(By.id("menu_pim_viewPimModule"));
        doClick(pim);

    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
     WebElement pimTab= driver.findElement(By.id("menu_pim_addEmployee"));
             doClick(pimTab);

    }
    @When("use enters firstname and middlename and lastname")
    public void use_enters_firstname_and_middlename_and_lastname() {
        WebElement firstName=driver.findElement(By.id("firstName"));
        sendText(firstName, ConfigReader.getPropertyValue("firstName"));
        WebElement middleName=driver.findElement(By.id("middleName"));
        sendText(middleName,ConfigReader.getPropertyValue("middleName"));
        WebElement lastName=driver.findElement(By.id("lastName"));
        sendText(lastName,ConfigReader.getPropertyValue("lastName"));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
       WebElement saveBtn= driver.findElement(By.id("btnSave"));
       doClick(saveBtn);

    }

}
