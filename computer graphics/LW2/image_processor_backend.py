import cv2
import numpy as np
from PIL import Image


class ImageProcessor:
    # OpenCV.
    
    def __init__(self):
        self.original_image = None  # Исходное изображение
        self.current_image = None   

    def load_file(self, path):
        # BGR (Blue-Green-Red)
        img = cv2.imread(path)
        if img is not None:
            self.original_image = img
            self.current_image = img.copy()
            return True
        return False

    def reset(self):
        if self.original_image is not None:
            self.current_image = self.original_image.copy()

    def get_display_image(self):
        """
        # OpenCV использует BGR, а Pillow (и экраны) используют RGB. Нужно конвертировать.
        # Если картинка ч/б (2 измерения), конвертируем в RGB для совместимости.
        """
        if self.current_image is None:
            return None
        
        if len(self.current_image.shape) == 2:
            img_rgb = cv2.cvtColor(self.current_image, cv2.COLOR_GRAY2RGB)
        else:
            img_rgb = cv2.cvtColor(self.current_image, cv2.COLOR_BGR2RGB)
            
        return Image.fromarray(img_rgb)

    def save_current(self, path):
        if self.current_image is not None:
            cv2.imwrite(path, self.current_image)

    
    def apply_morphology(self, operation_type, kernel_shape_str, kernel_size):
        """
        Тип операции 
        Форма ядра
        Размер ядра 
        """
        if self.current_image is None: return

        # Определяем форму ядра
        shape_dict = {
            "Прямоугольник": cv2.MORPH_RECT,
            "Крест": cv2.MORPH_CROSS,
            "Эллипс": cv2.MORPH_ELLIPSE
        }
        shape_cv = shape_dict.get(kernel_shape_str.split()[0], cv2.MORPH_RECT)
        
        kernel = cv2.getStructuringElement(shape_cv, (kernel_size, kernel_size))
        
        # Применяем операцию
        if operation_type == "erode":
            res = cv2.erode(self.current_image, kernel, iterations=1)
        elif operation_type == "dilate":
            res = cv2.dilate(self.current_image, kernel, iterations=1)
        else:
            # Маппинг остальных операций
            op_cv = {
                "open": cv2.MORPH_OPEN,
                "close": cv2.MORPH_CLOSE,
                "grad": cv2.MORPH_GRADIENT
            }.get(operation_type)
            res = cv2.morphologyEx(self.current_image, op_cv, kernel)
            
        self.current_image = res

    def apply_sobel(self):
        """Оператор Собеля для обнаружения перепадов яркости."""
        if self.current_image is None: return
        
        # Переводим в оттенки серого (по якости)
        gray = cv2.cvtColor(self.current_image, cv2.COLOR_BGR2GRAY)
        
        grad_x = cv2.Sobel(gray, cv2.CV_16S, 1, 0, ksize=3)
        grad_y = cv2.Sobel(gray, cv2.CV_16S, 0, 1, ksize=3)
        
        abs_grad_x = cv2.convertScaleAbs(grad_x)
        abs_grad_y = cv2.convertScaleAbs(grad_y)
    
        self.current_image = cv2.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0)

    def apply_canny(self):
        # Детектор границ Канни

        if self.current_image is None: return
        
        gray = cv2.cvtColor(self.current_image, cv2.COLOR_BGR2GRAY)
        # 100 и 200 - пороговые значения гистерезиса
        edges = cv2.Canny(gray, 100, 200)
        self.current_image = edges

    def apply_hough(self):
        # Обнаружение прямых линий

        if self.current_image is None: return
        
        # границы 
        gray = cv2.cvtColor(self.current_image, cv2.COLOR_BGR2GRAY)
        edges = cv2.Canny(gray, 50, 150, apertureSize=3)
        
        # Ищем линии
        lines = cv2.HoughLinesP(edges, 1, np.pi/180, threshold=100, minLineLength=50, maxLineGap=10)
        
        # Рисуем линии поверх текущего изображения
        result = self.current_image.copy()
        count = 0
        if lines is not None:
            for line in lines:
                x1, y1, x2, y2 = line[0]
                cv2.line(result, (x1, y1), (x2, y2), (0, 255, 0), 3)
                count += 1
        
        self.current_image = result
        return count