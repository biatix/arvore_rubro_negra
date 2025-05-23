//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //arvore vazia
        ArvoreRubroNegra arvoreVazia = new ArvoreRubroNegra();

        arvoreVazia.raiz = null;
        System.out.println("arvore vazia " + arvoreVazia.repr());
        //Saida: -

        //arvore só com a raiz
        ArvoreRubroNegra arvoreSoComRaiz = new ArvoreRubroNegra();

        arvoreSoComRaiz.put(40, "R");
        System.out.println("arvore só com a raiz " + arvoreSoComRaiz.repr());
        //Saida: [-] 40_R [-]

        // Construindo uma árvore de exemplo:
        //       10_R
        //      /     \
        //   5         20
        //     \        \
        //     7         55
        ArvoreRubroNegra arvorePreenchida = new ArvoreRubroNegra();

        arvorePreenchida.put(10, "A");
        arvorePreenchida.put(5, "B");
        arvorePreenchida.put(20, "C");
        arvorePreenchida.put(7, "D");
        arvorePreenchida.put(55, "E");

        System.out.println("arvore preenchida " + arvorePreenchida.repr());
        // Saída: "[[-] 5 [[-] 7 [-]]] 10_R [[[-] 55 [-]] 20 [-]]"

    }
}