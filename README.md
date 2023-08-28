# README

Welcome to the README file of **Subway Ad Management System!** 

This is the demo of this system:
In this demo version, the change password function is disabled(annotated)



## Usage and Licensing

### Ownership

This project is the intellectual property of **Yaohong Xiang**. All rights to the code, design, and associated content are owned by Yaohong Xiang.  

### Commercial Use

**This project is not open for commercial use.** It is provided for educational and non-commercial purposes only. You are not allowed to use this project, or any part of it, for any commercial endeavor or purpose without explicit permission from Yaohong Xiang.

### Open Source Components 

This project may use open-source components and libraries, which are subject to their own respective licenses. Please review and comply with the terms of those licenses if you intend to use or modify those components.

 ### Contributions 

Contributions to this project are welcome. By contributing, you agree to license your contributions under the same terms outlined here. [Include link to CONTRIBUTING.md if applicable] 

### Contact 

For inquiries regarding the use of this project or any questions, please contact xiangyaohong6@gmail.com. 

### Disclaimer 

This README is provided for informational purposes only and does not constitute legal advice. For any legal concerns or clarifications about usage and licensing, please consult with a legal professional.





## System Introduction

### Tools and framwork

This is an **SpringBoot** application. The system use **Thymeleaf** for the most frontend design and combining **REST API** for **AJAX** and certain pages.

For the database I use **MySQL** and the corresponding SQL code are in the "sql" folder.



### Modules

**Spot Management**: CRUD  operations on spot info

**Ad Management**: CRU operation on spot info. Here you can drop an ad(which means it is not longer displayed on subway) but delete operation is not provided

**User&Authority Management**: CRUD operations on user account and admin account can also change the user authority.

**User Operation Management**: All records of the above operations. Read only. 

**Spring Security**: Using spring security features for user validation and access control.



### Roles

**Admin:** Full access to any operation (but cannot create another admin account)

**Manager**: Spot management and Ad management

**Staff**: Can only read Ad and Spot information



### Design

In this system, every  advertisement is displayed on one certain spot.

I divide spot into two main categories: In_station&In_train. The ads types can be further divided into several subtypes which you can see when log into the system.





*The login page image is from "https://unsplash.com/photos/El5zuQAtfeo" shared Balazs Busznyak*.

