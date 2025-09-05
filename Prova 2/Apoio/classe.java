/**
 * ARQUIVO DE CONSULTA JAVA - TÓPICO 4: CLASSE, OBJETO E MODIFICADORES DE ACESSO
 * * Objetivo: Explicar a relação fundamental entre Classe e Objeto e detalhar
 * como os modificadores de acesso controlam a visibilidade e o encapsulamento.
 * * @author Seu Assistente Gemini
 * @version 1.0
 */

// =================================================================================
// 1. CLASSE
// =================================================================================
// -> O QUE É: É o "molde", a "planta" ou a "receita". É um modelo que define
//    atributos (o que o objeto TEM) e métodos (o que o objeto FAZ).
//    Uma classe, por si só, não "existe" na memória como um dado concreto; ela
//    é apenas a definição.
// ---------------------------------------------------------------------------------

class ContaBancaria {

    // =================================================================================
    // 2. MODIFICADORES DE ACESSO
    // =================================================================================
    // -> O QUE SÃO: Palavras-chave que definem o nível de "visibilidade" de uma
    //    classe, atributo ou método. Eles controlam quem pode acessar o quê.
    // ---------------------------------------------------------------------------------

    // --- A. public ---
    // Visível para QUALQUER classe em QUALQUER pacote. É o menos restritivo.
    public String titular;

    // --- B. private ---
    // Visível APENAS dentro da PRÓPRIA classe (ContaBancaria). É o mais restritivo.
    // Este é o pilar do ENCAPSULAMENTO. Protege os dados de acessos indevidos.
    private double saldo;

    // --- C. protected ---
    // Visível para classes no MESMO PACOTE e para SUBCLASSES (filhas) em pacotes diferentes.
    protected String tipoDeConta; // Ex: "Corrente", "Poupança"

    // --- D. default (package-private) ---
    // Quando NENHUM modificador é usado.
    // Visível APENAS para classes que estão no MESMO PACOTE.
    String agencia;

    // --- CONSTRUTOR ---
    // Um método especial para criar objetos da classe.
    public ContaBancaria(String titular, String agencia, double saldoInicial) {
        this.titular = titular; // 'this' se refere ao objeto atual
        this.agencia = agencia;
        this.saldo = saldoInicial;
        this.tipoDeConta = "Corrente";
    }

    // --- MÉTODOS ---

    // Método PÚBLICO para depositar. Qualquer um pode chamar.
    // Esta é a forma segura de interagir com o saldo, que é privado.
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }
    
    // Método PÚBLICO para sacar. Controla a regra de negócio (não sacar mais do que tem).
    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor de saque inválido.");
        }
    }

    // Método PÚBLICO para consultar o saldo. Conhecido como "Getter".
    // Permite que o mundo exterior VEJA o saldo, mas não o MODIFIQUE diretamente.
    public double getSaldo() {
        return this.saldo;
    }
}


// Classe principal para demonstrar os conceitos
public class GuiaClassesObjetos {

    public static void main(String[] args) {

        // =================================================================================
        // 3. OBJETO
        // =================================================================================
        // -> O QUE É: É a "instância" de uma classe. É a casa construída a partir
        //    da planta, o bolo feito a partir da receita. É um dado concreto na memória.
        //    Usamos a palavra-chave 'new' para criar um objeto (instanciar).
        // ---------------------------------------------------------------------------------

        System.out.println("--- Criando e Usando Objetos ---");
        // Criando dois objetos (duas instâncias) da classe ContaBancaria
        ContaBancaria conta1 = new ContaBancaria("Ana Souza", "001", 500.00);
        ContaBancaria conta2 = new ContaBancaria("Beto Silva", "002", 1500.00);


        // --- Acessando membros com base nos modificadores ---

        // Acesso a membro 'public' (titular): Permitido de qualquer lugar.
        System.out.println("Titular da conta 1: " + conta1.titular);
        conta1.titular = "Ana Souza de Almeida"; // Podemos modificar diretamente.
        System.out.println("Titular da conta 1 (alterado): " + conta1.titular);
        
        System.out.println("--------------------");

        // Acesso a membro 'private' (saldo):
        // A linha abaixo causaria um ERRO DE COMPILAÇÃO. O acesso direto é proibido.
        // conta1.saldo = 1000000.0; // <<== ISTO NÃO FUNCIONA!
        
        // A forma CORRETA é usar os métodos públicos (Getters/Setters, etc)
        System.out.println("Saldo inicial da conta 2: R$" + conta2.getSaldo());
        conta2.depositar(250.50);
        conta2.sacar(1000.0);
        System.out.println("Saldo final da conta 2: R$" + conta2.getSaldo());

        System.out.println("--------------------");

        // Acesso a membro 'default' (agencia):
        // Permitido, pois a classe GuiaClassesObjetos está no mesmo "pacote"
        // (neste caso, o pacote padrão, pois não definimos um) que a ContaBancaria.
        System.out.println("Agência da conta 1: " + conta1.agencia);

        System.out.println("--------------------");

        // Acesso a membro 'protected' (tipoDeConta):
        // Também permitido, pois estamos no mesmo pacote.
        System.out.println("Tipo da conta 2: " + conta2.tipoDeConta);
    }
}