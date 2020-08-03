package com.tekcamp.spring.controller;

import com.tekcamp.spring.model.request.UserRequest;
import org.junit.jupiter.api.Test;

public class UserControllerTest {

    @Test
    public void testCreateUser() {
        UserRequest testUserRequest = new UserRequest("george", "torres", "torresgeorge2014@gmail.com", "gumdrops");

//        UserResponse actual = new UserController();
//        assertEquals(actual, expected);
    }

}
