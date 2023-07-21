package com.geekster.InstagramProject.controller;


import com.geekster.InstagramProject.dto.SignInInput;
import com.geekster.InstagramProject.dto.SignInOutput;
import com.geekster.InstagramProject.dto.SignUpOutput;
import com.geekster.InstagramProject.model.Admin;
import com.geekster.InstagramProject.service.AdminService;
import com.geekster.InstagramProject.service.AdminTokenService;
import com.geekster.InstagramProject.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminTokenService authService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@Valid @RequestBody Admin signUpDto)
    {
        return adminService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@Valid @RequestBody SignInInput signInDto)
    {
        return adminService.signIn(signInDto);
    }

    @PutMapping("/user/{id}/{blueTick}")
    public String toggleBlueTick(@RequestParam String email , @RequestParam String token ,@PathVariable Long id,@PathVariable boolean blueTick)
    {
        if(authService.authenticate(email,token))
        {
            return adminService.toggleBlueTick(id,blueTick);

        }
       return "Invalid Admin";
    }

}
