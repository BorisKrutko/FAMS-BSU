import pytest
from lab.models import Student, Class

@pytest.fixture
def students_list():
    return [
        Student(id=1, fio="Alice", marks=[85, 90, 78]),
        Student(id=2, fio="Bob", marks=[95, 88, 92]),
        Student(id=3, fio="Charlie", marks=[70, 75, 80]),
        Student(id=4, fio="david", marks=[90, 95, 100])
    ]

@pytest.fixture
def empty_class():
    return Class(students=[])

@pytest.fixture
def populated_class(students_list):
    return Class(students=students_list)



def test_group_average_mark(populated_class):
    """test the average mark for a populated class."""
    expected_average = (84.33 + 91.67 + 75.0 + 95.0) / 4
    assert pytest.approx(populated_class.group_average_mark(), 0.01) == expected_average

def test_group_average_mark_empty(empty_class):
    """test the average mark for an empty class."""
    assert empty_class.group_average_mark() == 0.0

def test_add_student(empty_class):
    new_student = Student(id=5, fio="Eve", marks=[100, 99])
    empty_class.add_student(new_student)
    assert len(empty_class.students) == 1
    assert empty_class.students[0].id == 5

def test_add_existing_student(populated_class):
    existing_student = Student(id=1, fio="Alice", marks=[100])
    with pytest.raises(ValueError, match="Student with ID 1 already exists."):
        populated_class.add_student(existing_student)

def test_add_students_valid(populated_class):
    new_students = [
        Student(id=5, fio="Eve", marks=[100, 99]),
        Student(id=6, fio="Frank", marks=[80, 85])
    ]
    populated_class.add_students(new_students)
    assert len(populated_class.students) == 6
    assert populated_class.get_student_by_id(5) is not None
    assert populated_class.get_student_by_id(6) is not None

def test_add_students_with_duplicate(populated_class):
    new_students = [
        Student(id=5, fio="Eve", marks=[100, 99]),
        Student(id=2, fio="Bob_duplicate", marks=[80])
    ]
    with pytest.raises(ValueError, match="Student with ID 2 already exists."):
        populated_class.add_students(new_students)
    assert populated_class.get_student_by_id(5) is not None
    assert len(populated_class.students) == 5

def test_remove_student_by_id(populated_class):
    populated_class.remove_student_by_id(2)
    assert len(populated_class.students) == 3
    assert populated_class.get_student_by_id(2) is None

def test_remove_nonexistent_student(populated_class):
    with pytest.raises(ValueError, match="Student with ID 99 doesn’t exist"):
        populated_class.remove_student_by_id(99)

def test_get_student_by_id_exists(populated_class):
    student = populated_class.get_student_by_id(2)
    assert student is not None
    assert student.fio == "Bob"

def test_get_student_by_id_nonexistent(populated_class):
    student = populated_class.get_student_by_id(99)
    assert student is None

def test_get_sorted_students_by_id(populated_class):
    sorted_students = populated_class.get_sorted_students(key="id")
    expected_order = [1, 2, 3, 4]
    assert [s.id for s in sorted_students] == expected_order

def test_get_sorted_students_by_fio(populated_class):
    sorted_students = populated_class.get_sorted_students(key="fio")
    expected_order = ["Alice", "Bob", "Charlie", "david"]
    assert [s.fio for s in sorted_students] == expected_order

def test_get_sorted_students_by_average_mark(populated_class):
    sorted_students = populated_class.get_sorted_students(key="average_mark")
    # david (95), Bob (91.67), Alice (84.33), Charlie (75)
    expected_order = ["david", "Bob", "Alice", "Charlie"]
    assert [s.fio for s in sorted_students] == expected_order
    
def test_get_sorted_students_invalid_key(populated_class):
    with pytest.raises(ValueError, match="it`s not avaliable key to sort students"):
        populated_class.get_sorted_students(key="invalid_key")

def test_get_top_n_students_valid(populated_class):
    top_2 = populated_class.get_top_n_students(2)
    expected_order = ["david", "Bob"]
    assert len(top_2) == 2
    assert [s.fio for s in top_2] == expected_order

def test_get_top_n_students_valid_n_one(populated_class):
    top_1 = populated_class.get_top_n_students(1)
    assert len(top_1) == 1
    assert top_1[0].fio == "david"