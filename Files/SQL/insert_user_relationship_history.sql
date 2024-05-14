INSERT INTO quackstagram.user_relationship_history (follow_history_id, source_id, destination_id, followed, relationship_date)
VALUES
(1, 1, 2, 1, '2024-05-01'),  -- 1 follows 2
(2, 2, 3, 1, '2024-05-01'),
(3, 3, 4, 1, '2024-05-02'),
(4, 4, 5, 1, '2024-05-02'),
(5, 1, 2, 0, '2024-05-03'),  -- 1 unfollows 2 after following
(6, 2, 1, 1, '2024-05-03'),  -- 2 follows 1
(7, 2, 1, 0, '2024-05-04'),  -- 2 unfollows 1 after following
(8, 5, 6, 1, '2024-05-04'),  -- 5 follows 6
(9, 6, 5, 0, '2024-05-05'),  -- 6 unfollows 5 after following
(10, 5, 6, 1, '2024-05-05'), -- 5 follows 6 again
(11, 7, 8, 1, '2024-05-06'),
(12, 8, 7, 1, '2024-05-06'),
(13, 8, 7, 0, '2024-05-07'), -- 8 unfollows 7 after following
(14, 9, 10, 1, '2024-05-07'),
(15, 10, 9, 1, '2024-05-08'),
(16, 10, 9, 0, '2024-05-08'), -- 10 unfollows 9 after following
(17, 11, 12, 1, '2024-05-09'),
(18, 12, 11, 1, '2024-05-09'),
(19, 13, 14, 1, '2024-05-10'),
(20, 14, 13, 0, '2024-05-10'); -- 14 unfollows 13 after following
