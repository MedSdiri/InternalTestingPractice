package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/spartan-cucumber.html",
                "json:target/spartan-cucumber.json",
                "rerun:target/spartan-rerun.txt"

        },
        features = "src/test/resources/features",
        glue = "com/step_definitions",
        dryRun = false,
        tags = "@wip"

)

//Fixing stuff

public class CukesRunner {



}
