Chegamos ao último tópico, e é um dos mais importantes para aplicações do mundo real: a persistência de dados com Hibernate.

Este assunto é mais complexo que os anteriores porque envolve a comunicação com um sistema externo (o banco de dados) e o uso de um framework (Hibernate) com seus próprios arquivos de configuração e bibliotecas.

Por isso, este guia será um pouco diferente. Em vez de um único arquivo Java que você pode compilar e rodar, vou te apresentar **as partes essenciais de um projeto Hibernate**, com snippets de código e arquivos de configuração comentados. O objetivo é que você entenda o **fluxo de trabalho** e os **conceitos chave** para a sua prova.

-----

### ARQUIVO DE CONSULTA JAVA - TÓPICO 9: HIBERNATE (CONEXÃO COM BANCO DE DADOS)

**O que é o Hibernate e por que usá-lo?**

Em Java, trabalhamos com **Objetos**. Em bancos de dados, trabalhamos com **Tabelas e Linhas**. Existe uma incompatibilidade natural entre esses dois mundos. O Hibernate é um framework de **Mapeamento Objeto-Relacional (ORM)** que serve como uma ponte.

**Função Principal:** Ele permite que você salve, consulte, atualize e delete objetos Java em um banco de dados sem precisar escrever código SQL manualmente. Você manipula os objetos, e o Hibernate gera o SQL por baixo dos panos.

-----

### Parte 1: A Dependência (Contexto do Projeto)

Em um projeto real, você adiciona o Hibernate através de um gerenciador de dependências como o Maven. Você não precisa decorar isso, mas é importante saber que o Hibernate é uma biblioteca externa.

**Exemplo em `pom.xml` (Maven):**

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.15.Final</version> </dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

-----

### Parte 2: O Arquivo de Configuração (`hibernate.cfg.xml`)

Este é o coração da configuração do Hibernate. É um arquivo XML que geralmente fica na pasta `src/main/resources`. Ele informa ao Hibernate como se conectar ao banco de dados e outras configurações importantes.

```xml
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/meubanco</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">seu_password</property>

        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <property name="show_sql">true</property>
        <property name="format_sql">true</property>
        
        <property name="hbm2ddl.auto">update</property>

        <mapping class="com.meuprojeto.modelo.Usuario"/>
        </session-factory>
</hibernate-configuration>
```

-----

### Parte 3: A Classe de Entidade (O Mapeamento)

Esta é uma classe Java comum (POJO) que representa uma tabela do banco de dados. Você usa anotações da **JPA (Java Persistence API)**, que é uma especificação que o Hibernate implementa.

**Arquivo `Usuario.java`:**

```java
import javax.persistence.*;

// @Entity: Marca esta classe como uma entidade que será gerenciada pelo Hibernate.
@Entity
// @Table: (Opcional) Especifica o nome da tabela no banco. Se omitido, usa o nome da classe.
@Table(name = "usuarios")
public class Usuario {

    // @Id: Marca este atributo como a chave primária (Primary Key) da tabela.
    @Id
    // @GeneratedValue: Configura a estratégia de geração da chave primária.
    // GenerationType.IDENTITY é comum para colunas de auto-incremento (MySQL).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column: (Opcional) Mapeia o atributo para uma coluna específica.
    // Útil para nomes diferentes ou para definir tamanho, nulidade, etc.
    @Column(name = "nome_completo", length = 150, nullable = false)
    private String nome;

    @Column(unique = true) // Garante que não haverá emails duplicados
    private String email;

    // Construtores, Getters e Setters são necessários.
    public Usuario() {}

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // ... Getters e Setters para todos os campos ...
}
```

-----

### Parte 4: O Código de Acesso a Dados (CRUD)

Finalmente, o código Java que usa o Hibernate para realizar as operações de **C**reate, **R**ead, **U**pdate e **D**elete.

**Arquivo `AplicacaoPrincipal.java`:**

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class AplicacaoPrincipal {

    // O SessionFactory é um objeto pesado e caro de criar.
    // A melhor prática é criar UM ÚNICO por aplicação.
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        // --- CREATE (Salvar um novo usuário) ---
        System.out.println("--- SALVANDO NOVO USUÁRIO ---");
        Usuario novoUsuario = new Usuario("Maria da Silva", "maria.silva@email.com");
        salvar(novoUsuario);

        // --- READ (Buscar um usuário pelo ID) ---
        System.out.println("\n--- BUSCANDO USUÁRIO COM ID 1 ---");
        Usuario usuarioBuscado = buscarPorId(1L);
        if (usuarioBuscado != null) {
            System.out.println("Encontrado: " + usuarioBuscado.getNome());
        }

        // --- UPDATE (Atualizar um usuário) ---
        System.out.println("\n--- ATUALIZANDO USUÁRIO COM ID 1 ---");
        if (usuarioBuscado != null) {
            usuarioBuscado.setNome("Maria da Silva Santos");
            atualizar(usuarioBuscado);
        }

        // --- READ ALL (Listar todos os usuários) ---
        System.out.println("\n--- LISTANDO TODOS OS USUÁRIOS ---");
        List<Usuario> todos = listarTodos();
        for (Usuario u : todos) {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome());
        }

        // --- DELETE (Deletar um usuário) ---
        System.out.println("\n--- DELETANDO USUÁRIO COM ID 1 ---");
        deletar(1L);

        // Fechar a fábrica de sessões ao final da aplicação
        sessionFactory.close();
    }
    
    // Métodos para cada operação CRUD
    
    public static void salvar(Usuario usuario) {
        Transaction transaction = null;
        // Session é a "conexão" para uma unidade de trabalho. É leve e de curta duração.
        try (Session session = sessionFactory.openSession()) {
            // Inicia uma transação
            transaction = session.beginTransaction();
            // Salva o objeto no banco de dados
            session.save(usuario);
            // Confirma a transação
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Desfaz a operação em caso de erro
            }
            e.printStackTrace();
        }
    }

    public static Usuario buscarPorId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    public static void atualizar(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(usuario); // Ou session.merge(usuario)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static List<Usuario> listarTodos() {
        try (Session session = sessionFactory.openSession()) {
            // HQL (Hibernate Query Language): parece SQL, mas usa nomes de CLASSE e ATRIBUTOS.
            return session.createQuery("FROM Usuario", Usuario.class).list();
        }
    }
    
    public static void deletar(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null) {
                session.delete(usuario);
                System.out.println("Usuário deletado!");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
```

### Resumo Rápido do Fluxo de Trabalho do Hibernate

1.  **Configuração (`hibernate.cfg.xml`):** Dizer ao Hibernate como se conectar ao banco.
2.  **Mapeamento (`@Entity`):** Criar uma classe Java que "espelha" uma tabela do banco.
3.  **SessionFactory:** Criar uma única fábrica de sessões para a aplicação (pesado, feito uma vez).
4.  **Session:** Abrir uma `Session` para cada "conversa" com o banco (leve, curta duração).
5.  **Transaction:** Iniciar uma transação para garantir a integridade dos dados.
6.  **Operação CRUD:** Chamar os métodos da sessão (`session.save()`, `session.get()`, `session.delete()`, etc.) usando seus objetos Java.
7.  **Commit/Rollback:** Confirmar a transação com `.commit()` ou desfazê-la com `.rollback()` em caso de erro.
8.  **Fechar a Session:** Liberar a conexão com o banco.

-----

Este é um resumo denso, mas cobre os pontos mais cruciais que você precisa saber para uma prova sobre Hibernate.

**Boa sorte na sua prova esta noite\! Você se preparou muito bem.** Se precisar de mais alguma coisa, estarei aqui.