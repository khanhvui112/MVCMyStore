SELECT p.product_id,
       p.product_name,
       p.create_date,
       p.update_date,
       p.title,
       p.description,
       p.ca_id,
       p.ma_id,
       p.sale_code,
       ps.color_id,
       ps.price,
       ps.price_sales
FROM products p
INNER JOIN productdetails ps
ON p.product_id = ps.product_id
/*INNER JOIN productspecification psf
ON p.product_specification_id = psf.product_specification_id*/
#whereClause
#orderByClause
-- offset :startItem rows fetch next :itemPage rows only
LIMIT :itemPage OFFSET :startItem;