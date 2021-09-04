import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumTodo {

    private AppiumDriver driver;

    @BeforeEach
    public void before() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","davinci");
        capabilities.setCapability("platformVersion","11");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName","Android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after(){
        driver.quit();
    }

    @Test
    public void verifyTheCreateTodoApp() throws InterruptedException{
        Thread.sleep(2000);
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("Lista de compras");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("15 latas de cerveza Pilsen");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/note_item_reminder_date1")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/due_date")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc='10 setiembre 2021']")).click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/time")).click();
        driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc='3']")).click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/repeat")).click();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Diariamente']")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
        String expectResult = "Lista de compras";
        String actualResult = driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();
        Assertions.assertEquals(expectResult, actualResult,"Error Contacto no creado");
    }
}
