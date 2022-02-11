import { Role } from "./role.model";

export class User {
    id!: string;
    username!: string;
    firstName!: string;
    lastName!: string;
    email!: string;
    password!: string;
    roles!: Role[];
    accessToken: string = '';
}
