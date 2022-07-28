package com.example.sahal.CustomerService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDto {

    private String details;
    private String message;
    private Date timestamp;
}
