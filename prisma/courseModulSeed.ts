import { PrismaClient } from "@prisma/client";

const prisma = new PrismaClient();

async function main() {
  const kursusTemplate: { namaKursus: string; deskripsi: string; dampak: string; foto: string }[] = [];

  kursusTemplate.push({
    namaKursus: "Fundamental Course(1)",
    deskripsi: "Fundamental Attitude toward sustainability",
    dampak:
      "mengubah paradigma mengenai sustainability. Memahami Prinsip Ekologi. Memahami Elemen dasar Green Mindset. Memahami Konsep Carbon Footprint. Membuat Rancang Bangun Ecology. Memahami Sustainable Community. Memahami Poin-poin SGGs",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Fundamental Course(2)",
    deskripsi: "Innovation method as problem solving",
    dampak: "Memahami konsep Growth Mindset. Memahami konsep Design Thinking. Memahami Konsep Lean Startup. Memahami Digital Startup",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Fundamental Course (3)",
    deskripsi: "The way to build sustainable business",
    dampak: "Mengenal Sustainable Startup. Memahami SDGs poin ke-12. Mengembangkan Sustainable Business Idea and Process of Growth. Membuat Profit Model untuk bisnis",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Indonesia Sustainability Coral Reef University Network (ISCORE)",
    deskripsi:
      "Terumbu karang menutupi kurang dari 1% wilayah lautan, tetapi mereka secara langsung mendukung jutaan orang dengan menyediakan makanan, pekerjaan, dan sumber daya lainnya. Bahkan lebih banyak orang yang mendapat manfaat dari terumbu karang secara tidak langsung; ekosistem ini membantu memberi makan 1 miliar orang di Asia saja. Populasi manusia hampir 7 miliar orang, dan kemungkinan akan tumbuh menjadi 9 miliar pada tahun 2040. Kita membutuhkan terumbu karang (dan ekosistem lainnya) untuk memasok lebih banyak sumber daya untuk mendukung jumlah kita yang terus bertambah, tetapi mereka semakin terancam dengan kehancuran.",
    dampak: "pelindungan habibat. mengurangi polusi. mengurangi dampak erosi. Promosi pariwisata. Meningkatkan komunitas lokal. Pemeliharaan agen perubahan berkelanjutan dan perlindungan terumbu karang",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Ecotourism",
    deskripsi:
      "Seiring dengan pelonggaran perjalanan dan stabilitas dalam kebijakan perjalanan, pertumbuhan dalam kedatangan internasional sejak Januari 2022 meningkat sebesar 350 persen dibandingkan dengan tahun lalu. Indonesia memiliki banyak lokasi wisata berbasis ecotourism, yang mampu meningkakan perekonomian dan lapangan pekerjaan. Namun kendala SDM membuat banyak lokasi wisata berbasis ecotourism belum bisa berakselerasi melakukan percepatan yg dibutuhkan.",
    dampak:
      "Pelestarian ekosistem terhadap biodiversitas. Penguatan obyek dan daya tarik wisata. Pelestarian budaya dari segi lokal wisdom dan narasi budaya. Penguatan layanan meliputi kepusan, kenyamanan & keselamatan. Penguatan ekonomi dalam peningkatan manfaat bagi pengguna, pengusaha dan pemerintah",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Moringa Academy",
    deskripsi:
      "Moringa Oliefera (kelor) merupakan salah satu tanaman pusaka nusantara yang mmiliki segudang khasiat manfaat karena memiliki nutrisi super, sehingga diberi julukan super food. Moringa juga mendapat julukan Magical Tree karena tidak saja memiliki khasiat kesehatan dan nutrisi, namun juga mampu memberikan dampak ekologis serta mampu meningkatkan peluang ekonomi masyarakat. Namun kelor masih sering dianggap kurang bernilai bagi Sebagian besar masyarakat, sehingga edukasi dan penggalian manfaat dan potensinya perlu dikenali.",
    dampak:
      "Mengembangkan, memelihara dan melindungi hutan melalui pengembangan Kelor. Mendukung penanaman 5 juta Pohon Kelor di Indonesia. Bisnis Inklusif untuk mendukung komunitas yang terlibat termasuk perempuan, difabel. Menghasilkan pendapatan bagi masyarakat dengan menyediakan berbagai macam produk kelor yang mudah dibuat untuk dijual sebagai pendapatan. Meningkatkan kesehatan masyarakat, mengatasi isu stunting Menyediakan rute ke pasar lokal dan global untuk mengembangkan petani pedesaan",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Indonesia Sustainable Social Forestry Education Program (IS-FREE)",
    deskripsi:
      'Program ini terkait dengan potensi "perhutanan sosial" yang sangat besar untuk menetapkan hutan produksi seluas 120.000 km2 di dalam kawasan hutan kepada masyarakat lokal di Indonesia melalui pengelolaan hutan. Program ini menggunakan pendekatan partisipatif yang berorientasi pada tindakan, berdasarkan contoh-contoh praktis dari proyek-proyek Universitas Ghent. Penekanan akan ditempatkan pada bagaimana perhutanan sosial dan pendekatan fasilitatif dapat berkontribusi untuk mengubah praktik para pemangku kepentingan untuk menciptakan manfaat jangka panjang dan berskala besar bagi masyarakat yang tinggal di dalam dan di luar hutan.',
    dampak:
      "Pemasaran produk-produk hutan yang Berkelanjutan. Pengembangan pengetahuan dan kapasitas masyarakat perhutanan sosial serta pemangku kepentingan yang lain. Kapasitas peserta ditingkatkan untuk secara efektif menjangkau pembuat keputusan yang relevan dan pemangku kepentingan yang berpengaruh dalam masyarakat",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Waste Management",
    deskripsi:
      "Bukan hanya kota yang darurat sampah, Desa Desa di Indonesia pun memiliki issue yang sama. Manajemen sampah saat ini menjadi industri yang sangat besar di Indonesia. Hampir semua perusahaan membutuhkannya. Program ini mengajak peserta membuat Inovasi proses manajemen sampah di Desa. Dari mulai Edukasi, daur ulang, peluang bisnis.",
    dampak:
      "pelindungan habitat. mengurangi polusi sampah di tanah, air dan udara. Aksi nyata pemuda lokal. Sampah terpilah. Mengurangi timbunan sampah di TPU setempat. Recycle sampah ke pabrk pengolahan. Meningkatkan kemandirian warga Desa dengan merubah sampah menjadi sesuatu yang bernilai Mencegah musibah karna sampah",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Integrated Farming",
    deskripsi:
      "Sistem pertanian terpadu merupakan sistem integrasi pertanian yang menggabungkan beberapa sektor, seperti pertanian, peternakan dan sektor lain (perkebunan, perikanan, dan kehutanan) sebagai solusi untuk meningkatkan produktivitas lahan dan konservasi lingkungan. Sistem pertanian terpadu dapat menghasilkan empat produk (4F), yaitu bahan bakar (fuel), pupuk (fertilizer), pakan ternak (feed), dan makanan (food). Keuntungan yang diperoleh dari sistem pertanian terpadu adalah terjadinya peningkatan keluaran hasil (output) yang lebih bervariasi.",
    dampak:
      "Menjaga keseimbangan ekosistem di dalamnya sehingga aliran nutrisi dan energi berimbang. Meningkatkan produktivitas dan menjaga keberlanjutan produksi. Meminimalkan input dari luar. Meningkatkan pendapatan petani. Memberikan dampak positif di bidang ekonomi, sosial, dan ekologi.",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Solar Academy",
    deskripsi:
      "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
    dampak:
      "Memberikan pengetahuan dan informasi terkini kepada mahasiswa dalam memahami terkait energi terbarukan, khususnya sistem PLTS (Pembangkit Listrik Tenaga Surya).  Munculnya Inovasi energi terbarukan untuk masyarakat Indonesia. Munculnya wirausaha baru di bidang energi terbarukan",
    foto: ''
  })
  kursusTemplate.push({
    namaKursus: "Program Startup",
    deskripsi:
      "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
    dampak: "Munculnya usaha baru berbasis isu & potensi Desa.",
    foto: ''
  })

  const kursus = await prisma.kursus.createMany({
    data: [...kursusTemplate],
  })

  const modulTemplate: { id: number; namaModul: string }[] = []

  // kursus 1
  modulTemplate.push({ id: 1, namaModul: "Perubahan menuju sustainable mindset" })
  modulTemplate.push({ id: 2, namaModul: "5 Prinsip Ekologi" })
  modulTemplate.push({ id: 3, namaModul: "12 Elemen Dasar Green Mindset" })
  modulTemplate.push({ id: 4, namaModul: "Carbon Footprint" })
  modulTemplate.push({ id: 5, namaModul: "Rancangan Bangun Ecology" })
  modulTemplate.push({ id: 6, namaModul: "Sustainable Community" })
  modulTemplate.push({ id: 7, namaModul: "Sustainable Development Goals" })

  // kursus 2
  modulTemplate.push({ id: 8, namaModul: "Growth mindset" })
  modulTemplate.push({ id: 9, namaModul: "Design thinking" })
  modulTemplate.push({ id: 10, namaModul: "Lean Startup" })
  modulTemplate.push({ id: 11, namaModul: "Digital Startup" })

  // kursus 3
  modulTemplate.push({ id: 12, namaModul: "Mengenal Sustainable Startup" })
  modulTemplate.push({ id: 13, namaModul: "SDGs 12 Responsible Consumption and Production" })
  modulTemplate.push({ id: 14, namaModul: "Sustainable Business Idea and the process of growth" })
  modulTemplate.push({ id: 15, namaModul: "Sustainable impact and sustainable business mission" })
  modulTemplate.push({ id: 16, namaModul: "building profit model" })

  // kursus 4
  modulTemplate.push({ id: 17, namaModul: "Sustainability Leadership" })
  modulTemplate.push({ id: 18, namaModul: "Coral Reef Ecology" })
  modulTemplate.push({ id: 19, namaModul: "Human threats & Challenges" })
  modulTemplate.push({ id: 20, namaModul: "Integrating Sustainability in Coral Reef" })
  modulTemplate.push({ id: 21, namaModul: "Improving reef management by sustainable financing for Communities benefit" })

  // kursus 5
  modulTemplate.push({ id: 22, namaModul: "Ecoturism Introduction" })
  modulTemplate.push({ id: 23, namaModul: "Sustainable Tourism Product and Stategy Development" })
  modulTemplate.push({ id: 24, namaModul: "Management Destinasi" })
  modulTemplate.push({ id: 25, namaModul: "Service of Excellence" })
  modulTemplate.push({ id: 26, namaModul: "Penyusunan Jalur Interpretasi" })
  modulTemplate.push({ id: 27, namaModul: "Tourism and Travel Geography" })
  modulTemplate.push({ id: 28, namaModul: "Willingness to Pay Strategy" })

  // kursus 6
  modulTemplate.push({ id: 29, namaModul: "Pengenalan Kelor" })
  modulTemplate.push({ id: 30, namaModul: "Pemahaman Kelor Melalui sosio-kultural" })
  modulTemplate.push({ id: 31, namaModul: "Tinjauan Kelor dari sisi Ekologis" })
  modulTemplate.push({ id: 32, namaModul: "Sustainable Development Goal Through Moringa" })
  modulTemplate.push({ id: 33, namaModul: "Penciptaan Sirkulasi Ekonomi Kelor" })
  modulTemplate.push({ id: 34, namaModul: "Sejuta Manfaat kelor dari kandungan nutrisi" })
  modulTemplate.push({ id: 35, namaModul: "Pembudidayaan Kelor" })
  modulTemplate.push({ id: 36, namaModul: "Aneka Produk Turunan Olahan Kelor" })

  // kursus 7
  modulTemplate.push({ id: 37, namaModul: "Forest Management and Conservation Program (FMCP)" })
  modulTemplate.push({ id: 38, namaModul: "Non-timber Forest Products" })
  modulTemplate.push({ id: 39, namaModul: "Development Program (NTFP-DP)" })
  modulTemplate.push({ id: 40, namaModul: "Managing Forest Ecosystems" })
  modulTemplate.push({ id: 41, namaModul: "Sustainable Forest Value Chain Program (SFMP)" })
  modulTemplate.push({ id: 42, namaModul: "Practical Manual on Principles and Practices of Social Forestry" })

  // kursus 8
  modulTemplate.push({ id: 43, namaModul: "Perubahan Mindset" })
  modulTemplate.push({ id: 44, namaModul: "5 Prinsip Ekologi" })
  modulTemplate.push({ id: 45, namaModul: "Rancang Bangun Ekologi" })
  modulTemplate.push({ id: 46, namaModul: "Sustainable Community" })
  modulTemplate.push({ id: 47, namaModul: "Audit Sampah" })
  modulTemplate.push({ id: 48, namaModul: "Rethink- Refuse-Reuse-Reduce-Rot-Recycle" })
  modulTemplate.push({ id: 49, namaModul: "Ecoenzym" })
  modulTemplate.push({ id: 50, namaModul: "Kompos" })
  modulTemplate.push({ id: 51, namaModul: "Sabun natural & jelantah" })
  modulTemplate.push({ id: 52, namaModul: "Daur ulang metode heat press" })
  modulTemplate.push({ id: 53, namaModul: "Daur ulang metode braiding" })
  modulTemplate.push({ id: 54, namaModul: "Daur ulang metode melt & oven" })
  modulTemplate.push({ id: 55, namaModul: "Urban Farming" })
  modulTemplate.push({ id: 56, namaModul: "Seni Meramban" })

  // kursus 9
  modulTemplate.push({ id: 57, namaModul: "Analisis Masalah Sistem pertanian dan pengenalan Sistem pertanian terpadu" })
  modulTemplate.push({ id: 58, namaModul: "Pertanian terpadu dari segi model permaculture, agroforestry dan multiple cropping" })
  modulTemplate.push({ id: 59, namaModul: "Integrasi peternakan sebagai sumber fertilizer dan energi" })
  modulTemplate.push({ id: 60, namaModul: "Kajian system mina padi" })
  modulTemplate.push({ id: 61, namaModul: "Pengolahan produksi hasil panen" })
  modulTemplate.push({ id: 62, namaModul: "Teknik manajemen pemasaran hasil panen" })
  modulTemplate.push({ id: 63, namaModul: "Studi kasus dan analisis agrowisata berdasarkan komponen pertanian terpadu" })
  modulTemplate.push({ id: 64, namaModul: "Identifikasi pemenuhan target SDGs melalui system pertanian terpadu" })

  // kursus 10
  modulTemplate.push({ id: 65, namaModul: "Dasar - dasar solar PV" })
  modulTemplate.push({ id: 66, namaModul: "Pemahaman dasar rangkaian listrik" })
  modulTemplate.push({ id: 67, namaModul: "Perkenalan perangkat solar PV (Baterai, Investor, dan solar charge controller)" })
  modulTemplate.push({ id: 68, namaModul: "Kommisioning dan sistem monitoring pada solar PV" })
  modulTemplate.push({ id: 69, namaModul: "Maintenance & Inspection" })
  modulTemplate.push({ id: 70, namaModul: "Tinjauan solar PV dari analisis finansial" })

  // kursus 11
  modulTemplate.push({ id: 71, namaModul: "Mengenal Sustainable Start Up" })
  modulTemplate.push({ id: 72, namaModul: "SDGs 12 - Responsible" })
  modulTemplate.push({ id: 73, namaModul: "Consuption & Production" })
  modulTemplate.push({ id: 74, namaModul: "Sustainable Business Idea & the process of growth" })
  modulTemplate.push({ id: 75, namaModul: "Sustainable Impact & sustainable business Mission" })
  modulTemplate.push({ id: 76, namaModul: "Building Profit Model" })
  modulTemplate.push({ id: 77, namaModul: "Business Scale up & Innovation" })
  modulTemplate.push({ id: 78, namaModul: "Meassuring Sustainable Impact" })
  modulTemplate.push({ id: 79, namaModul: "Sustainable Business Model)" })
  modulTemplate.push({ id: 80, namaModul: "Growth Mindset" })
  modulTemplate.push({ id: 81, namaModul: "Design Thinking" })
  modulTemplate.push({ id: 82, namaModul: "Lean Canvas" })
  modulTemplate.push({ id: 83, namaModul: "Customer - Problem Fit" })
  modulTemplate.push({ id: 84, namaModul: "Problem - Sollution Fit" })
  modulTemplate.push({ id: 85, namaModul: "Prototype & MVP" })
  modulTemplate.push({ id: 86, namaModul: "English for Business" })
  modulTemplate.push({ id: 87, namaModul: "Product Management" })
  modulTemplate.push({ id: 88, namaModul: "Digital Marketing" })

  const moduls: number = 88
  for (let i = 0; i < moduls; i++) {
    if (i < 7) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 1 }
          }
        }
      })
    }
    else if (i < 11) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 2 }
          }
        }
      })
    }
    else if (i < 16) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 3 }
          }
        }
      })
    }
    else if (i < 21) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 4 }
          }
        }
      })
    }
    else if (i < 28) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 5 }
          }
        }
      })
    }
    else if (i < 36) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 6 }
          }
        }
      })
    }
    else if (i < 42) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 7 }
          }
        }
      })
    }
    else if (i < 56) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 8 }
          }
        }
      })
    }
    else if (i < 64) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 9 }
          }
        }
      })
    }
    else if (i < 70) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 10 }
          }
        }
      })
    }
    else if (i < 88) {
      await prisma.modul.create({
        data: {
          namaModul: modulTemplate[i].namaModul,
          kursus: {
            connect: { id: 11 }
          }
        }
      })
    }
  }
}

main()
  .catch((e: Error) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    // Disconnect Prisma Client
    await prisma.$disconnect();
  });
