package br.edu.uniritter.canoas.poo.jogo.view;
import br.edu.uniritter.canoas.poo.jogo.model.Tabuleiro;

public class TabuleiroView {
    public static void showSituacaoAtual(Tabuleiro tab){
        System.out.println("\n============ Situação atual do tabuleiro ============\n");
        for (int i = 0; i < tab.getQtdCasas(); i++) {
            CasaView.desenhaCasa(tab.getCasa(i), tab.getJogadoresCasa(i));
        }
        System.out.println("============");
        JogoView.continuar();
    }

    public static void mostrarDado(int numeroSorteado) {
        System.out.println("O dado sorteou: " + numeroSorteado + "\n");
        JogoView.continuar();
    }
}