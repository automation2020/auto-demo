package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverFacade {
    private WebDriver driver;

    public void search(WebElement barraBusqueda, WebElement boton, String texto){
        barraBusqueda.sendKeys(texto);
        boton.click();

    }

    public WebElement buscarElemento(By estrategiaDeBusqueda){
        return driver.findElement(estrategiaDeBusqueda);
    }

}
