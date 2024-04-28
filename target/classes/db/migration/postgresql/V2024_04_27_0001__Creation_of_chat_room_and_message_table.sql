CREATE SEQUENCE chat_room_sequence START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE message_sequence START WITH 1000 INCREMENT BY 1;

CREATE TABLE chat_room (
	id					INT8 			DEFAULT nextval('chat_room_sequence'),
	chat_room_name		VARCHAR(255),
	created_by		    VARCHAR(255),
	created_at			TIMESTAMP,

	PRIMARY KEY(id),
	CONSTRAINT chat_room_name_uq UNIQUE (chat_room_name)
);

CREATE TABLE message (
	id					INT8 			DEFAULT nextval('message_sequence'),
	chat_room_id		VARCHAR(255),
	sender_name			VARCHAR(255),
	message		        VARCHAR(255),
	created_by		    VARCHAR(255),
    created_at			TIMESTAMP,

	PRIMARY KEY(id)
);

CREATE INDEX sender_name_idx ON message(LOWER(sender_name));