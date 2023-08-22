package com.herokuapp.booker.restful.cucumber;


import com.herokuapp.booker.restful.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Author - Sarvat Shaikh
 * Project Name: restfulbooker-serenity-cucumber-project
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/")


public class CucumberRunner extends TestBase {
}
