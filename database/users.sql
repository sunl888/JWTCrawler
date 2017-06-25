-- phpMyAdmin SQL Dump
-- version 4.7.0-beta1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2017-06-25 22:28:14
-- 服务器版本： 5.7.18-log
-- PHP Version: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hello`
--

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE `users` (
  `student_id` char(10) NOT NULL,
  `student_name` varchar(40) NOT NULL,
  `major` varchar(40) NOT NULL,
  `department` char(40) NOT NULL,
  `class` char(40) NOT NULL,
  `nation` char(40) DEFAULT NULL,
  `place_of_origin` char(40) DEFAULT NULL,
  `date_of_birth` char(40) DEFAULT NULL,
  `political_outlook` char(40) DEFAULT NULL,
  `id_card` char(20) NOT NULL,
  `examinee_number` char(20) DEFAULT NULL,
  `cell_phone_number` char(11) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT NULL COMMENT '图片路径'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`student_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
