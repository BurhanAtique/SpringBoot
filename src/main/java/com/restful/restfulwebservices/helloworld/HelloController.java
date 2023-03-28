package com.restful.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloController {

    @Autowired
    MessageSource messageSource;


//    @RequestMapping(method= RequestMethod.GET, path="/hello-world")
    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "Hello World new";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean hellogg(){
        return new HelloWorldBean("Bean here");
    }

    @GetMapping(path="/hello-world/path-variable/{nameee}")
    public HelloWorldBean helloWorldPath(@PathVariable String nameee){
        return new HelloWorldBean(nameee);
    }

    @GetMapping(path="/international-greeting") // here this Accept Language could be any name
    public String greeting(@RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning",null,locale);
    }
}
