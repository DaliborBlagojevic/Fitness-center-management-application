// example-dto.model.ts
import { Rate } from './Rate';
import { Facility } from './Facility';
import { Comment} from './Comment'
import { User } from './User';

export interface Review {
  id?: number;
  createdAt?: Date;
  exerciseCount?: number;
  hidden?: boolean;
  rates?: Rate[];
  facility?: Facility;
  comments?: Comment[]; 
  user?: User;
}
