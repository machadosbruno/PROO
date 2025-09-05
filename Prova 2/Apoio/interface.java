/**
 * ARQUIVO DE CONSULTA JAVA - TÓPICO 8: INTERFACE
 * * Objetivo: Explicar o que são interfaces, como elas definem um contrato 100% abstrato
 * e como permitem uma forma de "herança múltipla" de comportamento.
 *
 * * CONCEITO DE INTERFACE:
 * - É um "contrato" ou uma "especificação de comportamento" completamente abstrata.
 * - Define um conjunto de métodos que uma classe DEVE implementar. Ela diz "O QUE" a
 * classe deve fazer, mas nunca "COMO" ela deve fazer.
 * - Uma classe não "herda" (`extends`) de uma interface, ela "assina o contrato",
 * ou seja, ela "implementa" (`implements`) a interface.
 * - A maior vantagem: Uma classe pode implementar MÚLTIPLAS interfaces, ao contrário
 * da herança de classe, que é única.
 *
 * * @author Seu Assistente Gemini
 * @version 1.0
 */

// =================================================================================
// 1. A INTERFACE (O CONTRATO PURO)
// =================================================================================
// Define um comportamento "Controlável". Qualquer classe que implementar esta
// interface está garantindo que terá os métodos listados abaixo.
interface Controlavel {

    // --- Atributos em Interfaces ---
    // Todos os atributos em uma interface são, por padrão, `public static final`.
    // Ou seja, são constantes.
    int VOLUME_MAXIMO = 100;
    int VOLUME_MINIMO = 0;

    // --- Métodos em Interfaces ---
    // Todos os métodos (na forma clássica) são, por padrão, `public` e `abstract`.
    // Não é necessário escrever essas palavras-chave.
    void ligar();
    void desligar();
    void aumentarVolume(int quantidade);
    void diminuirVolume(int quantidade);
}

// =================================================================================
// 2. AS CLASSES CONCRETAS (QUE ASSINAM O CONTRATO)
// =================================================================================
// Estas classes são de "famílias" diferentes, mas ambas assinam o contrato
// para serem 'Controlavel'.

class Televisao implements Controlavel {
    private boolean ligada = false;
    private int volume = 10;

    @Override
    public void ligar() {
        this.ligada = true;
        System.out.println("TV ligada.");
    }

    @Override
    public void desligar() {
        this.ligada = false;
        System.out.println("TV desligada.");
    }

    @Override
    public void aumentarVolume(int quantidade) {
        if (this.ligada) {
            this.volume += quantidade;
            if (this.volume > Controlavel.VOLUME_MAXIMO) {
                this.volume = Controlavel.VOLUME_MAXIMO;
            }
            System.out.println("Volume da TV: " + this.volume);
        }
    }

    @Override
    public void diminuirVolume(int quantidade) {
        if (this.ligada) {
            this.volume -= quantidade;
            if (this.volume < Controlavel.VOLUME_MINIMO) {
                this.volume = Controlavel.VOLUME_MINIMO;
            }
            System.out.println("Volume da TV: " + this.volume);
        }
    }
}

class ArCondicionado implements Controlavel {
    private boolean ligado = false;
    private int temperatura = 23;

    @Override
    public void ligar() {
        this.ligado = true;
        System.out.println("Ar Condicionado ligado.");
    }

    @Override
    public void desligar() {
        this.ligado = false;
        System.out.println("Ar Condicionado desligado.");
    }

    // Note como a classe adapta o "contrato" à sua realidade.
    // Para um Ar Condicionado, "volume" pode ser interpretado como "temperatura".
    @Override
    public void aumentarVolume(int quantidade) {
        if (this.ligado) {
            this.temperatura += quantidade;
            System.out.println("Temperatura do Ar: " + this.temperatura + "°C");
        }
    }

    @Override
    public void diminuirVolume(int quantidade) {
        if (this.ligado) {
            this.temperatura -= quantidade;
            System.out.println("Temperatura do Ar: " + this.temperatura + "°C");
        }
    }
}


// Classe principal para demonstrar os conceitos
public class GuiaInterface {

    // Este método demonstra o poder da interface. Ele pode operar em QUALQUER
    // objeto que seja 'Controlavel', sem se importar se é uma TV, Ar, etc.
    public static void testarDispositivo(Controlavel dispositivo) {
        System.out.println("\n--- Testando um novo dispositivo ---");
        dispositivo.ligar();
        dispositivo.aumentarVolume(5);
        dispositivo.desligar();
        System.out.println("--- Teste finalizado ---");
    }

    public static void main(String[] args) {
        // Criando instâncias das classes concretas
        Televisao minhaTV = new Televisao();
        ArCondicionado meuAC = new ArCondicionado();

        // Usando o polimorfismo através da interface
        // A variável é do tipo da INTERFACE, mas o objeto é do tipo da CLASSE.
        Controlavel dispositivo1 = minhaTV;
        Controlavel dispositivo2 = meuAC;

        dispositivo1.ligar();
        dispositivo1.aumentarVolume(20);
        dispositivo1.desligar();

        System.out.println(); // Espaçamento

        dispositivo2.ligar();
        // A mesma chamada de método 'aumentarVolume' tem um comportamento diferente
        // pois a implementação no Ar Condicionado é outra.
        dispositivo2.aumentarVolume(2);
        dispositivo2.desligar();

        // Usando nosso método genérico
        testarDispositivo(minhaTV);
        testarDispositivo(meuAC);
    }
}