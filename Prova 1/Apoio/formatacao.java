/**
 * ARQUIVO DE CONSULTA JAVA - TÓPICO 1: CONVENÇÕES DE NOMENCLATURA
 * * Objetivo: Demonstrar os padrões de nomenclatura (Case Styles) mais comuns em Java.
 * Seguir essas convenções é crucial para a legibilidade e manutenção do código.
 * É uma prática padrão na indústria e frequentemente avaliada.
 * * @author Seu Assistente Gemini
 * @version 1.0
 */

// =================================================================================
// 1. PASCAL CASE (ou UpperCamelCase)
// =================================================================================
// -> QUANDO USAR: Nomes de Classes, Interfaces, Enums e Records.
// -> REGRA: A primeira letra de CADA palavra é maiúscula.
// ---------------------------------------------------------------------------------

// EXEMPLO CORRETO para Classes:
class CalculadoraDeImposto {
    // Código da classe aqui...
}

// EXEMPLO CORRETO para Interfaces:
interface RepositorioDeCliente {
    // Assinaturas de métodos aqui...
}

// EXEMPLO CORRETO para Enums:
enum NivelDePrioridade {
    BAIXA, MEDIA, ALTA
}

/*
 * EXEMPLOS INCORRETOS (EVITAR):
 * class calculadora_de_imposto; // Incorreto, usa snake_case
 * class calculadoradeimposto;   // Incorreto, tudo minúsculo
 * class Calculadora_De_Imposto; // Incorreto, usa underscore
 */


// =================================================================================
// 2. CAMEL CASE (ou lowerCamelCase)
// =================================================================================
// -> QUANDO USAR: Nomes de Variáveis e Métodos.
// -> REGRA: A primeira letra da PRIMEIRA palavra é minúscula. 
//           A primeira letra de CADA palavra SUBSEQUENTE é maiúscula.
// ---------------------------------------------------------------------------------

class ExemploCamelCase {

    // EXEMPLO CORRETO para Variáveis (de instância):
    String nomeCompletoDoUsuario;
    int quantidadeDeProdutosNoCarrinho;
    double valorTotalDaCompra;

    /*
     * EXEMPLOS INCORRETOS DE VARIÁVEIS (EVITAR):
     * String NomeCompletoDoUsuario; // Incorreto, inicia com maiúscula (parece classe)
     * int quantidade_de_produtos;   // Incorreto, usa snake_case (comum em outras linguagens como Python)
     */


    // EXEMPLO CORRETO para Métodos:
    public double calcularValorFinal() {
        // Lógica do método...
        return 0.0;
    }

    public void buscarItemPorId(int id) {
        // Lógica do método...
    }

    /*
     * EXEMPLOS INCORRETOS DE MÉTODOS (EVITAR):
     * public void Buscardados();      // Incorreto, inicia com maiúscula
     * public void calcular_valor_final(); // Incorreto, usa snake_case
     */
}


// =================================================================================
// 3. SNAKE CASE (ou UPPER_SNAKE_CASE)
// =================================================================================
// -> QUANDO USAR: Nomes de Constantes (variáveis que não mudam de valor).
// -> REGRA: Todas as letras são maiúsculas, e as palavras são separadas por underscore (_).
//           Em Java, constantes são declaradas com as palavras-chave 'static final'.
// ---------------------------------------------------------------------------------

class ConfiguracoesDoSistema {

    // EXEMPLO CORRETO para Constantes:
    public static final int NUMERO_MAXIMO_DE_TENTATIVAS = 5;
    public static final double TAXA_DE_JUROS_PADRAO = 0.05;
    public static final String URL_DA_API = "https://api.meusistema.com/v1/";

    /*
     * EXEMPLOS INCORRETOS DE CONSTANTES (EVITAR):
     * public static final int numeroMaximoDeTentativas = 5; // Incorreto, usa camelCase
     * public static final int NumeroMaximo = 5;             // Incorreto, usa PascalCase
     */
}


// =================================================================================
// CLASSE PRINCIPAL PARA DEMONSTRAÇÃO
// =================================================================================

public class GuiaDeNomenclatura {

    public static void main(String[] args) {

        // 1. Usando PascalCase para declarar o tipo da classe
        //    'ExemploCamelCase' e 'ConfiguracoesDoSistema' seguem o padrão.
        
        // 2. Usando camelCase para nomear a variável (instância)
        ExemploCamelCase meuExemplo = new ExemploCamelCase();
        
        // 3. Atribuindo valores a variáveis que usam camelCase
        meuExemplo.nomeCompletoDoUsuario = "João da Silva";
        meuExemplo.quantidadeDeProdutosNoCarrinho = 3;
        
        // 4. Chamando um método que usa camelCase
        double valor = meuExemplo.calcularValorFinal();
        
        // 5. Usando uma constante que usa UPPER_SNAKE_CASE
        System.out.println("O número máximo de tentativas de login é: " + ConfiguracoesDoSistema.NUMERO_MAXIMO_DE_TENTATIVAS);

        // 6. Exemplo de variável local, também em camelCase
        String mensagemDeStatus = "Processamento concluído.";
        System.out.println(mensagemDeStatus);
    }
}