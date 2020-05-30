package ru.site;

import org.junit.After;
import org.junit.Before;
import ru.site.Init;

public class BaseTest {

    @Before
    public void startUp(){
        Init.initWebdriver();
    }

    @After
    public void afterUp(){
        Init.getDriver().close();
    }
}
