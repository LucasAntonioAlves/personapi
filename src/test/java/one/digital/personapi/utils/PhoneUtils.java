package one.digital.personapi.utils;

import one.digital.personapi.dto.request.PhoneDTO;
import one.digital.personapi.entity.Phone;
import one.digital.personapi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "6199999-6666";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
