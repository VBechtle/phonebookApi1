package api.tests;

import api.phone.PhoneApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonPhoneTest extends PhoneApi {

    @Test
    public void createEditDeletePhoneTest() {
        createPhone(201);
        Response response = getAllPhones(200);
        int phoneId = response.jsonPath().getInt("[0].id");
        String phone = response.jsonPath().getString("[0].phoneNumber");
        String countryCode = response.jsonPath().getString("[0].countryCode");
        Assert.assertEquals(phone, randomDataBodyForCreatePhone().getPhoneNumber(), "Created phone is not equals");
        Assert.assertEquals(countryCode, randomDataBodyForCreatePhone().getCountryCode(), "Created countryCode is not equals");

        editExistingPhone(200, phoneId);
        Response editedPhones = getAllPhones(200);
        String editedPhone = editedPhones.jsonPath().getString("[0].phoneNumber");
        String editedCountryCode = response.jsonPath().getString("[0].countryCode");
        Assert.assertEquals(editedPhone, randomDataBodyForEditPhone(phoneId).getPhoneNumber(), "Edited");
        Assert.assertEquals(editedCountryCode, randomDataBodyForEditPhone(phoneId).getCountryCode(), "Edited");
        deleteExistingPhone(200, phoneId);
        Response errorMessage = getContactPhone(500, phoneId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");

    }
}
