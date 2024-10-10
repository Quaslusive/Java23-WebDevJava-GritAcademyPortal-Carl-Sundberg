-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Värd: 127.0.0.1
-- Tid vid skapande: 10 okt 2024 kl 17:42
-- Serverversion: 10.4.32-MariaDB
-- PHP-version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `gritacademyportal`
--

-- --------------------------------------------------------

--
-- Tabellstruktur `courses`
--

CREATE TABLE `courses` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `yhp` int(11) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `courses`
--

INSERT INTO `courses` (`id`, `name`, `yhp`, `description`) VALUES
(1, 'JAVA23 | Konsultrollen', 20, 'Kursens syfte är att den studerande ska få kunskaper och färdigheter att utöva ett konsultativt arbete\r\nsamt ge den studerande förståelse för konsultrollen och affärsmannaskap i rollen som IT-utvecklare.\r\nVidare ska den studerande kunna vara en god ambassadör för den arbetsgivare som den studerande\r\nrepresenterar.'),
(2, 'JAVA23 | Javaprogrammering', 25, 'Kursens syfte är att den studerande ska utveckla fördjupade kunskaper inom programmeringsspråket\r\nJava och dess ramverk. Kursens syfte är även att den studerande ska kunna programmera\r\nändamålsenligt och enligt god programmeringskonvention.'),
(3, 'JAVA23 | Avancerad Javaprogrammering', 25, 'Kursens syfte är att den studerande ska utveckla specialiserade kunskaper inom Javaprogrammering\r\noch språket Java. Vidare är syftet att den studerande ska få kunskap i objektorienterade funktioner för\r\nmodern utveckling.'),
(4, 'JAVA23 | Databaser och Databasdesign', 20, 'Kursens syfte är att den studerande ska utveckla specialiserade kunskaper inom databaser, SQL och\r\ndatabasdesign. Vidare är syftet att den studerande ska få god förtrogenhet med arbetet med\r\ndatabaser och databasdesign.'),
(5, 'JAVA23 | Webbutveckling med Java', 25, 'Kursens syfte är att den studerande ska utveckla specialiserade kunskaper i webbapplikationer\r\nbyggda med Java på serversidan. Vidare ska den studerande även få kunskaper inom\r\nwebbapplikationsdesign.'),
(6, 'JAVA23 | Java Web Services', 20, 'Kursens syfte är att ge den studerande utvecklade kunskaper i hur man kopplar upp sig på olika\r\n\r\ninternetbaserade tjänster. Vidare ämnar kurser ge fördjupning inom praktisk användning av Java-\r\nAPI:er. Kursens syfte är utöver det att den studerande ska få kunskaper inom HTTP och HTTPs samt\r\n\r\nkunna skapa Javadrivna tjänster enligt god praxis.'),
(7, 'JAVA23 | JavaScript', 20, 'Kursens syfte är att ge den studerande gedigna kunskaper i JavaScript och hur man använder det för\r\natt skapa dynamiska hemsidor. Vidare ämnar kursen att ge förståelse om integrationen och\r\ninteraktionen av andra typer av webb relaterade kodspråk.'),
(8, 'JAVA23 | Avancerad JavaScript', 25, 'Kursens syfte är att den studerande ska få gedigna och avancerade kunskaper inom Javascript. Vidare\r\nska den studerande lära sig hur man skapar dynamiska webbsidor. Denna kurs bygger på kursen\r\nJavaScript och är fortsättnings- och fördjupningskurs inom JavaScript med fokus på serverbaserad och\r\nklientbaserad programmering samt webbapplikationer.');

-- --------------------------------------------------------

--
-- Tabellstruktur `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `fName` varchar(50) NOT NULL,
  `lName` varchar(50) NOT NULL,
  `town` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `students`
--

INSERT INTO `students` (`id`, `fName`, `lName`, `town`, `email`, `phone`, `username`, `password`) VALUES
(1, 'Carl', 'Sundberg', 'Lund', 'carl.sundberg@gritacademy.se', '123456789', 'carsun', 'carsun'),
(2, 'Marie', 'Curie', 'Warsaw', 'Marie Curie@hotmail.com', '846404623', 'curcuri', 'Typhus420');

-- --------------------------------------------------------

--
-- Tabellstruktur `students_courses`
--

CREATE TABLE `students_courses` (
  `id` int(11) NOT NULL,
  `students_id` int(11) DEFAULT NULL,
  `courses_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `students_courses`
--

INSERT INTO `students_courses` (`id`, `students_id`, `courses_id`) VALUES
(1, 1, 4),
(2, 2, 6);

-- --------------------------------------------------------

--
-- Tabellstruktur `teachers`
--

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `fName` varchar(50) NOT NULL,
  `lName` varchar(50) NOT NULL,
  `town` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `privilage_type` enum('USER','ADMIN') NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `teachers`
--

INSERT INTO `teachers` (`id`, `fName`, `lName`, `town`, `email`, `phone`, `username`, `password`, `privilage_type`) VALUES
(1, 'Admin', 'User', 'TownName', 'admin@gritacademy.se', '1234567890', 'admin', 'admin', 'ADMIN'),
(2, 'Clara', 'Leivas', 'Malmö', 'Clara.Leivas@gritacademy.se', '872390895', 'Leivas4ever', 'jf82iojf987278732', 'USER');

-- --------------------------------------------------------

--
-- Tabellstruktur `teachers_courses`
--

CREATE TABLE `teachers_courses` (
  `id` int(11) NOT NULL,
  `teachers_id` int(11) DEFAULT NULL,
  `courses_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `teachers_courses`
--

INSERT INTO `teachers_courses` (`id`, `teachers_id`, `courses_id`) VALUES
(1, 2, 7),
(2, 2, 8),
(3, 1, 6),
(4, 1, 5);

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index för tabell `students_courses`
--
ALTER TABLE `students_courses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `students_id` (`students_id`),
  ADD KEY `courses_id` (`courses_id`);

--
-- Index för tabell `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index för tabell `teachers_courses`
--
ALTER TABLE `teachers_courses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `teachers_id` (`teachers_id`),
  ADD KEY `courses_id` (`courses_id`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT för tabell `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT för tabell `students_courses`
--
ALTER TABLE `students_courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT för tabell `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT för tabell `teachers_courses`
--
ALTER TABLE `teachers_courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restriktioner för dumpade tabeller
--

--
-- Restriktioner för tabell `students_courses`
--
ALTER TABLE `students_courses`
  ADD CONSTRAINT `students_courses_ibfk_1` FOREIGN KEY (`students_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `students_courses_ibfk_2` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE;

--
-- Restriktioner för tabell `teachers_courses`
--
ALTER TABLE `teachers_courses`
  ADD CONSTRAINT `teachers_courses_ibfk_1` FOREIGN KEY (`teachers_id`) REFERENCES `teachers` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `teachers_courses_ibfk_2` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
