import numpy as np
from file_manager import MatrixFileManager
from typing import List, Optional


class LUMatrixProcessor:
    file_manager: MatrixFileManager
    A: np.ndarray
    b: np.ndarray


    def __init__(self):
        self.file_manager: MatrixFileManager = MatrixFileManager()

        self.A = np.array([])
        self.b = np.array([])


    def lu_decomposition(self, filename: str) -> tuple[np.ndarray, np.ndarray, np.ndarray]:
        self.A, self.b = self.file_manager.read(filename)
        size_A = len(self.A)
        
        U = np.array(self.A, dtype=float)
        L = np.eye(size_A)
        p = np.arange(size_A)

        for i in range(size_A):
            imax = self._find_ind_max_el(i, size_A, U)

            if imax != i:
                U[[i, imax]] = U[[imax, i]]
                p[[i, imax]] = p[[imax, i]]
                
                if i > 0:
                    L[[i, imax], :i] = L[[imax, i], :i]

            for j in range(i+1, size_A):
                L[j, i] = U[j, i] / U[i, i]
                U[j, i:] = U[j, i:] - L[j, i] * U[i, i:]

        return L, U, p
    

    def linalg_solve(self, in_filename, out_filename):
        L, U, p = self.lu_decomposition(in_filename)

        b_permuted = self.b.flatten()[p]
        y = self._solve_triangular(L, b_permuted, False)
        x = self._solve_triangular(U, y, True)

        self.file_manager.write(out_filename, L=L, U=U, p=p, x=x)
            
    
    def _find_ind_max_el(self, k: int, size_A: int, U: np.ndarray):
        max_el = abs(U[k, k])
        imax = k
        for l in range(k+1, size_A):
            new_max = max(max_el, abs(U[l, k]))
            if new_max != max_el:
                imax = l
                max_el = new_max

        return imax


    def _solve_triangular(self, U: np.ndarray, y: np.ndarray, is_upper_tr: bool) -> np.ndarray:
        size_A = len(U)
        x = np.zeros(size_A)

        if is_upper_tr:
            # обратный ход 
            for j in range(size_A - 1, -1, -1):
                x[j] = (y[j] - np.dot(U[j], x)) / U[j, j]
        else:
            # прямой ход 
            for j in range(size_A):
                x[j] = (y[j] - np.dot(U[j], x)) / U[j, j]

        return x



