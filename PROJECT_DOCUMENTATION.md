# DBT Data Pipeline Project Documentation

## Project Overview
The DBT Data Pipeline Project is a data engineering project built using dbt (data build tool) to implement a multi-layered data transformation pipeline. The project follows a medallion architecture with bronze, silver, and gold layers to organize data models progressively from raw to refined data. It uses Databricks SQL Warehouse as the data platform and Python scripts for querying data.

## Technologies Used
- dbt (data build tool)
- Databricks SQL Warehouse
- Python
- SQL
- Poetry for dependency management

## Project Architecture
The project follows a medallion architecture with three layers:
1. **Bronze Layer:** Raw data ingestion from sources, materialized as views or tables.
2. **Silver Layer:** Data cleansing and standardization with intermediate transformations.
3. **Gold Layer:** Business-ready data for analytics and reporting.

## Project Structure
- `models/bronze/` - Raw data layer with dimension and fact tables (e.g., `bronze_fact_sales.sql`, `bronze_dim_customer.sql`).
- `models/silver/` - Intermediate transformation layer (e.g., `silver_salesinfo.sql`).
- `models/gold/` - Final analytics layer.
- `models/source/` - Source data definitions and schema configurations (`sources.yml`, `schema.yml`).
- `analyses/` - Analytical queries for exploration and reporting.
- `tests/` - Test cases for data validation.
- `seeds/` - Static data files used in the project (e.g., `lookup.csv`).
- `macros/` - Reusable SQL macros (e.g., `multiply.sql`).
- `snapshots/` - Slowly changing dimension snapshots.
- `DBT_Data_Pipeline_Project/dbt_project.yml` - Main dbt project configuration.
- `pyproject.toml` - Python project configuration and dependencies.
- `main.py` - Python entry point script.
- `query_fact_sales.py` - Python script to query the `fact_sales` table from Databricks.

## Data Models
### Bronze Layer Models
- `bronze_fact_sales`: Sales transaction data.
- `bronze_fact_returns`: Returns transaction data.
- `bronze_dim_customer`: Customer dimension.
- `bronze_dim_date`: Date dimension.
- `bronze_dim_product`: Product dimension.
- `bronze_dim_store`: Store dimension.

### Silver Layer Models
- `silver_salesinfo`: Combines sales data with product category and customer gender using joins and macros.

### Gold Layer Models
- Final business-ready models (not detailed in current files).

## Python Scripts
- `main.py`: Simple entry point printing a welcome message.
- `query_fact_sales.py`: Connects to Databricks SQL Warehouse using access token and queries the `fact_sales` table, printing sample data.

## Macros
- `multiply.sql`: A reusable macro to multiply two columns in SQL queries.

## Seeds
- `lookup.csv`: Static lookup data for customers with IDs, names, and emails.

## Dependencies and Environment
- Managed using Poetry with dependencies:
  - `dbt-core>=1.10.11`
  - `dbt-databricks>=1.10.9`
  - `dbt-python>=1.10.11`
  - `python-dotenv>=1.0.0`
- Python version requirement: >=3.12

## Databricks Connection Details
- Host: dbc-2a213262-2333.cloud.databricks.com
- HTTP Path: /sql/1.0/warehouses/7d109ca1b01d8933
- Catalog: dbt_project_dev
- Schema: default
- Access token is expected to be set as environment variable `DATABRICKS_ACCESS_TOKEN`.

## Skills Demonstrated
- Data modeling and transformation using dbt.
- Working with Databricks SQL warehouse.
- Writing SQL models and Python scripts for data querying.
- Organizing data pipelines with layered architecture (bronze, silver, gold).
- Managing dependencies and environment with Poetry.
- Implementing ELT data pipelines.
- Using dbt for data transformation and modeling.

---

This documentation summarizes the DBT Data Pipeline Project and can be used to showcase the skills and work done in this project for your resume or portfolio.
