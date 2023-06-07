import { PrismaClient } from "@prisma/client";

const prisma = new PrismaClient();

async function main() {
  const modulTemplate: { id: number; namaModul: string }[] = [];

  // kursus 1
  modulTemplate.push({ id: 1, namaModul: "Perubahan menuju sustainable mindset" });
  modulTemplate.push({ id: 2, namaModul: "5 Prinsip Ekologi" });
  modulTemplate.push({ id: 3, namaModul: "12 Elemen Dasar Green Mindset" });
  modulTemplate.push({ id: 4, namaModul: "Carbon Footprint" });
  modulTemplate.push({ id: 5, namaModul: "Rancangan Bangun Ecology" });
  modulTemplate.push({ id: 6, namaModul: "Sustainable Community" });
  modulTemplate.push({ id: 7, namaModul: "Sustainable Development Goals" });

  // kursus 2
  modulTemplate.push({ id: 8, namaModul: "Growth mindset" });
  modulTemplate.push({ id: 9, namaModul: "Design thinking" });
  modulTemplate.push({ id: 10, namaModul: "Lean Startup" });
  modulTemplate.push({ id: 11, namaModul: "Digital Startup" });

  // kursus 3
  modulTemplate.push({ id: 12, namaModul: "Mengenal Sustainable Startup" });
  modulTemplate.push({ id: 13, namaModul: "SDGs 12 Responsible Consumption and Production" });
  modulTemplate.push({ id: 14, namaModul: "Sustainable Business Idea and the process of growth" });
  modulTemplate.push({ id: 15, namaModul: "Sustainable impact and sustainable business mission" });
  modulTemplate.push({ id: 16, namaModul: "building profit model" });

  // kursus 4
  modulTemplate.push({ id: 17, namaModul: "Sustainability Leadership" });
  modulTemplate.push({ id: 18, namaModul: "Coral Reef Ecology" });
  modulTemplate.push({ id: 19, namaModul: "Human threats & Challenges" });
  modulTemplate.push({ id: 20, namaModul: "Integrating Sustainability in Coral Reef" });
  modulTemplate.push({ id: 21, namaModul: "Improving reef management by sustainable financing for Communities benefit" });

  // kursus 5
  modulTemplate.push({ id: 22, namaModul: "Ecoturism Introduction" });
  modulTemplate.push({ id: 23, namaModul: "Sustainable Tourism Product and Stategy Development" });
  modulTemplate.push({ id: 24, namaModul: "Management Destinasi" });
  modulTemplate.push({ id: 25, namaModul: "Service of Excellence" });
  modulTemplate.push({ id: 26, namaModul: "Penyusunan Jalur Interpretasi" });
  modulTemplate.push({ id: 27, namaModul: "Tourism and Travel Geography" });
  modulTemplate.push({ id: 28, namaModul: "Willingness to Pay Strategy" });

  // kursus 6
  modulTemplate.push({ id: 29, namaModul: "Pengenalan Kelor" });
  modulTemplate.push({ id: 30, namaModul: "Pemahaman Kelor Melalui sosio-kultural" });
  modulTemplate.push({ id: 31, namaModul: "Tinjauan Kelor dari sisi Ekologis" });
  modulTemplate.push({ id: 32, namaModul: "Sustainable Development Goal Through Moringa" });
  modulTemplate.push({ id: 33, namaModul: "Penciptaan Sirkulasi Ekonomi Kelor" });
  modulTemplate.push({ id: 34, namaModul: "Sejuta Manfaat kelor dari kandungan nutrisi" });
  modulTemplate.push({ id: 35, namaModul: "Pembudidayaan Kelor" });
  modulTemplate.push({ id: 36, namaModul: "Aneka Produk Turunan Olahan Kelor" });

  // kursus 7
  modulTemplate.push({ id: 37, namaModul: "Forest Management and Conservation Program (FMCP)" });
  modulTemplate.push({ id: 38, namaModul: "Non-timber Forest Products" });
  modulTemplate.push({ id: 39, namaModul: "Development Program (NTFP-DP)" });
  modulTemplate.push({ id: 40, namaModul: "Managing Forest Ecosystems" });
  modulTemplate.push({ id: 41, namaModul: "Sustainable Forest Value Chain Program (SFMP)" });
  modulTemplate.push({ id: 42, namaModul: "Practical Manual on Principles and Practices of Social Forestry" });

  // kursus 8
  modulTemplate.push({ id: 43, namaModul: "Eco Literasi (Perubahan Mindset" });
  modulTemplate.push({ id: 44, namaModul: "5 Prinsip Ekologi" });
  modulTemplate.push({ id: 45, namaModul: "Rancang Bangun Ekologi" });
  modulTemplate.push({ id: 46, namaModul: "Sustainable Community)" });
  modulTemplate.push({ id: 47, namaModul: "Zero Waste (Audit Sampah" });
  modulTemplate.push({ id: 48, namaModul: "Rethink- Refuse-Reuse-Reduce-Rot-Recycle)" });
  modulTemplate.push({ id: 49, namaModul: "Kelola Sampah Organik (Ecoenzym" });
  modulTemplate.push({ id: 50, namaModul: "Kompos" });
  modulTemplate.push({ id: 51, namaModul: "Sabun natural & jelantah)" });
  modulTemplate.push({ id: 52, namaModul: "Daur ulang Plastik (Daur ulang metode heat press" });
  modulTemplate.push({ id: 53, namaModul: "Daur ulang metode braiding" });
  modulTemplate.push({ id: 54, namaModul: "Daur ulang metode melt & oven)" });
  modulTemplate.push({ id: 55, namaModul: "Kemandirian Pangan (Urban Farming" });
  modulTemplate.push({ id: 56, namaModul: "Seni Meramban)" });

  // kursus 9
  modulTemplate.push({ id: 57, namaModul: "Analisis Masalah Sistem pertanian dan pengenalan Sistem pertanian terpadu" });
  modulTemplate.push({ id: 58, namaModul: "Pertanian terpadu dari segi model permaculture, agroforestry dan multiple cropping" });
  modulTemplate.push({ id: 59, namaModul: "Integrasi peternakan sebagai sumber fertilizer dan energi" });
  modulTemplate.push({ id: 60, namaModul: "Kajian system mina padi" });
  modulTemplate.push({ id: 61, namaModul: "Pengolahan produksi hasil panen" });
  modulTemplate.push({ id: 62, namaModul: "Teknik manajemen pemasaran hasil panen" });
  modulTemplate.push({ id: 63, namaModul: "Studi kasus dan analisis agrowisata berdasarkan komponen pertanian terpadu" });
  modulTemplate.push({ id: 64, namaModul: "Identifikasi pemenuhan target SDGs melalui system pertanian terpadu" });

  // kursus 10
  modulTemplate.push({ id: 67, namaModul: "Dasar - dasar solar PV" });
  modulTemplate.push({ id: 68, namaModul: "Pemahaman dasar rangkaian listrik" });
  modulTemplate.push({ id: 69, namaModul: "Perkenalan perangkat solar PV (Baterai, Investor, dan solar charge controller)" });
  modulTemplate.push({ id: 70, namaModul: "Kommisioning dan sistem monitoring pada solar PV" });
  modulTemplate.push({ id: 71, namaModul: "Maintenance & Inspection" });
  modulTemplate.push({ id: 72, namaModul: "Tinjauan solar PV dari analisis finansial" });

  // kursus 11
  modulTemplate.push({ id: 73, namaModul: "Sustainable Startup (Mengenal Sustainable Start Up" });
  modulTemplate.push({ id: 74, namaModul: "SDGs 12 - Responsible" });
  modulTemplate.push({ id: 75, namaModul: "Consuption & Production" });
  modulTemplate.push({ id: 76, namaModul: "Sustainable Business Idea & the process of growth" });
  modulTemplate.push({ id: 77, namaModul: "Sustainable Impact & sustainable business Mission" });
  modulTemplate.push({ id: 78, namaModul: "Building Profit Model" });
  modulTemplate.push({ id: 79, namaModul: "Business Scale up & Innovation" });
  modulTemplate.push({ id: 80, namaModul: "Meassuring Sustainable Impact" });
  modulTemplate.push({ id: 81, namaModul: "Sustainable Business Model)" });
  modulTemplate.push({ id: 82, namaModul: "Innovation Management (Growth Mindset" });
  modulTemplate.push({ id: 83, namaModul: "Design Thinking" });
  modulTemplate.push({ id: 84, namaModul: "Lean Canvas" });
  modulTemplate.push({ id: 85, namaModul: "Customer - Problem Fit" });
  modulTemplate.push({ id: 86, namaModul: "Problem - Sollution Fit" });
  modulTemplate.push({ id: 87, namaModul: "Prototype & MVP" });
  modulTemplate.push({ id: 88, namaModul: "English for Business" });
  modulTemplate.push({ id: 89, namaModul: "Product Management" });
  modulTemplate.push({ id: 90, namaModul: "Digital Marketing)" });
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
