/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : nuaacempos

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-01-07 16:17:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_alarm
-- ----------------------------
DROP TABLE IF EXISTS `t_alarm`;
CREATE TABLE `t_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_time` datetime DEFAULT NULL,
  `consensus_id` int(11) DEFAULT NULL,
  `send_state` datetime DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k0oc6pm60cipsxeuv0pxti18u` (`task_id`),
  CONSTRAINT `FK_k0oc6pm60cipsxeuv0pxti18u` FOREIGN KEY (`task_id`) REFERENCES `t_alarm_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_alarm_task
-- ----------------------------
DROP TABLE IF EXISTS `t_alarm_task`;
CREATE TABLE `t_alarm_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_cycle` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `task_state` int(11) DEFAULT NULL,
  `threshold_value` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ioihiernjewfph2x4w99intmf` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_analysis_report
-- ----------------------------
DROP TABLE IF EXISTS `t_analysis_report`;
CREATE TABLE `t_analysis_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `save_path` varchar(200) DEFAULT NULL,
  `keyword_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1v1xu8n960d6y8vh810ofgnpr` (`keyword_id`),
  CONSTRAINT `t_analysis_report_ibfk_1` FOREIGN KEY (`keyword_id`) REFERENCES `t_keyword` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_collect_task
-- ----------------------------
DROP TABLE IF EXISTS `t_collect_task`;
CREATE TABLE `t_collect_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `name` int(11) DEFAULT NULL,
  `pause_time` datetime DEFAULT NULL,
  `resume_time` datetime DEFAULT NULL,
  `task_state` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d8vr823fj1e3ne5ay8f21hokx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_collect_website
-- ----------------------------
DROP TABLE IF EXISTS `t_collect_website`;
CREATE TABLE `t_collect_website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `isPublic` bit(1) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agree_num` int(11) DEFAULT NULL,
  `content` varchar(400) DEFAULT NULL,
  `degree_num` int(11) DEFAULT NULL,
  `nick_name` varchar(30) DEFAULT NULL,
  `publsih_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_consensus
-- ----------------------------
DROP TABLE IF EXISTS `t_consensus`;
CREATE TABLE `t_consensus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(300) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `summary` varchar(500) DEFAULT NULL,
  `from_website` varchar(50) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `collect_time` datetime DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `collect_website_id` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_16alfnei8tjyqxt84h1eo1fx7` (`collect_website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2731 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_consensus_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_consensus_detail`;
CREATE TABLE `t_consensus_detail` (
  `consensus_id` int(11) NOT NULL,
  `div_id` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `cmt_uid` varchar(50) DEFAULT NULL,
  `sharp_topic` varchar(300) DEFAULT NULL,
  `blank_title` varchar(300) DEFAULT NULL,
  `ori_url` varchar(255) DEFAULT NULL,
  `forward_reason` varchar(255) DEFAULT NULL,
  `praise_num` int(11) DEFAULT NULL,
  `forward_num` int(11) DEFAULT NULL,
  `cmt_num` int(11) DEFAULT NULL,
  `emotion_pole` int(11) DEFAULT NULL,
  `impact_value` int(11) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`consensus_id`),
  UNIQUE KEY `div_id` (`div_id`),
  CONSTRAINT `t_consensus_detail_ibfk_1` FOREIGN KEY (`consensus_id`) REFERENCES `t_consensus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_consensus_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_consensus_keyword`;
CREATE TABLE `t_consensus_keyword` (
  `consensus_id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword_id` int(11) NOT NULL,
  PRIMARY KEY (`consensus_id`,`keyword_id`),
  KEY `FK_ns60ya8921qvjth9jtawgy4ja` (`consensus_id`),
  KEY `FK_6alorwsthapfjstl29etv3w2v` (`keyword_id`),
  CONSTRAINT `FK_6alorwsthapfjstl29etv3w2v` FOREIGN KEY (`keyword_id`) REFERENCES `t_keyword` (`id`),
  CONSTRAINT `FK_ns60ya8921qvjth9jtawgy4ja` FOREIGN KEY (`consensus_id`) REFERENCES `t_consensus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_consensus_tagword
-- ----------------------------
DROP TABLE IF EXISTS `t_consensus_tagword`;
CREATE TABLE `t_consensus_tagword` (
  `consensus_id` int(11) NOT NULL AUTO_INCREMENT,
  `tagword_id` int(11) NOT NULL,
  PRIMARY KEY (`consensus_id`,`tagword_id`),
  KEY `FK_6ogc80vltncyckmy2ofjlonkw` (`consensus_id`),
  KEY `FK_38f5uosekm0saytngtfejbkqd` (`tagword_id`),
  CONSTRAINT `FK_38f5uosekm0saytngtfejbkqd` FOREIGN KEY (`tagword_id`) REFERENCES `t_tagword` (`id`),
  CONSTRAINT `FK_6ogc80vltncyckmy2ofjlonkw` FOREIGN KEY (`consensus_id`) REFERENCES `t_consensus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_feed
-- ----------------------------
DROP TABLE IF EXISTS `t_feed`;
CREATE TABLE `t_feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `feed_time` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `keyword` varchar(20) DEFAULT NULL,
  `update_num` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_2b5k33iojbchwfroyk7dbq5f8` (`subject_id`),
  CONSTRAINT `FK_2b5k33iojbchwfroyk7dbq5f8` FOREIGN KEY (`subject_id`) REFERENCES `t_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `subject_name` varchar(50) DEFAULT NULL,
  `subject_desc` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_num` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_subject_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_subject_website
-- ----------------------------
DROP TABLE IF EXISTS `t_subject_website`;
CREATE TABLE `t_subject_website` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`subject_id`,`website_id`),
  KEY `website_id` (`website_id`),
  CONSTRAINT `t_subject_website_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `t_subject` (`id`),
  CONSTRAINT `t_subject_website_ibfk_2` FOREIGN KEY (`website_id`) REFERENCES `t_website` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tagword
-- ----------------------------
DROP TABLE IF EXISTS `t_tagword`;
CREATE TABLE `t_tagword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `del_state` int(11) DEFAULT NULL,
  `pole_id` int(11) DEFAULT NULL,
  `pole_value` int(11) DEFAULT NULL,
  `word` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `mobile_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `last_sign_in_ip` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_sign_in_time` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_website
-- ----------------------------
DROP TABLE IF EXISTS `t_website`;
CREATE TABLE `t_website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `collect_website_id` int(11) DEFAULT NULL,
  `website_name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `updateNum` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `isPublic` bit(1) DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  `version` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_5hjxrjr1lqo6n0ca32aqstje0` (`user_id`),
  KEY `collect_website_id` (`collect_website_id`),
  CONSTRAINT `FK_5hjxrjr1lqo6n0ca32aqstje0` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_website_ibfk_1` FOREIGN KEY (`collect_website_id`) REFERENCES `t_collect_website` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_weibo_user
-- ----------------------------
DROP TABLE IF EXISTS `t_weibo_user`;
CREATE TABLE `t_weibo_user` (
  `uid` varchar(20) NOT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
