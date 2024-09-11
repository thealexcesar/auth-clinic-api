package com.authclinic.application.user.dtos;

import com.authclinic.domain.user.RoleType;

public record UserResponseDTO(Long id, String username, RoleType role) {}
