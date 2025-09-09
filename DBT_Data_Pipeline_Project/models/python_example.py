import pandas as pd

def model(dbt, session):
    # This is a sample Python model in dbt
    # It creates a simple DataFrame

    data = {
        'id': [1, 2, 3],
        'name': ['Alice', 'Bob', 'Charlie']
    }

    df = pd.DataFrame(data)

    return df
