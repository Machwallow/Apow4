package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Joueur implements Serializable {
    private String nom = "test";
    private String img;

    public Joueur(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public String getNom(){
        return nom;
    }

    private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {

        this.nom = ois.readUTF() ;
        this.img = ois.readUTF() ;
    }

    private  void writeObject(ObjectOutputStream oos) throws IOException {

        oos.writeUTF(nom);
        oos.writeUTF(img);
    }

    public String toString(){
        return nom + ":  " + img;
    }
}
