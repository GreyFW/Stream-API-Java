package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sms {
    private String message;
    private  String phoneNumber;
}
