-- auto Generated on 2019-02-12 11:35:59 
-- DROP TABLE IF EXISTS `problems`; 
CREATE TABLE problems(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `problem_title` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目标题',
    `problem_content` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目内容',
    `problem_details` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目描述',
    `problem_option` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目选项',
    `problem_key` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目答案',
    `key_analysis` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目答案分析',
    `is_single_election` INTEGER(12) NOT NULL DEFAULT -1 COMMENT '是否单选题',
    `problem_from` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目来源',
    `facility_value` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目难易度',
    `problem_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '题目类型',
    `grade` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '年级',
    `subject` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '科目',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'problems';
