package Main;

enum Color{
    WHITE, RED, BLUE;
}
enum Type{
    RARE, ORDINARY;
}
class ColorException extends Exception{
    public ColorException(String text){
        super(text);
    }
}
class TypeException extends Exception{
    public TypeException(String text){
        super(text);
    }

}

public class Plant {
    private String name;
    private Color color;
    private Type type;
    public Plant(String type, String color, String name) throws ColorException, TypeException {
        this.name = name;
        try{
            this.color = Color.valueOf(color.toUpperCase());
        }catch (Exception e){
            throw new ColorException("Invalid value "+ color +" for field color");
        }
        try{
            this.type = Type.valueOf(type.toUpperCase());
        }catch (Exception e){
            throw new TypeException("Invalid value "+ type +" for field type");
        }

    }

    public static Plant tryCreatePlant (String type, String color, String name) throws TypeException, ColorException {
        try {
            return new Plant(type, color, name);
        }catch (TypeException t){
            try {
                return new Plant(Type.ORDINARY.name(), color, name);
            }catch (ColorException c){
                return new Plant(Type.ORDINARY.name(), Color.RED.name(), name);
            }
        }catch (ColorException c){
            try {
                return new Plant(type, Color.RED.name(), name);
            }catch (TypeException t){
                return new Plant(Type.ORDINARY.name(), Color.RED.name(), name);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{" +
                "type: " + this.getType().toString() +
                ", color: " + this.getColor().toString() +
                ", name: " + this.getName() +
                '}';
    }
}

