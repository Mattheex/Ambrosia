package com.example.ambrosia.Users;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class User implements Parcelable {

    private Date born;
    private String first;
    private String last;
    private String password;
    private String mail;
    private String sexe;
        private Integer age;

    public User(){}

    public User(Date born, String firstname, String lastname, String password, String sexe, Integer age ) {
        this.born = born;
        this.first = firstname;
        this.last = lastname;
        this.password = password;
        this.sexe = sexe;
        this.age = age;
    }

    protected User(Parcel in) {
        first = in.readString();
        last = in.readString();
        password = in.readString();
        mail = in.readString();
        sexe = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
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

    public String getFirst() { return first; }

    public void setFirst(String first) { this.first = first; }

    public String getLast() { return last; }

    public void setLast(String last) { this.last = last; }

    public Date getBorn() { return born; }

    public void setBorn(Date born) {
        this.born = born;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getSexe() { return sexe; }

    public void setSexe(String sexe) { this.sexe = sexe; }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(first);
        parcel.writeString(last);
        parcel.writeString(password);
        parcel.writeString(mail);
        parcel.writeString(sexe);
        if (age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(age);
        }
    }
}
