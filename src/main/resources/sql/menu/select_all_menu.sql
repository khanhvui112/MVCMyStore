SELECT m.me_id,
       m.me_name,
       m.me_link,
       m.status
FROM menu m
WHERE m.status <> 0