package com.uncledavecode.orders.model.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponses {
    private String[] errorMessages;

    public boolean hasErrors() {
        return errorMessages != null && errorMessages.length > 0;
    }
}
