create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT IGNORE INTO `users` VALUES('user','{noop}Qt6eZ7kYSwG3','1')
INSERT IGNORE INTO `authorities` VALUES('user','read')

INSERT IGNORE INTO `users` VALUES('admin','{bcrypt}$2a$12$fG/rSN7RGfz4sdqEkLAa2u0vX0R19J69eDB/BnfO63m4EXpxqXBXa','1')
INSERT IGNORE INTO `authorities` VALUES('admin', 'admin')

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT  INTO `employee` (`email`, `password`, `role`) VALUES ('user@example.com', '{noop}Qt6eZ7kYSwG3', 'read');
INSERT  INTO `employee` (`email`, `password`, `role`) VALUES ('admin@example.com', '{bcrypt}$2a$12$fG/rSN7RGfz4sdqEkLAa2u0vX0R19J69eDB/BnfO63m4EXpxqXBXa', 'admin');