from orthogonal_transformation_methods import OrthogonalTransformations
import numpy as np

a1 = np.array([3, 2, 1])
d1 = np.array([2, 5, 3, 2])
c1 = np.array([1, 1, 1])
b1 = np.array([4, 3, -4, 7])


cl = OrthogonalTransformations(a1, d1, c1, b1)
cl.straight_move()
cl.reverse_stroke()
cl.print_x()
print(cl.get_residual_norm(a1, d1, c1, b1))

