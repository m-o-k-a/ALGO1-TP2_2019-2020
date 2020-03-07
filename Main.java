import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        /*
            TEST DES MULTISETS
        MultiSet multiSet1 = new MultiSet("eunsun");
        MultiSet multiSet2 = new MultiSet("nouilles");
        MultiSet multiSet3 = new MultiSet("eddy");
        MultiSet multiSet11 = new MultiSet("nusnue");
        MultiSet multiSet12 = new MultiSet("eun");
        System.out.println(multiSet1); //waiting [e, u, n, s, u, n]
        multiSet1.addElement('z');
        System.out.println(multiSet1); //waiting [e, u, n, s, u, n, z]
        multiSet1.removeElement('z');
        System.out.println(multiSet1); //waiting [e, u, n, s, u, n]
        System.out.println(multiSet1.containsElement('y')); //waiting false
        System.out.println(multiSet1.containsElement('u')); //waiting true
        MultiSet unionset1set2 = multiSet1.twoSum(multiSet2);
        System.out.println(unionset1set2); //waiting [e, u, n, s, u, n, n, o, u, i, l, l, e, s]
        System.out.println(multiSet1.equals(multiSet3)); //waiting false
        System.out.println(multiSet1.equals(multiSet11)); //waiting true
        System.out.println(unionset1set2.equals(multiSet1.twoSum(multiSet2))); //waiting true
        System.out.println(multiSet1.complementary(multiSet2)); //waiting[]
        System.out.println(multiSet12.complementary(multiSet1)); //waiting[s, u, n]
        System.out.println(multiSet1.complementary(multiSet11)); //waiting[]
        */

        /*  Lecture du dictionnaire */
        //stockage des mots dans la table de hachage
        HashMap hashMap = new HashMap();
        hashMap.addAll("src/minidico.txt");
        //Recherche des complémentaires
        Dico dico = new Dico(hashMap.get());
        System.out.println(dico.allWords(new MultiSet("wapuitgons")));
        System.out.println(dico.allWords(new MultiSet("timbres-marierposte")));
    }
}