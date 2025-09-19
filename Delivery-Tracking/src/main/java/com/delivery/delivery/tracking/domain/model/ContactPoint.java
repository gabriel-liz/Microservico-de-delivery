package com.delivery.delivery.tracking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ContactPoint {
    private String zipCode;
    private String street;
    private String numer;
    private String complement;
    private String name;
    private String phone;
}
