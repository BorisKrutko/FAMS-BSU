import tkinter as tk
from tkinter import messagebox
import time
import math

from raster_logic import RasterLogic

class RasterUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Растровые алгоритмы")

        self.logic = RasterLogic()

        self.canvas_width = 800
        self.canvas_height = 600
        self.scale = 20  
        
        self._init_ui()
        self.draw_grid()

    def _init_ui(self):
        main_frame = tk.Frame(self.root)
        main_frame.pack(fill=tk.BOTH, expand=True)
        
        # Панель управления (слева)
        control_panel = tk.Frame(main_frame, width=250, bg="#f0f0f0", padx=10, pady=10)
        control_panel.pack(side=tk.LEFT, fill=tk.Y)
        
        # Холст (справа)
        self.canvas = tk.Canvas(main_frame, width=self.canvas_width, height=self.canvas_height, bg="white")
        self.canvas.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True)
        
        # --- Элементы ввода ---
        tk.Label(control_panel, text="Координаты (x1, y1) / Центр:", bg="#f0f0f0").pack(anchor="w")
        self.entry_x1 = tk.Entry(control_panel); self.entry_x1.insert(0, "-5"); self.entry_x1.pack(fill=tk.X)
        self.entry_y1 = tk.Entry(control_panel); self.entry_y1.insert(0, "-3"); self.entry_y1.pack(fill=tk.X)
        
        tk.Label(control_panel, text="Координаты (x2, y2) / Радиус (в X2):", bg="#f0f0f0").pack(anchor="w", pady=(10, 0))
        self.entry_x2 = tk.Entry(control_panel); self.entry_x2.insert(0, "10"); self.entry_x2.pack(fill=tk.X)
        self.entry_y2 = tk.Entry(control_panel); self.entry_y2.insert(0, "8"); self.entry_y2.pack(fill=tk.X)
        
        # Масштаб
        tk.Label(control_panel, text="Масштаб:", bg="#f0f0f0").pack(anchor="w", pady=(10, 0))
        self.scale_slider = tk.Scale(control_panel, from_=5, to=50, orient=tk.HORIZONTAL, command=self.update_scale)
        self.scale_slider.set(20)
        self.scale_slider.pack(fill=tk.X)

        # Кнопки
        tk.Label(control_panel, text="Алгоритмы:", bg="#f0f0f0", font=("Arial", 10, "bold")).pack(anchor="w", pady=(20, 5))
        
        self._create_btn(control_panel, "Пошаговый", "step")
        self._create_btn(control_panel, "ЦДА (DDA)", "dda")
        self._create_btn(control_panel, "Брезенхем (Линия)", "bres_line")
        self._create_btn(control_panel, "Брезенхем (Окружность)", "bres_circle")
        
        tk.Button(control_panel, text="Очистить", command=self.clear_canvas, bg="#ffcccc").pack(fill=tk.X, pady=(20, 2))

        # Метка времени
        self.time_label = tk.Label(control_panel, text="Время: -", bg="#f0f0f0", fg="blue")
        self.time_label.pack(pady=20)

    def _create_btn(self, parent, text, algo_tag):
        tk.Button(parent, text=text, command=lambda: self.run_algorithm(algo_tag)).pack(fill=tk.X, pady=2)

    def update_scale(self, val):
        self.scale = int(val)
        self.clear_canvas()

    def clear_canvas(self):
        self.canvas.delete("all")
        self.draw_grid()

    def draw_grid(self):
        """ Рисует координатную сетку и оси """
        self.canvas.delete("grid")
        w = self.canvas.winfo_width()
        h = self.canvas.winfo_height()
        if w == 1: w, h = self.canvas_width, self.canvas_height
        
        cx, cy = w // 2, h // 2
        
        # Линии сетки
        for i in range(cx % self.scale, w, self.scale):
            color, width = ("black", 2) if i == cx else ("#ddd", 1)
            self.canvas.create_line(i, 0, i, h, fill=color, width=width, tags="grid")
            
        for i in range(cy % self.scale, h, self.scale):
            color, width = ("black", 2) if i == cy else ("#ddd", 1)
            self.canvas.create_line(0, i, w, i, fill=color, width=width, tags="grid")

        # Подписи
        self.canvas.create_text(cx + 10, cy + 10, text="0", tags="grid")
        self.canvas.create_text(w - 20, cy - 15, text="X", tags="grid", fill="red")
        self.canvas.create_text(cx + 15, 20, text="Y", tags="grid", fill="red")

    def draw_pixel(self, x, y, color):
        """ Преобразует логические координаты в экранные и рисует квадрат """
        cx = self.canvas.winfo_width() // 2
        cy = self.canvas.winfo_height() // 2
        
        # Экранные координаты (верхний левый угол квадрата)
        screen_x = cx + (x * self.scale)
        # Y инвертирован (в математике верх +, на экране верх 0)
        # -scale чтобы рисовать "от линии вверх" для положительных
        screen_y = cy - (y * self.scale) - self.scale 
        
        self.canvas.create_rectangle(
            screen_x, screen_y, 
            screen_x + self.scale, screen_y + self.scale, 
            fill=color, outline="gray"
        )

    def get_input_data(self):
        try:
            return int(self.entry_x1.get()), int(self.entry_y1.get()), \
                   int(self.entry_x2.get()), int(self.entry_y2.get())
        except ValueError:
            messagebox.showerror("Ошибка", "Введите целые числа")
            return None

    def run_algorithm(self, algo_tag):
        coords = self.get_input_data()
        if not coords: return
        x1, y1, x2, y2 = coords
        
        self.clear_canvas()
        points = []
        color = "black"
        
        # === ВЗАИМОДЕЙСТВИЕ С BACKEND ===
        start_time = time.perf_counter_ns()
        
        if algo_tag == "step":
            points = self.logic.step_by_step(x1, y1, x2, y2)
            color = "blue"
        elif algo_tag == "dda":
            points = self.logic.dda(x1, y1, x2, y2)
            color = "green"
        elif algo_tag == "bres_line":
            points = self.logic.bresenham_line(x1, y1, x2, y2)
            color = "red"
        elif algo_tag == "bres_circle":
            # Для окружности используем x2 как радиус
            radius = x2
            points = self.logic.bresenham_circle(x1, y1, radius)
            color = "purple"
            
        end_time = time.perf_counter_ns()
        # ================================
        
        # Отрисовка результатов
        for p in points:
            self.draw_pixel(p[0], p[1], color)
            
        elapsed_mk = (end_time - start_time) / 1000
        self.time_label.config(text=f"Время расчета: {elapsed_mk:.2f} мкс")