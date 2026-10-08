# Gioca Numeri

Il programma simula il comportamento dei thread tramite un gioco in cui viene
inserita una parola e un numero per poi calcolare un punteggio dei 2 giocatori presenti.
L'obbiettivo del programma è vedere il funzionamento dei thread su un semplice programma.

## Logica del gioco

Appena inizia il programma viene chiesto di inserire un numero e una parola
che rimangono costanti per il resto dell'esecuzione del programma, successivamente
viene fermato il programma per 5 secondi per poi avviare l'esecuzione del gioco
che andrà a contare fino al numero da noi impostato all'inizio. L'operazione di conteggio
parte con Giocatore1 (primo Thread), quando il thread termina parte l'esecuzione di
Giocatore2 (secondo Thread). Infine viene stampato il punteggio di entrambi i giocatori 
che viene calcolato secondo questo criterio: **lunghezza parola * numero scelto**.

## File del progetto

- `GiocaNumeri.java` - classe main che esegue il codice
- `Giocatore.java` - classe che implementa i thread e la logica di gioco




