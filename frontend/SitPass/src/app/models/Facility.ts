import { WorkDay } from "./WorkDay";
import { Discipline } from "./Discipline";

export interface Facility {
    id?: number;
    name?: string;
    description: string;
    createdAt?: Date;
    address: string;
    city: string;
    totalRating?: number;
    active?: boolean;
    workDays?: WorkDay[];
    images?: any[];
    disciplines?: Discipline[];
    isDeleted?: boolean | null;
}