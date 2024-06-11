Create database Minesweeper
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE level (
    levelid INT IDENTITY(1,1) PRIMARY KEY,
    levelname NVARCHAR(50) NOT NULL
);

CREATE TABLE bangxephang (
    bangxephangid INT IDENTITY(1,1) PRIMARY KEY,
    levelid INT,
    username VARCHAR(50),
    timeComplete INT,
    FOREIGN KEY (levelid) REFERENCES level(levelid),
    FOREIGN KEY (username) REFERENCES users(username)
);


INSERT INTO users (username, password)
VALUES ('user1', 'password1'),
       ('user2', 'password2'),
       ('user3', 'password3');

INSERT INTO level (levelname)
VALUES (N'Dễ'),
       (N'Trung bình'),
       (N'Khó');
