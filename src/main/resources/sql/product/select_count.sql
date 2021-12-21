select
    count(1)
FROM
     products p
INNER JOIN productdetails ps
ON p.product_id = ps.product_id
#whereClause