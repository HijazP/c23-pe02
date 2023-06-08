import Hapi from '@hapi/hapi'
import bcrypt from 'bcrypt'
import Jwt from '@hapi/jwt'

async function registerUser(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const { namaLengkap, email, password } = request.payload as any

    try {
        const checkUser = await prisma.pengguna.findUnique({
            where: {
                email: email
            }
        })

        if (checkUser) {
            return h.response({
                statusCode: 401,
                message: 'Email sudah terdaftar'
            }).code(401)
        }

        const user = await prisma.pengguna.create({
            data: {
                namaLengkap: namaLengkap,
                email: email,
                password: bcrypt.hashSync(password, 10),
                foto: '',
            },
            select: {
                id: true,
                namaLengkap: true,
                email: true,
            },
        })
        return h.response({
            statusCode: 201,
            message: 'Pengguna berhasil ditambahkan',
            user
        }).code(201)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function loginUser(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { email, password } = request.payload as any
    const { prisma } = request.server.app

    try {
        const user = await prisma.pengguna.findUnique({
            where: {
                email,
            },
        })

        const decoded = bcrypt.compareSync(password, user? user.password : '')

        if (!decoded || !user) {
            return h.response({
                statusCode: 401,
                message: 'Email atau password salah'
            }).code(401)
        }

        const token = Jwt.token.generate({ userId: user.id }, { key: process.env.JWT_SECRET as string, algorithm: 'HS256'})

        return h.response({
            statusCode: 200,
            data: {id: user.id, email: user.email, 'nama lengkap': user.namaLengkap},
            message: `Berhasil masuk ke ${user.namaLengkap}`,
            token
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function getUser(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const token = request.auth.artifacts.token
    let userId
    if (typeof token === "string") {
        const decode = Jwt.token.decode(token)
        if (decode !== undefined) {
            userId = decode.decoded.payload.userId
        }
    }
    const { prisma } = request.server.app

    try {
        const user = await prisma.pengguna.findUnique({
            where: {
                id: userId
            },
            select: {
                id: true,
                namaLengkap: true,
                email: true,
                foto: true,
            },
        })
        return h.response({
            statusCode: 200,
            message: 'User berhasil ditampilkan',
            user
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function updateUser(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const token = request.auth.artifacts.token
    let userId
    if (typeof token === "string") {
        const decode = Jwt.token.decode(token)
        if (decode !== undefined) {
            userId = decode.decoded.payload.userId
        }
    }
    const { prisma } = request.server.app
    const { namaLengkap, email, password, foto } = request.payload as any

    try {
        const user = await prisma.pengguna.update({
            where: {
                id: userId
            },
            data: {
                namaLengkap: namaLengkap,
                email: email,
                password: bcrypt.hashSync(password, 10),
                foto: foto,
            },
            select: {
                id: true,
                namaLengkap: true,
                email: true,
                foto: true,
            },
        })
        return h.response({
            statusCode: 200,
            message: 'User berhasil diupdate',
            user
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function ambilKursus(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const token = request.auth.artifacts.token
    let userId
    if (typeof token === "string") {
        const decode = Jwt.token.decode(token)
        if (decode !== undefined) {
            userId = decode.decoded.payload.userId
        }
    }
    const { prisma } = request.server.app
    const { id } = request.params as { id: string }

    try {
        const jumlahModul = await prisma.modul.count({
            where: {
                idKursus: parseInt(id)
            }
        })

        const checkAmbilKursus = await prisma.ambilkursus.findFirst({
            where: {
                AND: [
                    {
                        idKursus: parseInt(id)
                    },
                    {
                        idPengguna: userId
                    },
                ]
            }
        })

        if (checkAmbilKursus) {
            return h.response({
                statusCode: 400,
                message: 'Kursus sudah diambil'
            }).code(400)
        }

        const ambilKursus = await prisma.ambilkursus.create({
            data: {
                kursus: {
                    connect: {
                        id: parseInt(id)
                    }
                },
                pengguna: {
                    connect: { id: userId }
                },
                jumlahModul: jumlahModul,
                modulSekarang: 0,
                statusSelesai: false,
            }
        })
        return h.response({
            statusCode: 200,
            message: 'Kursus berhasil diambil',
            ambilKursus
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function updateAmbilKursus(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const { id, status } = request.query as { id: string, status: string }

    try {
        const kursus = await prisma.ambilkursus.findUnique({
            where: {
                id: parseInt(id)
            }
        })

        if (kursus !== null) {
            if (status === 'completed') {
                const ambilKursus = await prisma.ambilkursus.update({
                    where: {
                        id: parseInt(id)
                    },
                    data: {
                        modulSekarang: kursus.jumlahModul,
                        statusSelesai: true
                    }
                })
                return h.response({
                    statusCode: 200,
                    message: 'Kursus berhasil diselesaikan',
                    ambilKursus
                }).code(200)
            }
            else if (status === 'next') {
                const ambilKursus = await prisma.ambilkursus.update({
                    where: {
                        id: parseInt(id)
                    },
                    data: {
                        modulSekarang: kursus.modulSekarang + 1
                    }
                })
                return h.response({
                    statusCode: 200,
                    message: 'Kursus berhasil diupdate',
                    ambilKursus
                }).code(200)
            }
        }
        return h.response({
            statusCode: 200,
            message: 'Tidak ada kursus yang diupdate',
            ambilKursus
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function rekomendasiKursus(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const { kursus1, kursus2, kursus3, kursus4, kursus5 } = request.payload as any

    try {
        const kursus = await prisma.kursus.findMany({
            where: {
                OR: [
                    {
                        namaKursus: kursus1
                    },
                    {
                        namaKursus: kursus2
                    },
                    {
                        namaKursus: kursus3
                    },
                    {
                        namaKursus: kursus4
                    },
                    {
                        namaKursus: kursus5
                    }
                ]
            }
        })
        return h.response({
            statusCode: 200,
            message: 'Kursus berhasil diambil',
            kursus
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function rekomendasiDesa(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const { desa } = request.params

    try {
        let masalahdesa: any[] = []
        const recDesa = await prisma.desa.findMany({
            where: {
                namaDesa: desa
            }
        })

        if (recDesa.length !== undefined) {
            masalahdesa = await prisma.masalah.findMany({
                where: {
                    idDesa: recDesa[0]?.id as number
                }
            })
        }

        return h.response({
            statusCode: 200,
            message: 'Rekomendasi desa dan masalah berhasil ditampilkan',
            recDesa,
            masalahdesa
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function ambilMasalah(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const token = request.auth.artifacts.token
    let userId
    if (typeof token === "string") {
        const decode = Jwt.token.decode(token)
        if (decode !== undefined) {
            userId = decode.decoded.payload.userId
        }
    }
    const { prisma } = request.server.app
    const { id } = request.params as { id: string }

    try {
        const masalah = await prisma.masalah.findUnique({
            where: {
                id: parseInt(id)
            }
        })
        const ambilmasalah = await prisma.ambilmasalah.create({
            data: {
                masalah: {
                    connect: {
                        id: masalah?.id
                    }
                },
                pengguna: {
                    connect: { id: userId }
                },
                statusSelesai: false,
            }
        })
        return h.response({
            statusCode: 200,
            message: 'Masalah berhasil diambil',
            masalah,
            ambilmasalah
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }
}

async function selesaiMasalah(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const { id } = request.params as { id: string }

    try {
        const ambilmasalah = await prisma.ambilmasalah.update({
            where: {
                id: parseInt(id)
            },
            data: {
                statusSelesai: true
            }
        })
        return h.response({
            statusCode: 200,
            message: 'Masalah berhasil diselesaikan',
            ambilmasalah
        }).code(200)
    } catch (err) {
        console.log(err)
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500)
    }

}

export default {
    registerUser,
    loginUser,
    getUser,
    updateUser,
    ambilKursus,
    updateAmbilKursus,
    rekomendasiKursus,
    rekomendasiDesa,
    ambilMasalah,
    selesaiMasalah,
}
