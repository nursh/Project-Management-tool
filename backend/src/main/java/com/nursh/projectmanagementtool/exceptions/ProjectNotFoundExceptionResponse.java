package com.nursh.projectmanagementtool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectNotFoundExceptionResponse {

    private String ProjectNotFound;
}
