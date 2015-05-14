CREATE TABLE user (
	userId int NOT NULL AUTO_INCREMENT,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	PRIMARY KEY (userID),
	UNIQUE (username)
);

CREATE TABLE user_profile (
	userId int NOT NULL,
	firstName varchar(255) NOT NULL,
	lastName varchar(255) NOT NULL,
	PRIMARY KEY (userId),
	FOREIGN KEY (userId) references user(userId)
);