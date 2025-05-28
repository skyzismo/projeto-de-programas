public class GoogleMain {
    public static void main(String[] args) {
        // Cria a lista invertida
        ListaInvertida li = new ListaInvertida();

        // Insere várias palavras e documentos
        li.insere("force", "document1.txt");
        li.insere("force", "document2.txt");
        li.insere("force", "document3.txt");
        li.insere("always", "document1.txt");
        li.insere("one",    "document3.txt");
        li.insere("is",     "document2.txt");
        li.insere("is",     "document3.txt");
        li.insere("be",     "document1.txt");
        li.insere("will",   "document1.txt");
        li.insere("you",    "document1.txt");
        li.insere("you",    "document2.txt");
        li.insere("the",    "document1.txt");
        li.insere("the",    "document2.txt");
        li.insere("the",    "document3.txt");
        li.insere("remember","document1.txt");
        li.insere("this",   "document3.txt");
        li.insere("strong", "document2.txt");

        // Testa buscas
        System.out.println(li.busca("force"));
        System.out.println(li.busca("the"));
        System.out.println(li.busca("foo"));  // deve imprimir null

        // Agora chama explicitamente o toString() da lista completa
        System.out.println(li.toString());
    }
}
