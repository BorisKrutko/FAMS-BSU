class Student():
    def __init__(self, id: int, fio: str, marks: list[int]) -> None:
        if id is None or not isinstance(id, int) or id <= 0:
            raise ValueError(f"Invalid student ID: {id!r}")
        
        if not fio or not isinstance(fio, str):
            raise ValueError("FIO must be a non-empty string.")
        
        if not all(isinstance(mark, int) and 0 <= mark <= 100 for mark in marks):
            raise ValueError("All marks must be integers between 0 and 100.")
        
        self.id = id
        self.fio = fio
        self.marks = marks

    def average_mark(self) -> float:
        return sum(self.marks) / len(self.marks) if self.marks else 0.0
    
    def __eq__(self, other):
        if not isinstance(other, Student):
            return NotImplemented
        return (self.id == other.id and 
                self.fio == other.fio and 
                self.marks == other.marks)
    
    