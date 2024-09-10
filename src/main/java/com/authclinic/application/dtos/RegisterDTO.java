package com.authclinic.application.dtos;

import com.authclinic.domain.user.RoleType;

public record RegisterDTO(String username, String password, RoleType role) {}
