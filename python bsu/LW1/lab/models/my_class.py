from lab.models.student import Student

class Class():
    def __init__(self, students: list[Student]) -> None:
        self.students = students

    def group_average_mark(self) -> float:
        total_marks = sum(student.average_mark() for student in self.students)
        return total_marks / len(self.students) if self.students else 0.0
    
    def add_student(self, student: Student) -> None:
        if not any(s.id == student.id for s in self.students):
            self.students.append(student)
        else:
            raise ValueError(f"Student with ID {student.id} already exists.") 

    def add_students(self, students: list[Student]) -> None:
        for st in students:
            self.add_student(st)

    def remove_student_by_id(self, student_id: int) -> None:
        if not any(s.id == student_id for s in self.students):
            raise ValueError(f"Student with ID {student_id} doesn’t exist")

        self.students = [student for student in self.students if student.id != student_id]

    def get_student_by_id(self, student_id: int) -> Student | None:
        for student in self.students:
            if student.id == student_id:
                return student
        return None
    
    def get_sorted_students(self, key: str) -> list[Student]:
        match key:
            case "id":
                return sorted(self.students, key=lambda s: s.id)
            case "average_mark":
                return sorted(self.students, key=lambda s: (s.average_mark(), s.fio), reverse=True)                 
            case "fio":
                return sorted(self.students, key=lambda s: s.fio)
            case _:
                raise ValueError(f"it`s not avaliable key to sort students") 
                    
    def get_top_n_students(self, n: int) -> list[Student]:
        if n <= 0 or n > len(self.students):
            raise ValueError(f"n must be between 1 and the number of students ({len(self.students)})")
        return sorted(self.students, key=lambda s: (s.average_mark(), s.fio), reverse=True)[:n]
    
    def get_filter_students(self, key: str, value: str) -> list[Student]:
        match key:
            case "average_mark":
                try:
                    mark = int(value)
                except ValueError:
                    raise ValueError(f"Expected integer for average_mark, got {value!r}")
                return [st for st in self.students if st.average_mark() == mark]              
            case "fio":
                return [st for st in self.students if value.lower() in st.fio.lower()]
            case _:
                raise ValueError(f"it`s not avaliable key to sort students")  

               

    
