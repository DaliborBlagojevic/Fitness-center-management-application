package com.example.SitPass.controller;

import com.example.SitPass.dto.UserDto;
import com.example.SitPass.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @GetMapping("/{email}")
    public  ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }
    @GetMapping()
    public  ResponseEntity<List<UserDto>> getAll() {

        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable String email) {
        return ResponseEntity.ok().body(userService.updateProfile(userDto, email));
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<UserDto> updatePassword(@PathVariable Long id,@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.updatePassword(id, userDto));
    }


}
