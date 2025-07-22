import { Author } from '../entities/author.entity';

export class ResponseAutorDto {
  id: number;
  name: string;
  country: string;
  createdAt: Date;
  updatedAt: Date;
  totalBooks: number;

  constructor(author: Author) {
    this.id = author.id;
    this.name = author.name;
    this.country = author.country;
    this.createdAt = author.createdAt;
    this.updatedAt = author.updatedAt;
    this.totalBooks = author.books?.length ?? 0;
  }
}
