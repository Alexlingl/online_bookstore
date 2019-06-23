/*
Navicat MySQL Data Transfer

Source Server         : msql_database
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2019-06-22 17:11:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('20170001', '111111');

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author` varchar(80) NOT NULL,
  `translator` varchar(80) DEFAULT NULL,
  `publish_id` int(11) NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `introduction` text,
  `language` varchar(10) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `vip_price` decimal(10,2) NOT NULL,
  `pubdate` date DEFAULT NULL,
  `class_id` varchar(10) DEFAULT NULL,
  `pressmark` int(11) DEFAULT NULL,
  `state` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `fk_class` (`class_id`),
  KEY `fk_publish` (`publish_id`),
  CONSTRAINT `fk_class` FOREIGN KEY (`class_id`) REFERENCES `class_info` (`class_id`),
  CONSTRAINT `fk_publish` FOREIGN KEY (`publish_id`) REFERENCES `publish_info` (`publish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50000012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('10000001', '大雪中的山庄', '东野圭吾；林国龙', '小七', '1', '9787530216835', '东野圭吾长篇小说杰作，中文简体首次出版。 一出没有剧本的舞台剧，为什么能让七个演员赌上全部人生.东野圭吾就是有这样过人的本领，能从充满悬念的案子写出荡气回肠的情感，在极其周密曲折的同时写出人性的黑暗与美丽。 一家与外界隔绝的民宿里，七个演员被要求住满四天，接受导演的考验，但不断有人失踪。难道这并非正常排练，而是有人布下陷阱要杀他们。 那时候我开始喜欢上戏剧和音乐，《大雪中的山庄》一书的灵感就来源于此。我相信这次的诡计肯定会让人大吃一惊。——东野圭吾', '中文', '35.00', '30.00', '2017-06-01', 'B01', '13', '11');
INSERT INTO `book_info` VALUES ('10000003', '三生三世 十里桃花', '唐七公子；唐三', '', '1', '9787544138000', '三生三世，她和他，是否注定背负一段纠缠的姻缘？三生三世，她和他，是否终能互许一个生生世世的承诺？', '中文', '26.80', '20.00', '2009-01-06', 'B01', '2', '0');
INSERT INTO `book_info` VALUES ('10000004', '何以笙箫默', '顾漫 ', null, '1', '9787505414709', '一段年少时的爱恋，牵出一生的纠缠。大学时代的赵默笙阳光灿烂，对法学系大才子何以琛一见倾心，开朗直率的她拔足倒追，终于使才气出众的他为她停留驻足。然而，不善表达的他终于使她在一次伤心之下远走他乡……', '中文', '15.00', '14.60', '2007-04-03', 'C', '2', '6');
INSERT INTO `book_info` VALUES ('10000005', '11处特工皇妃', '潇湘冬儿', null, '1', '9787539943893', '《11处特工皇妃(套装上中下册)》内容简介：她是国安局军情十一处惊才绝艳的王牌军师——收集情报、策划部署、进不友好国家布置暗杀任务……她运筹帷幄之中，决胜于千里之外，堪称军情局大厦的定海神针。', '中文', '74.80', '0.00', '2011-05-05', 'O', '2', '17');
INSERT INTO `book_info` VALUES ('10000006', '人类简史', '[以色列] 尤瓦尔·赫拉利 ', null, '1', '9787508647357', '十万年前，地球上至少有六种不同的人\r\n但今日，世界舞台为什么只剩下了我们自己？\r\n从只能啃食虎狼吃剩的残骨的猿人，到跃居食物链顶端的智人，\r\n从雪维洞穴壁上的原始人手印，到阿姆斯壮踩上月球的脚印，\r\n从认知革命、农业革命，到科学革命、生物科技革命，\r\n我们如何登上世界舞台成为万物之灵的？\r\n从公元前1776年的《汉摩拉比法典》，到1776年的美国独立宣言，\r\n从帝国主义、资本主义，到自由主义、消费主义，\r\n从兽欲，到物欲，从兽性、人性，到神性，\r\n我们了解自己吗？我们过得更快乐吗？\r\n我们究竟希望自己得到什么、变成什么？', '英文', '68.00', '0.00', '2014-11-01', 'O', '3', '8');
INSERT INTO `book_info` VALUES ('10000007', '明朝那些事儿（1-9）', '当年明月 ', null, '1', '9787801656087', '《明朝那些事儿》讲述从1344年到1644年，明朝三百年间的历史。作品以史料为基础，以年代和具体人物为主线，运用小说的笔法，对明朝十七帝和其他王公权贵和小人物的命运进行全景展示，尤其对官场政治、战争、帝王心术着墨最多。作品也是一部明朝政治经济制度、人伦道德的演义。', '中文', '358.20', '0.00', '2009-04-06', 'B1', '3', '4');
INSERT INTO `book_info` VALUES ('10000010', '经济学原理（上下）', '[美] 曼昆 ', null, '1', '9787111126768', '此《经济学原理》的第3版把较多篇幅用于应用与政策，较少篇幅用于正规的经济理论。书中主要从供给与需求、企业行为与消费者选择理论、长期经济增长与短期经济波动以及宏观经济政策等角度深入浅出地剖析了经济学家们的世界观。', '英文', '88.00', '0.00', '2003-08-05', 'B02', '5', '15');
INSERT INTO `book_info` VALUES ('50000004', '方向', '马克-安托万·马修 ', null, '1', '9787020125265', '《方向》即便在马修的作品中也算得最独特的：不着一字，尽得风流。原作本无一字，标题只是一个→，出版时才加了个书名Sens——既可以指“方向”，也可以指“意义”。 《方向》没有“字”，但有自己的语言——请读者在尽情释放想象力和独立思考之余，破解作者的密码，听听作者对荒诞的看法。', '中文', '99.80', '0.00', '2017-04-01', 'B1', '13', '8');
INSERT INTO `book_info` VALUES ('50000005', '画的秘密', '马克-安托万·马修 ', null, '1', '9787550265608', '一本关于友情的疗伤图像小说，直击人内心最为隐秘的情感。 一部追寻艺术的纸上悬疑电影，揭示命运宇宙中奇诡的真相。 ★ 《画的秘密》荣获欧洲第二大漫画节“瑞士谢尔漫画节最佳作品奖”。 作者曾两度夺得安古兰国际漫画节重要奖项。 ★ 《画的秘密》是一部罕见的、结合了拼贴、镜像、3D等叙事手法的实验型漫画作品。作者巧妙地调度光线、纬度、时间、记忆，在一个悬念重重又温情治愈的故事中，注入了一个有关命运的哲学议题。', '中文', '60.00', '0.00', '2016-01-01', 'C', '13', '50');
INSERT INTO `book_info` VALUES ('50000007', '造彩虹的人', '东野圭吾 ', null, '1', '9787530216859', '其实每个人身上都会发光，但只有纯粹渴求光芒的人才能看到。 从那一刻起，人生会发生奇妙的转折。 ------------------------------------------------------------------------------------------------------ 功一高中退学后无所事事，加入暴走族消极度日；政史备战高考却无法集中精神，几近崩溃；辉美因家庭不和对生活失去勇气，决定自杀。面对糟糕的人生，他们无所适从，直到一天夜里，一道如同彩虹的光闯进视野。 凝视着那道光，原本几乎要耗尽的气力，源源不断地涌了出来。一切又开始充满希望。打起精神来，不能输。到这儿来呀，快来呀——那道光低声呼唤着。 他们追逐着呼唤，到达一座楼顶，看到一个人正用七彩绚烂的光束演奏出奇妙的旋律。 他们没想到，这一晚看到的彩虹，会让自己的人生彻底转...', '中文', '39.50', '0.00', '2017-06-01', 'C', '13', '15');
INSERT INTO `book_info` VALUES ('50000008', '控方证人', '阿加莎·克里斯蒂 ', null, '1', '9787513325745', '经典同名话剧六十年常演不衰； 比利•怀尔德执导同名电影，获奥斯卡金像奖六项提名！ 阿加莎对神秘事物的向往大约来自于一种女性祖先的遗传，在足不出户的生活里，生出对世界又好奇又恐惧的幻想。 ——王安忆 伦纳德•沃尔被控谋杀富婆艾米丽以图染指其巨额遗产，他却一再表明自己的无辜。伦纳德的妻子是唯一能够证明他无罪的证人，却以控方证人的身份出庭指证其确实犯有谋杀罪。伦纳德几乎陷入绝境，直到一个神秘女人的出现…… 墙上的犬形图案；召唤死亡的收音机；蓝色瓷罐的秘密；一只疯狂的灰猫……十一篇神秘的怪谈，最可怕的不是“幽灵”，而是你心中的魔鬼。', '中文', '35.00', '0.00', '2017-05-01', 'C', '12', '20');
INSERT INTO `book_info` VALUES ('50000009', '少有人走的路', 'M·斯科特·派克 ', null, '1', '9787807023777', '这本书处处透露出沟通与理解的意味，它跨越时代限制，帮助我们探索爱的本质，引导我们过上崭新，宁静而丰富的生活；它帮助我们学习爱，也学习独立；它教诲我们成为更称职的、更有理解心的父母。归根到底，它告诉我们怎样找到真正的自我。 正如开篇所言：人生苦难重重。M·斯科特·派克让我们更加清楚：人生是一场艰辛之旅，心智成熟的旅程相当漫长。但是，他没有让我们感到恐惧，相反，他带领我们去经历一系列艰难乃至痛苦的转变，最终达到自我认知的更高境界。', '中文', '26.00', '0.00', '2007-01-01', 'O', '12', '5');
INSERT INTO `book_info` VALUES ('50000010', '追寻生命的意义', '[奥] 维克多·弗兰克 ', null, '1', '9787501162734', '《追寻生命的意义》是一个人面对巨大的苦难时，用来拯救自己的内在世界，同时也是一个关于每个人存在的价值和能者多劳们生存的社会所应担负职责的思考。本书对于每一个想要了解我们这个时代的人来说，都是一部必不可少的读物。这是一部令人鼓舞的杰作……他是一个不可思议的人，任何人都可以从他无比痛苦的经历中，获得拯救自己的经验……高度推荐。', '中文', '12.00', '0.00', '2003-01-01', 'O', '16', '18');
INSERT INTO `book_info` VALUES ('50000011', '秘密花园', '乔汉娜·贝斯福 ', null, '1', '9787550252585', '欢迎来到秘密花园！ 在这个笔墨编织出的美丽世界中漫步吧 涂上你喜爱的颜色，为花园带来生机和活力 发现隐藏其中的各类小生物，与它们共舞 激活创造力，描绘那些未完成的仙踪秘境 各个年龄段的艺术家和“园丁”都可以来尝试喔！', '中文', '42.00', '0.00', '2015-06-01', 'B02', '18', '20');

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('A', '马克思主义');
INSERT INTO `class_info` VALUES ('B', '哲学');
INSERT INTO `class_info` VALUES ('B0', '哲学理论');
INSERT INTO `class_info` VALUES ('B00', '马克思主义哲学');
INSERT INTO `class_info` VALUES ('B01', '哲学基本问题');
INSERT INTO `class_info` VALUES ('B02', '辩证唯物主义');
INSERT INTO `class_info` VALUES ('B1', '世界哲学');
INSERT INTO `class_info` VALUES ('C', '社会科学总论');
INSERT INTO `class_info` VALUES ('D', '政治法律');
INSERT INTO `class_info` VALUES ('E', '军事');
INSERT INTO `class_info` VALUES ('F', '经济');
INSERT INTO `class_info` VALUES ('G', '文化');
INSERT INTO `class_info` VALUES ('H', '语言');
INSERT INTO `class_info` VALUES ('I', '文学');
INSERT INTO `class_info` VALUES ('J', '艺术');
INSERT INTO `class_info` VALUES ('K', '历史地理');
INSERT INTO `class_info` VALUES ('N', '自然科学总论');
INSERT INTO `class_info` VALUES ('O', ' 数理科学和化学');
INSERT INTO `class_info` VALUES ('P', '天文学、地球科学');
INSERT INTO `class_info` VALUES ('Q', '生物科学');
INSERT INTO `class_info` VALUES ('R', '医药、卫生');
INSERT INTO `class_info` VALUES ('S', '农业科学');
INSERT INTO `class_info` VALUES ('T', '工业技术');
INSERT INTO `class_info` VALUES ('U', '交通运输');
INSERT INTO `class_info` VALUES ('V', '航空、航天');
INSERT INTO `class_info` VALUES ('X', '环境科学');
INSERT INTO `class_info` VALUES ('Z', '综合');

-- ----------------------------
-- Table structure for publish_info
-- ----------------------------
DROP TABLE IF EXISTS `publish_info`;
CREATE TABLE `publish_info` (
  `publish_id` int(11) NOT NULL AUTO_INCREMENT,
  `publish_name` varchar(50) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `contacter` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`publish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of publish_info
-- ----------------------------
INSERT INTO `publish_info` VALUES ('1', '北京十月文艺出版社', '15207484004', '双木林', '952877313@qq.com', '北京');
INSERT INTO `publish_info` VALUES ('2', '沈阳出版社', '15298377721', '小华', '142123315@qq.com', '天津');
INSERT INTO `publish_info` VALUES ('5', '八月飞雪出版社', '1234513454', '老', '3124', '湖南');

-- ----------------------------
-- Table structure for reader_card
-- ----------------------------
DROP TABLE IF EXISTS `reader_card`;
CREATE TABLE `reader_card` (
  `reader_id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `passwd` varchar(15) NOT NULL DEFAULT '111111',
  `vip_state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader_card
-- ----------------------------
INSERT INTO `reader_card` VALUES ('2016008', 'lgl', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014101', '张华', '123456', '1');
INSERT INTO `reader_card` VALUES ('1501014102', '王小伟', '111111', '0');
INSERT INTO `reader_card` VALUES ('1501014103', '王莞尔', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014104', '张明华', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014105', '李一琛', '111111', '1');
INSERT INTO `reader_card` VALUES ('1501014106', '李二飞', '111111', '0');

-- ----------------------------
-- Table structure for reader_info
-- ----------------------------
DROP TABLE IF EXISTS `reader_info`;
CREATE TABLE `reader_info` (
  `reader_id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telcode` varchar(11) NOT NULL,
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader_info
-- ----------------------------
INSERT INTO `reader_info` VALUES ('1501014101', '张华', '女', '1996-05-11', '天津市', '12345678900');
INSERT INTO `reader_info` VALUES ('1501014102', '王小伟', '女', '1996-02-01', '北京市', '12345678909');
INSERT INTO `reader_info` VALUES ('1501014103', '王莞尔', '女', '1994-03-12', '浙江省杭州市', '12345678908');
INSERT INTO `reader_info` VALUES ('1501014104', '张明华', '男', '1996-08-29', '陕西省西安市', '12345678907');
INSERT INTO `reader_info` VALUES ('1501014105', '李一琛', '男', '1996-01-01', '陕西省西安市', '15123659875');
INSERT INTO `reader_info` VALUES ('1501014106', '李二飞', '男', '1996-05-03', '山东省青岛市', '15369874123');

-- ----------------------------
-- Table structure for sell_info
-- ----------------------------
DROP TABLE IF EXISTS `sell_info`;
CREATE TABLE `sell_info` (
  `serial_number` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `reader_id` int(11) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `number` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `state` int(10) NOT NULL,
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_info
-- ----------------------------
INSERT INTO `sell_info` VALUES ('13', '2019-05-21', '1501014101', '10000001', '4', '120.00', '0');
INSERT INTO `sell_info` VALUES ('14', '2019-05-21', '1501014101', '10000001', '3', '90.00', '0');
INSERT INTO `sell_info` VALUES ('17', '2019-05-22', '1501014104', '10000001', '1', '35.00', '0');
INSERT INTO `sell_info` VALUES ('23', '2019-05-23', '1501014106', '10000006', '2', '136.00', '0');
INSERT INTO `sell_info` VALUES ('24', '2019-05-23', '1501014106', '10000007', '12', '4298.40', '0');
INSERT INTO `sell_info` VALUES ('26', '2019-05-23', '1501014106', '50000008', '4', '140.00', '0');
INSERT INTO `sell_info` VALUES ('27', '2019-05-23', '1501014106', '50000009', '4', '104.00', '0');
INSERT INTO `sell_info` VALUES ('28', '2019-05-23', '1501014106', '50000010', '2', '24.00', '0');
INSERT INTO `sell_info` VALUES ('29', '2019-05-23', '1501014106', '50000011', '8', '336.00', '0');
INSERT INTO `sell_info` VALUES ('30', '2019-05-23', '1501014106', '10000005', '2', '149.60', '0');
INSERT INTO `sell_info` VALUES ('31', '2019-05-23', '1501014102', '10000001', '20', '700.00', '0');
INSERT INTO `sell_info` VALUES ('33', '2019-05-23', '1501014103', '10000001', '3', '90.00', '0');
INSERT INTO `sell_info` VALUES ('34', '2019-05-23', '1501014103', '10000001', '2', '60.00', '0');
INSERT INTO `sell_info` VALUES ('35', '2019-05-23', '1501014103', '10000001', '2', '60.00', '0');
INSERT INTO `sell_info` VALUES ('36', '2019-05-23', '1501014103', '10000003', '10', '200.00', '0');
INSERT INTO `sell_info` VALUES ('37', '2019-05-23', '1501014103', '10000004', '8', '116.80', '0');
INSERT INTO `sell_info` VALUES ('39', '2019-05-23', '1501014103', '10000006', '4', '0.00', '0');
INSERT INTO `sell_info` VALUES ('40', '2019-05-23', '1501014103', '10000007', '14', '0.00', '0');
INSERT INTO `sell_info` VALUES ('42', '2019-05-23', '1501014103', '50000005', '6', '0.00', '0');
INSERT INTO `sell_info` VALUES ('44', '2019-05-23', '1501014103', '50000008', '8', '0.00', '1');
INSERT INTO `sell_info` VALUES ('45', '2019-05-23', '1501014103', '50000009', '5', '0.00', '0');
INSERT INTO `sell_info` VALUES ('46', '2019-05-23', '1501014103', '50000010', '14', '0.00', '0');
INSERT INTO `sell_info` VALUES ('47', '2019-05-23', '1501014101', '10000001', '14', '420.00', '0');
INSERT INTO `sell_info` VALUES ('49', '2019-05-28', '1501014101', '10000004', '4', '58.40', '0');
INSERT INTO `sell_info` VALUES ('50', '2019-06-10', '1501014101', '50000004', '7', '0.00', '0');
INSERT INTO `sell_info` VALUES ('51', '2019-06-11', '1501014101', '50000004', '1', '0.00', '0');
INSERT INTO `sell_info` VALUES ('52', '2019-06-11', '1501014101', '50000004', '1', '0.00', '0');
INSERT INTO `sell_info` VALUES ('53', '2019-06-12', '1501014101', '10000003', '1', '20.00', '0');
INSERT INTO `sell_info` VALUES ('54', '2019-06-12', '1501014101', '10000003', '1', '20.00', '0');
INSERT INTO `sell_info` VALUES ('55', '2019-06-12', '1501014101', '10000004', '0', '0.00', '0');
