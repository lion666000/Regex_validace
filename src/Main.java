void main() {


    Path path = Path.of("src/trenink_objednavky_testovaci_data.txt");
    List<Objednavka> objednavky = new ArrayList<>();

    try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
        String radek;
        while ((radek = bufferedReader.readLine()) != null) {
            if (radek.isBlank()) {
                continue;
            }
            Objednavka objednavka = new Validation((radek)).validation();
            if (objednavka != null) {
                objednavky.add(objednavka);
            }
        }
    }
    catch(IOException e){
        System.err.println("Chyba pri praci se souborem.");
    }

    System.out.println();
    System.out.println("=== Platně zadaní vězni (příjmení, rok narození) ===");
    for (Objednavka objednavka : objednavky) {
        System.out.println(objednavka.toString());
    }
    System.out.println("Celkem platných záznamů: " + objednavky.size());

    Double totalPrice = 0.0;
    int totalAmountMouse = 0;
    int totalAmountKeyboard = 0;
    int totalAmountMonitor = 0;
    int totalAmountGraphics = 0;
    for (Objednavka objednavka : objednavky) {

        totalPrice += objednavka.getPrice() * objednavka.getQuantity();

        switch(objednavka.getProdukt()) {
            case "Herní myš":
                totalAmountMouse+=objednavka.getQuantity();
                break;
            case "Klávesnice":
                totalAmountKeyboard+=objednavka.getQuantity();
                break;
            case "Monitor":
                totalAmountMonitor+=objednavka.getQuantity();
                break;
            case "Grafická karta":
                totalAmountGraphics+=objednavka.getQuantity();
                break;
        }
    }

    System.out.println("Celková cena: " + totalPrice);
    System.out.println("Celkové množství myší: " + totalAmountMouse);
    System.out.println("Celkové množství klávesnic: " + totalAmountKeyboard);
    System.out.println("Celkové množství monitorů: " + totalAmountMonitor);
    System.out.println("Celkové množství grafik: " + totalAmountGraphics);
}
