package controller;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class Joueur implements Serializable {
    private String nom = "test";
    private String img;

    public Joueur(String img) {
        this.img = img;
    }
    public Joueur(String nom, String img) {
        this.nom = nom;
        this.img = img;
    }

    public String getImg() {
        return img;
    }
    public String getNom(){
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setImg(String img) {
        this.img = img;
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

    public void saveImage(){
        try {
            File f = new File(new URI(this.getImg()));
            FileInputStream input = new FileInputStream(f);
            BufferedImage bi = ImageIO.read(input);
            File outputfile = new File(("src\\tmp\\"+this.getNom()+".png"));
            ImageIO.write(bi, "png", outputfile);
            this.setImg(outputfile.toURI().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void saveImageEdit(String prevName, String newImage){
        try {
            BufferedImage bi = ImageIO.read(new FileInputStream(new File("src\\tmp\\"+prevName+".png")));
            File outputfile = new File(("src\\tmp\\"+this.getNom()+".png"));
            ImageIO.write(bi, "png", outputfile);
            this.setImg(outputfile.toURI().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
