var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const statusPlugin = {
    name: 'app/status',
    register: function (server) {
        return __awaiter(this, void 0, void 0, function* () {
            server.route({
                method: 'GET',
                path: '/',
                handler: (_, h) => {
                    return h.response({ up: true }).code(200);
                },
                options: {
                    auth: false,
                }
            });
        });
    },
};
export default statusPlugin;
//# sourceMappingURL=status.js.map