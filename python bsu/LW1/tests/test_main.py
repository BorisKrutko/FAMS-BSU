import io
import pytest
from unittest.mock import patch
from lab.models import Class, Student
import lab.main as main


@pytest.fixture
def my_class():
    return Class(students=[Student(id=1, fio="Test Student", marks=[90, 80])])

def test_add_new_student_success(my_class):
    user_inputs = [
        "y",             # start
        "1",             # file w header
        "some_file.csv", # uload file
        "1",             # add student
        "2",             # ID 
        "New Student",   # fio
        "100 95 98",     # marks
        "8",             # exit
        "n"              # exit main loop
    ]

    # Используем patch для имитации ввода
    with patch('builtins.input', side_effect=user_inputs), \
         patch('lab.main.get_class_from_csv', return_value=my_class):
        
        main.main_loop()
        
        new_student = my_class.get_student_by_id(2)
        assert new_student is not None
        assert new_student.fio == "New Student"
        
