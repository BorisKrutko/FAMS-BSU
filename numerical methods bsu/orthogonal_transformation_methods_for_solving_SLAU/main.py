from orthogonal_transformation_methods import OrthogonalTransformations
import numpy as np

a1 = np.array([2, -2, -2])
d1 = np.array([-2, 3, 4, 3])
c1 = np.array([2, 5, 1])
b1 = np.array([2,23,12,6])


cl = OrthogonalTransformations(a1, d1, c1, b1)
cl.straight_move()
cl.reverse_stroke()
cl.print_x()
print(cl.get_residual_norm(a1, d1, c1, b1))


a2 = np.array([-4, 2, -2, 5, -5, -3, 0, 0, 5])
d2 = np.array([4, -6, 4, 3, -2, 1, -6, 2, 3, -4])
c2 = np.array([-3, 4, -4, -1, 4, -4, 0, 4, 2])
b2 = np.array([1,-6,2,-9,-11,-53,-9,6,5,1])


cl = OrthogonalTransformations(a2, d2, c2, b2)
cl.straight_move()
cl.reverse_stroke()
cl.print_x()
print(cl.get_residual_norm(a2, d2, c2, b2))


a3 = np.array([1, 5, -2, 0, 3, 0, 3, 5, -3, -3, 3, 0, 4, 1, -1, -1])
d3 = np.array([5, 3, 6, -6, 6, -2, 3, 5, 6, -2, -6, 5, 2, 4, 1, -2, 4 ])
c3 = np.array([-3, 2, -1, 3, -4, 0, -5, -1, 3, 2, -1, -4, -1, 5, -1, -2])
b3 = np.array([-1,13,24,-15,6,3,-19,52,124,-25,-108,41,12,183,13,-81,52])


cl = OrthogonalTransformations(a3, d3, c3, b3)
if cl.straight_move(): 
    cl.reverse_stroke()
    cl.print_x()
    print(cl.get_residual_norm(a3, d3, c3, b3))
