Drop user if exists 'adms'@'localhost';


 CREATE USER 'adms'@'localhost' IDENTIFIED BY 'adms';
 
 GRANT ALL PRIVILEGES ON * . * TO 'adms'@'localhost';
 
