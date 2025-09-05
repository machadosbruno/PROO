/**
 * ARQUIVO DE CONSULTA JAVA - TÓPICO 6: HERANÇA (CLASSES PAI E FILHO)
 * * Objetivo: Explicar o conceito de Herança, onde uma classe (filha) pode
 * herdar atributos e métodos de outra classe (pai), promovendo o reuso de código.
 *
 * * CONCEITO DE HERANÇA:
 * - Estabelece uma relação "É UM" (IS-A). Ex: Um Gerente "É UM" Funcionário.
 * - A classe que herda é chamada de Subclasse ou Classe Filha.
 * - A classe da qual se herda é chamada de Superclasse ou Classe Pai.
 * - A palavra-chave para herdar é `extends`.
 *
 * * @author Seu Assistente Gemini
 * @version 1.0
 */

// =================================================================================
// 1. A SUPERCLASSE (CLASSE PAI)
// =================================================================================
// -> DEFINIÇÃO: Contém os atributos e métodos COMUNS a todas as classes que
//    irão herdar dela. É a classe mais genérica.
// ---------------------------------------------------------------------------------
class Funcionario {
    // Atributos 'protected' são visíveis na própria classe, em classes do mesmo
    // pacote e, mais importante para herança, em subclasses.
    protected String nome;
    protected String matricula;

    // Construtor da classe Pai
    public Funcionario(String nome, String matricula) {
        System.out.println("-> Construtor de Funcionario (Pai) foi chamado.");
        this.nome = nome;
        this.matricula = matricula;
    }

    // Método comum a todos os funcionários
    public void exibirInformacoes() {
        System.out.println("Nome: " + this.nome + " | Matrícula: " + this.matricula);
    }
}


// =================================================================================
// 2. A SUBCLASSE (CLASSE FILHA)
// =================================================================================
// -> DEFINIÇÃO: Herda tudo que é 'public' e 'protected' da classe pai.
//    Pode adicionar seus próprios atributos e métodos, ou modificar os herdados.
//    Usa a palavra-chave `extends`.
// ---------------------------------------------------------------------------------
class Gerente extends Funcionario {

    // Atributo específico da classe Gerente
    private String areaDeResponsabilidade;

    // Construtor da classe Filha
    public Gerente(String nome, String matricula, String area) {
        // A PRIMEIRA linha do construtor de uma subclasse DEVE ser uma chamada
        // ao construtor da superclasse, usando a palavra-chave `super()`.
        // Isso garante que a "parte Pai" do objeto seja construída primeiro.
        super(nome, matricula);
        
        System.out.println("-> Construtor de Gerente (Filho) foi chamado.");
        this.areaDeResponsabilidade = area;
    }

    // Método específico da classe Gerente
    public void darOrdem() {
        System.out.println("O gerente " + this.nome + " está gerenciando a área de " + this.areaDeResponsabilidade + ".");
    }
}

class Vendedor extends Funcionario {

    // Atributo específico da classe Vendedor
    private double comissao;

    public Vendedor(String nome, String matricula, double comissao) {
        // Chamando o construtor da classe pai (Funcionario)
        super(nome, matricula);
        System.out.println("-> Construtor de Vendedor (Filho) foi chamado.");
        this.comissao = comissao;
    }

    // Método específico da classe Vendedor
    public void vender() {
        System.out.println("O vendedor " + this.nome + " realizou uma venda!");
    }
}


// Classe principal para demonstrar os conceitos
public class GuiaHeranca {
    
    public static void main(String[] args) {
        System.out.println("--- Criando um objeto Gerente ---");
        // Ao criar um Gerente, note no console que o construtor do Pai (Funcionario)
        // é chamado antes do construtor do Filho (Gerente).
        Gerente gerente = new Gerente("Carlos Andrade", "G-123", "Tecnologia");
        
        System.out.println("\n--- Acessando membros do Gerente ---");
        // 'nome' e 'matricula' foram herdados de Funcionario
        System.out.println("Nome do gerente: " + gerente.nome);
        
        // 'exibirInformacoes()' é um método herdado de Funcionario
        System.out.print("Informações herdadas: ");
        gerente.exibirInformacoes();
        
        // 'darOrdem()' é um método específico de Gerente
        gerente.darOrdem();

        System.out.println("\n----------------------------------\n");
        
        System.out.println("--- Criando um objeto Vendedor ---");
        Vendedor vendedor = new Vendedor("Ana Paula", "V-456", 0.10);

        System.out.println("\n--- Acessando membros do Vendedor ---");
        // O Vendedor também herda nome, matricula e o método exibirInformacoes()
        System.out.print("Informações herdadas: ");
        vendedor.exibirInformacoes();
        
        // E possui seu próprio método específico
        vendedor.vender();
    }
}