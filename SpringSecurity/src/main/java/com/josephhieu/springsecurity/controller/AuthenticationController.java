package com.josephhieu.springsecurity.controller;

import com.josephhieu.springsecurity.dto.request.AuthenticationRequest;
import com.josephhieu.springsecurity.dto.request.IntrospectRequest;
import com.josephhieu.springsecurity.dto.response.ApiResponse;
import com.josephhieu.springsecurity.dto.response.AuthenticationResponse;
import com.josephhieu.springsecurity.dto.response.IntrospectResponse;
import com.josephhieu.springsecurity.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;


    @PostMapping("/log-in")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) {

        var result = authenticationService.introspect(request.getToken());
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
