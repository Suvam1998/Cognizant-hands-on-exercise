package com.digitalnurture.order;

/** Minimal view of a User as returned by the User Service. */
public record UserDto(Long id, String name, String email) {
}
