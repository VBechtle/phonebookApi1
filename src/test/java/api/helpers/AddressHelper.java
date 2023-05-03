package api.helpers;

import api.address.AddressesApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class AddressHelper extends AddressesApi {

    public void deleteAddress(Integer addressId) {
        deleteExistingAddress(200, addressId);
        Response errorMessage = getAddress(500, addressId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This address doesn't exist in our DB");
    }
}
