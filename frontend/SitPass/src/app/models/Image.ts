import { Facility } from "./Facility";
import { User } from "./User";

export interface Image {
    id?: number;
    path: string;
    facility?: Facility;
    user?: User;
}