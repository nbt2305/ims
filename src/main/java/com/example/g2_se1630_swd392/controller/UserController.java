package com.example.g2_se1630_swd392.controller;

import com.example.g2_se1630_swd392.dto.sms.SmsRequest;
import com.example.g2_se1630_swd392.dto.user.request.*;
import com.example.g2_se1630_swd392.common.BaseResponse;
import com.example.g2_se1630_swd392.dto.user.response.UserResponse;
import com.example.g2_se1630_swd392.service.SmsService;
import com.example.g2_se1630_swd392.service.UserService;

import javax.mail.MessagingException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class UserController {
    private final UserService userService;
    private final SmsService smsService;

    @PostMapping("/register")
    public BaseResponse<?> register(@RequestBody RegisterUserRequest request) {
        return BaseResponse.ok(userService.registerUser(request));
    }

    @PostMapping("/create")
    public BaseResponse<?> create(@RequestBody CreateUserRequest request) {
        return BaseResponse.ok(userService.create(request));
    }

    @PutMapping("/update/{id}")
    public BaseResponse<?> update(@PathVariable("id") Integer id, @RequestBody UpdateUserRequest request) {
        return BaseResponse.ok(userService.update(id, request));
    }

    @PutMapping("/forgot-password/change-password")
    public BaseResponse<?> changePassOfForgot(@RequestBody ChangePasswordOfForgotPassRequest request) {
        return BaseResponse.ok(userService.changePassOfForgotPass(request));
    }

    @GetMapping("/get-detail/{id}")
    public BaseResponse<?> getDetail(@PathVariable("id") Integer id) {
        return BaseResponse.ok(userService.getDetail(id));
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Get List User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserResponse.class),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    public BaseResponse<?> getAll() {
        return BaseResponse.ok(userService.findAll());
    }

    @PostMapping("/send-email-code")
    public BaseResponse<String> sendEmailCode(@RequestBody SendEmailCodeRequest request) throws MessagingException, UnsupportedEncodingException {
        return BaseResponse.ok(userService.sendEmailCode(request));
    }

    @PostMapping("/send-email-code/register")
    public BaseResponse<String> sendEmailCodeForRegister(@RequestBody SendEmailCodeRequest request) throws MessagingException, UnsupportedEncodingException {
        return BaseResponse.ok(userService.sendEmailCodeForRegister(request));
    }

    @PostMapping("/send-sms")
    public BaseResponse<String> sendSms(@RequestBody SmsRequest request) {
        smsService.sendSms(request.getEmail());
        return BaseResponse.ok("Done");
    }

    @PutMapping("/change-active/{id}")
    public BaseResponse<UserResponse> changeActive(@PathVariable("id") Integer id) {
        return BaseResponse.ok(userService.changeActive(id));
    }

    @PutMapping("/change-password/{id}")
    public BaseResponse<?> changePassword(@PathVariable("id") Integer id, @RequestBody ChangePasswordRequest request) {
        return BaseResponse.ok(userService.changePassword(id, request));
    }

}
