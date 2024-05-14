create function get_all_users()
returns decimal(5, 2) deterministic
return (select Count(user_id) from user);

create procedure get_follow_history(user_id int)
select source_id as user, destination_id as user_followed, followed
from user_relationship_history
where source_id = user_id

create procedure update_user_relationship_history(source_id int, destination_id int, followed bool, date_time datetime)
	insert into user_relationship_history (source_id, destination_id, followed, relationship_date)
	values (source_id, destination_id, followed, date_time);
	
create procedure validate_user_relationship_history()
SELECT history.follow_history_id
FROM user_relationship_history history
WHERE history.followed = 0 AND NOT EXISTS (
    SELECT 1
    FROM user_relationship_history previous
    WHERE previous.source_id = history.source_id
    AND previous.destination_id = history.destination_id
    AND previous.followed = 1
    AND previous.relationship_date < history.relationship_date
);

create procedure delete_invalid_user_relationship_history()
DELETE FROM user_relationship_history
WHERE followed = 0 AND follow_history_id IN (
    SELECT u1.follow_history_id
    FROM user_relationship_history u1
    WHERE u1.followed = 0 AND NOT EXISTS (
        SELECT 1
        FROM user_relationship_history u2
        WHERE u2.source_id = u1.source_id
        AND u2.destination_id = u1.destination_id
        AND u2.followed = 1
        AND u2.relationship_date < u1.relationship_date
    )
);

create trigger update_user_relationship_history_insert
after insert 
on user_relationship
for each row 
	call update_user_relationship_history(new.source_id, new.destination_id, 1, now());

create trigger update_user_relationship_history_delete
after delete  
on user_relationship
for each row
	call update_user_relationship_history(old.source_id, old.destination_id, 0, now());
	
create trigger validate_user_relationship_history_insert
after insert 
on user_relationship_history
for each row
	call delete_invalid_user_relationship_history();
	
create trigger validate_user_relationship_history_delete
after delete 
on user_relationship_history
for each row
	call delete_invalid_user_relationship_history();
