package com.nandan.modernlibraryusingfirebase;

public class UserHelperClass {

     String fullName, rollNo, studentClass, email, password;
     public UserHelperClass(){}

     public String getFullName() {
          return fullName;
     }

     public void setFullName(String fullName) {
          this.fullName = fullName;
     }

     public String getRollNo() {
          return rollNo;
     }

     public void setRollNo(String rollNo) {
          this.rollNo = rollNo;
     }

     public String getStudentClass() {
          return studentClass;
     }

     public void setStudentClass(String studentClass) {
          this.studentClass = studentClass;
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

     public UserHelperClass(String fullName, String rollNo, String studentClass, String email, String password) {
          this.fullName = fullName;
          this.rollNo = rollNo;
          this.studentClass = studentClass;
          this.email = email;
          this.password = password;

     }

}