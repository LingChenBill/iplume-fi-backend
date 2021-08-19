-- iplume-fi 数据库
drop DATABASE iplume_fi;
CREATE DATABASE iplume_fi character set utf8;

use iplume_fi;

-- 用户表
CREATE TABLE `fi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `login_email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮件名称',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `token` varchar(256) NOT NULL DEFAULT '' COMMENT '给用户生成的 token',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_email` (`login_email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 权限表.
CREATE TABLE `fi_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '权限名称',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- INSERT INTO `fi_authority` (`id`, `name`, `create_time`, `update_time`) VALUES (1, 'ROLE_USER', '2021-08-19 20:29:01', '2021-08-19 20:29:01');
-- INSERT INTO `fi_authority` (`id`, `name`, `create_time`, `update_time`) VALUES (2, 'ROLE_ADMIN', '2021-08-19 20:29:01', '2021-08-19 20:29:01');


-- 用户权限表.
drop table `fi_user_authority`;

CREATE TABLE `fi_user_authority` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`user_id`, `authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- INSERT INTO `fi_user_authority` (`user_id`, `authority_id`, `create_time`, `update_time`) VALUES (1, 1, '2021-08-19 20:29:01', '2021-08-19 20:29:01');
-- INSERT INTO `fi_user_authority` (`user_id`, `authority_id`, `create_time`, `update_time`) VALUES (2, 1, '2021-08-19 20:29:01', '2021-08-19 20:29:01');
-- INSERT INTO `fi_user_authority` (`user_id`, `authority_id`, `create_time`, `update_time`) VALUES (2, 2, '2021-08-19 20:29:01', '2021-08-19 20:29:01');