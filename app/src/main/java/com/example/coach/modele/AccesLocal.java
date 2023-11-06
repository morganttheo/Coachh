package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;

    private ContentValues values;

    private SQLiteDatabase bd;

    private static AccesLocal instance;

    public static AccesLocal getInstance(Context context){
        if(instance == null){
            instance = new AccesLocal(context);
        }
        return instance;

    }
    private MySQLiteOpenHelper accesBD;
    private AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase,versionBase);
    }

    public void ajout(Profil profil){
        bd = accesBD.getWritableDatabase();
        values = new ContentValues();
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("sexe", profil.getSexe());
        values.put("age", profil.getAge());
        values.put("dateMesure", profil.getDateMesure().toString());
        bd.insert("profil",null,values);
        bd.close();

    }

    public Profil recupDernier(){
        Profil profil = null;
        bd = accesBD.getReadableDatabase();
        String req ="select * from profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));
            Log.d("date","**********date="+dateMesure);
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer sexe = curseur.getInt(3);
            Integer age = curseur.getInt(4);
            profil = new Profil(dateMesure, poids,taille, age, sexe);
        }
        curseur.close();
        bd.close();
        return profil;
    }



}
