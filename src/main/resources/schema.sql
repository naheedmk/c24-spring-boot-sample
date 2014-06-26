
CREATE TABLE Messages (
  id INTEGER identity,
  msgId INTEGER,
  status VARCHAR(32) NOT NULL,
  message VARCHAR(120000),
  quantity INTEGER
);
