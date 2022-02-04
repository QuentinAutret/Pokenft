import { User } from "./user.model";

export class Nft {
    id!: string;
    name!: string;
    creator!: string;
    filepath!: string;
    price!: number;
    forSale!: boolean;
    owner!: User;
}
