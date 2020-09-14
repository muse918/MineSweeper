public class Main {
    public static void main(String[] args) {
        Plate plate = null;
        try {
            plate = new Plate(10, 10, 15);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        plate.dig(3, 4);
        plate.printmines();
    }
}
