var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import desaHandler from './handlers/desaHandler';
const desaPlugin = {
    name: 'app/desa',
    dependencies: ['prisma'],
    register: function (server) {
        return __awaiter(this, void 0, void 0, function* () {
            server.route([
                {
                    method: 'POST',
                    path: '/desa/register',
                    handler: desaHandler.registerDesa,
                    options: {
                        auth: false,
                    }
                },
                {
                    method: 'POST',
                    path: '/desa/login',
                    handler: desaHandler.loginDesa,
                    options: {
                        auth: false,
                    }
                },
                {
                    method: 'PUT',
                    path: '/desa/update',
                    handler: desaHandler.updateDesa,
                },
                {
                    method: 'POST',
                    path: '/desa/problem',
                    handler: desaHandler.addProblem,
                },
                {
                    method: 'GET',
                    path: '/desa/problem',
                    handler: desaHandler.getAllProblems,
                },
                {
                    method: 'GET',
                    path: '/desa/problem/{id}',
                    handler: desaHandler.getProblemById,
                },
                {
                    method: 'PUT',
                    path: '/desa/problem/{id}',
                    handler: desaHandler.updateProblemById,
                },
                {
                    method: 'DELETE',
                    path: '/desa/problem/{id}',
                    handler: desaHandler.deleteProblemById,
                },
            ]);
        });
    },
};
export default desaPlugin;
//# sourceMappingURL=desa.js.map