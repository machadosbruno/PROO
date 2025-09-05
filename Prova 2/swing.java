import javax.swing.*; // Importa todas as classes do Swing (JFrame, JButton, etc.)
import java.awt.*;    // Importa classes do AWT, como LayoutManagers e a classe Event
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ARQUIVO DE CONSULTA JAVA - TÓPICO 3: FRAMEWORK SWING (GUI)
 * * Objetivo: Apresentar os conceitos e componentes básicos para criar
 * Interfaces Gráficas de Usuário (GUI) com o Swing.
 * * @author Seu Assistente Gemini
 * @version 1.0
 */

// =================================================================================
// 1. O CONTAINER PRINCIPAL: JFrame
// =================================================================================
// -> O QUE É: É a janela principal da sua aplicação. Todos os outros
//    componentes visuais (botões, textos, etc.) são adicionados dentro dela.
// -> BOA PRÁTICA: Criar uma classe que herda de JFrame para organizar o código.
// ---------------------------------------------------------------------------------
public class GuiaFrameworkSwing extends JFrame {

    // =================================================================================
    // 2. COMPONENTES BÁSICOS DO SWING
    // =================================================================================
    // -> O QUE SÃO: São os "blocos de construção" da sua interface.
    //    Declaramos eles como variáveis de instância da nossa classe de janela.
    // ---------------------------------------------------------------------------------
    private JPanel painelPrincipal;      // Um painel para agrupar outros componentes.
    private JLabel labelNome;            // Um rótulo de texto simples (não editável).
    private JTextField campoTextoNome;   // Um campo para o usuário digitar uma linha de texto.
    private JButton botaoSaudacao;       // Um botão clicável.
    private JTextArea areaDeLog;         // Uma área para exibir múltiplas linhas de texto.
    private JScrollPane scrollLog;       // Uma barra de rolagem para a área de texto.


    // O CONSTRUTOR da nossa janela é onde a mágica acontece.
    // Aqui nós configuramos a janela, criamos e posicionamos os componentes.
    public GuiaFrameworkSwing() {
        // --- A. Configuração básica da Janela (JFrame) ---
        super("Guia de Estudo do Swing"); // Define o título da janela
        this.setSize(500, 400);                // Define a largura e altura
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Faz o programa fechar ao clicar no 'X'
        this.setLocationRelativeTo(null);      // Abre a janela no centro da tela

        // --- B. Gerenciadores de Layout (Layout Managers) ---
        // -> O QUE SÃO: Controlam a posição e o tamanho dos componentes dentro de um container.
        //    É a forma correta de organizar a tela, em vez de usar coordenadas fixas (x, y).
        //
        // BorderLayout: Divide o container em 5 áreas: NORTE, SUL, LESTE, OESTE e CENTRO.
        // É o layout padrão do JFrame.
        this.setLayout(new BorderLayout(10, 10)); // 10px de espaçamento horizontal e vertical

        // --- C. Inicialização e Montagem dos Componentes ---

        // 1. Criando o painel superior com FlowLayout
        // FlowLayout: Coloca os componentes um ao lado do outro, em linha.
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // 2. Criando e adicionando componentes ao painel superior
        labelNome = new JLabel("Digite seu nome:");
        campoTextoNome = new JTextField(15); // 15 é uma estimativa da largura em colunas
        botaoSaudacao = new JButton("Clique aqui!");

        painelPrincipal.add(labelNome);
        painelPrincipal.add(campoTextoNome);
        painelPrincipal.add(botaoSaudacao);

        // 3. Criando a área de texto central com barra de rolagem
        areaDeLog = new JTextArea();
        areaDeLog.setEditable(false); // O usuário não pode digitar aqui
        scrollLog = new JScrollPane(areaDeLog); // Importante: Adicionamos o JTextArea ao JScrollPane

        // --- D. Adicionando os painéis/componentes ao JFrame ---
        this.add(painelPrincipal, BorderLayout.NORTH); // Adiciona o painel na parte de CIMA da janela
        this.add(scrollLog, BorderLayout.CENTER);      // Adiciona a área de log rolável no CENTRO

        // =================================================================================
        // 3. TRATAMENTO DE EVENTOS (Event Handling)
        // =================================================================================
        // -> O QUE É: É como fazemos nosso programa REAGIR a ações do usuário (cliques, etc.).
        //    Usamos "Listeners" (Ouvintes) para esperar por uma ação.
        // ---------------------------------------------------------------------------------
        botaoSaudacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Este código é executado QUANDO o botão é clicado.
                String nomeDigitado = campoTextoNome.getText(); // Pega o texto do campo de input

                if (nomeDigitado.trim().isEmpty()) {
                    areaDeLog.append("Por favor, digite um nome!\n");
                } else {
                    String mensagem = "Olá, " + nomeDigitado + "! Bem-vindo(a) ao Swing.\n";
                    areaDeLog.append(mensagem); // Adiciona a mensagem na área de texto
                }

                campoTextoNome.setText(""); // Limpa o campo de texto após o clique
            }
        });
        
        // Forma moderna de fazer o mesmo Listener acima, usando uma expressão Lambda:
        // botaoSaudacao.addActionListener(e -> {
        //     String nomeDigitado = campoTextoNome.getText();
        //     areaDeLog.append("Olá, " + nomeDigitado + "!\n");
        //     campoTextoNome.setText("");
        // });
    }

    // =================================================================================
    // 4. INICIALIZAÇÃO DA APLICAÇÃO SWING
    // =================================================================================
    // -> BOA PRÁTICA: O Swing não é "thread-safe", o que significa que todas as
    //    interações com a GUI devem ocorrer em uma thread específica chamada
    //    Event Dispatch Thread (EDT). `SwingUtilities.invokeLater` garante isso.
    // ---------------------------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Cria e exibe a nossa janela
                GuiaFrameworkSwing minhaJanela = new GuiaFrameworkSwing();
                minhaJanela.setVisible(true); // TORNA A JANELA VISÍVEL!
            }
        });
        
        // Forma moderna com Lambda:
        // SwingUtilities.invokeLater(() -> new GuiaFrameworkSwing().setVisible(true));
    }
}