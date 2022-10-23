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

CREATE TABLE `mbom_part` (
  `uid` varchar(45) NOT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
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
