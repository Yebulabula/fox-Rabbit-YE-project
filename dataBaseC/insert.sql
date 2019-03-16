-- Part 2.2 insert.sql
--
-- Submitted by: <Ye Mao>, <k1894303>
--

-- DO NOT use these SQL commands in your submission(they will cause an
--  error on the NMS database server):
-- CREATE SCHEMA
-- USE

-- Insert values in Coach table
INSERT INTO Coach
VALUES
             ("James Cameron","Cameron","1954-08-16","C14602401","07961148652",400,"M"),
             ("Robert Downey Jr.","Downey","1965-04-04","C14602402","08752465728",300,"M"),
             ("Rowan Atkinson","Atkinson","1955-01-06","C15289743","05459837249",600,"M"),
             ("Taylor Swift","Swift","1989-12-13","C16289745","05397448706","400","F");

-- Insert values in Contender table
INSERT INTO Contender
VALUES
 ("Queen","Individual","CT0569789","C14602401"),
             ("Rebecca","Individual","CT0451768","C15289743"),
             ("BAD_BOYS","Group","CT0579798","C16289745"),
             ("Christian","Individual","CT5473285","C15289743"),
             ("LondonGirl","Individual","CT7238912","C14602401");

-- insert values in participant table
INSERT INTO Participant
VALUES
 ("Mike Robinson","Robinson","1994-06-27","187-023","09283714241",150,"M","CT5473285"),
             ("Derrick Harden","Harden","1983-06-27","176-083","02803184241",100,"M","CT0451768"),
             ("Michael Wallance","Wallance","1978-08-24","176-085","02808929241",100,"M","CT0579798"),
             ("Leborn Jenny","Jenny","1989-07-28","186-084","03803124041",150,"F","CT0569789"),
             ("Tom Cruise","Cruise","1962-07-03","196-085","02823144241",50,"M","CT0579798"),
             ("Jenny Buss","Buss","1993-05-24","176-086","07961184241",50,"F","CT7238912"),
             ("Danny Green","Green","1989-06-25","185-082","08971684241",50,"M","CT0579798"),
             ("Stephen Jackson","Jackson","1978-02-24","175-086","08971284241",50,"M","CT0579798"),
             ("Sebastian Curry","Curry","1990-04-22","183-096","02148184241",50,"M","CT0579798"),
             ("Eric  Nick","Nick","1990-05-24","179-086","07961892341",50,"M","CT0579798");

-- Insert values in TVShow table
INSERT INTO TVShow
VALUES
("Tower Bridge","2019-03-02","TB1000P8A","19:00:00","21:00:00"),
("Temple","2019-03-03","TP1001P8A","20:00:00","22:00:00"),
("Hyde Park","2019-03-09","HY2407P8A","20:00:00","22:00:00"),
(NULL,"2019-03-10","TS2002P8A","19:00:00","21:00:00"),
("Covent Garden","2019-03-16","CG2408P8A","19:00:00","21:00:00"),
("Hyde Park","2019-03-17","HY2409P8A","19:00:00","21:00:00"),
("Bush House","2019-03-23","BH2409P8A","19:00:00","21:00:00"),
("NULL","2019-03-24","TS1002P9A","20:00:00","22:00:00"),
("Covent Garden","2019-03-30","CG1003P9A","19:00:00","21:00:00"),
("NULL","2019-03-31","TS1004P9A","20:00:00","22:00:00"),
("Temple","2019-04-06","TP1005P8A","19:00:00","21:00:00"),
("Tower Bridge","2019-04-07","TB1006P8A","19:00:00","21:00:00"),
("Temple","2019-04-13","TP1007P9A","20:00:00","22:00:00"),
("Big Ben","2019-04-14","BB1008P9A","20:00:00","22:00:00"),
("Bush House","2019-04-20","BH1009P9A","19:00:00","21:00:00"),
("Big Ben","2019-04-21","BB3425P8A","20:00:00","22:00:00"),
("SSE ARENA","2019-04-27","SE1020P9A","19:00:00","21:00:00"),
(NULL,"2019-04-28","TS3425P8A","20:00:00","22:00:00");

-- Insert valuse in CoachInShow table
INSERT INTO CoachInShow
VALUES
("C15289743","TB1000P8A"),
("C14602401","TB1000P8A"),
("C15289743","TP1001P8A"),
("C14602401","TP1001P8A"),
("C14602402","HY2407P8A"),
("C15289743","HY2407P8A"),
("C16289745","HY2407P8A"),
("C14602401","TS2002P8A"),
("C15289743","TS2002P8A"),
("C16289745","TS2002P8A"),
("C14602402","CG2408P8A"),
("C16289745","CG2408P8A"),
("C15289743","CG2408P8A"),
("C15289743","HY2409P8A"),
("C16289745","HY2409P8A"),
("C14602401","BH2409P8A"),
("C14602402","BH2409P8A"),
("C15289743","BH2409P8A"),
("C16289745","BH2409P8A"),
("C15289743","TS1002P9A"),
("C16289745","TS1002P9A"),
("C14602401","TS1002P9A"),
("C15289743","CG1003P9A"),
("C16289745","CG1003P9A"),
("C14602401","CG1003P9A"),
("C14602401","TS1004P9A"),
("C14602402","TS1004P9A"),
("C15289743","TS1004P9A"),
("C16289745","TS1004P9A"),
("C14602401","TP1005P8A"),
("C15289743","TP1005P8A"),
("C16289745","TP1005P8A"),
("C14602402","TB1006P8A"),
("C14602401","TB1006P8A"),
("C15289743","TB1006P8A"),
("C14602402","TP1007P9A"),
("C14602401","TP1007P9A"),
("C15289743","TP1007P9A"),
("C16289745","BB1008P9A"),
("C14602401","BB1008P9A"),
("C15289743","BB1008P9A"),
("C14602401","BH1009P9A"),
("C14602402","BH1009P9A"),
("C15289743","BH1009P9A"),
("C16289745","BH1009P9A"),
("C15289743","BB3425P8A"),
("C16289745","BB3425P8A"),
("C15289743","SE1020P9A"),
("C16289745","SE1020P9A"),
("C14602402","SE1020P9A"),
("C15289743","TS3425P8A"),
("C16289745","TS3425P8A"),
("C14602401","TS3425P8A");

-- Insert values in table ContenderInShow
INSERT INTO ContenderInShow
VALUES("CT0569789","TB1000P8A"),
 ("CT0451768","TB1000P8A"),
 ("CT5473285","TB1000P8A"),
 ("CT7238912","TB1000P8A"),
 ("CT0569789","TP1001P8A"),
 ("CT0451768","TP1001P8A"),
 ("CT5473285","TP1001P8A"),
 ("CT7238912","TP1001P8A"),
 ("CT0451768","HY2407P8A"),
 ("CT0579798","HY2407P8A"),
 ("CT5473285","HY2407P8A"),
 ("CT0451768","TS2002P8A"),
 ("CT0579798","TS2002P8A"),
 ("CT0569789","TS2002P8A"),
 ("CT7238912","TS2002P8A"),
 ("CT5473285","TS2002P8A"),
 ("CT0579798","CG2408P8A"),
 ("CT0451768","CG2408P8A"),
 ("CT5473285","CG2408P8A"),
 ("CT0579798","HY2409P8A"),
 ("CT0451768","HY2409P8A"),
 ("CT5473285","HY2409P8A"),
 ("CT0451768","BH2409P8A"),
 ("CT0579798","BH2409P8A"),
 ("CT5473285","BH2409P8A"),
 ("CT7238912","BH2409P8A"),
 ("CT0569789","BH2409P8A"),
 ("CT0579798","TS1002P9A"),
 ("CT0451768","TS1002P9A"),
 ("CT5473285","TS1002P9A"),
 ("CT7238912","TS1002P9A"),
 ("CT0569789","TS1002P9A"),
 ("CT0579798","CG1003P9A"),
 ("CT0451768","CG1003P9A"),
 ("CT5473285","CG1003P9A"),
 ("CT7238912","CG1003P9A"),
 ("CT0569789","CG1003P9A"),
 ("CT0579798","TS1004P9A"),
 ("CT0451768","TS1004P9A"),
 ("CT5473285","TS1004P9A"),
 ("CT7238912","TS1004P9A"),
 ("CT0569789","TS1004P9A"),
 ("CT0579798","TP1005P8A"),
 ("CT0451768","TP1005P8A"),
 ("CT5473285","TP1005P8A"),
 ("CT7238912","TP1005P8A"),
 ("CT0569789","TP1005P8A"),
 ("CT0451768","TB1006P8A"),
 ("CT5473285","TB1006P8A"),
 ("CT7238912","TB1006P8A"),
 ("CT0569789","TB1006P8A"),
 ("CT0451768","TP1007P9A"),
 ("CT5473285","TP1007P9A"),
 ("CT7238912","TP1007P9A"),
 ("CT0569789","TP1007P9A"),
 ("CT0579798","BB1008P9A"),
 ("CT0451768","BB1008P9A"),
 ("CT5473285","BB1008P9A"),
 ("CT7238912","BB1008P9A"),
 ("CT0569789","BB1008P9A"),
 ("CT0451768","BH1009P9A"),
 ("CT0579798","BH1009P9A"),
 ("CT5473285","BH1009P9A"),
 ("CT7238912","BH1009P9A"),
 ("CT0569789","BH1009P9A"),
 ("CT0451768","BB3425P8A"),
 ("CT0579798","BB3425P8A"),
 ("CT5473285","BB3425P8A"),
 ("CT0451768","SE1020P9A"),
 ("CT0579798","SE1020P9A"),
 ("CT5473285","SE1020P9A"),
 ("CT0451768","TS3425P8A"),
 ("CT0579798","TS3425P8A"),
 ("CT5473285","TS3425P8A"),
 ("CT7238912","TS3425P8A"),
 ("CT0569789","TS3425P8A");