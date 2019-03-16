-- Part 2.5 delete.sql
--
-- Submitted by: <Ye Mao>, <k1894303>
--

-- DO NOT use these SQL commands in your submission(they will cause an
--  error on the NMS database server):
-- CREATE SCHEMA
-- USE
-- at first, when I created the schema , I used the "Delete on CASCADE",
-- therefore, if I delete a contender directly, all its personal data will be
-- deleted absolutely at the same time  .
-- delete all info of contender and its participants
DELETE a.*,b.*
FROM Participant a LEFT JOIN  Contender b
ON a.contender=b.idContender
WHERE a.contender IN
( -- look up the contender who has the lowest hour salary.
  SELECT contender
FROM(
  SELECT contender, Min_HourSalary, Total
  FROM(
    (SELECT MIN(Total) AS Min_HourSalary
     FROM(
       SELECT SUM(hourSalary) AS Total, contender
       FROM Participant
       GROUP BY contender
     ) AS SumHSOfEachContender
   ) AS MinHSContender

JOIN

  (SELECT SUM(hourSalary) AS Total, contender
   FROM Participant
   GROUP BY contender) AS ContenderHSInfo
  )
 WHERE Total = Min_HourSalary
 ) AS FindPoorestContender);
