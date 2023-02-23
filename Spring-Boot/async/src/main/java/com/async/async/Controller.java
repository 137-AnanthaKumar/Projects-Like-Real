package com.async.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class Controller {
@Autowired
Service service;

@PostMapping
    public String add(){

    service.addEnti();
         return "done";
    }
}
