import time
from lu_matrix_processor import LUMatrixProcessor
import numpy as np


max_n = 8000         
repeats = 3          
times = np.zeros(max_n) 

for n in range(3600, 3601):
    A = np.random.rand(n, n)
    b = np.random.rand(n)

    with open("data/in_x.txt", "w") as f:
        np.savetxt(f, A, fmt="%.16f", delimiter=" ")
        f.write("\n")  
        np.savetxt(f, [b], fmt="%.16f", delimiter=" ")

    run_times = []
    for _ in range(repeats):
        start_time = time.perf_counter()

        processor = LUMatrixProcessor()
        processor.linalg_solve("data/in_x.txt", "data/out_x.txt")

        end_time = time.perf_counter()
        run_times.append(end_time - start_time)

    avg_time = np.mean(run_times)
    times[n - 1] = avg_time

    print(f"Размерность: {n}, среднее время: {avg_time:.6f} сек")
    np.savetxt("data/times.txt", times, fmt="%.8f")

np.savetxt("data/times.txt", times, fmt="%.8f")
print("\nСредние времена сохранены в data/times.txt")
