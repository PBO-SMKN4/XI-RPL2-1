-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 13, 2020 at 12:25 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `nama_kelas` varchar(30) NOT NULL,
  `guru` char(10) NOT NULL,
  `jurusan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`nama_kelas`, `guru`, `jurusan`) VALUES
('XI RPL 2', '12345678', 'Rekayasa Perangkat Lunak');

-- --------------------------------------------------------

--
-- Table structure for table `detail_jadwal`
--

CREATE TABLE `detail_jadwal` (
  `id_detail` int(11) NOT NULL,
  `id_jadwal` int(11) NOT NULL,
  `guru_matpel` char(10) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat') NOT NULL,
  `jam_ke` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_jadwal`
--

INSERT INTO `detail_jadwal` (`id_detail`, `id_jadwal`, `guru_matpel`, `hari`, `jam_ke`) VALUES
(1, 1, '12345679', 'Senin', 1),
(2, 1, '12345680', 'Senin', 6),
(3, 1, '123456781', 'Selasa', 1),
(4, 1, '123456782', 'Selasa', 5),
(5, 1, '123456783', 'Selasa', 8),
(6, 1, '123456784', 'Rabu', 1),
(7, 1, '123456785', 'Rabu', 3),
(8, 1, '123456786', 'Rabu', 7),
(9, 1, '123456787', 'Kamis', 1),
(10, 1, '123456788', 'Kamis', 3),
(11, 1, '123456789', 'Kamis', 4),
(12, 1, '123456790', 'Jumat', 1),
(13, 1, '123456791', 'Jumat', 3);

-- --------------------------------------------------------

--
-- Table structure for table `guru_matpel`
--

CREATE TABLE `guru_matpel` (
  `nip` char(10) NOT NULL,
  `nama` varchar(225) NOT NULL,
  `matpel` varchar(35) NOT NULL,
  `jk` enum('Laki-laki','Perempuan') NOT NULL,
  `email` varchar(35) NOT NULL,
  `no_wa` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guru_matpel`
--

INSERT INTO `guru_matpel` (`nip`, `nama`, `matpel`, `jk`, `email`, `no_wa`) VALUES
('123456781', 'Bu Rani', 'PPL', 'Perempuan', 'rani@mail.com', '087643786521'),
('123456782', 'Bu Dwi', 'Bahasa Inggris', 'Perempuan', 'dwi@mail.com', '08796547346'),
('123456783', 'Pak Ikin', 'Matematika', 'Laki-laki', 'kang@mail.com', '08757893654'),
('123456784', 'Bu Alis', 'PPKN', 'Perempuan', 'liz@mail.com', '084572859275'),
('123456785', 'Pak Ali', 'Basis Data', 'Laki-laki', 'ali@mail.com', '086754362801'),
('123456786', 'Bu Isnaini', 'Pendidikan Agama Islam', 'Perempuan', 'isme@mail.com', '087554938298'),
('123456787', 'Pak Agus', 'Olahraga', 'Laki-laki', 'yes@mail.com', '086473820976'),
('123456788', 'Bu Hani', 'BK', 'Perempuan', 'han@mail.com', '0863472839843'),
('123456789', 'Bu Hana', 'PWPB', 'Perempuan', 'hana@mail.com', '086473929847'),
('12345679', 'Pak Iman', 'PKK', 'Laki-laki', 'iman@mail.com', '098767898765'),
('123456790', 'Pak Rizal', 'Bahasa Sunda', 'Laki-laki', 'ijale@mail.com', '087645839273'),
('123456791', 'Bu Offy', 'PBO', 'Perempuan', 'offy@mail.com', '086327482984'),
('12345680', 'Pak Jafar', 'Bahasa Indonesia', 'Laki-laki', 'jafar@mail.com', '087156429867');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_pelajaran`
--

CREATE TABLE `jadwal_pelajaran` (
  `id_jadwal` int(11) NOT NULL,
  `nama_kelas` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwal_pelajaran`
--

INSERT INTO `jadwal_pelajaran` (`id_jadwal`, `nama_kelas`) VALUES
(1, 'XI RPL 2');

-- --------------------------------------------------------

--
-- Table structure for table `jawaban`
--

CREATE TABLE `jawaban` (
  `id_jawaban` int(11) NOT NULL,
  `id_pertanyaan` int(11) NOT NULL,
  `nis` char(10) NOT NULL,
  `jawaban` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pertanyaan`
--

CREATE TABLE `pertanyaan` (
  `id_pertanyaan` int(11) NOT NULL,
  `nis` char(10) NOT NULL,
  `pertanyaan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pesan`
--

CREATE TABLE `pesan` (
  `id_pesan` int(11) NOT NULL,
  `nis` char(10) NOT NULL,
  `nip` char(10) NOT NULL,
  `pesan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pesan`
--

INSERT INTO `pesan` (`id_pesan`, `nis`, `nip`, `pesan`) VALUES
(1, '1819116234', '12345678', 'Kono Giorno Giovanna ni wa yume ga aru!'),
(2, '1819116235', '12345678', 'Yare yare daze');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `nis` char(10) NOT NULL,
  `nama` varchar(225) NOT NULL,
  `jk` char(10) NOT NULL,
  `nama_kelas` varchar(30) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `username` varchar(35) NOT NULL,
  `email` varchar(35) NOT NULL,
  `password` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`nis`, `nama`, `jk`, `nama_kelas`, `tgl_lahir`, `username`, `email`, `password`) VALUES
('1819116234', 'Giorno Giovanna', 'Laki-laki', 'XI RPL 2', '2019-03-12', 'giorno', 'giorno@mail.com', 'goldexperience'),
('1819116235', 'Bruno Bucciarati', 'Laki-laki', 'XI RPL 2', '2019-10-14', 'bruno', 'bruno@mail.com', 'stickyfingers');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `nip` char(10) NOT NULL,
  `nama` varchar(225) NOT NULL,
  `jk` enum('Laki-laki','Perempuan') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `username` varchar(35) NOT NULL,
  `email` varchar(35) NOT NULL,
  `password` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`nip`, `nama`, `jk`, `tgl_lahir`, `username`, `email`, `password`) VALUES
('12345678', 'Guru BK', 'Perempuan', '1996-03-02', 'debug1', 'bk@mail.com', 'goldexperience');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`nama_kelas`),
  ADD KEY `guru` (`guru`);

--
-- Indexes for table `detail_jadwal`
--
ALTER TABLE `detail_jadwal`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `guru_matpel` (`guru_matpel`),
  ADD KEY `id_jadwal` (`id_jadwal`);

--
-- Indexes for table `guru_matpel`
--
ALTER TABLE `guru_matpel`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `jadwal_pelajaran`
--
ALTER TABLE `jadwal_pelajaran`
  ADD PRIMARY KEY (`id_jadwal`),
  ADD KEY `nama_kelas` (`nama_kelas`);

--
-- Indexes for table `jawaban`
--
ALTER TABLE `jawaban`
  ADD PRIMARY KEY (`id_jawaban`),
  ADD KEY `nis` (`nis`),
  ADD KEY `id_pertanyaan` (`id_pertanyaan`);

--
-- Indexes for table `pertanyaan`
--
ALTER TABLE `pertanyaan`
  ADD PRIMARY KEY (`id_pertanyaan`),
  ADD KEY `nis` (`nis`);

--
-- Indexes for table `pesan`
--
ALTER TABLE `pesan`
  ADD PRIMARY KEY (`id_pesan`),
  ADD KEY `nis` (`nis`),
  ADD KEY `nip` (`nip`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`nis`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `nama_kelas` (`nama_kelas`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`nip`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_jadwal`
--
ALTER TABLE `detail_jadwal`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `jadwal_pelajaran`
--
ALTER TABLE `jadwal_pelajaran`
  MODIFY `id_jadwal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `jawaban`
--
ALTER TABLE `jawaban`
  MODIFY `id_jawaban` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pertanyaan`
--
ALTER TABLE `pertanyaan`
  MODIFY `id_pertanyaan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pesan`
--
ALTER TABLE `pesan`
  MODIFY `id_pesan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`guru`) REFERENCES `teachers` (`nip`);

--
-- Constraints for table `detail_jadwal`
--
ALTER TABLE `detail_jadwal`
  ADD CONSTRAINT `detail_jadwal_ibfk_2` FOREIGN KEY (`guru_matpel`) REFERENCES `guru_matpel` (`nip`),
  ADD CONSTRAINT `detail_jadwal_ibfk_3` FOREIGN KEY (`id_jadwal`) REFERENCES `jadwal_pelajaran` (`id_jadwal`);

--
-- Constraints for table `jadwal_pelajaran`
--
ALTER TABLE `jadwal_pelajaran`
  ADD CONSTRAINT `jadwal_pelajaran_ibfk_1` FOREIGN KEY (`nama_kelas`) REFERENCES `classes` (`nama_kelas`);

--
-- Constraints for table `jawaban`
--
ALTER TABLE `jawaban`
  ADD CONSTRAINT `jawaban_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `students` (`nis`),
  ADD CONSTRAINT `jawaban_ibfk_2` FOREIGN KEY (`id_pertanyaan`) REFERENCES `pertanyaan` (`id_pertanyaan`);

--
-- Constraints for table `pertanyaan`
--
ALTER TABLE `pertanyaan`
  ADD CONSTRAINT `pertanyaan_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `students` (`nis`);

--
-- Constraints for table `pesan`
--
ALTER TABLE `pesan`
  ADD CONSTRAINT `pesan_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `students` (`nis`),
  ADD CONSTRAINT `pesan_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `teachers` (`nip`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`nama_kelas`) REFERENCES `classes` (`nama_kelas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
