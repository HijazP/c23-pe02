var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import { PrismaClient } from "@prisma/client";
const prisma = new PrismaClient();
function main() {
    var _a, _b, _c, _d, _e, _f, _g, _h, _j, _k, _l;
    return __awaiter(this, void 0, void 0, function* () {
        const kursusTemplate = [];
        kursusTemplate.push({
            namaKursus: "Fundamental Course(1)",
            deskripsi: "Fundamental Attitude toward sustainability",
            dampak: "mengubah paradigma mengenai sustainability. Memahami Prinsip Ekologi. Memahami Elemen dasar Green Mindset. Memahami Konsep Carbon Footprint. Membuat Rancang Bangun Ecology. Memahami Sustainable Community. Memahami Poin-poin SGGs",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Fundamental Course(2)",
            deskripsi: "Innovation method as problem solving",
            dampak: "Memahami konsep Growth Mindset. Memahami konsep Design Thinking. Memahami Konsep Lean Startup. Memahami Digital Startup",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Fundamental Course (3)",
            deskripsi: "The way to build sustainable business",
            dampak: "Mengenal Sustainable Startup. Memahami SDGs poin ke-12. Mengembangkan Sustainable Business Idea and Process of Growth. Membuat Profit Model untuk bisnis",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Indonesia Sustainability Coral Reef University Network (ISCORE)",
            deskripsi: "Terumbu karang menutupi kurang dari 1% wilayah lautan, tetapi mereka secara langsung mendukung jutaan orang dengan menyediakan makanan, pekerjaan, dan sumber daya lainnya. Bahkan lebih banyak orang yang mendapat manfaat dari terumbu karang secara tidak langsung; ekosistem ini membantu memberi makan 1 miliar orang di Asia saja. Populasi manusia hampir 7 miliar orang, dan kemungkinan akan tumbuh menjadi 9 miliar pada tahun 2040. Kita membutuhkan terumbu karang (dan ekosistem lainnya) untuk memasok lebih banyak sumber daya untuk mendukung jumlah kita yang terus bertambah, tetapi mereka semakin terancam dengan kehancuran.",
            dampak: "pelindungan habibat. mengurangi polusi. mengurangi dampak erosi. Promosi pariwisata. Meningkatkan komunitas lokal. Pemeliharaan agen perubahan berkelanjutan dan perlindungan terumbu karang",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Ecotourism",
            deskripsi: "Seiring dengan pelonggaran perjalanan dan stabilitas dalam kebijakan perjalanan, pertumbuhan dalam kedatangan internasional sejak Januari 2022 meningkat sebesar 350 persen dibandingkan dengan tahun lalu. Indonesia memiliki banyak lokasi wisata berbasis ecotourism, yang mampu meningkakan perekonomian dan lapangan pekerjaan. Namun kendala SDM membuat banyak lokasi wisata berbasis ecotourism belum bisa berakselerasi melakukan percepatan yg dibutuhkan.",
            dampak: "Pelestarian ekosistem terhadap biodiversitas. Penguatan obyek dan daya tarik wisata. Pelestarian budaya dari segi lokal wisdom dan narasi budaya. Penguatan layanan meliputi kepusan, kenyamanan & keselamatan. Penguatan ekonomi dalam peningkatan manfaat bagi pengguna, pengusaha dan pemerintah",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Moringa Academy",
            deskripsi: "Moringa Oliefera (kelor) merupakan salah satu tanaman pusaka nusantara yang mmiliki segudang khasiat manfaat karena memiliki nutrisi super, sehingga diberi julukan super food. Moringa juga mendapat julukan Magical Tree karena tidak saja memiliki khasiat kesehatan dan nutrisi, namun juga mampu memberikan dampak ekologis serta mampu meningkatkan peluang ekonomi masyarakat. Namun kelor masih sering dianggap kurang bernilai bagi Sebagian besar masyarakat, sehingga edukasi dan penggalian manfaat dan potensinya perlu dikenali.",
            dampak: "Mengembangkan, memelihara dan melindungi hutan melalui pengembangan Kelor. Mendukung penanaman 5 juta Pohon Kelor di Indonesia. Bisnis Inklusif untuk mendukung komunitas yang terlibat termasuk perempuan, difabel. Menghasilkan pendapatan bagi masyarakat dengan menyediakan berbagai macam produk kelor yang mudah dibuat untuk dijual sebagai pendapatan. Meningkatkan kesehatan masyarakat, mengatasi isu stunting Menyediakan rute ke pasar lokal dan global untuk mengembangkan petani pedesaan",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Indonesia Sustainable Social Forestry Education Program (IS-FREE)",
            deskripsi: 'Program ini terkait dengan potensi "perhutanan sosial" yang sangat besar untuk menetapkan hutan produksi seluas 120.000 km2 di dalam kawasan hutan kepada masyarakat lokal di Indonesia melalui pengelolaan hutan. Program ini menggunakan pendekatan partisipatif yang berorientasi pada tindakan, berdasarkan contoh-contoh praktis dari proyek-proyek Universitas Ghent. Penekanan akan ditempatkan pada bagaimana perhutanan sosial dan pendekatan fasilitatif dapat berkontribusi untuk mengubah praktik para pemangku kepentingan untuk menciptakan manfaat jangka panjang dan berskala besar bagi masyarakat yang tinggal di dalam dan di luar hutan.',
            dampak: "Pemasaran produk-produk hutan yang Berkelanjutan. Pengembangan pengetahuan dan kapasitas masyarakat perhutanan sosial serta pemangku kepentingan yang lain. Kapasitas peserta ditingkatkan untuk secara efektif menjangkau pembuat keputusan yang relevan dan pemangku kepentingan yang berpengaruh dalam masyarakat",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Waste Management",
            deskripsi: "Bukan hanya kota yang darurat sampah, Desa Desa di Indonesia pun memiliki issue yang sama. Manajemen sampah saat ini menjadi industri yang sangat besar di Indonesia. Hampir semua perusahaan membutuhkannya. Program ini mengajak peserta membuat Inovasi proses manajemen sampah di Desa. Dari mulai Edukasi, daur ulang, peluang bisnis.",
            dampak: "pelindungan habitat. mengurangi polusi sampah di tanah, air dan udara. Aksi nyata pemuda lokal. Sampah terpilah. Mengurangi timbunan sampah di TPU setempat. Recycle sampah ke pabrk pengolahan. Meningkatkan kemandirian warga Desa dengan merubah sampah menjadi sesuatu yang bernilai Mencegah musibah karna sampah",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Integrated Farming",
            deskripsi: "Sistem pertanian terpadu merupakan sistem integrasi pertanian yang menggabungkan beberapa sektor, seperti pertanian, peternakan dan sektor lain (perkebunan, perikanan, dan kehutanan) sebagai solusi untuk meningkatkan produktivitas lahan dan konservasi lingkungan. Sistem pertanian terpadu dapat menghasilkan empat produk (4F), yaitu bahan bakar (fuel), pupuk (fertilizer), pakan ternak (feed), dan makanan (food). Keuntungan yang diperoleh dari sistem pertanian terpadu adalah terjadinya peningkatan keluaran hasil (output) yang lebih bervariasi.",
            dampak: "Menjaga keseimbangan ekosistem di dalamnya sehingga aliran nutrisi dan energi berimbang. Meningkatkan produktivitas dan menjaga keberlanjutan produksi. Meminimalkan input dari luar. Meningkatkan pendapatan petani. Memberikan dampak positif di bidang ekonomi, sosial, dan ekologi.",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Solar Academy",
            deskripsi: "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
            dampak: "Memberikan pengetahuan dan informasi terkini kepada mahasiswa dalam memahami terkait energi terbarukan, khususnya sistem PLTS (Pembangkit Listrik Tenaga Surya).  Munculnya Inovasi energi terbarukan untuk masyarakat Indonesia. Munculnya wirausaha baru di bidang energi terbarukan",
            foto: ''
        });
        kursusTemplate.push({
            namaKursus: "Program Startup",
            deskripsi: "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
            dampak: "Munculnya usaha baru berbasis isu & potensi Desa.",
            foto: ''
        });
        yield prisma.kursus.createMany({
            data: [...kursusTemplate],
        });
        const modulTemplate = [
            { namaModul: "Perubahan menuju sustainable mindset" },
            { namaModul: "5 Prinsip Ekologi" },
            { namaModul: "12 Elemen Dasar Green Mindset" },
            { namaModul: "Carbon Footprint" },
            { namaModul: "Rancangan Bangun Ecology" },
            { namaModul: "Sustainable Community" },
            { namaModul: "Sustainable Development Goals" },
            { namaModul: "Growth mindset" },
            { namaModul: "Design thinking" },
            { namaModul: "Lean Startup" },
            { namaModul: "Digital Startup" },
            { namaModul: "Mengenal Sustainable Startup" },
            { namaModul: "SDGs 12 Responsible Consumption and Production" },
            { namaModul: "Sustainable Business Idea and the process of growth" },
            { namaModul: "Sustainable impact and sustainable business mission" },
            { namaModul: "Building profit model" },
            { namaModul: "Sustainability Leadership" },
            { namaModul: "Coral Reef Ecology" },
            { namaModul: "Human threats & Challenges" },
            { namaModul: "Integrating Sustainability in Coral Reef" },
            { namaModul: "Improving reef management by sustainable financing for Communities benefit" },
            { namaModul: "Ecoturism Introduction" },
            { namaModul: "Sustainable Tourism Product and Stategy Development" },
            { namaModul: "Management Destinasi" },
            { namaModul: "Service of Excellence" },
            { namaModul: "Penyusunan Jalur Interpretasi" },
            { namaModul: "Tourism and Travel Geography" },
            { namaModul: "Willingness to Pay Strategy" },
            { namaModul: "Pengenalan kelor" },
            { namaModul: "Pemahaman kelor melalui sosio-kultural" },
            { namaModul: "Tinjauan kelor dari sisi ekologis" },
            { namaModul: "Sustainable Development Goal Through Moringa" },
            { namaModul: "Penciptaan sirkulasi ekonomi kelor" },
            { namaModul: "Sejuta manfaat kelor dari kandungan nutrisi" },
            { namaModul: "Pembudidayaan kelor" },
            { namaModul: "Pengolahan kelor" },
            { namaModul: "Pengenalan Forest Management and Conservation Program (FMCP)" },
            { namaModul: "Non-timber Forest Products" },
            { namaModul: "Development Program (NTFP-DP)" },
            { namaModul: "Managing Forest Ecosystems" },
            { namaModul: "Sustainable Forest Value Chain Program (SFMP)" },
            { namaModul: "Practical Manual on Principles and Practices of Social Forestry" },
            { namaModul: "Perubahan Mindset" },
            { namaModul: "5 Prinsip Ekologi" },
            { namaModul: "Rancang Bangun Ekologi" },
            { namaModul: "Sustainable Community" },
            { namaModul: "Audit Sampah" },
            { namaModul: "Rethink- Refuse-Reuse-Reduce-Rot-Recycle" },
            { namaModul: "Ecoenzym" },
            { namaModul: "Kompos" },
            { namaModul: "Sabun natural & jelantah" },
            { namaModul: "Daur ulang metode heat press" },
            { namaModul: "Daur ulang metode braiding" },
            { namaModul: "Daur ulang metode melt & oven" },
            { namaModul: "Urban Farming" },
            { namaModul: "Seni Meramban" },
            { namaModul: "Analisis Masalah Sistem pertanian dan pengenalan Sistem pertanian terpadu" },
            { namaModul: "Pertanian terpadu dari segi model permaculture, agroforestry dan multiple cropping" },
            { namaModul: "Integrasi peternakan sebagai sumber fertilizer dan energi" },
            { namaModul: "Kajian system mina padi" },
            { namaModul: "Pengolahan produksi hasil panen" },
            { namaModul: "Teknik manajemen pemasaran hasil panen" },
            { namaModul: "Studi kasus dan analisis agrowisata berdasarkan komponen pertanian terpadu" },
            { namaModul: "Identifikasi pemenuhan target SDGs melalui system pertanian terpadu" },
            { namaModul: "Dasar - dasar solar PV" },
            { namaModul: "Pemahaman dasar rangkaian listrik" },
            { namaModul: "Perkenalan perangkat solar PV (Baterai, Investor, dan solar charge controller)" },
            { namaModul: "Kommisioning dan sistem monitoring pada solar PV" },
            { namaModul: "Maintenance & Inspection" },
            { namaModul: "Tinjauan solar PV dari analisis finansial" },
            { namaModul: "Mengenal Sustainable Start Up" },
            { namaModul: "SDGs 12 - Responsible" },
            { namaModul: "Consuption & Production" },
            { namaModul: "Sustainable Business Idea & the process of growth" },
            { namaModul: "Sustainable Impact & sustainable business Mission" },
            { namaModul: "Building Profit Model" },
            { namaModul: "Business Scale up & Innovation" },
            { namaModul: "Meassuring Sustainable Impact" },
            { namaModul: "Sustainable Business Model" },
            { namaModul: "Growth Mindset" },
            { namaModul: "Design Thinking" },
            { namaModul: "Lean Canvas" },
            { namaModul: "Customer - Problem Fit" },
            { namaModul: "Problem - Solution Fit" },
            { namaModul: "Prototype & MVP" },
            { namaModul: "English for Business" },
            { namaModul: "Product Management" },
            { namaModul: "Digital Marketing" },
        ];
        const moduls = 88;
        try {
            for (let i = 0; i < moduls; i++) {
                if (i < 7) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_a = modulTemplate[i]) === null || _a === void 0 ? void 0 : _a.namaModul,
                            kursus: {
                                connect: { id: 1 }
                            }
                        }
                    });
                }
                else if (i < 11) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_b = modulTemplate[i]) === null || _b === void 0 ? void 0 : _b.namaModul,
                            kursus: {
                                connect: { id: 2 }
                            }
                        }
                    });
                }
                else if (i < 16) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_c = modulTemplate[i]) === null || _c === void 0 ? void 0 : _c.namaModul,
                            kursus: {
                                connect: { id: 3 }
                            }
                        }
                    });
                }
                else if (i < 21) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_d = modulTemplate[i]) === null || _d === void 0 ? void 0 : _d.namaModul,
                            kursus: {
                                connect: { id: 4 }
                            }
                        }
                    });
                }
                else if (i < 28) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_e = modulTemplate[i]) === null || _e === void 0 ? void 0 : _e.namaModul,
                            kursus: {
                                connect: { id: 5 }
                            }
                        }
                    });
                }
                else if (i < 36) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_f = modulTemplate[i]) === null || _f === void 0 ? void 0 : _f.namaModul,
                            kursus: {
                                connect: { id: 6 }
                            }
                        }
                    });
                }
                else if (i < 42) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_g = modulTemplate[i]) === null || _g === void 0 ? void 0 : _g.namaModul,
                            kursus: {
                                connect: { id: 7 }
                            }
                        }
                    });
                }
                else if (i < 56) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_h = modulTemplate[i]) === null || _h === void 0 ? void 0 : _h.namaModul,
                            kursus: {
                                connect: { id: 8 }
                            }
                        }
                    });
                }
                else if (i < 64) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_j = modulTemplate[i]) === null || _j === void 0 ? void 0 : _j.namaModul,
                            kursus: {
                                connect: { id: 9 }
                            }
                        }
                    });
                }
                else if (i < 70) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_k = modulTemplate[i]) === null || _k === void 0 ? void 0 : _k.namaModul,
                            kursus: {
                                connect: { id: 10 }
                            }
                        }
                    });
                }
                else if (i < 88) {
                    yield prisma.modul.create({
                        data: {
                            namaModul: (_l = modulTemplate[i]) === null || _l === void 0 ? void 0 : _l.namaModul,
                            kursus: {
                                connect: { id: 11 }
                            }
                        }
                    });
                }
            }
        }
        catch (error) {
            console.log(error);
        }
    });
}
main()
    .catch((e) => {
    console.error(e);
    process.exit(1);
})
    .finally(() => __awaiter(void 0, void 0, void 0, function* () {
    yield prisma.$disconnect();
}));
//# sourceMappingURL=courseModulSeed.js.map