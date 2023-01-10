create database bankmanagementsystem; -- Creates our database

show databases -- shows the list of all the databases(Can be used for confirming whether the database is created or not)

use bankmanagementsystem; -- Switches the current database to bankmanagementsystem

show tables -- List of all the existing tables in the database


--login table
create table login(FormNo varchar(20), cardnumber varchar(30) , pinnumber varchar(20)); 


--signup table
create table signup(formno varchar(20),
name varchar(20),
father_name varchar(20),
dob varchar(20),
gender varchar(20),
email varchar(30),
marital_status varchar(20),
address varchar(50),
city varchar(25),
pincode varchar(20),
state varchar(25)
);

--signuptwo table
create table signuptwo(FormNo varchar(20),
Religion varchar(20),
Category varchar(20),
Income varchar(20),
Education varchar(20),
Occupation varchar(30),
PAN_No varchar(20),
Aadhar_No varchar(50),
Senior_Citizen varchar(25),
Existing_Account varchar(20)
)

--signupthree table
create table signupthree(
FormNO varchar(20),
AccountType varchar(50),
cardnumber varchar(30),
pinnumber varchar(20),
facility varchar(200)
);

--bank table
CREATE TABLE bank(
   pin varchar(20) DEFAULT NULL,
   date varchar(50) DEFAULT NULL,
   type varchar(20) DEFAULT NULL,
   amount varchar(20) DEFAULT NULL
 )