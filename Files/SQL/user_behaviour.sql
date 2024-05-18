create view user_behaviour as
select u.user_id, coalesce(r.followers_count, 0) as followers_count, coalesce(f.followings_count, 0) as followings_count
from (select distinct user_id from user) u
left join 
	(select destination_id, count(destination_id) as followers_count
	from user_relationship ur2
	group by destination_id) r on u.user_id = r.destination_id
left join
	(select source_id, count(source_id) as followings_count
	from user_relationship ur
	group by source_id) f on u.user_id = f.source_id
