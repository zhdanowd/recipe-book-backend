package com.zhdanov.recipebook.security;

public class Constants {
  public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 3600;
  public static final String SIGNING_KEY = "appKey";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String AUTHORITIES_KEY = "scopes";
}
