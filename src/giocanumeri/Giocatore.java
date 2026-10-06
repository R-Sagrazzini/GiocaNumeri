package giocanumeri;

/**
 * La classe Giocatore estende Thread per gestire il gioco dei numeri in modalità concorrente.
 * Permette di elaborare una parola inserita dall'utente, eseguire un ciclo di conteggio,
 * calcolare un punteggio e gestire eventuali errori a runtime.
 *
 * @author Riccardo Sagrazzini
 * @version 1.0
 */
public class Giocatore extends Thread {
    private String nome;
    private String parola;
    private int punteggio;
    private int numeroLimite;

    public Giocatore(String nome, int numeroLimite) {
        this.nome = nome;
        this.numeroLimite = numeroLimite;
        this.punteggio = 0;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        // controllo se la parola è vuota
        if (parola == null || parola.trim().isEmpty()) {
            System.err.println("[" + nome + "] Errore: La parola inserita non può essere vuota o nulla!");
            this.parola = "DEFAULT";
        } else {
            this.parola = parola;
        }
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    /**
     * Metodo eseguito dal thread all'avvio. Esegue il conteggio, trasforma la parola
     * in maiuscolo, calcola il punteggio, applica la pausa richiesta e usa yield().
     */
    @Override
    public void run() {
        try {
            System.out.println("--> Thread " + nome + " avviato.");

            //ciclo di conteggio fino al valore scelto
            for (int i = 1; i <= numeroLimite; i++) {
                System.out.println("Thread " + nome + " - Conteggio: " + i);

                //uso di Thread.yield() per alternare l'esecuzione dei thread
                Thread.yield();
            }

            // Elaborazione della parola in maiuscolo
            if (parola != null) {
                String parolaMaiuscola = parola.toUpperCase();
                System.out.println("==> Thread " + nome + " | Parola in maiuscolo: " + parolaMaiuscola);
            }

            // Calcolo del punteggio (criterio: lunghezza della parola * numero scelto)
            if (parola != null) {
                punteggio = parola.length() * numeroLimite;
            }

            // stampa riga di riferimento (simulata intorno alla riga 49)
            System.out.println("--- [Checkpoint] Thread " + nome + " completato con successo. Punteggio provvisorio: " + punteggio);

            //inserisco una sospensione di 2 secondi utilizzando Thread.sleep()
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            System.err.println("[!]" + nome + " interrotto inaspettatamente: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}