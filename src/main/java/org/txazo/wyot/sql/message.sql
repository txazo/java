BEGIN;
CREATE TABLE IF NOT EXISTS `message_000` (
`id` int(11) AUTO_INCREMENT NOT NULL COMMENT '自增主键',
`user_id` int(11) NOT NULL COMMENT '接收者id',
`template_id` int(11) NOT NULL COMMENT '模板id',
`params` varchar(255) NOT NULL COMMENT '模板替换参数，json对象',
`message_type` tinyint(2) DEFAULT 0 NOT NULL COMMENT '站内信通知类型，1-系统通知，2-人工通知',
`type` tinyint(2) DEFAULT 0 NOT NULL COMMENT '站内信模板类型，1-系统通知，2-人工通知(全体)，3-人工通知(游戏)，4-人工通知(自定义)',
`readed` tinyint(2) DEFAULT 0 NOT NULL COMMENT '是否已读，0-未读，1-已读',
`deleted` tinyint(2) DEFAULT 0 NOT NULL COMMENT '是否删除，0-未删除，1-已删除',
`send_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '发送时间',
`created_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
`updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
INDEX `idx_user_id_template_id` (`user_id`, `template_id`) USING BTREE,
INDEX `idx_user_id_deleted_readed_message_type` (`user_id`, `deleted`, `readed`, `message_type`) USING BTREE,
INDEX `idx_user_id_deleted_send_time` (`user_id`, `deleted`, `send_time`) USING BTREE,
INDEX `idx_user_id_deleted_message_type_send_time` (`user_id`, `deleted`, `message_type`, `send_time`) USING BTREE,
INDEX `idx_created_time` (`created_time`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8 COLLATE=utf8_bin COMMENT='站内信表';

CREATE TABLE `message_001` LIKE `message_000`;
CREATE TABLE `message_002` LIKE `message_000`;
CREATE TABLE `message_003` LIKE `message_000`;
CREATE TABLE `message_004` LIKE `message_000`;
CREATE TABLE `message_005` LIKE `message_000`;
CREATE TABLE `message_006` LIKE `message_000`;
CREATE TABLE `message_007` LIKE `message_000`;
CREATE TABLE `message_008` LIKE `message_000`;
CREATE TABLE `message_009` LIKE `message_000`;
CREATE TABLE `message_010` LIKE `message_000`;
CREATE TABLE `message_011` LIKE `message_000`;
CREATE TABLE `message_012` LIKE `message_000`;
CREATE TABLE `message_013` LIKE `message_000`;
CREATE TABLE `message_014` LIKE `message_000`;
CREATE TABLE `message_015` LIKE `message_000`;
CREATE TABLE `message_016` LIKE `message_000`;
CREATE TABLE `message_017` LIKE `message_000`;
CREATE TABLE `message_018` LIKE `message_000`;
CREATE TABLE `message_019` LIKE `message_000`;
CREATE TABLE `message_020` LIKE `message_000`;
CREATE TABLE `message_021` LIKE `message_000`;
CREATE TABLE `message_022` LIKE `message_000`;
CREATE TABLE `message_023` LIKE `message_000`;
CREATE TABLE `message_024` LIKE `message_000`;
CREATE TABLE `message_025` LIKE `message_000`;
CREATE TABLE `message_026` LIKE `message_000`;
CREATE TABLE `message_027` LIKE `message_000`;
CREATE TABLE `message_028` LIKE `message_000`;
CREATE TABLE `message_029` LIKE `message_000`;
CREATE TABLE `message_030` LIKE `message_000`;
CREATE TABLE `message_031` LIKE `message_000`;
CREATE TABLE `message_032` LIKE `message_000`;
CREATE TABLE `message_033` LIKE `message_000`;
CREATE TABLE `message_034` LIKE `message_000`;
CREATE TABLE `message_035` LIKE `message_000`;
CREATE TABLE `message_036` LIKE `message_000`;
CREATE TABLE `message_037` LIKE `message_000`;
CREATE TABLE `message_038` LIKE `message_000`;
CREATE TABLE `message_039` LIKE `message_000`;
CREATE TABLE `message_040` LIKE `message_000`;
CREATE TABLE `message_041` LIKE `message_000`;
CREATE TABLE `message_042` LIKE `message_000`;
CREATE TABLE `message_043` LIKE `message_000`;
CREATE TABLE `message_044` LIKE `message_000`;
CREATE TABLE `message_045` LIKE `message_000`;
CREATE TABLE `message_046` LIKE `message_000`;
CREATE TABLE `message_047` LIKE `message_000`;
CREATE TABLE `message_048` LIKE `message_000`;
CREATE TABLE `message_049` LIKE `message_000`;
CREATE TABLE `message_050` LIKE `message_000`;
CREATE TABLE `message_051` LIKE `message_000`;
CREATE TABLE `message_052` LIKE `message_000`;
CREATE TABLE `message_053` LIKE `message_000`;
CREATE TABLE `message_054` LIKE `message_000`;
CREATE TABLE `message_055` LIKE `message_000`;
CREATE TABLE `message_056` LIKE `message_000`;
CREATE TABLE `message_057` LIKE `message_000`;
CREATE TABLE `message_058` LIKE `message_000`;
CREATE TABLE `message_059` LIKE `message_000`;
CREATE TABLE `message_060` LIKE `message_000`;
CREATE TABLE `message_061` LIKE `message_000`;
CREATE TABLE `message_062` LIKE `message_000`;
CREATE TABLE `message_063` LIKE `message_000`;
COMMIT;
