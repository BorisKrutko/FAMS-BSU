import numpy as np
import math

def tridiagonalize_householder(A: np.ndarray) -> np.ndarray:
    n = A.shape[0]

    if not np.allclose(A, A.T):
        raise ValueError("A должна быть симметричной")

    for k in range(n - 2):
        x = A[k+1:, k]   

        if np.linalg.norm(x) < 1e-10:
            continue
        
        norm_x = np.linalg.norm(x)

        if x[0] >= 0:
            alpha = -norm_x
        else:
            alpha = norm_x

        u = x.copy()
        u[0] -= alpha 

        v = u / np.linalg.norm(u)
        v = v.reshape(-1, 1)

        # A' = (I - 2vvT) A (I - 2vvT)
        A_sub = A[k+1:, k+1:]

        # H * A = (I - 2vvT)A = A - 2 v (vT A)
        A[k+1:, k+1:] -= 2 * v @ (v.T @ A_sub)

        # A * H = A (I - 2vvT) = A - 2 (A v) vT
        A[k+1:, k+1:] -= 2 * (A_sub @ v) @ v.T

        A[k+2:, k] = 0.0
        A[k, k+2:] = 0.0
        A[k+1, k] = alpha
        A[k, k+1] = alpha
    return A

def qr_eigenvalues(T: np.ndarray, eps: float):
    n = T.shape[0]
    k = 0

    while True:
        Q, R = qr_decomposition_tridiagonal(T)
        T = R @ Q

        k += 1
        
        last_it = True
        for i in range(1, n):  
            if abs(T[i, i-1]) > eps:
                last_it = False
                break

        if last_it:
            break

    return [T[i, i] for i in range(T.shape[0])]

def qr_decomposition_tridiagonal(T: np.ndarray):
    n = T.shape[0]
    Q = np.eye(n)
    R = T.copy()

    for i in range(n-1):
        a = R[i, i]
        b = R[i+1, i]

        if abs(b) < 1e-15:
            continue

        r = math.sqrt(a ** 2 + b ** 2)
        c = a / r
        s = b / r

        G = np.array([[c, -s], [s, c]])
        R[i:i+2, i:i+3] = G.T @ R[i:i+2, i:i+3]

        R[i+1, i] = 0.0
        Q[:, i:i+2] = Q[:, i:i+2] @ G

    return Q, R