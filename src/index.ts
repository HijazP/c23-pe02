import Hapi from '@hapi/hapi'
import prisma from './plugins/prisma'
import Jwt from '@hapi/jwt'
import Inert from '@hapi/inert'
import status from './plugins/status'
import desa from './plugins/desa'
import user from './plugins/user'


const server: Hapi.Server = Hapi.server({
    port: process.env.PORT || 3000,
    host: process.env.HOST || 'localhost',
})

export async function start(): Promise<Hapi.Server> {
    await server.register([
        prisma,
        status,
        desa,
        user
    ])
    await server.register(Inert)
    await server.register(Jwt)
    server.auth.strategy('bigstem-strategy', 'jwt', {
        keys: process.env.JWT_SECRET,
        verify: {
            aud: false,
            iss: false,
            sub: false,
            nbf: true,
            exp: true,
            maxAgeSec: 14400, // 4 hours
            timeSkewSec: 15,
        },
        validate: (decoded: any) => {
            return {
                isValid: true,
                credentials: decoded
            }
        }
    })
    server.auth.default('bigstem-strategy')

    await server.start()
    return server
}

process.on('unhandledRejection', async (err) => {
    await server.app.prisma.$disconnect()
    console.log(err)
    process.exit(1)
})

start()
    .then((server) => {
        console.log(`🚀 Server ready at: ${server.info.uri}`)
    })
    .catch((err) => {
        console.log(err)
    })
