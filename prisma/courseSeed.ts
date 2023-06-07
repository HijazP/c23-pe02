import { PrismaClient } from "@prisma/client";

const prisma = new PrismaClient();

async function main() {
  const kursusTemplate: { id: number; namaKursus: string; deskripsi: string; dampak: string }[] = [];

  kursusTemplate.push({
    id: 1,
    namaKursus: "Fundamental Course(1)",
    deskripsi: "Fundamental Attitude toward sustainability",
    dampak:
      "mengubah paradigma mengenai sustainability; Memahami Prinsip Ekologi; Memahami Elemen dasar Green Mindset; Memahami Konsep Carbon Footprint; Membuat Rancang Bangun Ecology; Memahami Sustainable Community; Memahami Poin-poin SGGs",
  });
  kursusTemplate.push({
    id: 2,
    namaKursus: "Fundamental Course(2)",
    deskripsi: "Innovation method as problem solving",
    dampak: "Memahami konsep Growth Mindset; Memahami konsep Design Thinking; Memahami Konsep Lean Startup; Memahami Digital Startup",
  });
  kursusTemplate.push({
    id: 3,
    namaKursus: "Fundamental Course (3)",
    deskripsi: "The way to build sustainable business",
    dampak: "Mengenal Sustainable Startup; Memahami SDGs poin ke-12; Mengembangkan Sustainable Business Idea and Process of Growth; Membuat Profit Model untuk bisnis",
  });
  kursusTemplate.push({
    id: 4,
    namaKursus: "Indonesia Sustainability Coral Reef University Network (ISCORE)",
    deskripsi:
      "Terumbu karang menutupi kurang dari 1% wilayah lautan, tetapi mereka secara langsung mendukung jutaan orang dengan menyediakan makanan, pekerjaan, dan sumber daya lainnya. Bahkan lebih banyak orang yang mendapat manfaat dari terumbu karang secara tidak langsung; ekosistem ini membantu memberi makan 1 miliar orang di Asia saja. Populasi manusia hampir 7 miliar orang, dan kemungkinan akan tumbuh menjadi 9 miliar pada tahun 2040. Kita membutuhkan terumbu karang (dan ekosistem lainnya) untuk memasok lebih banyak sumber daya untuk mendukung jumlah kita yang terus bertambah, tetapi mereka semakin terancam dengan kehancuran.",
    dampak: "pelindungan habibat; mengurangi polusi; mengurangi dampak erosi; promosi pariwisata; meningkatkan komunitas lokal; pemeliharaan agen perubahan berkelanjutan dan perlindungan terumbu karang",
  });
  kursusTemplate.push({
    id: 5,
    namaKursus: "Ecotourism",
    deskripsi:
      "Seiring dengan pelonggaran perjalanan dan stabilitas dalam kebijakan perjalanan, pertumbuhan dalam kedatangan internasional sejak Januari 2022 meningkat sebesar 350 persen dibandingkan dengan tahun lalu. Indonesia memiliki banyak lokasi wisata berbasis ecotourism, yang mampu meningkakan perekonomian dan lapangan pekerjaan. Namun kendala SDM membuat banyak lokasi wisata berbasis ecotourism belum bisa berakselerasi melakukan percepatan yg dibutuhkan.",
    dampak:
      "Pelestarian ekosistem terhadap biodiversitas; Penguatan obyek dan daya tarik wisata; Pelestarian budaya dari segi lokal wisdom dan narasi budaya; Penguatan layanan meliputi kepusan, kenyamanan & keselamatan; Penguatan ekonomi dalam peningkatan manfaat bagi pengguna, pengusaha dan pemerintah",
  });
  kursusTemplate.push({
    id: 6,
    namaKursus: "Moringa Academy",
    deskripsi:
      "Moringa Oliefera (kelor) merupakan salah satu tanaman pusaka nusantara yang mmiliki segudang khasiat manfaat karena memiliki nutrisi super, sehingga diberi julukan super food. Moringa juga mendapat julukan Magical Tree karena tidak saja memiliki khasiat kesehatan dan nutrisi, namun juga mampu memberikan dampak ekologis serta mampu meningkatkan peluang ekonomi masyarakat. Namun kelor masih sering dianggap kurang bernilai bagi Sebagian besar masyarakat, sehingga edukasi dan penggalian manfaat dan potensinya perlu dikenali.",
    dampak:
      "Mengembangkan, memelihara dan melindungi hutan melalui pengembangan Kelor; Mendukung penanaman 5 juta Pohon Kelor di Indonesia; Bisnis Inklusif untuk mendukung komunitas yang terlibat termasuk perempuan, difabel; Menghasilkan pendapatan bagi masyarakat dengan menyediakan berbagai macam produk kelor yang mudah dibuat untuk dijual sebagai pendapatan; Meningkatkan kesehatan masyarakat, mengatasi isu stunting Menyediakan rute ke pasar lokal dan global untuk mengembangkan petani pedesaan",
  });
  kursusTemplate.push({
    id: 7,
    namaKursus: "Indonesia Sustainable Social Forestry Education Program (IS-FREE)",
    deskripsi:
      'Program ini terkait dengan potensi "perhutanan sosial" yang sangat besar untuk menetapkan hutan produksi seluas 120.000 km2 di dalam kawasan hutan kepada masyarakat lokal di Indonesia melalui pengelolaan hutan. Program ini menggunakan pendekatan partisipatif yang berorientasi pada tindakan, berdasarkan contoh-contoh praktis dari proyek-proyek Universitas Ghent. Penekanan akan ditempatkan pada bagaimana perhutanan sosial dan pendekatan fasilitatif dapat berkontribusi untuk mengubah praktik para pemangku kepentingan untuk menciptakan manfaat jangka panjang dan berskala besar bagi masyarakat yang tinggal di dalam dan di luar hutan.',
    dampak:
      "Pemasaran produk-produk hutan yang Berkelanjutan; Pengembangan pengetahuan dan kapasitas masyarakat perhutanan sosial serta pemangku kepentingan yang lain; Kapasitas peserta ditingkatkan untuk secara efektif menjangkau pembuat keputusan yang relevan dan pemangku kepentingan yang berpengaruh dalam masyarakat",
  });
  kursusTemplate.push({
    id: 8,
    namaKursus: "Waste Management",
    deskripsi:
      "Bukan hanya kota yang darurat sampah, Desa Desa di Indonesia pun memiliki issue yang sama. Manajemen sampah saat ini menjadi industri yang sangat besar di Indonesia. Hampir semua perusahaan membutuhkannya. Program ini mengajak peserta membuat Inovasi proses manajemen sampah di Desa. Dari mulai Edukasi, daur ulang, peluang bisnis.",
    dampak:
      "pelindungan habitat; mengurangi polusi sampah di tanah, air dan udara; Aksi nyata pemuda lokal; Sampah terpilah;  Mengurangi timbunan sampah di TPU setempat; Recycle sampah ke pabrk pengolahan; Meningkatkan kemandirian warga Desa dengan merubah sampah menjadi sesuatu yg bernilai; Mencegah musibah karna sampah",
  });
  kursusTemplate.push({
    id: 9,
    namaKursus: "Integrated Farming",
    deskripsi:
      "Sistem pertanian terpadu merupakan sistem integrasi pertanian yang menggabungkan beberapa sektor, seperti pertanian, peternakan dan sektor lain (perkebunan, perikanan, dan kehutanan) sebagai solusi untuk meningkatkan produktivitas lahan dan konservasi lingkungan. Sistem pertanian terpadu dapat menghasilkan empat produk (4F), yaitu bahan bakar (fuel), pupuk (fertilizer), pakan ternak (feed), dan makanan (food). Keuntungan yang diperoleh dari sistem pertanian terpadu adalah terjadinya peningkatan keluaran hasil (output) yang lebih bervariasi.",
    dampak:
      "Menjaga keseimbangan ekosistem di dalamnya sehingga aliran nutrisi dan energi berimbang; Meningkatkan produktivitas dan menjaga keberlanjutan produksi; Meminimalkan input dari luar; Meningkatkan pendapatan petani; Memberikan dampak positif di bidang ekonomi, sosial, dan ekologi.",
  });
  kursusTemplate.push({
    id: 10,
    namaKursus: "Solar Academy",
    deskripsi:
      "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
    dampak:
      "Memberikan pengetahuan dan informasi terkini kepada mahasiswa dalam memahami terkait energi terbarukan, khususnya sistem PLTS (Pembangkit Listrik Tenaga Surya); Munculnya lulusan siap kerja dalam industri energi terbarukan; Munculnya Inovasi energi terbarukan untuk masyarakat Indonesia; Munculnya wirausaha baru di bidang energi terbarukan",
  });
  kursusTemplate.push({
    id: 11,
    namaKursus: "Program Startup",
    deskripsi:
      "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
    dampak: "Munculnya usaha baru berbasis isu & potensi Desa; Mendukung pemenuhan SDGs Desa; Mental wirausaha & rasa percaya diri peserta meningkat; Alternatif pengembangan desa melalui Open Innovation",
  });

  const kursus = await prisma.kursus.createMany({
    data: [...kursusTemplate],
  });
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
