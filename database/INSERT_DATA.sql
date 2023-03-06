/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     25/01/2023 18:47:36                          */
/*==============================================================*/

use sicpa;

INSERT INTO `phone_types` 
(`ID_PHONE_TYPE`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`, `TYPE`, `STATUS`) 
VALUES ('CON', 'ALBO', '2023-01-25', NULL, NULL, 'CONVENTIONAL', '1'), 
('CEL', 'ALBO', '2023-01-25', NULL, NULL, 'CELL PHONE', '1');

INSERT INTO `cities` 
(`ID_CITY`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`, `NAME`, `STATUS`)
VALUES ('UIO', 'ALBO', '2023-01-25', NULL, NULL, 'QUITO', '1'), 
('GYE', 'ALBO', '2023-01-25', NULL, NULL, 'GUAYAQUIL', '1');

INSERT INTO `positions` 
(`ID_POSITION`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`, `NAME`, `STATUS`)
VALUES ('CEO', 'ALBO', '2023-01-26', NULL, NULL, 'CEO', '1'), 
('ING', 'ALBO', '2023-01-26', NULL, NULL, 'INGENIERO', '1');
