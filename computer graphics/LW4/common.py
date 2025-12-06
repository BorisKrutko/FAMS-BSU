import matplotlib.pyplot as plt
import matplotlib.patches as patches

def read_input_file(filename):
    try:
        with open(filename, 'r') as f:
            lines = f.readlines()
            
        clean_lines = [l.split('*')[0].strip() for l in lines if l.strip()]
        
        n = int(clean_lines[0])
        segments = []
        for i in range(1, n + 1):
            coords = list(map(float, clean_lines[i].split()))
            segments.append(((coords[0], coords[1]), (coords[2], coords[3])))
            
        window_coords = list(map(float, clean_lines[n+1].split()))
        window = tuple(window_coords) # xmin, ymin, xmax, ymax
        
        return segments, window
    except Exception as e:
        print(f"Ошибка чтения файла: {e}")
        return [], (0,0,0,0)

def create_sample_file(filename="input.txt"):
    content = """5 *число отрезков*
-50 20 50 80
20 120 40 -20
-30 50 150 50
60 60 80 80
-40 -40 -20 -20
0 0 100 100 *координаты окна Xmin Ymin Xmax Ymax*
"""
    with open(filename, 'w') as f:
        f.write(content)

