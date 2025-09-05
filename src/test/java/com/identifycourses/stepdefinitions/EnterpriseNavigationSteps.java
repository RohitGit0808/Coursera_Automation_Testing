package com.identifycourses.stepdefinitions;

import com.identifycourses.pages.EnterprisePage;
import io.cucumber.java.en.*;

public class EnterpriseNavigationSteps {

    EnterprisePage enterprisePage = new EnterprisePage();

    @When("the user goes to the enterprise campus product page")
    public void the_user_goes_to_the_enterprise_campus_product_page() {
        enterprisePage.navigateToForEnterprise();
        enterprisePage.navigateToCampusProduct();
    }
} 