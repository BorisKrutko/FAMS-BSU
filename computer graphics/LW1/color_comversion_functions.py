import colorsys

def cmyk_to_rgb(c, m, y, k):
    # [0, 100] -> [0, 1]
    c, m, y, k = c / 100.0, m / 100.0, y / 100.0, k / 100.0
    
    r = 255 * (1 - c) * (1 - k)
    g = 255 * (1 - m) * (1 - k)
    b = 255 * (1 - y) * (1 - k)
    
    return int(r), int(g), int(b)

def rgb_to_cmyk(r, g, b):
    # [0, 255]
    if (r, g, b) == (0, 0, 0):
        return 0, 0, 0, 100 # black

    # [0, 255] -> [0, 1]
    r, g, b = r / 255.0, g / 255.0, b / 255.0

    k = 1 - max(r, g, b)
    c = (1 - r - k) / (1 - k)
    m = (1 - g - k) / (1 - k)
    y = (1 - b - k) / (1 - k)

    return round(c * 100), round(m * 100), round(y * 100), round(k * 100)

def rgb_to_hls(r, g, b):
    # [0, 1]
    h, l, s = colorsys.rgb_to_hls(r / 255.0, g / 255.0, b / 255.0)
    
    # H (0-360), L/S (0-100)
    return round(h * 360), round(l * 100), round(s * 100)

def hls_to_rgb(h, l, s):
    #  [0, 1]
    r, g, b = colorsys.hls_to_rgb(h / 360.0, l / 100.0, s / 100.0)
    
    # [0, 255]
    return int(r * 255), int(g * 255), int(b * 255)
