-- Table structure for table `t_blogger`
DROP TABLE IF EXISTS `tb_blogger`;

CREATE TABLE `tb_blogger` (
  `blogger_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `blogger_name` varchar(64) NOT NULL,
  `blogger_password` varchar(100) NOT NULL,
  `blogger_profile` text,
  `blogger_nickname` varchar(64),
  `blogger_signature` varchar(128),
  `blogger_profilepicname` varchar(128)
);


-- Table structure for table `tb_blogtype`
DROP TABLE IF EXISTS `tb_blogtype`;

CREATE TABLE `tb_blogtype` (
  `blogtype_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `blogtype_name` varchar(64) NOT NULL,
  `blogtype_orderno` int(11)
) ;


-- Table structure for table `tb_blog`
DROP TABLE IF EXISTS `tb_blog`;

CREATE TABLE `tb_blog` (
  `blog_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `blog_title` varchar(128) NOT NULL,
  `blog_digest` varchar(512),
  `blog_releasedate` datetime,
  `blog_clickHit` int(11),
  `blog_replyhit` int(11),
  `blog_content` text,
  `blog_typeid` int(11),
  `blog_keyword` varchar(256)
  CONSTRAINT `tb_blog_tb_blogtype_blogtype_id_fk` FOREIGN KEY (`blog_typeid`) REFERENCES `tb_blogtype` (`blogtype_id`)
);


-- Table structure for table `tb_comment`
DROP TABLE IF EXISTS `tb_comment`;

CREATE TABLE `tb_comment` (
  `comment_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `comment_userip` varchar(50),
  `comment_blogid` int(11) NOT NULL,
  `comment_content` varchar(1000) NOT NULL,
  `comment_date` datetime,
  `comment_state` int(11)
);


-- Table structure for table `tb_link`
DROP TABLE IF EXISTS `tb_link`;

CREATE TABLE `tb_link` (
  `link_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `link_name` varchar(100) NOT NULL,
  `link_url` varchar(200) NOT NULL,
  `link_orderno` int(11)
);

-- Insert the first blogger info for login
INSERT INTO tb_blogger VALUES(1, 'Allan', '368ae049d56bd31aa202d72585aff19e0d27256f6d836110f585acb6d8644ae4', '90后野生Java程序员', '沐风', '欲穷千里目，更上一层楼', 'profilePic.jpg');