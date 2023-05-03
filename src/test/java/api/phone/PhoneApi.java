package api.phone;

import api.ApiBase;
import io.restassured.response.Response;
import shemas.phone.PhoneDto;

public class PhoneApi extends ApiBase {
    Response response;

    PhoneDto dto;

    public PhoneDto randomDataBodyForCreatePhone() {
        dto = new PhoneDto();
        dto.setCountryCode("+49");
        dto.setPhoneNumber("0661110035");
        dto.setContactId(4996);
        return dto;
    }


    public PhoneDto randomDataBodyForEditPhone(Integer phoneId) {
        dto = new PhoneDto();
        dto.setId(phoneId);
        dto.setCountryCode("+49");
        dto.setPhoneNumber("0661110035");
        return dto;
    }

    public void createPhone(Integer code) {
        String endpoint = "/api/phone";
        postRequest(endpoint, code, randomDataBodyForCreatePhone());
    }

    public void editExistingPhone(Integer code, Integer phoneId) {
        String endpoint = "/api/phone";
        putRequest(endpoint, code, randomDataBodyForEditPhone(phoneId));
    }

    public void deleteExistingPhone(Integer code, int phoneId) {
        String endpoint = "/api/phone/{id}";
        deleteRequest(endpoint, code, phoneId);
    }

    public Response getAllPhones(Integer code) {
        String endpoint = "/api/phone/{contactId}/all";
        response = getRequestWithParam(endpoint, code, "contactId", 4996);
        return response;
    }

    public Response getContactPhone(Integer code, int phoneId) {
        String endpoint = "/api/phone/{id}";
        response = getRequestWithParam(endpoint, code, "id", phoneId);
        return response;
    }
}
