BEGIN;
CREATE TABLE IF NOT EXISTS `message_user_config_000` (
`id` int(11) AUTO_INCREMENT NOT NULL COMMENT '自增主键',
`user_id` int(11) NOT NULL COMMENT '用户id，分表依据',
`register_time` datetime COMMENT '用户注册时间',
`last_sync_all_time` datetime COMMENT '上一次同步全体通知时间',
`last_sync_game_time` datetime COMMENT '上一次同步游戏通知时间',
`created_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
`updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8 COLLATE=utf8_bin COMMENT='站内信用户配置表';

CREATE TABLE `message_user_config_001` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_002` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_003` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_004` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_005` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_006` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_007` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_008` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_009` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_010` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_011` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_012` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_013` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_014` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_015` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_016` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_017` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_018` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_019` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_020` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_021` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_022` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_023` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_024` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_025` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_026` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_027` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_028` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_029` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_030` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_031` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_032` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_033` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_034` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_035` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_036` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_037` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_038` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_039` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_040` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_041` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_042` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_043` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_044` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_045` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_046` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_047` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_048` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_049` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_050` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_051` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_052` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_053` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_054` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_055` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_056` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_057` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_058` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_059` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_060` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_061` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_062` LIKE `message_user_config_000`;
CREATE TABLE `message_user_config_063` LIKE `message_user_config_000`;
COMMIT;
