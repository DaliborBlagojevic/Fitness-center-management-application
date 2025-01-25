export interface User {
    id?: number;
    email: string;
    password: string;
    name?: string;
    surname?: string;
    createdAt?: Date;
    phoneNumber?: string;
    birthday?: Date;
    address?: string;
    city?: string;
    zipCode?: string;
    isDeleted?: boolean | null;
  }