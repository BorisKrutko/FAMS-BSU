import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv("C:\\git_boris\\demo\\my_projects\\ML\\organisations.csv")
features = pd.read_csv("C:\\git_boris\\demo\\my_projects\\ML\\features.csv")
rubrics = pd.read_csv("C:\\git_boris\\demo\\my_projects\\ML\\rubrics.csv")

# Используем метод head() для объекта DataFrame
print(data.head())
feature_dict = pd.Series(features.feature_name.values, 
                         index=features.feature_id).to_dict()
rubric_dict = pd.Series(rubrics.rubric_name.values, 
                        index=rubrics.rubric_id).to_dict()
print("Feature Dictionary:", list(feature_dict.items()))
print("Rubric Dictionary:", list(rubric_dict.items()))

