from unittest.mock import patch, MagicMock
import pytest
import os

from lab.io_utils.csv_reader import get_class_from_csv
from lab.models import Student, Class

@pytest.fixture
def data_dir(tmp_path):
    """This fixture creates a temporary directory for tests."""
    data_path = tmp_path / "data"
    data_path.mkdir()
    return data_path

# --- Positive Test Cases (Valid CSVs) ---

def test_valid_csv_with_header(data_dir):
    """Test a valid CSV file with a header row."""
    csv_content = "id,name,grade1,grade2\n1,Alice,90,85\n2,Bob,75,80"
    file_path = data_dir / 'valid_with_header.csv'
    file_path.write_text(csv_content)
    # Pass the full path to the function
    result = get_class_from_csv(str(file_path), has_header=True)
    expected_students = [
        Student(id=1, fio='Alice', marks=[90, 85]),
        Student(id=2, fio='Bob', marks=[75, 80])
    ]
    assert result.students == expected_students

def test_valid_csv_without_header(data_dir):
    """Test a valid CSV file without a header row."""
    csv_content = "1,Alice,90,85\n2,Bob,75,80"
    file_path = data_dir / 'valid_no_header.csv'
    file_path.write_text(csv_content)
    result = get_class_from_csv(str(file_path), has_header=False)
    expected_students = [
        Student(id=1, fio='Alice', marks=[90, 85]),
        Student(id=2, fio='Bob', marks=[75, 80])
    ]
    assert result.students == expected_students
    
# --- Error Handling & Edge Cases ---

def test_csv_with_errors(data_dir, capsys):
    csv_content = "id,name,grade1,grade2\n1,Alice,90,101\n2,Bob,75,not_a_number\nthree,Charlie,88,92"
    file_path = data_dir / 'errors.csv'
    file_path.write_text(csv_content)
    
    result = get_class_from_csv(str(file_path), has_header=True)
    expected_students = [
        Student(id=1, fio='Alice', marks=[90]),  
        Student(id=2, fio='Bob', marks=[75])   
    ]
    
    assert result.students == expected_students
    captured = capsys.readouterr()
    
    assert "Skipping row due to error: invalid literal for int() with base 10: 'three'" in captured.out

def test_empty_csv(data_dir, capsys):
    """Test an empty CSV file."""
    empty_file = data_dir / 'empty.csv'
    empty_file.write_text('')
    result = get_class_from_csv(str(empty_file), has_header=True)
    assert result is None
    captured = capsys.readouterr()
    assert "Error processing CSV: CSV file is empty." in captured.out

def test_file_not_found(data_dir, capsys):
    """file does not exist."""
    result = get_class_from_csv(str(data_dir / 'non_existent.csv'), has_header=True)
    assert result is None
    captured = capsys.readouterr()
    assert "Error: The file 'non_existent.csv' was not found." in captured.out

def test_csv_with_less_than_two_columns(data_dir, capsys):
    """Test a CSV where rows do not have the minimum required columns (id, name)."""
    csv_content = "id\n1"
    file_path = data_dir / 'less_columns.csv'
    file_path.write_text(csv_content)
    result = get_class_from_csv(str(file_path), has_header=True)
    assert result is None
    captured = capsys.readouterr()
    assert "Error processing CSV: Each row must have at least two columns (id and name)." in captured.out

def test_csv_with_missing_required_columns(data_dir, capsys):
    """Test a CSV where a required header column ('id' or 'name') is missing."""
    csv_content = "student_id,fio,grade1\n1,Alice,90"
    file_path = data_dir / 'missing_cols.csv'
    file_path.write_text(csv_content)
    result = get_class_from_csv(str(file_path), has_header=True)
    assert result is None
    captured = capsys.readouterr()
    assert "Error processing CSV: Missing required column in header: 'id'" in captured.out

def test_empty_csv_with_header(data_dir):
    """Test a CSV file that has a header but no data rows."""
    csv_content = "id,name,grade1\n"
    file_path = data_dir / 'header_only.csv'
    file_path.write_text(csv_content)
    result = get_class_from_csv(str(file_path), has_header=True)
    assert isinstance(result, Class)
    assert result.students == []