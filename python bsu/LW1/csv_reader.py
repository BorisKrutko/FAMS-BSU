from typing import Tuple, List, Optional
import csv 

def read_csv(
        filename: str, 
        with_header: bool = True,
        delimiter: str = "," 
    ) -> Tuple[Optional[List[str]], List[str]]:

    with open(file=filename, mode='r', encoding='utf-8', newline='') as f:
        reader = csv.reader(f, delimiter=delimiter)
        rows = list(reader)

        if not rows:
            return (None, [])
        
        if with_header:
            header = rows[0]
            data = rows[1:]
        else:
            col_count = max(len(len(row) for row in rows))
            
            if col_count < 2:
                raise ValueError(f"this csv file have max {col_count} col")
            
            header = ["id", "fio"]
            header += [f"col{i+1}" for i in range(col_count-2)]

        max_len = len(header)
        normalized_data = [row + ["" for _ in range(max_len - len(row))] for row in data]   

    return((header, normalized_data))

            

     