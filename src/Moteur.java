import java.util.ArrayList;
import java.util.List;

public class Moteur {
    private List<Rule> rules ;

    public Moteur(){
        rules = new ArrayList<>();
    }


    // methods
    public void addRule(Rule r) {this.rules.add(r); }

    public void chainageAvant(FactBase fb){
        boolean newFact;
        do {
            newFact = false ;
            for(Rule rule : rules){
                if(rule.estApplicable(fb)){
                    Fact conclusion = rule.getConclusion();
                    if(!fb.contains(conclusion)){
                        fb.addFact(new Fact (conclusion.getName(), conclusion.getArguments(), true));
                        newFact = true ;
                    }
                }
            }
        }while(newFact);
    }

    public boolean chainageArriere(Fact goal, FactBase fb){
        // verifie si le fait existe dans la base de fait
        if(fb.contains(goal))
            return fb.getFact(goal).getValue();
        
        //cherche des regles qui concluent le fait
        for(Rule rule : rules){
            if(rule.getConclusion().equals(goal)){
                boolean premissesTrue = true ;
                for (Fact premisse : rule.getPremisses()){
                    if(!chainageArriere(premisse, fb)){
                        premissesTrue = false ;
                        break;
                    }
                }
                if(premissesTrue){
                    fb.addFact(new Fact(goal.getName(), goal.getArguments(), true));
                    return true;
                }
            }
        }
        // aucune regle ne permet de conclure le fait
        return false ;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Rule rule : rules ){
            sb.append(rule.toString()+"\n");
        }
        return sb.toString();
    }


    //getters and setters

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> getRules() {
        return this.rules;
    }
    
}
