BEGIN;
CREATE TABLE IF NOT EXISTS `message_template` (
`id` int(11) AUTO_INCREMENT NOT NULL COMMENT '自增主键，模板ID',
`title` varchar(128) NOT NULL COMMENT '站内信标题',
`content` text NOT NULL COMMENT '模板内容',
`type` tinyint(2) DEFAULT 0 NOT NULL COMMENT '站内信类型，1-系统通知，2-人工通知(全体)，3-人工通知(游戏)，4-人工通知(自定义)，5-人工通知(文件)',
`status` tinyint(2) DEFAULT 0 NOT NULL COMMENT '模板状态，-1，删除状态，0-编辑状态，1-待发送，2-发送中，3-已发送',
`system_type` tinyint(2) DEFAULT 0 NOT NULL COMMENT '系统通知类型',
`selected` tinyint(2) DEFAULT 0 NOT NULL COMMENT '系统通知是否选中，0-未选中，1-选中',
`send_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '站内信发送时间',
`worker_id` varchar(32) NOT NULL COMMENT '运营人员工号',
`app_ids` varchar(255) COMMENT '通知的游戏列表',
`created_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
`updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
INDEX `idx_type_status_send_time` (`type`, `status`, `send_time`) USING BTREE,
INDEX `idx_system_type` (`system_type`) USING BTREE,
INDEX `idx_created_time` (`created_time`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8 COLLATE=utf8_bin COMMENT='站内信模板表';
COMMIT;
