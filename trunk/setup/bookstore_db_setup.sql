-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Dec 03, 2008 at 09:57 AM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `bookstore`
--

DROP SCHEMA IF EXISTS `bookstore`;
CREATE SCHEMA `bookstore`;
USE `bookstore`;

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
CREATE TABLE IF NOT EXISTS `author` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `first_name` varchar(255) NOT NULL,
  `lat_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `first_name`, `lat_name`) VALUES
(1, 'Don', 'McArther'),
(2, 'Juan', 'Bergion');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(10) NOT NULL auto_increment,
  `isbn` varchar(10) NOT NULL,
  `title` text NOT NULL,
  `author_id` int(10) unsigned NOT NULL,
  `copyright_date` date NOT NULL,
  `image` varchar(255) default NULL,
  `created_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  KEY `author_id` (`author_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `isbn`, `title`, `author_id`, `copyright_date`, `image`, `created_date`) VALUES
(1, '1111111111', 'Simple Book', 1, '2004-05-11', 'book1.jpg', '1988-12-02 00:00:00'),
(2, '2222222222', 'Book #2', 2, '2007-06-01', 'book2.jpg', '2008-12-02 00:00:00'),
(3, '7854785214', 'Random Book', 1, '1990-12-14', 'book3.jpg', '2005-12-14 14:36:06'),
(4, '7812325142', 'Blah Blah', 1, '1990-12-14', 'book4.jpg', '1990-12-14 14:36:06'),
(5, '9865258741', 'Hello There', 2, '1990-12-14', 'book5.jpg', '1990-12-14 14:36:06'),
(6, '8754659821', 'Book of Books', 2, '1990-12-14', 'book6.jpg', '1990-12-14 14:36:06');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`) VALUES
(1, 'Bob', 'Johnson', 'bob@johnson.com', 'pwd4bob'),
(2, 'Rex', 'Smith', 'rex@smith.com', 'pwd4rex'),
(8, 'Tommy', 'Thompson', 'tommy@thompson.com', 'pwd4tommy');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`);
