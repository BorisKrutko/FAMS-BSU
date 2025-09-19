package KW1;

enum TypeChocolate {
    Black("B"), Wight("W"), Milk("M"), Dubai("D");
        
    private String code;
    
        
    TypeChocolate(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    public static TypeChocolate fromCode(String code) {
        for (TypeChocolate type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}

public class Chocolate extends Candy {
    private TypeChocolate typeChocolate;
    private int numberOfSlices;
    private boolean withFilling;

    public Chocolate(int waight, String name, CandyColor color, String manufacturer, double sugarPercentage, 
                    TypeChocolate typeChocolate, int numberOfSlices, boolean withFilling) {
        super(waight, name, color, manufacturer, sugarPercentage);
        this.typeChocolate = typeChocolate;
        this.numberOfSlices = numberOfSlices;
        this.withFilling = withFilling;
    }

    public Chocolate(String[] args) {
        super(Double.parseDouble(args[1]), args[2], CandyColor.fromCode(args[3]), args[4], Double.parseDouble(args[5]));
        try {
            if (args.length != 9) throw new IllegalArgumentException();  
            this.typeChocolate = TypeChocolate.fromCode(args[6]);
            this.numberOfSlices = Integer.parseInt(args[7]);
            this.withFilling = Boolean.parseBoolean(args[8]);
        } catch (IllegalArgumentException e) {System.err.println("BAD CANDY");} 
    }

    @Override
    public String toString() {
        return (super.toString() + " typeChocolate: " + this.typeChocolate 
                + " numberOfSlices: " + this.numberOfSlices + " withFillin: " + this.withFilling + "\n");  
    }
}
