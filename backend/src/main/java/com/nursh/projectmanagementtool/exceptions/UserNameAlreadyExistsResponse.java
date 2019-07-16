package com.nursh.projectmanagementtool.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameAlreadyExistsResponse {

    private String username;


}
