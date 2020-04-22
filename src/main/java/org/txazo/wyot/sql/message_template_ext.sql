BEGIN;
CREATE TABLE IF NOT EXISTS `message_template_ext` (
`id` int(11) AUTO_INCREMENT NOT NULL COMMENT '自增主键',
`template_id` int(11) NOT NULL COMMENT '模板id',
`send_status` tinyint(2) DEFAULT 0 NOT NULL COMMENT '发送状态，0-未开始，1-发送中，2-发送完成',
`receiver_list` mediumtext COMMENT '接收者名单',
`receiver_count` int(11) DEFAULT 0 NOT NULL COMMENT '接收者名单数量',
`receiver_success_count` int(11) DEFAULT 0 NOT NULL COMMENT '已成功发送的接收者名单数量',
`created_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
`updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_template_id` (`template_id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8 COLLATE=utf8_bin COMMENT='站内信模板扩展表';
COMMIT;
