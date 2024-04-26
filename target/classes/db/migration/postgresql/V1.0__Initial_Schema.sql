CREATE SEQUENCE user_sequence START WITH 1000 INCREMENT BY 1;

CREATE TABLE users (
	id					INT8 			DEFAULT nextval('user_sequence'),
	name		        VARCHAR(255),
	username			VARCHAR(255),
	password			VARCHAR(255),
	role		        VARCHAR(100),

	PRIMARY KEY(id)
);