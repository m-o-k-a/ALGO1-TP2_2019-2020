import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiSet {
    /*
        MULTISET: la classe s'occupant des ensembles de mots
     */
    //possède un multi ensemble de letters, multi donc possible d'avoir plusieurs fois la meme lettre
    private List<Character> multiSet;

    //se construit en lui donnant un mot
    public MultiSet(String word) {
        //que l'on passe totallement en minuscule pour eviter les valeurs différentes lors du hash
        word = word.toLowerCase();
        //stockage de la longeur du mot er creation de la liste
        int length = word.length();
        multiSet = new ArrayList<>();
        //ajout des lettres dans le multiset
        for(int c = 0; c<length; c++) {
            addElement(word.charAt(c));
        }
    }

    //permet d'obtenir les elements du multiset
    public List<Character> elements() { return multiSet; }
    //permet d'obtenir la taille du multiset
    public int size() { return multiSet.size(); }
    //permet d'ajouter un element au multiset
    public boolean addElement(Character c) { return multiSet.add(c); }

    //mise en place de la 2-somme entre ce multiset et un autre
    public MultiSet twoSum(MultiSet multiSet2) {
        //en creant une chaine
        String union = "";
        //en y concatenant toutes les lettres de cet ensemble...
        for(Character c1 : this.elements()) { union = union + c1; }
        //... et les lettres de l'ensemble suivant
        for(Character c2 : multiSet2.elements()) { union = union + c2; }
        //retourne le nouveau multiset correspondant à la 2-somme
        return new MultiSet(union);
    }

    //Obtenir le complementaire de ce multiset par rapport a un autre multiset
    public MultiSet complementary(MultiSet multiSet2) {
        if (this.size() > multiSet2.size()) return new MultiSet("");
        MultiSet complementary = new MultiSet("");
        complementary.addWord(multiSet2.elements());
        for(Character c : multiSet) {
            if(!complementary.removeElement(c)) { return new MultiSet(""); }
        }
        return complementary;
    }

    //Verifie l'égalité entre 2 multiset
    @Override
    public boolean equals(Object o) {
        //egal si c'est le meme objet de multiset
        if (this == o) return true;
        //pas egal si classe différentes ou objet null
        if (o == null || getClass() != o.getClass()) return false;
        //sinon cast en multiset
        MultiSet multiSet2 = (MultiSet) o;
        //stockage temporaire de notre multiset
        List<Character> multiTemp = new ArrayList<>();
        multiTemp.addAll(this.elements());
        //on retire les elements correspondant petit a petit
        for(Character c : multiSet2.elements()) {
            if(!multiSet.contains(c)) return false;
            multiTemp.remove(c);
        }
        //si tous les elements sont enlever alors c'est le meme
        return multiTemp.size() == 0;
    }


    //Création du hashcode de notre multiset
    public int hashCode() {
        //stockage temporaire de notre multiset
        MultiSet multitemp = new MultiSet("");
        multitemp.addWord(this.multiSet);
        //on le trie par ordre lexicographique
        Collections.sort(multitemp.multiSet);
        int temp = 0;
        List<Character> multiset = multitemp.elements();
        //puis pour chaque elements du set trier on fais une fonction
        for(int i = 0; i<multiset.size(); i++) {
            temp += (multiset.get(i)*(Math.pow(3, multiset.size()-i)))%569;
        }
        //et on retourne un hashcode
        return temp%569;
    }

    //Ajout de mots dans le multiset avec une chaine...
    public boolean addWord(String w) {
        int length = w.length();
        for(int c = 0; c<length; c++) {
            addElement(w.charAt(c));
        }
        return true;
    }
    //ou une liste de lettres
    public boolean addWord(List<Character> list) {
        for(Character c : list) {
            addElement(c);
        }
        return true;
    }
    //Verifie si la lettre c est dans ce multiset
    public boolean containsElement(Character c) {
        return multiSet.contains(c);
    }
    //Enlève la lettre c du multiset si elle existe
    public boolean removeElement(Character c) {
        return multiSet.remove(c);
    }
    //Dis si le multiset est vide ou non
    public boolean isEmpty() { return multiSet.size() == 0; }


    @Override
    public String toString() {
        return multiSet.toString();
    }

    public String toWord() {
        String word = "";
        for(Character c : multiSet) { word += c; }
        return word;
    }
}