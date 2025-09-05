package com.identifycourses.stepdefinitions;

import com.identifycourses.models.FormData;
import com.identifycourses.pages.EnterprisePage;
import com.identifycourses.utils.ConfigReader;
import com.identifycourses.utils.ExcelUtil;
import io.cucumber.java.en.*;

public class EnterpriseFormSteps {

    EnterprisePage enterprisePage = new EnterprisePage();

    @When("the user navigates to enterprise campus product page")
    public void the_user_navigates_to_enterprise_campus_product_page() {
        enterprisePage.navigateToForEnterprise();
        enterprisePage.navigateToCampusProduct();
    }

    @Then("the user fills the form with test data")
    public void the_user_fills_the_form_with_test_data() {
        ExcelUtil excelUtil = new ExcelUtil(ConfigReader.getTestDataFile(), ConfigReader.getTestDataSheetNameForm());
        Object[][] data = excelUtil.getSheetData();
        for (Object[] row : data) {
            FormData formData = (FormData) row[0];
            enterprisePage.fillForm(formData);
            enterprisePage.clearFormFields();
        }
    }
}

