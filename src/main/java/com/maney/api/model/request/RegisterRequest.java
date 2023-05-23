package com.maney.api.model.request;

public class RegisterRequest {

    public RegisterRequest(String firstname,String email, String password) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    public RegisterRequest() {
    }

    private String firstname;

    private String email;

    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

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

        public String firstname;

        public String email;

        public String password;

        public void withFirstname(String firstname){
            this.firstname = firstname;
        }

        public void withEmail(String email){
            this.email = email;
        }

        public void withPassword(String password){
            this.password = password;
        }

        public RegisterRequest build() {
            return new RegisterRequest(firstname, email, password);
        }

    }
}
