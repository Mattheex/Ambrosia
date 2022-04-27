package com.example.ambrosia.programmes;

public abstract class ProgrammeFactory {
    public static final int PRISEPOIDS=1;
    public static final int PERTEPOIDS=2;
    public static final int FITNESS=3;
    public static final int VEGAN=4;
    abstract protected Programme choose(int type, int jour) throws Throwable;

}
