package com.api.user.configs;

public class SecurityConstants {

    public static final String SECRET = "463408a1-54c9-4307-bb1c-6cced559f5a7";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/services/controller/user";
}