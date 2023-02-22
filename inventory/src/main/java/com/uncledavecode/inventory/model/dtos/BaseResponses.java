package com.uncledavecode.inventory.model.dtos;

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
