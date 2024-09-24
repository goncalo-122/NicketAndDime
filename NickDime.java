import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe que representa um estado do problema "Nickel and Dime"
 * Implementa a interface IState
 */
public class NickDime implements IState {

    private int[] estado; 
    private static final int NICKEL = 1;
    private static final int DIME = 0;
    private static final int VAZIO = -1; 

   
    public NickDime(int[] estadoInicial) {
        this.estado = estadoInicial;
    }

    
    @Override
    public double h() {
        int[] estadoObjetivo = {DIME, DIME, VAZIO, NICKEL, NICKEL};
        int h = 0;
        for (int i = 0; i < estado.length; i++) {
            if (estado[i] != estadoObjetivo[i]) {
                h++;
            }
        }
        return h;
    }

    @Override
    public boolean goal() {
        return Arrays.equals(estado, new int[] {DIME, DIME, VAZIO, NICKEL, NICKEL});
    }

    
    @Override
    public ArrayList<NickDime> suc() {
        ArrayList<NickDime> sucessores = new ArrayList<>();

        for (int i = 0; i < estado.length; i++) {
            if (estado[i] == NICKEL) {
                
                if (i < estado.length - 1 && estado[i + 1] == VAZIO) {
                    int[] novoEstado = estado.clone();
                    novoEstado[i] = VAZIO;
                    novoEstado[i + 1] = NICKEL;
                    sucessores.add(new NickDime(novoEstado));
                }
                
                if (i < estado.length - 2 && estado[i + 1] == DIME && estado[i + 2] == VAZIO) {
                    int[] novoEstado = estado.clone();
                    novoEstado[i] = VAZIO;
                    novoEstado[i + 2] = NICKEL;
                    sucessores.add(new NickDime(novoEstado));
                }
            } else if (estado[i] == DIME) {
               
                if (i > 0 && estado[i - 1] == VAZIO) {
                    int[] novoEstado = estado.clone();
                    novoEstado[i] = VAZIO;
                    novoEstado[i - 1] = DIME;
                    sucessores.add(new NickDime(novoEstado));
                }
               
                if (i > 1 && estado[i - 1] == NICKEL && estado[i - 2] == VAZIO) {
                    int[] novoEstado = estado.clone();
                    novoEstado[i] = VAZIO;
                    novoEstado[i - 2] = DIME;
                    sucessores.add(new NickDime(novoEstado));
                }
            }
        }

        return sucessores;
    }

 
    public static IState getInitialState() {
        return new NickDime(new int[] {NICKEL, VAZIO, NICKEL, DIME, DIME});
    }

   
    @Override
    public boolean equals(Object p) {
        if (p instanceof NickDime) {
            return Arrays.equals(estado, ((NickDime) p).estado);
        }
        return false;
    }

    
    @Override
    public int hashCode() {
        return Arrays.hashCode(estado);
    }

   
    @Override
    public String toString() {
        return Arrays.toString(estado);
    }

    
    public static void main(String[] args) {
        IState estadoInicial = NickDime.getInitialState();
        System.out.println("Estado inicial: " + estadoInicial);

        if (estadoInicial.goal()) {
            System.out.println("Estado inicial já é o estado objetivo!");
        } else {
            ArrayList<NickDime> sucessores = estadoInicial.suc();
            System.out.println("Sucessores do estado inicial:");
            for (NickDime suc : sucessores) {
                System.out.println(suc);
            }
        }
    }
}
