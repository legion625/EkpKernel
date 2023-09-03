CREATE TABLE `invt_invt_order` (
  `uid` varchar(45) NOT NULL,
  `iosn` varchar(45) DEFAULT NULL,
  `status_idx` tinyint(4) DEFAULT NULL,
  `applier_id` varchar(45) DEFAULT NULL,
  `applier_name` varchar(45) DEFAULT NULL,
  `apply_time` bigint(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `apv_time` bigint(20) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_invt_order_item` (
  `uid` varchar(45) NOT NULL,
  `io_uid` varchar(45) DEFAULT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `io_type_idx` tinyint(4) DEFAULT NULL,
  `order_qty` double DEFAULT NULL,
  `order_value` double DEFAULT NULL,
  `mbsb_stmt_created` tinyint(4) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_bin_stock` (
  `uid` varchar(45) NOT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `mano` varchar(45) DEFAULT NULL,
  `wrhs_bin_uid` varchar(45) DEFAULT NULL,
  `sum_stock_qty` double DEFAULT NULL,
  `sum_stock_value` double DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_bin_stock_batch` (
  `uid` varchar(45) NOT NULL,
  `mbs_uid` varchar(45) DEFAULT NULL,
  `mi_uid` varchar(45) DEFAULT NULL,
  `stock_qty` double DEFAULT NULL,
  `stock_value` double DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_inst` (
  `uid` varchar(45) NOT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `misn` varchar(45) DEFAULT NULL,
  `miac_idx` tinyint(4) DEFAULT NULL,
  `miac_src_no` varchar(45) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `eff_date` bigint(20) DEFAULT NULL,
  `exp_date` bigint(20) DEFAULT NULL,
  `src_status_idx` tinyint(4) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mat_inst_src_conj` (
  `uid` varchar(45) NOT NULL,
  `mi_uid` varchar(45) DEFAULT NULL,
  `src_mi_uid` varchar(45) DEFAULT NULL,
  `src_mi_qty` double DEFAULT NULL,
  `src_mi_value` double DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
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
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_mbsb_stmt` (
  `uid` varchar(45) NOT NULL,
  `mbsb_uid` varchar(45) DEFAULT NULL,
  `ioi_uid` varchar(45) DEFAULT NULL,
  `mbsb_flow_type_idx` tinyint(4) DEFAULT NULL,
  `stmt_qty` double DEFAULT NULL,
  `stmt_value` double DEFAULT NULL,
  `posting_status_idx` tinyint(4) DEFAULT NULL,
  `posting_time` bigint(20) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_wrhs_bin` (
  `uid` varchar(45) NOT NULL,
  `wl_uid` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `invt_wrhs_loc` (
  `uid` varchar(45) NOT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

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
  `unit_id` varchar(10) DEFAULT NULL,
  `mm_assigned` tinyint(4) DEFAULT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `mm_mano` varchar(45) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_acq` (
  `uid` varchar(45) NOT NULL,
  `part_uid` varchar(45) DEFAULT NULL,
  `part_pin` varchar(45) DEFAULT NULL,
  `status_idx` tinyint(4) DEFAULT '0',
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type_idx` tinyint(4) DEFAULT NULL,
  `publish_time` bigint(20) DEFAULT '0',
  `ref_unit_cost` double DEFAULT '0',
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mbom_part_acq_r_s` (
  `uid` varchar(45) NOT NULL,
  `part_acq_uid` varchar(45) DEFAULT NULL,
  `seq` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
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
  `publish_time` bigint(20) DEFAULT '0',
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

CREATE TABLE `mbom_prod` (
  `uid` varchar(45) NOT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
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

CREATE TABLE `mbom_prod_ctl_part_cfg_conj` (
  `uid` varchar(45) NOT NULL,
  `prod_ctl_uid` varchar(45) DEFAULT NULL,
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

CREATE TABLE `mf_wo` (
  `uid` varchar(45) NOT NULL,
  `wo_no` varchar(45) DEFAULT NULL,
  `status_idx` tinyint(4) DEFAULT NULL,
  `part_uid` varchar(45) DEFAULT NULL,
  `part_pin` varchar(45) DEFAULT NULL,
  `part_mm_mano` varchar(45) DEFAULT NULL,
  `part_acq_uid` varchar(45) DEFAULT NULL,
  `part_acq_id` varchar(45) DEFAULT NULL,
  `rq_qty` double DEFAULT NULL,
  `start_work_time` bigint(20) DEFAULT NULL,
  `finish_work_time` bigint(20) DEFAULT NULL,
  `over_time` bigint(20) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `mf_wom` (
  `uid` varchar(45) NOT NULL,
  `wo_uid` varchar(45) DEFAULT NULL,
  `wo_no` varchar(45) DEFAULT NULL,
  `mm_uid` varchar(45) DEFAULT NULL,
  `mm_mano` varchar(45) DEFAULT NULL,
  `mm_name` varchar(45) DEFAULT NULL,
  `qty0` double DEFAULT NULL,
  `qty1` double DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ;

CREATE TABLE `pu_purch` (
  `uid` varchar(45) NOT NULL,
  `pu_no` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `supplier_name` varchar(45) DEFAULT NULL,
  `supplier_ban` varchar(45) DEFAULT NULL,
  `perf_status_idx` tinyint(4) DEFAULT NULL,
  `perf_time` bigint(20) DEFAULT NULL,
  `object_create_time` bigint(20) DEFAULT NULL,
  `object_update_time` bigint(20) DEFAULT NULL,
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
