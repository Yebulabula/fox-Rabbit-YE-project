-- Part 2.4 update.sql
--
-- Submitted by: <Your Name>, <Your Student Number>
--

-- DO NOT use these SQL commands in your submission(they will cause an
--  error on the NMS database server):
-- CREATE SCHEMA
-- USE

-- get the approxiamte value of the hour salary, and then
-- change the daily salary in Coach table into the hour salary.
UPDATE Coach
SET dailySalary=dailySalary/4;
-- hour salary should be more precise, so I change the data type to float.
ALTER TABLE Coach CHANGE dailySalary hourSalary FLOAT(5,2);

-- Make the same change in table Participant
UPDATE Participant
SET dailySalary=dailySalary/4;
ALTER TABLE Participant CHANGE dailySalary hourSalary FLOAT(5,2);

-- add two more fields with type "TIME" into attendance tables to register
-- the leaving time and the arriving time of attenders in each show.
ALTER TABLE ContenderInShow add arriving_Time TIME NOT NULL;
ALTER TABLE ContenderInShow add leaving_Time TIME NOT NULL;
ALTER TABLE CoachInShow add arriving_Time TIME NOT NULL;
ALTER TABLE CoachInShow add leaving_Time TIME NOT NULL;

-- update and add arriving time and leaving time to CoachInShow table.
UPDATE CoachInShow
SET arriving_Time="19:00:00" , leaving_Time="23:00:00"
WHERE current_Show="HY2407P8A"
OR current_Show="TS1002P9A"
OR current_Show="TP1001P8A"
OR current_Show="TS1004P9A"
OR current_Show="TP1007P9A"
OR current_Show="BB1008P9A"
OR current_Show="BB3425P8A"
OR current_Show="TS3425P8A";

UPDATE CoachInShow
SET arriving_Time="18:00:00" , leaving_Time="22:00:00"
WHERE current_Show="TB1000P8A"
OR current_Show="TS2002P8A"
OR current_Show="CG2408P8A"
OR current_Show="HY2409P8A"
OR current_Show="BH2409P8A"
OR current_Show="CG1003P9A"
OR current_Show="TP1005P8A"
OR current_Show="TB1006P8A"
OR current_Show="BH1009P9A"
OR current_Show="SE1020P9A";

 --add arriving time and leaving time to ContenderInShow table.
UPDATE ContenderInShow
SET arriving_Time="19:00:00" , leaving_Time="23:00:00"
WHERE current_Show="HY2407P8A"
OR current_Show="TS1002P9A"
OR current_Show="TP1001P8A"
OR current_Show="TS1004P9A"
OR current_Show="TP1007P9A"
OR current_Show="BB1008P9A"
OR current_Show="BB3425P8A"
OR current_Show="TS3425P8A";

UPDATE ContenderInShow
SET arriving_Time="18:00:00" , leaving_Time="22:00:00"
WHERE current_Show="TB1000P8A"
OR current_Show="TS2002P8A"
OR current_Show="CG2408P8A"
OR current_Show="HY2409P8A"
OR current_Show="BH2409P8A"
OR current_Show="CG1003P9A"
OR current_Show="TP1005P8A"
OR current_Show="TB1006P8A"
OR current_Show="BH1009P9A"
OR current_Show="SE1020P9A";
