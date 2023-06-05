-- CreateTable
CREATE TABLE `Desa` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(191) NOT NULL,
    `password` VARCHAR(191) NOT NULL,
    `namaDesa` VARCHAR(191) NOT NULL,
    `telepon` VARCHAR(191) NOT NULL,
    `lokasiDesa` VARCHAR(191) NOT NULL,
    `latitude` DOUBLE NOT NULL,
    `longitude` DOUBLE NOT NULL,

    UNIQUE INDEX `Desa_email_key`(`email`),
    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Masalah` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `namaMasalah` VARCHAR(191) NOT NULL,
    `deskripsi` VARCHAR(191) NOT NULL,
    `idDesa` INTEGER NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Modul` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `namaModul` VARCHAR(191) NOT NULL,
    `idKursus` INTEGER NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `ModulMasalah` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `idModul` INTEGER NOT NULL,
    `idMasalah` INTEGER NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Kursus` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `namaKursus` VARCHAR(191) NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Pengguna` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `namaLengkap` VARCHAR(191) NOT NULL,
    `email` VARCHAR(191) NOT NULL,
    `password` VARCHAR(191) NOT NULL,

    UNIQUE INDEX `Pengguna_email_key`(`email`),
    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `AmbilMasalah` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `idMasalah` INTEGER NOT NULL,
    `idPengguna` INTEGER NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `Masalah` ADD CONSTRAINT `Masalah_idDesa_fkey` FOREIGN KEY (`idDesa`) REFERENCES `Desa`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Modul` ADD CONSTRAINT `Modul_idKursus_fkey` FOREIGN KEY (`idKursus`) REFERENCES `Kursus`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `ModulMasalah` ADD CONSTRAINT `ModulMasalah_idModul_fkey` FOREIGN KEY (`idModul`) REFERENCES `Modul`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `ModulMasalah` ADD CONSTRAINT `ModulMasalah_idMasalah_fkey` FOREIGN KEY (`idMasalah`) REFERENCES `Masalah`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `AmbilMasalah` ADD CONSTRAINT `AmbilMasalah_idMasalah_fkey` FOREIGN KEY (`idMasalah`) REFERENCES `Masalah`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `AmbilMasalah` ADD CONSTRAINT `AmbilMasalah_idPengguna_fkey` FOREIGN KEY (`idPengguna`) REFERENCES `Pengguna`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
