import { Facility } from "./Facility";
import { User } from "./User";

export interface Exercise {
    
    id?: number;
    fromDate?: Date; 
    untilDate?: Date; 
    facility?: Facility;
    user?: User;

}