with sales as (
    select 
        sales_id,
        product_sk,
        customer_sk,
        {{ multiply("unit_price", "quantity") }} as gross_amount,
        payment_method
    from {{ ref('bronze_fact_sales') }}
),
products as (
    select 
        product_sk, 
        category 
    from {{ ref('bronze_dim_product') }}
),
customer as (
    select 
        customer_sk,
        customer_name,
        gender
    from {{ ref('bronze_dim_customer') }}
)
select
    sales.sales_id, 
    sales.gross_amount,
    sales.payment_method,
    products.category,
    customer.gender
from sales
join products on sales.product_sk = products.product_sk
join customer on sales.customer_sk = customer.customer_sk
