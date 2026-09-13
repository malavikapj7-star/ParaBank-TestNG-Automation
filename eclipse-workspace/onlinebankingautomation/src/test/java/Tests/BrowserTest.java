package Tests;




import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserTest extends BaseTest {

    @Test
    public void openParaBank() {

        System.out.println("Page title: " + driver.getTitle());

        Assert.assertTrue(
                driver.getCurrentUrl().contains("parabank"),
                "ParaBank was not opened"
        );
    }
}