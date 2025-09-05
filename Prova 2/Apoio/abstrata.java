/**
 * ARQUIVO DE CONSULTA JAVA - TÓPIco 7: CLASSE ABSTRATA
 * * Objetivo: Explicar o que são classes e métodos abstratos, como eles criam
 * "contratos" para as subclasses e por que não podem ser instanciados.
 *
 * * CONCEITO DE CLASSE ABSTRATA:
 * - É uma classe "incompleta" que serve como um modelo (template) para suas subclasses.
 * - NÃO PODE ser instanciada diretamente (você não pode dar `new` em uma classe abstrata).
 * - Pode ter tanto métodos concretos (com corpo, com implementação) quanto métodos abstratos.
 * - É marcada com a palavra-chave `abstract`.
 *
 * * CONCEITO DE MÉTODO ABSTRATO:
 * - É uma declaração de método sem corpo (sem `{}`). Termina com ponto e vírgula ';'.
 * - Também é marcado com a palavra-chave `abstract`.
 * - Funciona como um CONTRATO: qualquer classe filha CONCRETA é OBRIGADA a implementar
 * (sobrescrever com @Override) todos os métodos abstratos da classe mãe.
 *
 * * @author Seu Assistente Gemini
 * @version 1.0
 */

// =================================================================================
// 1. A CLASSE ABSTRATA (O CONTRATO)
// =================================================================================
// 'Forma' é um conceito. Não podemos criar um objeto "Forma", mas podemos
// definir características e comportamentos comuns a todas as formas.
abstract class Forma {

    // Uma classe abstrata PODE ter atributos.
    protected String nome;

    // Uma classe abstrata PODE ter um construtor.
    // Ele será chamado pelas subclasses usando `super()`.
    public Forma(String nome) {
        this.nome = nome;
    }

    // --- Método Concreto ---
    // Este método tem implementação e será herdado normalmente por todas as filhas.
    // Promove o REUSO de código.
    public void exibirNome() {
        System.out.println("Eu sou uma forma do tipo: " + this.nome);
    }

    // --- Método Abstrato ---
    // Este método NÃO tem implementação. Ele define "O QUÊ" as subclasses devem
    // fazer, mas não "COMO". Cada filha terá sua própria fórmula.
    // Ele FORÇA a implementação nas subclasses.
    public abstract double calcularArea();
}


// =================================================================================
// 2. AS CLASSES CONCRETAS (A IMPLEMENTAÇÃO DO CONTRATO)
// =================================================================================
// Uma classe concreta herda da classe abstrata e implementa TODOS os seus
// métodos abstratos. Só então ela pode ser instanciada.

class Retangulo extends Forma {
    private double base;
    private double altura;

    public Retangulo(double base, double altura) {
        super("Retângulo"); // Chama o construtor da classe pai (Forma)
        this.base = base;
        this.altura = altura;
    }

    // Implementação OBRIGATÓRIA do método abstrato da classe pai.
    @Override
    public double calcularArea() {
        // Lógica específica para calcular a área de um retângulo
        return this.base * this.altura;
    }
}

class Circulo extends Forma {
    private double raio;

    public Circulo(double raio) {
        super("Círculo"); // Chama o construtor da classe pai (Forma)
        this.raio = raio;
    }

    // Implementação OBRIGATÓRIA do método abstrato da classe pai.
    @Override
    public double calcularArea() {
        // Lógica específica para calcular a área de um círculo
        return Math.PI * this.raio * this.raio;
    }
}

// Classe principal para demonstrar os conceitos
public class GuiaClasseAbstrata {

    public static void main(String[] args) {

        // A linha abaixo causaria um ERRO DE COMPILAÇÃO.
        // Não é possível instanciar uma classe abstrata diretamente.
        // Forma formaGenerica = new Forma("Genérica"); // <<== ISTO NÃO FUNCIONA!

        System.out.println("--- Criando objetos de classes concretas ---");

        // Podemos instanciar as classes filhas que implementaram o contrato.
        Retangulo meuRetangulo = new Retangulo(10, 5);
        Circulo meuCirculo = new Circulo(7);

        // Chamando o método CONCRETO herdado da classe abstrata 'Forma'
        meuRetangulo.exibirNome();
        // Chamando o método ABSTRATO que foi implementado em 'Retangulo'
        System.out.println("Área: " + meuRetangulo.calcularArea());

        System.out.println(); // Espaçamento

        meuCirculo.exibirNome();
        System.out.println("Área: " + String.format("%.2f", meuCirculo.calcularArea()));
        
        System.out.println("\n--- Polimorfismo com Classe Abstrata ---");

        // O poder das classes abstratas brilha com o polimorfismo.
        // Podemos declarar uma variável do tipo abstrato, mas instanciar um tipo concreto.
        Forma forma1 = new Retangulo(8, 3);
        Forma forma2 = new Circulo(4);

        // O compilador sabe que QUALQUER objeto do tipo 'Forma' TERÁ o método 'calcularArea()'.
        // Em tempo de execução, o Java chama a versão correta do método (do Retângulo ou do Círculo).
        System.out.println("Área da forma 1 (Retângulo): " + forma1.calcularArea());
        System.out.println("Área da forma 2 (Círculo): " + String.format("%.2f", forma2.calcularArea()));
    }
}