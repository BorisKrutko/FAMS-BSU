from typing import List
import numpy as np


class MatrixFileManager:
    def read(self, filename: str):
        A: List[List[float]] = []
        b: List[float] = []
        is_b = False

        with open(filename) as file:
            for line in file:
                line = line.strip()
                if not line:
                    is_b = True
                    continue

                try:
                    row = [float(num) for num in line.split()]
                    if not is_b:
                        A.append(row)
                    else:
                        b.append(row)

                except Exception as e:
                    print(f"cant read metrix from this file, error: {e}")

        return np.array(A, dtype=float), np.array(b, dtype=float)
    
    def write(self, filename: str, **kwargs):
        with open(filename, 'w') as file:
            for key, v in kwargs.items():
                file.write(f"\n========{key}=======\n")
                
                if v.ndim == 1:  
                    file.write(" ".join(f"{el:10.3f}" for el in v) + "\n")
                elif v.ndim == 2:  
                    for row in v:
                        file.write(" ".join(f"{el:10.3f}" for el in row) + "\n")


        
                    