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



-- 0.1.7 -> 0.1.8
ALTER TABLE `mbom_part` CHANGE COLUMN `unit_idx` `unit_id` VARCHAR(10) NULL DEFAULT NULL ;

/* data migration */
/*
update `mbom_part` set unit_id = case 
  when unit_id = 10 then 'EAC'
  when unit_id = 21 then 'GRM'
  when unit_id = 31 then 'MMT'
  else unit_id
end;
*/

-- 0.1.8 -> 0.2.0
CREATE TABLE `invt_wrhs_loc` (
  `uid` varchar(45) NOT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_wrhs_bin` (
  `uid` varchar(45) NOT NULL,
  `wl_uid` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_invt_order` (
  `uid` varchar(45) NOT NULL,
  `iosn` varchar(45) DEFAULT NULL,
  `applier_id` varchar(45) DEFAULT NULL,
  `applier_name` varchar(45) DEFAULT NULL,
  `apv_time` bigint DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_invt_order_item` (
  `uid` varchar(45) NOT NULL,
  `io_uid` varchar(45) DEFAULT NULL,
  `mbs_uid` varchar(45) DEFAULT NULL,
  `io_type_idx` tinyint DEFAULT NULL,
  `order_qty` double DEFAULT NULL,
  `order_value` double DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_mstr` (
  `uid` varchar(45) NOT NULL,
  `mano` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `specification` varchar(200) DEFAULT NULL,
  `std_unit_id` varchar(10) DEFAULT NULL,
  `sum_stock_qty` double DEFAULT NULL,
  `sum_stock_value` double DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_inst` (
  `uid` varchar(45) NOT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `misn` varchar(45) DEFAULT NULL,
  `miac_idx` tinyint DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `eff_date` bigint DEFAULT NULL,
  `exp_date` bigint DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_bin_stock` (
  `uid` varchar(45) NOT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `mano` varchar(45) DEFAULT NULL,
  `wrhs_bin_uid` varchar(45) DEFAULT NULL,
  `sum_stock_qty` double DEFAULT NULL,
  `sum_stock_value` double DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_bin_stock_batch` (
  `uid` varchar(45) NOT NULL,
  `mbs_uid` varchar(45) DEFAULT NULL,
  `mi_uid` varchar(45) DEFAULT NULL,
  `stock_qty` double DEFAULT NULL,
  `stock_value` double DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mbsb_stmt` (
  `uid` varchar(45) NOT NULL,
  `mbsb_uid` varchar(45) DEFAULT NULL,
  `ioi_uid` varchar(45) DEFAULT NULL,
  `mbsb_flow_type_idx` tinyint DEFAULT NULL,
  `stmt_qty` double DEFAULT NULL,
  `stmt_value` double DEFAULT NULL,
  `posting_status_idx` tinyint DEFAULT NULL,
  `posting_time` bigint DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

-- mh_sp8


-- 0.2.1 -> 0.2.2
ALTER TABLE `invt_invt_order_item` 
DROP COLUMN `mbs_uid`,
ADD COLUMN `mm_uid` VARCHAR(45) NULL AFTER `io_uid`,
ADD COLUMN `mi_uid` VARCHAR(45) NULL AFTER `mm_uid`,
ADD COLUMN `wrhs_bin_uid` VARCHAR(45) NULL AFTER `mi_uid`;

-- 0.2.2 -> 0.3.0
CREATE TABLE `pu_purch` (
  `uid` varchar(45) NOT NULL,
  `pu_no` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `supplier_name` varchar(45) DEFAULT NULL,
  `supplier_ban` varchar(45) DEFAULT NULL,
  `perf_status_idx` tinyint DEFAULT NULL,
  `perf_time` bigint DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `pu_purch_item` (
  `uid` varchar(45) NOT NULL,
  `purch_uid` varchar(45) DEFAULT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `mm_mano` varchar(45) DEFAULT NULL,
  `mm_name` varchar(45) DEFAULT NULL,
  `mm_specification` varchar(45) DEFAULT NULL,
  `mm_std_unit_id` varchar(10) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `object_create_time` bigint DEFAULT NULL,
  `object_update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

-- mh_pc

