package api.address;

import api.ApiBase;
import io.restassured.response.Response;
import shemas.adresses.AddressesDto;

public class AddressesApi extends ApiBase {
    Response response;
    AddressesDto dto;

    public AddressesDto randomDataBodyForCreateAddress(Integer contactId) {
        dto = new AddressesDto();
        dto.setCity("Odessa");
        dto.setCountry("Ukraine");
        dto.setStreet("Deribas");
        dto.setZip("34001");
        dto.setContactId(contactId);
        return dto;
    }

    public AddressesDto randomDataBodyForEditAddress(Integer addressId, Integer contactId) {
        dto = new AddressesDto();
        dto.setId(addressId);
        dto.setCity("Odessa");
        dto.setCountry("Ukraine");
        dto.setStreet("Deribas");
        dto.setZip("34001");
        dto.setContactId(contactId);
        return dto;
    }

    public void createAddress(Integer code, Integer contactId) {
        String endpoint = "/api/address";
        postRequest(endpoint, code, randomDataBodyForCreateAddress(contactId));
    }

    public void editExistingAddress(Integer code, Integer addressId, Integer contactId) {
        String endpoint = "/api/address";
        putRequest(endpoint, code, randomDataBodyForEditAddress(addressId, contactId));
    }

    public void deleteExistingAddress(Integer code, int addressId) {
        String endpoint = "/api/address/{id}";
        deleteRequest(endpoint, code, addressId);
    }

    public Response getAllAddresses(Integer code, Integer addressId) {
        String endpoint = "/api/address/{contactId}/all";
        response = getRequestWithParam(endpoint, code, "addressId", addressId);
        return response;
    }

    public Response getAddress(Integer code, int addressId) {
        String endpoint = "/api/address/{id}";
        response = getRequestWithParam(endpoint, code, "id", addressId);
        return response;
    }
}
