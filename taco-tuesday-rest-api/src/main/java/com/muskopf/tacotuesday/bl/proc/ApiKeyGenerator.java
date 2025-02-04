package com.muskopf.tacotuesday.bl.proc;

import com.muskopf.tacotuesday.domain.ApiKey;
import com.muskopf.tacotuesday.domain.Employee;

import java.util.UUID;

import static java.util.Objects.isNull;

public class ApiKeyGenerator {
    private static String getRandomKeyString(Employee employee) {
        return UUID.randomUUID().toString();
    }

    public static ApiKey generateForEmployee(Employee employee) {
        String randomKey = getRandomKeyString(employee);

        ApiKey apiKey = new ApiKey();
        apiKey.setEmployee(employee);
        apiKey.setKey(randomKey);

        return apiKey;
    }
}
