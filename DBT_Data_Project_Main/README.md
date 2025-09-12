Welcome to your new dbt project!
# DBT Data Pipeline Project (Medallion Architecture)

## Project Overview
This project implements an end-to-end **data pipeline** using **DBT (Data Build Tool)** on **Databricks**, following the **Medallion Architecture** (Bronze, Silver, Gold layers). The pipeline is designed for **sales analytics**, demonstrating best practices in data engineering, including modular design, automated testing, incremental loads, snapshots, and documentation.
<img width="1908" height="869" alt="Screenshot 2025-09-12 210245" src="https://github.com/user-attachments/assets/e8c08eb9-5266-4899-8e92-f341ed35d91d" />

---

## Key Technologies
- **DBT (Data Build Tool)**: Transformation framework, macros, snapshots, testing  
- **Databricks**: Cloud data platform for execution  
- **SQL**: Core language for transformations  
- **Python**: Environment management and scripting  
- **Jinja2**: Dynamic SQL and templating  
- **Git/GitHub**: Version control  

---

## Project Architecture

### Medallion Layers
| Layer   | Purpose                         | Examples                        |
|---------|---------------------------------|--------------------------------|
| Bronze  | Raw data ingestion              | `bronze_fact_sales`, `bronze_dim_customer` |
| Silver  | Cleaned & transformed data      | `silver_salesinfo`             |
| Gold    | Aggregated & analytics-ready    | KPI tables, dashboards         |

### DBT Project Structure
DBT_Data_Project_Main/
├── dbt_project.yml # Project configuration
├── models/
│ ├── bronze/ # Raw ingestion
│ ├── silver/ # Cleansed & enriched data
│ └── gold/ # Aggregated business metrics
├── macros/ # Reusable SQL snippets
├── analyses/ # Ad-hoc queries and exploration
├── seeds/ # Static/reference data
├── snapshots/ # Slowly Changing Dimensions
└── tests/ # Data quality tests


---

## Setup & Installation

1. **Install DBT and dependencies**
```bash
pip install dbt-core dbt-databricks
