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
CREATE TABLE `Problem` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `namaProblem` VARCHAR(191) NOT NULL,
    `deskripsi` VARCHAR(191) NOT NULL,
    `idDesa` INTEGER NOT NULL,
    `idModul` INTEGER NOT NULL,

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
CREATE TABLE `ModulProblem` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `idModul` INTEGER NOT NULL,
    `idProblem` INTEGER NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Kursus` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `namaKursus` VARCHAR(191) NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `Problem` ADD CONSTRAINT `Problem_idDesa_fkey` FOREIGN KEY (`idDesa`) REFERENCES `Desa`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Modul` ADD CONSTRAINT `Modul_idKursus_fkey` FOREIGN KEY (`idKursus`) REFERENCES `Kursus`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `ModulProblem` ADD CONSTRAINT `ModulProblem_idModul_fkey` FOREIGN KEY (`idModul`) REFERENCES `Modul`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `ModulProblem` ADD CONSTRAINT `ModulProblem_idProblem_fkey` FOREIGN KEY (`idProblem`) REFERENCES `Problem`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
