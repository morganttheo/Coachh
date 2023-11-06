package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

public final class Controle {
    private static Controle instance;
    private static Profil profil;
    private static String nomFic = "saveprofil";

    private Controle(Context context) {

        recupSerialize(context);
    }

    public final static Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle(context);
        }
        return Controle.instance;
    }

    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize( nomFic,  profil, context);
    }

    /**
     *
     * @param context
     */
    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }
    public float getImg(){
        if(profil != null){
            return profil.getImg();
        }else{
            return 0;
        }

    }
    public String getMessage(){
        if(profil != null){
            return profil.getMessage();
        }else{
            return "";
        }
    }

    /**
     * Retourne le poids si le profil existe
     * @return
     */
    public Integer getPoids(){
        if(profil == null){
            return null;
        }else{
            return profil.getPoids();
        }
    }

    /**
     * Retourne la taille si le profil existe
     * @return
     */
    public Integer getTaille(){
        if(profil == null){
            return null;
        }else{
            return profil.getTaille();
        }
    }

    /**
     * Retourne l'âge si le profil existe
     * @return
     */
    public Integer getAge(){
        if(profil == null){
            return null;
        }else{
            return profil.getAge();
        }
    }

    /**
     * Retourne le sexe si le profil existe
     * @return
     */
    public Integer getSexe(){
        if(profil == null){
            return null;
        }else{
            return profil.getSexe();
        }
    }
}
