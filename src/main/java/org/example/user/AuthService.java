package org.example.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.BaseService;

public class AuthService extends BaseService {
    @Step
    public ValidatableResponse createUser(User user) {
        return spec()
                .body(user)
                .post("/auth/register")
                .then();
    }

    @Step
    public ValidatableResponse deleteUser(String token) {
        return spec()
                .header("Authorization", "Bearer" + token)
                .delete("/auth/user")
                .then();
    }
}