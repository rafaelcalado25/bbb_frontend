-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 03-Ago-2020 às 02:50
-- Versão do servidor: 10.1.34-MariaDB
-- PHP Version: 7.2.7

--DROP TABLE `paredao`, `paredaoxparticipantes`, `participante`, `perfil`, `usuario`, `votacao`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bbb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `paredao`
--

CREATE TABLE `paredao` (
  `id` bigint(20) NOT NULL,
  `dh_fechamento` datetime DEFAULT NULL,
  `fechado` bit(1) DEFAULT NULL,
  `realizacao` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `paredaoxparticipantes`
--

CREATE TABLE `paredaoxparticipantes` (
  `paredao_id` bigint(20) NOT NULL,
  `participante_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `participante`
--

CREATE TABLE `participante` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `eliminado` tinyint(1) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `participante`
--

INSERT INTO `participante` (`id`, `nome`, `picture`, `eliminado`) VALUES
(1, 'Nick Jones', 'assets/images/nick.png', 0),
(2, 'Eva Moor', 'assets/images/eva.png', 0),
(3, 'Jack Williams', 'assets/images/jack.png', 0),
(4, 'Lee Wong', 'assets/images/lee.png', 0),
(5, 'Alan Thompson', 'assets/images/alan.png', 0),
(6, 'Kate Martinez', 'assets/images/kate.png', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil`
--

CREATE TABLE `perfil` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `perfil`
--

INSERT INTO `perfil` (`id`, `descricao`, `nome`) VALUES
(1, 'Acesso do Publico', 'ROLE_AUDIENCIA'),
(2, 'Acesso interno', 'ROLE_SUPERVISAO');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `perfil_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `password`, `username`, `perfil_id`) VALUES
(1, '$2a$10$02Db0WafXNVY7jXFg30hgOKy2ip0ulsaKY6vmspLCbB/ec1wGVKpm', 'rafael.camara@gmail.com', 1),
(2, '$2a$10$02Db0WafXNVY7jXFg30hgOKy2ip0ulsaKY6vmspLCbB/ec1wGVKpm', 'rafael.calado@gmail.com', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `votacao`
--

CREATE TABLE `votacao` (
  `id` bigint(20) NOT NULL,
  `dh_voto` datetime DEFAULT NULL,
  `paredao_id` bigint(20) DEFAULT NULL,
  `participante_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `paredao`
--
ALTER TABLE `paredao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `paredaoxparticipantes`
--
ALTER TABLE `paredaoxparticipantes`
  ADD PRIMARY KEY (`participante_id`,`paredao_id`),
  ADD KEY `FKcxoy6f82y5wm7nx4ck33kyurk` (`paredao_id`);

--
-- Indexes for table `participante`
--
ALTER TABLE `participante`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_8m46w0jj2ksw0subwvu0i5kmd` (`nome`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9po12ytp6krwvwht1kmd0qgxf` (`perfil_id`);

--
-- Indexes for table `votacao`
--
ALTER TABLE `votacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8qa2tmg1lcbaqfahrw91vrv9y` (`paredao_id`),
  ADD KEY `FKpk8spuxm6qm95y2f5xbg9miae` (`participante_id`),
  ADD KEY `FKs3ba8j1n5r6g8kx92tr8q0y6s` (`usuario_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `paredao`
--
ALTER TABLE `paredao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `participante`
--
ALTER TABLE `participante`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `votacao`
--
ALTER TABLE `votacao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=199;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
