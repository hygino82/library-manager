export type Book = {
  id: number;
  title: string;
  author: string;
  personalCode: string;
  edition: number;
  publisher: string;
  totalPages: number;
  bookStatus: string;
};

export type BookPage = {
  content: Book[];
  page: Page;
}

export type Page = {
  size: number;
  number: number;
  totalPages: number;
  totalElements: number;
}
