import { Book } from "../entities/book.entity";

export class ResponseBookDto{
    id:number;
    title: string;
    publisher: string;
    edition: number;
    author: string;
    createdAt?: Date;
    updatedAt?: Date;

    constructor(book:Book){
        this.id = book.id;
        this.title = book.title;
        this.publisher = book.publisher;
        this.edition = book.edition;
        this.author = book.author?.name ;
        this.createdAt = book.createdAt;
        this.updatedAt = book.updatedAt;
    }
}