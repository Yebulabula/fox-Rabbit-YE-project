-- Part 2.1 schema.sql
--
-- Submitted by: <Ye Mao>, <k1894303>
--

-- DO NOT use these SQL commands in your submission(they will cause an
--  error on the NMS database server):
-- CREATE SCHEMA
-- USE
-- Create table Coach
CREATE TABLE Coach(
name VARCHAR(20) NOT NULL,
surname VARCHAR(10) NOT NULL,
DoB DATE NOT NULL,
idCoach CHAR(9) NOT NULL,
phone VARCHAR(11),
dailySalary INT UNSIGNED,
gender CHAR(1) NOT NULL,
PRIMARY KEY(idCoach)
);
-- Create table  Contender
CREATE TABLE Contender(
stageName VARCHAR(10) NOT NULL,
type VARCHAR(10) NOT NULL,
idContender CHAR(9)  NOT NULL,
coach CHAR(9) NOT NULL,
PRIMARY KEY(idContender),
-- attribute coach in Contender use the same domain as the idCoach attribute in Coach.
-- Use idCoach as its foreign key
FOREIGN KEY(coach)REFERENCES
Coach(idCoach) ON DELETE  CASCADE
);
-- Create table Participant
CREATE TABLE Participant(
name VARCHAR(20) NOT NULL,
surname VARCHAR(10) NOT NULL,
DoB DATE NOT NULL,
idParticipant CHAR(7)  NOT NULL,
phone VARCHAR(11) NOT NULL,
dailySalary INT UNSIGNED,
gender CHAR(1) NOT NULL,
contender CHAR(9) NOT NULL,
-- each participant's id should be unique.
PRIMARY KEY(idParticipant),

FOREIGN KEY(contender)REFERENCES
Contender(idContender) ON DELETE  CASCADE
);
-- Create table TVShow
-- this table is used to provide the relavent information of each show.
CREATE TABLE TVShow(
location VARCHAR(20) ,
date DATE NOT NULL,
idShow CHAR(10)  NOT NULL,
startTime TIME NOT NULL,
endTime TIME NOT NULL,
PRIMARY KEY(idShow)
);
-- register the attendance of coaches in this table
CREATE TABLE CoachInShow(
coach CHAR(9) NOT NULL,
current_Show CHAR(10) NOT NULL,
PRIMARY KEY(coach,current_Show),
FOREIGN KEY(coach)REFERENCES
Coach(idCoach)
ON DELETE CASCADE,

FOREIGN KEY(current_Show)REFERENCES
TVShow(idShow)
ON DELETE CASCADE
);
-- register the attendance of contenders
CREATE TABLE ContenderInShow(
contender CHAR(9) NOT NULL,
current_Show CHAR(10) NOT NULL,
PRIMARY KEY(contender,current_Show),
FOREIGN KEY(contender)REFERENCES
Contender(idContender)
ON DELETE  CASCADE,

FOREIGN KEY(current_Show)REFERENCES
TVShow(idShow)
ON DELETE CASCADE
);
