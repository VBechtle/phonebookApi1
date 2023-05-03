package api.helpers;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class ContactHelper extends ContactApi {

    public int createContact() {
        //Создаем контакт. записываем ответ после создания коетакта
        Response actualResponse = createNewContact(201);
        //Из ответа вытаскиваем айд
        int contactId = actualResponse.jsonPath().getInt("id");
        //Получаем данные по созданному контакту
        Response expectedResponse = getContact(200, contactId);
        //Проверка! Сравниваем ответ эндпоинта по созд контакта с ответом эндпоинта
        Assert.assertEquals(actualResponse.jsonPath().getString("firstName"), expectedResponse.jsonPath().getString("firstName"), "First name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("lastName"), expectedResponse.jsonPath().getString("lastName"), "Last name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("description"), expectedResponse.jsonPath().getString("description"), "Description contact not equal");
        return contactId;
    }

    public void deleteContact(Integer contactId) {
        deleteExistingContact(200, contactId);
        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
