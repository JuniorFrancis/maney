package com.maney.api.model.responses;

public class AuthenticationResponse {

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {

        public String token;

        public void withToken(String token) {
            this.token = token;
        }

        public AuthenticationResponse build(){
            return new AuthenticationResponse(this.token);
        }
    }
}
