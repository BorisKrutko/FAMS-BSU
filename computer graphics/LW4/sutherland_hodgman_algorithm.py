def clip_polygon_against_edge(polygon, edge_start, edge_end):
    new_polygon = []
    
    # проверка положения точки относительно ребра отсекателя
    def is_inside(p):
        return (edge_end[0] - edge_start[0]) * (p[1] - edge_start[1]) - \
               (edge_end[1] - edge_start[1]) * (p[0] - edge_start[0]) >= 0

    # Функция поиска пересечения (ребра многоугольника и ребра окна)
    def intersection(p1, p2):
        x1, y1 = p1
        x2, y2 = p2
        x3, y3 = edge_start
        x4, y4 = edge_end
        
        denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1)
        if denom == 0:
            return None # Параллельны
            
        ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom
        return (x1 + ua * (x2 - x1), y1 + ua * (y2 - y1))

    if not polygon:
        return []

    s = polygon[-1] # Последняя точка (предыдущая)
    
    for p in polygon:
        if is_inside(p):
            if not is_inside(s):
                inter = intersection(s, p)
                if inter: new_polygon.append(inter)
            new_polygon.append(p)
        elif is_inside(s):
            inter = intersection(s, p)
            if inter: new_polygon.append(inter)
        s = p
        
    return new_polygon

def sutherland_hodgman_clip(polygon, x_min, y_min, x_max, y_max):
    clipper_polygon = [
        (x_min, y_min), (x_max, y_min), # Нижняя грань
        (x_max, y_max), (x_min, y_max)  # Правая, Верхняя, Левая
    ]
    
    # Обходим все грани окна
    clipper_edges = [
        ((x_min, y_min), (x_max, y_min)), # Bottom
        ((x_max, y_min), (x_max, y_max)), # Right
        ((x_max, y_max), (x_min, y_max)), # Top
        ((x_min, y_max), (x_min, y_min))  # Left
    ]

    clipped_poly = polygon
    for edge_start, edge_end in clipper_edges:
        clipped_poly = clip_polygon_against_edge(clipped_poly, edge_start, edge_end)
    
    return clipped_poly