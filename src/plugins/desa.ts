import Hapi from '@hapi/hapi'
import Joi from '@hapi/joi'
import Boom from "@hapi/boom";

// plugin to instantiate Prisma Client
const desaPlugin = {
    name: 'app/desa',
    dependencies: ['prisma'],
    register: async function(server: Hapi.Server) {
        // here you can use server.app.prisma
        server.route([
            {
                method: 'GET',
                path: '/desa/{desaId}',
                handler: getUserHandler,
                options: {
                    validate: {
                        // params: Joi.object({
                        //     userId: Joi.number().integer(),
                        // }),
                    },
                },
            },
            {
                method: 'POST',
                path: '/desa',
                handler: registerHandler,
                options: {
                    validate: {
                        //payload: userInputValidator,
                        failAction: (request, h, err) => {
                            throw err
                        },
                    },
                },
            },
            {
                method: 'DELETE',
                path: '/desa/{desaId}',
                handler: deleteDesaHandler,
                options: {
                    validate: {
                        // params: Joi.object({
                        //     userId: Joi.number().integer(),
                        // }),
                    },
                },
            },
        ])
    },
}
export default desaPlugin

const userInputValidator = Joi.object({
    email: Joi.string().email().required(),
    password: Joi.string().required(),
    namaDesa: Joi.string().required(),
    telepon: Joi.string().required(),
    lokasiDesa: Joi.string().required(),
    longitude: Joi.number().required(),
    latitude: Joi.number().required(),
})

interface UserInput {
    email: string
    password: string
    namaDesa: string
    telepon: string
    lokasiDesa: string
    longitude: number
    latitude: number
}

async function registerHandler(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const payload = request.payload as UserInput

    try {
        const createdDesa = await prisma.desa.create({
            data: {
                email: payload.email,
                password: payload.password,
                namaDesa: payload.namaDesa,
                telepon: payload.telepon,
                lokasiDesa: payload.lokasiDesa,
                longitude: payload.longitude,
                latitude: payload.latitude,
            },
            select: {
                id: true,
            },
        })
        return h.response(createdDesa).code(201)
    } catch (err) {
        console.log(err)
    }
}

async function getUserHandler(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const desaId = parseInt(request.params.desaId, 10)

    try {
        const desa = await prisma.desa.findUnique({
            where: {
                id: desaId,
            },
        })
        if (!desa) {
            return h.response().code(404)
        } else {
            return h.response(desa).code(200)
        }
    } catch (err) {
        console.log(err)
        return Boom.badImplementation('terrible implementation')
    }
}

async function deleteDesaHandler(request: Hapi.Request, h: Hapi.ResponseToolkit) {
    const { prisma } = request.server.app
    const desaId = parseInt(request.params.desaId, 10)

    try {
        await prisma.desa.delete({
            where: {
                id: desaId,
            },
        })
        return h.response().code(204)
    } catch (err) {
        console.log(err)
        return h.response().code(500)
    }
}
