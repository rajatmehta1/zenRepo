--Common schema shared by all the other entities

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

