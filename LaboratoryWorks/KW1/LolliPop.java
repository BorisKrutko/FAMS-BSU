package KW1;

public class LolliPop extends Candy {
    private boolean isSour;
    private String lolliPopType;
    private  boolean withStick;

    public LolliPop(int waight, String name, CandyColor color, String manufacturer, double sugarPercentage,
                    boolean isSour, String lolliPopType, boolean withStick) {
        super(waight, name, color, manufacturer, sugarPercentage);
        this.isSour = isSour;
        this.lolliPopType = lolliPopType;
        this.withStick = withStick;
    }

    public LolliPop(String[] args) {
        super(Double.parseDouble(args[1]), args[2], CandyColor.fromCode(args[3]), args[4], Double.parseDouble(args[5]));
        try {
            if (args.length != 9) throw new IllegalArgumentException();  
            this.isSour = Boolean.parseBoolean(args[6]);
            this.lolliPopType = args[7];
            this.withStick = Boolean.parseBoolean(args[8]);
        } catch (IllegalArgumentException e) {System.err.println("BAD CANDY");} 
    }

    @Override
    public String toString() {
        return (super.toString() + " isSour: " + this.isSour 
                + " lolliPopType: " + this.lolliPopType + " withStick: " + this.withStick + "\n");  
    }
}