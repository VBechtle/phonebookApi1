package api.tests;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTest extends ContactApi {

    @Test
    public void createEditDeleteNewContact() {

        //Создаем контакт. записываем ответ после создания коетакта
        Response actualResponse = createContact(201);
        //Из ответа вытаскиваем айд
        int contactId = actualResponse.jsonPath().getInt("id");
        //Получаем данные по созданному контакту
        Response expectedResponse = getContact(200, contactId);
        //Проверка! Сравниваем ответ эндпоинта по созд контакта с ответом эндпоинта
        Assert.assertEquals(actualResponse.jsonPath().getString("firstName"), expectedResponse.jsonPath().getString("firstName"), "First name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("lastName"), expectedResponse.jsonPath().getString("lastName"), "Last name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("description"), expectedResponse.jsonPath().getString("description"), "Description contact not equal");

        // Изменяем данные контакта
        editExistingContact(200, contactId);
        // Получаем измененный контакт
        Response actualEditedResponse = getContact(200, contactId);
        // Сравниваем актуальные данные (с помощью гет вытащили)
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description contact not equal");

        deleteExistingContact(200, contactId);

        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
