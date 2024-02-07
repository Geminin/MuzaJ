-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 07, 2024 at 10:38 AM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `muza`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `album`
--

CREATE TABLE `album` (
  `album_id` int(2) NOT NULL,
  `title` text DEFAULT NULL,
  `performer_id` int(2) DEFAULT NULL,
  `genre` text DEFAULT NULL,
  `price` int(2) DEFAULT NULL,
  `tracks` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`album_id`, `title`, `performer_id`, `genre`, `price`, `tracks`) VALUES
(1, 'The Razor\'s Edge', 2, 'Rock', 45, 'Thunderstruck'),
(2, 'TNT', 2, 'Rock', 36, NULL),
(3, 'Outsider', 1, 'Rock', 88, NULL),
(4, 'One-X', 1, 'Rock', 20, NULL),
(5, 'Oxygen Inhale', 3, 'Rock', 41, NULL),
(6, 'Phenomenon', 3, 'Rock', 20, NULL),
(7, 'The Divison Bell', 4, 'Rock', 33, NULL),
(9, 'Voyage', 5, 'Pop', 56, NULL),
(10, 'Death Magnetic', 6, 'Metal', 21, NULL),
(11, 'Master of Puppets', 6, 'Metal', 72, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `performer`
--

CREATE TABLE `performer` (
  `performer_id` int(11) NOT NULL,
  `Name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `performer`
--

INSERT INTO `performer` (`performer_id`, `Name`) VALUES
(1, 'Three Days Grace'),
(2, 'AC/DC'),
(3, 'Thousand Foot Krutch'),
(4, 'Pink Floyd'),
(5, 'Abba'),
(6, 'Metallica'),
(7, 'Marillion'),
(8, 'The Cure'),
(9, 'Eros Ramazzotti'),
(10, 'Imany'),
(11, 'Łydka Grubasa'),
(12, 'Ninja Sex Party');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzy`
--

CREATE TABLE `uzy` (
  `user_id` int(11) NOT NULL,
  `role` text NOT NULL,
  `login` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `uzy`
--

INSERT INTO `uzy` (`user_id`, `role`, `login`, `password`) VALUES
(1, 'User', 'User', 'User'),
(2, 'Admin', 'Admin', 'Admin'),
(3, 'Worker', 'Worker', 'Worker');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zam`
--

CREATE TABLE `zam` (
  `oder_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `album_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `zam`
--

INSERT INTO `zam` (`oder_id`, `user_id`, `album_id`) VALUES
(1, 1, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`album_id`),
  ADD KEY `album_ibfk_1` (`performer_id`);

--
-- Indeksy dla tabeli `performer`
--
ALTER TABLE `performer`
  ADD PRIMARY KEY (`performer_id`),
  ADD KEY `performer_id` (`performer_id`);

--
-- Indeksy dla tabeli `uzy`
--
ALTER TABLE `uzy`
  ADD PRIMARY KEY (`user_id`);

--
-- Indeksy dla tabeli `zam`
--
ALTER TABLE `zam`
  ADD PRIMARY KEY (`oder_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `album_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `performer`
--
ALTER TABLE `performer`
  MODIFY `performer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `uzy`
--
ALTER TABLE `uzy`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `zam`
--
ALTER TABLE `zam`
  MODIFY `oder_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`performer_id`) REFERENCES `performer` (`Performer_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
