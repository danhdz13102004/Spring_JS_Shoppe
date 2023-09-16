package com.example.my_shoppe.web;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
public class RegisterUser {
    @NotBlank(message = "Required Information!")
    @Size(min = 8,message = "Độ dài tối thiểu là 8")
    private String username;
    @NotBlank(message = "Required Information!")
    @Size(min = 8,message = "Độ dài tối thiểu là 8")
    private String password;
    @NotBlank(message = "Required Information!")
    private String firstname;
    @NotBlank(message = "Required Information!")
    private String lastname;
    @NotBlank(message = "Required Information!")
    @Email(message = "Email không hợp lệ!")
    private String email;

    public RegisterUser() {
    }

    public RegisterUser(String username, String password, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
