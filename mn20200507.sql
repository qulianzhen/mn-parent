/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql5.6
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : localhost:3306
 Source Schema         : mn

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : 65001

 Date: 07/05/2020 16:02:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mn_article
-- ----------------------------
DROP TABLE IF EXISTS `mn_article`;
CREATE TABLE `mn_article`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `AC_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文章标题',
  `AC_DESC` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章描述',
  `AC_TYPE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文章分类',
  `AC_CONTENT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `AC_SORT` int(3) NOT NULL DEFAULT 0 COMMENT '序号',
  `AC_STATUS` int(3) NOT NULL DEFAULT 0 COMMENT '文章状态[0未发布 1已发布]',
  `AC_ENABLE` int(3) NOT NULL DEFAULT 0 COMMENT '是否有效[0有效  1无效]',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `PUBLISH_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_article
-- ----------------------------
INSERT INTO `mn_article` VALUES (1, '测试1', '测试描述', '1', '<p>							</p><p><br/></p><p><img src=\"http://localhost:8080/mn/resources/plugins/utf8-jsp/dialogs/emotion/images/jx2/j_0025.gif\"/>你好，世界！！！！<img src=\"http://localhost:8080/mn//ueditor/jsp/upload/image/20161211/1481447095388081752.gif\" title=\"1481447095388081752.gif\" alt=\"新玩具.gif\"/></p><p><br/></p><p>							</p>', 1, 1, 0, '2016-12-13 00:45:07', '2016-12-13 23:41:47');
INSERT INTO `mn_article` VALUES (2, '不用手机也能解锁 ofo车锁破解技巧在小学生中流传', '不用手机也能解锁 ofo车锁破解技巧在小学生中流传', '1', '<p class=\"detailPic\" style=\"margin: 0px auto 10px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: center; word-wrap: break-word; word-break: normal;\"><img src=\"http://localhost:8080/mn//ueditor/jsp/upload/image/20170329/1490780002877073931.jpg\" alt=\"\"/></p><p class=\"picIntro\" style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 0px; font-size: 14px; text-align: center; word-wrap: break-word; word-break: normal; font-family: 楷体_gb2312, 楷体;\"><span style=\"font-family: 楷体_gb2312, 楷体; font-size: 14px;\">共享单车</span></p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">3月26日，一位未满12周岁的儿童因为骑共享单车ofo，在上海浙江北路、天潼路路口被大客车碾压，送医后抢救无效而宣布死亡。共享单车是不让未满12周岁的孩子注册使用的，那个孩子是如何骑上共享单车的还不得而知，具体情况正在调查。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">让人担忧的是，现在未满12周岁的小孩骑行共享单车的情况越来越多。有网友专门拍摄了小孩使用ofo的种种视频：骑着共享单车晃晃悠悠出现在车流附近；不依靠手机就将ofo轻松解锁……</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">虽然《中华人民共和国道路交通安全法实施条例》第72条规定明确：驾驶自行车、三轮车必须年满12周岁。但单车企业防范儿童骑车方面明显存在漏洞。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\"><strong style=\"font-style: normal; font-weight: bold;\">驾驶自行车须满12周岁</strong></p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">3月26日当天，网友“一定说说才行了”发帖称：“饭后路过天潼路浙江北路路口，一个孩子和ofo车子被大客车碾压，孩子被救出，不动了。”记者从静安警方了解到，事故时，肇事大巴车正沿着天潼路由东往西行驶到浙江北路路口，在左转弯时撞到这名骑着ofo单车的男孩。事发后，公安、消防部门随后赶到现场将男孩救出，并且将男孩及时送医。但经送院救治无效，男孩不幸死亡。另据知情人透露，事发时小孩与小黄车一起被卡在右前轮胎，而男孩今年上小学4年级，不满12周岁。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">事发后不久，ofo派人跟进并处理此事，同时第一时间同警方取得联系，留下联系方式。ofo相关工作人员表示，不管对于事情本身还是孩子和家长，ofo都深表遗憾。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">根据《中华人民共和国道路交通安全法实施条例》第72条规定，在道路上驾驶自行车、三轮车、电动自行车、残疾人机动轮椅车应当遵守多项规定，其中第一项就明确表示，驾驶自行车、三轮车必须年满12周岁。该事故发生后，或为上海首例不满12岁未成年人使用共享单车致死事件。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">针对此事，同济大学法学院副教授刘春彦表示，此案本质上是一个普通的交通肇事案件，应由交警进行责任认定。但是儿童是否骑车应由监护人决定，并承担责任。“道路交通法及机动车交通事故责任强制保险条例、侵权责任法都可以提供法律方案。”如果单车公司在车辆设计、生产、维护等方面存在问题，例如刹车失灵，那单车公司就应相应的侵权承担责任。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\"><strong style=\"font-style: normal; font-weight: bold;\">儿童骑共享单车很常见</strong></p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">早在本学期开学前，市教委就再次明确要求，不满12周岁学生不准骑自行车。各校也已经按照市教委通知，明确向学生和家长发出通知，严禁12周岁以下孩子骑车，有的学校还有专门的交通安全宣讲。悲剧发生后，不少学校也紧急在家长群再次强调了相关规定。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">虽然学校也在管理，但是不满12周岁的儿童违规骑共享单车上路的情况不在少数。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">“摩拜和ofo我都骑过。”本市某小学四年级男生小沈说，自己已经骑了半年的共享单车了，“基本在周末骑，比如要去上培训班或者和小伙伴一起出来玩。”对此，有家长表示，其实在几个月之前，学校就已经给每个家长发过书面通知，明确12周岁以下的孩子不可以骑单车上路，还请家长提交相关的安全通知签收回执。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\"><strong style=\"font-style: normal; font-weight: bold;\">儿童可轻易打开ofo车锁</strong></p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">调查发现，儿童能违规骑行ofo，和ofo车锁“容易打开”有很大关系。共享单车都是实名制，昨日记者尝试用一位未满12周岁的儿童身份证号在ofo平台注册，未满12周岁不能骑共享单车的提示出现在页面上。但在使用环节，ofo开锁只要密码即可。实名验证存在漏洞。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">小沈说，平时和小伙伴掌握了一些“用车技巧”，比如“ofo的密码是不变的，只要记住密码，平时把车停在稍微偏僻的角落，第二天找到再骑，不用妈妈的手机也可以”。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">此前，沪上共享单车志愿者团队“魔族猎人”的发起人庄骥，在接受晨报采访时表示，因为自己是两个孩子的爸爸，因此，他和团队从两三个月前就开始注意孩子骑行共享单车的危险情况，并拍摄和记录下了不少这样的情形。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">在庄骥提供的一段视频中，一名男孩拨弄着一辆ofo 的车锁，他说：“如果你看到锁上的那个（密码）摇动，你就点那个破解密码，如果看不到，就一直按解锁键，就能看到。”此外，几名未成年儿童结伴在路边挑选共享单车骑走的现象也被庄骥一一记录在内。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">有业内人士就曾透露，像ofo这样的机械锁，一旦使用时间较长，车锁上面必有磨损不均匀的痕迹，“很多小孩不需要手机，就可以根据这些痕迹破解密码。”</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\"><strong style=\"font-style: normal; font-weight: bold;\">ofo称智能锁陆续推广</strong></p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">就车锁容易被打开等问题，ofo相关负责人表示，每当用户使用完单车，APP端都会提示用户在用完以后拨乱密码锁。另外，ofo在每个指定区域都会配一个运维师傅，负责修车和车辆摆放，这些师傅会统一着装和戴工牌，如果遇到未成年人骑车他们会及时劝阻，并在上下学高峰期加强学校周边的巡查。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">该负责人还表示，目前ofo 智能锁已经在北京地区投放，以后将陆续推广到全国。ofo在积极配合相关部门出台共享单车的相关行业标准，并对线下共享单车进行网格化运营，每200辆共享单车配备一个运营人员，以保障车辆的正常使用。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\"><strong style=\"font-style: normal; font-weight: bold;\">【专家观点】</strong></p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\"><strong style=\"font-style: normal; font-weight: bold;\">低成本竞争不应成为共享单车发展方向</strong></p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">针对未满12周岁儿童轻易便可破解ofo密码锁一事，不少市民表示，共享单车是否应在技术上进行提升，从而找到更好的预防方式？“从源头上来讲，共享单车公司从技术上控制孩子使用单车是最行之有效的。”庄骥说，“小黄车的锁，从设计上就有‘bug’。一是有的用户使用完小黄车后，密码不拨乱，孩子一辆辆按过来，总会有一辆可以被打开。还有一种，即使是将密码拨乱，在孩子间也流传着这样的解锁秘笈。”</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">技术上到底该如何防范和规避呢？庄骥认为，把“锁”这个问题解决了，这也并非难事，“目前我们团队发现孩子骑行共享单车的案例普遍都存在于配备机械锁的单车上，因为机械锁可以绕开单车公司平台监管。”庄骥说，像电子锁，无论是蓝牙匹配还是二维码扫描解锁，都会大大增加孩子解锁的难度和门槛。“如果用的是机械锁，那这家共享单车公司至少要通过相关的检测标准，证明和保障你使用的锁是不能轻易让小孩打开的。”</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">同济大学经济与管理学院教授诸大建表示，目前共享单车的技术路线有两条，第一就是与现代技术变革相吻合的一套高标准技术，比如摩拜使用的GPS、实名认证，通过技术对一些不符合骑车条件的用户进行防范；另一条就是必须改进的技术路线，目前ofo的低成本竞争不应该成为共享单车的发展方向，必须进行转变。</p><p style=\"margin: 0px 0px 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal;\">刘春彦也认为，单车公司更多需要承担的应该是一种社会责任，单车公司究竟如何真正落实这个实名制？或许还是一个问题。<span class=\"ifengLogo\"><a href=\"http://www.ifeng.com/\" target=\"_blank\" style=\"text-decoration: none; color: rgb(0, 66, 118); font-weight: bold;\"><img src=\"http://localhost:8080/mn//ueditor/jsp/upload/image/20170329/1490780003072018698.png\"/></a></span></p><p><img src=\"http://localhost:8080/mn//ueditor/jsp/upload/image/20170329/1490780003087096332.jpg\"/></p><p class=\"iphone_none\" style=\"margin: 0px; padding: 0px; float: left; width: 322px;\">[责任编辑：张楠 PT012]</p><p><br/></p>', 3, 1, 0, '2017-03-29 17:33:29', '2017-03-29 17:33:38');

-- ----------------------------
-- Table structure for mn_article_type
-- ----------------------------
DROP TABLE IF EXISTS `mn_article_type`;
CREATE TABLE `mn_article_type`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `TYPE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `TYPE_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类编码',
  `TYPE_DESC` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '分类描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_article_type
-- ----------------------------
INSERT INTO `mn_article_type` VALUES (1, '资讯', 'information', '资讯是用户因为及时地获得它并利用它而能够在相对短的时间内给自己带来价值的信息，资讯有时效性和地域性，它必须被消费者利用。并且“提供－使用（阅读或利用）－反馈”之间能够形成一个长期稳定的CS链，具有这些特点的消息才可以称之为资讯。');
INSERT INTO `mn_article_type` VALUES (2, '测试分类', 'testType01', 'ceshi');
INSERT INTO `mn_article_type` VALUES (3, '通知', 'notification', '通知，是运用广泛的知照性公文。用来发布法规、规章，转发上级机关、同级机关和不相隶属机关的公文，批转下级机关的公文，要求下级机关办理某项事务等。通知，一般由标题、主送单位（受文对象）、正文、落款四部分组成。');
INSERT INTO `mn_article_type` VALUES (4, '新闻', 'news', '新闻，也叫消息，是指通过报纸、电台、广播、电视台等媒体途径所传播信息的一种称谓。是记录社会、传播信息、反映时代的一种文体。');
INSERT INTO `mn_article_type` VALUES (5, '公告', 'announcement', '公告，通常是以国家的名义向国内外宣布重大事件，有时也授权新华社以公告形式公开宣布某一事项的有关规定、要求。如公布国家领导人的出国访问，国家领导人的选举结果，洲际导弹、人造卫星的发射等。地方行政机关有时也可用公告');

-- ----------------------------
-- Table structure for mn_business_code
-- ----------------------------
DROP TABLE IF EXISTS `mn_business_code`;
CREATE TABLE `mn_business_code`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `CODE_TAG` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CODE_PREFIXION` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '前缀',
  `CODE_SUFFIX` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '后缀',
  `CURRENT_VAL` int(11) NOT NULL DEFAULT 0 COMMENT '当前值',
  `CODE_TYPE` int(2) NOT NULL DEFAULT 0 COMMENT '流水类型:0无,1按日流水,2按月流水,3按年流水',
  `CODE_LENGTH` int(2) NULL DEFAULT 0 COMMENT '流水号总长度',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `CODE_DESC` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务流水号' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_business_code
-- ----------------------------
INSERT INTO `mn_business_code` VALUES (1, 'EM', 'EM', '', 3, 1, 18, '2017-03-02 00:00:00', '2017-03-24 00:10:49', '员工编号');
INSERT INTO `mn_business_code` VALUES (2, 'JH', 'JH', '', 5, 0, 30, '2017-03-02 23:54:00', '2017-03-24 00:11:15', '进货单据号');
INSERT INTO `mn_business_code` VALUES (3, 'ORDER_CODE', 'YYYY', '', 8, 3, 30, '2017-03-05 01:22:48', '2017-03-05 23:38:31', '订单编号');

-- ----------------------------
-- Table structure for mn_class
-- ----------------------------
DROP TABLE IF EXISTS `mn_class`;
CREATE TABLE `mn_class`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mn_generate_columntrans
-- ----------------------------
DROP TABLE IF EXISTS `mn_generate_columntrans`;
CREATE TABLE `mn_generate_columntrans`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `DB_TYPE` int(2) NOT NULL DEFAULT 1 COMMENT '数据库类型 1mysql 2oracle',
  `COLUMN_TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字段类型',
  `JAVA_TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'javaBean中对应的类型',
  `JDBC_TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'jdbc类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成-字段类型转换信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_generate_columntrans
-- ----------------------------
INSERT INTO `mn_generate_columntrans` VALUES (1, 1, 'varchar', 'java.lang.String', 'VARCHAR');
INSERT INTO `mn_generate_columntrans` VALUES (2, 1, 'char', 'java.lang.String', 'VARCHAR');
INSERT INTO `mn_generate_columntrans` VALUES (3, 1, 'timestamp', 'java.util.Date', 'DATE');
INSERT INTO `mn_generate_columntrans` VALUES (4, 1, 'double', 'java.lang.Double', 'DOUBLE');
INSERT INTO `mn_generate_columntrans` VALUES (5, 1, 'decimal', 'java.math.BigDecimal', 'DECIMAL');
INSERT INTO `mn_generate_columntrans` VALUES (6, 1, 'int', 'java.lang.Integer', 'INTEGER');
INSERT INTO `mn_generate_columntrans` VALUES (7, 1, 'date', 'java.util.Date', 'DATE');
INSERT INTO `mn_generate_columntrans` VALUES (8, 1, 'clob', 'java.lang.String', 'CLOB');

-- ----------------------------
-- Table structure for mn_generate_db_info
-- ----------------------------
DROP TABLE IF EXISTS `mn_generate_db_info`;
CREATE TABLE `mn_generate_db_info`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `DB_TYPE` int(2) NOT NULL DEFAULT 1 COMMENT '数据库类型 1mysql 2oracle',
  `DB_TABLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '表名(目前支持一次一张表)',
  `DB_USER_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '数据库用户名',
  `DB_USER_PWD` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `FTL_PATHS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模板文件本地路径，多个之间以分号分割',
  `COLUMN_SEARCH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '查询字段，多个的话以分号分隔',
  `COLUMN_UPDATE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '需要更新的字段，多个的话以分号分隔',
  `COLUMN_IGNORE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '忽略的字段，多个以分号分隔',
  `TARGET_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '生成代码文件存放路径',
  `DB_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '数据库连接URL',
  `ENTITY_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名',
  `TRANSLATE_TYPE` int(2) NULL DEFAULT 1 COMMENT '转换类型1驼峰2原样',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '上次代码生成信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_generate_db_info
-- ----------------------------
INSERT INTO `mn_generate_db_info` VALUES (1, 1, 'sys_user', 'qlz', 'qlz', 'E:\\test\\src', 'USER_NAME,ISLOCK,CREATE_DATE', 'USER_NAME,PASSWORD', 'LAST_LOGIN_DATE,REMARK', 'E:\\test\\target', 'jdbc:mysql://localhost:3306/mn?useUnicode=true&characterEncoding=utf-8', 'SysUser', 1);

-- ----------------------------
-- Table structure for mn_picture_manage
-- ----------------------------
DROP TABLE IF EXISTS `mn_picture_manage`;
CREATE TABLE `mn_picture_manage`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `PIC_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片标题',
  `PIC_DESC` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片描述',
  `PIC_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片路径',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_picture_manage
-- ----------------------------
INSERT INTO `mn_picture_manage` VALUES (1, '测试图片', '大学时代', 'http://localhost:8080/mn/mnPicUpload/1480003982749http_imgloadCA3B1AS8.jpg', NULL);
INSERT INTO `mn_picture_manage` VALUES (2, '测试11122333', '121', 'http://localhost:8080/mn/mnPicUpload/1480759138976相亲.gif', NULL);

-- ----------------------------
-- Table structure for mn_student
-- ----------------------------
DROP TABLE IF EXISTS `mn_student`;
CREATE TABLE `mn_student`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `GENDER` int(2) NOT NULL DEFAULT 0 COMMENT '0男1女',
  `AGE` int(3) NOT NULL DEFAULT 0 COMMENT '年龄',
  `WEIGHT` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '体重',
  `CLASS_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属班级ID',
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `PHOTO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mn_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `mn_sys_dict`;
CREATE TABLE `mn_sys_dict`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `DICT_TYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类别名',
  `DICT_DESC` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_sys_dict
-- ----------------------------
INSERT INTO `mn_sys_dict` VALUES (1, 'enable', '是否有效', '2016-12-04 14:38:02');
INSERT INTO `mn_sys_dict` VALUES (2, 'publish', '文章是否发布', '2016-12-04 14:15:06');
INSERT INTO `mn_sys_dict` VALUES (3, 'isHave', '有无', '2016-12-01 23:39:23');
INSERT INTO `mn_sys_dict` VALUES (4, 'public_gender', '性别', '2016-11-27 23:25:08');
INSERT INTO `mn_sys_dict` VALUES (5, 'businessCodeType', '流水号类型', '2017-03-02 00:52:35');

-- ----------------------------
-- Table structure for mn_sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `mn_sys_dict_item`;
CREATE TABLE `mn_sys_dict_item`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `DICT_TYPE_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '所属类别',
  `QUOTE_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '引用名',
  `ITEM_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '条目名字',
  `ITEM_VALUE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '条目值',
  `ITEM_ORDER` int(3) NULL DEFAULT 0 COMMENT '序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典条目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_sys_dict_item
-- ----------------------------
INSERT INTO `mn_sys_dict_item` VALUES (1, '3', 'have', '有', '1', 1);
INSERT INTO `mn_sys_dict_item` VALUES (2, '1', 'yes', '有效', '0', 1);
INSERT INTO `mn_sys_dict_item` VALUES (3, '5', 'byMonth', '按月流水', '2', 3);
INSERT INTO `mn_sys_dict_item` VALUES (4, '5', 'byDay', '按日流水', '1', 2);
INSERT INTO `mn_sys_dict_item` VALUES (5, '1', 'no', '无效', '1', 2);
INSERT INTO `mn_sys_dict_item` VALUES (6, '2', 'no', '未发布', '0', 1);
INSERT INTO `mn_sys_dict_item` VALUES (7, '5', 'no', '无', '0', 1);
INSERT INTO `mn_sys_dict_item` VALUES (8, '2', 'yes', '已发布', '1', 2);
INSERT INTO `mn_sys_dict_item` VALUES (9, '4', 'boy', '男', '1', 1);
INSERT INTO `mn_sys_dict_item` VALUES (10, '4', 'girl', '女', '2', 2);
INSERT INTO `mn_sys_dict_item` VALUES (11, '3', 'noHave', '无', '2', 2);
INSERT INTO `mn_sys_dict_item` VALUES (12, '5', 'byYear', '按年流水', '3', 4);

-- ----------------------------
-- Table structure for mn_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `mn_sys_menu`;
CREATE TABLE `mn_sys_menu`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `MENU_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `MENU_URL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `MENU_ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `MENU_LEVEL` int(2) NULL DEFAULT 1 COMMENT '菜单级别',
  `MENU_ORDER` int(2) NULL DEFAULT 1 COMMENT '显示序号',
  `MENU_ENABLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效：0无效，1有效',
  `IS_LEFT` int(2) NULL DEFAULT 0 COMMENT '是否是叶子节点：0否，1是',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `IS_DELETED` int(1) NULL DEFAULT 0 COMMENT '是否删除：0不删除，1删除',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_sys_menu
-- ----------------------------
INSERT INTO `mn_sys_menu` VALUES (1, '0', '业务模块', '', 'icon-th-large', 1, 1, '1', 0, '2016-08-17 00:00:00', '2016-10-04 11:46:46', 0);
INSERT INTO `mn_sys_menu` VALUES (2, '0', '个人中心', '', 'icon-user', 1, 2, '1', 0, NULL, '2016-10-04 11:46:39', 0);
INSERT INTO `mn_sys_menu` VALUES (3, '0', '系统设置', '', 'icon-cogs', 1, 3, '1', 0, NULL, '2016-10-04 11:46:36', 0);

-- ----------------------------
-- Table structure for mn_sys_permit
-- ----------------------------
DROP TABLE IF EXISTS `mn_sys_permit`;
CREATE TABLE `mn_sys_permit`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `PERMIT_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `PERMIT_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限URL',
  `PERMIT_TYPE` int(1) NOT NULL DEFAULT 0 COMMENT '0操作url 1页面元素',
  `PARENT_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上级ID',
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `IS_DELETED` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1删除',
  `PERMIT_ORDER` int(3) NOT NULL DEFAULT 0 COMMENT '显示序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mn_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `mn_sys_role`;
CREATE TABLE `mn_sys_role`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `ROLE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `ROLE_ENABLE` int(1) NOT NULL DEFAULT 0 COMMENT '是否可用 0可用 1 不可用',
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `IS_DELETED` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1删除',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_sys_role
-- ----------------------------
INSERT INTO `mn_sys_role` VALUES (1, '角色100', 0, '备足1', NULL, '2016-10-06 16:46:44', 0);
INSERT INTO `mn_sys_role` VALUES (2, '菜单管理员', 0, '只能管理菜单', NULL, NULL, 0);
INSERT INTO `mn_sys_role` VALUES (3, '角色002', 0, '备注002', NULL, '2016-10-03 18:34:15', 0);

-- ----------------------------
-- Table structure for mn_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `mn_sys_user`;
CREATE TABLE `mn_sys_user`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `USER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名',
  `NICK_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `EMPLOYYER_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应员工的ID',
  `ISLOCK` int(1) NOT NULL DEFAULT 0 COMMENT '是否锁定 0否 1是',
  `PHOTO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `USER_STATUS` int(1) NULL DEFAULT NULL COMMENT '用户状态，备用',
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `IS_DELETED` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1删除',
  `LAST_LOGIN_DATE` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_sys_user
-- ----------------------------
INSERT INTO `mn_sys_user` VALUES (1, 'qlz', '风居住の街道', 'ecafb14eade37065cfa968520d99f6a2', '1', 0, 'xxxxxx', 1, '无', '2016-09-13 23:13:47', '2020-04-20 13:17:53', 0, '2017-01-04 00:18:03');

-- ----------------------------
-- Table structure for mn_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mn_sys_user_role`;
CREATE TABLE `mn_sys_user_role`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色对应表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mn_sys_user_role
-- ----------------------------
INSERT INTO `mn_sys_user_role` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for sys_role_permit
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permit`;
CREATE TABLE `sys_role_permit`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  `PREMIT_ID` bigint(20) NOT NULL COMMENT '权限ID',
  `PERMIT_TYPE` int(1) NOT NULL COMMENT '权限类型：0操作url；1页面元素 ；2菜单URL',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-权限对应表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
