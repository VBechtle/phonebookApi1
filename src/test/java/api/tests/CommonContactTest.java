package api.tests;

import api.contact.ContactApi;
import api.helpers.ContactHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTest extends ContactApi {
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEditDeleteNewContact() {
        //Создаем контакт
        Integer contactId = contactHelper.createContact();
        // Изменяем данные контакта
        editExistingContact(200, contactId);
        // Получаем измененный контакт
        Response actualEditedResponse = getContact(200, contactId);
        // Сравниваем актуальные данные (с помощью гет вытащили)
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description contact not equal");

        contactHelper.deleteContact(contactId);
    }
}
