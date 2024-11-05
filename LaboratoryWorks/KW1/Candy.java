package KW1;

enum CandyColor {
    Green("G"), Red("R"), Black("B"), Wight("W"), Blue("Bl");

    private String code;

    CandyColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CandyColor fromCode(String code) {
        for (CandyColor type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}

public abstract class Candy {
    private double waight;
    private String name;
    private CandyColor color;
    private String manufacturer;
    private double sugarPercentage;

    public Candy(double waight, String name, CandyColor color, String manufacturer, double sugarPercentage) {
        this.waight = waight;
        this.name = name;
        this.color = color;
        this.manufacturer = manufacturer;
        this.sugarPercentage = sugarPercentage;
    }

    public double getWaight() { return this.waight; }
    public String getName() { return  this.name; } 
    public CandyColor getCandyColor() { return this.color; }
    public String getManufacturer() { return this.manufacturer; }
    public Double getSugarPercrntage() { return this.sugarPercentage; }

    @Override
    public String toString() {
        return ("waight: " + this.waight + " name: " + this.name + " color: " + this.color 
                + " manufacturer: " + this.manufacturer + " SugarPercrntage: " + this.sugarPercentage);
    }
}


