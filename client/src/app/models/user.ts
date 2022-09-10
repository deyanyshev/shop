import {Role} from './enums/role';

export class User {
  id: number;
  login: string;
  password: string;
  name: string;
  email: string;
  role: Role;
}
