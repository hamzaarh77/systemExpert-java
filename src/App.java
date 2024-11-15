import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Création de la base de faits
        FactBase factBase = new FactBase();
        Fact fact1 = new Fact( "Le ciel est couvert", List.of("Argument1"),true);
        Fact fact2 = new Fact( "Il y a du vent", List.of("Argument2"), true);
        Fact fact3 = new Fact( "Il va pleuvoir", List.of(), false);
        Fact fact4 = new Fact( "Prenez un parapluie", List.of(), false);

        factBase.addFact(fact1);
        factBase.addFact(fact2);

        

        // Création de la base de connaissances
        Moteur moteur = new Moteur();
        moteur.addRule(new Rule(List.of(fact1, fact2), fact3));
        moteur.addRule(new Rule(List.of(fact3), fact4));

        // Application des règles
        System.out.println("Base de faits initiale : " + factBase);
        moteur.chainageAvant(factBase);
        System.out.println("Base de faits après inférence : " + factBase);

    }
}
