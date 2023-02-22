package com.uncledavecode.inventory.model.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {
    private boolean success = true;
    private String errorMessage;
}
