package com.PVMS.PassportVisaManagementSystem.Exceptions;

public class ResourceNotFoundException
extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}