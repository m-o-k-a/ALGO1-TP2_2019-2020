import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dico {
    /*
        DICO: Classe s'occupant de la structure du dictionnaire
     */
    //possède la Hashmap contenant nos mots
    private ArrayList<LinkedList<MultiSet>> dico;
    //donc se construit avec une hashmap
    public Dico(ArrayList<LinkedList<MultiSet>> dico) {
        this.dico = dico;
    }

    //retourne la hashmap du dico -> le dico
    public ArrayList<LinkedList<MultiSet>> get() {
        return dico;
    }

    //LA 2 SOMME ! Permet de trouver pour un ensemble R deux mots qui en 2 somme le forme
    public List<MultiSet> allWords(MultiSet R) { //List<MultiSet>
        //pour tt mots du dico hashmap
        for(LinkedList<MultiSet> linkedList : dico) {
            for(MultiSet multiSet : linkedList) {
                //cherche le complementaire du mot dans R
                MultiSet complementary = multiSet.complementary(R);
                //verifier si il existe et si la 2-somme du mot et du mot complementaire forment le mot R
                if(find(complementary) && multiSet.twoSum(complementary).equals(R)) {
                    //si oui retourner une liste avec ces deux morts
                    List<MultiSet> words = new ArrayList<>();
                    words.add(multiSet);
                    words.add(complementary);
                    return words;
                }
            }
        }
        //sinon rien retourner
        return null;
    }

    //cherche si un mot existe dans le dictionnaire
    public boolean find(MultiSet complementary) {
        //en récuperant son hashcode
        int complementaryCode = complementary.hashCode();
        //et regarder si pour les mots du meme hashcode, il est egal à un des ensembles
        for(MultiSet multiSet : dico.get(complementaryCode)) {
            if(multiSet.equals(complementary)) return true;
        }
        return false;
    }
}