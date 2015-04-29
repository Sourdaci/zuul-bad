
/**
 * Write a description of class WorldOfGreg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldOfGreg
{
    // instance variables - replace the example below with your own
    private static Game juego;

    public static void main(String[] args){
        juego = new Game();
        System.out.println("DOS/4GW Professional Protected Mode Run-time Version 1.97");
        System.out.println("Copyright (c) Rational Systems, Inc. 1990-1994");
        System.out.println("Loading 'World of Greg', please wait...");
        juego.play();
    }
}
