package com.authclinic.application.user.dtos;

import com.authclinic.domain.user.RoleType;

public record UserRegisterDTO(String username, String password, RoleType role) {}
