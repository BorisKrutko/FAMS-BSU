class RasterLogic:
    def step_by_step(self, x1, y1, x2, y2):
        """ Пошаговый алгоритм """
        points = []
        if x1 == x2 and y1 == y2:
            return [(x1, y1)]
            
        dx = x2 - x1
        dy = y2 - y1
        
        # Выбор ведущей оси
        if abs(dx) >= abs(dy):
            if x1 > x2: x1, x2, y1, y2 = x2, x1, y2, y1
            k = dy / dx if dx != 0 else 0
            b = y1 - k * x1
            for x in range(x1, x2 + 1):
                y = k * x + b
                points.append((x, round(y)))
        else:
            if y1 > y2: x1, x2, y1, y2 = x2, x1, y2, y1
            k = dx / dy if dy != 0 else 0
            b = x1 - k * y1
            for y in range(y1, y2 + 1):
                x = k * y + b
                points.append((round(x), y))
        return points

    def dda(self, x1, y1, x2, y2):
        """ Алгоритм ЦДА """
        points = []
        dx = x2 - x1
        dy = y2 - y1
        
        steps = max(abs(dx), abs(dy))
        if steps == 0: return [(x1, y1)]
        
        x_inc = dx / steps
        y_inc = dy / steps
        
        x = x1
        y = y1
        
        for _ in range(steps + 1):
            points.append((round(x), round(y)))
            x += x_inc
            y += y_inc
            
        return points

    def bresenham_line(self, x1, y1, x2, y2):
        """ Алгоритм Брезенхема для линии """
        points = []
        dx = abs(x2 - x1)
        dy = abs(y2 - y1)
        sx = 1 if x1 < x2 else -1
        sy = 1 if y1 < y2 else -1
        err = dx - dy
        
        curr_x, curr_y = x1, y1
        
        while True:
            points.append((curr_x, curr_y))
            if curr_x == x2 and curr_y == y2:
                break
            e2 = 2 * err
            if e2 > -dy:
                err -= dy
                curr_x += sx
            if e2 < dx:
                err += dx
                curr_y += sy
        return points

    def bresenham_circle(self, xc, yc, r):
        """ Алгоритм Брезенхема для окружности """
        points = []
        x = 0
        y = r
        d = 3 - 2 * r
        
        def add_symmetry(cx, cy, tx, ty):
            return [
                (cx + tx, cy + ty), (cx - tx, cy + ty),
                (cx + tx, cy - ty), (cx - tx, cy - ty),
                (cx + ty, cy + tx), (cx - ty, cy + tx),
                (cx + ty, cy - tx), (cx - ty, cy - tx)
            ]

        while y >= x:
            points.extend(add_symmetry(xc, yc, x, y))
            x += 1
            if d > 0:
                y -= 1
                d = d + 4 * (x - y) + 10
            else:
                d = d + 4 * x + 6
                
        return points