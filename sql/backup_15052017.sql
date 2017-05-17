-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Erstellungszeit: 17. Mai 2017 um 15:36
-- Server Version: 5.5.33a-MariaDB
-- PHP-Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `sw-ma-xp3`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `postal_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_bio` int(11) NOT NULL,
  `longitude` decimal(9,6) DEFAULT NULL,
  `latitude` decimal(9,6) DEFAULT NULL,
  `commercial` int(1) DEFAULT NULL,
  `delivery` int(1) DEFAULT NULL,
  `yard_sale` int(1) DEFAULT NULL,
  `self_harvest` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `User`
--

INSERT INTO `User` (`id`, `first_name`, `last_name`, `company_name`, `address`, `city`, `postal_code`, `email`, `phone_number`, `password`, `is_bio`, `longitude`, `latitude`, `commercial`, `delivery`, `yard_sale`, `self_harvest`) VALUES
(1, 'Christof', 'Jahn', 'JaheAG', 'Inffeldgasse 10', 'Kappel am Krappfeld', '9321', 'christof.jahn@student.tugraz.at', '0650 978 2153', 'test1', 1, '47.058469', '15.460378', NULL, NULL, NULL, NULL),
(2, 'Test', 'Test', 'Test', 'Test', 'Test', '9321', 'Test@gmx.at', 'Test', 'Test', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Christof', 'Jahn', 'JaheAG', 'Garzern 13', 'Kappel am Krappfeld', '9321', 'christof.jahn@student.tugraz.at', '0650 978 2153', 'test1', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'Test', 'Test', 'Test', 'Test', 'Test', '9321', 'Test@gmx.at', 'Test', 'Test', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'Tobias', 'Stangl', 'Regionalo', 'Klosterwiesgasse 1', 'Graz', '8010', 'tobias.stangl@student.tugraz.at', '06601111111', '123456', 1, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'Max', 'Mustermann', 'MusterAG', 'Mustergasse 1', 'Graz', '8010', 'muster@gmx.at', '0660133311', '123456', 0, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 'Albert', 'Einstein', 'Emc2', 'Genial 1', 'Wien', '1010', 'soklug@gmx.at', '066009876111', '123456', 1, NULL, NULL, NULL, NULL, NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
