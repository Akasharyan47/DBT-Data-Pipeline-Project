import os
from databricks import sql

# Databricks connection details from profiles.yml
host = "dbc-2a213262-2333.cloud.databricks.com"
http_path = "/sql/1.0/warehouses/7d109ca1b01d8933"
catalog = "dbt_project_dev"
schema = "default"

# Access token - set as environment variable or replace with actual token
access_token = os.getenv("DATABRICKS_ACCESS_TOKEN", "YOUR_ACCESS_TOKEN")

# Connect to Databricks using access token
connection = sql.connect(
    server_hostname=host,
    http_path=http_path,
    access_token=access_token
)

# Query the fact_sales table
query = f"SELECT * FROM {catalog}.{schema}.fact_sales LIMIT 10"

with connection.cursor() as cursor:
    cursor.execute(query)
    result = cursor.fetchall()

    # Print the results
    print("Data from fact_sales table:")
    for row in result:
        print(row)

# Close the connection
connection.close()
