SELECT
    top 20
	p.product_id,
        p.product_name,
    p.create_date,
    p.update_date,
    p.title,
    p.description,
    p.ca_id,
    p.ma_id,
    p.sale_code,
    p.color_id,
    p.product_detail_id,
    p.image_link,
    p.price
FROM
    products p
order by create_date desc