import java.util.Hashtable;
import java.util.LinkedList;

public class ListaInvertida {
    // tabela: chave = palavra, valor = lista de documentos onde a palavra aparece
    private Hashtable<String, LinkedList<String>> tabela;

    /**
     * Construtor: inicializa a tabela hash vazia.
     */
    public ListaInvertida() {
        this.tabela = new Hashtable<>();
    }

    /**
     * Insere o documento na lista associado à palavra.
     * @param palavra palavra-chave
     * @param documento nome do documento onde a palavra aparece
     * @return true se o documento foi inserido (novidade), false se já existia
     */
    public boolean insere(String palavra, String documento) {
        // procura lista existente
        LinkedList<String> lista = tabela.get(palavra);
        if (lista != null) {
            // se já contém, não insere de novo
            if (lista.contains(documento)) {
                return false;
            } else {
                lista.add(documento);
                return true;
            }
        } else {
            // cria nova lista e associa
            lista = new LinkedList<>();
            lista.add(documento);
            tabela.put(palavra, lista);
            return true;
        }
    }

    /**
     * Retorna a lista de documentos para a palavra, ou null se não existir.
     * @param palavra palavra-chave
     * @return LinkedList de nomes de documentos, ou null
     */
    public LinkedList<String> busca(String palavra) {
        return tabela.get(palavra);
    }

    /**
     * Converte toda a tabela invertida para string usando o toString() da própria Hashtable.
     * @return representação textual da tabela
     */
    @Override
    public String toString() {
        return tabela.toString();
    }
}
