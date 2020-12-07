package br.edu.uniritter.canoas.poo.jogo.view;

import br.edu.uniritter.canoas.poo.jogo.model.Casa;
import br.edu.uniritter.canoas.poo.jogo.model.CasaAzar;
import br.edu.uniritter.canoas.poo.jogo.model.CasaSorte;

import java.util.List;

public class CasaView {
    public static void desenhaCasa(Casa casa, String txtJogadores) {
        System.out.println(preenche("",'-', 25));
        if (casa instanceof CasaSorte) {
            System.out.println(preenche(" Casa de SORTE ",' ',25));
            System.out.println(preenche(" Avance "+casa.getIncremento()+" casas.",' ',25));
        } else {
            if (casa instanceof CasaAzar) {
                System.out.println(preenche("Casa de "+((CasaAzar)casa).getDescricao(),' ',25));
                System.out.println(preenche(" Volte "+casa.getIncremento()+" casas.",' ',25));
            } else{
                System.out.println(preenche(" Casa Neutra    ",' ',25));
            }

        }
        if (txtJogadores != "") {
            System.out.println(preenche("jogadores:",' ',25));
            System.out.println(preenche(txtJogadores,' ',25));
        }
        System.out.println(preenche("",'-', 25));

    }

    private static String preenche(String texto, char preenchimento, int tamanho) {
        //for(int t = texto.length(); t <= tamanho; t++) {
        //    texto += preenchimento;
        //}
        while (texto.length()<tamanho) {
            texto += preenchimento;
        }
        return "|"+texto+"|";
    }
}