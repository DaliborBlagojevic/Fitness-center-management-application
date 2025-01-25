// rate.model.ts
import { Review } from './Review'; // Uveri se da imaš odgovarajući Review model

export interface Rate {
  id?: number;          // `Long` u Javi se preslikava na `number` u TypeScript-u
  equipment?: number;   // `Integer` u Javi se preslikava na `number` u TypeScript-u
  staff?: number;       // Isto kao gore
  hygene?: number;      // Isto kao gore
  space?: number;       // Isto kao gore
  review?: Review;      // Preslikava `Review` objekat
  average?: number;     // `Double` u Javi se preslikava na `number` u TypeScript-u
}
