DROP INDEX IF EXISTS datetime_unique_each_user_id;
DROP INDEX IF EXISTS meals_idx;

EXPLAIN ANALYSE
SELECT *
FROM meals
WHERE user_id = 10000
  AND date_time BETWEEN '2015-02-10' AND '2015-05-20'
ORDER BY date_time DESC;