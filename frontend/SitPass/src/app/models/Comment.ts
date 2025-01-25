// comment-dto.model.ts
import { Review } from "./Review";

export interface Comment {
  id?: number;
  text: string;
  createdAt?: Date; 
  review?: Review;  
}
