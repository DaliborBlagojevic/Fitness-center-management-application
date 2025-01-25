
import { Facility } from "./Facility";
import { DayOfWeek } from "./DayOfWeek";

export interface WorkDay {
    id?: number;
    validFrom?: Date;
    day: DayOfWeek;
    from: Date;
    until: Date;
    facility?: Facility;
    isDeleted?: boolean | null;
}