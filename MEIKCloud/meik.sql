/*
Navicat MySQL Data Transfer

Source Server         : localhost (Mysql)
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : meik

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-09-19 14:04:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for nuvo_admin
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_admin`;
CREATE TABLE `nuvo_admin` (
  `admin_id` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role_id` int(11) NOT NULL,
  `status` bit(1) NOT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `created_time` bigint(18) DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  `updated_time` bigint(18) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_admin
-- ----------------------------
INSERT INTO `nuvo_admin` VALUES ('admin', 'admin@campray.com', '1f82c942befda29b6ed487a51da199f78fce7f05', '1', '', null, null, null, null, 'd08771c9-138b-4ca8-abfb-2909a740bc31');

-- ----------------------------
-- Table structure for nuvo_admin_info
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_admin_info`;
CREATE TABLE `nuvo_admin_info` (
  `admin_id` varchar(100) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `about` varchar(2000) DEFAULT NULL,
  `avatar_small` blob,
  `avatar` mediumblob,
  `notify` bit(1) DEFAULT b'1',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_admin_info
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_admin_job
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_admin_job`;
CREATE TABLE `nuvo_admin_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` smallint(6) NOT NULL,
  `status` bit(1) NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  `admin_id` varchar(100) DEFAULT NULL,
  `assign_time` bigint(20) DEFAULT NULL,
  `report_time` bigint(20) DEFAULT NULL,
  `done_time` bigint(20) DEFAULT NULL,
  `close_time` bigint(20) DEFAULT NULL,
  `download_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_admin_job
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_admin_log`;
CREATE TABLE `nuvo_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(100) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `level` smallint(6) NOT NULL,
  `created_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_admin_log
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_admin_nodes
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_admin_nodes`;
CREATE TABLE `nuvo_admin_nodes` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT,
  `bit_flag` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `uri` varchar(100) NOT NULL,
  `request_method` varchar(10) NOT NULL,
  `pid` int(11) NOT NULL,
  `is_menu` bit(1) NOT NULL,
  `status` bit(1) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `group_sort` smallint(6) DEFAULT NULL,
  `descr` varchar(1000) DEFAULT NULL,
  `menu_icon` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`node_id`),
  UNIQUE KEY `node_binary_UNIQUE` (`bit_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_admin_nodes
-- ----------------------------
INSERT INTO `nuvo_admin_nodes` VALUES ('1', '1', 'System Management', '/rights', 'ALL', '0', '', '', 'System Management', '0', 'Rights Management Menu', 'icon-wrench');
INSERT INTO `nuvo_admin_nodes` VALUES ('2', '2', 'Rights Page', '/rights', 'GET', '1', '', '', 'System Management', '1', 'Display the rights list page', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('3', '4', 'All Rights Functions', '/rights/*', 'ALL', '1', '\0', '', 'System Management', '2', 'All  of the rights function', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('4', '8', 'Roles Page', '/roles', 'GET', '1', '', '', 'System Management', '3', 'System Roles List', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('5', '16', 'All Roles Functions', '/roles/*', 'ALL', '1', '\0', '', 'System Management', '4', 'All  of the roles function', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('6', '32', 'Manager Page', '/manager', 'GET', '1', '', '', 'System Management', '5', 'System Manager List', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('7', '64', 'All Manager Functions', '/manager/*', 'ALL', '1', '\0', '', 'System Management', '6', 'All of the manager function', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('8', '128', 'Language Page', '/language', 'GET', '1', '', '', 'System Management', '7', 'System Language List', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('9', '256', 'All Language Functions', '/language/*', 'ALL', '1', '\0', '', 'System Management', '8', 'All of the language function', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('10', '512', 'Licenses List', '/license', 'GET', '1', '', '', 'System Management', '9', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('11', '1024', 'All Licenses Functions', '/license/*', 'ALL', '1', '\0', '', 'System Management', '10', 'All of the license function', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('12', '2048', 'System Setting', '/settings', 'GET', '1', '', '', 'System Management', '11', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('13', '4096', 'Edit Setting', '/settings/editsetting', 'POST', '1', '\0', '', 'System Management', '12', 'Edit Setting', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('14', '8192', 'Group Page', '/group', 'GET', '1', '', '', 'System Management', '13', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('15', '16384', 'Group List', '/group/groupList', 'GET', '1', '\0', '', 'System Management', '14', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('16', '32768', 'Add Group Functions', '/group/*', 'ALL', '1', '\0', '', 'System Management', '15', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('17', '65536', 'GroupUser Page', '/groupuser', 'GET', '1', '\0', '', 'System Management', '16', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('18', '131072', 'GroupUser List', '/groupuser/groupUserList/*', 'GET', '1', '\0', '', 'System Management', '17', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('19', '262144', 'All GroupUser Functions', '/groupuser/*', 'ALL', '1', '\0', '', 'System Management', '18', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('20', '524288', 'User Profile', '/userprofile', 'GET', '1', '\0', '', 'System Management', '19', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('21', '1048576', 'All Profile Functions', '/userprofile/*', 'ALL', '1', '\0', '', 'System Management', '20', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('22', '2097152', 'Job Management', '/jobs', 'ALL', '0', '', '', 'Job Management', '1', 'Job Management', 'icon-energy');
INSERT INTO `nuvo_admin_nodes` VALUES ('23', '4194304', 'Job Page', '/jobs', 'GET', '22', '', '', 'Job Management', '0', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('24', '8388608', 'Job List', '/jobs/list', 'ALL', '22', '\0', '', 'Job Management', '1', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('25', '16777216', 'Assign Job', '/jobs/assign', 'POST', '22', '\0', '', 'Job Management', '2', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('26', '33554432', 'Delete Jobs', '/jobs/delete/*', 'ALL', '22', '\0', '', 'Job Management', '3', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('27', '67108864', 'Download Screen PDF', '/jobs/downloadScreenPdf/*', 'ALL', '22', '\0', '', 'Job Management', '4', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('28', '134217728', 'Download Doctor PDF', '/jobs/download/*', 'ALL', '22', '\0', '', 'Job Management', '5', '', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('29', '268435456', 'Report Management', '/report', 'ALL', '0', '', '', 'Report Management', '2', '', 'icon-docs');
INSERT INTO `nuvo_admin_nodes` VALUES ('30', '536870912', 'Records', '/records', 'GET', '29', '', '', 'Report Management', '0', 'Records List', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('31', '1073741824', 'Operator Report', '/report/operator', 'GET', '29', '', '', 'Report Management', '1', 'Operator Report', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('32', '2147483648', 'Operator List', '/report/operator/list', 'GET', '29', '\0', '', 'Report Management', '2', 'Operator Report', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('33', '4294967296', 'Export Operator', '/report/operator/excel', 'POST', '29', '\0', '', 'Report Management', '3', 'Export operator report', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('34', '8589934592', 'Doctor Report', '/report/doctor', 'GET', '29', '', '', 'Report Management', '5', 'Doctor Report', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('35', '17179869184', 'Doctor List', '/report/doctor/list', 'GET', '29', '\0', '', 'Report Management', '6', 'Export Doctor Report', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('36', '34359738368', 'Export Doctor', '/report/doctor/excel', 'POST', '29', '\0', '', 'Report Management', '7', 'Export operator report', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('37', '68719476736', 'DownLoad Screen Zip', '/report/downloadScreenZip/*', 'GET', '29', '\0', '', 'Report Management', '8', 'DownLoad Screen Zip', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('38', '137438953472', 'DownLoad Doctor Zip', '/report/downloadDocotrZip/*', 'GET', '29', '\0', '', 'Report Management', '9', 'DownLoad Doctor Zip', '');
INSERT INTO `nuvo_admin_nodes` VALUES ('39', '274877906944', 'View Register Info', '/viewreg', 'GET', '0', '\0', '', 'Register management', '3', 'Register management', '');

-- ----------------------------
-- Table structure for nuvo_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_admin_role`;
CREATE TABLE `nuvo_admin_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `pid` int(11) NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_admin_role
-- ----------------------------
INSERT INTO `nuvo_admin_role` VALUES ('1', 'admin', '0', '');
INSERT INTO `nuvo_admin_role` VALUES ('2', 'Operator', '0', '');
INSERT INTO `nuvo_admin_role` VALUES ('3', 'Doctor', '0', '');
INSERT INTO `nuvo_admin_role` VALUES ('4', 'Client', '0', '');

-- ----------------------------
-- Table structure for nuvo_admin_role_rights
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_admin_role_rights`;
CREATE TABLE `nuvo_admin_role_rights` (
  `role_id` int(11) NOT NULL,
  `role_rights` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_admin_role_rights
-- ----------------------------
INSERT INTO `nuvo_admin_role_rights` VALUES ('1', '549755775929');
INSERT INTO `nuvo_admin_role_rights` VALUES ('2', '351599591424');
INSERT INTO `nuvo_admin_role_rights` VALUES ('3', '489038544896');
INSERT INTO `nuvo_admin_role_rights` VALUES ('4', '251133952');

-- ----------------------------
-- Table structure for nuvo_group
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_group`;
CREATE TABLE `nuvo_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) NOT NULL,
  `group_info` varchar(3000) DEFAULT NULL,
  `created_time` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_group
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_group_user
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_group_user`;
CREATE TABLE `nuvo_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `admin_id` varchar(100) NOT NULL,
  `created_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_group_user
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_language
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_language`;
CREATE TABLE `nuvo_language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `local` varchar(10) NOT NULL,
  `flag_image` tinyblob,
  `status` bit(1) NOT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_language
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_license
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_license`;
CREATE TABLE `nuvo_license` (
  `license` varchar(100) NOT NULL,
  `cpu_id` varchar(100) DEFAULT NULL,
  `active_time` bigint(20) DEFAULT NULL,
  `device_id` varchar(10) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `deadline` bigint(20) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`license`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_license
-- ----------------------------
INSERT INTO `nuvo_license` VALUES ('34a716de-ad89-11e6-9d0f-74d435c02096', 'BFEBFBFF000306A9', '1505798713457', null, '', null, null);
INSERT INTO `nuvo_license` VALUES ('4241cadc-ad89-11e6-9d0f-74d435c02096', null, null, null, '', null, null);
INSERT INTO `nuvo_license` VALUES ('4c75e8e2-013f-49cd-a957', null, null, null, '', null, null);
INSERT INTO `nuvo_license` VALUES ('9145585c-ae43-11e6-9d0f', null, null, null, '', null, null);
INSERT INTO `nuvo_license` VALUES ('9ae99df5-bbe2-42fb-ad8c', null, null, null, '\0', null, null);
INSERT INTO `nuvo_license` VALUES ('ac8aa1d5-ae43-11e6-9d0f', null, null, null, '\0', null, null);
INSERT INTO `nuvo_license` VALUES ('acca1655-551e-4fb7-8e97', 'BFEBFBFF000306A9', '1481511801411', null, '', null, null);

-- ----------------------------
-- Table structure for nuvo_localized_field
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_localized_field`;
CREATE TABLE `nuvo_localized_field` (
  `locale_id` int(11) NOT NULL AUTO_INCREMENT,
  `language_id` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `table_name` varchar(50) NOT NULL,
  `table_field` varchar(50) NOT NULL,
  `locale_value` text NOT NULL,
  PRIMARY KEY (`locale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_localized_field
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_records
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_records`;
CREATE TABLE `nuvo_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `client_name` varchar(100) NOT NULL,
  `screen_time` bigint(20) NOT NULL,
  `device_id` varchar(50) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `license` varchar(100) DEFAULT NULL,
  `upload_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_records
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_setting
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_setting`;
CREATE TABLE `nuvo_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `value` varchar(2000) NOT NULL,
  `descr` varchar(2000) DEFAULT NULL,
  `sort` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_setting
-- ----------------------------
INSERT INTO `nuvo_setting` VALUES ('1', 'Email_Username', 'admin@campray.com', '系统电邮帐号', '1');
INSERT INTO `nuvo_setting` VALUES ('2', 'Email_Password', 'CampRay2014', '系统电邮密码', '2');
INSERT INTO `nuvo_setting` VALUES ('3', 'Email_Host', 'smtp.exmail.qq.com', '发送电邮HOST', '3');
INSERT INTO `nuvo_setting` VALUES ('4', 'Max_Login_Error_Times', '3', '最大允许登录错误次数', '4');
INSERT INTO `nuvo_setting` VALUES ('5', 'Login_Error_Locked', '3', '登录错误锁定时间', '5');
INSERT INTO `nuvo_setting` VALUES ('6', 'System_Doctor_Id', 'phills', '系统医生帐号', '6');
INSERT INTO `nuvo_setting` VALUES ('7', 'Create_Account', 'false', '是否需要為每個用戶自動創建帳號', '7');

-- ----------------------------
-- Table structure for nuvo_user
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_user`;
CREATE TABLE `nuvo_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `cid` varchar(100) DEFAULT NULL,
  `created_by` varchar(100) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `other_name` varchar(100) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthday` varchar(50) NOT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `free` bit(1) DEFAULT NULL,
  `screen_location` varchar(3000) DEFAULT NULL,
  `screen_result` int(11) DEFAULT NULL,
  `missing_data` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_user
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_user_data
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_user_data`;
CREATE TABLE `nuvo_user_data` (
  `data_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `stream` mediumblob NOT NULL,
  `data_type` smallint(6) NOT NULL,
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nuvo_user_data
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_user_info
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_user_info`;
CREATE TABLE `nuvo_user_info` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_time` bigint(20) NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `other_name` varchar(100) DEFAULT NULL,
  `birthday` varchar(50) NOT NULL,
  `mobile` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `height` varchar(100) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `address2` varchar(500) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `zipcode` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `family_breast_cancer1` bit(1) DEFAULT NULL,
  `family_breast_cancer2` bit(1) DEFAULT NULL,
  `family_breast_cancer3` bit(1) DEFAULT NULL,
  `family_breast_cancer_desc` text,
  `family_uterine_cancer1` bit(1) DEFAULT NULL,
  `family_uterine_cancer2` bit(1) DEFAULT NULL,
  `family_uterine_cancer3` bit(1) DEFAULT NULL,
  `family_uterine_cancer_desc` text,
  `family_cervical_cancer1` bit(1) DEFAULT NULL,
  `family_cervical_cancer2` bit(1) DEFAULT NULL,
  `family_cervical_cancer3` bit(1) DEFAULT NULL,
  `family_cervical_cancer_desc` text,
  `family_ovarian_cancer1` bit(1) DEFAULT NULL,
  `family_ovarian_cancer2` bit(1) DEFAULT NULL,
  `family_ovarian_cancer3` bit(1) DEFAULT NULL,
  `family_ovarian_cancer_desc` text,
  `family_cancer_desc` text,
  `complaints_palpable_lumps` bit(1) DEFAULT NULL,
  `complaints_pain` bit(1) DEFAULT NULL,
  `complaints_degree` int(11) DEFAULT NULL,
  `complaints_colostrum` bit(1) DEFAULT NULL,
  `complaints_serous` bit(1) DEFAULT NULL,
  `complaints_blood` bit(1) DEFAULT NULL,
  `complaints_lumps_left_position` int(11) DEFAULT NULL,
  `complaints_lumps_right_position` int(11) DEFAULT NULL,
  `complaints_pregnancy` bit(1) DEFAULT NULL,
  `complaints_pregnancy_term` varchar(100) DEFAULT NULL,
  `complaints_lactation` bit(1) DEFAULT NULL,
  `complaints_lactation_term` varchar(100) DEFAULT NULL,
  `complaints_breast_implants` bit(1) DEFAULT NULL,
  `complaints_breast_implants_left` bit(1) DEFAULT NULL,
  `complaints_breast_implants_left_year` varchar(50) DEFAULT NULL,
  `complaints_breast_implants_right` bit(1) DEFAULT NULL,
  `complaints_breast_implants_right_year` varchar(50) DEFAULT NULL,
  `complaints_breast_implants_gel` bit(1) DEFAULT NULL,
  `complaints_breast_implants_fat` bit(1) DEFAULT NULL,
  `complaints_breast_implants_others` bit(1) DEFAULT NULL,
  `complaints_other_desc` text,
  `menses_last_menstruation` varchar(50) DEFAULT NULL,
  `menses_cycle_disorder` bit(1) DEFAULT NULL,
  `menses_cycle_disorder_desc` text,
  `menses_postmenopause` bit(1) DEFAULT NULL,
  `menses_postmenopause_year` varchar(50) DEFAULT NULL,
  `menses_hormonal_contraceptives` bit(1) DEFAULT NULL,
  `menses_hormonal_contraceptives_name` varchar(100) DEFAULT NULL,
  `menses_hormonal_contraceptives_period` varchar(50) DEFAULT NULL,
  `menses_desc` text,
  `obstetric_infertility` bit(1) DEFAULT NULL,
  `obstetric_abortions` bit(1) DEFAULT NULL,
  `obstetric_abortions_times` varchar(20) DEFAULT NULL,
  `obstetric_births` bit(1) DEFAULT NULL,
  `obstetric_births_times` varchar(20) DEFAULT NULL,
  `obstetric_lactation_under_month` bit(1) DEFAULT NULL,
  `obstetric_lactation_under_year` bit(1) DEFAULT NULL,
  `obstetric_lactation_over_year` bit(1) DEFAULT NULL,
  `obstetric_desc` text,
  `anamnesis_breast_diseases_trauma` bit(1) DEFAULT NULL,
  `anamnesis_breast_diseases_mastitis` bit(1) DEFAULT NULL,
  `anamnesis_breast_diseases_fibrous` bit(1) DEFAULT NULL,
  `anamnesis_breast_diseases_cysts` bit(1) DEFAULT NULL,
  `anamnesis_breast_diseases_cancer` bit(1) DEFAULT NULL,
  `anamnesis_breast_diseases_desc` text,
  `anamnesis_ovary_diseases_inflammation` bit(1) DEFAULT NULL,
  `anamnesis_ovary_diseases_cyst` bit(1) DEFAULT NULL,
  `anamnesis_ovary_diseases_cancer` bit(1) DEFAULT NULL,
  `anamnesis_ovary_diseases_endometriosis` bit(1) DEFAULT NULL,
  `anamnesis_ovary_diseases_desc` text,
  `anamnesis_uterus_diseases_inflammation` bit(1) DEFAULT NULL,
  `anamnesis_uterus_diseases_myoma` bit(1) DEFAULT NULL,
  `anamnesis_uterus_diseases_cancer` bit(1) DEFAULT NULL,
  `anamnesis_uterus_diseases_endometriosis` bit(1) DEFAULT NULL,
  `anamnesis_uterus_diseases_desc` text,
  `anamnesis_somatic_diseases_adiposity` bit(1) DEFAULT NULL,
  `anamnesis_somatic_diseases_hypertension` bit(1) DEFAULT NULL,
  `anamnesis_somatic_diseases_diabetes` bit(1) DEFAULT NULL,
  `anamnesis_somatic_diseases_thyroid` bit(1) DEFAULT NULL,
  `anamnesis_somatic_diseases_desc` text,
  `anamnesis_desc` text,
  `examinations_palpation` bit(1) DEFAULT NULL,
  `examinations_palpation_year` varchar(50) DEFAULT NULL,
  `examinations_palpation_normal` bit(1) DEFAULT NULL,
  `examinations_palpation_abnormal` bit(1) DEFAULT NULL,
  `examinations_palpation_diffuse` bit(1) DEFAULT NULL,
  `examinations_palpation_focal` bit(1) DEFAULT NULL,
  `examinations_palpation_desc` text,
  `examinations_ultrasound` bit(1) DEFAULT NULL,
  `examinations_ultrasound_year` varchar(50) DEFAULT NULL,
  `examinations_ultrasound_normal` bit(1) DEFAULT NULL,
  `examinations_ultrasound_abnormal` bit(1) DEFAULT NULL,
  `examinations_ultrasound_diffuse` bit(1) DEFAULT NULL,
  `examinations_ultrasound_focal` bit(1) DEFAULT NULL,
  `examinations_ultrasound_desc` text,
  `examinations_mammography` bit(1) DEFAULT NULL,
  `examinations_mammography_year` varchar(50) DEFAULT NULL,
  `examinations_mammography_normal` bit(1) DEFAULT NULL,
  `examinations_mammography_abnormal` bit(1) DEFAULT NULL,
  `examinations_mammography_diffuse` bit(1) DEFAULT NULL,
  `examinations_mammography_focal` bit(1) DEFAULT NULL,
  `examinations_mammography_desc` text,
  `examinations_biopsy` bit(1) DEFAULT NULL,
  `examinations_biopsy_year` varchar(50) DEFAULT NULL,
  `examinations_biopsy_normal` bit(1) DEFAULT NULL,
  `examinations_biopsy_abnormal` bit(1) DEFAULT NULL,
  `examinations_biopsy_cancer` bit(1) DEFAULT NULL,
  `examinations_biopsy_proliferation` bit(1) DEFAULT NULL,
  `examinations_biopsy_dysplasia` bit(1) DEFAULT NULL,
  `examinations_biopsy_papilloma` bit(1) DEFAULT NULL,
  `examinations_biopsy_fibroadenoma` bit(1) DEFAULT NULL,
  `examinations_biopsy_desc` text,
  `examinations_meik` bit(1) DEFAULT NULL,
  `examinations_meik_year` varchar(50) DEFAULT NULL,
  `examinations_meik_point` varchar(50) DEFAULT NULL,
  `examinations_meik_desc` text,
  `examinations_desc` text,
  `visual_swollen` bit(1) DEFAULT NULL,
  `visual_swollen_left` int(11) DEFAULT NULL,
  `visual_swollen_right` int(11) DEFAULT NULL,
  `visual_swollen_desc` text,
  `visual_palpable` bit(1) DEFAULT NULL,
  `visual_palpable_left` int(11) DEFAULT NULL,
  `visual_palpable_right` int(11) DEFAULT NULL,
  `visual_palpable_desc` text,
  `visual_serous` bit(1) DEFAULT NULL,
  `visual_serous_left` int(11) DEFAULT NULL,
  `visual_serous_right` int(11) DEFAULT NULL,
  `visual_serous_desc` text,
  `visual_wounds` bit(1) DEFAULT NULL,
  `visual_wounds_left` int(11) DEFAULT NULL,
  `visual_wounds_right` int(11) DEFAULT NULL,
  `visual_wounds_desc` text,
  `visual_scars` bit(1) DEFAULT NULL,
  `visual_scars_left` int(11) DEFAULT NULL,
  `visual_scars_right` int(11) DEFAULT NULL,
  `visual_scars_desc` text,
  `visual_desc` text,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for nuvo_version
-- ----------------------------
DROP TABLE IF EXISTS `nuvo_version`;
CREATE TABLE `nuvo_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(50) NOT NULL,
  `stream` mediumblob NOT NULL,
  `type` int(11) NOT NULL,
  `filename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nuvo_version
-- ----------------------------
