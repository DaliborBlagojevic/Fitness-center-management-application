import { Facility } from "./Facility";
import { User } from "./User";

export interface Menages {
    fromDate?: Date; 
    untilDate?: Date; 
    user?: User;
    facility?: Facility;


}