import { UserType } from '../entities/user.entity';

export class CreateUserDto {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phone: string;
  userType: UserType;
}
