import csv
from typing import List
import os
from lab.models import Student

def export_topn_students_to_csv(file_path: str, students: list[Student], with_header: bool = True) -> None:
    try:
        with open(file_path, 'w', newline='', encoding='utf-8') as f:
            writer = csv.writer(f)

            if with_header:
                max_marks = max((len(student.marks) for student in students), default=0)
                header = ["id", "name", "average_mark"] + [f"grade{i + 1}" for i in range(max_marks)]
                writer.writerow(header)

            for student in students:
                row = [student.id, student.fio, student.average_mark()] + student.marks
                writer.writerow(row)
    except Exception as e:
        print(f"Error exporting to CSV: {e}")