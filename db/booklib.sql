-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2021 at 10:08 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `booklib`
--

-- --------------------------------------------------------

--
-- Table structure for table `booklib_authors`
--

CREATE TABLE `booklib_authors` (
  `id` int(191) NOT NULL,
  `name` varchar(191) NOT NULL,
  `bio` text DEFAULT NULL,
  `email` varchar(191) NOT NULL,
  `profile` varchar(191) DEFAULT 'default.png',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booklib_authors`
--

INSERT INTO `booklib_authors` (`id`, `name`, `bio`, `email`, `profile`, `created_at`) VALUES
(34, 'Kamal Ferrell', 'Aut et lorem laborio', 'midaf1@mailinator.com', 'oequrtlmeu.png', '2021-05-21 10:32:53'),
(35, 'Melodie Clark', 'Deserunt voluptatem ', 'lokif@mailinator.com', 'czafxbsush.png', '2021-05-22 04:24:28'),
(36, 'Keaton Dixon', 'Deleniti accusantium', 'kowu@mailinator.com', 'bypuqsmycu.png', '2021-05-22 08:18:19'),
(38, 'Colette Hebert', 'Sint dolor et anim ', 'maduqikali@mailinator.com', 'meaoyszrxf.png', '2021-05-22 08:18:05');

-- --------------------------------------------------------

--
-- Table structure for table `booklib_books`
--

CREATE TABLE `booklib_books` (
  `book_id` int(191) NOT NULL,
  `title` varchar(191) NOT NULL,
  `description` text DEFAULT NULL,
  `price` varchar(191) NOT NULL,
  `author_id` int(191) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booklib_books`
--

INSERT INTO `booklib_books` (`book_id`, `title`, `description`, `price`, `author_id`, `created_at`) VALUES
(6, 'Sint eaque repudian', 'Asperiores sed nihil', '503', 36, '2021-05-22 08:20:37'),
(9, 'Omnis doloribus sed ', 'Sit quo reprehenderi', '98', 35, '2021-05-22 08:20:54'),
(11, 'Harry Poter', 'Some description here', '520', 36, '2021-05-22 08:46:37'),
(12, 'Tenetur lorem ut ten', 'Quisquam molestiae q', '113', 35, '2021-05-22 13:39:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booklib_authors`
--
ALTER TABLE `booklib_authors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `booklib_books`
--
ALTER TABLE `booklib_books`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `author_id` (`author_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booklib_authors`
--
ALTER TABLE `booklib_authors`
  MODIFY `id` int(191) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `booklib_books`
--
ALTER TABLE `booklib_books`
  MODIFY `book_id` int(191) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booklib_books`
--
ALTER TABLE `booklib_books`
  ADD CONSTRAINT `booklib_books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `booklib_authors` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
