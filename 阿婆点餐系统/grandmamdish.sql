
-- 管理员 
create table admin(
id int(10) not null PRIMARY key auto_increment COMMENT 'id自增',
`name` VARCHAR(50) not null COMMENT '管理员名称',
pwd VARCHAR(50) not null comment '密码',
authority VARCHAR(10) not null comment '权限'

);

INSERT INTO `admin` VALUES ('5', 'sa', '123', '0');

-- 用户
create table users(
id int(11) not null PRIMARY key not null  auto_increment COMMENT '用户id自增',
`name` VARCHAR(50) not null COMMENT '用户名称',
pwd VARCHAR(50) not null comment '密码',
realname VARCHAR(50)not null ,
sex VARCHAR(50) not null,
age varchar(50) not null,
card VARCHAR(50) not null COMMENT '身份证号',
adress VARCHAR(100) ,
phone VARCHAR(50),
email VARCHAR(50),
`code` VARCHAR(50) COMMENT '邮政编码',
type VARCHAR(50) COMMENT '类型：游客/注册用户'

);

INSERT INTO `users` VALUES ('1', '111', '111', '111', '男', '22', '111111111111111111', '天津市', '13977777777', '1123@163.com', '110044', '1');
INSERT INTO `users` VALUES ( '2','222', '222', '张三', '男', '24', '111111111111111111', '天津市', '13988888888', '123@163.com', '110044', '1');
INSERT INTO `users` VALUES ( '3','sunday', '123', '张三', '男', '26', '4222222222222222', '北京市海淀区', '13901001111', '13901001111@139.com', '101000', '1');
INSERT INTO `users` VALUES ('0', '333', '333', '333', '女', '22', '4888888888888888', '天津市', '13977777777', '1123@163.com', '110044', '1');
insert into users(name,pwd,realname,sex,age,card,adress,phone,email,code,type) values( '333', '333', '333', '女', '22', '4888888888888888', '天津市', '13977777777', '1123@163.com', '110044', '1');
 -- 清空表数据，并清空属性值自增从新开始
TRUNCATE table users;
drop table orders
drop table users

-- 菜品类别
create table types(
id int(10) not null PRIMARY key auto_increment,
`name` VARCHAR(50) not null COMMENT'类别名称'
);

ALTER table menus modify column id int(10);

-- 菜品
create table menus(
id integer not null PRIMARY key auto_increment,
`name` VARCHAR(50) not null ,
typeid int(10) not null ,
burden VARCHAR(100) COMMENT'原材料',
brief varchar(500) comment'简介',
price VARCHAR(50) COMMENT'单价',
sums VARCHAR(50) COMMENT'数量',
pricel VARCHAR(50) COMMENT'折后单价',
sumsl VARCHAR(50) comment'优惠数量',
imgpath VARCHAR(100) comment'图片',
FOREIGN key (typeid) REFERENCES types(id)
);
-- 增加菜品库存大于0约束
ALTER TABLE menus ADD CHECK (sums>0);

alter table menus add FOREIGN key (typeid) REFERENCES types(id);
-- 订单
create table orders(
id int(10) not null PRIMARY key auto_increment COMMENT 'id自增',
menuid integer not null COMMENT'外键，菜品id,注意一定要类型一致',
userid int(10) not null COMMENT'外键，用户id,注意一定要类型一致',
menunum varchar(50) not null COMMENT'订购数量',
times varchar(50) not null COMMENT '时间',
delivery VARCHAR(50)not null COMMENT'状态0/1',
FOREIGN key(userid) REFERENCES users(id),
foreign key(menuid) REFERENCES menus(id)
);

INSERT INTO `orders`(menuid,userid,menunum,times,delivery) VALUES ( '12', '1', '1', '2015-02-15 13:26:09', '1');


-- 公告
create table notice(
id int(10) not null PRIMARY key auto_increment,
`name` VARCHAR(50) not null COMMENT '公告名称',
content VARCHAR(100) not null comment '公告内容',
times VARCHAR(50) not null comment '公告时间'
);


SELECT id, name from admin where pwd="admin";
update admin set name='root', pwd='admin' where name='zhangsan';

-- Users
INSERT INTO `users` VALUES ('4', '1', '1', '1', '男', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `users` VALUES ('1', '111', '111', '111', '男', '22', '111111111111111111', '天津市', '13977777777', '1123@163.com', '110044', '1');
INSERT INTO `users` VALUES ('2', '222', '222', '张三', '男', '24', '111111111111111111', '天津市', '13988888888', '123@163.com', '110044', '1');
INSERT INTO `users` VALUES ('3', 'sunday', '123', '张三', '男', '26', '4222222222222222', '北京市海淀区', '13901001111', '13901001111@139.com', '101000', '1');

-- types
INSERT INTO `types` VALUES ('1', '凉拌菜');
INSERT INTO `types` VALUES ('2', '炒菜');
INSERT INTO `types` VALUES ('6', '炒饭');
INSERT INTO `types` VALUES ('10', '蒸菜');
INSERT INTO `types` VALUES ('11', '川菜');

-- menus
INSERT INTO `menus` VALUES ('12', '粉蒸肉', '10', '米粉、五花肉', '暂无11111', '26', '0', '23', '0', 'img/m_fenzhengrou.gif');
INSERT INTO `menus` VALUES ('18', '黄瓜拉皮', '1', '黄瓜、拉皮', '暂无', '8', '0', '6', '1', 'img/m_huanggualapi.gif');
INSERT INTO `menus` VALUES ('19', '水煮鱼', '11', '鱼，辣椒', '暂无', '38', '0', '32', '1', 'img/m_shuizhuyu.gif');
INSERT INTO `menus` VALUES ('14', '糖醋排骨', '2', '排骨、糖、醋', '暂无', '26', '0', '24', '4', 'img/m_tangcupaigu.gif');
INSERT INTO `menus` VALUES ('17', '五香驴肉', '1', '驴肉', '暂无', '25', '0', '21', '1', 'img/m_wuxianglvrou.gif');
INSERT INTO `menus` VALUES ('15', '咸肉菜饭', '6', '咸肉、米饭', '暂无', '15', '0', '12', '4', 'img/m_xianroucaifan.gif');

INSERT INTO `menus`(name,typeid,burden,brief,price,sums,pricel,sumsl) VALUES ( '红烧肉', '10', '五花肉', '暂无', '48', '0', '45', '1');
drop table menus;
INSERT INTO `menus`(name,typeid,burden,brief,price,sums,pricel,sumsl,imgpath) VALUES ( '白灼菜心', (select types.id from types where types.`name`='炒菜'), '菜心', '暂无', '30', '0', '25', '1','images/9,jpg')
select types.id from types where types.`name`='炒菜'

-- orders
INSERT INTO `orders` VALUES ('16', '2', '12', '2', '2015-02-15 13:16:28', '1');
INSERT INTO `orders` VALUES ('17', '1', '14', '1', '2015-02-15 13:23:30', '1');
INSERT INTO `orders` VALUES ('18', '1', '15', '1', '2015-02-15 13:26:09', '1');
INSERT INTO `orders` VALUES ('20', '2', '12', '1', '2015-02-16 00:38:49', '0');
INSERT INTO `orders` VALUES ('21', '2', '15', '1', '2015-02-16 00:38:49', '0');
INSERT INTO `orders` VALUES ('22', '2', '17', '1', '2015-02-16 00:38:49', '0');
INSERT INTO `orders` VALUES ('23', '1', '14', '1', '2015-04-30 17:22:27', '0');
INSERT INTO `orders` VALUES ('24', '1', '15', '1', '2015-04-30 17:22:27', '0');
INSERT INTO `orders` VALUES ('31', '1', '14', '1', '2015-07-02 23:09:56', '0');
INSERT INTO `orders` VALUES ('32', '1', '18', '1', '2015-07-02 23:09:56', '0');


-- 在查询出的表上创建视图相当一个新的虚拟表，这样来查找行数
create view order_v
AS
select orders.id, userid,realname,phone,adress,menus.`name`,sum(menunum) as sum,price,times,delivery from menus,orders,users
where users.id=orders.userid and menus.id=orders.menuid GROUP BY times, menus.`name`  ;

-- 创建视图
create view statistic
as
SELECT menus.`name`,sum(menunum) as menusum,price from menus,orders where menus.id=orders.menuid and times like CONCAT((select DATE(CURDATE())),"%") GROUP BY menus.`name` ;
