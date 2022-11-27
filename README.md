# E-ComWebApp-AdvanceJava


For run this project in intelliJ

Add below jar file:

[servlet.zip](https://github.com/santoshkumaawat/E-ComWebApp-AdvanceJava/files/10098300/servlet.zip)

[javax.servlet.jsp.zip.zip](https://github.com/santoshkumaawat/E-ComWebApp-AdvanceJava/files/10098315/javax.servlet.jsp.zip.zip)

1.Open your installed IntelliJ IDEA Project and

2.Go to the File > Project Structure

3.Select Modules at the left panel and select the Dependencies tab.

4.Select the + icon and select 1 JARs or Directories option.

5.select your JAR file or you can select the directories.

6.Click on the OK button

--------------------------------



For run this project in Netbeans:

Add below mysql-connector files:

[mysql-connector-java-8.0.19.zip](https://github.com/santoshkumaawat/E-ComWebApp-AdvanceJava/files/10098451/mysql-connector-java-8.0.19.zip)

1. Open projects

2. Right click on Libraries

3. Click on Add/JAR folder

4. Select downloaded file.

5. Click open

--------------------------------



Create below DB in local:

CREATE DATABASE ECOMMERCE;

USE ECOMMERCE;

CREATE TABLE USERS 
(FIRST_NAME VARCHAR(30), 
LAST_NAME VARCHAR(30), 
EMAIL VARCHAR(50) PRIMARY KEY, 
PASSWORD VARCHAR(50), 
PHONE_NUMBER VARCHAR(15));

CREATE TABLE PRODUCTS
(pcode integer PRIMARY KEY,
pname varchar(50), 
pdesc varchar(500),
price integer,
pcatg varchar(50));

--------------------------------


Thankyou for visit!
