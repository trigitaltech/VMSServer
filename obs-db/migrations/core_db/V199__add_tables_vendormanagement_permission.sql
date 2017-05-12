
Drop table IF EXISTS b_vendor_management;

CREATE TABLE if not existS `b_vendor_management` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `vendor_name` varchar(256) NOT NULL,
  `entity_type` int(30) DEFAULT NULL,
  `other_entity` varchar(60) DEFAULT NULL,
  `contact_name` varchar(256) DEFAULT NULL,
  `address_1` varchar(256) DEFAULT NULL,
  `address_2` varchar(256) DEFAULT NULL,
  `address_3` varchar(256) DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  `state` varchar(60) DEFAULT NULL,
  `city` varchar(60) DEFAULT NULL,
  `postal_code` varchar(10) DEFAULT NULL,
  `residential_status` int(30) DEFAULT NULL,
  `other_residential` varchar(60) DEFAULT NULL,
  `landline_no` varchar(15) DEFAULT NULL,
  `mobile_no` varchar(12) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `vendor_name_UNIQUE` (`vendor_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

CREATE TABLE if not existS `b_vendor_bank_details` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `vendor_id` int(10) NOT NULL,
  `bank_name` varchar(60) DEFAULT NULL,
  `account_no` varchar(20) DEFAULT NULL,
  `branch` varchar(256) DEFAULT NULL,
  `ifsc_code` varchar(60) DEFAULT NULL,
  `swift_code` varchar(60) DEFAULT NULL,
  `iban_code` varchar(60) DEFAULT NULL,
  `account_name` varchar(60) DEFAULT NULL,
  `cheque_no` varchar(60) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `fk_vendor_id` (`vendor_id`),
  CONSTRAINT `fk_vendor_id` FOREIGN KEY (`vendor_id`) REFERENCES `b_vendor_management` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

CREATE TABLE if not existS `b_vendor_other_details` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `vendor_id` int(10) NOT NULL,
  `pan_no` varchar(60) NOT NULL,
  `pan_file_name` varchar(256) DEFAULT NULL,
  `incur_certification` varchar(60) DEFAULT NULL,
  `certificate_file_name` varchar(256) DEFAULT NULL,
  `st_no` varchar(60) DEFAULT NULL,
  `st_file_name` varchar(256) DEFAULT NULL,
  `msm_status` int(2) DEFAULT NULL,
  `msm_reg_no` varchar(60) DEFAULT NULL,
  `msm_reg_date` date DEFAULT NULL,
  `msm_file_name` varchar(256) DEFAULT NULL,
  `vat_no` varchar(60) DEFAULT NULL,
  `vat_file_name` varchar(256) DEFAULT NULL,
  `gst_no` varchar(60) DEFAULT NULL,
  `gst_file_name` varchar(256) DEFAULT NULL,
  `cst_no` varchar(60) DEFAULT NULL,
  `cst_file_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pan_no_uq` (`pan_no`),
  KEY `fk_vendor_other_details_id` (`vendor_id`),
  CONSTRAINT `fk_vendor_other_details_id` FOREIGN KEY (`vendor_id`) REFERENCES `b_vendor_management` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


insert IGNORE into m_permission values(null, 'portfolio', 'CREATE_VENDOROTHERDETAILS', 'VENDOROTHERDETAILS', 'CREATE', '0');

insert IGNORE into m_permission values(null, 'portfolio', 'READ_VENDOROTHERDETAILS', 'VENDOROTHERDETAILS', 'READ', '0');
