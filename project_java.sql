-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2020 at 04:27 AM
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
  `jurusan` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`nama_kelas`, `guru`, `jurusan`) VALUES
('X AV 1', '12345679', 'Audio Video'),
('X AV 2', '12345679', 'Audio Video'),
('X AV 3', '12345679', 'Audio Video'),
('X AV 4', '12345679', 'Audio Video'),
('X MM', '12345679', 'Multimedia'),
('X RPL 1', '12345679', 'Rekayasa Perangkat Lunak'),
('X RPL 2', '12345679', 'Rekayasa Perangkat Lunak'),
('X RPL 3', '12345679', 'Rekayasa Perangkat Lunak'),
('X TITL 1', '12345679', 'Teknik Instalasi Tenaga Listrik'),
('X TITL 2', '12345679', 'Teknik Instalasi Tenaga Listrik'),
('X TKJ 1', '12345679', 'Teknik Komputer Jaringan'),
('X TKJ 2', '12345679', 'Teknik Komputer Jaringan'),
('X TOI 1', '12345679', 'Teknik Otomasi Industri'),
('X TOI 2', '12345679', 'Teknik Otomasi Industri'),
('XI AV 1', '12345678', 'Audio Video'),
('XI AV 2', '12345678', 'Audio Video'),
('XI AV 3', '12345678', 'Audio Video'),
('XI MM', '12345678', 'Multimedia'),
('XI RPL 1', '12345678', 'Rekayasa Perangkat Lunak'),
('XI RPL 2', '12345678', 'Rekayasa Perangkat Lunak'),
('XI RPL 3', '12345678', 'Rekayasa Perangkat Lunak'),
('XI TITL 1', '12345678', 'Teknik Instalasi Tenaga Listrik'),
('XI TITL 2', '12345678', 'Teknik Instalasi Tenaga Listrik'),
('XI TKJ 1', '12345678', 'Teknik Komputer Jaringan'),
('XI TKJ 2', '12345678', 'Teknik Komputer Jaringan'),
('XI TOI 1', '12345678', 'Teknik Otomasi Industri'),
('XI TOI 2', '12345678', 'Teknik Otomasi Industri'),
('XII AV 1', '12345680', 'Audio Video'),
('XII AV 2', '12345680', 'Audio Video'),
('XII AV 3', '12345680', 'Audio Video'),
('XII AV 4', '12345680', 'Audio Video'),
('XII MM', '12345680', 'Multimedia'),
('XII RPL 1', '12345680', 'Rekayasa Perangkat Lunak'),
('XII RPL 2', '12345680', 'Rekayasa Perangkat Lunak'),
('XII RPL 3', '12345680', 'Rekayasa Perangkat Lunak'),
('XII TITL 1', '12345680', 'Teknik Instalasi Tenaga Listrik'),
('XII TITL 2', '12345680', 'Teknik Instalasi Tenaga Listrik'),
('XII TKJ', '12345680', 'Teknik Komputer Jaringan'),
('XII TOI 1', '12345680', 'Teknik Otomasi Industri'),
('XII TOI 2', '12345680', 'Teknik Otomasi Industri');

-- --------------------------------------------------------

--
-- Table structure for table `curhat`
--

CREATE TABLE `curhat` (
  `id_curhat` int(20) NOT NULL,
  `id_siswa` char(10) DEFAULT NULL,
  `id_guru` char(10) DEFAULT NULL,
  `isi_chat` text NOT NULL,
  `id_ruang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Table structure for table `jenis_kesalahan`
--

CREATE TABLE `jenis_kesalahan` (
  `id` int(11) NOT NULL,
  `jenis_kesalahan` varchar(100) NOT NULL,
  `skor_kesalahan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kategori_sikap`
--

CREATE TABLE `kategori_sikap` (
  `id_kategori` int(11) NOT NULL,
  `kategori` varchar(3) NOT NULL,
  `keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori_sikap`
--

INSERT INTO `kategori_sikap` (`id_kategori`, `kategori`, `keterangan`) VALUES
(1, 'SB', 'Sangat Baik'),
(2, 'B', 'Baik'),
(3, 'C', 'Cukup'),
(4, 'K', 'Kurang'),
(5, 'SK', 'Sangat Kurang');

-- --------------------------------------------------------

--
-- Table structure for table `mading`
--

CREATE TABLE `mading` (
  `id_mading` int(11) NOT NULL,
  `kategori` enum('lomba','workshop','pengumuman') NOT NULL,
  `tema` varchar(225) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `isi_mading` text NOT NULL,
  `pengirim` char(10) NOT NULL,
  `tgl_upload` datetime NOT NULL,
  `tgl_kadaluarsa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `penilaian`
--

CREATE TABLE `penilaian` (
  `id_penilaian` int(11) NOT NULL,
  `nis` char(10) NOT NULL,
  `sikap` int(11) NOT NULL,
  `skor` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `peringatan`
--

CREATE TABLE `peringatan` (
  `id_peringatan` int(11) NOT NULL,
  `nis` char(10) NOT NULL,
  `nip` char(10) NOT NULL,
  `isi_peringatan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pertanyaan`
--

CREATE TABLE `pertanyaan` (
  `id_pertanyaan` int(11) NOT NULL,
  `nis` char(10) NOT NULL,
  `tipe_soal` int(11) NOT NULL,
  `pertanyaan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ruang_curhat`
--

CREATE TABLE `ruang_curhat` (
  `id_ruang` int(11) NOT NULL,
  `foto_ruang` varchar(255) DEFAULT NULL,
  `id_siswa` char(10) DEFAULT NULL,
  `id_guru` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `password` varchar(255) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `wali_1` char(10) NOT NULL,
  `wali_2` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `nip` char(10) NOT NULL,
  `nama` varchar(225) NOT NULL,
  `jk` enum('Laki-laki','Perempuan') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `no_whatsapp` varchar(16) NOT NULL,
  `username` varchar(35) NOT NULL,
  `email` varchar(35) NOT NULL,
  `password` varchar(255) NOT NULL,
  `foto` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`nip`, `nama`, `jk`, `tgl_lahir`, `no_whatsapp`, `username`, `email`, `password`, `foto`) VALUES
('12345678', 'Hani', 'Perempuan', '1996-03-02', '089577722122', 'debug1', 'bk@mail.com', 'goldexperience', '0'),
('12345679', 'Aat', 'Perempuan', '1987-03-11', '08876351241', 'debug2', 'hex@mail.com', 'pronio', ''),
('12345680', 'Lisa', 'Perempuan', '1985-09-23', '0836244567', 'lis', 'lisa@mail.com', 'skrappa', '');

-- --------------------------------------------------------

--
-- Table structure for table `tipe_soal`
--

CREATE TABLE `tipe_soal` (
  `id_tipe` int(11) NOT NULL,
  `kelompok` varchar(30) NOT NULL,
  `nama_matpel` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipe_soal`
--

INSERT INTO `tipe_soal` (`id_tipe`, `kelompok`, `nama_matpel`) VALUES
(1, 'Umum', 'Bahasa Inggris'),
(2, 'Umum', 'Bahasa Indonesia'),
(3, 'Umum', 'Bahasa Sunda'),
(4, 'Umum', 'Matematika'),
(5, 'Umum', 'PAI'),
(6, 'Umum', 'Sejarah'),
(7, 'Umum', 'PPKN'),
(8, 'Umum', 'Seni Budaya'),
(9, 'Umum', 'Bahasa Jepang'),
(10, 'RPL', 'PBO'),
(11, 'RPL', 'PWPB'),
(12, 'RPL', 'PKK'),
(13, 'RPL', 'PPL'),
(14, 'RPL', 'Basis Data'),
(15, 'RPL', 'PAI');

-- --------------------------------------------------------

--
-- Table structure for table `wali`
--

CREATE TABLE `wali` (
  `id_wali` char(10) NOT NULL,
  `nama` varchar(225) NOT NULL,
  `jk` enum('Laki-laki','Perempuan') NOT NULL,
  `goldar` enum('A','B','AB','O') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `no_hp` char(15) NOT NULL,
  `alamat` text NOT NULL,
  `hubungan` enum('Ibu','Ayah','Wali') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Indexes for table `curhat`
--
ALTER TABLE `curhat`
  ADD PRIMARY KEY (`id_curhat`),
  ADD KEY `id_ruang` (`id_ruang`),
  ADD KEY `id_user` (`id_siswa`),
  ADD KEY `id_guru` (`id_guru`);

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
-- Indexes for table `jenis_kesalahan`
--
ALTER TABLE `jenis_kesalahan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kategori_sikap`
--
ALTER TABLE `kategori_sikap`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `mading`
--
ALTER TABLE `mading`
  ADD PRIMARY KEY (`id_mading`),
  ADD KEY `pengirim` (`pengirim`);

--
-- Indexes for table `penilaian`
--
ALTER TABLE `penilaian`
  ADD PRIMARY KEY (`id_penilaian`),
  ADD KEY `nis` (`nis`),
  ADD KEY `sikap` (`sikap`);

--
-- Indexes for table `peringatan`
--
ALTER TABLE `peringatan`
  ADD PRIMARY KEY (`id_peringatan`),
  ADD KEY `nis` (`nis`),
  ADD KEY `nip` (`nip`);

--
-- Indexes for table `pertanyaan`
--
ALTER TABLE `pertanyaan`
  ADD PRIMARY KEY (`id_pertanyaan`),
  ADD KEY `nis` (`nis`),
  ADD KEY `tipe_soal` (`tipe_soal`);

--
-- Indexes for table `ruang_curhat`
--
ALTER TABLE `ruang_curhat`
  ADD PRIMARY KEY (`id_ruang`),
  ADD KEY `id_user` (`id_siswa`),
  ADD KEY `id_guru` (`id_guru`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`nis`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `nama_kelas` (`nama_kelas`),
  ADD KEY `wali_1` (`wali_1`),
  ADD KEY `wali_2` (`wali_2`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`nip`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `tipe_soal`
--
ALTER TABLE `tipe_soal`
  ADD PRIMARY KEY (`id_tipe`);

--
-- Indexes for table `wali`
--
ALTER TABLE `wali`
  ADD PRIMARY KEY (`id_wali`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `curhat`
--
ALTER TABLE `curhat`
  MODIFY `id_curhat` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
-- AUTO_INCREMENT for table `jenis_kesalahan`
--
ALTER TABLE `jenis_kesalahan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `kategori_sikap`
--
ALTER TABLE `kategori_sikap`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `mading`
--
ALTER TABLE `mading`
  MODIFY `id_mading` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `penilaian`
--
ALTER TABLE `penilaian`
  MODIFY `id_penilaian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `peringatan`
--
ALTER TABLE `peringatan`
  MODIFY `id_peringatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pertanyaan`
--
ALTER TABLE `pertanyaan`
  MODIFY `id_pertanyaan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ruang_curhat`
--
ALTER TABLE `ruang_curhat`
  MODIFY `id_ruang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `tipe_soal`
--
ALTER TABLE `tipe_soal`
  MODIFY `id_tipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`guru`) REFERENCES `teachers` (`nip`);

--
-- Constraints for table `curhat`
--
ALTER TABLE `curhat`
  ADD CONSTRAINT `curhat_ibfk_1` FOREIGN KEY (`id_ruang`) REFERENCES `ruang_curhat` (`id_ruang`),
  ADD CONSTRAINT `curhat_ibfk_2` FOREIGN KEY (`id_ruang`) REFERENCES `ruang_curhat` (`id_ruang`),
  ADD CONSTRAINT `curhat_ibfk_3` FOREIGN KEY (`id_ruang`) REFERENCES `ruang_curhat` (`id_ruang`),
  ADD CONSTRAINT `curhat_ibfk_4` FOREIGN KEY (`id_siswa`) REFERENCES `students` (`nis`),
  ADD CONSTRAINT `curhat_ibfk_5` FOREIGN KEY (`id_guru`) REFERENCES `teachers` (`nip`);

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
-- Constraints for table `mading`
--
ALTER TABLE `mading`
  ADD CONSTRAINT `mading_ibfk_1` FOREIGN KEY (`pengirim`) REFERENCES `teachers` (`nip`);

--
-- Constraints for table `penilaian`
--
ALTER TABLE `penilaian`
  ADD CONSTRAINT `penilaian_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `students` (`nis`),
  ADD CONSTRAINT `penilaian_ibfk_2` FOREIGN KEY (`sikap`) REFERENCES `kategori_sikap` (`id_kategori`);

--
-- Constraints for table `peringatan`
--
ALTER TABLE `peringatan`
  ADD CONSTRAINT `peringatan_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `students` (`nis`),
  ADD CONSTRAINT `peringatan_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `teachers` (`nip`);

--
-- Constraints for table `pertanyaan`
--
ALTER TABLE `pertanyaan`
  ADD CONSTRAINT `pertanyaan_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `students` (`nis`),
  ADD CONSTRAINT `pertanyaan_ibfk_2` FOREIGN KEY (`tipe_soal`) REFERENCES `tipe_soal` (`id_tipe`);

--
-- Constraints for table `ruang_curhat`
--
ALTER TABLE `ruang_curhat`
  ADD CONSTRAINT `ruang_curhat_ibfk_1` FOREIGN KEY (`id_siswa`) REFERENCES `students` (`nis`),
  ADD CONSTRAINT `ruang_curhat_ibfk_2` FOREIGN KEY (`id_guru`) REFERENCES `teachers` (`nip`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`nama_kelas`) REFERENCES `classes` (`nama_kelas`),
  ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`wali_1`) REFERENCES `wali` (`id_wali`),
  ADD CONSTRAINT `students_ibfk_3` FOREIGN KEY (`wali_2`) REFERENCES `wali` (`id_wali`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
