from lab.models import Class, Student
from typing import Tuple, List, Optional
import csv 
import os

def get_class_from_csv(file_path: str, has_header: bool) -> Class | None:
    try:
        with open(file_path, 'r', newline='', encoding='utf-8') as f:
            # read rows to analyze
            sample = f.read(1024)
            sniffer = csv.Sniffer()
            
            try:
                dialect = sniffer.sniff(sample)
            except csv.Error:
                # Fall back to the default delimiter if sniffing fails
                dialect = 'excel'
            
            f.seek(0)
            reader = csv.reader(f, dialect)
        
            rows = list(reader)
            if not rows:
                raise ValueError("CSV file is empty.")

            max_count = max(len(row) for row in rows)
            min_count = min(len(row) for row in rows)

            if min_count < 2:
                raise ValueError("Each row must have at least two columns (id and name).") 

            if has_header:
                header = rows[0]
                data = rows[1:]
            else:
                data = rows
                # Auto-generate header for files without one
                if data:
                    header = ["id", "name"] + [f"grade{i + 1}" for i in range(max_count - 2)]
                else:
                    header = []

        if header:
            max_len = len(header)
            normalized_data = [row + [""] * (max_len - len(row)) for row in data]
        else:
            normalized_data = data

        return _get_class(header, normalized_data)

    except FileNotFoundError:
        print(f"Error: The file '{os.path.basename(file_path)}' was not found.")
        return None
    except ValueError as e:
        print(f"Error processing CSV: {e}")
        return None
    except csv.Error as e:
        print(f"Error reading CSV: {e}")
        return None
    except Exception as e:
        print(f"An unexpected error occurred: {e}")
        return None
    

def _get_class(headers: List[str], data: List[List[str]]) -> Class:
    try:
        id_index = headers.index("id")
        fio_index = headers.index("name") 
    except ValueError as e:
        raise ValueError(f"Missing required column in header: {e}")
    
    students = []
    for row in data:
        try:
            student_id = int(row[id_index])
            fio = row[fio_index]

            marks = []
            for i, mark_str in enumerate(row):
                if i not in (id_index, fio_index) and mark_str.isdigit():
                    mark = int(mark_str)
                    if 0 <= mark <= 100:
                        marks.append(mark)
                        
            student = Student(id=student_id, fio=fio, marks=marks)
            students.append(student)
        except ValueError as e:
            print(f"Skipping row due to error: {e}")
            continue

    return Class(students=students)

    

            

     