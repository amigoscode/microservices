package com.amigoscode.notification;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationServiceTest {

    @Test
    void itShouldPass() {
        // Given
        NotificationService service = new NotificationService(null);
        int input = 1;
        // When
        boolean expected = service.foo(input);
        // Then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldPass2() {
        // Given
        NotificationService service = new NotificationService(null);
        int input = 0;
        // When
        boolean expected = service.foo(input);
        // Then
        assertThat(expected).isTrue();
    }
}
