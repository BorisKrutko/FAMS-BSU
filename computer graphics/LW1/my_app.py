import tkinter as tk
from tkinter import ttk, colorchooser
from color_comversion_functions import (
    cmyk_to_rgb, rgb_to_hls, 
    rgb_to_cmyk, hls_to_rgb,
)

class ColorConverterApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("(CMYK-RGB-HLS)")
        self.geometry("650x550")

        style = ttk.Style(self)

        style.configure(
            'custom.Horizontal.TScale', 
            troughcolor='lightblue', 
            background='navy'
        )

        self._is_updating = False

        self.c_var = tk.IntVar()
        self.m_var = tk.IntVar()
        self.y_var = tk.IntVar()
        self.k_var = tk.IntVar()

        self.r_var = tk.IntVar()
        self.g_var = tk.IntVar()
        self.b_var = tk.IntVar()

        self.h_var = tk.IntVar()
        self.l_var = tk.IntVar()
        self.s_var = tk.IntVar()

        self._create_widgets()
        
        self.update_from_rgb() 

    def _create_widgets(self):
        color_preview_frame = ttk.Frame(self, padding=10)
        color_preview_frame.pack(pady=10, padx=10, fill=tk.X)

        self.color_preview = tk.Label(color_preview_frame, text="Цвет", height=15, relief="raised", borderwidth=2)
        self.color_preview.pack(fill=tk.BOTH, expand=True)

        ttk.Button(
            color_preview_frame, 
            text="Выбрать цвет из палитры", 
            command=self.pick_color
        ).pack(pady=(10, 0), fill=tk.X)

        models_container = ttk.Frame(self, padding=10)
        models_container.pack(expand=True, fill=tk.BOTH)
        
        cmyk_frame = self._create_model_frame(models_container, "CMYK")
        rgb_frame = self._create_model_frame(models_container, "RGB")
        hls_frame = self._create_model_frame(models_container, "HLS")

        cmyk_frame.pack(side=tk.LEFT, expand=True, fill=tk.BOTH, padx=5)
        rgb_frame.pack(side=tk.LEFT, expand=True, fill=tk.BOTH, padx=5)
        hls_frame.pack(side=tk.LEFT, expand=True, fill=tk.BOTH, padx=5)

        self._create_slider_entry(cmyk_frame, "C (Cyan)", self.c_var, 0, 100, self.update_from_cmyk)
        self._create_slider_entry(cmyk_frame, "M (Magenta)", self.m_var, 0, 100, self.update_from_cmyk)
        self._create_slider_entry(cmyk_frame, "Y (Yellow)", self.y_var, 0, 100, self.update_from_cmyk)
        self._create_slider_entry(cmyk_frame, "K (Black)", self.k_var, 0, 100, self.update_from_cmyk)

        self._create_slider_entry(rgb_frame, "R (Red)", self.r_var, 0, 255, self.update_from_rgb)
        self._create_slider_entry(rgb_frame, "G (Green)", self.g_var, 0, 255, self.update_from_rgb)
        self._create_slider_entry(rgb_frame, "B (Blue)", self.b_var, 0, 255, self.update_from_rgb)
        
        self._create_slider_entry(hls_frame, "H (Hue)", self.h_var, 0, 360, self.update_from_hls)
        self._create_slider_entry(hls_frame, "L (Lightness)", self.l_var, 0, 100, self.update_from_hls)
        self._create_slider_entry(hls_frame, "S (Saturation)", self.s_var, 0, 100, self.update_from_hls)
    
    def _create_model_frame(self, parent, text):
        frame = ttk.Labelframe(parent, text=text, padding=10)
        return frame

    def _create_slider_entry(self, parent, label, var, from_, to, update_cmd):
        frame = ttk.Frame(parent)
        frame.pack(pady=5, fill=tk.X)
        
        ttk.Label(frame, text=label, width=12).pack(side=tk.LEFT)
        
        entry = ttk.Entry(frame, textvariable=var, width=5)
        entry.pack(side=tk.RIGHT, padx=(5,0))
      
        entry.bind("<Return>", lambda event: update_cmd())
        
        slider = ttk.Scale(
            frame, 
            from_=from_, 
            to=to, 
            orient=tk.HORIZONTAL, 
            variable=var, 
            command=lambda e: update_cmd(),
            style='custom.Horizontal.TScale' 
        )
        slider.pack(side=tk.RIGHT, expand=True, fill=tk.X)


    def update_from_cmyk(self):
        if self._is_updating: return
        try:
            r, g, b = cmyk_to_rgb(self.c_var.get(), self.m_var.get(), self.y_var.get(), self.k_var.get())
            self.update_all_models(r, g, b, source_model='cmyk')
        except (ValueError, tk.TclError):
            pass

    def update_from_rgb(self):
        if self._is_updating: return
        try:
            r, g, b = self.r_var.get(), self.g_var.get(), self.b_var.get()
            self.update_all_models(r, g, b, source_model='rgb')
        except (ValueError, tk.TclError):
            pass

    def update_from_hls(self):
        if self._is_updating: return
        try:
            r, g, b = hls_to_rgb(self.h_var.get(), self.l_var.get(), self.s_var.get())
            self.update_all_models(r, g, b, source_model='hls')
        except (ValueError, tk.TclError):
            pass
   
    def update_all_models(self, r, g, b, source_model=None):
        self._is_updating = True 
        
        c, m, y, k = rgb_to_cmyk(r, g, b)
        h, l, s = rgb_to_hls(r, g, b)
        
        if source_model != 'rgb':
            self.r_var.set(r)
            self.g_var.set(g)
            self.b_var.set(b)
        
        if source_model != 'cmyk':
            self.c_var.set(c)
            self.m_var.set(m)
            self.y_var.set(y)
            self.k_var.set(k)
        
        if source_model != 'hls':
            self.h_var.set(h)
            self.l_var.set(l)
            self.s_var.set(s)
        
        hex_color = f"#{r:02x}{g:02x}{b:02x}"
        self.color_preview.config(background=hex_color)

        self._is_updating = False

    def pick_color(self):
        color = colorchooser.askcolor()
        if color and color[0]:
            r, g, b = map(int, color[0])
            self.update_all_models(r, g, b, source_model='rgb')