package com.example.demo.Utils.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody<T> {
    private String message;
    private boolean success;
    private HttpStatus httpStatus;
    private T data;
}
