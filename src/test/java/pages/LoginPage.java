package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class LoginPage {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage invalidLogin() {
        loginField.setValue(DataHelper.generateUser().getLogin());
        passwordField.setValue(DataHelper.generateUser().getPassword());
        loginButton.click();
        errorNotificationVisible();
        return new LoginPage();
    }

    public LoginPage getUserWithDifferentPassword() {
        loginField.setValue(DataHelper.getAuthInfoFromTestData().getLogin());
        passwordField.setValue(DataHelper.generateUser().getPassword());
        loginButton.click();
        errorNotificationVisible();
        return new LoginPage();
    }

    public LoginPage threeTimesLoginDifferentPassword() {
        loginField.setValue(DataHelper.getAuthInfoFromTestData().getLogin());
        passwordField.setValue(DataHelper.generateUser().getPassword());
        loginButton.click();
        new LoginPage();
        errorNotificationVisible();
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passwordField.setValue(DataHelper.generateUser().getPassword());
        loginButton.click();
        errorNotificationVisible();
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passwordField.setValue(DataHelper.generateUser().getPassword());
        loginButton.click();
        errorNotificationVisible();
        return new LoginPage();
    }

    public void errorNotificationVisible() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
}

