CREATE TABLE `mbom_pars_part` (
  `uid` varchar(45) NOT NULL,
  `pars_uid` varchar(45) DEFAULT NULL,
  `assign_part` tinyint(4) DEFAULT NULL,
  `part_uid` varchar(45) DEFAULT NULL,
  `part_pin` varchar(45) DEFAULT NULL,
  `part_req_qty` double DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_pars_proc` (
  `uid` varchar(45) NOT NULL,
  `pars_uid` varchar(45) DEFAULT NULL,
  `seq` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
  `assign_proc` tinyint(4) DEFAULT NULL,
  `proc_uid` varchar(45) DEFAULT NULL,
  `proc_id` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part` (
  `uid` varchar(45) NOT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_acq` (
  `uid` varchar(45) NOT NULL,
  `part_uid` varchar(45) DEFAULT NULL,
  `part_pin` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type_idx` tinyint(4) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_acq_r_s` (
  `uid` varchar(45) NOT NULL,
  `part_acq_uid` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_cfg_conj` (
  `uid` varchar(45) NOT NULL,
  `part_cfg_uid` varchar(45) DEFAULT NULL,
  `part_acq_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_cfg` (
  `uid` varchar(45) NOT NULL,
  `root_part_uid` varchar(45) DEFAULT NULL,
  `root_part_pin` varchar(45) DEFAULT NULL,
  `status_idx` tinyint(4) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_mod_item` (
  `uid` varchar(45) NOT NULL,
  `prod_mod_uid` varchar(45) DEFAULT NULL,
  `prod_ctl_uid` varchar(45) DEFAULT NULL,
  `part_cfg_assigned` tinyint(4) DEFAULT NULL,
  `part_cfg_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_mod` (
  `uid` varchar(45) NOT NULL,
  `prod_uid` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_ctl_part_cfg_conj` (
  `uid` varchar(45) NOT NULL,
  `prod_ctl_uid` varchar(45) DEFAULT NULL,
  `part_cfg_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod_ctl` (
  `uid` varchar(45) NOT NULL,
  `id` varchar(45) DEFAULT NULL,
  `lv` tinyint(4) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `req` tinyint(4) DEFAULT NULL,
  `parent_uid` varchar(45) DEFAULT NULL,
  `parent_id` varchar(45) DEFAULT NULL,
  `prod_uid` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_prod` (
  `uid` varchar(45) NOT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `sys_attr` (
  `uid` varchar(45) NOT NULL,
  `type_idx` tinyint(4) DEFAULT NULL,
  `attr_key` varchar(45) DEFAULT NULL,
  `attr_value` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `system_seq` (
  `item_id` varchar(45) NOT NULL,
  `current_num` bigint(20) DEFAULT NULL,
  `last_num` bigint(20) DEFAULT NULL,
  `max_num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ;
