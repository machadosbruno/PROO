-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 02/09/2025 às 23:17
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bdpetshop`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbanimal`
--

CREATE TABLE `tbanimal` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `raca` varchar(255) NOT NULL,
  `codCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbanimal`
--

INSERT INTO `tbanimal` (`id`, `nome`, `raca`, `codCliente`) VALUES
(2, 'Marley', 'Cachorro', 1),
(3, 'Pretinha', 'Desconhecido', 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbcliente`
--

CREATE TABLE `tbcliente` (
  `id` int(11) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `CPF` varchar(15) NOT NULL,
  `Telefone` varchar(15) NOT NULL,
  `Email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbcliente`
--

INSERT INTO `tbcliente` (`id`, `Nome`, `CPF`, `Telefone`, `Email`) VALUES
(1, 'Bruno', '123.123.123-12', '(12) 12312-5555', 'bruno@gmail.com'),
(2, 'Pollyana', '321.321.456-32', '(32) 32132-1234', 'Pollyana@gmail.com'),
(3, 'Santo Deus', '100.100.100.99', '(12) 12312-1212', 'Deus@ceu');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbservico`
--

CREATE TABLE `tbservico` (
  `id` int(11) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `codAnimal` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `valor` double NOT NULL,
  `formaPagamento` int(11) NOT NULL,
  `pago` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tbanimal`
--
ALTER TABLE `tbanimal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `codCliente` (`codCliente`);

--
-- Índices de tabela `tbcliente`
--
ALTER TABLE `tbcliente`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tbservico`
--
ALTER TABLE `tbservico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `codCliente` (`codCliente`),
  ADD KEY `codAnimal` (`codAnimal`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbanimal`
--
ALTER TABLE `tbanimal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tbcliente`
--
ALTER TABLE `tbcliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `tbanimal`
--
ALTER TABLE `tbanimal`
  ADD CONSTRAINT `tbanimal_ibfk_1` FOREIGN KEY (`codCliente`) REFERENCES `tbcliente` (`id`);

--
-- Restrições para tabelas `tbservico`
--
ALTER TABLE `tbservico`
  ADD CONSTRAINT `tbservico_ibfk_1` FOREIGN KEY (`codCliente`) REFERENCES `tbcliente` (`id`),
  ADD CONSTRAINT `tbservico_ibfk_2` FOREIGN KEY (`codAnimal`) REFERENCES `tbanimal` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
