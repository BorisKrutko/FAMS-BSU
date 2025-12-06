# Алгоритм Сазерленда-Коэна (Отрезки)

INSIDE = 0  # 0000
LEFT = 1    # 0001
RIGHT = 2   # 0010
BOTTOM = 4  # 0100
TOP = 8     # 1000

def compute_code(x, y, x_min, y_min, x_max, y_max):
    code = INSIDE
    if x < x_min:      
        code |= LEFT
    elif x > x_max:    
        code |= RIGHT
    if y < y_min:     
        code |= BOTTOM
    elif y > y_max:    
        code |= TOP
    return code

def sutherland_cohen_clip(lines, x_min, y_min, x_max, y_max):
    clipped_lines = []

    for p1, p2 in lines:
        x1, y1 = p1
        x2, y2 = p2
        
        code1 = compute_code(x1, y1, x_min, y_min, x_max, y_max)
        code2 = compute_code(x2, y2, x_min, y_min, x_max, y_max)
        
        accept = False
        
        while True:
            if code1 == 0 and code2 == 0:
                accept = True
                break
            # Если оба конца снаружи с одной стороны (побитовое И не равно 0)
            elif (code1 & code2) != 0:
                break
            # Отрезок пересекает границы
            else:
                # Выбираем точку снаружи
                x, y = 0.0, 0.0
                code_out = code1 if code1 != 0 else code2
                
                # Ищем пересечение. 
                if code_out & TOP:
                    x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1)
                    y = y_max
                elif code_out & BOTTOM:
                    x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1)
                    y = y_min
                elif code_out & RIGHT:
                    y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1)
                    x = x_max
                elif code_out & LEFT:
                    y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1)
                    x = x_min
                
                # Заменяем точку снаружи на точку пересечения и пересчитываем код
                if code_out == code1:
                    x1, y1 = x, y
                    code1 = compute_code(x1, y1, x_min, y_min, x_max, y_max)
                else:
                    x2, y2 = x, y
                    code2 = compute_code(x2, y2, x_min, y_min, x_max, y_max)
        
        if accept:
            clipped_lines.append(((x1, y1), (x2, y2)))
            
    return clipped_lines
