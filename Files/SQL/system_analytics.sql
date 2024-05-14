create view system_analytics as
select publication_date as post_date, count(post_id) as posts_count
from post
group by publication_date
having count(post_id) >= 2
order by post_date