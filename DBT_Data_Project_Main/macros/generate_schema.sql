{% macro generate_schema(schema_name, table_name, columns) %}
  {% set sql %}
    CREATE SCHEMA IF NOT EXISTS {{ schema_name }};

    CREATE TABLE IF NOT EXISTS {{ schema_name }}.{{ table_name }} (
      {% for column in columns %}
        {{ column.name }} {{ column.type }}{% if column.nullable %} NULL{% else %} NOT NULL{% endif %}{% if column.default %} DEFAULT {{ column.default }}{% endif %}{% if not loop.last %},{% endif %}
      {% endfor %}
    );
  {% endset %}
  {{ return(sql) }}
{% endmacro %}

{% macro generate_view_schema(schema_name, view_name, select_statement) %}
  {% set sql %}
    CREATE SCHEMA IF NOT EXISTS {{ schema_name }};

    CREATE OR REPLACE VIEW {{ schema_name }}.{{ view_name }} AS
    {{ select_statement }};
  {% endset %}
  {{ return(sql) }}
{% endmacro %}

{% macro generate_incremental_schema(schema_name, table_name, columns, unique_key) %}
  {% set sql %}
    CREATE SCHEMA IF NOT EXISTS {{ schema_name }};

    CREATE TABLE IF NOT EXISTS {{ schema_name }}.{{ table_name }} (
      {% for column in columns %}
        {{ column.name }} {{ column.type }}{% if column.nullable %} NULL{% else %} NOT NULL{% endif %}{% if column.default %} DEFAULT {{ column.default }}{% endif %}{% if not loop.last %},{% endif %}
      {% endfor %}
    );

    -- Add unique constraint if specified
    {% if unique_key %}
      ALTER TABLE {{ schema_name }}.{{ table_name }} ADD CONSTRAINT {{ table_name }}_pk PRIMARY KEY ({{ unique_key }});
    {% endif %}
  {% endset %}
  {{ return(sql) }}
{% endmacro %}

{% macro generate_fact_table_schema(schema_name, table_name, dimensions, measures) %}
  {% set sql %}
    CREATE SCHEMA IF NOT EXISTS {{ schema_name }};

    CREATE TABLE IF NOT EXISTS {{ schema_name }}.{{ table_name }} (
      -- Surrogate key
      {{ table_name }}_sk BIGINT IDENTITY(1,1) PRIMARY KEY,

      -- Dimension foreign keys
      {% for dim in dimensions %}
        {{ dim.name }}_sk BIGINT{% if not loop.last %},{% endif %}
      {% endfor %},

      -- Measures
      {% for measure in measures %}
        {{ measure.name }} {{ measure.type }}{% if not loop.last %},{% endif %}
      {% endfor %},

      -- Metadata
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
  {% endset %}
  {{ return(sql) }}
{% endmacro %}

{% macro generate_dimension_schema(schema_name, table_name, columns, scd_type='type1') %}
  {% set sql %}
    CREATE SCHEMA IF NOT EXISTS {{ schema_name }};

    CREATE TABLE IF NOT EXISTS {{ schema_name }}.{{ table_name }} (
      -- Surrogate key
      {{ table_name }}_sk BIGINT IDENTITY(1,1) PRIMARY KEY,

      -- Natural key
      {% for column in columns if column.is_natural_key %}
        {{ column.name }} {{ column.type }} NOT NULL{% if not loop.last %},{% endif %}
      {% endfor %},

      -- Attributes
      {% for column in columns if not column.is_natural_key %}
        {{ column.name }} {{ column.type }}{% if column.nullable %} NULL{% else %} NOT NULL{% endif %}{% if not loop.last %},{% endif %}
      {% endfor %},

      -- SCD metadata
      {% if scd_type == 'type2' %}
        effective_start_date DATE NOT NULL,
        effective_end_date DATE,
        is_current BOOLEAN DEFAULT TRUE,
      {% endif %}

      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Add unique constraint on natural key for type 1 SCD
    {% if scd_type == 'type1' %}
      ALTER TABLE {{ schema_name }}.{{ table_name }} ADD CONSTRAINT {{ table_name }}_nk_uk UNIQUE (
        {% for column in columns if column.is_natural_key %}
          {{ column.name }}{% if not loop.last %},{% endif %}
        {% endfor %}
      );
    {% endif %}
  {% endset %}
  {{ return(sql) }}
{% endmacro %}

{% macro generate_schema_from_model(model_name, schema_name) %}
  {% set model = graph.nodes.get('model.' + project_name + '.' + model_name) %}
  {% if model %}
    {% set columns = model.columns.values() %}
    {% set sql %}
      CREATE SCHEMA IF NOT EXISTS {{ schema_name }};

      CREATE TABLE IF NOT EXISTS {{ schema_name }}.{{ model_name }} (
        {% for column in columns %}
          {{ column.name }} {{ column.data_type or 'VARCHAR(255)' }}{% if not column.nullable %} NOT NULL{% endif %}{% if not loop.last %},{% endif %}
        {% endfor %}
      );
    {% endset %}
    {{ return(sql) }}
  {% else %}
    {{ return('-- Model ' + model_name + ' not found') }}
  {% endif %}
{% endmacro %}
