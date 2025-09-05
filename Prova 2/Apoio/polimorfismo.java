/**
 * ARQUIVO DE CONSULTA JAVA - TÓPICO 5: POLIMORFISMO DE SOBRECARGA (OVERLOADING)
 * * Objetivo: Explicar e demonstrar o conceito de Sobrecarga de método e construtor,
 * uma das formas de polimorfismo em Java.
 *
 * * CONCEITO DE POLIMORFISMO:
 * Do grego "muitas formas". Em programação, é a capacidade de um método ou objeto
 * se comportar de maneiras diferentes em contextos distintos. Existem dois tipos em Java:
 * 1. Sobrecarga (Overloading): Polimorfismo estático, resolvido em tempo de compilação. (Este arquivo)
 * 2. Sobreposição (Overriding): Polimorfismo dinâmico, resolvido em tempo de execução. (Próximo tópico)
 *
 * * @author Seu Assistente Gemini
 * @version 1.0
 */

// =================================================================================
// 1. O QUE É SOBRECARGA (OVERLOADING)?
// =================================================================================
// -> DEFINIÇÃO: É a capacidade de ter, dentro da MESMA CLASSE, múltiplos métodos
//    com o MESMO NOME, desde que suas "assinaturas" sejam diferentes.
//
// -> A REGRA DA ASSINATURA: Para sobrecarregar um método, a lista de parâmetros
//    DEVE ser diferente em pelo menos um dos seguintes aspectos:
//      a) NÚMERO de parâmetros diferente.
//      b) TIPO dos parâmetros diferente.
//      c) ORDEM dos tipos de parâmetros diferente.
//
// -> PONTO CRÍTICO: Mudar APENAS o tipo de retorno NÃO é sobrecarga e causa ERRO de compilação.
// ---------------------------------------------------------------------------------

class Calculadora {

    // Exemplo 1: Método 'somar' sobrecarregado

    // a) Versão com DOIS parâmetros do tipo 'int'
    public int somar(int a, int b) {
        System.out.println("-> Versão somar(int, int) foi chamada.");
        return a + b;
    }

    // b) Sobrecarga com NÚMERO diferente de parâmetros
    public int somar(int a, int b, int c) {
        System.out.println("-> Versão somar(int, int, int) foi chamada.");
        return a + b + c;
    }

    // c) Sobrecarga com TIPO diferente de parâmetros
    public double somar(double a, double b) {
        System.out.println("-> Versão somar(double, double) foi chamada.");
        return a + b;
    }

    // d) Sobrecarga com ORDEM de tipos diferente (exemplo didático)
    public void exibir(String texto, int numero) {
        System.out.println("-> Versão exibir(String, int): " + texto + numero);
    }
    
    public void exibir(int numero, String texto) {
        System.out.println("-> Versão exibir(int, String): " + numero + texto);
    }
    
    /*
     * EXEMPLO INCORRETO (NÃO COMPILA): Mudar apenas o tipo de retorno.
     * O compilador não saberia qual método chamar para `calculadora.somar(5, 10);`
     *
     * public double somar(int a, int b) {
     * return (double)(a + b);
     * }
     */
}

class Produto {
    String nome;
    double preco;
    int estoque;

    // =================================================================================
    // 2. SOBRECARGA DE CONSTRUTORES
    // =================================================================================
    // -> DEFINIÇÃO: O mesmo conceito de sobrecarga de métodos se aplica aos construtores.
    //    Isso permite criar objetos da mesma classe de formas diferentes e mais flexíveis.
    // ---------------------------------------------------------------------------------

    // Construtor 1: Padrão, sem parâmetros
    public Produto() {
        this.nome = "Não definido";
        this.preco = 0.0;
        this.estoque = 0;
    }

    // Construtor 2: Sobrecarga com o nome do produto
    public Produto(String nome) {
        this.nome = nome;
        this.preco = 0.0;
        this.estoque = 0;
    }

    // Construtor 3: Sobrecarga com nome e preço
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = 0;
    }
    
    // Construtor 4: Sobrecarga com todos os parâmetros
    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void exibirDetalhes() {
        System.out.println("Produto: " + this.nome + " | Preço: R$" + this.preco + " | Estoque: " + this.estoque);
    }
}


// Classe principal para demonstrar os conceitos
public class GuiaPolimorfismoSobrecarga {

    public static void main(String[] args) {
        System.out.println("--- DEMONSTRAÇÃO DE SOBRECARGA DE MÉTODO ---");
        Calculadora calc = new Calculadora();

        // O compilador Java "sabe" qual método chamar com base nos argumentos que passamos.
        int resultado1 = calc.somar(10, 5);       // Chama a versão com dois 'int'
        int resultado2 = calc.somar(10, 5, 2);    // Chama a versão com três 'int'
        double resultado3 = calc.somar(12.5, 7.2); // Chama a versão com dois 'double'
        
        System.out.println("Resultado 1 (10 + 5): " + resultado1);
        System.out.println("Resultado 2 (10 + 5 + 2): " + resultado2);
        System.out.println("Resultado 3 (12.5 + 7.2): " + resultado3);
        
        calc.exibir("Teste", 1);
        calc.exibir(1, "Teste");
        
        System.out.println("\n--- DEMONSTRAÇÃO DE SOBRECARGA DE CONSTRUTOR ---");
        
        // Criando objetos da classe Produto usando os diferentes construtores
        Produto p1 = new Produto();
        Produto p2 = new Produto("Caneta BIC");
        Produto p3 = new Produto("Notebook Dell", 4500.00);
        Produto p4 = new Produto("Mouse Logitech", 150.00, 50);
        
        System.out.println("Objetos criados com construtores sobrecarregados:");
        p1.exibirDetalhes();
        p2.exibirDetalhes();
        p3.exibirDetalhes();
        p4.exibirDetalhes();
    }
}