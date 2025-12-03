import tkinter as tk
from raster_ui import RasterUI

if __name__ == "__main__":
    root = tk.Tk()
    root.geometry("1000x700")
    app = RasterUI(root)
    root.mainloop()