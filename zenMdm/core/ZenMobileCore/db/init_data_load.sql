-- Initial Data Load
 
-- Tenant
insert into tenant(name,description,schema_name,created_by,created_on,updated_by,updated_on) values('Test Tenant','Test Tenant Desc','zenmobiledb','system',curDate(),'system',CURDATE());
 
-- users - Developer / Admin / End User
insert into roles(name,description,created_by,created_on,updated_by,updated_on) values('Admin','Admin User','system',curDate(),'system',CURDATE());
 
insert into roles(name,description,created_by,created_on,updated_by,updated_on) values('Developer','Developer','system',curDate(),'system',CURDATE());
 
insert into roles(name,description,created_by,created_on,updated_by,updated_on) values('EndUser','Normal End User','system',curDate(),'system',CURDATE());
 
-- using tenant id as 1
insert into user_group(tenant_id,group_name,created_by,created_on,updated_by,updated_on) values(1,'Marketing','system',curDate(),'system',CURDATE());
insert into user_group(tenant_id,group_name,created_by,created_on,updated_by,updated_on) values(1,'Legal and Compliance','system',curDate(),'system',CURDATE());
insert into user_group(tenant_id,group_name,created_by,created_on,updated_by,updated_on) values(1,'HR','system',curDate(),'system',CURDATE());
 
insert into user_details(user_id,last_name,first_name,email,username,password,secret_question,secret_password,expiration_date,created_by,created_on,updated_by,updated_on) 
values(1,'admin','admin','admin@zenmobile.com','admin','admin123','secreet question','asdf',CURDATE(),'system',curDate(),'system',CURDATE())
 
insert into user_details(user_id,last_name,first_name,email,username,password,secret_question,secret_password,expiration_date,created_by,created_on,updated_by,updated_on) 
values(2,'Mehta','Harpreet','admin@zenmobile.com','harpreet','harpreet','secreet question','asdf',CURDATE(),'system',curDate(),'system',CURDATE())
 
insert into device_details(device_id,user_id,device_name,device_type,description,os,device_model,serial_num) values(1,2,'Rajats Droid','Android','Android Phone','Android Jelly Bean','Samsung Galaxy S3','2378342988282')
 
insert into device_details(device_id,user_id,device_name,device_type,description,os,device_model,serial_num) values(2,2,'My Ipad','iPad','expensive ipad','iOS 5.0','iPad 3','78123489012389')
 
 
insert into application_details(app_id,app_name,app_type,description,version,author) values(1,'Pulse','News','News Reader App','2.0','Pulse Team')
insert into application_details(app_id,app_name,app_type,description,version,author) values(2,'Angry Birds','Games','Games','4.0','Angry Birds')
 
 
insert into device_apps(device_app_id,device_id,app_id) values(1,2,1)
insert into device_apps(device_app_id,device_id,app_id) values(2,2,2)
insert into device_apps(device_app_id,device_id,app_id) values(3,1,2)