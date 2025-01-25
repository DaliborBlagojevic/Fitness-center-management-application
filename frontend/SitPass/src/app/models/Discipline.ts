import { Facility } from "./Facility";

export interface Discipline {
    id?: number,
    name: String,
    facility?: Facility,
    isDeleted?: boolean | null
}