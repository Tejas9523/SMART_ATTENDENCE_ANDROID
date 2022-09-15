package com.example.smartattendence.Models;

public class Company {

    String CompanyName , mail , password ;

    public Company(String CompanyName, String mail, String password) {
        this.CompanyName = CompanyName;
        this.mail = mail;
        this.password = password;
    }

    public Company(){}
    public Company( String mail, String password) {
        this.mail = mail;
        this.password = password;
    }


    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
