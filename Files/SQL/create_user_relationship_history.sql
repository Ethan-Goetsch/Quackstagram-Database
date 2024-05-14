CREATE TABLE `user_relationship_history` (
  `follow_history_id` int,
  `source_id` int,
  `destination_id` int,
  `followed` bool,
  `relationship_date` datetime,
  PRIMARY KEY (`follow_history_id`)
);