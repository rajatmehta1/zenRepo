-- Main database file
drop database if exists zenmobiledb;
create database zenmobiledb;
 
-- NOTE : tenant_id is primary key
drop table if exists tenant;
create table tenant (
  tenant_id int auto_increment,
  name VARCHAR(255),
  description VARCHAR(100),
  schema_name VARCHAR(20),
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,
  primary key (tenant_id)
) type = innodb;
 
-- Group table e.g Marketing, Sales etc
drop table if exists user_group;
create table user_group (
  group_id int auto_increment,
  tenant_id int not null,
  group_name VARCHAR(255),
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,
  primary key (group_id),
  foreign key (tenant_id) references tenant(tenant_id)
) type = innodb;
 
-- NOTE : username being unique should have a unique index
drop table if exists user_details;
create table user_details (
  user_id int auto_increment,
  last_name VARCHAR(255),
  first_name VARCHAR(255),
  email VARCHAR(255),
  username VARCHAR(10),
  password VARCHAR(10),
  secret_question VARCHAR(255),
  secret_password VARCHAR(255),
  expiration_date DATE,
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,
  primary key (user_id)
) type = innodb;
 
-- all roles -- e.g Admin's etc also can be called USER_GROUP
drop table if exists roles;
create table roles (
  role_id int auto_increment,
  name VARCHAR(255),
  description VARCHAR(255),
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,  
  primary key (role_id)
) type = innodb;
 
-- users to group mapping
drop table if exists users;
create table users (
  userid int not null,
  groupid int not null,
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,  
  primary key (userid,groupid)
) type = innodb;
 
-- holds the users to roles mappings
drop table if exists user_roles;
create table user_roles (
  userid int not null,
  roleid int not null,
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,  
  primary key (userid,roleid)
) type = innodb;
 
-- applications
drop table if exists application_details;
create table application_details (
app_id int auto_increment,
app_name VARCHAR(255),
app_type VARCHAR(255), 
 ipa_file_name VARCHAR(255),
icon_file_name VARCHAR(255),
description VARCHAR(255),
version int,
version_name VARCHAR(10),
author VARCHAR(255),
file_storage_location VARCHAR(1000),
os VARCHAR(20),
pkg_name VARCHAR(50),
status VARCHAR(2), 
  primary key (app_id)
) type = innodb;
 
-- applications
drop table if exists applications;
create table applications (
  application_id int not null,
  tenant_id int not null,
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
 updated_on DATE,  
  primary key (application_id,tenant_id)
) type = innodb;
 
 
 
-- applications
drop table if exists application_workflow;
create table application_workflow (
app_id int not null,
app_status int not null, 
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,  
  primary key (app_id,app_status)
) type = innodb;
 
 
drop table if exists application_status_type;
create table application_status_type (
status_id int auto_increment,
name VARCHAR(255), 
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,  
  primary key (status_id)
) type = innodb;
 
 
-- holds static data if needed for any drop downs etc
-- key value pairs kinds
-- e.g
-- type = HELP_GUIDE , type_value = http://<LOCATIO TO GUIDE>
-- APP_STORAGE_LOCATION =<APP_STORAGE_LOCATION>...
drop table if exists system_config_constants;
create table system_config_constants (
   sid int auto_increment,
   tenant_id int not null,
   key_name VARCHAR(40) NOT NULL,
   key_type VARCHAR(40) NOT NULL,
   key_value VARCHAR(2000) NOT NULL,
  created_by VARCHAR(10),
  created_on DATE,
  updated_by VARCHAR(10),
  updated_on DATE,     
   primary key (sid),
   foreign key (tenant_id) references tenant(tenant_id)
) type = innodb;
 
-- device
drop table if exists device_details;
create table device_details (
device_id int auto_increment,
user_id int not null,
device_name VARCHAR(255),
device_type VARCHAR(255), 
 description VARCHAR(255),
os VARCHAR(20),
device_model VARCHAR(30),
serial_num VARCHAR(255),
phone_num VARCHAR(20),
enroll_date DATE,
status VARCHAR(2),
power_status int,
storage_capacity VARCHAR(10),
created_by VARCHAR(10),
created_on DATE,
updated_by VARCHAR(10),
updated_on DATE,  
 primary key (device_id),
foreign key (user_id) references user_details(user_id)
) type = innodb;
 
 
-- deviceapps
drop table if exists device_apps;
create table device_apps (
device_app_id int auto_increment,
device_id int not null,
app_id int not null,
primary key (device_app_id),
foreign key (device_id) references device_details(device_id),
foreign key (app_id) references application_details(app_id)
) type = innodb;
 
 
-- enrollment
drop table if exists enrollments;
create table enrollments (
  enrollment_id int auto_increment,
  device_id int not null,
  device_user varchar(20),
  device_name VARCHAR(30),
  device_type VARCHAR(20),
  device_model VARCHAR(30),  
  phone_num VARCHAR(20),
  enroll_date DATE,     
  os VARCHAR(20),
  os_version VARCHAR(20),
  status VARCHAR(2),
  primary key(enrollment_id),
  foreign key (device_id) references device_details(device_id)    
) type = innodb;