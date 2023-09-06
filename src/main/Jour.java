package main;

import java.util.ArrayList;
import java.util.List;

public class Jour {
    private int tempsJour = 24;
    private List<Jeu> jeuxDuJour = new ArrayList<>();
    private int argentDeb;
    private Joueur joueur;

    public Jour(int argentDeb, Joueur joueur) {
        this.argentDeb = argentDeb;
        this.joueur = joueur;
    }

    public int getTempsJour() {
        return tempsJour;
    }

    public void setTempsJour(int tempsJour) {
        this.tempsJour = tempsJour;
    }

    public void moinsTempsJour(int tempsJour){
        this.tempsJour -= tempsJour;
    }

    public String toString(){
        String res = "Jeu(x) du jour :\n";
        for(Jeu j : jeuxDuJour){
            res += j + " -> " + j.duree() + "h\n";
        }
        res += "Argent en début de journée : " + this.argentDeb + "\n";
        res += "Argent en fin de journée : " + this.joueur.getArgent() + "\n";
        return res;
    }
}
