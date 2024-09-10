package com.authclinic.application.dtos;

import com.authclinic.domain.user.RoleType;

public record UserResponseDTO(Long id, String username, RoleType role) {}
