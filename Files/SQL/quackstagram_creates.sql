CREATE TABLE `Post` (
  `Post_ID` int,
  `Author_ID` int,
  `Picture_ID` int,
  `Title` varchar(50),
  PRIMARY KEY (`Post_ID`)
);

CREATE TABLE `Picture` (
  `Picture_ID` int,
  `Path_File` varchar(50),
  PRIMARY KEY (`Picture_ID`)
);

CREATE TABLE `Relationship` (
  `Follower_ID` int,
  `Followee_ID` int,
  PRIMARY KEY (`Follower_ID`, `Followee_ID`)
);

CREATE TABLE `Notification` (
  `Notification_ID` int,
  `Target_ID` int,
  `Message` varchar(50),
  PRIMARY KEY (`Notification_ID`)
);

CREATE TABLE `User` (
  `User_ID` int,
  `Username` varchar(50),
  `Password` varchar(50),
  `Bio` varchar(50),
  `Picture_ID` int,
  PRIMARY KEY (`User_ID`)
);

CREATE TABLE `post_relationship` (
  `user_id` int,
  `post_id` int,
  PRIMARY KEY (`user_id`, `post_id`)
);

CREATE TABLE `Comment` (
  `comment_id` int,
  `post_id` int,
  `user_id` int,
  `message` int,
  `publication_date` time,
  PRIMARY KEY (`comment_id`)
);

CREATE TABLE `user_relationship_history` (
  `follow_history_id` int,
  `source_id` int,
  `destination_id` int,
  `followed` bool,
  `relationship_date` datetime,
  PRIMARY KEY (`follow_history_id`)
);

