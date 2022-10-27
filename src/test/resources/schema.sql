create table comment (
   comment_id bigint not null auto_increment,
   content varchar(500),
   parent_id bigint,
   post_id bigint,
   primary key (comment_id)
);
create table member (
   member_id bigint not null auto_increment,
   createdDate datetime(6),
   modifiedDate datetime(6),
   email varchar(255) not null,
   pw varchar(255) not null,
   primary key (member_id)
);
create table post (
   post_id bigint not null auto_increment,
   description varchar(10000),
   title varchar(255),
   member_id bigint,
   primary key (post_id)
);