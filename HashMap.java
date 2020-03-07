import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HashMap {
    /*
        HASHMAP: Notre HashMap pour stocker le dictionnaire de mots
     */
    //avec une capacité
    private final int CAPACITY = 569;
    //la hashmap sera de la forme Position dans notre ArrayList : Liste chainée
    private ArrayList<LinkedList<MultiSet>> hashMap;
    //private int DEBUG_VALUE = 0;

    //création de la hashmap
    public HashMap() {
        //on initialise la liste (Arraylist)
        hashMap = new ArrayList<>();
        //on initialise les sous liste (linkedlist)
        for(int i = 0; i< CAPACITY; i++) {
            hashMap.add(new LinkedList<>());
        }
    }

    //retourne notre hashmap
    public ArrayList<LinkedList<MultiSet>> get() { return hashMap; }


    //ajoute tous les elements d'un filename
    public void addAll(String filename) throws IOException {
        //notre reader
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String temp;
        //tant qu'on a pas lu tous les mots, on récupère le prochain mot
        while((temp = reader.readLine()) != null && temp != "\n") { //System.out.println(temp+" - "+DEBUG_VALUE++);
            //on crer un multiset de ce mot
            MultiSet multitemp = new MultiSet(temp);
            //on récupère son hashcode
            int code = multitemp.hashCode();
            //on le place dans notre HashMap. Si c'est le premier avec ce hashcode ce sera la tete de la linkedList, on ajoute normalement sinon
            if(hashMap.get(code).isEmpty()) {
                hashMap.get(code).addFirst(multitemp); //System.out.println("j'ai ajouté "+multitemp+" "+hashMap.get(code).getFirst());
            }
            else if (!isExist(code, multitemp)) { hashMap.get(code).add(multitemp); }
        }
        reader.close();
    }

    //verifie l'existence d'un multiset dans notre HashMap
    //Fonction utiliser par le addAll
    private boolean isExist(int code, MultiSet multitemp) {
        //si il est egal a la tete il existe
        if (hashMap.get(code).getFirst().equals(multitemp)) { return true; }
        else {
            //sinon on regarde si il est egal a au moins un de mot de la linkedlist
            for (MultiSet multiSet : hashMap.get(code)) {
                if (multiSet.equals(multitemp)) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String toString = "{\n";
        for(LinkedList elements : hashMap) {
            if (elements.size() > 0) {
                toString += "  "+hashMap.indexOf(elements)+": ";
                for(Object set : elements) {
                    MultiSet multiSet = (MultiSet) set;
                    toString += multiSet.toWord()+", ";
                }
                toString += "\n";
            }
        }
        return toString+"}";
    }
}
