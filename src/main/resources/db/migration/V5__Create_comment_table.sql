CREATE TABLE COMMENT
(
	ID BIGINT AUTO_INCREMENT PRIMARY KEY,
	PARENT_ID BIGINT NOT NULL,
	TYPE INT NOT NULL,
	COMMENTATOR INT NOT NULL,
	GMT_CREATE BIGINT NOT NULL,
	GMT_MODIFIED BIGINT NOT NULL,
	LIKE_COUNT INT DEFAULT 0,
	CONTENT VARCHAR(1024)
);