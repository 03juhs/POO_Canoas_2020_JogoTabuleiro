package br.edu.uniritter.canoas.poo.jogo;

import br.edu.uniritter.canoas.poo.jogo.model.*;
import br.edu.uniritter.canoas.poo.jogo.view.CasaView;
import br.edu.uniritter.canoas.poo.jogo.view.JogoView;
import br.edu.uniritter.canoas.poo.jogo.view.TabuleiroView;

public class JogoController {
    private static int qtdJogadores;
    private static Tabuleiro tabuleiro;
    private static int jogadorAtual = 0;
    private static boolean finalizado = false;

    public static void iniciarJogo() {
        tabuleiro = new Tabuleiro(50,20,20);
        qtdJogadores = JogoView.intQtdJogadores(2, 6);
        registrarJogadores();

        while(!finalizado) {
            iniciarJogada();
            avancaJogador();
            verificaSeVenceuOJogo();
            TabuleiroView.showSituacaoAtual(tabuleiro);
            proximoJogador();
        }
        JogoView.mensagem(tabuleiro.getJogadores().get(jogadorAtual));
    }

    private static int validaAvanco(int incremento){
        Jogador jogador = tabuleiro.getJogadores().get(jogadorAtual);

        int novaPosicao = jogador.getPosicaoAtual() + incremento;
        if (novaPosicao > (tabuleiro.getQtdCasas() - 1)) {
            return (tabuleiro.getQtdCasas() - 1) - jogador.getPosicaoAtual();
        }
        if (novaPosicao < 0) {
            return 0;
        }
        return incremento;
    }

    private static void avancaJogador(){
        int numeroSorteado = tabuleiro.getDado().jogar();
        TabuleiroView.mostrarDado(numeroSorteado);
        tabuleiro.getJogadores().get(jogadorAtual).avanca(validaAvanco(numeroSorteado));
        Casa casa;

        do {
            casa = tabuleiro.getCasa(tabuleiro.getJogadores().get(jogadorAtual).getPosicaoAtual());
            int A = validaAvanco(casa.getIncremento());
            tabuleiro.getJogadores().get(jogadorAtual).avanca(A);
            CasaView.desenhaCasa(casa, "");
            if (A == 0) {
                break;
            }
            verificaSeVenceuOJogo();
        }while(!(casa instanceof CasaNeutra) && !finalizado);
    }

    private static void verificaSeVenceuOJogo() {
        int pa = tabuleiro.getJogadores().get(jogadorAtual).getPosicaoAtual();
        if (pa == (tabuleiro.getQtdCasas() - 1)) {
            finalizado = true;
        }
    }

    private static void proximoJogador() {
        jogadorAtual++;
        if(jogadorAtual == qtdJogadores) {
            jogadorAtual = 0;
        }
    }

    public static void registrarJogadores() {
        for (int i = 1; i <= qtdJogadores; i++) {
            String n = JogoView.InformeJogador(i);
            try {
                tabuleiro.addJogador(new Jogador(n));
            } catch (MuitosJogadoresException e) {
                e.printStackTrace();
            }
        }
    }

    private static void iniciarJogada() {
        JogoView.mostraJogadorAtual(tabuleiro.getJogadores().get(jogadorAtual));
    }
}