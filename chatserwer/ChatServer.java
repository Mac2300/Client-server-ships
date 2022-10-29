/*
*  GRA W STATKI NA 2 OSOBY
*  Jak grac:
* 1.Uruchom klase ChatServer
* 2.Uruchom klase ChatClient
* 3.Zaznacz pola w oknie mniejszym (moje statki)
* 4.Dopiero teraz dodaj 2 uzytkownika poprzez uruchomienie klasy ChatClient po raz 2
* 5.Zaznacz pola w oknie mniejszym(moje statki) 2 klienta
* 6.Oddaj 1 strzal, tzn zaznacz pole w oknie wiekszym(statki przeciwnika) uzytkownikiem, ktory zostal dodany jako 1
* 7.Uzytkownicy trafiaja na zmiane
* 8.Jesli uzytkownik kliknie we wczesniej juz trafiony przycisk strzal przepada, atakuje kolejny klient
* 9.Gra konczy sie kiedy wszystkie statki zostana zestrzelone







package chatserwer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;


public class ChatServer {


    private static final int PORT = 9001;

    private static HashSet<String> names = new HashSet<String>();

    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    private ArrayList<String> list= new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;


        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("name");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }

                out.println("ustaw");
                writers.add(out);

                String input;
                int intputint = 0;

                while(true) {
                    String line = in.readLine();
                    if (line.startsWith("stop")){
                        if(names.size()==1){
                            out.println("lp");
                        }else {
                            break;
                        }
                    }
                    if (names.size()>=1) {
                        break;
                    }
                }

                while(true) {
                    String line = in.readLine();

                    if(line.startsWith("liczba"))
                    {
                        input = line.substring(6);           //klient wysyla wiadomosc liczba + i, skracamy ja do 6 znakow i zostaje sama liczba
                        intputint = Integer.parseInt(input);//zamienia stringa na inta
                        for (PrintWriter writer : writers) {
                            writer.println("2Sprawdzeniemojestatki" + intputint);     //jesli tak to wysyla ta liczbe do 2 uzytkownika (on wysyla tak naprawde liczbe z zakresu 0-63
                        }
                        // break;
                    }
                    else if(line.startsWith("trafiony")){
                        input = line.substring(8);
                        intputint = Integer.parseInt(input);
                        for (PrintWriter writer : writers) {
                            writer.println("trafiony" + intputint);
                        }
                    }
                    else if(line.startsWith("pudlo")){
                        input = line.substring(5);
                        intputint = Integer.parseInt(input);
                        for (PrintWriter writer : writers) {
                            writer.println("pudlo" + intputint);
                        }
                    }

                    else if (line.startsWith("2przeciwnik")){
                        for (PrintWriter writer : writers) {
                            writer.println("atak2");
                        }
                    }

                    else if(line.startsWith("2liczba"))
                    {
                        input = line.substring(7);           //klient wysyla wiadomosc liczba + i, skracamy ja do 6 znakow i zostaje sama liczba
                        intputint = Integer.parseInt(input);//zamienia stringa na inta
                        for (PrintWriter writer : writers) {
                            writer.println("Sprawdzeniemojestatki" + intputint);     //jesli tak to wysyla ta liczbe do 2 uzytkownika (on wysyla tak naprawde liczbe z zakresu 0-63
                        }
                        // break;
                    }

                    else if(line.startsWith("2trafiony")){
                        input = line.substring(9);
                        intputint = Integer.parseInt(input);
                        for (PrintWriter writer : writers) {
                            writer.println("2trafiony" + intputint);
                        }
                    }
                    else if(line.startsWith("2pudlo")){
                        input = line.substring(6);
                        intputint = Integer.parseInt(input);
                        for (PrintWriter writer : writers) {
                            writer.println("2pudlo" + intputint);
                        }
                    }

                    else if (line.startsWith("od nowa")){
                        for (PrintWriter writer : writers) {
                            writer.println("od nowa");
                        }
                    }
                }


            } catch (IOException e) {
                System.out.println(e);
            } finally {

                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}