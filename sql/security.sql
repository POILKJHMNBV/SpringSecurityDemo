-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
CREATE TABLE `tb_user`
(
    `id`          int(11)                                                   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '用户名',
    `password`    varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '密码',
    `nickname`    varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '昵称',
    `sex`         enum ('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '性别',
    `tel`         char(11) CHARACTER SET utf8 COLLATE utf8_general_ci       NULL     DEFAULT NULL COMMENT '手机号码',
    `email`       varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '邮箱',
    `hire_date`   date                                                      NULL     DEFAULT NULL COMMENT '入职日期',
    `role`        json                                                      NOT NULL COMMENT '角色',
    `status`      tinyint(4)                                                NOT NULL COMMENT '状态 1-在职 2-离职',
    `create_time` datetime                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `unq_username` (`username`) USING BTREE,
    INDEX `unq_email` (`email`) USING BTREE,
    INDEX `idx_status` (`status`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户表'
  ROW_FORMAT = DYNAMIC;
-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'kokomi', '{bcrypt}$2a$10$AjgkSP46MI.1iELJNEcWnu6adD5Zf/8e4Q/WF5pDoxCCP10GpbIqK', '深海舌鲆鱼', '女', '13312345678', 'kokomi@qq.com', '2020-08-10', '[2]',  1, '2020-07-03 20:10:23');
INSERT INTO `tb_user` VALUES (2, 'miko', '{noop}123456', '玲珑油豆腐', '女', '17590435098', 'miko@qq.com', '2021-09-09', '[1]',  1, '2020-07-03 20:10:23');
INSERT INTO `tb_user` VALUES (3, 'ayaka', '$2a$10$GBHfYA6zs2Gf5GJSut0beOPyPG4o1NWy1sJ5aN5m6WgnqPqPWyD4m', '白鹭公主', '女', '13590435098', 'ayaka@qq.com', '2020-09-09', '[1]',  1, '2020-06-03 10:10:23');
INSERT INTO `tb_user` VALUES (4, 'furina', '$2a$10$I7xxRCt.r4YsxQksbq7PcOlXWdSMNzh5htLMZuuUZNrgxVHPnnp2K', '芙卡洛斯', '女', '15590435098', 'furina@qq.com', '2021-07-09', '[2]',  1, '2020-02-01 21:10:23');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
CREATE TABLE `tb_role`
(
    `id`                  int(10) UNSIGNED                                        NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name`           varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
    `permissions`         json                                                    NOT NULL COMMENT '权限集合',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `unq_role_name` (`role_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色表'
  ROW_FORMAT = DYNAMIC;
-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'admin', '[1, 2]');
INSERT INTO `tb_role` VALUES (2, 'user', '[2]');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
CREATE TABLE `tb_permission`
(
    `id`              int(10) UNSIGNED                                        NOT NULL AUTO_INCREMENT COMMENT '主键',
    `permission_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `unq_permission` (`permission_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;
-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, 'USER:ENTER');
INSERT INTO `tb_permission` VALUES (2, 'USER:SELECT');