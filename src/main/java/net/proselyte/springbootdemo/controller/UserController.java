package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.entity.UserEntity;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserEntity>> getAllUsers()
    {
        List<UserEntity> users = userService.getAll();

        if(users.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            ResponseEntity<List<UserEntity>> result = new ResponseEntity(users, HttpStatus.OK);
            return result;
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Integer id)
    {
        UserEntity userEntity = userService.getById(id);

        if(userEntity == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            ResponseEntity<UserEntity> result = new ResponseEntity(userEntity, HttpStatus.OK);
            return result;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserEntity> createUser(UserEntity userEntity)
    {
        userService.save(userEntity);

        ResponseEntity<UserEntity> result = new ResponseEntity(userEntity, HttpStatus.CREATED);
        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserEntity> deleteUser(@PathVariable("id") Integer id)
    {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}