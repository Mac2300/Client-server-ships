
package chatclient;

import Listener.Listener;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;


public class ChatClient {

    BufferedReader in;
    PrintWriter out;

    JFrame frame = new JFrame("Moje statki");
    JFrame frame2 = new JFrame("Statki przeciwnika");
    public ArrayList<JButton> list = new ArrayList<>();



    private JButton [] mojestatki = new JButton[64];

    private JButton [] statkiprzeciwnika = new JButton[64];
    int Licznikswiatowy =0;
    int pierwszyklient=0;
    int drugiklient=0;
    int ktoryklient=0;

    public ChatClient() {
        frame.setLayout(new GridLayout(8,8));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        for(int i =0; i<64;i++){
            mojestatki[i] = new JButton();
            mojestatki[i].addActionListener(new Listener());
        }
        for(int i=0; i<64; i++) {
            frame.add(mojestatki[i]);
        }

        frame.setSize(300,300);
        frame.setVisible(true);


//---------------------------------------------------------------

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(new GridLayout(8,8));

        for(int i =0; i<64;i++){
            statkiprzeciwnika[i] = new JButton();
            statkiprzeciwnika[i].addActionListener(new Listener());
        }
        for(int i=0; i<64; i++) {
            frame2.add(statkiprzeciwnika[i]);
        }


        frame2.setSize(500,500);
        frame2.setVisible(true);
    }


    private String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Welcome to the Chatter",
                JOptionPane.QUESTION_MESSAGE);
    }


    private String getName() {
        return JOptionPane.showInputDialog(
                frame,
                "Choose a screen name:",
                "Screen name selection",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void UstawStatki(int i){

        JOptionPane.showMessageDialog(frame, "Ustaw statek o polu " + i);

    }

    private void Sprawdzanie(int liczba){
        int licznik = 0;
        while(true){

            for(int i =0 ; i<64; i++) {
                    if(mojestatki[i].getBackground() == Color.RED){
                        licznik++;
                        mojestatki[i].setBackground(Color.BLACK);
                }
            }
            if(licznik==liczba){
                break;
            }
        }
    }

    private void KoniecUstawiania(){

        JOptionPane.showMessageDialog(frame, "STOP");

    }

    private void F_atak(){
        int licznik=0;
        while(true){
            for (int i = 0; i < 64; i++) {
                if (statkiprzeciwnika[i].getBackground() == Color.RED ) {
                    out.println("liczba" + i);
                    licznik=1;
                }
            }
            if(licznik==1){
                break;
            }
        }
    }


    private void F_odczytanie2(String przeslanedane){
        int licznik=0;
        String input = przeslanedane.substring(22);                  //Tworzymy zmienna input do ktorej przypisujemy wiadomosc przeslana i skracamy ja zeby uzyskac sama liczbe
        int intputint = Integer.parseInt(input);                     //Zamieniamy Stringa na inta
       while(true) {
                    if (mojestatki[intputint].getBackground() == (Color.BLACK)) {   //sprawdzamy czy przycisk wcisniety przez przeciwnika pokrywa sie z przyciskiem na ktorym my wczesniej postawilismy statek
                        mojestatki[intputint].setBackground(Color.BLUE);                              //przeciwnik trafil w nasz statek
                        out.println("trafiony" + intputint);
                        licznik=1;
                    } else {
                        mojestatki[intputint].setBackground(Color.GREEN);                             //przeciwnik nie trafil w nasz statek
                        out.println("pudlo" + intputint);
                        licznik=1;
                    }
                    if(licznik==1){
                        break;
                    }
                }
    }

    private void trafiony(String line) {
        String input = line.substring(8);
        int intputint = Integer.parseInt(input);
        statkiprzeciwnika[intputint].setBackground(Color.BLUE);
        out.println("2przeciwnik");
    }

    private void pudlo(String line) {
        String input = line.substring(5);
        int intputint = Integer.parseInt(input);
        statkiprzeciwnika[intputint].setBackground(Color.GREEN);
        out.println("2przeciwnik");
    }

    private void F_atak2(){
        int licznik=0;
        while(true){
            for (int i = 0; i < 64; i++) {
                if (statkiprzeciwnika[i].getBackground() == Color.RED ) {
                    out.println("2liczba" + i);
                    licznik=1;
                }
            }
            if(licznik==1){
                break;
            }
        }
    }



    private void F_odczytanie(String przeslanedane){
        int licznik=0;
        String input = przeslanedane.substring(21);                  //Tworzymy zmienna input do ktorej przypisujemy wiadomosc przeslana i skracamy ja zeby uzyskac sama liczbe
        int intputint = Integer.parseInt(input);                     //Zamieniamy Stringa na inta
        while(true) {
            if (mojestatki[intputint].getBackground() == Color.BLACK) {   //sprawdzamy czy przycisk wcisniety przez przeciwnika pokrywa sie z przyciskiem na ktorym my wczesniej postawilismy statek
                mojestatki[intputint].setBackground(Color.BLUE);                              //przeciwnik trafil w nasz statek
                out.println("2trafiony" + intputint);
                licznik=1;
            } else {
                mojestatki[intputint].setBackground(Color.GREEN);                             //przeciwnik nie trafil w nasz statek
                out.println("2pudlo" + intputint);
                licznik=1;
            }
            if(licznik==1){
                break;
            }
        }
    }

    private void trafiony2(String line) {
        String input = line.substring(9);
        int intputint = Integer.parseInt(input);
        statkiprzeciwnika[intputint].setBackground(Color.BLUE);
        out.println("od nowa");
    }

    private void pudlo2(String line) {
        String input = line.substring(6);
        int intputint = Integer.parseInt(input);
        statkiprzeciwnika[intputint].setBackground(Color.GREEN);
        out.println("od nowa");
    }

    private void KoniecLose(){
        int licznik = 0;
        while(true){

            for(int i =0 ; i<64; i++) {
                if(mojestatki[i].getBackground() == Color.BLUE){
                    licznik++;
                }
            }
            if(licznik==9){
                WiadomoscLose();
            }
            else{
                break;
            }
        }
    }

    private void KoniecWin(){
        int licznik = 0;
        while(true){

            for(int i =0 ; i<64; i++) {
                if(statkiprzeciwnika[i].getBackground() == Color.BLUE){
                    licznik++;
                }
            }
            if(licznik==9){
                WiadomoscWin();
            }
            else{
                break;
            }
        }
    }

    private void WiadomoscLose(){
        JOptionPane.showMessageDialog(frame, "YOU LOSE");
    }

    private void WiadomoscWin(){
        JOptionPane.showMessageDialog(frame, "YOU WIN!");
    }


    private void runn() throws IOException {

        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);


        while (true) {
            KoniecLose();
            KoniecWin();
            String line = in.readLine();

            if (line.startsWith("name")) {
                out.println(getName());
            } else if (line.startsWith("ustaw")) {
                UstawStatki(4);
                Sprawdzanie(4);
                UstawStatki(3);
                Sprawdzanie(3);
                UstawStatki(2);
                Sprawdzanie(2);
                KoniecUstawiania();
                out.println("stop");
            } else if (line.startsWith("lp")) {
                Licznikswiatowy++;
                pierwszyklient = Licznikswiatowy%2;
                if (pierwszyklient==1) {
                    F_atak();
                } else {
                    continue;
                }
            } else if (line.startsWith("2Sprawdzeniemojestatki")) {
                Licznikswiatowy++;
                drugiklient=Licznikswiatowy%2;
                if(drugiklient==1){
                F_odczytanie2(line);
                }
            } else if (line.startsWith("trafiony")) {
                Licznikswiatowy++;
                pierwszyklient = Licznikswiatowy%2;
                if(pierwszyklient==1) {
                    trafiony(line);
                }
            } else if (line.startsWith("pudlo")) {
                Licznikswiatowy++;
                pierwszyklient = Licznikswiatowy%2;
                if(pierwszyklient==1) {
                    pudlo(line);
                }
            } else if (line.startsWith("atak2")) {
                Licznikswiatowy++;
                drugiklient=Licznikswiatowy%2;
                if(drugiklient==1) {
                    F_atak2();
                }
            } else if (line.startsWith("Sprawdzeniemojestatki")) {
                Licznikswiatowy++;
                pierwszyklient=Licznikswiatowy%2;
                if(pierwszyklient==1) {
                    F_odczytanie(line);
                }
            } else if (line.startsWith("2trafiony")) {
                Licznikswiatowy++;
                drugiklient=Licznikswiatowy%2;
                if(drugiklient==1) {
                    trafiony2(line);
                }
            } else if (line.startsWith("2pudlo")) {
                Licznikswiatowy++;
                drugiklient=Licznikswiatowy%2;
                if(drugiklient==1) {
                    pudlo2(line);
                }
            } else if (line.startsWith("od nowa")) {
                Licznikswiatowy++;
                pierwszyklient=Licznikswiatowy%2;
                if(pierwszyklient==1) {
                    F_atak();
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.runn();
    }

}
