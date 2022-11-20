package com.fluffytrio.giftrio.utils.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ValidationTest {

    Validation validation = new Validation();

    @Test
    public void emailValidationTest() {
        String notValidEmail = "email";
        String validEmail = "email@test.com";

        assertThat(validation.isValidEmail(notValidEmail)).isFalse();
        assertThat(validation.isValidEmail(validEmail)).isTrue();
    }
}
