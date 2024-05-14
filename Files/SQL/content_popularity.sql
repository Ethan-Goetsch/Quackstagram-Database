create view content_popularity as 
select
	p.post_id,
	coalesce(l.like_count, 0) as like_count,
	coalesce (c.comment_count, 0) as comment_count
from
	post p
left join
	(select post_id, count(user_id) as like_count
	from post_relationship
	group by post_id) l on p.post_id = l.post_id
left join 
	(select post_id, count(comment_id) as comment_count
	from comment
	group by post_id) c on p.post_id = c.post_id