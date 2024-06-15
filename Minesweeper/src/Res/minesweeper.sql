Create database Minesweeper
use Minesweeper
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
VALUES ('hntung', '123'),
       ('giathuan', '123'),
       ('phong', '123'),
       ('dangcap', '123'),
       ('vippro', '123'),
       ('hoangduy', '123');

INSERT INTO level (levelname)
VALUES (N'Dễ'),
       (N'Trung bình'),
       (N'Khó');

INSERT INTO bangxephang (levelid, username, timeComplete)
VALUES 
-- Level 1
(1, 'hntung', 100),
(1, 'giathuan', 120),
(1, 'phong', 90),
(1, 'dangcap', 110),
(1, 'vippro', 130),
(1, 'hoangduy', 140),

-- Level 2
(2, 'giathuan', 200),
(2, 'hntung', 220),
(2, 'dangcap', 190),
(2, 'vippro', 210),
(2, 'hoangduy', 230),
(2, 'phong', 240),

-- Level 3
(3, 'hntung', 300),
(3, 'dangcap', 320),
(3, 'giathuan', 290),
(3, 'vippro', 310),
(3, 'hoangduy', 330),
(3, 'phong', 340);
