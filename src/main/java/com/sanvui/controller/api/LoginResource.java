package com.sanvui.controller.api;

import com.nimbusds.jose.JOSEException;
import com.sanvui.service.imp.UserDetailServiceImpl;
import com.sanvui.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 14/12/2021-12:42 AM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/login")
public class LoginResource {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> submitLoginApi(
            @RequestParam("username") String username
            , @RequestParam("password") String password) throws ParseException, JOSEException {

        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        if (Objects.nonNull(userDetails)
                && passwordEncoder.matches(password, userDetails.getPassword())) {
            // Generate token
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("userName", userDetails.getUsername());

            List<String> role = userDetails.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            objectMap.put("role", role);

            String token = JwtUtil.encode(objectMap);

            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
