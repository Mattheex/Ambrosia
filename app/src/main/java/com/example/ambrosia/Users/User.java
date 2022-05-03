package com.example.ambrosia.Users;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class User implements Parcelable {

    private String born;
    private String first;
    private String last;
    private String password;
    private String pseudo;
    private String sexe;
    private Integer age;
    private Integer poidsActuel;
    private Integer poidsSouhaité;
    private String programme;

    public User(String born, String first, String last, String password, String pseudo, String sexe, Integer age, Integer poidsActuel, Integer poidsSouhaité, String programme, String allergie) {
        this.born = born;
        this.first = first;
        this.last = last;
        this.password = password;
        this.pseudo = pseudo;
        this.sexe = sexe;
        this.age = age;
        this.poidsActuel = poidsActuel;
        this.poidsSouhaité = poidsSouhaité;
        this.programme = programme;
        this.allergie = allergie;
    }

    private String allergie;


    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPoidsActuel() {
        return poidsActuel;
    }

    public void setPoidsActuel(Integer poidsActuel) {
        this.poidsActuel = poidsActuel;
    }

    public Integer getPoidsSouhaité() {
        return poidsSouhaité;
    }

    public void setPoidsSouhaité(Integer poidsSouhaité) {
        this.poidsSouhaité = poidsSouhaité;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getAllergie() {
        return allergie;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public User(){}

    protected User(Parcel in) {
        born = in.readString();
        first = in.readString();
        last = in.readString();
        password = in.readString();
        pseudo = in.readString();
        sexe = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
        if (in.readByte() == 0) {
            poidsActuel = null;
        } else {
            poidsActuel = in.readInt();
        }
        if (in.readByte() == 0) {
            poidsSouhaité = null;
        } else {
            poidsSouhaité = in.readInt();
        }
        programme = in.readString();
        allergie = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(born);
        parcel.writeString(first);
        parcel.writeString(last);
        parcel.writeString(password);
        parcel.writeString(pseudo);
        parcel.writeString(sexe);
        if (age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(age);
        }
        if (poidsActuel == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(poidsActuel);
        }
        if (poidsSouhaité == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(poidsSouhaité);
        }
        parcel.writeString(programme);
        parcel.writeString(allergie);
    }
}