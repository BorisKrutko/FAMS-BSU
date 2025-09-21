from lab.io_utils import get_class_from_csv, save_top_n_students_to_csv
from lab.models import Class, Student
import os

def main_loop():
    while True:
        uns = input("if you want to upload new csv file, print 'y' else 'n': ")
        if uns not in ['y', 'n']:
            print("print 'y' or 'n'")
            continue
        if uns == 'n':
            break
        
        with_header = input("if csv file has header print '1' else '0': ")

        if with_header not in ['1', '0']:
            print("print '1' or '0'")
            continue
        filename = input("print csv filename: ")

        try:
            current_dir = os.path.dirname(os.path.abspath(__file__))
            parent_dir = os.path.abspath(os.path.join(current_dir, os.pardir))
            file_path = os.path.join(parent_dir, 'data', filename)
            my_class = get_class_from_csv(file_path=file_path, has_header=with_header)
        except Exception as e:
            print(f"Error loading class from CSV: {e}")
            continue

        if my_class is None:
            print("Failed to load class from CSV.")
            continue    
        print(f"Loaded class with {len(my_class.students)} students.")

        while True:
            action = input("Choose action: \n(1) Add Student, \n(2) Remove Student, \n(3) View Students, \n(4) sort, \n(5) mark statistics, \n(6) update marks (id), \n(7) export TOP_N students, \n(8) Exit\nchoise number: ")

            if action not in [str(i) for i in range(1, 9)]:
                print("Invalid choice. Please select a valid action.")
                continue

            match action:
                case "1":
                    try:
                        student_id = int(input("Enter student ID: "))
                        fio = input("Enter student FIO: ")
                        marks_input = input("Enter marks separated by spaces: ")
                        marks = [int(mark) for mark in marks_input.split()]
                        new_student = Student(id=student_id, fio=fio, marks=marks)
                        my_class.add_student(new_student)
                        print(f"Student {fio} added successfully.")
                    except ValueError as e:
                        print(f"Error adding student: {e}")

                case "2":
                    try:
                        student_id = int(input("Enter student ID to remove: "))
                        my_class.remove_student_by_id(student_id)
                        print(f"Student with ID {student_id} removed successfully.")
                    except ValueError as e:
                        print(f"Error removing student: {e}")
                
                case "3":
                    if not my_class.students:
                        print("No students in the class.")
                    else:
                        for student in my_class.students:
                            print(f"ID: {student.id}, FIO: {student.fio}, Marks: {student.marks}, Average Mark: {student.average_mark():.2f}")

                case "4":
                    sort_key = input("Enter sort key (id, average_mark, fio): ")
                    try:
                        sorted_students = my_class.get_sorted_students(sort_key)
                        for student in sorted_students:
                            print(f"ID: {student.id}, FIO: {student.fio}, Marks: {student.marks}, Average Mark: {student.average_mark():.2f}")
                    except ValueError as e:
                        print(f"Error sorting students: {e}")

                case "5":
                    if not my_class.students:
                        print("No students in the class.")
                    else:
                        # Number of students
                        student_count = len(my_class.students)
                        print(f"Number of students: {student_count}")

                        # Class average mark
                        avg_mark = my_class.group_average_mark()
                        print(f"Class Average Mark: {avg_mark:.2f}")

                        # Best and worst students
                        sorted_by_mark = my_class.get_sorted_students(key="average_mark")
                        
                        best_student = sorted_by_mark[0]
                        worst_student = sorted_by_mark[-1]

                        print(f"Best student: {best_student.fio} (Average Mark: {best_student.average_mark():.2f})")
                        print(f"Worst student: {worst_student.fio} (Average Mark: {worst_student.average_mark():.2f})")

                case "6":
                    try:
                        for st in my_class.students:
                            print(f"ID: {st.id}, FIO: {st.fio}, Marks: {st.marks}, Average Mark: {st.average_mark():.2f}")

                        student_id = int(input("Enter student ID to update marks: "))
                        student = my_class.get_student_by_id(student_id)
                        if student:
                            marks_input = input("Enter new marks separated by spaces: ")
                            new_marks = []
                            mark_strings = marks_input.split()

                            for mark_str in mark_strings:
                                try:
                                    mark = int(mark_str)
                                    
                                    if not 0 <= mark <= 100:
                                        raise ValueError(f"Mark '{mark_str}' is out of the valid range (0-100).")
                                    
                                    new_marks.append(mark)
                                    
                                except ValueError:
                                    raise ValueError(f"Invalid mark '{mark_str}'. All marks must be numbers.")
                        
                            student.marks = new_marks
                            print(f"Marks for student ID {student_id} updated successfully.")
                        else:
                            print(f"No student found with ID {student_id}.")    
                    except ValueError as e:
                        print(f"Error updating marks: {e}")

                case "7":
                    try:
                        n = int(input("Enter number of top students to export: "))
                        top_students = my_class.get_top_n_students(n)
                        export_filename = input("Enter filename to export top students (e.g., top_students.csv): ")
                        current_dir = os.path.dirname(os.path.abspath(__file__))
                        parent_dir = os.path.abspath(os.path.join(current_dir, os.pardir))
                        file_path = os.path.join(parent_dir, 'data', export_filename)
                        save_top_n_students_to_csv(file_path, top_students, with_header=True)
                    except ValueError as e:
                        print(f"Error exporting top students: {e}")
                    except Exception as e:
                        print(f"Unexpected error: {e}")
                
                case "8":
                    print("Exiting to main menu.")  
                    break
            

if __name__ == "__main__":
    main_loop()