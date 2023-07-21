package com.geekster.InstagramProject.service;


import com.geekster.InstagramProject.dto.SignInInput;
import com.geekster.InstagramProject.dto.SignInOutput;
import com.geekster.InstagramProject.dto.SignUpOutput;
import com.geekster.InstagramProject.model.Admin;
import com.geekster.InstagramProject.model.AdminAuthenticationToken;
import com.geekster.InstagramProject.model.AuthenticationToken;
import com.geekster.InstagramProject.repo.IAdminRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {

    @Autowired
    UserService userService;

    @Autowired
    IAdminRepo adminRepository;

    @Autowired
    AdminTokenService adminTokenService;


    public String toggleBlueTick(Long id, boolean blueTick) {
        return userService.toggleBlueTick(id,blueTick);

    }


    public SignUpOutput signUp(Admin signUpDto) {

        //check if user exists or not based on email
        Admin admin = adminRepository.findFirstByEmail(signUpDto.getEmail());

        if(admin != null)
        {
            throw new IllegalStateException("Admin already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        signUpDto.setPassword(encryptedPassword);
        adminRepository.save(signUpDto);

        return new SignUpOutput("Admin registered","Instagram account created successfully");

    }

    private String encryptPassword(String adminPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(adminPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

    public SignInOutput signIn(SignInInput signInDto) {
        //check if user exists or not based on email
        Admin admin = adminRepository.findFirstByEmail(signInDto.getEmail());

        if(admin == null)
        {
            throw new IllegalStateException("Admin invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(admin.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("Admin invalid!!!!...sign up instead");
        }

        AdminAuthenticationToken token = new AdminAuthenticationToken(admin);

        adminTokenService.saveToken(token);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!", token.getToken());

    }

}
