package com.example.sahal.InventoryService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    private String details;
    private String message;
    private Date timestamp;
}
