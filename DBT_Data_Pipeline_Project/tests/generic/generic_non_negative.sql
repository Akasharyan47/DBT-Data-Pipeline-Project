{% test generic_non_negative(model,column)%}

select *
from {{ model }}
where {{ column_name }} < 0

{% endtest %}