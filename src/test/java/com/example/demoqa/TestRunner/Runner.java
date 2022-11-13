package com.example.demoqa.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/example/demoqa/Features", glue = "com.example.demoqa.Steps", monochrome = true, plugin = {"html:target/cucumber.html"})
public class Runner extends AbstractTestNGCucumberTests {
}
