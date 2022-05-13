package com.example.slotmachinestart.auth;

import jakarta.ws.rs.NameBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@NameBinding
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface JWTNeeded {
}
