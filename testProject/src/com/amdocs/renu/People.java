package com.amdocs.renu;

/**
 * Created by atinsingh on 6/20/17.
 */
public class People {

    int age;
    String name;

   public People(){

   }

   public void setAge(int age){
       this.age = age;
   }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void printDetails(){
       System.out.printf("Your Age is "+this.age+" and Name is "+name);
   }

}
