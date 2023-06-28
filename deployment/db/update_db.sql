

-- 0.0.0 -> 0.0.1
CREATE TABLE `sys_attr` (
  `uid` varchar(45) NOT NULL,
  `type_idx` tinyint DEFAULT NULL,
  `attr_key` varchar(45) DEFAULT NULL,
  `attr_value` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `system_seq` (
  `item_id` varchar(45) NOT NULL,
  `current_num` bigint DEFAULT NULL,
  `last_num` bigint DEFAULT NULL,
  `max_num` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ;

-- 0.0.1 -> 0.1.0
CREATE TABLE `mbom_part` (
  `uid` varchar(45) NOT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;


-- 0.1.0.1 -> 0.1.1

CREATE TABLE `mbom_part_acq` (
  `uid` varchar(45) NOT NULL,
  `part_uid` varchar(45) DEFAULT NULL,
  `part_pin` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type_idx` tinyint DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_acq_r_s` (
  `uid` varchar(45) NOT NULL,
  `part_acq_uid` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_pars_proc` (
  `uid` varchar(45) NOT NULL,
  `pars_uid` varchar(45) DEFAULT NULL,
  `seq` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
  `assign_proc` tinyint DEFAULT NULL,
  `proc_uid` varchar(45) DEFAULT NULL,
  `proc_id` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_pars_part` (
  `uid` varchar(45) NOT NULL,
  `pars_uid` varchar(45) DEFAULT NULL,
  `assign_part` tinyint DEFAULT NULL,
  `part_uid` varchar(45) DEFAULT NULL,
  `part_pin` varchar(45) DEFAULT NULL,
  `part_req_qty` double DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_cfg` (
  `uid` varchar(45) NOT NULL,
  `root_part_uid` varchar(45) DEFAULT NULL,
  `root_part_pin` varchar(45) DEFAULT NULL,
  `status_idx` tinyint DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_cfg_conj` (
  `uid` varchar(45) NOT NULL,
  `part_cfg_uid` varchar(45) DEFAULT NULL,
  `part_acq_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;



CREATE TABLE `mbom_prod` (
  `uid` varchar(45) NOT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_ctl` (
  `uid` varchar(45) NOT NULL,
  `id` varchar(45) DEFAULT NULL,
  `lv` tinyint DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `req` tinyint DEFAULT NULL,
  `parent_uid` varchar(45) DEFAULT NULL,
  `parent_id` varchar(45) DEFAULT NULL,
  `prod_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_ctl_part_cfg_conj` (
  `uid` varchar(45) NOT NULL,
  `prod_ctl_uid` varchar(45) DEFAULT NULL,
  `part_cfg_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_mod` (
  `uid` varchar(45) NOT NULL,
  `prod_uid` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_mod_item` (
  `uid` varchar(45) NOT NULL,
  `prod_mod_uid` varchar(45) DEFAULT NULL,
  `prod_ctl_uid` varchar(45) DEFAULT NULL,
  `part_cfg_assigned` tinyint DEFAULT NULL,
  `part_cfg_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;


-- 0.1.1 -> 0.1.2
ALTER TABLE `mbom_prod_mod` 
CHANGE COLUMN `desp` `desp` VARCHAR(200) NULL DEFAULT NULL ;

-- 0.1.3 -> 0.1.4
ALTER TABLE `mbom_part` 
ADD COLUMN `unit_idx` TINYINT NULL AFTER `name`;

ALTER TABLE `mbom_part_acq_r_s` 
CHANGE COLUMN `id` `seq` VARCHAR(45) NULL DEFAULT NULL ;

-- 0.1.5 -> 0.1.6
ALTER TABLE `mbom_part_cfg` 
ADD COLUMN `publish_time` BIGINT(20) NULL DEFAULT 0 AFTER `desp`;

ALTER TABLE `mbom_part_acq` 
ADD COLUMN `status_idx` TINYINT(4) NULL DEFAULT 0 AFTER `part_pin`,
ADD COLUMN `publish_time` BIGINT(20) NULL DEFAULT 0 AFTER `type_idx`;

/* data migration */
-- update `mbom_part_acq` set status_idx = 2;

-- 0.1.6 -> 0.1.7
ALTER TABLE `mbom_part_acq` 
ADD COLUMN `ref_unit_cost` double DEFAULT 0 AFTER `publish_time`;

-- mh_sp8
-- mh_pc
