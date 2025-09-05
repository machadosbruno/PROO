import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * ARQUIVO DE CONSULTA JAVA - TÓPICO 2: TIPOS DE DADOS E ESTRUTURAS BÁSICAS
 * * Objetivo: Apresentar os tipos primitivos e as estruturas de dados fundamentais
 * em Java, como Vetores (Arrays), Listas, Pilhas e Filas.
 * * @author Seu Assistente Gemini
 * @version 1.0
 */
public class GuiaEstruturaDeDados {

    public static void main(String[] args) {

        // =================================================================================
        // 1. TIPOS PRIMITIVOS
        // =================================================================================
        // -> O QUE SÃO: São os tipos de dados mais básicos em Java. Eles não são objetos.
        //    Armazenam valores diretamente na memória, o que os torna muito rápidos.
        // ---------------------------------------------------------------------------------

        // --- Tipos Numéricos Inteiros ---
        byte    idade = 30;                 // 8 bits. Faixa: -128 a 127. Ideal para economizar memória.
        short   anoModelo = 2024;           // 16 bits. Faixa: -32,768 a 32,767.
        int     populacaoDaCidade = 1200000;  // 32 bits. O tipo inteiro mais comum e padrão.
        long    populacaoMundial = 8000000000L; // 64 bits. Usado para números muito grandes. Note o 'L' no final.

        // --- Tipos Numéricos de Ponto Flutuante (Decimais) ---
        float   precoProduto = 49.99f;      // 32 bits. Precisão simples. Note o 'f' no final.
        double  pi = 3.14159265359;         // 64 bits. Precisão dupla. É o tipo decimal padrão.

        // --- Outros Tipos ---
        char    letraInicial = 'G';         // 16 bits. Armazena um único caractere Unicode. Usa aspas simples.
        boolean isJavaDivertido = true;       // 1 bit (geralmente). Armazena apenas 'true' ou 'false'.

        System.out.println("--- Tipos Primitivos ---");
        System.out.println("Idade (byte): " + idade);
        System.out.println("Preço (float): " + precoProduto);
        System.out.println("Letra (char): " + letraInicial);
        System.out.println("É divertido? (boolean): " + isJavaDivertido);
        System.out.println("\n----------------------------------\n");


        // =================================================================================
        // 2. VETORES (ARRAYS)
        // =================================================================================
        // -> O QUE SÃO: Estrutura de dados de tamanho FIXO que armazena elementos
        //    do mesmo tipo em sequência. O acesso é feito por um índice (começando em 0).
        // ---------------------------------------------------------------------------------

        // --- Vetor Unidimensional ---
        
        // Declaração e inicialização (forma 1: com valores definidos)
        int[] notas = {8, 7, 9, 10, 6};

        // Declaração e inicialização (forma 2: definindo o tamanho)
        String[] nomes = new String[4];
        nomes[0] = "Ana";
        nomes[1] = "Bruno";
        nomes[2] = "Carlos";
        nomes[3] = "Daniela";

        System.out.println("--- Vetor Unidimensional ---");
        System.out.println("A nota do segundo aluno é: " + notas[1]); // Acessando o elemento de índice 1

        // Percorrendo um vetor com 'for-each' (forma mais moderna)
        System.out.print("Nomes dos alunos: ");
        for (String nome : nomes) {
            System.out.print(nome + " ");
        }
        System.out.println("\n");


        // --- Vetor Multidimensional (Matriz) ---
        // Exemplo: uma matriz 2x3 (2 linhas, 3 colunas)
        int[][] matriz = {
            {1, 2, 3}, // Linha 0
            {4, 5, 6}  // Linha 1
        };

        System.out.println("--- Vetor Multidimensional (Matriz) ---");
        System.out.println("Elemento na linha 1, coluna 2: " + matriz[1][2]); // Deve imprimir 6

        // Percorrendo a matriz com 'for' aninhado
        for (int i = 0; i < matriz.length; i++) { // Percorre as linhas
            for (int j = 0; j < matriz[i].length; j++) { // Percorre as colunas da linha atual
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n----------------------------------\n");


        // =================================================================================
        // 3. LISTAS (INTERFACE List, IMPLEMENTAÇÃO ArrayList)
        // =================================================================================
        // -> O QUE SÃO: Parte do Java Collections Framework. São como vetores, mas com
        //    tamanho DINÂMICO (podem crescer e diminuir). Permitem elementos duplicados.
        //    'ArrayList' é a implementação mais comum.
        // ---------------------------------------------------------------------------------
        List<String> listaDeCompras = new ArrayList<>();

        // Adicionando elementos
        listaDeCompras.add("Maçã");
        listaDeCompras.add("Leite");
        listaDeCompras.add("Pão");
        listaDeCompras.add("Maçã"); // Listas permitem duplicados

        System.out.println("--- Listas (ArrayList) ---");
        System.out.println("Lista original: " + listaDeCompras);
        System.out.println("Tamanho da lista: " + listaDeCompras.size());
        System.out.println("Elemento no índice 2: " + listaDeCompras.get(2));

        // Removendo um elemento
        listaDeCompras.remove("Leite");
        System.out.println("Lista após remover 'Leite': " + listaDeCompras);
        System.out.println("\n----------------------------------\n");


        // =================================================================================
        // 4. PILHAS (Stack)
        // =================================================================================
        // -> O QUE SÃO: Estrutura de dados LIFO (Last-In, First-Out). O último
        //    elemento a entrar é o primeiro a sair. Pense numa pilha de pratos.
        // ---------------------------------------------------------------------------------
        Stack<String> pilhaDeLivros = new Stack<>();

        // Empilhando (push)
        pilhaDeLivros.push("O Senhor dos Anéis");
        pilhaDeLivros.push("Duna");
        pilhaDeLivros.push("Fundação");

        System.out.println("--- Pilhas (Stack) ---");
        System.out.println("Pilha completa: " + pilhaDeLivros);

        // Espiando o topo (peek) sem remover
        System.out.println("Livro no topo da pilha: " + pilhaDeLivros.peek());

        // Desempilhando (pop) - remove o elemento do topo
        String livroRemovido = pilhaDeLivros.pop();
        System.out.println("Livro removido do topo: " + livroRemovido);
        System.out.println("Pilha após remoção: " + pilhaDeLivros);
        System.out.println("\n----------------------------------\n");


        // =================================================================================
        // 5. FILAS (INTERFACE Queue, IMPLEMENTAÇÃO LinkedList)
        // =================================================================================
        // -> O QUE SÃO: Estrutura de dados FIFO (First-In, First-Out). O primeiro
        //    elemento a entrar é o primeiro a sair. Pense numa fila de banco.
        //    'LinkedList' é uma implementação comum da interface 'Queue'.
        // ---------------------------------------------------------------------------------
        Queue<String> filaDeAtendimento = new LinkedList<>();

        // Enfileirando (add ou offer)
        filaDeAtendimento.add("Cliente A");
        filaDeAtendimento.add("Cliente B");
        filaDeAtendimento.offer("Cliente C"); // 'offer' é mais seguro, não lança exceção se a fila estiver cheia

        System.out.println("--- Filas (Queue/LinkedList) ---");
        System.out.println("Fila de atendimento: " + filaDeAtendimento);

        // Espiando o primeiro da fila (peek) sem remover
        System.out.println("Próximo a ser atendido: " + filaDeAtendimento.peek());

        // Desenfileirando (remove ou poll) - remove o primeiro da fila
        String clienteAtendido = filaDeAtendimento.poll(); // 'poll' é mais seguro, retorna null se a fila estiver vazia
        System.out.println("Cliente atendido: " + clienteAtendido);
        System.out.println("Fila após atendimento: " + filaDeAtendimento);
    }
}