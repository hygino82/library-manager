import { User, UserType } from '../entities/user.entity';

export class ResponseUserDto {
  id: number;
  fullName: string;
  email: string;
  phone: string;
  booklist: string[];
  userType: UserType;
  createdAt: Date;
  updatedAt: Date;
  password: string;

  constructor(user: User) {
    this.id = user.id;
    this.email = user.email;
    this.phone = user.phone;
    this.fullName = `${user.firstName} ${user.lastName}`;
    this.userType = user.userType;
    this.createdAt = user.createdAt;
    this.updatedAt = user.updatedAt;
    this.booklist = user.bookList.map((book) => book.title);
    this.password = user.password;
  }
}
