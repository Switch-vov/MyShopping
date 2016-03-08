--用户表
create table users
(
  id number primary key, --用户ID
  name varchar2(50) not null, --用户名
  pwd varchar2(50) not null,  --密码
  email varchar2(100) not null,--邮箱
  tel varchar2(20) not null,--电话
  grade number(2) default 1 not null--级别
);

--商品表
create table book(
  id number primary key, --商品ID
  name varchar2(50) not null, --商品名
  author varchar2(100) not null, --作者
  publishHouse varchar2(100) not null, --出版社
  price number not null, --价格
  nums number default 1000 not null --剩余数量
);

--测试数据(用户)
insert into users values(10000,'switch','switch','q547550831@outlook.com','12345678910',1);
insert into users values(10001,'zhangsan','123456','zhangsan@outlook.com','01987654321',1);

--书籍编号自增长
create sequence book_seq --序列名
start with 1    --开始于
increment by 1  --增长步长
minvalue 1      --最小值
maxvalue 9999999--最大值
nocycle         --不循环
nocache         --不缓存
;

--测试数据(商品)
insert into book values(book_seq.nextval,'jsp应用开发详解','小风','电子工业出版社',59,400);
insert into book values(book_seq.nextval,'JAVA WEB 开发','小黄','电子工业出版社',43,734);
insert into book values(book_seq.nextval,'java 编程思想','小花','电子工业出版社',120,130);
insert into book values(book_seq.nextval,'j2ee指南','小王','电子工业出版社',65,1320);
insert into book values(book_seq.nextval,'ps教程','小钱','电子工业出版社',44,800);
insert into book values(book_seq.nextval,'html基础','小刘','电子工业出版社',38,450);
insert into book values(book_seq.nextval,'css拓展','小青','电子工业出版社',75,870);
insert into book values(book_seq.nextval,'深入jva虚拟机','小吴','电子工业出版社',23,870);
insert into book values(book_seq.nextval,'永远的js','小贺','电子工业出版社',66,480);

--订单表
create table orders
(
  id number primary key,--订单编号
  userId number  references users(id),--用户编号
  totalPrice number default 0 not null,--图书总价
  orderDate date default sysdate not null--订单日期
);

--订单细节表
create table orderItem
(
  id number primary key, --订单细节编号
  ordersId number references orders(id),--订单编号
  bookId number references book(id),--图书编号
  bookNum number default 0 not null--图书订购数量
);

--订单自增长
create sequence order_seq--序列名
start with 1--开始于
increment by 1--增长步长
minvalue 1--最小值
maxvalue 9999999--最大值
nocycle--不循环
nocache--不缓存
;

--订单细节自增长
create sequence orderitem_seq
start with 1
increment by 1
minvalue 1
maxvalue 9999999
nocycle
nocache
;