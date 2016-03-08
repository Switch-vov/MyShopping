--�û���
create table users
(
  id number primary key, --�û�ID
  name varchar2(50) not null, --�û���
  pwd varchar2(50) not null,  --����
  email varchar2(100) not null,--����
  tel varchar2(20) not null,--�绰
  grade number(2) default 1 not null--����
);

--��Ʒ��
create table book(
  id number primary key, --��ƷID
  name varchar2(50) not null, --��Ʒ��
  author varchar2(100) not null, --����
  publishHouse varchar2(100) not null, --������
  price number not null, --�۸�
  nums number default 1000 not null --ʣ������
);

--��������(�û�)
insert into users values(10000,'switch','switch','q547550831@outlook.com','12345678910',1);
insert into users values(10001,'zhangsan','123456','zhangsan@outlook.com','01987654321',1);

--�鼮���������
create sequence book_seq --������
start with 1    --��ʼ��
increment by 1  --��������
minvalue 1      --��Сֵ
maxvalue 9999999--���ֵ
nocycle         --��ѭ��
nocache         --������
;

--��������(��Ʒ)
insert into book values(book_seq.nextval,'jspӦ�ÿ������','С��','���ӹ�ҵ������',59,400);
insert into book values(book_seq.nextval,'JAVA WEB ����','С��','���ӹ�ҵ������',43,734);
insert into book values(book_seq.nextval,'java ���˼��','С��','���ӹ�ҵ������',120,130);
insert into book values(book_seq.nextval,'j2eeָ��','С��','���ӹ�ҵ������',65,1320);
insert into book values(book_seq.nextval,'ps�̳�','СǮ','���ӹ�ҵ������',44,800);
insert into book values(book_seq.nextval,'html����','С��','���ӹ�ҵ������',38,450);
insert into book values(book_seq.nextval,'css��չ','С��','���ӹ�ҵ������',75,870);
insert into book values(book_seq.nextval,'����jva�����','С��','���ӹ�ҵ������',23,870);
insert into book values(book_seq.nextval,'��Զ��js','С��','���ӹ�ҵ������',66,480);

--������
create table orders
(
  id number primary key,--�������
  userId number  references users(id),--�û����
  totalPrice number default 0 not null,--ͼ���ܼ�
  orderDate date default sysdate not null--��������
);

--����ϸ�ڱ�
create table orderItem
(
  id number primary key, --����ϸ�ڱ��
  ordersId number references orders(id),--�������
  bookId number references book(id),--ͼ����
  bookNum number default 0 not null--ͼ�鶩������
);

--����������
create sequence order_seq--������
start with 1--��ʼ��
increment by 1--��������
minvalue 1--��Сֵ
maxvalue 9999999--���ֵ
nocycle--��ѭ��
nocache--������
;

--����ϸ��������
create sequence orderitem_seq
start with 1
increment by 1
minvalue 1
maxvalue 9999999
nocycle
nocache
;