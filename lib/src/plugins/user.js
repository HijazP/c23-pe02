var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import userHandler from './handlers/userHandler';
const userPlugin = {
    name: 'app/user',
    dependencies: ['prisma'],
    register: function (server) {
        return __awaiter(this, void 0, void 0, function* () {
            server.route([
                {
                    method: 'POST',
                    path: '/user/register',
                    handler: userHandler.registerUser,
                    options: {
                        auth: false,
                    }
                },
                {
                    method: 'POST',
                    path: '/user/login',
                    handler: userHandler.loginUser,
                    options: {
                        auth: false,
                    }
                },
                {
                    method: 'PUT',
                    path: '/user/update',
                    handler: userHandler.updateUser,
                },
                {
                    method: 'POST',
                    path: '/user/course/{id}',
                    handler: userHandler.ambilKursus,
                },
                {
                    method: 'PUT',
                    path: '/user/course',
                    handler: userHandler.updateAmbilKursus,
                },
                {
                    method: 'POST',
                    path: '/user/course/recommendation/list',
                    handler: userHandler.rekomendasiKursus,
                },
                {
                    method: 'GET',
                    path: '/user/course/recommendation/{desa}',
                    handler: userHandler.rekomendasiDesa,
                },
                {
                    method: 'GET',
                    path: '/user/problem/{id}',
                    handler: userHandler.ambilMasalah
                },
                {
                    method: 'PUT',
                    path: '/user/problem/{id}',
                    handler: userHandler.selesaiMasalah
                },
            ]);
        });
    },
};
export default userPlugin;
//# sourceMappingURL=user.js.map