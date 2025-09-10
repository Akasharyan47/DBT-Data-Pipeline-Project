select *from {{ ref('bronze_fact_sales') }}
where gross_amount < 0 AND net_amount < 0  