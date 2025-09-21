package com.delivery.delivery.tracking.domain.model;

import com.delivery.delivery.tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced(){
        Delivery delivery = Delivery.draft();

        delivery.editPrepartionDetails(createValidPrepationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public void shouldNotToPlace(){
        Delivery delivery = Delivery.draft();
        assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());
    }

    private Delivery.PrepartionDetails createValidPrepationDetails() {
        ContactPoint sender = ContactPoint.builder()
                .zipCode("00000-000")
                .street("Rua São Paulo")
                .numer("100")
                .complement("Sala 2")
                .name("João da Silva")
                .phone("(11) 9000-1234")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("12345-111")
                .street("Rua Rio")
                .numer("200")
                .complement("Sala 400")
                .name("Maria Silva")
                .phone("(11) 9100-4321")
                .build();

        return Delivery.PrepartionDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }
}