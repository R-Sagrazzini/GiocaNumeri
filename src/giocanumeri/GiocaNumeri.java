package giocanumeri;

import java.util.Scanner;

/**
 * Classe principale che gestisce l'avvio dell'applicazione, l'interazione con l'utente,
 * la creazione dei thread e la loro sincronizzazione tramite join() e sleep().
 *
 * @author Riccardo Sagrazzini
 * @version 1.0
 */
public class GiocaNumeri {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        String parola = "";

        System.out.println("=== GIOCO DEI NUMERI (MULTITHREADING) ===");

        //acquisizione del numero con gestione degli errori (System.err)
        try {
            System.out.print("Inserisci un numero intero positivo: ");
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                scanner.nextLine();
                if (numero <= 0) {
                    System.err.println("[Avviso] Il numero inserito è minore o uguale a zero. Verrà impostato a 5 di default.");
                    numero = 5;
                }
            } else {
                System.err.println("[Errore] Input non valido per il numero. Verrà assegnato il valore predefinito 5.");
                numero = 5;
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.err.println("[Errore] Errore nella lettura del numero: " + e.getMessage());
            numero = 5;
        }

        // acquisizione della parola
        System.out.print("Inserisci una parola: ");
        parola = scanner.nextLine();
        scanner.close();

        // Creazione dei due thread giocatori
        Giocatore g1 = new Giocatore("Giocatore1", numero);
        Giocatore g2 = new Giocatore("Giocatore2", numero);

        g1.setParola(parola);
        g2.setParola(parola);

        // Avvio del primo thread (g1)
        g1.start();

        // sospensione di 5 secondi dopo l'avvio del primo thread
        try {
            System.out.println("Sospensione del thread principale per 5 secondi...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
                System.err.println("[Errore] Errore durante la sospensione: " + e.getMessage());
        }

        // Avvio del secondo thread (g2)
        g2.start();

        //uso di join() per la logica: g1 e g2 in parallelo
        try {
            System.out.println("In attesa del completamento dei thread g1 e g2...");
            g1.join();
            g2.join();
            //uso join e non start perchè il programma andrebbe avanti prima che i thread g1 e g2 terminino la loro esecuzione
        } catch (InterruptedException e) {
            System.err.println("[Errore] Errore nell'attesa dei thread con join(): " + e.getMessage());
        }

        // risultati finali mostrati dal thread main
        System.out.println("===== RISULTATI FINALI =====");
        System.out.println("Numero scelto: " + numero);
        System.out.println("Parola inserita: " + parola);
        System.out.println(g1.getName() + " - Punteggio finale: " + g1.getPunteggio());
        System.out.println(g2.getName() + " - Punteggio finale: " + g2.getPunteggio());
        System.out.println("==============================");
    }
}