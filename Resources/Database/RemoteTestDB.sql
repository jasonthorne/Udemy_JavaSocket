/*DROP DATABASE IF EXISTS remote_test_db;*/
CREATE DATABASE remote_test_db;

USE remote_test_db;

CREATE TABLE users (
	userID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) DEFAULT NULL,
	ip INT DEFAULT 0,
	port INT DEFAULT 0,
	PRIMARY KEY (userID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO users (name, ip, port) VALUES ("user1", 1, 00);
INSERT INTO users (name, ip, port) VALUES ("user2", 2, 01);
INSERT INTO users (name, ip, port) VALUES ("user3", 3, 02);