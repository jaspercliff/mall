package com.jasper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author com
 * 6/20/2023  11:46 AM
 */
@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
}
