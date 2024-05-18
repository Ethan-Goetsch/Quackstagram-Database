-- Question 1
select destination_id as user_id, count(source_id) as followers
from user_relationship ur
group by destination_id
having followers > 1

-- Question 8
with user_posts as
(
	select post_id
	from post
	where author_id = 1
),
user_likes as
(
	select user_id, post_id
	from post_relationship
	where post_id in (select post_id from user_posts)
	group by user_id, post_id
),
user_likes_count as
(
	select user_id, count(post_id) as liked_posts_count
	from user_likes
	group by user_id
)
select user.user_id
from user
join user_likes_count ulc on user.user_id = ulc.user_id
where ulc.liked_posts_count = (select count(post_id) from user_posts) 

-- Question 11
with user_posts as
(
	select author_id, count(author_id) as posts
	from post
	group by author_id
),
user_comments as
(
	select user_id, count(comment_id) as comments
	from comment
	group by user_id
),
user_likes as
(
	select user_id, count(post_id) as likes
	from post_relationship
	group by user_id
)
select author_id as user, max(posts + comments + likes) as activity
from user_posts
inner join user_comments on user_posts.author_id = user_comments.user_id
inner join user_likes on user_posts.author_id = user_likes.user_id
group by user
limit 1

-- Question 14
select user_id, count(post_id)
from post_relationship
where user_id = 1
group by user_id

select count(post_id)
from post_relationship
where post_id in (select post_id from post where author_id = 1)

-- Question 16
select user_id, followers_count / followings_count as ratio
from user_behaviour
order by ratio desc 
limit 1

-- QUESTION 18
-- find all users who have not liked a post and who have not commented on a post
-- get all posts from a user. Find any users who have not liked that post or commented on that post
-- get all people who have liked a post

select post_id
from post
where author_id = 1

select user_id
from user
where user_id not in
	(select user_id
	from post_relationship
	where post_id in (select post_id from post where author_id = X))
and user_id not in
	(select user_id
	from comment
	where post_id in (select post_id from post where author_id = X))
	
with user_posts as
(
	select post_id
	from post
	where post_id = X
),
liked_users as
(
	select user_id
	from post_relationship
	where post_id in (select post_id from user_posts)
),
commented_users as
(
	select user_id
	from comment
	where post_id in (select post_id from user_posts)
)
select user_id
from user
where user_id not in (select user_id from liked_users)
and user_id not in (select user_id from commented_users)

-- Question 19
select destination_id as user, count(followed) as followers_gained
from user_relationship_history urh
where followed = 1
and relationship_date > date_sub(now() , interval 10 day) 
group by destination_id
order by followers_gained desc
limit 1

-- QUESTION 20
select user_id, followers_count
from user_behaviour
where followers_count > (select count(user_id) from user) / 100 * 5
