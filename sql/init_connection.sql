Drop user if exists 'jgchuanmei'@'localhost';


 CREATE USER 'jgchuanmei'@'localhost' IDENTIFIED BY 'jgchuanmei';
 
 GRANT ALL PRIVILEGES ON * . * TO 'jgchuanmei'@'localhost';
 
 
 -- Drop user first if they exist
DROP USER if exists 'springstudent'@'localhost' ;

-- Now create user with prop privileges
CREATE USER 'springstudent'@'localhost' IDENTIFIED BY 'springstudent';
GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';