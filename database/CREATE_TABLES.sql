/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     28/02/2023 18:47:36                          */
/*==============================================================*/

drop table if exists DEPARTMENTS_EMPLOYEES;

drop table if exists DEPARTMENTS;

drop table if exists EMPLOYEES;

drop table if exists ENTERPRISES;

/*==============================================================*/
/* Table: DEPARTMENTS                                           */
/*==============================================================*/
create table DEPARTMENTS
(
   ID_DEPARTMENT        varchar(36) not null,
   ID_ENTERPRISE        varchar(36) not null,
   CREATED_BY           varchar(36) not null,
   CREATED_DATE         date not null,
   MODIFIED_BY          varchar(36),
   MODIFIED_DATE        date,
   STATUS               bool not null,
   DESCRIPTION          varchar(256) not null,
   NAME                 varchar(128) not null,
   PHONE                varchar(10) not null,
   primary key (ID_DEPARTMENT)
);

/*==============================================================*/
/* Table: DEPARTMENTS_EMPLOYEES                                 */
/*==============================================================*/
create table DEPARTMENTS_EMPLOYEES
(
   ID_DEP_EMP	        varchar(36) not null,
   ID_DEPARTMENT        varchar(36) not null,
   ID_EMPLOYEE          varchar(36) not null,
   CREATED_BY           varchar(36) not null,
   CREATED_DATE         date not null,
   MODIFIED_BY          varchar(36),
   MODIFIED_DATE        date,
   STATUS               bool not null,
   primary key (ID_DEP_EMP)
);

/*==============================================================*/
/* Table: EMPLOYEES                                             */
/*==============================================================*/
create table EMPLOYEES
(
   ID_EMPLOYEE	        varchar(36) not null,
   CREATED_BY           varchar(36) not null,
   CREATED_DATE         date not null,
   MODIFIED_BY          varchar(36),
   MODIFIED_DATE        date,
   STATUS               bool not null,
   AGE                  smallint not null,
   EMAIL                varchar(128) not null,
   NAME                 varchar(128) not null,
   SURNAME              varchar(128) not null,
   POSITION             varchar(64) not null,
   primary key (ID_EMPLOYEE)
);

/*==============================================================*/
/* Table: ENTERPRISES                                           */
/*==============================================================*/
create table ENTERPRISES
(
   ID_ENTERPRISE        varchar(36) not null,
   CREATED_BY           varchar(36) not null,
   CREATED_DATE         date not null,
   MODIFIED_BY          varchar(36),
   MODIFIED_DATE        date,
   STATUS               bool not null,
   NAME                 varchar(128) not null,
   ADDRESS              varchar(256) not null,
   PHONE                varchar(10) not null,
   primary key (ID_ENTERPRISE)
);

alter table DEPARTMENTS add constraint FK_RELATIONSHIP_1 foreign key (ID_ENTERPRISE)
      references ENTERPRISES (ID_ENTERPRISE) on delete restrict on update restrict;

alter table DEPARTMENTS_EMPLOYEES add constraint FK_RELATIONSHIP_2 foreign key (ID_DEPARTMENT)
      references DEPARTMENTS (ID_DEPARTMENT) on delete restrict on update restrict;

alter table DEPARTMENTS_EMPLOYEES add constraint FK_RELATIONSHIP_3 foreign key (ID_EMPLOYEE)
      references EMPLOYEES (ID_EMPLOYEE) on delete restrict on update restrict;

