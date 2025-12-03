import tkinter as tk
from tkinter import filedialog, ttk, messagebox
import cv2
import numpy as np
from PIL import Image, ImageTk

from image_processor_backend import ImageProcessor

class LabGUI:
    """
    Класс отвечает за создание окна, кнопок и связывание их с ImageProcessor.
    """
    def __init__(self, root):
        self.root = root
        self.processor = ImageProcessor()
        self.tk_image_ref = None 
        
        self.setup_window()
        self.create_widgets()

    def setup_window(self):
        self.root.title("Лабораторная работа №2 - Вариант 19")
        self.root.geometry("1200x750")

    def create_widgets(self):
        # Меню
        control_panel = tk.Frame(self.root, width=300, bg="#e1e1e1")
        control_panel.pack(side=tk.LEFT, fill=tk.Y, padx=10, pady=10)
        
        tk.Label(control_panel, text="Вариант 19\n(Морфология + Сегментация)", 
                 font=("Arial", 12, "bold"), bg="#e1e1e1").pack(pady=10)

        # Кнопки Файл
        tk.Button(control_panel, text="Загрузить фото", command=self.on_load, bg="#4CAF50", fg="white").pack(fill=tk.X, pady=2)
        tk.Button(control_panel, text="Сбросить изменения", command=self.on_reset).pack(fill=tk.X, pady=2)

        # БЛОК МОРФОЛОГИЯ 
        morph_frame = tk.LabelFrame(control_panel, text="1. Морфологическая обработка", font=("Arial", 10, "bold"), bg="#e1e1e1")
        morph_frame.pack(fill=tk.X, pady=15, padx=5)

        # Выбор формы
        tk.Label(morph_frame, text="Форма элемента:", bg="#e1e1e1").pack(anchor="w")
        self.combo_shape = ttk.Combobox(morph_frame, values=["Прямоугольник (Rect)", "Крест (Cross)", "Эллипс (Ellipse)"])
        self.combo_shape.current(0)
        self.combo_shape.pack(fill=tk.X, padx=5, pady=2)

        # Выбор размера
        tk.Label(morph_frame, text="Размер ядра:", bg="#e1e1e1").pack(anchor="w")
        self.scale_size = tk.Scale(morph_frame, from_=3, to=25, orient=tk.HORIZONTAL, resolution=2, bg="#e1e1e1")
        self.scale_size.set(5)
        self.scale_size.pack(fill=tk.X, padx=5)

        # Кнопки операций
        ops = [
            ("Эрозия (Сужение)", "erode"),
            ("Дилатация (Расширение)", "dilate"),
            ("Открытие (Убрать шум)", "open"),
            ("Закрытие (Залить дыры)", "close"),
            ("Градиент (Контур)", "grad")
        ]
        for text, op_code in ops:
            tk.Button(morph_frame, text=text, 
                      command=lambda c=op_code: self.run_morph(c)).pack(fill=tk.X, padx=5, pady=2)

        # БЛОК СЕГМЕНТАЦИЯ 
        seg_frame = tk.LabelFrame(control_panel, text="2. Сегментация / Границы", font=("Arial", 10, "bold"), bg="#e1e1e1")
        seg_frame.pack(fill=tk.X, pady=15, padx=5)

        tk.Button(seg_frame, text="Перепады яркости (Sobel)", command=self.run_sobel).pack(fill=tk.X, padx=5, pady=2)
        tk.Button(seg_frame, text="Границы объектов (Canny)", command=self.run_canny).pack(fill=tk.X, padx=5, pady=2)
        tk.Button(seg_frame, text="Найти прямые линии (Hough)", command=self.run_hough).pack(fill=tk.X, padx=5, pady=2)

        tk.Button(control_panel, text="Сохранить результат", command=self.on_save, bg="#2196F3", fg="white").pack(side=tk.BOTTOM, fill=tk.X, pady=20)

        # Картинка
        self.canvas_frame = tk.Frame(self.root, bg="#333")
        self.canvas_frame.pack(side=tk.RIGHT, expand=True, fill=tk.BOTH)
        
        self.lbl_image = tk.Label(self.canvas_frame, text="Загрузите изображение...", bg="#333", fg="white")
        self.lbl_image.pack(expand=True)


    def update_display(self):
        pil_img = self.processor.get_display_image()
        if pil_img:
            # Ресайз 
            w_max, h_max = 900, 700
            pil_img.thumbnail((w_max, h_max), Image.Resampling.LANCZOS)
            
            self.tk_image_ref = ImageTk.PhotoImage(pil_img)
            self.lbl_image.config(image=self.tk_image_ref, text="")
        else:
            self.lbl_image.config(text="Ошибка отображения", image="")

    def on_load(self):
        path = filedialog.askopenfilename(filetypes=[("Images", "*.jpg *.png *.bmp *.jpeg")])
        if path:
            if self.processor.load_file(path):
                self.update_display()

    def on_reset(self):
        self.processor.reset()
        self.update_display()

    def on_save(self):
        if self.processor.current_image is None: return
        path = filedialog.asksaveasfilename(defaultextension=".png", filetypes=[("PNG", "*.png"), ("JPG", "*.jpg")])
        if path:
            self.processor.save_current(path)

    # Обертки для запуска алгоритмов
    def run_morph(self, op_code):
        if self.processor.current_image is None: return
        shape = self.combo_shape.get()
        size = self.scale_size.get()
        self.processor.apply_morphology(op_code, shape, size)
        self.update_display()

    def run_sobel(self):
        if self.processor.current_image is None: return
        self.processor.apply_sobel()
        self.update_display()

    def run_canny(self):
        if self.processor.current_image is None: return
        self.processor.apply_canny()
        self.update_display()

    def run_hough(self):
        if self.processor.current_image is None: return
        count = self.processor.apply_hough()
        self.update_display()
        if count == 0:
            messagebox.showinfo("Инфо", "Линии не найдены. Попробуйте более контрастное изображение с четкими прямыми линиями.")
        else:
            messagebox.showinfo("Инфо", f"Найдено линий: {count}")