package api.tests;

import api.address.AddressesApi;
import api.helpers.AddressHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonAddressTest {

    AddressesApi addressesApi = new AddressesApi();

    AddressHelper addressHelper = new AddressHelper();

    int contactId = 4996;

    @Test
    public void createEditDeleteContactAddressTest() {
        addressesApi.createAddress(201, contactId);
        Response response = addressesApi.getAllAddresses(200, contactId);
        int addressId = response.jsonPath().getInt("[0].id");
        String address = response.jsonPath().getString("[0].address");
        Assert.assertEquals(address, addressesApi.randomDataBodyForCreateAddress(contactId).getCountry(), "Error! This address doesn't exist in our DB");

        addressHelper.deleteAddress(addressId);
    }
}
