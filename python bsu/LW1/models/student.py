class Student():
    def __init__(self, id: int, fio: str, marks: list[int]) -> None:
        if id is None or not isinstance(id, int) or id <= 0:
            raise ValueError(f"Invalid student ID: {id!r}")
        
        self.id = id
        self.fio = fio
        self.marks = marks

    def average_mark(self) -> float:
        return sum(self.marks) / len(self.marks) if self.marks else 0.0
    
    