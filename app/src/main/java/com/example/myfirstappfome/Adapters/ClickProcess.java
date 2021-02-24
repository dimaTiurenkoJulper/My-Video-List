package com.example.myfirstappfome.Adapters;

/***
 * Interface for set Click listener into fragment
 * @param <T> get View
 * @param <R> Get object , which call event
 */
public  interface ClickProcess<T , R> {
    void get (T t , R r );
}