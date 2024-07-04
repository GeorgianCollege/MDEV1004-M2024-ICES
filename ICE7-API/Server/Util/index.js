"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.SanitizeArray = SanitizeArray;
exports.GenerateToken = GenerateToken;
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
const db_1 = __importDefault(require("../Config/db"));
function SanitizeArray(inputString) {
    let unsanitizedArray = inputString.split(",");
    let sanitizedArray = Array();
    for (const unsanitizedString of unsanitizedArray) {
        sanitizedArray.push(unsanitizedString.trim());
    }
    return sanitizedArray;
}
function GenerateToken(user) {
    const payload = {
        id: user.id,
        DisplayName: user.displayName,
        username: user.username,
        EmailAddress: user.emailAddress
    };
    const jwtOptions = {
        expiresIn: 604800
    };
    return jsonwebtoken_1.default.sign(payload, db_1.default.secret, jwtOptions);
}
//# sourceMappingURL=index.js.map