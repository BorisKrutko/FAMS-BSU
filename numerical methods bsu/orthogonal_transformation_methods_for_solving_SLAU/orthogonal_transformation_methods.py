import numpy as np
import math
import sys


class OrthogonalTransformations:
    a: np.ndarray
    d: np.ndarray
    c: np.ndarray
    f: np.ndarray
    n: int
    x: np.ndarray

    def __init__(self, a, d, c, b):
        self.a = np.array(a, dtype=float).copy() 
        self.d = np.array(d, dtype=float).copy() 
        self.c = np.array(c, dtype=float).copy() 
        self.b = np.array(b, dtype=float).copy()
        self.n = len(d)
        self.temp = np.zeros(len(d) - 2)
        self.x = np.zeros(self.n)

    def straight_move(self):
        for i in range(self.n-1):
            r = math.sqrt(self.a[i] ** 2 + self.d[i] ** 2)
            
            if r <= sys.float_info.epsilon:
                print("Матрица вырождена на шаге: ", i)
                return False
            
            cos = self.d[i] / r
            sin = self.a[i] / r
            c_i = self.c[i]
            b_i1 = self.d[i+1]

            # i 
            self.d[i] = r
            self.a[i] = 0
            self.c[i] = cos * c_i + sin * b_i1

            if i < self.n - 2:
                self.temp[i] = sin * self.c[i+1]

            # i+1
            self.d[i+1] = -sin * c_i + cos * b_i1

            if i < self.n - 2:
                self.c[i+1] = cos * self.c[i+1]

            f_i = self.b[i]
            self.b[i] = cos * f_i + sin * self.b[i+1]
            self.b[i+1] = -sin * f_i + cos * self.b[i+1]

        return True
    
    def reverse_stroke(self):        
        for i in range(self.n - 1, -1, -1):
            if abs(self.d[i]) <= sys.float_info.epsilon:
                print(f"Матрица вырождена на обратном ходе, b[{i}] = 0")
                return None
            
            t_sum = 0.0

            if i < self.n - 1:
                t_sum += self.c[i] * self.x[i+1]

            if i < self.n - 2:
                t_sum += self.temp[i] * self.x[i+2]

            self.x[i] = (self.b[i] - t_sum) / self.d[i]

    def print_x(self):
        print(self.x)

    def get_residual_norm(self, a, d, c, b):
        A = np.diag(d) \
            + np.diag(c, 1) \
            + np.diag(a, -1)
        r = b - A @ self.x 

        r2 = np.linalg.norm(r, 2)     
        r_inf = np.linalg.norm(r, np.inf)

        return r2, r_inf

