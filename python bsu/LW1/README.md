# cteate venv
python -m venv venv
venv\Scripts\activate

# download requirements
pip install -r requirements-dev.txt

# run cli
python -m lab.main

# tests
python -m pytest