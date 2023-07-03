package com.maney.api.models.request;

public class AuthenticationRequest {

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        public String email;

        public String password;

        public void withEmail(String email){
            this.email = email;
        }

        public void withPassword(String password){
            this.password = password;
        }

        public AuthenticationRequest build() {
            return new AuthenticationRequest(email, password);
        }

    }


}
