import pandas as pd
from collections import Counter
from sklearn.model_selection import train_test_split
from sklearn.base import ClassifierMixin
import csv

# Load data
data = pd.read_csv("C:\\git_boris\\demo\\my_projects\\ML\\organisations.csv")
features = pd.read_csv("C:\\git_boris\\demo\\my_projects\\ML\\features.csv")
rubrics = pd.read_csv("C:\\git_boris\\demo\\my_projects\\ML\\rubrics.csv")

# Create dictionaries for features and rubrics
feature_dict = pd.Series(features.feature_name.values, index=features.feature_id).to_dict()
rubric_dict = pd.Series(rubrics.rubric_name.values, index=rubrics.rubric_id).to_dict()

# Data Cleaning
clean_data = data.dropna(subset=["average_bill"])
clean_data = clean_data[clean_data["average_bill"] <= 2500]

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.base import BaseEstimator, ClassifierMixin
import csv

clean_data['modified_features'] = clean_data["rubrics_id"].astype(str) + 'q' + clean_data['features_id'].astype(str)

clean_data_train, clean_data_test = train_test_split(clean_data, stratify=clean_data['average_bill'], test_size=0.33, random_state=42)

# Инициализация уникальных значений
unique_f = clean_data_train['modified_features'].unique()
clean_data_test['modified_features'] = clean_data_test['modified_features'].apply(lambda x: x if x in unique_f else 'other')

global_median = clean_data_train['average_bill'].median()

# Пользовательский классификатор
class RubricCityMedianClassifier(BaseEstimator, ClassifierMixin):
    def __init__(self):
        self.counter = dict()
        self.global_median = global_median

    def fit(self, X, y):
        data = X.copy()
        data['target'] = y
        
        groups = data.groupby(['modified_features'])['target'].median()
        self.counter = groups.to_dict()
        return self

    def predict(self, X):
        predictions = []
        for index, row in X.iterrows():
            prediction = self.counter.get(row['modified_features'], self.global_median)  # Используем global_median, если не найдено
            predictions.append([index, int(prediction)])    
        return predictions

classifier = RubricCityMedianClassifier()

classifier.fit(clean_data_train[['modified_features']], clean_data_train['average_bill'])

predictions = classifier.predict(clean_data_test[['modified_features']])

# Создание DataFrame из предсказаний
dataframe = pd.DataFrame(predictions)

# Сохранение в CSV без кавычек
dataframe.to_csv('unser.csv', index=False, header=False, quoting=csv.QUOTE_NONE, escapechar='\\')