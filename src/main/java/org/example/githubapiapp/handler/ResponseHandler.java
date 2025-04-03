package org.example.githubapiapp.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    //Responsible for messages Returns ResponseEntity<Object>(map, status);
    public static ResponseEntity<Object> generateResponse(HttpStatusCode status, String message){
        Map<String,Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", message);
        return new ResponseEntity<Object>(map, status);
    }
}
