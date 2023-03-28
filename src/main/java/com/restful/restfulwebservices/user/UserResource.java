package com.restful.restfulwebservices.user;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;
    @GetMapping(path="/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping(path="/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException("Against this id : "+id);
        }
        //HATEOAS
        //all-users, SERVER_PATH+"/users"
        //instead of hard coding the link as users we'll add the link to
        //retrieveAllUsers() method so that in case in future the path changes i.e users
        // we'll have to change it all places therefore we'll add the link to the method
        //which has the path

        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));



        return resource;
    }

    @PostMapping(path="/users") // this gives response 201 created earlier it always returns 200 status code now it is returning relevant status
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){ // here we'll pass the object that automatically maps to its values to all attributes of theUser
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        User deletedUser = service.deleteById(id);
        if(deletedUser==null){
            throw new UserNotFoundException("masla aa gia");
        }
        return ResponseEntity.noContent().build();
    }

}
