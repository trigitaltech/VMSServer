INSERT IGNORE INTO m_code VALUES (null,'EntityType',0,'EntityType');
SET @id = (select id from m_code where code_name='EntityType');

INSERT IGNORE INTO m_code_value VALUES (null,@id,'Proprietorship',0);
INSERT IGNORE INTO m_code_value VALUES (null,@id,'Pvt.Ltd.Co',1);
INSERT IGNORE INTO m_code_value VALUES (null,@id,'Ltd.Co',2);
INSERT IGNORE INTO m_code_value values (null, @id,'HUF', '3');
INSERT IGNORE INTO m_code_value values (null, @id,'Individual', '4');
INSERT IGNORE INTO m_code_value values (null, @id,'Foreign Co', '5');
INSERT IGNORE INTO m_code_value values (null, @id,'Others', '6');
INSERT IGNORE INTO m_code_value values (null, @id,'Individual-Govt. Official', '7');
INSERT IGNORE INTO m_code_value values (null, @id,'Governament Entity', '8');


INSERT IGNORE INTO m_code VALUES (null,'Residential Status',0,'Residential Status');
SET @id = (select id from m_code where code_name='Residential Status');

INSERT IGNORE INTO m_code_value VALUES (null,@id,'Resident',0);
INSERT IGNORE INTO m_code_value VALUES (null,@id,'NOR',1);
INSERT IGNORE INTO m_code_value VALUES (null,@id,'NR',2);
INSERT IGNORE INTO m_code_value values (null, @id,'Foreign Co', '3');
INSERT IGNORE INTO m_code_value values (null, @id,'Others', '4');

ALTER TABLE `obstenant-default`.`m_document` 
CHANGE COLUMN `name` `name` VARCHAR(250) CHARACTER SET 'utf8' NULL ;

ALTER TABLE `b_vendor_management` 
ADD COLUMN `password` VARCHAR(20) NULL AFTER `is_deleted`;
