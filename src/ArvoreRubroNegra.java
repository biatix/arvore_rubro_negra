public class ArvoreRubroNegra {
    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    class No {
        int chave;
        String valor;
        No dir = null;
        No esq = null;
        boolean cor;

        No(int chave, String valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    No raiz;

    private boolean eVermelho(No no) {
        if (no == null) return false;
        return no.cor = VERMELHO;
    }

    private No rodaPraEsquerda(No no) {
        No x = no.dir;
        no.dir = x.esq;
        x.esq = no;
        x.cor = no.cor;
        no.cor = VERMELHO;

        return x;
    }

    private No rodaPraDireita(No no) {
        No x = no.dir;
        no.esq = x.dir;
        x.dir = no;
        no.dir = x.esq;
        x.cor = no.cor;
        no.cor = VERMELHO;

        return x;
    }

    private void trocaCor(No no) {
        no.cor = VERMELHO;
        no.esq.cor = PRETO;
        no.dir.cor = PRETO;
    }

    public void put(int chave, String valor) { //armazena um par chave/valor na árvore
        /*se o método put receber uma chave que já está na árvore, o novo valor sobrepõe o valor anterior;
        o método put não pode receber um valor nulo, isso deve ser verificado dentro do método e tratado de uma forma apropriada (vocês podem decidir como);*/

        raiz = put(raiz, chave, valor);
    }

    private No put(No no, int chave, String valor) {
        if (no == null) return new No(chave, valor);
        if (chave < no.chave)
            no.esq = put(no.esq, chave, valor); //se a chave é menor que a do nó que estamos comparando, a gente anda pra esquerda
        else if (chave > no.chave)
            no.dir = put(no.dir, chave, valor); //se a chave é maior que a do nó que estamos comparando a gente anda pra direita
        else no.valor = valor; //sobrepondo o valor do nó existente

        if (eVermelho(no.dir) && !eVermelho(no.esq)) no = rodaPraEsquerda(no);
        if (eVermelho(no.dir) && eVermelho(no.esq.esq)) no = rodaPraDireita(no);
        if (eVermelho(no.esq) && eVermelho(no.dir)) trocaCor(no);

        return no;
    }

    public No get(int chave) { //retorna o valor associado a uma chave e armazenado anteriormente
        return get(raiz, chave);
    }

    private No get(No no, int chave) {
        if (no != null) {
            if (chave < no.chave) get(no.esq, chave); //procura do lado esquerdo se a chave for menor que o nó
            else if (chave > no.chave) get(no.dir, chave); //procura do lado direito se a chave foi maior que o nó
            else return no; //encontrou o nó
        }

        return null;
    }

    public boolean contains(int chave) { //indica se uma chave está ou não armazenada na árvore (sem retornar o valor associado)
        return get(chave) != null; //usa o método get pra ver se a chave existe na árvore, e retorna falso quando o metodo get retorna null
    }

    public String repr() { //retorna a representação da árvore como uma string
        //* uma árvore vazia é representada pela string "-";
        //* a representação de uma árvore geral segue o formato "[<esq>] K_R [<dir>]", onde:
        //K_R é a chave (como uma string) armazenada na raiz da árvore,
        //<esq> é a representação da subárvore a partir do filho esquerdo da raiz,
        //<dir> é a representação da subárvore a partir do filho direito da raiz,
        //(atenção: os colchetes são caracteres a serem incluídos na string)
        String arv = "";

        if (raiz == null) return arv += "-";
        else {
            arv += "[" + repr(raiz.esq) + "]";
            arv += " " + raiz.chave + "_R ";
            arv += "[" + repr(raiz.dir) + "]";
            return arv;
        }

    }

    private String repr(No no) {
        if (no == null) return "-";
        String esqStr = (no.esq != null) ? repr(no.esq) : "-"; //método recursivo que varre todos os nós da arv esquerda
        String dirStr = (no.dir != null) ? repr(no.dir) : "-"; //método recursivo que varre todos os nós da arv direita
        return "[" + esqStr + "] " + no.chave + " [" + dirStr + "]";
    }

}
